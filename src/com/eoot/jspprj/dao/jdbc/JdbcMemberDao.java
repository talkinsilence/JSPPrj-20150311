package com.eoot.jspprj.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.eoot.jspprj.dao.MemberDao;
import com.eoot.jspprj.model.Member;

public class JdbcMemberDao implements MemberDao{

	@Override
	public Member getMember(String uid) {
		String sql = "SELECT * FROM MEMBERS WHERE MID ='" + uid + "'"; 
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, "sist", "newlec");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			rs.next();

			//Model 마련하기 
			Member m = new Member();
			m.setUid(rs.getString("MID"));
			m.setPwd(rs.getString("PWD"));
			m.setName(rs.getString("NAME"));
			
			rs.close();
			st.close();
			con.close();
			
			return m;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	

}
