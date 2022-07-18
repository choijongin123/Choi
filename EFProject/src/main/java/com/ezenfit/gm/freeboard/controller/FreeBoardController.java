package com.ezenfit.gm.freeboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezenfit.gm.vo.FreeBoardCommentVO;
import com.ezenfit.gm.vo.FreeBoardVO;

//-----------------------------------------------------------------------------------------------------------
// public interface FreeBoardController
//-----------------------------------------------------------------------------------------------------------
public interface FreeBoardController {

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 추가
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView addPosts(@ModelAttribute("FreeBoardVO") FreeBoardVO freeBoardVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 보기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView freeBoardViewPosts(@RequestParam("freeBoard_no") int freeBoard_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 수정 하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView modPosts(@ModelAttribute("FreeBoardVO") FreeBoardVO freeBoardVO, HttpServletRequest request, HttpServletResponse response) throws Exception;

	//-----------------------------------------------------------------------------------------------------------
	// 댓글 추가하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView addComments(@ModelAttribute("FreeBoardCommentVO") FreeBoardCommentVO freeBoardCommentVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 답글 추가하기
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView addReply(@ModelAttribute("FreeBoardCommentVO") FreeBoardCommentVO freeBoardCommentVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
} // End - public interface FreeBoardController
