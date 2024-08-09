package com.yedam.app.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.yedam.app.emp.service.EmpVO;

@Aspect //AOP의 설정
@Component
public class CommonAspect {
	//포인트 컷 : 조인포인트 중에서 Advice(횡단관심)이 적용될 메소드 필터
	@Pointcut("within(com.yedam.app.emp.service.impl.*)")// within은 지정한 경로에 모든 파일 총칭
	public void empPointCut() {} // 포인트컷의 결과를 불러오는 역할
	
	//Weaving : 포인트 컷 + 타이밍 + Advice(횡단관심)
	@Before("empPointCut()")
	public void beforeAdvice(JoinPoint joinPoint) { //joinPoint service 메소드를 갖고있음
		String sinagerStr = joinPoint.getSignature().toString();
		Object[] args = joinPoint.getArgs();
		System.out.println("####### 실행 : " + sinagerStr);
		for(Object arg : args) {
			System.err.println("매개변수 ");
			if(arg instanceof Integer) {
				System.err.println((Integer)arg);
			}else if(arg instanceof EmpVO) {
				System.err.println((EmpVO)arg);
			}
		}
	}
	
	@Around("empPointCut()")
	public Object executeTime(ProceedingJoinPoint joinPoint) throws Throwable {
		String signaterStr
			= joinPoint.getSignature().toString();
		System.out.println("=== 시작 : " + signaterStr);
		
		//공통기능
		System.out.println("=== 핵심 기능전 실행 : " + System.currentTimeMillis());
		
		try {
			// 비즈니스 메소드를 실행
			Object obj = joinPoint.proceed();
			return obj;
		}finally {
			// 공통기능
			System.out.println("=== 핵심 기능 후 실행 : "
					 			+ System.currentTimeMillis());
			System.out.println("=== 끝 : " + signaterStr);
		}
	}
	
}
