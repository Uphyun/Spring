package com.yedam.app.test.web;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;
@CrossOrigin(origins = "*")
@Controller // 웹을 처리하는 Bean으로 컨트롤러(Annotation)가 필요함
public class ParamController {
	// QueryString(질의문자열) : key=value&key=value&...
	// Content-type : application/x-www-form-urlcode
	// Method : 상관없음
	
	//1. QueryString => 커맨드 객체 (어노테이션 필요없음, 객체 , 검색할때 많이 씀)
	@RequestMapping(path="comobj", 
			method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String commandObject(EmpVO empVO) {
		String result = "";
		result += "Path : /comobj \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
	
	//2. QueryString => @RequestParam : 기본타입 필요, 단일값
	@RequestMapping(path="reqparm", 
			method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String requsetParam(@RequestParam Integer employeeId, //생략 불가능
											   String lastName, // 생략가능
			@RequestParam(name="message", defaultValue="No mesaage", required = true) String msg) {
		String result = "";
		result += "Path : /reqparm \n";
		result += "\t employee_id : " + employeeId;
		result += "\t last_name : " + lastName;
		result += "\t message : " + msg;
		return result;
	}
	
	//3. @PathVariable : 경로에 값을 포함하는 방식, 단일값 !!
	//Method는 상관없음
	// Content-type도 상관없음
	@RequestMapping("path/{id}") // path/Hong, path/1234
	@ResponseBody
	public String pathVariable(@PathVariable String id) {
		String result= "";
		result += "path : /path/{id} \n";
		result += "\t id : " + id;
		return result;
	}
	
	//4. @RequestBody : JSON 포맷, *배열행태*
	// Method : POST, PUT
	// Content-type : application/json
	@PostMapping("resbodyList")
	@ResponseBody
	public String requestBodyList(@RequestBody List<EmpVO> list) {
		String result = "path: /resbodyList \n";
		for(EmpVO empVO : list) {
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		}
		return result;
	}
	
	//4. @RequestBody : JSON 포맷, *객체형태*
	// Method : POST, PUT
	// Content-type : application/json
	@PostMapping("resbody")
	@ResponseBody
	public String requestBody(@RequestBody EmpVO empVO) {
		String result = "path: /resbody \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
	
}
