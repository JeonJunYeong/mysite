package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class DeleteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String no = request.getParameter("n");
		
		
		request.setAttribute("no", no);
		
		
		HttpSession session = request.getSession();
		UserVo vo = null;
		
		if(session != null && session.getAttribute("authUser")!=null) {
		
			request.setAttribute("login", true);
			vo=(UserVo)session.getAttribute("authUser");
		}else {
			request.setAttribute("login", false);
			vo=new UserVo();
			vo.setNo(0);
		}
		
		BoardVo findUser=new BoardRepository().findByNo(Long.parseLong(no));
		
		if(findUser.getUserNo()==vo.getNo()) {
			request.setAttribute("user", true);
		}else {
			request.setAttribute("user", false);
		}
	
		
		
		WebUtil.forward("WEB-INF/views/board/deleteform.jsp", request, response);
		
	}

}
