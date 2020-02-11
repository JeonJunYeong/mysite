package com.douzone.mysite.action.guestbook;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
   		String password = request.getParameter("pass");
   		String contents = request.getParameter("content");
   		
   		Date today = new Date();
		
		SimpleDateFormat format1=new SimpleDateFormat("yyyyMMdd");
   		String date = format1.format(today);
   		GuestbookVo vo = new GuestbookVo();
   		
   		vo.setName(name);
   		vo.setPassword(password);
   		vo.setContents(contents);
   		vo.setReg_date(date);
   		
   		new GuestbookRepository().insert(vo);
   		WebUtil.redirect(request.getContextPath()+"/guestbook?a=list", request, response);
		
	}

}
