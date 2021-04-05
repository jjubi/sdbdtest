package egovframework.vaiv.kr.cmmn.bbs.author.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.bbs.author.service.BbsAuthorService;
import egovframework.vaiv.kr.cmmn.bbs.author.service.BbsAuthorVO;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;
import egovframework.vaiv.kr.cmmn.bbs.service.BbsService;
import egovframework.vaiv.kr.cmmn.bbs.service.BbsVO;

/**
 * 게시판 권한 관리 / 관리자 : 게시판 권한관리 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/adm/bbs/author/")
public class BbsAuthorController extends Loggable{
	
	/* 게시판 권한 서비스 선언 */
	@Resource(name="BbsAuthorService")
	private BbsAuthorService bbsAuthorService;
	
	/* 게시판관리 서비스 선언*/
	@Resource(name="BbsService")
	private BbsService bbsService;
	
	/* 권한관리 서비스 선언 */
	@Resource(name="egovAuthorManageService")
	private EgovAuthorManageService egovAuthorManageService;
	
	/* 메시지 서비스 선언 */
	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;
	
	/**
	 * 게시판 권한 수정 페이지로 이동
	 * @param searchVO 검색 VO
	 * @param model 화면 모델
	 * @return bbsAuthorModify
	 */
	@RequestMapping(value="bbsAuthorModify")
	public String bbsAuthorModify(@ModelAttribute("searchVO") SearchVO searchVO
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			//권한 목록 가져오기
			AuthorManageVO authorVO = new AuthorManageVO();
			List<AuthorManageVO> authorManageList = egovAuthorManageService.selectAuthorAllList(authorVO);
			List<AuthorManageVO> authorManageList2 = new ArrayList<AuthorManageVO>();
			for(AuthorManageVO vo : authorManageList) {
				if(!vo.getAuthorCode().contains("IS_AUTHENTICATED_")) {
					authorManageList2.add(vo);
				}
			}
			model.addAttribute("authorList", authorManageList2);
			//게시판 정보
			BbsVO bbsVO = bbsService.selectBbs(searchVO); 
	        model.addAttribute("bbsVO", bbsVO);
	        
	        //게시판 권한 가져오기
	        BbsAuthorVO bbsAuthorVO = bbsAuthorService.selectBbsAuthor(searchVO);
	        model.addAttribute("bbsAuthorVO", bbsAuthorVO);
	
		} catch (SQLException e) {
			exLogging("bbsAuthorModify", e);
			return "egovframework/com/cmm/error/egovError";
		} catch (Exception e) {
			exLogging("bbsAuthorModify", e);
			return "egovframework/com/cmm/error/egovError";
		}
		
		return "vaiv/cmmn/adm/bbs/author/bbsAuthorModify.adm";
	}
	
	/**
	 * 게시판 권한 수정
	 * @param updateVO 수정VO
	 * @param model 화면 모델
	 * @return bbsAuthorModify.do
	 */
	@RequestMapping(value="updateBbsAuthor", method=RequestMethod.POST)
	public String updateBbsAuthor(@ModelAttribute("bbsAuthorVO") BbsAuthorVO updateVO
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			bbsAuthorService.updateBbsAuthor(updateVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
		} catch(SQLException e) {
			exLogging("updateBbsAuthor", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.update"));
		}

		return "redirect:/cmmn/adm/bbs/author/bbsAuthorModify.do?bbsId="+updateVO.getBbsId();
	}
	
	
}
