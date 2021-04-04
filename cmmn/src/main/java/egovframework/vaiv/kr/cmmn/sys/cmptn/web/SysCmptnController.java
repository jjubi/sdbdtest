package egovframework.vaiv.kr.cmmn.sys.cmptn.web;

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
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.bbs.ntt.service.NttVO;
import egovframework.vaiv.kr.cmmn.comCode.group.service.ComCodeGroupVO;
import egovframework.vaiv.kr.cmmn.sys.cmptn.service.SysCmptnService;
import egovframework.vaiv.kr.cmmn.sys.cmptn.service.SysCmptnVO;

/**
 * 시스템구성정보 관리 / 관리자 : 시스템구성정보 관리에 대한 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/adm/sys/cmptn/")
public class SysCmptnController extends Loggable{

	/* 시스템 설정 관리 서비스 선언 */
	@Resource(name="SysCmptnService")
	private SysCmptnService sysCmptnService;
	
	/* 메시지 서비스 선언 */
	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;
	
	/* bean Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * 시스템 구성 정보 메인 페이지 (목록 표출)
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return sysCmptnMain
	 * @throws SQLException
	 */
	@RequestMapping("sysCmptnMain")
	public String sysCmptnMain(@ModelAttribute("searchVO") SysCmptnVO searchVO
			, ModelMap model) throws SQLException {
		
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
		
		//게시글 목록
		List<SysCmptnVO> sysCmptnList = sysCmptnService.selectSysCmptnList(searchVO);
		model.addAttribute("sysCmptnList", sysCmptnList);
		
		//게시글 총 갯수
		int totCnt = sysCmptnService.selectSysCmptnListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
        model.addAttribute("paginationInfo", paginationInfo);
		
		
		return "vaiv/cmmn/adm/sys/cmptn/sysCmptnMain.adm";
	}
	
	/**
	 * 시스템 구성 정보 저장 페이지로 이동
	 * @param searchVO 검색VO
	 * @param sysCmptnVO 시스템구정정보VO
	 * @param model 화면 모델 
	 * @return sysCmptnRegist
	 */
	@RequestMapping(value="sysCmptnRegist", method=RequestMethod.GET)
	public String sysCmptnRegist(@ModelAttribute("searchVO") SysCmptnVO searchVO
			, @ModelAttribute("sysCmptnVO") SysCmptnVO sysCmptnVO
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		return "vaiv/cmmn/adm/sys/cmptn/sysCmptnRegist.adm";
	}
	
	/**
	 * 시스템 구성 정보코드 중복 체크 (Ajax)
	 * @param paramMap Request Value Map
	 * @param model 화면 모델
	 * @return jsonView
	 * @throws SQLException
	 */
	@RequestMapping(value="sysCmptnDplctCnfirmAjax", method=RequestMethod.POST)
	public String sysCmptnDplctCnfirmAjax(@RequestParam Map paramMap,
			ModelMap model) throws SQLException {

		String checkCode = (String) paramMap.get("checkCode");

		int usedCnt = sysCmptnService.checkSysCmptnCodeDplct(checkCode);
		model.addAttribute("usedCnt", usedCnt);
		model.addAttribute("checkCode", checkCode);

		return "jsonView";
	}
	
	/**
	 * 입력된 정보로 시스템 구성 정보 저장
	 * @param searchVO 검색VO
	 * @param insertVO 등록VO
	 * @param model 화면 모델
	 * @param bindingResult BindingResult
	 * @return sysCmptnMain.do
	 */
	@RequestMapping(value="insertSysCmptn", method=RequestMethod.POST)
	public String insertSysCmptn(@ModelAttribute("searchVO") SysCmptnVO searchVO
			, @ModelAttribute("sysCmptnVO") SysCmptnVO insertVO
			, ModelMap model
			, BindingResult bindingResult) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(insertVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "vaiv/cmmn/adm/sys/cmptn/sysCmptnRegist.adm";
		} else {
			try {
				sysCmptnService.insertSysCmptn(insertVO);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			} catch (SQLException e) {
				exLogging("insertSysCmptn", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));
			}
			
			return "redirect:/cmmn/adm/sys/cmptn/sysCmptnMain.do";
		}
	}
	
	/**
	 * 시스템 구성 정보 수정 페이지로 이동
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return sysCmptnModify
	 */
	@RequestMapping(value="sysCmptnModify")
	public String sysCmptnModify(@ModelAttribute("searchVO") SysCmptnVO searchVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
			
		try {
			SysCmptnVO sysCmptnVO = sysCmptnService.selectSysCmptn(searchVO);
			model.addAttribute("sysCmptnVO", sysCmptnVO);
		} catch (SQLException e) {
			exLogging("sysCmptnModify", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));
		}
				
		return "/vaiv/cmmn/adm/sys/cmptn/sysCmptnModify.adm";
	}
	
	/**
	 * 입력된 정보로 시스템 구성 정보 수정
	 * @param searchVO 검색VO
	 * @param updateVO 수정VO
	 * @param model 화면 모델
	 * @param bindingResult BindingResult
	 * @return sysCmptnMain.do
	 */
	@RequestMapping(value="updateSysCmptn", method=RequestMethod.POST)
	public String updateSysCmptn(@ModelAttribute("searchVO") SysCmptnVO searchVO
			, @ModelAttribute("sysCmptnVO") SysCmptnVO updateVO
			, ModelMap model
			, BindingResult bindingResult) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(updateVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "/vaiv/cmmn/adm/sys/cmptn/sysCmptnModify.adm";
		} else {
			try {
				sysCmptnService.updateSysCmptn(updateVO);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			} catch (SQLException e) {
				exLogging("updateComCode", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.update"));
			}
			
			return "redirect:/cmmn/adm/sys/cmptn/sysCmptnMain.do";
		}
	}
	
	/**
	 * 시스템 구성 정보 삭제
	 * @param searchVO 검색VO
	 * @param deleteVO 삭제VO
	 * @param model 화면 모델
	 * @return sysCmptnMain.do
	 */
	@RequestMapping(value="deleteSysCmptn", method=RequestMethod.POST)
	public String deleteSysCmptn(@ModelAttribute("searchVO") SysCmptnVO searchVO
			, @ModelAttribute("sysCmptnVO") SysCmptnVO deleteVO
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			sysCmptnService.deleteSysCmptn(deleteVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		} catch (SQLException e) {
			exLogging("deleteComCode", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));
		}
		
		return "redirect:/cmmn/adm/sys/cmptn/sysCmptnMain.do";
	}
	
	
	
}
