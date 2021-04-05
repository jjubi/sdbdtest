package egovframework.vaiv.kr.cmmn.menu.web;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.menu.service.MenuService;
import egovframework.vaiv.kr.cmmn.menu.service.MenuVO;

/**
 * 메뉴 관리 / 사용자 : 메뉴 관리에 대한 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/usr/menu/")
public class UsrMenuController extends Loggable {
	
	/* 메뉴 서비스 */
	@Resource(name="MenuService")
	private MenuService menuService;
	
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
			searchVO.setMenuSe("U");
			List<MenuVO> resultList = menuService.viewMenuList(searchVO);
			model.addAttribute("resultList", resultList);
		} catch(SQLException e){
			exLogging("menuView", e);
		}
		
		return "vaiv/cmmn/usr/menu/menuView";
	}
}
