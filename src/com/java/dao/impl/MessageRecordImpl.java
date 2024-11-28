package com.java.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.dao.MessageRecordDao;
import com.java.vo.MessageRecordVo;

public class MessageRecordImpl implements MessageRecordDao{
	static final String dburl = "jdbc:mysql://localhost:3306/contactdb";
	static final String dbuser = "root";	
	static final String dbpass = "root";
	
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbuser, dbpass);
		} catch (ClassNotFoundException e) {
			System.err.println("드라이브 로드 실패");
		}
		return conn;
	}
	
	@Override
	public void send(String message, Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "INSERT INTO message_records (message_comment, read_check, employee_id) VALUES "
					+ "(?, 1, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, message);
			pstmt.setLong(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	@Override
	public List<MessageRecordVo> getList() {
		List<MessageRecordVo> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT mr.id, emp.id, emp.name, mr.read_check, mr.regdate FROM message_records mr JOIN employees emp "
					+ "ON mr.employee_id = emp.id";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Long id = rs.getLong(1);
				Long empId = rs.getLong(2);
				String empName = rs.getString(3);
				int check = rs.getInt(4);
				String regdate = rs.getString(5);
				MessageRecordVo vo = new MessageRecordVo(id, empId, empName, regdate, check);
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
	public MessageRecordVo getVo(Long id) {
		MessageRecordVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = "SELECT mr.id, emp.id, emp.name, emp.number, mr.message_comment, mr.read_check, mr.regdate"
					+ " FROM message_records mr JOIN employees emp"
					+ " ON mr.employee_id = emp.id WHERE mr.id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Long mrId = rs.getLong(1);
				Long empId = rs.getLong(2);
				String empName = rs.getString(3);
				String empNumber = rs.getString(4);
				String message = rs.getString(5);
				int check = rs.getInt(6);
				String regdate = rs.getString(7);
				vo = new MessageRecordVo(mrId, empId, empName, empNumber, message, check, regdate);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return vo;
	}
	
	@Override
	public void readUpdate(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql  = "UPDATE message_records SET read_check = 0 WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
