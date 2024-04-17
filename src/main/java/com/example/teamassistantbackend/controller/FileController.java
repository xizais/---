package com.example.teamassistantbackend.controller;

import cn.hutool.log.Log;
import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.common.ResultUtils;
import com.example.teamassistantbackend.entity.requestCommon.UserInfo;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.service.FileinfoService;
import com.example.teamassistantbackend.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author: xizai
 * @Date: 2024-03-26
 */
@Controller
@RequestMapping("/File")
public class FileController {
    @Resource
    FileinfoService fileinfoService;

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
}
