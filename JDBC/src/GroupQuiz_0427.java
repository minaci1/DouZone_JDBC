import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import kr.or.kosa.utils.SingletonHelper;

public class GroupQuiz_0427 {
	public static void main(String[] args) {
		GroupQuiz_0427 gq = new GroupQuiz_0427();
		gq.getEmpList();
		gq.setInsertEmp();
	}
	
	// select
	public void getEmpList() {
		Connection conn = null;
		
		CallableStatement cstmt = null; // 명령객체(프로시저)
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("mariadb");
			String sql = "{call usp_EmpList(?)}";
			cstmt = conn.prepareCall(sql);
			
			cstmt.setInt(1, 2000);			
			rs = cstmt.executeQuery();			
			
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
	
	// insert
	public void setInsertEmp() {
		Connection conn = null;
		CallableStatement cstmt = null;
		
		try {
			conn = SingletonHelper.getConnection("mariadb");
			String sql = "{call usp_Insert_Emp(?, ?, ?, ?)}";
			
			cstmt = conn.prepareCall(sql);
			
			cstmt.setInt(1, 9999);
			cstmt.setString(2, "진호");
			cstmt.setString(3, "IT");
			cstmt.registerOutParameter(4, Types.VARCHAR);
			
			cstmt.execute();
			
			String maria_msg = (String)cstmt.getObject(4);
			System.out.println(maria_msg);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			SingletonHelper.close(cstmt);
		}
	}
}
