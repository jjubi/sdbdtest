<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>

<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${kakaoAPIKey}&libraries=services"></script>

<script>
$(document).ready(function(){
	let listNum = "${not empty listNum ? listNum : '' }";
	eval("var mapContainer"+listNum+" = document.getElementById(\"mapViewArea"+listNum+"\");");
	eval("addrPosition"+listNum+" = new kakao.maps.LatLng('${lat}', '${lng}');");
	eval("var mapOption"+listNum+" = {center : addrPosition"+listNum+", level : 3};");
	eval("map"+listNum+" = new kakao.maps.Map(mapContainer"+listNum+", mapOption"+listNum+");");
	eval("var zoomControl"+listNum+" = new kakao.maps.ZoomControl();");
	eval("map"+listNum+".addControl(zoomControl"+listNum+", kakao.maps.ControlPosition.TOPRIGHT);");
	eval("var marker"+listNum+" = new kakao.maps.Marker({position : addrPosition"+listNum+"});");
	eval("marker"+listNum+".setMap(map"+listNum+");");
	eval("var content"+listNum+" = '<div class=\"infoWindowAddr\"><a class=\"text-reset\" href=\"https://map.kakao.com/link/search/${addr}\" target=\"_blank\">${addr}</a></div>';");
	eval("var customOverlay"+listNum+" = new kakao.maps.CustomOverlay({map:map"+listNum+",position:addrPosition"+listNum+",content:content"+listNum+",yAnchor:2});");
	
// 	var mapContainer = document.getElementById("mapViewArea${not empty listNum ? listNum : '' }");
// 	var addrPosition = new kakao.maps.LatLng('${lat}', '${lng}');
// 	var mapOption = {
// 			center : addrPosition,
// 			level : 3
// 	};
// 	var map = new kakao.maps.Map(mapContainer, mapOption);
	
	//지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
// 	let zoomControl = new kakao.maps.ZoomControl();
// 	map.addControl(zoomControl, kakao.maps.ControlPosition.TOPRIGHT);
	
// 	let marker = new kakao.maps.Marker({
// 		position : addrPosition
// 	});
	
// 	marker.setMap(map);
	
// 	let content = '<div class="infoWindowAddr">'+
// 						'<a class="text-reset" href="https://map.kakao.com/link/search/${addr}" target="_blank">${addr}</a>'+
// 					'</div>';
					
// 	let customOverlay = new kakao.maps.CustomOverlay({
// 		map:map,
// 		position:addrPosition,
// 		content:content,
// 		yAnchor:2
// 	});
});
</script>

<div class="map-wrap">
	<div>
		<div class="map" id="mapViewArea${not empty listNum ? listNum : '' }"></div>
		<div class="Map-info" style="cursor: pointer;" onclick="window.open('https://map.kakao.com/link/search/${addr}');">
    		<span class="Map-name"><c:out value="${addr}"/></span>
    		<span class="Map-address"><c:out value="${addr}"/></span>
		</div>
	</div>
</div>
