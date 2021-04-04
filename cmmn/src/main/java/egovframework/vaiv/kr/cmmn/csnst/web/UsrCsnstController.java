package egovframework.vaiv.kr.cmmn.csnst.web;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.utl.sim.service.EgovClntInfo;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.csnst.service.CsnstService;
import egovframework.vaiv.kr.cmmn.csnst.service.CsnstVO;

/**
 * 만족도 조사 관리 / 사용자 : 만족도 조사 관리에 대한 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/csnst/")
public class UsrCsnstController extends Loggable {
	/* 만족도조사 서비스 선언 */
	@Resource(name="CsnstService")
	private CsnstService csnstService;
	
	/**
	 * 입력된 정보로 만족도 조사 등록 (Ajax)
	 * @param insertVO 등록VO
	 * @param request HttpServletRequest
	 * @param model 화면 모델
	 * @return jsonView
	 */
	@RequestMapping(value="insertCsnstAjax", method=RequestMethod.POST)
	public String insertCsnstAjax(@ModelAttribute("csnstVO") CsnstVO insertVO
			, HttpServletRequest request
			, ModelMap model) {
		
		try {
			insertVO.setConectIp(EgovClntInfo.getClntIP(request));
			int cnt = csnstService.insertCsnst(insertVO);
			if(cnt > 0) {
				model.addAttribute("result", "success");
			} else {
				model.addAttribute("result", "fail");
			}
		} catch (SQLException e) {
			exLogging("insertCsnstAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
	
}
