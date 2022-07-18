package com.edu.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.edu.member.vo.ArticleVO;
import com.edu.member.vo.CenterVO;
import com.edu.member.vo.JoinregiVO;
import com.edu.member.vo.MemberVO;
import com.edu.member.vo.MemregiVO;
import com.edu.member.vo.PtVO;
import com.edu.member.vo.RecordVO;
import com.edu.member.vo.ReservationVO;

//-----------------------------------------------------------------------------------------------------------
// public class MemberDAOImpl implements MemberDAO
//-----------------------------------------------------------------------------------------------------------
@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {
		System.out.println("daoVO ==>" + memberVO);
		int result = sqlSession.insert("mapper.memberall.insertMember", memberVO);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 센터 등록 처리
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int addCenter(CenterVO centerVO) throws DataAccessException {
		
		return sqlSession.insert("mapper.memberall.insertCenter", centerVO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 업체명 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<CenterVO> listCenter() throws DataAccessException {
		
		return sqlSession.selectList("mapper.memberall.listCenter");
	}

	//-----------------------------------------------------------------------------------------------------------
	// 업체명 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int cNameCheck(CenterVO centerVO) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.memberall.cNameCheck", centerVO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int idCheck(MemberVO memberVO) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.memberall.idCheck", memberVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// ID 찾기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO findId(MemberVO memberVO) throws DataAccessException {
		System.out.println("dao id찾기 ==>" + memberVO);
		MemberVO memVO = sqlSession.selectOne("mapper.memberall.findId", memberVO);
		System.out.println("dao id찾기 결과 ==>" + memVO);
		return memVO;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PWD 찾기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO findPwd(MemberVO memberVO) throws DataAccessException {
		System.out.println("service pwd찾기 ==>" + memberVO);
		MemberVO memVO = sqlSession.selectOne("mapper.memberall.findPwd", memberVO);
		System.out.println("dao pwd찾기 결과 ==>" + memVO);
		return memVO;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO login(MemberVO memberVO) throws DataAccessException {
		System.out.println("dao 로그인 ==>" + memberVO);
		MemberVO memVO = sqlSession.selectOne("mapper.memberall.login", memberVO);
		System.out.println("dao 로그인 결과 ==>" + memVO);
		return memVO;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 미등록 강사 목록 조회 (오너 로그인 처리)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<MemberVO> listMembers1(MemberVO memberVO) throws DataAccessException {
		List<MemberVO> membersList1 = sqlSession.selectList("mapper.memberall.listMembers1", memberVO);  
		return membersList1;
	}
	//-----------------------------------------------------------------------------------------------------------
	// 미등록 회원 목록 조회 (오너 로그인 처리)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<MemberVO> listMembers2(MemberVO memberVO) throws DataAccessException {
		List<MemberVO> membersList2 = sqlSession.selectList("mapper.memberall.listMembers2", memberVO); 
		return membersList2;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 오너 페이지 에서 미등록 강사, 회원 등록하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int registMember(String ef_id) throws DataAccessException {
		
		return sqlSession.update("mapper.memberall.registMember", ef_id);
	}


	//-----------------------------------------------------------------------------------------------------------
	// 오너 페이지 에서 미등록 강사, 회원 등록 시 m_regi 테이블에 col생성
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int insertRegi(String ef_id) throws DataAccessException {
		
		return sqlSession.insert("mapper.memberall.insertRegi", ef_id);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO myPage(MemberVO memberVO) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.memberall.myPage", memberVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 정보 수정 하기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int myUpdate(MemberVO memberVO) throws DataAccessException {
		
		return sqlSession.update("mapper.memberall.myUpdate", memberVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 등록 강사 목록 조회
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<MemberVO> listMembers3(MemberVO memberVO) throws DataAccessException {
		List<MemberVO> membersList3 = sqlSession.selectList("mapper.memberall.listMembers3", memberVO); 
		return membersList3;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 등록 회원 목록 조회
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<JoinregiVO> listMembers4(MemberVO memberVO) throws DataAccessException {
		List<JoinregiVO> membersList4 = sqlSession.selectList("mapper.memberall.listMembers4", memberVO);
		return membersList4;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 인원 관리 페이지 강사 탈퇴
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int delete(String ef_id) throws DataAccessException {
		
		return sqlSession.delete("mapper.memberall.delete", ef_id);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 인원 관리 페이지 회원 탈퇴
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int regidelete(String ef_id) throws DataAccessException {
		int result1 = sqlSession.delete("mapper.memberall.regidelete1", ef_id);
		return sqlSession.delete("mapper.memberall.regidelete2", ef_id);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 수강 등록 및 수정 페이지
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public MemregiVO courseRegistForm(String ef_id) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.memberall.courseRegistForm", ef_id);
	}
	

	//-----------------------------------------------------------------------------------------------------------
	// 수강 등록 및 수정 하기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public int courseRegist(MemregiVO memregiVO) throws DataAccessException {
		
		return sqlSession.update("mapper.memberall.courseRegist", memregiVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 수강 등록 및 수정시 record 테이블에 기록 추가
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public int insertRecord(RecordVO recordVO) throws DataAccessException {
		
		return sqlSession.insert("mapper.memberall.insertRecord", recordVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// pt정보 달력에 가져오기(강사)
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> findPt(String ef_id) throws DataAccessException {
		List<PtVO> findPt = sqlSession.selectList("mapper.memberall.findPt", ef_id);
		return findPt;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// pt리스트 강의현황에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> ptList(MemberVO memberVO) throws DataAccessException {
		List<PtVO> ptList = sqlSession.selectList("mapper.memberall.ptList", memberVO);
		return ptList;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 강의이력
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> ptHistory(MemberVO memberVO) throws DataAccessException {
		List<PtVO> ptHistory = sqlSession.selectList("mapper.memberall.ptHistory", memberVO);
		return ptHistory;
	}

	//-----------------------------------------------------------------------------------------------------------
	// PT 추가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addPt(PtVO ptVO) throws DataAccessException {
		System.out.println("ptVO DAO ==> " + ptVO);
		int result = sqlSession.insert("mapper.memberall.insertPt", ptVO);
		System.out.println("ptVO DAO 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 삭제
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int deletePt(PtVO ptVO) throws DataAccessException {
		System.out.println("ptVO DAO ==> " + ptVO);
		int result = sqlSession.insert("mapper.memberall.deletePt", ptVO);
		System.out.println("ptVO DAO 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 수정
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public int updatePt(PtVO ptVO) throws DataAccessException {
		
		return sqlSession.update("mapper.memberall.updatePt", ptVO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// PT 예약현황 보기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<ReservationVO> reservationList(ReservationVO reservationVO) throws DataAccessException {
		List<ReservationVO> reservationList = sqlSession.selectList("mapper.memberall.reservationList", reservationVO);
		return reservationList;
	}

	//-----------------------------------------------------------------------------------------------------------
	// pt정보 달력에 가져오기 회원의 강의종류(ef_p_type)
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public MemregiVO memberRegi(String ef_id) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.memberall.memberRegi", ef_id);
	}

	//-----------------------------------------------------------------------------------------------------------
	// pt정보 달력에 가져오기(회원)
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> findPt2(PtVO ptVO) throws DataAccessException {
		List<PtVO> findPt2 = sqlSession.selectList("mapper.memberall.findPt2", ptVO);
		return findPt2;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// pt정보 오너 달력에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> findPt3(PtVO ptVO) throws DataAccessException {
		System.out.println("findPt3 ptVO DAO ==> " + ptVO);
		List<PtVO> findPt3 = sqlSession.selectList("mapper.memberall.findPt3", ptVO);
		System.out.println("findPt3 ptVO결과 DAO ==> " + findPt3);
		return findPt3;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 모든 pt리스트 오너 강의현황에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> ptAllList(MemberVO memberVO) throws DataAccessException {
		List<PtVO> ptAllList = sqlSession.selectList("mapper.memberall.ptAllList", memberVO);
		return ptAllList;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 예약 정보 카운트
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int countReservation(ReservationVO reservationVO) throws DataAccessException {
		int count = sqlSession.selectOne("mapper.memberall.countReservation", reservationVO);
		return count;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 예약하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int insertReservation(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("insertReservation DAO ==> " + reservationVO);
		int result = sqlSession.insert("mapper.memberall.insertReservation", reservationVO);
		System.out.println("insertReservation DAO 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약인원 1증가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int r_personalOnePlus(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("r_personalOnePlus DAO ==> " + reservationVO);
		int result = sqlSession.update("mapper.memberall.r_personalOnePlus", reservationVO);
		System.out.println("r_personalOnePlus DAO 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 참여가능횟수 1감소
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int countOneMiuns(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("countOneMiuns DAO ==> " + reservationVO);
		int result = sqlSession.update("mapper.memberall.countOneMiuns", reservationVO);
		System.out.println("countOneMiuns DAO 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약 취소하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int deleteReservation(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("deleteReservation DAO ==> " + reservationVO);
		int result = sqlSession.delete("mapper.memberall.deleteReservation", reservationVO);
		System.out.println("deleteReservation DAO 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약인원 1감소
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int r_personalOneMinus(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("r_personalOneMinus DAO ==> " + reservationVO);
		int result = sqlSession.update("mapper.memberall.r_personalOneMinus", reservationVO);
		System.out.println("r_personalOneMinus DAO 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 참여가능횟수 1증가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int countOnePlus(ReservationVO reservationVO) throws DataAccessException {
		System.out.println("countOnePlus DAO ==> " + reservationVO);
		int result = sqlSession.update("mapper.memberall.countOnePlus", reservationVO);
		System.out.println("countOnePlus DAO 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약한 pt리스트, 나의 예약 현황에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	public List<PtVO> reservationPtlist(ReservationVO reservationVO) throws DataAccessException {
		List<PtVO> reservationPtlist = sqlSession.selectList("mapper.memberall.reservationPtlist", reservationVO);
		return reservationPtlist;
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의하기 목록 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------				
	@Override
	public List<ArticleVO> inquiryList(ArticleVO articleVO) throws DataAccessException {
		List<ArticleVO> inquiryList = sqlSession.selectList("mapper.memberall.inquiryList", articleVO);		
		return inquiryList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 등록
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addInquiry(ArticleVO articleVO) throws DataAccessException {
		System.out.println("daoVO ==>" + articleVO);
		 return sqlSession.insert("mapper.memberall.addInquiry", articleVO); 
	}
} // End - public class MemberDAOImpl implements MemberDAO
