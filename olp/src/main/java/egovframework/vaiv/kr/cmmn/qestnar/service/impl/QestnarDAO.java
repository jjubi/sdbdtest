package egovframework.vaiv.kr.cmmn.qestnar.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.vaiv.kr.cmmn.qestnar.common.SearchVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarResultDtlVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarResultVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarTrgtVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarVO;

/**
 * 설문 관리 / 관리자 : 설문 관리에 대한 데이터 접근 클래스 정의
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
@Repository("QestnarDAO")
public class QestnarDAO extends EgovComAbstractDAO {
	/**
	 * 설문조사 목록 데이터 조회
	 * @param vo 검색VO
	 * @return List of QestnarVO 설문조사 목록
	 * @throws SQLException
	 */
	public List<QestnarVO> selectQestnarList(SearchVO vo) throws SQLException {
		return selectList("selectQestnarList", vo);
	}

	/**
	 * 설문조사 목록 총 갯수 데이터 조회
	 * @param vo 검색VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	public int selectQestnarListTotCnt(SearchVO vo) throws SQLException {
		return selectOne("selectQestnarListTotCnt", vo);
	}

	/**
	 * 설문조사 상세 데이터 조회
	 * @param vo 검색VO
	 * @return QestnarVO 설문조사VO
	 * @throws SQLException
	 */
	public QestnarVO selectQestnar(SearchVO vo) throws SQLException {
		QestnarVO returnVO = selectOne("selectQestnar", vo);
		if(returnVO != null) {
			returnVO.setQestnarTrgetList(selectList("selectQestnarTrgetList", vo));
		} else {
			returnVO = null;
		}
		return returnVO;
	}
	
	/**
	 * 입력된 정보로 설문조사 데이터 등록
	 * @param vo 설문조사VO
	 * @return String 설문조사 일련번호
	 * @throws SQLException
	 */
	public String insertQestnar(QestnarVO vo) throws SQLException {
		insert("insertQestnar", vo);
		return vo.getQestnarSeqNo();
	}
	
	/**
	 * 설문조사 대상 등록
	 * @param vo 설문조사 대상VO
	 * @throws SQLException
	 */
	public void insertQestnarTrget(QestnarTrgtVO vo) throws SQLException {
		insert("insertQestnarTrget", vo);
	}
	
	/**
	 * 설문조사 대상 삭제
	 * @param vo 설문조사 대상VO
	 * @throws SQLException
	 */
	public void deleteQestnarTrget(QestnarTrgtVO vo) throws SQLException {
		delete("deleteQestnarTrget", vo);
	}
	
	/**
	 * 입력된 정보로 설문조사 수정
	 * @param vo 설문조사VO
	 * @throws SQLException
	 */
	public void updateQestnar(QestnarVO vo) throws SQLException {
		update("updateQestnar", vo);
	}
	
	/**
	 * 입력된 정보로 설문조사 삭제
	 * @param vo 설문조사VO
	 * @throws SQLException
	 */
	public void deleteQestnar(QestnarVO vo) throws SQLException {
		delete("deleteQestnar", vo);
	}
	
	/**
	 * 설문조사 대상 목록 조회
	 * @param map 검색Map
	 * @return List of EgovMap 설문조사 대상 목록
	 * @throws SQLException
	 */
	public List<EgovMap> selectQestnarTargetList(Map map) throws SQLException {
		return selectList("selectQestnarTargetList", map);
	}

	/**
	 * 설문조사 대상 목록 총 갯수 조회
	 * @param map 검색Map
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	public int selectQestnarTargetListTotCnt(Map map) throws SQLException {
		return selectOne("selectQestnarTargetListTotCnt", map);
	}
	
	public QestnarResultVO selectQestnarResult(QestnarResultVO vo) throws SQLException {
		return selectOne("selectQestnarResult", vo);
	}
	
	public String insertQestnarResult(QestnarResultVO vo) throws SQLException {
		insert("insertQestnarResult", vo);
		return vo.getQestnarResultSeqNo();
	}
	
	public void updateQestnarResult(QestnarResultVO vo) throws SQLException {
		update("updateQestnarResult", vo);
	}
	
	public void insertQestnarResultDtl(QestnarResultDtlVO vo) throws SQLException {
		insert("insertQestnarResultDtl", vo);
	}
	
	public List<EgovMap> selectQestnarResultStat(EgovMap map) throws SQLException {
		return selectList("selectQestnarResultStat", map);
	}
}
