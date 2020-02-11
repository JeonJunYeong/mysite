package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class UpdateAction implements Action{

	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String password=request.getParameter("password");
		String gender=request.getParameter("gender");
		
		long num = Long.parseLong(no);
		
		UserVo vo = new UserVo();
		
		vo.setNo(num);
		vo.setName(name);
		vo.setPassword(password);
		vo.setGender(gender);
		new UserRepository().update(vo);
		
		
		
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser",vo );
		WebUtil.redirect(request.getContextPath(), request, response);
	}

}
