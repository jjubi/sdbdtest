package egovframework.vaiv.kr.cmmn.cntnts.service;

/**
 * 컨텐츠 히스토리 관리 / 관리자 : 컨텐츠 히스토리 정보 객체
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
public class CntntsHisVO extends CntntsVO{
	/** 히스토리 일련번호 */
	private String histSeqNo = "";
	/** 히스토리 등록일 */
	private String histRegistDe = "";
	
	
	/**
	 * @return the histSeqNo
	 */
	public String getHistSeqNo() {
		return histSeqNo;
	}
	/**
	 * @param histSeqNo the histSeqNo to set
	 */
	public void setHistSeqNo(String histSeqNo) {
		this.histSeqNo = histSeqNo;
	}
	/**
	 * @return the histRegistDe
	 */
	public String getHistRegistDe() {
		return histRegistDe;
	}
	/**
	 * @param histRegistDe the histRegistDe to set
	 */
	public void setHistRegistDe(String histRegistDe) {
		this.histRegistDe = histRegistDe;
	}
	
	
	

}
