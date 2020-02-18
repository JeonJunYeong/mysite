package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
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

		String option="";
		String kwd="";
		String now="";
		
		long pNo=1;
		long start;
		
		long total;
		if(request.getParameter("p")!=null) {
			request.getSession().setAttribute("p", request.getParameter("p"));
			pNo=Long.parseLong(request.getParameter("p"));
		}
		
		if(request.getParameter("kwd")==null)
			kwd="";
		else {
			kwd=request.getParameter("kwd");
			option = request.getParameter("option");
		}
		
		if("".equals(kwd)) {
			
			request.setAttribute("total", new BoardRepository().findAllCount());
			total=new BoardRepository().findAllCount()/5;
			if(new BoardRepository().findAllCount()/5!=0) {
				total=total+1;
			}	
			
			request.setAttribute("option","" );
			request.setAttribute("kwd","" );
			
			
			now="list";
			
		}else {
			long count = new BoardRepository().findSearchCount(option,kwd);
			request.setAttribute("total", count);
			total=count/5;
			if(count%5!=0) {
				total=total+1;
			}
			
			request.setAttribute("option",option );
			request.setAttribute("kwd",kwd );
			
			
			now="search";
			
		}
		

		HttpSession session = request.getSession();
		UserVo vo = null;
			
		if(session != null && session.getAttribute("authUser")!=null) {
		
			request.setAttribute("login", true);
			
			vo=(UserVo)session.getAttribute("authUser");
		}else {
			request.setAttribute("login", false);
			//vo.setNo(0);
		}
		
		
		
		List<BoardVo> list = Paging(pNo,request,now);
		
		
		if((pNo%5)==0) {
			start= (pNo/5)-1;
		}else {
			start= (pNo/5);
		}
		
		if(start>total) {
			start=total;
		}
		
		
		
		request.setAttribute("start", start);
		request.setAttribute("end", (start+1)*5);
		//request.setAttribute("p", pNo);
		request.setAttribute("list", list);
		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);
		
	}

	public List<BoardVo> Paging(long pageNum,HttpServletRequest request,String a) {
		HttpSession session = request.getSession();
		long offset = (pageNum-1)*5;
		String option;
		String kwd;
		List<BoardVo> list;
		if("list".equals(a)) {
			list = new BoardRepository().findAll(offset);
			request.setAttribute("a", "list");
		}else {
			
			option = (String)session.getAttribute("option");
			kwd = (String)session.getAttribute("kwd");
			
			list = new BoardRepository().findSearchAll(offset,option,kwd);
			request.setAttribute("a", "search");
		}
		
		return list;
		
	}
	
	
	
}
