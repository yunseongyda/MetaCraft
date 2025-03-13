package com.metacraft.assetstore;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@jakarta.servlet.annotation.WebFilter(urlPatterns = "/Build/*") // /Build 경로에 대한 요청에 필터 적용
public class MimeTypeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 초기화
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // MIME 타입을 'application/javascript'로 설정
        httpResponse.setContentType("application/javascript");

        // 필터 체인 계속 진행
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 필터 종료 시 처리
    }
}

