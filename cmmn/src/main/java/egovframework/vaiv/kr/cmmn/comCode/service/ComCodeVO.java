package egovframework.vaiv.kr.cmmn.comCode.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 공통코드 관리 : 공통코드 정보 객체
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
public class ComCodeVO extends ComDefaultVO{
	private String groupCode;
	
	private String code;
	
	private String codeValue;
	
	private String codeNm;
	
	private String registDe;
	
	private String updtDe;
	
	private String useAt;
	
	private String deleteAt;
	
	private String rm1;
	
	private String codeOrdr;
	
	
	/**
	 * @return the groupCode
	 */
	public String getGroupCode() {
		return groupCode;
	}

	/**
	 * @param groupCode the groupCode to set
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the codeValue
	 */
	public String getCodeValue() {
		return codeValue;
	}

	/**
	 * @param codeValue the codeValue to set
	 */
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	/**
	 * @return the codeNm
	 */
	public String getCodeNm() {
		return codeNm;
	}

	/**
	 * @param codeNm the codeNm to set
	 */
	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
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
	 * @return the rm1
	 */
	public String getRm1() {
		return rm1;
	}

	/**
	 * @param rm1 the rm1 to set
	 */
	public void setRm1(String rm1) {
		this.rm1 = rm1;
	}

	/**
	 * @return the codeOrdr
	 */
	public String getCodeOrdr() {
		return codeOrdr;
	}

	/**
	 * @param codeOrdr the codeOrdr to set
	 */
	public void setCodeOrdr(String codeOrdr) {
		this.codeOrdr = codeOrdr;
	}
	
}
