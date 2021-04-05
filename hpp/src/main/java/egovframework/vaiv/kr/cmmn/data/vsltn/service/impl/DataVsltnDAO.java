package egovframework.vaiv.kr.cmmn.data.vsltn.service.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.vaiv.kr.cmmn.data.vsltn.service.DataVsltnCnrsVO;

/**
 * 데이터 시각화 관리 / 관리자 : 데이터 시각화에 대한 데이터 접근 클래스 정의
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
@Repository("DataVsltnDAO")
public class DataVsltnDAO extends EgovComAbstractDAO {
	
	/**
	 * 데이터 시각화 공유 URL 정보 등록
	 * @param vo DataVsltnCnrsVO
	 * @throws SQLException
	 */
	public void insertDataVsltnCnrs(DataVsltnCnrsVO vo) throws SQLException {
		insert("cmmn.DataVsltn.insertDataVsltnCnrs", vo);
	}

	public DataVsltnCnrsVO selectDataVsltnCnrs(DataVsltnCnrsVO vo) throws SQLException {
		return selectOne("cmmn.DataVsltn.selectDataVsltnCnrs", vo);
	}
	
}
