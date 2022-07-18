package com.ezenfit.gm.article.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezenfit.gm.vo.ArticleReplyVO;
import com.ezenfit.gm.vo.ArticleVO;

//-----------------------------------------------------------------------------------------------------------
// public interface ArticleController
//-----------------------------------------------------------------------------------------------------------
public interface ArticleController {
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 작성하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView addInquiry(@ModelAttribute("articleVO") ArticleVO articleVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
		
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 게시글현황 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView inqSituation(@RequestParam("ef_i_no") int ef_i_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 수정반영하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView updateReflect(@ModelAttribute("articleVO") ArticleVO articleVO, 
			HttpServletRequest request, HttpServletResponse response) throws Exception;		
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView deleteInquiry(@RequestParam("ef_i_no") int ef_i_no,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 답글반영하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView addReply(@ModelAttribute("articleReplyVO") ArticleReplyVO articleReplyVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 답글 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView deleteReply(@RequestParam("ef_i_no") int ef_i_no, @RequestParam("ef_comment") int ef_comment,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
		
		
} // End - public interface ArticleController
