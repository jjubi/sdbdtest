package egovframework.vaiv.kr.cmmn.bbs.ntt.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;

/**
 * 게시물 관리 / 관리자 : 게시물 관리에 대한 인터페이스 정의
 * @category 공통
 * @author jo
 * @since 2020-12-31
 * @version : v1.0
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
public interface NttService {
	/**
	 * 입력된 정보로 게시물 등록
	 * @param vo 게시물VO
	 * @param multiReq Multipart Request
	 * @throws SQLException
	 */
	public void insertNtt(NttVO vo, MultipartHttpServletRequest multiReq) throws SQLException;
	
	/**
	 * 입력된 정보로 게시물 수정
	 * @param vo 게시물VO
	 * @param multiReq Multipart Request
	 * @throws SQLException
	 */
	public void updateNtt(NttVO vo, MultipartHttpServletRequest multiReq) throws SQLException;
	
	/**
	 * 입력된 정보로 게시물 삭제
	 * @param vo 게시물VO
	 * @throws SQLException
	 */
	public void deleteNtt(NttVO vo) throws SQLException;
	
	/**
	 * 게시물 조회수 증가
	 * @param vo 검색VO
	 * @throws SQLException
	 */
	public void incrementNttRdcnt(SearchVO vo) throws SQLException;
	
	/**
	 * 게시물 상세 조회
	 * @param vo 검색VO
	 * @return NttVO 게시물VO
	 * @throws SQLException
	 */
	public NttVO selectNtt(SearchVO vo) throws SQLException;
	
	/**
	 * 게시물 목록 조회 
	 * @param vo 검색VO
	 * @return List of NttVO 게시물 목록
	 * @throws SQLException
	 */
	public List<NttVO> selectNttList(SearchVO vo) throws SQLException;
	
	/**
	 * 게시물 목록 총 갯수 조회 
	 * @param vo 검색VO
	 * @return int 게시물 총 갯수 
	 * @throws SQLException
	 */
	public int selectNttListTotCnt(SearchVO vo) throws SQLException;
	
	/**
	 * 게시물 총 갯수 조회 (게시판 구분X)
	 * @return int 게시물 총 갯수
	 * @throws SQLException
	 */
	public int selectNttListTotCntNonBbsId() throws SQLException;
}
