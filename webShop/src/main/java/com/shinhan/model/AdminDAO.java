package com.shinhan.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.util.OracleUtil;
import com.shinhan.vo.AdminVO;



public class AdminDAO {
	
	Connection conn;
	PreparedStatement st;
	ResultSet rs;
	
	//중복체크!
	public int dupCheck(String email) {
		int count=0;
		AdminVO admin = null;
		String sql="select count(*) from admins where email=?"; 
		conn=OracleUtil.getConnection();//db연결
		try {
			st=conn.prepareStatement(sql);//통로를 만들어야함.sql문장을 보내면 됨.
			st.setString(1, email);
			
			//sql을 넣은다면 ?있는 sql을 넣는거기 때문에 값이 당연히 안나옴.
			rs = st.executeQuery();//실행은 디비에서 하고 결과는 자바쪽으로 온것
			//rs는 executeQuery를 나타내므로 sql쿼리를 나타냄.
			while(rs.next()) {//데이터를 읽어서 arraylist에 담음.
				count=rs.getInt(1);//첫번째 컬럼을 숫자로 읽어라!
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}
		return count;
	}
	
	//특정직원 조회 : 아이디를 주면 특정 직원 상세조회
	public AdminVO loginCheck(String email, String pass) {
		//검증하는 sql 문
		AdminVO admin = null;
		String sql="select * from admins where email=? and pass=?";
		
		
	 
		conn=OracleUtil.getConnection();//db연결
		try {
			st=conn.prepareStatement(sql);//통로를 만들어야함.sql문장을 보내면 됨.
			st.setString(1, email);
			st.setString(2, pass);
			//sql을 넣은다면 ?있는 sql을 넣는거기 때문에 값이 당연히 안나옴.
			rs = st.executeQuery();//실행은 디비에서 하고 결과는 자바쪽으로 온것
			//rs는 executeQuery를 나타내므로 sql쿼리를 나타냄.
			while(rs.next()) {//데이터를 읽어서 arraylist에 담음.
				//첫번쨰 이메일, 매니저 이름, 패스를 읽어줌.
				//rs.getString(1) 
				//sql쿼리에서 가져오기때문에 manager_name만 선택했기때문에 첫번째가 온다.
				//전체 컬럼을 다 가져올 경우에는 컬럼 이름 혹은 컬럼안에 몇번째인지 !!
				admin = new AdminVO();
				admin.setEmail(email);
				admin.setManager_name(rs.getString("manager_name"));
				admin.setPass(pass);
				admin.setPic(rs.getString("pic"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}
		return admin;
	}
	
	public int registerAdmin(AdminVO admin) {
		int result = 0;
		String sql = " insert into admins(email,pass,manager_name,pic) values(?,?,?,?)";
		conn = OracleUtil.getConnection();
		try {
		st = conn.prepareStatement(sql);
		st.setString(1, admin.getEmail());
		st.setString(2, admin.getPass());
		st.setString(3, admin.getManager_name());
		st.setString(4, admin.getPic());
		result = st.executeUpdate();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}finally {
		OracleUtil.dbDisconnect(rs, st, conn);
		}
		return result;
		}
	
}
