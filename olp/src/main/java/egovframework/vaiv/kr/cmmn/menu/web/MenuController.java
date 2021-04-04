package egovframework.vaiv.kr.cmmn.menu.web;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;
import egovframework.vaiv.kr.cmmn.bbs.service.BbsService;
import egovframework.vaiv.kr.cmmn.bbs.service.BbsVO;
import egovframework.vaiv.kr.cmmn.cntnts.service.CntntsService;
import egovframework.vaiv.kr.cmmn.cntnts.service.CntntsVO;
import egovframework.vaiv.kr.cmmn.menu.service.MenuService;
import egovframework.vaiv.kr.cmmn.menu.service.MenuVO;

/**
 * 메뉴 관리 / 관리자 : 메뉴 관리에 대한 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/adm/menu/")
public class MenuController extends Loggable {
	
	/* 메뉴 서비스 */
	@Resource(name="MenuService")
	private MenuService menuService;
	/* 게시판 서비스 */
	@Resource(name="BbsService")
	private BbsService bbsService;
	/* 컨텐츠 서비스 */
	@Resource(name="CntntsService")
	private CntntsService cntntsService;
	/* 권한 서비스 */
	@Resource(name="egovAuthorManageService")
	private EgovAuthorManageService egovAuthorManageService;
	
	/**
	 * 메뉴를 관리하는 메인 페이지 (목록 표출)
	 * @param searchVO 검색VO
 	 * @param model 화면 모델
	 * @return menuManageMain
	 * @throws SQLException
	 */
	@RequestMapping(value="menuManageMain")
	public String menuManageMain(@ModelAttribute("searchVO") MenuVO searchVO
			, ModelMap model) throws SQLException {
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		logging("메뉴 관리 목록 페이지");
		
		AuthorManageVO authorVO = new AuthorManageVO();
		authorVO.setSearchCondition("1");
		authorVO.setFirstIndex(0);
		authorVO.setRecordCountPerPage(100);
		
		if("A".equals(searchVO.getMenuSe())) {
			authorVO.setSearchKeyword("관리자");
			List<AuthorManageVO> author_result = egovAuthorManageService.selectAuthorList(authorVO);
			model.addAttribute("authorList", author_result);
			model.addAttribute("nowMenuSe", "A");
		} else {
			authorVO.setSearchKeyword("사용자");
			List<AuthorManageVO> author_result = egovAuthorManageService.selectAuthorList(authorVO);
			model.addAttribute("authorList", author_result);
			model.addAttribute("nowMenuSe", "U");
		}
		
		//게시판 목록
		SearchVO sBbsVO = new SearchVO();
		sBbsVO.setSearchCondition("5");
		sBbsVO.setSearchKeyword("Y");
		sBbsVO.setPagingYn("N");
		List<BbsVO> bbsList = bbsService.selectBbsList(sBbsVO);
		model.addAttribute("bbsList", bbsList);
		
		//컨텐츠 목록
		CntntsVO sCntntsVO = new CntntsVO();
		sCntntsVO.setSearchCondition("5");
		sCntntsVO.setSearchKeyword("Y");
		sCntntsVO.setPagingYn("N");
		List<CntntsVO> cntntsList = cntntsService.selectCntntsList(sCntntsVO);
		model.addAttribute("cntntsList", cntntsList);
		
		return "vaiv/cmmn/adm/menu/menuManageMain.adm";
	}
	
	/**
	 * 메뉴 목록 데이터 가져오기 (Ajax)
	 * @param request HttpServletRequest
	 * @param model 화면 모델
	 * @return jsonView
	 * @throws SQLException
	 */
	@RequestMapping(value="selectMenuListAjax", method=RequestMethod.POST)
	public String selectMenuListAjax(HttpServletRequest request
			, ModelMap model) throws SQLException {
		
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			return "jsonView";
		}
		
		logging("메뉴 관리 목록 가져오기 Ajax");
		
		//메뉴 가져오기
		try{			
			//서비스 호출하여 리스트 가져오기
			MenuVO searchVO = new MenuVO();
			searchVO.setMenuSe(request.getParameter("menuSe"));
			List<MenuVO> resultList = menuService.selectMenuList(searchVO);
			model.addAttribute("resultList", resultList);
			model.addAttribute("result", "success");
		} catch(SQLException e){
			exLogging("selectMenuListAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
		
	/**
	 * 메뉴 목록 일괄 저장
	 * @param request HttpServletRequest
	 * @param model 화면 모델
	 * @return menuManageMain.do
	 * @throws SQLException
	 */
	@RequestMapping(value="insertMenu", method=RequestMethod.POST)
	public String insertMenu(HttpServletRequest request
			, ModelMap model) throws SQLException {
		
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		logging("메뉴 저장");
		String menuSe = "";
		try {
			String menuListToJson = request.getParameter("menuListJson");
			String menuDeleteStr = request.getParameter("menuDeleteStr");
			menuSe = request.getParameter("menuSe");
			if(menuSe == null || "".equals(menuSe)) {
				menuSe = "U";
			}
			
			if(menuDeleteStr != null && !"".equals(menuDeleteStr)) {
				MenuVO deleteVO = new MenuVO();
				deleteVO.setMenuSe(menuSe);
				deleteVO.setMenuDeleteStr(menuDeleteStr);
				//메뉴 삭제
				menuService.deleteMenu(deleteVO);
			}
			//메뉴 등록
			menuService.insertMenu(menuListToJson);
		} catch (SQLException e) {
			exLogging("insertMenu", e);
		}
		String gubunStr = "";
		if("A".equals(menuSe)) {
			gubunStr = "?menuSe=A";
		}
		return "redirect:/cmmn/adm/menu/menuManageMain.do"+gubunStr;
	}
	
	/**
	 * 메뉴 개별 저장 (Ajax)
	 * @param request HttpServletRequest
	 * @param model 화면 모델
	 * @return jsonView
	 * @throws SQLException
	 */
	@RequestMapping(value="updateMenuAjax", method=RequestMethod.POST)
	public String updateMenuAjax(HttpServletRequest request
			, ModelMap model) throws SQLException {
		
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			return "jsonView";
		}
		logging("메뉴 개별 저장 Ajax");
		
		try {
			//메뉴 수정
			menuService.updateMenu(request.getParameter("menuJson"));
			model.addAttribute("result", "success");
		} catch (Exception e) {
			model.addAttribute("result", "fail");
			exLogging("updateMenu", e);
		}
		
		return "jsonView";
	}
	
	/**
	 * 설정된 메뉴 리스트를 보여주는 View
	 * @param model 화면 모델 
	 * @return menuView
	 */
	@RequestMapping(value="menuView")
	public String menuView(ModelMap model) {
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		logging("메뉴 목록 가져오기");
		
		//메뉴 가져오기
		try{			
			//서비스 호출하여 리스트 가져오기
			MenuVO searchVO = new MenuVO();
			searchVO.setMenuSe("A");
			List<MenuVO> resultList = menuService.viewMenuList(searchVO);
			model.addAttribute("resultList", resultList);
		} catch(SQLException e){
			exLogging("menuView", e);
		}
		
		return "vaiv/cmmn/adm/menu/menuView";
	}
	
}
