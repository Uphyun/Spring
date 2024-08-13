package com.yedam.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yedam.app.aop.service.AaaService;

@SpringBootTest
public class AopTests {
	@Autowired
	AaaService aaaService;
	
	@Test
	void transcationalTest() {
		aaaService.insert();
	}
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	void pwdEconderTest() {
		String password = "1234";
		
		//DB에 저장된 비밀번호 => 암호화 작업
		String enPwd = passwordEncoder.encode(password); //1. encode 작업으로 암호화 후
		System.out.println(enPwd);
		
		boolean result = passwordEncoder.matches(password, enPwd); //2. matches 함수를 사용해서 비교 
		System.out.println(result);
	}
}
