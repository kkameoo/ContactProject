package com.java.dao.impl; // 구현 해주기위한 메서드 설계역할

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.java.dao.EmploymentDao;
import com.java.vo.DepartmentListVo;
import com.java.vo.EmploymentVo;
import com.java.vo.RankVo;
import com.java.vo.RegionsVo;


public class EmploymentImpl implements EmploymentDao {
	static final String dburl = "jdbc:mysql://localhost:3306/contactdb";	//	연결 완료
	static final String dbuser = "root";	
	static final String dbpass = "root"; 
	
	private Connection getConnection() throws SQLException { // 커넥션객체
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(dburl, dbuser, dbpass);
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패");
		} 
		return conn;
	}

	// 모든 목록 얻기
	@Override
	public List<EmploymentVo> getList() {
		
		List<EmploymentVo> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT id, name, age, number, email, address, department_id, employee_rank, salary " +
					"FROM employees";	
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String age = rs.getString(3);
				String number = rs.getString(4);
				String email = rs.getString(5);
				String address = rs.getString(6);
				Long departmentId = rs.getLong(7);
				String employeeRank = rs.getString(8);
				Long salary = rs.getLong(9);
				
				EmploymentVo vo = new EmploymentVo(id, name, age, number, email, address, departmentId, employeeRank, salary);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch(Exception e) {}
		}
		return list;
	}

	// name 검색
	@Override
	public List<EmploymentVo> search(String keyword) {
		
		List<EmploymentVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT id, name, age, number, email, address, department_id, employee_rank, salary " +
					"FROM employees " +
					"WHERE name LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String age = rs.getString(3);
				String number = rs.getString(4);
				String email = rs.getString(5);
				String address = rs.getString(6);
				Long departmentId = rs.getLong(7);
				String employeeRank = rs.getString(8);
				Long salary = rs.getLong(9);
				EmploymentVo vo = new EmploymentVo(id, name, age, number, email, address, departmentId, employeeRank, salary);
				
				list.add(vo);
			}		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		return list;
	}
	
	// ID 검색
	@Override
	public List<EmploymentVo> searchId(Long keyId) {
		
		List<EmploymentVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT id, name, age, number, email, address, department_id, employee_rank, salary " +
					"FROM employees " +
					"WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, keyId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String age = rs.getString(3);
				String number = rs.getString(4);
				String email = rs.getString(5);
				String address = rs.getString(6);
				Long departmentId = rs.getLong(7);
				String employeeRank = rs.getString(8);
				Long salary = rs.getLong(9);
				EmploymentVo vo = new EmploymentVo(id, name, age, number, email, address, departmentId, employeeRank, salary);
				list.add(vo);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		return list;
	}

	
	

	// 부서별 목록
	@Override
	public List<DepartmentListVo> searchDptId(Long keyDptId) {
		List<DepartmentListVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT emp.id, emp.name, age, number, email, address, dpt.name, employee_rank, salary " +
					"FROM employees emp JOIN departments dpt ON emp.department_id = dpt.id " +
					"WHERE department_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, keyDptId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String age = rs.getString(3);
				String number = rs.getString(4);
				String email = rs.getString(5);
				String address = rs.getString(6);
				String departmentName = rs.getString(7);
				String employeeRank = rs.getString(8);
				Long salary = rs.getLong(9);
				
				DepartmentListVo vo = new DepartmentListVo(id, name, age, number, email, address, departmentName, employeeRank, salary);
				list.add(vo);
			
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		return list;
	}
	
	
	// 전화번호 검색
	@Override
	public List<EmploymentVo> searchNumber(String keyNumber) {
		
		List<EmploymentVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT id, name, age, number, email, address, department_id, employee_rank, salary " +
					"FROM employees " +
					"WHERE number Like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyNumber + "%");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String age = rs.getString(3);
				String number = rs.getString(4);
				String email = rs.getString(5);
				String address = rs.getString(6);
				Long departmentId = rs.getLong(7);
				String employeeRank = rs.getString(8);
				Long salary = rs.getLong(9);
				EmploymentVo vo = new EmploymentVo(id, name, age, number, email, address, departmentId, employeeRank, salary);
				
				list.add(vo);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		return list;
	}

	// 사원 추가
	@Override
	public boolean insert(EmploymentVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			
			String sql = "INSERT INTO employees (name, age, number, email, address, department_id, employee_rank, salary) " + 
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getAge());
			pstmt.setString(3, vo.getNumber());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
			pstmt.setLong(6, vo.getDepartmentId());
			pstmt.setString(7, vo.getEmployeeRank());
			pstmt.setLong(8, vo.getSalary());
					
			insertedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		return 1 == insertedCount;
	}

	// 사원 수정
	@Override
	public boolean update(EmploymentVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updatedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "UPDATE employees SET name=?, age=?, number=?, email=?, address=?, department_id=?, employee_rank=?, salary=? " +
					"WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getAge());
			pstmt.setString(3, vo.getNumber());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
			pstmt.setLong(6, vo.getDepartmentId());
			pstmt.setString(7, vo.getEmployeeRank());
			pstmt.setLong(8, vo.getSalary());
			pstmt.setLong(9, vo.getId());
			
			updatedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		return 1 == updatedCount;
	}

	// 사원 삭제
	@Override
	public boolean delete(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM employees " +
					"WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			
			deletedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		return 1 == deletedCount;
	}

	// ID로 1개 검색
	@Override
	public EmploymentVo getEmploymentById(Long keyword) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmploymentVo vo = null;
		try {
			conn = getConnection();
			String sql = "SELECT id, name, age, email, address, department_id, employee_rank, salary, number " +
					"FROM employees " +
					"WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, keyword);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String age = rs.getString(3);
				String email = rs.getString(4);
				String address = rs.getString(5);
				Long departmentId = rs.getLong(6);
				String employeeRank = rs.getString(7);
				Long salary = rs.getLong(8);
				String number = rs.getString(9);
				vo = new EmploymentVo(id, name, age, number,email, address, departmentId, employeeRank, salary);
			}				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if( rs != null) rs.close();
					if( pstmt != null ) pstmt.close();
					if( conn != null ) conn.close();
				} catch (Exception e) {}
			}
		return vo;
	}
	
