package com.edu.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.member.vo.ArticleVO;
import com.edu.member.vo.CenterVO;
import com.edu.member.vo.MemberVO;
import com.edu.member.vo.MemregiVO;
import com.edu.member.vo.PtVO;
import com.edu.member.vo.RecordVO;

//-----------------------------------------------------------------------------------------------------------
// public interface MemberController
//-----------------------------------------------------------------------------------------------------------
public interface MemberController {
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView addMember(@ModelAttribute("info") MemberVO memberVO, CenterVO centerVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// ID 찾기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView findId(@ModelAttribute("member") MemberVO member,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
			
	//-----------------------------------------------------------------------------------------------------------
	// PWD 찾기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView findPwd(@ModelAttribute("member") MemberVO member,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView login(@ModelAttribute("member") MemberVO member,
			RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	//-----------------------------------------------------------------------------------------------------------
	// 오너 페이지 에서 미등록 강사, 회원 등록하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView registMember(@RequestParam("ef_id") String ef_id, @RequestParam("ef_class") String ef_class, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 정보 수정 하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView myUpdate(@ModelAttribute("member") MemberVO memberVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 인원 관리 페이지 회원 탈퇴
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView delete(@RequestParam("ef_id") String ef_id, @RequestParam("ef_class") String ef_class, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 수강 등록 및 수정 페이지
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView courseRegistForm(@RequestParam("ef_id") String ef_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 수강 등록 및 수정 하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView courseRegist(@ModelAttribute("memregi") MemregiVO memregiVO, @ModelAttribute("record") RecordVO recordVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 추가
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView addPt(@ModelAttribute("PtVO") PtVO ptVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 삭제
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView deletePt(@RequestParam("ef_p_date") String ef_p_date, @RequestParam("ef_p_time") String ef_p_time, 
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 수정
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView updatePt(@ModelAttribute("PtVO") PtVO ptVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView insertReservation(@RequestParam("ef_p_no") int ef_p_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약 취소하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView deleteReservation(@RequestParam("ef_p_no") int ef_p_no, @RequestParam("ef_id") String ef_id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	//-----------------------------------------------------------------------------------------------------------
	// 문의하기 목록 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView inquiryList(@ModelAttribute("articleVO") ArticleVO articleVO, 
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 등록
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView addInquiry(@ModelAttribute("articleVO") ArticleVO articleVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
		


} // End - public interface MemberController
