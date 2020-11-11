package com.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MemberDAO;
import com.dto.MemberDTO;

@Service
public class MemberService {
	@Autowired //자동주입
	MemberDAO dao;
	
	public void memberAdd(MemberDTO m) {//회원가입
		dao.memberAdd(m);
	}
	
	public MemberDTO login(Map<String,String> map) {
		MemberDTO dto = dao.login(map);
		return dto;
	}
	
	public MemberDTO mypage(String userid) {
		MemberDTO dto = dao.mypage(userid);
		return dto;
	}
}
