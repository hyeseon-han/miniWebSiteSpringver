package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.dto.OrderDTO;
import com.service.GoodsService;
import com.service.MemberService;
@Controller
public class GoodsController {
	
	@Autowired
	GoodsService service;
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/goodsList")
	public ModelAndView goodList(@RequestParam("gCategory") String gCategory) {
		if (gCategory == null) {
			gCategory = "top"; //데이터가 없을 경우 기본값 설정 
		}
		List<GoodsDTO> list = service.goodsList(gCategory);
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodsList",list);
		//request.setAttribute("goodsList",list")와 동일
		mav.setViewName("main"); //main.jsp // goodList.jsp
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
	
	@RequestMapping(value = "loginCheck/cartUpdate")
	@ResponseBody
	public void cartUpdate(@RequestParam HashMap<String, String> map ) {//한글설정 필요 없음
		service.cartUpdate(map);
	}
	
	@RequestMapping(value = "loginCheck/cartDelete")
	@ResponseBody //비동기 사용하기 위해 사용 
	public void cartDel(@RequestParam("num") int num) {
		service.cartDelete(num);
	}
	
	@RequestMapping(value = "loginCheck/delAllCart")
	public String delAllCart(@RequestParam("check") ArrayList<String> list) {
		service.delAllCart(list);
		return "redirect:../loginCheck/CartList";
	}
	
	@RequestMapping(value = "loginCheck/orderConfirm")
	public String orderConfirm(HttpSession session, @RequestParam("num") int num, RedirectAttributes xxx) {
		MemberDTO Mdto = (MemberDTO) session.getAttribute("login");
		String userid = Mdto.getUserid();
		Mdto = memberService.mypage(userid); //사용자 정보 가져오기
		CartDTO Cdto = service.orderConfirmByNum(num); //장바구니 정보 가져오기 
		xxx.addFlashAttribute("Mdto", Mdto); //request에 회원정보 저장
		xxx.addFlashAttribute("Cdto", Cdto); //request에 카트 정보 저장
		return "redirect:../orderConfirm"; //servlet-context에 등록
	} 
	
	@RequestMapping(value = "loginCheck/orderDone")
	public void orderDone(@RequestParam("orderNum") int num, OrderDTO Odto) {
		//Odto를 파싱할 필요가 없음. orderConfirm.jsp에서 변수명도 모두 동일하므로 자동으로 OrderDTO형태로 꽂힘
		System.out.println("num=>" + num);
		System.out.println("Odto=>" + Odto);
	}
	
}
