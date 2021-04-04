/**
 * jusoPopup 호출 function
 * @author kmk
 */

var zipIdC = "";
var addrIdC = "";
var detailAddrIdC = "";
var addrLatIdC = "";
var addrLngIdC = "";

/**
 * 파라미터 따라서 해당 id에 해당하는 태그가 있을 경우 값을 입력한다.
 * @param zipId 우편번호
 * @param addrId 주소
 * @param detailAddrId 상세 주소
 * @returns
 */
function searchJusoAdres(zipId, addrId, detailAddrId, addrLatId, addrLngId){
	zipIdC = zipId;
	addrIdC = addrId;
	detailAddrIdC = detailAddrId;
	addrLatIdC = addrLatId;
	addrLngIdC = addrLngId;
	
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("/static/common/popup/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}
 
/**
 * jusoPopup.jsp에서 callback 
 */
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	if(zipIdC != '' && $('#'+zipIdC).length > 0){
		$('#'+zipIdC).val(zipNo);
	}
	
	if(addrIdC != '' && $('#'+addrIdC).length > 0){
		$('#'+addrIdC).val(roadAddrPart1);
	}
	
	if(detailAddrIdC != '' && $('#'+detailAddrIdC).length > 0){
		$('#'+detailAddrIdC).val(addrDetail);
	}
	
	if(addrLatIdC != '' && $('#'+addrLatIdC).length > 0 && addrLngIdC != '' && $('#'+addrLngIdC).length > 0){
		callAjaxExtend({url: "/cmmn/com/cuc/getGeoCoord.do",
			data: {"address" : roadAddrPart1},
			success : function(result){
				data = result.data;
				$('#'+addrLatIdC).val(data.result.point.y);
				$('#'+addrLngIdC).val(data.result.point.x);
			},
			error : function(request,status,error){
				console.log(request,status,error);
			}
		});
	}
}