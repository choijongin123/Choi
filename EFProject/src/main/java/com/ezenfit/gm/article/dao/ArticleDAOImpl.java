package com.ezenfit.gm.article.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ezenfit.gm.vo.ArticleReplyVO;
import com.ezenfit.gm.vo.ArticleVO;
import com.ezenfit.gm.vo.Criteria;

//-----------------------------------------------------------------------------------------------------------
// public class ArticleDAOImpl implements ArticleDAO
//-----------------------------------------------------------------------------------------------------------
@Repository("articleDAO")
public class ArticleDAOImpl implements ArticleDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ArticleDAOImpl.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의하기 목록 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------				
	@Override
	public List<ArticleVO> inquiryList(Criteria cri) throws DataAccessException {
		List<ArticleVO> inquiryList = sqlSession.selectList("mapper.article.inquiryList", cri);		
		return inquiryList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의하기 리스트 카운트
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public int listCount() throws DataAccessException {
		
		return sqlSession.selectOne("mapper.article.listCount");
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 작성하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addInquiry(ArticleVO articleVO) throws DataAccessException {
		System.out.println("daoVO ==>" + articleVO);
		sqlSession.insert("mapper.article.addInquiry", articleVO);	
		System.out.println("aaaaaa111" + articleVO.getEf_i_no());
		return 	articleVO.getEf_i_no(); 	
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 게시글현황 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public ArticleVO inqSituation(int ef_i_no) throws DataAccessException {
		System.out.println("daoVO ==>" + ef_i_no);
		
		return sqlSession.selectOne("mapper.article.inqSituation", ef_i_no);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 답글 현황 리스트 불러오기
	//-----------------------------------------------------------------------------------------------------------				
	@Override
	public List<ArticleReplyVO> replyList(int ef_i_no) throws DataAccessException {
		System.out.println("daoVO ==>" + ef_i_no);
		
		List<ArticleReplyVO> replyList = sqlSession.selectList("mapper.article.replyList", ef_i_no);		
		return replyList;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 수정반영하기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int updateReflect(ArticleVO articleVO) throws DataAccessException {	
		System.out.println("updateReflect DAO ==> " + articleVO);
		return sqlSession.update("mapper.article.updateReflect", articleVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int deleteInquiry(int ef_i_no) throws DataAccessException {	
		System.out.println("deleteInquiry DAO ==> " + ef_i_no);
		return sqlSession.delete("mapper.article.deleteInquiry", ef_i_no);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 답글반영하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addReply(ArticleReplyVO articleReplyVO) throws DataAccessException {
		System.out.println("daoVO ==>" + articleReplyVO);
		return sqlSession.insert("mapper.article.addReply", articleReplyVO); 
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 이미지 파일 명 imgInquiry ==> 안겹치게 하기 위해서
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int imgContact(String ef_imageFileName) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.article.imgContact", ef_imageFileName);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 답글 삭제하기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public int deleteReply(int ef_comment) throws DataAccessException {
		
		System.out.println("deleteReply DAO ==> " + ef_comment);
		return sqlSession.delete("mapper.article.deleteReply", ef_comment);
	}
	
	

} // End - public class ArticleDAOImpl implements ArticleDAO
