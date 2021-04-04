package egovframework.vaiv.kr.cmmn.csnst.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 만족도 조사 관리 : 만족도 조사 정보 객체
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
public class CsnstVO extends ComDefaultVO{
	/** 고객만족도조사 일련 번호 */ 
	private String csnstSeqNo = "";
	/** 메뉴 명 */ 
	private String menuNm = "";
	/** 메뉴 일련 번호 */ 
	private String menuSeqNo = "";
	/** 고객만족도조사 설명 */
	private String csnstDc = "";
	/** 페이지 URL */
	private String pgeUrl = "";
	/** 게시판 ID */ 
	private String bbsId = "";
	/** 게시판 명 */
	private String bbsNm = "";
	/** 게시물 ID */ 
	private String nttId = "";
	/** 게시물 제목 */
	private String nttSj = "";
	/** 컨텐츠 ID */ 
	private String cntntsId = "";
	/** 컨텐츠 명 */
	private String cntntsNm = "";
	/** 접속 IP */
	private String conectIp = "";
	/** 고객만족도조사 점수 */ 
	private String csnstScore = "";
	/** 등록 일자 */ 
	private String registDe = "";
	/** 등록 ID */
	private String registId = "";
	
	
	
	
	
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
	 * @return the nttSj
	 */
	public String getNttSj() {
		return nttSj;
	}
	/**
	 * @param nttSj the nttSj to set
	 */
	public void setNttSj(String nttSj) {
		this.nttSj = nttSj;
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
	 * @return the csnstSeqNo
	 */
	public String getCsnstSeqNo() {
		return csnstSeqNo;
	}
	/**
	 * @param csnstSeqNo the csnstSeqNo to set
	 */
	public void setCsnstSeqNo(String csnstSeqNo) {
		this.csnstSeqNo = csnstSeqNo;
	}
	/**
	 * @return the menuNm
	 */
	public String getMenuNm() {
		return menuNm;
	}
	/**
	 * @param menuNm the menuNm to set
	 */
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	/**
	 * @return the menuSeqNo
	 */
	public String getMenuSeqNo() {
		return menuSeqNo;
	}
	/**
	 * @param menuSeqNo the menuSeqNo to set
	 */
	public void setMenuSeqNo(String menuSeqNo) {
		this.menuSeqNo = menuSeqNo;
	}
	/**
	 * @return the csnstDc
	 */
	public String getCsnstDc() {
		return csnstDc;
	}
	/**
	 * @param csnstDc the csnstDc to set
	 */
	public void setCsnstDc(String csnstDc) {
		this.csnstDc = csnstDc;
	}
	/**
	 * @return the pgeUrl
	 */
	public String getPgeUrl() {
		return pgeUrl;
	}
	/**
	 * @param pgeUrl the pgeUrl to set
	 */
	public void setPgeUrl(String pgeUrl) {
		this.pgeUrl = pgeUrl;
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
	 * @return the nttId
	 */
	public String getNttId() {
		return nttId;
	}
	/**
	 * @param nttId the nttId to set
	 */
	public void setNttId(String nttId) {
		this.nttId = nttId;
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
	 * @return the conectIp
	 */
	public String getConectIp() {
		return conectIp;
	}
	/**
	 * @param conectIp the conectIp to set
	 */
	public void setConectIp(String conectIp) {
		this.conectIp = conectIp;
	}
	/**
	 * @return the csnstScore
	 */
	public String getCsnstScore() {
		return csnstScore;
	}
	/**
	 * @param csnstScore the csnstScore to set
	 */
	public void setCsnstScore(String csnstScore) {
		this.csnstScore = csnstScore;
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
