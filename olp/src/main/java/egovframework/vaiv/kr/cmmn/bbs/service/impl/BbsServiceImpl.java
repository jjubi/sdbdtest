package egovframework.vaiv.kr.cmmn.bbs.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.vaiv.kr.cmmn.bbs.author.service.impl.BbsAuthorDAO;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;
import egovframework.vaiv.kr.cmmn.bbs.optn.service.impl.BbsOptnDAO;
import egovframework.vaiv.kr.cmmn.bbs.service.BbsService;
import egovframework.vaiv.kr.cmmn.bbs.service.BbsVO;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;

/**
 * 게시판 관리 / 관리자 : 게시판 관리에 대한 비지니스 클래스 정의
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
@Service("BbsService")
public class BbsServiceImpl implements BbsService{

	/* 게시판 DAO 선언 */
	@Resource(name="BbsDAO")
	private BbsDAO bbsDAO;
	
	/* 게시판 권한 DAO 선언 */
	@Resource(name="BbsAuthorDAO")
	private BbsAuthorDAO bbsAuthorDAO;
	
	/* 게시판 옵션 DAO 선언 */
	@Resource(name="BbsOptnDAO")
	private BbsOptnDAO bbsOptnDAO;
	
	/* 게시판 ID 생성 서비스 선언 */
	@Resource(name="bbsGnrService")
    private EgovIdGnrService bbsGnrService;
	
	/**
	 * 게시판 목록 조회
	 * @param vo 검색VO
	 * @return List of BbsVO 게시판VO 목록
	 * @throws SQLException
	 */
	@Override
	public List<BbsVO> selectBbsList(SearchVO vo) throws SQLException {
		return bbsDAO.selectBbsList(vo);
	}

	/**
	 * 게시판 목록 총 갯수 조회
	 * @param vo 검색VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	@Override
	public int selectBbsListTotCnt(SearchVO vo) throws SQLException {
		return bbsDAO.selectBbsListTotCnt(vo);
	}

	/**
	 * 입력된 정보로 게시판 등록
	 * @param vo 게시판VO
	 * @throws SQLException
	 */
	@Override
	public void insertBbs(BbsVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		vo.setRegistId(user.getUniqId());
		vo.setUpdtId(user.getUniqId());
		
		if("".equals(vo.getBbsPdBgnde()) || "".equals(vo.getBbsPdEndde())) {
			vo.setBbsPdBgnde(null);
			vo.setBbsPdEndde(null);
		}
		
		String bbsId = "";
		try {
			bbsId = bbsGnrService.getNextStringId();
			vo.setBbsId(bbsId);
		} catch (FdlException e) {
			new Loggable().exLogging("insertBbs", e);
		}
		
		bbsDAO.insertBbs(vo);
		
		//권한, 옵션 최초 N으로 모두 저장
		bbsAuthorDAO.insertDefaultBbsAuthor(bbsId);
		bbsOptnDAO.insertDefaultBbsOptn(bbsId);
	}

	/**
	 * 게시판 상세 조회
	 * @param vo 검색VO
	 * @return BbsVO 게시판VO
	 * @throws SQLException
	 */
	@Override
	public BbsVO selectBbs(SearchVO vo) throws SQLException {
		return bbsDAO.selectBbs(vo);
	}

	/**
	 * 입력된 정보로 게시판 수정
	 * @param vo 게시판VO
	 * @throws SQLException
	 */
	@Override
	public void updateBbs(BbsVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		
		if("".equals(vo.getBbsPdBgnde()) || "".equals(vo.getBbsPdEndde())) {
			vo.setBbsPdBgnde(null);
			vo.setBbsPdEndde(null);
		}
		
		bbsDAO.updateBbs(vo);
	}

	/**
	 * 게시판 삭제
	 * @param vo 게시판VO
	 * @throws SQLException
	 */
	@Override
	public void deleteBbs(BbsVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		
		bbsDAO.deleteBbs(vo);
	}

}
