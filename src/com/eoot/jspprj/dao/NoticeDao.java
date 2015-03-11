package com.eoot.jspprj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.eoot.jspprj.model.Notice;

public interface NoticeDao {
	
	@Select("SELECT * FROM NOTICES WHERE CODE=#{code}")
	public Notice getNotice(String code);
	
	@Select("SELECT N.* FROM( SELECT ( ROW_NUMBER() OVER (ORDER BY REGDATE DESC)) NUM, NOTICES.* FROM NOTICES WHERE ${field} LIKE '%${query}%' ) N WHERE N.NUM BETWEEN 1+(#{page}-1)-20 AND 20+(#{page}-1)*20")
	public List<Notice> getNotices(@Param("page")int page, @Param("query")String query, @Param("field")String field);

	public List<Notice> getNotices(int page, String query);
	public List<Notice> getNotices(int page);

	@Select("SELECT TOP 1 * FROM NOTICES WHERE REGDATE &gt; (SELECT REGDATE FROM NOTICES WHERE CODE =#{currentCode}) ORDER BY REGDATE ASC")
	public Notice prevNotice(String currentCode);
	
	@Select("SELECT TOP 1 * FROM NOTICES WHERE REGDATE &lt; (SELECT REGDATE FROM NOTICES WHERE CODE =#{currentCode}) ORDER BY REGDATE DESC")
	public Notice nextNotice(String currentCode);
	
	@SelectKey(before=true, 
			keyColumn="code", 
			statement="SELECT ISNULL(MAX(CAST(CODE AS INT)),0)+1 CODE FROM NOTICES", 
			resultType=java.lang.String.class,
			keyProperty="String")
	@Insert("INSERT INTO NOTICES(CODE, TITLE, WRITER, CONTENT,REGDATE, HIT) VALUES(#{code},#{title},#{writer},#{content},GetDate(),0)")
	public int insert(Notice notice); 
	
	public int update(Notice notice); 
	public int delete(String code);
	public int getSize(String query, String field);
	public int getSize(String query);
	
	public String lastCode();
	

}
