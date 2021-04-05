package egovframework.vaiv.kr.cmmn.common.lds;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.log4j.Logger;

import egovframework.vaiv.kr.cmmn.common.util.Loggable;

public class LinkedDataServicePre extends Loggable{
	private static String encodingType = "UTF-8";
	
	private final static Logger LOG = Logger.getLogger(LinkedDataServicePre.class.getName());
	
	public static StringBuilder makeUrl(Map<String, Object> map) {
		if(map.get("url") != null) {
			LOG.debug("==== 연계 api 호출 url 생성 start ====");
			StringBuilder urlBuilder = new StringBuilder((String)map.get("url"));
			int i = 0;
			if(map.get("encodingType") != null) {
				encodingType = (String)map.get("encodingType");
				map.remove("encodingType");
			}
			
			for(Map.Entry<String, Object> elem : map.entrySet()) {
				String urlAppend = "";
				if(!"url".equals(elem.getKey())) {
					if(i == 0) {
						urlAppend = "?";
					}else {
						urlAppend = "&";
					}
					try {
						if(!"serviceKey".equals(elem.getKey())){
							urlAppend += URLEncoder.encode(elem.getKey(), encodingType) + "=" + URLEncoder.encode(String.valueOf(elem.getValue()), encodingType);
						}else {
							urlAppend += URLEncoder.encode(elem.getKey(), encodingType) + "=" + elem.getValue();
						}
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					urlBuilder.append(urlAppend);
				}
				i++;
			}
			LOG.debug("==== 연계 api 호출 url 생성 end ====");
			return urlBuilder;
		}else {
			LOG.debug("api 호출 url이 존재하지 않습니다.");
			return null;
		}
	}
}
