package egovframework.vaiv.kr.cmmn.qestnar.qestn.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.vaiv.kr.cmmn.qestnar.common.SearchVO;
import egovframework.vaiv.kr.cmmn.qestnar.qestn.service.QestnAswperVO;
import egovframework.vaiv.kr.cmmn.qestnar.qestn.service.QestnOptnVO;
import egovframework.vaiv.kr.cmmn.qestnar.qestn.service.QestnVO;

/**
 * 설문문항 관리 / 관리자 : 설문문항 관리에 대한 데이터 접근 클래스 정의
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
@Repository("QestnDAO")
public class QestnDAO extends EgovComAbstractDAO {
	/**
	 * 설문조사 문항 삭제
	 * @param vo QestnVO
	 * @throws SQLException
	 */
	public void deleteQestn(QestnVO vo) throws SQLException {
		//히스토리 저장
		insert("insertQestnHistory", vo);
		delete("deleteQestnOptn", vo);
		delete("deleteQestnAswper", vo);
		delete("deleteQestn", vo);
	}

	/**
	 * 설문조사 문항 데이터 저장
	 * @param vo QestnVO
	 * @return String 문항 일련번호
	 * @throws SQLException
	 */
	public String insertQestn(QestnVO vo) throws SQLException {
		insert("insertQestn", vo);
		return vo.getQestnSeqNo();
		
	}
	
	/**
	 * 설문조사 문항 옵션 데이터 저장
	 * @param vo QestnOptnVO
	 * @throws SQLException
	 */
	public void insertQestnOptn(QestnOptnVO vo) throws SQLException {
		insert("insertQestnOptn", vo);
	}
	
	/**
	 * 설문조사 문항 답안 데이터 저장
	 * @param vo QestnAswperVO
	 * @throws SQLException
	 */
	public void insertQestnAswper(QestnAswperVO vo) throws SQLException {
		insert("insertQestnAswper", vo);
	}
	
	/**
	 * 설문 문항 데이터 목록 조회
	 * @param vo 검색VO
	 * @return List of QestnVO 문항 목록VO
	 * @throws SQLException
	 */
	public List<QestnVO> selectQestnList(SearchVO vo) throws SQLException {
		List<QestnVO> returnList = selectList("selectQestnList", vo);
		
		for(QestnVO qVO : returnList) {
			qVO.setQestnAswperList(selectList("selectQestnAswperList", qVO));
			qVO.setQestnOptnList(selectList("selectQestnOptnList", qVO));
		}
		
		return returnList;
	}
	
	/**
	 * 설문조사 문항 데이터 상세 조회
	 * @param vo 검색VO
	 * @return QestnVO 문항VO
	 * @throws SQLException
	 */
	public QestnVO selectQestn(SearchVO vo) throws SQLException {
		QestnVO qvo = selectOne("selectQestn", vo);
		List<QestnAswperVO> aswperList = selectList("selectQestnAswperList", vo);
		if(aswperList != null && !aswperList.isEmpty()) {
			qvo.setQestnAswperList(aswperList);
		}
		
		List<QestnOptnVO> optnList = selectList("selectQestnOptnList", vo);
		if(optnList != null && !optnList.isEmpty()) {
			qvo.setQestnOptnList(optnList);
		}
		
		return qvo;
	}
}
