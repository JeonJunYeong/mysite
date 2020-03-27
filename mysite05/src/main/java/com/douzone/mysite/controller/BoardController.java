package com.douzone.mysite.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/list")
	public String index(@RequestParam(value="p",required = true,defaultValue = "1") Integer page,
					@RequestParam( value="kwd", required=true, defaultValue="") String keyword,
					@RequestParam( value="option" ,required=true, defaultValue ="title")String option,
					Model model,HttpSession session) {
	
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser==null) {
			authUser = new UserVo();
			model.addAttribute("login",false);
		}else {
			model.addAttribute("login",true);
		}
		
		Map<String,Object> map  = boardService.find(page,keyword,option);
		
		SiteVo now = adminService.find();
		model.addAttribute("siteVo",now);
		
		model.addAttribute("map",map);
		return "board/list";
	}
	
	
	@RequestMapping("/view")
	public String view (HttpSession session ,@RequestParam(value="n") Integer no,@RequestParam(value="p") Integer page,Model model) {
		
		
		BoardVo vo = boardService.findView(no);
		
		//접근제어 and 글쓴이 구분 
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser==null) {
			authUser = new UserVo();
			model.addAttribute("login",false);
		}else {
			model.addAttribute("login",true);
			//로그인 했을 경우 글쓴이와 일치하는 지 확인 필요 
			
			boolean userResult = (vo.getUserNo()==authUser.getNo()) ? true: false;
		
			model.addAttribute("user",userResult);
		}
		
		SiteVo now = adminService.find();
		model.addAttribute("siteVo",now);
		
		model.addAttribute("page",page); //돌아가기 위한 페이지 
		model.addAttribute("vo",vo); // 내용 출력 위함 
		
		return "board/view";
	}
	
	@RequestMapping(value = "/write",method = RequestMethod.GET)
	public String write(Model model) {
		
		model.addAttribute("nowState","write");		
		return "board/write";
	}
	
	@RequestMapping(value = "/write",method = RequestMethod.POST)
	public String write(HttpSession session,BoardVo vo) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}else {
			//insert
			
			vo.setUserNo(authUser.getNo());
			
			boardService.insert(vo);
		}
		
		return "redirect:/board/list";
		
		
	}
	
	@RequestMapping(value = "/modify",method = RequestMethod.GET)
	public String modify(@RequestParam(value="n") Integer no,@RequestParam(value="p") Integer page,Model model) {
		
		BoardVo vo = boardService.findView(no);
		model.addAttribute("vo",vo); // 내용 출력 위함 
		model.addAttribute("n",no);
		model.addAttribute("p",page);
		
		SiteVo now = adminService.find();
		model.addAttribute("siteVo",now);
		
		return "board/modify";
	}
	
	@RequestMapping(value="/modify",method = RequestMethod.POST)
	public String modify(HttpSession session,@RequestParam(value="n") Integer no,@RequestParam(value="p") Integer page,BoardVo vo) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}else {
			vo.setNo(no);
			boardService.update(vo);
		}
		
		return "redirect:/board/view?n="+no+"&p="+page;
	}
	
	@RequestMapping(value="/reply",method =RequestMethod.GET)
	public String reply(Model model,@RequestParam(value="n") Integer no,@RequestParam(value="p") Integer page) {
		
		
		model.addAttribute("n",no);
		model.addAttribute("p",page);
		model.addAttribute("nowState","reply");	
		
		SiteVo now = adminService.find();
		model.addAttribute("siteVo",now);
		
		return "/board/write";
	}
	
	@RequestMapping(value="/reply",method = RequestMethod.POST)
	public String reply(HttpSession session,@RequestParam(value="n") Integer no,@RequestParam(value="p") Integer page,BoardVo vo) {
		
	
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}else {
			vo.setNo(no);
			vo.setUserNo(authUser.getNo());
			boardService.replyinsert(vo);
		}
	
		return "redirect:/board/list?p="+page;
	}
	
	
	
	
}
