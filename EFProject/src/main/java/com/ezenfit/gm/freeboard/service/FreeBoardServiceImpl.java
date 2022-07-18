package com.ezenfit.gm.freeboard.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ezenfit.gm.freeboard.dao.FreeBoardDAO;
import com.ezenfit.gm.vo.Criteria;
import com.ezenfit.gm.vo.FreeBoardCommentVO;
import com.ezenfit.gm.vo.FreeBoardVO;


//-----------------------------------------------------------------------------------------------------------
// public class FreeBoardServiceImpl implements FreeBoardService
//-----------------------------------------------------------------------------------------------------------
@Service("freeBoardService")
public class FreeBoardServiceImpl implements FreeBoardService {

	@Autowired
	private FreeBoardDAO freeBoardDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 자유게시판(freeboard)리스트 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<FreeBoardVO> freeBoardList(Criteria cri) throws DataAccessException {
		List<FreeBoardVO> freeBoardList = freeBoardDAO.freeBoardList(cri);
		return freeBoardList;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 자유게시판 리스트 카운트
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public int listCount() throws DataAccessException {
		
		return freeBoardDAO.listCount();
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 추가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addPosts(FreeBoardVO freeBoardVO) throws DataAccessException {
		System.out.println("freeBoardVO Service ==> " + freeBoardVO);
		return freeBoardDAO.addPosts(freeBoardVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 보기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public FreeBoardVO freeBoardViewPosts(int freeBoard_no) throws DataAccessException {
		
		return freeBoardDAO.freeBoardViewPosts(freeBoard_no);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 자유게시판(freeboard) 댓글리스트 가져오기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public List<FreeBoardCommentVO> freeBoardViewCommentsList(int freeBoard_no) throws DataAccessException {
		List<FreeBoardCommentVO> freeBoardViewCommentsList = freeBoardDAO.freeBoardViewCommentsList(freeBoard_no);
		return freeBoardViewCommentsList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 수정 하기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public int modPosts(FreeBoardVO freeBoardVO) throws DataAccessException {
		
		return freeBoardDAO.modPosts(freeBoardVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 삭제
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int freeBoardDeletePosts(int freeBoard_no) throws DataAccessException {
		System.out.println("freeBoardVO Service ==> " + freeBoard_no);
		return freeBoardDAO.freeBoardDeletePosts(freeBoard_no);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 댓글 추가
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int addComments(FreeBoardCommentVO freeBoardCommentVO) throws DataAccessException {
		System.out.println("addComments freeBoardCommentVO Service ==> " + freeBoardCommentVO);
		return freeBoardDAO.addComments(freeBoardCommentVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 페이지 이동할 때 필요한 게시글 번호를 가여져오기 위함
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public FreeBoardCommentVO fbcvo(int freeBoard_commentNO) throws DataAccessException {
		System.out.println("게시글 번호를 가져오기 위함 ==> " + freeBoard_commentNO);
		return freeBoardDAO.fbcvo(freeBoard_commentNO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 댓글 삭제
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int deleteComments(FreeBoardCommentVO freeBoardCommentVO) throws DataAccessException {
		System.out.println("deleteComments Service ==> " + freeBoardCommentVO);
		return freeBoardDAO.deleteComments(freeBoardCommentVO); 
	}

	//-----------------------------------------------------------------------------------------------------------
	// 답글 리스트 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<FreeBoardCommentVO> replyList(int freeBoard_commentParentsNO) throws DataAccessException {
		List<FreeBoardCommentVO> replyList = freeBoardDAO.replyList(freeBoard_commentParentsNO);
		return replyList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 이미지 파일 명 count ==> 안겹치게 하기 위해서
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int imgCount(String freeBoard_imageFileName) throws DataAccessException {
		
		return freeBoardDAO.imgCount(freeBoard_imageFileName);
	}

	
	
} // End - public class FreeBoardServiceImpl implements FreeBoardService
