package com.yedam.app.common.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice //컨드롤이 동작할때 보조적인 역할하는 annotation
				 
public class WebControllerAdvice {
	@ModelAttribute("contextPath") //모든 컨트롤러가 변수를 등록할때 사용(전역변수)
	public String contextPath
			(final HttpServletRequest request) {
		return request.getContextPath();
	}
}
