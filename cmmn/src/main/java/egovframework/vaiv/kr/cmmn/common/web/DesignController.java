package egovframework.vaiv.kr.cmmn.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/design/")
public class DesignController {
	@RequestMapping(value="{path}")
	public String designPage1(@PathVariable("path") String path){
		return path+".design";
	}
	@RequestMapping(value="{path1}/{path2}")
	public String designPage2(@PathVariable("path1") String path1
			, @PathVariable("path2") String path2){
		return path1+"/"+path2+".design";
	}
	@RequestMapping(value="{path1}/{path2}/{path3}")
	public String designPage3(@PathVariable("path1") String path1
			, @PathVariable("path2") String path2
			, @PathVariable("path3") String path3){
		return path1+"/"+path2+"/"+path3+".design";
	}
}
