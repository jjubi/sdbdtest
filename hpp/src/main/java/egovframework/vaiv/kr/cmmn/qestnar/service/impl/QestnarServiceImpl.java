package egovframework.vaiv.kr.cmmn.qestnar.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cmm.util.EgovWebUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.vaiv.kr.cmmn.qestnar.common.SearchVO;
import egovframework.vaiv.kr.cmmn.qestnar.qestn.service.QestnVO;
import egovframework.vaiv.kr.cmmn.qestnar.qestn.service.impl.QestnDAO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarResultDtlVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarResultVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarService;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarTrgtVO;
import egovframework.vaiv.kr.cmmn.qestnar.service.QestnarVO;

/**
 * 설문 관리 / 관리자 : 설문 관리에 대한 비지니스 클래스 정의
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
@Service("QestnarService")
public class QestnarServiceImpl implements QestnarService{
	/* 설문조사 DAO 선언 */
	@Resource(name="QestnarDAO")
	private QestnarDAO qestnarDAO;
	
	/* 설문조사 문항 DAO 선언 */
	@Resource(name="QestnDAO")
	private QestnDAO qestnDAO;

	/**
	 * 설문조사 목록 조회
	 * @param vo 검색VO
	 * @return List of QestnarVO 설문조사 목록
	 * @throws SQLException
	 */
	@Override
	public List<QestnarVO> selectQestnarList(SearchVO vo) throws SQLException {
		return qestnarDAO.selectQestnarList(vo);
	}

	/**
	 * 설문조사 목록 총 갯수 조회
	 * @param vo 검색VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	@Override
	public int selectQestnarListTotCnt(SearchVO vo) throws SQLException {
		return qestnarDAO.selectQestnarListTotCnt(vo);
	}

	/**
	 * 설문조사 상세 조회
	 * @param vo 검색VO
	 * @return QestnarVO 설문조사VO
	 * @throws SQLException
	 */
	@Override
	public QestnarVO selectQestnar(SearchVO vo) throws SQLException {
		return qestnarDAO.selectQestnar(vo);
	}

	/**
	 * 설문조사 대상 목록 저장
	 * @param qestnarTrgetListStr 설문조사타겟 목록 문자열
	 * @param qestnarSeqNo 설문조사 일련번호
	 * @throws SQLException
	 */
	private void insertQestnarTrget(String qestnarTrgetListStr, String qestnarSeqNo) throws SQLException {
		QestnarTrgtVO trgetVO = new QestnarTrgtVO();
		trgetVO.setQestnarSeqNo(qestnarSeqNo);
		if(qestnarTrgetListStr != null) {
			String[] trgetList = qestnarTrgetListStr.split(";");
			for(String trgetInfo : trgetList) {
				String[] infos = trgetInfo.split("-");
				if(infos != null && infos.length != 0) {
					trgetVO.setEsntlId(infos[0]);
					trgetVO.setMberNm(infos[1]);
					qestnarDAO.insertQestnarTrget(trgetVO);
				}
			}
		}
	}
	
	/**
	 * 입력된 정보로 설문조사 등록
	 * @param vo 설문조사VO
	 * @throws SQLException
	 */
	@Override
	public void insertQestnar(QestnarVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setRegistId(user.getUniqId());
		vo.setUpdtId(user.getUniqId());
		
		String qestnarSeqNo = qestnarDAO.insertQestnar(vo);
		
		//대상 있을 시 저장
		if(!"ALL".equals(vo.getQestnarTrget())) {
			if(vo.getQestnarTrgetListStr() != null) {
				insertQestnarTrget(vo.getQestnarTrgetListStr(), qestnarSeqNo);
			}
		}
	}

	/**
	 * 입력된 정보로 설문조사 수정
	 * @param vo 설문조사VO
	 * @throws SQLException
	 */
	@Override
	public void updateQestnar(QestnarVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		
		qestnarDAO.updateQestnar(vo);
		
		//대상 있을 시 저장
		if(!"ALL".equals(vo.getQestnarTrget())) {
			if(vo.getQestnarTrgetListStr() != null) {
				//삭제 
				QestnarTrgtVO trgetVO = new QestnarTrgtVO();
				trgetVO.setQestnarSeqNo(vo.getQestnarSeqNo());
				qestnarDAO.deleteQestnarTrget(trgetVO);
				insertQestnarTrget(vo.getQestnarTrgetListStr(), vo.getQestnarSeqNo());
			}
		} else {
			//전체일 경우 대상 목록 삭제
			QestnarTrgtVO trgetVO = new QestnarTrgtVO();
			trgetVO.setQestnarSeqNo(vo.getQestnarSeqNo());
			qestnarDAO.deleteQestnarTrget(trgetVO);
		}
	}

	/**
	 * 입력된 정보로 설문조사 삭제
	 * @param vo 설문조사VO
	 * @throws SQLException
	 */
	@Override
	public void deleteQestnar(QestnarVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		
		qestnarDAO.deleteQestnar(vo);
	}

	/**
	 * 설문조사 대상 목록 조회
	 * @param map 검색Map
	 * @return List of EgovMap 설문조사 대상 목록
	 * @throws SQLException
	 */
	@Override
	public List<EgovMap> selectQestnarTargetList(Map map) throws SQLException {
		return qestnarDAO.selectQestnarTargetList(map);
	}

	/**
	 * 설문조사 대상 목록 총 갯수 조회
	 * @param map 검색Map
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	@Override
	public int selectQestnarTargetListTotCnt(Map map) throws SQLException {
		return qestnarDAO.selectQestnarTargetListTotCnt(map);
	}

	@Override
	public String insertQestnarResult(QestnarResultVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setRegistId(user.getUniqId());
		vo.setUpdtId(user.getUniqId());
		String qestnarResultSeqNo = qestnarDAO.insertQestnarResult(vo);	
		
		return qestnarResultSeqNo;
	}

	@Override
	public void updateQestnarResult(QestnarResultVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		qestnarDAO.updateQestnarResult(vo);
	}
	
	@Override
	public void insertQestnarResultDtl(QestnarResultVO vo) throws SQLException {
		SearchVO searchVO = new SearchVO();
		QestnVO nowQestnVO = new QestnVO();
		String[] qestnSeqNoArr = null;
		searchVO.setQestnarSeqNo(vo.getQestnarSeqNo());
		if(vo.getNowQestnSeqNo().contains(",")) {
			qestnSeqNoArr = vo.getNowQestnSeqNo().split(",");
		} else {
			qestnSeqNoArr = new String[1];
			qestnSeqNoArr[0] = vo.getNowQestnSeqNo();
		}
		
		String[] qestnAnswer = vo.getAnswerValue().split(";");
		
		if(qestnAnswer != null && qestnAnswer.length != 0) {
			QestnarResultDtlVO insertVO = new QestnarResultDtlVO();
			insertVO.setQestnarResultSeqNo(vo.getQestnarResultSeqNo());
			int i = 0;
			for(String answer : qestnAnswer) {
				if(!"".equals(answer)) {
					searchVO.setQestnSeqNo(qestnSeqNoArr[i]);
					nowQestnVO = qestnDAO.selectQestn(searchVO);
					String qestnTy = nowQestnVO.getQestnTy();
					
					insertVO.setQestnSeqNo(qestnSeqNoArr[i]);
					insertVO = setQestnarResultDtl(insertVO, answer, qestnTy);
					
					qestnarDAO.insertQestnarResultDtl(insertVO);
				}
				i++;
			}
		}
	}
	
	private QestnarResultDtlVO setQestnarResultDtl(QestnarResultDtlVO insertVO, String answer, String qestnTy) {
		if("radio".equals(qestnTy) || "select".equals(qestnTy)) {
			if(answer.contains(":")) {
				String[] answerEtc = answer.split(":");
				insertVO.setQestnResultCn(answerEtc[0]);
				insertVO.setQestnEtcResultCn(answerEtc[1]);
			} else {
				insertVO.setQestnResultCn(answer);
			}
		} else if("checkbox".equals(qestnTy)) {
			String[] answerArr = answer.split(",");
			String chAnswerStr = "";
			for(String chAnswer : answerArr) {
				if(chAnswer.contains(":")) {
					String[] answerEtc = chAnswer.split(":");
					if(!"".equals(chAnswerStr)) {
						chAnswerStr += ",";
					} 
					chAnswerStr += answerEtc[0];
					insertVO.setQestnEtcResultCn(answerEtc[1]);
				} else {
					if(!"".equals(chAnswerStr)) {
						chAnswerStr += ",";
					} 
					chAnswerStr += chAnswer;
				}
			}
			insertVO.setQestnResultCn(chAnswerStr);
		} else if("file".equals(qestnTy)) {
			
		} else {
			insertVO.setQestnResultCn(answer);
		}
		
		return insertVO;
	}

	@Override
	public List<EgovMap> selectQestnarResultStat(EgovMap map) throws SQLException {
		return qestnarDAO.selectQestnarResultStat(map);
	}

	@Override
	public QestnarResultVO checkQestnarResultTarget(QestnarResultVO vo) throws SQLException {
		return qestnarDAO.selectQestnarResult(vo);
	}

	
	
}




