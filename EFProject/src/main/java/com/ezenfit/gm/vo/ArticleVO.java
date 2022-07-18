package com.ezenfit.gm.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

//-----------------------------------------------------------------------------------------------------------
//public class ArticleVO
//-----------------------------------------------------------------------------------------------------------
@Component("articleVO")
public class ArticleVO {
	
	private	int		ef_i_no;
	private	String	ef_i_title;
	private	String	ef_content;
	private	String	ef_imageFileName;
	private	String	ef_id;
	private	Date	ef_writeDate;
	
	//-----------------------------------------------------------------------------------------------------------
	public ArticleVO() {}
	
	//-----------------------------------------------------------------------------------------------------------
	public ArticleVO(int ef_i_no, String ef_i_title, String ef_content, String ef_imageFileName,
					  String ef_id, Date ef_writeDate) {
		super();
		this.ef_i_no		= ef_i_no;
		this.ef_i_title			= ef_i_title;
		this.ef_content		= ef_content;
		this.ef_writeDate		= ef_writeDate;
		this.ef_imageFileName	= ef_imageFileName;
		this.ef_id				= ef_id;
	}
	//-----------------------------------------------------------------------------------------------------------

	public int getEf_i_no() {
		return ef_i_no;
	}

	public void setEf_i_no(int ef_i_no) {
		this.ef_i_no = ef_i_no;
	}

	public String getEf_i_title() {
		return ef_i_title;
	}

	public void setEf_i_title(String ef_i_title) {
		this.ef_i_title = ef_i_title;
	}

	public String getEf_content() {
		return ef_content;
	}

	public void setEf_content(String ef_content) {
		this.ef_content = ef_content;
	}

	public String getEf_imageFileName() {
		return ef_imageFileName;
	}

	public void setEf_imageFileName(String ef_imageFileName) {
		this.ef_imageFileName = ef_imageFileName;
	}

	public String getEf_id() {
		return ef_id;
	}

	public void setEf_id(String ef_id) {
		this.ef_id = ef_id;
	}

	public Date getEf_writeDate() {
		return ef_writeDate;
	}

	public void setEf_writeDate(Date ef_writeDate) {
		this.ef_writeDate = ef_writeDate;
	}

	@Override
	public String toString() {
		return "ArticleVO [ef_i_no=" + ef_i_no + ", ef_i_title=" + ef_i_title + ", ef_content=" + ef_content
				+ ", ef_imageFileName=" + ef_imageFileName + ", ef_id=" + ef_id + ", ef_writeDate=" + ef_writeDate
				+ "]";
	}

	
	
	
	
} // End - public class ArticleVO
