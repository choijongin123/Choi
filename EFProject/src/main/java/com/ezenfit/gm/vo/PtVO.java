package com.ezenfit.gm.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

//-----------------------------------------------------------------------------------------------------------
//public class PtVO
//-----------------------------------------------------------------------------------------------------------
@Component("ptVO")
public class PtVO {
	private int    ef_p_no;
	private String ef_id;
	private String ef_name;
	private String ef_c_name;
	private String ef_p_type;
	private String ef_p_date;
	private String ef_p_time;
	private String ef_p_personal;
	private String ef_r_personal;
	
	public PtVO() {
		
	}
	
	public PtVO(int ef_p_no, String ef_id, String ef_name, String ef_c_name, String ef_p_type,	String ef_p_date, String ef_p_time, String ef_p_personal, String ef_r_personal) {
		
		this.ef_p_no 		= ef_p_no;
		this.ef_id		 	= ef_id;
		this.ef_name		= ef_name;
		this.ef_c_name		= ef_c_name;
		this.ef_p_type 	 	= ef_p_type;
		this.ef_p_date 		= ef_p_date;
		this.ef_p_time		= ef_p_time;
		this.ef_p_personal  = ef_p_personal;
		this.ef_r_personal  = ef_r_personal;
	}

	public int getEf_p_no() {
		return ef_p_no;
	}

	public void setEf_p_no(int ef_p_no) {
		this.ef_p_no = ef_p_no;
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
	
	public String getEf_c_name() {
		return ef_c_name;
	}

	public void setEf_c_name(String ef_c_name) {
		this.ef_c_name = ef_c_name;
	}

	public String getEf_p_type() {
		return ef_p_type;
	}

	public void setEf_p_type(String ef_p_type) {
		this.ef_p_type = ef_p_type;
	}


	public String getEf_p_date() {
		return ef_p_date;
	}

	public void setEf_p_date(String ef_p_date) {
		this.ef_p_date = ef_p_date;
	}

	public String getEf_p_time() {
		return ef_p_time;
	}

	public void setEf_p_time(String ef_p_time) {
		this.ef_p_time = ef_p_time;
	}

	public String getEf_p_personal() {
		return ef_p_personal;
	}

	public void setEf_p_personal(String ef_p_personal) {
		this.ef_p_personal = ef_p_personal;
	}

	
	public String getEf_r_personal() {
		return ef_r_personal;
	}

	public void setEf_r_personal(String ef_r_personal) {
		this.ef_r_personal = ef_r_personal;
	}

	@Override
	public String toString() {
		return "PtVO [ef_p_no=" + ef_p_no + ", ef_id=" + ef_id + ", ef_name=" + ef_name + ", ef_c_name=" + ef_c_name
				+ ", ef_p_type=" + ef_p_type + ", ef_p_date=" + ef_p_date + ", ef_p_time=" + ef_p_time
				+ ", ef_p_personal=" + ef_p_personal + ", ef_r_personal=" + ef_r_personal + "]";
	}

	


	
	
	
} // End- public class PtVO 
