package egovframework.vaiv.kr.cmmn.qestnar.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.qestnar.common.SearchVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarSenarioDtlVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarSenarioService;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarSenarioVO;

@Service("QestnarSenarioService")
public class QestnarSenarioServiceImpl implements QestnarSenarioService{
	/* 설문 시나리오 DAO 선언 */
	@Resource(name="QestnarSenarioDAO")
	private QestnarSenarioDAO qestnarSenarioDAO;
	/**
	 * @method 설명 : 설문조사 시나리오 목록 조회
	 * @param : vo(SearchVO)
	 * @return : List of QestnarSenarioVO
	 * @exception : SQLException
	 * */
	@Override
	public List<QestnarSenarioVO> selectQestnarSenarioList(SearchVO vo) throws SQLException {
		return qestnarSenarioDAO.selectQestnarSenarioList(vo);
	}
	
	/**
	 * @method 설명 : 설문조사 시나리오 저장
	 * @param : senarioJsonData(String)
	 * @exception : SQLException
	 * */
	@Override
	public void insertQestnarSenario(String senarioJsonData) throws SQLException {
		Gson gson = new Gson();
		JsonArray jsonArr = gson.fromJson(senarioJsonData.replaceAll("&quot;", "\""), JsonArray.class);	
		System.out.println(senarioJsonData +"\n"+jsonArr);
		
		//삭제 후 저장
		String qestnarSeqNo = ((JsonObject) jsonArr.get(0)).get("qestnarSeqNo").getAsString();
		QestnarSenarioVO deleteVO = new QestnarSenarioVO();
		deleteVO.setQestnarSeqNo(qestnarSeqNo);
		qestnarSenarioDAO.deleteQestnarSenario(deleteVO);
		
		for(int i = 0; i < jsonArr.size(); i++) {
			JsonObject jsonObj = (JsonObject) jsonArr.get(i);
			System.out.println("obj = " + jsonObj);
			Map<String, Object> mapData = (Map) gson.fromJson(jsonObj, new HashMap().getClass());
			if(mapData.size() == 1) {
				break;
			}
			QestnarSenarioVO insertVO = setQestnarSenarioVO(mapData, qestnarSeqNo);
			qestnarSenarioDAO.insertQestnarSenario(insertVO);
		}
	}
	
	/**
	 * @method 설명 : 설문조사 시나리오 VO 생성
	 * @param : senarioJsonData(String)
	 * @exception : SQLException
	 * */
	private QestnarSenarioVO setQestnarSenarioVO(Map<String, Object> mapData, String qestnarSeqNo) {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		//vo 생성
		QestnarSenarioVO vo = new QestnarSenarioVO();
		vo.setQestnarSeqNo(qestnarSeqNo);
		vo.setDtrmnQestnSeqNo(mapData.get("dtrmnQestnSeqNo") == null ? null : mapData.get("dtrmnQestnSeqNo").toString());
		vo.setTrgetQestnSeqNo(mapData.get("trgetQestnSeqNo") == null ? null : mapData.get("trgetQestnSeqNo").toString());
		vo.setRegistId(user.getUniqId());
		
		List<QestnarSenarioDtlVO> dtlList = new ArrayList<QestnarSenarioDtlVO>();
		int ordr = 0;
		//{s1 = {sdf = sadf, sfd = asdf}, s2 = {sfd = sdf, sf = sdf}}
		Map<String, Object> dtrnmMap = (Map<String, Object>) mapData.get("dtrmnCnd");
		for(String mapKey : dtrnmMap.keySet()) {
			Map<String, Object> maps = (Map<String, Object>) dtrnmMap.get(mapKey);
			QestnarSenarioDtlVO insertDtlVO = new QestnarSenarioDtlVO();
			insertDtlVO.setDtrmnCndOrdr(String.valueOf(++ordr));
			for(String mapsKey : maps.keySet()) {
				if("cnd".equals(mapsKey)) {
					insertDtlVO.setDtrmnCnd(maps.get(mapsKey).toString());
				} else if("value".equals(mapsKey)) {
					insertDtlVO.setDtrmnCndValue(maps.get(mapsKey).toString());
				} else if("logic".equals(mapsKey)) {
					insertDtlVO.setDtrmnCndLogic(maps.get(mapsKey).toString());
				}
			}
			dtlList.add(insertDtlVO);
		}
		
		vo.setSenarioDtlList(dtlList);
		
		return vo;	
	}
	
	/**
	 * @method 설명 : 설문조사 시나리오 체크 후 다음 문항 번호 가져오기
	 * @param : answerValue(String), vo(QestnarSenarioVO)
	 * @return : int
	 * @exception : SQLException
	 * */
	@Override
	public int selectNextQestnSenarioCheck(String answerValue, SearchVO vo) throws SQLException {
		int nextQestnSeqNo = (vo.getDtrmnQestnSeqNo() == "" ? 0 : Integer.parseInt(vo.getDtrmnQestnSeqNo())) + 1;
		
		//해당 문항의 시나리오 가져오기
		List<QestnarSenarioVO> senarioList = qestnarSenarioDAO.selectQestnarSenarioList(vo);
			
		//시나리오 확인
		boolean checkCnd = true;
		int checkCndNextSeqNo = 0;
		for(QestnarSenarioVO qsvo : senarioList) {
			boolean checkSenarioCnd = checkCnd;
			for(QestnarSenarioDtlVO qsdVO : qsvo.getSenarioDtlList()) {
				String cnd = qsdVO.getDtrmnCnd();
				String value = qsdVO.getDtrmnCndValue();
				boolean valueCheck = true;
				if("is".equals(cnd)) {
					if(!answerValue.equals(value)) {
						valueCheck = false;
					}
				} else if("isNot".equals(cnd)) {
					if(answerValue.equals(value)) {
						valueCheck = false;
					}
				} else if("isContain".equals(cnd)) {
					if(!answerValue.contains(value)) {
						valueCheck = false;
					}
				} else if("isNotContain".equals(cnd)) {
					if(answerValue.contains(value)) {
						valueCheck = false;
					}
				}
				
				String logic = qsdVO.getDtrmnCndLogic(); 
				if("".equals(logic)) {
					checkSenarioCnd = valueCheck;
				} else if("and".equals(logic)) {
					if(checkSenarioCnd && valueCheck) {
						checkSenarioCnd = true;
					} else {
						checkSenarioCnd = false;
					}
				} else if("or".equals(logic)) {
					if(checkSenarioCnd || valueCheck) {
						checkSenarioCnd = true;
					} else {
						checkSenarioCnd = false;
					}
				}
			}
			if(checkCnd && checkSenarioCnd) {
				checkCnd = true;
				checkCndNextSeqNo = Integer.parseInt(qsvo.getTrgetQestnSeqNo());
			} else {
				checkCnd = false;
			}
		}
		
		if(checkCndNextSeqNo != 0) {
			nextQestnSeqNo = checkCndNextSeqNo;
		}
		
		return nextQestnSeqNo;
	}
	
}
