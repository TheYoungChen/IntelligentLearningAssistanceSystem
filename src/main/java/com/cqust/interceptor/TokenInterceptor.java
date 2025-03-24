package com.cqust.interceptor;

import com.cqust.utils.CurrentHolder;
import com.cqust.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 令牌校验拦截器
 */
@Slf4j
//@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求路径
        String requestURI = request.getRequestURI();

//        // 2.判断请求路径是否为登录路径
//        if (requestURI.contains("/login")) {
//            // 3.如果是登录请求，放行
//            log.info("登录请求，放行");
//            return true;
//        }

        // 3.判断请求头中是否有令牌
        String token = request.getHeader("token");
        // 4.若没有令牌，则返回未登录状态码
        if (token == null || token.isEmpty()) {
            log.info("无令牌未登录，返回未登录状态码401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 5.如果有令牌，则校验令牌 如果校验失败 则返回未登录状态码401
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("令牌校验通过，当前用户id为：{} 已将其存入 ThreadLocal", empId);
        } catch (Exception e) {
            log.info("令牌校验失败，返回未登录状态码401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 6.校验通过 放行
        log.info("令牌校验通过，放行");

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 7.请求结束，清理 ThreadLocal
        CurrentHolder.remove();
    }
}
