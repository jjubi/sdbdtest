package egovframework.vaiv.kr.cmmn.csnst.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.vaiv.kr.cmmn.csnst.service.CsnstVO;

/**
 * 만족도 조사 관리 : 만족도 조사 관리에 대한 데이터 접근 클래스 정의
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
@Repository("CsnstDAO")
public class CsnstDAO extends EgovComAbstractDAO {
	

	/**
	 * @method 설명 : 만족도조사 데이터 저장
	 * @param : vo(CsnstVO)
	 * @return : int
	 * @exception : SQLException
	 * */
	public int insertCsnst(CsnstVO vo) throws SQLException {
		return insert("insertCsnst", vo);
	}
	
	/**
	 * 조건에 맞는 만족도 조사 목록 데이터 조회
	 * @param vo 만족도조사VO
	 * @return List of CsnstVO 만족도 조사 목록
	 * @throws SQLException
	 */
	public List<CsnstVO> selectCsnstList(CsnstVO vo) throws SQLException {
		return selectList("selectCsnstList", vo);
	}

	/**
	 * 조건에 맞는 만족도 조사 목록 총 갯수 데이터 조회
	 * @param vo 만족도조사VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	public int selectCsnstListTotCnt(CsnstVO vo) throws SQLException {
		return selectOne("selectCsnstListTotCnt", vo);
	}
}
