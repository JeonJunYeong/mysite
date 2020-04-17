package com.douzone.mysite.repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.douzone.mysite.vo.GuestbookVo;;

@Repository
public class GuestbookRepository {

	
	@Autowired
	SqlSession sqlSession;
	
	public boolean insert(GuestbookVo vo) {
		
		int count = sqlSession.insert("guestbook.insert",vo);
		
		boolean result = count ==1;
		
		return result;
	}

	public List<GuestbookVo> findAll(){
		List<GuestbookVo> list = sqlSession.selectList("guestbook.findAll");
		return list;
	}
	
	
	public List<GuestbookVo> findAll(long startNo) {
		
		return sqlSession.selectList("guestbook.findAllByNo",startNo);
		
	}
	
	
	public boolean delete(Long no, String password) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("no",no);
		map.put("password", password);
		
		int count = sqlSession.delete("guestbook.delete",map);
		
		boolean result = count ==1;

		return result;
	}
	
	
	
	
}
