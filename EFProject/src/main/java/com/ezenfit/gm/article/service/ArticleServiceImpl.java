package com.ezenfit.gm.article.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ezenfit.gm.article.dao.ArticleDAO;
import com.ezenfit.gm.freeboard.dao.FreeBoardDAO;
import com.ezenfit.gm.vo.ArticleReplyVO;
import com.ezenfit.gm.vo.ArticleVO;
import com.ezenfit.gm.vo.Criteria;
import com.ezenfit.gm.vo.FreeBoardVO;


//-----------------------------------------------------------------------------------------------------------
// public class ArticleServiceImpl implements ArticleService
//-----------------------------------------------------------------------------------------------------------
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
	
	private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
	
	
	@Autowired
	private ArticleDAO articleDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의하기 목록 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<ArticleVO> inquiryList(Criteria cri) throws DataAccessException {
		List<ArticleVO> inquiryList = articleDAO.inquiryList(cri);		
		return inquiryList;
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의하기 리스트 카운트
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public int listCount() throws DataAccessException {
		
		return articleDAO.listCount();
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 작성하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addInquiry(ArticleVO articleVO) throws DataAccessException {
		System.out.println("serviceVO ==>" + articleVO);
		return articleDAO.addInquiry(articleVO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 게시글현황 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public ArticleVO inqSituation(int ef_i_no) throws DataAccessException {
		System.out.println("serviceVO ==>" + ef_i_no);	
		return articleDAO.inqSituation(ef_i_no);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 답글 현황 리스트 불러오기
	//-----------------------------------------------------------------------------------------------------------			
	@Override
	public List<ArticleReplyVO> replyList(int ef_i_no) throws DataAccessException {
		System.out.println("serviceVO ==>" + ef_i_no);	
		
		List<ArticleReplyVO> replyList = articleDAO.replyList(ef_i_no);		
		return replyList;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 수정반영하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int updateReflect(ArticleVO articleVO) throws DataAccessException {
	
		return articleDAO.updateReflect(articleVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int deleteInquiry(int ef_i_no) throws DataAccessException {
		
		return articleDAO.deleteInquiry(ef_i_no);	
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 답글반영하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addReply(ArticleReplyVO articleReplyVO) throws DataAccessException {
		System.out.println("serviceVO ==>" + articleReplyVO);
		return articleDAO.addReply(articleReplyVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 이미지 파일 명 imgInquiry ==> 안겹치게 하기 위해서
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int imgContact(String ef_imageFileName) throws DataAccessException {
		
		return articleDAO.imgContact(ef_imageFileName);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 답글 삭제하기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int deleteReply(int ef_comment) throws DataAccessException {
		
		return articleDAO.deleteReply(ef_comment);
	}
	
	
	
	
} // End - public class ArticleServiceImpl implements ArticleService
