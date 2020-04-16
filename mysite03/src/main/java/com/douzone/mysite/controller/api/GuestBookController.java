package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@RestController("GuestbookApiController")
@RequestMapping("/api/guestbook")
public class GuestBookController {

	@Autowired
	private GuestbookService guestBookService;
	
	@GetMapping("/list/{no}")
	public JsonResult list(@PathVariable("no") long startNo) {
		
		List<GuestbookVo> list = guestBookService.getMessageList(startNo);
		
		return JsonResult.success(list);
	}
	
	
	
	@PostMapping("/add")
	public JsonResult add(@RequestBody GuestbookVo vo) {
		
		System.out.println("DATA  "+vo);
		
		guestBookService.add(vo);
		vo.setPassword("");
		return JsonResult.success(vo);
	}
	
}
