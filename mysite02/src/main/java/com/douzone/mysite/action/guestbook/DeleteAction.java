package com.douzone.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class DeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		GuestbookVo vo =new GuestbookVo();
		long num = Long.parseLong(no);
		vo.setNo(num);
		vo.setPassword(password);
		
		
		
		boolean result =new GuestbookRepository().delete(vo);
		
		if(result==false) {
			request.setAttribute("result", "fail");
			WebUtil.forward("/WEB-INF/views/guestbook/deleteform.jsp", request, response);
			return;
		}
		
		WebUtil.redirect(request.getContextPath()+"/guestbook?a=list", request, response);
	}

}
