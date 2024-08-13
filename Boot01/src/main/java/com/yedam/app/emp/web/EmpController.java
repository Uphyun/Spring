package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller // == router 이랑 같음 : 사용자의 요청(endpoint)와 그에 대한 처리
			// : URL + METHOD 가 endpoint => Service => View
public class EmpController {
	// 해당 컨트롤러에서 제공하는 서비스
	private EmpService empService;
	
	@Autowired
	EmpController(EmpService empService){
		this.empService = empService;
	}
	
	// GET : 조회, 빈페이지(보안안됌)
	// POST : 데이터 조자(등록, 수정, 삭제)
	
	//전제조회 : GET
	@GetMapping("empList") // Model = Request + Response
	public String empList(Model model) {
		// 1) 해당기능 수행 => Service
		List<EmpVO> list = empService.empList();
		
		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("emps", list);// ==req.setAtrribute
		
		// 3)데이터를 출력할 페이지 결정
		return "emp/list"; 
		// classpath :/templates/emp/list .html 
		// prefix                 return subfix
		
		
	}
	
	//단건조회 : GET => QueryString 많이 씀
	@GetMapping("empInfo")
	    
	public String empInfo(EmpVO empVO, Model model) {
		// 1) 해당기능 수행 => Service
		EmpVO findVO = empService.empInfo(empVO);
		
		// 2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("emp",findVO);
		
		// 3)데이터를 출력할 페이지 결정
		//return "emp/info";
		//classpath:/templates/ emp/info .html
		return "emp/info";
	}
	
	//등록 - 페이지
	@GetMapping("empInsert")
	public String empInsertForm() {
		return "emp/insert";
	}
	
	//등록 - 처리 : POST => form태그를 통한 submit => QueryString => 커맨드 객체
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.empInsert(empVO);
		String url = null;
		if(eid > -1) {
			//정상적으로 등록된 경우
			url = "redirect:empInfo?employeeId=" + eid;
		}else {
			//등록되지 않은 경우
			url = "redirect:empList";
		}
		return url;
	}
	
	//수정 - 페이지 <=> 단건조회
	@GetMapping("empUpdate")
	public String empUpdateForm(EmpVO empVO, Model model) {
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("emp",findVO);
		return "emp/update";
	}
	
	/*
	 * //수정 - 처리 : AJAX => QueryString
	 * 
	 * @PostMapping("empUpdate")
	 * 
	 * @ResponseBody //AJAX public Map<String, Object>
	 * empupdateAJAXQueryString(EmpVO empVO){ return empService.empUpdate(empVO);
	 * //contentType 지정할필요 없음 어차피 JSON임 }
	 */
	
	
	  //수정 - 처리 : AJAX => JSON (@RequestBody)
	  
	  @PostMapping("empUpdate")
	  @ResponseBody //AJAX public Map<String, Object>
		/*
		 * Map<String, Object> empupdateAJAXJSON(@RequestBody EmpVO empVO){
		 * 
		 * return empService.empUpdate(empVO); }
		 * 
		 * i)contentType : 'application/json', data : JSON.stringify(dataObj) //body 반드시
		 * 보내줘야함
		 */
	
	//삭제 - 처리 : GET(넘길데이터가 별로 없을 때) POST(데이터가 많을 때)
	@GetMapping("empDelete")
	public String empDelete(Integer employeeId) {
		empService.empDelete(employeeId);
		return "redirect:empList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
