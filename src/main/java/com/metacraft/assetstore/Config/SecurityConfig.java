package com.metacraft.assetstore.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
			.authorizeHttpRequests()
					// 인증이 필요한 경로 추가
					.requestMatchers("/api/assets/upload").authenticated()  // 이 경로는 로그인 후 접근 가능
					.anyRequest().permitAll()  // 다른 모든 경로는 인증 없이 접근 가능
					// 나머지 경로는 모두 허용
			.and()
			.csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))  // 특정 경로에 대해 CSRF 보호 제외
			.headers(headers -> headers.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))  // X-Frame-Options 설정
			.formLogin(formLogin -> formLogin
					.loginPage("/siteuser/login")  // 로그인 페이지 경로 설정
					.defaultSuccessUrl("/")  // 로그인 성공 후 리다이렉트될 경로
			)
			.logout(logout -> logout
					.logoutRequestMatcher(new AntPathRequestMatcher("/siteuser/signout"))  // 로그아웃 경로 설정
					.logoutSuccessUrl("/")  // 로그아웃 후 리다이렉트될 경로
					.invalidateHttpSession(true)  // 로그아웃 후 세션 무효화
			);
	return http.build();
}


	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
