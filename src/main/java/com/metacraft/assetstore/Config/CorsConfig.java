package com.metacraft.assetstore.Config;

import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**") // 기존 API 경로
        .allowedOrigins("http://localhost:8080") // 허용할 Origin
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메소드
        .allowedHeaders("*") // 허용할 헤더
        .allowCredentials(true);

    // 프록시 경로 추가
    registry.addMapping("/proxy/**") // 프록시 경로
        .allowedOrigins("http://localhost:8080") // 허용할 Origin
        .allowedMethods("GET") // 프록시는 GET 요청만 허용
        .allowedHeaders("*") // 허용할 헤더
        .allowCredentials(true);
  }
}