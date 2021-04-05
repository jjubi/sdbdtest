/*
* 파 일 명 : CmtServiceImpl.java
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

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;
import egovframework.vaiv.kr.cmmn.bbs.ntt.cmt.service.CmtService;
import egovframework.vaiv.kr.cmmn.bbs.ntt.cmt.service.CmtVO;

/**
*  : 댓글 관리에 대한 기능 구현
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
@Service("CmtService")
public class CmtServiceImpl implements CmtService{
	
	/* 댓글 DAO 선언 */
	@Resource(name="CmtDAO")
	private CmtDAO cmtDAO;

	/**
	 * @method 설명 : 댓글 목록 조회
	 * @param : vo(CmtVO)
	 * @return : List of CmtVO
	 * @exception : SQLException
	 * */
	@Override
	public List<CmtVO> selectCmtList(SearchVO vo) throws SQLException {
		return cmtDAO.selectCmtList(vo);
	}

	/**
	 * @method 설명 : 댓글 등록
	 * @param : vo(CmtVO)
	 * @return : CmtVO
	 * @exception : SQLException
	 * */
	@Override
	public CmtVO insertCmt(CmtVO vo) throws SQLException {
		/* 인증된 사용자객체를 VO형식으로 가져온다. */
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		
		vo.setRegistId(user.getUniqId());
		vo.setUpdtId(user.getUniqId());
		vo.setRegistNm(user.getName());
		vo.setCmtId(cmtDAO.insertCmt(vo));
		
		return vo;
	}

	/**
	 * @method 설명 : 댓글 수정
	 * @param : vo(CmtVO)
	 * @exception : SQLException
	 * */
	@Override
	public void updateCmt(CmtVO vo) throws SQLException {
		/* 인증된 사용자객체를 VO형식으로 가져온다. */
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		cmtDAO.updateCmt(vo);
	}
	
	/**
	 * @method 설명 : 댓글 삭제
	 * @param : vo(CmtVO)
	 * @exception : SQLException
	 * */
	@Override
	public void deleteCmt(CmtVO vo) throws SQLException {
		/* 인증된 사용자객체를 VO형식으로 가져온다. */
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		cmtDAO.deleteCmt(vo);
	}

	/**
	 * @method 설명 : 댓글 상세 조회
	 * @param : vo(CmtVO)
	 * @return : CmtVO
	 * @exception : SQLException
	 * */
	@Override
	public CmtVO selectCmt(SearchVO vo) throws SQLException {
		return cmtDAO.selectCmt(vo);
	}
}
