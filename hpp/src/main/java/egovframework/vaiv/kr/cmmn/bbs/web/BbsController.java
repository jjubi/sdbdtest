package egovframework.vaiv.kr.cmmn.bbs.web;

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
import egovframework.vaiv.kr.cmmn.bbs.service.BbsService;
import egovframework.vaiv.kr.cmmn.bbs.service.BbsVO;
import egovframework.vaiv.kr.cmmn.bbs.ty.service.BbsTyService;
import egovframework.vaiv.kr.cmmn.bbs.ty.service.BbsTyVO;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;

/**
 * 게시판 관리 / 관리자 : 게시판 관리에 대한 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/adm/bbs/")
public class BbsController extends Loggable{
	/* 게시판 서비스 선언 */
	@Resource(name="BbsService")
	private BbsService bbsService;
	
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
	 * 게시판을 관리하는 메인 페이지 (목록표출)
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return bbsMain
	 */
	@RequestMapping(value="bbsMain")
	public String bbsMain(@ModelAttribute("searchVO") SearchVO searchVO
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
			
			List<BbsVO> bbsList = bbsService.selectBbsList(searchVO); 
	        model.addAttribute("bbsList", bbsList);
	
	        int totCnt = bbsService.selectBbsListTotCnt(searchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			
	        model.addAttribute("paginationInfo", paginationInfo);
		
		} catch (SQLException e) {
			exLogging("bbsMain", e);
			return "egovframework/com/cmm/error/egovError";
		}
		
		return "vaiv/cmmn/adm/bbs/bbsMain.adm";
	}
	
	/**
	 * 게시판을 등록하는 페이지
	 * @param searchVO 검색VO
	 * @param bbsVO 게시판 VO
	 * @param model 화면 모델
	 * @return bbsRegist
	 */
	@RequestMapping(value="bbsRegist", method=RequestMethod.GET)
	public String bbsRegist(@ModelAttribute("searchVO") SearchVO searchVO
			, @ModelAttribute("bbsVO") BbsVO bbsVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			//게시판 유형 목록 가져오기
			SearchVO bbstListSearchVO = new SearchVO();
			bbstListSearchVO.setPagingYn("N");
			bbstListSearchVO.setSearchCondition("4");
			bbstListSearchVO.setSearchKeyword("Y");
			List<BbsTyVO> bbsTList = bbsTyService.selectBbsTyList(bbstListSearchVO);
			model.addAttribute("bbsTyList", bbsTList);
		} catch (SQLException e) {
			exLogging("bbsRegist", e);
		} 
		
		return "vaiv/cmmn/adm/bbs/bbsRegist.adm";
	}
	
	/**
	 * 입력한 게시판 정보를 등록
	 * @param searchVO 검색VO
	 * @param insertVO 등록VO
	 * @param bindingResult BindingResult
	 * @param model 화면 모델
	 * @return bbsMain.do
	 */
	@RequestMapping(value="insertBbs", method=RequestMethod.POST)
	public String insertBbs(@ModelAttribute("searchVO") SearchVO searchVO
			, @ModelAttribute("bbsVO") BbsVO insertVO
			, BindingResult bindingResult
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(insertVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "vaiv/cmmn/adm/bbs/bbsRegist.adm";
		} else {
			
			try {
				bbsService.insertBbs(insertVO);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			} catch (SQLException e) {
				exLogging("insertBbs", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));
			}
			
			return "redirect:/cmmn/adm/bbs/bbsMain.do";
		}
	}
	
	/**
	 * 게시판 정보를 수정하는 페이지로 이동
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return bbsModify
	 */
	@RequestMapping(value="bbsModify")
	public String bbsModify(@ModelAttribute("searchVO") SearchVO searchVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		try {
			//게시판 유형 목록 가져오기
			SearchVO bbstListSearchVO = new SearchVO();
			bbstListSearchVO.setPagingYn("N");
			bbstListSearchVO.setSearchCondition("4");
			bbstListSearchVO.setSearchKeyword("Y");
			List<BbsTyVO> bbsTList = bbsTyService.selectBbsTyList(bbstListSearchVO);
			model.addAttribute("bbsTyList", bbsTList);
			
			//게시판 상세
			BbsVO bbsVO = bbsService.selectBbs(searchVO);
			model.addAttribute("bbsVO", bbsVO);
			
		} catch (SQLException e) {
			exLogging("bbsModify", e);
		} 
		
		return "vaiv/cmmn/adm/bbs/bbsModify.adm";
	}
	
	/**
	 * 입력된 정보로 게시판 정보를 수정
	 * @param searchVO 검색VO
	 * @param updateVO 수정VO
	 * @param bindingResult BindingResult
	 * @param model 화면 모델
	 * @return bbsMain.do
	 */
	@RequestMapping(value="updateBbs", method=RequestMethod.POST)
	public String updateBbs(@ModelAttribute("searchVO") SearchVO searchVO
			, @ModelAttribute("bbsVO") BbsVO updateVO
			, BindingResult bindingResult
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(updateVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "vaiv/cmmn/adm/bbs/bbsModify.adm";
		} else {
			
			try {
				bbsService.updateBbs(updateVO);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			} catch (SQLException e) {
				exLogging("updateBbs", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.update"));
			}
			
			return "redirect:/cmmn/adm/bbs/bbsMain.do";
		}
	}
	
	/**
	 * 게시판을 삭제
	 * @param searchVO 검색VO
	 * @param deleteVO 삭제VO
	 * @param model 화면 모델
	 * @return bbsMain.do
	 */
	@RequestMapping(value="deleteBbs", method=RequestMethod.POST)
	public String deleteBbs(@ModelAttribute("searchVO") SearchVO searchVO
			, @ModelAttribute("bbsVO") BbsVO deleteVO
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			bbsService.deleteBbs(deleteVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		} catch (SQLException e) {
			exLogging("deleteBbs", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));
		}
		
		return "redirect:/cmmn/adm/bbs/bbsMain.do";
	}
	
	
}
