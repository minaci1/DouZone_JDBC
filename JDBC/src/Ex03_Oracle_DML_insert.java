import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/***
 * 
 * JDBC 작업
 * 
 * 1. select
 * 1.1 결과 집합 생성
 * 
 * 2. insert, update, delete
 * 2.1 결과 집합 생성 없음 >> ResultSet (x)
 * 2.2 반영 결과 return(반영된 행의 수) >> update 5건 수정 >> return : 5
 * 
 * ex)
 * update emp set sal = 0; >> 실행 >> update 14건 >> return : 14
 * update emp set sal = 100 where empno = 9999; >> update 0건 >> return : 0
 * 
 * 결과를 받아서 자바코드 로직처리
 * 
 * Today key Point
 * 1. Oracle DML(Sqldeveloper, Cmd(sqlplus), Tool)로 작업시 commit or rollback 강제
 * 2. JDBC API를 사용해서 Oracle의 DML 작업을 수행하면 >> default >> auto commit >> Java에서 insert문을 날리면 무조건 실반영
 * 3. JDBC API를 사용해서 Java 코드로 delete from emp를 실행하면 >> JDBC 자동 commit >> 실반영
 * 4. 그래서 JDBC API는 옵션을 통해 commit, rollback을 강제하는 방법을 제공한다
 * 
 * begin
 * 	A 계좌 인출(update ...)
 * 	B 계좌 입금(update ...)
 * end
 * 논리적인 단위로 트랜잭션(transaction)
 * 전체 성공 아니면 실패 >> commit or rollback
 * 
 * >> 업무 처리 >> JDBC >> auto commit 옵션을 'false로 전환'
 * >> 반드시 Java 코드에서도 똑같이 commit, rollback을 무조건 처리해줘야 한다
 * 
 * >> 트랜잭션은 같은 DML은 허용하지 않는다.
 * 	>> update 이후 select은 허용하지만
 * 	>> delete 이후 update는 허용하지 않는다 >> 기본적으로 트랜잭션이 lock을 수반하기 때문
 * 
 * create table dmlemp as select * from emp;
 * select * from dmlemp;
 * select * from user_constraints where lower(table_name) = 'dmlemp';
 * alter table dmlemp add constraints pk_dmlemp_empno primary key(empno);
 */

public class Ex03_Oracle_DML_insert {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			// 드라이버로딩 생략
			// Class.forName("oracle.jdbc.OracleDriver");
			// 연결 객체 생성하기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KOSA", "1004");
			stmt = conn.createStatement();
			
			// insert
			int empno = 0;
			String ename = null;
			int deptno = 0;
			
			Scanner sc = new Scanner(System.in);
			System.out.print("사번 입력 : ");
			empno = Integer.parseInt(sc.nextLine());
			
			System.out.print("이름 입력 : ");
			ename = sc.nextLine();
			
			System.out.print("부서 입력 : ");
			deptno = Integer.parseInt(sc.nextLine());
			
			// insert into dmlemp(empno, ename, deptno) values(100, '홍길동', 10);
			// 전통적이고 고전적인 방법
			String sql = "insert into dmlemp(empno, ename, deptno) ";
			       sql+= "values(" + empno + ", '" + ename + "', " + deptno + ")";
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
