package egovframework.vaiv.kr.cmmn.menu.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.vaiv.kr.cmmn.menu.service.MenuVO;

/**
 * 메뉴 관리 / 관리자 : 메뉴 관리에 대한 데이터 접근 클래스 정의
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
@Repository("MenuDAO")
public class MenuDAO extends EgovComAbstractDAO{
	
	/**
	 * 메뉴 목록 데이터 조회
	 * @param vo MenuVO
	 * @return List of MenuVO
	 * @throws SQLException
	 */
	public List<MenuVO> selectMenuList(MenuVO vo) throws SQLException {
		return selectList("selectMenuList", vo);
	}
	
	/**
	 * 입력된 정보로 메뉴 데이터 저장
	 * @param vo MenuVO
	 * @return int 저장된 메뉴 수
	 * @throws SQLException
	 */
	public int insertMenu(MenuVO vo) throws SQLException {
		return insert("insertMenu", vo);
	}
	
	/**
	 * 기존 메뉴 목록 데이터 히스토리로 저장 (목록)
	 * @param vo MenuVO
	 * @throws SQLException
	 */
	public void insertHistoryMenu(MenuVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null) {
			vo.setRegistId("system");
		} else {
			vo.setRegistId(user.getUniqId());
		}
		
		insert("insertHistoryMenu", vo);
	}
	
	/**
	 * 기존 메뉴 데이터 히스토리로 저장 (1개)
	 * @param menuSeqNo 메뉴 일련번호
	 * @throws SQLException
	 */
	public void insertOneHistoryMenu(int menuSeqNo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		EgovMap param = new EgovMap();
		param.put("value", user.getUniqId());
		param.put("menuSeqNo", menuSeqNo);
		insert("insertOneHistoryMenu", param);
	}
	
	/**
	 * 입력된 정보로 메뉴 데이터 수정
	 * @param vo MenuVO
	 * @throws SQLException
	 */
	public void updateMenu(MenuVO vo) throws SQLException {
		update("updateMenu", vo);
	}
	
	/**
	 * 메뉴 데이터 삭제
	 * @param vo MenuVO
	 * @throws SQLException
	 */
	public void deleteMenu(MenuVO vo) throws SQLException {
		delete("deleteMenu", vo);
	}
	
}
