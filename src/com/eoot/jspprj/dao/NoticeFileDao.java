package com.eoot.jspprj.dao;

import java.util.Date;
import java.util.List;

import com.eoot.jspprj.model.Notice;
import com.eoot.jspprj.model.NoticeFile;

public interface NoticeFileDao {
	public List<NoticeFile> getNoticeFiles(String noticeCode); 
	public int insert(NoticeFile file); 

}
