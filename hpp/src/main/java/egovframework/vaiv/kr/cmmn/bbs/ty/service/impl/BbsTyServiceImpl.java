package egovframework.vaiv.kr.cmmn.bbs.ty.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;
import egovframework.vaiv.kr.cmmn.bbs.ty.service.BbsTyService;
import egovframework.vaiv.kr.cmmn.bbs.ty.service.BbsTyVO;

/**
 * 게시판 유형 관리 / 관리자 : 게시판 유형 관리에 대한 비지니스 클래스 정의
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
@Service("BbsTyService")
public class BbsTyServiceImpl implements BbsTyService{
	
	/* 게시판 유형 DAO 선언 */
	@Resource(name="BbsTyDAO")
	private BbsTyDAO BbsTyDAO;
	
	/* 게시판 유형 ID 생성 서비스 선언 */
	@Resource(name="bbsTyGnrService")
    private EgovIdGnrService BbsTyGnrService;

	/**
	 * 조건에 맞는 게시판 유형 목록 조회
	 * @param vo 검색VO
	 * @return List of BbsTyVO 게시판 유형 목록
	 * @throws SQLException
	 */
	@Override
	public List<BbsTyVO> selectBbsTyList(SearchVO vo) throws SQLException {
		return BbsTyDAO.selectBbsTyList(vo);
	}

	/**
	 * 조건에 맞는 게시판 유형 목록 총 갯수 조회
	 * @param vo 검색VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	@Override
	public int selectBbsTyListTotCnt(SearchVO vo) throws SQLException {
		return BbsTyDAO.selectBbsTyListTotCnt(vo);
	}

	/**
	 * 입력된 정보의 게시판 유형 등록
	 * @param vo 게시판 유형 VO
	 * @throws SQLException
	 */
	@Override
	public void insertBbsTy(BbsTyVO vo) throws SQLException {
		try {
			LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			vo.setRegistId(user.getUniqId());
			vo.setUpdtId(user.getUniqId());
			//Id Gen
			vo.setBbsTyId(BbsTyGnrService.getNextStringId());
			
		} catch (FdlException e) {
			new Loggable().exLogging("getNextStringId", e);
		}
		
		BbsTyDAO.insertBbsTy(vo);
	}

	/**
	 * 조건에 맞는 게시판 유형 상세 조회
	 * @param vo 검색VO
	 * @return BbsTyVO 게시판 유형 VO
	 * @throws SQLException
	 */
	@Override
	public BbsTyVO selectBbsTy(SearchVO vo) throws SQLException {
		return BbsTyDAO.selectBbsTy(vo);
	}

	/**
	 * 입력된 정보의 게시판 유형 수정
	 * @param vo 게시판 유형 VO
	 * @throws SQLException
	 */
	@Override
	public void updateBbsTy(BbsTyVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		
		BbsTyDAO.updateBbsTy(vo);
	}

	/**
	 * 입력된 정보의 게시판 유형 삭제
	 * @param vo 게시판 유형 VO
	 * @throws SQLException
	 */
	@Override
	public void deleteBbsTy(BbsTyVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		
		BbsTyDAO.deleteBbsTy(vo);
	}

	/**
	 * 게시판 유형 코드 중복 조회
	 * @param : vo 검색VO
	 * @return : int 조회된 코드 수
	 * @throws : SQLException
	 * */
	@Override
	public int checkBbsTyCode(SearchVO vo) throws SQLException {
		return BbsTyDAO.checkBbsTyCode(vo);
	}
}
