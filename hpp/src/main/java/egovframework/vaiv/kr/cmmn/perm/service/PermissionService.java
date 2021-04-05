package egovframework.vaiv.kr.cmmn.perm.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * IP 접근 관리 : IP 접근 관리에 대한 인터페이스 정의
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
public interface PermissionService {

	/**
	 * IP 관리 목록 조회
	 * @param vo 검색VO
	 * @return List of IpCtrlVO IP관리 목록
	 * @throws SQLException
	 */
	public List<IpCtrlVO> selectIpCtrlList(IpCtrlVO vo) throws SQLException;
	
	/**
	 * IP 관리 목록 총 갯수 조회
	 * @param vo 검색VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	public int selectIpCtrlTotCnt(IpCtrlVO vo) throws SQLException;
	
	/**
	 * 입력된 정보로 IP관리 저장
	 * @param vo IP관리VO
	 * @throws SQLException
	 */
	public void insertIpCtrl(IpCtrlVO vo) throws SQLException;
	
	/**
	 * IP 정보 상세 조회
	 * @param vo 검색VO
	 * @return IpCtrlVO IP관리VO
	 * @throws SQLException
	 */
	public IpCtrlVO selectIpCtrl(IpCtrlVO vo) throws SQLException;
	
	/**
	 * 입력된 정보로 IP관리 수정
	 * @param vo IP관리VO
	 * @throws SQLException
	 */
	public void updateIpCtrl(IpCtrlVO vo) throws SQLException;
	
	/**
	 * 입력된 정보로 IP관리 삭제
	 * @param vo IP관리VO
	 * @throws SQLException
	 */
	public void deleteIpCtrl(IpCtrlVO vo) throws SQLException;
	
	/**
	 * IP 중복 체크
	 * @param vo IP관리VO
	 * @return int 중복갯수
	 * @throws SQLException
	 */
	public int checkIpCtrlDplct(IpCtrlVO vo) throws SQLException;
	
}
