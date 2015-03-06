package nami.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eoot.jspprj.dao.mybatis.MyBatisMain;

public class Program {

	public static void main(String[] args) {
		/*NoticeDao noticeDao = new JdbcNoticeDao();
		NoticeView view = new NoticeView();
		view.setNoticeDao(noticeDao);*/
		//두 개의 객체를 생성하고 하나의 인젝션을 수행하는 이 부분을 스프링에게 부탁할 것임.  
		
		/*MyBatisMain.start();*/
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("nami/spring/spring-context.xml");
		
/*		NoticeView view = (NoticeView) context.getBean("view");
		view.print();*/
/*		Exam exam = (Exam) context.getBean("exam");
		System.out.println((exam.total()));*/
		ExamMng mng = (ExamMng) context.getBean("mng");
		mng.print();
		
	}
}
