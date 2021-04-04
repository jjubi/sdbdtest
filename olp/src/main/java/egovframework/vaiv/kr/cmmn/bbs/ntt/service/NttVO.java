package egovframework.vaiv.kr.cmmn.bbs.ntt.service;

/**
 * 게시물 관리 / 관리자 : 게시물 정보 객체
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
public class NttVO {
	/** 게시판 ID */ 
	private String bbsId = "";
	/** 게시글 ID */ 
	private int nttId = 0;
	/** 게시글 제목 */ 
	private String nttSj = "";
	/** 게시글 내용 */ 
	private String nttCn = "";
	/** 게시글 조회수 */ 
	private String nttRdcnt = "";
	/** 부모 게시글 ID */
	private String upperNttId = "";
	/** 첨부파일 ID */ 
	private String atchFileId = "";
	/** 게시글 정렬 순서 */ 
	private String nttOrdr = "";
	/** 게시글 깊이 */ 
	private String nttDp = "";
	/** 게시글 내부 정렬 순서 */ 
	private String nttInnerOrdr = "";
	/** 비밀글 여부 */ 
	private String secretAt = "";
	/** 비밀글 키 */ 
	private String secretKey = "";
	/** 공지글 여부 */ 
	private String noticeAt = "";
	/** 위치표시사용여부 */ 
	private String lcIndictAt = "";
	/** 주소 */ 
	private String adres = "";
	/** 주소 위도 */ 
	private String adresLa = "";
	/** 주소 경도 */ 
	private String adresLo = "";
	/** 팝업 사용 여부 */ 
	private String popupAt = "";
	/** 삭제 여부 */ 
	private String deleteAt = "";
	/** 등록일 */ 
	private String registDe = "";
	/** 등록ID */ 
	private String registId = "";
	/** 수정일 */ 
	private String updtDe = "";
	/** 수정ID */ 
	private String updtId = "";
	/** 등록자 이름 */
	private String registNm = "";
	/** 답글 존재 여부 */
	private String nttAnswerYn = "";
	/** 공공누리 유형 */
	private String koglTy = "";
	/** ccl 유형 */
	private String cclTy = "";
	
	
	
	/**
	 * @return cclTy
	 */
	public String getCclTy() {
		return cclTy;
	}
	/**
	 * @param cclTy
	 */
	public void setCclTy(String cclTy) {
		this.cclTy = cclTy;
	}
	/**
	 * @return the koglTy
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
	public int getNttId() {
		return nttId;
	}
	/**
	 * @param nttId the nttId to set
	 */
	public void setNttId(int nttId) {
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
	 * @return the nttRdcnt
	 */
	public String getNttRdcnt() {
		return nttRdcnt;
	}
	/**
	 * @param nttRdcnt the nttRdcnt to set
	 */
	public void setNttRdcnt(String nttRdcnt) {
		this.nttRdcnt = nttRdcnt;
	}
	/**
	 * @return the upperNttId
	 */
	public String getUpperNttId() {
		return upperNttId;
	}
	/**
	 * @param upperNttId the upperNttId to set
	 */
	public void setUpperNttId(String upperNttId) {
		this.upperNttId = upperNttId;
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
	 * @return the nttOrdr
	 */
	public String getNttOrdr() {
		return nttOrdr;
	}
	/**
	 * @param nttOrdr the nttOrdr to set
	 */
	public void setNttOrdr(String nttOrdr) {
		this.nttOrdr = nttOrdr;
	}
	/**
	 * @return the nttDp
	 */
	public String getNttDp() {
		return nttDp;
	}
	/**
	 * @param nttDp the nttDp to set
	 */
	public void setNttDp(String nttDp) {
		this.nttDp = nttDp;
	}
	/**
	 * @return the nttInnerOrdr
	 */
	public String getNttInnerOrdr() {
		return nttInnerOrdr;
	}
	/**
	 * @param nttInnerOrdr the nttInnerOrdr to set
	 */
	public void setNttInnerOrdr(String nttInnerOrdr) {
		this.nttInnerOrdr = nttInnerOrdr;
	}
	/**
	 * @return the secretAt
	 */
	public String getSecretAt() {
		return secretAt;
	}
	/**
	 * @param secretAt the secretAt to set
	 */
	public void setSecretAt(String secretAt) {
		this.secretAt = secretAt;
	}
	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}
	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	/**
	 * @return the noticeAt
	 */
	public String getNoticeAt() {
		return noticeAt;
	}
	/**
	 * @param noticeAt the noticeAt to set
	 */
	public void setNoticeAt(String noticeAt) {
		this.noticeAt = noticeAt;
	}
	/**
	 * @return the lcIndictAt
	 */
	public String getLcIndictAt() {
		return lcIndictAt;
	}
	/**
	 * @param lcIndictAt the lcIndictAt to set
	 */
	public void setLcIndictAt(String lcIndictAt) {
		this.lcIndictAt = lcIndictAt;
	}
	/**
	 * @return the adres
	 */
	public String getAdres() {
		return adres;
	}
	/**
	 * @param adres the adres to set
	 */
	public void setAdres(String adres) {
		this.adres = adres;
	}
	/**
	 * @return the adresLa
	 */
	public String getAdresLa() {
		return adresLa;
	}
	/**
	 * @param adresLa the adresLa to set
	 */
	public void setAdresLa(String adresLa) {
		this.adresLa = adresLa;
	}
	/**
	 * @return the adresLo
	 */
	public String getAdresLo() {
		return adresLo;
	}
	/**
	 * @param adresLo the adresLo to set
	 */
	public void setAdresLo(String adresLo) {
		this.adresLo = adresLo;
	}
	/**
	 * @return the popupAt
	 */
	public String getPopupAt() {
		return popupAt;
	}
	/**
	 * @param popupAt the popupAt to set
	 */
	public void setPopupAt(String popupAt) {
		this.popupAt = popupAt;
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
	/**
	 * @return the nttAnswerYn
	 */
	public String getNttAnswerYn() {
		return nttAnswerYn;
	}
	/**
	 * @param nttAnswerYn the nttAnswerYn to set
	 */
	public void setNttAnswerYn(String nttAnswerYn) {
		this.nttAnswerYn = nttAnswerYn;
	}
	
}
