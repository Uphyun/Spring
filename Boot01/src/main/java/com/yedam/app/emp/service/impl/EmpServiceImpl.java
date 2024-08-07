package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Service //** 까먹는 경우가 많은 annotation
//@AllArgsConstructor -> 생성자 주입 
public class EmpServiceImpl implements EmpService {
	
	private EmpMapper empMapper;
	
	@Autowired
	EmpServiceImpl(EmpMapper empMapper){
		this.empMapper = empMapper;
	}
	
	@Override
	public List<EmpVO> empList(){
		return empMapper.selectEmpAllList();
	}
	
	@Override
	public EmpVO empInfo(EmpVO empVO) {
		return empMapper.selectEmpInfo(empVO);
	}
	
	@Override
	public int empInsert(EmpVO empVO) {
		int result = empMapper.insertEmpInfo(empVO);
		
		return result == 1 ? empVO.getEmployeeId() : -1;
	}
	
	
	@Override
	public Map<String, Object> empUpdate(EmpVO empVO){
		
		Map<String, Object> map = new HashMap<>();	
		boolean isSuccessed = false;
		
		int result
		 = empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", empVO);
		
		return map;
	}
	
	public Map<String, Object> empDelete(int empId){
		Map<String, Object> map = new HashMap<>();
		
		int result = empMapper.deleteEmpInfo(empId);
		
		if(result == 1) {
			map.put("employeeId", empId);
		}
		
		return map;
		
		// 실행실패 { "employeeId" : 104}
		// 정상실행 {}
	}
}











