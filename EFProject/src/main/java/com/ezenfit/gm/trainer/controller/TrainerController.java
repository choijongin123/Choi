package com.ezenfit.gm.trainer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezenfit.gm.vo.PtVO;

//-----------------------------------------------------------------------------------------------------------
// public interface MemberController
//-----------------------------------------------------------------------------------------------------------
public interface TrainerController {
	
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
	

		
} // End - public interface MemberController
