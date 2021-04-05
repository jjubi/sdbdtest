package egovframework.vaiv.kr.cmmn.sys.cmptn.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 시스템구성정보 관리 : 시스템구성 정보 객체
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
public class SysCmptnVO extends ComDefaultVO{
	/** 시스템 구성 코드 */ 
	private String sysCmptnCode = "";
	/** 시스템 구성 명 */ 
	private String sysCmptnNm = "";
	/** 시스템 구성 값 */ 
	private String sysCmptnValue = "";
	/** 사용 여부 */
	private String useAt = "";
	/** 등록일 */ 
	private String registDe = "";
	/** 등록ID */ 
	private String registId = "";
	/** 수정일 */ 
	private String updtDe = "";
	/** 수정ID */ 
	private String updtId = "";
	
	
	
	/**
	 * @return the sysCmptnCode
	 */
	public String getSysCmptnCode() {
		return sysCmptnCode;
	}
	/**
	 * @param sysCmptnCode the sysCmptnCode to set
	 */
	public void setSysCmptnCode(String sysCmptnCode) {
		this.sysCmptnCode = sysCmptnCode;
	}
	/**
	 * @return the sysCmptnNm
	 */
	public String getSysCmptnNm() {
		return sysCmptnNm;
	}
	/**
	 * @param sysCmptnNm the sysCmptnNm to set
	 */
	public void setSysCmptnNm(String sysCmptnNm) {
		this.sysCmptnNm = sysCmptnNm;
	}
	/**
	 * @return the sysCmptnValue
	 */
	public String getSysCmptnValue() {
		return sysCmptnValue;
	}
	/**
	 * @param sysCmptnValue the sysCmptnValue to set
	 */
	public void setSysCmptnValue(String sysCmptnValue) {
		this.sysCmptnValue = sysCmptnValue;
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
