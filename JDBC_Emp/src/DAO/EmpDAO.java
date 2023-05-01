package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.EmpDTO;
import UTILS.SingletonHelper;

public class EmpDAO {
	/*
	 * EMP 테이블 .......
	 * 전체조회
	 * 조건조회 where empno=?
	 * 삽입 insert into emp( ....) values(?,?,?,?,?,?,?,?)
	 * 삭제 delete from emp where empno=?	
	 * 수정 update emp set ename=? , job=? , sal=? , hiredate=? where empno=?	
	 * Like 검색 >> 이름 검색 
	 * 5개의 함수 생성 처리 (답글로 올려주세요)
	 * 
	 * 	private int empno;
	 * 	private String ename;
	 * 	private String job;	
	 *  private int sal;
	 *  private Date hiredate;
	 */
	
	// 전체 조회
	public List<EmpDTO> getSelectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EmpDTO> empList = new ArrayList<EmpDTO>();
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select empno, ename, job, sal, hiredate from emp";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					EmpDTO edto = new EmpDTO();
					edto.setEmpno(rs.getInt(1));
					edto.setEname(rs.getString(2));
					edto.setJob(rs.getString(3));
					edto.setEmpno(rs.getInt(4));
					edto.setHiredate(rs.getDate(5));
					empList.add(edto);
				}while(rs.next());
			}else {
				System.out.println("조회된 데이터가 없습니다.");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}		
		return empList;
	}
	
	// 조건 조회
	public EmpDTO getSelectOne(int empno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		EmpDTO edto = new EmpDTO();
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select empno, ename, job, sal, hiredate from emp where empno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				edto.setEmpno(rs.getInt(1));
				edto.setEname(rs.getString(2));
				edto.setJob(rs.getString(3));
				edto.setEmpno(rs.getInt(4));
				edto.setHiredate(rs.getDate(5));
			}			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}		
		return edto; 
	}
	
	// Like 검색
	public List<EmpDTO> getSelectLike(String ename) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EmpDTO> empList = new ArrayList<EmpDTO>();
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select empno, ename, job, sal, hiredate from emp where ename like ?";
			pstmt = conn.prepareStatement(sql);
			String search = "%"+ename+"%";
			pstmt.setString(1, search);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					EmpDTO edto = new EmpDTO();
					edto.setEmpno(rs.getInt(1));
					edto.setEname(rs.getString(2));
					edto.setJob(rs.getString(3));
					edto.setEmpno(rs.getInt(4));
					edto.setHiredate(rs.getDate(5));
					empList.add(edto);
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}		
		return empList;
	}
	
	// 삽입
	public int empInsert(EmpDTO edto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "insert into emp(empno, ename, job, sal, hiredate) values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, edto.getEmpno());
			pstmt.setString(2, edto.getEname());
			pstmt.setString(3, edto.getJob());
			pstmt.setInt(4, edto.getSal());
			System.out.println("!!");
			pstmt.setDate(5, edto.getHiredate());
			row = pstmt.executeUpdate();
			if(row > 0) {
				System.out.println("insert row count : " + row);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			SingletonHelper.close(pstmt);
		}		
		return row;
	}
	
	// 삭제
	public int empDelete(int empno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "delete from emp where empno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			row = pstmt.executeUpdate();
			if(row > 0) {
				System.out.println("delete row count : " + row);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			SingletonHelper.close(pstmt);
		}		
		return row;
	}
	
	// 수정
	public int empUpdate(EmpDTO edto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "update emp set ename = ?, job = ?, sal = ?, hiredate = ? where empno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, edto.getEname());
			pstmt.setString(2, edto.getJob());
			pstmt.setInt(3, edto.getSal());
			pstmt.setDate(4, edto.getHiredate());
			pstmt.setInt(5, edto.getEmpno());
			
			row = pstmt.executeUpdate();
			if(row > 0) {
				System.out.println("update row count : " + row);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			SingletonHelper.close(pstmt);
		}		
		return row;
	}	
}
