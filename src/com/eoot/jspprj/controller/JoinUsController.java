package com.eoot.jspprj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/joinus/*")
public class JoinUsController{

	@RequestMapping("login.htm") 
	public String login(String uid, String pwd){		
		return "/WEB-INF/views/joinus/login.jsp"; 
	}	
}
