package egovframework.vaiv.kr.cmmn.banner.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.banner.service.BannerService;
import egovframework.vaiv.kr.cmmn.banner.service.BannerVO;

/**
 * 배너 관리 : 배너관리에 대한 비지니스 클래스 정의
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
@Service("BannerService")
public class BannerServiceImpl implements BannerService {
	/* 배너 DAO 선언 */
	@Resource(name="BannerDAO")
	private BannerDAO BannerDAO;
	
	/* 전자정부 파일 util 선언 */
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil egovFileMngUtil;

	/**
	 * 입력한 조건에 맞는 배너 목록을 조회
	 * @param vo 배너VO
	 * @return List of BannerVO 배너목록
	 * @exception SQLException
	 * */
	@Override
	public List<BannerVO> selectBannerList(BannerVO vo) throws SQLException {
		return BannerDAO.selectBannerList(vo);
	}

	/**
	 * 입력한 조건에 맞는 배너 목록 총 갯수 조회
	 * @param vo 배너VO
	 * @return int 배너 총 갯수
	 * @exception SQLException
	 * */
	@Override
	public int selectBannerListTotCnt(BannerVO vo) throws SQLException {
		return BannerDAO.selectBannerListTotCnt(vo);
	}

	/**
	 * 입력된 정보로 배너 등록
	 * @param vo 등록 배너VO
	 * @param multiReq Multipart Request
	 * @throws SQLException
	 */
	@Override
	public void insertBanner(BannerVO vo, MultipartHttpServletRequest multiReq) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		vo.setRegistId(user.getUniqId());
		vo.setUpdtId(user.getUniqId());
		
		//첨부파일 저장
		String atchFileId = "";
		atchFileId = egovFileMngUtil.addFile(multiReq, "BNR_");
		if(atchFileId != null && atchFileId != "") {
			vo.setAtchFileId(atchFileId);
		}
		
		BannerDAO.insertBanner(vo);
				
	}

	/**
	 * 입력된 정보의 배너 상세 조회
	 * @param vo 배너VO
	 * @return BannerVO
	 * @throws SQLException
	 */
	@Override
	public BannerVO selectBanner(BannerVO vo) throws SQLException {
		return BannerDAO.selectBanner(vo);
	}

	/**
	 * 입력된 정보로 배너 수정
	 * @param vo 수정 배너VO
	 * @param multiReq Multipart Request
	 * @throws SQLException
	 */
	@Override
	public void updateBanner(BannerVO vo, MultipartHttpServletRequest multiReq) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		vo.setUpdtId(user.getUniqId());
		
		//첨부파일 저장
		String atchFileId = "";
		atchFileId = egovFileMngUtil.addFile(multiReq, "BNR_");
		if(atchFileId != null && atchFileId != "") {
			vo.setAtchFileId(atchFileId);
		}
		
		BannerDAO.updateBanner(vo);
		
	}

	/**
	 * 입력된 정보의 배너 삭제
	 * @param vo 삭제 배너VO
	 * @throws SQLException
	 */
	@Override
	public void deleteBanner(BannerVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		vo.setUpdtId(user.getUniqId());
		
		BannerDAO.deleteBanner(vo);
	}

	/**
	 * 입력된 정보로 배너 순서 수정
	 * @param vo 배너VO
	 * @return int 수정된 배너 수
	 * @throws SQLException
	 */
	@Override
	public int updateBannerOrdr(BannerVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		int updateCnt = 0;
		if(vo.getBannerOrdr() != null && !"".equals(vo.getBannerOrdr())) {
			int ordrIdx = 1;
			for(String ordrSeqNo : vo.getBannerOrdr().split("-")) {
				BannerVO updateVO = new BannerVO();
				updateVO.setBannerSeqNo(ordrSeqNo);
				updateVO.setBannerOrdr(String.valueOf(ordrIdx++));
				updateVO.setUpdtId(user.getUniqId());
				updateCnt += BannerDAO.updateBannerOrdr(updateVO);
			}
		}
		
		return updateCnt;
	}

	/**
	 * 입력된 정보로 배너 사용여부 수정
	 * @param vo 배너VO
	 * @return int 수정된 배너 수
	 * @throws SQLException
	 */
	@Override
	public int updateBannerUseAt(BannerVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		
		return BannerDAO.updateBannerUseAt(vo);
	}
}
