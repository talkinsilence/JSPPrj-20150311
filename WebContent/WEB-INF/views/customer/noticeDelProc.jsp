<%@page import="com.eoot.jspprj.dao.jdbc.JdbcNoticeDao"%>
<%@page import="com.eoot.jspprj.dao.NoticeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String code = request.getParameter("c");
	
	NoticeDao noticeDao = new JdbcNoticeDao();
	noticeDao.delete(code);
	
	response.sendRedirect("notice.jsp");
%>