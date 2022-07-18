package com.ezenfit.gm.owner.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.ezenfit.gm.owner.service.OwnerService;
import com.ezenfit.gm.vo.JoinrecordVO;
import com.ezenfit.gm.vo.JoinregiVO;
import com.ezenfit.gm.vo.MemberVO;
import com.ezenfit.gm.vo.MemregiVO;
import com.ezenfit.gm.vo.PtVO;
import com.ezenfit.gm.vo.RecordVO;

//-----------------------------------------------------------------------------------------------------------
//public class OwnerControllerImpl implements OwnerController
//-----------------------------------------------------------------------------------------------------------
@Controller("ownerController")
public class OwnerControllerImpl implements OwnerController {
	
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private MemberVO memberVO;
	
	@Autowired
	private MemregiVO memregiVO;
	
	@Autowired
	private RecordVO redordVO;
	
	@Autowired
	private JoinregiVO joinregiVO;
	
	@Autowired
	private PtVO ptVO;

	//-----------------------------------------------------------------------------------------------------------
	// 승인관리 페이지 ==> 미등록 강사, 회원 리스트
	//-----------------------------------------------------------------------------------------------------------	
	@RequestMapping(value="/owner/approval.do", method=RequestMethod.GET)
	public ModelAndView approval(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// memberVO 선언
		MemberVO member = new MemberVO();
		System.out.println("memberVO null ==> " + member);
				
		// 세션 가져오기
		HttpSession session = request.getSession();
		// 세션에 들어있는  업체명 String type 저장
		String sef_c_name = (String) session.getAttribute("ef_c_name");
		
		// 저장한 String type memberVO에 담기 
		member.setEf_c_name(sef_c_name);
		System.out.println("session memberVO ==> " + member);
				
		ModelAndView mav = new ModelAndView("/owner/host");
				
		List<MemberVO> unRegiListTrainers = ownerService.unRegiListTrainers(member); // 미등록 강사 정보 조회
		System.out.println("listTrainers ==> " + unRegiListTrainers);
		mav.addObject("trainerList", unRegiListTrainers);
				
		List<MemberVO> unRegiListMembers = ownerService.unRegiListMembers(member); // 미등록 회원 정보 조회
		System.out.println("listMembers ==> " + unRegiListMembers);
		mav.addObject("memberList", unRegiListMembers);
				
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 오너 페이지 에서 미등록 강사, 회원 등록하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/owner/registUser.do", method=RequestMethod.GET)
	public ModelAndView registUser(@RequestParam("ef_id") String ef_id, @RequestParam("ef_class") String ef_class, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("controller regist ef_id ==> " + ef_id);
		
		// 미등록 강사, 회원 등록
		ownerService.registUser(ef_id);
		
		// 회원일 경우 등록 시 mem_regi 테이블에 ef_id추가
		if(ef_class.equals("03")) {
			ownerService.insertRegi(ef_id);
		} 
		
		ModelAndView mav = new ModelAndView("redirect:/owner/approval.do");
		
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 인원 관리 페이지
	//-----------------------------------------------------------------------------------------------------------	
	@RequestMapping(value="/owner/management.do", method=RequestMethod.GET)
	public ModelAndView management(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberVO member = new MemberVO();
		HttpSession session = request.getSession();
		String sef_c_name = (String) session.getAttribute("ef_c_name");
		
		member.setEf_c_name(sef_c_name);
		
		ModelAndView mav = new ModelAndView("/owner/host1");
		
		// 등록된 강사 정보 조회
		List<MemberVO> regiListTrainers = ownerService.regiListTrainers(member); 
		mav.addObject("trainerList", regiListTrainers);
		// 등록된 회원 정보 조회
		List<JoinregiVO> regiListMembers = ownerService.regiListMembers(member);
		mav.addObject("memberList", regiListMembers);
		System.out.println("join membersList4 ==> " + regiListMembers);
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 인원 관리 페이지 회원 탈퇴
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/owner/delete.do", method=RequestMethod.POST)
	public int delete(@RequestParam("ef_id") String ef_id, @RequestParam("ef_class") String ef_class, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("회원 탈퇴 ef_id == > " + ef_id);
		System.out.println("회원 탈퇴 ef_class == > " + ef_class);
		int count = 0;
		
		if(ef_class.equals("12")) {
			// System.out.println("강사 탈퇴");
			int trainerCount = ownerService.trainerCount(ef_id);
			//System.out.println("trainerCount ==> " + trainerCount);
			if(trainerCount == 0) {
				int result  = ownerService.delete(ef_id);
			} else {
				count = trainerCount;
			}
		} else {
			// System.out.println("회원 탈퇴");
			int memberCount = ownerService.memberCount(ef_id);
			System.out.println("memberCount ==> " + memberCount);
			if(memberCount == 0) {
				int result  = ownerService.regidelete(ef_id);
			} else {
				count = memberCount;
			}
		}
		
		/*
		if(ef_class.equals("12")) {	// 인원 관리 페이지 에서 강사 탈퇴처리 할때
			int result  = ownerService.delete(ef_id);
		} else {					// 인원 관리 패이지 에서 회원 탈퇴처리 할때
			int result  = ownerService.regidelete(ef_id); //regi table 기록도 삭제
		}
	    */
		return count;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 수강 등록 및 수정 페이지
	//-----------------------------------------------------------------------------------------------------------	
	@Override
	@RequestMapping(value="/owner/courseRegistForm.do", method=RequestMethod.GET)
	public ModelAndView courseRegistForm(String ef_id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MemregiVO memregiVO = new MemregiVO();
		memregiVO = ownerService.courseRegistForm(ef_id);
		ModelAndView mav = new ModelAndView("/owner/host_alter");
		mav.addObject("member", memregiVO);
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 수강 등록 및 수정 하기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	@RequestMapping(value="/owner/courseRegist.do", method=RequestMethod.POST)
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
		int result = ownerService.courseRegist(memregiVO);
		
		// 수강 등록 및 수정시 record 테이블에 기록 추가
		int result1 = ownerService.insertRecord(recordVO); 
		ModelAndView mav = new ModelAndView("redirect:/owner/management.do");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 업체 관리 페이지
	//-----------------------------------------------------------------------------------------------------------		
	@RequestMapping(value="/owner/company.do", method=RequestMethod.GET)
	public ModelAndView ptAllList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberVO member = new MemberVO();
		HttpSession session = request.getSession();
		String ef_c_name  = (String) session.getAttribute("ef_c_name");
		
		member.setEf_c_name(ef_c_name);
		
		List<PtVO> ptAllList = ownerService.ptAllList(member);
		System.out.println("ptAllList ==> " + ptAllList);
		ModelAndView mav = new ModelAndView("/owner/owner_calender");
		mav.addObject("trainer_ptList", ptAllList);
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// pt정보 오너 달력에 가져오기
	//-----------------------------------------------------------------------------------------------------------		
	@ResponseBody
	@RequestMapping(value="/owner/findAllPtList.do", method=RequestMethod.POST)
	public List<PtVO> findPt3(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String ef_c_name  = (String) session.getAttribute("ef_c_name");	
		
		PtVO ptVO = new PtVO();
		ptVO.setEf_c_name(ef_c_name);
		System.out.println("findPt3 ptVO ==> " + ptVO);
		
		List<PtVO> findPt3 = new ArrayList<PtVO>();
 		
		findPt3 = ownerService.findPt3(ptVO);
		System.out.println("findPt3 결과 ==> " + findPt3);
		
		return findPt3;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원기록 페이지 ==> ef_record 리스트 불러오기
	//-----------------------------------------------------------------------------------------------------------		
	@RequestMapping(value="/owner/memRecord.do", method=RequestMethod.GET)
	public ModelAndView memRecord(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<JoinrecordVO> memRecord = ownerService.memRecord();
		ModelAndView mav = new ModelAndView("/owner/owner_memRecord");
		mav.addObject("memRecord", memRecord);
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원기록 페이지 record 삭제
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/owner/memRecordDelete.do", method=RequestMethod.GET)
	public ModelAndView memRecordDelete(@RequestParam("ef_r_time") String ef_r_time, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// System.out.println("controller ef_r_time ==> " + ef_r_time);
		int result = ownerService.memRecordDelete(ef_r_time);
		ModelAndView mav = new ModelAndView("redirect:/owner/memRecord.do");
		return mav;
	}
	
	
	

} // End - public class OwnerControllerImpl
