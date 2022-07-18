package com.ezenfit.gm.vo;

import org.springframework.stereotype.Component;

//-----------------------------------------------------------------------------------------------------------
// public class FreeBoardCommentVO
//-----------------------------------------------------------------------------------------------------------
@Component("freeBoardCommentVO")
public class FreeBoardCommentVO {
	private int 	freeBoard_commentNO;
	private String  freeBoard_commentLevel;
	private String	freeBoard_commentContent;
	private int		freeBoard_commentParentsNO;
	private int		freeBoard_no;
	private String	ef_id;
	private String	freeBoard_commentWriteDate;
	private String  freeBoard_reCommentCount;

	
	
	public FreeBoardCommentVO() {
		
	}
	
	public FreeBoardCommentVO(int freeBoard_commentNO, String freeBoard_commentLevel, String freeBoard_commentContent, int freeBoard_commentParentsNO, 
			int freeBoard_no, String ef_id, String freeBoard_commentWriteDate, String freeBoard_reCommentCount) {
		
		this.freeBoard_commentNO 			= freeBoard_commentNO;
		this.freeBoard_commentLevel			= freeBoard_commentLevel;
		this.freeBoard_commentContent 		= freeBoard_commentContent;
		this.freeBoard_commentParentsNO 	= freeBoard_commentParentsNO;
		this.freeBoard_no 					= freeBoard_no;
		this.ef_id 							= ef_id;
		this.freeBoard_commentWriteDate 	= freeBoard_commentWriteDate;
		this.freeBoard_reCommentCount       = freeBoard_reCommentCount;
	}

	public int getFreeBoard_commentNO() {
		return freeBoard_commentNO;
	}

	public void setFreeBoard_commentNO(int freeBoard_commentNO) {
		this.freeBoard_commentNO = freeBoard_commentNO;
	}

	public String getFreeBoard_commentLevel() {
		return freeBoard_commentLevel;
	}

	public void setFreeBoard_commentLevel(String freeBoard_commentLevel) {
		this.freeBoard_commentLevel = freeBoard_commentLevel;
	}

	public String getFreeBoard_commentContent() {
		return freeBoard_commentContent;
	}

	public void setFreeBoard_commentContent(String freeBoard_commentContent) {
		this.freeBoard_commentContent = freeBoard_commentContent;
	}

	public int getFreeBoard_commentParentsNO() {
		return freeBoard_commentParentsNO;
	}

	public void setFreeBoard_commentParentsNO(int freeBoard_commentParentsNO) {
		this.freeBoard_commentParentsNO = freeBoard_commentParentsNO;
	}

	public int getFreeBoard_no() {
		return freeBoard_no;
	}

	public void setFreeBoard_no(int freeBoard_no) {
		this.freeBoard_no = freeBoard_no;
	}

	public String getEf_id() {
		return ef_id;
	}

	public void setEf_id(String ef_id) {
		this.ef_id = ef_id;
	}

	public String getFreeBoard_commentWriteDate() {
		return freeBoard_commentWriteDate;
	}

	public void setFreeBoard_commentWriteDate(String freeBoard_commentWriteDate) {
		this.freeBoard_commentWriteDate = freeBoard_commentWriteDate;
	}

	public String getFreeBoard_reCommentCount() {
		return freeBoard_reCommentCount;
	}

	public void setFreeBoard_reCommentCount(String freeBoard_reCommentCount) {
		this.freeBoard_reCommentCount = freeBoard_reCommentCount;
	}

	@Override
	public String toString() {
		return "FreeBoardCommentVO [freeBoard_commentNO=" + freeBoard_commentNO + ", freeBoard_commentLevel="
				+ freeBoard_commentLevel
				+ ", freeBoard_commentContent=" + freeBoard_commentContent + ", freeBoard_commentParentsNO="
				+ freeBoard_commentParentsNO + ", freeBoard_no=" + freeBoard_no + ", ef_id=" + ef_id
				+ ", freeBoard_commentWriteDate=" + freeBoard_commentWriteDate + ", freeBoard_reCommentCount="
				+ freeBoard_reCommentCount + "]";
	}

	
	

		
	
}
