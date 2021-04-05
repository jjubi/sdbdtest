package egovframework.vaiv.kr.cmmn.bbs.optn.service;

/**
 * 게시판 옵션 관리 / 관리자 : 게시판 옵션 정보 객체
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
public class BbsOptnVO {
	/** 게시판 ID */ 
	private String bbsId = "";
	/** 팝업사용여부 */ 
	private String popupUseAt = "";
	/** 첨부파일사용여부 */ 
	private String atchFileUseAt = "";
	/** 첨부파일 허용 확장자 */ 
	private String atchFilePermExtsn = "";
	/** 첨부파일 허용 최대 갯수 */
	private String atchFilePermMxmmCnt = "";
	/** 답글사용여부 */ 
	private String answerUseAt = "";
	/** 댓글사용여부 */ 
	private String cmtUseAt = "";
	/** 비밀글사용여부 */ 
	private String secretUseAt = "";
	/** 공지글사용여부 */ 
	private String noticeUseAt = "";
	/** 위치표시사용여부 */
	private String lcIndictUseAt = "";
	/** CCL사용여부 */ 
	private String cclUseAt = "";
	/** 공공누리사용여부*/
	private String koglUseAt = "";
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
	 * @return the cclUseAt
	 */
	public String getCclUseAt() {
		return cclUseAt;
	}
	/**
	 * @param cclUseAt the cclUseAt to set
	 */
	public void setCclUseAt(String cclUseAt) {
		this.cclUseAt = cclUseAt;
	}
	/**
	 * @return the koglUseAt
	 */
	public String getKoglUseAt() {
		return koglUseAt;
	}
	/**
	 * @param koglUseAt the koglUseAt to set
	 */
	public void setKoglUseAt(String koglUseAt) {
		this.koglUseAt = koglUseAt;
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
	 * @return the popupUseAt
	 */
	public String getPopupUseAt() {
		return popupUseAt;
	}
	/**
	 * @param popupUseAt the popupUseAt to set
	 */
	public void setPopupUseAt(String popupUseAt) {
		this.popupUseAt = popupUseAt;
	}
	/**
	 * @return the atchFileUseAt
	 */
	public String getAtchFileUseAt() {
		return atchFileUseAt;
	}
	/**
	 * @param atchFileUseAt the atchFileUseAt to set
	 */
	public void setAtchFileUseAt(String atchFileUseAt) {
		this.atchFileUseAt = atchFileUseAt;
	}
	/**
	 * @return the atchFilePermExtsn
	 */
	public String getAtchFilePermExtsn() {
		return atchFilePermExtsn;
	}
	/**
	 * @param atchFilePermExtsn the atchFilePermExtsn to set
	 */
	public void setAtchFilePermExtsn(String atchFilePermExtsn) {
		this.atchFilePermExtsn = atchFilePermExtsn;
	}
	/**
	 * @return the atchFilePermMxmmCnt
	 */
	public String getAtchFilePermMxmmCnt() {
		return atchFilePermMxmmCnt;
	}
	/**
	 * @param atchFilePermMxmmCnt the atchFilePermMxmmCnt to set
	 */
	public void setAtchFilePermMxmmCnt(String atchFilePermMxmmCnt) {
		this.atchFilePermMxmmCnt = atchFilePermMxmmCnt;
	}
	/**
	 * @return the answerUseAt
	 */
	public String getAnswerUseAt() {
		return answerUseAt;
	}
	/**
	 * @param answerUseAt the answerUseAt to set
	 */
	public void setAnswerUseAt(String answerUseAt) {
		this.answerUseAt = answerUseAt;
	}
	/**
	 * @return the cmtUseAt
	 */
	public String getCmtUseAt() {
		return cmtUseAt;
	}
	/**
	 * @param cmtUseAt the cmtUseAt to set
	 */
	public void setCmtUseAt(String cmtUseAt) {
		this.cmtUseAt = cmtUseAt;
	}
	/**
	 * @return the secretUseAt
	 */
	public String getSecretUseAt() {
		return secretUseAt;
	}
	/**
	 * @param secretUseAt the secretUseAt to set
	 */
	public void setSecretUseAt(String secretUseAt) {
		this.secretUseAt = secretUseAt;
	}
	/**
	 * @return the noticeUseAt
	 */
	public String getNoticeUseAt() {
		return noticeUseAt;
	}
	/**
	 * @param noticeUseAt the noticeUseAt to set
	 */
	public void setNoticeUseAt(String noticeUseAt) {
		this.noticeUseAt = noticeUseAt;
	}
	/**
	 * @return the lcIndictUseAt
	 */
	public String getLcIndictUseAt() {
		return lcIndictUseAt;
	}
	/**
	 * @param lcIndictUseAt the lcIndictUseAt to set
	 */
	public void setLcIndictUseAt(String lcIndictUseAt) {
		this.lcIndictUseAt = lcIndictUseAt;
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
