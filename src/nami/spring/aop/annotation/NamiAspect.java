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
	
		Log log = LogFactory.getLog(this.getClass()); //import: commons�� �޾ƿ���. 
		StopWatch sw = new StopWatch();
		log.info("�Լ� ȣ�� ����");
		sw.start();
		
		//�߽�. �־���. ���ڴ� ȣ��. 
		Object result = joinPoint.proceed();
		
		//�ڿ��� ó���� ������ �ִٸ� ���⿡. 
		sw.stop();
		log.info("�Լ� ȣ�� ��!!!!!!");
		log.info("[ȣ�� �޼ҵ�] : " + joinPoint.getSignature().getName());
		log.info("[arguments] : " + Arrays.toString(joinPoint.getArgs()));
		log.info("[ȣ�� �ð�] : " + sw.getTotalTimeMillis());
		
		return result;
	}
	
	@Before("execution(* com.eoot.jspprj.dao.NoticeDao.getSize(..))") //getSize()������ ���� ��
	public void authBefore(JoinPoint joinPoint) throws Throwable{

		Log log = LogFactory.getLog(this.getClass());
		log.info("[ȣ�� �޼ҵ�] : " + joinPoint.getSignature().getName());
		log.info("[arguments] : " + Arrays.toString(joinPoint.getArgs()));
		log.info("[Method ����] ó����"); //�̰͸� ���� ��� JoinPoint�� ���� ���� �ʿ����. 
	}
}
