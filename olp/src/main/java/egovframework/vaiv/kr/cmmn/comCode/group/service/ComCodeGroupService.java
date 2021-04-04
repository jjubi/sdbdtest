package egovframework.vaiv.kr.cmmn.comCode.group.service;

import java.sql.SQLException;
import java.util.List;


/**
 * 공통코드 그룹 관리 : 공통코드 그룹 관리에 대한 인터페이스 정의
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
public interface ComCodeGroupService {
	/**
	 * 공통코드 그룹 목록 조회
	 * @param vo 검색VO
	 * @return List of ComCodeGroupVO 공통코드 그룹 목록
	 * @throws SQLException
	 */
	public List<ComCodeGroupVO> selectComCodeGroupList(ComCodeGroupVO vo) throws SQLException;
	
	/**
	 * 공통코드 그룹 목록 총 갯수 조회
	 * @param vo 검색VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	public int selectComCodeGroupListTotCnt(ComCodeGroupVO vo) throws SQLException;
	
	/**
	 * 입력된 정보의 공통코드 그룹 등록
	 * @param vo 공통코드 그룹 VO
	 * @throws SQLException
	 */
	public void insertComCodeGroup(ComCodeGroupVO vo) throws SQLException;
	
	/**
	 * 그룹코드 중복 체크
	 * @param groupCode 그룹코드
	 * @return int 중복 갯수
	 * @throws SQLException
	 */
	public int checkGroupCodeDplct(String groupCode) throws SQLException;
	
	/**
	 * 공통코드 그룹 상세 조회
	 * @param vo 검색VO
	 * @return ComCodeGroupVO 공통코드 그룹VO
	 * @throws SQLException
	 */
	public ComCodeGroupVO selectComCodeGroup(ComCodeGroupVO vo) throws SQLException;
	
	/**
	 * 입력된 정보의 공통코드 그룹 수정
	 * @param vo 공통코드 그룹 VO
	 * @throws SQLException
	 */
	public void updateComCodeGroup(ComCodeGroupVO vo) throws SQLException;
	
	/**
	 * 입력된 정보의 공통코드 그룹 삭제
	 * @param vo 공통코드 그룹 VO
	 * @throws SQLException
	 */
	public void deleteComCodeGroup(ComCodeGroupVO vo) throws SQLException;
}
