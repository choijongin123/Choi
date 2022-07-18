package com.ezenfit.gm.trainer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ezenfit.gm.trainer.dao.TrainerDAO;
import com.ezenfit.gm.vo.CenterVO;
import com.ezenfit.gm.vo.MemberVO;
import com.ezenfit.gm.vo.PtVO;
import com.ezenfit.gm.vo.ReservationVO;
//-----------------------------------------------------------------------------------------------------------
// public class TrainerServiceImpl implements TrainerService
//-----------------------------------------------------------------------------------------------------------
@Service("trainerService")
public class TrainerServiceImpl implements TrainerService {
	private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);
	
	@Autowired
	private TrainerDAO trainerDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// pt정보 달력에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> findPt(String ef_id) throws DataAccessException {
		List<PtVO> findPt = trainerDAO.findPt(ef_id);
		return findPt;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// pt리스트 강의현황에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> ptList(MemberVO memberVO) throws DataAccessException {
		List<PtVO> ptList = trainerDAO.ptList(memberVO);
		return ptList;
	}

	//-----------------------------------------------------------------------------------------------------------
	// PT 추가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addPt(PtVO ptVO) throws DataAccessException {
		System.out.println("ptVO Service 결과 ==> " + ptVO);
		return trainerDAO.addPt(ptVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 삭제
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int deletePt(PtVO ptVO) throws DataAccessException {
		System.out.println("ptVO Service 결과 ==> " + ptVO);
		return trainerDAO.deletePt(ptVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 수정
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int updatePt(PtVO ptVO) throws DataAccessException {
		System.out.println("ptVO updatePt Service 결과 ==> " + ptVO);
		return trainerDAO.updatePt(ptVO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// PT 예약현황 보기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public List<ReservationVO> reservationList(int ef_p_no) throws DataAccessException {
		List<ReservationVO> reservationList = trainerDAO.reservationList(ef_p_no);
		return reservationList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 강의이력 (오늘전까지)
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public List<PtVO> ptHistory(String ef_id) throws DataAccessException {
		List<PtVO> ptHistory = trainerDAO.ptHistory(ef_id);
		return ptHistory;
	}
	

	
} // End - public class TrainerServiceImpl implements TrainerService
