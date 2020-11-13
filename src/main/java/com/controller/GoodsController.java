package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.service.GoodsService;
@Controller
public class GoodsController {
	
	@Autowired
	GoodsService service;
	
	@RequestMapping(value = "/goodsList")
	public ModelAndView goodList(@RequestParam("gCategory") String gCategory) {
		if (gCategory == null) {
			gCategory = "top"; //데이터가 없을 경우 기본값 설정 
		}
		List<GoodsDTO> list = service.goodsList(gCategory);
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodsList",list);
		//request.setAttribute("goodsList",list")와 동일
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value = "/goodsRetrieve") //return 주소가 없어서 goodsRetrieve.jsp로 감 
	@ModelAttribute("goodsRetrieve") //key 값이 goodsRetrieve가 됨 
	public GoodsDTO goodRetrieve(@RequestParam("gCode") String gCode) {
		GoodsDTO dto = service.goodRetrieve(gCode);
		return dto;
	}
	
	@RequestMapping(value = "/loginCheck/cartAdd")
	public String cartAdd(CartDTO cDto ,HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		cDto.setUserid(dto.getUserid());
		session.setAttribute("mesg", cDto.getgCode());
		service.cartAdd(cDto);
		return "redirect:../goodsRetrieve?gCode=" + cDto.getgCode(); //리다이렉션 
	}
	
	@RequestMapping(value = "/loginCheck/CartList")
	public String cartList(RedirectAttributes attr, HttpSession session) {
		MemberDTO mDto = (MemberDTO)session.getAttribute("login");
		List<CartDTO> list = service.cartList(mDto.getUserid());
		attr.addFlashAttribute("cartList", list); //redirect 데이터 전달 
		return "redirect:../cartList";// servlet-context에 등록
	}
	
	
}
