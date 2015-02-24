<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter"%>

<%
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String _x = request.getParameter("x");
		String _y = request.getParameter("y");
		
		int x = 0;
		int y = 0; 
		if(request.getMethod().equals("POST")){
		if(_x != null && !_x.equals(""))
			x = Integer.parseInt(_x);
		
		if(_y != null && !_y.equals(""))
			y = Integer.parseInt(_y);
		}
/*		if(request.getMethod().equals("POST")){
			x = Integer.parseInt(request.getParameter("x"));
			y = Integer.parseInt(request.getParameter("y"));
		}*/
		
		int sum = x + y;
		
		//세 가지 저장소 사용해보기
		String _save = request.getParameter("save");
		
		if(_save != null){
			String _sum = request.getParameter("sum");
//			_save = new String(_save.getBytes("ISO-8859-1"),"UTF-8"); 이거 쓰지 말고 필터쓰자.
			//_save가져온 것을 다시 UTF-8로 변경하여 문자열을 만들어 주는 작업을 해 준다. 
			System.out.println(_save);
			
			if(_save.equals("앱앱")){
			//	ServletContext application = request.getServletContext();
				application.setAttribute("sum", _sum);
				System.out.println("saved in app");
			}
			
			else if(_save.equals("session")){
				//HttpSession session = request.getSession(); //이 부분만 좀 다른 거임!!
				session.setAttribute("sum", _sum);
				System.out.println("saved in session");
			}
			
			else if(_save.equals("cookie")){
				Cookie cookie = new Cookie("sum", _sum);
				cookie.setMaxAge(24*60*60); //24시간
				response.addCookie(cookie);
				System.out.println("saved in cookie");
			}
		
		}
//PrintWriter out = response.getWriter();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action = "add.jsp" method = "post" >
	<ul>
		<li><label for = "x">X:</label><input name = "x"/></li>
		<li><label for = "y">Y:</label><input name = "y"/></li>
		<li><label for = "sum">SUM:</label><input name = "sum" value = 뀨앙sum /></li>
	</ul>
	<ul>
		<input type = "submit" value = "덧셈" />
		<input type = "submit" name = "save" value = "앱앱" />
		<input type = "submit" name = "save" value = "session" />
		<input type = "submit" name = "save" value = "cookie" />
	</ul>
	<p><a href = "index">홈으로 돌아가기</a></p>
</form>
</body>
</html>
