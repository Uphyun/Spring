package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest //junit은 컨테이너를 생성 못함 그래서 test환경에서 IOC컨테이너를 생성해주는 역할을 함
class Boot01ApplicationTests {
	@Autowired // 필드주입방식 보안의 문제때문에 테스트 환경에서만 사용
	EmpMapper empMapper;
	
	//@Test
	void empList() { //전체조회
		List<EmpVO> list = empMapper.selectEmpAllList();
		assertTrue(!list.isEmpty()); // 해당변수 전체가 null 값으로 잡을수 없음
	}
	
	//@Test
	void empInfo(){ //단건조회
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		assertEquals("King",findVO.getLastName()); //findvo와 steven이 같다고 가정하고 정상실행되는지 확인
	}
	
	//@Test
	void empInsert() { //등록
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Hong");
		empVO.setEmail("kdHong123@google.com");
		empVO.setJobId("IT_PROG");
		empVO.setSalary(1000);
		
		int result = empMapper.insertEmpInfo(empVO);
		System.out.println(empVO.getEmployeeId());
		
		assertEquals(1, result);
	}
	
	//@Test
	void empDelete() { //삭제
		int result = empMapper.deleteEmpInfo(4323);
		assertEquals(1, result);
		
	}
	@Test
	void empUpdate() {
		
		//사용자가 수정하는 내용
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(4322);
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		findVO.setLastName("SANGHYUN");
		findVO.setEmail("sanghuyn@naver.com");
		findVO.setJobId("IT_PROG");
		findVO.setSalary(100000);
		
		int result = empMapper.updateEmpInfo(findVO.getEmployeeId(),findVO);
		assertEquals(1, result);
		
		// 사용자 수정 내용입력
	}
	
	

}
