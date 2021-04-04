package egovframework.vaiv.kr.cmmn.sys.cmptn.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.vaiv.kr.cmmn.sys.cmptn.service.SysCmptnVO;

/**
 * 시스템구성정보 관리 / 관리자 : 시스템구성정보 관리에 대한 데이터 접근 클래스 정의
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
@Repository("SysCmptnDAO")
public class SysCmptnDAO extends EgovComAbstractDAO{

	/**
	 * 시스템 구성 정보 목록 데이터 조회
	 * @param vo SysCmptnVO
	 * @return List of SysCmptnVO
	 * @throws SQLException
	 * */
	public List<SysCmptnVO> selectSysCmptnList(SysCmptnVO vo) throws SQLException {
		return selectList("selectSysCmptnList", vo);
	}
	
	/**
	 * 시스템 구성 정보 목록 총 갯수 데이터 조회
	 * @param vo SysCmptnVO
	 * @return int 총 갯수
	 * @throws SQLException
	 * */
	public int selectSysCmptnListTotCnt(SysCmptnVO vo) throws SQLException {
		return selectOne("selectSysCmptnListTotCnt", vo);
	}
	
	/**
	 * 구성 코드 중복 데이터 체크
	 * @param code 구성코드
	 * @return int 중복코드 갯수
	 * @throws SQLException
	 * */
	public int checkSysCmptnCodeDplct(String code) throws SQLException {
		return selectOne("checkSysCmptnCodeDplct", code);
	}
	
	/**
	 * 시스템 구성 정보 저장
	 * @param vo SysCmptnVO
	 * @throws SQLException
	 * */
	public void insertSysCmptn(SysCmptnVO vo) throws SQLException {
		insert("insertSysCmptn", vo);
	}
	
	/**
	 * 시스템 구성 상세 정보 조회
	 * @param vo SysCmptnVO
	 * @return SysCmptnVO 
	 * @throws SQLException
	 * */
	public SysCmptnVO selectSysCmptn(SysCmptnVO vo) throws SQLException {
		return selectOne("selectSysCmptn", vo);
	}
	
	/**
	 * 시스템 구성 정보 수정
	 * @param vo SysCmptnVO
	 * @throws SQLException
	 * */
	public void updateSysCmptn(SysCmptnVO vo) throws SQLException {
		update("updateSysCmptn", vo);
	}
	
	/**
	 * 시스템 구성 정보 삭제
	 * @param vo SysCmptnVO
	 * @throws SQLException
	 * */
	public void deleteSysCmptn(SysCmptnVO vo) throws SQLException {
		delete("deleteSysCmptn", vo);
	}
}
