package com.example.demo.component;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.demo.repository.LogRepository;

@Component
public class RequestTimeInterceptor extends HandlerInterceptorAdapter{
	
	private static final Log logg = LogFactory.getLog(RequestTimeInterceptor.class);
	
	@Autowired
	private LogRepository logRepository;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
		long startTime = (long) request.getAttribute("startTime");
		
		String username = "";
		String url = request.getRequestURL().toString();
		
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		//if(auth != null && auth.isAuthenticated()) {
			//username = auth.getName();
		//} 
		
		//com.example.demo.entity.Log log = new com.example.demo.entity.Log(LocalDateTime.now().toString(), auth.getDetails().toString(), username, url);
		//Guardamos en log las acciones del usuario
		//logRepository.saveLog(log);
		logg.info("Request Url : " + request.getRequestURL().toString() + "Tiempo total respuesta : " + (System.currentTimeMillis() - startTime) + " ms");
	}
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}

}
