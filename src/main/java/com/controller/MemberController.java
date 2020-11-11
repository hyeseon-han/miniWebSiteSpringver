package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@RequestMapping(value = "/memberAdd")
	public String memberAdd(MemberDTO m, Model model) {//폼에서 넘어온 데이터를 MemberDTO에 자동 저장
		service.memberAdd(m);
		model.addAttribute("success", "회원가입성공");
		return "main";//main.jsp
	}
	
	@RequestMapping(value = "/loginCheck/myPage")
	public String myPage(HttpSession session) {
		//세션에 id얻기
		//db에서 id에 해당하는 DTO정보 얻기 myPage(사용자가 id이용)
		//dto정보 콘솔에 출력 
		
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		String userid = dto.getUserid();
		dto = service.mypage(userid);
		session.setAttribute("login", dto);
		System.out.println(dto);
		return "redirect:../myPage"; //servlet-context에 등록
		
	}
}
