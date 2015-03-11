<%@page import="com.eoot.jspprj.dao.NoticeDao"%>
<%@page import="com.eoot.jspprj.model.Notice"%>
<%@page import="com.eoot.jspprj.dao.jdbc.JdbcNoticeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String title = request.getParameter("title");
String file = request.getParameter("file");
String content = request.getParameter("content");
String code = request.getParameter("code");

Notice notice = new Notice();
notice.setTitle(title);
notice.setContent(content);
notice.setCode(code);

NoticeDao noticeDao = new JdbcNoticeDao();
noticeDao.update(notice);

response.sendRedirect("noticeDetail.jsp?c=" + code);
//또는 이렇게도 가능하다.
//String url = String.format("noticeDetail.jsp?c=%s", notice.getCode());
//response.sendRedirect(url); 
%>