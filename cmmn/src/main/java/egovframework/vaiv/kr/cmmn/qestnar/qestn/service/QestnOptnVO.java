/*
* 파 일 명 : QestnOptnVO.java
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
package egovframework.vaiv.kr.cmmn.qestnar.qestn.service;

/**
*  : 설문 문항 옵션 VO
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
public class QestnOptnVO {
	/** 질문 일련 번호 */
	private String qestnSeqNo = "";
	/** 옵션 일련 번호 */
	private String optnSeqNo = "";
	/** 옵션 유형 */ 
	private String optnTy = "";
	/** 옵션 값 */
	private String optnValue = "";
	/** 등록 일자 */ 
	private String registDe = "";
	
	
	
	
	
	
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
	 * @return the optnSeqNo
	 */
	public String getOptnSeqNo() {
		return optnSeqNo;
	}
	/**
	 * @param optnSeqNo the optnSeqNo to set
	 */
	public void setOptnSeqNo(String optnSeqNo) {
		this.optnSeqNo = optnSeqNo;
	}
	/**
	 * @return the optnTy
	 */
	public String getOptnTy() {
		return optnTy;
	}
	/**
	 * @param optnTy the optnTy to set
	 */
	public void setOptnTy(String optnTy) {
		this.optnTy = optnTy;
	}
	/**
	 * @return the optnValue
	 */
	public String getOptnValue() {
		return optnValue;
	}
	/**
	 * @param optnValue the optnValue to set
	 */
	public void setOptnValue(String optnValue) {
		this.optnValue = optnValue;
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
