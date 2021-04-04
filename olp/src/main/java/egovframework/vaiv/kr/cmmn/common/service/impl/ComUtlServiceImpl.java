package egovframework.vaiv.kr.cmmn.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.vaiv.kr.cmmn.common.service.ComUtlService;

@Service("ComUtlService")
public class ComUtlServiceImpl implements ComUtlService{
	
	@Resource(name="ComUtlDAO")
	private ComUtlDAO comUtlDAO;

	@Override
	public List<EgovMap> selectSlickGridData(EgovMap param) throws RuntimeException {
		String query = (String) param.get("query");
		param.remove("query");
		
		List<EgovMap> resultList = null;
		if(param.isEmpty()) {
			resultList = comUtlDAO.selectSlickGridData(query);
		} else {
			resultList = comUtlDAO.selectSlickGridData(query, param);
		}
		
		return resultList;
	}

}
