package mx.com.dulceayuda.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		long startTimeRequest = System.currentTimeMillis();
		
		request.setAttribute("startTimeRequest", startTimeRequest);
		
		return true;
	}
	
	
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		
		logger.info("Recurso solicitado: {} {} Tiempo total de respuesta: {} ms ", request.getMethod(), request.getRequestURL().toString(), (System.currentTimeMillis() - (long)request.getAttribute("startTimeRequest")));
		
	}
	
	

}
