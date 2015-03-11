<%@page import="com.eoot.jspprj.dao.MemberDao"%>
<%@page import="com.eoot.jspprj.dao.jdbc.JdbcMemberDao"%>
<%@page import="com.eoot.jspprj.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String uid = request.getParameter("uid");
	String pwd = request.getParameter("pwd");
	
	Member member = new JdbcMemberDao().getMember(uid); //다오객체의 getMember를 이용해야. 
	
	String msg ="";
	
	if(member == null) //아이디 검사. 회원이 존재하지 않는경우. 
		msg = "회원이 존재하지 않습니다. 신규 회원가입을 해주세요.";
	else if(!member.getPwd().equals(pwd))//비번이 일치하지 않는다면. (아이디 검사는 끝남)
		msg = "비밀번호가 일치하지 않습니다. 다시 입력해 주세요.";
	else//로그인성공
	{
		session.setAttribute("uid", uid); //세션에 로그인기록을 저장한다. 
		//msg = "로그인 성공 ^0^";
	}
	
	
	   //msg != null로 조건식 주고 싶다면, 초기값을 String msg = null;라고 주었어야 함!!
	if(!msg.equals("")) //오류가 있을 경우, 오류메시지를 담아 login.jsp로 가도록 해야함.
	{
		request.setAttribute("msg", msg);
		//jsp에서 상태유지를 위해 쓰는 4대 저장소 page, request, session, application
		//현재페이지에서 담아서 포워드하는 페이지에 줄 때 request에 담아준다. 	
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
		//현재 페이지가 포워드하는 페이지에 전달해줄때 request와 response라는 도구를 공유한다. 
	}
	else//오류 없으면 초기페이지로.
		response.sendRedirect("../index.jsp");
%>

<%=msg%>