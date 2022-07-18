package com.ezenfit.gm.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


//-----------------------------------------------------------------------------------------------------------
// public interface MemberController
//-----------------------------------------------------------------------------------------------------------
public interface MemberController {
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView insertReservation(@RequestParam("ef_p_no") int ef_p_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약 취소하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView deleteReservation(@RequestParam("ef_p_no") int ef_p_no, @RequestParam("ef_id") String ef_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
		
		
} // End - public interface MemberController
