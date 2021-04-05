package egovframework.vaiv.kr.cmmn.common.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.perm.service.IpCtrlVO;
import egovframework.vaiv.kr.cmmn.perm.service.impl.PermissionDAO;

/**
 * IP 접근 제어 Interceptor
 * @category 공통
 * @author jyb
 * @since 2020-12-31
 * @version v1.0
 * @see
 * <pre>
 *  ******************************************
 *  수정 이력
 *  
 *  수정일                  수정자                 수정내용
 *  ------------------------------------------
 *  2020.12.31    jyb           최초 등록
 * 
 * 
 *  ******************************************
 *  Copyright 2020 VAIV Co.
 *  All rights reserved
 * </pre>
 */
public class IPCtrlInterceptor implements HandlerInterceptor {
	
	@Resource(name="PermissionDAO")
	private PermissionDAO PermissionDAO;
	
	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e)
            throws Exception {
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView view){
    }
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
    	//ip 로그
    	new Loggable().logging("client ip" + request.getRemoteAddr());
    	
    	String ip = request.getRemoteAddr();
    	String ipCheckList = EgovProperties.getProperty("Globals.IPCtrl");
    	boolean ipCheck = true; //default blackList
    	
    	if(ipCheckList != null && ipCheckList.equals("blackList")) {
    	    ipCheckList = "N";
    		ipCheck = true;
    	} else if(ipCheckList != null && ipCheckList.equals("whiteList")) {
    		ipCheckList = "Y";
    		ipCheck = false;
    	}
    	
    	IpCtrlVO ipCtrlVO = new IpCtrlVO();
    	ipCtrlVO.setConnectIp(ip);
    	ipCtrlVO.setPermAt(ipCheckList);
    	ipCtrlVO.setPagingYn("N");
        int ipCnt = PermissionDAO.checkIpCtrl(ipCtrlVO); 
     	
        if(ipCnt > 0) {
        	return !ipCheck;
        } else {
        	return ipCheck;
        }
	}

}
