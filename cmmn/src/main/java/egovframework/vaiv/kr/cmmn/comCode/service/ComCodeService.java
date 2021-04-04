package egovframework.vaiv.kr.cmmn.comCode.service;

import java.sql.SQLException;
import java.util.List;

/**
 * 공통코드 관리 : 공통코드 관리에 대한 인터페이스 정의
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
public interface ComCodeService {
	
	/**
	 * 공통코드 목록 조회
	 * @param vo 검색VO
	 * @return List of ComCodeVO 공통코드 목록
	 * @throws SQLException
	 */
	public List<ComCodeVO> selectComCodeList(ComCodeVO vo) throws SQLException;
	
	/**
	 * 공통코드 목록 조회
	 * @param groupCode 그룹코드
	 * @return List of ComCodeVO 공통코드 목록
	 * @throws SQLException
	 */
	public List<ComCodeVO> selectComCodeList(String groupCode) throws SQLException;
	
	/**
	 * 공통코드 목록 총 갯수 조회
	 * @param vo 공통코드VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	public int selectComCodeListTotCnt(ComCodeVO vo) throws SQLException;
	
	/**
	 * 공통코드 중복 체크
	 * @param code 코드
	 * @return int 중복코드 갯수
	 * @throws SQLException
	 */
	public int checkCodeDplct(String code) throws SQLException;
	
	/**
	 * 입력된 정보로 공통코드 수정
	 * @param vo 공통코드VO
	 * @throws SQLException
	 */
	public void updateComCode(ComCodeVO vo) throws SQLException;
	
	/**
	 * 입력된 정보로 공통코드 삭제
	 * @param vo 공통코드VO
	 * @throws SQLException
	 */
	public void deleteComCode(ComCodeVO vo) throws SQLException;
	
	/**
	 * 입력된 정보의 공통코드 등록
	 * @param vo 공통코드VO
	 * @throws SQLException
	 */
	public void insertComCode(ComCodeVO vo) throws SQLException;
	
	/**
	 * 공통코드 상세 조회
	 * @param vo 공통코드VO
	 * @return ComCodeVO 공통코드VO
	 * @throws SQLException
	 */
	public ComCodeVO selectComCode(ComCodeVO vo) throws SQLException;
	
	/**
	 * 공통코드 상세 조회
	 * @param code 코드
	 * @return ComCodeVO 공통코드VO
	 * @throws SQLException
	 */
	public ComCodeVO selectComCode(String code) throws SQLException;
	
	
	
}
