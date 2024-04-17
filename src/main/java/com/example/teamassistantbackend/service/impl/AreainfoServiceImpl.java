package com.example.teamassistantbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.entity.Areainfo;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.mapper.AreabookMapper;
import com.example.teamassistantbackend.service.AreainfoService;
import com.example.teamassistantbackend.mapper.AreainfoMapper;
import com.example.teamassistantbackend.service.PersoninfoService;
import com.example.teamassistantbackend.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author huang
* @description 针对表【areainfo】的数据库操作Service实现
* @createDate 2024-03-27 14:01:25
*/
@Service
public class AreainfoServiceImpl extends ServiceImpl<AreainfoMapper, Areainfo>
    implements AreainfoService{
    @Resource
    PersoninfoService personinfoService;
    @Resource
    AreainfoMapper areainfoMapper;
    @Resource
    AreabookMapper areabookMapper;
    @Override
    public JSONObject addAreaInfo(JSONObject request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (request.get("cAIName") == null) {
          throw new BusinessException(ErrorCode.PARAMS_ERROR,"请填写场地名称信息！");
        }
        if (request.get("cAIContent") == null) {
          throw new BusinessException(ErrorCode.PARAMS_ERROR,"请填写场地内容信息！");
        }
        if (request.get("bAIIsEnable") == null) {
          throw new BusinessException(ErrorCode.PARAMS_ERROR,"请选择是否启用信息！");
        }
        if (request.get("bAIIsApprove") == null) {
          throw new BusinessException(ErrorCode.PARAMS_ERROR,"请选择是否审批信息！");
        }
        if (request.get("cAIManagerName") == null) {
          throw new BusinessException(ErrorCode.PARAMS_ERROR,"请填写联系人信息！");
        }
        if (request.get("cAIManagerPhone") == null) {
          throw new BusinessException(ErrorCode.PARAMS_ERROR,"请填写联系人联系方式信息！");
        }
        // 获取个人信息
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        Areainfo areainfo ;

        if (request.get("iAIId") != null) {
            areainfo = areainfoMapper.selectById(request.getInteger("iAIId"));
        } else {
            areainfo = new Areainfo();
            areainfo.setDAICreateTime(new Date());
        }
        // 查询该人存不存在
        areainfo.setCAIManagerName(request.getString("cAIManagerName"));
        areainfo.setCAIManagerPhone(request.getString("cAIManagerPhone"));
        areainfo.setCAIName(request.getString("cAIName"));
        areainfo.setCAIContent(request.getString("cAIContent"));
        areainfo.setBAIIsEnable(request.getBoolean("bAIIsEnable"));
        areainfo.setBAIIsApprove(request.getBoolean("bAIIsApprove"));
        areainfo.setDAIUpdateTime(new Date());
        areainfo.setCAIHandleCode(curUserInfo.getString("code"));
        if (request.get("iAIId") == null) {
            areainfoMapper.insert(areainfo);
        } else {
            areainfoMapper.updateById(areainfo);
        }
        JSONObject result = new JSONObject();
        result.put("message","保存成功！");
        return result;
    }

    @Override
    public JSONObject deleteAreaInfo(JSONObject request) {
        if (request == null || request.get("iAIId") == null ){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Areainfo areainfo = areainfoMapper.selectById(request.getInteger("iAIId"));
        if (areainfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        areainfo.setCAIHandleCode(curUserInfo.getString("code"));
        areainfo.setDAIUpdateTime(new Date());
        areainfo.setDataState("1");
        areainfoMapper.updateById(areainfo);
        JSONObject result = new JSONObject();
        result.put("message","删除成功！");
        return result;
    }

    @Override
    public JSONObject getAreaInfoList(JSONObject request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (request.get("searchData") != null
                && request.getJSONObject("searchData").get("showOther")!=null
                && request.getJSONObject("searchData").getBoolean("showOther")) {
            if (request.getJSONObject("searchData").get("startDate") == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"请选择预约开始时间！");
            }
            if (request.getJSONObject("searchData").get("endDate") == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"请选择预约结束时间！！");
            }
        }
        // 查询时间段内的已经预约的场地
        String doneBookId = getDoneBookId(request);
        if (StringUtils.isNotEmpty(doneBookId)) {
            request.put("ids",doneBookId);
        }
        List<JSONObject> infoList = areainfoMapper.getInfoList(request);
        int amount = areainfoMapper.getInfoListAmount(request);
        JSONObject result = new JSONObject();
        result.put("infoList",infoList);
        result.put("amount",amount);
        return result;
    }

    @Override
    public JSONObject getAreaInfo(JSONObject request) {
        if (request == null || request.get("iAIId") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        JSONObject result = areainfoMapper.selectInfo(request.getString("iAIId"));
        return result;
    }

    private String getDoneBookId(JSONObject request) {
        if (request.get("page") != null){
            return areabookMapper.getDoneBookABIds(request);
        }
        return null;
    }
}




