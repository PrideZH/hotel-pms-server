package com.pengfu.pms.filter;

import com.pengfu.pms.service.impl.UserServiceImpl;
import com.pengfu.pms.util.JSONResult;
import com.pengfu.pms.util.ResultCode;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author PrideZH
 * @date 2021/8/13 - 21:41
 */
@WebFilter(filterName = "loginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {

    @Resource
    private UserServiceImpl userService;

    /**
     * 不需登录的请求
     */
    private String[] includeUrls = new String[]{"/v1/auth/login", "/v1/auth/register"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURI();
        String token = request.getHeader("token");

        // 不需过滤的api放行
        if (!isFilter(url)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        // token有效放行
        else if (userService.isValidToken(token)) {
            // 重置token时间
            userService.updateToken(token);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String result = JSONResult.build(ResultCode.USER_NOT_LOGIN, null).toString();

            response.setContentType("json/text;charset=utf-8");
            PrintWriter out = response.getWriter();

            out.write(result);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isFilter(String uri) {
        for (String includeUrl : includeUrls) {
            if(includeUrl.equals(uri)) {
                return false;
            }
        }
        return true;
    }
}
