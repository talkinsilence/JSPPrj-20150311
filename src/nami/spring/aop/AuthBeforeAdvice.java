package nami.spring.aop;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;

public class AuthBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		// TODO Auto-generated method stub
		Log log = LogFactory.getLog(this.getClass());
		log.info("[Method 인증] 처리함");
	}
}
