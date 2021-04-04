package egovframework.vaiv.kr.cmmn.cntnts.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 컨텐츠 관리 / 관리자 : 컨텐츠 정보 객체
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
public class CntntsVO extends ComDefaultVO{
	
	/** 컨텐츠 id */ 
	private String cntntsId = "";
	/** 컨텐츠 코드 */ 
	private String cntntsCode = "";
	/** 컨텐츠 명 */ 
	private String cntntsNm = "";
	/** 컨텐츠 내용 */ 
	private String cntntsCn = "";
	/** 사용여부 */
	private String useAt = "";
	/** 삭제여부 */
	private String deleteAt = "";
	/** 등록일 */
	private String registDe = "";
	/** 등록 ID */
	private String registId = "";
	/** 수정일 */
	private String updtDe = "";
	/** 수정 ID */
	private String updtId = "";
	/** 첨부파일 */
	private String atchFileId = "";
	/** CCL 유형 */
	private String cclTy = "";
	/** 공공누리 유형 */
	private String koglTy = "";
	/** 등록자 이름 */
	private String registNm = "";
	
	
	
	
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
	 * @return the cntntsId
	 */
	public String getCntntsId() {
		return cntntsId;
	}
	/**
	 * @param cntntsId the cntntsId to set
	 */
	public void setCntntsId(String cntntsId) {
		this.cntntsId = cntntsId;
	}
	/**
	 * @return the cntntsCode
	 */
	public String getCntntsCode() {
		return cntntsCode;
	}
	/**
	 * @param cntntsCode the cntntsCode to set
	 */
	public void setCntntsCode(String cntntsCode) {
		this.cntntsCode = cntntsCode;
	}
	/**
	 * @return the cntntsNm
	 */
	public String getCntntsNm() {
		return cntntsNm;
	}
	/**
	 * @param cntntsNm the cntntsNm to set
	 */
	public void setCntntsNm(String cntntsNm) {
		this.cntntsNm = cntntsNm;
	}
	/**
	 * @return the cntntsCn
	 */
	public String getCntntsCn() {
		return cntntsCn;
	}
	/**
	 * @param cntntsCn the cntntsCn to set
	 */
	public void setCntntsCn(String cntntsCn) {
		this.cntntsCn = cntntsCn;
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
	 * @return cclTy
	 */
	public String getCclTy() {
		return cclTy;
	}
	/**
	 * @param cclTy the cclTy to set
	 */
	public void setCclTy(String cclTy) {
		this.cclTy = cclTy;
	}
	/**
	 * @return koglTy
	 */
	public String getKoglTy() {
		return koglTy;
	}
	/**
	 * @param koglTy the koglTy to set
	 */
	public void setKoglTy(String koglTy) {
		this.koglTy = koglTy;
	}
	public String getRegistNm() {
		return registNm;
	}
	public void setRegistNm(String registNm) {
		this.registNm = registNm;
	}
}
