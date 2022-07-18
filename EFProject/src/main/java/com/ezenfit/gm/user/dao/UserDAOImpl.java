package com.ezenfit.gm.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ezenfit.gm.vo.CenterVO;
import com.ezenfit.gm.vo.MemberVO;



//-----------------------------------------------------------------------------------------------------------
//public class UserDAOImpl implements UserDAO
//-----------------------------------------------------------------------------------------------------------
@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int addUser(MemberVO memberVO) throws DataAccessException {
		System.out.println("daoVO ==>" + memberVO);
		int result = sqlSession.insert("mapper.user.addUser", memberVO);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 센터 등록 처리
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int addCenter(CenterVO centerVO) throws DataAccessException {
		
		return sqlSession.insert("mapper.user.addCenter", centerVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 업체명 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<CenterVO> listCenter() throws DataAccessException {
		
		return sqlSession.selectList("mapper.user.listCenter");
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 업체명 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int checkCname(CenterVO centerVO) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.user.checkCname", centerVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int checkId(MemberVO memberVO) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.user.checkId", memberVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// ID 찾기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO findId(MemberVO memberVO) throws DataAccessException {
		System.out.println("dao id찾기 ==>" + memberVO);
		MemberVO memVO = sqlSession.selectOne("mapper.user.findId", memberVO);
		System.out.println("dao id찾기 결과 ==>" + memVO);
		return memVO;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PWD 찾기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO findPwd(MemberVO memberVO) throws DataAccessException {
		System.out.println("service pwd찾기 ==>" + memberVO);
		MemberVO memVO = sqlSession.selectOne("mapper.user.findPwd", memberVO);
		System.out.println("dao pwd찾기 결과 ==>" + memVO);
		return memVO;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO login(MemberVO memberVO) throws DataAccessException {
		System.out.println("dao 로그인 ==>" + memberVO);
		MemberVO memVO = sqlSession.selectOne("mapper.user.login", memberVO);
		System.out.println("dao 로그인 결과 ==>" + memVO);
		return memVO;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO myPage(MemberVO memberVO) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.user.myPage", memberVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 정보 수정 하기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	public int updateInfo(MemberVO memberVO) throws DataAccessException {
		
		return sqlSession.update("mapper.user.updateInfo", memberVO);
	}
}
