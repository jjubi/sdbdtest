/*
* 파 일 명 : adm > StatsController.java
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
package egovframework.vaiv.kr.cmmn.stats.web;

import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.stats.service.StatsService;

/**
*  : 관리자 기능 - 통계 기능 controller
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
@Controller
@RequestMapping("/cmmn/adm/stats/")
public class StatsController extends Loggable{
	/* 통계 서비스 호출 */
	@Resource(name="StatsService")
	private StatsService statsService;
	
	/**
	 * @method 설명 : 통계 데이터를 조회 (Ajax)
	 * @param : request(검색VO), model(model), statsType(통계유형)
	 * @return : String(jsonView)
	 * @exception : SQLException
	 * */
	@RequestMapping(value="select{statsType}DataAjax", method=RequestMethod.POST)
	public String selectStatsDataAjax(HttpServletRequest request
			, @PathVariable("statsType") String statsType
			, ModelMap model) {
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			return "jsonView";
		}
		
		try {
			EgovMap resultStatsData = new EgovMap();
			Map<String, String[]> maps = request.getParameterMap();
			EgovMap map = new EgovMap();
			for(String key : maps.keySet()) {
				map.put(key, maps.get(key)[0]);
			}
			
			if("LoginLog".equals(statsType)) {
				resultStatsData = statsService.selectLoginLogStats(map);
			} else if("BbsToNtt".equals(statsType)) {
				resultStatsData = statsService.selectBbsToNttStats(map);
			} else if("Menu".equals(statsType)) {
				resultStatsData = statsService.selectMenuUseStats(map);
			}
			
			model.addAttribute("statsData", resultStatsData);
			model.addAttribute("result", "success");
		} catch (SQLException e) {
			exLogging("selectStatsDataAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
	
	
	
}
