/*
* 파 일 명 : qestnar > SearchVO.java
* 작성일시 : 2020.12.31
* 작 성 자 : jo
* 수정이력
*
* 수정일      수정자        수정내용
*---------------   --------------   ------------------------------------
* 2020.12.31   jo      최초등록
* 
*********************************************************************************
* Copyright 2020 VAIV Co.
* All rights reserved
*/
package egovframework.vaiv.kr.cmmn.qestnar.common;

import egovframework.com.cmm.ComDefaultVO;

/**
*  : 설문조사 검색 VO
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
public class SearchVO extends ComDefaultVO{
	/** 설문조사 일련 번호 */
	private String qestnarSeqNo = "";
	/** 설문조사 기간 체크 여부 */
	private String qestnarPdCheckYn = "";
	/** 설문 문항 일련 번호 */
	private String qestnSeqNo = "";
	/** 사용 여부 */
	private String useAt = "";
	/** 설문 문항 페이지 번호 */
	private String qestnPge = "";
	/** 대상 질문 일련 번호 */
	private String dtrmnQestnSeqNo = "";
	/** 시나리오 일련 번호 */
	private String senarioSeqNo = "";
	
	
	

	/**
	 * @return the senarioSeqNo
	 */
	public String getSenarioSeqNo() {
		return senarioSeqNo;
	}

	/**
	 * @param senarioSeqNo the senarioSeqNo to set
	 */
	public void setSenarioSeqNo(String senarioSeqNo) {
		this.senarioSeqNo = senarioSeqNo;
	}

	/**
	 * @return the dtrmnQestnSeqNo
	 */
	public String getDtrmnQestnSeqNo() {
		return dtrmnQestnSeqNo;
	}

	/**
	 * @param dtrmnQestnSeqNo the dtrmnQestnSeqNo to set
	 */
	public void setDtrmnQestnSeqNo(String dtrmnQestnSeqNo) {
		this.dtrmnQestnSeqNo = dtrmnQestnSeqNo;
	}

	/**
	 * @return the useAt
	 */
	public String getUseAt() {
		return useAt;
	}

	/**
	 * @param useAt the useAt to set
	 */
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	/**
	 * @return the qestnPge
	 */
	public String getQestnPge() {
		return qestnPge;
	}

	/**
	 * @param qestnPge the qestnPge to set
	 */
	public void setQestnPge(String qestnPge) {
		this.qestnPge = qestnPge;
	}

	/**
	 * @return the qestnSeqNo
	 */
	public String getQestnSeqNo() {
		return qestnSeqNo;
	}

	/**
	 * @param qestnSeqNo the qestnSeqNo to set
	 */
	public void setQestnSeqNo(String qestnSeqNo) {
		this.qestnSeqNo = qestnSeqNo;
	}

	/**
	 * @return the qestnarPdCheckYn
	 */
	public String getQestnarPdCheckYn() {
		return qestnarPdCheckYn;
	}

	/**
	 * @param qestnarPdCheckYn the qestnarPdCheckYn to set
	 */
	public void setQestnarPdCheckYn(String qestnarPdCheckYn) {
		this.qestnarPdCheckYn = qestnarPdCheckYn;
	}

	/**
	 * @return the qestnarSeqNo
	 */
	public String getQestnarSeqNo() {
		return qestnarSeqNo;
	}

	/**
	 * @param qestnarSeqNo the qestnarSeqNo to set
	 */
	public void setQestnarSeqNo(String qestnarSeqNo) {
		this.qestnarSeqNo = qestnarSeqNo;
	}
	
}
