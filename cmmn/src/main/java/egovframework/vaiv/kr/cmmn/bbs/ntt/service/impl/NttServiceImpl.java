package egovframework.vaiv.kr.cmmn.bbs.ntt.service.impl;

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
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.bbs.common.SearchVO;
import egovframework.vaiv.kr.cmmn.bbs.ntt.service.NttService;
import egovframework.vaiv.kr.cmmn.bbs.ntt.service.NttVO;

/**
 * 게시물 관리 / 관리자 : 게시물 관리에 대한 비지니스 클래스 정의
 * @category 공통
 * @author jo
 * @since 2020-12-31
 * @version : v1.0
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
@Service("NttService")
public class NttServiceImpl implements NttService{
	
	/* 게시물 DAO 선언 */
	@Resource(name="NttDAO")
	private NttDAO nttDAO;
	
	/* 파일 Util 선언 */
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil egovFileMngUtil;
	
	/* 파일 서비스 선언 */
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	/**
	 * 게시물 목록 조회 
	 * @param vo 검색VO
	 * @return List of NttVO 게시물 목록
	 * @throws SQLException
	 */
	@Override
	public List<NttVO> selectNttList(SearchVO vo) throws SQLException {
		return nttDAO.selectNttList(vo);
	}

	/**
	 * 게시물 목록 총 갯수 조회 
	 * @param vo 검색VO
	 * @return int 게시물 총 갯수 
	 * @throws SQLException
	 */
	@Override
	public int selectNttListTotCnt(SearchVO vo) throws SQLException {
		return nttDAO.selectNttListTotCnt(vo);
	}

	/**
	 * 입력된 정보로 게시물 등록
	 * @param vo 게시물VO
	 * @param multiReq Multipart Request
	 * @throws SQLException
	 */
	@Override
	public void insertNtt(NttVO vo, MultipartHttpServletRequest multiReq) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		vo.setRegistId(user.getUniqId());
		vo.setUpdtId(user.getUniqId());
		
		//첨부파일 저장
		String atchFileId = "";
		atchFileId = egovFileMngUtil.addFile(multiReq, "NTT_");
		if(atchFileId != null && atchFileId != "") {
			vo.setAtchFileId(atchFileId);
		}
		
		//답글이 아닐경우 depth 1로
		if(vo.getNttDp() == null || "".equals(vo.getNttDp())) {
			vo.setNttDp("1");
			vo.setNttInnerOrdr("1");
			vo.setUpperNttId("0");
		} else {
			//답글일 경우 innerOrdr 가져오기
			vo.setNttInnerOrdr(nttDAO.orderNttInnerOrdr(vo));			
		}
		
		//비밀글 default N
		if(vo.getSecretAt() == null || "".equals(vo.getSecretAt())) {
			vo.setSecretAt("N");
		}
		//공지글 default N
		if(vo.getNoticeAt() == null || "".equals(vo.getNoticeAt())) {
			vo.setNoticeAt("N");
		}
		//위치표시 default N
		if(vo.getLcIndictAt() == null || "".equals(vo.getLcIndictAt())) {
			vo.setLcIndictAt("N");
		}
		//팝업 default N
		if(vo.getPopupAt() == null || "".equals(vo.getPopupAt())) {
			vo.setPopupAt("N");
		}
		
		nttDAO.insertNtt(vo);
		
	}

	/**
	 * 게시물 상세 조회
	 * @param vo 검색VO
	 * @return NttVO 게시물VO
	 * @throws SQLException
	 */
	@Override
	public NttVO selectNtt(SearchVO vo) throws SQLException {
		return nttDAO.selectNtt(vo);
	}

	/**
	 * 게시물 조회수 증가
	 * @param vo 검색VO
	 * @throws SQLException
	 */
	@Override
	public void incrementNttRdcnt(SearchVO vo) throws SQLException {
		nttDAO.incrementNttRdcnt(vo);
	}

	/**
	 * 입력된 정보로 게시물 수정
	 * @param vo 게시물VO
	 * @param multiReq Multipart Request
	 * @throws SQLException
	 */
	@Override
	public void updateNtt(NttVO vo, MultipartHttpServletRequest multiReq) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		
		//첨부파일 삭제
		egovFileMngUtil.deleteMultiFiles(multiReq.getParameter("deleteFileArr"));
		
		//첨부파일 수정
		String atchFileId = "";
		try {
			atchFileId = egovFileMngUtil.updateMultiFiles(multiReq, "NTT_", vo.getAtchFileId(), true);
			if(atchFileId != null && !"".equals(atchFileId)){
				vo.setAtchFileId(atchFileId);
			}
		} catch (FdlException e) {
			new Loggable().exLogging("updateNtt", e);
		} catch (IllegalStateException e) {
			new Loggable().exLogging("updateNtt", e);
		} catch (IOException e) {
			new Loggable().exLogging("updateNtt", e);
		}
		
		//비밀글 default N
		if(vo.getSecretAt() == null || "".equals(vo.getSecretAt())) {
			vo.setSecretAt("N");
		}
		//공지글 default N
		if(vo.getNoticeAt() == null || "".equals(vo.getNoticeAt())) {
			vo.setNoticeAt("N");
		}
		//위치표시 default N
		if(vo.getLcIndictAt() == null || "".equals(vo.getLcIndictAt())) {
			vo.setLcIndictAt("N");
		}
		//팝업 default N
		if(vo.getPopupAt() == null || "".equals(vo.getPopupAt())) {
			vo.setPopupAt("N");
		}
		
		nttDAO.updateNtt(vo);
	}

	/**
	 * 입력된 정보로 게시물 삭제
	 * @param vo 게시물VO
	 * @throws SQLException
	 */
	@Override
	public void deleteNtt(NttVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		
		nttDAO.deleteNtt(vo);
	}

	/**
	 * 게시물 총 갯수 조회 (게시판 구분X)
	 * @return int 게시물 총 갯수
	 * @throws SQLException
	 */
	@Override
	public int selectNttListTotCntNonBbsId() throws SQLException {
		return nttDAO.selectNttListTotCntNonBbsId();
	}
	
}













