import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import kr.or.kosa.utils.SingletonHelper;
import oracle.jdbc.internal.OracleTypes;

public class Ex12_Oracle_Procedure_Select {
	public static void main(String[] args) {
		Connection conn = null;
		
		CallableStatement cstmt = null; // 명령객체(프로시저)
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "{call usp_EmpList(?, ?)}";
			cstmt = conn.prepareCall(sql);
			// 첫번째 ?는 INPUT
			// 두번쨰 ?는 OUTPUT
			cstmt.setInt(1, 2000);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR); // p_cursor OUT SYS_REFCURSOR,, SYS_REFCURSOR 타입이랑 맞춰주는 것
			
			boolean result = cstmt.execute(); // ?
			
			rs = (ResultSet)cstmt.getObject(2); // cstmt.getObject(물음표 위치) -> Object 타입이기 때문에 다운캐스팅 필요
												// rs -> JDBC쪽 메모리
			while(rs.next()) {
				System.out.println(rs.getInt(1) + " / " + rs.getString(2) + " / " + rs.getInt(3));				
			}			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(cstmt);
		}
	}
}
