package egovframework.vaiv.kr.cmmn.common.web;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.cmm.util.EgovWebUtil;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;

/**
 * 공통 파일 뷰 관리 : 공통 파일 뷰 관리에 대한 요청을 처리하는 Controller
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
@RequestMapping("/cmmn/com/cfc/")
public class ComFileController {
	
	/* 전자정부프레임워크 파일 관리 서비스 선언 */
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	
    /**
     * 첨부파일 목록 뷰
     * @param paramMap Request Value Map
     * @param model 화면 모델
     * @return loadType
     * @throws SQLException
     */
    @RequestMapping("nttFileList")
    public String selectNttFileList(@RequestParam Map<String, Object> paramMap
    		, ModelMap model) throws SQLException {
    	
    	String atchFileId = (String) paramMap.get("atchFileId");
    	String loadType = (String) paramMap.get("loadType");
    	String nttId = (String) paramMap.get("nttId");
    	
    	FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(atchFileId);
		List<FileVO> result = fileService.selectFileInfs(fileVO);
		
		model.addAttribute("fileList", result);
		
		if(nttId != null) {
			model.addAttribute("nttId", nttId);
		}
		
		if(loadType == null) {
			loadType = "viewFileList";
		}
							 
    	return "vaiv/cmmn/com/cfc/"+loadType;
    }
    
    /**
     * 이미지 파일 1개 가져오기
     * @param model
     * @param commandMap
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     */
    @RequestMapping("getOneImageFile")
    public void getImageInf(ModelMap model
    		, @RequestParam Map<String, Object> commandMap
    		, HttpServletRequest request
    		, HttpServletResponse response) throws SQLException, IOException {
    	
    	String atchFileId = (String)commandMap.get("atchFileId");
    	String fileOrdrNo = (String)commandMap.get("fileOrdrNo");
    	FileVO vo = new FileVO();

		vo.setAtchFileId(atchFileId);
		
		List<FileVO> fvo = fileService.selectImageFileList(vo);
		if(fvo != null && !fvo.isEmpty()) {
			if(fileOrdrNo != null && !"".equals(fileOrdrNo)) {
				returnImageFile(fvo.get(Integer.parseInt(fileOrdrNo)), request, response);
			} else {
				returnImageFile(fvo.get(0), request, response);
			}
		} else {
			returnImageFile(new FileVO(), request, response);
		}
    }
    
    private void returnImageFile(FileVO fvo, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	File file = null;
		FileInputStream fis = null;

		BufferedInputStream in = null;
		ByteArrayOutputStream bStream = null;
		
		try {
			if(fvo.getOrignlFileNm() == null || "".equals(fvo.getOrignlFileNm())) {
				fvo.setFileStreCours(request.getContextPath()+"/static/images/vaiv/cmmn/com/");
				fvo.setStreFileNm("no_image_photoG");
				fvo.setFileExtsn("png");
			}
			
			file = new File(fvo.getFileStreCours(), fvo.getStreFileNm() + "." + fvo.getFileExtsn());
		    fis = new FileInputStream(file);
		    
		    in = new BufferedInputStream(fis);
		    bStream = new ByteArrayOutputStream();

		    int imgByte;
		    while ((imgByte = in.read()) != -1) {
		    	bStream.write(imgByte);
		    }
		    
		    String type = "";

			if (fvo.getFileExtsn() != null && !"".equals(fvo.getFileExtsn())) {
			    if ("jpg".equals(fvo.getFileExtsn().toLowerCase())) {
			    	type = "image/jpeg";
			    } else {
			    	type = "image/" + fvo.getFileExtsn().toLowerCase();
			    }
			    /*type = "image/" + fvo.getFileExtsn().toLowerCase();*/

			} else {
				new Loggable().logging("Image fileType is null.");
			}
			
			response.setHeader("Content-Type", EgovWebUtil.removeCRLF(type));
			response.setContentLength(bStream.size());

			bStream.writeTo(response.getOutputStream());

			response.getOutputStream().flush();
			response.getOutputStream().close();

		    
		} finally {
			EgovResourceCloseHelper.close(bStream, in, fis);
		}
		
    }
    
    
}
