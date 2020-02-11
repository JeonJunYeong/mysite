package com.douzone.mysite.action.board;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class ReWriteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		if(session != null && session.getAttribute("authUser")!=null) {
			
			UserVo vo=(UserVo)session.getAttribute("authUser");
			request.setAttribute("writeNo",request.getParameter("n") );
			
			request.setAttribute("check", "rewrite");
			request.setAttribute("userNo", vo.getNo());
			WebUtil.forward("/WEB-INF/views/board/write.jsp", request, response);
			
			
		}
		
		
	}

}
