package egovframework.vaiv.kr.cmmn.sys.excp.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.cmmn.exception.BaseException;
import egovframework.vaiv.kr.cmmn.sys.excp.service.SysExcpService;
import egovframework.vaiv.kr.cmmn.sys.excp.service.SysExcpVO;

public class TdmsSimpleMappingExceptionResolver extends org.springframework.web.servlet.handler.SimpleMappingExceptionResolver{
	private String exceptionAttribute = DEFAULT_EXCEPTION_ATTRIBUTE;
	
	@Resource(name="SysExcpService")
	private SysExcpService sysExcpService;
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {
		
		String viewName = determineViewName(ex, request);
		if(viewName != null) {
			if(logger.isDebugEnabled()) {
				logger.debug("Ajax 호출인가?");
			}
			
			if(request.getHeader("AJAX") != null && request.getHeader("AJAX").equals("true")) {
				Integer statusCode = determineStatusCode(request, viewName);
				if(statusCode != null) {
					applyStatusCodeIfPossible(request, response, statusCode);
				}
			}
			
			HandlerMethod handlerMethod = ((HandlerMethod)handler);
			StackTraceElement[] stacks = ex.getStackTrace();
			
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();

			SysExcpVO sysExcpVO = new SysExcpVO();
			sysExcpVO.setExceptionClass(handlerMethod.getResolvedFromHandlerMethod().getBeanType().toString());
			sysExcpVO.setExceptionMethod(handlerMethod.getMethod().getName());
			String exceptionNm = "";
			if(ex.getCause().getCause()!=null) {
				int subNum = ex.getCause().getCause().toString().lastIndexOf(".")+1;
				int subNum2 = ex.getCause().getCause().toString().indexOf("Exception");
				if(subNum2 != -1) {
					subNum2 = subNum2 + 9;
					exceptionNm = ex.getCause().getCause().toString().substring(subNum, subNum2);
				}else {
					exceptionNm = ex.getCause().getCause().toString().substring(subNum);
				}
			}else {
				int subNum = ex.getCause().toString().lastIndexOf(".")+1;
				int subNum2 = ex.getCause().toString().indexOf("Exception");
				if(subNum2 != -1) {
					subNum2 = subNum2 + 9;
					exceptionNm = ex.getCause().toString().substring(subNum, subNum2);
				}else {
					exceptionNm = ex.getCause().toString().substring(subNum);
				}
			}
			sysExcpVO.setExceptionNm(exceptionNm);
			sysExcpVO.setExceptionContent(exceptionAsString);
			
			try {
				sysExcpService.insertSysExcp(sysExcpVO);
			} catch (Exception e) {
				
			}
			
			return getModelAndView(viewName, ex, request);
		}
		
		return null;
	}
	
	protected ModelAndView getModelAndView(String viewName, Exception ex, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(viewName);
		
		if(this.exceptionAttribute != null) {
			if(logger.isDebugEnabled()) {
				logger.debug("Exposing Exception as model attribute '" + this.exceptionAttribute + "'");
			}
			mv.addObject(this.exceptionAttribute, ex);
			mv.addObject("url", request.getRequestURL());
		}
		return mv;
	}
}
