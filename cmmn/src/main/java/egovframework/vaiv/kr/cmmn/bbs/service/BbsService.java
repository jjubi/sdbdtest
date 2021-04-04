package egovframework.vaiv.kr.cmmn.bbs.service;

import java.sql.SQLException;

import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;

import java.util.List;

/**
 * 게시판 관리 / 관리자 : 게시판 관리에 대한 인터페이스 정의
 * @category 공통
 * @author jo
 * @since 2020-12-31
 * @version : v1.0
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
public interface BbsService {
	/**
	 * 입력된 정보로 게시판 등록
	 * @param vo 게시판VO
	 * @throws SQLException
	 */
	public void insertBbs(BbsVO vo) throws SQLException;

	/**
	 * 입력된 정보로 게시판 수정
	 * @param vo 게시판VO
	 * @throws SQLException
	 */
	public void updateBbs(BbsVO vo) throws SQLException;

	/**
	 * 게시판 삭제
	 * @param vo 게시판VO
	 * @throws SQLException
	 */
	public void deleteBbs(BbsVO vo) throws SQLException;

	/**
	 * 게시판 목록 조회
	 * @param vo 검색VO
	 * @return List of BbsVO 게시판VO 목록
	 * @throws SQLException
	 */
	public List<BbsVO> selectBbsList(SearchVO vo) throws SQLException;
	
	/**
	 * 게시판 목록 총 갯수 조회
	 * @param vo 검색VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	public int selectBbsListTotCnt(SearchVO vo) throws SQLException;
	
	/**
	 * 게시판 상세 조회
	 * @param vo 검색VO
	 * @return BbsVO 게시판VO
	 * @throws SQLException
	 */
	public BbsVO selectBbs(SearchVO vo) throws SQLException;
}
