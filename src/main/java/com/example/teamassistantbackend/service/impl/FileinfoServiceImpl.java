package com.example.teamassistantbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.entity.Fileinfo;
import com.example.teamassistantbackend.entity.Filemanager;
import com.example.teamassistantbackend.entity.Personinfo;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.mapper.FilemanagerMapper;
import com.example.teamassistantbackend.service.FileinfoService;
import com.example.teamassistantbackend.mapper.FileinfoMapper;
import com.example.teamassistantbackend.service.PersoninfoService;
import com.example.teamassistantbackend.utils.FileUtils;
import com.example.teamassistantbackend.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
* @author huang
* @description 针对表【fileinfo(文件信息表)】的数据库操作Service实现
* @createDate 2023-10-26 23:43:40
*/
@Service
public class FileinfoServiceImpl extends ServiceImpl<FileinfoMapper, Fileinfo>
    implements FileinfoService{

    @Value("${Variable.FilePath}")
    private String homePath;
    @Resource
    PersoninfoService personinfoService;
    @Resource
    FileinfoMapper fileinfoMapper;
    @Resource
    FilemanagerMapper filemanagerMapper;
    @Override
    public JSONObject saveFileByType(MultipartFile fileInfo, String type) {
        if (fileInfo.isEmpty() || StringUtils.isEmpty(type)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请选择上传文件！");
        }
        // 要保存文件的目录
        String saveDirectory = homePath;// 系统文件路径
        String relativePath = type + File.separator;// 相对路径
        // 获取当前操作人信息
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        relativePath += curUserInfo.getString("name") + File.separator;
        saveDirectory += relativePath;

        // 确保目录存在
        File directory = new File(saveDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 生成文件名和MD5编码
        String originalFilename = fileInfo.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String md5 = generateMD5(fileInfo);
        String fileName = md5 + fileExtension;

        // 检查相同MD5编码+文件类型的文件是否存在
        if (checkFileExists(saveDirectory,fileName)) {
            // 返回已存在文件的信息

            QueryWrapper<Fileinfo> fileinfoQueryWrapper = new QueryWrapper<>();
            fileinfoQueryWrapper.eq("cFIMD5Code",md5);
            fileinfoQueryWrapper
                    .like("cFIFilePath",type + File.separator + File.separator
                    + curUserInfo.getString("name") + File.separator + File.separator
                    + fileName);
            Fileinfo fileinfo = fileinfoMapper.selectOne(fileinfoQueryWrapper);
            // 存储文件管理信息
            Filemanager filemanager = new Filemanager();
            filemanager.setIFM_iFICode(fileinfo.getIFICode());
            filemanager.setCFMType(type);
            filemanager.setCFMManagerCode(curUserInfo.getString("code"));
            filemanager.setDFMHandleTime(new Date());
            filemanager.setCFMHandleCode(curUserInfo.getString("code"));
            filemanagerMapper.insert(filemanager);

            // 返回JSON对象
            JSONObject result = new JSONObject();
            result.put("message","文件上传成功！");
            result.put("fileName",originalFilename);
            result.put("fileId",filemanager.getIFMId());
            return result;
        }

        try {
            // 保存文件到目标位置
            File targetFile = new File(saveDirectory, fileName);
            Files.copy(fileInfo.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // 存储文件信息
        Fileinfo fileinfo = new Fileinfo();
        fileinfo.setCFIFileName(originalFilename);
        fileinfo.setCFIMD5Code(md5);
        fileinfo.setCFIFilePath(relativePath + fileName);
        fileinfo.setDFICreateTime(new Date());
        fileinfo.setCFI_cPICode(curUserInfo.getString("code"));
        fileinfo.setIFIFileSize(fileInfo.getSize());
        fileinfo.setCFIMimeType(getFileType(fileInfo));
        fileinfoMapper.insert(fileinfo);

        // 存储文件管理信息
        Filemanager filemanager = new Filemanager();
        filemanager.setIFM_iFICode(fileinfo.getIFICode());
        filemanager.setCFMType(type);
        filemanager.setCFMManagerCode(curUserInfo.getString("code"));
        filemanager.setDFMHandleTime(new Date());
        filemanager.setCFMHandleCode(curUserInfo.getString("code"));
        filemanagerMapper.insert(filemanager);

        // 返回JSON对象
        JSONObject result = new JSONObject();
        result.put("message","文件上传成功！");
        result.put("fileName",originalFilename);
        result.put("fileId",filemanager.getIFMId());
        return result;
    }

    // 根据文件管理ID进行文件信息查询
    @Override
    public JSONObject getFilePath(int fileId) {
        if (fileId == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 获取文件信息
        JSONObject result = fileinfoMapper.getFileInfo(fileId);
        return result;
    }

    @Override
    public JSONObject deleteFile(int fileId) {
        if (fileId == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Filemanager filemanager = filemanagerMapper.selectById(fileId);
        if (filemanager == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 删除文件
        filemanager.setDataState("1");
        filemanagerMapper.updateById(filemanager);
        JSONObject result = new JSONObject();
        result.put("message","文件删除成功！");
        return result;
    }

    @Override
    public void handleFile(JSONObject handleInfo) {
        // 获取人员信息
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        List<JSONObject> fileInfoList = fileinfoMapper.getFileInfoList(handleInfo.getString("fileIds"));
        for (JSONObject fileInfo : fileInfoList) {
            // 获取文件信息
            String pathA = homePath + fileInfo.getString("cFIFilePath");
            String pathB = homePath + handleInfo.getString("type") + File.separator
                    + handleInfo.getString("typeId") + File.separator
                    + curUserInfo.getString("code") + File.separator;
            File fileA = new File(pathA);
            File fileB = new File(pathB);

            // 处理文件信息
            // 转移
            if (fileA.exists()) {
                // 检查路径B是否存在，不存在则创建
                if (!fileB.exists()) {
                    fileB.mkdirs(); // 创建多级目录
                }

                // 获取文件名
                String fileName = fileA.getName();
                // 构造目标路径
                File destFile = new File(fileB, fileName);

                // 转移文件
                if (fileA.renameTo(destFile)) {
                    // 转移成功操作
                    // 修改文件管理信息
                    Filemanager filemanager = filemanagerMapper.selectById(fileInfo.getString("iFMId"));
                    filemanager.setCFMTypeId(handleInfo.getString("typeId"));
                    filemanager.setDFMHandleTime(new Date());
                    filemanager.setCFMHandleCode(curUserInfo.getString("code"));
                    filemanagerMapper.updateById(filemanager);
                    // 修改文件基础信息
                    Fileinfo fileinfo = fileinfoMapper.selectById(fileInfo.getString("iFICode"));
                    fileinfo.setCFIFilePath(handleInfo.getString("type") + File.separator
                            + handleInfo.getString("typeId") + File.separator
                            + curUserInfo.getString("code") + File.separator
                            + fileinfo.getCFIMD5Code() + "." + fileinfo.getCFIMimeType()
                    );
                    fileinfoMapper.updateById(fileinfo);
                } else {
                    throw new BusinessException(ErrorCode.OPERATION_ERROR,"文件处理失败！");
                }
            } else {
                throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,fileInfo.getString("cFIFileName")+"文件不存在！");
            }
        }
    }

    @Override
    public List<JSONObject> getFileInfoList(JSONObject fileInfo) {
        if (fileInfo == null || fileInfo.get("type") == null || fileInfo.get("typeId") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 获取文件信息
        return fileinfoMapper.getTableFileList(fileInfo);
    }

    @Override
    public void updateFileManagerTypeId(String fileDoIds, Integer typeId) {
        filemanagerMapper.updateTypeId(fileDoIds, typeId);
    }

    @Override
    public JSONObject exportFile(MultipartFile fileInfo) {
        // 鉴别是否是xlsx文件
        if (!fileInfo.getOriginalFilename().endsWith(".xlsx")) {
            JSONObject result = new JSONObject();
            result.put("message", "文件格式不正确,请上传XLSX文件");
            return result;
        }

        // 获取表格数据
        List<String[]> data = new ArrayList<>();
        try (InputStream inputStream = fileInfo.getInputStream();
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String[] rowData = new String[row.getLastCellNum()];
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                rowData[j] = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                rowData[j] = String.valueOf(cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                rowData[j] = String.valueOf(cell.getBooleanCellValue());
                                break;
                            default:
                                rowData[j] = "";
                                break;
                        }
                    } else {
                        rowData[j] = "";
                    }
                }
                data.add(rowData);
            }
        } catch (IOException e) {
            JSONObject result = new JSONObject();
            result.put("message", "读取文件时发生错误");
            return result;
        }

        // 处理数据
        List<JSONObject> result = new ArrayList<>();
        List<String> codes = new ArrayList<>();
        int index = 0;
        for (String[] row : data) {
            if ("END".equals(row[0])) {
                break;
            }
            index++;
            JSONObject record = new JSONObject();
            record.put("personCode", row[0]);
            record.put("personName", row[1]);
            record.put("gender", row[2]);
            record.put("registeredResidence", row[3]);
            record.put("phoneNumber", row[4]);
            record.put("email", row[5]);
            record.put("enabled", "是".equals(row[6]));
            codes.add(row[0]);
            result.add(record);

            // 数据检查
            if (StringUtils.isEmpty(record.getString("personCode"))) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"第"+index+"行人员编码不允许为空！");
            }
            if (StringUtils.isEmpty(record.getString("personName"))) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"第"+index+"行人员名称不允许为空！");
            }
            if (StringUtils.isEmpty(record.getString("gender"))) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"第"+index+"行性别不允许为空！");
            }
            if (StringUtils.isEmpty(record.getString("registeredResidence"))) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"第"+index+"行户籍地不允许为空！");
            }
            if (StringUtils.isEmpty(record.getString("phoneNumber"))) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"第"+index+"行联系电话不允许为空！");
            }
            if (StringUtils.isEmpty(record.getString("email"))) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"第"+index+"行邮箱不允许为空！");
            }
            if (StringUtils.isEmpty(record.getString("enabled"))) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"第"+index+"行是否启用不允许为空！");
            }
        }

        // 删除已有数据
        personinfoService.removeBatchByIds(codes);
        // 存入数据
        for (JSONObject info : result) {
            Personinfo personinfo = new Personinfo();
            personinfo.setCPICode(info.getString("personCode"));
            personinfo.setCPIName(info.getString("personName"));
            personinfo.setCPIGender(info.getString("gender"));
            personinfo.setCPIHometown(info.getString("registeredResidence"));
            personinfo.setCPIContactNumber(info.getString("phoneNumber"));
            personinfo.setCPIEmail(info.getString("email"));
            personinfo.setDPICreateTime(new Date());
            personinfo.setBPIIsEnabled(info.getBoolean("enabled"));
            personinfoService.save(personinfo);
        }

        // 返回结果
        JSONObject jsonResult = new JSONObject();
        jsonResult.put("message", "导入成功!");
        return jsonResult;
    }

    @Override
    public JSONObject handleTaskZipFile(int iTMId) throws Exception {
        String fileName = System.currentTimeMillis() + "_FileGenerate";
        String sourceFilePath = homePath + "generate" + File.separator + "task" + File.separator + fileName + File.separator;
        String targetPath = homePath + "generate" + File.separator + "task" + File.separator + iTMId + "_" + fileName + File.separator;

        createFolderIfNotExists(sourceFilePath); // 创建文件夹
        createFolderIfNotExists(targetPath); // 创建文件夹

        // 获取文件信息
        List<JSONObject> files = filemanagerMapper.getFileInfoList(iTMId,"doTask");

        // 将文件信息转移到sourceFilePath路径下
        for (JSONObject fileInfo : files) {
            String tempFileName = fileInfo.getString("cFIFileName");
            if (FileUtils.isFileExists(sourceFilePath + tempFileName)) {
                tempFileName = System.currentTimeMillis() + "_" + tempFileName;
            }
            FileUtils.copyOrMoveFile(homePath + fileInfo.getString("cFIFilePath"),sourceFilePath + tempFileName,false);
        }

        createZipFile(sourceFilePath, targetPath, fileName + ".zip");

        // 删除临时源文件
        File folder = new File(sourceFilePath);
        deleteFolder(folder);

        JSONObject fileInfo = new JSONObject();
        fileInfo.put("fileName",fileName+".zip");
        fileInfo.put("filePath",targetPath);
        return fileInfo;
    }

    @Override
    public void deleteFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file); // 递归删除子文件夹
                    } else {
                        file.delete(); // 删除文件
                    }
                }
            }
            folder.delete(); // 删除目录
        }
    }

    /**
     * @param sourceFilePath 待压缩文件（夹）路径
     * @param targetPath 压缩文件所在目录
     * @param zipFileName 压缩后的文件名称{.zip结尾}
     * @return
     * @Description: 创建zip文件
     */
    public static boolean createZipFile(String sourceFilePath, String targetPath, String zipFileName) throws Exception{

        boolean flag = false;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        // 要压缩的文件资源
        File sourceFile = new File(sourceFilePath);
        // zip文件存放路径
        String zipPath = "";

        File TempFilePath = new File(targetPath);
        if (!TempFilePath.exists()) {
            TempFilePath.mkdirs();
        }

        if(null != targetPath && !"".equals(targetPath)){
            zipPath = targetPath + File.separator + zipFileName;
        } else {
            zipPath = new File(sourceFilePath).getParent() + File.separator + zipFileName;
        }

        if (!sourceFile.exists()) {
            throw  new Exception("待压缩的文件目录：" + sourceFilePath + "不存在.");
        }

        try {
            File zipFile = new File(zipPath);
            if (zipFile.exists()) {
                throw  new Exception(zipPath + "目录下存在名字为:" + zipFileName + ".zip" + "打包文件.");
            } else {
                File[] sourceFiles = sourceFile.listFiles();
                fos = new FileOutputStream(zipPath);
                zos = new ZipOutputStream(new BufferedOutputStream(fos));
                // 生成压缩文件
                writeZip(sourceFile, "", zos);
                flag = true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (null != zos) {
                    zos.close();
                }
                if (null != fos){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * @param file
     * @param parentPath
     * @param zos
     * @Description:
     */
    private static void writeZip(File file, String parentPath, ZipOutputStream zos) throws Exception {
        if (file.exists()) {
            // 处理文件夹
            if (file.isDirectory()) {
                parentPath += file.getName() + File.separator;
                File[] files = file.listFiles();
                if (files.length != 0) {
                    for (File f : files) {
                        // 递归调用
                        writeZip(f, parentPath, zos);
                    }
                } else {
                    // 空目录则创建当前目录的ZipEntry
                    try {
                        zos.putNextEntry(new ZipEntry(parentPath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());
                    zos.putNextEntry(ze);
                    byte[] content = new byte[1024];
                    int len;
                    while ((len = fis.read(content)) != -1) {
                        zos.write(content, 0, len);
                        zos.flush();
                    }
                } catch (FileNotFoundException e) {
                    throw  new Exception("创建ZIP文件失败", e);
                } catch (IOException e) {
                    throw  new Exception("创建ZIP文件失败", e);
                } finally {
                    try {
                        if (fis != null) {
                            fis.close();
                        }
                    } catch (IOException e) {
                        throw  new Exception("创建ZIP文件失败", e);
                    }
                }
            }
        }
    }

    private void createFolderIfNotExists(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            try {
                folder.mkdirs();
            } catch (Exception e) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
        }
    }

    /**
     * MD5编码处理
     */
    private String generateMD5(MultipartFile fileInfo) {
        try {
            byte[] bytes = fileInfo.getBytes();
            return DigestUtils.md5DigestAsHex(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检查文件是否存在
     */
    private boolean checkFileExists(String path,String fileName) {
        // 实现检查相同MD5编码+文件类型的文件是否存在的逻辑
        File targetFile = new File(path, fileName);
        return targetFile.exists();
    }


    /**
     * 获取文件类型
     */
    private String getFileType(MultipartFile fileInfo) {
        // 获取文件类型的逻辑
        // 这里简单示例，直接从文件名中提取后缀作为文件类型
        String originalFilename = fileInfo.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        return fileExtension;
    }
}