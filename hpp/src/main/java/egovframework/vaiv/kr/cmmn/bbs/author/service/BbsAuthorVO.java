package egovframework.vaiv.kr.cmmn.bbs.author.service;

/**
 * 게시판 권한 관리 / 관리자 : 게시판 권한 객체
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
public class BbsAuthorVO {
	/** 게시판 ID */ 
	private String bbsId = "";
	/** 게시글 조회 권한 */ 
	private String nttRedngAuthor = "";
	/** 게시글 등록 권한 */ 
	private String nttRegistAuthor = "";
	/** 게시글 수정 권한 */ 
	private String nttUpdtAuthor = "";
	/** 게시글 삭제 권한 */ 
	private String nttDeleteAuthor = "";
	/** 게시글 파일업로드 권한 */ 
	private String nttFileUploadAuthor = "";
	/** 게시글 파일다운로드 권한 */ 
	private String nttFileDwldAuthor = "";
	/** 댓글 조회 권한 */ 
	private String cmtRedngAuthor = "";
	/** 댓글 등록 권한 */ 
	private String cmtRegistAuthor = "";
	/** 댓글 수정 권한 */ 
	private String cmtUpdtAuthor = "";
	/** 댓글 삭제 권한 */ 
	private String cmtDeleteAuthor = "";
	/** 답글 조회 권한 */ 
	private String answerRedngAuthor = "";
	/** 답글 등록 권한 */ 
	private String answerRegistAuthor = "";
	/** 답글 수정 권한 */ 
	private String answerUpdtAuthor = "";
	/** 답글 삭제 권한 */ 
	private String answerDeleteAuthor = "";
	/** 비밀글 사용 권한 */ 
	private String secretUseAuthor = "";
	/** 공지글 사용 권한 */ 
	private String noticeUseAuthor = "";
	/** 위치 표시 사용 권한 */ 
	private String lcIndictUseAuthor = "";
	/** 팝업 사용 권한 */ 
	private String popupUseAuthor = "";
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
	 * @return the nttRedngAuthor
	 */
	public String getNttRedngAuthor() {
		return nttRedngAuthor;
	}
	/**
	 * @param nttRedngAuthor the nttRedngAuthor to set
	 */
	public void setNttRedngAuthor(String nttRedngAuthor) {
		this.nttRedngAuthor = nttRedngAuthor;
	}
	/**
	 * @return the nttRegistAuthor
	 */
	public String getNttRegistAuthor() {
		return nttRegistAuthor;
	}
	/**
	 * @param nttRegistAuthor the nttRegistAuthor to set
	 */
	public void setNttRegistAuthor(String nttRegistAuthor) {
		this.nttRegistAuthor = nttRegistAuthor;
	}
	/**
	 * @return the nttUpdtAuthor
	 */
	public String getNttUpdtAuthor() {
		return nttUpdtAuthor;
	}
	/**
	 * @param nttUpdtAuthor the nttUpdtAuthor to set
	 */
	public void setNttUpdtAuthor(String nttUpdtAuthor) {
		this.nttUpdtAuthor = nttUpdtAuthor;
	}
	/**
	 * @return the nttDeleteAuthor
	 */
	public String getNttDeleteAuthor() {
		return nttDeleteAuthor;
	}
	/**
	 * @param nttDeleteAuthor the nttDeleteAuthor to set
	 */
	public void setNttDeleteAuthor(String nttDeleteAuthor) {
		this.nttDeleteAuthor = nttDeleteAuthor;
	}
	/**
	 * @return the nttFileUploadAuthor
	 */
	public String getNttFileUploadAuthor() {
		return nttFileUploadAuthor;
	}
	/**
	 * @param nttFileUploadAuthor the nttFileUploadAuthor to set
	 */
	public void setNttFileUploadAuthor(String nttFileUploadAuthor) {
		this.nttFileUploadAuthor = nttFileUploadAuthor;
	}
	/**
	 * @return the nttFileDwldAuthor
	 */
	public String getNttFileDwldAuthor() {
		return nttFileDwldAuthor;
	}
	/**
	 * @param nttFileDwldAuthor the nttFileDwldAuthor to set
	 */
	public void setNttFileDwldAuthor(String nttFileDwldAuthor) {
		this.nttFileDwldAuthor = nttFileDwldAuthor;
	}
	/**
	 * @return the cmtRedngAuthor
	 */
	public String getCmtRedngAuthor() {
		return cmtRedngAuthor;
	}
	/**
	 * @param cmtRedngAuthor the cmtRedngAuthor to set
	 */
	public void setCmtRedngAuthor(String cmtRedngAuthor) {
		this.cmtRedngAuthor = cmtRedngAuthor;
	}
	/**
	 * @return the cmtRegistAuthor
	 */
	public String getCmtRegistAuthor() {
		return cmtRegistAuthor;
	}
	/**
	 * @param cmtRegistAuthor the cmtRegistAuthor to set
	 */
	public void setCmtRegistAuthor(String cmtRegistAuthor) {
		this.cmtRegistAuthor = cmtRegistAuthor;
	}
	/**
	 * @return the cmtUpdtAuthor
	 */
	public String getCmtUpdtAuthor() {
		return cmtUpdtAuthor;
	}
	/**
	 * @param cmtUpdtAuthor the cmtUpdtAuthor to set
	 */
	public void setCmtUpdtAuthor(String cmtUpdtAuthor) {
		this.cmtUpdtAuthor = cmtUpdtAuthor;
	}
	/**
	 * @return the cmtDeleteAuthor
	 */
	public String getCmtDeleteAuthor() {
		return cmtDeleteAuthor;
	}
	/**
	 * @param cmtDeleteAuthor the cmtDeleteAuthor to set
	 */
	public void setCmtDeleteAuthor(String cmtDeleteAuthor) {
		this.cmtDeleteAuthor = cmtDeleteAuthor;
	}
	/**
	 * @return the answerRedngAuthor
	 */
	public String getAnswerRedngAuthor() {
		return answerRedngAuthor;
	}
	/**
	 * @param answerRedngAuthor the answerRedngAuthor to set
	 */
	public void setAnswerRedngAuthor(String answerRedngAuthor) {
		this.answerRedngAuthor = answerRedngAuthor;
	}
	/**
	 * @return the answerRegistAuthor
	 */
	public String getAnswerRegistAuthor() {
		return answerRegistAuthor;
	}
	/**
	 * @param answerRegistAuthor the answerRegistAuthor to set
	 */
	public void setAnswerRegistAuthor(String answerRegistAuthor) {
		this.answerRegistAuthor = answerRegistAuthor;
	}
	/**
	 * @return the answerUpdtAuthor
	 */
	public String getAnswerUpdtAuthor() {
		return answerUpdtAuthor;
	}
	/**
	 * @param answerUpdtAuthor the answerUpdtAuthor to set
	 */
	public void setAnswerUpdtAuthor(String answerUpdtAuthor) {
		this.answerUpdtAuthor = answerUpdtAuthor;
	}
	/**
	 * @return the answerDeleteAuthor
	 */
	public String getAnswerDeleteAuthor() {
		return answerDeleteAuthor;
	}
	/**
	 * @param answerDeleteAuthor the answerDeleteAuthor to set
	 */
	public void setAnswerDeleteAuthor(String answerDeleteAuthor) {
		this.answerDeleteAuthor = answerDeleteAuthor;
	}
	/**
	 * @return the secretUseAuthor
	 */
	public String getSecretUseAuthor() {
		return secretUseAuthor;
	}
	/**
	 * @param secretUseAuthor the secretUseAuthor to set
	 */
	public void setSecretUseAuthor(String secretUseAuthor) {
		this.secretUseAuthor = secretUseAuthor;
	}
	/**
	 * @return the noticeUseAuthor
	 */
	public String getNoticeUseAuthor() {
		return noticeUseAuthor;
	}
	/**
	 * @param noticeUseAuthor the noticeUseAuthor to set
	 */
	public void setNoticeUseAuthor(String noticeUseAuthor) {
		this.noticeUseAuthor = noticeUseAuthor;
	}
	/**
	 * @return the lcIndictUseAuthor
	 */
	public String getLcIndictUseAuthor() {
		return lcIndictUseAuthor;
	}
	/**
	 * @param lcIndictUseAuthor the lcIndictUseAuthor to set
	 */
	public void setLcIndictUseAuthor(String lcIndictUseAuthor) {
		this.lcIndictUseAuthor = lcIndictUseAuthor;
	}
	/**
	 * @return the popupUseAuthor
	 */
	public String getPopupUseAuthor() {
		return popupUseAuthor;
	}
	/**
	 * @param popupUseAuthor the popupUseAuthor to set
	 */
	public void setPopupUseAuthor(String popupUseAuthor) {
		this.popupUseAuthor = popupUseAuthor;
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
