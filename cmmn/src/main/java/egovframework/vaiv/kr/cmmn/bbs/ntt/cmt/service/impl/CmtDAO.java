/*
* 파 일 명 : CmtDAO.java
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
package egovframework.vaiv.kr.cmmn.bbs.ntt.cmt.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;
import egovframework.vaiv.kr.cmmn.bbs.ntt.cmt.service.CmtVO;

/**
*  : 댓글 관리에 필요한 데이터를 조작하는 클래스
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
@Repository("CmtDAO")
public class CmtDAO extends EgovComAbstractDAO{
	/**
	 * @method 설명 : 댓글 목록 데이터 조회
	 * @param : vo(CmtVO)
	 * @return : List of CmtVO
	 * @exception : SQLException
	 * */
	public List<CmtVO> selectCmtList(SearchVO vo) throws SQLException {
		return selectList("selectCmtList", vo);
	}
	
	/**
	 * @method 설명 : 댓글 데이터 저장
	 * @param : vo(CmtVO)
	 * @return : String(댓글 id)
	 * @exception : SQLException
	 * */
	public String insertCmt(CmtVO vo) throws SQLException {
		insert("insertCmt", vo);
		return vo.getCmtId();
	}
	
	/**
	 * @method 설명 : 댓글 데이터 수정
	 * @param : vo(CmtVO)
	 * @exception : SQLException
	 * */
	public void updateCmt(CmtVO vo) throws SQLException {
		update("updateCmt", vo);
	}
	
	/**
	 * @method 설명 : 댓글 데이터 삭제
	 * @param : vo(CmtVO)
	 * @exception : SQLException
	 * */
	public void deleteCmt(CmtVO vo) throws SQLException {
		delete("deleteCmt", vo);
	}
	
	/**
	 * @method 설명 : 댓글 상세 데이터 조회
	 * @param : vo(CmtVO)
	 * @return : CmtVO
	 * @exception : SQLException
	 * */
	public CmtVO selectCmt(SearchVO vo) throws SQLException {
		return selectOne("selectCmt", vo);
	}
}
