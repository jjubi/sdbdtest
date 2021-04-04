package egovframework.vaiv.kr.cmmn.sys.excp.web;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetVisibility;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.vaiv.kr.cmmn.common.util.ExcelUtil;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;
import egovframework.vaiv.kr.cmmn.prhibtWrd.service.PrhibtWrdVO;
import egovframework.vaiv.kr.cmmn.sys.excp.service.SysExcpService;
import egovframework.vaiv.kr.cmmn.sys.excp.service.SysExcpVO;

/**
 * Exception 관리 / 관리자 : Exception 관리에 대한 요청을 처리하는 Controller
 * @category 공통
 * @author kmk
 * @since 2021-01-29
 * @version : v1.0
 * @see
 * <pre>
 *  ******************************************
 *  수정 이력
 *  
 *  수정일                  수정자                 수정내용
 *  ------------------------------------------
 *  2021.01.29    kmk           최초 등록
 * 
 * 
 *  ******************************************
 *  Copyright 2020 VAIV Co.
 *  All rights reserved
 * </pre>
 */

@Controller
@RequestMapping("/cmmn/adm/sys/excp/")
public class SysExcpController extends Loggable{

	/* 시스템 설정 관리 서비스 선언 */
	@Resource(name="SysExcpService")
	private SysExcpService sysExcpService;
	
	/* 메시지 서비스 선언 */
	@Resource(name="egovMessageSource")
	private EgovMessageSource egovMessageSource;
	
	/**
	 * exception 관리 메인 페이지 (목록)
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return sysExcpMain
	 * @throws SQLException
	 */
	@RequestMapping("sysExcpMain")
	public String sysCmptnMain(@ModelAttribute("searchVO") SysExcpVO searchVO
			, ModelMap model) throws SQLException {
		
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		//paging 
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		//게시글 목록
		List<SysExcpVO> sysCmptnList = sysExcpService.selectSysExcpList(searchVO);
		model.addAttribute("sysExcpList", sysCmptnList);
		
		//게시글 총 갯수
		int totCnt = sysExcpService.selectSysExcpListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		
        model.addAttribute("paginationInfo", paginationInfo);
		
		
		return "vaiv/cmmn/adm/sys/excp/sysExcpMain.adm";
	}
	
	/**
	 * Exception 관리 상세 화면
	 * @param searchVO 검색VO
	 * @param model 화면 모델
	 * @return sysExcpDetail
	 */
	@RequestMapping(value="sysExcpDetail")
	public String sysExcpDetail(@ModelAttribute("searchVO") SysExcpVO searchVO
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
			
		try {
			SysExcpVO sysExcpVO = sysExcpService.selectSysExcp(searchVO);
			model.addAttribute("sysExcpVO", sysExcpVO);
		} catch (SQLException e) {
			exLogging("sysExcpDetail", e);
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.select"));
		}
				
		return "/vaiv/cmmn/adm/sys/excp/sysExcpDetail.adm";
	}
	
