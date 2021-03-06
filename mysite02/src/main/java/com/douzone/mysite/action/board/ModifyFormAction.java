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

public class ModifyFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		long n = Long.parseLong(request.getParameter("n"));
		System.out.println("NNNNNN : "+n);
		BoardVo vo= new BoardRepository().findByNo(n);
		vo.setNo(n);
		System.out.println("TTTTT"+vo.toString());
		request.setAttribute("vo", vo);
		WebUtil.forward("WEB-INF/views/board/modify.jsp", request, response);
		
	}

}
