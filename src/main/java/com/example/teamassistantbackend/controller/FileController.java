package com.example.teamassistantbackend.controller;

import cn.hutool.log.Log;
import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.common.ResultUtils;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.service.CollectInfoService;
import com.example.teamassistantbackend.service.FileinfoService;
import com.example.teamassistantbackend.utils.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

/**
 * @Author: xizai
 * @Date: 2024-03-26
 */
@Controller
@RequestMapping("/File")
public class FileController {
    @Resource
    FileinfoService fileinfoService;
    @Resource
    CollectInfoService collectInfoService;

    // 日志信息输出
    private Log log = Log.get();

    @Value("${Variable.FilePath}")
    private String homePath;
    /**
     * 上传文件
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public BaseResponse<JSONObject> uploadFile(@RequestParam("file") MultipartFile fileInfo, @RequestParam("type") String type){
        return ResultUtils.success(fileinfoService.saveFileByType(fileInfo,type));
    }

    /**
     * 导入人员信息
     */
    @PostMapping("/exportFile")
    @ResponseBody
    public BaseResponse<JSONObject> exportFile(@RequestParam("file") MultipartFile fileInfo){
        return ResultUtils.success(fileinfoService.exportFile(fileInfo));
    }

    @PostMapping("/deleteFile")
    @ResponseBody
    public BaseResponse<JSONObject> deleteFile(@RequestParam("fileId") int fileId){
        return ResultUtils.success(fileinfoService.deleteFile(fileId));
    }

