import java.sql.Connection;
import java.sql.DriverManager;

public class Ex02_Mariadb_Connection {
	public static void main(String[] args) throws Exception{
		// DB 연결 -> 명령 생성(CRUD) -> 실행 -> 처리 -> 자원 해제
		
		Class.forName("org.mariadb.jdbc.Driver");
		System.out.println("마리아DB 드라이버 로딩 ...");
		
		// 로딩된 드라이버를 통해서 Oracle 서버에 연결
		Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/kosadb", "kosa", "1004");
		// DriverManager가 mySQL, Oracle 등 연결 객체를 생성 >> Connection 인터페이스를 구현한 객체 >> 다형성
		System.out.println(conn.isClosed() + " 아니 -> false : 연결되어 있는데?"); // 연결이 끊겼는지 확인
		
	}
}
