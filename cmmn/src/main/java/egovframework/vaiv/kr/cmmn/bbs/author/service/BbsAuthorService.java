package egovframework.vaiv.kr.cmmn.bbs.author.service;

import java.sql.SQLException;

import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;

/**
 * 게시판 권한 관리 / 관리자 : 게시판 권한 관리에 대한 인터페이스 정의
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
public interface BbsAuthorService {

	/**
	 * 게시판 권한 상세 조회
	 * @param vo 검색VO
	 * @return BbsAuthorVO 게시판권한VO
	 * @throws SQLException
	 */
	public BbsAuthorVO selectBbsAuthor(SearchVO vo) throws SQLException;
	
	/**
	 * 게시판 권한 수정
	 * @param vo 게시판 권한 VO
	 * @throws SQLException
	 */
	public void updateBbsAuthor(BbsAuthorVO vo) throws SQLException;
}
