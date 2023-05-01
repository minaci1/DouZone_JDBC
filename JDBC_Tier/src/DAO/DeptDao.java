package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.Dept;
import UTILS.SingletonHelper;

/**
 * 1. DB 연결 2. CRUD 함수 만들기 3. 기본 5가지(전체 조회, 조건 조회, insert, update, delete) +
 * 알파(조건 검색, 문자열 검색 등등)
 */
public class DeptDao {
	// 1. 전체 조회 >> select 결과 >> return multi row(Dept 객체 여러개)
	// select deptno, dname, loc from dept

	// 2. 조건 조회 >> select 결과(where deptno = ?) >> return single row(Dept 객체 1개)
	// select deptno, dname, loc from dept where deptno = ?

	// 3. 데이터 삽입 >> parameter(Dept 객체) >> return 정수
	// insert into dept(deptno, dname, loc) values(?, ?, ?)

	// 4. 데이터 수정 >> parameter(Dept 객체) >> return 정수
	// update dept set dname = ?, loc = ? where deptno = ?

	// 5. 데이터 삭제 >> parameter(deptno) >> return 정수
	// delete from dept where deptno = ?

	public List<Dept> selectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Dept> deptList = new ArrayList<Dept>();

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select deptno, dname, loc from dept";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					Dept dept = new Dept();
					dept.setDeptno(rs.getInt(1));
					dept.setDname(rs.getString(2));
					dept.setLoc(rs.getString(3));
					deptList.add(dept);
				} while (rs.next());
			} else {
				System.out.println("조회된 데이터가 없습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		return deptList;
	}

	public Dept selectOne(int deptno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Dept dept = new Dept();

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select deptno, dname, loc from dept where deptno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
					dept.setDeptno(rs.getInt(1));
					dept.setDname(rs.getString(2));
					dept.setLoc(rs.getString(3));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}

		return dept;
	}

	public int deptInsert(Dept dept) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "insert into dept(deptno, dname, loc) values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dept.getDeptno());
			pstmt.setString(2, dept.getDname());
			pstmt.setString(3, dept.getLoc());
			row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("insert row count : " + row);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}		
		return row;
	}

	public int deptUpdate(Dept dept) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "update dept set dname = ?, loc = ? where deptno = ?";
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, dept.getDname());
			pstmt.setString(2, dept.getLoc());
			pstmt.setInt(3, dept.getDeptno());
			row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("insert row count : " + row);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}		
		return row;
	}

	public int deptDelete(int deptno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "delete from dept where deptno = ?";
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, deptno);
			row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("insert row count : " + row);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}		
		return row;
	}
}
