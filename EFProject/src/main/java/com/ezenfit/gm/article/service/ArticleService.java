package com.ezenfit.gm.article.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ezenfit.gm.vo.ArticleReplyVO;
import com.ezenfit.gm.vo.ArticleVO;
import com.ezenfit.gm.vo.Criteria;


//-----------------------------------------------------------------------------------------------------------
// public interface ArticleService
//-----------------------------------------------------------------------------------------------------------
public interface ArticleService {
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의하기 목록 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------
	public List<ArticleVO> inquiryList(Criteria cri) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의하기 리스트 카운트
	//-----------------------------------------------------------------------------------------------------------		
	public int listCount() throws DataAccessException;
			
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 작성하기
	//-----------------------------------------------------------------------------------------------------------
	public int addInquiry(ArticleVO articleVO) throws DataAccessException;
		
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 게시글현황 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------
	public ArticleVO inqSituation(int ef_i_no) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// 답글 현황 리스트 불러오기
	//-----------------------------------------------------------------------------------------------------------
	public List<ArticleReplyVO> replyList(int ef_i_no) throws DataAccessException;
		
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 수정반영하기
	//-----------------------------------------------------------------------------------------------------------
	public int updateReflect(ArticleVO articleVO) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	public int deleteInquiry(int ef_i_no) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// 답글반영하기
	//-----------------------------------------------------------------------------------------------------------
	public int addReply(ArticleReplyVO articleReplyVO) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// 이미지 파일 명 imgInquiry ==> 안겹치게 하기 위해서
	//-----------------------------------------------------------------------------------------------------------
	public int imgContact(String ef_imageFileName) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// 답글 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	public int deleteReply(int ef_comment) throws DataAccessException;
			
	
} // End - public interface ArticleService
