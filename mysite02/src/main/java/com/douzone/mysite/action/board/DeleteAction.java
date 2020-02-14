package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		String evt = request.getParameter("evt");
		String no = request.getParameter("no");
		
		
		if("확인".equals(evt)) {
			
			new BoardRepository().delete((Long.parseLong(no)));
			
		}
		
		
		WebUtil.redirect(request.getContextPath()+"/board?a=list&p=1", request, response);
		
		
		
	}

}
