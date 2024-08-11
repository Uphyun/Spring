package com.yedam.app.dept.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.dept.mapper.DeptMapper;
import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Service
public class DeptServiceImpl implements DeptService {
	
	private DeptMapper deptMapper;
	
	@Autowired
	public DeptServiceImpl(DeptMapper deptMapper) {
		this.deptMapper = deptMapper;
	}
	
	@Override
	public List<DeptVO> deptList(){
		return deptMapper.deptList();
	}

	@Override
	public DeptVO deptGet(DeptVO deptVO) {
		// TODO Auto-generated method stub
		return deptMapper.deptGet(deptVO);
	}

	@Override
	public int deptInsert(DeptVO deptVO) {
		// TODO Auto-generated method stub
		return deptMapper.insertDept(deptVO);
	}

	@Override
	public Map<String, Object> deptUpdate(DeptVO deptVO) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
	   int result =  deptMapper.updateDept(deptVO.getDepartmentId(), deptVO);
	   if(result == 1) {
		   isSuccessed = true;
	   }
	   map.put("result", isSuccessed);
	   map.put("target", deptVO);
	   
	   return map;
	}

	@Override
	public Map<String, Object> deptDelete(int deptId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		int result = deptMapper.deleteDept(deptId);
		if(result == 1) {
			map.put("id", deptId);
		}
		return map;
	}
	
	
	
}
