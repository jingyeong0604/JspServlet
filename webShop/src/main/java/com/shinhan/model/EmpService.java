package com.shinhan.model;

import java.util.List;

import com.shinhan.vo.EmpVO;



//service: 업무로직 담당
public class EmpService {
	
	//service가 생기면 dao 부르기
	//controller에게 보내줌
	//다른 코드가 추가될수도 있음.
	EmpDAO empDao = new EmpDAO();
	
	//SP호출
	public EmpVO getSalary(int empid) {
		return empDao.getSalary(empid);
	}

	
	public List<EmpVO> selectAll() {
		return empDao.selectAll();
	}
	
	public EmpVO selectById(int empid) {
		return empDao.selectById(empid);
	}
	
	public List<EmpVO> selectByDept(int deptid) {
		return empDao.selectByDept(deptid);
	}
	
	public List<EmpVO> selectByCondition(int deptid, String jobid, double salary) {
		return empDao.selectByCondition(deptid, jobid, salary);
	}
	

	//자신이 속한 부서의 평균 급여보다 더 적은 급여를 받는 직원들을 조회 empcontroller 로 이동
		public List<EmpVO> selectLAB() {
			List<EmpVO> emplist = empDao.selectLAB(); //리턴을 바로 할 수 없을 때
			System.out.println("[EempService] 실행건수: "+emplist.size());
			return emplist;
		}
	
		
		//신규직원등록(insert) EmpVO: 데이터를 받는 가방임, 가방에서 꺼내야함.
		public String empInsert(EmpVO emp) {
			int result = empDao.empInsert(emp);
			
			return result>0? "입력성공":"입력실패"; 
		}
		
		//신규직원등록(insert) EmpVO: 데이터를 받는 가방임, 가방에서 꺼내야함.
		public String empUpdate(EmpVO emp) {
			int result = empDao.empUpdate(emp);
			return result>0? "입력성공":"입력실패"; 
		}
		
		//한 건의 직원 삭제
		public String empDelete(int empid) {
			int result = empDao.empDelete(empid);
			return result>0? "삭제성공":"삭제실패"; 
		}
		}

