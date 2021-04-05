/*
* 파 일 명 : QestnVO.java
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

import java.util.ArrayList;
import java.util.List;

/**
*  : 설문 문항 VO
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
public class QestnVO {
	/** 설문조사 일련 번호 */
	private String qestnarSeqNo = "";
	/** 질문 일련 번호 */
	private String qestnSeqNo = "";
	/** 질문 유형 */
	private String qestnTy = "";
	/** 질문 제목 */
	private String qestnSj = "";
	/** 질문 내용 */
	private String qestnHpcm = "";
	/** 질문 옵션 */
	private String qestnOptn = "";
	/** 질문 필수 여부 */
	private String qestnEssntlAt = "";
	/** 질문 순서 */
	private String qestnOrdr = "";
	/** 질문 페이지 */
	private String qestnPge = "";
	/** 사용 여부 */
	private String useAt = "";
	/** 등록 일자 */ 
	private String registDe = "";
	/** 등록 ID */
	private String registId = "";
	
	////////////////////////////////////////////////////////////////////////////////
	
	/** 질문 답안 목록 */
	private List<QestnAswperVO> qestnAswperList = new ArrayList<QestnAswperVO>();
	
	/** 질문 옵션 목록 */
	private List<QestnOptnVO> qestnOptnList = new ArrayList<QestnOptnVO>();
	
	/** 마지막 페이지 번호 */
	private String qestnMaxPge = "";
	
	
	
	/**
	 * @return the qestnMaxPge
	 */
	public String getQestnMaxPge() {
		return qestnMaxPge;
	}
	/**
	 * @param qestnMaxPge the qestnMaxPge to set
	 */
	public void setQestnMaxPge(String qestnMaxPge) {
		this.qestnMaxPge = qestnMaxPge;
	}
	/**
	 * @return the qestnAswperList
	 */
	public List<QestnAswperVO> getQestnAswperList() {
		return qestnAswperList;
	}
	/**
	 * @param qestnAswperList the qestnAswperList to set
	 */
	public void setQestnAswperList(List<QestnAswperVO> qestnAswperList) {
		this.qestnAswperList = qestnAswperList;
	}
	/**
	 * @return the qestnOptnList
	 */
	public List<QestnOptnVO> getQestnOptnList() {
		return qestnOptnList;
	}
	/**
	 * @param qestnOptnList the qestnOptnList to set
	 */
	public void setQestnOptnList(List<QestnOptnVO> qestnOptnList) {
		this.qestnOptnList = qestnOptnList;
	}
	/**
	 * @return the qestnHpcm
	 */
	public String getQestnHpcm() {
		return qestnHpcm;
	}
	/**
	 * @param qestnHpcm the qestnHpcm to set
	 */
	public void setQestnHpcm(String qestnHpcm) {
		this.qestnHpcm = qestnHpcm;
	}
	/**
	 * @return the qestnOrdr
	 */
	public String getQestnOrdr() {
		return qestnOrdr;
	}
	/**
	 * @param qestnOrdr the qestnOrdr to set
	 */
	public void setQestnOrdr(String qestnOrdr) {
		this.qestnOrdr = qestnOrdr;
	}
	/**
	 * @return the qestnTy
	 */
	public String getQestnTy() {
		return qestnTy;
	}
	/**
	 * @param qestnTy the qestnTy to set
	 */
	public void setQestnTy(String qestnTy) {
		this.qestnTy = qestnTy;
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
	 * @return the qestnSj
	 */
	public String getQestnSj() {
		return qestnSj;
	}
	/**
	 * @param qestnSj the qestnSj to set
	 */
	public void setQestnSj(String qestnSj) {
		this.qestnSj = qestnSj;
	}
	/**
	 * @return the qestnOptn
	 */
	public String getQestnOptn() {
		return qestnOptn;
	}
	/**
	 * @param qestnOptn the qestnOptn to set
	 */
	public void setQestnOptn(String qestnOptn) {
		this.qestnOptn = qestnOptn;
	}
	/**
	 * @return the qestnEssntlAt
	 */
	public String getQestnEssntlAt() {
		return qestnEssntlAt;
	}
	/**
	 * @param qestnEssntlAt the qestnEssntlAt to set
	 */
	public void setQestnEssntlAt(String qestnEssntlAt) {
		this.qestnEssntlAt = qestnEssntlAt;
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
