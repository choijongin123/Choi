package com.ezenfit.gm.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

//-----------------------------------------------------------------------------------------------------------
// public class FreeBoardVO
//-----------------------------------------------------------------------------------------------------------
@Component("freeBoardVO")
public class FreeBoardVO {
	private int 	freeBoard_no;
	private String	freeBoard_notice;
	private String	freeBoard_publicScope;
	private String	freeBoard_title;
	private String	freeBoard_content;
	private String	freeBoard_imageFileName;
	private String	ef_id;
	private Date	freeBoard_writeDate;
	private String	freeBoard_viewsCount;
	private String	freeBoard_commentCount;
	
	
	public FreeBoardVO() {
		
	}
	
	public FreeBoardVO(int freeBoard_no, String	freeBoard_notice, String freeBoard_publicScope, String freeBoard_title, String freeBoard_content, 
			String freeBoard_imageFileName, String ef_id, Date freeBoard_writeDate, String freeBoard_viewsCount, 
			String freeBoard_commentCount) {
		
		this.freeBoard_no 				= freeBoard_no;
		this.freeBoard_notice 			= freeBoard_notice;
		this.freeBoard_publicScope 		= freeBoard_publicScope;
		this.freeBoard_title 			= freeBoard_title;
		this.freeBoard_content 			= freeBoard_content;
		this.freeBoard_imageFileName 	= freeBoard_imageFileName;
		this.ef_id 						= ef_id;
		this.freeBoard_writeDate 		= freeBoard_writeDate;
		this.freeBoard_viewsCount 		= freeBoard_viewsCount;
		this.freeBoard_commentCount     = freeBoard_commentCount;
		
	}

	public int getFreeBoard_no() {
		return freeBoard_no;
	}

	public void setFreeBoard_no(int freeBoard_no) {
		this.freeBoard_no = freeBoard_no;
	}

	
	public String getFreeBoard_notice() {
		return freeBoard_notice;
	}

	public void setFreeBoard_notice(String freeBoard_notice) {
		this.freeBoard_notice = freeBoard_notice;
	}

	public String getFreeBoard_publicScope() {
		return freeBoard_publicScope;
	}

	public void setFreeBoard_publicScope(String freeBoard_PublicScope) {
		this.freeBoard_publicScope = freeBoard_PublicScope;
	}

	public String getFreeBoard_title() {
		return freeBoard_title;
	}

	public void setFreeBoard_title(String freeBoard_title) {
		this.freeBoard_title = freeBoard_title;
	}

	public String getFreeBoard_content() {
		return freeBoard_content;
	}

	public void setFreeBoard_content(String freeBoard_content) {
		this.freeBoard_content = freeBoard_content;
	}

	public String getFreeBoard_imageFileName() {
		return freeBoard_imageFileName;
	}

	public void setFreeBoard_imageFileName(String freeBoard_imageFileName) {
		this.freeBoard_imageFileName = freeBoard_imageFileName;
	}

	public String getEf_id() {
		return ef_id;
	}

	public void setEf_id(String ef_id) {
		this.ef_id = ef_id;
	}

	public Date getFreeBoard_writeDate() {
		return freeBoard_writeDate;
	}

	public void setFreeBoard_writeDate(Date freeBoard_writeDate) {
		this.freeBoard_writeDate = freeBoard_writeDate;
	}

	public String getFreeBoard_viewsCount() {
		return freeBoard_viewsCount;
	}

	public void setFreeBoard_viewsCount(String freeBoard_viewsCount) {
		this.freeBoard_viewsCount = freeBoard_viewsCount;
	}

	public String getFreeBoard_commentCount() {
		return freeBoard_commentCount;
	}

	public void setFreeBoard_commentCount(String freeBoard_commentCount) {
		this.freeBoard_commentCount = freeBoard_commentCount;
	}


	@Override
	public String toString() {
		return "FreeBoardVO [freeBoard_no=" + freeBoard_no + ", freeBoard_notice=" + freeBoard_notice
				+ ", freeBoard_publicScope=" + freeBoard_publicScope + ", freeBoard_title=" + freeBoard_title
				+ ", freeBoard_content=" + freeBoard_content + ", freeBoard_imageFileName=" + freeBoard_imageFileName
				+ ", ef_id=" + ef_id + ", freeBoard_writeDate=" + freeBoard_writeDate + ", freeBoard_viewsCount="
				+ freeBoard_viewsCount + ", freeBoard_commentCount=" + freeBoard_commentCount + ", freeBoard_goodCount="
				+  "]";
	}

	
	
}
