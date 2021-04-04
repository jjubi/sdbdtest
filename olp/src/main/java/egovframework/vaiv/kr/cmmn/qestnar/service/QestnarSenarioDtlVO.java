/*
* 파 일 명 : QestnarSenarioDtlVO.java
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
package egovframework.vaiv.kr.cmmn.qestnar.service;

/**
*  : 설문 시나리오 상세 VO
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
public class QestnarSenarioDtlVO {
	/** 시나리오 일련 번호 */
	private String senarioSeqNo = "";
	/** 설정 조건 순서 */ 
	private String dtrmnCndOrdr = "";
	/** 설정 조건 */
	private String dtrmnCnd = "";
	/** 설정 조건 값 */ 
	private String dtrmnCndValue = "";
	/** 설정 조건 논리 */ 
	private String dtrmnCndLogic = "";
	/** 등록 일자 */ 
	private String registDe = "";
	
	
	
	
	
	
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
	 * @return the dtrmnCndOrdr
	 */
	public String getDtrmnCndOrdr() {
		return dtrmnCndOrdr;
	}
	/**
	 * @param dtrmnCndOrdr the dtrmnCndOrdr to set
	 */
	public void setDtrmnCndOrdr(String dtrmnCndOrdr) {
		this.dtrmnCndOrdr = dtrmnCndOrdr;
	}
	/**
	 * @return the dtrmnCnd
	 */
	public String getDtrmnCnd() {
		return dtrmnCnd;
	}
	/**
	 * @param dtrmnCnd the dtrmnCnd to set
	 */
	public void setDtrmnCnd(String dtrmnCnd) {
		this.dtrmnCnd = dtrmnCnd;
	}
	/**
	 * @return the dtrmnCndValue
	 */
	public String getDtrmnCndValue() {
		return dtrmnCndValue;
	}
	/**
	 * @param dtrmnCndValue the dtrmnCndValue to set
	 */
	public void setDtrmnCndValue(String dtrmnCndValue) {
		this.dtrmnCndValue = dtrmnCndValue;
	}
	/**
	 * @return the dtrmnCndLogic
	 */
	public String getDtrmnCndLogic() {
		return dtrmnCndLogic;
	}
	/**
	 * @param dtrmnCndLogic the dtrmnCndLogic to set
	 */
	public void setDtrmnCndLogic(String dtrmnCndLogic) {
		this.dtrmnCndLogic = dtrmnCndLogic;
	}
	/**
	 * @return the registDe
	 */
	public String getRegistDe() {
		return registDe;
	}
	/**
	 * @param registDe the registDe to set
	 */
	public void setRegistDe(String registDe) {
		this.registDe = registDe;
	}
}
