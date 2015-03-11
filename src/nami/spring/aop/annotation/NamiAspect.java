package nami.spring.aop.annotation;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.util.StopWatch;

@Aspect
public class NamiAspect {
	
	@Around("execution(* com.eoot.jspprj.dao.NoticeDao.get*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
	
		Log log = LogFactory.getLog(this.getClass()); //import: commons꺼 받아오자. 
		StopWatch sw = new StopWatch();
		log.info("함수 호출 시작");
		sw.start();
		
		//중심. 주업무. 왕자님 호출. 
		Object result = joinPoint.proceed();
		
		//뒤에서 처리할 내용이 있다면 여기에. 
		sw.stop();
		log.info("함수 호출 끝!!!!!!");
		log.info("[호출 메소드] : " + joinPoint.getSignature().getName());
		log.info("[arguments] : " + Arrays.toString(joinPoint.getArgs()));
		log.info("[호출 시간] : " + sw.getTotalTimeMillis());
		
		return result;
	}
	
	@Before("execution(* com.eoot.jspprj.dao.NoticeDao.getSize(..))") //getSize()에서만 위빙 生
	public void authBefore(JoinPoint joinPoint) throws Throwable{

		Log log = LogFactory.getLog(this.getClass());
		log.info("[호출 메소드] : " + joinPoint.getSignature().getName());
		log.info("[arguments] : " + Arrays.toString(joinPoint.getArgs()));
		log.info("[Method 인증] 처리함"); //이것만 썼을 경우 JoinPoint에 대한 정보 필요없다. 
	}
}
