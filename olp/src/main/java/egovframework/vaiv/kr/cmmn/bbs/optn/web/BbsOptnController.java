package egovframework.vaiv.kr.cmmn.bbs.optn.web;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;
import egovframework.vaiv.kr.cmmn.bbs.optn.service.BbsOptnService;
import egovframework.vaiv.kr.cmmn.bbs.optn.service.BbsOptnVO;
import egovframework.vaiv.kr.cmmn.bbs.service.BbsService;
import egovframework.vaiv.kr.cmmn.bbs.service.BbsVO;

/**
 * 게시판 옵션 관리 / 관리자 : 게시판 옵션 관리 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/adm/bbs/optn/")
public class BbsOptnController extends Loggable {
	
	/* 게시판 옵션 서비스 선언 */
	@Resource(name="BbsOptnService")
	private BbsOptnService bbsOptnService;
	
	/* 게시판관리 서비스 선언 */
	@Resource(name="BbsService")
	private BbsService bbsService;
	
	/* 메시지 서비스 선언 */
	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;
	
	/**
	 * 게시판 옵션 수정 페이지로 이동
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return bbsOptnModify
	 */
	@RequestMapping(value="bbsOptnModify")
	public String bbsOptnModify(@ModelAttribute("searchVO") SearchVO searchVO
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			//게시판 정보
			BbsVO bbsVO = bbsService.selectBbs(searchVO); 
	        model.addAttribute("bbsVO", bbsVO);
	        
	        //게시판 옵션 정보 가져오기
	        BbsOptnVO bbsOptnVO = bbsOptnService.selectBbsOptn(searchVO);
	        model.addAttribute("bbsOptnVO", bbsOptnVO);
	
		} catch (SQLException e) {
			exLogging("bbsOptnModify", e);
			return "egovframework/com/cmm/error/egovError";
		} catch (Exception e) {
			exLogging("bbsOptnModify", e);
			return "egovframework/com/cmm/error/egovError";
		}
		
		
		return "vaiv/cmmn/adm/bbs/optn/bbsOptnModify.adm";
	}
	
	/**
	 * 게시판 옵션 수정
	 * @param updateVO 수정VO
	 * @param model 화면 모델
	 * @return bbsOptnModify.do
	 */
	@RequestMapping(value="updateBbsOptn", method=RequestMethod.POST)
	public String updateBbsOptn(@ModelAttribute("bbsOptnVO") BbsOptnVO updateVO
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			bbsOptnService.updateBbsOptn(updateVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
		} catch(SQLException e) {
			exLogging("updateBbsOptn", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.update"));
		}

		return "redirect:/cmmn/adm/bbs/optn/bbsOptnModify.do?bbsId="+updateVO.getBbsId();
	}
}
