package egovframework.vaiv.kr.cmmn.qestnar.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.vaiv.kr.cmmn.qestnar.common.SearchVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarSenarioDtlVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarSenarioVO;

@Repository("QestnarSenarioDAO")
public class QestnarSenarioDAO extends EgovComAbstractDAO {
	/**
	 * @method 설명 : 설문조사 시나리오 목록 데이터 조회
	 * @param : vo(SearchVO)
	 * @return : List of QestnarSenarioVO
	 * @exception : SQLException
	 * */
	public List<QestnarSenarioVO> selectQestnarSenarioList(SearchVO vo) throws SQLException {
		List<QestnarSenarioVO> returnList = selectList("selectQestnarSenarioList", vo);
		
		for(QestnarSenarioVO qsVO : returnList) {
			qsVO.setSenarioDtlList(selectList("selectQestnarSenarioDtlList", qsVO));
		}
		
		return returnList;
	}
	
	/**
	 * @method 설명 : 설문조사 시나리오 데이터 저장
	 * @param : senarioJsonData(String)
	 * @exception : SQLException
	 * */
	public void insertQestnarSenario(QestnarSenarioVO vo) throws SQLException {
		insert("insertQestnarSenario", vo);
		for(QestnarSenarioDtlVO dtlVO : vo.getSenarioDtlList()) {
			dtlVO.setSenarioSeqNo(vo.getSenarioSeqNo());
			insert("insertQestnarSenarioDtl", dtlVO);
		}
	}
	
	/** 설문조사 시라*/
	public void deleteQestnarSenario(QestnarSenarioVO vo) throws SQLException {
		delete("deleteQestnarSenarioDtl", vo);
		delete("deleteQestnarSenario", vo);
	}
}
