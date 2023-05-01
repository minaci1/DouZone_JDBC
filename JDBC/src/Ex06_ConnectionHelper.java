import java.sql.Connection;

import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.SingletonHelper;

public class Ex06_ConnectionHelper {
	public static void main(String[] args) throws Exception{
		Connection conn = null;
		conn = ConnectionHelper.getConnection("mariadb");
		
		System.out.println(conn.toString());
		System.out.println(conn.getMetaData().getDatabaseProductName()); // 데이터베이스 이름(Oracle, MariaDB 등등)
		System.out.println(conn.getMetaData().getDatabaseProductVersion()); // 데이터베이스 버전
		System.out.println(conn.isClosed()); // 데이터베이스 연결여부
		
		ConnectionHelper.close(conn);
		System.out.println(conn.isClosed());
		
		conn = ConnectionHelper.getConnection("oracle");
		System.out.println(conn.toString()); // oracle.jdbc.driver.T4CConnection@58a9760d
		
		// getConnection 요청시마다 새로운 객체가 생성된다.
		// 현업(Connection Pool) >> 미리 연결 객체를 생성하고 >> 가져다 쓰고 반환하는 방식
		
		// 복습(Singleton)
		
	}
}
