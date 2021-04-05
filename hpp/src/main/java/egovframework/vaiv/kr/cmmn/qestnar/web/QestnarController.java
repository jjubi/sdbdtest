package egovframework.vaiv.kr.cmmn.qestnar.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cmm.util.EgovWebUtil;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.qestnar.common.SearchVO;
import egovframework.vaiv.kr.cmmn.qestnar.qestn.service.QestnService;
import egovframework.vaiv.kr.cmmn.qestnar.qestn.service.QestnVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarResultDtlVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarResultVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarSenarioService;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarSenarioVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarService;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarTrgtVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarVO;

/**
 * 설문관리 / 관리자 : 설문관리에 대한 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/adm/qestnar/")
public class QestnarController extends Loggable{
	/* 설문 서비스 호출 */
	@Resource(name="QestnarService")
	private QestnarService qestnarService;
	
	/* 설문 문항 서비스 호출 */
	@Resource(name="QestnService")
	private QestnService qestnService;
	
	/* 설문 문항 서비스 호출 */
	@Resource(name="QestnarSenarioService")
	private QestnarSenarioService qestnarSenarioService;
	
	/* 전자정부 메시지 서비스 호출 */
	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;
	
	/* bean validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * 설문조사를 관리하는 메인 페이지 (목록 표출)
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return qestnarMain
	 */
	@RequestMapping(value="qestnarMain")
	public String qestnarMain(@ModelAttribute("searchVO") SearchVO searchVO
			, ModelMap model) {
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			//paging 
	    	PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
			paginationInfo.setPageSize(searchVO.getPageSize());
	
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			List<QestnarVO> qestnarList = qestnarService.selectQestnarList(searchVO); 
	        model.addAttribute("qestnarList", qestnarList);
	
	        int totCnt = qestnarService.selectQestnarListTotCnt(searchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			
	        model.addAttribute("paginationInfo", paginationInfo);
		} catch (SQLException e) {
			exLogging("qestnarMain", e);
			return "egovframework/com/cmm/error/egovError";
		}
		
		
		return "vaiv/cmmn/adm/qestnar/qestnarMain.adm";
	}

	/**
	 * 설문조사 등록 페이지로 이동
	 * @param searchVO 검색VO
	 * @param qestnarVO 설문VO
	 * @param model 화면 모델
	 * @return qestnarRegist
	 */
	@RequestMapping(value="qestnarRegist", method=RequestMethod.GET)
	public String qestnarRegist(@ModelAttribute("searchVO") SearchVO searchVO
			, @ModelAttribute("qestnarVO") QestnarVO qestnarVO
			, ModelMap model) {
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		return "vaiv/cmmn/adm/qestnar/qestnarRegist.adm";
	}
	
	/**
	 * 입력된 정보의 설문조사 등록
	 * @param searchVO 검색VO
	 * @param insertVO 등록VO
	 * @param bindingResult BindingResult
	 * @param model 화면 모델
	 * @return qestnarMain.do
	 */
	@RequestMapping(value="insertQestnar", method=RequestMethod.POST)
	public String insertQestnar(@ModelAttribute("searchVO") SearchVO searchVO
			, @ModelAttribute("qestnarVO") QestnarVO insertVO
			, BindingResult bindingResult
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(insertVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "vaiv/cmmn/adm/qestnar/qestnarRegist.adm";
		} else {
			
			try {
				qestnarService.insertQestnar(insertVO);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			} catch (SQLException e) {
				exLogging("insertQestnar", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));
			}
			
			return "redirect:/cmmn/adm/qestnar/qestnarMain.do";
		}
	}
	
	/**
	 * 설문조사 수정 페이지로 이동
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return qestnarModify
	 */
	@RequestMapping(value="qestnarModify")
	public String qestnarModify(@ModelAttribute("searchVO") SearchVO searchVO
			, ModelMap model) {
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			QestnarVO selectVO = qestnarService.selectQestnar(searchVO);
//			selectVO.setQestnarPrface(EgovWebUtil.nonClearXSSMinimum(selectVO.getQestnarPrface()));
//			selectVO.setQestnarCnclsn(EgovWebUtil.nonClearXSSMinimum(selectVO.getQestnarCnclsn()));
			
			model.addAttribute("qestnarVO", selectVO);
		} catch (SQLException e) {
			exLogging("qestnarModify", e);
		} 
		
		return "vaiv/cmmn/adm/qestnar/qestnarModify.adm";
	}
	
	/**
	 * 입력된 정보로 설문조사 수정
	 * @param searchVO 검색VO
	 * @param updateVO 수정VO
	 * @param bindingResult BindingResult
	 * @param model 화면 모델
	 * @return qestnarMain.do
	 */
	@RequestMapping(value="updateQestnar", method=RequestMethod.POST)
	public String updateQestnar(@ModelAttribute("searchVO") SearchVO searchVO
			, @ModelAttribute("qestnarVO") QestnarVO updateVO
			, BindingResult bindingResult
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(updateVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			return "vaiv/cmmn/adm/qestnar/qestnarModify.adm";
		} else {
			
			try {
				qestnarService.updateQestnar(updateVO);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			} catch (SQLException e) {
				exLogging("updateQestnar", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.update"));
			}
			
			return "redirect:/cmmn/adm/qestnar/qestnarMain.do";
		}
	}
	
	/**
	 * 입력된 정보로 설문조사 삭제
	 * @param searchVO 검색VO
	 * @param deleteVO 삭제VO
	 * @param model 화면 모델
	 * @return qestnarMain.do
	 */
	@RequestMapping(value="deleteQestnar", method=RequestMethod.POST)
	public String deleteQestnar(@ModelAttribute("searchVO") SearchVO searchVO
			, @ModelAttribute("qestnarVO") QestnarVO deleteVO
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			qestnarService.deleteQestnar(deleteVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		} catch (SQLException e) {
			exLogging("deleteQestnar", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));
		}
		
		return "redirect:/cmmn/adm/qestnar/qestnarMain.do";
	}
	
	/**
	 * 설문조사 대상 목록 조회 (Ajax)
	 * @param request HttpServletRequest
	 * @param model 화면 모델
	 * @return jsonView
	 */
	@RequestMapping(value="selectQestnarTrgetListAjax", method=RequestMethod.POST)
	public String selectQestnarTrgetListAjax(HttpServletRequest request
			, ModelMap model) {
		
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			return "jsonView";
		}
		
		logging("설문조사 대상 목록 가져오기 Ajax");
		
		try{
			//서비스 호출하여 리스트 가져오기
			String nowPageIndex = request.getParameter("pageIndex");
			Map<String, Object> sMap = new HashMap<String, Object>();
			sMap.put("pageIndex", nowPageIndex);
			sMap.put("pagingYn", "Y");
			
			
			//paging 
	    	PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(Integer.parseInt(nowPageIndex));
			paginationInfo.setRecordCountPerPage(10);
			paginationInfo.setPageSize(10);
	
			sMap.put("firstIndex", paginationInfo.getFirstRecordIndex());
			sMap.put("lastIndex", paginationInfo.getLastRecordIndex());
			sMap.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
			
			List<EgovMap> userList = qestnarService.selectQestnarTargetList(sMap);			
				 
	        int totCnt = qestnarService.selectQestnarTargetListTotCnt(sMap);
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("resultList", userList);
			model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("result", "success");
		} catch(SQLException e){
			exLogging("selectQestnarTrgetListAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
	
	/**
	 * 설문조사 미리보기 
	 * @param request HttpServletRequest
	 * @param qestnarSeqNo 설문조사 일련번호
	 * @param viewType viewType
	 * @param qestnAnswer 문항 답변
	 * @param qestnSeqNo 문항 일련번호
	 * @param qestnarRate 문항 Rate
	 * @param model 화면 모델
	 * @return qestnView
	 */
	@RequestMapping(value="{qestnarSeqNo}/selectQestn{viewType}View{testYn}")
	public String selectQestnView(HttpServletRequest request
			, @PathVariable("qestnarSeqNo") String qestnarSeqNo
			, @PathVariable("viewType") String viewType
			, @PathVariable("testYn") String testYn
			, String qestnAnswer
			, String qestnSeqNo
			, String qestnarRate
			, String qestnarPgeNum
			, String qestnarResultSeqNo
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}

		String returnUrl = "";
		try {
			model.addAttribute("qestnarSeqNo", qestnarSeqNo);
			model.addAttribute("viewType", viewType);
			model.addAttribute("qestnarRate", qestnarRate);
			model.addAttribute("testYn", testYn);
			model.addAttribute("qestnarResultSeqNo", qestnarResultSeqNo);
			
			//설문 정보 가져가기
			SearchVO searchVO = new SearchVO();
			searchVO.setQestnarSeqNo(qestnarSeqNo);
			searchVO.setSearchCondition("5");
			searchVO.setSearchKeyword("Y");
			QestnarVO qestnarInfo = qestnarService.selectQestnar(searchVO);
			
			model.addAttribute("title", "확인");
			model.addAttribute("icon", "warning");
			if(qestnarInfo == null) {
				model.addAttribute("message", "존재하지 않는 설문입니다.");
				return "vaiv/cmmn/com/message";
			} else {
				if("".equals(qestnarInfo.getFirstQestnSeqNo())) {
					model.addAttribute("message", "설문 문항이 없습니다.");
					return "vaiv/cmmn/com/message";
				}
				
				String nowDate = EgovDateUtil.getToday();
				if(EgovDateUtil.getDaysDiff(qestnarInfo.getQestnarBgnde().replaceAll("-", ""), nowDate) < 0) {
					model.addAttribute("message", "시작되지 않은 설문입니다.");
					return "vaiv/cmmn/com/message";
				} else if(EgovDateUtil.getDaysDiff(qestnarInfo.getQestnarEndde().replaceAll("-", ""), nowDate) > 0) {
					model.addAttribute("message", "종료된 설문입니다.");
					return "vaiv/cmmn/com/message";
				}
			}
			
			LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
			//설문조사 대상 확인하기
			boolean targetBool = false;
			if("TARGET".equals(qestnarInfo.getQestnarTrget())) {
				List<QestnarTrgtVO> trgtList = qestnarInfo.getQestnarTrgetList();
				for(QestnarTrgtVO vo : trgtList) {
					if(vo.getEsntlId() != null && !"".equals(vo.getEsntlId())) {
						if(vo.getEsntlId().equals(user.getUniqId())) {
							targetBool = true;
						}
					}
				}
			} else {
				targetBool = true;
			}
			
			if(!targetBool) {
				model.addAttribute("message", "설문조사 대상이 아닙니다.");
				return "vaiv/cmmn/com/message";
			}
			
			//중복 체크하기
			if("N".equals(qestnarInfo.getQestnarDplctAt())) {
				QestnarResultVO rspSearchVO = new QestnarResultVO();
				rspSearchVO.setQestnarSeqNo(qestnarSeqNo);
				rspSearchVO.setRegistId(user.getUniqId());
				rspSearchVO.setRspnsComptAt("Y");
				QestnarResultVO trgtVO = qestnarService.checkQestnarResultTarget(rspSearchVO);
				if(trgtVO != null) {
					model.addAttribute("message", "이미 완료한 설문입니다.");
					model.addAttribute("icon", "success");
					return "vaiv/cmmn/com/message";	
				}
			}
			
			if("Prface".equals(viewType)) {
				qestnarInfo.setQestnarPrface(EgovWebUtil.nonClearXSSMinimum(qestnarInfo.getQestnarPrface()));
			} else if("Cnclsn".equals(viewType)) {
				qestnarInfo.setQestnarCnclsn(EgovWebUtil.nonClearXSSMinimum(qestnarInfo.getQestnarCnclsn()));
			}
			model.addAttribute("qestnarInfo", qestnarInfo);
			
			if(!"Y".equals(String.valueOf(model.get("testYn")))) {
				//설문 시작일 경우 마스터 저장
				if("1".equals(model.get("qestnarRate"))){	
					//미리보기가 아닐 경우 결과 마스터 저장
					QestnarResultVO iQestnarResultVO = new QestnarResultVO();
					iQestnarResultVO.setQestnarSeqNo(String.valueOf(model.get("qestnarSeqNo")));
					iQestnarResultVO.setRspnsComptAt("N");
					qestnarResultSeqNo = qestnarService.insertQestnarResult(iQestnarResultVO);
					model.addAttribute("qestnarResultSeqNo", qestnarResultSeqNo);
				} else if("end".equals(model.get("qestnarRate"))) {
					//미리보기가 아닐 경우 결과 마스터 수정
					QestnarResultVO iQestnarResultVO = new QestnarResultVO();
					iQestnarResultVO.setQestnarResultSeqNo(qestnarResultSeqNo);
					iQestnarResultVO.setRspnsComptAt("Y");
					qestnarService.updateQestnarResult(iQestnarResultVO);
				}
			}
			
			String nowQestnSeqNo = (qestnSeqNo == null ? "" : qestnSeqNo);
			
			if("Y".equals(qestnarInfo.getQestnarPgeAt())) {
				model.addAttribute("qestnarPgeNum", qestnarPgeNum);
				model.addAttribute("qestnAnswer", qestnAnswer);
				model.addAttribute("qestnList", setQestnPgeView(searchVO, nowQestnSeqNo, model));
				returnUrl = "vaiv/cmmn/adm/qestnar/qestnPgeView.adm";
			} else {
				model.addAttribute("qestnAnswer", qestnAnswer);
				model.addAttribute("qestnInfo", setQestnView(searchVO, nowQestnSeqNo, model));
				returnUrl = "vaiv/cmmn/adm/qestnar/qestnView.adm";
			}
			
			/*if("".equals(viewType)) {		//다음
				//답변 등록
				if(!"Y".equals(testYn)) {		//미리보기일경우 답변 저장 안함
					QestnarResultVO iQestnarResultVO = new QestnarResultVO();
					iQestnarResultVO.setQestnarSeqNo(qestnarSeqNo);
					iQestnarResultVO.setAnswerValue(qestnAnswer);
					iQestnarResultVO.setQestnSeqNo(nowQestnSeqNo);
					qestnarService.insertQestnarResult(iQestnarResultVO);
				}
				//시나리오 체크
				if(!"".equals(nowQestnSeqNo) && !"1".equals(qestnarRate)) {
					QestnarSenarioVO sQestnSenarioVO = new QestnarSenarioVO();
					sQestnSenarioVO.setQestnarSeqNo(qestnarSeqNo);
					sQestnSenarioVO.setDtrmnQestnSeqNo(nowQestnSeqNo);
					int nextQestnSeqNo = qestnarService.selectNextQestnSenarioCheck(qestnAnswer, sQestnSenarioVO);
					nowQestnSeqNo = String.valueOf(nextQestnSeqNo);
				} 
				
				//설문 문항 정보 가져가기
				QestnVO sQestnVO = new QestnVO();
				sQestnVO.setQestnarSeqNo(qestnarSeqNo);
				sQestnVO.setQestnSeqNo(nowQestnSeqNo);
				sQestnVO.setUseAt("Y");
				QestnVO qestnInfo = qestnarService.selectQestn(sQestnVO);
				if(!qestnInfo.getQestnOptn().equals("{}")) {
					Gson gson = new Gson();
					Map optnMap = gson.fromJson(qestnInfo.getQestnOptn(), Map.class);
					model.addAttribute("qestnOption", optnMap);
				}
				model.addAttribute("qestnInfo", qestnInfo);
			} else if("Cnclsn".equals(viewType)) {		//제출
				//답변 등록
				if(!"Y".equals(testYn)) {		//미리보기일경우 답변 저장 안함
					QestnarResultVO iQestnarResultVO = new QestnarResultVO();
					iQestnarResultVO.setQestnarSeqNo(qestnarSeqNo);
					iQestnarResultVO.setAnswerValue(qestnAnswer);
					iQestnarResultVO.setQestnSeqNo(nowQestnSeqNo);
					iQestnarResultVO.setRspnsComptAt("Y");
					iQestnarResultVO.setQestnarRate(qestnarRate);
					qestnarService.insertQestnarResult(iQestnarResultVO);
				}
			}*/
			
		} catch (SQLException e) {
			exLogging("selectQestnView", e);
		}
		
		return returnUrl;
	}	
	
	private QestnVO setQestnView(SearchVO searchVO, String nowQestnSeqNo, ModelMap model) throws SQLException {
		QestnVO returnVO = new QestnVO();
		
		if(!"Prface".equals(String.valueOf(model.get("viewType")))) {		
			if(!"Y".equals(String.valueOf(model.get("testYn"))) && !"1".equals(String.valueOf(model.get("qestnarRate")))) {
				//미리보기가 아닐 경우 답변 저장
				QestnarResultVO iQestnarResultVO = new QestnarResultVO();
				iQestnarResultVO.setQestnarSeqNo(String.valueOf(model.get("qestnarSeqNo")));
				iQestnarResultVO.setAnswerValue(String.valueOf(model.get("qestnAnswer")));
				iQestnarResultVO.setQestnarResultSeqNo(String.valueOf(model.get("qestnarResultSeqNo")));
				iQestnarResultVO.setNowQestnSeqNo(nowQestnSeqNo);
				qestnarService.insertQestnarResultDtl(iQestnarResultVO);
			}
			
			//본 설문 시 답변에 따른 시나리오 체크
			if(!"".equals(nowQestnSeqNo) && !"1".equals(model.get("qestnarRate"))) {
				searchVO.setDtrmnQestnSeqNo(nowQestnSeqNo);
				int nextQestnSeqNo = qestnarSenarioService.selectNextQestnSenarioCheck(String.valueOf(model.get("qestnAnswer")), searchVO);
				nowQestnSeqNo = String.valueOf(nextQestnSeqNo);
			}
			//다음 문항 정보 조회
			searchVO.setQestnSeqNo(nowQestnSeqNo);
			searchVO.setUseAt("Y");
			returnVO = qestnService.selectQestn(searchVO);
		} 
		
		return returnVO;
	}
	
	private List<QestnVO> setQestnPgeView(SearchVO searchVO, String nowQestnSeqNo, ModelMap model) throws SQLException {
		List<QestnVO> returnVO = new ArrayList<QestnVO>();
		
		if(!"Prface".equals(model.get("viewType"))) {		//본 설문 시 답변에 따른 시나리오 체크
			if(!"Y".equals(String.valueOf(model.get("testYn"))) && !"1".equals(String.valueOf(model.get("qestnarRate")))) {
				//미리보기가 아닐 경우 답변 저장
				QestnarResultVO iQestnarResultVO = new QestnarResultVO();
				iQestnarResultVO.setQestnarSeqNo(String.valueOf(model.get("qestnarSeqNo")));
				iQestnarResultVO.setAnswerValue(String.valueOf(model.get("qestnAnswer")));
				iQestnarResultVO.setQestnarResultSeqNo(String.valueOf(model.get("qestnarResultSeqNo")));
				iQestnarResultVO.setNowQestnSeqNo(nowQestnSeqNo);
				qestnarService.insertQestnarResultDtl(iQestnarResultVO);
			}
			//다음 페이지 문항 정보 조회			
			searchVO.setUseAt("Y");
			searchVO.setQestnPge(String.valueOf(model.get("qestnarPgeNum")));
			returnVO = qestnService.selectQestnList(searchVO);
		}
		
		return returnVO;
	}
	
	@RequestMapping(value="result/qestnarResultMain")
	public String qestnarResultMain(@ModelAttribute("searchVO") SearchVO searchVO
			, ModelMap model) throws SQLException {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		//설문 정보 가져가기
		QestnarVO qestnarInfo = qestnarService.selectQestnar(searchVO);
		model.addAttribute("qestnarInfo", qestnarInfo);
		
		//설문 문항 정보 가져가기
		List<QestnVO> qestnList = qestnService.selectQestnList(searchVO);
		model.addAttribute("qestnList", qestnList);
		
		return "vaiv/cmmn/adm/qestnar/qestnarResultMain.adm";
	}
	
	@RequestMapping(value="result/qestnarResultStat")
	public String qestnarResultStat(@RequestBody EgovMap paramMap
			, ModelMap model) throws SQLException {
		
		List<EgovMap> resultList = qestnarService.selectQestnarResultStat(paramMap);
		model.addAttribute("resultList", resultList);
		
		return "jsonView";
	}
	
}
