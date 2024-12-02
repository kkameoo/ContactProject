package com.java.dao;

import java.util.List;

import com.java.vo.MessageRecordVo;

public interface MessageRecordDao {
	public boolean send(String message, Long id);
	public List<MessageRecordVo> getList();
	public MessageRecordVo getVo(Long id);
	public void readUpdate(Long id);
}
