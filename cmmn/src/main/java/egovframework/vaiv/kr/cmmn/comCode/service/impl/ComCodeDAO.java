package egovframework.vaiv.kr.cmmn.comCode.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.vaiv.kr.cmmn.comCode.service.ComCodeVO;

/**
 * 공통코드 관리 : 공통코드 관리에 대한 데이터 접근 클래스 정의
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
@Repository("ComCodeDAO")
public class ComCodeDAO extends EgovComAbstractDAO {

	/**
	 * 공통코드 목록 데이터 조회
	 * @param vo 검색VO
	 * @return List of ComCodeVO 공통코드 목록
	 * @throws SQLException
	 */
	public List<ComCodeVO> selectComCodeList(ComCodeVO vo) throws SQLException {
		return selectList("selectComCodeList", vo);
	}

	/**
	 * 공통코드 목록 데이터 총 갯수 조회
	 * @param vo 공통코드VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	public int selectComCodeListTotCnt(ComCodeVO vo) throws SQLException {
		return selectOne("selectComCodeListTotCnt", vo);
	}
	
	/**
	 * 공통코드 중복 체크
	 * @param code 코드
	 * @return int 중복코드 갯수
	 * @throws SQLException
	 */
	public int checkCodeDplct(String code) throws SQLException {
		return selectOne("checkCodeDplct", code);
	}
	
	/**
	 * 입력된 정보의 공통코드 데이터 등록
	 * @param vo 공통코드VO
	 * @throws SQLException
	 */
	public void insertComCode(ComCodeVO vo) throws SQLException {
		insert("insertComCode", vo);
	}
	
	/**
	 * 입력된 정보로 공통코드 데이터 수정
	 * @param vo 공통코드VO
	 * @throws SQLException
	 */
	public void updateComCode(ComCodeVO vo) throws SQLException {
		update("updateComCode", vo);
	}

	/**
	 * 입력된 정보로 공통코드 데이터 삭제
	 * @param vo 공통코드VO
	 * @throws SQLException
	 */
	public void deleteComCode(ComCodeVO vo) throws SQLException {
		delete("deleteComCode", vo);
	}

	/**
	 * 공통코드 상세 데이터 조회
	 * @param vo 공통코드VO
	 * @return ComCodeVO 공통코드VO
	 * @throws SQLException
	 */
	public ComCodeVO selectComCode(ComCodeVO vo) throws SQLException {
		return selectOne("selectComCode", vo);
	}

}
