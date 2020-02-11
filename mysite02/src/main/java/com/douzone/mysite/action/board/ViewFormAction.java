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

public class ViewFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		
		long n = Long.parseLong(request.getParameter("n"));
		
		BoardVo vo= new BoardRepository().findByNo(n);
		
		HttpSession session = request.getSession();
		UserVo nowVo = null;
		if(session != null && session.getAttribute("authUser")!=null) {
			
			nowVo=(UserVo)session.getAttribute("authUser");	
			request.setAttribute("login", true);
			if(nowVo.getNo()!=vo.getUserNo()) {
				request.setAttribute("user", false);
			}
			else {
				request.setAttribute("user", true);
			}
		}else {
			request.setAttribute("login", false);
		}
		request.setAttribute("no", n);
		request.setAttribute("vo", vo);
		WebUtil.forward("WEB-INF/views/board/view.jsp", request, response);
	
	}

}
