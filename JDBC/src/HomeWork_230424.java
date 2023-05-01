import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.or.kosa.utils.SingletonHelper;

public class HomeWork_230424 {
	public static void main(String[] args) {
		// mariaDB
		// 1. 테이블 생성
		// 2. insert
		// 3. update
		// 4. select

		/*
		// insert
		// insert into dmlemp(empno, ename, deptno) values(?, ?, ?)

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = SingletonHelper.getConnection("mariadb");
			String sql = "insert into dmlemp(empno, ename, deptno) values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql); // 미리 컴파일

			// 파라미터만 설정해서 보내기
			pstmt.setInt(1, 9900);
			pstmt.setString(2, "홍길동");
			pstmt.setInt(3, 20);

			// 실행
			int row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("insert row count : " + row);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
			SingletonHelper.close(conn);
		}
		 */

		
		// update
		// update dmlemp set ename = ?, sal = ?, job = ?, deptno = ?
		// where empno = ?

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = SingletonHelper.getConnection("mariadb");
			String sql = "update dmlemp set ename = ?, sal = ?, job =?, deptno = ? where empno = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "누굴까");
			pstmt.setInt(2, 2000);
			pstmt.setString(3, "IT");
			pstmt.setInt(4, 10);
			pstmt.setInt(5, 9900);

			int row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("update row count : " + row);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
			SingletonHelper.close(conn);
		}
		
	}
}
