package egovframework.vaiv.kr.cmmn.common.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.log.lgm.service.EgovSysLogService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.vaiv.kr.cmmn.common.service.ComUtlService;
import egovframework.vaiv.kr.cmmn.common.util.GoogleOTPAuth;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.sys.cmptn.service.SysCmptnService;

/**
 * 공통 유틸 뷰 관리 : 공통 유틸 뷰 관리에 대한 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/com/cuc/")
public class ComUtlController extends Loggable {
	
	/* 시스템 구성 서비스 선언 */
	@Resource(name="SysCmptnService")
	private SysCmptnService sysCmptnService;
	
	@Resource(name="EgovSysLogService")
	private EgovSysLogService sysLogService;
	
	@Resource(name="ComUtlService")
	private ComUtlService comUtlService;
	
	@Resource(name="GoogleOTPAuth")
	private GoogleOTPAuth googleOTPAuth;
	
	/**
	 * 지도 표출
	 * @param request HttpServletRequest 
	 * @param model 화면 모델
	 * @return mapView
	 */
	@RequestMapping(value="mapView")
	public String mapView(HttpServletRequest request
			, ModelMap model) {
		
		try {
			String kakaoAPIKey = sysCmptnService.selectSysCmptnValueToCode("KAKAO_JAVASCRIPT_API");
			String lat = request.getParameter("lat");
			String lng = request.getParameter("lng");
			String addr = request.getParameter("addr");
			String listNum = request.getParameter("listNum");
			
			model.addAttribute("kakaoAPIKey", kakaoAPIKey);
			model.addAttribute("lat", lat);
			model.addAttribute("lng", lng);
			model.addAttribute("addr", addr);
			if(listNum != null && !"".equals(listNum)) {
				model.addAttribute("listNum", listNum);
			}
		} catch (SQLException e) {
			exLogging("mapView", e);
		}
		
		
		return "vaiv/cmmn/com/cuc/mapView";
	}
	
	/**
	 * 시스템 구성 코드 가져오기 (Ajax)
	 * @param code 코드
	 * @param model 화면 모델
	 * @return jsonView
	 * @throws SQLException
	 */
	@RequestMapping(value="selectSysCmptnValueToCodeAjax")
	public String selectSysCmptnValueToCodeAjax(@RequestParam String code
			, ModelMap model) throws SQLException {
		model.addAttribute("cmptnValue", sysCmptnService.selectSysCmptnValueToCode(code));
		return "jsonView";
	}
	
	/**
	 * 시스템 로그 전체보기 페이지로 이동
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value="selectSysLogAllListView")
	public String selectSysLogAllListView(ModelMap model) throws SQLException {
		return "egovframework/com/sym/log/lgm/EgovSysLogAllList";
	}
	
	/**
	 * 시스템 로그 전체 목록 가져오기 (Ajax)
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="selectSysLogAllListAjax")
	public String selectSysLogAllListAjax(ModelMap model) throws Exception {
		
		model.addAttribute("resultList", sysLogService.selectSysLogAllList());
		
		return "jsonView";
	}
	
	/**
	 * 시스템 로그 통계 데이터 전체 목록 가져오기 (Ajax)
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="selectSysLogSummaryListAjax")
	public String selectSysLogSummaryListAjax(ModelMap model) throws Exception {
		
		model.addAttribute("resultList", sysLogService.selectSysLogSummaryList());	
		
		return "jsonView";
	}
	
	
	/**
	 * SNS 공유 목록 표출
	 * @param request HttpServletRequest
	 * @param model 화면 모델
	 * @return socialShare
	 */
	@RequestMapping(value="socialShareView")
	public String socialShareView(HttpServletRequest request,
			ModelMap model) {
		
		String kakaoAPIKey;
		try {
			kakaoAPIKey = sysCmptnService.selectSysCmptnValueToCode("KAKAO_JAVASCRIPT_API");
			model.addAttribute("kakaoAPIKey", kakaoAPIKey);
			
			String nttId = request.getParameter("nttId");
			if(nttId != null) {
				model.addAttribute("shareNttId", nttId);
			}
			
		} catch (SQLException e) {
			exLogging("socialShareView", e);
		}
		
		return "vaiv/cmmn/com/cuc/socialShare";
	}
	
	/**
	 * slickGrid Data 가져오기
	 * @param paramMap
	 * @param model
	 * @return
	 */
	@PostMapping(value="getSlickGridData")
	public String getSlickGridData(@RequestBody EgovMap paramMap
			, ModelMap model) {
		
		model.addAttribute("resultList", comUtlService.selectSlickGridData(paramMap));
		
		return "jsonView";
	}
	
	/**
	 * 도로명 주소 이용하여 위/경도 값 받아오는 ajax
	 * @param address 도로명주소
	 * @param model 응답 data return시킬 model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getGeoCoord")
	public String getGeoCoord(@RequestParam String address
			, ModelMap model) throws Exception{
		HttpURLConnection connection = null;
      
		String addressSearchurl = "http://api.vworld.kr/req/address?"
								+ "service=address"
								+ "&request=GetCoord"
								+ "&type=ROAD"
								+ "&address=" + URLEncoder.encode(address,"UTF-8")
								+ "&key=6C37422A-3B55-3858-B49B-498CB1DB5385";
  
		// 요청 URL 생성
		URL url = new URL(addressSearchurl);
		connection = (HttpURLConnection) url.openConnection();
		
		//요청 방식
		connection.setRequestMethod("GET");
//		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		System.out.println("Response code : " + connection.getResponseCode());
		BufferedReader rd;
		
		if(connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		}else {
			rd = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
		}
		
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = rd.readLine()) != null) {
			sb.append(line);
		}
		
		rd.close();
		connection.disconnect();
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject)jsonParser.parse(sb.toString());
	    
		model.addAttribute("data", jsonObject.get("response"));

		return "jsonView";
	}
	
	/**
	 * vworld 지도 표출
	 * @param request HttpServletRequest 
	 * @param model 화면 모델
	 * @return mapView
	 */
	@RequestMapping(value="vworldMapView")
	public String vworldMapView(HttpServletRequest request
			, ModelMap model) {
		try {
			String vworldAPIKey = sysCmptnService.selectSysCmptnValueToCode("VWORLD_JAVASCRIPT_API");
			String lat = request.getParameter("lat");
			String lng = request.getParameter("lng");
			String addr = request.getParameter("addr");
			String listNum = request.getParameter("listNum");
			
			model.addAttribute("vworldAPIKey", vworldAPIKey);
			model.addAttribute("lat", lat);
			model.addAttribute("lng", lng);
			model.addAttribute("addr", addr);
			if(listNum != null && !"".equals(listNum)) {
				model.addAttribute("listNum", listNum);
			}
		} catch (SQLException e) {
			exLogging("mapView", e);
		}
		
		return "vaiv/cmmn/com/cuc/vworldMapView";
	}

	/**
	 * 구글 OTP 인증코드 입력 화면으로 이동
	 * @param model 화면 모델
	 * @return
	 */
	@RequestMapping(value="getGoogleOTPAuthKey")
	public String getGoogleOTPAuthKey(ModelMap model) {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
//		String[] emailArr = user.getEmail().split("@");
//		String emailId = emailArr[0];
//		String emailHost = emailArr[1];
//		
//		HashMap<String, String> returnMap = googleOTPAuth.keyGenerate(emailId, emailHost);
		
//		String mbtlnum = user.getMbtlnum();
		
		String otpKeyStr = "CMS_GOOGLE_OTP_KEY_"+user.getId();
		HashMap<String, String> returnMap = googleOTPAuth.keyGenerate(otpKeyStr);
		
		model.addAttribute("authKey", returnMap);
		
		return "vaiv/cmmn/com/cuc/googleAuth";
	}
	
	/**
	 * OTP 인증 코드 확인 (Ajax)
	 * @param param 인증코드를 담은 param
	 * @param model 화면 모델
	 * @return
	 */
	@RequestMapping(value="checkGoogleOTPAuthCodeAjax")
	public String checkGoogleOTPAuthCode(@RequestBody EgovMap param
			, ModelMap model) {
		String userCode = (String) param.get("userCode");
		String encodedKey = (String) param.get("encodedKey");
		long user_code = Integer.parseInt(userCode);
		long l = new Date().getTime();
		long ll = l / 30000;
		
		boolean check_code = false;
		
		try {
			check_code = googleOTPAuth.check_code(encodedKey, user_code, ll);
			if(check_code) {
				model.addAttribute("result", "success");
			} else {
				model.addAttribute("result", "fail");
			}
		} catch (InvalidKeyException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return "jsonView";
	}
	
	/**
	 * jsp 페이지를 가져오는 (Ajax)
	 * @param param pageNm을 담은 param
	 * @param model 화면 모델
	 * @return
	 */
	@RequestMapping(value="getJSPPageAjax")
	public String getPageAjax(@RequestBody EgovMap param
			, ModelMap model) {
		String pageNm = (String) param.get("pageNm");
		
		return "vaiv/cmmn/com/loadPage/"+pageNm;
	}
	
	/**
	 * cclTy에 따른 ccl View 페이지 출력
	 * @param request cclTy를 담은 parameter
	 * @param model 화면 모델
	 * @return
	 */
	@RequestMapping(value="cclView")
	public String cclView(HttpServletRequest request,
			ModelMap model) {
		String cclTy = request.getParameter("cclTy");
		if(cclTy != null) {
			model.addAttribute("cclTy", cclTy);
		}
		String registNm = request.getParameter("registNm");
		if(registNm != null) {
			model.addAttribute("registNm", registNm);
		}
		String cntntsNm = request.getParameter("cntntsNm");
		if(cntntsNm != null) {
			model.addAttribute("cntntsNm", cntntsNm);
		}
		
		return "vaiv/cmmn/com/cuc/cclView";
	}
	
	/**
	 * koglTy에 따른 공공누리 View 페이지 출력
	 * @param request koglTy를 담은 parameter
	 * @param model 화면 모델
	 * @return
	 */
	@RequestMapping(value="koglView")
	public String koglView(HttpServletRequest request,
			ModelMap model) {
		String koglTy = request.getParameter("koglTy");
		if(koglTy != null) {
			model.addAttribute("koglTy", koglTy);
		}
		String registNm = request.getParameter("registNm");
		if(registNm != null) {
			model.addAttribute("registNm", registNm);
		}
		String cntntsNm = request.getParameter("cntntsNm");
		if(cntntsNm != null) {
			model.addAttribute("cntntsNm", cntntsNm);
		}
		
		return "vaiv/cmmn/com/cuc/koglView";
	}

}
