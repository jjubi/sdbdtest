package egovframework.vaiv.kr.cmmn.qestnar.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.vaiv.kr.cmmn.qestnar.common.SearchVO;

/**
 * 설문관리 / 관리자 : 설문관리에 대한 인터페이스 정의
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
public interface QestnarService {

	/**
	 * 설문조사 목록 조회
	 * @param vo 검색VO
	 * @return List of QestnarVO 설문조사 목록
	 * @throws SQLException
	 */
	public List<QestnarVO> selectQestnarList(SearchVO vo) throws SQLException;
	
	/**
	 * 설문조사 목록 총 갯수 조회
	 * @param vo 검색VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	public int selectQestnarListTotCnt(SearchVO vo) throws SQLException;
	
	/**
	 * 설문조사 상세 조회
	 * @param vo 검색VO
	 * @return QestnarVO 설문조사VO
	 * @throws SQLException
	 */
	public QestnarVO selectQestnar(SearchVO vo) throws SQLException;
	
	/**
	 * 입력된 정보로 설문조사 등록
	 * @param vo 설문조사VO
	 * @throws SQLException
	 */
	public void insertQestnar(QestnarVO vo) throws SQLException;
	
	/**
	 * 입력된 정보로 설문조사 수정
	 * @param vo 설문조사VO
	 * @throws SQLException
	 */
	public void updateQestnar(QestnarVO vo) throws SQLException;

	/**
	 * 입력된 정보로 설문조사 삭제
	 * @param vo 설문조사VO
	 * @throws SQLException
	 */
	public void deleteQestnar(QestnarVO vo) throws SQLException;

	/**
	 * 설문조사 대상 목록 조회
	 * @param map 검색Map
	 * @return List of EgovMap 설문조사 대상 목록
	 * @throws SQLException
	 */
	public List<EgovMap> selectQestnarTargetList(Map map) throws SQLException;
	
	/**
	 * 설문조사 대상 목록 총 갯수 조회
	 * @param map 검색Map
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	public int selectQestnarTargetListTotCnt(Map map) throws SQLException;

	/**
	 * 설문조사 응답 결과 저장 (master)
	 * @param vo
	 * @throws SQLException
	 */
	public String insertQestnarResult(QestnarResultVO vo) throws SQLException;

	/**
	 * 설문조사 응답 결과 수정 (master)
	 * @param vo
	 * @throws SQLException
	 */
	public void updateQestnarResult(QestnarResultVO vo) throws SQLException;
	
	/**
	 * 설문조사 응답 결과 저장 (detail)
	 * @param vo
	 * @throws SQLException
	 */
	public void insertQestnarResultDtl(QestnarResultVO vo) throws SQLException;
	
	/**
	 * 설문조사 타켓 응답 체크
	 * @param vo
	 * @return QestnarResultVO
	 * @throws SQLException
	 */
	public QestnarResultVO checkQestnarResultTarget(QestnarResultVO vo) throws SQLException;
	
	/**
	 * 설문조사 통계 데이터 조회
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<EgovMap> selectQestnarResultStat(EgovMap map) throws SQLException;
	
}
