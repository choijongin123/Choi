package com.ezenfit.gm.freeboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
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

import com.ezenfit.gm.freeboard.service.FreeBoardService;
import com.ezenfit.gm.vo.Criteria;
import com.ezenfit.gm.vo.FreeBoardCommentVO;
import com.ezenfit.gm.vo.FreeBoardVO;
import com.ezenfit.gm.vo.PageMaker;

//-----------------------------------------------------------------------------------------------------------
//public class FreeBoardControllerImpl implements FreeBoardController
//-----------------------------------------------------------------------------------------------------------
@Controller("freeBoardController")
public class FreeBoardControllerImpl implements FreeBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(FreeBoardControllerImpl.class);
	
	@Autowired
	private FreeBoardService freeBoardService;
	
	@Autowired
	private FreeBoardVO freeBoardVO;
	
	private static String FREEBOARD_IMAGE_REPO = "C:\\data\\freeBoard_image";
	
	//-----------------------------------------------------------------------------------------------------------
	// 자유게시판(freeboard) 메뉴
	//-----------------------------------------------------------------------------------------------------------		
	@RequestMapping(value="/freeboard/freeboardMenu.do", method=RequestMethod.GET)
	public ModelAndView freeBoardList(Criteria cri, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		List<FreeBoardVO> freeBoardList = freeBoardService.freeBoardList(cri);
		System.out.println("freeBoardList ==> " + freeBoardList);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(freeBoardService.listCount()); 
		
		ModelAndView mav = new ModelAndView("/freeboard/freeboard");
		mav.addObject("freeBoardList", freeBoardList);
		mav.addObject("pageMaker", pageMaker);
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 자유게시판 글 작성 페이지 이동
	//-----------------------------------------------------------------------------------------------------------		
	@RequestMapping(value="/freeboard/freeboardWritePosts.do", method=RequestMethod.GET)
	public ModelAndView freeboardWritePosts(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/freeboard/freeboardWritePosts");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 추가
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/freeboard/addPosts.do", method=RequestMethod.POST)
	public ModelAndView addPosts(@ModelAttribute("FreeBoardVO") FreeBoardVO freeBoardVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("addPosts ==>" + freeBoardVO);

		ModelAndView mav = new ModelAndView();
		
		Map<String, String> freeBoardMap	= upload(request, response);//
		
		System.out.println("bookInfoMap ==> " + freeBoardMap);
		freeBoardVO.setEf_id(freeBoardMap.get("ef_id"));
		freeBoardVO.setFreeBoard_publicScope(freeBoardMap.get("freeBoard_publicScope"));
		freeBoardVO.setFreeBoard_content(freeBoardMap.get("freeBoard_content"));
		freeBoardVO.setFreeBoard_imageFileName(freeBoardMap.get("freeBoard_imageFileName"));
		freeBoardVO.setFreeBoard_title(freeBoardMap.get("freeBoard_title"));
		freeBoardVO.setFreeBoard_notice(freeBoardMap.get("freeBoard_notice"));
		System.out.println("freeBoardVO ==> " + freeBoardVO);
		int result = freeBoardService.addPosts(freeBoardVO);
		System.out.println("게시글 추가 controller 결과 freeboard_no ==> " + result);

		
		if(freeBoardMap.get("freeBoard_imageFileName") != null && freeBoardMap.get("freeBoard_imageFileName").length() != 0) {
			File srcFile = new File(FREEBOARD_IMAGE_REPO + "\\" + "temp" + "\\" + freeBoardMap.get("freeBoard_imageFileName"));
			File destDir = new File(FREEBOARD_IMAGE_REPO + "\\" + result);
			
			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
		}

		mav = new ModelAndView("redirect:/freeboard/freeboardMenu.do");
		
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 보기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/freeboard/freeBoardViewPosts.do", method=RequestMethod.GET)
	public ModelAndView freeBoardViewPosts(@RequestParam("freeBoard_no") int freeBoard_no, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// System.out.println("freeBoardView freeBoard_no ==> " + freeBoard_no);
		freeBoardVO = freeBoardService.freeBoardViewPosts(freeBoard_no);
		// System.out.println("freeBoardView Controller ==> " + freeBoardVO);
		List<FreeBoardCommentVO> freeBoardViewCommentsList = freeBoardService.freeBoardViewCommentsList(freeBoard_no);
		ModelAndView mav = new ModelAndView("/freeboard/freeboardViewPosts");
		mav.addObject("freeboard", freeBoardVO);
		mav.addObject("freeBoardViewCommentsList", freeBoardViewCommentsList);
		return mav;
	} 	
	
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 수정 하기
	//-----------------------------------------------------------------------------------------------------------		
	@Override
	@RequestMapping(value="/freeboard/modPosts.do", method=RequestMethod.POST)
	public ModelAndView modPosts(@ModelAttribute("FreeBoardVO") FreeBoardVO freeBoardVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// System.out.println("수정하기 ==> " + freeBoardVO);
		ModelAndView mav = new ModelAndView();
		Map<String, String> freeBoardMap	= upload(request, response);
		// System.out.println("수정하기 freeBoardMap ==> " + freeBoardMap.get("freeBoard_imageFileName"));
		freeBoardVO.setFreeBoard_no(Integer.parseInt(freeBoardMap.get("freeBoard_no")));
		freeBoardVO.setEf_id(freeBoardMap.get("ef_id"));
		freeBoardVO.setFreeBoard_publicScope(freeBoardMap.get("freeBoard_publicScope"));
		freeBoardVO.setFreeBoard_content(freeBoardMap.get("freeBoard_content"));
		if(freeBoardMap.get("freeBoard_imageFileName") == null || freeBoardMap.get("freeBoard_imageFileName").length() == 0) { // 수정하기시 이미지 변경을 안하고 내용만 변경하는 경우
			freeBoardVO.setFreeBoard_imageFileName(freeBoardMap.get("originalImageFileName"));
		} else {
			freeBoardVO.setFreeBoard_imageFileName(freeBoardMap.get("freeBoard_imageFileName"));
		}
		freeBoardVO.setFreeBoard_title(freeBoardMap.get("freeBoard_title"));
		freeBoardVO.setFreeBoard_notice(freeBoardMap.get("freeBoard_notice"));
		// System.out.println("수정하기 freeBoardVO ==> " + freeBoardVO.getFreeBoard_imageFileName());
		
		int result = freeBoardService.modPosts(freeBoardVO);
		
		if(freeBoardMap.get("freeBoard_imageFileName") != null && freeBoardMap.get("freeBoard_imageFileName").length() != 0) {
			File srcFile = new File(FREEBOARD_IMAGE_REPO + "\\" + "temp" + "\\" + freeBoardMap.get("freeBoard_imageFileName"));
			File destDir = new File(FREEBOARD_IMAGE_REPO + "\\" + freeBoardVO.getFreeBoard_no());
			
			
			File oldFile = new File(FREEBOARD_IMAGE_REPO + "\\" + freeBoardVO.getFreeBoard_no() + "\\" + freeBoardMap.get("originalImageFileName"));
			oldFile.delete();
			
			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);

		}
		
		mav = new ModelAndView("redirect:/freeboard/freeBoardViewPosts.do");
		mav.addObject("freeBoard_no", freeBoardVO.getFreeBoard_no());
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 삭제
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/freeboard/freeBoardDeletePosts.do", method=RequestMethod.GET)
	public ModelAndView freeBoardDeletePosts(@RequestParam("freeBoard_no") int freeBoard_no, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		System.out.println("freeBoardViewPosts freeBoard_no ==> " + freeBoard_no);
		
		int result = freeBoardService.freeBoardDeletePosts(freeBoard_no);
		System.out.println("freeBoardViewPosts Controller ==> " + result);
		
		File imgDir = new File(FREEBOARD_IMAGE_REPO + "\\" + freeBoard_no);
		if(imgDir.exists()) {
			FileUtils.deleteDirectory(imgDir);
		}
		
		ModelAndView mav = new ModelAndView("redirect:/freeboard/freeboardMenu.do");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 댓글 추가하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/freeboard/addComments.do", method=RequestMethod.POST)
	public ModelAndView addComments(@ModelAttribute("FreeBoardCommentVO") FreeBoardCommentVO freeBoardCommentVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("addPosts freeBoardCommentVO ==> " + freeBoardCommentVO);
			
		int result = freeBoardService.addComments(freeBoardCommentVO);
		System.out.println("addPosts Controller ==> " + result);		
			
		int freeBoard_no = freeBoardCommentVO.getFreeBoard_no();
			
		ModelAndView mav = new ModelAndView("redirect:/freeboard/freeBoardViewPosts.do");
		mav.addObject("freeBoard_no", freeBoard_no);
			
		return mav;
	}
		
	//-----------------------------------------------------------------------------------------------------------
	// 댓글 삭제
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/freeboard/deleteComments.do", method=RequestMethod.GET)
	public ModelAndView deleteComments(@RequestParam("freeBoard_commentNO") int freeBoard_commentNO, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		// 페이지 이동할 때 필요한 게시글 번호를 가여져오기 위함
		FreeBoardCommentVO freeBoardCommentVO = freeBoardService.fbcvo(freeBoard_commentNO);
		System.out.println("게시글 번호를 가져오기 위한 댓글 정보 ==> " + freeBoardCommentVO);
		int freeBoard_no = freeBoardCommentVO.getFreeBoard_no();
		
		int result = freeBoardService.deleteComments(freeBoardCommentVO);
		System.out.println("DeleteComments Controller 결과 ==> " + result);
		
		ModelAndView mav = new ModelAndView("redirect:/freeboard/freeBoardViewPosts.do");
		mav.addObject("freeBoard_no", freeBoard_no);
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 답글 추가하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/freeboard/addReply.do", method=RequestMethod.POST)
	public ModelAndView addReply(FreeBoardCommentVO freeBoardCommentVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("답글달기 vo ==> " + freeBoardCommentVO);
		// 답글달기도 댓들달기랑 같음
		int result = freeBoardService.addComments(freeBoardCommentVO);
		ModelAndView mav = new ModelAndView("redirect:/freeboard/freeBoardViewPosts.do");
		mav.addObject("freeBoard_no", freeBoardCommentVO.getFreeBoard_no());
			
		return mav;
	}

	
	//-----------------------------------------------------------------------------------------------------------
	// 답글 리스트 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/freeboard/replyList.do", method=RequestMethod.POST)
	public List<FreeBoardCommentVO> replyList(@RequestParam("freeBoard_commentParentsNO") int freeBoard_commentParentsNO, FreeBoardCommentVO freeBoardCommentVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("답글리스트 불러오기 ==> " + freeBoard_commentParentsNO);
		
		List<FreeBoardCommentVO> replyList = freeBoardService.replyList(freeBoard_commentParentsNO);
		System.out.println("답글 리스트 결과 ==> " + replyList);
		
		return replyList;
	}
	

	//-----------------------------------------------------------------------------------------------------------
	// 파일 올리기 메서드
	//-----------------------------------------------------------------------------------------------------------
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		Map<String, String> freeBoardMap = new HashMap<String, String>();
		String encoding	=	"utf-8";
		
		// 업로드할 파일의 경로를 지정한다.
		File				currentDirPath		= new File(FREEBOARD_IMAGE_REPO);
		
		DiskFileItemFactory	factory				= new DiskFileItemFactory();
		
		// 파일 경로를 설정한다.
		factory.setRepository(currentDirPath);
		
		// 업로드될 파일의 최대 크기를 설정한다.
		factory.setSizeThreshold(1024*1024*1024);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			// request객체에서 매개 변수를 List로 가져온다.
			List items = upload.parseRequest(request);
			
			for(int i = 0; i < items.size(); i++) {
				//	파일 업로드 창에서 업로드된 항목들을 하나씩 가져와서 작업을 한다.
				FileItem fileItem = (FileItem) items.get(i);
				
				// 폼 필드이면 전송된 매개 변수의 값을 출력한다.
				if(fileItem.isFormField()) {
					freeBoardMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else { // 폼 필드가 아니면 파일 업로드 기능을 실행한다.
					// 업로드한 파일의 이름을 가져온다.
					// 파일의 사이즈가 0보다 큰 경우만 업로드를 한다.
					if(fileItem.getSize() > 0) {
						// 변수.lastIndexOf(검색값) => 변수에서 검색값 들 중에서 마지막 것을 말한다.
						int idx = fileItem.getName().lastIndexOf("\\");
						if(idx == -1) { // 이런(\\) 경로가 아니라면 / 경로의 마지막에서 파일이름을 찾는다.
							idx = fileItem.getName().lastIndexOf("/");
						}
						
						// 경로에서 파일 이름을 추출한다.
						// "ABCDEFGHIJ"
						// substring(4) = > 인덱스번호 4이상 모든 값 => EFGHIJ
						// substring(3, 7) => 인덱스번호 3번 부터 7번 전까지 => DEFG
						String fileName = fileItem.getName().substring(idx+1);
						
						
						// 업로드한 파일의 이름으로 저장소(currentDirPath)에 파일을 업로드 한다.
						// File uploadFile = new File(currentDirPath + "\\" + fileName);
						// 파일이름이 중복되면 마지막에 올린 파일만 존재하게 되므로 임시 파일에 저장시키고,
						// 책 번호를 부여받게 되면 책 번호의 폴더를 만들어서 저장시키도록 한다.
						// upload()를 호출한 곳으로 bookInfoMap에 파일 정보를 넣어준다.
						freeBoardMap.put(fileItem.getFieldName(), fileName);
						
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						fileItem.write(uploadFile);
					}
					
				} // End - if
				
			} // End - for
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return freeBoardMap;
	}

	
	
	
} // End - public class FreeBoardControllerImpl implements FreeBoardController
