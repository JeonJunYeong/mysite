package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.DefaultEditorKit.CutAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {

	private static final int LIST_SIZE =5;
	private static final int PAGE_SIZE =5;
	
	@Autowired
	private BoardRepository boardRepository;
	
	public Map<String,Object> find(int currentPage,String keyword,String option){
		
		int offset=(currentPage-1)*5;
		int total = boardRepository.totalCount(option,keyword);
		
		
		int pageCnt=(total%LIST_SIZE!=0) ? (total/LIST_SIZE)+1 :  (total/LIST_SIZE);
		int calCnt=(currentPage%5)==0 ? currentPage-1 : currentPage;
	
		int beginPage=calCnt-(calCnt%5)==0 ? 1 : calCnt-(calCnt%5)+1;
		int prevPage = beginPage == 1 ? 1 : beginPage -1;
		int endPage = (pageCnt-(pageCnt%5))==(calCnt-(calCnt%5)) ? pageCnt : (beginPage+PAGE_SIZE)-1;
		int nextPage = (pageCnt-(pageCnt%5))==(calCnt-(calCnt%5)) ? pageCnt : endPage+1;
		
		if(nextPage>=pageCnt)
			nextPage=pageCnt;
		if(endPage>=pageCnt)
			endPage=pageCnt;
		
		
		List<BoardVo> list = boardRepository.find(offset,keyword);
		
		
		Map<String,Object> map = new HashMap<>();
		
		System.out.println("KEYWORD : "+keyword);
		
		map.put("list", list);
		map.put("beginPage",beginPage);
		map.put("prevPage",prevPage);
		map.put("endPage",endPage);
		map.put("nextPage",nextPage);
		map.put("page",currentPage);
		map.put("total",total);
		map.put("kwd",keyword);
		if(endPage!=pageCnt)
			map.put("listsize",LIST_SIZE);
		else
			map.put("listsize",endPage%5);
		
		return map;
		
	}


	public BoardVo findView(int no) {

		BoardVo vo = boardRepository.findView(no);
		
		return vo;
	}


	public boolean insert(BoardVo vo) {
		
		
		int getG_no= boardRepository.findGno();
		
		vo.setG_no(getG_no+1);
		vo.setO_no(1);
		vo.setDepth(0);
		
		boolean result = boardRepository.insert(vo);
		
		return result;
		
	}


	public boolean update(BoardVo vo) {
	
		boolean result = boardRepository.update(vo);
		
		return result;
	}


	public boolean replyinsert(BoardVo vo) {
		
		
		BoardVo tempVo = findView((int)vo.getNo());
		int count = boardRepository.findGnoCount(tempVo.getG_no());
		
		if(count!=1) {
			
			int diff = count -(int)tempVo.getO_no();
			int startNo = count +1;
			
			for(int i=0;i<diff;i++) {
				
				boardRepository.orderUpdate(startNo);
				startNo--;
			}
			
		}
		vo.setG_no(tempVo.getG_no());
		vo.setO_no(tempVo.getO_no()+1);
		vo.setDepth(tempVo.getDepth()+1);
		
		
		boolean result = boardRepository.insert(vo);
		
		return result;
		
	}
	
}
