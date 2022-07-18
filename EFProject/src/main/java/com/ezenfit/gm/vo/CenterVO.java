package com.ezenfit.gm.vo;

import org.springframework.stereotype.Component;

//-----------------------------------------------------------------------------------------------------------
//public class CenterVO
//-----------------------------------------------------------------------------------------------------------
@Component("centerVO")
public class CenterVO {
	private String ef_c_name;
	private String ef_c_class;
	
	public CenterVO() {
		
	}
	
	public CenterVO(String ef_c_name) {
		this.ef_c_name = ef_c_name;
	}

	public String getEf_c_name() {
		return ef_c_name;
	}

	public void setEf_c_name(String ef_c_name) {
		this.ef_c_name = ef_c_name;
	}

	public String getEf_c_class() {
		return ef_c_class;
	}

	public void setEf_c_class(String ef_c_class) {
		this.ef_c_class = ef_c_class;
	}

	@Override
	public String toString() {
		return "CenterVO [ef_c_name=" + ef_c_name + ", ef_c_class=" + ef_c_class + "]";
	}



	
	
}
