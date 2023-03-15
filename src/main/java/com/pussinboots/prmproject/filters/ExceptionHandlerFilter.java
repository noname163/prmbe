package com.pussinboots.prmproject.filters;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pussinboots.prmproject.dto.response.ExceptionResponse;
import com.pussinboots.prmproject.exceptions.ForbiddenException;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Slf4j
@Component

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            System.out.println("Access data");
            System.out.println("Url " + request.getRequestURL());
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
            if (ex instanceof ExpiredJwtException) {
                httpStatus = HttpStatus.UNAUTHORIZED;
            } else if (ex instanceof ForbiddenException) {
                httpStatus = HttpStatus.FORBIDDEN;
            }
            setErrorResponse(httpStatus, response, ex);
        }
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable ex) {
        response.setStatus(status.value());
        response.setContentType("application/json");
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().message(ex.getCause().getMessage()).build();
        try {
            String json = convertObjectToJson(exceptionResponse.getMessage());
            response.getWriter().write(json);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
