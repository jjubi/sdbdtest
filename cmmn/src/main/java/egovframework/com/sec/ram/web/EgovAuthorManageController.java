package egovframework.com.sec.ram.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sec.ram.service.AuthorHierarchyVO;
import egovframework.com.sec.ram.service.AuthorManage;
import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * 권한관리에 관한 controller 클래스를 정의한다.
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
 *   2011.8.26	정진오			IncludedInfo annotation 추가s
 *
 * </pre>
 */
 

@Controller
public class EgovAuthorManageController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "egovAuthorManageService")
    private EgovAuthorManageService egovAuthorManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Autowired
	private DefaultBeanValidator beanValidator;
    
    /**
	 * 권한 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/sec/ram/EgovAuthorListView.do")
    public String selectAuthorListView()
            throws Exception {
        return "egovframework/com/sec/ram/EgovAuthorManage.adm";
    }
    
    /**
	 * 권한 목록을 조회한다
	 * @param authorManageVO AuthorManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/sec/ram/selectEgovAuthorList.do")
    public String selectAuthorList(@ModelAttribute("authorManageVO") AuthorManageVO authorManageVO, 
    		                        ModelMap model)
            throws Exception {
    	
    	/** EgovPropertyService.sample */
    	//authorManageVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	//authorManageVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(authorManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(authorManageVO.getPageUnit());
		paginationInfo.setPageSize(authorManageVO.getPageSize());
		
		authorManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		authorManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		authorManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		authorManageVO.setAuthorManageList(egovAuthorManageService.selectAuthorList(authorManageVO));
        model.addAttribute("authorList", authorManageVO.getAuthorManageList());
        
        int totCnt = egovAuthorManageService.selectAuthorListTotCnt(authorManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
//        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

        return "egovframework/com/sec/ram/EgovAuthorManage.adm";
    } 
    
    /**
	 * 권한 세부정보를 조회한다.
	 * @param authorCode String
	 * @param authorManageVO AuthorManageVO
	 * @return String
	 * @exception Exception
	 */   
    @RequestMapping(value="/sec/ram/selectEgovAuthor.do")
    public String selectAuthor(@RequestParam("authorCode") String authorCode,
    	                       @ModelAttribute("authorManageVO") AuthorManageVO authorManageVO, 
    		                    ModelMap model) throws Exception {
    	
    	authorManageVO.setAuthorCode(authorCode);

    	model.addAttribute("authorManage", egovAuthorManageService.selectAuthor(authorManageVO));
//    	model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
    	return "egovframework/com/sec/ram/EgovAuthorUpdate.adm";
    }     

    /**
	 * 권한 등록화면 이동
	 * @return String
	 * @exception Exception
	 */     
    @RequestMapping("/sec/ram/EgovAuthorInsertView.do")
    public String insertAuthorView(@ModelAttribute("authorManage") AuthorManage authorManage)
            throws Exception {
        return "egovframework/com/sec/ram/EgovAuthorInsert.adm";
    }
    
    /**
	 * 권한 세부정보를 등록한다.
	 * @param authorManage AuthorManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */ 
    @RequestMapping(value="/sec/ram/insertEgovAuthor.do")
    public String insertAuthor(@ModelAttribute("authorManage") AuthorManage authorManage, 
    		                    BindingResult bindingResult,
    		                    ModelMap model) throws Exception {
    	
    	beanValidator.validate(authorManage, bindingResult); //validation 수행
    	
		if (bindingResult.hasErrors()) { 
			return "egovframework/com/sec/ram/EgovAuthorInsert.adm";
		} else {
	    	egovAuthorManageService.insertAuthor(authorManage);
	        model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
	        return "forward:/sec/ram/selectEgovAuthorList.do";
		}
    }
    
    /**
	 * 권한 세부정보를 수정한다.
	 * @param authorManage AuthorManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */   
    @RequestMapping(value="/sec/ram/updateEgovAuthor.do")
    public String updateAuthor(@ModelAttribute("authorManage") AuthorManage authorManage, 
    		                    BindingResult bindingResult,
    		                    Model model) throws Exception {

    	beanValidator.validate(authorManage, bindingResult); //validation 수행
    	
		if (bindingResult.hasErrors()) {
			return "egovframework/com/sec/ram/EgovAuthorUpdate.adm";
		} else {
	    	egovAuthorManageService.updateAuthor(authorManage);
	        model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
	        return "forward:/sec/ram/selectEgovAuthorList.do";
		}
    }    

    /**
	 * 권한 세부정보를 삭제한다.
	 * @param authorManage AuthorManage
	 * @return String
	 * @exception Exception
	 */  
    @RequestMapping(value="/sec/ram/deleteEgovAuthor.do")
    public String deleteAuthor(@ModelAttribute("authorManage") AuthorManage authorManage, 
    		                    Model model) throws Exception {
    	
    	egovAuthorManageService.deleteAuthor(authorManage);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
        return "forward:/sec/ram/selectEgovAuthorList.do";
    }   
    
    /**
	 * 권한목록을 삭제한다.
	 * @param authorCodes String
	 * @param authorManage AuthorManage
	 * @return String
	 * @exception Exception
	 */  
    @RequestMapping(value="/sec/ram/EgovAuthorListDelete.do")
    public String deleteAuthorList(@RequestParam("authorCodes") String authorCodes,
    		                       @ModelAttribute("authorManage") AuthorManage authorManage, 
    		                        Model model) throws Exception {

    	String [] strAuthorCodes = authorCodes.split(";");
    	for(int i=0; i<strAuthorCodes.length;i++) {
    		authorManage.setAuthorCode(strAuthorCodes[i]);
    		egovAuthorManageService.deleteAuthor(authorManage);
    	}
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
        return "forward:/sec/ram/selectEgovAuthorList.do";
    }    
    
    /**
	 * 권한제한 화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/sec/ram/accessDenied.do")
    public String accessDenied()
            throws Exception {
        return "egovframework/com/cmm/error/accessDenied";
    } 
    
    /**
	 * 권한 코드 중복 체크
	 * */
	@RequestMapping(value="/sec/ram/checkAuthorCodeAjax.do", method=RequestMethod.POST)
	public ModelAndView checkAuthorCodeAjax(AuthorManage searchVO
			, ModelAndView andView) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			andView.addObject("result", "fail");
			return andView;
		}
				
		try{
			int checkCnt = egovAuthorManageService.checkAuthorCode(searchVO);
			if(checkCnt > 0) {
				andView.addObject("result", "fail");
			} else {
				andView.addObject("result", "success");
			}
		} catch(SQLException e){
			new Loggable().exLogging("checkAuthorCodeAjax", e);
			andView.addObject("result", "fail");
		}
		
		andView.setViewName("jsonView");
		return andView;
	}
	
	/**
	 * 계층 권한 목록 불러오기
	 * */
	@RequestMapping(value="/sec/ram/selectAuthorHierarchyListAjax.do", method=RequestMethod.POST)
	public ModelAndView selectAuthorHierarchyListAjax(ModelAndView andView) {
		//인증된 사용자 여부 판단
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			andView.addObject("result", "fail");
			return andView;
		}
		
		try{
			
			List<AuthorManageVO> authorList = egovAuthorManageService.selectAuthorAllList(new AuthorManageVO());
			andView.addObject("authorList", authorList);
			
			List<AuthorHierarchyVO> hierarchyList = egovAuthorManageService.selectAuthorHierarchyList();
			andView.addObject("hierarchyList", hierarchyList);
			
			andView.addObject("result", "success");
		} catch(SQLException e){
			new Loggable().exLogging("selectAuthorHierarchyListAjax", e);
			andView.addObject("result", "fail");
		}
		
		andView.setViewName("jsonView");
		return andView;
	}
	
	/**
	 * 계층 권한 저장
	 * */
	@RequestMapping(value="/sec/ram/insertAuthorHierarchy.do", method=RequestMethod.POST)
	public String insertAuthorHierarchy(AuthorHierarchyVO insertVO
			, ModelMap model) {
		if(!EgovUserDetailsHelper.isAuthenticated()) {
			return "redirect:/uat/uia/egovLoginUsr.do";
		}
		
		try {
			egovAuthorManageService.insertAuthorHierarchy(insertVO);
			
		} catch(SQLException e) {
			new Loggable().exLogging("insertAuthorHierarchy", e);
			return "egovframework/com/cmm/error/egovError";
		}
		
		return "redirect:/sec/ram/selectEgovAuthorList.do";
	}
	
	
	
	
	
	
	
}
