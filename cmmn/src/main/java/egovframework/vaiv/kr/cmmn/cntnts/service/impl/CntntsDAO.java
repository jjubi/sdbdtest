package egovframework.vaiv.kr.cmmn.cntnts.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.vaiv.kr.cmmn.cntnts.service.CntntsHisVO;
import egovframework.vaiv.kr.cmmn.cntnts.service.CntntsVO;

/**
 * 컨텐츠 관리 / 관리자 : 컨텐츠 관리에 대한 데이터 접근 클래스 정의
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
@Repository("CntntsDAO")
public class CntntsDAO extends EgovComAbstractDAO{
	
	/**
	 * 컨텐츠 목록 데이터 조회
	 * @param vo 검색VO
	 * @return List of CntntsVO 컨텐츠VO 목록
	 * @throws SQLException
	 * */
	public List<CntntsVO> selectCntntsList(CntntsVO vo) throws SQLException {
		return selectList("selectCntntsList", vo);
	}

	/**
	 * 컨텐츠 목록 총 개수 데이터 조회
	 * @param vo 검색VO
	 * @return int 총 갯수
	 * @throws SQLException
	 * */
	public int selectCntntsListTotCnt(CntntsVO vo) throws SQLException {
		return selectOne("selectCntntsListTotCnt", vo);
	}
	
	/**
	 * 입력된 정보의 컨텐츠 데이터 등록
	 * @param vo 컨텐츠 VO
	 * @throws SQLException
	 * */
	public void insertCntnts(CntntsVO vo) throws SQLException {
		insert("insertCntnts", vo);
	}
	
	/**
	 * 컨텐츠 상세 데이터 조회
	 * @param vo 검색VO
	 * @return CntntsVO 컨텐츠VO
	 * @throws SQLException
	 * */
	public CntntsVO selectCntnts(CntntsVO vo) throws SQLException {
		return selectOne("selectCntnts", vo);
	}
	
	/**
	 * 입력된 정보로 컨텐츠 수정
	 * @param vo 컨텐츠VO
	 * @throws SQLException
	 * */
	public void updateCntnts(CntntsVO vo) throws SQLException {
		//수정 전 현재 컨텐츠 정보 저장
		CntntsVO hisVO = new CntntsVO();
		hisVO.setCntntsId(vo.getCntntsId());
		insertHistoryCntnts(hisVO);
				
		update("updateCntnts", vo);
	}
	
	/**
	 * 입력된 정보의 컨텐츠 삭제
	 * @param vo 컨텐츠VO
	 * @throws SQLException
	 * */
	public void deleteCntnts(CntntsVO vo) throws SQLException {
		update("deleteCntnts", vo);
	}
	
	/**
	 * 컨텐츠 히스토리 데이터 저장
	 * @param vo 컨텐츠 VO
	 * @throws SQLException
	 */
	public void insertHistoryCntnts(CntntsVO vo) throws SQLException {
		insert("insertHistoryCntnts", vo);
	}
	
	/**
	 * 컨텐츠 히스토리 목록 조회
	 * @param vo 검색VO
	 * @return List of CntntsHisVO 컨텐츠 히스토리 목록
	 * @throws SQLException
	 * */
	public List<CntntsHisVO> selectCntntsHisList(CntntsHisVO vo) throws SQLException {
		return selectList("selectCntntsHisList", vo);
	}
	
	/**
	 * 컨텐츠 히스토리 목록 총 개수 조회
	 * @param vo 컨텐츠 히스토리VO
	 * @return int 총 갯수
	 * @throws SQLException
	 * */
	public int selectCntntsHisListTotCnt(CntntsHisVO vo) throws SQLException {
		return selectOne("selectCntntsHisListTotCnt", vo);
	}
	
	/**
	 * 컨텐츠 히스토리 상세 조회
	 * @param vo 컨텐츠 히스토리VO
	 * @return CntntsHisVO 컨텐츠 히스토리VO
	 * @throws SQLException
	 * */
	public CntntsHisVO selectCntntsHis(CntntsHisVO vo) throws SQLException {
		return selectOne("selectCntntsHis", vo);
	}
}
