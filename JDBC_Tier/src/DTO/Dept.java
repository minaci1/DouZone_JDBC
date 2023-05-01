package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
DTO or VO or Domain
데이터 베이스에 있는 Dept 테이블과 1:1매핑 클래스
Dept 데이터 1건을 담을 수 있는 클래스

new Dept() >>  select * from dept where deptno=10;

*/

@Data
public class Dept {
	private int deptno;
	private String dname;
	private String loc;
}
