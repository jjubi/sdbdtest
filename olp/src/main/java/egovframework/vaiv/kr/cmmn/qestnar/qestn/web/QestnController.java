package egovframework.vaiv.kr.cmmn.qestnar.qestn.web;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.qestnar.common.SearchVO;
import egovframework.vaiv.kr.cmmn.qestnar.qestn.service.QestnService;
import egovframework.vaiv.kr.cmmn.qestnar.qestn.service.QestnVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarService;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarVO;

/**
 * 설문문항 관리 / 관리자 : 설문문항 관리에 대한 요청을 처리하는 Controller
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
@Controller
@RequestMapping("/cmmn/adm/qestnar/qestn/")
public class QestnController extends Loggable{
	/* 설문 조사 서비스 호출 */
	@Resource(name="QestnarService")
	private QestnarService qestnarService;
	
	/* 설문 문항 서비스 호출 */
	@Resource(name="QestnService")
	private QestnService qestnService;
	
	/* 전자정부 메시지 서비스 호출 */
	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;
	
	/**
	 * 설문조사 문항 관라 메인 페이지로 이동
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return qestnMain
	 */
	@RequestMapping(value="qestnMain")
	public String qestnMain(@ModelAttribute("searchVO") SearchVO searchVO
			, ModelMap model) {
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			//설문 정보 가져가기
			QestnarVO qestnarInfo = qestnarService.selectQestnar(searchVO);
			model.addAttribute("qestnarInfo", qestnarInfo);
			
		} catch (SQLException e) {
			exLogging("qestnMain", e);
		}
		
		return "vaiv/cmmn/adm/qestnar/qestn/qestnMain.adm";
	}
	
	/**
	 * 설문조사 문항 저장 (Ajax)
	 * @param request HttpServletRequest
	 * @param model 화면 모델
	 * @return jsonView
	 */
	@RequestMapping(value="insertQestnAjax", method=RequestMethod.POST)
	public String insertQestnAjax(HttpServletRequest request
			, ModelMap model) {
		
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			return "jsonView";
		}
		
		try {
			String qestnData = request.getParameter("qestnData");
			String qestnarSeqNo = request.getParameter("qestnarSeqNo");
			
			QestnVO qestnVO = new QestnVO();
			qestnVO.setQestnarSeqNo(qestnarSeqNo);
			
			//설문 문항 삭제
			qestnService.deleteQestn(qestnVO);
			
			//설문 문항 저장
			qestnService.insertQestn(qestnVO, qestnData);
			
			model.addAttribute("result", "success");
		} catch (SQLException e) {
			exLogging("insertQestnAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
	
	/**
	 * 설문조사 문항 목록 가져오기 (Ajax)
	 * @param request HttpServletRequest
	 * @param model 화면 모델
	 * @return jsonView
	 */
	@RequestMapping(value="selectQestnListAjax", method=RequestMethod.POST)
	public String selectQestnListAjax(HttpServletRequest request
			, ModelMap model) {
		
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			return "jsonView";
		}
		
		try {
			String qestnarSeqNo = request.getParameter("qestnarSeqNo");
			SearchVO searchVO = new SearchVO();
			searchVO.setQestnarSeqNo(qestnarSeqNo);
			
			//설문 문항 정보 가져가기
			List<QestnVO> qestnDataList = qestnService.selectQestnList(searchVO);
			model.addAttribute("qestnDataList", qestnDataList);
			
			model.addAttribute("result", "success");
		} catch (SQLException e) {
			exLogging("insertQestnAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
	
}
