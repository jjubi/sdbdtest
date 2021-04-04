package egovframework.vaiv.kr.cmmn.sys.excp.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * Exception 관리 / 관리자 : Exception 정보 객체
 * @category 공통
 * @author kmk
 * @since 2021-01-29
 * @version : v1.0
 * @see
 * <pre>
 *  ******************************************
 *  수정 이력
 *  
 *  수정일                  수정자                 수정내용
 *  ------------------------------------------
 *  2021.01.29    kmk           최초 등록
 * 
 * 
 *  ******************************************
 *  Copyright 2020 VAIV Co.
 *  All rights reserved
 * </pre>
 */
public class SysExcpVO extends ComDefaultVO{
	/** 예외 일련번호 */
	private String exceptionSeqNo = "";
	/** 예외 명 */
	private String exceptionNm = "";
	/** 예외 발생 내용 */
	private String exceptionContent = "";
	/** 예외 발생 클래스 */
	private String exceptionClass = "";
	/** 예외 발생 메소드 */
	private String exceptionMethod = "";
	/** 등록 일자 */
	private String registDe = "";
	
	public String getExceptionSeqNo() {
		return exceptionSeqNo;
	}
	public void setExceptionSeqNo(String exceptionSeqNo) {
		this.exceptionSeqNo = exceptionSeqNo;
	}
	public String getExceptionNm() {
		return exceptionNm;
	}
	public void setExceptionNm(String exceptionNm) {
		this.exceptionNm = exceptionNm;
	}
	public String getExceptionContent() {
		return exceptionContent;
	}
	public void setExceptionContent(String exceptionContent) {
		this.exceptionContent = exceptionContent;
	}
	public String getExceptionClass() {
		return exceptionClass;
	}
	public void setExceptionClass(String exceptionClass) {
		this.exceptionClass = exceptionClass;
	}
	public String getExceptionMethod() {
		return exceptionMethod;
	}
	public void setExceptionMethod(String exceptionMethod) {
		this.exceptionMethod = exceptionMethod;
	}
	public String getRegistDe() {
		return registDe;
	}
	public void setRegistDe(String registDe) {
		this.registDe = registDe;
	}
}
