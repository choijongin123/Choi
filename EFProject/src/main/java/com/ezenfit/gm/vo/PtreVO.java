package com.ezenfit.gm.vo;

import org.springframework.stereotype.Component;

//-----------------------------------------------------------------------------------------------------------
//public class PtreVO
//-----------------------------------------------------------------------------------------------------------
@Component("ptreVO")
public class PtreVO {
	private String ef_p_date;
	private String ef_p_time;
	private String ef_id;
	
	public PtreVO() {
		
	}
	
	public PtreVO(String ef_p_date, String ef_p_time, String ef_id) {
		this.ef_p_date = ef_p_date;
		this.ef_p_time = ef_p_time;
		this.ef_id 	   = ef_id;
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

	public String getEf_id() {
		return ef_id;
	}

	public void setEf_id(String ef_id) {
		this.ef_id = ef_id;
	}

	@Override
	public String toString() {
		return "PtreVO [ef_p_date=" + ef_p_date + ", ef_p_time=" + ef_p_time + ", ef_id=" + ef_id + "]";
	}
	
	
}
