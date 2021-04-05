package egovframework.vaiv.kr.cmmn.banner.web;

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
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.banner.service.BannerService;
import egovframework.vaiv.kr.cmmn.banner.service.BannerVO;
import egovframework.vaiv.kr.cmmn.comCode.service.ComCodeService;

/**
 * 배너 관리 / 관리자 : 배너관리 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/adm/banner/")
public class BannerController extends Loggable{
	/* 배너 서비스 호출 */
	@Resource(name="BannerService")
	private BannerService bannerService;
	
	/* 전자정부 메시지 서비스 호출 */
	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;

	@Resource(name="ComCodeService")
	private ComCodeService comCodeService;
	
	/* bean validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * 배너를 관리하는 메인 페이지로 이동 (목록표출)
	 * @param searchVO 검색VO
	 * @param model 화면모델
	 * @return cmmn/adm/banner/bannerMain
	 * @throws SQLException
	 */
	@RequestMapping(value="bannerMain")
	public String bannerMain(@ModelAttribute("searchVO") BannerVO searchVO
			,ModelMap model) throws SQLException {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
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
        List<BannerVO> bannerList = bannerService.selectBannerList(searchVO); 
        model.addAttribute("bannerList", bannerList);

        //총 조건에 맞는 배너 총 갯수
        int totCnt = bannerService.selectBannerListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        //배너 유형 공통코드에서 가져가기
        List<?> bannerTyList = comCodeService.selectComCodeList("BANNER_TY");
        model.addAttribute("bannerTyList", bannerTyList);
		
		return "vaiv/cmmn/adm/banner/bannerMain.adm";
	}
	
	/**
	 * 배너를 등록하는 페이지로 이동
	 * @param searchVO 검색VO
	 * @param BannerVO 등록 배너 VO
	 * @param model 화면 모델
	 * @return bannerRegist
	 * @throws SQLException
	 */
	@RequestMapping(value="bannerRegist", method=RequestMethod.GET)
	public String bannerRegist(@ModelAttribute("searchVO") BannerVO searchVO
			, @ModelAttribute("bannerVO") BannerVO BannerVO
			, ModelMap model) throws SQLException {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		List<?> bannerTyList = comCodeService.selectComCodeList("BANNER_TY");
        model.addAttribute("bannerTyList", bannerTyList);
		
		return "vaiv/cmmn/adm/banner/bannerRegist.adm";
	}
	
	/**
	 * 배너를 등록
	 * @param searchVO 검색VO
	 * @param insertVO 등록VO
	 * @param multiReq Multipart Request
	 * @param bindingResult BindingResult
	 * @param model 화면 모델
	 * @return bannerMain.do
	 */
	@RequestMapping(value="insertBanner", method=RequestMethod.POST)
	public String insertBanner(@ModelAttribute("searchVO") BannerVO searchVO
			, @ModelAttribute("bannerVO") BannerVO insertVO
			, MultipartHttpServletRequest multiReq
			, BindingResult bindingResult
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(insertVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "vaiv/cmmn/adm/banner/bannerRegist.adm";
		} else {
			
			try {
				bannerService.insertBanner(insertVO, multiReq);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			} catch (SQLException e) {
				exLogging("insertBanner", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));
			}
			
			return "redirect:/cmmn/adm/banner/bannerMain.do";
		}
	}
	
	/**
	 * 배너를 수정하는 페이지로 이동
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return bannerModify
	 * @throws SQLException 
	 */
	@RequestMapping(value="bannerModify")
	public String bannerModify(@ModelAttribute("searchVO") BannerVO searchVO
			, ModelMap model) throws SQLException {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		BannerVO selectVO = bannerService.selectBanner(searchVO);
		model.addAttribute("bannerVO", selectVO);
		
		List<?> bannerTyList = comCodeService.selectComCodeList("BANNER_TY");
        model.addAttribute("bannerTyList", bannerTyList);
	        
		return "vaiv/cmmn/adm/banner/bannerModify.adm";
	}
	
	
	/**
	 * 배너를 수정
	 * @param searchVO 검색VO
	 * @param updateVO 수정VO
	 * @param multiReq Multipart Request
	 * @param bindingResult BindingResult
	 * @param model 화면 모델
	 * @return bannerMain.do
	 */
	@RequestMapping(value="updateBanner", method=RequestMethod.POST)
	public String updateBanner(@ModelAttribute("searchVO") BannerVO searchVO
			, @ModelAttribute("bannerVO") BannerVO updateVO
			, MultipartHttpServletRequest multiReq
			, BindingResult bindingResult
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(updateVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "vaiv/cmmn/adm/banner/bannerModify.adm";
		} else {
			
			try {
				bannerService.updateBanner(updateVO, multiReq);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			} catch (SQLException e) {
				exLogging("updateBanner", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.update"));
			}
			
			return "redirect:/cmmn/adm/banner/bannerMain.do";
		}
	}
	
	/**
	 * 배너를 삭제
	 * @param searchVO 검색VO
	 * @param deleteVO 삭제VO
	 * @param model 화면모델
	 * @return bannerMain.do
	 */
	@RequestMapping(value="deleteBanner", method=RequestMethod.POST)
	public String deleteBanner(@ModelAttribute("searchVO") BannerVO searchVO
			, @ModelAttribute("bannerVO") BannerVO deleteVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
			
		try {
			bannerService.deleteBanner(deleteVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		} catch (SQLException e) {
			exLogging("deleteBanner", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));
		}
		
		return "redirect:/cmmn/adm/banner/bannerMain.do";
	}
	
	/**
	 * 배너의 순서 및 사용여부를 수정 (Ajax)
	 * @param updateVO 수정VO
	 * @param updateType 수정타입(ordr, useAt)
	 * @param model 화면 모델
	 * @return jsonView
	 */
	@RequestMapping(value="updateBannerAjax", method=RequestMethod.POST)
	public String updateBannerAjax(BannerVO updateVO
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
				checkCnt = bannerService.updateBannerOrdr(updateVO);
			} else if("useAt".equals(updateType)) {
				checkCnt = bannerService.updateBannerUseAt(updateVO);
			}
			if(checkCnt > 0) {
				model.addAttribute("result", "success");
			} else {
				model.addAttribute("result", "fail");
			}
		} catch(SQLException e){
			exLogging("updateBannerAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
	
}
