package cn.jt.filter;


import cn.jt.common.R;
import cn.jt.common.entity.other.JwtUser;
import cn.jt.common.exception.BizException;
import cn.jt.common.string.StrPool;
import cn.jt.entity.User;
import cn.jt.exception.ExceptionCode;
import cn.jt.service.UserService;
import cn.jt.utils.JwtTokenUtils;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;


@Slf4j
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    private JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding(StrPool.UTF_8);
        response.setContentType("text/html;charset=UTF-8");
        String requestURI = request.getRequestURI();
        String[] urls = new String[]{
                "/anno/login",
                "/anno/captcha",
                "/anno/check",

                "/favicon.ico",
                "/v3/api-docs/**",
                "/doc.html",
                "/webjars/**"
        };
        //判断请求是否需要处理
        boolean check = check(urls, requestURI);
        //不需要则放行
        if (check) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String token = request.getHeader("Authorization");
            if (token != null) {
                JwtUser jwtUser = jwtTokenUtils.getUserInfo(token);
                Long userId = jwtUser.getUserId();
                User user = userService.getById(userId);
                if (user != null) {
                    //已登录且用户存在->放行
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        } catch (BizException e) {
            log.error(e.toString());
            response.getWriter().write(JSON.toJSONString(R.fail(ExceptionCode.JWT_TOKEN_EXPIRED)));
            return;
        }
        //未登录,返回未登录结果
        response.getWriter().write(JSON.toJSONString(R.fail(ExceptionCode.UNAUTHORIZED)));
    }


    private boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }


}
