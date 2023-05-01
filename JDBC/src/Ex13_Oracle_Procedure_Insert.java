import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import kr.or.kosa.utils.SingletonHelper;

/**
 * create or replace procedure usp_Insert_Emp
 * (
 *     vempno IN emp.empno%TYPE,
 *     vename IN emp.ename%TYPE,
 *     vjob IN emp.job%TYPE,
 *     p_outmsg OUT varchar2
 * )
 * is
 *     begin
 *         insert into emp(empno, ename, job) values(vempno, vename, vjob);
 *         commit;
 *         p_outmsg := 'success'; -- 할당은 이모티콘
 *         EXCEPTION WHEN OTHERS THEN
 *         p_outmsg := SQLERRM; -- oracle이 가지고 있는 오류메시지
 *         rollback;
 *     end;
 */
    
public class Ex13_Oracle_Procedure_Insert {
	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement cstmt = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "{call usp_Insert_Emp(?, ?, ?, ?)}";
			cstmt = conn.prepareCall(sql);
			
			// 3개는 INPUT
			// 1개는 OUTPUT -> 문자열 타입
			cstmt.setInt(1, 9999);
			cstmt.setString(2, "홍길동");
			cstmt.setString(3, "IT");			
			cstmt.registerOutParameter(4, Types.VARCHAR); // 문자열 타입 가져오기 p_outmsg OUT varchar2
			
			cstmt.execute();
			
			String oracle_msg = (String)cstmt.getObject(4);
			System.out.println(oracle_msg);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			SingletonHelper.close(cstmt);
		}
	}
}
