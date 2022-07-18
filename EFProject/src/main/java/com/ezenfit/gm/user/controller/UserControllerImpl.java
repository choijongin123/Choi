package com.ezenfit.gm.user.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezenfit.gm.user.service.UserService;
import com.ezenfit.gm.vo.CenterVO;
import com.ezenfit.gm.vo.MemberVO;

//-----------------------------------------------------------------------------------------------------------
//public class UserControllerImpl implements UserController
//-----------------------------------------------------------------------------------------------------------
@Controller("userController")
public class UserControllerImpl implements UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MemberVO memberVO;
	
	@Autowired
	private CenterVO centerVO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 메인 화면 불러오기 layout
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "main";
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회사소개 페이지 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/companyProfile.do", method=RequestMethod.GET)
	public String companyProfile(Locale locale, Model model) {
		return "/companyProfile"; 
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// 프로그램 소개 페이지 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/ProgramIntroduction.do", method=RequestMethod.GET)
	public String ProgramIntroduction(Locale locale, Model model) {
		return "/ProgramIntroduction"; 
	}	

	//-----------------------------------------------------------------------------------------------------------
	// 이용안내 페이지 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/InformationUse.do", method=RequestMethod.GET)
	public String InformationUse(Locale locale, Model model) {
		return "/InformationUse"; 
	}			
	
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 유형 선택 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/user/userClassForm.do", method=RequestMethod.GET)
	private ModelAndView memberClassForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/signUp/efMemberClass");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/user/userForm.do", method=RequestMethod.GET)
	public ModelAndView memberForm(@RequestParam("ef_class") String ef_class, 
									HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		System.out.println("controller class==> " + ef_class);
		ModelAndView mav = new ModelAndView();
		if(ef_class.equals("01")) { // 오너 회원 가입폼
			mav.setViewName("/user/signUp/efMemberFormOwner");
		} else if(ef_class.equals("02")) { // 강사 회원 가입 폼
			List<CenterVO> centerList = userService.listCenter(); // 업체명 보여주는 리스트
			System.out.println("회원가입 폼 ==> " + centerList );
			mav.setViewName("/user/signUp/efMemberFormTrainer");
			mav.addObject("centerList", centerList);
		} else { // 일반 회원 회원가입  폼
			List<CenterVO> centerList = userService.listCenter(); // 업체명 보여주는 리스트
			mav.setViewName("/user/signUp/efMemberFormMember");
			mav.addObject("centerList", centerList);
		}
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 업체명 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/user/checkCname.do", method=RequestMethod.POST)
	public int checkCname(CenterVO centerVO) throws Exception {
		
		System.out.println("controller cNameCheck ==> " + centerVO);
		int result = userService.checkCname(centerVO);
		System.out.println("controller cNameCheck 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/user/checkId.do", method=RequestMethod.POST)
	public int checkId(MemberVO memberVO) throws Exception {
		
		System.out.println("controller checkId ==>" + memberVO);
		int result = userService.checkId(memberVO);
		
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/user/addUser.do", method=RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("memberVO") MemberVO memberVO, CenterVO centerVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("controllermVO ==>" + memberVO);
		System.out.println("controllercVO ==>" + centerVO);
		ModelAndView mav = new ModelAndView();
			
			if(memberVO.getEf_class().equals("01")) {	// 신규 오너 이면
				int result2 = userService.addCenter(centerVO);	// 업체명 등록
			} 
			int result1 = userService.addUser(memberVO); 	// 회원 가입
			mav = new ModelAndView("redirect:/index.do");
			
		return mav;
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// id pwd 찾기 폼
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/user/find.do", method=RequestMethod.GET)
	private ModelAndView findForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/find/find");
		return mav;
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// ID 찾기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/user/findId.do", method=RequestMethod.POST)
	public MemberVO findId(@RequestParam("ef_name") String ef_name, @RequestParam("ef_p_number") String ef_p_number, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("controller id찾기 ==>" + ef_name + ", " + ef_p_number);
		MemberVO member = new MemberVO();
		member.setEf_name(ef_name);
		member.setEf_p_number(ef_p_number);
		
		memberVO = userService.findId(member);
		
		System.out.println("id찾기 결과 ==>" + memberVO);
		
		//ModelAndView mav = new ModelAndView("/user/find/find_id");
		//mav.addObject("memberId", memberVO);
		return memberVO;
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PWD 찾기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/user/findPwd.do", method=RequestMethod.POST)
	public MemberVO findPwd(@RequestParam("ef_id") String ef_id, @RequestParam("ef_p_number") String ef_p_number, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		System.out.println("controller pwd찾기 ==>" + ef_id + ", " + ef_p_number);
		MemberVO member = new MemberVO();
		member.setEf_id(ef_id);
		member.setEf_p_number(ef_p_number);
		
		memberVO = userService.findPwd(member);
		
		System.out.println("pwd찾기 결과 ==>" + memberVO);
		
		//ModelAndView mav = new ModelAndView("/user/find/find_pwd");
		//mav.addObject("memberPwd", memberVO);
		return memberVO;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/user/loginForm.do", method=RequestMethod.GET)
	private ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/log");
		return mav;
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/user/login.do", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("controller 로그인 ==> " + member);
		
		
		ModelAndView mav = new ModelAndView();
		
		memberVO = userService.login(member);   // memberVO에 입력한 로그인 정보가 있는지 확인
	    
		System.out.println("controller 로그인 ==> " + memberVO);
		
		
		if(memberVO == null) { // 로그인 정보가 없을 경우
			// 다시 로그인 페이지로
			rAttr.addAttribute("result", "loginFailed");
			mav = new ModelAndView("redirect:/user/loginForm.do");
			
		} else { // 로그인 정보가 있는 경우
			// 입력한 id, pwd 가 등록된 id, pwd와 같을 경우
			if(member.getEf_id().equals(memberVO.getEf_id()) && member.getEf_pwd().equals(memberVO.getEf_pwd())) { 
				
				if(memberVO.getEf_class().equals("11") || memberVO.getEf_class().equals("12") || memberVO.getEf_class().equals("13")) { //  등록된 회원 로그인
					// 세션 생성
					HttpSession session = request.getSession();
					
					session.setAttribute("ef_id", memberVO.getEf_id());
					session.setAttribute("ef_c_name", memberVO.getEf_c_name());
					session.setAttribute("ef_name", memberVO.getEf_name());
					session.setAttribute("ef_pwd", memberVO.getEf_pwd());
					session.setAttribute("ef_class", memberVO.getEf_class());
					session.setAttribute("ef_p_number", memberVO.getEf_p_number());
					session.setAttribute("isLogOn", 	true);
					session.setMaxInactiveInterval(60*120);

					
					mav = new ModelAndView("redirect:/index.do");
				
				} else { // 미등록 회원 로그인시
					// 다시 로그인 페이지로
					rAttr.addAttribute("result", "unRegistered");
					mav = new ModelAndView("redirect:/user/loginForm.do");
				}
			}	
		}
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/user/myPage.do", method=RequestMethod.GET)
	public ModelAndView myPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// MemberVO 선언
		MemberVO member = new MemberVO();
		// 세션 가져오기
		HttpSession session = request.getSession();
		// 세션에 담긴 아이디 비번 저장
		String sef_id  = (String) session.getAttribute("ef_id");
		String sef_pwd = (String) session.getAttribute("ef_pwd");
		// member에 아이디 비번 값 담기
		member.setEf_id(sef_id);
		member.setEf_pwd(sef_pwd);
		// 마이페이지에 보여줄 목록 조회
		memberVO = userService.myPage(member);
		
		ModelAndView mav = new ModelAndView("/user/myPage/myPage");
		mav.addObject("member", memberVO);
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 수정 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/user/updateForm.do", method=RequestMethod.GET)
	public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberVO member = new MemberVO();
		HttpSession session = request.getSession();
		String sef_id  = (String) session.getAttribute("ef_id");
		String sef_pwd = (String) session.getAttribute("ef_pwd");
		member.setEf_id(sef_id);
		member.setEf_pwd(sef_pwd);
		memberVO = userService.myPage(member);
		ModelAndView mav = new ModelAndView("/user/myPage/update");
		mav.addObject("member", memberVO);
		return mav;
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 정보 수정 하기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	@RequestMapping(value="/user/updateInfo.do", method=RequestMethod.POST)
	public ModelAndView updateInfo(@ModelAttribute("member") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int result = userService.updateInfo(memberVO);
		ModelAndView mav = new ModelAndView("redirect:/user/logout.do");
		return mav;
	}	
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그아웃
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/user/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
			session.removeAttribute("ef_id");
			session.removeAttribute("ef_c_name");
			session.removeAttribute("ef_name");
			session.removeAttribute("ef_pwd");
			session.removeAttribute("ef_class");
			session.removeAttribute("ef_p_number");
			session.removeAttribute("isLogOn");
			
		ModelAndView mav = new ModelAndView("redirect:/index.do");
		
		return mav;
	}	
} // End -- public class UserControllerImpl
