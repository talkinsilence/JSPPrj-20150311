package com.eoot.jspprj.model;

import java.util.Date;

public class Notice {
	private String code;
	private String title;
	private String writer;
	private Date regdate;
	private String content;
	private int hit;
	
	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public String getWriter() {return writer;}
	public void setWriter(String writer) {this.writer = writer;}
	public Date getRegdate() {return regdate;}
	public void setRegdate(Date regdate) {this.regdate = regdate;}
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	public int getHit() {return hit;}
	public void setHit(int hit) {this.hit = hit;}
	
}
