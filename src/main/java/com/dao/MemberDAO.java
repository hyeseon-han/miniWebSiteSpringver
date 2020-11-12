package com.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MemberDTO;

@Repository
public class MemberDAO {
	@Autowired
	SqlSessionTemplate template; //자동주입
	
	public int memberAdd(MemberDTO m) {
		int n = template.insert("MemberMapper.memberAdd", m);
		return n;
	}
	
	public MemberDTO login(Map<String,String> map) {
		MemberDTO dto = template.selectOne("MemberMapper.login", map);
		return dto;
	}
	
	public MemberDTO mypage(String userid) {
		MemberDTO dto = template.selectOne("MemberMapper.mypage", userid);
		return dto;
	}
	
	public int memberUpdate(MemberDTO m) {
		int n = template.update("MemberMapper.memberUpdate", m);
		return n;
	}
}
