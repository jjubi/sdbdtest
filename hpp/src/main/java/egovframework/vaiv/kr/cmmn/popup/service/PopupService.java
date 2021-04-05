package egovframework.vaiv.kr.cmmn.popup.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 팝업 관리 / 관리자 : 팝업 관리에 대한 인터페이스 정의
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
public interface PopupService {
	/**
	 * 팝업 목록 조회
	 * @param vo 검색VO
	 * @return List of PopupVO 팝업VO 목록
	 * @throws SQLException
	 */
	public List<PopupVO> selectPopupList(PopupVO vo) throws SQLException;
	
	/**
	 * 팝업 목록 총 갯수 조회
	 * @param vo 검색VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	public int selectPopupListTotCnt(PopupVO vo) throws SQLException;
	
	/**
	 * 입력된 정보로 팝업 등록
	 * @param vo 팝업VO
	 * @param multiReq Multipart Request
	 * @throws SQLException 
	 */
	public void insertPopup(PopupVO vo, MultipartHttpServletRequest multiReq) throws SQLException;
	
	/**
	 * 팝업 상세 조회
	 * @param vo 팝업VO
	 * @return PopupVO 팝업VO
	 * @throws SQLException
	 */
	public PopupVO selectPopup(PopupVO vo) throws SQLException;
	
	/**
	 * 입력된 정보로 팝업 수정
	 * @param vo 팝업VO
	 * @param multiReq Multipart Request
	 * @throws SQLException 
	 */
	public void updatePopup(PopupVO vo, MultipartHttpServletRequest multiReq) throws SQLException;
	
	/**
	 * 입력된 정보로 팝업 삭제
	 * @param vo 팝업VO
	 * @throws SQLException
	 */
	public void deletePopup(PopupVO vo) throws SQLException;
	
	/**
	 * 팝업 순서 수정
	 * @param vo 팝업VO
	 * @return int 수정 팝업 수
	 * @throws SQLException
	 */
	public int updatePopupOrdr(PopupVO vo) throws SQLException;
	
	/**
	 * 팝업 사용여부 수정
	 * @param vo 팝업VO
	 * @return int 수정 팝업 수
	 * @throws SQLException
	 */
	public int updatePopupUseAt(PopupVO vo) throws SQLException;
}