	/**
	 * 엑셀 업로드 (Ajax)
	 * @param mulRequest Multipart Request
	 * @param model 화면 모델
	 * @return jsonView
	 */
	@RequestMapping(value="excelDataAjax", method=RequestMethod.POST)
	public String insertPrhibtWrdExcelDataAjax(MultipartHttpServletRequest mulRequest
			, ModelMap model) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			model.addAttribute("result", "fail");
			return "jsonView";
		}
		
		Map<String, List<Map>> getData = getExcelData(mulRequest);
		
		return "jsonView";
	}
	
	/**
	 * 버스운행 엑셀파일 데이터 추출
	 * @param mulRequest Multipart Request
	 * @return String
	 */
	private Map<String, List<Map>> getExcelData(MultipartHttpServletRequest mulRequest) {
		Map<String, List<Map>> returnStr = null;
		final Map<String, MultipartFile> files = mulRequest.getFileMap();
		if(files.isEmpty()) {
			return null;
		}
		
		MultipartFile file = null;
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		
		Map<String, List<Map>> data = new LinkedHashMap<String, List<Map>>();
		Map<String, List<Map>> data2 = new LinkedHashMap<>();
		String[] time = null;
		
		try {
			while(itr.hasNext()) {
				Entry<String, MultipartFile> entry = itr.next();
				//파일 가져오기 
				file = entry.getValue();
				//workbook 가져오기
				Workbook wb = ExcelUtil.getWorkbook(file);
				
				//활성화된 마지막 sheet 가져오기
				int lastSheetNum = 0;
				for(int i = 0; i < wb.getNumberOfSheets(); i++) {
					if(wb.getSheetVisibility(i).equals(SheetVisibility.VISIBLE)) {
						lastSheetNum = i;
					}
				}
				Sheet sheet = (Sheet) wb.getSheetAt(lastSheetNum);
				
				//버스운행 배차표 데이터 추출
				for(int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
					Row row = sheet.getRow(i);
					//시간 데이터 추출을 위한 배열 생성
					if(i == 6) {
						time = new String[row.getLastCellNum()];
					}
					
					if(row != null) {
						boolean checkB = false;
						String checkStr = "";
						for(int j = 0; j < row.getLastCellNum(); j++) {
							Cell cell = row.getCell(j);
							String value = ExcelUtil.cellValue(cell);
							
							if(i == 6 && j > 0 && !value.equals("") && time != null) {
								//시간 데이터 저장
								//12 -> j==1 time=4, j=2 time=4, j=12 time=4, j=13 time=5, j=25 time=6
								time[j] = (4 + ((j%12 == 0) ? (j/12) - 1 : j/12) + "") + "-" + value;
							} else if(i > 7 && j == 0) {
								checkStr = (value == null || value.equals("")) ? "row=" + i : value;
								if(data.get(checkStr) != null) {
									break;
								}
								checkB = true;
								data.put((value == null || value.equals("")) ? "row=" + i : value, new ArrayList<>());
							}
							
							if(cell != null) {
								//배차표 데이터 저장
								if(i > 6 && j > 0 && checkB) {
									if(cell != null) {
										Map<String, String> tmpMap = new HashMap<>();
										tmpMap.put("cellNo", "" + j);
										tmpMap.put("value", value);
										if(time.length > j) {
											tmpMap.put("time", time[j]);
										}
										tmpMap.put("color", ExcelUtil.getFillForegroundColorARGBHex(cell));
										List tmp = data.get(checkStr);
										tmp.add(tmpMap);
										data.put(checkStr, tmp);
									}
								}
							}
						}
					}
				}
			}
			//추가 데이터 핸들링이 필요한 부분입니다~
			
			Map<String, List<Map>> busmap = new LinkedHashMap<>();
			//임시 표출을 위한 코드 (
	        for(String key : data.keySet()){
	            System.out.println(String.format("%s = %s", key, data.get(key)));
	            String color = "";
	            List<Map> inBusMap = new ArrayList<>();
	            int addCnt = 0;
	            for(Map map : data.get(key)) {
	            	if(map.get("color") != null && !map.get("color").equals("")) {
	            		String nowColor = (String) map.get("color");
	            		if(!color.equals(nowColor)) {
	            			color = nowColor;
	            			Map inMap = new HashMap<>();
	            			inMap.put("name", map.get("value"));
	            			inMap.put("cellNo", map.get("cellNo"));
	            			inMap.put("color", color);
	            			String mapTime = (String) map.get("time");
	            			if(mapTime != null && !"".equals(mapTime)) {
	            				String[] timeSplit = mapTime.split("-"); 
	            				int min = Integer.parseInt(timeSplit[1]);
	            				min = min - 5;
	            				inMap.put("stTime", timeSplit[0] + ":" + min);
	            				if(addCnt != 0) {
		            				Map preMap = (Map) inBusMap.get(addCnt-1);
		            				if(preMap.get("edTime") == null || "".equals(preMap.get("edTime"))) {
		            					preMap.put("edTime", timeSplit[0] + ":" + min);
		            				}
		            			}
	            			}
	            			inBusMap.add(inMap);
	            			addCnt++;
	            		}
	            	} else if(addCnt != 0 && map.get("value").equals("휴")) {
            			Map preMap = (Map) inBusMap.get(addCnt-1);
            			if(preMap.get("edTime") == null || "".equals(preMap.get("edTime"))) {
		            		String mapTime = (String) map.get("time");
		            		if(mapTime != null && !"".equals(mapTime)) {
			            		String[] timeSplit = mapTime.split("-"); 
		        				int min = Integer.parseInt(timeSplit[1]);
		        				min = min - 5;
	            				preMap.put("edTime", timeSplit[0] + ":" + min);
		            		}
            			}
	            	}
	            }
	            if(!inBusMap.isEmpty()) {
	            	if(!key.contains("row=")) {
	            		busmap.put(key, inBusMap);
	            	}
	            }
	        }
	        System.out.println("==========================================================================");
	        for(String key : busmap.keySet()){
	        	System.out.println(String.format("%s = %s", key, busmap.get(key)));
	        }
	        returnStr = busmap;
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.out.println(data.toString());
			System.out.println(time);
		}
		
		return returnStr;
	}
	
}
