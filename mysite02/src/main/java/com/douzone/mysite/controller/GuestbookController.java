package com.douzone.mysite.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.action.guestbook.GuestbookFactory;
import com.douzone.web.action.Action;

public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String actionName = request.getParameter("a");
		
		Action action = new GuestbookFactory().getAction(actionName);
		
		action.execute(request, response);
		
//		if("deleteform".equals(action)) {
//			 WebUtil.forward("/WEB-INF/views/guestbook/deleteform.jsp", request, response);
//		}else if("delete".equals(action)) {
//			
//			String no = request.getParameter("no");
//			String password = request.getParameter("password");
//			GuestbookVo vo =new GuestbookVo();
//			long num = Long.parseLong(no);
//			vo.setNo(num);
//			vo.setPassword(password);
//			
//			boolean result =new GuestbookRepository().delete(vo);
//			WebUtil.redirect(request.getContextPath()+"/guestbook", request, response);
//		}else if("add".equals(action)) {
//			String name = request.getParameter("name");
//	   		String password = request.getParameter("pass");
//	   		String contents = request.getParameter("content");
//	   		
//	   		Date today = new Date();
//			
//			SimpleDateFormat format1=new SimpleDateFormat("yyyyMMdd");
//	   		String date = format1.format(today);
//	   		GuestbookVo vo = new GuestbookVo();
//	   		
//	   		vo.setName(name);
//	   		vo.setPassword(password);
//	   		vo.setContents(contents);
//	   		vo.setReg_date(date);
//	   		
//	   		new GuestbookRepository().insert(vo);
//	   		WebUtil.redirect(request.getContextPath()+"/guestbook", request, response);
//	   
//		}
//		else {
//			
//			//default 요청 처리(list)
//			List<GuestbookVo> list = new GuestbookRepository().findAll();
//
//			 request.setAttribute("list", list);
//			 WebUtil.forward("/WEB-INF/views/guestbook/list.jsp", request, response);
//		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
