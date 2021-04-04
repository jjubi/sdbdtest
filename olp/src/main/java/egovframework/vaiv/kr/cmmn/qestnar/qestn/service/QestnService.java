package egovframework.vaiv.kr.cmmn.qestnar.qestn.service;

import java.sql.SQLException;
import java.util.List;

import egovframework.vaiv.kr.cmmn.qestnar.common.SearchVO;

/**
 * 설문문항 관리 / 관리자 : 설문문항 관리에 대한 인터페이스 정의
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
public interface QestnService {
	/**
	 * 설문조사 문항 삭제
	 * @param vo QestnVO
	 * @throws SQLException
	 */
	public void deleteQestn(QestnVO vo) throws SQLException;
	
	/**
	 * 설문조사 문항 등록
	 * @param vo QestnVO
	 * @param qestnData 문항 목록
	 * @throws SQLException
	 */
	public void insertQestn(QestnVO vo, String qestnData) throws SQLException;
	
	/**
	 * 설문조사 문항 목록 조회
	 * @param vo 검색VO
	 * @return List of QestnVO 문항VO 목록
	 * @throws SQLException
	 */
	public List<QestnVO> selectQestnList(SearchVO vo) throws SQLException;
	
	/**
	 * 설문조사 문항 상세 조회
	 * @param vo 검색VO
	 * @return QestnVO 문항VO
	 * @throws SQLException
	 */
	public QestnVO selectQestn(SearchVO vo) throws SQLException;
}
