package egovframework.vaiv.kr.cmmn.data.vsltn.service.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.data.vsltn.service.DataVsltnCnrsVO;
import egovframework.vaiv.kr.cmmn.data.vsltn.service.DataVsltnService;

/**
 * 데이터 시각화 관리 / 관리자 : 데이터 시각화에 대한 비지니스 클래스 정의
 * @category 공통
 * @author jo
 * @since 2021-03-22
 * @version v1.0
 * @see
 * <pre>
 *  ******************************************
 *  수정 이력
 *  
 *  수정일                  수정자                 수정내용
 *  ------------------------------------------
 *  2021.03.22    jo           최초 등록
 * 
 * 
 *  ******************************************
 *  Copyright 2020 VAIV Co.
 *  All rights reserved
 * </pre>
 */
@Service("DataVsltnService")
public class DataVsltnServiceImpl implements DataVsltnService {
	
	@Resource(name="DataVsltnDAO")
	private DataVsltnDAO dataVsltnDAO;
	
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil egovFileMngUtil;

	/**
	 * 데이터 시각화 공유 URL 생성
	 * @param vo DataVsltnCnrsVO
	 * @param multiRequest 파일 request
	 * @return url key
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 */
	@Override
	public String insertDataVsltnCnrs(DataVsltnCnrsVO vo, MultipartHttpServletRequest multiRequest)
			throws SQLException, NoSuchAlgorithmException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser(); 
				
		//원본파일 저장
		String atchFileId = "";
		atchFileId = egovFileMngUtil.addFile(multiRequest, "VSLTN_");
		if(atchFileId != null && atchFileId != "") {
			vo.setVsltnOrignlFileId(atchFileId);
		}
		
		//url Key 생성
		String urlKey = EgovFileScrty.encryptPassword(vo.getVsltnOrignlFileId(), vo.getVsltnTy());//KISA 보안약점 조치 (2018-10-29, 윤창원)
		vo.setVsltnUrlKey(urlKey);
		
		//시각화 데이터 등록
		vo.setRegistId(user.getUniqId());
		vo.setUpdtId(user.getUniqId());
		
		dataVsltnDAO.insertDataVsltnCnrs(vo);
		
		return urlKey;
	}

	/**
	 * 데이터 시각화 공유 정보 가져오기
	 * @param vo 검색 객체
	 * @return 데이터 시각화 공유 객체
	 * @throws SQLException
	 */
	@Override
	public DataVsltnCnrsVO selectDataVsltnCnrs(DataVsltnCnrsVO vo) throws SQLException {
		return dataVsltnDAO.selectDataVsltnCnrs(vo);
	}

}
