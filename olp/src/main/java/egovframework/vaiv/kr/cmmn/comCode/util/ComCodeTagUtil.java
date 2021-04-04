package egovframework.vaiv.kr.cmmn.comCode.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import egovframework.vaiv.kr.cmmn.comCode.service.ComCodeService;
import egovframework.vaiv.kr.cmmn.comCode.service.ComCodeVO;
import egovframework.vaiv.kr.cmmn.common.util.CommonUtil;

public class ComCodeTagUtil extends SimpleTagSupport{
	private String groupCode;
	private String type;
	private String id = "";
	private String name = "";
	
	public ComCodeTagUtil() {
		
	}
	
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void doTag() throws JspException, IOException{
		try {
			ComCodeService comCodeService = (ComCodeService) CommonUtil.getSpringBean("ComCodeService");
			
			List<ComCodeVO> comCodeList = comCodeService.selectComCodeList(groupCode);
			String returnHtml = "";
			if("select".equals(type)) {
				returnHtml += "<select class=\"form-control\" ";
				if(!"".equals(id)) {
					returnHtml += "id=\""+id+"\"";
				}
				if(!"".equals(name)) {
					returnHtml += "name=\""+name+"\"";
				}
			} 
			int i = 0;
			for(ComCodeVO vo : comCodeList) {
				if(type.equals("option") || type.equals("select")) {
					returnHtml += "<option value=\""+vo.getCode()+"\">"+vo.getCodeNm()+"</option>";
				} else if("radio".equals(type)) {
					returnHtml += "<label class=\"radio-inline\" for=\""+(!"".equals(id) ? id + vo.getCode() : "")+"\">";
					returnHtml += "<input id=\""+(!"".equals(id) ? id + vo.getCode() : "")+"\" name=\""+(!"".equals(name) ? name : "")+"\"";
					if(i == 0) {
						returnHtml += " checked=\"checked\"";
					}
					returnHtml += " type=\"radio\" value=\""+vo.getCode()+"\"> "+vo.getCodeNm();
					returnHtml += "</label>";
				} else if("checkbox".equals(type)) {
					returnHtml += "<label class=\"checkbox-inline\" for=\""+(!"".equals(id) ? id + vo.getCode() : "")+"\">";
					returnHtml += "<input id=\""+(!"".equals(id) ? id + vo.getCode() : "")+"\" name=\""+(!"".equals(name) ? name : "")+"\"";
					returnHtml += " type=\"checkbox\" value=\""+vo.getCode()+"\"> "+vo.getCodeNm();
					returnHtml += "</label>";
				}
				i++;
			}
			
			if("select".equals(type)) {
				returnHtml += "</select>";
			}
			
			getJspContext().getOut().write(returnHtml);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
