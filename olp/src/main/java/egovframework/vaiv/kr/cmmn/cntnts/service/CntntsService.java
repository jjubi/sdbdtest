package egovframework.vaiv.kr.cmmn.cntnts.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 컨텐츠 관리 / 관리자 : 컨텐츠 관리에 대한 인터페이스 정의
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
public interface CntntsService {
	/**
	 * 입력된 정보의 컨텐츠 등록
	 * @param vo 컨텐츠 VO
	 * @throws SQLException
	 * */
	public void insertCntnts(CntntsVO vo, MultipartHttpServletRequest multiReq) throws SQLException;
	
	/**
	 * 입력된 정보로 컨텐츠 수정
	 * @param vo 컨텐츠VO
	 * @throws SQLException
	 * */
	public void updateCntnts(CntntsVO vo, MultipartHttpServletRequest multiReq) throws SQLException;
	
	/**
	 * 입력된 정보의 컨텐츠 삭제
	 * @param vo 컨텐츠VO
	 * @throws SQLException
	 * */
	public void deleteCntnts(CntntsVO vo) throws SQLException;
	
	/**
	 * 컨텐츠 Rollback
	 * @param vo 컨텐츠히스토리VO
	 * @throws SQLException
	 * */
	public void updateCntntsHisRollback(CntntsHisVO vo) throws SQLException;
	
	/**
	 * 컨텐츠 목록 조회
	 * @param vo 검색VO
	 * @return List of CntntsVO 컨텐츠VO 목록
	 * @throws SQLException
	 * */
	public List<CntntsVO> selectCntntsList(CntntsVO vo) throws SQLException;
	
	/**
	 * 컨텐츠 목록 총 개수 조회
	 * @param vo 검색VO
	 * @return int 총 갯수
	 * @throws SQLException
	 * */
	public int selectCntntsListTotCnt(CntntsVO vo) throws SQLException;
	
	/**
	 * 컨텐츠 히스토리 목록 조회
	 * @param vo 검색VO
	 * @return List of CntntsHisVO 컨텐츠 히스토리 목록
	 * @throws SQLException
	 * */
	public List<CntntsHisVO> selectCntntsHisList(CntntsHisVO vo) throws SQLException;
	
	/**
	 * 컨텐츠 히스토리 목록 총 개수 조회
	 * @param vo 컨텐츠 히스토리VO
	 * @return int 총 갯수
	 * @throws SQLException
	 * */
	public int selectCntntsHisListTotCnt(CntntsHisVO vo) throws SQLException;
	
	/**
	 * 컨텐츠 상세 조회
	 * @param vo 검색VO
	 * @return CntntsVO 컨텐츠VO
	 * @throws SQLException
	 * */
	public CntntsVO selectCntnts(CntntsVO vo) throws SQLException;
	
	/**
	 * 컨텐츠 히스토리 상세 조회
	 * @param vo 컨텐츠 히스토리VO
	 * @return CntntsHisVO 컨텐츠 히스토리VO
	 * @throws SQLException
	 * */
	public CntntsHisVO selectCntntsHis(CntntsHisVO vo) throws SQLException;
	
}
