package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter{
	@Override
	//loginCheck
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
		System.out.println("preHandle동작 ===");
		HttpSession session = request.getSession();
		if (session.getAttribute("login") == null) {
			response.sendRedirect("../loginForm");
			return false;//주의
		}else {
			return true; //주의 //로그인 정보가 있는 경우 이후 작업 계속 진행 
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelandview) throws Exception{
		
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
	}
	
}
