package com.java.dao; // 선언역할

import java.util.List;

import com.java.vo.EmploymentVo;

public interface EmploymentDao {
	public List<EmploymentVo> getList();	//	전체 목록
	public List<EmploymentVo> search(String keyword);	//	이름 검색
	public List<EmploymentVo> searchId(Long keyId);	//	id 검색
	public List<EmploymentVo> searchDptId(Long keyDptId);	//	부서 id 검색
	public List<EmploymentVo> searchNumber(String keyNumber);	//	전화번호 검색
	public boolean insert(EmploymentVo vo);
	public boolean update(EmploymentVo vo);
<<<<<<< HEAD
	public boolean delete(String name);
	
=======
	public boolean delete(Long id);
	public EmploymentVo getEmploymentById(Long id);

>>>>>>> 131252e7d9d2230afb7134cdc9eeda8a1191e803
}
