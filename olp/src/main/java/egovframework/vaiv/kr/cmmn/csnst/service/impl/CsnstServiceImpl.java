package egovframework.vaiv.kr.cmmn.csnst.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.csnst.service.CsnstService;
import egovframework.vaiv.kr.cmmn.csnst.service.CsnstVO;

/**
 * 만족도 조사 관리 : 만족도 조사 관리에 대한 비지니스 클래스 정의
 * @category 공통
 * @author jo
 * @since 2020-12-31
 * @version v1.0
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
@Service("CsnstService")
public class CsnstServiceImpl implements CsnstService{

	/* 만족도조사 DAO */
	@Resource(name="CsnstDAO")
	private CsnstDAO csnstDAO;
	
	/**
	 * 입력된 정보로 만족도 조사 등록
	 * @param vo 만족도조사VO
	 * @return int 등록된 만족도 조사 갯수
	 * @throws SQLException
	 */
	@Override
	public int insertCsnst(CsnstVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user != null) {
			vo.setRegistId(user.getUniqId());
		}
		
		return csnstDAO.insertCsnst(vo);
	}

	/**
	 * 조건에 맞는 만족도 조사 목록 조회
	 * @param vo 만족도조사VO
	 * @return List of CsnstVO 만족도 조사 목록
	 * @throws SQLException
	 */
	@Override
	public List<CsnstVO> selectCsnstList(CsnstVO vo) throws SQLException {
		return csnstDAO.selectCsnstList(vo);
	}

	/**
	 * 조건에 맞는 만족도 조사 목록 총 갯수 조회
	 * @param vo 만족도조사VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	@Override
	public int selectCsnstListTotCnt(CsnstVO vo) throws SQLException {
		return csnstDAO.selectCsnstListTotCnt(vo);
	}

}
