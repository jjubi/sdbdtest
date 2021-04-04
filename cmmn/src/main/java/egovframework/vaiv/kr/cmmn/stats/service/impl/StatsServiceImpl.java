/*
* 파 일 명 : StatsServiceImpl.java
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

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.vaiv.kr.cmmn.common.util.CommonUtil;
import egovframework.vaiv.kr.cmmn.stats.service.StatsService;

/**
*  : 통계 데이터 조회에 대한 기능 구현
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
@Service("StatsService")
public class StatsServiceImpl implements StatsService{
	/* 통계 DAO 선언 */
	@Resource(name="StatsDAO")
	private StatsDAO statsDAO;

	/**
	 * @method 설명 : 로그인 로그 통계
	 * @param : map(EgovMap)
	 * @return : EgovMap
	 * @exception : SQLException
	 * */
	@Override
	public EgovMap selectLoginLogStats(EgovMap map) throws SQLException {
		EgovMap resultMap = new EgovMap();
		resultMap.put("result", "success");
		if("day".equals(map.get("type"))) {
			//일별 통계 (default : 최근 1달)
			//날짜 있을 선택 시 날짜 확인 (최대 1년)
			if(CommonUtil.periodDateCheck(map, 365)) {
				resultMap.put("resultData", statsDAO.selectLoginLogDayStats(map));
			} else {
				resultMap.put("result", "날짜 체크 필요. (최대 1년)");
			}
		} else if("month".equals(map.get("type"))) {
			//월별 통계 (default : 최근 1년)
			//날짜 있을 선택 시 날짜 확인 (최대 5년)
			if(CommonUtil.periodDateCheck(map, (365*5))) {
				resultMap.put("resultData", statsDAO.selectLoginLogMonthStats(map));
			} else {
				resultMap.put("result", "날짜 체크 필요. (최대 5년)");
			}
		} else if("year".equals(map.get("type"))) {
			//년별 통계 (default : 최근 10년)
			//날짜 있을 선택 시 날짜 확인 
			if(CommonUtil.periodDateCheck(map, 0)) {
				resultMap.put("resultData", statsDAO.selectLoginLogYearStats(map));
			} else {
				resultMap.put("result", "날짜 체크 필요.");
			}
		} else {
			resultMap.put("result", "Non Type");
		}
				
		return resultMap;
	}

	/**
	 * @method 설명 : 시스템 로그 통계
	 * @param : map(EgovMap)
	 * @return : EgovMap
	 * @exception : SQLException
	 * */
	@Override
	public EgovMap selectSysLogStats(EgovMap map) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @method 설명 : 게시판별 게시물 통계
	 * @param : map(EgovMap)
	 * @return : EgovMap
	 * @exception : SQLException
	 * */
	@Override
	public EgovMap selectBbsToNttStats(EgovMap map) throws SQLException {
		EgovMap resultMap = new EgovMap();
		resultMap.put("result", "success");
		if("cnt".equals(map.get("type"))) {
			//날짜 있을 선택 시 날짜 확인 (default : 최근 1달)
			if(CommonUtil.periodDateCheck(map, 0)) {
				resultMap.put("resultData", statsDAO.selectBbsToNttCntStats(map));
			} else {
				resultMap.put("result", "날짜 체크 필요.");
			}
		} else if("attrCnt".equals(map.get("type"))) {
			//날짜 있을 선택 시 날짜 확인 (default : 최근 1달)
			if(CommonUtil.periodDateCheck(map, 0)) {
				resultMap.put("resultData", statsDAO.selectBbsToNttAttrCntStats(map));
			} else {
				resultMap.put("result", "날짜 체크 필요.");
			}
		} else {
			resultMap.put("result", "Non Type");
		}
		
		return resultMap;
	}

	/**
	 * @method 설명 : 메뉴 사용 통계
	 * @param : map(EgovMap)
	 * @return : EgovMap
	 * @exception : SQLException
	 * */
	@Override
	public EgovMap selectMenuUseStats(EgovMap map) throws SQLException {
		EgovMap resultMap = new EgovMap();
		resultMap.put("result", "success");
		//날짜 있을 선택 시 날짜 확인 (default : 최근 1달)
		if(CommonUtil.periodDateCheck(map, 0)) {
			resultMap.put("resultData", statsDAO.selectMenuUseStats(map));
		} else {
			resultMap.put("result", "날짜 체크 필요.");
		}
		
		return resultMap;
	}

	/**
	 * @method 설명 : 회원별 메뉴 사용 통계
	 * @param : map(EgovMap)
	 * @return : EgovMap
	 * @exception : SQLException
	 * */
	@Override
	public EgovMap selectMemberToMenuStats(EgovMap map) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @method 설명 : 총 회원 수 통계
	 * @param : map(EgovMap)
	 * @return : EgovMap
	 * @exception : SQLException
	 * */
	@Override
	public List<EgovMap> selectMberTotCntToView() throws SQLException {
		return statsDAO.selectMberTotCntToView();
	}
}
