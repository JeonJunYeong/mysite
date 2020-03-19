package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.vo.UserVo;

public class LogoutInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		UserVo vo = (UserVo)session.getAttribute("authUser");
		
		if(vo ==null) {
			
		}
		
		session.removeAttribute("authUser");
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/");
			
		return false;
	}

}
