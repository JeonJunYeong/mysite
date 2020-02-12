package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		if(session != null && session.getAttribute("authUser")!=null) {
			session.removeAttribute("authUser");
			session.invalidate();
			
			
			
		}
			

		Cookie[] cookies = request.getCookies();
		
		if(cookies !=null) {
			for(int i=0;i<cookies.length;i++) {
				cookies[i].setMaxAge(0);
				
				response.addCookie(cookies[i]);
			}
		}
		
		WebUtil.redirect(request.getContextPath(), request, response);
		return;
	
	}

}
