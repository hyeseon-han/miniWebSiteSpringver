package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.GoodsDAO;
import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.OrderDTO;

@Service
public class GoodsService {
	@Autowired
	GoodsDAO dao;

	public List<GoodsDTO> goodsList(String gCategory) {
		List<GoodsDTO> list = dao.goodsList(gCategory);
		return list;
	}
	
	public GoodsDTO  goodRetrieve(String gCode) {
		GoodsDTO dto = dao.goodRetrieve(gCode);
		return dto;
	}
	
	public int cartAdd(CartDTO dto) {
		int n = dao.cartAdd(dto);
		return n;
	}
	
	public List<CartDTO> cartList(String userid) {
		List<CartDTO> list = dao.cartList(userid);
		return list;
	}
	
	public int cartUpdate(HashMap<String, String> map) {
		int n = dao.cartUpdate(map);
		return n;
	}
	
	public int cartDelete(int num) {
		int n = dao.cartDelete(num);
		return n;
	}
	
	public int delAllCart(ArrayList<String> list) {
		int n = dao.delAllCart(list);
		return n;
	}
	
	public CartDTO orderConfirmByNum(int num) {
		CartDTO dto = dao.orderConfirmByNum(num);
		return dto;
	}
	
   @Transactional
   public void orderDone(OrderDTO Odto, int orderNum) {
      dao.orderDone(Odto); //주문정보 저장 - insert
      dao.cartDelete(orderNum); //카트에서 삭제 두 처리를 tx 처리함  root-context.xml에
      // tx-Manager등록필요
   }

}
