package com.ezenfit.gm.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

//-----------------------------------------------------------------------------------------------------------
//public class ArticleReplyVO
//-----------------------------------------------------------------------------------------------------------
@Component("articleReplyVO")
public class ArticleReplyVO {
	
	private	int		ef_i_no;
	private	int		ef_comment;
	private	String	ef_r_content;
	private	String	ef_id;
	private	Date	ef_r_date;
	
	//-----------------------------------------------------------------------------------------------------------
	public ArticleReplyVO() {}
	
	//-----------------------------------------------------------------------------------------------------------
	public ArticleReplyVO(int ef_i_no, int ef_comment, String ef_r_content, String ef_id, Date ef_r_date) {
		super();
		this.ef_i_no			= ef_i_no;
		this.ef_comment			= ef_comment;
		this.ef_r_content		= ef_r_content;
		this.ef_id				= ef_id;
		this.ef_r_date			= ef_r_date;
	}
	//-----------------------------------------------------------------------------------------------------------

	public int getEf_i_no() {
		return ef_i_no;
	}

	public void setEf_i_no(int ef_i_no) {
		this.ef_i_no = ef_i_no;
	}

	public int getEf_comment() {
		return ef_comment;
	}

	public void setEf_comment(int ef_comment) {
		this.ef_comment = ef_comment;
	}

	public String getEf_r_content() {
		return ef_r_content;
	}

	public void setEf_r_content(String ef_r_content) {
		this.ef_r_content = ef_r_content;
	}

	public String getEf_id() {
		return ef_id;
	}

	public void setEf_id(String ef_id) {
		this.ef_id = ef_id;
	}

	public Date getEf_r_date() {
		return ef_r_date;
	}

	public void setEf_r_date(Date ef_r_date) {
		this.ef_r_date = ef_r_date;
	}

	@Override
	public String toString() {
		return "ArticleReplyVO [ef_i_no=" + ef_i_no + ", ef_comment=" + ef_comment + ", ef_r_content=" + ef_r_content
				+ ", ef_id=" + ef_id + ", ef_r_date=" + ef_r_date + "]";
	}

	
} // End - public class ArticleReplyVO
