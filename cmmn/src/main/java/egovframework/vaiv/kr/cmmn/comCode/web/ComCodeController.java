package egovframework.vaiv.kr.cmmn.comCode.web;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.vaiv.kr.cmmn.comCode.group.service.ComCodeGroupService;
import egovframework.vaiv.kr.cmmn.comCode.group.service.ComCodeGroupVO;
import egovframework.vaiv.kr.cmmn.comCode.service.ComCodeService;
import egovframework.vaiv.kr.cmmn.comCode.service.ComCodeVO;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;

/**
 * 공통코드 관리 / 관리자 : 공통코드 관리에 대한 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/adm/comCode/")
public class ComCodeController extends Loggable{
	/* 공통코드 서비스 호출 */
	@Resource(name="ComCodeService")
	private ComCodeService comCodeService;
	
	/* 공통코드 그룹코드 서비스 호출 */
	@Resource(name="ComCodeGroupService")
	private ComCodeGroupService comCodeGroupService;
	
	/* 전자정부 메시지 서비스 호출 */
	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;
	
	/* bean validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * 공통코드를 관리하는 메인 페이지 (목록표출)
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return comCodeMain
	 */
	@RequestMapping(value="comCodeMain")
	public String comCodeMain(@ModelAttribute("searchVO") ComCodeVO searchVO
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
			
			List<ComCodeVO> comCodeList = comCodeService.selectComCodeList(searchVO); 
	        model.addAttribute("comCodeList", comCodeList);
	
	        int totCnt = comCodeService.selectComCodeListTotCnt(searchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			
	        model.addAttribute("paginationInfo", paginationInfo);
		
		} catch (SQLException e) {
			exLogging("comCodeMain", e);
			return "egovframework/com/cmm/error/egovError";
		}
		
		return "vaiv/cmmn/adm/comCode/comCodeMain.adm";
	}
	
	/**
	 * 공통코드 등록 페이지로 이동
	 * @param searchVO 검색VO
	 * @param comCodeVO 공통코드VO
	 * @param model 화면 모델
	 * @return comCodeRegist
	 */
	@RequestMapping(value="comCodeRegist", method=RequestMethod.GET)
	public String comCodeRegist(@ModelAttribute("searchVO") ComCodeVO searchVO
			, @ModelAttribute("comCodeVO") ComCodeVO comCodeVO
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			//그룹코드 목록 가져오기
			ComCodeGroupVO groupCodeVO = new ComCodeGroupVO();
			groupCodeVO.setPagingYn("N");
			groupCodeVO.setSearchCondition("3");
			groupCodeVO.setSearchKeyword("Y");
			List<ComCodeGroupVO> groupCodeList = comCodeGroupService.selectComCodeGroupList(groupCodeVO);
			model.addAttribute("groupCodeList", groupCodeList);
		} catch (SQLException e) {
			exLogging("comCodeRegist", e);
			return "egovframework/com/cmm/error/egovError";
		}
		
		return "vaiv/cmmn/adm/comCode/comCodeRegist.adm";
	}
	
	/**
	 * 공통코드 코드 중복 체크
	 * @param paramMap Request value Map
	 * @param model 화면 모델
	 * @return jsonView
	 * @throws SQLException
	 */
	@RequestMapping(value="comCodeDplctCnfirmAjax", method=RequestMethod.POST)
	public String comCodeDplctCnfirmAjax(@RequestParam Map paramMap,
			ModelMap model) throws SQLException {

		String checkCode = (String) paramMap.get("checkCode");

		int usedCnt = comCodeService.checkCodeDplct(checkCode);
		model.addAttribute("usedCnt", usedCnt);
		model.addAttribute("checkCode", checkCode);

		return "jsonView";
	}
	
	/**
	 * 입력된 정보를 공통코드 저장
	 * @param searchVO 검색VO 
	 * @param insertVO 등록VO
	 * @param model 화면 모델
	 * @param bindingResult BindingResult
	 * @return comCodeMain.do
	 */
	@RequestMapping(value="insertComCode", method=RequestMethod.POST)
	public String insertComCode(@ModelAttribute("searchVO") ComCodeVO searchVO
			, @ModelAttribute("comCodeVO") ComCodeVO insertVO
			, ModelMap model
			, BindingResult bindingResult) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(insertVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "vaiv/cmmn/adm/comCode/comCodeRegist.adm";
		} else {
			try {
				comCodeService.insertComCode(insertVO);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			} catch (SQLException e) {
				exLogging("insertComCode", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));
			}
			
			return "redirect:/cmmn/adm/comCode/comCodeMain.do";
		}
	}
	
	/**
	 * 공통코드 수정 페이지로 이동
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return comCodeModify
	 */
	@RequestMapping(value="comCodeModify")
	public String comCodeModify(@ModelAttribute("searchVO") ComCodeVO searchVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
			
		try {
			//그룹코드 목록 가져오기
			ComCodeGroupVO groupCodeVO = new ComCodeGroupVO();
			groupCodeVO.setPagingYn("N");
			groupCodeVO.setSearchCondition("3");
			groupCodeVO.setSearchKeyword("Y");
			List<ComCodeGroupVO> groupCodeList = comCodeGroupService.selectComCodeGroupList(groupCodeVO);
			model.addAttribute("groupCodeList", groupCodeList);
			
			ComCodeVO comCodeVO = comCodeService.selectComCode(searchVO);
			model.addAttribute("comCodeVO", comCodeVO);
		} catch (SQLException e) {
			exLogging("comCodeModify", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.update"));
		}
				
		return "vaiv/cmmn/adm/comCode/comCodeModify.adm";
	}
	
	/**
	 * 입력된 정보로 공통코드 수정
	 * @param searchVO 검색VO
	 * @param updateVO 수정VO
	 * @param model 화면 모델
	 * @param bindingResult BindingResult
	 * @return comCodeMain.do
	 */
	@RequestMapping(value="updateComCode", method=RequestMethod.POST)
	public String updateComCode(@ModelAttribute("searchVO") ComCodeVO searchVO
			, @ModelAttribute("comCodeVO") ComCodeVO updateVO
			, ModelMap model
			, BindingResult bindingResult) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(updateVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "/vaiv/cmmn/adm/comCode/comCodeModify.adm";
		} else {
			try {
				comCodeService.updateComCode(updateVO);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			} catch (SQLException e) {
				exLogging("updateComCode", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.update"));
			}
			
			return "redirect:/cmmn/adm/comCode/comCodeMain.do";
		}
	}
	
	/**
	 * 입력된 정보의 공통코드 삭제
	 * @param searchVO 검색VO
	 * @param deleteVO 삭제VO
	 * @param model 화면 모델
	 * @return comCodeMain.do
	 */
	@RequestMapping(value="deleteComCode", method=RequestMethod.POST)
	public String deleteComCode(@ModelAttribute("searchVO") ComCodeVO searchVO
			, @ModelAttribute("comCodeVO") ComCodeVO deleteVO
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			comCodeService.deleteComCode(deleteVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		} catch (SQLException e) {
			exLogging("deleteComCode", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));
		}
		
		return "redirect:/cmmn/adm/comCode/comCodeMain.do";
	}
}







