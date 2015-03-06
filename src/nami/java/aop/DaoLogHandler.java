package nami.java.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

import com.eoot.jspprj.dao.NoticeDao;

public class DaoLogHandler implements InvocationHandler {
	
	private NoticeDao noticeDao;
	
	public DaoLogHandler(NoticeDao noticeDao) {
		// TODO Auto-generated constructor stub
		this.noticeDao = noticeDao;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) //invoke? 왕자로부터 기대되는 함수가 호출됨.
			throws Throwable {
		// TODO Auto-generated method stub
		
		//앞에서 처리할 내용이 있다면 여기에.
		Log log = LogFactory.getLog(this.getClass()); //import: commons꺼 받아오자. 
		StopWatch sw = new StopWatch();
		log.info("함수 호출 시작");
		sw.start();
		
		//중심. 주업무. 왕자님 호출. 
		Object result = method.invoke(noticeDao, args);
		
		//뒤에서 처리할 내용이 있다면 여기에. 
		sw.stop();
		log.info("함수 호출 끝!!!!!!");
		log.info("[호출 메소드] : " + method.getName());
		log.info("[호출 시간] : " + sw.getTotalTimeMillis());
		
		return result;
	}

}
