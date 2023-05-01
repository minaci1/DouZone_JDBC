import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import DAO.EmpDAO;
import DTO.EmpDTO;

public class Program {
	public static void main(String[] args) throws Exception{
		EmpDAO dao = new EmpDAO();
		
		System.out.println("[전체조회]");
		List<EmpDTO> empList = dao.getSelectAll();
		
		if(empList != null) {
			empPrint(empList);
		}
		
		/*
		System.out.println("[데이터 삽입]");
		EmpDTO insertEmp = new EmpDTO();
		insertEmp.setEmpno(3150);
		insertEmp.setEname("길동");
		insertEmp.setJob("IT");
		insertEmp.setHiredate("2022-05-13");
		
		int insertrow = dao.empInsert(insertEmp);
		if(insertrow > 0) {
			System.out.println("INSERT ROW : " + insertrow);
		}else {
			System.out.println("INSERT FAIL");
		}
         */
		
		System.out.println("[Like 조회]");
		List<EmpDTO> empList2 = dao.getSelectLike("SCOTT");
		if(empList2 != null) {
			empPrint(empList2);
		}
	}
	
	public static void empPrint(EmpDTO emp) {
		System.out.println(emp.toString());
	}
	
	public static void empPrint(List<EmpDTO> empList) {
		for(EmpDTO emp : empList) {
			System.out.println(emp.toString());			
		}
	}
	
	//날짜 변환 함수
	public static Date changeDate(String datestring) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 형식 지정
		java.util.Date date=null;
		java.sql.Date sqldate=null;
		try {
			date = dateFormat.parse(datestring);
			sqldate = new java.sql.Date(date.getTime()); // java.sql.Date 객체로 변환
		} catch (ParseException e) {
			e.printStackTrace();
		} // 문자열을 Date 객체로 변환
        return sqldate;
	}
}
