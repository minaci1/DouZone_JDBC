import java.util.List;

import DAO.DeptDao;
import DTO.Dept;

/**
 * App 서버 구성(MVC 패턴) >> 잘하는 것만 해~
 * 
 * Model(java) >> DTO(데이터를 담을 수 있는 클래스), DAO(데이터를 처리할 수 있는 클래스(JDBC API)), service(DTO, DAO를 처리하는 클래스)
 * View(UI) >> html, jsp 등등 >> 현재 console 화면 제어(main 정도..?)
 * Controller >> 중앙제어(통제) >> 웹의 접근 통제 >> 요청과 응답처리 >> JAVA >> Servlet(웹용 자바파일)
 * 
 *
 */

public class Program {
	public static void main(String[] args) {
		
		/**
		Dept dept = new Dept();
		dept.setDeptno(100);
		dept.setDname("IT");
		dept.setLoc("SEOUL");
		System.out.println(dept.toString());
		
		Emp emp = new Emp(200, "김유신");
		System.out.println(emp.toString());
		 */
		
		DeptDao dao = new DeptDao();
		
		System.out.println("[전체조회]");
		List<Dept> deptList = dao.selectAll();
		
		if(deptList != null) {
			deptPrint(deptList);
		}
		
		System.out.println("[조건조회]");
		Dept dept = dao.selectOne(10);
		if(dept != null) {
			deptPrint(dept);
		}else {
			System.out.println("조건 조회 FAIL");
		}
		
		System.out.println("[데이터 삽입]");
		Dept insertdept = new Dept();
		insertdept.setDeptno(100);
		insertdept.setDname("IT");
		insertdept.setLoc("SEOUL");
		
		int insertrow = dao.deptInsert(insertdept);
		if(insertrow > 0) {
			System.out.println("INSERT ROW : " + insertrow);
		}else {
			System.out.println("INSERT FAIL");
		}
		
		System.out.println("[방금전 INSERT한 데이터 조회]");
		deptList = dao.selectAll();
		if(deptList != null) {
			deptPrint(deptList);
		}
		
		System.out.println("[방금전 INSERT한 데이터 수정]");
		Dept updatedept = new Dept();
		updatedept.setDeptno(100);
		updatedept.setDname("IT_UP");
		updatedept.setLoc("BUSAN");
		
		int updaterow = dao.deptUpdate(updatedept);
		if(updaterow > 0) {
			System.out.println("UPDATE ROW : " + updaterow);
		}else {
			System.out.println("UPDATE FAIL");
		}
		
		System.out.println("[방금전 UPDATE 한 데이터 조회]");
		deptList = dao.selectAll(); //변경된 데이터 다시 조회
		if(deptList != null) {
			deptPrint(deptList);
		}
		
		System.out.println("[방금전 UPDATE 데이터 삭제하기]");
		int deleterow = dao.deptDelete(100);
		if(deleterow > 0 ) {
			System.out.println("DELETE ROW : " + deleterow);
		}else {
			System.out.println("DELETE FAIL");
		}
		
		System.out.println("[방금전 DELETE 한 데이터 조회]");
		deptList = dao.selectAll(); //변경된 데이터 다시 조회
		if(deptList != null) {
			deptPrint(deptList);
		}
	}
	
	public static void deptPrint(Dept dept) {
		System.out.println(dept.toString());
	}
	
	public static void deptPrint(List<Dept> deptList) {
		for(Dept dept : deptList) {
			System.out.println(dept.toString());			
		}
	}
}
