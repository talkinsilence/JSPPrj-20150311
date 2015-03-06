package nami.spring.aop;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eoot.jspprj.dao.NoticeDao;
import com.eoot.jspprj.model.Notice;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("nami/spring/aop/spring-context.xml");
		
		NoticeDao noticeDao = (NoticeDao) context.getBean("noticeDao");
		List<Notice> list = noticeDao.getNotices(1, "", "TITLE");
		
		for(Notice n : list)
			System.out.printf("title : %s\n", n.getTitle());
	}
}
