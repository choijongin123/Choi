package com.edu.member.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.edu.member.dao.MemberDAO;
import com.edu.member.vo.ArticleVO;
import com.edu.member.vo.CenterVO;
import com.edu.member.vo.JoinregiVO;
import com.edu.member.vo.MemberVO;
import com.edu.member.vo.MemregiVO;
import com.edu.member.vo.PtVO;
import com.edu.member.vo.RecordVO;
import com.edu.member.vo.ReservationVO;

//-----------------------------------------------------------------------------------------------------------
// public class MemberServiceImpl implements MemberService
//-----------------------------------------------------------------------------------------------------------
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private MemberDAO memberDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {
		System.out.println("serviceVO ==>" + memberVO);
		return memberDAO.addMember(memberVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 센터 등록 처리
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int addCenter(CenterVO centerVO) throws DataAccessException {
		
		return memberDAO.addCenter(centerVO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 업체명 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<CenterVO> listCenter() throws DataAccessException {
		
		return memberDAO.listCenter();
	}

	//-----------------------------------------------------------------------------------------------------------
	// 업체명 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int cNameCheck(CenterVO centerVO) throws DataAccessException {
		
		return memberDAO.cNameCheck(centerVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int idCheck(MemberVO memberVO) throws DataAccessException {
		
		return memberDAO.idCheck(memberVO);
	}
	

	//-----------------------------------------------------------------------------------------------------------
	// ID 찾기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO findId(MemberVO memberVO) throws DataAccessException {
		System.out.println("service id찾기 ==>" + memberVO);
		return memberDAO.findId(memberVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PWD 찾기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO findPwd(MemberVO memberVO) throws DataAccessException {
		System.out.println("service pwd찾기 ==>" + memberVO);
		return memberDAO.findPwd(memberVO);
	}
	//-----------------------------------------------------------------------------------------------------------
	// 로그인
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO login(MemberVO memberVO) throws DataAccessException {
		System.out.println("service 로그인 ==>" + memberVO);
		return memberDAO.login(memberVO);
	}
	//-----------------------------------------------------------------------------------------------------------
	// 미등록 강사 목록 조회 (오너 로그인 처리)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<MemberVO> listMembers1(MemberVO memberVO) throws DataAccessException {
		List<MemberVO> membersList1 = memberDAO.listMembers1(memberVO);
		return membersList1;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 미등록 회원 목록 조회 오너 로그인 처리)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<MemberVO> listMembers2(MemberVO memberVO) throws DataAccessException {
		List<MemberVO> membersList2 = memberDAO.listMembers2(memberVO);
		return membersList2;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 오너 페이지 에서 미등록 강사, 회원 등록하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int registMember(String ef_id) throws DataAccessException {
	
		return memberDAO.registMember(ef_id);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 오너 페이지 에서 미등록 강사, 회원 등록 시 m_regi 테이블에 col생성
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int insertRegi(String ef_id) throws DataAccessException {
		
		return memberDAO.insertRegi(ef_id);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO myPage(MemberVO memberVO) throws DataAccessException {
		
		return memberDAO.myPage(memberVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 정보 수정 하기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int myUpdate(MemberVO memberVO) throws DataAccessException {
	
		return memberDAO.myUpdate(memberVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 등록 강사 목록 조회
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<MemberVO> listMembers3(MemberVO memberVO) throws DataAccessException {
		List<MemberVO> membersList3 = memberDAO.listMembers3(memberVO);
		return membersList3;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 등록 회원 목록 조회
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<JoinregiVO> listMembers4(MemberVO memberVO) throws DataAccessException {
		List<JoinregiVO> membersList4 = memberDAO.listMembers4(memberVO);
		return membersList4;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 인원 관리 페이지 강사 탈퇴
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int delete(String ef_id) throws DataAccessException {
		
		return memberDAO.delete(ef_id);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 인원 관리 페이지 회원 탈퇴
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int regidelete(String ef_id) throws DataAccessException {
		
		return memberDAO.regidelete(ef_id);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 수강 등록 및 수정 페이지
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public MemregiVO courseRegistForm(String ef_id) throws DataAccessException {
		
		return memberDAO.courseRegistForm(ef_id);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 수강 등록 및 수정 하기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public int courseRegist(MemregiVO memregiVO) throws DataAccessException {
		
		return memberDAO.courseRegist(memregiVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 수강 등록 및 수정시 record 테이블에 기록 추가
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public int insertRecord(RecordVO recordVO) throws DataAccessException {
		
		return memberDAO.insertRecord(recordVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// pt정보 달력에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> findPt(String ef_id) throws DataAccessException {
		List<PtVO> findPt = memberDAO.findPt(ef_id);
		return findPt;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// pt리스트 강의현황에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> ptList(MemberVO memberVO) throws DataAccessException {
		List<PtVO> ptList = memberDAO.ptList(memberVO);
		return ptList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 강의이력
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> ptHistory(MemberVO memberVO) throws DataAccessException {
		List<PtVO> ptHistory = memberDAO.ptHistory(memberVO);
		return ptHistory;
	}

	//-----------------------------------------------------------------------------------------------------------
	// PT 추가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addPt(PtVO ptVO) throws DataAccessException {
		System.out.println("ptVO Service 결과 ==> " + ptVO);
		return memberDAO.addPt(ptVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 삭제
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int deletePt(PtVO ptVO) throws DataAccessException {
		System.out.println("ptVO Service 결과 ==> " + ptVO);
		return memberDAO.deletePt(ptVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 수정
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int updatePt(PtVO ptVO) throws DataAccessException {
		System.out.println("ptVO updatePt Service 결과 ==> " + ptVO);
		return memberDAO.updatePt(ptVO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// PT 예약현황 보기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public List<ReservationVO> reservationList(ReservationVO reservationVO) throws DataAccessException {
		List<ReservationVO> reservationList = memberDAO.reservationList(reservationVO);
		return reservationList;
	}

	//-----------------------------------------------------------------------------------------------------------
	// pt정보 달력에 가져오기 회원의 강의종류(ef_p_type)
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public MemregiVO memberRegi(String ef_id) throws DataAccessException {
		
		return memberDAO.memberRegi(ef_id);
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
	// pt정보 오너 달력에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> findPt3(PtVO ptVO) throws DataAccessException {
		List<PtVO> findPt3 = memberDAO.findPt3(ptVO);
		return findPt3;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 모든 pt리스트 오너 강의현황에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> ptAllList(MemberVO memberVO) throws DataAccessException {
		List<PtVO> ptAllList = memberDAO.ptAllList(memberVO);
		return ptAllList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약 정보 카운트
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int countReservation(ReservationVO reservationVO) throws DataAccessException {
		
		return memberDAO.countReservation(reservationVO);
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
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약한 pt리스트, 나의 예약 현황에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> reservationPtlist(ReservationVO reservationVO) throws DataAccessException {
		List<PtVO> reservationPtlist = memberDAO.reservationPtlist(reservationVO);
		return reservationPtlist;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의하기 목록 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<ArticleVO> inquiryList(ArticleVO articleVO) throws DataAccessException {
		List<ArticleVO> inquiryList = memberDAO.inquiryList(articleVO);		
		return inquiryList;
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 등록
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addInquiry(ArticleVO articleVO) throws DataAccessException {
		System.out.println("serviceVO ==>" + articleVO);
		return memberDAO.addInquiry(articleVO);
	}

} // End - public class MemberServiceImpl implements MemberService
