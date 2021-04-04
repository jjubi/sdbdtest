package egovframework.vaiv.kr.cmmn.common.lds;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
//import org.json.JSONObject;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 연계 서비스 호출 : 연계서비스 호출에 따른 처리 작업 Controller
 * @category 공통
 * @author kmk
 * @since 2021-02-09
 * @version v1.0
 * @see
 * <pre>
 *  ******************************************
 *  수정 이력
 *  
 *  수정일                  수정자                 수정내용
 *  ------------------------------------------
 *  2021.02.09    kmk           최초 등록
 * 
 * 
 *  ******************************************
 *  Copyright 2021 VAIV Co.
 *  All rights reserved
 * </pre>
 */
public class LinkedDataService {

	private final static Logger LOG = Logger.getLogger(LinkedDataServicePre.class.getName());
	// 사용 예제
//	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, ParseException {
		
		// dbconnection 방식 이용 시 활용
//		List<HashMap<String, Object>> sqlList = connectionSql("jdbc:mariadb://1.221.237.243:53307/cms_v1", "cms", "cms1234vaiv!", "SELECT * FROM T_CMT_M01");
		
		// api url 호출 시 json 파일 parsing을 통해 정보를 가져온다.
//		Map<String, Object> map = new HashMap<String, Object>();
//		map = setJsonFile(map, "C://vaiv/cms_v1_git/cms/src/main/resources/egovframework/jsonProps/VilageFcstInfoService.json", "getUltraSrtNcst");

		// 별도 추가 요청 param은 아래와 같이 추가하면 된다.
		
//		Date d = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		SimpleDateFormat sdf2 = new SimpleDateFormat("hhmm");
/*		map.put("base_date", sdf.format(d));
		map.put("base_time", sdf2.format(d));*/
//		map.put("base_date", "20210208");
//		map.put("base_time", "0500");
//		map.put("encodingType", "EUC-KR");

//		StringBuilder sb = LinkedDataService.requestApi(map);
		
		// 반환받은 데이터 처리
		// * json일 경우.
		// org.json.JSONObject resultJsonObj = new org.json.JSONObject(sb.toString());
		
		// * xml일 경우.
		// XML getTageValue 이용하여 tag 값의 정보를 받아온다.
		// 공공데이터 포털 기상청 우리동네 예보 기준입니다. 각 xml 리스트 tag Name에 맞추어 활용 바랍니다.
/*		
		NodeList list = getTagNodeList(sb, "item");
		
		for(int i=0; i<list.getLength(); i++) {
			Node node = list.item(i);
			
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element eElement = (Element) node;
				System.out.println(getTagValue("baseDate", eElement));
				System.out.println(getTagValue("baseTime", eElement));
				System.out.println(getTagValue("category", eElement));
				System.out.println(getTagValue("baseDate", eElement));
				System.out.println(getTagValue("nx", eElement));
				System.out.println(getTagValue("ny", eElement));
				System.out.println(getTagValue("obsrValue", eElement));
			}
		}*/
		
		//json file에서 가져와서 처리 json file로 만들어서 처리 가능한지?
//	}

	/**
	 * db connection 방식을 이용하여 List<map> 형태로 쿼리 결과 반환
	 * @param url db connection url
	 * @param userId db user
	 * @param password db password
	 * @param sqlQuery 조회 할 쿼리
	 * @return sqlList 조회한 쿼리의 결과값을 list 형태의 map에 담아 반환
	 */
	public static List<HashMap<String, Object>> connectionSql(String url, String userId, String password, String sqlQuery) {
		LOG.debug("==== db connection start ====");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		List<HashMap<String, Object>> sqlList = new ArrayList<HashMap<String, Object>>();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
//			con = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/dbname", "userId", "password");
//			con = DriverManager.getConnection("jdbc:mariadb://1.221.237.243:53307/cms_v1", "cms", "cms1234vaiv!");
			con = DriverManager.getConnection(url, userId, password);
			
			pstmt = con.prepareStatement(sqlQuery);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				rsmd = rs.getMetaData();
				int columnCnt = rsmd.getColumnCount();
				for(int i = 1; i <= columnCnt; i++) {
					map.put(rsmd.getColumnName(i), rs.getString(rsmd.getColumnName(i)));
				}
				sqlList.add(map);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		LOG.debug("==== db connection end ====");
		return sqlList;
	}
	
