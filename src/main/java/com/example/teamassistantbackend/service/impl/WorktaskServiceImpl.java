package com.example.teamassistantbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.entity.Infoform;
import com.example.teamassistantbackend.entity.Workmessage;
import com.example.teamassistantbackend.entity.Worktask;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.mapper.InfoformMapper;
import com.example.teamassistantbackend.mapper.InfoformcreateMapper;
import com.example.teamassistantbackend.mapper.WorkmessageMapper;
import com.example.teamassistantbackend.mapper.WorktaskMapper;
import com.example.teamassistantbackend.service.CollectInfoService;
import com.example.teamassistantbackend.service.PersoninfoService;
import com.example.teamassistantbackend.service.WorktaskService;
import com.example.teamassistantbackend.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author 希仔
* @description 针对表【worktask(工作待办表)】的数据库操作Service实现
* @createDate 2024-03-17 22:29:29
*/
@Service
public class WorktaskServiceImpl extends ServiceImpl<WorktaskMapper, Worktask>
    implements WorktaskService {

    @Resource
    WorktaskMapper worktaskMapper;
    @Resource
    WorkmessageMapper workmessageMapper;
    @Resource
    PersoninfoService personinfoService;
    @Resource
    InfoformMapper infoformMapper;// 表单配置
    @Resource
    InfoformcreateMapper infoformcreateMapper;// 表单创建
    @Override
    public JSONObject handleWorkTask(JSONObject request) {
        if (request == null
                || request.get("workTaskId") == null
                || request.get("type") == null
                || request.get("title") == null
        ) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String type = request.getString("type");
        JSONObject result = new JSONObject();
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        Worktask worktask = worktaskMapper.selectById(request.getInteger("workTaskId"));
        switch (type) {
            case "CollectInfo": {
                break;
            }
            case "CollectInfoApproval": {
                if (request.get("typeId") == null) {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR);
                }
                if (request.get("approvalResult") == null) {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR,"审批结果不允许为空！");
                }

                result.put("message","审批成功！");
                worktask.setUpdatetime(new Date());
                worktask.setContent(request.getString("approvalCotent"));
                worktask.setState(request.getString("approvalResult"));
                worktaskMapper.updateById(worktask);

                // 通过处理
                if (StringUtils.isEmpty(request.getString("approvalCotent"))) {
                    request.put("approvalCotent","无。");
                } else {
                    request.put("approvalCotent",request.getString("approvalCotent")+"。");
                }
                Workmessage workmessage = new Workmessage();
                workmessage.setType("CollectInfo");
                workmessage.setTypeId(request.getInteger("iIFId"));
                workmessage.setPersonCode(worktask.getCode());
                workmessage.setPersonName(personinfoService.getPersonName(worktask.getCode()));
                workmessage.setContent("表单："+request.getString("title")+"审批"+request.getString("approvalResult")+"\n; 审批描述："+request.getString("approvalCotent"));
                workmessage.setHandler(curUserInfo.getString("name"));
                workmessage.setHandleTime(new Date());
                workmessageMapper.insert(workmessage);

                // 不通过处理,再新增一条工作待办
                if ("不通过".equals(request.getString("approvalResult"))) {
                    Worktask worktaskNew = new Worktask();
                    worktaskNew.setType("CollectInfo");
                    worktaskNew.setTypeid(request.getInteger("typeId"));
                    worktaskNew.setCode(worktask.getCode());
                    worktaskNew.setUpdatetime(new Date());
                    worktaskNew.setContent("审批未通过，请重新完成"+request.getString("title")+"收集表单的填写！");
                    worktaskMapper.insert(worktaskNew);
                }

                // 更改该收集数据的状态
                updateState(request.getInteger("typeId"),request.getInteger("id"),"通过".equals(request.getString("approvalResult")));
                break;
            }
            case "Notify": {
                worktask.setState("已确认");
                worktaskMapper.updateById(worktask);
                result.put("message","确认成功！");
                break;
            }
        }
        return result;
    }

    @Override
    public JSONObject getWorkInfoList(JSONObject request) {
        if (request == null
                || request.get("selectPage") == null
                || request.getJSONObject("selectPage").get("currentPage") == null
                || request.getJSONObject("selectPage").get("pageSize") == null
                || request.getJSONObject("selectPage").get("offset") == null
        ) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 封装数据
        // 获取工作任务
        JSONObject curUserInfo = personinfoService.getCurUserInfo();
        List<JSONObject> workList = worktaskMapper.getWorkInfoList(curUserInfo.getString("code"),request);
        // 获取总量
        int mount = worktaskMapper.getWorkInfoListMount(curUserInfo.getString("code"),request);
        JSONObject result = new JSONObject();
        result.put("workList",workList);
        result.put("amount",mount);
        return result;
    }

    @Override
    public JSONObject deleteWorkTask(JSONObject request) {
        if (request == null || request.get("id") == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        worktaskMapper.deleteById(request.getInteger("id"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message","删除成功!");
        return jsonObject;
    }

    @Override
    public void deleteWorkTaskAll(String typeId, String type) {
        QueryWrapper<Worktask> worktaskQueryWrapper = new QueryWrapper<>();
        worktaskQueryWrapper.eq("type",type);
        worktaskQueryWrapper.eq("typeId",typeId);
        worktaskQueryWrapper.eq("dataState","0");
        List<Worktask> worktasks = worktaskMapper.selectList(worktaskQueryWrapper);
        for (Worktask worktask : worktasks) {
            worktask.setDatastate("1");
        }
        if (!worktasks.isEmpty()) {
            this.updateBatchById(worktasks);
        }
    }

    private void updateState(Integer typeId, Integer id, boolean approvalResult) {
        String tableName = getDataTableName(typeId);
        String updateSql = "update "+tableName+" set cState = '"+(approvalResult?1:0)+"' where id = "+id;
        infoformcreateMapper.doExcuSql(updateSql);
    }
    /**
     * 获取表名
     * @param iIFId 信息收集配置表id
     */
    private String getDataTableName(Integer iIFId) {
        Infoform infoform = infoformMapper.selectById(iIFId);
        if (infoform == null || StringUtils.isEmpty(infoform.getCIFTableName()))
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        return infoform.getCIFTableName();
    }
}




