/*
* 파 일 명 : CmtVO.java
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
package egovframework.vaiv.kr.cmmn.bbs.ntt.cmt.service;

import egovframework.com.cmm.ComDefaultVO;

/**
*  : 댓글 VO
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
public class CmtVO extends ComDefaultVO{
	/** 게시글 ID */ 
	private String nttId = "";
	/** 댓글 ID */
	private String cmtId = "";
	/** 댓글 내용 */ 
	private String cmtCn = "";
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
	/** 등록자이름 */ 
	private String registNm = "";
	
	
	
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
	 * @return the cmtId
	 */
	public String getCmtId() {
		return cmtId;
	}
	/**
	 * @param cmtId the cmtId to set
	 */
	public void setCmtId(String cmtId) {
		this.cmtId = cmtId;
	}
	/**
	 * @return the cmtCn
	 */
	public String getCmtCn() {
		return cmtCn;
	}
	/**
	 * @param cmtCn the cmtCn to set
	 */
	public void setCmtCn(String cmtCn) {
		this.cmtCn = cmtCn;
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
	 * @return the registNm
	 */
	public String getRegistNm() {
		return registNm;
	}
	/**
	 * @param registNm the registNm to set
	 */
	public void setRegistNm(String registNm) {
		this.registNm = registNm;
	}
	
}
