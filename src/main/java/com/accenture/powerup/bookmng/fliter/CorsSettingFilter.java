package com.accenture.powerup.bookmng.fliter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

public class CorsSettingFilter extends OncePerRequestFilter {

    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN_DEFAULT = "http://localshot:4200";

    private String[] accessControlAllowOriginValueArray;

    private String accessControlAllowHeadersValue;

    public CorsSettingFilter(String[] accessControlAllowOrigins, String accessControlAllowHeaders) {
        this.accessControlAllowOriginValueArray = accessControlAllowOrigins.clone();
        this.accessControlAllowHeadersValue = accessControlAllowHeaders;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        String originHeader = request.getHeader(HttpHeaders.ORIGIN);

        // valid header
        if (originHeader != null && !"".equals(originHeader)) {
            if (Arrays.asList(accessControlAllowOriginValueArray).contains(originHeader)) {
                response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, originHeader);
            } else {
                response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN_DEFAULT);
            }
        }
        response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, accessControlAllowHeadersValue);

        filterChain.doFilter(request, response);
    }
}
