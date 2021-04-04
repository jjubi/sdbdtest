package egovframework.vaiv.kr.cmmn.data.vsltn.web;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.data.vsltn.service.DataVsltnCnrsVO;
import egovframework.vaiv.kr.cmmn.data.vsltn.service.DataVsltnService;

/**
 * 데이터 시각화 관리 / 관리자 : 데이터 시각화에 대한 요청을 처리하는 Controller
 * @category 공통
 * @author jo
 * @since 2021-03-22
 * @version v1.0
 * @see
 * <pre>
 *  ******************************************
 *  수정 이력
 *  
 *  수정일                  수정자                 수정내용
 *  ------------------------------------------
 *  2021.03.22    jo           최초 등록
 * 
 * 
 *  ******************************************
 *  Copyright 2020 VAIV Co.
 *  All rights reserved
 * </pre>
 */

@Controller
@RequestMapping("/cmmn/adm/data/vsltn/")
public class DataVsltnController extends Loggable{
	@Resource(name="DataVsltnService")
	private DataVsltnService dataVsltnService;
	
	/**
	 * data 시각화 페이지로 이동
	 * @return view
	 */
	@RequestMapping(value="dataVsltnMain")
	public String dataVsltnMain() {
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		return "vaiv/cmmn/adm/data/vsltn/dataVsltnMain.adm";
	}
	
	/**
	 * 데이터 시각화 공유 URL 생성
	 * @param createVO 공유 URL VO
	 * @param multiRequest 파일 Request
	 * @param model 화면 모델
	 * @return jsonView
	 */
	@RequestMapping(value="createDataVsltnCnrsAjax", method=RequestMethod.POST)
	public String createDataVsltnCnrs(DataVsltnCnrsVO createVO
			, MultipartHttpServletRequest multiRequest
			, ModelMap model) {
		
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		String vsltnUrl = "/cmmn/data/vsltn/dataVsltnView.do?key=";
		
		try {
			String vsltnUrlKey = dataVsltnService.insertDataVsltnCnrs(createVO, multiRequest);
			
			vsltnUrl += vsltnUrlKey;
			model.addAttribute("cnrsUrl", vsltnUrl);
			model.addAttribute("result", "success");
			
		} catch (SQLException e) {
			exLogging("createDataVsltnCnrs", e);
			model.addAttribute("result", "fail");
		} catch (NoSuchAlgorithmException e) {
			exLogging("createDataVsltnCnrs", e);
			model.addAttribute("result", "fail");
		}
		
		
		return "jsonView";
	}
	
}
