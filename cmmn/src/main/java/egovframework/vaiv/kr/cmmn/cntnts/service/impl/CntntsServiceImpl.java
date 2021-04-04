package egovframework.vaiv.kr.cmmn.cntnts.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.vaiv.kr.cmmn.cntnts.service.CntntsHisVO;
import egovframework.vaiv.kr.cmmn.cntnts.service.CntntsService;
import egovframework.vaiv.kr.cmmn.cntnts.service.CntntsVO;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;

/**
 * 컨텐츠 관리 / 관리자 : 컨텐츠 관리에 대한 비지니스 클래스 정의
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
@Service("CntntsService")
public class CntntsServiceImpl implements CntntsService{
	
	/* 컨텐츠 DAO 선언 */
	@Resource(name="CntntsDAO")
	public CntntsDAO ctntsDAO;
	
	/* 파일 Util 선언 */
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil egovFileMngUtil;
	
	/* 파일 서비스 선언 */
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	/**
	 * 컨텐츠 목록 조회
	 * @param vo 검색VO
	 * @return List of CntntsVO 컨텐츠VO 목록
	 * @throws SQLException
	 * */
	@Override
	public List<CntntsVO> selectCntntsList(CntntsVO vo) throws SQLException {
		return ctntsDAO.selectCntntsList(vo);
	}

	/**
	 * 컨텐츠 목록 총 개수 조회
	 * @param vo 검색VO
	 * @return int 총 갯수
	 * @throws SQLException
	 * */
	@Override
	public int selectCntntsListTotCnt(CntntsVO vo) throws SQLException {
		return ctntsDAO.selectCntntsListTotCnt(vo);
	}

	/**
	 * 입력된 정보의 컨텐츠 등록
	 * @param vo 컨텐츠 VO
	 * @throws SQLException
	 * */
	@Override
	public void insertCntnts(CntntsVO vo, MultipartHttpServletRequest multiReq) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		vo.setRegistId(user.getUniqId());
		vo.setUpdtId(user.getUniqId());
		
		//첨부파일 저장
		String atchFileId = "";
		atchFileId = egovFileMngUtil.addFile(multiReq, "CNTNTS_");
		if(atchFileId != null && atchFileId != "") {
			vo.setAtchFileId(atchFileId);
		}
		
		ctntsDAO.insertCntnts(vo);
	}

	/**
	 * 컨텐츠 상세 조회
	 * @param vo 검색VO
	 * @return CntntsVO 컨텐츠VO
	 * @throws SQLException
	 * */
	@Override
	public CntntsVO selectCntnts(CntntsVO vo) throws SQLException {
		return ctntsDAO.selectCntnts(vo);
	}

	/**
	 * 입력된 정보로 컨텐츠 수정
	 * @param vo 컨텐츠VO
	 * @throws SQLException
	 * */
	@Override
	public void updateCntnts(CntntsVO vo, MultipartHttpServletRequest multiReq) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		vo.setUpdtId(user.getUniqId());
		
		if(multiReq != null) {
			//첨부파일 삭제
			egovFileMngUtil.deleteMultiFiles(multiReq.getParameter("deleteFileArr"));
			
			//첨부파일 수정
			String atchFileId = "";
			try {
				atchFileId = egovFileMngUtil.updateMultiFiles(multiReq, "CNTNTS_", vo.getAtchFileId(), true);
				if(atchFileId != null && !"".equals(atchFileId)){
					vo.setAtchFileId(atchFileId);
				}
			} catch (FdlException e) {
				new Loggable().exLogging("updateCntnts", e);
			} catch (IllegalStateException e) {
				new Loggable().exLogging("updateCntnts", e);
			} catch (IOException e) {
				new Loggable().exLogging("updateCntnts", e);
			}
		}
		
		ctntsDAO.updateCntnts(vo);
	}

	/**
	 * 입력된 정보의 컨텐츠 삭제
	 * @param vo 컨텐츠VO
	 * @throws SQLException
	 * */
	@Override
	public void deleteCntnts(CntntsVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		vo.setUpdtId(user.getUniqId());
		
		ctntsDAO.deleteCntnts(vo);
	}

	/**
	 * 컨텐츠 히스토리 목록 조회
	 * @param vo 검색VO
	 * @return List of CntntsHisVO 컨텐츠 히스토리 목록
	 * @throws SQLException
	 * */
	@Override
	public List<CntntsHisVO> selectCntntsHisList(CntntsHisVO vo) throws SQLException {
		return ctntsDAO.selectCntntsHisList(vo);
	}

	/**
	 * 컨텐츠 히스토리 목록 총 개수 조회
	 * @param vo 컨텐츠 히스토리VO
	 * @return int 총 갯수
	 * @throws SQLException
	 * */
	@Override
	public int selectCntntsHisListTotCnt(CntntsHisVO vo) throws SQLException {
		return ctntsDAO.selectCntntsHisListTotCnt(vo);
	}

	/**
	 * 컨텐츠 히스토리 상세 조회
	 * @param vo 컨텐츠 히스토리VO
	 * @return CntntsHisVO 컨텐츠 히스토리VO
	 * @throws SQLException
	 * */
	@Override
	public CntntsHisVO selectCntntsHis(CntntsHisVO vo) throws SQLException {
		return ctntsDAO.selectCntntsHis(vo);
	}

	/**
	 * 컨텐츠 Rollback
	 * @param vo 컨텐츠히스토리VO
	 * @throws SQLException
	 * */
	@Override
	public void updateCntntsHisRollback(CntntsHisVO vo) throws SQLException {
		CntntsHisVO ctntsHisVO = ctntsDAO.selectCntntsHis(vo);
		CntntsVO updateVO = new CntntsVO();
		
		updateVO.setCntntsId(ctntsHisVO.getCntntsId());
		updateVO.setCntntsCode(ctntsHisVO.getCntntsCode());
		updateVO.setCntntsNm(ctntsHisVO.getCntntsNm());
		updateVO.setCntntsCn(ctntsHisVO.getCntntsCn());
		updateVO.setUseAt("Y"); 		//복구시 사용유무는 무조건 Y
		
		updateCntnts(updateVO, null);
	}
}
