
<%@page import="com.eoot.jspprj.dao.jdbc.JdbcNoticeFileDao"%>
<%@page import="com.eoot.jspprj.dao.NoticeFileDao"%>
<%@page import="com.eoot.jspprj.model.NoticeFile"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.eoot.jspprj.dao.NoticeDao"%>
<%@page import="com.eoot.jspprj.model.Notice"%>
<%@page import="com.eoot.jspprj.dao.jdbc.JdbcNoticeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
ServletContext ctx = request.getServletContext();
String path = ctx.getRealPath("/customer/upload");

//out.println(path + "<br />");

MultipartRequest req = new MultipartRequest(request
								, path
								, 1024*1024*10
								, "UTF-8"
								, new DefaultFileRenamePolicy());//상속받아서 재정의 가능. 

String title = req.getParameter("title");
String fileName = req.getFilesystemName("file"); //req.getParameter("file")  
String content = req.getParameter("content");
//req.getFile("file");

Notice notice = new Notice();
notice.setTitle(title);
notice.setContent(content);

JdbcNoticeDao noticeDao = new JdbcNoticeDao();
noticeDao.insert(notice);

if(req.getFile("file") != null){
	String noticeCode = noticeDao.lastCode();

	NoticeFile noticeFile = new NoticeFile();
	noticeFile.setSrc(fileName);
	noticeFile.setDescription("");
	noticeFile.setNoticeCode(noticeCode);
	
	NoticeFileDao fileDao = new JdbcNoticeFileDao();
	fileDao.insert(noticeFile);
}

response.sendRedirect("notice.jsp");

%>
