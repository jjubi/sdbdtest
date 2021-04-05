package egovframework.vaiv.kr.cmmn.bbs.ntt.web;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.vaiv.kr.cmmn.common.util.CommonUtil;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.bbs.author.service.BbsAuthorService;
import egovframework.vaiv.kr.cmmn.bbs.author.service.BbsAuthorVO;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;
import egovframework.vaiv.kr.cmmn.bbs.ntt.cmt.service.CmtService;
import egovframework.vaiv.kr.cmmn.bbs.ntt.cmt.service.CmtVO;
import egovframework.vaiv.kr.cmmn.bbs.ntt.service.NttService;
import egovframework.vaiv.kr.cmmn.bbs.ntt.service.NttVO;
import egovframework.vaiv.kr.cmmn.bbs.optn.service.BbsOptnService;
import egovframework.vaiv.kr.cmmn.bbs.optn.service.BbsOptnVO;
import egovframework.vaiv.kr.cmmn.bbs.service.BbsService;
import egovframework.vaiv.kr.cmmn.bbs.service.BbsVO;
import egovframework.vaiv.kr.cmmn.bbs.ty.service.BbsTyService;
import egovframework.vaiv.kr.cmmn.bbs.ty.service.BbsTyVO;
import egovframework.vaiv.kr.cmmn.sys.cmptn.service.SysCmptnService;

