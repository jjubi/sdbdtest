package egovframework.vaiv.kr.cmmn.perm.service;

import java.sql.Date;

import egovframework.com.cmm.ComDefaultVO;

/**
 * IP 접근 관리 : IP 접근 정보 객체
 * @category 공통
 * @author jeon
 * @since 2021-01-19
 * @version v1.0
 * @see
 * <pre>
 *  ******************************************
 *  수정 이력
 *  
 *  수정일                  수정자                 수정내용
 *  ------------------------------------------
 *  2021.01.19    jeon           최초 등록
 * 
 * 
 *  ******************************************
 *  Copyright 2020 VAIV Co.
 *  All rights reserved
 * </pre>
 */
public class IpCtrlVO extends ComDefaultVO{
	
	private String permIpId;  // IP id
	private String connectIp;  // 접속 IP
	private String permAt;    // 허용 여부
	private String deleteAt;  // 삭제 여부
	private String registId;  // 등록자
	private Date registDe;    // 등록 날짜
	private String updtId;    // 수정자
	private Date updtDe;      // 수정날짜
	private boolean checkIpBoolean; // ip 중복 확인
	
	public String getPermIpId() {
		return permIpId;
	}
	public void setPermIpId(String permIpId) {
		this.permIpId = permIpId;
	}
	public String getConnectIp() {
		return connectIp;
	}
	public void setConnectIp(String conectIp) {
		this.connectIp = conectIp;
	}
	public String getPermAt() {
		return permAt;
	}
	public void setPermAt(String permAt) {
		this.permAt = permAt;
	}
	public String getDeleteAt() {
		return deleteAt;
	}
	public void setDeleteAt(String deleteAt) {
		this.deleteAt = deleteAt;
	}
	public String getRegistId() {
		return registId;
	}
	public void setRegistId(String registId) {
		this.registId = registId;
	}
	public Date getRegistDe() {
		return registDe;
	}
	public void setRegistDe(Date registDe) {
		this.registDe = registDe;
	}
	public String getUpdtId() {
		return updtId;
	}
	public void setUpdtId(String updtId) {
		this.updtId = updtId;
	}
	public Date getUpdtDe() {
		return updtDe;
	}
	public void setUpdtDe(Date updtDe) {
		this.updtDe = updtDe;
	}
	public boolean isCheckIpBoolean() {
		return checkIpBoolean;
	}
	public void setCheckIpBoolean(boolean checkIpBoolean) {
		this.checkIpBoolean = checkIpBoolean;
	}
	
	

}
