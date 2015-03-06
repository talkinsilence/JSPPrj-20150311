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
	//오버로드와 오버라이드는 완전 다른 기능!! 비교 대상이 아님. 이름만 비슷할 뿐이다. 오버로드는 중복정의. 사용자에게 선택할 수 있도록 하는 것임. 
	//페이지만 있는경우, 검색어 옵션도 주는 경우, 제목 외에 검색할 수 있는 옵션도 주는 경우. 
	//오버로드 - 재사용 - 집중화 
	//위의 셋 중에 어느 한 놈만 구현하면 되는데, 그럼 누구를 하는 게 맞을까? 무조건 인자가 제일 많은 것을 구현하면 됨.
	
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