	/**
	 * request url에 따른 api 호출
	 * @param map map 형태의 호출 파라미터들을 담고있는 변수
	 * @return sb 호출 후 리턴받은 값을 담은 변수
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ProtocolException
	 */
	public static StringBuilder requestApi(Map<String, Object> map)
			throws MalformedURLException, IOException, ProtocolException {
		LOG.debug("==== 연계 api 호출 start ====");
		
		StringBuilder strBuilder = LinkedDataServicePre.makeUrl(map);
		System.out.println(strBuilder.toString());
		URL url = new URL(strBuilder.toString());
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code : " + conn.getResponseCode());
		BufferedReader rd;
		
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		}else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = rd.readLine()) != null) {
			sb.append(line);
		}
		
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		
		LOG.debug("==== 연계 api 호출 end ====");
		return sb;
	}

	/**
	 * request api xml parsing 시 tag 값 정보 가져오기
	 * @param tag 뽑아온 eElement내 정보를 가져올 tag 이름
	 * @param eElement 뽑아 올 element을 포함하고있는 변수
	 * @return
	 */
	public static String getTagValue(String tag, Element eElement) {
		LOG.debug("==== xml parsing 시 tag 정보 가져오기 start ====");
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node)nlList.item(0);
		if(nValue == null)
			return null;
		
		LOG.debug("==== xml parsing 시 tag 정보 가져오기 end ====");
		return nValue.getNodeValue();
	}
	
	/**
	 * jsonFile을 가져와 map에 호출 정보들을 담는 메서드
	 * @param map json파일에 있는 정보를 담을 변수
	 * @param filePath jsonFile의 filepath
	 * @param jsonObjKey jsonFile 내 호출 api의 key 값.
	 * @return map json 파일에 있는 정보들을 담고있는 변수
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static Map<String, Object> setJsonFile(Map<String, Object> map, String filePath, String jsonObjKey) throws FileNotFoundException, IOException, ParseException {
		LOG.debug("==== xml parsing 시 tag 정보 가져오기 start ====");
		JSONParser parser = new JSONParser();
		org.json.simple.JSONObject jsonObj = (org.json.simple.JSONObject) parser.parse(new FileReader(filePath));
		
		JSONObject jsonUltraSrtNcst = (JSONObject)jsonObj.get(jsonObjKey);
		
		Iterator i = jsonUltraSrtNcst.keySet().iterator();
		while(i.hasNext()) {
			String key = i.next().toString();
			map.put(key, jsonUltraSrtNcst.get(key));
		}
		
		LOG.debug("==== xml parsing 시 tag 정보 가져오기 end ====");
		return map;
	}
	
	/**
	 * xml parsing 및 해당 item들의 리스트 반환 메서드
	 * @param sb api 호출 후 받아온 정보
	 * @param tagsName tag item list에 해당하는 정보
	 * @return NodeList item들을 list에 담아 반환하는 변수
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static NodeList getTagNodeList(StringBuilder sb, String tagsName) throws SAXException, IOException, ParserConfigurationException {
		LOG.debug("==== xml parsing tag NodeList start ====");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		InputStream is = new ByteArrayInputStream(sb.toString().getBytes());
		Document doc = documentBuilder.parse(is);
		Element element = doc.getDocumentElement();
		NodeList list = element.getElementsByTagName(tagsName);
		
		LOG.debug("==== xml parsing tag NodeList end ====");
		return list;
	}
}
