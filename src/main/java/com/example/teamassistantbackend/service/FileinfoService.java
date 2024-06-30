package com.example.teamassistantbackend.service;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.entity.Fileinfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
* @author huang
* @description 针对表【fileinfo(文件信息表)】的数据库操作Service
* @createDate 2023-10-26 23:43:40
*/
public interface FileinfoService extends IService<Fileinfo> {

    JSONObject saveFileByType(MultipartFile fileInfo, String type);

    JSONObject getFilePath(int fileId);

    JSONObject deleteFile(int fileId);

    void handleFile(JSONObject handleInfo); // 处理文件：即转移文件信息

    List<JSONObject> getFileInfoList(JSONObject fileInfo);

    void updateFileManagerTypeId(String fileDoIds, Integer typeId); // 修改文件的关联表的ID为工作待办的ID

    JSONObject exportFile(MultipartFile fileInfo); // 导入人员信息

    JSONObject handleTaskZipFile(int iTMId) throws Exception; // 处理任务完成文件并且将任务的文件进行压缩，以及返回文件路径
    public void deleteFolder(File folder); // 删除文件夹
}
