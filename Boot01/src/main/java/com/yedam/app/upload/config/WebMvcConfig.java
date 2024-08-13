package com.yedam.app.upload.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{ // 내부 메소드 사용
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// URL -> D:\ upload\학생목록.png 실제경로 -> mapping http://localhost:8099/yedam/images/학생목록.png
		registry.addResourceHandler("/images/**")
				.addResourceLocations("file:///" + uploadPath, "");
	}
	
}
