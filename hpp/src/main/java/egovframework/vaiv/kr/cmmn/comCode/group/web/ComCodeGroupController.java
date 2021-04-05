package egovframework.vaiv.kr.cmmn.comCode.group.web;

import java.sql.SQLException;
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
import egovframework.vaiv.kr.cmmn.comCode.group.service.ComCodeGroupVO;
import egovframework.vaiv.kr.cmmn.comCode.group.service.ComCodeGroupService;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;

/**
 * 공통코드 그룹 관리 / 관리자 : 공통코드 그룹 관리에 대한 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/adm/comCode/group/")
public class ComCodeGroupController extends Loggable{
	/* 공통코드그룹 서비스 */
	@Resource(name="ComCodeGroupService")
	private ComCodeGroupService comCodeGroupService;
	/* 전자정부프레임워크 메시지 소스 */
	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;
	/* bean validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * 공통코드 그룹을 관리하는 메인 페이지 (목록표출)
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return comCodeGroupMain
	 */
	@RequestMapping(value="comCodeGroupMain")
	public String comCodeGroupMain(@ModelAttribute("searchVO") ComCodeGroupVO searchVO
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
			
			List<ComCodeGroupVO> comCodeGroupList = comCodeGroupService.selectComCodeGroupList(searchVO); 
	        model.addAttribute("comCodeGroupList", comCodeGroupList);
	
	        int totCnt = comCodeGroupService.selectComCodeGroupListTotCnt(searchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			
	        model.addAttribute("paginationInfo", paginationInfo);
		
		} catch (SQLException e) {
			exLogging("selectComCodeGroupList", e);
			return "egovframework/com/cmm/error/egovError";
		}
		
		return "vaiv/cmmn/adm/comCode/group/comCodeGroupMain.adm";
	}
	
	/**
	 * 공통코드 그룹 등록 페이지로 이동
	 * @param searchVO 검색VO
	 * @param comCodeGroupVO 공통코드 그룹 VO
	 * @param model 화면 모델
	 * @return comCodeGroupRegist
	 */
	@RequestMapping(value="comCodeGroupRegist", method=RequestMethod.GET)
	public String comCodeGroupRegist(@ModelAttribute("searchVO") ComCodeGroupVO searchVO
			, @ModelAttribute("comCodeGroupVO") ComCodeGroupVO comCodeGroupVO
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		return "vaiv/cmmn/adm/comCode/group/comCodeGroupRegist.adm";
	}
	
	/**
	 * 그룹코드 중복 확인 (Ajax)
	 * @param paramMap Request value Map
	 * @param model 화면 모델
	 * @return jsonView
	 * @throws SQLException
	 */
	@RequestMapping(value="comCodeGroupDplctCnfirmAjax", method=RequestMethod.POST)
	public String comCodeGroupDplctCnfirmAjax(@RequestParam Map paramMap,
			ModelMap model) throws SQLException {
		
		String checkGroupCode = (String) paramMap.get("checkGroupCode");
		
		int usedCnt = comCodeGroupService.checkGroupCodeDplct(checkGroupCode);
		
		model.addAttribute("usedCnt", usedCnt);
		model.addAttribute("checkGroupCode", checkGroupCode);

		return "jsonView";
	}
	
	/**
	 * 입력된 정보의 공통코드 그룹 등록
	 * @param searchVO 검색VO
	 * @param insertVO 등록VO
	 * @param model 화면 모델
	 * @param bindingResult BindingResult
	 * @return comCodeGroupMain.do
	 */
	@RequestMapping(value="insertComCodeGroup", method=RequestMethod.POST)
	public String insertComCodeGroup(@ModelAttribute("searchVO") ComCodeGroupVO searchVO
			, @ModelAttribute("comCodeGroupVO") ComCodeGroupVO insertVO
			, ModelMap model
			, BindingResult bindingResult) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(insertVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "vaiv/cmmn/adm/comCode/group/comCodeGroupRegist.adm";
		} else {
			try {
				comCodeGroupService.insertComCodeGroup(insertVO);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			} catch (SQLException e) {
				exLogging("insertComCodeGroup", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));
			}
			
			return "redirect:/cmmn/adm/comCode/group/comCodeGroupMain.do";
		}
	}
	
	/**
	 * 공통코드 그룹 수정 페이지로 이동
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return comCodeGroupModify
	 */
	@RequestMapping(value="comCodeGroupModify")
	public String comCodeGroupModify(@ModelAttribute("searchVO") ComCodeGroupVO searchVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
			
		try {
			ComCodeGroupVO comCodeGroupVO = comCodeGroupService.selectComCodeGroup(searchVO);
			model.addAttribute("comCodeGroupVO", comCodeGroupVO);
		} catch (SQLException e) {
			exLogging("selectComCodeGroup", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));
		}
				
		return "vaiv/cmmn/adm/comCode/group/comCodeGroupModify.adm";
	}
	
	/**
	 * 입력된 정보로 공통코드 그룹 수정
	 * @param searchVO 검색VO
	 * @param updateVO 수정VO
	 * @param model 화면 모델
	 * @param bindingResult BindingResult
	 * @return comCodeGroupMain.do
	 */
	@RequestMapping(value="updateComCodeGroup", method=RequestMethod.POST)
	public String updateComCodeGroup(@ModelAttribute("searchVO") ComCodeGroupVO searchVO
			, @ModelAttribute("comCodeGroupVO") ComCodeGroupVO updateVO
			, ModelMap model
			, BindingResult bindingResult) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(updateVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "vaiv/cmmn/adm/comCode/group/comCodeGroupModify.adm";
		} else {
			try {
				comCodeGroupService.updateComCodeGroup(updateVO);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			} catch (SQLException e) {
				exLogging("updateComCodeGroup", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.update"));
			}
			
			return "redirect:/cmmn/adm/comCode/group/comCodeGroupMain.do";
		}
	}
	
	/**
	 * 입력된 정보로 공통코드 그룹 삭제
	 * @param searchVO 검색VO
	 * @param deleteVO 삭제VO
	 * @param model 화면 모델
	 * @return comCodeGroupMain.do
	 */
	@RequestMapping(value="deleteComCodeGroup", method=RequestMethod.POST)
	public String deleteComCodeGroup(@ModelAttribute("searchVO") ComCodeGroupVO searchVO
			, @ModelAttribute("comCodeGroupVO") ComCodeGroupVO deleteVO
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			comCodeGroupService.deleteComCodeGroup(deleteVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		} catch (SQLException e) {
			exLogging("deleteComCodeGroup", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));
		}
		
		return "redirect:/cmmn/adm/comCode/group/comCodeGroupMain.do";
	}
}







