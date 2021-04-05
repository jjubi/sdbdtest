/*
* 파 일 명 : StatsDAO.java
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
package egovframework.vaiv.kr.cmmn.stats.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
*  : 통계 데이터 조회에 필요한 데이터를 조작하는 클래스
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
@Repository("StatsDAO")
public class StatsDAO extends EgovComAbstractDAO{
	/**
	 * @method 설명 : 로그인 로그 일별 통계
	 * @param : map(EgovMap)
	 * @return : List of EgovMap
	 * @exception : SQLException
	 * */
	public List<EgovMap> selectLoginLogDayStats(EgovMap map) throws SQLException {
		return selectList("selectLoginLogDayStats", map);
	}
	
	/**
	 * @method 설명 : 로그인 로그 월별 통계
	 * @param : map(EgovMap)
	 * @return : List of EgovMap
	 * @exception : SQLException
	 * */
	public List<EgovMap> selectLoginLogMonthStats(EgovMap map) throws SQLException {
		return selectList("selectLoginLogMonthStats", map);
	}
	
	/**
	 * @method 설명 : 로그인 로그 년별 통계
	 * @param : map(EgovMap)
	 * @return : List of EgovMap
	 * @exception : SQLException
	 * */
	public List<EgovMap> selectLoginLogYearStats(EgovMap map) throws SQLException {
		return selectList("selectLoginLogYearStats", map);
	}

	/**
	 * @method 설명 : 시스템 로그 통계
	 * @param : map(EgovMap)
	 * @return : List of EgovMap
	 * @exception : SQLException
	 * */
	public EgovMap selectSysLogStats(EgovMap map) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @method 설명 : 게시판별 게시물 통계
	 * @param : map(EgovMap)
	 * @return : List of EgovMap
	 * @exception : SQLException
	 * */
	public List<EgovMap> selectBbsToNttCntStats(EgovMap map) throws SQLException {
		return selectList("selectBbsToNttStats", map);
	}
	
	/**
	 * @method 설명 : 게시판별 게시물 통계
	 * @param : map(EgovMap)
	 * @return : List of EgovMap
	 * @exception : SQLException
	 * */
	public List<EgovMap> selectBbsToNttAttrCntStats(EgovMap map) throws SQLException {
		return selectList("selectBbsToNttAttrCntStats", map);
	}

	/**
	 * @method 설명 : 메뉴 사용 통계
	 * @param : map(EgovMap)
	 * @return : List of EgovMap
	 * @exception : SQLException
	 * */
	public List<EgovMap> selectMenuUseStats(EgovMap map) throws SQLException {
		return selectList("selectMenuUseStats", map);
	}
	
	/**
	 * @method 설명 : 사용자별 메뉴 통계
	 * @param : map(EgovMap)
	 * @return : List of EgovMap
	 * @exception : SQLException
	 * */
	public EgovMap selectMemberToMenuStats(EgovMap map) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @method 설명 : 사용자 총 인원 통계
	 * @param : map(EgovMap)
	 * @return : List of EgovMap
	 * @exception : SQLException
	 * */
	public List<EgovMap> selectMberTotCntToView() throws SQLException {
		return selectList("selectMberTotCntToView");
	}
}
