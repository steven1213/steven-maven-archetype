package com.steven.maven.archetype.infra.filter;

import com.steven.maven.archetype.infra.general.utils.ThreadMdcUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author: dr.panda.
 * @date: 2022/10/10.
 * @description: com.steven.maven.archetype.infra.interceptor.
 * @version: 1.8.
 */
@Slf4j
@Order(0)
@WebFilter(filterName = "traceIdFilter", urlPatterns = "/*")
@Component
public class TraceIdFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ThreadMdcUtils.setTraceIdIfAbsent();
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        ThreadMdcUtils.clear();
    }
}
