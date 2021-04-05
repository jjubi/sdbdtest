package egovframework.vaiv.kr.cmmn.menu.service;

import java.sql.SQLException;
import java.util.List;

/**
 * 메뉴 관리 / 관리자 : 메뉴 관리에 대한 인터페이스 정의
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
public interface MenuService {
	
	/**
	 * 메뉴 목록 조회
	 * @param vo MenuVO
	 * @return List of MenuVO
	 * @throws SQLException
	 */
	public List<MenuVO> selectMenuList(MenuVO vo) throws SQLException;
	
	/**
	 * 입력된 정보로 메뉴 목록 저장
	 * @param menuListToJson 메뉴 목록 정보 JSON 문자열
	 * @throws SQLException
	 */
	public void insertMenu(String menuListToJson) throws SQLException;
	
	/**
	 * 입력된 정보로 메뉴 데이터 수정
	 * @param menuToJson 메뉴 정보 JSON 문자열
	 * @throws SQLException
	 */
	public void updateMenu(String menuToJson) throws SQLException;
	
	/**
	 * 메뉴 데이터 삭제
	 * @param vo MenuVO
	 * @throws SQLException
	 */
	public void deleteMenu(MenuVO vo) throws SQLException;
	
	/**
	 * 설정한 메뉴 목록 데이터 가져오기 (로그 분기를 위한 생성)
	 * @param vo MenuVO
	 * @return List of MenuVO
	 * @throws SQLException
	 */
	public List<MenuVO> viewMenuList(MenuVO vo) throws SQLException;
	
}
