package com.ezenfit.gm.vo;

import org.springframework.stereotype.Component;

//-----------------------------------------------------------------------------------------------------------
//public class ReservationVO
//-----------------------------------------------------------------------------------------------------------
@Component("ReservationVO")
public class ReservationVO {
	private int ef_p_no;
	private String ef_id;
	private String ef_name;
	private String ef_p_number;
	
	public ReservationVO() {
		
	}
	
	public ReservationVO(int ef_p_no, String ef_id, String ef_name, String ef_p_number) {
		
		this.ef_p_no		= ef_p_no;
		this.ef_id		 	= ef_id;
		this.ef_name		= ef_name;
		this.ef_p_number	= ef_p_number;
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

	public String getEf_p_number() {
		return ef_p_number;
	}

	public void setEf_p_number(String ef_p_number) {
		this.ef_p_number = ef_p_number;
	}

	@Override
	public String toString() {
		return "ReservationVO [ef_p_no=" + ef_p_no + ", ef_id=" + ef_id + ", ef_name=" + ef_name + ", ef_p_number="
				+ ef_p_number + "]";
	}
	
	
}