/**
 * 게시물 관리 / 관리자 : 게시물 관리에 대한 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/adm/bbs/ntt/")
public class NttController extends Loggable{
	
	/* 게시물 관리 서비스 선언 */
	@Resource(name="NttService")
	private NttService nttService;
	
	/* 게시판 관리 서비스 선언 */
	@Resource(name="BbsService")
	private BbsService bbsService;
	
	/* 게시판 유형 서비스 선언 */
	@Resource(name="BbsTyService")
	private BbsTyService bbsTyService;
	
	/* 게시판 권한 서비스 선언 */
	@Resource(name="BbsAuthorService")
	private BbsAuthorService bbsAuthorService;
	
	/* 게시판 옵션 서비스 선언 */
	@Resource(name="BbsOptnService")
	private BbsOptnService bbsOptnService;
	
	/* 댓글 관리 서비스 선언 */
	@Resource(name="CmtService")
	private CmtService cmtService;
	
	/* 시스템 구성 서비스 선언 */
	@Resource(name="SysCmptnService")
	private SysCmptnService sysCmptnService;
	
	/* 메시지 서비스 선언 */
	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;
	
	/* bean Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * 게시판 ID로 게시판 정보 가져오기 (게시판정보, 게시판유형정보, 권한정보, 옵션정보)
	 * @param bbsId 게시판 ID
	 * @return Map 게시판 정보를 담은 Map
	 * @throws SQLException 
	 */
	private Map<String, Object> getBbsInfotoBbsId(String bbsId) throws SQLException {
		Map<String, Object> returnMap = new HashMap<>();
		SearchVO searchVO = new SearchVO();
		searchVO.setBbsId(bbsId);
		BbsVO bbsVO = bbsService.selectBbs(searchVO);
		returnMap.put("bbsVO", bbsVO);
		
		searchVO.setBbsTyId(bbsVO.getBbsTyId());
		BbsTyVO bbsTyVO = bbsTyService.selectBbsTy(searchVO);
		returnMap.put("bbsTyVO", bbsTyVO);
		returnMap.put("bbsTyCode", bbsTyVO.getBbsTyCode());
	
		BbsAuthorVO bbsAuthorVO = bbsAuthorService.selectBbsAuthor(searchVO);
		returnMap.put("bbsAuthorVO", bbsAuthorVO);
		
		BbsOptnVO bbsOptnVO = bbsOptnService.selectBbsOptn(searchVO);
		returnMap.put("bbsOptnVO", bbsOptnVO);
		
		return returnMap;
	}
	
	/**
	 * 게시물 목록을 관리하는 페이지 (목록 표출)
	 * @param searchVO 검색VO
	 * @param bbsId 게시판 ID (pathVariable)
	 * @param model 화면 모델
	 * @return nttList
	 */
	@RequestMapping(value="{bbsId}/nttList")
	public String nttList(@ModelAttribute("searchVO") SearchVO searchVO
			, @PathVariable("bbsId") String bbsId
			, ModelMap model) {

		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		//게시판 유형 코드
		String bbsTyCode = "";
				
		try {
		    //게시판 정보, 옵션, 권한 가져오기
			Map<String, Object> bbsInfoMap = getBbsInfotoBbsId(bbsId);
			model.addAttribute("bbsVO", bbsInfoMap.get("bbsVO"));
			model.addAttribute("bbsTyVO", bbsInfoMap.get("bbsTyVO"));
			bbsTyCode = (String) bbsInfoMap.get("bbsTyCode");
			model.addAttribute("bbsAuthorVO", bbsInfoMap.get("bbsAuthorVO"));
			BbsOptnVO bbsOptnVO = (BbsOptnVO) bbsInfoMap.get("bbsOptnVO");
			model.addAttribute("bbsOptnVO", bbsOptnVO);
			
			//게시글 조회 권한 확인
			if(!CommonUtil.checkBbsAuthor(bbsId, "nttRedng")) {
				return "redirect:/sec/ram/accessDenied.do";
			}
			
			//paging 
	    	PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
			paginationInfo.setPageSize(searchVO.getPageSize());
	
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			searchVO.setBbsId(bbsId);
			
			if("Y".equals(bbsOptnVO.getNoticeUseAt())) {
				//공지글 목록 가져오기 (최근 5개의 공지)
				SearchVO notiSearchVO = new SearchVO();
				notiSearchVO.setBbsId(bbsId);
				notiSearchVO.setFirstIndex(0);
				notiSearchVO.setRecordCountPerPage(5);
				notiSearchVO.setSearchCondition("5");
				notiSearchVO.setSearchKeyword("Y");
				List<NttVO> noticeNttList = nttService.selectNttList(notiSearchVO);
				model.addAttribute("noticeNttList", noticeNttList);
			}
			
			//게시글 목록
			List<NttVO> nttList = nttService.selectNttList(searchVO);
			model.addAttribute("nttList", nttList);
			
			//게시글 총 갯수
			int totCnt = nttService.selectNttListTotCnt(searchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			
	        model.addAttribute("paginationInfo", paginationInfo);
			
	        if(searchVO.getNttId() != null && !"".equals(searchVO.getNttId())) {
	        	model.addAttribute("nttId", searchVO.getNttId());
	        }
	        
			model.addAttribute("bbsId", bbsId);
			model.addAttribute("bbsTyCode", bbsTyCode);
		} catch (SQLException e) {
			exLogging("nttList", e);
		}
		
		return "vaiv/cmmn/adm/bbs/tmpl/"+bbsTyCode+"/nttList.adm";
	}
	
	/**
	 * 게시물 등록 페이지로 이동
	 * @param searchVO 검색VO
	 * @param nttVO 게시물 VO
	 * @param bbsId 게시판 ID
	 * @param model 화면 모델
	 * @return nttRegist
	 */
	@RequestMapping(value="{bbsId}/nttRegist", method=RequestMethod.GET)
	public String nttRegist(@ModelAttribute("searchVO") SearchVO searchVO
			, @ModelAttribute("nttVO") NttVO nttVO
			, @PathVariable("bbsId") String bbsId
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		String bbsTyCode = "";
		
		try {
		
			//게시판 정보, 옵션, 권한 가져오기
			Map<String, Object> bbsInfoMap = getBbsInfotoBbsId(bbsId);
			model.addAttribute("bbsVO", bbsInfoMap.get("bbsVO"));
			model.addAttribute("bbsTyVO", bbsInfoMap.get("bbsTyVO"));
			bbsTyCode = (String) bbsInfoMap.get("bbsTyCode");
			model.addAttribute("bbsAuthorVO", bbsInfoMap.get("bbsAuthorVO"));
			model.addAttribute("bbsOptnVO", bbsInfoMap.get("bbsOptnVO"));
			
			//게시글 등록 권한 확인
			if(!CommonUtil.checkBbsAuthor(bbsId, "nttRegist")) {
				return "redirect:/sec/ram/accessDenied.do";
			}
			
			model.addAttribute("bbsId", bbsId);
			model.addAttribute("bbsTyCode", bbsTyCode);
		} catch (SQLException e) {
			exLogging("nttRegist", e);
		}
		
		return "vaiv/cmmn/adm/bbs/tmpl/"+bbsTyCode+"/nttRegist.adm";
	}
	
	/**
	 * 답글 게시물 등록 페이지로 이동
	 * @param searchVO 검색VO
	 * @param nttAnswerVO 답글 게시물VO
	 * @param bbsId 게시판ID
	 * @param model 화면 모델
	 * @return nttAnswerRegist
	 */
	@RequestMapping(value="{bbsId}/nttAnswerRegist", method=RequestMethod.GET)
	public String nttAnswerRegist(@ModelAttribute("searchVO") SearchVO searchVO
			, @ModelAttribute("nttAnswerVO") NttVO nttAnswerVO
			, @PathVariable("bbsId") String bbsId
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		String bbsTyCode = "";
		
		try {
		
			//게시판 정보, 옵션, 권한 가져오기
			Map<String, Object> bbsInfoMap = getBbsInfotoBbsId(bbsId);
			model.addAttribute("bbsVO", bbsInfoMap.get("bbsVO"));
			model.addAttribute("bbsTyVO", bbsInfoMap.get("bbsTyVO"));
			bbsTyCode = (String) bbsInfoMap.get("bbsTyCode");
			model.addAttribute("bbsAuthorVO", bbsInfoMap.get("bbsAuthorVO"));
			model.addAttribute("bbsOptnVO", bbsInfoMap.get("bbsOptnVO"));
			
			//게시글 답글 등록 권한 확인
			if(!CommonUtil.checkBbsAuthor(bbsId, "answerRegist")) {
				return "redirect:/sec/ram/accessDenied.do";
			}
			
			//부모 게시글 정보
			//게시글 상세
			NttVO nttVO = nttService.selectNtt(searchVO);
			model.addAttribute("nttVO", nttVO);
			
			model.addAttribute("bbsId", bbsId);
			model.addAttribute("bbsTyCode", bbsTyCode);
		} catch (SQLException e) {
			exLogging("nttAnswerRegist", e);
		}
		
		
		return "vaiv/cmmn/adm/bbs/tmpl/"+bbsTyCode+"/nttAnswerRegist.adm";
	}
	
	/**
	 * 입력한 정보로 게시물 등록
	 * @param searchVO 검색VO
	 * @param bbsId 게시판ID
	 * @param insertVO 등록VO
	 * @param multiReq Multipart Request
	 * @param bindingResult BindingResult
	 * @param model 화면 모델
	 * @return nttList.do
	 */
	@RequestMapping(value="{bbsId}/insertNtt", method=RequestMethod.POST)
	public String insertNtt(@ModelAttribute("searchVO") SearchVO searchVO
			, @PathVariable("bbsId") String bbsId
			, @ModelAttribute("nttVO") NttVO insertVO
			, MultipartHttpServletRequest multiReq
			, BindingResult bindingResult
			, ModelMap model) {
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		beanValidator.validate(insertVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			String bbsTyCode = multiReq.getParameter("bbsTyCode");
			if(insertVO.getNttDp() == null || "".equals(insertVO.getNttDp())) {
				return "vaiv/cmmn/adm/bbs/tmpl/"+bbsTyCode+"/nttRegist.adm";
			} else {
				return "vaiv/cmmn/adm/bbs/tmpl/"+bbsTyCode+"/nttAnswerRegist.adm";
			}
		} else {
			
			try {
				if(insertVO.getNttDp() == null || "".equals(insertVO.getNttDp())) {
					if(!CommonUtil.checkBbsAuthor(bbsId, "nttRegist")) {
						return "redirect:/sec/ram/accessDenied.do";
					}
				} else {
					if(!CommonUtil.checkBbsAuthor(bbsId, "answerRegist")) {
						return "redirect:/sec/ram/accessDenied.do";
					}
				}
				insertVO.setBbsId(bbsId);
				nttService.insertNtt(insertVO, multiReq);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			} catch (SQLException e) {
				exLogging("insertNtt", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.insert"));
			}
			
			return "redirect:/cmmn/adm/bbs/ntt/"+bbsId+"/nttList.do";
		}
	}
	
	/**
	 * 게시물 상세 조회 페이지로 이동
	 * @param searchVO 검색VO
	 * @param bbsId 게시판ID
	 * @param model 화면 모델
	 * @return nttDetail
	 */
	@RequestMapping(value="{bbsId}/nttDetail")
	public String nttDetail(@ModelAttribute("searchVO") SearchVO searchVO
			, @PathVariable("bbsId") String bbsId
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		//게시판 유형 코드
		String bbsTyCode = "";
				
		try {
			//게시판 정보, 옵션, 권한 가져오기
			Map<String, Object> bbsInfoMap = getBbsInfotoBbsId(bbsId);
			model.addAttribute("bbsVO", bbsInfoMap.get("bbsVO"));
			model.addAttribute("bbsTyVO", bbsInfoMap.get("bbsTyVO"));
			bbsTyCode = (String) bbsInfoMap.get("bbsTyCode");
			model.addAttribute("bbsAuthorVO", bbsInfoMap.get("bbsAuthorVO"));
			model.addAttribute("bbsOptnVO", bbsInfoMap.get("bbsOptnVO"));
			
			//게시글 조회 권한 확인
			if(!CommonUtil.checkBbsAuthor(bbsId, "nttRedng")) {
				return "redirect:/sec/ram/accessDenied.do";
			}
			
			//게시글 조회수 올리기
			nttService.incrementNttRdcnt(searchVO);
			
			//게시글 상세
			NttVO nttVO = nttService.selectNtt(searchVO);
			model.addAttribute("nttVO", nttVO);
			
			if(nttVO.getNttDp() != null && !"1".equals(nttVO.getNttDp())) {
				SearchVO tmpVO = new SearchVO();
				tmpVO.setNttId(nttVO.getUpperNttId());
				NttVO parentNttVO = nttService.selectNtt(tmpVO);
				model.addAttribute("parentNttVO", parentNttVO);
			}
			
			model.addAttribute("bbsId", bbsId);
			model.addAttribute("bbsTyCode", bbsTyCode);
		} catch (SQLException e) {
			exLogging("nttDetail", e);
		}
		
		return "vaiv/cmmn/adm/bbs/tmpl/"+bbsTyCode+"/nttDetail.adm";
	}
	
	/**
	 * 게시물 수정 페이지로 이동
	 * @param searchVO 검색VO
	 * @param bbsId 게시판ID
	 * @param model 화면 모델
	 * @return nttModify
	 */
	@RequestMapping(value="{bbsId}/nttModify")
	public String nttModify(@ModelAttribute("searchVO") SearchVO searchVO
			, @PathVariable("bbsId") String bbsId
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		String bbsTyCode = "";
		
		try {
			//게시판 정보, 옵션, 권한 가져오기
			Map<String, Object> bbsInfoMap = getBbsInfotoBbsId(bbsId);
			model.addAttribute("bbsVO", bbsInfoMap.get("bbsVO"));
			model.addAttribute("bbsTyVO", bbsInfoMap.get("bbsTyVO"));
			bbsTyCode = (String) bbsInfoMap.get("bbsTyCode");
			model.addAttribute("bbsAuthorVO", bbsInfoMap.get("bbsAuthorVO"));
			model.addAttribute("bbsOptnVO", bbsInfoMap.get("bbsOptnVO"));
			
			//게시글 등록 권한 확인
			if(!CommonUtil.checkBbsAuthor(bbsId, "nttUpdt")) {
				return "redirect:/sec/ram/accessDenied.do";
			}
			
			//게시글 상세
			NttVO nttVO = nttService.selectNtt(searchVO);
			model.addAttribute("nttVO", nttVO);
			
			model.addAttribute("bbsId", bbsId);
			model.addAttribute("bbsTyCode", bbsTyCode);
		} catch (SQLException e) {
			exLogging("nttModify", e);
		}
		
		
		return "vaiv/cmmn/adm/bbs/tmpl/"+bbsTyCode+"/nttModify.adm";
	}
	
	/**
	 * 입력된 정보로 게시물을 수정
	 * @param searchVO 검색VO
	 * @param updateVO 수정VO
	 * @param bbsId 게시판ID
	 * @param multiReq Multipart Request
	 * @param bindingResult BindingResult
	 * @param model 화면 모델
	 * @return nttList.do
	 */
	@RequestMapping(value="{bbsId}/updateNtt", method=RequestMethod.POST)
	public String updateNtt(@ModelAttribute("searchVO") SearchVO searchVO
			, @ModelAttribute("nttVO") NttVO updateVO
			, @PathVariable("bbsId") String bbsId
			, MultipartHttpServletRequest multiReq
			, BindingResult bindingResult
			, ModelMap model) {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
	
		beanValidator.validate(updateVO, bindingResult); //validation 수행
		
		if(bindingResult.hasErrors()) {
			String bbsTyCode = multiReq.getParameter("bbsTyCode");
			return "vaiv/cmmn/adm/bbs/tmpl/"+bbsTyCode+"/nttModify.adm";
		} else {
			
			try {
				if(!CommonUtil.checkBbsAuthor(bbsId, "nttUpdt")) {
					return "redirect:/sec/ram/accessDenied.do";
				}
				updateVO.setBbsId(bbsId);
				nttService.updateNtt(updateVO, multiReq);
				model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
			} catch (SQLException e) {
				exLogging("updateNtt", e);
				model.addAttribute("message", egovMessageSource.getMessage("fail.common.update"));
			}
			
			return "redirect:/cmmn/adm/bbs/ntt/"+bbsId+"/nttList.do";
		}
	}
	
	/**
	 * 입력된 정보로 게시물 삭제
	 * @param searchVO 검색VO
	 * @param bbsId 게시판ID
	 * @param deleteVO 삭제VO
	 * @param model 화면 모델
	 * @return nttList.do
	 */
	@RequestMapping(value="{bbsId}/deleteNtt", method=RequestMethod.POST)
	public String deleteNtt(@ModelAttribute("searchVO") SearchVO searchVO
			, @PathVariable("bbsId") String bbsId
			, @ModelAttribute("nttVO") NttVO deleteVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			if(!CommonUtil.checkBbsAuthor(bbsId, "nttDelete")) {
				return "redirect:/sec/ram/accessDenied.do";
			}
			nttService.deleteNtt(deleteVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		} catch (SQLException e) {
			exLogging("deleteNtt", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.delete"));
		}
		
		return "redirect:/cmmn/adm/bbs/ntt/"+bbsId+"/nttList.do";
	}
	
	/**
	 * 게시물 옵션 정보 템플릿 불러오기
	 * @param optnNm 옵션명
	 * @param bbsId 게시판 ID
	 * @param formType 폼 유형(update, regist, view)
	 * @param model 화면 모델
	 * @param request HttpServletRequest
	 * @return 옵션명에 맞는 페이지
	 */
	@RequestMapping(value="{bbsId}/{formtype}/nttOptn{optnNm}")
	public String nttOptnView(@PathVariable("optnNm") String optnNm
			, @PathVariable("bbsId") String bbsId
			, @PathVariable("formtype") String formType
			, ModelMap model
			, HttpServletRequest request) {
		
		String bbsTyCode = request.getParameter("bbsTyCode");
		if(bbsTyCode == null) {
			model.addAttribute("bbsTyCode", "default");
		} else {
			model.addAttribute("bbsTyCode", bbsTyCode);
		}
		
		String optnNmLC = optnNm.toLowerCase();
		
		if("atchfile".equals(optnNmLC)) {
			String atchFilePermExtsn = request.getParameter("atchFilePermExtsn");
			String atchFilePermMxmmCnt = request.getParameter("atchFilePermMxmmCnt");
			if(atchFilePermExtsn != null) {
				model.addAttribute("atchFilePermExtsn", atchFilePermExtsn);
			}
			if(atchFilePermMxmmCnt != null) {
				model.addAttribute("atchFilePermMxmmCnt", atchFilePermMxmmCnt);
			}
		}
		
		if("lotdspl".equals(optnNmLC)) {
			//카카오 키 가져오기
			try {
				String kakaoAPIKey = sysCmptnService.selectSysCmptnValueToCode("KAKAO_JAVASCRIPT_API");
				model.addAttribute("kakaoAPIKey", kakaoAPIKey);
			} catch (SQLException e) {
				exLogging("optionView", e);
			}
		}
		
		if("cmtview".equals(optnNmLC)) {
			String nttId = request.getParameter("nttId");
			SearchVO sCmtVO = new SearchVO();
			sCmtVO.setNttId(nttId);
			sCmtVO.setPagingYn("N");
			try {
				
				List<CmtVO> cmtList = cmtService.selectCmtList(sCmtVO);
				model.addAttribute("cmtList", cmtList);
				model.addAttribute("cmtNttId", nttId);
				model.addAttribute("cmtBbsTyCode", bbsTyCode);
			} catch (SQLException e) {
				exLogging("optionView", e);
			}
		}
		
		model.addAttribute("formType", formType);
									
		return "vaiv/cmmn/adm/bbs/tmpl/optn/"+optnNmLC;
	}
	
	/**
	 * 게시글 비밀번호 확인 (Ajax)
	 * @param checkVO 체크VO 
	 * @param model 화면 모델
	 * @return jsonView
	 * @throws Exception
	 */
	@RequestMapping(value="checkNttSecretKeyAjax", method=RequestMethod.POST)
	public String checkNttSecretKeyAjax(NttVO checkVO
			, ModelMap model) throws Exception {

		try{
			//게시물 조회
			SearchVO vo = new SearchVO();
			vo.setNttId(String.valueOf(checkVO.getNttId()));
			NttVO selectVO = nttService.selectNtt(vo);
			
			if(selectVO.getSecretKey().equals(checkVO.getSecretKey())) {
				model.addAttribute("result", "success");
			} else {
				model.addAttribute("result", "fail");
			}
		} catch(SQLException e){
			exLogging("checkNttSecretKeyAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
	
	/**
	 * 게시판 권한 목록 조회 (Ajax)
	 * @deprecated 권한 관련 스크립트 처리 삭제로 인한 사용 안함
	 * @param request HttpServletRequest
	 * @param model 화면 모델
	 * @return jsonView
	 * @throws Exception
	 */
	@RequestMapping(value="loadBbsAuthorListAjax", method=RequestMethod.POST)
	public String loadBbsAuthorListAjax(HttpServletRequest request
			, ModelMap model) throws Exception {
		
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("message", "Not Login");
			model.addAttribute("result", "fail");
			return "jsonView";
		}

		try{			
			//게시판 권한 가져오기
			SearchVO vo = new SearchVO();
			vo.setBbsId(request.getParameter("bbsId"));
			BbsAuthorVO bbsAuthorVO = bbsAuthorService.selectBbsAuthor(vo);
			
			model.addAttribute("result", "success");
			Map authMap = new HashMap<>();
			authMap.put("bbsAuthorVO", bbsAuthorVO);
			authMap.put("authorities", EgovUserDetailsHelper.getAuthorities());
			model.addAttribute("authMap", authMap);
		} catch(SQLException e){
			exLogging("loadBbsAuthorListAjax", e);
			model.addAttribute("result", "fail");
		}
		
		return "jsonView";
	}
	
	
}
