package com.ezenfit.gm.owner.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezenfit.gm.vo.MemregiVO;
import com.ezenfit.gm.vo.RecordVO;



//-----------------------------------------------------------------------------------------------------------
//public interface OwnerController
//-----------------------------------------------------------------------------------------------------------
public interface OwnerController {
	
	//-----------------------------------------------------------------------------------------------------------
	// 오너 페이지 에서 미등록 강사, 회원 등록하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView registUser(@RequestParam("ef_id") String ef_id, @RequestParam("ef_class") String ef_class, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 수강 등록 및 수정 페이지
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView courseRegistForm(@RequestParam("ef_id") String ef_id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	//-----------------------------------------------------------------------------------------------------------
	// 수강 등록 및 수정 하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView courseRegist(@ModelAttribute("memregi") MemregiVO memregiVO, @ModelAttribute("record") RecordVO recordVO, HttpServletRequest request, HttpServletResponse response) throws Exception;

	//-----------------------------------------------------------------------------------------------------------
	// 회원기록 페이지 record 삭제
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView memRecordDelete(@RequestParam("ef_r_time") String ef_r_time, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
