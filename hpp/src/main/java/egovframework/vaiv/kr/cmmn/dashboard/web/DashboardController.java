package egovframework.vaiv.kr.cmmn.dashboard.web;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.scripting.xmltags.WhereSqlNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mongodb.DBCollection;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.bbs.ntt.service.NttService;
import egovframework.vaiv.kr.cmmn.popup.service.PopupService;
import egovframework.vaiv.kr.cmmn.popup.service.PopupVO;
import egovframework.vaiv.kr.cmmn.stats.service.StatsService;

/**
 * 대쉬보드 관리 / 관리자 : 대쉬보드 관리에 대한 요청을 처리하는 Controller
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
@Controller
@RequestMapping("/cmmn/adm/dashboard/")
public class DashboardController extends Loggable{
	/* 게시물 서비스 선언 */
	@Resource(name="NttService")
	private NttService nttService;
	
	/* 팝업 서비스 선언 */
	@Resource(name="PopupService")
	private PopupService popupService;
	
	/* 통계 서비스 선언 */
	@Resource(name="StatsService")
	private StatsService statsService;
	
//	@Resource(name="mongoTemplate")
//	private MongoTemplate mongodb;
	
	/**
	 * 대쉬보드 메인
	 * @param model 화면 모델
	 * @return dashboardMain
	 */
	@RequestMapping(value="dashboardMain")
	public String dashboardMain(ModelMap model) {
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
//			Query query = new Query(Criteria.where("1").is("1"));
//			List<EgovMap> maps = mongodb.findAll(EgovMap.class, "test");
//			System.out.println(maps);
			//총 게시물 수
			model.addAttribute("nttTotCnt", nttService.selectNttListTotCntNonBbsId());
			//총 팝업 수
			PopupVO selectPopupVO = new PopupVO();
			selectPopupVO.setPdCondition("Y");
			selectPopupVO.setUseAt("Y");
			model.addAttribute("popupTotCnt", popupService.selectPopupListTotCnt(selectPopupVO));	
			//총 회원 수
			model.addAttribute("mberTotCnt", statsService.selectMberTotCntToView());
			
		} catch (SQLException e) {
			exLogging("dashboard", e);
		}
		
		return "vaiv/cmmn/adm/dashboard/dashboardMain.adm";
	}
	
	
	
}
