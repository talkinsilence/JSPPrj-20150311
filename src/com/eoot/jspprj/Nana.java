package com.eoot.jspprj;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/nana") //nana로 랩핑됨. 이제 404에러 안뜬다. 단,톰캣이 7.0이상일 때에만 가능.
public class Nana extends HttpServlet{
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8"); //출력시 
		response.setContentType("text/html; charset=UTF-8");
		
//		request//입력도구
//		?는 cnt받음. cnt는 미리 약속되어 있어야. 
//		request로 꺼내오면 무조건 문자열이됨. _언더바로 임시변수임을 나타내주자. 
//		if(_cnt가 널이 아니면)
//			cnt = _cnt를 정수로 변환;
//		http://..../hello?cnt=1
/*		네가지 방식으로 사용자의 요청 전달 가능 
		http://..../hello?cnt=3 //3이라는 문자열이 전달됨
		http://..../hello?cnt= //빈문자열이 전달됨. 이경우 오류 생김. 숫자로 변환이 안되니까. 
		http://..../hello? //null
		http://..../hello //null
*/		
		
		String _cnt = request.getParameter("cnt");
		
		int cnt = 100; 
		
		if(_cnt != null)
			cnt = Integer.parseInt(_cnt);
	
		PrintWriter out = response.getWriter();
		for(int i = 0; i < cnt; i++)
			out.println(i+1 +". 안녕 서블릿<br />");
	}
}
