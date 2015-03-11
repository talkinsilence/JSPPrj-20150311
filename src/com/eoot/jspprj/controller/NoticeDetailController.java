package com.eoot.jspprj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.eoot.jspprj.dao.NoticeDao;
import com.eoot.jspprj.dao.jdbc.JdbcNoticeDao;
import com.eoot.jspprj.model.Notice;

public class NoticeDetailController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String _code = request.getParameter("c");
		
		NoticeDao noticeDao = new JdbcNoticeDao();
		Notice n = noticeDao.getNotice(_code);

		ModelAndView mv = new ModelAndView("noticeDetail.jsp");
		
		mv.addObject("n", n);
		
		return mv;
	}

}
