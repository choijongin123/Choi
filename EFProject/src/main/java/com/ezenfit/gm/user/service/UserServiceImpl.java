package com.ezenfit.gm.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import com.ezenfit.gm.user.dao.UserDAO;
import com.ezenfit.gm.vo.CenterVO;
import com.ezenfit.gm.vo.MemberVO;



//-----------------------------------------------------------------------------------------------------------
//public class UserServiceImpl implements UserService
//-----------------------------------------------------------------------------------------------------------
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addUser(MemberVO memberVO) throws DataAccessException {
		System.out.println("serviceVO ==>" + memberVO);
		return userDAO.addUser(memberVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 센터 등록 처리
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int addCenter(CenterVO centerVO) throws DataAccessException {
		
		return userDAO.addCenter(centerVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 업체명 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<CenterVO> listCenter() throws DataAccessException {
		
		return userDAO.listCenter();
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// 업체명 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int checkCname(CenterVO centerVO) throws DataAccessException {
		
		return userDAO.checkCname(centerVO);
	}	

	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int checkId(MemberVO memberVO) throws DataAccessException {
		
		return userDAO.checkId(memberVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// ID 찾기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO findId(MemberVO memberVO) throws DataAccessException {
		System.out.println("service id찾기 ==>" + memberVO);
		return userDAO.findId(memberVO);
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// PWD 찾기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO findPwd(MemberVO memberVO) throws DataAccessException {
		System.out.println("service pwd찾기 ==>" + memberVO);
		return userDAO.findPwd(memberVO);
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO login(MemberVO memberVO) throws DataAccessException {
		System.out.println("service 로그인 ==>" + memberVO);
		return userDAO.login(memberVO);
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO myPage(MemberVO memberVO) throws DataAccessException {
		
		return userDAO.myPage(memberVO);
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 정보 수정 하기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int updateInfo(MemberVO memberVO) throws DataAccessException {
	
		return userDAO.updateInfo(memberVO);
	}	
} // End -- public class UserServiceImpl
