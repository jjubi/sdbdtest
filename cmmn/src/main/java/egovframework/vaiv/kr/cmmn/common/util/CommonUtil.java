package egovframework.vaiv.kr.cmmn.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.security.config.SecurityConfig;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.vaiv.kr.cmmn.bbs.author.service.BbsAuthorService;
import egovframework.vaiv.kr.cmmn.bbs.author.service.BbsAuthorVO;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;
import egovframework.vaiv.kr.cmmn.comCode.service.ComCodeService;
import egovframework.vaiv.kr.cmmn.comCode.service.ComCodeVO;
import egovframework.vaiv.kr.cmmn.sys.cmptn.service.SysCmptnService;
import egovframework.vaiv.kr.cmmn.sys.cmptn.service.SysCmptnVO;
import egovframework.vaiv.kr.cmmn.sys.excp.service.SysExcpVO;
import egovframework.vaiv.kr.cmmn.sys.excp.service.impl.SysExcpDAO;

/**
 * 공통 유틸 관리 : 공통 유틸 관리에 대한 요청을 처리하는 Util
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
public class CommonUtil {
	/**
	 * 게시판 권한 체크 함수
	 * @param bbsId 게시판 ID
	 * @param checkType 권한 타입
	 * @return true / false
	 */
	public static boolean checkBbsAuthor(String bbsId, String checkType) {
		boolean resultBool = false;
		try {
			BbsAuthorVO authVO = getBbsAuthor(bbsId);
			
			switch (checkType) {
				case "nttRedng": resultBool = isUserBbsAuthor(authVO.getNttRedngAuthor()); break;
				case "nttRegist": resultBool = isUserBbsAuthor(authVO.getNttRegistAuthor()); break;
				case "nttUpdt": resultBool = isUserBbsAuthor(authVO.getNttUpdtAuthor()); break;
				case "nttDelete": resultBool = isUserBbsAuthor(authVO.getNttDeleteAuthor()); break;
				case "nttFileUp": resultBool = isUserBbsAuthor(authVO.getNttFileUploadAuthor()); break;
				case "nttFileDown": resultBool = isUserBbsAuthor(authVO.getNttFileDwldAuthor()); break;
				case "cmtRedng": resultBool = isUserBbsAuthor(authVO.getCmtRedngAuthor()); break;
				case "cmtRegist": resultBool = isUserBbsAuthor(authVO.getCmtRegistAuthor()); break;
				case "cmtUpdt": resultBool = isUserBbsAuthor(authVO.getCmtUpdtAuthor()); break;
				case "cmtDelete": resultBool = isUserBbsAuthor(authVO.getCmtDeleteAuthor()); break;
				case "answerRedng": resultBool = isUserBbsAuthor(authVO.getAnswerRedngAuthor()); break;
				case "answerRegist": resultBool = isUserBbsAuthor(authVO.getAnswerRegistAuthor()); break;
				case "answerUpdt": resultBool = isUserBbsAuthor(authVO.getAnswerUpdtAuthor()); break;
				case "answerDelete": resultBool = isUserBbsAuthor(authVO.getAnswerDeleteAuthor()); break;
				case "secretUse": resultBool = isUserBbsAuthor(authVO.getSecretUseAuthor()); break;
				case "noticeUse": resultBool = isUserBbsAuthor(authVO.getNoticeUseAuthor()); break;
				case "lcIndictUse": resultBool = isUserBbsAuthor(authVO.getLcIndictUseAuthor()); break;
				case "popupUse": resultBool = isUserBbsAuthor(authVO.getPopupUseAuthor()); break;
				default: break;
			}
			
		} catch (SQLException e) {
			new Loggable().exLogging("checkBbsAuthor", e);
		}
		
		return resultBool;
	}
	
	/**
	 * 게시판 권한 목록 받아오기
	 * @param bbsId 게시판 ID
	 * @return BbsAuthorVO 게시판 권한VO
	 * @throws SQLException
	 */
	private static BbsAuthorVO getBbsAuthor(String bbsId) throws SQLException{
		BbsAuthorService bbsAuthorService = (BbsAuthorService) getSpringBean("BbsAuthorService");
		
		SearchVO searchVO = new SearchVO();
		searchVO.setBbsId(bbsId);
		BbsAuthorVO authVO = bbsAuthorService.selectBbsAuthor(searchVO);
		return authVO;
	}
	
	/**
	 * 사용자 게시판 권한 체크
	 * @param nttAuthor 권한
	 * @return true / false
	 */
	private static boolean isUserBbsAuthor(String nttAuthor) {
		List<String> userAuthorList =  EgovUserDetailsHelper.getAuthorities();
		if(userAuthorList.contains(nttAuthor)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Bean 객체 가져오기
	 * @param beanName Bean 이름
	 * @return Object 객체
	 */
	public static Object getSpringBean(String beanName){
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		return context.getBean(beanName);
	}
	
	/**
	 * 현재 사용자 권한 가져오기
	 * @return String 권한
	 */
	public static String getUserAuth() {
		SecurityConfig securityConfigBean = (SecurityConfig) getSpringBean("securityConfig");
		
		String returnAuth = "";
		List<String> userRole = null;
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		if(user != null) {
			JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) getSpringBean("egov.dataSource"));
			userRole = jdbcTemplate.query(securityConfigBean.getJdbcAuthoritiesByUsernameQuery(),
					new String[] { user.getId() }, new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					String roleName = rs.getString(2);

					return roleName;
				}
			});
		} else {
			returnAuth = "ROLE_ANONYMOUS";
		}
		
		if(userRole != null) {
			returnAuth = userRole.get(0);
		}
		
		return returnAuth;
	}
	
	/**
	 * 기간 날짜 체크 (yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param map stDate, edDate를 포함한 map
	 * @param maxPeriodDays 기간 최대 날짜
	 * @return
	 */
	public static Boolean periodDateCheck(EgovMap map, int maxPeriodDays) {
		boolean returnBool = false;
		String stDate = map.get("stDate") == null ? "" : map.get("stDate").toString();
		String edDate = map.get("edDate") == null ? "" : map.get("edDate").toString();
		if(!"".equals(stDate) && !"".equals(edDate)) {
			if(EgovDateUtil.checkDate(stDate) && EgovDateUtil.checkDate(edDate)) {
				if(maxPeriodDays == 0) {
					returnBool = true;
				} else {
					int diffDays = EgovDateUtil.getDaysDiff(stDate, edDate);
					if(!(diffDays > maxPeriodDays || diffDays < 0)) {
						returnBool = true;
					}
				}
			} 
		} else {
			returnBool = true;
		}
		
		return returnBool;
	}
	
	
	/**
	 * 예외 처리 catch Exception에서 예외 등록
	 * static 메소드이므로 CommonUtil.insertSysExcpLog 이와 같은 형태로 호출한다.
	 * 예시 -> CommonUtil.insertSysExcpLog(e, this.getClass().toString(), Thread.currentThread().getStackTrace()[1].getMethodName());
	 * @param e Exception 변수
	 * @param ExceptionClass 예외 발생 class 명
	 * @param ExceptionMethod 예외 발생 Method 명
	 */
	public static void insertSysExcpLog(Exception e, String ExceptionClass, String ExceptionMethod) {
		try { 
			SysExcpVO sysExcpVO = new SysExcpVO();
			sysExcpVO.setExceptionClass(ExceptionClass);
			sysExcpVO.setExceptionMethod(ExceptionMethod);
			String exceptionNm = "";
			if(e.getCause().getCause()!=null) {
				int subNum = e.getCause().getCause().toString().lastIndexOf(".")+1;
				int subNum2 = e.getCause().getCause().toString().indexOf("Exception");
				if(subNum2 != -1) {
					subNum2 = subNum2 + 9;
					exceptionNm = e.getCause().getCause().toString().substring(subNum, subNum2);
				}else {
					exceptionNm = e.getCause().getCause().toString().substring(subNum);
				}
			}else {
				int subNum = e.getCause().toString().lastIndexOf(".")+1;
				int subNum2 = e.getCause().toString().indexOf("Exception");
				if(subNum2 != -1) {
					subNum2 = subNum2 + 9;
					exceptionNm = e.getCause().toString().substring(subNum, subNum2);
				}else {
					exceptionNm = e.getCause().toString().substring(subNum);
				}
			}
			sysExcpVO.setExceptionNm(exceptionNm);
			
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			sysExcpVO.setExceptionContent(exceptionAsString);
			
			SysExcpDAO sysExcpDAOStatic = (SysExcpDAO) getSpringBean("SysExcpDAO");
			sysExcpDAOStatic.insertSysExcp(sysExcpVO);
		}catch(SQLException sqlE) {
			
		}
	}
	
	/**
	 * 그룹코드로 그룹코드명 조회
	 * @param comCode
	 * @return 
	 * @throws SQLException
	 */
	public static String getComCodeNm(String comCode) throws SQLException {
		ComCodeService comCodeService = (ComCodeService) getSpringBean("ComCodeService");
		
		ComCodeVO vo = comCodeService.selectComCode(comCode);
		
		return vo.getCodeNm();
	}
	
	/**
	 * 그룹코드로 그룹코드값 조회
	 * @param comCode
	 * @return 
	 * @throws SQLException
	 */
	public static String getComCodeValue(String comCode) throws SQLException {
		ComCodeService comCodeService = (ComCodeService) getSpringBean("ComCodeService");
		
		ComCodeVO vo = comCodeService.selectComCode(comCode);
		
		return vo.getCodeValue();
	}
	
	/**
	 * 그룹코드로 코드목록 조회
	 * @param comCode
	 * @return 
	 * @throws SQLException
	 */
	public static List<ComCodeVO> getComCodeList(String groupcode) throws SQLException {
		ComCodeService comCodeService = (ComCodeService) getSpringBean("ComCodeService");
		
		List<ComCodeVO> list = comCodeService.selectComCodeList(groupcode);
		
		return list;
	}
	
	/**
	 * 시스템 구성 정보 코드로 value 조회
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	public static String getSysCmptnValue(String code) throws SQLException {
		SysCmptnService sysCmptnService = (SysCmptnService) getSpringBean("SysCmptnService");
		
		String sysCmptnValue = sysCmptnService.selectSysCmptnValueToCode(code);
		
		return sysCmptnValue;
	}
	
	/**
	 * list to map
	 * @param list
	 * @return
	 */
	public static <T> List<Map<String, Object>> convertListToMap(Collection<T> list){
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for(T ele : list) {
			Map<String,Object> resultMap = new HashMap<String,Object>();
			Field[] fieldList = ele.getClass().getDeclaredFields();
			if (fieldList != null && fieldList.length > 0) {
				try {
					for (int i = 0; i < fieldList.length; i++) {
						String curInsName = fieldList[i].getName();
						Field field = ele.getClass().getDeclaredField(curInsName);
						field.setAccessible(true);
						Object targetValue = field.get(ele);
						resultMap.put(curInsName, targetValue);
					}
					resultList.add(resultMap);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return resultList;
	}
	
	public static String clobToString(Clob clob) throws IOException, SQLException {
		StringBuffer strOut = new StringBuffer();
		String str = "";
		BufferedReader br = new BufferedReader(clob.getCharacterStream());
		while((str = br.readLine()) != null) {
			strOut.append(str);
		}
		return strOut.toString();
	}
	
	/**
	 * request안에 있는 cookie값 map으로 가져오기
	 * @param request
	 * @return
	 */
	public static Map<String, String> getCookiesToMap(HttpServletRequest request){
		Map<String, String> returnMap = new HashMap<String, String>();
		
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for(Cookie ck : cookies) {
				returnMap.put(ck.getName(), ck.getValue());
			}
		}
		
		return returnMap;
	}
	
}
