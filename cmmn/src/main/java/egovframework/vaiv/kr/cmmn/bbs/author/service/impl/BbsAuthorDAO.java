package egovframework.vaiv.kr.cmmn.bbs.author.service.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.bbs.author.service.BbsAuthorVO;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;

/**
 * 게시판 권한 관리 / 관리자 : 게시판 권한 관리에 대한 데이터 접근 클래스 정의
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
@Repository("BbsAuthorDAO")
public class BbsAuthorDAO extends EgovComAbstractDAO{
	
	/**
	 * 게시판 권한 초기 데이터 저장 (게시판 생성 시 호출)
	 * @param bbsId 게시판 ID
	 */
	public void insertDefaultBbsAuthor(String bbsId) {
		//기본 ROLE_USER으로 모두 저장
		String defaultAuth = "ROLE_USER";
		
		BbsAuthorVO defaultBbsAuthVO = new BbsAuthorVO();
		defaultBbsAuthVO.setBbsId(bbsId);
		defaultBbsAuthVO.setNttRedngAuthor(defaultAuth);
		defaultBbsAuthVO.setNttRegistAuthor(defaultAuth);
		defaultBbsAuthVO.setNttUpdtAuthor(defaultAuth);
		defaultBbsAuthVO.setNttDeleteAuthor(defaultAuth);
		defaultBbsAuthVO.setNttFileUploadAuthor(defaultAuth);
		defaultBbsAuthVO.setNttFileDwldAuthor(defaultAuth);
		defaultBbsAuthVO.setCmtRedngAuthor(defaultAuth);
		defaultBbsAuthVO.setCmtRegistAuthor(defaultAuth);
		defaultBbsAuthVO.setCmtUpdtAuthor(defaultAuth);
		defaultBbsAuthVO.setCmtDeleteAuthor(defaultAuth);
		defaultBbsAuthVO.setAnswerRedngAuthor(defaultAuth);
		defaultBbsAuthVO.setAnswerRegistAuthor(defaultAuth);
		defaultBbsAuthVO.setAnswerUpdtAuthor(defaultAuth);
		defaultBbsAuthVO.setAnswerDeleteAuthor(defaultAuth);
		defaultBbsAuthVO.setSecretUseAuthor(defaultAuth);
		defaultBbsAuthVO.setNoticeUseAuthor(defaultAuth);
		defaultBbsAuthVO.setLcIndictUseAuthor(defaultAuth);
		defaultBbsAuthVO.setPopupUseAuthor(defaultAuth);
		
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		defaultBbsAuthVO.setRegistId(user.getUniqId());
		defaultBbsAuthVO.setUpdtId(user.getUniqId());
		
		insertBbsAuthor(defaultBbsAuthVO);		
	}
	
	/**
	 * 게시판 권한 데이터 저장
	 * @param vo 게시판 권한 VO
	 */
	public void insertBbsAuthor(BbsAuthorVO vo) {
		insert("insertBbsAuthor", vo);
	}
	
	/**
	 * 게시판 권한 상세 조회
	 * @param vo 검색VO
	 * @return BbsAuthorVO 게시판권한VO
	 * @throws SQLException
	 */
	public BbsAuthorVO selectBbsAuthor(SearchVO vo) throws SQLException {
		return selectOne("selectBbsAuthor", vo);
	}
	
	/**
	 * 게시판 권한 수정
	 * @param vo 게시판 권한 VO
	 * @throws SQLException
	 */
	public void updateBbsAuthor(BbsAuthorVO vo) throws SQLException {
		update("updateBbsAuthor", vo);
	}
}
