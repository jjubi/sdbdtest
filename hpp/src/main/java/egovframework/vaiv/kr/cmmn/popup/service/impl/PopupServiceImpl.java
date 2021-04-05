package egovframework.vaiv.kr.cmmn.popup.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.popup.service.PopupService;
import egovframework.vaiv.kr.cmmn.popup.service.PopupVO;

/**
 * 팝업 관리 / 관리자 : 팝업 관리에 대한 비지니스 클래스 정의
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
@Service("PopupService")
public class PopupServiceImpl implements PopupService{
	/* 팝업 DAO 선언 */
	@Resource(name="PopupDAO")
	private PopupDAO popupDAO;
	
	/* 전자정부 파일 util 선언 */
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil egovFileMngUtil;

	/**
	 * 팝업 목록 조회
	 * @param vo 검색VO
	 * @return List of PopupVO 팝업VO 목록
	 * @throws SQLException
	 */
	@Override
	public List<PopupVO> selectPopupList(PopupVO vo) throws SQLException {
		return popupDAO.selectPopupList(vo);
	}

	/**
	 * 팝업 목록 총 갯수 조회
	 * @param vo 검색VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	@Override
	public int selectPopupListTotCnt(PopupVO vo) throws SQLException {
		return popupDAO.selectPopupListTotCnt(vo);
	}

	/**
	 * 입력된 정보로 팝업 등록
	 * @param vo 팝업VO
	 * @param multiReq Multipart Request
	 * @throws SQLException 
	 */
	@Override
	public void insertPopup(PopupVO vo, MultipartHttpServletRequest multiReq) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		vo.setRegistId(user.getUniqId());
		vo.setUpdtId(user.getUniqId());
		
		//첨부파일 저장
		String atchFileId = "";
		atchFileId = egovFileMngUtil.addFile(multiReq, "POP_");
		if(atchFileId != null && atchFileId != "") {
			vo.setAtchFileId(atchFileId);
		}
		
		popupDAO.insertPopup(vo);
	}
	
	/**
	 * 팝업 상세 조회
	 * @param vo 팝업VO
	 * @return PopupVO 팝업VO
	 * @throws SQLException
	 */
	@Override
	public PopupVO selectPopup(PopupVO vo) throws SQLException {
		return popupDAO.selectPopup(vo);
	}

	/**
	 * 입력된 정보로 팝업 수정
	 * @param vo 팝업VO
	 * @param multiReq Multipart Request
	 * @throws SQLException 
	 */
	@Override
	public void updatePopup(PopupVO vo, MultipartHttpServletRequest multiReq) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		vo.setUpdtId(user.getUniqId());
		
		//첨부파일 저장
		String atchFileId = "";
		atchFileId = egovFileMngUtil.addFile(multiReq, "POP_");
		if(atchFileId != null && atchFileId != "") {
			vo.setAtchFileId(atchFileId);
		}
		
		popupDAO.updatePopup(vo);
		
	}

	/**
	 * 입력된 정보로 팝업 삭제
	 * @param vo 팝업VO
	 * @throws SQLException
	 */
	@Override
	public void deletePopup(PopupVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		vo.setUpdtId(user.getUniqId());
		
		popupDAO.deletePopup(vo);
	}

	/**
	 * 팝업 순서 수정
	 * @param vo 팝업VO
	 * @return int 수정 팝업 수
	 * @throws SQLException
	 */
	@Override
	public int updatePopupOrdr(PopupVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		int updateCnt = 0;
		if(vo.getPopupOrdr() != null && !"".equals(vo.getPopupOrdr())) {
			int ordrIdx = 1;
			for(String ordrSeqNo : vo.getPopupOrdr().split("-")) {
				PopupVO updateVO = new PopupVO();
				updateVO.setPopupSeqNo(ordrSeqNo);
				updateVO.setPopupOrdr(String.valueOf(ordrIdx++));
				updateVO.setUpdtId(user.getUniqId());
				updateCnt += popupDAO.updatePopupOrdr(updateVO);
			}
		}
		
		return updateCnt;
	}

	/**
	 * 팝업 사용여부 수정
	 * @param vo 팝업VO
	 * @return int 수정 팝업 수
	 * @throws SQLException
	 */
	@Override
	public int updatePopupUseAt(PopupVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		
		return popupDAO.updatePopupUseAt(vo);
	}
}
