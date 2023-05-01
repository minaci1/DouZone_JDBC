import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.or.kosa.utils.SingletonHelper;

/**
 * create table trans_A(
 *     num number,
 *     name varchar2(20)
 * );
 * 
 * create table trans_B(
 *     num number constraints pk_trans_B_num primary key,
 *     name varchar2(20)
 * );
 * 
 * JDBC >> default(DML) >> auto commit >> 실반영된다 
 * JDBC >> auto commit >> 변경(옵션) >> False >> 개발자 직접(반드시) >> commit, rollback 제어 허락
 * 
 * 은행업무(여러개의 DML이 처리되어야 하는 경우)
 *
 */
public class Ex10_Oracle_Transaction {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		conn = SingletonHelper.getConnection("oracle");
		
		String sql = "insert into trans_A(num, name) values(100, 'A')";
		String sql2 = "insert into trans_B(num, name) values(100, 'B')";
		
		try {
			// 업무상(둘 다 성공 또는 둘 다 실패) >> 하나의 논리적인 단위
			conn.setAutoCommit(false); // *** JDBC에 대한 처리(commit, rollback)를 개발자가 직접 하겠다
			
			// begin tran
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt.executeUpdate();	// insert
			pstmt2.executeUpdate();	// insert
			
			// 예외가 발생하지 않으면
			conn.commit(); // 둘 다 정상적으로 실행되고 예외가 발생하지 않으면 commit 하겠다
		}catch(Exception e) {
			// 예외가 발생했을 때 rollback 처리
			System.out.println("문제 발생 : " + e.getMessage());
			try {
				conn.rollback(); // 둘 다 취소하겠다				
			}catch(Exception e1) {
				System.out.println(e1.getMessage());				
			}
		}finally {
			SingletonHelper.close(pstmt);
			SingletonHelper.close(pstmt2);
//			SingletonHelper.close(conn); 시스템이 살아있는동안 다른 처리를 위해 해제하지 않겠다
		}
	}
}
