package egovframework.vaiv.kr.cmmn.menu.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import egovframework.com.cmm.util.EgovWebUtil;
import egovframework.vaiv.kr.cmmn.menu.service.MenuService;
import egovframework.vaiv.kr.cmmn.menu.service.MenuVO;

/**
 * 메뉴 관리 / 관리자 : 메뉴 관리에 대한 비지니스 클래스 정의
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
@Service("MenuService")
public class MenuServiceImpl implements MenuService {

	private int menuOrdr = 0;			//메뉴 순서
	/* 메뉴 DAO */
	@Resource(name="MenuDAO")
	private MenuDAO menuDAO;
	
	/**
	 * 메뉴 목록 조회
	 * @param vo MenuVO
	 * @return List of MenuVO
	 * @throws SQLException
	 */
	@Override
	public List<MenuVO> selectMenuList(MenuVO vo) throws SQLException {
		return menuDAO.selectMenuList(vo);
	}
	
	/**
	 * 입력된 정보로 메뉴 목록 저장
	 * @param menuListToJson 메뉴 목록 정보 JSON 문자열
	 * @throws SQLException
	 */
	@Override
	public void insertMenu(String menuListToJson) throws SQLException {
		Gson gson = new Gson();
		JsonArray jsonArr = gson.fromJson(menuListToJson.replaceAll("&quot;", "\""), JsonArray.class);	
		menuOrdr = 0;
		insertMenuObjectArr(jsonArr, menuOrdr, 1, 0);
	}
	
	/**
	 * 메뉴 목록 데이터 저장 전 정제 (JSON -> VO)
	 * @param jsonArr 메뉴 목록 JSON 데이터
	 * @param menuOrdr 메뉴 순서
	 * @param menuDp 메뉴 Depth
	 * @param upperSeqNo 상위 메뉴 일련번호
	 * @throws SQLException
	 */
	private void insertMenuObjectArr(JsonArray jsonArr, int menuOrdr, int menuDp, int upperSeqNo) throws SQLException {
		for(int i = 0; i < jsonArr.size(); i++) {
			JsonObject jsonObj = (JsonObject) jsonArr.get(i);
			System.out.println("menuDp : " + menuDp + "jsonObj" + jsonObj);
			
			//VO 생성
			MenuVO insertVO = new MenuVO();
			insertVO = setMenuVO(jsonObj, ++this.menuOrdr, upperSeqNo, menuDp);
			
			if(insertVO.getMenuSeqNo() == 0) {
				menuDAO.insertMenu(insertVO);
			} else {
				menuDAO.updateMenu(insertVO);
			}
			
			int insertSn = insertVO.getMenuSeqNo(); 
			
			if(jsonObj.get("children") != null) {
				Gson gson = new Gson();
				JsonArray children_jsonArr = gson.fromJson(String.valueOf(jsonObj.get("children")), JsonArray.class);
				insertMenuObjectArr(children_jsonArr, this.menuOrdr, menuDp + 1, insertSn);
			}
		}
	}
	
	/**
	 * 저장 메뉴VO 설정 (JSON -> VO)
	 * @param jsonObj 메뉴 JSON 데이터
	 * @param menuOrdr 메뉴순서
	 * @param upperSeqNo 상위메뉴일련번호
	 * @param menuDp 메뉴깊이
	 * @return
	 */
	private MenuVO setMenuVO(JsonObject jsonObj, int menuOrdr, int upperSeqNo, int menuDp) {
		MenuVO vo = new MenuVO();
		System.out.println("========================="+jsonObj.get("menuSeqNo"));
		if(jsonObj.get("menuSeqNo") != null) {
			vo.setMenuSeqNo(jsonObj.get("menuSeqNo").getAsInt());
		}
		vo.setUpperSeqNo(upperSeqNo);
		vo.setMenuAuthor(jsonObj.get("menuAuthor").isJsonNull() ? null : jsonObj.get("menuAuthor").getAsString());
		vo.setMenuNm(jsonObj.get("menuNm").isJsonNull() ? null : jsonObj.get("menuNm").getAsString());
		vo.setMenuUrl(jsonObj.get("menuUrl").isJsonNull() ? null : jsonObj.get("menuUrl").getAsString());
		vo.setMenuTarget(jsonObj.get("menuTarget").isJsonNull() ? null : jsonObj.get("menuTarget").getAsString());
		vo.setMenuOrdr(menuOrdr);
		vo.setMenuDp(menuDp);
		vo.setUseAt(jsonObj.get("useAt").isJsonNull() ? null : jsonObj.get("useAt").getAsString());
		vo.setMenuIconClass(jsonObj.get("menuIconClass").isJsonNull() ? null : jsonObj.get("menuIconClass").getAsString());
		vo.setMenuSe(jsonObj.get("menuSe").isJsonNull() ? null : jsonObj.get("menuSe").getAsString());
		vo.setMenuTy(jsonObj.get("menuTy").isJsonNull() ? null : jsonObj.get("menuTy").getAsString());
		
		return vo;
	}

	/**
	 * 입력된 정보로 메뉴 데이터 수정
	 * @param menuToJson 메뉴 정보 JSON 문자열
	 * @throws SQLException
	 */
	@Override
	public void updateMenu(String menuToJson) throws SQLException {
		Gson gson = new Gson();
		menuToJson = EgovWebUtil.nonClearXSSMinimum(menuToJson);
		JsonObject jsonObj = gson.fromJson(menuToJson, JsonObject.class);	
		System.out.println(jsonObj);
		
		//메뉴 히스토리 저장
		int menuSeqNo = jsonObj.get("menuSeqNo").getAsInt();
		menuDAO.insertOneHistoryMenu(menuSeqNo);
		
		//메뉴 수정
		MenuVO updateVO = new MenuVO();
		updateVO.setMenuSeqNo(menuSeqNo);
		updateVO.setMenuAuthor(jsonObj.get("menuAuthor").isJsonNull() ? null : jsonObj.get("menuAuthor").getAsString());
		updateVO.setMenuNm(jsonObj.get("menuNm").isJsonNull() ? null : jsonObj.get("menuNm").getAsString());
		updateVO.setMenuUrl(jsonObj.get("menuUrl").isJsonNull() ? null : jsonObj.get("menuUrl").getAsString());
		updateVO.setMenuTarget(jsonObj.get("menuTarget").isJsonNull() ? null : jsonObj.get("menuTarget").getAsString());
		updateVO.setUseAt(jsonObj.get("useAt").isJsonNull() ? null : jsonObj.get("useAt").getAsString());
		updateVO.setMenuIconClass(jsonObj.get("menuIconClass").isJsonNull() ? null : jsonObj.get("menuIconClass").getAsString());
		updateVO.setMenuTy(jsonObj.get("menuTy").isJsonNull() ? null : jsonObj.get("menuTy").getAsString());
		
		menuDAO.updateMenu(updateVO);
		
	}
	
	/**
	 * 메뉴 데이터 삭제
	 * @param vo MenuVO
	 * @throws SQLException
	 */
	@Override
	public void deleteMenu(MenuVO vo) throws SQLException {
		//메뉴 삭제
		String[] menuDeleteSeqNos = vo.getMenuDeleteStr().split(",");
		if(menuDeleteSeqNos != null && menuDeleteSeqNos.length != 0) {
			MenuVO deleteVO = new MenuVO();
			for(String deleteSeqNo : menuDeleteSeqNos) {
				//메뉴 히스토리 저장
				menuDAO.insertOneHistoryMenu(Integer.parseInt(deleteSeqNo));
				//메뉴 삭제
				deleteVO.setMenuSeqNo(Integer.parseInt(deleteSeqNo));
				menuDAO.deleteMenu(deleteVO);
			}
		}
	}

	/**
	 * 설정한 메뉴 목록 데이터 가져오기 (로그 분기를 위한 생성)
	 * @param vo MenuVO
	 * @return List of MenuVO
	 * @throws SQLException
	 */
	@Override
	public List<MenuVO> viewMenuList(MenuVO vo) throws SQLException {
		vo.setUseAt("Y");
		vo.setSearchCondition("999");
		return menuDAO.selectMenuList(vo);
	}

}
