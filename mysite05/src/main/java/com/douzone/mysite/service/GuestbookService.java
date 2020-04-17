package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;


@Service
public class GuestbookService {

	
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	
	public List<GuestbookVo> findAll(){
		
		List<GuestbookVo> list = guestbookRepository.findAll();
		
		return list;
		
	}
	
	public boolean add(GuestbookVo vo) {
		
		boolean result =guestbookRepository.insert(vo);
		
		return result;
	}
	
	public boolean delete(long no, String password) {
		
		boolean result = guestbookRepository.delete(no, password);
		
		
		return result;
	}
	
	public List<GuestbookVo> getMessageList(long startNo) {
		
		return guestbookRepository.findAll(startNo);
	}
	
}
