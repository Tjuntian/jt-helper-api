package cn.jt.filter;

import cn.jt.wrapper.XssRequestWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/*
 *过滤所有提交到服务器的请求参数
 */
public class XssFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        filterChain.doFilter(new XssRequestWrapper(request),servletResponse);//传入重写后的Request
    }
}