package com.ezenfit.gm.article.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.ezenfit.gm.article.service.ArticleService;
import com.ezenfit.gm.vo.ArticleReplyVO;
import com.ezenfit.gm.vo.ArticleVO;
import com.ezenfit.gm.vo.Criteria;
import com.ezenfit.gm.vo.PageMaker;



//-----------------------------------------------------------------------------------------------------------
//public class ArticleControllerImpl implements ArticleController
//-----------------------------------------------------------------------------------------------------------
@Controller("ArticleController")
public class ArticleControllerImpl implements ArticleController {
	
	private static final Logger logger = LoggerFactory.getLogger(ArticleControllerImpl.class);
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ArticleVO articleVO;
	
	private static String ARTI_IMAGE_REPO = "C:\\data\\article_image";
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의하기 목록 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/article/inquiry.do", method=RequestMethod.GET)
	public ModelAndView inquiryList(Criteria cri, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List<ArticleVO> inquiryList = articleService.inquiryList(cri);	// 문의하기 목록 보여주기	
		System.out.println("inquiryList ==> " + inquiryList);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(articleService.listCount()); 
		
		ModelAndView mav = new ModelAndView("/article/inquiry");
		mav.addObject("inquiryList", inquiryList);
		mav.addObject("pageMaker", pageMaker);
		return mav;
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 글 작성 페이지 이동
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/article/inqWrite.do", method=RequestMethod.GET)
	public ModelAndView inqWriteForm(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/article/inqWrite");
		return mav;
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 작성하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/article/addWrite.do", method=RequestMethod.POST)
	   public ModelAndView addInquiry(@ModelAttribute("articleVO") ArticleVO articleVO, HttpServletRequest request, HttpServletResponse response)
	         throws Exception {

	      request.setCharacterEncoding("utf-8");
	      response.setContentType("text/html;charset=utf-8");

	      System.out.println("Controller에서 받은 ArticleVO ==> " + articleVO);
	      Map<String, String> articleMap	= upload(request, response);
		  System.out.println("bookInfoMap ==> " + articleMap);
		 
		 articleVO.setEf_id(articleMap.get("ef_id"));
		 articleVO.setEf_content(articleMap.get("ef_content"));
		 articleVO.setEf_imageFileName(articleMap.get("ef_imageFileName"));
		 articleVO.setEf_i_title(articleMap.get("ef_i_title"));
						
	     int result = articleService.addInquiry(articleVO);
	     System.out.println("Controller에서 받은 result" + result);
	     if(articleMap.get("ef_imageFileName") != null && articleMap.get("ef_imageFileName").length() != 0) {
			File srcFile = new File(ARTI_IMAGE_REPO + "\\" + "temp" + "\\" + articleMap.get("ef_imageFileName"));
			File destDir = new File(ARTI_IMAGE_REPO + "\\" + result);

			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
		 }
	     ModelAndView mav = new ModelAndView("redirect:/article/inquiry.do");
	     return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 게시글현황 페이지 불러오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/article/inqStatus.do", method=RequestMethod.GET)
	public ModelAndView inqSituation(@RequestParam("ef_i_no") int ef_i_no, HttpServletRequest request, HttpServletResponse response)
	         throws Exception {
		
		System.out.println("Controller에서 받은 ef_i_no  ==> " + ef_i_no);
		
		articleVO = articleService.inqSituation(ef_i_no);
		
		List<ArticleReplyVO> replyList = articleService.replyList(ef_i_no);
		System.out.println("Controller에서 받은 articleReplyVO  ==> " + ef_i_no);
		System.out.println("문의하기 불러오기 ==> " + articleVO);
		System.out.println("Controller에서 받은 replyList  ==> " + replyList);
		//mav = new ModelAndView("redirect:/member/inqUpdate.do");	--잘못된 경로
		ModelAndView mav = new ModelAndView("/article/inqStatus");
		mav.addObject("article", articleVO);
		mav.addObject("replyList", replyList);
		
		return mav;		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 수정반영하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/article/addUpdate.do", method=RequestMethod.POST)
	public ModelAndView updateReflect(@ModelAttribute("articleVO") ArticleVO articleVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ModelAndView mav = new ModelAndView();
		System.out.println("수정반영하기");
		Map<String, String> articleMap	= upload(request, response);
		System.out.println("bookInfoMap ==> " + articleMap);
		articleVO.setEf_i_no(Integer.parseInt(articleMap.get("ef_i_no")));
		articleVO.setEf_id(articleMap.get("ef_id"));
		articleVO.setEf_content(articleMap.get("ef_content"));
		if(articleMap.get("ef_imageFileName") == null || articleMap.get("ef_imageFileName").length() == 0) { // 수정하기시 이미지 변경을 안하고 내용만 변경하는 경우
			articleVO.setEf_imageFileName(articleMap.get("origimageFileName"));
		} else {
			articleVO.setEf_imageFileName(articleMap.get("ef_imageFileName"));
		}
		articleVO.setEf_i_title(articleMap.get("ef_i_title"));
		
		System.out.println("Controller에서 받은 articleVO  ==> " + articleVO);
		
		int result = articleService.updateReflect(articleVO);
		System.out.println("updateReflect Controller에서 받은 result 결과 ==> " + result);
		
		if(articleMap.get("ef_imageFileName") != null && articleMap.get("ef_imageFileName").length() != 0) {
			File srcFile = new File(ARTI_IMAGE_REPO + "\\" + "temp" + "\\" + articleMap.get("ef_imageFileName"));
			File destDir = new File(ARTI_IMAGE_REPO + "\\" + articleVO.getEf_i_no());
			
			File	oldFile	= new File(ARTI_IMAGE_REPO + "\\" + articleVO.getEf_i_no() + "\\" + articleMap.get("origimageFileName"));
			oldFile.delete();
			
			destDir.mkdirs();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
		}
		
		mav = new ModelAndView("redirect:/article/inqStatus.do");		
		int ef_i_no = articleVO.getEf_i_no();
	    mav.addObject("ef_i_no", ef_i_no);
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 문의사항 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/article/removeArticle.do", method=RequestMethod.POST)
	public ModelAndView deleteInquiry(@RequestParam("ef_i_no") int ef_i_no, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("Controller에서 받은 ef_i_no  ==> " + ef_i_no);
		
		ModelAndView mav = new ModelAndView();
		int result = articleService.deleteInquiry(ef_i_no);
		
		File imgDir = new File(ARTI_IMAGE_REPO + "\\" + ef_i_no);
		if(imgDir.exists()) {
			FileUtils.deleteDirectory(imgDir);
		}
		
		mav = new ModelAndView("redirect:/article/inquiry.do");	
		
		return mav;		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 답글반영하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/article/addReply.do", method=RequestMethod.POST)
	public ModelAndView addReply(@ModelAttribute("articleReplyVO") ArticleReplyVO articleReplyVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		  request.setCharacterEncoding("utf-8");
	      response.setContentType("text/html;charset=utf-8");
	        
	      ModelAndView mav = new ModelAndView();
	      System.out.println("Controller에서 받은 ArticleReplyVO ==> " + articleReplyVO);
			
	      int result = articleService.addReply(articleReplyVO);
	      System.out.println("addReply Controller에서 받은 result 결과 ==> " + result);
			
	      mav = new ModelAndView("redirect:/article/inqStatus.do");	      	      
	      
	      int ef_i_no = articleReplyVO.getEf_i_no();
	      mav.addObject("ef_i_no", ef_i_no);
	      
	      return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 파일 올리기 메서드
	//-----------------------------------------------------------------------------------------------------------
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
			
			Map<String, String> articleMap = new HashMap<String, String>();
			String encoding	=	"utf-8";
			
			// 업로드할 파일의 경로를 지정한다.
			File				currentDirPath		= new File(ARTI_IMAGE_REPO);
			
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
						articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
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
							articleMap.put(fileItem.getFieldName(), fileName);
							
							File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
							fileItem.write(uploadFile);
						}
						
					} // End - if
					
				} // End - for
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return articleMap;
		}

	//-----------------------------------------------------------------------------------------------------------
	// 답글 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/article/deleteReply.do", method=RequestMethod.GET)
	public ModelAndView deleteReply(@RequestParam("ef_i_no") int ef_i_no, @RequestParam("ef_comment") int ef_comment, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		System.out.println("Controller에서 받은 ef_comment  ==> " + ef_comment);
		
		ModelAndView mav = new ModelAndView();
		int result = articleService.deleteReply(ef_comment);
		
		mav = new ModelAndView("redirect:/article/inqStatus.do");	
		
	    mav.addObject("ef_i_no", ef_i_no);
	      
		return mav;	
	}	
	
	
	
} // End - public class ArticleControllerImpl implements ArticleController
