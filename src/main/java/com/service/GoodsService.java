package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.GoodsDAO;
import com.dto.CartDTO;
import com.dto.GoodsDTO;

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

}
