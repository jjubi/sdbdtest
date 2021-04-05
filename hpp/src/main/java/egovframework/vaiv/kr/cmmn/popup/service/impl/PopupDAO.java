package egovframework.vaiv.kr.cmmn.popup.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.vaiv.kr.cmmn.popup.service.PopupVO;

/**
 * 팝업 관리 / 관리자 : 팝업 관리에 대한 데이터 접근 클래스 정의
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
@Repository("PopupDAO")
public class PopupDAO extends EgovComAbstractDAO{
	/**
	 * 팝업 목록 데이터 조회
	 * @param vo 검색VO
	 * @return List of PopupVO 팝업VO 목록
	 * @throws SQLException
	 */
	public List<PopupVO> selectPopupList(PopupVO vo) throws SQLException {
		return selectList("selectPopupList", vo);
	}

	/**
	 * 팝업 목록 총 갯수 데이터 조회
	 * @param vo 검색VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	public int selectPopupListTotCnt(PopupVO vo) throws SQLException {
		return selectOne("selectPopupListTotCnt", vo);
	}
	
	/**
	 * 입력된 정보로 팝업 데이터 등록
	 * @param vo 팝업VO
	 * @param multiReq Multipart Request
	 * @throws SQLException 
	 */
	public void insertPopup(PopupVO vo) throws SQLException {
		insert("insertPopup", vo);
	}
	
	/**
	 * 팝업 상세 데이터 조회
	 * @param vo 팝업VO
	 * @return PopupVO 팝업VO
	 * @throws SQLException
	 */
	public PopupVO selectPopup(PopupVO vo) throws SQLException {
		return selectOne("selectPopup", vo);
	}
	
	/**
	 * 입력된 정보로 팝업 데이터 수정
	 * @param vo 팝업VO
	 * @param multiReq Multipart Request
	 * @throws SQLException 
	 */
	public void updatePopup(PopupVO vo) throws SQLException {
		update("updatePopup", vo);
	}
	
	/**
	 * 입력된 정보로 팝업 데이터 삭제
	 * @param vo 팝업VO
	 * @throws SQLException
	 */
	public void deletePopup(PopupVO vo) throws SQLException {
		delete("deletePopup", vo);
	}
	
	/**
	 * 팝업 순서 데이터 수정
	 * @param vo 팝업VO
	 * @return int 수정 팝업 수
	 * @throws SQLException
	 */
	public int updatePopupOrdr(PopupVO vo) throws SQLException {
		return update("updatePopupOrdr", vo);
	}
	
	/**
	 * 팝업 사용여부 데이터 수정
	 * @param vo 팝업VO
	 * @return int 수정 팝업 수
	 * @throws SQLException
	 */
	public int updatePopupUseAt(PopupVO vo) throws SQLException {
		return update("updatePopupUseAt", vo);
	}
}
