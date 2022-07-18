package com.ezenfit.gm.owner.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ezenfit.gm.vo.JoinrecordVO;
import com.ezenfit.gm.vo.JoinregiVO;
import com.ezenfit.gm.vo.MemberVO;
import com.ezenfit.gm.vo.MemregiVO;
import com.ezenfit.gm.vo.PtVO;
import com.ezenfit.gm.vo.RecordVO;



//-----------------------------------------------------------------------------------------------------------
//public class OwnerDAOImpl implements OwnerDAO
//-----------------------------------------------------------------------------------------------------------
@Repository("ownerDAO")
public class OwnerDAOImpl implements OwnerDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	//-----------------------------------------------------------------------------------------------------------
	// 승인 관리 페이지 미등록 강사 목록 조회 
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<MemberVO> unRegiListTrainers(MemberVO memberVO) throws DataAccessException {
		List<MemberVO> unRegiListTrainers = sqlSession.selectList("mapper.owner.unRegiListTrainers", memberVO);  
		return unRegiListTrainers;
	}
	//-----------------------------------------------------------------------------------------------------------
	// 승인 관리 페이지 미등록 회원 목록 조회 
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<MemberVO> unRegiListMembers(MemberVO memberVO) throws DataAccessException {
		List<MemberVO> unRegiListMembers = sqlSession.selectList("mapper.owner.unRegiListMembers", memberVO); 
		return unRegiListMembers;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 오너 페이지 에서 미등록 강사, 회원 등록하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int registUser(String ef_id) throws DataAccessException {
		
		return sqlSession.update("mapper.owner.registUser", ef_id);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 오너 페이지 에서 미등록 강사, 회원 등록 시 m_regi 테이블에 col생성
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int insertRegi(String ef_id) throws DataAccessException {
		
		return sqlSession.insert("mapper.owner.insertRegi", ef_id);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 인원 관리 페이지 등록 강사 목록 조회
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<MemberVO> regiListTrainers(MemberVO memberVO) throws DataAccessException {
		List<MemberVO> regiListTrainers = sqlSession.selectList("mapper.owner.regiListTrainers", memberVO); 
		return regiListTrainers;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 인원 관리 페이지 등록 회원 목록 조회
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<JoinregiVO> regiListMembers(MemberVO memberVO) throws DataAccessException {
		List<JoinregiVO> regiListMembers = sqlSession.selectList("mapper.owner.regiListMembers", memberVO);
		return regiListMembers;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 인원 관리 페이지 강사 탈퇴시 개설 강의 있는지 카운트
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int trainerCount(String ef_id) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.owner.trainerCount", ef_id);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 인원 관리 페이지 회원 탈퇴시 예약 강의 있는지 카운트
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int memberCount(String ef_id) throws DataAccessException {
	
		return sqlSession.selectOne("mapper.owner.memberCount", ef_id);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 인원 관리 페이지 강사 탈퇴, (게시글 댓글 답글) 작성자를 '탈퇴된 계정'으로 변경
	//-----------------------------------------------------------------------------------------------------------
    @Override
    public int delete(String ef_id) throws DataAccessException {
       int result1 = sqlSession.delete("mapper.owner.updateBoardWriter", ef_id);
       int result2 = sqlSession.delete("mapper.owner.updateCommentWriter", ef_id);
       return sqlSession.delete("mapper.owner.delete", ef_id);
    }
   
   //-----------------------------------------------------------------------------------------------------------
   // 인원 관리 페이지 회원 탈퇴, 탈퇴시 regi 삭제, (게시글 댓글 답글) 작성자를 '탈퇴된 계정'으로 변경
   //-----------------------------------------------------------------------------------------------------------
   @Override
   public int regidelete(String ef_id) throws DataAccessException {
      int result1 = sqlSession.delete("mapper.owner.delete", ef_id);
      int result2 = sqlSession.delete("mapper.owner.updateBoardWriter", ef_id);
      int result3 = sqlSession.delete("mapper.owner.updateCommentWriter", ef_id);
      return sqlSession.delete("mapper.owner.regidelete", ef_id);
   }
	
	//-----------------------------------------------------------------------------------------------------------
	// 수강 등록 및 수정 페이지
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public MemregiVO courseRegistForm(String ef_id) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.owner.courseRegistForm", ef_id);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 수강 등록 및 수정 하기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public int courseRegist(MemregiVO memregiVO) throws DataAccessException {
		
		return sqlSession.update("mapper.owner.courseRegist", memregiVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 수강 등록 및 수정시 record 테이블에 기록 추가
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public int insertRecord(RecordVO recordVO) throws DataAccessException {
		
		return sqlSession.insert("mapper.owner.insertRecord", recordVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// pt정보 오너 달력에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> findPt3(PtVO ptVO) throws DataAccessException {
		System.out.println("findPt3 ptVO DAO ==> " + ptVO);
		List<PtVO> findPt3 = sqlSession.selectList("mapper.owner.findPt3", ptVO);
		System.out.println("findPt3 ptVO결과 DAO ==> " + findPt3);
		return findPt3;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 모든 pt리스트 오너 강의현황에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> ptAllList(MemberVO memberVO) throws DataAccessException {
		List<PtVO> ptAllList = sqlSession.selectList("mapper.owner.ptAllList", memberVO);
		return ptAllList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원기록 페이지 ==> ef_record 리스트 불러오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<JoinrecordVO> memRecord() throws DataAccessException {
		List<JoinrecordVO> memRecord = sqlSession.selectList("mapper.owner.memRecord");
		return memRecord;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원기록 페이지 record 삭제
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int memRecordDelete(String ef_r_time) throws DataAccessException {
		
		return sqlSession.update("mapper.owner.memRecordDelete", ef_r_time);
	}
	
	
	
	
} // End - public class OwnerDAOImpl
