package egovframework.vaiv.kr.cmmn.prhibtWrd.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 금지단어 관리 : 금지단어 정보 객체
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
public class PrhibtWrdVO extends ComDefaultVO{
	/** 금지 단어 일련 번호 */
	private String prhibtWrdSeqNo = "";
	/** 금지 단어 */
	private String prhibtWrd = "";
	/** 사용 여부 */
	private String useAt = "";
	/** 등록 일자 */ 
	private String registDe = "";
	/** 등록 ID */ 
	private String registId = "";
	/** 수정 일자 */ 
	private String updtDe = "";
	/** 수정 ID */
	private String updtId = "";
	/** 삭제 여부 */ 
	private String deleteAt = "";

	
	
	
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
	 * @return the prhibtWrdSeqNo
	 */
	public String getPrhibtWrdSeqNo() {
		return prhibtWrdSeqNo;
	}
	/**
	 * @param prhibtWrdSeqNo the prhibtWrdSeqNo to set
	 */
	public void setPrhibtWrdSeqNo(String prhibtWrdSeqNo) {
		this.prhibtWrdSeqNo = prhibtWrdSeqNo;
	}
	/**
	 * @return the prhibtWrd
	 */
	public String getPrhibtWrd() {
		return prhibtWrd;
	}
	/**
	 * @param prhibtWrd the prhibtWrd to set
	 */
	public void setPrhibtWrd(String prhibtWrd) {
		this.prhibtWrd = prhibtWrd;
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
