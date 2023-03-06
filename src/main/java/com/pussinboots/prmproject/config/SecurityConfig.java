package com.pussinboots.prmproject.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.WebApplicationContext;

import com.pussinboots.prmproject.filters.ExceptionHandlerFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public FilterRegistrationBean<ExceptionHandlerFilter> myFilterRegistrationBean() {
        FilterRegistrationBean<ExceptionHandlerFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ExceptionHandlerFilter());
        registrationBean.setOrder(0);
        return registrationBean;
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SecurityContext securityContext() {
        return SecurityContextHolder.getContext();
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity httpSecurity, ExceptionHandlerFilter exceptionHandlerFilter) throws Exception {
        httpSecurity.csrf().disable().cors();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.authorizeHttpRequests().anyRequest().permitAll();
        // httpSecurity.addFilterBefore(exceptionHandlerFilter,ExceptionHandlerFilter.class);
        return httpSecurity.build();
    }
}
