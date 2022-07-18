package com.ezenfit.gm.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ezenfit.gm.vo.PtVO;
import com.ezenfit.gm.vo.PtreVO;
import com.ezenfit.gm.vo.ReservationVO;
import com.ezenfit.gm.vo.MemregiVO;

//-----------------------------------------------------------------------------------------------------------
// public class MemberDAOImpl implements MemberDAO
//-----------------------------------------------------------------------------------------------------------
@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	//-----------------------------------------------------------------------------------------------------------
	// pt정보 달력에 가져오기 회원의 강의종류(ef_p_type)
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public MemregiVO memberRegi(String ef_id) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.member.memberRegi", ef_id);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 예약한 pt리스트, 나의 예약 현황에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> reservationPtlist(ReservationVO reservationVO) throws DataAccessException {
		List<PtVO> reservationPtlist = sqlSession.selectList("mapper.member.reservationPtlist", reservationVO);
		return reservationPtlist;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// pt정보 달력에 가져오기(회원)
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> findPt2(PtVO ptVO) throws DataAccessException {
		List<PtVO> findPt2 = sqlSession.selectList("mapper.member.findPt2", ptVO);
		return findPt2;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 중복 예약 방지
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int countReservation(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("countReservation DAO ==> " + reservationVO);
		int result = sqlSession.selectOne("mapper.member.countReservation", reservationVO);
		System.out.println("countReservation DAO 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약 카운트 (같은 날 같은 타임에 여러개 강의 예약 방지)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int reservationCount(PtreVO ptreVO) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.member.reservationCount", ptreVO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 예약하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int insertReservation(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("insertReservation DAO ==> " + reservationVO);
		int result = sqlSession.insert("mapper.member.insertReservation", reservationVO);
		System.out.println("insertReservation DAO 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약인원 1증가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int r_personalOnePlus(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("r_personalOnePlus DAO ==> " + reservationVO);
		int result = sqlSession.update("mapper.member.r_personalOnePlus", reservationVO);
		System.out.println("r_personalOnePlus DAO 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 참여가능횟수 1감소
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int countOneMiuns(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("countOneMiuns DAO ==> " + reservationVO);
		int result = sqlSession.update("mapper.member.countOneMiuns", reservationVO);
		System.out.println("countOneMiuns DAO 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약 취소하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int deleteReservation(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("deleteReservation DAO ==> " + reservationVO);
		int result = sqlSession.delete("mapper.member.deleteReservation", reservationVO);
		System.out.println("deleteReservation DAO 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약인원 1감소
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int r_personalOneMinus(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("r_personalOneMinus DAO ==> " + reservationVO);
		int result = sqlSession.update("mapper.member.r_personalOneMinus", reservationVO);
		System.out.println("r_personalOneMinus DAO 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 참여가능횟수 1증가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int countOnePlus(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("countOnePlus DAO ==> " + reservationVO);
		int result = sqlSession.update("mapper.member.countOnePlus", reservationVO);
		System.out.println("countOnePlus DAO 결과 ==> " + result);
		return result;
	}
	

	
} // End - public class MemberDAOImpl implements MemberDAO
