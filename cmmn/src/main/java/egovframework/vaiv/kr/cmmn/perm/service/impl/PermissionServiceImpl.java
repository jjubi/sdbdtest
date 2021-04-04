package egovframework.vaiv.kr.cmmn.perm.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.perm.service.IpCtrlVO;
import egovframework.vaiv.kr.cmmn.perm.service.PermissionService;

/**
 * IP 접근 관리 : IP 접근 관리에 대한 비지니스 클래스 정의
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
@Service("PermissionService")
public class PermissionServiceImpl implements PermissionService{
	
	@Resource(name="PermissionDAO")
	private PermissionDAO permissionDAO;
	
	/* IP ID 생성 서비스 선언 */
	@Resource(name="ipIdService")
    private EgovIdGnrService ipIdService;
	
	/**
	 * IP 관리 목록 조회
	 * @param vo 검색VO
	 * @return List of IpCtrlVO IP관리 목록
	 * @throws SQLException
	 */
	@Override
	public List<IpCtrlVO> selectIpCtrlList(IpCtrlVO vo) throws SQLException{
		return permissionDAO.selectIpCtrlList(vo);
	}
	
	/**
	 * IP 관리 목록 총 갯수 조회
	 * @param vo 검색VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	@Override
	public int selectIpCtrlTotCnt(IpCtrlVO vo) throws SQLException{
		return permissionDAO.selectIpCtrlTotCnt(vo);
	}
	
	/**
	 * 입력된 정보로 IP관리 저장
	 * @param vo IP관리VO
	 * @throws SQLException
	 */
	@Override
	public void insertIpCtrl(IpCtrlVO vo) throws SQLException{
		String ipId = "";
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		try {
			ipId = ipIdService.getNextStringId();
			vo.setPermIpId(ipId);
			vo.setRegistId(user.getUniqId());
			vo.setUpdtId(user.getUniqId());
		} catch (FdlException e) {
			new Loggable().exLogging("insertIpCtrl", e);
		}
		permissionDAO.insertIpCtrl(vo);
	}
	
	/**
	 * IP 정보 상세 조회
	 * @param vo 검색VO
	 * @return IpCtrlVO IP관리VO
	 * @throws SQLException
	 */
	@Override
	public IpCtrlVO selectIpCtrl(IpCtrlVO vo) throws SQLException{
		return permissionDAO.selectIpCtrl(vo);
	}
	
	/**
	 * 입력된 정보로 IP관리 수정
	 * @param vo IP관리VO
	 * @throws SQLException
	 */
	@Override
	public void updateIpCtrl(IpCtrlVO vo) throws SQLException{
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		permissionDAO.updateIpCtrl(vo);
	}
	
	/**
	 * 입력된 정보로 IP관리 삭제
	 * @param vo IP관리VO
	 * @throws SQLException
	 */
	@Override
	public void deleteIpCtrl(IpCtrlVO vo) throws SQLException{
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		permissionDAO.deleteIpCtrl(vo);
	}
	
	/**
	 * IP 중복 체크
	 * @param vo IP관리VO
	 * @return int 중복갯수
	 * @throws SQLException
	 */
	@Override
	public int checkIpCtrlDplct(IpCtrlVO vo) throws SQLException{
		return permissionDAO.checkIpCtrlDplct(vo);
	}
	

}
