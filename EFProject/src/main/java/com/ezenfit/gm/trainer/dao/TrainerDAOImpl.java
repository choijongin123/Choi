package com.ezenfit.gm.trainer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ezenfit.gm.vo.PtVO;
import com.ezenfit.gm.vo.ReservationVO;
import com.ezenfit.gm.vo.MemberVO;

//-----------------------------------------------------------------------------------------------------------
// public class MemberDAOImpl implements MemberDAO
//-----------------------------------------------------------------------------------------------------------
@Repository("trainerDAO")
public class TrainerDAOImpl implements TrainerDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(TrainerDAOImpl.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	//-----------------------------------------------------------------------------------------------------------
	// pt정보 달력에 가져오기(강사)
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> findPt(String ef_id) throws DataAccessException {
		List<PtVO> findPt = sqlSession.selectList("mapper.trainer.findPt", ef_id);
		return findPt;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// pt리스트 강의현황에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> ptList(MemberVO memberVO) throws DataAccessException {
		List<PtVO> ptList = sqlSession.selectList("mapper.trainer.ptList", memberVO);
		return ptList;
	}

	//-----------------------------------------------------------------------------------------------------------
	// PT 추가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addPt(PtVO ptVO) throws DataAccessException {
		System.out.println("ptVO DAO ==> " + ptVO);
		int result = sqlSession.insert("mapper.trainer.addPt", ptVO);
		System.out.println("ptVO DAO 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 삭제
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int deletePt(PtVO ptVO) throws DataAccessException {
		System.out.println("ptVO DAO ==> " + ptVO);
		int result = sqlSession.insert("mapper.trainer.deletePt", ptVO);
		System.out.println("ptVO DAO 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 수정
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public int updatePt(PtVO ptVO) throws DataAccessException {
		
		return sqlSession.update("mapper.trainer.updatePt", ptVO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// PT 예약현황 보기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<ReservationVO> reservationList(int ef_p_no) throws DataAccessException {
		List<ReservationVO> reservationList = sqlSession.selectList("mapper.trainer.reservationList", ef_p_no);
		return reservationList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	//  PT 강의이력 (오늘전까지)
	//-----------------------------------------------------------------------------------------------------------
	public List<PtVO> ptHistory(String ef_id) throws DataAccessException {
		List<PtVO> ptHistory = sqlSession.selectList("mapper.trainer.ptHistory", ef_id);
		return ptHistory;
	}


} // End - public class MemberDAOImpl implements MemberDAO
