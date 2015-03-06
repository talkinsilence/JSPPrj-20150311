package nami.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

public class LogAroundAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		// TODO Auto-generated method stub
		
		//앞에서 처리할 내용이 있다면 여기에.
		Log log = LogFactory.getLog(this.getClass()); //import: commons꺼 받아오자. 
		StopWatch sw = new StopWatch();
		log.info("함수 호출 시작");
		sw.start();
		
		//중심. 주업무. 왕자님 호출. 
		Object result = method.proceed();
		
		//뒤에서 처리할 내용이 있다면 여기에. 
		sw.stop();
		log.info("함수 호출 끝!!!!!!");
		log.info("[호출 메소드] : " + method.getMethod().getName());
		log.info("[호출 시간] : " + sw.getTotalTimeMillis());
		
		return result;
	}

}
