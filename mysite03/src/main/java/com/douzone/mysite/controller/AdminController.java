package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;
@Auth("ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping("")
	public String main(Model model) {
		
		
		SiteVo now = adminService.find();
		
		
		model.addAttribute("siteVo",now);
		return "admin/main";
	}
	
	
	@RequestMapping(value="/main/update",method=RequestMethod.POST)
	public String upload(
						@RequestParam(value="file1") MultipartFile multipartFile,
						SiteVo vo,
						Model model){
		
		
		String fileName = adminService.getFile(multipartFile);
		
		vo.setProfileURL(fileName);
		
		adminService.update(vo);
		
		return "redirect:/admin";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	
}
