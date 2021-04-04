package egovframework.vaiv.kr.cmmn.menu.service;

import egovframework.com.cmm.ComDefaultVO;

/**
 * 메뉴 관리 : 메뉴 정보 객체
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
public class MenuVO extends ComDefaultVO{
	// 일련번호 
    private int menuSeqNo;
    // 부모 일련번호 
    private int upperSeqNo;
    // 메뉴 권한 
    private String menuAuthor;
    // 이름 
    private String menuNm;
    // URL 
    private String menuUrl;
    // TARGET 
    private String menuTarget;
    // 순서 
    private int menuOrdr;
    // 사용여부 
    private String useAt;
    // 아이콘 Class 
    private String menuIconClass;
    // 메뉴 구분
    private String menuSe;
    // 메뉴 depth
    private int menuDp;
    // 메뉴 히스토리 저장 아이디
    private String registId;
    // 메뉴 타입
    private String menuTy;
    // 삭제 문자열
    private String menuDeleteStr;
    
    
    
	/**
	 * @return the menuDeleteStr
	 */
	public String getMenuDeleteStr() {
		return menuDeleteStr;
	}
	/**
	 * @param menuDeleteStr the menuDeleteStr to set
	 */
	public void setMenuDeleteStr(String menuDeleteStr) {
		this.menuDeleteStr = menuDeleteStr;
	}
	/**
	 * @return the menuSeqNo
	 */
	public int getMenuSeqNo() {
		return menuSeqNo;
	}
	/**
	 * @param menuSeqNo the menuSeqNo to set
	 */
	public void setMenuSeqNo(int menuSeqNo) {
		this.menuSeqNo = menuSeqNo;
	}
	/**
	 * @return the upperSeqNo
	 */
	public int getUpperSeqNo() {
		return upperSeqNo;
	}
	/**
	 * @param upperSeqNo the upperSeqNo to set
	 */
	public void setUpperSeqNo(int upperSeqNo) {
		this.upperSeqNo = upperSeqNo;
	}
	/**
	 * @return the menuAuthor
	 */
	public String getMenuAuthor() {
		return menuAuthor;
	}
	/**
	 * @param menuAuthor the menuAuthor to set
	 */
	public void setMenuAuthor(String menuAuthor) {
		this.menuAuthor = menuAuthor;
	}
	/**
	 * @return the menuNm
	 */
	public String getMenuNm() {
		return menuNm;
	}
	/**
	 * @param menuNm the menuNm to set
	 */
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	/**
	 * @return the menuUrl
	 */
	public String getMenuUrl() {
		return menuUrl;
	}
	/**
	 * @param menuUrl the menuUrl to set
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	/**
	 * @return the menuTarget
	 */
	public String getMenuTarget() {
		return menuTarget;
	}
	/**
	 * @param menuTarget the menuTarget to set
	 */
	public void setMenuTarget(String menuTarget) {
		this.menuTarget = menuTarget;
	}
	/**
	 * @return the menuOrdr
	 */
	public int getMenuOrdr() {
		return menuOrdr;
	}
	/**
	 * @param menuOrdr the menuOrdr to set
	 */
	public void setMenuOrdr(int menuOrdr) {
		this.menuOrdr = menuOrdr;
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
	 * @return the menuIconClass
	 */
	public String getMenuIconClass() {
		return menuIconClass;
	}
	/**
	 * @param menuIconClass the menuIconClass to set
	 */
	public void setMenuIconClass(String menuIconClass) {
		this.menuIconClass = menuIconClass;
	}
	/**
	 * @return the menuSe
	 */
	public String getMenuSe() {
		return menuSe;
	}
	/**
	 * @param menuSe the menuSe to set
	 */
	public void setMenuSe(String menuSe) {
		this.menuSe = menuSe;
	}
	/**
	 * @return the menuDp
	 */
	public int getMenuDp() {
		return menuDp;
	}
	/**
	 * @param menuDp the menuDp to set
	 */
	public void setMenuDp(int menuDp) {
		this.menuDp = menuDp;
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
	 * @return the menuTy
	 */
	public String getMenuTy() {
		return menuTy;
	}
	/**
	 * @param menuTy the menuTy to set
	 */
	public void setMenuTy(String menuTy) {
		this.menuTy = menuTy;
	}
    
    
	
}
