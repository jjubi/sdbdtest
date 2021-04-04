package egovframework.vaiv.kr.cmmn.common.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface ComUtlService {
	public List<EgovMap> selectSlickGridData(EgovMap param) throws RuntimeException;
}
