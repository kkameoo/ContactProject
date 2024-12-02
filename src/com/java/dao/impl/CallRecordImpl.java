package com.java.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.java.dao.CallRecordDao;
import com.java.vo.CallRecordVo;

public class CallRecordImpl implements CallRecordDao{
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
	public List<CallRecordVo> getList() {
		List<CallRecordVo> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT cr.id, emp.id, emp.name, cr.start_date, cr.end_date "
					+ "FROM call_records cr JOIN employees emp "
					+ "ON cr.employee_id = emp.id";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long id = rs.getLong(1);
				Long employeeId = rs.getLong(2);
				String name = rs.getString(3);
				String input1 = rs.getString(4);
				String input2 = rs.getString(5);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime startDate = LocalDateTime.parse(input1, formatter);
				LocalDateTime endDate = LocalDateTime.parse(input2, formatter);
				int totalTime = ((endDate.getHour() * 60 * 60) + (endDate.getMinute() * 60) + (endDate.getSecond())) -
						((startDate.getHour() * 60 * 60) + (startDate.getMinute() * 60) + (startDate.getSecond()));
				CallRecordVo vo = new CallRecordVo(id, employeeId, name, startDate, endDate, totalTime);
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean call(Long num) {
		
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		LocalDateTime now = LocalDateTime.now();
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO call_records (start_date, employee_id) VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			pstmt.setString(1, formatedNow);
			pstmt.setLong(2, num);
			
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {}
		}
		return count == 1;
	}
	
	@Override
	public void callDown() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		LocalDateTime now = LocalDateTime.now();
		
		try {
			conn = getConnection();
			String sql = "UPDATE call_records SET end_date = ? WHERE id = "
					+ "(SELECT a.id FROM "
					+ "(SELECT id FROM call_records WHERE start_date = (SELECT max(start_date) FROM call_records)"
					+ ") AS a )";
			pstmt = conn.prepareStatement(sql);
			
			String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			pstmt.setString(1, formatedNow);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {}
		}		
	}
}
