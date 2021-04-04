/*
* 파 일 명 : adm > QestnarSenarioController.java
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
package egovframework.vaiv.kr.cmmn.qestnar.web;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.qestnar.common.SearchVO;
import egovframework.vaiv.kr.cmmn.qestnar.qestn.service.QestnService;
import egovframework.vaiv.kr.cmmn.qestnar.qestn.service.QestnVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarSenarioService;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarSenarioVO;

/**
*  : 관리자 기능 - 설문 시나리오 관리
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
@Controller
@RequestMapping("/cmmn/adm/qestnar/senario/")
public class QestnarSenarioController extends Loggable {
	/* 설문 문항 서비스 호출 */
	@Resource(name="QestnService")
	private QestnService qestnService;
	
	/* 설문 문항 서비스 호출 */
	@Resource(name="QestnarSenarioService")
	private QestnarSenarioService qestnarSenarioService;
	
	/* 전자정부 메시지 서비스 호출 */
	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;
	
	/**
	 * @method 설명 : 설문조사 시나리오 목록 조회
	 * @param : request, model(model)
	 * @return : String(View)
	 * @exception : SQLException
	 * */
	@RequestMapping(value="selectQestnarSenarioListAjax", method=RequestMethod.POST)
	public String selectQestnarSenarioAjax(HttpServletRequest request
			, ModelMap model) {
		
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			return "jsonView";
		}
		
		try {
			String qestnarSeqNo = request.getParameter("qestnarSeqNo");
			SearchVO searchVO = new SearchVO();
			searchVO.setQestnarSeqNo(qestnarSeqNo);
			List<QestnVO> qestnList = qestnService.selectQestnList(searchVO);
			model.addAttribute("qestnList", qestnList);
			
			List<QestnarSenarioVO> senarioList = qestnarSenarioService.selectQestnarSenarioList(searchVO);
			model.addAttribute("senarioList", senarioList);
			
			model.addAttribute("result", "success");
		} catch (SQLException e) {
			exLogging("selectQestnarSenarioListAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
	
	/**
	 * 설문조사 시나리오 저장
	 * */
	@RequestMapping(value="insertQestnarSenarioAjax", method=RequestMethod.POST)
	public String insertQestnarSenarioAjax(HttpServletRequest request
			, ModelMap model) {

		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			return "jsonView";
		}
		
		try {
			String senarioJsonData = request.getParameter("senarioJsonData");
			
			//시나리오 저장
			qestnarSenarioService.insertQestnarSenario(senarioJsonData);
			
			model.addAttribute("result", "success");
		} catch (SQLException e) {
			exLogging("insertQestnarSenarioAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
}
