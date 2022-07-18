package com.ezenfit.gm.member.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ezenfit.gm.vo.MemregiVO;
import com.ezenfit.gm.member.service.MemberService;
import com.ezenfit.gm.vo.PtVO;
import com.ezenfit.gm.vo.PtreVO;
import com.ezenfit.gm.vo.ReservationVO;

//-----------------------------------------------------------------------------------------------------------
// public class MemberControllerImpl implements MemberController
//-----------------------------------------------------------------------------------------------------------
@Controller("memberController")
public class MemberControllerImpl implements MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ReservationVO reservationVO;
	
	@Autowired
	private MemregiVO memregiVO;
	
	@Autowired
	private PtVO ptVO;
	
	
	//-----------------------------------------------------------------------------------------------------------
	// member메뉴
	//-----------------------------------------------------------------------------------------------------------		
	@RequestMapping(value="/member/memberMenu.do", method=RequestMethod.GET)
	public ModelAndView reservationPtlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String ef_id  = (String) session.getAttribute("ef_id");
		String ef_c_name  = (String) session.getAttribute("ef_c_name");
		
		ReservationVO reservationVO = new ReservationVO();
		reservationVO.setEf_id(ef_id);
		
		List<PtVO> reservationPtlist = new ArrayList<PtVO>();
		reservationPtlist = memberService.reservationPtlist(reservationVO);
		
		MemregiVO memregiVO = new MemregiVO();
	    memregiVO = memberService.memberRegi(ef_id);
		String ef_p_type = (String) memregiVO.getEf_p_type();
		if(ef_p_type == null) {
			mav = new ModelAndView("/member/error");
		} else {			
			PtVO ptVO = new PtVO();
			ptVO.setEf_c_name(ef_c_name);
			ptVO.setEf_p_type(ef_p_type);
			List<PtVO> findPt2 = new ArrayList<PtVO>();
			findPt2 = memberService.findPt2(ptVO);
			
			mav = new ModelAndView("/member/member_r");
			mav.addObject("reservationPtlist", reservationPtlist);
			mav.addObject("member_ptList", findPt2);
			mav.addObject("memregi", memregiVO);
		}
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// pt정보 달력에 가져오기 (회원)
	//-----------------------------------------------------------------------------------------------------------		
	@ResponseBody
	@RequestMapping(value="/member/findMyTypePtList.do", method=RequestMethod.POST)
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
	// 예약 카운트 (같은 날 같은 타임에 여러개 강의 예약 방지)
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/member/reservationCount.do", method=RequestMethod.POST)
	public int reservationCount(@RequestParam("ef_p_date") String ef_p_date, @RequestParam("ef_p_time") String ef_p_time, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("예약 카운트 ==> " + ef_p_date + ", " + ef_p_time);
		
		HttpSession session = request.getSession();
		String ef_id  = (String) session.getAttribute("ef_id");
		
		PtreVO ptreVO = new PtreVO();
		ptreVO.setEf_id(ef_id);
		ptreVO.setEf_p_date(ef_p_date);
		ptreVO.setEf_p_time(ef_p_time);
		
		int result = memberService.reservationCount(ptreVO);
		System.out.println("예약카운트 결과 ==> " + result);
		
		
		return result;
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
		
		int result	= memberService.countReservation(reservationVO);
		System.out.println(result);
		
		if(result == 0) {
			int result1 = memberService.insertReservation(reservationVO);
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
	
} // End - public class MemberControllerImpl implements MemberController
