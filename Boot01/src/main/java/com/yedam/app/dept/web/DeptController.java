package com.yedam.app.dept.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Controller
public class DeptController {
	private DeptService deptService;
	
	DeptController(DeptService deptService){
		this.deptService = deptService;
	}
	
	@GetMapping("deptList")
	public String deptList(Model model) {
		List<DeptVO> list = deptService.deptList();
		
		model.addAttribute("depts" , list);
		
		return "dept/list";
	}
	
	@GetMapping("deptInfo")
	public String deptGet(DeptVO deptVO, Model model) {
		DeptVO findVO = deptService.deptGet(deptVO);
		model.addAttribute("dept" , findVO);
		
		return "dept/info";
	}
	
	@GetMapping("deptInsert")
	public String deptInsertForm() {
		return "dept/insert";
	}
	
	@PostMapping("deptInsert")
	public String deptInsert(DeptVO deptVO) {
		int deptid = deptService.deptInsert(deptVO);
		String url = null;
		if(deptid > -1) {
			url = "redirect:deptInsert?deptmentId=" + deptid;
		}else {
			url = "redirect:deptList";
		}
		return url;
	}
	
	@GetMapping("deptUpdate")
	public String deptUpdateForm(DeptVO deptVO, Model model) {
		DeptVO findVO = deptService.deptGet(deptVO);
		
		model.addAttribute("dept", findVO);
		
		return "dept/update";
	}
	
	@PostMapping("deptUpdate")
	@ResponseBody
	public Map<String, Object> deptUpdate(DeptVO deptVO) {
		return deptService.deptUpdate(deptVO);
	}
	
	@GetMapping("deptDelete")
	public String deptDelete(Integer deptId) {
		deptService.deptDelete(deptId);
		return "redirect:deptList";
	}
	
}
