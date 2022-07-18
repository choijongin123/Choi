package com.ezenfit.gm.trainer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ezenfit.gm.trainer.service.TrainerService;
import com.ezenfit.gm.vo.CenterVO;
import com.ezenfit.gm.vo.MemberVO;
import com.ezenfit.gm.vo.PtVO;
import com.ezenfit.gm.vo.ReservationVO;

//-----------------------------------------------------------------------------------------------------------
// public class MemberControllerImpl implements MemberController
//-----------------------------------------------------------------------------------------------------------
@Controller("trainerController")
public class TrainerControllerImpl implements TrainerController {
	
	private static final Logger logger = LoggerFactory.getLogger(TrainerControllerImpl.class);
	
	@Autowired
	private TrainerService trainerService;
	
	@Autowired
	private MemberVO memberVO;
	
	@Autowired
	private CenterVO centerVO;
	
	@Autowired
	private PtVO ptVO;
	
	@Autowired
	private ReservationVO reservationVO;
	
	//-----------------------------------------------------------------------------------------------------------
	// trainer메뉴
	//-----------------------------------------------------------------------------------------------------------		
	@RequestMapping(value="/trainer/trainerMenu.do", method=RequestMethod.GET)
	public ModelAndView ptList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberVO member = new MemberVO();
		HttpSession session = request.getSession();
		String sef_id = (String) session.getAttribute("ef_id");
		
		member.setEf_id(sef_id);
		
		List<PtVO> ptList = trainerService.ptList(member);
		System.out.println("ptList ==> " + ptList);
		ModelAndView mav = new ModelAndView("/trainer/calender");
		mav.addObject("trainer_ptList", ptList);
		return mav;
	}
	
	
	//-----------------------------------------------------------------------------------------------------------
	// pt정보 달력에 가져오기 (강사)
	//-----------------------------------------------------------------------------------------------------------		
	@ResponseBody
	@RequestMapping(value="/trainer/findMyOpenPtList.do", method=RequestMethod.POST)
	public List<PtVO> findPt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String ef_id  = (String) session.getAttribute("ef_id");
		
		
		List<PtVO> findPt = new ArrayList<PtVO>();
 		
		findPt = trainerService.findPt(ef_id);
		
		return findPt;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 추가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/trainer/addPt.do", method=RequestMethod.POST)
	public ModelAndView addPt(@ModelAttribute("PtVO") PtVO ptVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		System.out.println("ptVO ==>" + ptVO);

		ModelAndView mav = new ModelAndView();
			
		int result = trainerService.addPt(ptVO); 	
		
		mav = new ModelAndView("redirect:/trainer/trainerMenu.do");
		
		return mav;
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 삭제
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/trainer/deletePt.do", method=RequestMethod.GET)
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
		
		int result = trainerService.deletePt(pt); 	
		
		ModelAndView mav = new ModelAndView("redirect:/trainer/trainerMenu.do");
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 수정
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	@RequestMapping(value="/trainer/updatePt.do", method=RequestMethod.POST)
	public ModelAndView updatePt(@ModelAttribute("PtVO") PtVO ptVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("PT 수정 요청 자료 ==> " + ptVO);
		HttpSession session = request.getSession();
		String sef_id = (String) session.getAttribute("ef_id");
		ptVO.setEf_id(sef_id);
		System.out.println("PT 세션 추가 ==>" + ptVO);
		int result = trainerService.updatePt(ptVO);
		System.out.println("PT 요청 결과 ==> " + result);
		
		ModelAndView mav = new ModelAndView("redirect:/trainer/trainerMenu.do");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 예약현황 보기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/trainer/ptReservation.do", method=RequestMethod.POST)
	public List<ReservationVO> reservationList(@RequestParam("ef_p_no") int ef_p_no, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		
		
		System.out.println("prReservtion ajax ==> " + ef_p_no);
		List<ReservationVO> reservationList = trainerService.reservationList(ef_p_no); 
		System.out.println("예약리스트 == > " + reservationList);

		
		return reservationList;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// PT 강의이력 (오늘전까지)
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/trainer/ptHistory.do", method=RequestMethod.GET)
	public ModelAndView ptHistory(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		
		HttpSession session = request.getSession();
		String ef_id  = (String) session.getAttribute("ef_id");
		
		List<PtVO> ptHistory = trainerService.ptHistory(ef_id); 
		ModelAndView mav = new ModelAndView("/trainer/ptHistory");
		System.out.println("예약리스트 == > " + ptHistory);
		mav.addObject("ptHistory", ptHistory);
		
		return mav;
	}

	
} // End - public class MemberControllerImpl implements MemberController
