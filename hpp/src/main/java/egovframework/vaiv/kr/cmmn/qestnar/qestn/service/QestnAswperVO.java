/*
* 파 일 명 : QestnAswperVO.java
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
*  : 설문 문항 선택형 답안 VO
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
public class QestnAswperVO {
	/** 질문 일련 번호 */
	private String qestnSeqNo = "";
	/** 답안 일련 번호 */
	private String aswperSeqNo = "";
	/** 답안 텍스트 */ 
	private String aswperText = "";
	/** 답안 점수 */
	private String aswperScore = "";
	/** 답안 이미지 */
	private String aswperImage = "";
	/** 답안 기타 여부 */
	private String aswperEtcAt = "";
	/** 등록 일자 */ 
	private String registDe = "";
	
	
	
	
	/**
	 * @return the aswperEtcAt
	 */
	public String getAswperEtcAt() {
		return aswperEtcAt;
	}
	/**
	 * @param aswperEtcAt the aswperEtcAt to set
	 */
	public void setAswperEtcAt(String aswperEtcAt) {
		this.aswperEtcAt = aswperEtcAt;
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
	 * @return the aswperSeqNo
	 */
	public String getAswperSeqNo() {
		return aswperSeqNo;
	}
	/**
	 * @param aswperSeqNo the aswperSeqNo to set
	 */
	public void setAswperSeqNo(String aswperSeqNo) {
		this.aswperSeqNo = aswperSeqNo;
	}
	/**
	 * @return the aswperText
	 */
	public String getAswperText() {
		return aswperText;
	}
	/**
	 * @param aswperText the aswperText to set
	 */
	public void setAswperText(String aswperText) {
		this.aswperText = aswperText;
	}
	/**
	 * @return the aswperScore
	 */
	public String getAswperScore() {
		return aswperScore;
	}
	/**
	 * @param aswperScore the aswperScore to set
	 */
	public void setAswperScore(String aswperScore) {
		this.aswperScore = aswperScore;
	}
	/**
	 * @return the aswperImage
	 */
	public String getAswperImage() {
		return aswperImage;
	}
	/**
	 * @param aswperImage the aswperImage to set
	 */
	public void setAswperImage(String aswperImage) {
		this.aswperImage = aswperImage;
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
