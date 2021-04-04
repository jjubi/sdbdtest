package egovframework.vaiv.kr.cmmn.prhibtWrd.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 금지단어 관리 / 관리자 : 금지단어 관리에 대한 인터페이스 정의
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
public interface PrhibtWrdService {

	/**
	 * 금지단어 목록 조회
	 * @param vo 금지단어VO
	 * @return List of PrhibtWrdVO 금지단어VO 목록
	 * @throws SQLException
	 */
	public List<PrhibtWrdVO> selectPrhibtWrdList(PrhibtWrdVO vo) throws SQLException;
	
	/**
	 * 금지단어 목록  총 갯수 조회
	 * @param vo 금지단어VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	public int selectPrhibtWrdListTotCnt(PrhibtWrdVO vo) throws SQLException;
	
	/**
	 * 금지단어 중복 확인
	 * @param vo 금지단어VO
	 * @return boolean 중복여부(true/false)
	 * @throws SQLException
	 */
	public boolean checkDupPrhibtWrd(PrhibtWrdVO vo) throws SQLException;
	
	/**
	 * 금지단어 포함 여부 확인
	 * @param checkStr 입력단어
	 * @return List of String 포함된 금지단어 목록
	 * @throws SQLException
	 */
	public List<String> checkContainsPrhibtWrd(String checkStr) throws SQLException;
	
	/**
	 * 입력한 금지단어 등록
	 * @param vo 금지단어VO
	 * @return int 등록된 금지단어 수
	 * @throws SQLException
	 */
	public int insertPrhibtWrd(PrhibtWrdVO vo) throws SQLException;
	
	/**
	 * 금지단어 상세 조회
	 * @param vo 금지단어VO
	 * @return PrhibtWrdVO 금지단어VO
	 * @throws SQLException
	 */
	public PrhibtWrdVO selectPrhibtWrd(PrhibtWrdVO vo) throws SQLException;

	/**
	 * 입력한 금지단어 수정
	 * @param vo 금지단어VO
	 * @return int 수정된 금지단어 수
	 * @throws SQLException
	 */
	public int updatePrhibtWrd(PrhibtWrdVO vo) throws SQLException;

	/**
	 * 입력한 금지단어 삭제
	 * @param vo 금지단어VO
	 * @return int 삭제된 금지단어 수
	 * @throws SQLException
	 */
	public int deletePrhibtWrd(PrhibtWrdVO vo) throws SQLException;

	/**
	 * 금지단어 엑셀 업로드 등록
	 * @param mulRequest Multipart Request
	 * @return List of String 등록못한 금지단어 목록
	 * @throws SQLException
	 */
	public List<String> insertPrhibtWrdExcelData(MultipartHttpServletRequest mulRequest) throws SQLException;
}
