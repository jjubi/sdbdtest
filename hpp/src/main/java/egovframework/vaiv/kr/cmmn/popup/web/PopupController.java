package egovframework.vaiv.kr.cmmn.popup.web;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.vaiv.kr.cmmn.comCode.service.ComCodeService;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.popup.service.PopupService;
import egovframework.vaiv.kr.cmmn.popup.service.PopupVO;

/**
 * 팝업 관리 / 관리자 : 팝업 관리에 대한 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/adm/popup/")
public class PopupController extends Loggable{
	/* 팝업 서비스 선언 */
	@Resource(name="PopupService")
	private PopupService popupService;
	
	/* 공통코드 서비스 선언 */
	@Resource(name="ComCodeService")
	private ComCodeService comCodeService;
	
	/* 전자정부 메시지 서비스 호출 */
	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;
	
	/* bean validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * 팝업관리를 관리하는 메인 페이지 (목록 표출)
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return popupMain
	 */
	@RequestMapping(value="popupMain")
	public String popupMain(@ModelAttribute("searchVO") PopupVO searchVO
			,ModelMap model) {
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
			
	        //정렬은 순서 컬럼으로 페이징 없이 모든 데이터
			searchVO.setOrdrCondition("ordr");
			searchVO.setPagingYn("N");
			List<PopupVO> popupUseYList = popupService.selectPopupList(searchVO); 
	        model.addAttribute("popupList", popupUseYList);
	        
	        int totCnt = popupService.selectPopupListTotCnt(searchVO);
			paginationInfo.setTotalRecordCount(totCnt);
	        model.addAttribute("paginationInfo", paginationInfo);
	        
	        //팝업 유형 공통코드에서 가져가기
	        List<?> popupTyList = comCodeService.selectComCodeList("POPUP_TY");
	        model.addAttribute("popupTyList", popupTyList);
	        
		} catch (SQLException e) {
			exLogging("popupMain", e);
			return "egovframework/com/cmm/error/egovError";
		}
		
		
		return "vaiv/cmmn/adm/popup/popupMain.adm";
	}
	
	/**
	 * 팝업관리 등록 페이지로 이동
	 * @param searchVO 검색VO
	 * @param popupVO 팝업VO
	 * @param model 화면 모델
	 * @return popupRegist
	 * @throws SQLException 
	 */
	@RequestMapping(value="popupRegist", method=RequestMethod.GET)
	public String popupRegist(@ModelAttribute("searchVO") PopupVO searchVO
			, @ModelAttribute("popupVO") PopupVO popupVO
			, ModelMap model) throws SQLException {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		//팝업 유형 공통코드에서 가져가기
        List<?> popupTyList = comCodeService.selectComCodeList("POPUP_TY");
        model.addAttribute("popupTyList", popupTyList);
        
		return "vaiv/cmmn/adm/popup/popupRegist.adm";
	}
	
	/**
	 * 입력된 정보의 팝업 등록
	 * @param searchVO 검색VO
	 * @param insertVO 등록VO
	 * @param multiReq Multipart Request
	 * @param bindingResult BindingResult
	 * @param model 화면 모델
	 * @return popupMain.do
	 */
	@RequestMapping(value="insertPopup", method=RequestMethod.POST)
	public String insertPopup(@ModelAttribute("searchVO") PopupVO searchVO
			, @ModelAttribute("popupVO") PopupVO insertVO
			, MultipartHttpServletRequest multiReq
			, BindingResult bindingResult
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(insertVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "vaiv/cmmn/adm/popup/popupRegist.adm";
		} else {
			
			try {
				popupService.insertPopup(insertVO, multiReq);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			} catch (SQLException e) {
				exLogging("insertPopup", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));
			}
			
			return "redirect:/cmmn/adm/popup/popupMain.do";
		}
	}
	
	/**
	 * 팝업 수정 페이지로 이동
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return popupModify
	 */
	@RequestMapping(value="popupModify")
	public String popupModify(@ModelAttribute("searchVO") PopupVO searchVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			PopupVO selectVO = popupService.selectPopup(searchVO);
			model.addAttribute("popupVO", selectVO);
			
			//팝업 유형 공통코드에서 가져가기
	        List<?> popupTyList = comCodeService.selectComCodeList("POPUP_TY");
	        model.addAttribute("popupTyList", popupTyList);
		} catch (SQLException e) {
			exLogging("selectPopup", e);
		}
		
		return "vaiv/cmmn/adm/popup/popupModify.adm";
	}
	
	/**
	 * 입력된 정보로 팝업 수정
	 * @param searchVO 검색VO
	 * @param updateVO 수정VO
	 * @param multiReq Multipart Request
	 * @param bindingResult BindingResult
	 * @param model 화면 모델
	 * @return popupMain.do
	 */
	@RequestMapping(value="updatePopup", method=RequestMethod.POST)
	public String updatePopup(@ModelAttribute("searchVO") PopupVO searchVO
			, @ModelAttribute("popupVO") PopupVO updateVO
			, MultipartHttpServletRequest multiReq
			, BindingResult bindingResult
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(updateVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "vaiv/cmmn/adm/popup/popupModify.adm";
		} else {
			
			try {
				popupService.updatePopup(updateVO, multiReq);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			} catch (SQLException e) {
				exLogging("updatePopup", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.update"));
			}
			
			return "redirect:/cmmn/adm/popup/popupMain.do";
		}
	}
	
	/**
	 * 입력된 정보로 팝업 삭제
	 * @param searchVO 검색VO
	 * @param deleteVO 삭제VO
	 * @param model 화면 모델
	 * @return popupMain.do
	 */
	@RequestMapping(value="deletePopup", method=RequestMethod.POST)
	public String deletePopup(@ModelAttribute("searchVO") PopupVO searchVO
			, @ModelAttribute("popupVO") PopupVO deleteVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			popupService.deletePopup(deleteVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		} catch (SQLException e) {
			exLogging("updatePopup", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));
		}
		
		return "redirect:/cmmn/adm/popup/popupMain.do";
	}
	
	/**
	 * 팝업 순서 수정 (Ajax)
	 * @param updateVO 수정VO
	 * @param updateType 수정유형
	 * @param model 화면 모델
	 * @return jsonView
	 */
	@RequestMapping(value="updatePopupAjax", method=RequestMethod.POST)
	public String updatePopupAjax(PopupVO updateVO
			, String updateType
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			return "jsonView";
		}
				
		try{
			int checkCnt = 0;
			if("ordr".equals(updateType)) {
				checkCnt = popupService.updatePopupOrdr(updateVO);
			} else if("useAt".equals(updateType)) {
				checkCnt = popupService.updatePopupUseAt(updateVO);
			}
			if(checkCnt > 0) {
				model.addAttribute("result", "success");
			} else {
				model.addAttribute("result", "fail");
			}
		} catch(SQLException e){
			exLogging("updatePopupAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
}
