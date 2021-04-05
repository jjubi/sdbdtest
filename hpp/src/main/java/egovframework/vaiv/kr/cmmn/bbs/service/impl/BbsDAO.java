package egovframework.vaiv.kr.cmmn.bbs.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;
import egovframework.vaiv.kr.cmmn.bbs.service.BbsVO;

/**
 * 게시판 관리 / 관리자 : 게시판 관리에 대한 데이터 접근 클래스 정의
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
@Repository("BbsDAO")
public class BbsDAO extends EgovComAbstractDAO{
	/**
	 * 게시판 목록 데이터 조회
	 * @param vo 검색VO
	 * @return List of BbsVO 게시판VO 목록
	 * @throws SQLException
	 */
	public List<BbsVO> selectBbsList(SearchVO vo) throws SQLException {
		return selectList("selectBbsList", vo);
	}

	/**
	 * 게시판 목록 총 갯수 데이터 조회
	 * @param vo 검색VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	public int selectBbsListTotCnt(SearchVO vo) throws SQLException {
		return selectOne("selectBbsListTotCnt", vo);
	}
	
	/**
	 * 입력된 정보로 게시판 데이터 등록
	 * @param vo 게시판VO
	 * @throws SQLException
	 */
	public void insertBbs(BbsVO vo) throws SQLException {
		insert("insertBbs", vo);
	}
	
	/**
	 * 게시판 상세 데이터 조회
	 * @param vo 검색VO
	 * @return BbsVO 게시판VO
	 * @throws SQLException
	 */
	public BbsVO selectBbs(SearchVO vo) throws SQLException {
		return selectOne("selectBbs", vo);
	}
	
	/**
	 * 입력된 정보로 게시판 데이터 수정
	 * @param vo 게시판VO
	 * @throws SQLException
	 */
	public void updateBbs(BbsVO vo) throws SQLException {
		update("updateBbs", vo);
	}
	
	/**
	 * 게시판 데이터 삭제
	 * @param vo 게시판VO
	 * @throws SQLException
	 */
	public void deleteBbs(BbsVO vo) throws SQLException {
		delete("deleteBbs", vo);
	}
}
