package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1. handler 종류 ㅎ확인 
		if(handler instanceof HandlerMethod == false) {
			
			//asset 접근 DefaultServletHandler가 처리하는 경우(보통,assets의 정적 자원 접근) 
			return true;
		}
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		//3. Method의 @Auth 받아오기 
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//4. Method에 @Auth가 없으면
		if(auth == null) {
			
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		//5. Type 이나 Method 둘 다 @Auth 적용이 안되어 있는 경우, 
		if(auth == null) {
			return true;
		}
		
		//5. 인증 여부 확인(@Auth가 붙어 있기 때문)
		HttpSession session = request.getSession();
		
		if(session == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
			
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		//6. 권한(Authorization) 체크를 위해서 role 가져오기 ("USER","ADMIN")
		
		//6. role 가져오기 
		String role = auth.value();
		System.out.println("role : "+role);
		//7. @Auth의 role이 "USER"인 경우에는
		// authUser의 role이 "USER" 이든 "ADMIN" 상관이 없음. 
		if("USER".equals(role)) {
			return true;
		}
		
		
		if("ADMIN".equals(authUser.getRole())==false) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		//8 @Auth의 role 이 "ADMIN" 인 경우에는 반드시 authUer의 role이 "ADMIN"이 된다 
		// 인증확인 되었으므로 핸들러 메소드 실행 
		return true;
	}
	
}
