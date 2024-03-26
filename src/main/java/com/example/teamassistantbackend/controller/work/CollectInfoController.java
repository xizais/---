package com.example.teamassistantbackend.controller.work;

import com.alibaba.fastjson.JSONObject;
import com.example.teamassistantbackend.common.BaseResponse;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.common.ResultUtils;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.service.CollectInfoService;
import com.example.teamassistantbackend.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/CollectInfo")
public class CollectInfoController {
    @Resource
    CollectInfoService collectInfoService;

    /**
     * 保存信息收集表单（按stage状态判断是新增还是修改）
     */
    @PostMapping("/saveCollectDesign")
    @ResponseBody
    public BaseResponse<JSONObject> saveCollectDesign(@RequestBody JSONObject request){
        ArrayList<HashMap<String,Object>> containerData = (ArrayList<HashMap<String,Object>>)request.get("container");
        if (StringUtils.isEmpty(request.getString("title"))) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"标题不允许为空！");
        }
        if (containerData.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"不允许创建空表单！");
        }
        JSONObject result = collectInfoService.saveCollectInfo(
                containerData,
                "add".equals(request.get("state")),
                request.getInteger("iIFId"),
                request.getString("title"));
        if (result.get("msg")!=null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,(String)result.get("msg"));
        } else {
            return ResultUtils.success(result);
        }
    }

    /**
     * 返回列表数据
     */
    @PostMapping("/getCollectInfoList")
    @ResponseBody
    public BaseResponse<JSONObject> getCollectInfoList(@RequestBody JSONObject request){
        return ResultUtils.success(collectInfoService.getInfoList(request));
    }

    /**
     * 返回表单数据
     */
    @PostMapping("/getCollectInfo")
    @ResponseBody
    public BaseResponse<JSONObject> getCollectInfo(@RequestBody JSONObject request){
        return ResultUtils.success(collectInfoService.getInfo(request.getString("iIFId")));
    }

    /**
     * 返回表单数据和用户数据
     */
    @PostMapping("/getCollectInfoAndPersonData")
    @ResponseBody
    public BaseResponse<JSONObject> getCollectInfoAndPersonData(@RequestBody JSONObject request){
        return ResultUtils.success(collectInfoService.getInfoAndPersonData(request));
    }

    /**
     * 删除表单
     */
    @PostMapping("/deleteCollectInfo")
    @ResponseBody
    public BaseResponse<String> deleteCollectInfo(@RequestBody JSONObject request){
        return ResultUtils.success(collectInfoService.deleteCollect(request));
    }

    /**
     * 发布
     */
    @PostMapping("/pubCollectInfo")
    @ResponseBody
    public BaseResponse<JSONObject> pubCollectInfo(@RequestBody JSONObject request){
        return ResultUtils.success(collectInfoService.pubCollectInfo(request));
    }

    /**
     * 获取表单收集用户的数据
     */
    @PostMapping("/getFromData")
    @ResponseBody
    public BaseResponse<JSONObject> getFromData(@RequestBody JSONObject request){
        return ResultUtils.success(collectInfoService.getFromData(request));
    }

    /**
     * 删除个人表单数据
     */
    @PostMapping("/deleteCollectDataById")
    @ResponseBody
    public BaseResponse<JSONObject> deleteCollectDataById(@RequestBody JSONObject request){
        return ResultUtils.success(collectInfoService.deleteDataById(request));
    }

    /**
     * 添加个人表单数据
     */
    @PostMapping("/addFromDataPerson")
    @ResponseBody
    public BaseResponse<JSONObject> addFromDataPerson(@RequestBody JSONObject request){
        return ResultUtils.success(collectInfoService.addFromDataPerson(request));
    }

    /**
     * 通知信息收集表单用户完成提交
     */
    @PostMapping("/clickNotify")
    @ResponseBody
    public BaseResponse<JSONObject> clickNotify(@RequestBody JSONObject request){
        return ResultUtils.success(collectInfoService.clickNotify(request));
    }

    /**
     * 停止/启动发布
     */
    @PostMapping("/reverseState")
    @ResponseBody
    public BaseResponse<JSONObject> reverseState(@RequestBody JSONObject request){
        return ResultUtils.success(collectInfoService.reverseState(request));
    }

    /**
     * 获得发布状态
     */
    @PostMapping("/getPubState")
    @ResponseBody
    public BaseResponse<JSONObject> getPubState(@RequestBody JSONObject request){
        return ResultUtils.success(collectInfoService.getPubState(request));
    }

    /**
     * 保存个人数据
     */
    @PostMapping("/saveFromPersonData")
    @ResponseBody
    public BaseResponse<JSONObject> saveFromPersonData(@RequestBody JSONObject request){
        return ResultUtils.success(collectInfoService.saveFromPersonData(request));
    }

    /**
     * 获取当前处理人对应收集表单的记录ID
     */
    @PostMapping("/getFromDataId")
    @ResponseBody
    public BaseResponse<JSONObject> getFromDataId(@RequestBody JSONObject request){
        return ResultUtils.success(collectInfoService.getFromDataId(request));
    }
}
