package egovframework.vaiv.kr.cmmn.prhibtWrd.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.vaiv.kr.cmmn.prhibtWrd.service.PrhibtWrdVO;

/**
 * 금지단어 관리 / 관리자 : 금지단어 관리에 대한 데이터 접근 클래스 정의
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
@Repository("PrhibtWrdDAO")
public class PrhibtWrdDAO extends EgovComAbstractDAO {
	
	/**
	 * @method 설명 : 금지단어 목록 데이터를 조회
	 * @param : vo(PrhibtWrdVO)
	 * @return : List of PrhibtWrdVO
	 * @exception : SQLException
	 * */
	public List<PrhibtWrdVO> selectPrhibtWrdList(PrhibtWrdVO vo) throws SQLException {
		return selectList("selectPrhibtWrdList", vo);
	}

	/**
	 * @method 설명 : 금지단어 목록 총 갯수 데이터를 조회
	 * @param : vo(PrhibtWrdVO)
	 * @return : int
	 * @exception : SQLException
	 * */
	public int selectPrhibtWrdListTotCnt(PrhibtWrdVO vo) throws SQLException {
		return selectOne("selectPrhibtWrdListTotCnt", vo);
	}
	
	/**
	 * @method 설명 : 금지단어 중복 데이터 갯수를 조회
	 * @param : vo(PrhibtWrdVO)
	 * @return : int
	 * @exception : SQLException
	 * */
	public int checkDupPrhibtWrd(PrhibtWrdVO vo) throws SQLException {
		return selectOne("checkDupPrhibtWrd", vo);
	}

	/**
	 * @method 설명 : 금지단어 데이터 등록
	 * @param : vo(PrhibtWrdVO)
	 * @exception : SQLException
	 * */
	public void insertPrhibtWrd(PrhibtWrdVO vo) throws SQLException {
		insert("insertPrhibtWrd", vo);
	}
	
	/**
	 * @method 설명 : 금지단어 상세 데이터 조회
	 * @param : vo(PrhibtWrdVO)
	 * @return : PrhibtWrdVO
	 * @exception : SQLException
	 * */
	public PrhibtWrdVO selectPrhibtWrd(PrhibtWrdVO vo) throws SQLException {
		return selectOne("selectPrhibtWrd", vo);
	}
	
	/**
	 * @method 설명 : 금지단어 수정
	 * @param : vo(PrhibtWrdVO)
	 * @return : int
	 * @exception : SQLException
	 * */
	public int updatePrhibtWrd(PrhibtWrdVO vo) throws SQLException {
		return update("updatePrhibtWrd", vo);
	}
	
	/**
	 * @method 설명 : 금지단어 삭제
	 * @param : vo(PrhibtWrdVO)
	 * @return : int
	 * @exception : SQLException
	 * */
	public int deletePrhibtWrd(PrhibtWrdVO vo) throws SQLException {
		return delete("deletePrhibtWrd", vo);
	}
}
