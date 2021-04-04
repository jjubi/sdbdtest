package egovframework.vaiv.kr.cmmn.data.vsltn.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 데이터 시각화 공유 관리 : 데이터 시각화 공유 데이터 객체
 * @category 공통
 * @author jo
 * @since 2021-03-23
 * @version v1.0
 * @see
 * <pre>
 *  ******************************************
 *  수정 이력
 *  
 *  수정일                  수정자                 수정내용
 *  ------------------------------------------
 *  2021.03.23    jo           최초 등록
 * 
 * 
 *  ******************************************
 *  Copyright 2021 VAIV Co.
 *  All rights reserved
 * </pre>
 */
public class DataVsltnCnrsVO extends ComDefaultVO{
	/** 시각화 공유 일련 번호 */
	private String vsltnCnrsSeqNo = "";
	/** 시각화 유형 */ 
	private String vsltnTy = "";
	/** 시각화 원 파일 ID */ 
	private String vsltnOrignlFileId = "";
	/** 시각화 옵션 */ 
	private String vsltnOptn = "";
	/** 시각화 URL 키 */
	private String vsltnUrlKey = "";
	/** 시각화 공유 명 */
	private String vsltnCnrsNm = "";
	/** 사용 여부 */ 
	private String useAt = "";
	/** 등록 일자 */
	private String registDe = "";
	/** 등록 ID */
	private String registId = "";
	/** 수정 일자 */
	private String updtDe = "";
	/** 수정 ID */
	private String updtId = "";
	
	
	/** 시각화 원 파일 명 */
	private String vsltnOrignlFileNm = "";
	
	
	
	/**
	 * @return the vsltnOrignlFileNm
	 */
	public String getVsltnOrignlFileNm() {
		return vsltnOrignlFileNm;
	}
	/**
	 * @param vsltnOrignlFileNm the vsltnOrignlFileNm to set
	 */
	public void setVsltnOrignlFileNm(String vsltnOrignlFileNm) {
		this.vsltnOrignlFileNm = vsltnOrignlFileNm;
	}
	/**
	 * @return the vsltnCnrsSeqNo
	 */
	public String getVsltnCnrsSeqNo() {
		return vsltnCnrsSeqNo;
	}
	/**
	 * @param vsltnCnrsSeqNo the vsltnCnrsSeqNo to set
	 */
	public void setVsltnCnrsSeqNo(String vsltnCnrsSeqNo) {
		this.vsltnCnrsSeqNo = vsltnCnrsSeqNo;
	}
	/**
	 * @return the vsltnTy
	 */
	public String getVsltnTy() {
		return vsltnTy;
	}
	/**
	 * @param vsltnTy the vsltnTy to set
	 */
	public void setVsltnTy(String vsltnTy) {
		this.vsltnTy = vsltnTy;
	}
	/**
	 * @return the vsltnOrignlFileId
	 */
	public String getVsltnOrignlFileId() {
		return vsltnOrignlFileId;
	}
	/**
	 * @param vsltnOrignlFileId the vsltnOrignlFileId to set
	 */
	public void setVsltnOrignlFileId(String vsltnOrignlFileId) {
		this.vsltnOrignlFileId = vsltnOrignlFileId;
	}
	/**
	 * @return the vsltnOptn
	 */
	public String getVsltnOptn() {
		return vsltnOptn;
	}
	/**
	 * @param vsltnOptn the vsltnOptn to set
	 */
	public void setVsltnOptn(String vsltnOptn) {
		this.vsltnOptn = vsltnOptn;
	}
	/**
	 * @return the vsltnUrlKey
	 */
	public String getVsltnUrlKey() {
		return vsltnUrlKey;
	}
	/**
	 * @param vsltnUrlKey the vsltnUrlKey to set
	 */
	public void setVsltnUrlKey(String vsltnUrlKey) {
		this.vsltnUrlKey = vsltnUrlKey;
	}
	/**
	 * @return the vsltnCnrsNm
	 */
	public String getVsltnCnrsNm() {
		return vsltnCnrsNm;
	}
	/**
	 * @param vsltnCnrsNm the vsltnCnrsNm to set
	 */
	public void setVsltnCnrsNm(String vsltnCnrsNm) {
		this.vsltnCnrsNm = vsltnCnrsNm;
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
