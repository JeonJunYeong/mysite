package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {

	@Autowired
	SqlSession sqlSession;
	
	public List<BoardVo> find(int offset,String keyword){
		
		Map<String,Object> map = new HashMap<>();
		
		map.put("offset",offset);
		map.put("keyword",keyword);
		
		
		List<BoardVo> list = sqlSession.selectList("board.find",map);
		
		return list;
	}
	
	public int totalCount(String option,String keyword) {
		
		
		Map<String,Object> map = new HashMap<>();
		
		map.put("option", option);
		map.put("keyword",keyword);
		
		int count = sqlSession.selectOne("board.totalCount",map);
		
		
		return count;
	}
	
	public BoardVo findView(int no) {
		
		BoardVo vo = sqlSession.selectOne("board.findView",no);
		
		return vo;
		
	}

	public boolean insert(BoardVo vo) {
		
		int count = sqlSession.insert("board.insert",vo);
		
		
		return count ==1;
	}

	public int findGno() {
		
		int result = 0;
		
		if(sqlSession.selectOne("board.findGno")!=null) {
			result  = sqlSession.selectOne("board.findGno");	
		}
		
		return result;
		
	}
	
	public boolean update(BoardVo vo) {
	
		int count =sqlSession.update("board.update",vo);
		
		return count==1;
	}

	public int findGnoCount(long g_no) {
		
		
		int count =sqlSession.selectOne("board.findGnoCount",(int)g_no);
		
		return count;
	}

	public boolean orderUpdate(int startNo) {
		
		Map<String,Object> map = new HashMap<>();
		
		map.put("updateNo",startNo);
		map.put("nowNo", startNo-1);
		
		
		int count = sqlSession.update("board.orderUpdate",map);
		
		
		return count ==1;
	}
	
}
