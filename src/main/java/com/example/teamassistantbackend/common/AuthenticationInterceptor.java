package com.example.teamassistantbackend.common;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.example.teamassistantbackend.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author huang
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 判断用户是否已登录
        HttpSession session = request.getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(USER_LOGIN_STATE);
        if (userInfo == null) {
            // 用户未登录，返回未登录提示
            JSONObject result = new JSONObject();
            result.put("code",ErrorCode.NOT_LOGIN_ERROR.getCode());
            result.put("message",ErrorCode.NOT_LOGIN_ERROR.getMessage());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(result.toString());
            return false;
        }
        // 用户已登录，放行请求
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在请求处理之后进行调用，但是在视图被渲染之前（Controller 方法调用之后）
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在整个请求结束之后被调用，也就是在 DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    }
}