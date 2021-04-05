/*
* 파 일 명 : CmtService.java
* 작성일시 : 2020.12.31
* 작 성 자 : jo
* 수정이력
*
* 수정일      수정자        수정내용
*---------------   --------------   ------------------------------------
* 2020.12.31   jo      최초등록
* 
*********************************************************************************
* Copyright 2020 VAIV Co.
* All rights reserved
*/
package egovframework.vaiv.kr.cmmn.bbs.ntt.cmt.service;

import java.util.List;

import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;

import java.sql.SQLException;

/**
*  : 댓글 관리에 대한 기능을 정의 하는 인터페이스
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
public interface CmtService {
	/**
	 * @method 설명 : 댓글 등록
	 * @param : vo(CmtVO)
	 * @return : CmtVO
	 * @exception : SQLException
	 * */
	public CmtVO insertCmt(CmtVO vo) throws SQLException;
	
	/**
	 * @method 설명 : 댓글 수정
	 * @param : vo(CmtVO)
	 * @exception : SQLException
	 * */
	public void updateCmt(CmtVO vo) throws SQLException;

	/**
	 * @method 설명 : 댓글 삭제
	 * @param : vo(CmtVO)
	 * @exception : SQLException
	 * */
	public void deleteCmt(CmtVO vo) throws SQLException;

	/**
	 * @method 설명 : 댓글 목록 조회
	 * @param : vo(CmtVO)
	 * @return : List of CmtVO
	 * @exception : SQLException
	 * */
	public List<CmtVO> selectCmtList(SearchVO vo) throws SQLException;
	
	/**
	 * @method 설명 : 댓글 상세 조회
	 * @param : vo(CmtVO)
	 * @return : CmtVO
	 * @exception : SQLException
	 * */
	public CmtVO selectCmt(SearchVO vo) throws SQLException;
}
