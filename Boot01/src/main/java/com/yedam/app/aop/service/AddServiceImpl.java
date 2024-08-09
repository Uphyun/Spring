package com.yedam.app.aop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.aop.mapper.AaaMapper;

@Service
public class AddServiceImpl implements AaaService {
	private AaaMapper aaaMapper;
	
	@Autowired
	AddServiceImpl(AaaMapper aaaMapper){
		this.aaaMapper = aaaMapper;
	}
	
	@Transactional
	@Override
	public void insert() {
		aaaMapper.aaaInsert("101");
		aaaMapper.aaaInsert("a101");
	}
	
}
