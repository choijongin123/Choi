package com.edu.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

//-----------------------------------------------------------------------------------------------------------
//public class JoinregiVO
//-----------------------------------------------------------------------------------------------------------
@Component("joinregiVO")
public class JoinregiVO {
	
	private String ef_id;
	private String ef_name;
	private String ef_email;
	private String ef_p_number;
	private String ef_count;
	private String ef_term;
	private Date   ef_start_term;
	private Date   ef_end_term;
	private String ef_p_type;
	
	public JoinregiVO() {
		
	}

	public String getEf_id() {
		return ef_id;
	}

	public void setEf_id(String ef_id) {
		this.ef_id = ef_id;
	}

	public String getEf_name() {
		return ef_name;
	}

	public void setEf_name(String ef_name) {
		this.ef_name = ef_name;
	}

	public String getEf_email() {
		return ef_email;
	}

	public void setEf_email(String ef_email) {
		this.ef_email = ef_email;
	}

	public String getEf_p_number() {
		return ef_p_number;
	}

	public void setEf_p_number(String ef_p_number) {
		this.ef_p_number = ef_p_number;
	}

	public String getEf_count() {
		return ef_count;
	}

	public void setEf_count(String ef_count) {
		this.ef_count = ef_count;
	}

	public String getEf_term() {
		return ef_term;
	}

	public void setEf_term(String ef_term) {
		this.ef_term = ef_term;
	}

	public Date getEf_start_term() {
		return ef_start_term;
	}

	public void setEf_start_term(Date ef_start_term) {
		this.ef_start_term = ef_start_term;
	}

	public Date getEf_end_term() {
		return ef_end_term;
	}

	public void setEf_end_term(Date ef_end_term) {
		this.ef_end_term = ef_end_term;
	}

	public String getEf_p_type() {
		return ef_p_type;
	}

	public void setEf_p_type(String ef_p_type) {
		this.ef_p_type = ef_p_type;
	}

	@Override
	public String toString() {
		return "JoinregiVO [ef_id=" + ef_id + ", ef_name=" + ef_name + ", ef_email=" + ef_email + ", ef_p_number="
				+ ef_p_number + ", ef_count=" + ef_count + ", ef_term=" + ef_term + ", ef_start_term=" + ef_start_term
				+ ", ef_end_term=" + ef_end_term + ", ef_p_type=" + ef_p_type + "]";
	}

	
	
	
}
