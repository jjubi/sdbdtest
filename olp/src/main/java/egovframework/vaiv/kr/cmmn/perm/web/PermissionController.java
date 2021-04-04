package egovframework.vaiv.kr.cmmn.perm.web;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import egovframework.vaiv.kr.cmmn.perm.service.IpCtrlVO;
import egovframework.vaiv.kr.cmmn.perm.service.PermissionService;

/**
 * IP 접근 관리 / 관리자 : IP 접근 관리에 대한 요청을 처리하는 Controller
 * @category 공통
 * @author jeon
 * @since 2021-01-19
 * @version v1.0
 * @see
 * <pre>
 *  ******************************************
 *  수정 이력
 *  
 *  수정일                  수정자                 수정내용
 *  ------------------------------------------
 *  2021.01.19    jeon           최초 등록
 * 
 * 
 *  ******************************************
 *  Copyright 2020 VAIV Co.
 *  All rights reserved
 * </pre>
 */
@Controller
@RequestMapping("/cmmn/adm/perm/")
public class PermissionController extends Loggable{
	
	/* 허가 서비스 선언 */
	@Resource(name="PermissionService")
	private PermissionService permissionService;
	
	/* 메시지 서비스 선언 */
	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;
	
	/* bean Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * IP 관리 메인 페이지 (목록표출)
	 * @param requst HttpServletRequest
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return ipCtrlMain
	 * @throws SQLException
	 */
	@RequestMapping("ipCtrlMain")
	public String ipCtrlMain(HttpServletRequest requst
			, @ModelAttribute("searchVO") IpCtrlVO searchVO
			, ModelMap model) throws SQLException {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		String permAt = requst.getParameter("permAt");
		searchVO.setPermAt(permAt);
		model.addAttribute("permAt", permAt );
		
		//paging 
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		//ip 목록
		List<IpCtrlVO> ipCrtlList = permissionService.selectIpCtrlList(searchVO);
		model.addAttribute("ipCrtlList", ipCrtlList );
		
		//ip 총 갯수
		int totCnt = permissionService.selectIpCtrlTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
        model.addAttribute("paginationInfo", paginationInfo);
		
		
		return "vaiv/cmmn/adm/perm/ipCtrlMain.adm";
	}
	
	/**
	 * IP 중복 체크 (Ajax)
	 * @param paramMap Request Value Map
	 * @param model 화면 모델
	 * @return jsonView
	 * @throws SQLException
	 */
	@RequestMapping(value="ipCtrlDplctCnfirmAjax", method=RequestMethod.POST)
	public String ipCtrlDplctCnfirmAjax(@RequestParam Map paramMap
			, ModelMap model) throws SQLException {

		String connectIp = (String) paramMap.get("connectIp");
		String permAt = (String) paramMap.get("permAt");
		
		IpCtrlVO ipCtrlVO = new IpCtrlVO();
		ipCtrlVO.setConnectIp(connectIp);
		ipCtrlVO.setPermAt(permAt);
		
		int usedCnt = permissionService.checkIpCtrlDplct(ipCtrlVO);
		model.addAttribute("usedCnt", usedCnt);
		model.addAttribute("connectIp", connectIp);

		return "jsonView";
	}

	/**
	 * IP 정보를 저장하는 페이지로 이동
	 * @param searchVO 검색VO
	 * @param ipCtrlVO IP 관리VO
	 * @param model 화면 모델
	 * @return ipCtrlRegist
	 */
	@RequestMapping(value="ipCtrlRegist", method=RequestMethod.GET)
	public String ipCtrlRegist(@ModelAttribute("searchVO") IpCtrlVO searchVO
			, @ModelAttribute("ipCtrlVO") IpCtrlVO ipCtrlVO
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		model.addAttribute("permAt", searchVO.getPermAt() );
		return "vaiv/cmmn/adm/perm/ipCtrlRegist.adm";
	}
	
