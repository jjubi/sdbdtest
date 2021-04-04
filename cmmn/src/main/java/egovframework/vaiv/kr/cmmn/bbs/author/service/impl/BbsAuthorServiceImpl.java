package egovframework.vaiv.kr.cmmn.bbs.author.service.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.bbs.author.service.BbsAuthorService;
import egovframework.vaiv.kr.cmmn.bbs.author.service.BbsAuthorVO;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;

/**
 * 게시판 권한 관리 / 관리자 : 게시판권한관리에 대한 비지니스 클래스 정의
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
@Service("BbsAuthorService")
public class BbsAuthorServiceImpl implements BbsAuthorService{

	/* 게시판 권한 DAO 선언 */
	@Resource(name="BbsAuthorDAO")
	private BbsAuthorDAO bbsAuthorDAO;
	
	/**
	 * 게시판 권한 상세 조회
	 * @param vo 검색VO
	 * @return BbsAuthorVO 게시판권한VO
	 * @throws SQLException
	 */
	@Override
	public BbsAuthorVO selectBbsAuthor(SearchVO vo) throws SQLException {
		return bbsAuthorDAO.selectBbsAuthor(vo);
	}

	/**
	 * 게시판 권한 수정
	 * @param vo 게시판 권한 VO
	 * @throws SQLException
	 */
	@Override
	public void updateBbsAuthor(BbsAuthorVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		
		bbsAuthorDAO.updateBbsAuthor(vo);
	}

}
