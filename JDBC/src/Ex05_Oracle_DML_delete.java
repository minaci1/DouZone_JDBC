import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Ex05_Oracle_DML_delete {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			// 드라이버로딩 생략
			// Class.forName("oracle.jdbc.OracleDriver");
			// 연결 객체 생성하기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KOSA", "1004");
			stmt = conn.createStatement();
			
			// delete
			int deptno = 0;
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("부서 입력 : ");
			deptno = Integer.parseInt(sc.nextLine());
			
			// delete from dmlemp where deptno = 10;
			// 전통적이고 고전적인 방법
			String sql = "delete from dmlemp where deptno = " + deptno; // auto commit
			System.out.println(sql);
			// 현재
			// values(?, ?, ?) >> ?는 파라미터
			
			// executeUpdate() 함수 >> insert, update, delete 수행
			int resultRow = stmt.executeUpdate(sql);
			
			if(resultRow > 0) {
				System.out.println("반영된 행의 수 : " + resultRow);
			}else {
				System.out.println("예외가 발생된 것이 아니고, 반영된 행이 없다는 것 >> 예외가 터지면 catch로 빠짐");
			}
		}catch(Exception e) {
			// 문제
			System.out.println("SQL 예외발생 : " + e.getMessage());
		}finally {
			// 자원 해제
			try {
				stmt.close();
			}catch(SQLException e1) {
			
			}
			try {
				conn.close();
			}catch(SQLException e2) {
			
			}
		}
	}
}
