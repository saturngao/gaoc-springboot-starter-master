package com.imooc.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class WebFilterConfiguration {
    /**跨域过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilterRegister() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //设置过滤器拦截请求
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);
        registrationBean.setName("corsFilter");
        CorsFilter filter = new CorsFilter();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(0);
        return registrationBean;
    }
}
