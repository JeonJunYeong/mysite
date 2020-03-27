package com.douzone.mysite.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.mysite.vo.SiteVo;


@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/list")
	public String index(Model model) {
		
		SiteVo now = adminService.find();
		model.addAttribute("siteVo",now);
		
		List<GuestbookVo> list = guestbookService.findAll();
		model.addAttribute("list",list);
		return "guestbook/list";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(GuestbookVo vo) {
		
		guestbookService.add(vo);
		
		return "redirect:/guestbook/list";
	}
	
	
	@RequestMapping(value="/delete/{n}",method = RequestMethod.GET)
	public String delete(@PathVariable("n") Long no,Model model) {
		model.addAttribute("n",no);
		
		SiteVo now = adminService.find();
		model.addAttribute("siteVo",now);
		
		
		return "guestbook/delete";
	}
	
	@RequestMapping(value="/delete/{n}",method = RequestMethod.POST)
	public String delete(@PathVariable("n")Long no,@RequestParam(value="password", required=true, defaultValue ="")String password,Model model) {
		
		
		boolean result = guestbookService.delete(no, password);
		
		if(result) {
			return "redirect:/guestbook/list";
		}else {
			model.addAttribute("result","false");
			model.addAttribute("n",no);
			return "guestbook/delete";
		}
		
		
	}
	
	
}
