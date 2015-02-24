package com.eoot.jspprj.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.eoot.jspprj.dao.NoticeFileDao;
import com.eoot.jspprj.model.Notice;
import com.eoot.jspprj.model.NoticeFile;

public class JdbcNoticeFileDao implements NoticeFileDao {

	@Override
	public List<NoticeFile> getNoticeFiles(String noticeCode) {
		
		//항상 최신글이 가장 먼저 나와야. 
/*		String sql = "SELECT * FROM ( "
				+ "SELECT ROWNUM NUM, N.* FROM ( "
				+ "SELECT * FROM NOTICES WHERE " + field + " LIKE ? ORDER BY REGDATE DESC) N) "
				+ "NOTICES WHERE NUM BETWEEN ? AND ?"; */
 
		String sql = "SELECT * FROM NOTICEFILES WHERE NOTICECODE = ?";
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, "sist", "newlec");

		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, noticeCode);
		
		ResultSet rs = pst.executeQuery();
//		prepared에서는 이미 sql넘겼으니까 쓰면 안됨!
	
		List<NoticeFile> list = new ArrayList<NoticeFile>();	
		
		while(rs.next()){
		
			//Model 마련하기 
			NoticeFile nf = new NoticeFile();
			nf.setCode(rs.getString("CODE"));
			nf.setSrc(rs.getString("SRC"));
			nf.setRegdate(rs.getDate("REGDATE"));
			nf.setDescription(rs.getString("DESCRIPTION"));
			nf.setNoticeCode(rs.getString("NOTICECODE"));
			
			list.add(nf); //이거 까먹지 말고 꼭 해줘야!!! 만들어진 notice를 list에 더해줘야하지요~
		}
		
		rs.close();
		pst.close();
		con.close();
		
		return list;
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public int insert(NoticeFile noticeFile) {

		String sqlCode = "SELECT ISNULL(MAX(CAST(CODE AS INT)),0)+1 CODE FROM NOTICEFILES";
		String sql = "INSERT INTO NOTICEFILES(CODE, SRC, REGDATE, DESCRIPTION, NOTICECODE) "
				+ "VALUES(?,?,GetDate(),?,?)"; 
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, "sist", "newlec");
		
		/*--------------추가---------------*/
		Statement st = con.createStatement(); 
		ResultSet rs = st.executeQuery(sqlCode);
		
		rs.next();
		
//		String code = rs.getString(0); //별칭 안붙였을 경우 
		String code = rs.getString("CODE"); //별칭을 CODE라고 설정해줬을 경우 
		
		rs.close();
		st.close();
		/*--------------추가---------------*/
		
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, code); //첫번째 물음표에는 code를 넣는다
		pst.setString(2, noticeFile.getSrc());
		pst.setString(3, noticeFile.getDescription());
		pst.setString(4, noticeFile.getNoticeCode());
		
		int result = pst.executeUpdate();
		
		pst.close();
		con.close();
		
		return result;
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}

}
