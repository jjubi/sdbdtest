package egovframework.vaiv.kr.cmmn.bbs.common;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 게시판 검색 : 게시판 검색 객체
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
public class SearchVO extends ComDefaultVO {
	/** 게시판 유형 ID */
	private String bbsTyId = "";
	/** 게시판 유형 이름 */
	private String bbsTyNm = "";
	/** 게시판 유형 코드 */
	private String bbsTyCode = "";
	
	/** 게시판 ID */
	private String bbsId = "";
	/** 게시판 명 */
	private String bbsNm = "";
	
	/** 게시글 ID */ 
	private String nttId = "";
	/** 게시글 제목 */ 
	private String nttSj = "";
	/** 게시글 내용 */ 
	private String nttCn = "";
	
	/** 댓글 ID */
	private String cmtId = "";
	
	
	
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
	 * @return the nttCn
	 */
	public String getNttCn() {
		return nttCn;
	}
	/**
	 * @param nttCn the nttCn to set
	 */
	public void setNttCn(String nttCn) {
		this.nttCn = nttCn;
	}
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
	
}
