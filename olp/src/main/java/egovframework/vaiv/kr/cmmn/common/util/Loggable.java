package egovframework.vaiv.kr.cmmn.common.util;

import org.apache.log4j.Logger;

public class Loggable {

	private Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 자손 컴포넌트에서 logging 메소드 이용 로그 남기기
	 * @param msg
	 */
	public void logging(String msg) {
		logger.debug(msg);
	}
	
	/**
	 * 자손 컴포넌트에서 exLogging 메소드를 사용하여 Exception로그 남기기.
	 * @param method
	 * @param e
	 */
	public void exLogging(String method, Exception e) {
		logger.debug(this.getClass().getName() + "." + method + " => " + e.getClass().getName() + "\n" +
					" 발생원인 : " + e.getMessage());
	}
}
