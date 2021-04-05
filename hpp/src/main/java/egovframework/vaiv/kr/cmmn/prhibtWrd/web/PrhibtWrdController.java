package egovframework.vaiv.kr.cmmn.prhibtWrd.web;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import egovframework.vaiv.kr.cmmn.prhibtWrd.service.PrhibtWrdService;
import egovframework.vaiv.kr.cmmn.prhibtWrd.service.PrhibtWrdVO;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;

/**
 * 금지단어 관리 / 관리자 : 금지단어 관리에 대한 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/adm/prhibtWrd/")
public class PrhibtWrdController extends Loggable{
	
	/* 금지 단어 서비스 선언 */
	@Resource(name="PrhibtWrdService")
	private PrhibtWrdService prhibtWrdService;
	
	/* 전자정부 메시지 서비스 호출 */
	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;
	
	/* bean validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * 금지단어를 관리하는 메인 페이지 (목록표출)
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return prhibtWrdProcMain
	 */
	@RequestMapping(value="prhibtWrdProcMain")
	public String prhibtWrdProcMain(@ModelAttribute("searchVO") PrhibtWrdVO searchVO
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
			
			List<PrhibtWrdVO> prhibtWrdList = prhibtWrdService.selectPrhibtWrdList(searchVO); 
	        model.addAttribute("prhibtWrdList", prhibtWrdList);
	
	        int totCnt = prhibtWrdService.selectPrhibtWrdListTotCnt(searchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			
	        model.addAttribute("paginationInfo", paginationInfo);
		} catch (SQLException e) {
			exLogging("prhibtWrdProcMain", e);
			return "egovframework/com/cmm/error/egovError";
		}
		
		return "vaiv/cmmn/adm/prhibtWrd/prhibtWrdProcMain.adm";
	}
	
	/**
	 * 입력된 정보로 금지단어 저장 (Ajax)
	 * @param insertVO 등록VO
	 * @param model 화면 모델
	 * @param bindingResult BindingResult
	 * @return jsonView
	 */
	@RequestMapping(value="insertPrhibtWrdAjax", method=RequestMethod.POST)
	public String insertPrhibtWrdAjax(@ModelAttribute("prhibtWrdVO") PrhibtWrdVO insertVO
			, ModelMap model
			, BindingResult bindingResult) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			return "jsonView";
		}
		
		try{
			beanValidator.validate(insertVO, bindingResult); //validation 수행
			
			if(bindingResult.hasErrors()) {
				model.addAttribute("message", "validation Error");
				model.addAttribute("result", "fail");
			} else {
				if(prhibtWrdService.checkDupPrhibtWrd(insertVO)) {
					model.addAttribute("result", "dup");
				} else {
					int prhibtWrdSeqNo = prhibtWrdService.insertPrhibtWrd(insertVO);
					insertVO.setPrhibtWrdSeqNo(String.valueOf(prhibtWrdSeqNo));
					
					PrhibtWrdVO selectVO = prhibtWrdService.selectPrhibtWrd(insertVO);
					model.addAttribute("prhibtWrdVO", selectVO);
					model.addAttribute("result", "success");
				}
			}
		} catch(SQLException e){
			exLogging("insertPrhibtWrdAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
	
	/**
	 * 입력된 정보로 금지단어 수정 (Ajax)
	 * @param updateVO 수정VO
	 * @param model 화면 모델
	 * @param bindingResult BindingResult
	 * @return jsonView
	 */
	@RequestMapping(value="updatePrhibtWrdAjax", method=RequestMethod.POST)
	public String updatePrhibtWrdAjax(@ModelAttribute("prhibtWrdVO") PrhibtWrdVO updateVO
			, ModelMap model
			, BindingResult bindingResult) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			return "jsonView";
		}
		
		try{

			if(bindingResult.hasErrors()) {
				model.addAttribute("message", "validation Error");
				model.addAttribute("result", "fail");
			} else {
				if(prhibtWrdService.checkDupPrhibtWrd(updateVO)) {
					model.addAttribute("result", "dup");
					return "jsonView";
				}
				
				int checkCnt = prhibtWrdService.updatePrhibtWrd(updateVO);
				
				if(checkCnt > 0) {
					PrhibtWrdVO selectVO = prhibtWrdService.selectPrhibtWrd(updateVO);
					model.addAttribute("prhibtWrdVO", selectVO);
					model.addAttribute("result", "success");
				} else {
					model.addAttribute("result", "fail");
				}
			}
			
		} catch(SQLException e){
			exLogging("updatePrhibtWrdAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
	
	/**
	 * 입력된 정보로 금지단어를 삭제 (Ajax)
	 * @param deleteVO 삭제VO
	 * @param model 화면 모델
	 * @return jsonView
	 */
	@RequestMapping(value="deletePrhibtWrdAjax", method=RequestMethod.POST)
	public String deletePrhibtWrdAjax(@ModelAttribute("prhibtWrdVO") PrhibtWrdVO deleteVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			return "jsonView";
		}
		
		try{
			int checkCnt = prhibtWrdService.deletePrhibtWrd(deleteVO);
			
			if(checkCnt > 0) {
				model.addAttribute("result", "success");
			} else {
				model.addAttribute("result", "fail");
			}
			
		} catch(SQLException e){
			exLogging("deletePrhibtWrdAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
	
	/**
	 * 금지단어 포함 여부 확인 (Ajax)
	 * @param request HttpServletRequest
	 * @param model 화면 모델
	 * @return jsonView
	 */
	@RequestMapping(value="checkPrhibtWrdAjax", method=RequestMethod.POST)
	public String checkPrhibtWrdAjax(HttpServletRequest request
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			return "jsonView";
		}
		
		String value = request.getParameter("checkValue");
		
		try {
			List<String> containPrhbWrdList = prhibtWrdService.checkContainsPrhibtWrd(value);
			
			model.addAttribute("containPrhbWrdList", containPrhbWrdList);
			model.addAttribute("result", "success");
		} catch (SQLException e) {
			model.addAttribute("result", "fail");
			exLogging("checkPrhibtWrdAjax", e);
		} 

		return "jsonView";
	}
	
	/**
	 * 금지단어 엑셀 업로드 (Ajax)
	 * @param mulRequest Multipart Request
	 * @param model 화면 모델
	 * @return jsonView
	 */
	@RequestMapping(value="insertPrhibtWrdExcelDataAjax", method=RequestMethod.POST)
	public String insertPrhibtWrdExcelDataAjax(MultipartHttpServletRequest mulRequest
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			return "jsonView";
		}
		
		try {
			List<String> errRowList = prhibtWrdService.insertPrhibtWrdExcelData(mulRequest);
			
			if(errRowList == null) {
				model.addAttribute("result", "fail");
			} else {
				model.addAttribute("errList", errRowList);
				model.addAttribute("result", "success");
			}
		}catch (SQLException e) {
			model.addAttribute("result", "fail");
			exLogging("insertPrhibtWrdExcelDataAjax", e);
		}
		
		return "jsonView";
	}
	
}
