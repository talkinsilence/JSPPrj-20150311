package nami.spring;

import java.util.List;

import com.eoot.jspprj.dao.NoticeDao;
//import com.eoot.jspprj.dao.jdbc.JdbcNoticeDao;
import com.eoot.jspprj.model.Notice;

public class NoticeView {

	private NoticeDao noticeDao;

//	주입의 두 가지 방법 ) 세터를 이용한 injection과 생성자 이용한 injection 
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	public NoticeView(){
//		noticeDao = new JdbcNoticeDao();
	}
	
	public NoticeDao getNoticeDao() {
		return noticeDao;
	}

	public void print(){
		List<Notice> list = noticeDao.getNotices(1, "", "TITLE");
		for(Notice n : list)
			System.out.printf("title : %s\n", n.getTitle());
	}
}
