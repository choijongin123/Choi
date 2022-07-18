package com.ezenfit.gm.freeboard.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ezenfit.gm.freeboard.service.FreeBoardService;
import com.ezenfit.gm.vo.Criteria;
import com.ezenfit.gm.vo.FreeBoardCommentVO;
import com.ezenfit.gm.vo.FreeBoardVO;

//-----------------------------------------------------------------------------------------------------------
// public class FreeBoardDAOImpl implements FreeBoardDAO
//-----------------------------------------------------------------------------------------------------------
@Repository("freeBoardDAO")
public class FreeBoardDAOImpl implements FreeBoardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	//-----------------------------------------------------------------------------------------------------------
	// 자유게시판(freeboard)리스트 가져오기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public List<FreeBoardVO> freeBoardList(Criteria cri) throws DataAccessException {
		List<FreeBoardVO> freeBoardList = sqlSession.selectList("mapper.freeboard.freeBoardList", cri);
		return freeBoardList;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 자유게시판 리스트 카운트
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public int listCount() throws DataAccessException {
		
		return sqlSession.selectOne("mapper.freeboard.freeBoardCount");
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 추가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addPosts(FreeBoardVO freeBoardVO) throws DataAccessException {
		System.out.println("freeBoardVO DAO ==> " + freeBoardVO);
		int result = sqlSession.insert("mapper.freeboard.addPosts", freeBoardVO);
		// System.out.println("addPosts DAO 결과 ==> " + result + ", " + freeBoardVO.getFreeBoard_no());
		result = freeBoardVO.getFreeBoard_no();
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 보기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public FreeBoardVO freeBoardViewPosts(int freeBoard_no) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.freeboard.freeBoardViewPosts", freeBoard_no);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 자유게시판(freeboard) 댓글리스트 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<FreeBoardCommentVO> freeBoardViewCommentsList(int freeBoard_no) throws DataAccessException {
		List<FreeBoardCommentVO> freeBoardViewCommentsList = sqlSession.selectList("mapper.freeboard.freeBoardViewCommentsList", freeBoard_no);
		return freeBoardViewCommentsList;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 수정 하기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public int modPosts(FreeBoardVO freeBoardVO) throws DataAccessException {
		
		return sqlSession.update("mapper.freeboard.modPosts", freeBoardVO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 삭제
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int freeBoardDeletePosts(int freeBoard_no) throws DataAccessException {
		System.out.println("freeBoardDeletePosts DAO ==> " + freeBoard_no);
		int result = sqlSession.delete("mapper.freeboard.freeBoardDeletePosts", freeBoard_no);
		System.out.println("freeBoardDeletePosts DAO 결과 ==> " + result);
		return sqlSession.delete("mapper.freeboard.commentDeletePosts", freeBoard_no);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 댓글 추가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addComments(FreeBoardCommentVO freeBoardCommentVO) throws DataAccessException {
		//System.out.println("addComments DAO ==> " + freeBoardCommentVO);
		int result = sqlSession.insert("mapper.freeboard.addComments", freeBoardCommentVO);
		//System.out.println("addPaddCommentsosts DAO 결과 ==> " + result);
		if(freeBoardCommentVO.getFreeBoard_commentLevel().equals("0")) { // 댓글 추가시 게시판 테이블 댓글 카운트 증가
			sqlSession.update("mapper.freeboard.commentCount", freeBoardCommentVO);
		} else { // 대댓글 추가시 댓글 테이블 대댓글 카운트 증가
			sqlSession.update("mapper.freeboard.reCommentCount", freeBoardCommentVO);
		}
		
		return result;
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// 페이지 이동할 때 필요한 게시글 번호를 가여져오기 위함
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public FreeBoardCommentVO fbcvo(int freeBoard_commentNO) throws DataAccessException {
		System.out.println("게시글 번호를 가져오기 위함 ==> " + freeBoard_commentNO);
		return sqlSession.selectOne("mapper.freeboard.fbcvo", freeBoard_commentNO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 댓글 삭제
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int deleteComments(FreeBoardCommentVO freeBoardCommentVO) throws DataAccessException {
		System.out.println("deleteComments Service ==> " + freeBoardCommentVO);
		if(freeBoardCommentVO.getFreeBoard_commentLevel().equals("0")) { // 댓글 삭제시 게시판 테이블 댓글 카운트 변경
			sqlSession.update("mapper.freeboard.delCommentCount", freeBoardCommentVO);
		} else { // 대댓글 삭제시 댓글 테이블 대댓글 카운트 변경
			sqlSession.update("mapper.freeboard.delreCommentCount", freeBoardCommentVO);
		}
		
		return sqlSession.delete("mapper.freeboard.deleteComments", freeBoardCommentVO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 답글 리스트 불러오기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public List<FreeBoardCommentVO> replyList(int freeBoard_commentParentsNO) throws DataAccessException {
		List<FreeBoardCommentVO> replyList = sqlSession.selectList("mapper.freeboard.replyList", freeBoard_commentParentsNO);
		return replyList;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 이미지 파일 명 count ==> 안겹치게 하기 위해서
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int imgCount(String freeBoard_imageFileName) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.freeboard.imgCount", freeBoard_imageFileName);
	}
	
	

} // End - public class FreeBoardDAOImpl implements FreeBoardDAO
