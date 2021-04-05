package egovframework.vaiv.kr.cmmn.bbs.ntt.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;
import egovframework.vaiv.kr.cmmn.bbs.ntt.service.NttVO;

/**
 * 게시물 관리 / 관리자 : 게시물 관리에 대한 데이터 접근 클래스 정의
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
@Repository("NttDAO")
public class NttDAO extends EgovComAbstractDAO{
	
	/**
	 * 게시물 목록 데이터 조회 
	 * @param vo 검색VO
	 * @return List of NttVO 게시물 목록
	 * @throws SQLException
	 */
	public List<NttVO> selectNttList(SearchVO vo) throws SQLException {
		return selectList("selectNttList", vo);
	}
	
	/**
	 * 게시물 목록 총 갯수 데이터 조회 
	 * @param vo 검색VO
	 * @return int 게시물 총 갯수 
	 * @throws SQLException
	 */
	public int selectNttListTotCnt(SearchVO vo) throws SQLException {
		return selectOne("selectNttListTotCnt", vo);
	}
	
	/**
	 * 입력된 정보로 게시물 데이터 등록
	 * @param vo 게시물VO
	 * @param multiReq Multipart Request
	 * @throws SQLException
	 */
	public void insertNtt(NttVO vo) throws SQLException {
		insert("insertNtt", vo);
	}
	
	/**
	 * 게시물 상세 데이터 조회
	 * @param vo 검색VO
	 * @return NttVO 게시물VO
	 * @throws SQLException
	 */
	public NttVO selectNtt(SearchVO vo) throws SQLException {
		return selectOne("selectNtt", vo);
	}
	
	/**
	 * 게시물 조회수 데이터 증가
	 * @param vo 검색VO
	 * @throws SQLException
	 */
	public void incrementNttRdcnt(SearchVO vo) throws SQLException {
		update("incrementNttRdcnt", vo);
	}
	
	/**
	 * 입력된 정보로 게시물 데이터 수정
	 * @param vo 게시물VO
	 * @param multiReq Multipart Request
	 * @throws SQLException
	 */
	public void updateNtt(NttVO vo) throws SQLException {
		//수정 전 현재 게시글 정보 저장
		NttVO nowVO = new NttVO();
		nowVO.setNttId(vo.getNttId());
		insertHistoryNtt(nowVO);
		
		//수정
		update("updateNtt", vo);
	}
	
	/**
	 * 입력된 정보로 게시물 데이터 삭제
	 * @param vo 게시물VO
	 * @throws SQLException
	 */
	public void deleteNtt(NttVO vo) throws SQLException {
		//하위 답글까지 삭제처리
		delete("deleteNtt", vo);
	}
	
	/**
	 * 게시물 위치(순서) 가져오기(답글일 경우)
	 * @param vo 게시물VO
	 * @return String 게시물 순번
	 * @throws SQLException
	 */
	public String orderNttInnerOrdr(NttVO vo) throws SQLException {
		int lastInnerOrdr = 0;
		
		EgovMap depthInLastNtt = selectOne("selectDepthInLastInnerOrdr", vo);
		
		if(depthInLastNtt == null || depthInLastNtt.isEmpty()) {
			//error
			throw new SQLException();
		} else {
			lastInnerOrdr = Integer.parseInt(depthInLastNtt.get("nttInnerOrdr").toString());			
		}
		vo.setNttInnerOrdr(String.valueOf(lastInnerOrdr + 1));
		
		update("orderNttInnerOrdr", vo);
		
		return vo.getNttInnerOrdr();
	}
	
	/**
	 * 게시물 히스토리 데이터 저장
	 * @param vo 게시물VO
	 * @throws SQLException
	 */
	public void insertHistoryNtt(NttVO vo) throws SQLException {
		insert("insertHistoryNtt", vo);
	}
	
	/**
	 * 게시물 총 갯수 조회 (게시판 구분X)
	 * @return int 게시물 총 갯수
	 * @throws SQLException
	 */
	public int selectNttListTotCntNonBbsId() throws SQLException {
		return selectOne("selectNttListTotCntNonBbsId");
	}
}
