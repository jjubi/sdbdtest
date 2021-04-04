package egovframework.vaiv.kr.cmmn.bbs.service;

/**
 * 게시판 관리 / 관리자 : 게시판 정보 객체
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
public class BbsVO {
	/** 게시판 유형 ID */
	private String bbsTyId = "";
	/** 게시판 ID */
	private String bbsId = "";
	/** 게시판 명 */
	private String bbsNm = "";
	/** 게시판 설명 */
	private String bbsDc = "";
	/** 게시판 기간 사용여부 */
	private String bbsPdUseAt = "";
	/** 게시판 사용 시작일 */
	private String bbsPdBgnde = "";
	/** 게시판 사용 종료일 */
	private String bbsPdEndde = "";
	/** 사용여부 */
	private String useAt = "";
	/** 종료여부 */
	private String deleteAt = "";
	/** 등록일 */
	private String registDe = "";
	/** 등록 ID */
	private String registId = "";
	/** 수정일 */
	private String updtDe = "";
	/** 수정 ID */
	private String updtId = "";
	
	/** 게시판 유형 이름 */
	private String bbsTyNm = "";
	
	

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
	 * @return the bbsId
	 */
	public String getBbsId() {
		return bbsId;
	}

	/**
	 * @param bbsId the bbsId to set
	 */
	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}

	/**
	 * @return the bbsNm
	 */
	public String getBbsNm() {
		return bbsNm;
	}

	/**
	 * @param bbsNm the bbsNm to set
	 */
	public void setBbsNm(String bbsNm) {
		this.bbsNm = bbsNm;
	}

	/**
	 * @return the bbsDc
	 */
	public String getBbsDc() {
		return bbsDc;
	}

	/**
	 * @param bbsDc the bbsDc to set
	 */
	public void setBbsDc(String bbsDc) {
		this.bbsDc = bbsDc;
	}

	/**
	 * @return the bbsPdUseAt
	 */
	public String getBbsPdUseAt() {
		return bbsPdUseAt;
	}

	/**
	 * @param bbsPdUseAt the bbsPdUseAt to set
	 */
	public void setBbsPdUseAt(String bbsPdUseAt) {
		this.bbsPdUseAt = bbsPdUseAt;
	}

	/**
	 * @return the bbsPdBgnde
	 */
	public String getBbsPdBgnde() {
		return bbsPdBgnde;
	}

	/**
	 * @param bbsPdBgnde the bbsPdBgnde to set
	 */
	public void setBbsPdBgnde(String bbsPdBgnde) {
		this.bbsPdBgnde = bbsPdBgnde;
	}

	/**
	 * @return the bbsPdEndde
	 */
	public String getBbsPdEndde() {
		return bbsPdEndde;
	}

	/**
	 * @param bbsPdEndde the bbsPdEndde to set
	 */
	public void setBbsPdEndde(String bbsPdEndde) {
		this.bbsPdEndde = bbsPdEndde;
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
	
	
	
	
}
