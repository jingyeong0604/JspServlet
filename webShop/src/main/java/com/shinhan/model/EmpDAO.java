package com.shinhan.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.util.OracleUtil;
import com.shinhan.vo.EmpVO;

//DAO(Date Access Object): DB업무...CRUD..insert, create, update, delete
public class EmpDAO {
	//필드 안에 있는 변수이므로 따로 초기화 안해줘도 됨.
	Connection conn;
	Statement st;
	PreparedStatement pst; //?지원
	CallableStatement cst;//sp지원
	ResultSet rs; //결과값을 넣어야함
	int resultCount; //insert, update, delete건수
	
	
	//SP호출 start procedure
	public EmpVO getSalary(int empid) {
		String sql = "{call sp_salary(?,?,?)}";//인변수, 아웃변수->?
		conn = OracleUtil.getConnection();
		//cst = OracleUtil.getConnection();
		EmpVO emp = new EmpVO();
		
		double salary = 0;
	
		try {
			cst=conn.prepareCall(sql);
			cst.setInt(1, empid);
			cst.registerOutParameter(2, Types.DOUBLE);//(?,?)// 두번째꺼는 타입이 더블이다
			cst.registerOutParameter(3, Types.VARCHAR);
			cst.executeQuery(); //resultset있으면 true이고 없으면 false
			//executeQuery(), executeUpdate() 값은 true/false임
			emp.setSalary(cst.getDouble(2));
			emp.setFirst_name(cst.getString(3));
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return emp;
	}
	
	
	//직원데이터 전부 조회 데이터 조회이므로 매개변수는 필요없고 리턴값이 필요함.
	public List<EmpVO> selectAll() {
		//검증하는 sql 문
		String sql="""
				select
						EMPLOYEE_ID,
						FIRST_NAME,
						LAST_NAME,
						EMAIL,
						PHONE_NUMBER,
						HIRE_DATE,
						JOB_ID,
						f_tax(SALARY) SALARY,
						COMMISSION_PCT,
						MANAGER_ID,
						DEPARTMENT_ID
				from employees 
				order by 1
				""" ;
		List<EmpVO> emplist = new ArrayList<>();
		conn=OracleUtil.getConnection();//db연결
		try {
			st=conn.createStatement();//통로를 만들어야함.sql문장을 보내면 됨.
			rs = st.executeQuery(sql);//실행은 디비에서 하고 결과는 자바쪽으로 온것
			
			
			while(rs.next()) {//데이터를 읽어서 arraylist에 담음.
				EmpVO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}
		return emplist;
	}
	
	
	//자신이 속한 부서의 평균 급여보다 더 적은 급여를 받는 직원들을 조회
		public List<EmpVO> selectLAB() {//작성 후 service로 간다.
			//검증하는 sql 문 *말고 원하는 테이블 몇개만 가져올때는?
			String sql="""
					select employee_id, first_name, salary, employees.department_id
					from employees,(
					select department_id, avg(salary) sal
					from employees
					group by department_id) inlineview_emp
					where employees.department_id = inlineview_emp.department_id
					and employees.salary < inlineview_emp.sal
										""";
			List<EmpVO> emplist = new ArrayList<>();
			conn=OracleUtil.getConnection();//db연결
			try {
				st=conn.createStatement();//통로를 만들어야함.sql문장을 보내면 됨.
				rs = st.executeQuery(sql);//실행은 디비에서 하고 결과는 자바쪽으로 온것
				while(rs.next()) {//데이터를 읽어서 arraylist에 담음.
					EmpVO emp = makeEmp2(rs);
					emplist.add(emp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				OracleUtil.dbDisconnect(rs, pst, conn);
			}
			return emplist;
		}
		
		//신규직원등록(insert) EmpVO: 데이터를 받는 가방임, 가방에서 꺼내야함.
		public int empInsert(EmpVO emp) {
			String sql="""
					insert into employees values(seq_employee2.nextval,?,?,?,?,?,?,?,?,?,?)
					""";
			conn = OracleUtil.getConnection();//db연결해줌.
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, emp.getFirst_name());
				pst.setString(2, emp.getLast_name());
				pst.setString(3, emp.getEmail());
				pst.setString(4, emp.getPhone_number());
				pst.setDate(5, emp.getHire_date());
				pst.setString(6, emp.getJob_id());
				pst.setDouble(7, emp.getSalary());
				pst.setDouble(8, emp.getCommission_pct());
				pst.setInt(9, emp.getManager_id());
				pst.setInt(10, emp.getDepartment_id());
			
				resultCount =pst.executeUpdate(); //select가 아닌경우는 다 update해줘야함. 리턴값은 다 int다
				//dml문장 실행 영향받은 견수가 return
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				OracleUtil.dbDisconnect(null, pst, conn);
			}
			
			return resultCount; //insert된 건수를 리턴해줌->어디서온거지?
		}
		
		
		//신규직원등록(insert) EmpVO: 데이터를 받는 가방임, 가방에서 꺼내야함.
		public int empUpdate(EmpVO emp) {
			String sql = """
					update employees
					set EMAIL=?, department_id=?, job_id=?,salary=?,
					hire_date=?, manager_id=?
					where employee_id=?
										""";
			conn = OracleUtil.getConnection();//db연결해줌.
			try {
				//물음표의 순서가 중요함.
				pst = conn.prepareStatement(sql);
				pst.setString(1, emp.getEmail());
				pst.setInt(2, emp.getDepartment_id());
				pst.setString(3, emp.getJob_id());
				pst.setDouble(4, emp.getSalary());
				pst.setDate(5, emp.getHire_date());
				pst.setInt(6, emp.getManager_id());
				pst.setInt(7, emp.getEmployee_id());
				
				resultCount =pst.executeUpdate(); //select가 아닌경우는 다 update해줘야함. 리턴값은 다 int다
				//dml문장 실행 영향받은 견수가 return
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				OracleUtil.dbDisconnect(null, pst, conn);
			}
			
			return resultCount; //insert된 건수를 리턴해줌->어디서온거지?
		}
		
		
		//한 건의 직원을 삭제하기
		public int empDelete(int empid) {
			String sql = """
					delete from employees
					where employee_id=?
										""";
			conn = OracleUtil.getConnection();//db연결해줌.
			try {
				//물음표의 순서가 중요함.
				pst = conn.prepareStatement(sql);
				pst.setDouble(1, empid);
				resultCount =pst.executeUpdate(); //select가 아닌경우는 다 update해줘야함. 리턴값은 다 int다
				//dml문장 실행 영향받은 견수가 return
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				OracleUtil.dbDisconnect(null, pst, conn);
			}
			System.out.println("delete 결과:"+ resultCount);
			return resultCount; //insert된 건수를 리턴해줌->어디서온거지?
		}
	//특정직원 조회 : 아이디를 주면 특정 직원 상세조회
	public EmpVO selectById(int empid) {
		//검증하는 sql 문
		EmpVO emp = null;
		String sql="select * from employees where employee_id= "+ empid;
		List<EmpVO> emplist = new ArrayList<>();
		conn=OracleUtil.getConnection();//db연결
		try {
			st=conn.createStatement();//통로를 만들어야함.sql문장을 보내면 됨.
			rs = st.executeQuery(sql);//실행은 디비에서 하고 결과는 자바쪽으로 온것
			while(rs.next()) {//데이터를 읽어서 arraylist에 담음.
				emp = makeEmp(rs);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}
		return emp;
	}
	
	//특정 부서의 직원을 조회
	public List<EmpVO> selectByDept(int deptid) {
		//검증하는 sql 문
		String sql="select * from employees where department_id=" + deptid;
		List<EmpVO> emplist = new ArrayList<>();
		conn=OracleUtil.getConnection();//db연결
		try {
			st=conn.createStatement();//통로를 만들어야함.sql문장을 보내면 됨.
			rs = st.executeQuery(sql);//실행은 디비에서 하고 결과는 자바쪽으로 온것
			while(rs.next()) {//데이터를 읽어서 arraylist에 담음.
				EmpVO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}
		return emplist;
	}
	
	//특정 부서, jobid, salary이상 직원조회
	public List<EmpVO> selectByCondition(int deptid, String jobid, double salary) {
		//검증하는 sql 문
		String sql="select * "
				+ "from employees "
				+"where department_id= ? " 
				+" and job_id = ? "
				+" and salary >= ?";
				
		List<EmpVO> emplist = new ArrayList<>();
		conn=OracleUtil.getConnection();//db연결
		try {
			pst=conn.prepareStatement(sql);//통로를 만들어야함.sql문장을 보내면 됨.
			pst.setInt(1, deptid);
			pst.setString(2, jobid);
			pst.setDouble(3, salary);//(?의 순서, 넣을 값)
			rs = pst.executeQuery();//실행은 디비에서 하고 결과는 자바쪽으로 온것
			while(rs.next()) {//데이터를 읽어서 arraylist에 담음.
				EmpVO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
		return emplist;
	}
	

	private EmpVO makeEmp2(ResultSet rs) throws SQLException {
		EmpVO emp =new EmpVO();//값이 너무 많아서 직접 넣기가 복잡함
		emp.setCommission_pct(rs.getDouble("Employee_id"));
		emp.setDepartment_id(rs.getInt("Department_id"));
		emp.setFirst_name(rs.getString("First_name"));
		emp.setSalary(rs.getDouble("Salary"));
		
		return emp;
	}
	//select할 컬럼에 대해서 makeEmp를 새로 만들어 준다.
	private EmpVO makeEmp(ResultSet rs) throws SQLException {
		EmpVO emp =new EmpVO();//값이 너무 많아서 직접 넣기가 복잡함
	
		emp.setCommission_pct(rs.getDouble("Commission_pct"));
		emp.setDepartment_id(rs.getInt("Department_id"));
		emp.setEmail(rs.getString("Email"));
		emp.setEmployee_id(rs.getInt("Employee_id"));
		emp.setFirst_name(rs.getString("First_name"));
		emp.setHire_date(rs.getDate("Hire_date"));
		emp.setJob_id(rs.getString("Job_id"));
		emp.setLast_name(rs.getString("Last_name"));
		emp.setManager_id(rs.getInt("Manager_id"));
		emp.setPhone_number(rs.getString("Phone_number"));
		emp.setSalary(rs.getDouble("Salary"));
		
		return emp;
	}

		}
	

