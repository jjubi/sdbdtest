package egovframework.vaiv.kr.cmmn.bbs.ty.web;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;
import egovframework.vaiv.kr.cmmn.bbs.ty.service.BbsTyService;
import egovframework.vaiv.kr.cmmn.bbs.ty.service.BbsTyVO;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;

/**
 * 게시판 유형 관리 / 관리자 : 게시판 유형 관리에 대한 요청을 처리하는 Controller
 * @category 공통
 * @author jo
 * @since 2020-12-31
 * @version : v1.0
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
@RequestMapping("/cmmn/adm/bbs/ty/")
public class BbsTyController extends Loggable {
	
	/* 게시판 유형 서비스 선언 */
	@Resource(name="BbsTyService")
	private BbsTyService bbsTyService;
	
	/* 메시지 서비스 선언 */
	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;
	
	/* bean Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * 게시판 유형을 관리하는 메인 페이지 (목록표출)
	 * @param searchVO 검색VO
	 * @param model 화면모델
	 * @return bbsTyMain
	 */
	@RequestMapping(value="bbsTyMain")
	public String bbsTyMain(@ModelAttribute("searchVO") SearchVO searchVO
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			//paging 
	    	PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
			paginationInfo.setPageSize(searchVO.getPageSize());
	
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			List<BbsTyVO> bbsTyList = bbsTyService.selectBbsTyList(searchVO); 
	        model.addAttribute("bbsTyList", bbsTyList);
	
	        int totCnt = bbsTyService.selectBbsTyListTotCnt(searchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			
	        model.addAttribute("paginationInfo", paginationInfo);
		
		} catch (SQLException e) {
			exLogging("bbsTyMain", e);
			return "egovframework/com/cmm/error/egovError";
		}
		
		return "vaiv/cmmn/adm/bbs/ty/bbsTyMain.adm";
	}
	
	/**
	 * 게시판 유형을 등록하는 페이지
	 * @param searchVO 검색VO
	 * @param BbsTyVO 게시판유형VO
	 * @param model 화면모델
	 * @return bbsTyRegist
	 */
	@RequestMapping(value="bbsTyRegist", method=RequestMethod.GET)
	public String bbsTyRegist(@ModelAttribute("searchVO") SearchVO searchVO
			, @ModelAttribute("bbsTyVO") BbsTyVO BbsTyVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
				
		return "vaiv/cmmn/adm/bbs/ty/bbsTyRegist.adm";
	}
	
	/**
	 * 입력된 정보로 게시판 유형 등록
	 * @param searchVO 검색VO
	 * @param insertVO 등록VO
	 * @param bindingResult BindingResult
	 * @param model 화면모델
	 * @return bbsTyMain.do
	 */
	@RequestMapping(value="insertBbsTy", method=RequestMethod.POST)
	public String insertBbsTy(@ModelAttribute("searchVO") SearchVO searchVO
			, @ModelAttribute("bbsTyVO") BbsTyVO insertVO
			, BindingResult bindingResult
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(insertVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "vaiv/cmmn/adm/bbs/ty/bbsTyRegist.adm";
		} else {
			
			try {
				bbsTyService.insertBbsTy(insertVO);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			} catch (SQLException e) {
				exLogging("insertBbsTy", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));
			}
			
			return "redirect:/cmmn/adm/bbs/ty/bbsTyMain.do";
		}
	}
	
	/**
	 * 게시판 유형 수정 페이지로 이동
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return bbsTyModify
	 */
	@RequestMapping(value="bbsTyModify")
	public String bbsTyModify(@ModelAttribute("searchVO") SearchVO searchVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
			
		try {
			BbsTyVO BbsTyVO = bbsTyService.selectBbsTy(searchVO);
			model.addAttribute("bbsTyVO", BbsTyVO);
		} catch (SQLException e) {
			exLogging("bbsTyModify", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.update"));
		}
		
		return "vaiv/cmmn/adm/bbs/ty/bbsTyModify.adm";
	}
	
	/**
	 * 입력된 정보로 게시판 유형 수정
	 * @param searchVO 검색VO
	 * @param updateVO 수정VO
	 * @param bindingResult BindingResult
	 * @param model 화면 모델
	 * @return bbsTyMain.do
	 */
	@RequestMapping(value="updateBbsTy", method=RequestMethod.POST)
	public String updateBbsTy(@ModelAttribute("searchVO") SearchVO searchVO
			, @ModelAttribute("bbsTyVO") BbsTyVO updateVO
			, BindingResult bindingResult
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(updateVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "vaiv/cmmn/adm/bbs/ty/bbsTyModify.adm";
		} else {
			try {
				bbsTyService.updateBbsTy(updateVO);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			} catch (SQLException e) {
				exLogging("updateBbsTy", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.update"));
			}
		}
		
		return "redirect:/cmmn/adm/bbs/ty/bbsTyMain.do";
	}
	
	/**
	 * 입력된 정보의 게시판 유형 삭제
	 * @param searchVO 검색VO
	 * @param deleteVO 삭제VO
	 * @param model 화면 모델
	 * @return bbsTyMain.do
	 */
	@RequestMapping(value="deleteBbsTy", method=RequestMethod.POST)
	public String deleteBbsTy(@ModelAttribute("searchVO") SearchVO searchVO
			, @ModelAttribute("bbsTyVO") BbsTyVO deleteVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			bbsTyService.deleteBbsTy(deleteVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		} catch (SQLException e) {
			exLogging("deleteBbsTy", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));
		}
		
		return "redirect:/cmmn/adm/bbs/ty/bbsTyMain.do";
	}
	
	/**
	 * 게시판 유형 코드 중복 체크 (Ajax)
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return jsonView
	 */
	@RequestMapping(value="checkBbsTyCodeAjax", method=RequestMethod.POST)
	public String checkBbsTyCodeAjax(SearchVO searchVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			return "jsonView";
		}
				
		try{
			int checkCnt = bbsTyService.checkBbsTyCode(searchVO);
			if(checkCnt > 0) {
				model.addAttribute("result", "fail");
			} else {
				model.addAttribute("result", "success");
			}
		} catch(SQLException e){
			exLogging("checkBbstCdAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
	
}
