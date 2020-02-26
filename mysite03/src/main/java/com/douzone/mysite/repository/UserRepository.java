package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	
	@Autowired
	private SqlSession sqlSession;

	public boolean insert(UserVo vo) {
		
	
		int count = sqlSession.insert("user.insert",vo);
		
		boolean result = count ==1;
		
		return result;
				
	}

	public int update(UserVo vo) {
	
		
		return sqlSession.update("user.update",vo);
		
	}


	public UserVo findByEmailAndPassword(UserVo vo) {
		
	UserVo userVo=sqlSession.selectOne("user.findByEmailAndPassword",vo);
	
	return userVo;
	}
	
	public UserVo findByEmailAndPassword(String email, String password) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("email",email);
		map.put("password", password);
		
		UserVo userVo=sqlSession.selectOne("user.findByEmailAndPassword2",map);
		
		return userVo;
		}
	
	public UserVo findByNo(long no) {
	
	UserVo userVo = sqlSession.selectOne("user.findByNo",no);
		
	
	return userVo;
	}
	
	

}