	/**
	 * 입력된 정보로 IP정보 저장
	 * @param searchVO 검색VO
	 * @param insertVO 등록VO
	 * @param model 화면 모델
	 * @param bindingResult BindingResult
	 * @return ipCtrlMain.do
	 */
	@RequestMapping(value="ipCtrlInsert", method=RequestMethod.POST)
	public String ipCtrlInsert(@ModelAttribute("searchVO") IpCtrlVO searchVO
			, @ModelAttribute("ipCtrlVO") IpCtrlVO insertVO
			, ModelMap model
			, BindingResult bindingResult) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(insertVO, bindingResult); //validation 수행
		if(bindingResult.hasErrors()) {
			return "vaiv/cmmn/adm/perm/ipCtrlRegist.adm";
		} else {
			try {
				permissionService.insertIpCtrl(insertVO);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			} catch (SQLException e) {
				exLogging("insertIpCtrl", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));
			}
			
			return "redirect:/cmmn/adm/perm/ipCtrlMain.do?permAt="+insertVO.getPermAt();
		}
	}
	
	/**
	 * IP 정보 수정 페이지로 이동
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return ipCtrlModify
	 */
	@RequestMapping(value="ipCtrlModify")
	public String ipCtrlModify(@ModelAttribute("searchVO") IpCtrlVO searchVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
			
		try {
			String ipId = searchVO.getPermIpId();
			if(ipId != null && !ipId.equals("")) {
				IpCtrlVO ipCtrlVO = permissionService.selectIpCtrl(searchVO);
				model.addAttribute("ipCtrlVO", ipCtrlVO);
			}else {
				return "redirect:/cmmn/adm/perm/ipCtrlMain.do?permAt=" + searchVO.getPermAt();
			}
		} catch (SQLException e) {
			exLogging("ipCtrlModify", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));
		}
				
		return "/vaiv/cmmn/adm/perm/ipCtrlModify.adm";
	}
	
	/**
	 * 입력된 정보로 IP 정보 수정
	 * @param searchVO 검색VO
	 * @param updateVO 수정VO
	 * @param model 화면 모델
	 * @param bindingResult BindingResult
	 * @return ipCtrlMain.do
	 */
	@RequestMapping(value="ipCtrlUpdate", method=RequestMethod.POST)
	public String ipCtrlUpdate(@ModelAttribute("searchVO") IpCtrlVO searchVO
			, @ModelAttribute("ipCtrlVO") IpCtrlVO updateVO
			, ModelMap model
			, BindingResult bindingResult) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(updateVO, bindingResult); //validation 수행
		if(bindingResult.hasErrors()) {
			return "/vaiv/cmmn/adm/perm/ipCtrlModify.adm";
		} else {
			try {
				permissionService.updateIpCtrl(updateVO);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			} catch (SQLException e) {
				exLogging("updateComCode", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.update"));
			}
			
			return "redirect:/cmmn/adm/perm/ipCtrlMain.do?permAt="+updateVO.getPermAt();
		}
	}
	
	/**
	 * 입력된 정보로 IP 정보 삭제
	 * @param searchVO 검색VO
	 * @param deleteVO 삭제VO
	 * @param model 화면 모델
	 * @return ipCtrlMain.do
	 */
	@RequestMapping(value="ipCtrlDelete", method=RequestMethod.POST)
	public String ipCtrlDelete(@ModelAttribute("searchVO") IpCtrlVO searchVO
			, @ModelAttribute("ipCtrlVO") IpCtrlVO deleteVO
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			String ipId = searchVO.getPermIpId();
			if(ipId == null || ipId.equals("")) {
				return "/vaiv/cmmn/adm/perm/ipCtrlModify.adm";
			}else {
				permissionService.deleteIpCtrl(deleteVO);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
			}
		} catch (SQLException e) {
			exLogging("deleteComCode", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));
		}
		
		return "redirect:/cmmn/adm/perm/ipCtrlMain.do?permAt=" + searchVO.getPermAt();
	}

}
