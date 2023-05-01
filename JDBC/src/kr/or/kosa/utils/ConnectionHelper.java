package kr.or.kosa.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * JDBC 작업(DB 결정)
 * ex) 회원 테이블
 * 1. 전체 조회, 조건 조회, 삽입, 삭제, 수정
 * 
 * 각각의 작업을 할때마다
 * 1. 드라이버 로딩
 * 2. 연결 객체 생성 .. 자원 해제
 * 3. 반복적인 코드 ... 제거
 * 공통 작업을 별도로 분리 했으면 좋겠다 ..
 * 
 * 기능(함수) >> 자주(static) >> 오버로딩(overloading)(편하게) >> 다형성(유연하게)
 *
 */
public class ConnectionHelper {
	public static Connection getConnection(String dsn) { // 어떤 자원을 쓸지를 받는다
		Connection conn = null;		
		try {
			if(dsn.equals("oracle")) {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KOSA", "1004");
			}else if(dsn.equals("mariadb")) {
				conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/kosadb", "kosa", "1004");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}		
		return conn;
	}
	
	public static Connection getConnection(String dsn, String id, String pwd) { // 어떤 자원을 쓸지를 받는다
		Connection conn = null;		
		try {
			if(dsn.equals("oracle")) {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", id, pwd);
			}else if(dsn.equals("mariadb")) {
				conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/kosadb", id, pwd);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}		
		return conn;
	}
	
	public static void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
