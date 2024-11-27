package com.java.dao;

import java.util.List;

import com.java.vo.EmploymentVo;

public interface EmploymentDao {
	public void showInfo();
	public List<EmploymentVo> getList();	//	전체 목록
	public List<EmploymentVo> search(String keyword);	//	검색
	public boolean insert(EmploymentVo vo);
	public boolean update(EmploymentVo vo);
	public boolean delete(Long id);
}
