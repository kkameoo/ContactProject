package com.java.dao;

import java.util.List;

import com.java.vo.CallRecordVo;

public interface CallRecordDao {
	public List<CallRecordVo> getList();
	public boolean call(Long num);
	public void callDown();
}
