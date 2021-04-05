package egovframework.vaiv.kr.cmmn.qestnar.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.com.cmm.ComDefaultVO;

public class QestnarResultVO extends ComDefaultVO{
	/** 설문조사 일련 번호 */
	private String qestnarSeqNo = "";
	/** 설문조사 결과 일련 번호 */
	private String qestnarResultSeqNo = "";
	/** 응답 완료 여부 */
	private String rspnsComptAt = "";
	/** 응답 완료 일자 */ 
	private String rspnsComptDe = "";
	/** 등록 일자 */
	private String registDe = "";
	/** 등록 ID */ 
	private String registId = "";
	/** 수정 일자 */ 
	private String updtDe = "";
	/** 수정 ID */ 
	private String updtId = "";
	
	
	///////////////////////////////////////
	
	/** 현재 문항 번호 */
	private String nowQestnSeqNo = "";
	/** 설문 답변 값 */
	private String answerValue = "";
	
	private List<QestnarResultDtlVO> resultDtlList = new ArrayList<QestnarResultDtlVO>();
	
	
	
	
	
	
	/**
	 * @return the nowQestnSeqNo
	 */
	public String getNowQestnSeqNo() {
		return nowQestnSeqNo;
	}
	/**
	 * @param nowQestnSeqNo the nowQestnSeqNo to set
	 */
	public void setNowQestnSeqNo(String nowQestnSeqNo) {
		this.nowQestnSeqNo = nowQestnSeqNo;
	}
	/**
	 * @return the resultDtlList
	 */
	public List<QestnarResultDtlVO> getResultDtlList() {
		return resultDtlList;
	}
	/**
	 * @param resultDtlList the resultDtlList to set
	 */
	public void setResultDtlList(List<QestnarResultDtlVO> resultDtlList) {
		this.resultDtlList = resultDtlList;
	}
	/**
	 * @return the answerValue
	 */
	public String getAnswerValue() {
		return answerValue;
	}
	/**
	 * @param answerValue the answerValue to set
	 */
	public void setAnswerValue(String answerValue) {
		this.answerValue = answerValue;
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
	 * @return the qestnarResultSeqNo
	 */
	public String getQestnarResultSeqNo() {
		return qestnarResultSeqNo;
	}
	/**
	 * @param qestnarResultSeqNo the qestnarResultSeqNo to set
	 */
	public void setQestnarResultSeqNo(String qestnarResultSeqNo) {
		this.qestnarResultSeqNo = qestnarResultSeqNo;
	}
	/**
	 * @return the rspnsComptAt
	 */
	public String getRspnsComptAt() {
		return rspnsComptAt;
	}
	/**
	 * @param rspnsComptAt the rspnsComptAt to set
	 */
	public void setRspnsComptAt(String rspnsComptAt) {
		this.rspnsComptAt = rspnsComptAt;
	}
	/**
	 * @return the rspnsComptDe
	 */
	public String getRspnsComptDe() {
		return rspnsComptDe;
	}
	/**
	 * @param rspnsComptDe the rspnsComptDe to set
	 */
	public void setRspnsComptDe(String rspnsComptDe) {
		this.rspnsComptDe = rspnsComptDe;
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
	/**
	 * @return the updtDe
	 */
	public String getUpdtDe() {
		return updtDe;
	}
	/**
	 * @param updtDe the updtDe to set
	 */
	public void setUpdtDe(String updtDe) {
		this.updtDe = updtDe;
	}
	/**
	 * @return the updtId
	 */
	public String getUpdtId() {
		return updtId;
	}
	/**
	 * @param updtId the updtId to set
	 */
	public void setUpdtId(String updtId) {
		this.updtId = updtId;
	}
	
	
}
