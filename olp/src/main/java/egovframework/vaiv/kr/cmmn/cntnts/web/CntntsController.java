package egovframework.vaiv.kr.cmmn.cntnts.web;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cmm.util.EgovWebUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.cntnts.service.CntntsHisVO;
import egovframework.vaiv.kr.cmmn.cntnts.service.CntntsService;
import egovframework.vaiv.kr.cmmn.cntnts.service.CntntsVO;

/**
 * 컨텐츠 관리 / 관리자 : 컨텐츠 관리에 대한 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/adm/cntnts/")
public class CntntsController extends Loggable{
	/* 컨텐츠 서비스 선언 */
	@Resource(name="CntntsService")
	private CntntsService cntntsService;
	
	/* 메시지 서비스 선언 */
	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;
	
	/* bean Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * 컨텐츠를 관리하는 메인 페이지 (목록 표출)
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return cntntsMain
	 */
	@RequestMapping(value="cntntsMain")
	public String cntntsMain(@ModelAttribute("searchVO") CntntsVO searchVO
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
			
			List<CntntsVO> cntntsList = cntntsService.selectCntntsList(searchVO); 
	        model.addAttribute("cntntsList", cntntsList);
	
	        int totCnt = cntntsService.selectCntntsListTotCnt(searchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			
	        model.addAttribute("paginationInfo", paginationInfo);
		
		} catch (SQLException e) {
			exLogging("selectCntntsList", e);
			return "egovframework/com/cmm/error/egovError";
		}
		
		
		return "vaiv/cmmn/adm/cntnts/cntntsMain.adm";
	}
	
	/**
	 * 컨텐츠 등록 페이지로 이동
	 * @param searchVO 검색VO
	 * @param cntntsVO 컨텐츠VO
	 * @param model 화면 모델
	 * @return cntntsRegist
	 */
	@RequestMapping(value="cntntsRegist", method=RequestMethod.GET)
	public String cntntsRegist(@ModelAttribute("searchVO") CntntsVO searchVO
			, @ModelAttribute("cntntsVO") CntntsVO cntntsVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
				
		return "vaiv/cmmn/adm/cntnts/cntntsRegist.adm";
	}
	
	/**
	 * 입력된 정보의 컨텐츠 저장
	 * @param searchVO 검색VO
	 * @param insertVO 등록VO
	 * @param bindingResult BindingResult
	 * @param model 화면 모델
	 * @return cntntsMain.do
	 */
	@RequestMapping(value="insertCntnts", method=RequestMethod.POST)
	public String insertCntnts(@ModelAttribute("searchVO") CntntsVO searchVO
			, @ModelAttribute("cntntsVO") CntntsVO insertVO
			, MultipartHttpServletRequest multiReq
			, BindingResult bindingResult
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(insertVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "vaiv/cmmn/adm/cntnts/cntntsRegist.adm";
		} else {
			
			try {
				cntntsService.insertCntnts(insertVO, multiReq);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			} catch (SQLException e) {
				exLogging("insertCntnts", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));
			}
			
			return "redirect:/cmmn/adm/cntnts/cntntsMain.do";
		}
	}
	
	/**
	 * 컨텐츠 수정 페이지로 이동
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return cntntsModify
	 */
	@RequestMapping(value="cntntsModify")
	public String cntntsModify(@ModelAttribute("searchVO") CntntsVO searchVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			//컨텐츠 상세
			CntntsVO cntntsVO = cntntsService.selectCntnts(searchVO);
			cntntsVO.setCntntsCn(EgovWebUtil.nonClearXSSMinimum(cntntsVO.getCntntsCn()));
			model.addAttribute("cntntsVO", cntntsVO);
		} catch (SQLException e) {
			exLogging("selectCntnts", e);
		} 
		
		return "vaiv/cmmn/adm/cntnts/cntntsModify.adm";
	}
	
	/**
	 * 입력된 정보로 컨텐츠 수정
	 * @param searchVO 검색VO
	 * @param updateVO 수정VO
	 * @param bindingResult BindingResult
	 * @param model 화면 모델
	 * @return cntntsMain.do
	 */
	@RequestMapping(value="updateCntnts", method=RequestMethod.POST)
	public String updateCntnts(@ModelAttribute("searchVO") CntntsVO searchVO
			, @ModelAttribute("cntntsVO") CntntsVO updateVO
			, MultipartHttpServletRequest multiReq
			, BindingResult bindingResult
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(updateVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "vaiv/cmmn/adm/cntnts/cntntsModify.adm";
		} else {
			
			try {
				cntntsService.updateCntnts(updateVO, multiReq);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			} catch (SQLException e) {
				exLogging("updateCntnts", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.update"));
			}
			
			return "redirect:/cmmn/adm/cntnts/cntntsMain.do";
		}
	}
	
	/**
	 * 입력된 정보의 컨텐츠 삭제
	 * @param searchVO 검색VO
	 * @param deleteVO 삭제VO
	 * @param model 화면 모델
	 * @return cntntsMain.do
	 */
	@RequestMapping(value="deleteCntnts", method=RequestMethod.POST)
	public String deleteCntnts(@ModelAttribute("searchVO") CntntsVO searchVO
			, @ModelAttribute("cntntsVO") CntntsVO deleteVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			cntntsService.deleteCntnts(deleteVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		} catch (SQLException e) {
			exLogging("deleteCntnts", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));
		}
		
		return "redirect:/cmmn/adm/cntnts/cntntsMain.do";
	}
	
	/**
	 * 관리자 > 등록된 컨텐츠 뷰
	 * @param cntntsId 컨텐츠 ID
	 * @param cntntsCode 컨텐츠 코드
	 * @param request HttpServletRequest
	 * @param model 화면 모델
	 * @return cntntsView
	 */
	@RequestMapping(value="{cntntsCode}/cntntsView{cntntsId}")
	public String cntntsView(@PathVariable("cntntsId") String cntntsId
			, @PathVariable("cntntsCode") String cntntsCode
			, HttpServletRequest request
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		String hisSeq = request.getParameter("hisSeq");
		
		if(hisSeq != null && !"".equals(hisSeq)) {
			//히스토리 컨텐츠 가져오기
			CntntsHisVO searchVO = new CntntsHisVO();
			searchVO.setHistSeqNo(hisSeq);
			
			try {
				CntntsHisVO viewVO = cntntsService.selectCntntsHis(searchVO);
				viewVO.setCntntsCn(EgovWebUtil.nonClearXSSMinimum(viewVO.getCntntsCn()));
				model.addAttribute("viewCntntsVO", viewVO);
			} catch (SQLException e) {
				exLogging("cntntsViewHist", e);
			}
		} else {
			//컨텐츠 가져오기
			CntntsVO searchVO = new CntntsVO();
			searchVO.setCntntsId(cntntsId);
			searchVO.setCntntsCode(cntntsCode);
			
			try {
				CntntsVO viewVO = cntntsService.selectCntnts(searchVO);
				viewVO.setCntntsCn(EgovWebUtil.nonClearXSSMinimum(viewVO.getCntntsCn()));
				model.addAttribute("viewCntntsVO", viewVO);
			} catch (SQLException e) {
				exLogging("cntntsView", e);
			}
		}
		
		return "vaiv/cmmn/adm/cntnts/cntntsView.adm";
	}
	
	/**
	 * 컨텐츠 히스토리 목록 조회 (Ajax)
	 * @param request HttpServletRequest
	 * @param model 화면 모델
	 * @return jsonView
	 */
	@RequestMapping(value="selectCntntsHisListAjax", method=RequestMethod.POST)
	public String selectCntntsHisListAjax(HttpServletRequest request
			, ModelMap model) {
		
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			model.addAttribute("message", "로그인 인증 실패");
			return "jsonView";
		}
		
		logging("컨텐츠 히스토리 목록 가져오기 Ajax");
		
		try{
			//서비스 호출하여 리스트 가져오기
			String cntntsId = request.getParameter("cntntsId");
			String nowPageIndex = request.getParameter("pageIndex");
			CntntsHisVO sCntntsHisVO = new CntntsHisVO();
			sCntntsHisVO.setCntntsId(cntntsId);
			sCntntsHisVO.setPageIndex(Integer.parseInt(nowPageIndex));
			
			//paging 
	    	PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(sCntntsHisVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(sCntntsHisVO.getPageUnit());
			paginationInfo.setPageSize(sCntntsHisVO.getPageSize());
	
			sCntntsHisVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			sCntntsHisVO.setLastIndex(paginationInfo.getLastRecordIndex());
			sCntntsHisVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			List<CntntsHisVO> cntntsHisList = cntntsService.selectCntntsHisList(sCntntsHisVO);			
				
	        int totCnt = cntntsService.selectCntntsHisListTotCnt(sCntntsHisVO);
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("resultList", cntntsHisList);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("result", "success");
		} catch(SQLException e){
			exLogging("selectCntntsHisListAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
	
	/**
	 * 컨텐츠 히스토리 롤백 기능 (Ajax)
	 * @param request HttpServletRequest
	 * @param model 화면 모델
	 * @return jsonView
	 */
	@RequestMapping(value="updateCntntsHisRollback", method=RequestMethod.POST)
	public String updateCntntsHisRollback(HttpServletRequest request
			, ModelMap model) {

		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			model.addAttribute("message", "로그인 인증 실패");
			return "jsonView";
		}
		
		try {
			String histSeqNo = request.getParameter("histSeqNo");
			
			CntntsHisVO updateVO = new CntntsHisVO();
			updateVO.setHistSeqNo(histSeqNo);
			cntntsService.updateCntntsHisRollback(updateVO);
			model.addAttribute("result", "success");
		} catch (SQLException e) {
			exLogging("updateCntnts", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
	
	
	
}