    @PostMapping("/downFile")
    public void downFile(@RequestParam("fileId") int fileId,
                               HttpServletResponse response) throws Exception {
        if (fileId == 0)
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        JSONObject condition = new JSONObject();
        condition.put("fileId",fileId);
        JSONObject fileInfo = fileinfoService.getFilePath(fileId);
        if (StringUtils.isEmpty(fileInfo))
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        String fileName = fileInfo.getString("cFIFileName");
        // 新建文件流，从磁盘读取文件流
        try (FileInputStream fis = new FileInputStream(homePath+fileInfo.getString("cFIFilePath"));
             BufferedInputStream bis = new BufferedInputStream(fis);
             OutputStream os = response.getOutputStream()) {    // OutputStream 是文件写出流，将文件下载到浏览器客户端
            // 新建字节数组，长度是文件的大小，比如文件 6kb, bis.available() = 1024 * 6
            byte[] bytes = new byte[bis.available()];
            // 从文件流读取字节到字节数组中
            bis.read(bytes);
            // 重置 response
            response.reset();
            // 设置 response 的下载响应头
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));  // 注意，这里要设置文件名的编码，否则中文的文件名下载后不显示
            // 写出字节数组到输出流
            os.write(bytes);
            // 刷新输出流
            os.flush();
        } catch (Exception e) {
            log.error(e);
        }
    }

    /**
     * 下载模板文件
     */
    @PostMapping("/downTemplateFile")
    public void downTemplateFile(@RequestParam("fileName") String fileName,
                               HttpServletResponse response) throws Exception {
        if (StringUtils.isEmpty(fileName))
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        // 新建文件流，从磁盘读取文件流
        String filePath = homePath + "templateFile" + File.separator ;
        File targetFile = new File(filePath, fileName);
        if (!targetFile.exists()) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        try (FileInputStream fis = new FileInputStream(filePath+fileName);
             BufferedInputStream bis = new BufferedInputStream(fis);
             OutputStream os = response.getOutputStream()) {    // OutputStream 是文件写出流，将文件下载到浏览器客户端
            // 新建字节数组，长度是文件的大小，比如文件 6kb, bis.available() = 1024 * 6
            byte[] bytes = new byte[bis.available()];
            // 从文件流读取字节到字节数组中
            bis.read(bytes);
            // 重置 response
            response.reset();
            // 设置 response 的下载响应头
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));  // 注意，这里要设置文件名的编码，否则中文的文件名下载后不显示
            // 写出字节数组到输出流
            os.write(bytes);
            // 刷新输出流
            os.flush();
        } catch (Exception e) {
            log.error(e);
        }
    }

    /**
     * 导出信息收集数据表格
     *
     * @return
     */
    @PostMapping("/downCollectFile")
    public ResponseEntity<byte[]> downCollectFile(@RequestParam("iIFId") int iIFId,
                                                  HttpServletResponse response) throws Exception {
        if (iIFId == 0)
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        List<JSONObject> tableNameList = collectInfoService.getTableFiled(iIFId); // 获取表单字段
        List<JSONObject> fromData = collectInfoService.getFromAllData(iIFId); // 获取表单数据
        try {
            // 创建工作簿
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");

            // 创建表头
            Row headerRow = sheet.createRow(0);
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            for (int i = 0; i < tableNameList.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(tableNameList.get(i).getString("filedName"));
                cell.setCellStyle(headerStyle);
            }

            // 填充数据
            int rowNum = 1;
            // 定义输出日期字符串的格式
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for (JSONObject data : fromData) {
                Row row = sheet.createRow(rowNum++);
                int colNum = 0;
                for (JSONObject columnName : tableNameList) {
                    Cell cell = row.createCell(colNum++);
                    String filedName = columnName.getString("filed");
                    if ("cState".equals(filedName)) {
                        String cState = data.getString(filedName);
                        cell.setCellValue("0".equals(cState) ? "未完成" : ("1".equals(cState) ? "已完成" : "待审批"));
                        continue;
                    }
                    if (filedName.contains("time") && data.get(filedName) != null) {
                        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                        String dateString = data.getString(filedName);
                        try {
                            // 将日期字符串解析为 LocalDateTime 对象
                            LocalDateTime dateTime = LocalDateTime.parse(dateString, inputFormatter);

                            // 格式化 LocalDateTime 对象为标准的年月日时分秒格式
                            String formattedDateTime = dateTime.format(outputFormatter);
                            cell.setCellValue(formattedDateTime);
                        } catch (Exception e){
                            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
                        }
                        continue;
                    }
                    cell.setCellValue(data.getString(filedName));
                }
            }

            // 将工作簿转换为字节数组
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            byte[] excelBytes = outputStream.toByteArray();

            // 设置响应头并返回Excel文件
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "excel_file.xlsx");
            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 导出任务文件压缩包
     *
     */
    @PostMapping("/downTaskZipFile")
    public void downTaskZipFile(@RequestParam("iTMId") int iTMId,
                                                  HttpServletResponse response) throws Exception {
        if (iTMId == 0)
            throw new BusinessException(ErrorCode.PARAMS_ERROR);

        JSONObject fileInfo = fileinfoService.handleTaskZipFile(iTMId);
        String fileName = fileInfo.getString("fileName");
        String filePath = fileInfo.getString("filePath");


        File targetFile = new File(filePath, fileName);
        if (!targetFile.exists()) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        try (FileInputStream fis = new FileInputStream(filePath+fileName);
             BufferedInputStream bis = new BufferedInputStream(fis);
             OutputStream os = response.getOutputStream()) {    // OutputStream 是文件写出流，将文件下载到浏览器客户端
            // 新建字节数组，长度是文件的大小，比如文件 6kb, bis.available() = 1024 * 6
            byte[] bytes = new byte[bis.available()];
            // 从文件流读取字节到字节数组中
            bis.read(bytes);
            // 重置 response
            response.reset();
            // 设置 response 的下载响应头
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));  // 注意，这里要设置文件名的编码，否则中文的文件名下载后不显示
            // 写出字节数组到输出流
            os.write(bytes);
            // 刷新输出流
            os.flush();
        } catch (Exception e) {
            log.error(e);
        }

        // 删除该压缩文件
        File folder = new File(filePath);
        fileinfoService.deleteFolder(folder);
    }

}
