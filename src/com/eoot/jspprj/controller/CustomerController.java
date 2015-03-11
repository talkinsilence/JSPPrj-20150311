package com.eoot.jspprj.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eoot.jspprj.dao.NoticeDao;
import com.eoot.jspprj.dao.jdbc.JdbcNoticeDao;
import com.eoot.jspprj.model.Notice;
import com.eoot.jspprj.model.NoticeFile;

@Controller
@RequestMapping("/customer/*")
public class CustomerController {
	
	
	private NoticeDao noticeDao;
	
	@Autowired  //속성에 붙이기보다는 가능하면 세터에 붙여주자. Autowired로 데이터 바인딩. 
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	@RequestMapping("notice.htm") //notice.htm으로부터 오는 건 얘가 처리할 것임. 
	public String notice(String p, String f, String q, Model model){
		                 //일치하는 키값이 있을 때만 전달된디
		int npage =1;
		String query = "";
		String field = "TITLE";
		
/*		String _page = request.getParameter("p");
		String _query = request.getParameter("q");
		String _field = request.getParameter("f");*/
		
		if(p != null && !p.equals(""))
		   npage = Integer.parseInt(p);
		
		if(q != null && !q.equals(""))
		   query = q;
		
		if(f != null && !f.equals(""))
		   field = f;
		
		//NoticeDao noticeDao = new JdbcNoticeDao();

		List<Notice> list = noticeDao.getNotices(npage, query, field); 
		
		/*ModelAndView mv = new ModelAndView("notice.jsp");
		
		mv.addObject("list", list);
		mv.addObject("total", noticeDao.getSize("", "TITLE"));*/
		model.addAttribute("list", list);
		model.addAttribute("total", noticeDao.getSize("", "TITLE"));
		return "/WEB-INF/views/customer/notice.jsp"; 
	}	
	
	@RequestMapping("noticeDetail.htm")
	public String noticeDetail(String c, Model model){
		
		/*String _code = request.getParameter("c");*/
		
		//NoticeDao noticeDao = new JdbcNoticeDao();
		Notice n = noticeDao.getNotice(c);

		/*ModelAndView mv = new ModelAndView("noticeDetail.jsp");
		
		mv.addObject("n", n);*/
		model.addAttribute("n", n);
		
		return "/WEB-INF/views/customer/noticeDetail.jsp";
	}
	
	@RequestMapping("noticeDel.htm")
	public String noticeDel(String c){
		
		//NoticeDao noticeDao = new JdbcNoticeDao();
		noticeDao.delete(c);
		
		return "redirect:notice.htm"; //forward가 아니라 redirect필요.
	}
	
	//입력 폼을 제공하는 GET과 입력된 값을 처리하는 POST필요. 
	@RequestMapping(value="noticeEdit.htm", method=RequestMethod.GET)
	public String noticeEdit(String c){ 
		
		//detail과 같은 로직 
		
		return "/WEB-INF/views/customer/noticeEdit.jsp";
	}
	
	//이전에는 POST를 위해 ~proc을 붙였지만... 이제는 proc필요 없다. 
	
	@RequestMapping(value="noticeEdit.htm", method=RequestMethod.POST)
	public String noticeEdit(Notice n){ //오버로드 
		
		//editProc과 같은 로직 
		
		return "redirect:noticeDetail.htm";
	}
	
	@RequestMapping(value="noticeReg.htm", method=RequestMethod.GET)
	public String noticeReg(){
		return "/WEB-INF/views/customer/noticeReg.jsp";
	}
	
	@RequestMapping(value="noticeReg.htm", method=RequestMethod.POST)
	public String noticeReg(Notice n/*, NoticeFile nf*/, HttpServletRequest request){ //세터명 충돌하지 않도록 주의!
		//multipartResolver설정만 잘 해주면 파일이든 문자열이든 알아서 잘 담기게 된다. 
		
		System.out.println(n.getFile().getOriginalFilename());

		
		String path = "/customer/upload"; //저장할때는 물리경로 필요. 서블릿 컨텍스트 필요. 
		path = request.getServletContext().getRealPath(path);
		
		String fname = n.getFile().getOriginalFilename();
		String fpath = path + "\\" + fname;
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fpath);
			fos.write(n.getFile().getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//파일 데이터
		//문자열 데이터
		return "redirect:notice.htm"; 
	}
		
	
}
