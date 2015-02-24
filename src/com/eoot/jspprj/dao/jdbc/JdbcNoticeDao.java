package com.eoot.jspprj.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.eoot.jspprj.dao.NoticeDao;
import com.eoot.jspprj.model.Notice;

public class JdbcNoticeDao implements NoticeDao {

	@Override
	public Notice getNotice(String code) {
		String sql = "SELECT * FROM NOTICES WHERE CODE ='" + code + "'"; 
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(url, "sist", "newlec");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			rs.next();

			//Model 마련하기 
			Notice n = new Notice();
			n.setCode(rs.getString("CODE"));
			n.setTitle(rs.getString("TITLE"));
			n.setWriter(rs.getString("WRITER"));
			n.setRegdate(rs.getDate("REGDATE"));
			n.setContent(rs.getString("CONTENT"));
			n.setHit(rs.getInt("HIT"));
			
			rs.close();
			st.close();
			con.close();
			
			return n;
			
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
	public List<Notice> getNotices(int page, String query, String field) {
		
		//등차수열의 규칙이 보이도록 수식은 계산하여 풀어주지 않도록 하자. 계산은 계산기에게 맡기자. 
		int start = 1 + 20*(page - 1);
		int end = 20 + 20*(page - 1);
//		int end = start + 19;
		
		//항상 최신글이 가장 먼저 나와야.
		String sql ="SELECT N.* FROM"
				+ "(SELECT (ROW_NUMBER() OVER (ORDER BY REGDATE DESC)) NUM"
				+ ", NOTICES.* FROM NOTICES WHERE " + field + " LIKE ?) N	"
				+ "WHERE N.NUM BETWEEN ? AND ?";
/*		String sql = "SELECT * FROM ( "
				+ "SELECT ROWNUM NUM, N.* FROM ( "
				+ "SELECT * FROM NOTICES WHERE " + field + " LIKE ? ORDER BY REGDATE DESC) N) "
				+ "NOTICES WHERE NUM BETWEEN ? AND ?"; */
//		String url = "jdbc:oracle:thin:@win.newlecture.com:1521:orcl";
		
		//연결문자열 붙여넣기 
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, "sist", "newlec");

		PreparedStatement pst = con.prepareStatement(sql);
//		pst.setString(1, field);라고 해주면 안됨. 이렇게 하면 쿼리문에 'FIELD'라고 작은따옴표가 붙어서 들어간다. 문자열이니까. 
		pst.setString(1, "%" + query + "%"); //%는 여기서 해주자. 쿼리문에서 쓰면 복잡해짐... 
		pst.setInt(2, start);
		pst.setInt(3, end);
		
		ResultSet rs = pst.executeQuery();
//		prepared에서는 이미 sql넘겼으니까 쓰면 안됨!
	
		List<Notice> list = new ArrayList<Notice>();	
		
		while(rs.next()){
		
			//Model 마련하기 
			Notice n = new Notice();
			n.setCode(rs.getString("CODE"));
			n.setTitle(rs.getString("TITLE"));
			n.setWriter(rs.getString("WRITER"));
			n.setRegdate(rs.getDate("REGDATE"));
			n.setContent(rs.getString("CONTENT"));
			n.setHit(rs.getInt("HIT"));
			
			list.add(n); //이거 까먹지 말고 꼭 해줘야!!! 만들어진 notice를 list에 더해줘야하지요~
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
	public List<Notice> getNotices(int page, String query) {
		//위의 함수를 넘겨받는데,
		//넘겨받지 않는 인자는 기본값으로 줌. 
		return getNotices(page, query, "TITLE");
	}
	
	@Override
	public List<Notice> getNotices(int page) { 
		return getNotices(page, "", "TITLE");
	}

	@Override
	public int insert(Notice notice) {
		//기존에 있는 번호보다 하나 더 큰 번호가 부여되어야
		String sqlCode = "SELECT ISNULL(MAX(CAST(CODE AS INT)),0)+1 CODE FROM NOTICES";
		String sql = "INSERT INTO NOTICES(CODE, TITLE, WRITER, REGDATE, CONTENT, HIT) VALUES(?,?,?,GetDate(),?,0)"; 
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
		pst.setString(2, notice.getTitle());
		pst.setString(3, notice.getWriter());
		pst.setString(4, notice.getContent());
		
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

	@Override
	public int update(Notice notice) {
		String sql = "UPDATE NOTICES SET(TITLE=?, CONTENT=?) WHERE CODE =?";  
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		Connection con = DriverManager.getConnection(url, "sist", "newlec");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, notice.getTitle());
		st.setString(2, notice.getContent());
		st.setString(3, notice.getCode());
		
		int result = st.executeUpdate();
		
		st.close();
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

	@Override
	public int delete(String code) {
		String sql = "DELETE NOTICES WHERE CODE = ?"; 
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		Connection con = DriverManager.getConnection(url, "sist", "newlec");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, code);
		
		int result = st.executeUpdate();
		
		st.close();
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


	@Override
	public int getSize(String query, String field) {

		String sql = "SELECT COUNT(*) CNT FROM NOTICES WHERE " + field + " LIKE ?";
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		
		try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, "sist", "newlec");

		PreparedStatement pst = con.prepareStatement(sql); 
		pst.setString(1, "%" + query + "%");
		ResultSet rs = pst.executeQuery();
//	                                	prepared에서는 이미 sql넘겼으니까 쓰면 안됨!
		
		rs.next();
		
		int size = rs.getInt("CNT");
		
		rs.close();
		pst.close();
		con.close();
		
		return size;
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	
	@Override
	public int getSize(String query) {
		return getSize(query, "TITLE");
	}


	@Override
	public String lastCode() {
		
		String sql = "SELECT ISNULL(MAX(CAST(CODE AS INT)),0) CODE FROM NOTICES";
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			Connection con = DriverManager.getConnection(url, "sist", "newlec");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			rs.next();
 
			String code = rs.getString("CODE");
			
			rs.close();
			st.close();
			con.close();
			
			return code;
			
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
	public Notice prevNotice(String currentCode) {
		String sql = "SELECT TOP 1 * FROM NOTICES "
				+ "WHERE REGDATE > (SELECT REGDATE FROM NOTICES WHERE CODE = ?) "
				+ "ORDER BY REGDATE ASC";
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		
		try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, "sist", "newlec");

		PreparedStatement pst = con.prepareStatement(sql); 
		pst.setString(1, currentCode);
		ResultSet rs = pst.executeQuery();
//	                                	prepared에서는 이미 sql넘겼으니까 쓰면 안됨!
		
		rs.next();
		//사실, 조건검사 필요 ! next()해서 없을 때는 어떻게 해줄지...  
		
		Notice n = new Notice();
		n.setCode(rs.getString("CODE"));
		n.setTitle(rs.getString("TITLE"));
		n.setWriter(rs.getString("WRITER"));
		n.setRegdate(rs.getDate("REGDATE"));
		n.setContent(rs.getString("CONTENT"));
		n.setHit(rs.getInt("HIT"));
		
		rs.close();
		pst.close();
		con.close();
		
		return n;
		
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
	public Notice nextNotice(String currentCode) {
		String sql = "SELECT TOP 1 * FROM NOTICES "
				+ "WHERE REGDATE < (SELECT REGDATE FROM NOTICES WHERE CODE = ?) "
				+ "ORDER BY REGDATE DESC";
		String url = "jdbc:sqlserver://win.newlecture.com:1433;databaseName=newlecdb";
		
		try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, "sist", "newlec");

		PreparedStatement pst = con.prepareStatement(sql); 
		pst.setString(1, currentCode);
		ResultSet rs = pst.executeQuery();
//	                                	prepared에서는 이미 sql넘겼으니까 쓰면 안됨!
		
		rs.next();
		
		Notice n = new Notice();
		n.setCode(rs.getString("CODE"));
		n.setTitle(rs.getString("TITLE"));
		n.setWriter(rs.getString("WRITER"));
		n.setRegdate(rs.getDate("REGDATE"));
		n.setContent(rs.getString("CONTENT"));
		n.setHit(rs.getInt("HIT"));
		
		rs.close();
		pst.close();
		con.close();
		
		return n;
		
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


