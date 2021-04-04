package egovframework.vaiv.kr.cmmn.banner.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 배너 관리 : 배너관리에 대한 인터페이스 정의
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
public interface BannerService {
	/**
	 * 입력한 조건에 맞는 배너 목록을 조회
	 * @param vo 배너VO
	 * @return List of BannerVO 배너목록
	 * @throws SQLException
	 * */
	public List<BannerVO> selectBannerList(BannerVO vo) throws SQLException;
	
	/**
	 * 입력한 조건에 맞는 배너 목록 총 갯수 조회
	 * @param vo 배너VO
	 * @return int 배너 총 갯수
	 * @throws SQLException
	 * */
	public int selectBannerListTotCnt(BannerVO vo) throws SQLException;
	
	/**
	 * 입력된 정보로 배너 등록
	 * @param vo 등록 배너VO
	 * @param multiReq Multipart Request
	 * @throws SQLException
	 */
	public void insertBanner(BannerVO vo, MultipartHttpServletRequest multiReq) throws SQLException;
	
	/**
	 * 입력된 정보의 배너 상세 조회
	 * @param vo 배너VO
	 * @return BannerVO
	 * @throws SQLException
	 */
	public BannerVO selectBanner(BannerVO vo) throws SQLException;
	
	/**
	 * 입력된 정보로 배너 수정
	 * @param vo 수정 배너VO
	 * @param multiReq Multipart Request
	 * @throws SQLException
	 */
	public void updateBanner(BannerVO vo, MultipartHttpServletRequest multiReq) throws SQLException;
	
	/**
	 * 입력된 정보의 배너 삭제
	 * @param vo 삭제 배너VO
	 * @throws SQLException
	 */
	public void deleteBanner(BannerVO vo) throws SQLException;
	
	/**
	 * 입력된 정보로 배너 순서 수정
	 * @param vo 배너VO
	 * @return int 수정된 배너 수
	 * @throws SQLException
	 */
	public int updateBannerOrdr(BannerVO vo) throws SQLException;

	/**
	 * 입력된 정보로 배너 사용여부 수정
	 * @param vo 배너VO
	 * @return int 수정된 배너 수
	 * @throws SQLException
	 */
	public int updateBannerUseAt(BannerVO vo) throws SQLException;
}
