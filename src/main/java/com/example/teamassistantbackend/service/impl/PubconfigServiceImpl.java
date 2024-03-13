package com.example.teamassistantbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.service.PubconfigService;
import com.example.teamassistantbackend.entity.Pubconfig;
import com.example.teamassistantbackend.mapper.PubconfigMapper;
import org.springframework.stereotype.Service;

/**
* @author huang
* @description 针对表【pubconfig(发布配置表)】的数据库操作Service实现
* @createDate 2024-03-13 17:41:28
*/
@Service
public class PubconfigServiceImpl extends ServiceImpl<PubconfigMapper, Pubconfig>
    implements PubconfigService {

    @Override
    public BaseResponse<JSONObject> savePubConfig(JSONObject request) {
        // 检查数据
        checkPubConfig(request);
        // 保存或修改信息

        return null;
    }

    private void checkPubConfig(JSONObject request) {
        if (request.get("iTypeId") == null || request.get("cType") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (request.get("dPubStartTime") == null || request.get("dPubEndTime") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请选择发布起始时间！");
        }
    }
}




