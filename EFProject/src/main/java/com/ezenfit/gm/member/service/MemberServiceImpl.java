package com.ezenfit.gm.member.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ezenfit.gm.vo.MemregiVO;
import com.ezenfit.gm.member.dao.MemberDAO;   
import com.ezenfit.gm.vo.PtVO;
import com.ezenfit.gm.vo.PtreVO;
import com.ezenfit.gm.vo.ReservationVO;
//-----------------------------------------------------------------------------------------------------------
// public class MemberService implements MemberService
//-----------------------------------------------------------------------------------------------------------
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private ReservationVO reservationVO;
	
	@Autowired
	private MemregiVO memregiVO;
	
	@Autowired
	private PtVO ptVO;
	
	//-----------------------------------------------------------------------------------------------------------
	// pt정보 달력에 가져오기 회원의 강의종류(ef_p_type)
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public MemregiVO memberRegi(String ef_id) throws DataAccessException {
		
		return memberDAO.memberRegi(ef_id);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약한 pt리스트, 나의 예약 현황에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> reservationPtlist(ReservationVO reservationVO) throws DataAccessException {
		List<PtVO> reservationPtlist = memberDAO.reservationPtlist(reservationVO);
		return reservationPtlist;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// pt정보 달력에 가져오기(회원)
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> findPt2(PtVO ptVO) throws DataAccessException {
		List<PtVO> findPt2 = memberDAO.findPt2(ptVO);
		return findPt2;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 중복 예약 방지
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int countReservation(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("countReservation Service 결과 ==> " + reservationVO);
		return memberDAO.countReservation(reservationVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약 카운트 (같은 날 같은 타임에 여러개 강의 예약 방지)
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public int reservationCount(PtreVO ptreVO) throws DataAccessException {
		
		return memberDAO.reservationCount(ptreVO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 예약하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int insertReservation(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("insertReservation Service 결과 ==> " + reservationVO);
		return memberDAO.insertReservation(reservationVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약인원 1증가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int r_personalOnePlus(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("r_personalOnePlus Service 결과 ==> " + reservationVO);
		return memberDAO.r_personalOnePlus(reservationVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 참여가능횟수 1감소
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int countOneMiuns(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("countOneMiuns Service 결과 ==> " + reservationVO);
		return memberDAO.countOneMiuns(reservationVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약 취소하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int deleteReservation(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("deleteReservation Service 결과 ==> " + reservationVO);
		return memberDAO.deleteReservation(reservationVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약인원 1감소
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int r_personalOneMinus(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("r_personalOneMinus Service 결과 ==> " + reservationVO);
		return memberDAO.r_personalOneMinus(reservationVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 참여가능횟수 1증가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int countOnePlus(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("countOnePlus Service 결과 ==> " + reservationVO);
		return memberDAO.countOnePlus(reservationVO);
	}
	

	
} // End - public class TrainerServiceImpl implements MemberService
