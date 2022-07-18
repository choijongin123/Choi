package com.ezenfit.gm.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezenfit.gm.vo.CenterVO;
import com.ezenfit.gm.vo.MemberVO;



//-----------------------------------------------------------------------------------------------------------
//public interface UserController
//-----------------------------------------------------------------------------------------------------------
public interface UserController {
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 폼 불러오기
	// parameter는 브라우저(client)에서 만들어진 정보이고,	
	//	attribute는 servlet(server)에서 만들어진 정보이다.
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView memberForm(@RequestParam("ef_class") String ef_class,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView addUser(@ModelAttribute("info") MemberVO memberVO, CenterVO centerVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView login(@ModelAttribute("member") MemberVO member,
			RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 정보 수정 하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView updateInfo(@ModelAttribute("member") MemberVO memberVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

}
