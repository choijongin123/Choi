package com.ezenfit.gm.vo;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

//-----------------------------------------------------------------------------------------------------------
//public class RecordVO
//-----------------------------------------------------------------------------------------------------------
@Component("recordVO")
public class RecordVO {
	
	private String 		ef_id;
	private String 		ef_r_count;
	private Date   		ef_start_term;
	private Date   		ef_end_term;
	private String 		ef_p_type;
	private String 		ef_detail;
	private Timestamp 	ef_r_time;
	private String		ef_use;
	
	
	public RecordVO() {
		
	}
	
	public RecordVO(String ef_id, String ef_r_count, Date ef_start_term, Date ef_end_term, String ef_p_type,
					String ef_detail, Timestamp ef_r_time, String ef_use) {
		this.ef_id = ef_id;
		this.ef_r_count = ef_r_count;
		this.ef_start_term = ef_start_term;
		this.ef_end_term = ef_end_term;
		this.ef_p_type = ef_p_type;
		this.ef_detail = ef_detail;
		this.ef_r_time = ef_r_time;
		this.ef_use    = ef_use;
	}

	public String getEf_id() {
		return ef_id;
	}

	public void setEf_id(String ef_id) {
		this.ef_id = ef_id;
	}

	public String getEf_r_count() {
		return ef_r_count;
	}

	public void setEf_r_count(String ef_r_count) {
		this.ef_r_count = ef_r_count;
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

	public String getEf_detail() {
		return ef_detail;
	}

	public void setEf_detail(String ef_detail) {
		this.ef_detail = ef_detail;
	}

	public Timestamp getEf_r_time() {
		return ef_r_time;
	}

	public void setEf_r_time(Timestamp ef_r_time) {
		this.ef_r_time = ef_r_time;
	}

	public String getEf_use() {
		return ef_use;
	}

	public void setEf_use(String ef_use) {
		this.ef_use = ef_use;
	}

	@Override
	public String toString() {
		return "RecordVO [ef_id=" + ef_id + ", ef_r_count=" + ef_r_count + ", ef_start_term=" + ef_start_term
				+ ", ef_end_term=" + ef_end_term + ", ef_p_type=" + ef_p_type + ", ef_detail=" + ef_detail
				+ ", ef_r_time=" + ef_r_time + ", ef_use=" + ef_use + "]";
	}

	
	
}
