package egovframework.vaiv.kr.cmmn.csnst.web;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.vaiv.kr.cmmn.comCode.service.ComCodeVO;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.csnst.service.CsnstService;
import egovframework.vaiv.kr.cmmn.csnst.service.CsnstVO;

/**
 * 만족도 조사 관리 / 관리자 : 만족도 조사 관리에 대한 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/adm/csnst/")
public class CsnstController extends Loggable{
	/* 만족도조사 서비스 선언 */
	@Resource(name="CsnstService")
	private CsnstService csnstService;
	
	/**
	 * 만족도 조사를 관리하는 메인 페이지로 이동 (목록표출)
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return csnstMain
	 */
	@RequestMapping(value="csnstMain")
	public String csnstMain(@ModelAttribute("searchVO") CsnstVO searchVO
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
			
			List<CsnstVO> csnstList = csnstService.selectCsnstList(searchVO); 
	        model.addAttribute("csnstList", csnstList);
	
	        int totCnt = csnstService.selectCsnstListTotCnt(searchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			
	        model.addAttribute("paginationInfo", paginationInfo);
		
		} catch (SQLException e) {
			exLogging("csnstMain", e);
			return "egovframework/com/cmm/error/egovError";
		}
		
		return "vaiv/cmmn/adm/csnst/csnstMain.adm";
	}
	
	@RequestMapping(value="csnstExcelDownload")
	public ModelAndView csnstExcelDownload(@ModelAttribute("searchVO") CsnstVO searchVO
			, ModelAndView andView) {
		
		andView.setViewName("excelView");
		return andView;
	}
	
	
}
