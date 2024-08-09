package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@RestController // @Controller + 모든 메소드에 @ResponseBody 
				// @ResponseBody가 AJAX를 쓰겠다는 선언
				// REST 방식은 기본적으로 PathVariable 방식과 JSON 이지만 필요에 따라 QueryString 방식도 사용

public class EmpRestController {
	
	private EmpService empService;
	
	@Autowired
	EmpRestController(EmpService empService){
		this.empService = empService;
	}
	
	//전체조회 : GET => emps
	@GetMapping("emps")
	public List<EmpVO> empList(){
		return empService.empList();
	}
	//단건조회 : GET => emps/100
	@GetMapping("emps/{eid}")
	public EmpVO empInfo(@PathVariable(name="eid") Integer employeesId) {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(employeesId);
		return empService.empInfo(empVO);
	}
	
	//등록 : POST => emps
	@PostMapping("emps") // @RequsetBody : JSON
	public int empInsert(@RequestBody EmpVO empVO) {
		return empService.empInsert(empVO);
	}
	
	//수정 : PUT => emps/100
	@PutMapping("emps/{employeeId}") // @RequsetBody : JSON
	public Map<String, Object> empUpdate(@PathVariable Integer employeeId, @RequestBody EmpVO empVO) {
		empVO.setEmployeeId(employeeId);
		return empService.empUpdate(empVO);
	}
	
	//삭제 : DELETE => emps/100
	@DeleteMapping("emps/{employeeId}")
	public Map<String, Object> empDelete(@PathVariable Integer employeeId){
		return empService.empDelete(employeeId);
	}
	

}
