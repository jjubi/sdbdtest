package egovframework.vaiv.kr.cmmn.comCode.util;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import egovframework.vaiv.kr.cmmn.common.util.CommonUtil;

public class CustomDataListTag extends SimpleTagSupport{
	private String type;
	private String id = "";
	private String name = "";
	private Object itemS;
	private String itemV;
	private String itemL;
	
	public CustomDataListTag() {
		
	}
	
	public void setItemS(Object itemS) {
		this.itemS = itemS;
	}
	
	public void setItemV(String itemV) {
		this.itemV = itemV;
	}
	
	public void setItemL(String itemL) {
		this.itemL = itemL;
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
			if(itemS instanceof Collection<?>) {
				Collection<?> itemSC = (Collection<?>) itemS;
				List<Map<String, Object>> itemList = CommonUtil.convertListToMap(itemSC);
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
				for(Map<String, Object> vo : itemList) {
					if(type.equals("option") || type.equals("select")) {
						returnHtml += "<option value=\""+vo.get(itemV)+"\">"+vo.get(itemL)+"</option>";
					} else if("radio".equals(type)) {
						returnHtml += "<label class=\"radio-inline\" for=\""+(!"".equals(id) ? id + vo.get(itemV) : "")+"\">";
						returnHtml += "<input id=\""+(!"".equals(id) ? id + vo.get(itemV) : "")+"\" name=\""+(!"".equals(name) ? name : "")+"\"";
						if(i == 0) {
							returnHtml += " checked=\"checked\"";
						}
						returnHtml += " type=\"radio\" value=\""+vo.get(itemV)+"\"> "+vo.get(itemL);
						returnHtml += "</label>";
					} else if("checkbox".equals(type)) {
						returnHtml += "<label class=\"checkbox-inline\" for=\""+(!"".equals(id) ? id + vo.get(itemV) : "")+"\">";
						returnHtml += "<input id=\""+(!"".equals(id) ? id + vo.get(itemV) : "")+"\" name=\""+(!"".equals(name) ? name : "")+"\"";
						returnHtml += " type=\"checkbox\" value=\""+vo.get(itemV)+"\"> "+vo.get(itemL);
						returnHtml += "</label>";
					}
					i++;
				}
				
				if("select".equals(type)) {
					returnHtml += "</select>";
				}
				getJspContext().getOut().write(returnHtml);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
}
