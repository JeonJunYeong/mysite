package com.douzone.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class DeleteFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String no = request.getParameter("no");
		
		GuestbookVo vo =new GuestbookVo();
		long num = Long.parseLong(no);
		vo.setNo(num);
		
		request.setAttribute("vo", vo);
		
		WebUtil.forward("/WEB-INF/views/guestbook/deleteform.jsp", request, response);
		
	}

}
