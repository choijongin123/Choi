package com.ezenfit.gm.vo;

import java.sql.Date;


import org.springframework.stereotype.Component;

//-----------------------------------------------------------------------------------------------------------
// public class MemberVO
//-----------------------------------------------------------------------------------------------------------
@Component("memberVO")
public class MemberVO {
	
	private String ef_id;
	private String ef_pwd;
	private String ef_name;
	private String ef_p_number;
	private String ef_email;
	private String ef_c_name;
	private String ef_class;
	private	Date   ef_date;
	

	public MemberVO() {
		
	}
	
	public MemberVO(String ef_id, String ef_pwd, String ef_name, String ef_p_number, String ef_email, String ef_c_name, String ef_class) {
		this.ef_id = ef_id;
		this.ef_pwd = ef_pwd;
		this.ef_name = ef_name;
		this.ef_p_number = ef_p_number;
		this.ef_email = ef_email;
		this.ef_c_name = ef_c_name;
		this.ef_class = ef_class;
	}
	
	public String getEf_id() {
		return ef_id;
	}
	public void setEf_id(String ef_id) {
		this.ef_id = ef_id;
	}
	public String getEf_pwd() {
		return ef_pwd;
	}
	public void setEf_pwd(String ef_pwd) {
		this.ef_pwd = ef_pwd;
	}
	public String getEf_name() {
		return ef_name;
	}
	public void setEf_name(String ef_name) {
		this.ef_name = ef_name;
	}
	public String getEf_p_number() {
		return ef_p_number;
	}
	public void setEf_p_number(String ef_p_number) {
		this.ef_p_number = ef_p_number;
	}
	public String getEf_email() {
		return ef_email;
	}
	public void setEf_email(String ef_email) {
		this.ef_email = ef_email;
	}
	public String getEf_c_name() {
		return ef_c_name;
	}
	public void setEf_c_name(String ef_c_name) {
		this.ef_c_name = ef_c_name;
	}
	public String getEf_class() {
		return ef_class;
	}
	public void setEf_class(String ef_class) {
		this.ef_class = ef_class;
	}

	public Date getEf_date() {
		return ef_date;
	}

	public void setEf_date(Date ef_date) {
		this.ef_date = ef_date;
	}

	@Override
	public String toString() {
		return "MemberVO [ef_id=" + ef_id + ", ef_pwd=" + ef_pwd + ", ef_name=" + ef_name + ", ef_p_number="
				+ ef_p_number + ", ef_email=" + ef_email + ", ef_c_name=" + ef_c_name + ", ef_class=" + ef_class
				+ ", ef_date=" + ef_date + "]";
	}



} // End - public class MemberVO
