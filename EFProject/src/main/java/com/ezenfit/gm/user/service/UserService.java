package com.ezenfit.gm.user.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ezenfit.gm.vo.CenterVO;
import com.ezenfit.gm.vo.MemberVO;



//-----------------------------------------------------------------------------------------------------------
//public interface UserService
//-----------------------------------------------------------------------------------------------------------
public interface UserService {
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	public int addUser(MemberVO memberVO) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// 센터 등록 처리
	//-----------------------------------------------------------------------------------------------------------
	public int addCenter(CenterVO centerVO) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 업체명 불러오기
	//-----------------------------------------------------------------------------------------------------------
	public List<CenterVO> listCenter() throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// 업체명 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	public int checkCname(CenterVO centerVO) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	public int checkId(MemberVO memberVO) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// ID 찾기
	//-----------------------------------------------------------------------------------------------------------
	public MemberVO findId(MemberVO memberVO) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// PWD 찾기
	//-----------------------------------------------------------------------------------------------------------
	public MemberVO findPwd(MemberVO memberVO) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인
	//-----------------------------------------------------------------------------------------------------------
	public MemberVO login(MemberVO memberVO) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------
	public MemberVO myPage(MemberVO memberVO) throws DataAccessException;
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 정보 수정 하기
	//-----------------------------------------------------------------------------------------------------------	
	public int updateInfo(MemberVO memberVO) throws DataAccessException;
	
} // End - public interface UserService
