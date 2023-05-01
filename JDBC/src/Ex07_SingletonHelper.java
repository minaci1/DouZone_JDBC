import java.sql.Connection;

import kr.or.kosa.utils.SingletonHelper;

public class Ex07_SingletonHelper {
	public static void main(String[] args) throws Exception{
		
		/*
		Connection conn = null;
		conn = SingletonHelper.getConnection("oracle");
		System.out.println(conn.toString());
		System.out.println(conn.getMetaData().getDatabaseProductName());
		
		Connection conn2 = null;
		conn2 = SingletonHelper.getConnection("oracle");
		System.out.println(conn2.toString());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		 */
		
		/*
		 * oracle.jdbc.driver.T4CConnection@6293abcc
		 * Oracle
		 * oracle.jdbc.driver.T4CConnection@6293abcc
		 * Oracle
		 */
		
		
		Connection conn = null;
		conn = SingletonHelper.getConnection("oracle");
		System.out.println(conn.toString());
		System.out.println(conn.getMetaData().getDatabaseProductName());
//		SingletonHelper.close(conn);
		SingletonHelper.dbClose();
		
		Connection conn2 = null;
		conn2 = SingletonHelper.getConnection("mariadb");
		System.out.println(conn2.toString());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		
	}
}
