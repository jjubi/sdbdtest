package egovframework.vaiv.kr.cmmn.comCode.group.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 공통코드 그룹 관리 : 공통코드 그룹 정보 객체
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
public class ComCodeGroupVO extends ComDefaultVO{
	private String groupCode;
	
	private String groupNm;
	
	private String registDe;
	
	private String updtDe;
	
	private String useAt;
	
	private String deleteAt;
	

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
	 * @return the groupNm
	 */
	public String getGroupNm() {
		return groupNm;
	}

	/**
	 * @param groupNm the groupNm to set
	 */
	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
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
	
	
}
