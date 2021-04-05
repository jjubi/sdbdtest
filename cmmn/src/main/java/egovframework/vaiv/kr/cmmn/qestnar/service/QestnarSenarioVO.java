/*
* 파 일 명 : QestnarSenarioVO.java
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

import java.util.ArrayList;
import java.util.List;

/**
*  : 설문 시나리오 VO
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
public class QestnarSenarioVO {
	/** 설문조사 일련 번호 */
	private String qestnarSeqNo = "";
	/** 시나리오 일련 번호 */
	private String senarioSeqNo = "";
	/** 설정 질문 일련 번호 */ 
	private String dtrmnQestnSeqNo = "";
	/** 대상 질문 일련 번호 */
	private String trgetQestnSeqNo = "";
	/** 등록 일자 */
	private String registDe = "";
	/** 등록 ID */
	private String registId = "";
	
	///////////////////////////////
	
	/** 시나리오 상세 데이터 목록 */
	private List<QestnarSenarioDtlVO> senarioDtlList = new ArrayList<QestnarSenarioDtlVO>();
	
	
	
	/**
	 * @return the senarioDtlList
	 */
	public List<QestnarSenarioDtlVO> getSenarioDtlList() {
		return senarioDtlList;
	}
	/**
	 * @param senarioDtlList the senarioDtlList to set
	 */
	public void setSenarioDtlList(List<QestnarSenarioDtlVO> senarioDtlList) {
		this.senarioDtlList = senarioDtlList;
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
	 * @return the trgetQestnSeqNo
	 */
	public String getTrgetQestnSeqNo() {
		return trgetQestnSeqNo;
	}
	/**
	 * @param trgetQestnSeqNo the trgetQestnSeqNo to set
	 */
	public void setTrgetQestnSeqNo(String trgetQestnSeqNo) {
		this.trgetQestnSeqNo = trgetQestnSeqNo;
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
	/**
	 * @return the registId
	 */
	public String getRegistId() {
		return registId;
	}
	/**
	 * @param registId the registId to set
	 */
	public void setRegistId(String registId) {
		this.registId = registId;
	}
	
}
