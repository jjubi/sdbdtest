/*
* 파 일 명 : QestnarSenarioService.java
* 작성일시 : 2020.12.31
* 작 성 자 : jo
* 수정이력
*
* 수정일      수정자        수정내용
*---------------   --------------   ------------------------------------
* 2020.12.31   jo      최초등록
* 
*********************************************************************************
* Copyright 2020 VAIV Co.
* All rights reserved
*/
package egovframework.vaiv.kr.cmmn.qestnar.service;

import java.sql.SQLException;
import java.util.List;

import egovframework.vaiv.kr.cmmn.qestnar.common.SearchVO;

/**
*  : 설문 시나리오에 대한 기능을 정의 하는 인터페이스
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
public interface QestnarSenarioService {
	/**
	 * @method 설명 : 설문조사 시나리오 목록 조회
	 * @param : vo(SearchVO)
	 * @return : List of QestnarSenarioVO
	 * @exception : SQLException
	 * */
	public List<QestnarSenarioVO> selectQestnarSenarioList(SearchVO vo) throws SQLException; 
	
	/**
	 * @method 설명 : 설문조사 시나리오 저장
	 * @param : senarioJsonData(String)
	 * @exception : SQLException
	 * */
	public void insertQestnarSenario(String senarioJsonData) throws SQLException;
	
	/**
	 * @method 설명 : 설문조사 시나리오 체크 후 다음 문항 번호 가져오기
	 * @param : answerValue(String), vo(SearchVO)
	 * @return : int
	 * @exception : SQLException
	 * */
	public int selectNextQestnSenarioCheck(String answerValue, SearchVO vo) throws SQLException;
}
