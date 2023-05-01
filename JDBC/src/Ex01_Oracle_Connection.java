import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/*
JDBC
1. Java 언어(APP)를 통해서 Oracle(소프트웨어) 연결해서 CRUD작업
2. Java App : Oracle , My-sql , MS-sql 등등 연결하고 작업(CRUD)
	2.1 각각의 제품에 맞는 드라이버를 가지고 있어야 합니다
	CASE 1: 삼성 노트북 >> HP 프린터 연결 >> HP프린터 사이트에서 드라이버 다운 >> 삼성 설치
	CASE 2: HP프린터 제조 회사는 ... 삼성, LG 회사마다 적용할 수 있는 드라이버를 별도 제작
Oracle (C:\Douzone\DataBase\Connect_Utils\OracleJDBC\ojdbc6.jar)
Mysql (https://dev.mysql.com/downloads/connector/j/)
3. 드라이버를 참조 (현재 프로젝트에서 사용) >> Java Project -> 속성 -> build path -> add External JARs.. -> jar 파일 추가 
	3.1 드라이버 사용 준비 완료 >> 메모리에 load 사용 ....
	3.2 Class.forName("oracle.jdbc.OracleDriver")..... new 객체 생성 ....
4. JAVA CODE (CRUD) >> JDBC API 제공 받는다
	4.1 import java.sql.* >> interface , class 제공
	4.2 개발자는 interface 를 통해서 표준화된 DB 작업 수행
		POINT) why interface형태로 제공 >> JDBC API >> Oracle, Mysql , ....)
		// OracleConnection >> Connection 구현
		// MysqlConnection >> Connection 구현
		// 다형성 Connection 부모타입 : 유연한 프로그래밍 작성
>>다형성을 구현 (인터페이스 활용)
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement 등등 
5. 작업순서
	5.1 DB연결 -> 명령생성 -> 명령실행 -> 처리 -> 자원해제
	5.2 명령 : DDL (create , alter , drop), CRUD (insert , select , update , delete)
		실행 : 쿼리문을 DB서버에게 전달 
		처리 : 결과를 받아서 화면 출력 , 또는 다른 프로그램에 전달 등등
		자원해제 : 연결해제 
*/
public class Ex01_Oracle_Connection {
	public static void main(String[] args) throws Exception{
		// DB 연결 -> 명령 생성(CRUD) -> 실행 -> 처리 -> 자원 해제
		
		Class.forName("oracle.jdbc.OracleDriver"); // new >> 생략가능하다.. 자동으로 로딩..
		System.out.println("오라클 드라이버 로딩 ...");
		
		// 로딩된 드라이버를 통해서 Oracle 서버에 연결
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KOSA", "1004");
		// DriverManager가 mySQL, Oracle 등 연결 객체를 생성 >> Connection 인터페이스를 구현한 객체 >> 다형성
		System.out.println(conn.isClosed() + " 아니 -> false : 연결되어 있는데?"); // 연결이 끊겼는지 확인
		
		// 명령객체 생성
		Statement stmt = conn.createStatement();
		
		// CRUD 구현
		String sql = "select empno, ename, sal, comm from emp";
		
		// 명령 실행
		ResultSet rs = stmt.executeQuery(sql);
		// 쿼리 문장을 명령객체에 담아 DB 서버에 보내서 실행한다(executeQuery)
		// 쿼리를 실행할 수 있는 주소값을 ResultSet rs에 담는다.
		
		// Tip) sql 출력 정보 가져오기(구조적인 정보 확인)
		ResultSetMetaData rsmd = rs.getMetaData();
		System.out.println("Total Columns : " + rsmd.getColumnCount());
		System.out.println("column Name : " + rsmd.getColumnName(1));
		System.out.println("column type name of 1st column : " + rsmd.getColumnTypeName(1));
		//////////////////////////////////////////////////////////////////////
		
		// rs 객체를 통해 DB 서버에 memory에 있는 정보를 조회 할 수 있다.
		// rs : 화면 처리(목록 출력)
		while(rs.next()) { // rs.next() : 현재 접근한 row가 있는지 확인
						   // 읽고 뿌리고, 읽고 뿌리고 ... >> 연결 지향(기반) 프로그래밍
			System.out.println(
				rs.getInt("empno") + " / " +
				rs.getString("ename") + " / " +
				rs.getInt("sal") + " / " +
				rs.getInt("comm")
			);
		}
		
		// 자원해제
		rs.close();
		stmt.close();
		conn.close();
		System.out.println("DB 연결 해제 : " + conn.isClosed());
	}
}
