package com.example.teamassistantbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.entity.Areabook;
import com.example.teamassistantbackend.entity.Areainfo;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.mapper.AreainfoMapper;
import com.example.teamassistantbackend.service.AreabookService;
import com.example.teamassistantbackend.mapper.AreabookMapper;
import com.example.teamassistantbackend.service.PersoninfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
* @author huang
* @description 针对表【areabook】的数据库操作Service实现
* @createDate 2024-03-27 14:01:25
*/
@Service
public class AreabookServiceImpl extends ServiceImpl<AreabookMapper, Areabook>
    implements AreabookService{

    @Resource
    AreabookMapper areabookMapper;
    @Resource
    AreainfoMapper areainfoMapper;
    @Resource
    PersoninfoService personinfoService;
    @Override
    public JSONObject addAreaBook(JSONObject request) {
        if (request == null
            || request.get("iAIId") == null // 对应场地信息的ID
            || request.get("dABBookStartTime") == null
            || request.get("dABBookEndTime") == null
        ) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 查询该预约数据是否存在
        int mount = areabookMapper.checkTime(request);
        if (mount > 0) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"该预约时间系统已存在，不允许预约！");
        }
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        Areainfo areainfo = areainfoMapper.selectById(request.getInteger("iAIId"));
        boolean isApproval = areainfo.getBAIIsApprove();
        Areabook areabook = new Areabook();
        areabook.setIAB_iAIId(request.getInteger("iAIId"));
        areabook.setCABBookerCode(curUserInfo.getString("name"));
        areabook.setDABBookStartTime(request.getDate("dABBookStartTime"));
        areabook.setDABBookEndTime(request.getDate("dABBookEndTime"));
        if (isApproval) {
            areabook.setCABState("待审批");





            // 增加一个工作待办--》前端也要进行修改







        }
        areabook.setDABCreateTime(new Date());
        areabook.setDABUpdateTime(new Date());
        areabookMapper.insert(areabook);
        JSONObject result = new JSONObject();
        result.put("message","预约成功！");
        return result;
    }

    @Override
    public JSONObject deleteAreaBook(JSONObject request) {
        if (request == null || request.get("iABId") == null ) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Areabook areabook = areabookMapper.selectById(request.getInteger("iABId"));
        if (areabook == null ) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        areabook.setDataState("1");
        areabookMapper.updateById(areabook);
        JSONObject result = new JSONObject();
        result.put("message","预约成功！");
        return result;
    }

    @Override
    public JSONObject getAreaBookList() {
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        List<JSONObject> infoList = areabookMapper.getInfoList(curUserInfo.getString("name"));
        JSONObject result = new JSONObject();
        result.put("infoList",infoList);
        return result;
    }

    @Override
    public JSONObject getAreaBookInfo(JSONObject request) {
        if (request == null || request.get("iABId") == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        JSONObject result = areabookMapper.selectInfo(request.getInteger("iABId"));
        return result;
    }
}




