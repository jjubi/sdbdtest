/*
* 파 일 명 : UsrCntntsController.java
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
package egovframework.vaiv.kr.cmmn.cntnts.web;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.util.EgovWebUtil;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.cntnts.service.CntntsService;
import egovframework.vaiv.kr.cmmn.cntnts.service.CntntsVO;

/**
*  : 사용자 기능 - 컨텐츠
* @author : jo
* @since : 2020-12-31
* @version : v1.0
*/
@Controller
@RequestMapping("cmmn/usr/cntnts/")
public class UsrCntntsController extends Loggable {
	/* 컨텐츠 서비스 선언 */
	@Resource(name="CntntsService")
	private CntntsService cntntsService;
	
	/**
	 * @method 설명 : 사용자 > 등록한 컨텐츠 뷰 (사용자 템플릿)
	 * @param cntntsId(컨텐츠id), cntntsCode(컨텐츠코드), request, model
	 * @return String(View)
	 */
	@RequestMapping(value="{cntntsCode}/cntntsView{cntntsId}")
	public String cntntsView(@PathVariable("cntntsId") String cntntsId
			, @PathVariable("cntntsCode") String cntntsCode
			, ModelMap model) {
		
		//컨텐츠 가져오기
		CntntsVO searchVO = new CntntsVO();
		searchVO.setCntntsId(cntntsId);
		searchVO.setCntntsCode(cntntsCode);
		
		try {
			CntntsVO viewVO = cntntsService.selectCntnts(searchVO);
			viewVO.setCntntsCn(EgovWebUtil.nonClearXSSMinimum(viewVO.getCntntsCn()));
			model.addAttribute("viewCntntsVO", viewVO);
		} catch (SQLException e) {
			exLogging("cntntsView", e);
		}
		
		return "vaiv/cmmn/usr/cntnts/cntntsView.usr";
	}
}
