package egovframework.vaiv.kr.cmmn.bbs.optn.service.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;
import egovframework.vaiv.kr.cmmn.bbs.optn.service.BbsOptnVO;

/**
 * 게시판 옵션 관리 / 관리자 : 게시판 옵션 관리에 대한 데이터 접근 클래스 정의
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
@Repository("BbsOptnDAO")
public class BbsOptnDAO extends EgovComAbstractDAO {
	
	/**
	 * 게시판 옵션 초기 데이터 설정 및 저장 (게시판 생성 시 호출)
	 * @param bbsId 게시판 ID
	 */
	public void insertDefaultBbsOptn(String bbsId) {
		//기본 N으로 모두 저장
		String defaultOptn = "N";
		
		BbsOptnVO defaultBbsOptnVO = new BbsOptnVO();
		defaultBbsOptnVO.setBbsId(bbsId);
		defaultBbsOptnVO.setPopupUseAt(defaultOptn);
		defaultBbsOptnVO.setAtchFileUseAt(defaultOptn);
		defaultBbsOptnVO.setAtchFilePermExtsn(EgovProperties.getProperty("Globals.fileUpload.Extensions"));
		defaultBbsOptnVO.setAtchFilePermMxmmCnt("0");
		defaultBbsOptnVO.setAnswerUseAt(defaultOptn);
		defaultBbsOptnVO.setCmtUseAt(defaultOptn);
		defaultBbsOptnVO.setSecretUseAt(defaultOptn);
		defaultBbsOptnVO.setNoticeUseAt(defaultOptn);
		defaultBbsOptnVO.setLcIndictUseAt(defaultOptn);
		defaultBbsOptnVO.setCclUseAt(defaultOptn);
		defaultBbsOptnVO.setKoglUseAt(defaultOptn);
		
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		defaultBbsOptnVO.setRegistId(user.getUniqId());
		defaultBbsOptnVO.setUpdtId(user.getUniqId());
		
		insertBbsOptn(defaultBbsOptnVO);		
	}
	
	/**
	 * 게시판 옵션 데이터 저장
	 * @param vo
	 */
	public void insertBbsOptn(BbsOptnVO vo) {
		insert("insertBbsOptn", vo);
	}
	
	/**
	 * 게시판 옵션 상세 데이터 조회
	 * @param vo 검색VO
	 * @return BbsOptnVO 게시판 옵션 VO
	 * @throws SQLException
	 */
	public BbsOptnVO selectBbsOptn(SearchVO vo) throws SQLException {
		return selectOne("selectBbsOptn", vo);
	}
	
	/**
	 * 게시판 옵션 데이터 수정
	 * @param vo 게시판 옵션 VO
	 * @throws SQLException
	 */
	public void updateBbsOptn(BbsOptnVO vo) throws SQLException {
		update("updateBbsOptn", vo);
	}
}
