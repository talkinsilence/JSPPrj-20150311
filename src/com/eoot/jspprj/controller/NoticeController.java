package com.eoot.jspprj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.eoot.jspprj.dao.NoticeDao;
import com.eoot.jspprj.dao.jdbc.JdbcNoticeDao;
import com.eoot.jspprj.model.Notice;

public class NoticeController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int npage =1;
		String query = "";
		String field = "TITLE";
		
		String _page = request.getParameter("p");
		String _query = request.getParameter("q");
		String _field = request.getParameter("f");
		
		if(_page != null && !_page.equals(""))
		   npage = Integer.parseInt(_page);
		
		if(_query != null && !_query.equals(""))
		   query = _query;
		
		if(_field != null && !_field.equals(""))
		   field = _field;
		
		NoticeDao noticeDao = new JdbcNoticeDao();

		List<Notice> list = noticeDao.getNotices(npage, query, field); 
		
		ModelAndView mv = new ModelAndView("notice.jsp");
		
		mv.addObject("list", list);
		mv.addObject("total", noticeDao.getSize("", "TITLE"));

		return mv;
	}

}
