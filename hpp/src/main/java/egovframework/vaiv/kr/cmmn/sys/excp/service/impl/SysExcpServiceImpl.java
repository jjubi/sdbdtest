package egovframework.vaiv.kr.cmmn.sys.excp.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import egovframework.vaiv.kr.cmmn.common.util.CommonUtil;
import egovframework.vaiv.kr.cmmn.sys.excp.service.SysExcpService;
import egovframework.vaiv.kr.cmmn.sys.excp.service.SysExcpVO;

/**
 * Exception 관리 / 관리자 : Exception 관리에 대한 비지니스 클래스 정의
 * @category 공통
 * @author kmk
 * @since 2021-01-29
 * @version : v1.0
 * @see
 * <pre>
 *  ******************************************
 *  수정 이력
 *  
 *  수정일                  수정자                 수정내용
 *  ------------------------------------------
 *  2021.01.29    kmk           최초 등록
 * 
 * 
 *  ******************************************
 *  Copyright 2020 VAIV Co.
 *  All rights reserved
 * </pre>
 */
@Service("SysExcpService")
public class SysExcpServiceImpl implements SysExcpService{

	@Resource(name="SysExcpDAO")
	private SysExcpDAO sysExcpDAO;
	
	/**
	 * 예외 처리 목록에대한 데이터 조회
	 * @param : vo(SysExcpVO)
	 * @return : List of SysExcpVO
	 * @throws : SQLException
	 * */
	@Override
	public List<SysExcpVO> selectSysExcpList(SysExcpVO vo) throws SQLException {
		return sysExcpDAO.selectSysExcpList(vo);
	}

	/**
	 * 예외 처리 목록 총 개수 데이터 조회
	 * @param : vo(SysExcpVO)
	 * @return : int
	 * @throws : SQLException
	 * */
	@Override
	public int selectSysExcpListTotCnt(SysExcpVO vo) throws SQLException {
		return sysExcpDAO.selectSysExcpListTotCnt(vo);
	}

	/**
	 * 예외 발생 데이터 저장
	 * @param vo(SysExcpVO)
	 * @throws SQLException
	 */
	public void insertSysExcp(SysExcpVO vo) throws SQLException {
		sysExcpDAO.insertSysExcp(vo);
	}
	
	/**
	 * 예외 처리 정보 데이터 조회
	 * @param : vo(SysExcpVO),
	 * @return : SysExcpVO
	 * @throws : SQLException
	 * */
	@Override
	public SysExcpVO selectSysExcp(SysExcpVO vo) throws SQLException {
		return sysExcpDAO.selectSysExcp(vo);
	}
}
