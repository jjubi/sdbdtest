package egovframework.vaiv.kr.cmmn.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("ComUtlDAO")
public class ComUtlDAO extends EgovComAbstractDAO {
	
	public List<EgovMap> selectSlickGridData(String query) throws RuntimeException {
		return selectList(query);
	}
		
	public List<EgovMap> selectSlickGridData(String query, EgovMap param) throws RuntimeException {
		return selectList(query, param);
	}
	
}
