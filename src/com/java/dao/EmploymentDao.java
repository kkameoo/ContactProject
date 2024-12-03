package com.java.dao; // 선언역할

import java.util.List;

import com.java.vo.DepartmentListVo;
import com.java.vo.EmploymentVo;
import com.java.vo.RankVo;
import com.java.vo.RegionsVo;

public interface EmploymentDao {
	public List<EmploymentVo> getList();	//	전체 목록
	public List<EmploymentVo> search(String keyword);	//	이름 검색
	public List<EmploymentVo> searchId(Long keyId);	//	id 검색
	public List<EmploymentVo> searchNumber(String keyNumber);	//	전화번호 검색
	public boolean insert(EmploymentVo vo);
	public boolean update(EmploymentVo vo);
	public boolean delete(Long id);
	public EmploymentVo getEmploymentById(Long id);
	public List<EmploymentVo> getListDesc();
	
	public List<DepartmentListVo> searchDptId(Long keyDptId);	//	부서별 목록
	public List<RegionsVo> searchRegions(Long keyRegions);//	지사별 목록
	public List<RankVo> searchRank(String keyRank);	//	직급별 목록
	public boolean updateCustom(int num, String value, Long id);	//	개별 업데이트
}