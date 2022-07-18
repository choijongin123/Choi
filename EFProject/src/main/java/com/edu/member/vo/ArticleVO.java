package com.edu.member.vo;


import java.sql.Date;

import org.springframework.stereotype.Component;



//-----------------------------------------------------------------------------------------------------------
// 게시글
//-----------------------------------------------------------------------------------------------------------
@Component("articleVO")
public class ArticleVO {

	private	int		ef_level;
	private	int		ef_i_no;
	private	int		ef_parentNO;
	private	String	ef_i_title;
	private	String	ef_content;
	//private	String	ef_imageFileName;
	private	String	ef_id;
	private	Date	ef_writeDate;
	
	//-----------------------------------------------------------------------------------------------------------
	public ArticleVO() {}
	
	//-----------------------------------------------------------------------------------------------------------
	public ArticleVO(int ef_level, int ef_i_no, int ef_parentNO, String ef_i_title, String ef_content,
					  String ef_id) {
		super();
		this.ef_level			= ef_level;
		this.ef_i_no		= ef_i_no;
		this.ef_parentNO		= ef_parentNO;
		this.ef_i_title			= ef_i_title;
		this.ef_content		= ef_content;
		//this.ef_imageFileName	= ef_imageFileName;
		this.ef_id				= ef_id;
	}
	//-----------------------------------------------------------------------------------------------------------

	public int getLevel() {
		return ef_level;
	}

	public void setLevel(int ef_level) {
		this.ef_level = ef_level;
	}

	public int getArticleNO() {
		return ef_i_no;
	}

	public void setArticleNO(int ef_i_no) {
		this.ef_i_no = ef_i_no;
	}

	public int getParentNO() {
		return ef_parentNO;
	}

	public void setParentNO(int ef_parentNO) {
		this.ef_parentNO = ef_parentNO;
	}

	public String getTitle() {
		return ef_i_title;
	}

	public void setTitle(String ef_i_title) {
		this.ef_i_title = ef_i_title;
	}

	public String getContent() {
		return ef_content;
	}

	public void setContent(String ef_content) {
		this.ef_content = ef_content;
	}
/*
	public String getImageFileName() {
		try {
			// 파일이름에 특수문자가 포함되어 있을 경우 인코딩한다.
			if(ef_imageFileName != null && ef_imageFileName.length() != 0) {
				this.ef_imageFileName = URLDecoder.decode(ef_imageFileName, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ef_imageFileName;
	}

	public void setImageFileName(String ef_imageFileName) {
		try {
			// 파일이름에 특수문자가 포함되어 있을 경우 인코딩한다.
			// 길이 체크는 URLEncoder.encode 사용하기 위한 필수 조건이다.
			if(ef_imageFileName != null && ef_imageFileName.length() != 0) {
				this.ef_imageFileName = URLEncoder.encode(ef_imageFileName, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
*/
	public String getId() {
		return ef_id;
	}

	public void setId(String ef_id) {
		this.ef_id = ef_id;
	}

	public Date getWriteDate() {
		return ef_writeDate;
	}

	public void setWriteDate(Date ef_writeDate) {
		this.ef_writeDate = ef_writeDate;
	}

	@Override
	public String toString() {
		return "ArticleVO [ef_level=" + ef_level + ", ef_i_no=" + ef_i_no + ", ef_parentNO=" + ef_parentNO
				+ ", ef_i_title=" + ef_i_title + ", ef_content=" + ef_content + ", ef_id=" + ef_id + ", ef_writeDate="
				+ ef_writeDate + "]";
	}

	
	
} // End - public class ArticleVO






