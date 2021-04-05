package egovframework.vaiv.kr.cmmn.sys.cmptn.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.sys.cmptn.service.SysCmptnService;
import egovframework.vaiv.kr.cmmn.sys.cmptn.service.SysCmptnVO;

/**
 * 시스템구성정보 관리 / 관리자 : 시스템구성정보 관리에 대한 비지니스 클래스 정의
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
@Service("SysCmptnService")
public class SysCmptnServiceImpl implements SysCmptnService{

	@Resource(name="SysCmptnDAO")
	private SysCmptnDAO sysCmptnDAO;
	
	/**
	 * 시스템 구성 정보 목록 조회
	 * @param vo SysCmptnVO
	 * @return List of SysCmptnVO
	 * @throws SQLException
	 * */
	@Override
	public List<SysCmptnVO> selectSysCmptnList(SysCmptnVO vo) throws SQLException {
		return sysCmptnDAO.selectSysCmptnList(vo);
	}

	/**
	 * 시스템 구성 정보 목록 총 갯수 조회
	 * @param vo SysCmptnVO
	 * @return int 총 갯수
	 * @throws SQLException
	 * */
	@Override
	public int selectSysCmptnListTotCnt(SysCmptnVO vo) throws SQLException {
		return sysCmptnDAO.selectSysCmptnListTotCnt(vo);
	}

	/**
	 * 구성 코드 중복 체크
	 * @param code 구성코드
	 * @return int 중복코드 갯수
	 * @throws SQLException
	 * */
	@Override
	public int checkSysCmptnCodeDplct(String code) throws SQLException {
		return sysCmptnDAO.checkSysCmptnCodeDplct(code);
	}

	/**
	 * 시스템 구성 정보 저장
	 * @param vo SysCmptnVO
	 * @throws SQLException
	 * */
	@Override
	public void insertSysCmptn(SysCmptnVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		vo.setRegistId(user.getUniqId());
		vo.setUpdtId(user.getUniqId());
		
		sysCmptnDAO.insertSysCmptn(vo);
	}

	/**
	 * 시스템 구성 상세 정보 조회
	 * @param vo SysCmptnVO
	 * @return SysCmptnVO 
	 * @throws SQLException
	 * */
	@Override
	public SysCmptnVO selectSysCmptn(SysCmptnVO vo) throws SQLException {
		return sysCmptnDAO.selectSysCmptn(vo);
	}

	/**
	 * 시스템 구성 정보 수정
	 * @param vo SysCmptnVO
	 * @throws SQLException
	 * */
	@Override
	public void updateSysCmptn(SysCmptnVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		vo.setUpdtId(user.getUniqId());
		
		sysCmptnDAO.updateSysCmptn(vo);
	}

	/**
	 * 시스템 구성 정보 삭제
	 * @param vo SysCmptnVO
	 * @throws SQLException
	 * */
	@Override
	public void deleteSysCmptn(SysCmptnVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		vo.setUpdtId(user.getUniqId());
		
		sysCmptnDAO.deleteSysCmptn(vo);
	}

	/**
	 * 시스템 구성 값 가져오기
	 * @param sysCmptnCode 시스템 구성 코드
	 * @return String 시스템 구성 값
	 * @throws SQLException
	 * */
	@Override
	public String selectSysCmptnValueToCode(String sysCmptnCode) throws SQLException {
		SysCmptnVO vo = new SysCmptnVO();
		vo.setSysCmptnCode(sysCmptnCode);
		vo.setUseAt("Y");
		
		vo = sysCmptnDAO.selectSysCmptn(vo);
		
		String returnValue = "";
		if(vo != null) {
			returnValue = vo.getSysCmptnValue();
		}
		
		return returnValue;
	}

}
