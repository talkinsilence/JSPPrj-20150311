package com.eoot.jspprj;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;

@WebServlet("/index")
public class Index extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String _sumapp = "0";
		ServletContext application = request.getServletContext();
		if(application.getAttribute("sum")!= null)
		_sumapp = application.getAttribute("sum").toString();//Object로 반환해주니까 스트링으로 변환해줘야함. 
		
		String _sumsess = "0";
		HttpSession session = request.getSession();
		if(session.getAttribute("sum")!= null)
		_sumsess = session.getAttribute("sum").toString();
		
		String _sumcook = "0";
		Cookie[] cookies = request.getCookies();//쿠키는 여러개 가져올 수 있으니까 cookies.
		if(cookies != null)
			for (Cookie cookie : cookies) //누가 이 쿠키 아는 사람 없나요~~~ 
				if("sum".equals(cookie.getName()))
					_sumcook = cookie.getValue();

		
		PrintWriter out = response.getWriter();
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("</head>");
		out.write("<body>");
		out.write("<p><a href = \"add\">계산하기</a></p>");
		out.write("<p>마지막 계산한 값 app: " + _sumapp + "</p>");
		out.write("<p>마지막 계산한 값 session: " + _sumsess + "</p>");
		out.write("<p>마지막 계산한 값 cookie: " + _sumcook + "</p>");
		out.write("</body>");
		out.write("</html>");
	}	
	

}
