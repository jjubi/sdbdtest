package egovframework.vaiv.kr.cmmn.data.vsltn.web;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
 * 데이터 시각화 관리 / 사용자 : 데이터 시각화에 대한 요청을 처리하는 Controller
 * @category 공통
 * @author jo
 * @since 2021-03-25
 * @version v1.0
 * @see
 * <pre>
 *  ******************************************
 *  수정 이력
 *  
 *  수정일                  수정자                 수정내용
 *  ------------------------------------------
 *  2021.03.25    jo           최초 등록
 * 
 * 
 *  ******************************************
 *  Copyright 2020 VAIV Co.
 *  All rights reserved
 * </pre>
 */

@Controller
@RequestMapping("/cmmn/data/vsltn/")
public class UsrDataVsltnController extends Loggable{
	@Resource(name="DataVsltnService")
	private DataVsltnService dataVsltnService;
	
	/**
	 * data 시각화 View 페이지로 이동
	 * @return view
	 * @throws SQLException 
	 */
	@RequestMapping(value="dataVsltnView")
	public String dataVsltnView(HttpServletRequest request
			, ModelMap model) throws SQLException {
		
		String vsltnKey = request.getParameter("key");
		
		DataVsltnCnrsVO searchVO = new DataVsltnCnrsVO();
		searchVO.setVsltnUrlKey(vsltnKey);
		
		searchVO = dataVsltnService.selectDataVsltnCnrs(searchVO);
		
		model.addAttribute("dataVsltnCnrsVO", searchVO);
		
		return "vaiv/cmmn/usr/data/vsltn/dataVsltnView";
	}
	
}
