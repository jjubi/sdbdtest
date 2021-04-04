package egovframework.vaiv.kr.cmmn.popup.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 팝업 관리 : 팝업 정보 객체
 * @category 공통
 * @author jo
 * @since 2020-12-31
 * @version v1.0
 * @see
 * <pre>
 *  ******************************************
 *  수정 이력
 *  
 *  수정일                  수정자                 수정내용
 *  ------------------------------------------
 *  2020.12.31    jo           최초 등록
 * 
 * 
 *  ******************************************
 *  Copyright 2020 VAIV Co.
 *  All rights reserved
 * </pre>
 */
public class PopupVO extends ComDefaultVO{
	/** 팝업 일련 번호  */ 
	private String popupSeqNo = "";
	/** 팝업 명 */ 
	private String popupNm = "";
	/** 팝업 설명 */
	private String popupDc = "";
	/** 팝업 URL */
	private String popupUrl = "";
	/** 첨부 파일 ID */ 
	private String atchFileId = "";
	/** 팝업 시작일 */ 
	private String popupBgnde = "";
	/** 팝업 종료일 */ 
	private String popupEndde = "";
	/** 팝업 순서 */
	private String popupOrdr = "";
	/** 팝업 유형 */
	private String popupTy = "";
	/** 팝업 TOP 위치 */
	private String popupTopLc = "";
	/** 팝업 LEFT 위치 */
	private String popupLeftLc = "";
	/** 사용여부 */
	private String useAt = "";
	/** 삭제여부 */
	private String deleteAt = "";
	/** 등록일 */
	private String registDe = "";
	/** 등록ID */
	private String registId = "";
	/** 수정일 */
	private String updtDe = "";
	/** 수정ID */
	private String updtId = "";
	
	///////////////////////////////////////////////////
	
	/** 기간 비교를 위한 날짜 조건 */
	private String pdCondition = "";
	/** 순서 정렬 조건 */
	private String ordrCondition = "";
	/** 팝업 기간 체크 */
	private String popupPdCheck = "";
	
	
	
	
	
	/**
	 * @return the popupPdCheck
	 */
	public String getPopupPdCheck() {
		return popupPdCheck;
	}
	/**
	 * @param popupPdCheck the popupPdCheck to set
	 */
	public void setPopupPdCheck(String popupPdCheck) {
		this.popupPdCheck = popupPdCheck;
	}
	/**
	 * @return the popupTopLc
	 */
	public String getPopupTopLc() {
		return popupTopLc;
	}
	/**
	 * @param popupTopLc the popupTopLc to set
	 */
	public void setPopupTopLc(String popupTopLc) {
		this.popupTopLc = popupTopLc;
	}
	/**
	 * @return the popupLeftLc
	 */
	public String getPopupLeftLc() {
		return popupLeftLc;
	}
	/**
	 * @param popupLeftLc the popupLeftLc to set
	 */
	public void setPopupLeftLc(String popupLeftLc) {
		this.popupLeftLc = popupLeftLc;
	}
	/**
	 * @return the popupSeqNo
	 */
	public String getPopupSeqNo() {
		return popupSeqNo;
	}
	/**
	 * @param popupSeqNo the popupSeqNo to set
	 */
	public void setPopupSeqNo(String popupSeqNo) {
		this.popupSeqNo = popupSeqNo;
	}
	/**
	 * @return the popupNm
	 */
	public String getPopupNm() {
		return popupNm;
	}
	/**
	 * @param popupNm the popupNm to set
	 */
	public void setPopupNm(String popupNm) {
		this.popupNm = popupNm;
	}
	/**
	 * @return the popupDc
	 */
	public String getPopupDc() {
		return popupDc;
	}
	/**
	 * @param popupDc the popupDc to set
	 */
	public void setPopupDc(String popupDc) {
		this.popupDc = popupDc;
	}
	/**
	 * @return the popupUrl
	 */
	public String getPopupUrl() {
		return popupUrl;
	}
	/**
	 * @param popupUrl the popupUrl to set
	 */
	public void setPopupUrl(String popupUrl) {
		this.popupUrl = popupUrl;
	}
	/**
	 * @return the atchFileId
	 */
	public String getAtchFileId() {
		return atchFileId;
	}
	/**
	 * @param atchFileId the atchFileId to set
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	/**
	 * @return the popupBgnde
	 */
	public String getPopupBgnde() {
		return popupBgnde;
	}
	/**
	 * @param popupBgnde the popupBgnde to set
	 */
	public void setPopupBgnde(String popupBgnde) {
		this.popupBgnde = popupBgnde;
	}
	/**
	 * @return the popupEndde
	 */
	public String getPopupEndde() {
		return popupEndde;
	}
	/**
	 * @param popupEndde the popupEndde to set
	 */
	public void setPopupEndde(String popupEndde) {
		this.popupEndde = popupEndde;
	}
	/**
	 * @return the popupOrdr
	 */
	public String getPopupOrdr() {
		return popupOrdr;
	}
	/**
	 * @param popupOrdr the popupOrdr to set
	 */
	public void setPopupOrdr(String popupOrdr) {
		this.popupOrdr = popupOrdr;
	}
	/**
	 * @return the popupTy
	 */
	public String getPopupTy() {
		return popupTy;
	}
	/**
	 * @param popupTy the popupTy to set
	 */
	public void setPopupTy(String popupTy) {
		this.popupTy = popupTy;
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
	 * @return the deleteAt
	 */
	public String getDeleteAt() {
		return deleteAt;
	}
	/**
	 * @param deleteAt the deleteAt to set
	 */
	public void setDeleteAt(String deleteAt) {
		this.deleteAt = deleteAt;
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
	/**
	 * @return the pdCondition
	 */
	public String getPdCondition() {
		return pdCondition;
	}
	/**
	 * @param pdCondition the pdCondition to set
	 */
	public void setPdCondition(String pdCondition) {
		this.pdCondition = pdCondition;
	}
	/**
	 * @return the ordrCondition
	 */
	public String getOrdrCondition() {
		return ordrCondition;
	}
	/**
	 * @param ordrCondition the ordrCondition to set
	 */
	public void setOrdrCondition(String ordrCondition) {
		this.ordrCondition = ordrCondition;
	}
	
}
