package egovframework.vaiv.kr.cmmn.qestnar.service;

import java.util.ArrayList;
import java.util.List;

/**
 * 설문 관리 : 설문 정보 객체
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
public class QestnarVO{
	/** 설문조사 일련 번호 */
	private String qestnarSeqNo = "";
	/** 설문조사 명 */
	private String qestnarNm = "";
	/** 설문조사 머리말 */
	private String qestnarPrface = "";
	/** 설문조사 맺음말 */
	private String qestnarCnclsn = "";
	/** 설문조사 시작일 */
	private String qestnarBgnde = "";
	/** 설문조사 종료일 */
	private String qestnarEndde = "";
	/** 설문조사 대상 */ 
	private String qestnarTrget = "";
	/** 설문조사 중복 여부 */
	private String qestnarDplctAt = "";
	/** 사용 여부 */ 
	private String useAt = "";
	/** 삭제 여부 */
	private String deleteAt = "";
	/** 등록일 */ 
	private String registDe = "";
	/** 등록 ID */ 
	private String registId = "";
	/** 수정일 */ 
	private String updtDe = "";
	/** 수정 ID */ 
	private String updtId = "";
	/** 설문조사 페이지 여부 */
	private String qestnarPgeAt = "";
	/** 설문조사 대상 목록 */
	private List<QestnarTrgtVO> qestnarTrgetList = new ArrayList<QestnarTrgtVO>(); 
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	/** 설문조사 대상 목록 문자열 */
	private String qestnarTrgetListStr = "";
	/** 설문조사 대상 수 */
	private String qestnarTrgetCnt = "";
	/** 설문 날짜 체크 여부 */
	private String qestnarPdCheckYn = "";
	/** 설문 문항 첫 일련 번호 */
	private String firstQestnSeqNo = "";
	/** 설문 문항 마지막 일련번호 */
	private String lastQestnSeqNo = "";
	/** 설문 결과 갯수 */
	private String qestnarResultCnt = "";
	
	
	
	
	/**
	 * @return the qestnarResultCnt
	 */
	public String getQestnarResultCnt() {
		return qestnarResultCnt;
	}
	/**
	 * @param qestnarResultCnt the qestnarResultCnt to set
	 */
	public void setQestnarResultCnt(String qestnarResultCnt) {
		this.qestnarResultCnt = qestnarResultCnt;
	}
	/**
	 * @return the qestnarPgeAt
	 */
	public String getQestnarPgeAt() {
		return qestnarPgeAt;
	}
	/**
	 * @param qestnarPgeAt the qestnarPgeAt to set
	 */
	public void setQestnarPgeAt(String qestnarPgeAt) {
		this.qestnarPgeAt = qestnarPgeAt;
	}
	/**
	 * @return the qestnarDplctAt
	 */
	public String getQestnarDplctAt() {
		return qestnarDplctAt;
	}
	/**
	 * @param qestnarDplctAt the qestnarDplctAt to set
	 */
	public void setQestnarDplctAt(String qestnarDplctAt) {
		this.qestnarDplctAt = qestnarDplctAt;
	}
	/**
	 * @return the qestnarTrgetListStr
	 */
	public String getQestnarTrgetListStr() {
		return qestnarTrgetListStr;
	}
	/**
	 * @param qestnarTrgetListStr the qestnarTrgetListStr to set
	 */
	public void setQestnarTrgetListStr(String qestnarTrgetListStr) {
		this.qestnarTrgetListStr = qestnarTrgetListStr;
	}
	/**
	 * @return the qestnarTrgetList
	 */
	public List<QestnarTrgtVO> getQestnarTrgetList() {
		return qestnarTrgetList;
	}
	/**
	 * @param qestnarTrgetList the qestnarTrgetList to set
	 */
	public void setQestnarTrgetList(List<QestnarTrgtVO> qestnarTrgetList) {
		this.qestnarTrgetList = qestnarTrgetList;
	}
	/**
	 * @return the lastQestnSeqNo
	 */
	public String getLastQestnSeqNo() {
		return lastQestnSeqNo;
	}
	/**
	 * @param lastQestnSeqNo the lastQestnSeqNo to set
	 */
	public void setLastQestnSeqNo(String lastQestnSeqNo) {
		this.lastQestnSeqNo = lastQestnSeqNo;
	}
	/**
	 * @return the firstQestnSeqNo
	 */
	public String getFirstQestnSeqNo() {
		return firstQestnSeqNo;
	}
	/**
	 * @param firstQestnSeqNo the firstQestnSeqNo to set
	 */
	public void setFirstQestnSeqNo(String firstQestnSeqNo) {
		this.firstQestnSeqNo = firstQestnSeqNo;
	}
	/**
	 * @return the qestnarPdCheckYn
	 */
	public String getQestnarPdCheckYn() {
		return qestnarPdCheckYn;
	}
	/**
	 * @param qestnarPdCheckYn the qestnarPdCheckYn to set
	 */
	public void setQestnarPdCheckYn(String qestnarPdCheckYn) {
		this.qestnarPdCheckYn = qestnarPdCheckYn;
	}
	/**
	 * @return the qestnarTrgetCnt
	 */
	public String getQestnarTrgetCnt() {
		return qestnarTrgetCnt;
	}
	/**
	 * @param qestnarTrgetCnt the qestnarTrgetCnt to set
	 */
	public void setQestnarTrgetCnt(String qestnarTrgetCnt) {
		this.qestnarTrgetCnt = qestnarTrgetCnt;
	}
	/**
	 * @return the qestnarSeqNo
	 */
	public String getQestnarSeqNo() {
		return qestnarSeqNo;
	}
	/**
	 * @param qestnarSeqNo the qestnarSeqNo to set
	 */
	public void setQestnarSeqNo(String qestnarSeqNo) {
		this.qestnarSeqNo = qestnarSeqNo;
	}
	/**
	 * @return the qestnarNm
	 */
	public String getQestnarNm() {
		return qestnarNm;
	}
	/**
	 * @param qestnarNm the qestnarNm to set
	 */
	public void setQestnarNm(String qestnarNm) {
		this.qestnarNm = qestnarNm;
	}
	/**
	 * @return the qestnarPrface
	 */
	public String getQestnarPrface() {
		return qestnarPrface;
	}
	/**
	 * @param qestnarPrface the qestnarPrface to set
	 */
	public void setQestnarPrface(String qestnarPrface) {
		this.qestnarPrface = qestnarPrface;
	}
	/**
	 * @return the qestnarCnclsn
	 */
	public String getQestnarCnclsn() {
		return qestnarCnclsn;
	}
	/**
	 * @param qestnarCnclsn the qestnarCnclsn to set
	 */
	public void setQestnarCnclsn(String qestnarCnclsn) {
		this.qestnarCnclsn = qestnarCnclsn;
	}
	/**
	 * @return the qestnarBgnde
	 */
	public String getQestnarBgnde() {
		return qestnarBgnde;
	}
	/**
	 * @param qestnarBgnde the qestnarBgnde to set
	 */
	public void setQestnarBgnde(String qestnarBgnde) {
		this.qestnarBgnde = qestnarBgnde;
	}
	/**
	 * @return the qestnarEndde
	 */
	public String getQestnarEndde() {
		return qestnarEndde;
	}
	/**
	 * @param qestnarEndde the qestnarEndde to set
	 */
	public void setQestnarEndde(String qestnarEndde) {
		this.qestnarEndde = qestnarEndde;
	}
	/**
	 * @return the qestnarTrget
	 */
	public String getQestnarTrget() {
		return qestnarTrget;
	}
	/**
	 * @param qestnarTrget the qestnarTrget to set
	 */
	public void setQestnarTrget(String qestnarTrget) {
		this.qestnarTrget = qestnarTrget;
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
