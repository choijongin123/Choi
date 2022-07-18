package com.edu.member.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.edu.member.service.MemberService;
import com.edu.member.vo.ArticleVO;
import com.edu.member.vo.CenterVO;
import com.edu.member.vo.JoinregiVO;
import com.edu.member.vo.MemberVO;
import com.edu.member.vo.MemregiVO;
import com.edu.member.vo.PtVO;
import com.edu.member.vo.RecordVO;
import com.edu.member.vo.ReservationVO;

//-----------------------------------------------------------------------------------------------------------
// public class MemberControllerImpl implements MemberController
//-----------------------------------------------------------------------------------------------------------
@Controller("memberController")
public class MemberControllerImpl implements MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberVO memberVO;
	
	@Autowired
	private CenterVO centerVO;
	
	@Autowired
	private MemregiVO memregiVO;
	
	@Autowired
	private JoinregiVO joinregiVO;
	
	@Autowired
	private PtVO ptVO;
	
	@Autowired
	private ReservationVO reservationVO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 메인 화면 불러오기 layout
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "main";
	}
	
	/*
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 폼 불러오기 
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/member/efmemberForm.do", method=RequestMethod.GET)
	private ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		List<CenterVO> centerList = memberService.listCenter();	// 강사, 회원 가입시 업체 목록 보여주기
		mav.setViewName("/efmember/efMemberForm");
		mav.addObject("centerList", centerList);
		return mav;
	}
	*/
	
	/*
	 * 가입 페이지 3개로 나눈거
	 */
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 유형 선택 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/member/efmemberClassForm.do", method=RequestMethod.GET)
	private ModelAndView memberClassForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/efmember/efMemberClass");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/member/efmemberForm.do", method=RequestMethod.POST)
	private ModelAndView memberForm(@ModelAttribute("member") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.info("MemberController memberForm.....");
		System.out.println("controller class==> " + memberVO.getEf_class());
		ModelAndView mav = new ModelAndView();
		if(memberVO.getEf_class().equals("01")) { // 오너 회원 가입폼
			mav.setViewName("/efmember/efMemberFormOwner");
		} else if(memberVO.getEf_class().equals("02")) { // 강사 회원 가입 폼
			List<CenterVO> centerList = memberService.listCenter(); // 업체명 보여주는 리스트
			System.out.println("회원가입 폼 ==> " + centerList );
			mav.setViewName("/efmember/efMemberFormTrainer");
			mav.addObject("centerList", centerList);
		} else { // 일반 회원 회원가입  폼
			List<CenterVO> centerList = memberService.listCenter();
			mav.setViewName("/efmember/efMemberFormMember");
			mav.addObject("centerList", centerList);
		}
		
		return mav;
	}
	
	
	//-----------------------------------------------------------------------------------------------------------
	// 업체명 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/member/cNameCheck.do", method=RequestMethod.POST)
	public int cNameCheck(CenterVO centerVO) throws Exception {
		
		System.out.println("controller cNameCheck ==> " + centerVO);
		int result = memberService.cNameCheck(centerVO);
		System.out.println("controller cNameCheck 결과 ==> " + result);
		return result;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 체크
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/member/idCheck.do", method=RequestMethod.POST)
	public int idCheck(MemberVO memberVO) throws Exception {
		
		System.out.println("controller idCheck ==>" + memberVO);
		int result = memberService.idCheck(memberVO);
		
		return result;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/addMember.do", method=RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("memberVO") MemberVO memberVO, CenterVO centerVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.info("MemberController addMember.....");
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("controllermVO ==>" + memberVO);
		System.out.println("controllercVO ==>" + centerVO);
		ModelAndView mav = new ModelAndView();
			
			if(memberVO.getEf_class().equals("01")) {	// 신규 오너 이면
				int result2 = memberService.addCenter(centerVO);	// 업체명 등록
			} 
			int result1 = memberService.addMember(memberVO); 	// 회원 가입
			mav = new ModelAndView("redirect:/index.do");
			
		
		return mav;
	}
	//-----------------------------------------------------------------------------------------------------------
	// id pwd 찾기 폼
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/member/find.do", method=RequestMethod.GET)
	private ModelAndView findForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/efmember/find");
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// ID 찾기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/findId.do", method=RequestMethod.POST)
	public ModelAndView findId(@ModelAttribute("member") MemberVO member, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("controller id찾기 ==>" + member);
		
		memberVO = memberService.findId(member);
		
		System.out.println("id찾기 결과 ==>" + memberVO);
		
		ModelAndView mav = new ModelAndView("/efmember/find_id");
		mav.addObject("memberId", memberVO);
		return mav;
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PWD 찾기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/findPwd.do", method=RequestMethod.POST)
	public ModelAndView findPwd(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		System.out.println("controller pwd찾기 ==>" + member);
		
		memberVO = memberService.findPwd(member);
		
		System.out.println("pwd찾기 결과 ==>" + memberVO);
		
		ModelAndView mav = new ModelAndView("/efmember/find_pwd");
		mav.addObject("memberPwd", memberVO);
		return mav;
	}
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/member/loginForm.do", method=RequestMethod.GET)
	private ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/efmember/log");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("controller 로그인 ==> " + member);
		
		
		ModelAndView mav = new ModelAndView();
		
		memberVO = memberService.login(member);   // memberVO에 입력한 로그인 정보가 있는지 확인
	    
		System.out.println("controller 로그인 ==> " + memberVO);
		
		
		if(memberVO == null) { // 로그인 정보가 없을 경우
			// 다시 로그인 페이지로
			rAttr.addAttribute("result", "loginFailed");
			mav = new ModelAndView("redirect:/member/loginForm.do");
			
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
					mav = new ModelAndView("redirect:/member/loginForm.do");
				}
			}	
		}
		return mav;
	}
		
	//-----------------------------------------------------------------------------------------------------------
	// 오너 페이지 에서 미등록 강사, 회원 등록하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/regist.do", method=RequestMethod.GET)
	public ModelAndView registMember(@RequestParam("ef_id") String ef_id, @RequestParam("ef_class") String ef_class, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("controller regist ef_id ==> " + ef_id);
		
		// 미등록 강사, 회원 등록
		memberService.registMember(ef_id);
		
		// 회원일 경우 등록 시 mem_regi 테이블에 ef_id추가
		if(ef_class.equals("03")) {
			memberService.insertRegi(ef_id);
		} 
		
		ModelAndView mav = new ModelAndView("redirect:/member/approval.do");
		
		
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/member/myPage.do", method=RequestMethod.GET)
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
		memberVO = memberService.myPage(member);
		
		ModelAndView mav = new ModelAndView("/efmember/myPage");
		mav.addObject("member", memberVO);
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 수정 폼 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/member/updateForm.do", method=RequestMethod.GET)
	public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberVO member = new MemberVO();
		HttpSession session = request.getSession();
		String sef_id  = (String) session.getAttribute("ef_id");
		String sef_pwd = (String) session.getAttribute("ef_pwd");
		member.setEf_id(sef_id);
		member.setEf_pwd(sef_pwd);
		memberVO = memberService.myPage(member);
		ModelAndView mav = new ModelAndView("/efmember/update");
		mav.addObject("member", memberVO);
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 마이 페이지 정보 수정 하기
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	@RequestMapping(value="/member/update.do", method=RequestMethod.POST)
	public ModelAndView myUpdate(@ModelAttribute("member") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int result = memberService.myUpdate(memberVO);
		ModelAndView mav = new ModelAndView("redirect:/member/logout.do");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 승인관리 페이지
	//-----------------------------------------------------------------------------------------------------------	
	@RequestMapping(value="/member/approval.do", method=RequestMethod.GET)
	public ModelAndView approval(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// memberVO 선언
		MemberVO member = new MemberVO();
		System.out.println("memberVO null ==> " + member);
				
		// 세션 가져오기
		HttpSession session = request.getSession();
		// 세션에 들어있는 아이디 비밀번호 업체명 String type 저장
		String sef_id  = (String) session.getAttribute("ef_id");
		String sef_pwd = (String) session.getAttribute("ef_pwd");
		String sef_c_name = (String) session.getAttribute("ef_c_name");
		System.out.println("세션확인 id = " + sef_id + ", pwd = " + sef_pwd);
		
		// 저장한 String type memberVO에 담기 
		member.setEf_id(sef_id);
		member.setEf_pwd(sef_pwd);
		member.setEf_c_name(sef_c_name);
		System.out.println("session memberVO ==> " + member);
				
		memberVO = memberService.login(member);
				
		ModelAndView mav = new ModelAndView("/efmember/host");
				
		List<MemberVO> membersList1 = memberService.listMembers1(memberVO); // 미등록 강사 정보 조회
		System.out.println("membersList1 ==> " + membersList1);
		mav.addObject("trainerList", membersList1);
				
		List<MemberVO> membersList2 = memberService.listMembers2(memberVO); // 미등록 회원 정보 조회
		System.out.println("membersList2 ==> " + membersList2);
		mav.addObject("memberList", membersList2);
				
		return mav;
	}
	
	
	
	//-----------------------------------------------------------------------------------------------------------
	// 인원 관리 페이지
	//-----------------------------------------------------------------------------------------------------------	
	@RequestMapping(value="/member/management.do", method=RequestMethod.GET)
	public ModelAndView management(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberVO member = new MemberVO();
		HttpSession session = request.getSession();
		String sef_c_name = (String) session.getAttribute("ef_c_name");
		
		member.setEf_c_name(sef_c_name);
		
		ModelAndView mav = new ModelAndView("/efmember/host1");
		
		// 등록된 강사 정보 조회
		List<MemberVO> membersList3 = memberService.listMembers3(member); 
		mav.addObject("trainerList", membersList3);
		
		List<JoinregiVO> membersList4 = memberService.listMembers4(member);
		mav.addObject("memberList", membersList4);
		System.out.println("join membersList4 ==> " + membersList4);
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 인원 관리 페이지 회원 탈퇴
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/delete.do", method=RequestMethod.GET)
	public ModelAndView delete(@RequestParam("ef_id") String ef_id, @RequestParam("ef_class") String ef_class, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("회원 탈퇴 ef_id == > " + ef_id);
		System.out.println("회원 탈퇴 ef_class == > " + ef_class);
		
		if(ef_class.equals("12")) {	// 인원 관리 페이지 에서 강사 탈퇴처리 할때
			int result = memberService.delete(ef_id);
		} else {					// 인원 관리 패이지 에서 회원 탈퇴처리 할때
			int result = memberService.regidelete(ef_id);
		}
		int result = memberService.delete(ef_id);
		ModelAndView mav = new ModelAndView("redirect:/member/management.do");
		return mav;

	}

	//-----------------------------------------------------------------------------------------------------------
	// 수강 등록 및 수정 페이지
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	@RequestMapping(value="/member/courseRegistForm.do", method=RequestMethod.GET)
	public ModelAndView courseRegistForm(String ef_id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MemregiVO memregiVO = new MemregiVO();
		memregiVO = memberService.courseRegistForm(ef_id);
		ModelAndView mav = new ModelAndView("/efmember/host_alter");
		mav.addObject("member", memregiVO);
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 수강 등록 및 수정 하기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	@RequestMapping(value="/member/courseRegist.do", method=RequestMethod.POST)
	public ModelAndView courseRegist(@ModelAttribute("memregi") MemregiVO memregiVO, @ModelAttribute("record") RecordVO recordVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// 폼 태크에서 서밋 한 데이터는 각 vo에 name에 따라 잘 찾아감
		// System.out.println("memregiVO ==> " + memregiVO);
		// System.out.println("recordVO ==> " + recordVO);
		
		// String type인 count , r_count 를 int type으로 바꿈
		int ief_count   = Integer.parseInt(memregiVO.getEf_count());
		int ief_r_count = Integer.parseInt(recordVO.getEf_r_count());
		
		// 회원 정보에 기존 수강횟수 + 추가된 수강횟수  
		ief_count = ief_count + ief_r_count;
		// System.out.println("ief_count ==> " + ief_count);
		
		// int type 인 count를 String type으로 바꿈
		String ef_count = String.valueOf(ief_count); 
		// System.out.println("ef_count == >" + ef_count);
		// 합쳐진 count를 memregiVO에 담음
		memregiVO.setEf_count(ef_count);
		// System.out.println("update memregiVO ==> " + memregiVO);
		
		// ef_m_regi 테이블에 회원정보 등록 및 수줭
		int result = memberService.courseRegist(memregiVO);
		
		// 수강 등록 및 수정시 record 테이블에 기록 추가
		int result1 = memberService.insertRecord(recordVO); 
		ModelAndView mav = new ModelAndView("redirect:/member/management.do");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// trainer메뉴
	//-----------------------------------------------------------------------------------------------------------		
	@RequestMapping(value="/member/trainerMenu.do", method=RequestMethod.GET)
	public ModelAndView ptList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberVO member = new MemberVO();
		HttpSession session = request.getSession();
		String sef_id = (String) session.getAttribute("ef_id");
		
		member.setEf_id(sef_id);
		
		List<PtVO> ptList = memberService.ptList(member);
		System.out.println("ptList ==> " + ptList);
		ModelAndView mav = new ModelAndView("/calender");
		mav.addObject("trainer_ptList", ptList);
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 강의이력
	//-----------------------------------------------------------------------------------------------------------		
	@RequestMapping(value="/member/ptHistory.do", method=RequestMethod.GET)
	public ModelAndView ptHistory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberVO member = new MemberVO();
		HttpSession session = request.getSession();
		String sef_id = (String) session.getAttribute("ef_id");
		
		member.setEf_id(sef_id);
		
		List<PtVO> ptHistory = memberService.ptHistory(member);
		
		ModelAndView mav = new ModelAndView("/ptHistory");
		mav.addObject("trainer_ptList", ptHistory);
		return mav;
	}
	
	
	//-----------------------------------------------------------------------------------------------------------
	// pt정보 달력에 가져오기 (강사)
	//-----------------------------------------------------------------------------------------------------------		
	@ResponseBody
	@RequestMapping(value="/member/findPt.do", method=RequestMethod.POST)
	public List<PtVO> findPt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String ef_id  = (String) session.getAttribute("ef_id");
		
		List<PtVO> findPt = new ArrayList<PtVO>();
 		
		findPt = memberService.findPt(ef_id);
		
		return findPt;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 추가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/addPt.do", method=RequestMethod.POST)
	public ModelAndView addPt(@ModelAttribute("PtVO") PtVO ptVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		System.out.println("ptVO ==>" + ptVO);

		ModelAndView mav = new ModelAndView();
			
		int result = memberService.addPt(ptVO); 	
		
		mav = new ModelAndView("redirect:/member/trainerMenu.do");
		
		return mav;
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 삭제
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/deletePt.do", method=RequestMethod.GET)
	public ModelAndView deletePt(@RequestParam("ef_p_date") String ef_p_date, @RequestParam("ef_p_time") String ef_p_time, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("deletePt.do ==> " + ef_p_date + ", " + ef_p_time);
		PtVO pt = new PtVO();
		HttpSession session = request.getSession();
		String sef_id = (String) session.getAttribute("ef_id");
		
		pt.setEf_id(sef_id);
		pt.setEf_p_date(ef_p_date);
		pt.setEf_p_time(ef_p_time);
		
		int result = memberService.deletePt(pt); 	
		
		ModelAndView mav = new ModelAndView("redirect:/member/trainerMenu.do");
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 수정
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	@RequestMapping(value="/member/updatePt.do", method=RequestMethod.POST)
	public ModelAndView updatePt(@ModelAttribute("PtVO") PtVO ptVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("PT 수정 요청 자료 ==> " + ptVO);
		HttpSession session = request.getSession();
		String sef_id = (String) session.getAttribute("ef_id");
		ptVO.setEf_id(sef_id);
		System.out.println("PT 세션 추가 ==>" + ptVO);
		int result = memberService.updatePt(ptVO);
		System.out.println("PT 요청 결과 ==> " + result);
		
		ModelAndView mav = new ModelAndView("redirect:/member/trainerMenu.do");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 예약현황 보기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/member/pt_reservation.do", method=RequestMethod.GET)
	public ModelAndView reservationList(ReservationVO reservationVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		System.out.println("예약현황 vo ==> " + reservationVO);
		
		List<ReservationVO> reservationList = memberService.reservationList(reservationVO); 
		ModelAndView mav = new ModelAndView("/ptHistoryMember");
		System.out.println("예약리스트 == > " + reservationList);
		mav.addObject("pt_reservationList", reservationList);
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// /member메뉴
	//-----------------------------------------------------------------------------------------------------------		
	@RequestMapping(value="/member/memberMenu.do", method=RequestMethod.GET)
	public ModelAndView memList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String ef_id  = (String) session.getAttribute("ef_id");
		
		ReservationVO reservationVO = new ReservationVO();
		reservationVO.setEf_id(ef_id);
		
		List<PtVO> reservationPtlist = new ArrayList<PtVO>();
		reservationPtlist = memberService.reservationPtlist(reservationVO);
			
		ModelAndView mav = new ModelAndView("/member_r");
		mav.addObject("reservationPtlist", reservationPtlist);
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// pt정보 달력에 가져오기 (회원)
	//-----------------------------------------------------------------------------------------------------------		
	@ResponseBody
	@RequestMapping(value="/member/findPt2.do", method=RequestMethod.POST)
	public List<PtVO> findPt2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession();
		String ef_id  = (String) session.getAttribute("ef_id");
		String ef_c_name  = (String) session.getAttribute("ef_c_name");
		
		MemregiVO memregiVO = new MemregiVO();
	    memregiVO = memberService.memberRegi(ef_id);
		String ef_p_type = (String) memregiVO.getEf_p_type();
		
		PtVO ptVO = new PtVO();
		ptVO.setEf_c_name(ef_c_name);
		ptVO.setEf_p_type(ef_p_type);
		List<PtVO> findPt2 = new ArrayList<PtVO>();
 		
		findPt2 = memberService.findPt2(ptVO);
		System.out.println("회원 달력에 뿌려줄 정보 ==> " + findPt2);
		
		return findPt2;
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 업체 관리 페이지
	//-----------------------------------------------------------------------------------------------------------		
	@RequestMapping(value="/member/company.do", method=RequestMethod.GET)
	public ModelAndView ptAllList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberVO member = new MemberVO();
		HttpSession session = request.getSession();
		String ef_c_name  = (String) session.getAttribute("ef_c_name");
		
		member.setEf_c_name(ef_c_name);
		
		List<PtVO> ptAllList = memberService.ptAllList(member);
		System.out.println("ptAllList ==> " + ptAllList);
		ModelAndView mav = new ModelAndView("/owner_calender");
		mav.addObject("trainer_ptList", ptAllList);
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// pt정보 오너 달력에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@ResponseBody
	@RequestMapping(value="/member/findPt3.do", method=RequestMethod.POST)
	public List<PtVO> findPt3(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String ef_c_name  = (String) session.getAttribute("ef_c_name");	
		
		PtVO ptVO = new PtVO();
		ptVO.setEf_c_name(ef_c_name);
		System.out.println("findPt2 ptVO ==> " + ptVO);
		
		List<PtVO> findPt3 = new ArrayList<PtVO>();
 		
		findPt3 = memberService.findPt3(ptVO);
		System.out.println("findPt2 결과 ==> " + findPt3);
		
		return findPt3;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/addReservation.do", method=RequestMethod.GET)
	public ModelAndView insertReservation(@RequestParam("ef_p_no") int ef_p_no, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		System.out.println("ef_p_no ==>" + ef_p_no);

		HttpSession session = request.getSession();
		String ef_id  = (String) session.getAttribute("ef_id");
		String ef_name  = (String) session.getAttribute("ef_name");
		String ef_p_number  = (String) session.getAttribute("ef_p_number");
		
		ReservationVO reservationVO = new ReservationVO();
		reservationVO.setEf_id(ef_id);
		reservationVO.setEf_name(ef_name);
		reservationVO.setEf_p_number(ef_p_number);
		reservationVO.setEf_p_no(ef_p_no);
		
		ModelAndView mav = new ModelAndView();
		
		int count = memberService.countReservation(reservationVO);
		System.out.println("count ==> " + count);
		
		if(count == 0) {
			int result = memberService.insertReservation(reservationVO);
			int result2 = memberService.r_personalOnePlus(reservationVO);
			int result3 = memberService.countOneMiuns(reservationVO);
		}
		
		mav = new ModelAndView("redirect:/member/memberMenu.do");
		
		return mav;
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 예약 취소하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/deleteReservation.do", method=RequestMethod.GET)
	public ModelAndView deleteReservation(@RequestParam("ef_p_no") int ef_p_no, @RequestParam("ef_id") String ef_id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("ef_id, ef_p_no ==> " + ef_id + ef_p_no);

		ReservationVO reservationVO = new ReservationVO();
		reservationVO.setEf_id(ef_id);
		reservationVO.setEf_p_no(ef_p_no);
		
		System.out.println("reservationVO ==>" + reservationVO);

		ModelAndView mav = new ModelAndView();
			
		int result = memberService.deleteReservation(reservationVO);
		int result2 = memberService.r_personalOneMinus(reservationVO);
		int result3 = memberService.countOnePlus(reservationVO);
		
		mav = new ModelAndView("redirect:/member/memberMenu.do");
		
		return mav;
		
	}
		
	//-----------------------------------------------------------------------------------------------------------
	// 로그아웃
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
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
	
	// 게시판
	//-----------------------------------------------------------------------------------------------------------
	// 문의하기 목록 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/inquiry.do", method=RequestMethod.GET)
	public ModelAndView inquiryList(@ModelAttribute("articleVO") ArticleVO articleVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		List<ArticleVO> inquiryList = memberService.inquiryList(articleVO);	// 문의하기 목록 보여주기	
		mav.setViewName("/efmember/inquiry");
		mav.addObject("inquiryList", inquiryList);
		return mav;
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 쓰기 폼
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/member/inqWrite.do", method=RequestMethod.GET)
	public ModelAndView inqWriteForm(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/efmember/inqWrite");
		return mav;
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 등록
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/addInquiry.do", method=RequestMethod.POST)
	public ModelAndView addInquiry(@ModelAttribute("articleVO") ArticleVO articleVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		System.out.println("controllermVO ==>" + articleVO);
		ModelAndView mav = new ModelAndView();
		
		int ef_i_no = memberService.addInquiry(articleVO);	// 문의사항 등록			
			
		mav = new ModelAndView("redirect:/member/inquiry.do");			
		
		return mav;
	}

	
	
} // End - public class MemberControllerImpl implements MemberController
