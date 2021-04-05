package egovframework.vaiv.kr.cmmn.banner.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 배너 관리 : 배너 정보 객체
 * @category 공통
 * @author jo
 * @since 2020-12-31
 * @version : v1.0
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
public class BannerVO extends ComDefaultVO{
	/** 배너 일련 번호  */ 
	private String bannerSeqNo = "";
	/** 배너 명 */ 
	private String bannerNm = "";
	/** 배너 설명 */
	private String bannerDc = "";
	/** 배너 URL */
	private String bannerUrl = "";
	/** 첨부 파일 ID */ 
	private String atchFileId = "";
	/** 배너 시작일 */ 
	private String bannerBgnde = "";
	/** 배너 종료일 */ 
	private String bannerEndde = "";
	/** 배너 순서 */
	private String bannerOrdr = "";
	/** 배너 유형 */
	private String bannerTy = "";
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
	
	/** 기간 비교를 위한 날짜 */
	private String pdCondition = "";
	/** 순서 정렬 조건 */
	private String ordrCondition = "";
	/** 배너 기간 체크 */
	private String bannerPdCheck = "";
	
	
	
	
	
	
	
	/**
	 * @return the bannerPdCheck
	 */
	public String getBannerPdCheck() {
		return bannerPdCheck;
	}
	/**
	 * @param bannerPdCheck the bannerPdCheck to set
	 */
	public void setBannerPdCheck(String bannerPdCheck) {
		this.bannerPdCheck = bannerPdCheck;
	}
	/**
	 * @return the bannerTy
	 */
	public String getBannerTy() {
		return bannerTy;
	}
	/**
	 * @param bannerTy the bannerTy to set
	 */
	public void setBannerTy(String bannerTy) {
		this.bannerTy = bannerTy;
	}
	/**
	 * @return the bannerUrl
	 */
	public String getBannerUrl() {
		return bannerUrl;
	}
	/**
	 * @param bannerUrl the bannerUrl to set
	 */
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	/**
	 * @return the bannerDc
	 */
	public String getBannerDc() {
		return bannerDc;
	}
	/**
	 * @param bannerDc the bannerDc to set
	 */
	public void setBannerDc(String bannerDc) {
		this.bannerDc = bannerDc;
	}
	/**
	 * @return the bannerOrdr
	 */
	public String getBannerOrdr() {
		return bannerOrdr;
	}
	/**
	 * @param bannerOrdr the bannerOrdr to set
	 */
	public void setBannerOrdr(String bannerOrdr) {
		this.bannerOrdr = bannerOrdr;
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
	/**
	 * @return the bannerSeqNo
	 */
	public String getBannerSeqNo() {
		return bannerSeqNo;
	}
	/**
	 * @param bannerSeqNo the bannerSeqNo to set
	 */
	public void setBannerSeqNo(String bannerSeqNo) {
		this.bannerSeqNo = bannerSeqNo;
	}
	/**
	 * @return the bannerNm
	 */
	public String getBannerNm() {
		return bannerNm;
	}
	/**
	 * @param bannerNm the bannerNm to set
	 */
	public void setBannerNm(String bannerNm) {
		this.bannerNm = bannerNm;
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
	 * @return the bannerBgnde
	 */
	public String getBannerBgnde() {
		return bannerBgnde;
	}
	/**
	 * @param bannerBgnde the bannerBgnde to set
	 */
	public void setBannerBgnde(String bannerBgnde) {
		this.bannerBgnde = bannerBgnde;
	}
	/**
	 * @return the bannerEndde
	 */
	public String getBannerEndde() {
		return bannerEndde;
	}
	/**
	 * @param bannerEndde the bannerEndde to set
	 */
	public void setBannerEndde(String bannerEndde) {
		this.bannerEndde = bannerEndde;
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

}
