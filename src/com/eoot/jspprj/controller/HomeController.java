package com.eoot.jspprj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class HomeController{

	@RequestMapping("index.htm") 
	public String index(){		
		return "/WEB-INF/views/index.jsp"; 
	}	
}
