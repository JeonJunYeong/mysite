package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Cookie[] cookies = request.getCookies();

		 if(cookies == null || cookies.length == 0){
		  
		  System.out.println("쿠키없음");
		  return; //return 실행의 제어권을 반환해준다.
		  
		 }

		 for(Cookie ck:cookies) {
		  System.out.println(ck.getName() + ":" + ck.getValue());
		 
		 }

		 
		HttpSession session = request.getSession();
		UserVo vo;
		
		if(session != null && session.getAttribute("authUser")!=null) {
		
			request.setAttribute("login", true);
			vo=(UserVo)session.getAttribute("authUser");
		}else {
			request.setAttribute("login", false);
		}
		
		long pNo;
		
		if("".equals(request.getParameter("p"))) {
			pNo=1;
		}else {
			pNo=Long.parseLong(request.getParameter("p"));
		}

		List<BoardVo> list = Paging(pNo,request);
		
		long start;
	
		if((Long.parseLong(request.getParameter("p"))%5)==0) {
			start= (Long.parseLong(request.getParameter("p"))/5)-1;
		}else {
			start= (Long.parseLong(request.getParameter("p"))/5);
		}
		long total=new BoardRepository().findAllCount()/5;
		if(new BoardRepository().findAllCount()/5!=0) {
			total=total+1;
		}
		
		if(start>total) {
			start=total;
		}
		
		request.setAttribute("start", start);
		request.setAttribute("end", (start+1)*5);
		request.setAttribute("p", pNo);
		request.setAttribute("total", new BoardRepository().findAllCount());
		request.setAttribute("list", list);
		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
		
	}

	public List<BoardVo> Paging(long pageNum,HttpServletRequest request) {
		
		
		
		long offset = (pageNum-1)*5;
		
		List<BoardVo> list = new BoardRepository().findAll(offset);
		
		
		
		return list;
		
	}
	
}
