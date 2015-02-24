package com.eoot.jspprj;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{

	public FilterConfig filterConfig;
	private String encoding;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(final ServletRequest request,final ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//		System.out.println("before 필터");
//		request.setCharacterEncoding("UTF-8"); //필터 써주는데, 이렇게 말구 인자를 넘겨받는 방식으로 하자. 
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
//		System.out.println("after 필터");
//		필터는 내가 만들었지만, 톰캣은 모름. 이 이름으로 필터 만들었다고 내가 web.xml에 알려줘야 한다. 
	}

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
	}
	

}
