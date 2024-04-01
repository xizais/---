package com.example.teamassistantbackend.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ResultUtils;
import com.example.teamassistantbackend.entity.requestCommon.UserInfo;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: xizai
 * @Date: 2024-03-26
 */
@Controller
@RequestMapping("/File")
public class FileController {
    /**
     * 上传文件
     */
    @PostMapping("/uploadFile")
    public BaseResponse<String> uploadFile(@RequestParam("file") MultipartFile request){
        System.out.println(request);
        return ResultUtils.success(request.toString());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId) {
        // 根据文件标识符获取文件内容
        // 创建一个ByteArrayResource，并将文件内容设置为其字节数组
        // 返回ResponseEntity<ByteArrayResource>，包含文件内容和适当的HTTP头信息
        return null;
    }
}
