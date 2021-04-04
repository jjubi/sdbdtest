/*
* 파 일 명 : CmtController.java
* 작성일시 : 2020.12.31
* 작 성 자 : jo
* 수정이력
*
* 수정일      수정자        수정내용
*---------------   --------------   ------------------------------------
* 2020.12.31   jo      최초등록
* 
*********************************************************************************
* Copyright 2020 VAIV Co.
* All rights reserved
*/
package egovframework.vaiv.kr.cmmn.bbs.ntt.cmt.web;

import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.common.util.CommonUtil;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;
import egovframework.vaiv.kr.cmmn.bbs.ntt.cmt.service.CmtService;
import egovframework.vaiv.kr.cmmn.bbs.ntt.cmt.service.CmtVO;
import egovframework.vaiv.kr.cmmn.bbs.ntt.service.NttService;
import egovframework.vaiv.kr.cmmn.bbs.ntt.service.NttVO;

/**
*  : 관리자 기능 - 댓글 관리
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
@Controller
@RequestMapping("/cmmn/adm/bbs/ntt/cmt/")
public class CmtController extends Loggable{
	
	/* 댓글 관리 서비스 선언 */
	@Resource(name="CmtService")
	private CmtService cmtService;
	
	/* 게시물 관리 서비스 선언 */
	@Resource(name="NttService")
	private NttService nttService;
	
	/**
	 * @method 설명 : 댓글 상세 페이지
	 * @param : request, searchVO, model
	 * @return : String(View)
	 * @exception : SQLException
	 * */
	@RequestMapping(value="cmtDetail")
	public String cmtDetail(@RequestParam Map<String, Object> paramMap
			, ModelMap model) {
		
		String bbsTyCode = "";
		try {
			bbsTyCode = (String) paramMap.get("bbsTyCode");
			String nttId = (String) paramMap.get("nttId");
			String cmtId = (String) paramMap.get("cmtId");
			SearchVO searchVO = new SearchVO();
			searchVO.setBbsTyCode(bbsTyCode);
			searchVO.setNttId(nttId);
			searchVO.setCmtId(cmtId);
			CmtVO selectVO = cmtService.selectCmt(searchVO);
			model.addAttribute("cmtVO", selectVO);
			
			NttVO nttVO = nttService.selectNtt(searchVO);
			model.addAttribute("cmtUpdateAuthor", CommonUtil.checkBbsAuthor(nttVO.getBbsId(), "cmtUpdt") ? "Y" : "N");
			model.addAttribute("cmtDeleteAuthor", CommonUtil.checkBbsAuthor(nttVO.getBbsId(), "cmtDelete") ? "Y" : "N");
			
		} catch (SQLException e) {
			exLogging("cmtDetail", e);
		}
		
		return "vaiv/cmmn/adm/bbs/tmpl/"+bbsTyCode+"/cmtDetail";
	}
	
	/**
	 * @method 설명 : 댓글 등록 페이지
	 * @param : request, searchVO, model
	 * @return : String(View)
	 * @exception : SQLException
	 * */
	@RequestMapping(value="cmtRegist")
	public String cmtRegist(HttpServletRequest request
			, ModelMap model) {
		
		String bbsTyCode = "";
		bbsTyCode = request.getParameter("bbsTyCode");
		
		return "vaiv/cmmn/adm/bbs/tmpl/"+bbsTyCode+"/cmtRegist";
	}
	
	/**
	 * @method 설명 : 댓글 저장 (Ajax)
	 * @param : request, model
	 * @return : String(View)
	 * @exception : SQLException
	 * */
	@RequestMapping(value="insertCmtAjax", method=RequestMethod.POST)
	public String insertCmtAjax(HttpServletRequest request
			, @ModelAttribute("cmtVO") CmtVO insertVO
			, ModelMap model) throws Exception {
		
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("message", "Not Login");
			model.addAttribute("result", "fail");
			return "jsonView";
		}
		
		try{			
			String nttId = insertVO.getNttId();
			SearchVO vo = new SearchVO();
			vo.setNttId(nttId);
			NttVO nttVO = nttService.selectNtt(vo);
			//댓글 등록 권한 확인
			if(!CommonUtil.checkBbsAuthor(nttVO.getBbsId(), "cmtRegist")) {
				model.addAttribute("message", "댓글 등록 권한이 없습니다.");
				model.addAttribute("result", "fail");
				return "jsonView";
			}
			
			//댓글 저장
			insertVO = cmtService.insertCmt(insertVO);
			model.addAttribute("result", "success");
		} catch(SQLException e){
			exLogging("insertCmtAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
	
	/**
	 * @method 설명 : 댓글 수정 (Ajax)
	 * @param : request, nttId(게시물id), model
	 * @return : String(View)
	 * @exception : SQLException
	 * */
	@RequestMapping(value="updateCmtAjax", method=RequestMethod.POST)
	public String updateCmtAjax(HttpServletRequest request
			, CmtVO updateVO
			, ModelMap model) throws Exception {
		
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("message", "Not Login");
			model.addAttribute("result", "fail");
			return "jsonView";
		}
		
		try{			
			//댓글 수정
			cmtService.updateCmt(updateVO);
			
			model.addAttribute("result", "success");
		} catch(SQLException e){
			exLogging("updateCmtAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}

	/**
	 * @method 설명 : 댓글 삭제 (Ajax)
	 * @param : request, model
	 * @return : String(View)
	 * @exception : SQLException
	 * */
	@RequestMapping(value="deleteCmtAjax", method=RequestMethod.POST)
	public String deleteCmtAjax(HttpServletRequest request
			, CmtVO deleteVO
			, ModelMap model) throws Exception {
		
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("message", "Not Login");
			model.addAttribute("result", "fail");
			return "jsonView";
		}
		
		try{			
			//댓글 삭제
			cmtService.deleteCmt(deleteVO);
			
			model.addAttribute("result", "success");
		} catch(SQLException e){
			exLogging("deleteCmtAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
}
