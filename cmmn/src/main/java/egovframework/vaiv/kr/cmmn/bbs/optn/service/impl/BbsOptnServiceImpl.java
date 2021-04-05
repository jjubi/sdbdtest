package egovframework.vaiv.kr.cmmn.bbs.optn.service.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;
import egovframework.vaiv.kr.cmmn.bbs.optn.service.BbsOptnService;
import egovframework.vaiv.kr.cmmn.bbs.optn.service.BbsOptnVO;

/**
 * 게시판 옵션 관리 / 관리자 : 게시판 옵션 관리에 대한 비지니스 클래스 정의
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
@Service("BbsOptnService")
public class BbsOptnServiceImpl implements BbsOptnService{

	/* 게시판 옵션 DAO 선언 */
	@Resource(name="BbsOptnDAO")
	private BbsOptnDAO bbsOptnDAO;
	
	/**
	 * 게시판 옵션 상세 조회
	 * @param vo 검색VO
	 * @return BbsOptnVO 게시판 옵션 VO
	 * @throws SQLException
	 */
	@Override
	public BbsOptnVO selectBbsOptn(SearchVO vo) throws SQLException {
		return bbsOptnDAO.selectBbsOptn(vo);
	}

	/**
	 * 게시판 옵션 수정
	 * @param vo 게시판 옵션 VO
	 * @throws SQLException
	 */
	@Override
	public void updateBbsOptn(BbsOptnVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		
		bbsOptnDAO.updateBbsOptn(vo);
	}

}
