package egovframework.com.sym.log.lgm.service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.common.util.CommonUtil;
import egovframework.vaiv.kr.cmmn.menu.service.MenuVO;
import egovframework.vaiv.kr.cmmn.menu.service.impl.MenuDAO;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UrlPathHelper;

/**
 * @Class Name : EgovSysLogAspect.java
 * @Description : 시스템 로그 생성을 위한 ASPECT 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.lgm)
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
public class EgovSysLogAspect {

	@Resource(name="EgovSysLogService")
	private EgovSysLogService sysLogService;
	
	/**
	 * 시스템 로그 정의
	 * 
	 * 실행순서 (적용 실행 순서 @Around -> 대상 메소드 -> @Around, @Around -> 대상 메소드(에러발생[throw]) -> @Around (catch)  
	 * @Before -> @Around (대상 메소드 수행 전) -> 대상 메소드 -> @AfterReturning -> @After(finally) -> @Around (대상 메소드 수행 후)
	 * @Before -> @Around (대상 메소드 수행 전) -> 대상 메소드 (예외가 발생) -> @AfterThrowing -> @After(finally)
	 * 
	 * 1. service 메소드 실행 후 메소드 실행 시간 등 시스템 로그 저장 (PS_R(read), PS_C(create), PS_U(update), PS_D(delete))
	 * 2. controller 메소드 실행 후 메뉴와 URI 체크 후 시스템 로그 저장 (PC_M(메뉴 구분 후 저장))
	 * */
	
	private SysLog createSysLogVO(ProceedingJoinPoint joinPoint, String processSeCode, String processTime, Throwable error) {
		SysLog returnVO = new SysLog();
		
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		String uniqId = "";
		String ip = "";
		String jobSe = "";	//업무구분코드 (일반사용자, 관리자, 기업관리자)

    	/* Authenticated  */
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(isAuthenticated.booleanValue()) {
			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			uniqId = user.getUniqId();
			ip = user.getIp();
			jobSe = user.getUserSe();
    	}

    	returnVO.setSrvcNm(className);
		returnVO.setMethodNm(methodName);
		returnVO.setProcessSeCode(processSeCode);
		returnVO.setProcessTime(processTime);
		returnVO.setRqesterId(uniqId);
		returnVO.setRqesterIp(ip);
		returnVO.setJobSeCode(jobSe);
		
		if(error != null) {
			returnVO.setErrorCo(1);
			returnVO.setErrorSe(error.getCause() == null ? "" : error.getCause().toString());
			returnVO.setErrorCode(error.getMessage());
		}
		
		//추가 데이터 넣기
		//request uri
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		String oriUri = urlPathHelper.getRequestUri(req);
		returnVO.setRequstUri(oriUri);
		if(!req.getRequestURI().endsWith(".jsp")) {
			String oriQueryStr = req.getQueryString();
			if(oriQueryStr != null && !"".equals(oriQueryStr)) {
				returnVO.setRequstUri(returnVO.getRequstUri() + "?" + oriQueryStr);
			}
		}
		//request site(domain)
		returnVO.setRequstDomn(req.getServerName() + ":" + req.getServerPort());
		
		return returnVO;
	}
	
	/**
	 * 시스템 로그정보를 생성한다.
	 * sevice Class의 insert로 시작되는 Method
	 *
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logInsert(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();
		Throwable exception = null;
		try {
			stopWatch.start();

			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			exception = e;
			throw e;
		} finally {
			stopWatch.stop();
			sysLogService.logInsertSysLog(createSysLogVO(joinPoint, "PS_C", Long.toString(stopWatch.getTotalTimeMillis()), exception));
		}

	}

	/**
	 * 시스템 로그정보를 생성한다.
	 * sevice Class의 update로 시작되는 Method
	 *
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logUpdate(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();
		Throwable exception = null;
		try {
			stopWatch.start();

			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			exception = e;
			throw e;
		} finally {
			stopWatch.stop();
			sysLogService.logInsertSysLog(createSysLogVO(joinPoint, "PS_U", Long.toString(stopWatch.getTotalTimeMillis()), exception));
		}

	}

	/**
	 * 시스템 로그정보를 생성한다.
	 * sevice Class의 delete로 시작되는 Method
	 *
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logDelete(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();
		Throwable exception = null;
		try {
			stopWatch.start();

			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			exception = e;
			throw e;
		} finally {
			stopWatch.stop();
			sysLogService.logInsertSysLog(createSysLogVO(joinPoint, "PS_D", Long.toString(stopWatch.getTotalTimeMillis()), exception));
		}

	}

	/**
	 * 시스템 로그정보를 생성한다.
	 * sevice Class의 select로 시작되는 Method
	 *
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logSelect(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();
		Throwable exception = null;
		try {
			stopWatch.start();

			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			exception = e;
			throw e;
		} finally {
			stopWatch.stop();
			sysLogService.logInsertSysLog(createSysLogVO(joinPoint, "PS_R", Long.toString(stopWatch.getTotalTimeMillis()), exception));
		}

	}
	
	/**
	 * 시스템 로그정보를 생성한다.
	 * controller Class의 Method
	 * 
	 * 메뉴 url과 비교 후  일치 시 저장 
	 *
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logWeb(ProceedingJoinPoint joinPoint) throws Throwable {
		
		StopWatch stopWatch = new StopWatch();
		Throwable exception = null;
		try {
			stopWatch.start();

			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			exception = e;
			throw e;
		} finally {
			stopWatch.stop();
			SysLog sysLog = createSysLogVO(joinPoint, "PC_M", Long.toString(stopWatch.getTotalTimeMillis()), exception);
			
			//메뉴 체크 후
			List<String> authorList = EgovUserDetailsHelper.getAuthorities();
			MenuVO selectVO = new MenuVO();
			selectVO.setUseAt("Y");
			selectVO.setSearchCondition("999");
			List<MenuVO> menuList = ((MenuDAO) CommonUtil.getSpringBean("MenuDAO")).selectMenuList(selectVO);
			for(MenuVO menu : menuList) {
				if(sysLog.getRequstUri() != null && sysLog.getRequstUri().equals(menu.getMenuUrl())) {
					if(authorList.contains(menu.getMenuAuthor())) {
						sysLog.setTrgetMenuNm(menu.getMenuNm());
					}
				}
			}
			if(sysLog.getTrgetMenuNm() != null && !"".equals(sysLog.getTrgetMenuNm())) {
				sysLogService.logInsertSysLog(sysLog);
			}
		}
	}

}
