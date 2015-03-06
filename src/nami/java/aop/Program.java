package nami.java.aop;

import java.lang.reflect.Proxy;
import java.util.List;

import com.eoot.jspprj.dao.NoticeDao;
import com.eoot.jspprj.dao.jdbc.JdbcNoticeDao;
import com.eoot.jspprj.model.Notice;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NoticeDao noticeDao = new JdbcNoticeDao();	
		
		NoticeDao noticeProxy = (NoticeDao) Proxy.newProxyInstance( 
				noticeDao.getClass().getClassLoader(),	
				noticeDao.getClass().getInterfaces(),
				new DaoLogHandler(noticeDao));			
		
		List<Notice> list = noticeProxy.getNotices(1, "", "TITLE");
		
		for(Notice n : list)
			System.out.printf("title : %s\n", n.getTitle());
		
		int size = noticeProxy.getSize("");
		System.out.println(size);
		
	}

}
