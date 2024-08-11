package com.yedam.app.dept.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.dept.service.DeptVO;

public interface DeptMapper {
	public List<DeptVO> deptList();
	public DeptVO deptGet(DeptVO deptVO);
	public int insertDept(DeptVO deptVO);
	public int updateDept(@Param("id") int deptid, @Param("dept") DeptVO deptVO);
	public int deleteDept(int deptid);

}
