package egovframework.vaiv.kr.cmmn.comCode.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.comCode.service.ComCodeService;
import egovframework.vaiv.kr.cmmn.comCode.service.ComCodeVO;

/**
 * 공통코드 관리 : 공통코드 관리에 대한 비지니스 클래스 정의
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
@Service("ComCodeService")
public class ComCodeServiceImpl extends Loggable implements ComCodeService{
	
	/* 공통코드 DAO 선언 */
    @Resource(name="ComCodeDAO")
    private ComCodeDAO comCodeDAO;

    /**
	 * 공통코드 목록 조회
	 * @param vo 검색VO
	 * @return List of ComCodeVO 공통코드 목록
	 * @throws SQLException
	 */
	@Override
	public List<ComCodeVO> selectComCodeList(ComCodeVO vo) throws SQLException {
		return comCodeDAO.selectComCodeList(vo);
	}
	
	/**
	 * 공통코드 목록 조회
	 * @param groupCode 그룹코드
	 * @return List of ComCodeVO 공통코드 목록
	 * @throws SQLException
	 */
	@Override
	public List<ComCodeVO> selectComCodeList(String groupCode) throws SQLException {
		ComCodeVO vo = new ComCodeVO();
		vo.setGroupCode(groupCode);
		vo.setUseAt("Y");
		vo.setPagingYn("N");
		return comCodeDAO.selectComCodeList(vo);
	}

	/**
	 * 공통코드 목록 총 갯수 조회
	 * @param vo 공통코드VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	@Override
	public int selectComCodeListTotCnt(ComCodeVO vo) throws SQLException {
		return comCodeDAO.selectComCodeListTotCnt(vo);
	}
	
	/**
	 * 공통코드 중복 체크
	 * @param code 코드
	 * @return int 중복코드 갯수
	 * @throws SQLException
	 */
	@Override
	public int checkCodeDplct(String code) throws SQLException {
		return comCodeDAO.checkCodeDplct(code);
	}

	/**
	 * 입력된 정보의 공통코드 등록
	 * @param vo 공통코드VO
	 * @throws SQLException
	 */
	@Override
	public void insertComCode(ComCodeVO vo) throws SQLException {
		comCodeDAO.insertComCode(vo);
	}
	
	/**
	 * 입력된 정보로 공통코드 수정
	 * @param vo 공통코드VO
	 * @throws SQLException
	 */
	@Override
	public void updateComCode(ComCodeVO vo) throws SQLException {
		comCodeDAO.updateComCode(vo);
	}

	/**
	 * 입력된 정보로 공통코드 삭제
	 * @param vo 공통코드VO
	 * @throws SQLException
	 */
	@Override
	public void deleteComCode(ComCodeVO vo) throws SQLException {
		comCodeDAO.deleteComCode(vo);
	}

	/**
	 * 공통코드 상세 조회
	 * @param vo 공통코드VO
	 * @return ComCodeVO 공통코드VO
	 * @throws SQLException
	 */
	@Override
	public ComCodeVO selectComCode(ComCodeVO vo) throws SQLException {
		return comCodeDAO.selectComCode(vo);
	}

	/**
	 * 공통코드 상세 조회
	 * @param code 코드
	 * @return ComCodeVO 공통코드VO
	 * @throws SQLException
	 */
	@Override
	public ComCodeVO selectComCode(String code) throws SQLException {
		ComCodeVO vo = new ComCodeVO();
		vo.setCode(code);
		vo.setUseAt("Y");
		return comCodeDAO.selectComCode(vo);
	}
    
}
