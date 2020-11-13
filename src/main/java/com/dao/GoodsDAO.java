package com.dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.CartDTO;
import com.dto.GoodsDTO;

@Repository
public class GoodsDAO {
	@Autowired
	SqlSessionTemplate template; 
	
	public List<GoodsDTO> goodsList(String gCategory){
		List<GoodsDTO> list = template.selectList("GoodsMapper.goodsList",gCategory);
		return list;
	}
	
	public GoodsDTO goodRetrieve(String gCode) {
		GoodsDTO dto = template.selectOne("GoodsMapper.goodsRetrieve",gCode);
		return dto;
	}
	
	public int cartAdd(CartDTO dto) {
		int n = template.insert("CartMapper.cartAdd",dto);
		return n;
	}
	
	public List<CartDTO> cartList(String userid) {
		List<CartDTO> list = template.selectList("CartMapper.cartList", userid);
		return list;
	}
}
