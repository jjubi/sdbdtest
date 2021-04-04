package egovframework.vaiv.kr.cmmn.qestnar.qestn.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.qestnar.common.SearchVO;
import egovframework.vaiv.kr.cmmn.qestnar.qestn.service.QestnAswperVO;
import egovframework.vaiv.kr.cmmn.qestnar.qestn.service.QestnOptnVO;
import egovframework.vaiv.kr.cmmn.qestnar.qestn.service.QestnService;
import egovframework.vaiv.kr.cmmn.qestnar.qestn.service.QestnVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarSenarioVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.impl.QestnarSenarioDAO;

/**
 * 설문문항 관리 / 관리자 : 설문문항 관리에 대한 비지니스 클래스 정의
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
@Service("QestnService")
public class QestnServiceImpl implements QestnService{
	/* 설문문항 DAO 선언 */
	@Resource(name="QestnDAO")
	private QestnDAO qestnDAO;
	
	/* 설문 시나리오 DAO 선언 */
	@Resource(name="QestnarSenarioDAO")
	private QestnarSenarioDAO qestnarSenarioDAO;
	
	/**
	 * 설문조사 문항 삭제
	 * @param vo QestnVO
	 * @throws SQLException
	 */
	@Override
	public void deleteQestn(QestnVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setRegistId(user.getUniqId());
		qestnDAO.deleteQestn(vo);
	}

	/**
	 * 설문조사 문항 등록
	 * @param vo QestnVO
	 * @param qestnData 문항 목록
	 * @throws SQLException
	 */
	@Override
	public void insertQestn(QestnVO vo, String qestnData) throws SQLException {
		//저장하기
		Gson gson = new Gson();
		JsonArray jsonArr = gson.fromJson(qestnData.replaceAll("&quot;", "\""), JsonArray.class);
		
		for(int i = 0; i < jsonArr.size(); i++) {
			JsonObject jsonObj = (JsonObject) jsonArr.get(i);
			System.out.println("obj = " + jsonObj);
			Map<String, Object> mapData = (Map) gson.fromJson(jsonObj, new HashMap().getClass());
			
			QestnVO insertVO = setQestnVO(mapData, vo.getQestnarSeqNo());
			insertVO.setQestnOrdr(String.valueOf(i+1));
			String qestnSeqNo = qestnDAO.insertQestn(insertVO);
			
			insertQestnAswper(mapData, qestnSeqNo);
			insertQestnOptn(mapData, qestnSeqNo);
		}
		
		//해당 설문의 시나리오 모두 삭제
		QestnarSenarioVO deleteVO = new QestnarSenarioVO();
		deleteVO.setQestnarSeqNo(vo.getQestnarSeqNo());
		qestnarSenarioDAO.deleteQestnarSenario(deleteVO);
		
	}
	
	/**
	 * 설문조사 문항 VO 설정
	 * @param mapData 입력한 문항 MapDATA
	 * @param qestnarSeqNo 설문조사 일련번호
	 * @return QestnVO 문항VO
	 */
	private QestnVO setQestnVO(Map<String, Object> mapData, String qestnarSeqNo) {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		//vo 생성
		QestnVO vo = new QestnVO();
		vo.setQestnarSeqNo(qestnarSeqNo);
		vo.setQestnTy(mapData.get("qestnTy") == null ? null : mapData.get("qestnTy").toString());
		vo.setQestnSj(mapData.get("qestnSj") == null ? null : mapData.get("qestnSj").toString());
		vo.setQestnHpcm(mapData.get("qestnHpcm") == null ? null : mapData.get("qestnHpcm").toString());
		vo.setQestnEssntlAt(mapData.get("qestnEssntlAt") == null ? null : mapData.get("qestnEssntlAt").toString());
		//페이지 구분 추후 개발 필요
		vo.setQestnPge(mapData.get("qestnPge") == null ? null : mapData.get("qestnPge").toString());
		vo.setRegistId(user.getUniqId());
		//사용여부 추후 개발 필요
		vo.setUseAt("Y");
			
		return vo;	
	}
	
	/**
	 * 설문조사 문항 답안 저장
	 * @param mapData 문항 답안 MapDATA
	 * @param qestnSeqNo 문항 일련번호
	 * @throws SQLException
	 */
	private void insertQestnAswper(Map<String, Object> mapData, String qestnSeqNo) throws SQLException {
		QestnAswperVO aswperVO = new QestnAswperVO();
		aswperVO.setQestnSeqNo(qestnSeqNo);
		String aswperEtcAt = String.valueOf(mapData.get("aswperEtcAt"));
		
		for(String mapKey : mapData.keySet()) {
			if(mapKey.equals("aswperText")) {
				if(mapData.get(mapKey) instanceof String) {
					aswperVO.setAswperText((String) mapData.get(mapKey));
					if("Y".equals(mapData.get("aswperScoreAt"))) {
						aswperVO.setAswperScore((String) mapData.get("aswperScore"));
					}
					qestnDAO.insertQestnAswper(aswperVO);
				} else {
					List<String> text = (List<String>) mapData.get(mapKey);
					List<String> score = null;
					if("Y".equals(mapData.get("aswperScoreAt"))) {
						score = (List<String>) mapData.get("aswperScore");
					}
					//추후 이미지 넣는 것 추가 개발 필요
					int i = 0;
					for(String textOne : text) {
						aswperVO.setAswperText(textOne);
						if(score != null) {
							aswperVO.setAswperScore(score.get(i));
						}
						
						if("Y".equals(aswperEtcAt)) {
							if(text.size() == (i + 1)) {
								aswperVO.setAswperEtcAt("Y");
							} else {
								aswperVO.setAswperEtcAt("N");
							}
						}
						
						qestnDAO.insertQestnAswper(aswperVO);
						i++;
					}
				}
			}
		}
	}
	
	/**
	 * 설문조사 문항 옵션 저장
	 * @param mapData 문항 옵션 MapDATA
	 * @param qestnSeqNo 문항 일련번호
	 * @throws SQLException
	 */
	private void insertQestnOptn(Map<String, Object> mapData, String qestnSeqNo) throws SQLException {
		QestnOptnVO insertVO = new QestnOptnVO();
		insertVO.setQestnSeqNo(qestnSeqNo);
		
		for(String mapKey : mapData.keySet()) {
			if(mapKey.startsWith("optn") && mapKey.endsWith("At")) {
				//옵션...
				String type = mapKey.replaceAll("optn", "");
				type = type.replaceAll("At", "");
				insertVO.setOptnTy(type);
				insertVO.setOptnValue(mapData.get(mapKey).toString());
				if(mapData.get(mapKey.replaceAll("At", "") + "Value") != null && "Y".equals(mapData.get(mapKey))) {
					insertVO.setOptnValue(mapData.get(mapKey.replaceAll("At", "") + "Value").toString());
				}
				qestnDAO.insertQestnOptn(insertVO);				
			}
		}
	}

	/**
	 * 설문조사 문항 목록 조회
	 * @param vo 검색VO
	 * @return List of QestnVO 문항VO 목록
	 * @throws SQLException
	 */
	@Override
	public List<QestnVO> selectQestnList(SearchVO vo) throws SQLException {
		return qestnDAO.selectQestnList(vo);
	}

	/**
	 * 설문조사 문항 상세 조회
	 * @param vo 검색VO
	 * @return QestnVO 문항VO
	 * @throws SQLException
	 */
	@Override
	public QestnVO selectQestn(SearchVO vo) throws SQLException {
		return qestnDAO.selectQestn(vo);
	}
}
