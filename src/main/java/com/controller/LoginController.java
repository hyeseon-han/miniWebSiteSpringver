package com.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class LoginController {
	@Autowired
	MemberService service;
	
	@RequestMapping(value = "/login")
	public String login(@RequestParam HashMap<String, String> map, Model model, HttpSession session) {
		MemberDTO dto = service.login(map);
		String nextPage = null;
		if(dto!=null) {
			session.setAttribute("login", dto);
			nextPage = "redirect:/goodsList?gCategory=top"; //로그인시 상품목록을 가져오도록 redirect함 
		}else {
			model.addAttribute("mesg","로그인 실패");
			nextPage = "loginForm";
		}
		return nextPage;
	}
	
	@RequestMapping(value = "/loginCheck/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:../"; //.xml에 설정 main.jsp .../을 이용하여 /loginCheck의 상위 주소로 이동하여 주소를 사용함 
	}
}
