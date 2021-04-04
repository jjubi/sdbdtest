/*
* 파 일 명 : StatsService.java
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
package egovframework.vaiv.kr.cmmn.stats.service;

import java.sql.SQLException;
import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
*  : 통계 데이터 조회에 대한 기능을 정의하는 인터페이스
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
public interface StatsService {
	/**
	 * @method 설명 : 로그인 로그 통계
	 * @param : map(EgovMap)
	 * @return : EgovMap
	 * @exception : SQLException
	 * */
	public EgovMap selectLoginLogStats(EgovMap map) throws SQLException;
	
	/**
	 * @method 설명 : 시스템 로그 통계
	 * @param : map(EgovMap)
	 * @return : EgovMap
	 * @exception : SQLException
	 * */
	public EgovMap selectSysLogStats(EgovMap map) throws SQLException;
	
	/**
	 * @method 설명 : 게시판별 게시물 통계
	 * @param : map(EgovMap)
	 * @return : EgovMap
	 * @exception : SQLException
	 * */
	public EgovMap selectBbsToNttStats(EgovMap map) throws SQLException;
	
	/**
	 * @method 설명 : 메뉴 사용 통계
	 * @param : map(EgovMap)
	 * @return : EgovMap
	 * @exception : SQLException
	 * */
	public EgovMap selectMenuUseStats(EgovMap map) throws SQLException;
	
	/**
	 * @method 설명 : 회원별 메뉴 사용 통계
	 * @param : map(EgovMap)
	 * @return : EgovMap
	 * @exception : SQLException
	 * */
	public EgovMap selectMemberToMenuStats(EgovMap map) throws SQLException;
	
	/**
	 * @method 설명 : 총 회원 수 통계
	 * @param : map(EgovMap)
	 * @return : EgovMap
	 * @exception : SQLException
	 * */
	public List<EgovMap> selectMberTotCntToView() throws SQLException;
}
