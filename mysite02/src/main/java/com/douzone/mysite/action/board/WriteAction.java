package com.douzone.mysite.action.board;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		
		String check=request.getParameter("check");
		String title= request.getParameter("title");
		String content = request.getParameter("content");
		long hit=0;

		Date today = new Date();
		SimpleDateFormat format1=new SimpleDateFormat("yyyyMMdd");
   		String date = format1.format(today);
		
   		long gNo=new BoardRepository().findgNo();
   		
   		
   		BoardVo vo = new BoardVo();
		
   		HttpSession session = request.getSession();
   		UserVo user=(UserVo)session.getAttribute("authUser");
   		
		vo.setTitle(title);
		vo.setContents(content);
		vo.setHit(hit);
		vo.setReg_date(date);
		vo.setUserNo(user.getNo());
		
		
		if("add".equals(check)) {
			vo.setG_no(gNo+1);
			vo.setO_no(0);
			vo.setDepth(0);
		}else if("rewrite".equals(check)) {
			
			long writeNo=Long.parseLong(request.getParameter("writeNo"));
			
			BoardVo tempVo=(BoardVo)new BoardRepository().findByNo(writeNo);
			vo.setG_no(tempVo.getG_no());
			vo.setO_no(tempVo.getO_no()+1);
			vo.setDepth(tempVo.getO_no()+1);
			
		}
		
		
		new BoardRepository().insert(vo);
		WebUtil.redirect(request.getContextPath()+"/board?a=list", request, response);
	}

}
