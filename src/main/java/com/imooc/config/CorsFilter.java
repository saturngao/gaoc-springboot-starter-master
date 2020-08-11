package com.imooc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 跨域
 * @author: yaox
 * @create: 2019/12/23
 **/
@Configuration
public class CorsFilter extends OncePerRequestFilter {

    private static String ALLOW_ORIGIN = "*";

    private static String ORIGIN_HEADER = "Origin";

    private static String ACCESS_CONTROL_ALLOW_METHODS = "POST,GET,PUT,OPTIONS,DELETE";

    private static String ACCESS_CONTROL_MAX_AGE = "1800";

    private static String ACCESS_CONTROL_ALLOW_HEADERS =
            "Origin,No-Cache,X-Requested-With,If-Modified-Since,Pragma,Last-Modified,Cache-Control,Expires,Content-Type,X-E4M-With,appId,appVersion,appKey,appName,clientId,accessToken,sign,timestamp,,accept,,accountToken";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //针对浏览器发起的ajax
        if (null != httpServletRequest.getHeader(ORIGIN_HEADER)) {
//            Environment env = SpringUtils.getBean(Environment.class);
            String allowMethods = ALLOW_ORIGIN;
            String maxAge = ACCESS_CONTROL_MAX_AGE;
            String allowHeaders = ACCESS_CONTROL_ALLOW_METHODS;
            String allowOrigin = ALLOW_ORIGIN;

            String[] allowOriginArray = allowOrigin.split(",");
            for (String e : allowOriginArray) {
            	if (ALLOW_ORIGIN.equals(e)||httpServletRequest.getHeader(ORIGIN_HEADER).startsWith(e)) {
                    httpServletResponse.addHeader("Access-Control-Allow-Origin", e);
                    httpServletResponse.addHeader("Access-Control-Allow-Methods", allowMethods);
                    httpServletResponse.addHeader("Access-Control-Max-Age", maxAge);
                    httpServletResponse.addHeader("Access-Control-Allow-Headers", allowHeaders);
                    break;
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}