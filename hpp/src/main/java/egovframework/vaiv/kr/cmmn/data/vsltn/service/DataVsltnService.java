package egovframework.vaiv.kr.cmmn.data.vsltn.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 데이터 시각화 관리 / 관리자 : 데이터 시각화에 대한 인터페이스 정의
 * @category 공통
 * @author jo
 * @since 2021-03-22
 * @version v1.0
 * @see
 * <pre>
 *  ******************************************
 *  수정 이력
 *  
 *  수정일                  수정자                 수정내용
 *  ------------------------------------------
 *  2021.03.22    jo           최초 등록
 * 
 * 
 *  ******************************************
 *  Copyright 2020 VAIV Co.
 *  All rights reserved
 * </pre>
 */
public interface DataVsltnService {
	/**
	 * 데이터 시각화 공유 URL 생성
	 * @param vo DataVsltnCnrsVO
	 * @param multiRequest 파일 request
	 * @return url key
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 */
	public String insertDataVsltnCnrs(DataVsltnCnrsVO vo, MultipartHttpServletRequest multiRequest) throws SQLException, NoSuchAlgorithmException;
	
	/**
	 * 데이터 시각화 공유 정보 가져오기
	 * @param vo 검색 객체
	 * @return 데이터 시각화 공유 객체
	 * @throws SQLException
	 */
	public DataVsltnCnrsVO selectDataVsltnCnrs(DataVsltnCnrsVO vo) throws SQLException;
}
