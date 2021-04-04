package egovframework.com.sec.ram.web;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.AuthorRoleManage;
import egovframework.com.sec.ram.service.AuthorRoleManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;
import egovframework.com.sec.ram.service.EgovAuthorRoleManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.intercept.EgovReloadableFilterInvocationSecurityMetadataSource;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 권한별 롤관리에 관한 controller 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이문준          최초 생성
 *   2011.09.07  서준식          롤 등록시 이미 등록된 경우 데이터 중복 에러 발생 문제 수정
 * </pre>
 */
@Controller
public class EgovAuthorRoleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovAuthorRoleController.class);
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "egovAuthorRoleManageService")
    private EgovAuthorRoleManageService egovAuthorRoleManageService;
    
    @Resource(name = "egovAuthorManageService")
    private EgovAuthorManageService egovAuthorManageService;
    
    @Resource(name = "databaseSecurityMetadataSource")
    private EgovReloadableFilterInvocationSecurityMetadataSource databaseSecurityMetadataSource;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
	 * 권한 롤 관계 화면 이동
	 * @return "egovframework/com/sec/ram/EgovDeptAuthorList"
	 * @exception Exception
	 */
    @RequestMapping("/sec/ram/EgovAuthorRoleListView.do")
    public String selectAuthorRoleListView() throws Exception {

        return "egovframework/com/sec/ram/EgovAuthorRoleManage";
    } 

	/**
	 * 권한별 할당된 롤 목록 조회
	 * 
	 * @param authorRoleManageVO AuthorRoleManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/sec/ram/EgovAuthorRoleList.do")
	public String selectAuthorRoleList(@ModelAttribute("authorRoleManageVO") AuthorRoleManageVO authorRoleManageVO,
			                            ModelMap model) throws Exception {

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(authorRoleManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(authorRoleManageVO.getPageUnit());
		paginationInfo.setPageSize(authorRoleManageVO.getPageSize());
		
		authorRoleManageVO.setFirstIndex(0);
		authorRoleManageVO.setLastIndex(1000);
		authorRoleManageVO.setRecordCountPerPage(1000);
		
		authorRoleManageVO.setAuthorRoleList(egovAuthorRoleManageService.selectAuthorRoleList(authorRoleManageVO));
        model.addAttribute("authorRoleList", authorRoleManageVO.getAuthorRoleList());
        model.addAttribute("searchVO", authorRoleManageVO);

        AuthorManageVO authorManageVO = new AuthorManageVO();
        authorManageVO.setAuthorCode(authorRoleManageVO.getSearchKeyword());
    	model.addAttribute("authorManage", egovAuthorManageService.selectAuthor(authorManageVO));
        
        int totCnt = egovAuthorRoleManageService.selectAuthorRoleListTotCnt(authorRoleManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

//        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
        
        return "egovframework/com/sec/ram/EgovAuthorRoleManage.adm";
	}
    
	/**
	 * 권한정보에 롤을 할당하여 데이터베이스에 등록
	 * @param authorCode String
	 * @param roleCodes String
	 * @param regYns String
	 * @param authorRoleManage AuthorRoleManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value="/sec/ram/EgovAuthorRoleInsert.do")
	public String insertAuthorRole(@RequestParam("authorCode") String authorCode,
			                       @RequestParam("roleCodes") String roleCodes,
			                       @RequestParam("regYns") String regYns,
			                       @RequestParam Map<?, ?> commandMap,
			                       @ModelAttribute("authorRoleManage") AuthorRoleManage authorRoleManage,
			                         ModelMap model) throws Exception {
		
    	String [] strRoleCodes = roleCodes.split(";");
    	String [] strRegYns = regYns.split(";");
    	    	
    	authorRoleManage.setRoleCode(authorCode);
    	
    	for(int i=0; i<strRoleCodes.length;i++) {
    		
    		authorRoleManage.setRoleCode(strRoleCodes[i]);
    		authorRoleManage.setRegYn(strRegYns[i]);
    		if(strRegYns[i].equals("Y")){
    			egovAuthorRoleManageService.deleteAuthorRole(authorRoleManage);//2011.09.07
    			egovAuthorRoleManageService.insertAuthorRole(authorRoleManage);
    		}else {
    			egovAuthorRoleManageService.deleteAuthorRole(authorRoleManage);
    		}
    	}
    	
    	databaseSecurityMetadataSource.reload();

		return "redirect:/sec/ram/EgovAuthorRoleList.do?searchKeyword="+authorCode;
	}    
}