//	지사별 목록
	@Override
	public List<RegionsVo> searchRegions(Long keyRegions) {
		List<RegionsVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT emp.id, emp.name, emp.age, emp.number, dpt.name, emp.employee_rank, dpt.department_number, reg.name " + 
					"FROM employees emp JOIN departments dpt ON emp.department_id = dpt.id " + 
				    "JOIN regions reg ON dpt.regions_id = reg.id " +
				    "WHERE reg.id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, keyRegions);
			rs = pstmt.executeQuery();

			
			while (rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String age = rs.getString(3);
				String number = rs.getString(4);
				String departmentName = rs.getString(5);
				String employeeRank = rs.getString(6);
				String departmentNumber = rs.getString(7);
				String regionsName = rs.getString(8);
				
			
				
				RegionsVo vo = new RegionsVo(id, name, age, number, departmentName, employeeRank, departmentNumber, regionsName);
				list.add(vo);
			
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		return list;
	}
	
	//	직급별 목록
	@Override
	public List<RankVo> searchRank(String keyRank) {
		List<RankVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT emp.id, emp.name, emp.age, emp.number, dpt.name, emp.employee_rank, emp.salary " + 
					"FROM employees emp JOIN departments dpt ON emp.department_id = dpt.id " + 
				    "WHERE emp.employee_rank=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyRank);
			rs = pstmt.executeQuery();

			
			while (rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String age = rs.getString(3);
				String number = rs.getString(4);
				String departmentName = rs.getString(5);
				String employeeRank = rs.getString(6);
				Long salary = rs.getLong(7);
				
				
				RankVo vo = new RankVo(id, name, age, number, departmentName, employeeRank, salary);
				list.add(vo);
			
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {}
		}
		return list;
	}
	
	
	// 사원 수정
		@Override
		public boolean updateCustom(int num, String value, Long id) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			int updatedCount = 0;
			String a = "";
			try {
				switch (num) {
				case 1 : {
					a = "name";
					break;
					}
				case 2 : {
					a = "age";
					break;
					}
				case 3 : {
					a = "number";
					break;
					}
				case 4 : {
					a = "email";
					break;
					}
				case 5 : {
					a = "address";
					break;
					}
				case 6 : {
					a = "department_id";
					break;
					}
				case 7 : {
					a = "employee_rank";
					break;
					}
				case 8 : {
					a = "salary";
					break;
					}
				}
				conn = getConnection();
				String sql = "UPDATE employees SET " + a + " = ? " +
						"WHERE id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, value);
				pstmt.setLong(2, id);
				
				updatedCount = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null) pstmt.close();
					if (conn != null) conn.close();
				} catch (Exception e) {}
			}
			return 1 == updatedCount;
		}
}
