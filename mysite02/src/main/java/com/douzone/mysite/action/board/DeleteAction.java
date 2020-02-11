package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		long n = Long.parseLong(request.getParameter("n"));
		
		BoardVo temp = new BoardRepository().findByNo(n);
		//현재 객체 잠시 저장 -> 삭제 -> 업데이트
		
		long gNo = temp.getG_no();
		long oNo = temp.getO_no();
		long depth = temp.getDepth();
		
		long maxDepth = new BoardRepository().findMaxDepth(gNo);
			
		new BoardRepository().delete(n);
		
		if(gNo==0) {
			new BoardRepository().allDelete(gNo);
			return ;
		}
		
		if(depth!=maxDepth) {
			long diff = maxDepth-depth;
			
			for(int i=0;i<diff; i++) {
				new BoardRepository().depthUpdate(depth);
			}
		}
		
		WebUtil.redirect(request.getContextPath()+"/board?a=list", request, response);
		
		
	}

}
