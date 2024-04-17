package com.example.teamassistantbackend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.teamassistantbackend.common.ErrorCode;
import com.example.teamassistantbackend.entity.Personinfo;
import com.example.teamassistantbackend.entity.Pubconfig;
import com.example.teamassistantbackend.exception.BusinessException;
import com.example.teamassistantbackend.service.PersoninfoService;
import com.example.teamassistantbackend.mapper.PersoninfoMapper;
import com.example.teamassistantbackend.utils.StringUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.zip.CRC32;

import static com.example.teamassistantbackend.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author huang
* @description 针对表【personinfo(人员信息表)】的数据库操作Service实现
* @createDate 2023-10-26 23:43:40
*/
@Service
public class PersoninfoServiceImpl extends ServiceImpl<PersoninfoMapper, Personinfo>
    implements PersoninfoService{

    @Resource
    PersoninfoMapper personinfoMapper;
    @Value("${Variable.sysManager}")
    private String sysManager;
    @Override
    public JSONObject userLogin(String code, String password, HttpServletRequest request) {
        // 1. 判空
        if (StringUtils.isEmpty(code)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请输入账号！");
        }
        if (StringUtils.isEmpty(password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请输入登入密码！");
        }
        // 2. 校验用户
        JSONObject userInfo = new JSONObject();
        userInfo.put("code",code);
        Personinfo personinfo = personinfoMapper.getUserInfo(userInfo);
        if (personinfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户不存在！");
        }
        // 3. 验证密码是否正确
        CRC32 crc32 = new CRC32();
        crc32.update(password.getBytes());
        String crc = "" + crc32.getValue();
        if (!personinfo.getCPIPassword().equals(crc)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号或密码输入不正确！");
        }
//        // 4. 鉴权（是否有权访问客户联系人所属部门页面）
//        if (!generalUtils.checkPower(162,users.get(0).getLoginname())){// 菜单：客户联系人所属部门-->对应记录id为162
//            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
//        }
        // 5. 用户数据信息封装及脱敏
        userInfo.put("name",personinfo.getCPIName());
        // 6. 保存用户信息
        request.getSession().setAttribute(USER_LOGIN_STATE, userInfo);
        return userInfo;
    }

    @Override
    public void logout() {
        JSONObject userInfo = getCurUserInfo();
        if (StringUtils.isEmpty(userInfo)) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        // 移除登入信息
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        httpServletRequest.getSession().removeAttribute(USER_LOGIN_STATE);
    }

    @Override
    public JSONObject getCurUserInfo() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        return (JSONObject) httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
    }

    @Override
    public String getPersonName(String code) {
        QueryWrapper<Personinfo> personinfoQueryWrapper = new QueryWrapper<>();
        personinfoQueryWrapper.eq("cPICode",code);
        personinfoQueryWrapper.eq("bPIIsEnabled",1);
        List<Personinfo> personinfos = personinfoMapper.selectList(personinfoQueryWrapper);
        if (personinfos.isEmpty()) {
            return null;
        }
        return personinfos.get(0).getCPIName();
    }

    @Override
    public JSONObject getCurPerInfo() {
        return getCurUserInfo();
    }

    @Override
    public JSONObject getAllPerInfo() {
        // 鉴权
        checkAuth();

        List<JSONObject> perList = personinfoMapper.getAllPerInfoList();
        JSONObject result = new JSONObject();
        result.put("perList",perList);
        return result;
    }

    @Override
    public JSONObject deletePerInfo(JSONObject request) {
        // 鉴权
        checkAuth();
        // 查询用户信息
        personinfoMapper.deleteById(request.getString("perCode"));
        JSONObject result = new JSONObject();
        result.put("message","删除成功！");
        return result;
    }


    // 鉴权
    @Override
    public void checkAuth() {
        // 获取用户信息
        if (!getCurPerInfo().getString("code").equals(sysManager)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"非系统管理员无权操作！");
        }
    }

}






