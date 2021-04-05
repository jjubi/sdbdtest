package egovframework.vaiv.kr.cmmn.bbs.ty.service;

/**
 * 게시판 유형 관리 / 관리자 : 게시판 유형 정보 객체
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
public class BbsTyVO {
	/** 게시판 유형 ID */
	private String bbsTyId = "";
	/** 게시판 유형 코드 */
	private String bbsTyCode = "";
	/** 게시판 유형 이름 */
	private String bbsTyNm = "";
	/** 게시판 유형 설명 */
	private String bbsTyDc = "";
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
	
	
	/**
	 * @return the bbsTyId
	 */
	public String getBbsTyId() {
		return bbsTyId;
	}
	/**
	 * @param bbsTyId the bbsTyId to set
	 */
	public void setBbsTyId(String bbsTyId) {
		this.bbsTyId = bbsTyId;
	}
	/**
	 * @return the bbsTyCode
	 */
	public String getBbsTyCode() {
		return bbsTyCode;
	}
	/**
	 * @param bbsTyCode the bbsTyCode to set
	 */
	public void setBbsTyCode(String bbsTyCode) {
		this.bbsTyCode = bbsTyCode;
	}
	/**
	 * @return the bbsTyNm
	 */
	public String getBbsTyNm() {
		return bbsTyNm;
	}
	/**
	 * @param bbsTyNm the bbsTyNm to set
	 */
	public void setBbsTyNm(String bbsTyNm) {
		this.bbsTyNm = bbsTyNm;
	}
	/**
	 * @return the bbsTyDc
	 */
	public String getBbsTyDc() {
		return bbsTyDc;
	}
	/**
	 * @param bbsTyDc the bbsTyDc to set
	 */
	public void setBbsTyDc(String bbsTyDc) {
		this.bbsTyDc = bbsTyDc;
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
