package com.douzone.mysite.action.board;

import java.io.IOException;

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

public class ViewFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
					
		long n = Long.parseLong(request.getParameter("n"));
		
		request.setAttribute("pNo", Long.parseLong(request.getParameter("p")));
		
		BoardVo vo= new BoardRepository().findByNo(n);
		
		HttpSession session = request.getSession();
		UserVo nowVo = null;
		if(session != null && session.getAttribute("authUser")!=null) {
			
			nowVo=(UserVo)session.getAttribute("authUser");	
			getHit(nowVo.getNo(),n, request, response,vo.getHit());
			request.setAttribute("login", true);
			if(nowVo.getNo()!=vo.getUserNo()) {
				request.setAttribute("user", false);
			}
			else {
				request.setAttribute("user", true);
			}
		}else {
			request.setAttribute("login", false);
			getHit(0,n, request, response,vo.getHit());
		}
		vo.setNo(n);
		request.setAttribute("no", n);
		request.setAttribute("vo", vo);
		WebUtil.forward("WEB-INF/views/board/view.jsp", request, response);
	
	}

	
	public void getHit(long user,long no,HttpServletRequest request,HttpServletResponse response,long hit) {
		
		Cookie[] cookies = request.getCookies();
		
		Cookie viewCookie = null;
		String now = null;
		for(int i = 0; i< cookies.length; i++){
			   if(cookies[i].getName().equals("VIEWCOOKIE")){ 
			    viewCookie = cookies[i];
			   }

		}
		if(viewCookie==null) {
			System.out.println("COOKIE X ");
			Cookie newCookie;
			
			
//			if(user!=0) {
//			 newCookie = new Cookie("VIEWCOOKIE",user+","+"|"+no+"|");
//			}else {
//				newCookie = new Cookie("VIEWCOOKIE","|"+no+"|");
//			}
		
			
			newCookie = new Cookie("VIEWCOOKIE","|"+no+"|");
			now=newCookie.getValue();
			
			response.addCookie(newCookie);
			new BoardRepository().hitUpdate(no, hit);
			return;

		
		}else {
			System.out.println("COOKIE O");
			String value = viewCookie.getValue();
			
//			if(value.indexOf(+user+",")<0) {
//				//유저가없으니 신규쿠키
//			Cookie newCookie = new Cookie("VIEWCOOKIE",user+","+"|"+no+"|");
//				response.addCookie(newCookie);
//			new BoardRepository().hitUpdate(no, hit);
//				}
//		 
//		
			if(value.indexOf("|"+no+"|")<0) {
				value = value+"|"+no+"|";
				viewCookie.setValue(value);
				response.addCookie(viewCookie);
				new BoardRepository().hitUpdate(no, hit);
				return ;
			}
			
			now=value;
			
			
		}
		System.out.println("VALUE : "+ now);
		System.out.println("HIT:" +hit);
		String con_value=now.replace("|", " ");
		String[] arr=con_value.split(" ");
		
		for(int i=0; i<arr.length; i++) {
			if(arr[i].equals(Long.toString(no))) {
				return ;
			}
		}
		
		
		new BoardRepository().hitUpdate(no, hit);
	}
	
	
}
