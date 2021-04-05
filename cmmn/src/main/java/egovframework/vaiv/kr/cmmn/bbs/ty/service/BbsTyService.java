package egovframework.vaiv.kr.cmmn.bbs.ty.service;

import java.sql.SQLException;
import java.util.List;

import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;

/**
 * 게시판 유형 관리 / 관리자 : 게시판 유형 관리에 대한 인터페이스 정의
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
public interface BbsTyService {
	/**
	 * 입력된 정보의 게시판 유형 등록
	 * @param vo 게시판 유형 VO
	 * @throws SQLException
	 */
	public void insertBbsTy(BbsTyVO vo) throws SQLException;
	
	/**
	 * 입력된 정보의 게시판 유형 수정
	 * @param vo 게시판 유형 VO
	 * @throws SQLException
	 */
	public void updateBbsTy(BbsTyVO vo) throws SQLException;
	
	/**
	 * 입력된 정보의 게시판 유형 삭제
	 * @param vo 게시판 유형 VO
	 * @throws SQLException
	 */
	public void deleteBbsTy(BbsTyVO vo) throws SQLException;
	
	/**
	 * 조건에 맞는 게시판 유형 목록 조회
	 * @param vo 검색VO
	 * @return List of BbsTyVO 게시판 유형 목록
	 * @throws SQLException
	 */
	public List<BbsTyVO> selectBbsTyList(SearchVO vo) throws SQLException;
	
	/**
	 * 조건에 맞는 게시판 유형 목록 총 갯수 조회
	 * @param vo 검색VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	public int selectBbsTyListTotCnt(SearchVO vo) throws SQLException;

	/**
	 * 조건에 맞는 게시판 유형 상세 조회
	 * @param vo 검색VO
	 * @return BbsTyVO 게시판 유형 VO
	 * @throws SQLException
	 */
	public BbsTyVO selectBbsTy(SearchVO vo) throws SQLException;
	
	/**
	 * 게시판 유형 코드 중복 조회
	 * @param : vo 검색VO
	 * @return : int 조회된 코드 수
	 * @throws : SQLException
	 * */
	public int checkBbsTyCode(SearchVO vo) throws SQLException;
}
