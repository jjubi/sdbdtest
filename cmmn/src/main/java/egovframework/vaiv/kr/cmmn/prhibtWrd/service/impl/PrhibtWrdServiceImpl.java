package egovframework.vaiv.kr.cmmn.prhibtWrd.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.vaiv.kr.cmmn.prhibtWrd.service.PrhibtWrdService;
import egovframework.vaiv.kr.cmmn.prhibtWrd.service.PrhibtWrdVO;
import egovframework.vaiv.kr.cmmn.common.util.ExcelUtil;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;

/**
 * 금지단어 관리 / 관리자 : 금지단어 관리에 대한 비지니스 클래스 정의
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
@Service("PrhibtWrdService")
public class PrhibtWrdServiceImpl implements PrhibtWrdService{
	/* 금지단어 DAO 선언 */
	@Resource(name="PrhibtWrdDAO")
	private PrhibtWrdDAO prhibtWrdDAO;

	/**
	 * 금지단어 목록 조회
	 * @param vo 금지단어VO
	 * @return List of PrhibtWrdVO 금지단어VO 목록
	 * @throws SQLException
	 */
	@Override
	public List<PrhibtWrdVO> selectPrhibtWrdList(PrhibtWrdVO vo) throws SQLException {
		return prhibtWrdDAO.selectPrhibtWrdList(vo);
	}

	/**
	 * 금지단어 목록  총 갯수 조회
	 * @param vo 금지단어VO
	 * @return int 총 갯수
	 * @throws SQLException
	 */
	@Override
	public int selectPrhibtWrdListTotCnt(PrhibtWrdVO vo) throws SQLException {
		return prhibtWrdDAO.selectPrhibtWrdListTotCnt(vo);
	}

	/**
	 * 금지단어 중복 확인
	 * @param vo 금지단어VO
	 * @return boolean 중복여부(true/false)
	 * @throws SQLException
	 */
	@Override
	public boolean checkDupPrhibtWrd(PrhibtWrdVO vo) throws SQLException {
		boolean checkDup = false;
		//금지단어 조회
		int checkCnt = prhibtWrdDAO.checkDupPrhibtWrd(vo);
		if(checkCnt > 0) {
			checkDup = true;
		} 
		
		return checkDup;
	}

	/**
	 * 입력한 금지단어 등록
	 * @param vo 금지단어VO
	 * @return int 등록된 금지단어 수
	 * @throws SQLException
	 */
	@Override
	public int insertPrhibtWrd(PrhibtWrdVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setRegistId(user.getUniqId());
		vo.setUpdtId(user.getUniqId());
		
		prhibtWrdDAO.insertPrhibtWrd(vo);
		
		return Integer.parseInt(vo.getPrhibtWrdSeqNo());
	}

	/**
	 * 금지단어 상세 조회
	 * @param vo 금지단어VO
	 * @return PrhibtWrdVO 금지단어VO
	 * @throws SQLException
	 */
	@Override
	public PrhibtWrdVO selectPrhibtWrd(PrhibtWrdVO vo) throws SQLException {
		return prhibtWrdDAO.selectPrhibtWrd(vo);
	}

	/**
	 * 입력한 금지단어 수정
	 * @param vo 금지단어VO
	 * @return int 수정된 금지단어 수
	 * @throws SQLException
	 */
	@Override
	public int updatePrhibtWrd(PrhibtWrdVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		
		return prhibtWrdDAO.updatePrhibtWrd(vo);
	}
	
	/**
	 * 입력한 금지단어 삭제
	 * @param vo 금지단어VO
	 * @return int 삭제된 금지단어 수
	 * @throws SQLException
	 */
	@Override
	public int deletePrhibtWrd(PrhibtWrdVO vo) throws SQLException {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		vo.setUpdtId(user.getUniqId());
		
		return prhibtWrdDAO.deletePrhibtWrd(vo);
	}

	/**
	 * 금지단어 엑셀 업로드 등록
	 * @param mulRequest Multipart Request
	 * @return List of String 등록못한 금지단어 목록
	 * @throws SQLException
	 */
	@Override
	@Transactional(rollbackFor=RuntimeException.class)
	public List<String> insertPrhibtWrdExcelData(MultipartHttpServletRequest mulRequest) throws SQLException {
		List<String> errList = new ArrayList<String>();
		
		final Map<String, MultipartFile> files = mulRequest.getFileMap();
		if(files.isEmpty()) {
			return null;
		}
		
		MultipartFile file = null;
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		
		try {
			while(itr.hasNext()) {
				Entry<String, MultipartFile> entry = itr.next();
				file = entry.getValue();
				
				Workbook wb = ExcelUtil.getWorkbook(file);
				Sheet sheet = (Sheet) wb.getSheetAt(0);
				
				for(int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
					Row row = sheet.getRow(i);
					String prhibtWrd = ExcelUtil.cellValue(row.getCell(1));
					if(!"".equals(prhibtWrd)) {
						//중복 체크
						PrhibtWrdVO checkVO = new PrhibtWrdVO();
						checkVO.setPrhibtWrd(prhibtWrd);
						int checkCnt = prhibtWrdDAO.checkDupPrhibtWrd(checkVO);
						if(checkCnt == 0) {
							String useAt = ExcelUtil.cellValue(row.getCell(2));
							PrhibtWrdVO insertVO = new PrhibtWrdVO();
							insertVO.setPrhibtWrd(prhibtWrd);
							if(!"Y".equals(useAt) && !"N".equals(useAt)) {
								insertVO.setUseAt("Y");
							} else {
								insertVO.setUseAt(useAt);
							}
							LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
							insertVO.setRegistId(user.getUniqId());
							insertVO.setUpdtId(user.getUniqId());
							prhibtWrdDAO.insertPrhibtWrd(insertVO);							
						} else {
							errList.add(i + "열 금지 단어 중복.");
						}
					} else {
						errList.add(i + "열 금지 단어 없음.");
					}
				}
			}
			
		} catch (RuntimeException e) {
			new Loggable().exLogging("insertPrhibtWrdExcelData", e);
			errList = null;
		}
		
		return errList;
	}

	/**
	 * 금지단어 포함 여부 확인
	 * @param checkStr 입력단어
	 * @return List of String 포함된 금지단어 목록
	 * @throws SQLException
	 */
	@Override
	public List<String> checkContainsPrhibtWrd(String checkStr) throws SQLException {
		PrhibtWrdVO sPrhibtWrdVO = new PrhibtWrdVO();
		sPrhibtWrdVO.setPagingYn("N");
		sPrhibtWrdVO.setUseAt("Y");
		List<PrhibtWrdVO> prhibtWrdList = prhibtWrdDAO.selectPrhibtWrdList(sPrhibtWrdVO);
		
		List<String> containPrhbWrdList = new ArrayList<String>();
		for(PrhibtWrdVO vo : prhibtWrdList) {
			String prhibtWrd = vo.getPrhibtWrd();
			if(checkStr.indexOf(prhibtWrd) > -1) {
				containPrhbWrdList.add(prhibtWrd);
			}
		}
		
		if(containPrhbWrdList.size() == 0) {
			containPrhbWrdList = null;
		}
		
		return containPrhbWrdList;
	}

}
