var isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) ? true : false;

/*
 * ajax binary 타입 사용을 위한 선언
 */
$.ajaxTransport("+binary", function(options, originalOptions, jqXHR) {
    if (window.FormData && ((options.dataType && (options.dataType == 'binary')) 
        || (options.data && ((window.ArrayBuffer && options.data instanceof ArrayBuffer) 
        || (window.Blob && options.data instanceof Blob))))){
        return {
            send: function(headers, callback) {
                var xhr = new XMLHttpRequest(),
                url = options.url,
                type = options.type,
                async = options.async || true,
                dataType = options.responseType || "blob",
                data = options.data || null,
                username = options.username || null,
                password = options.password || null;
 
                xhr.addEventListener('load', function() {
                    var data = {};
                    data[options.dataType] = xhr.response;
                    callback(xhr.status, xhr.statusText, data, xhr.getAllResponseHeaders());
                });
 
                xhr.open(type, url, async, username, password);
 
                for (var i in headers) {
                    xhr.setRequestHeader(i, headers[i]);
                }
 
                xhr.responseType = dataType;
                xhr.send(data);
            },
            abort: function() {}
        };
    }
});

/*
 * datepicker 한글버전 사용을 위한 선언
 */
!function($) {
	$.fn.datepicker.dates.ko = {
			days : [ "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" ],
			daysShort : [ "일", "월", "화", "수", "목", "금", "토" ],
			daysMin : [ "일", "월", "화", "수", "목", "금", "토" ],
			months : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월",
				"11월", "12월" ],
			monthsShort : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월",
					"10월", "11월", "12월" ],
			today : "오늘",
			clear : "삭제",
			format : "yyyy-mm-dd",
			titleFormat : "yyyy년mm월",
			weekStart : 0
	}
}(jQuery);

/*
 * datepicker 초기 설정
 */
var datepickerDefault = {
		format : 'yyyy-mm-dd',
//		minViewMode : 'days',
//		maxViewMode : 'days',
		startView : "days",
		autoclose : true,
		disableTouchKeyboard : true,
		todayHighlight : true,
		language : "ko",
		zIndexOffset : 99999
}

$(document).ready(function(){
	
	$("#sidebarToggle").on("click", function(e) {
	    e.preventDefault();
	    $("body").toggleClass("sb-sidenav-toggled");
	});
	 
	$(window).on("load",function(){
		page_resize_scroll();
    });
	
	$(window).on("resize",function(){
		page_resize_scroll();
    });
	
	$('.b-datepicker').datepicker(datepickerDefault);
	
	$('.pressEnter').on('keypress', function(event){
		let fncStr = $(this).data('press');
		if (event.keyCode==13) {
			eval(fncStr);
	    }
	});
	
	$(document).on('keyup', '.phoneNumber', function(event){ 
		$(this).autoHypenPhone()
    });
	
	$(document).on('keyup', '.onlyNum', function(event){ 
		let inputVal = $(this).val();
		let regVal = "0-9";
		let exceptVal = $(this).data('except');
		if(exceptVal != null && exceptVal != '' && exceptVal != undefined){
			$.each(exceptVal.split('|'), function(i, v){
				regVal = regVal + v;
			});
		}
		$(this).val(inputVal.replace(new RegExp('[^'+regVal+']', 'gi'), ''));
    });
	
	$(document).on('keyup', '.noKrLang', function(){
		let inputVal = $(this).val();
		$(this).val(inputVal.replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, ''));
	});
	
	//섬머노트 적용
	if($('.summernoteArea').length > 0){
		setSummernote($('.summernoteArea'));
	}
	
	//비밀번호 타입 capslock 체크
	$(".passCapsCheck").keypress(function(e){
		let keyCode = 0;
		let shiftKey = false;
		keyCode = e.keyCode;
		shiftKey = e.shiftKey
		//shift를 누르지 않은 상태에서 대문자
		if(((keyCode >= 65 && keyCode <=90) && !shiftKey) || ((keyCode >= 97 && keyCode <= 112) && shiftKey)){
			$(this).tooltip({
				placement : 'bottom',
				title : 'CapsLock이 켜져있습니다.'
			}).tooltip('show');
		} else {
			$(this).tooltip('hide');
		}
	});
	
	if($('#sidenavAccordion').length > 0){
		setNowMenu();
	}
});

function arrayBufferToBinary(arrayBufferData){
	var binary = "";
    var bytes = new Uint8Array(arrayBufferData);
    var length = bytes.byteLength;
    for (var i = 0; i < length; i++) {
    	binary += String.fromCharCode(bytes[i]);
    }
    return binary;
}

function hasOverflown(ele){
	var res;
	var cont = $('<div>'+ele.text()+'</div>').css("display", "table")
											 .css("z-index", "-1").css("position", "absolute")
											 .css("font-family", ele.css("font-family"))
											 .css("font-size", ele.css("font-size"))
											 .css("font-weight", ele.css("font-weight")).appendTo('body');
	res = (cont.width()>ele.width());
	cont.remove();
	return res;
}

function createClipboardCopyBtn(targetEle, copyText){
	//클립보드에 복사
	let clipboard = new ClipboardJS(targetEle, {
		text: function() {
			return copyText;
		}
	});
	
	clipboard.on('success', function(e) {
		console.info('Action:', e.action);
		console.info('Text:', e.text);
		
		e.clearSelection();
		swAlert('확인', '클립보드에 복사 완료!', 'success');
	});
}

function mapRelayout(mapNum){
	if($('#mapViewArea'+mapNum).length > 0){
		setTimeout(function(){
			eval("map"+mapNum+".relayout();");
			eval("map"+mapNum+".setCenter(addrPosition"+mapNum+");");
		}, 500);
	}
}

/*
 * form Data json 형식으로 반환
 */
function formToJSON(elem) {
	var current, entries, item, key, output, value;
	output = {};
	entries = new FormData(elem).entries();
	// Iterate over values, and assign to item.
	while(item = entries.next().value)
	{
		//assign to variables to make the code more readable.
		key = item[0];
	    value = item[1];
	    // Check if key already exist
	    if(Object.prototype.hasOwnProperty.call( output, key)) {
	    	current = output[ key ];
	        if(!Array.isArray( current )) {
	        	// If it's not an array, convert it to an array.
	        	current = output[ key ] = [ current ];
	        }
	        current.push( value ); // Add the new value to the array.
	    } else {
	    	output[ key ] = value;
	    }
	}
	return output;
}

function setSummernote(ele){
	$(ele).summernote({
		toolbar: [
			// [groupName, [list of button]]
			['style', ['bold', 'italic', 'underline', 'clear']],
		    ['font', ['strikethrough', 'superscript', 'subscript']],
		    ['fontsize', ['fontsize']],
		    ['color', ['color']],
		    ['para', ['ul', 'ol', 'paragraph']],
		    ['height', ['height']],
		    ['misc', ['codeview']]
		]
	});
}

function get_query(){
    var url = document.location.href;
    var qs = url.substring(url.indexOf('?') + 1).split('&');
    for(var i = 0, result = {}; i < qs.length; i++){
        qs[i] = qs[i].split('=');
        result[qs[i][0]] = decodeURIComponent(qs[i][1]);
    }
    return result;
}

function get_parameter_query(paramKey){
	let params = get_query();
	
	return params[paramKey];
}

function page_resize_scroll(){
	let vpW = Math.max(document.documentElement.clientWidth, window.innerWidth || 0)
	let vpH = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);
	
	if(vpW > 768){
		$(".custom-scrollbar-target").mCustomScrollbar({
			axis:"y"
			,theme:"minimal-dark"
		});
	} else {
		$(".custom-scrollbar-target").mCustomScrollbar("destroy");
	}
}

function logout(){
	swAlertConfirm('로그아웃 하시겠습니까?', {icon:'info',confirmButtonText:'로그아웃'}, function(){
		let frm = $('<form></form>');
		frm.attr('action', contextPath + '/uat/uia/actionLogout.do');
		frm.attr('method', 'post');
		$(document.body).append(frm);
		frm.submit();	
	});
}

function sleep(milliseconds) {
	const date = Date.now();
	let currentDate = null;
	do {
		currentDate = Date.now();
	} while (currentDate - date < milliseconds);
}

function stringReplaceAll(str, searchStr, replaceStr) {
	return str.split(searchStr).join(replaceStr);
}

function nonXssFiltering(origin){
	var cleanStr = origin;

	cleanStr = stringReplaceAll(cleanStr, "&amp;", "&");
	cleanStr = stringReplaceAll(cleanStr, "&lt;", "<");
	cleanStr = stringReplaceAll(cleanStr, "&gt;", ">");
	cleanStr = stringReplaceAll(cleanStr, "&#034;", "\"");
	cleanStr = stringReplaceAll(cleanStr, "&#039;", "\'");
//	cleanStr = stringReplaceAll(cleanStr, ".", "&#46;");
	cleanStr = stringReplaceAll(cleanStr, "&#046;", "%2E");
	cleanStr = stringReplaceAll(cleanStr, "&#047;", "%2F");
	cleanStr = stringReplaceAll(cleanStr, "&quot;", "\"");
	cleanStr = stringReplaceAll(cleanStr, "&nbsp;", " ");
	
	return cleanStr;
}

function xssFiltering(origin){
	var cleanStr = origin;

	cleanStr = stringReplaceAll(cleanStr, "&", "&amp;");
	cleanStr = stringReplaceAll(cleanStr, "<", "&lt;");
	cleanStr = stringReplaceAll(cleanStr, ">", "&gt;");
	cleanStr = stringReplaceAll(cleanStr, "\"", "&#34;");
	cleanStr = stringReplaceAll(cleanStr, "\'", "&#39;");
//	cleanStr = stringReplaceAll(cleanStr, ".", "&#46;");
	cleanStr = stringReplaceAll(cleanStr, "%2E", "&#46;");
	cleanStr = stringReplaceAll(cleanStr, "%2F", "&#47;");
	
	return cleanStr;
}

function setNowMenu(){
	//현재 url 가져오기
	let nowuri = location.pathname;
	if(location.search != ""){
		nowuri += location.search;
	}
    let menuWap = $('#leftNavbar');
    let menuATag = menuWap.find('a.nav-link[href="'+nowuri+'"]');
    if(menuATag.length > 0){
    	//매칭되는 메뉴가 있음
    	if(menuATag.parent('li').parent('#leftNavbar').get(0) != menuWap.get(0)){
	    	menuATag.addClass('active');
	    	let parentMenu = menuATag.parents('div.collapse');
	    	$.each(parentMenu, function(i, v){
	    		if($(this).prev('a.nav-link').hasClass('collapsed')){
	    			$(this).prev('a.nav-link').removeClass('collapsed').attr('aria-expanded', 'true');
	    		}
	    		$(this).addClass('show');
	    	});
	    	//title
	    	if($.trim($('.header-title').text()) == ''){
	    		$('.header-title').text($.trim(menuATag.text()));
	    	}
    	}
    	
    	setCookie('menu-nav-url', nowuri, 1);
    } else {
    	//매칭되는 메뉴가 없음
    	let menuuri = getCookie('menu-nav-url');
    	let menuATag = menuWap.find('a.nav-link[href="'+menuuri+'"]');
    	if(menuATag.parent('li').parent('#leftNavbar').get(0) != menuWap.get(0)){
	    	menuATag.addClass('active');
	    	let parentMenu = menuATag.parents('div.collapse');
	    	$.each(parentMenu, function(i, v){
	    		if($(this).prev('a.nav-link').hasClass('collapsed')){
	    			$(this).prev('a.nav-link').removeClass('collapsed').attr('aria-expanded', 'true');
	    		}
	    		$(this).addClass('show');
	    	});
	    	//title
	    	if($.trim($('.header-title').text()) == ''){
	    		$('.header-title').text($.trim(menuATag.text()));
	    	}
    	}
    	
    }
    
}

//로딩 페이지
/**
 * 로딩중 페이지 View 함수
 * mode : 로딩중 페이지 모드 (show, hide)
 * */
function pageLoadingView(mode){
	if(mode == 'show'){
		var loadingHtml = loadingViewHtml();
		//이미 켜있을 경우 그냥 넘어가기
		if($('body').find('.text-center.loading').length > 0){
			return ;
		} else {
			$('body').before(loadingHtml);
		}
	} else if(mode == 'hide'){
		$('.text-center.loading').fadeOut().remove();
	}
}
/**
 * 로딩중 페이지 View html 만드는 함수
 * */
function loadingViewHtml(imageName){
	//이미지 기본 경로 설정
	var loadingHtml = '';
	loadingHtml =  '<div class="text-center loading">';
	loadingHtml += '	<div class="spinner-border" role="status">';
	loadingHtml += '		<span class="sr-only">Loading...</span>';
	loadingHtml += '	</div>';
	loadingHtml += '</div>';
	return loadingHtml;
}

/**
 * 쿠키 설정 
 * cName : 쿠키에 설정할 이름
 * cValue : 쿠키에 설정할 값
 * days : 쿠키 유효기간 (일) 
 */
function setCookie(cName, cValue, days){
	var exdate = new Date();
	//만료값 지정
	exdate.setDate(exdate.getDate() + days);
	
	var cookieStr = escape(cValue) + ((days == undefined) ? '' : '; path=/; expires=' + exdate.toUTCString());
	document.cookie = cName + '=' + cookieStr;
};

/**
 * 쿠키 값 가져오기
 * cName : 가져올 쿠키 이름
 * (쿠키가 없을 시 ""을 리턴)
 */
function getCookie(cName){
	var search = cName + "=";
    if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
        offset = document.cookie.indexOf(search);
        if (offset != -1) { // 쿠키가 존재하면
            offset += search.length;
            // set index of beginning of value
            end = document.cookie.indexOf(";", offset);
            // 쿠키 값의 마지막 위치 인덱스 번호 설정
            if (end == -1)
                end = document.cookie.length;
            return unescape(document.cookie.substring(offset, end));
        }
    }
    return "";
};

/**
 * 쿠키 삭제
 * cName : 삭제할 쿠키 이름 
 */
function deleteCookie(cName){
	//만료시간을 -1로 설정하면서 쿠키 삭제처리
	setCookie(cName, '', -1);
};

/**
 * 주소 검색 (daum)
 * 해당 페이지에 https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js 스크립트 추가 필요
 * 위경도 필요시 
 * 해당 페이지에 //dapi.kakao.com/v2/maps/sdk.js?appkey=[앱키]&libraries=services 스크립트 추가 필요
 * */
function searchAddr(zipId, addrId, addrLatId, addrLngId){
	if($('script[src^="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod"]').length > 0){
		new daum.Postcode({
		    oncomplete: function(data) {
		        // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
		        // 예제를 참고하여 다양한 활용법을 확인해 보세요.
		    	if(zipId != '' && $('#'+zipId).length > 0){
		    		$('#'+zipId).val(data.zonecode);
		    	}
		    	if(addrId != '' && $('#'+addrId).length > 0){
		    		$('#'+addrId).val(data.address);
		    	}
		    	
		    	if((addrLatId != null && addrLatId != '') || (addrLngId != null && addrLngId != '')){
		    		if($('script[src^="//dapi.kakao.com/v2/maps/sdk.js"]').length > 0){
		    			let geocoder = new daum.maps.services.Geocoder();
		    			
		    			geocoder.addressSearch(data.address, function(results, status){
				        	if(status === daum.maps.services.Status.OK){
				        		let result = results[0];
				        		
				        		if(addrLatId != '' && $('#'+addrLatId).length > 0){
				    	    		$('#'+addrLatId).val(result.y);
				    	    	}
				        		if(addrLngId != '' && $('#'+addrLngId).length > 0){
				    	    		$('#'+addrLngId).val(result.x);
				    	    	}
				        	}
				        });
		    		} else {
		    			swAlert('확인', 'sdk.js 스크립트가 없습니다.', 'warning');
		    			return ;
		    		}
		    	}
		    },
		    onclose: function(state) {
		        //state는 우편번호 찾기 화면이 어떻게 닫혔는지에 대한 상태 변수 이며, 상세 설명은 아래 목록에서 확인하실 수 있습니다.
		        if(state === 'FORCE_CLOSE'){
		            //사용자가 브라우저 닫기 버튼을 통해 팝업창을 닫았을 경우, 실행될 코드를 작성하는 부분입니다.
		        } else if(state === 'COMPLETE_CLOSE'){
		            //사용자가 검색결과를 선택하여 팝업창이 닫혔을 경우, 실행될 코드를 작성하는 부분입니다.
		            //oncomplete 콜백 함수가 실행 완료된 후에 실행됩니다.
		        }
		    }
		}).open();
	} else {
		swAlert('확인', 'postcode.v2.js 스크립트가 없습니다.', 'warning');
		return ;
	}
}

/*
 * 배열 포함 확인
 * @param target 값
 * @param arraylist 배열
 * @returns 포함되어있으면 true, 아니면 false
 */
function arrayContain(target, arraylist){
	return ($.inArray(target, arraylist) > -1) ? true : false;
}

/*
 * json 배열 포함 확인
 * @param value 값
 * @param list 배열
 * @returns 포함되어있으면 true, 아니면 false
 */
function jsonArrayContain(value, list){
	for(let i = 0; i < list.length; i++){
		let obj = list[i];
		if(JSON.stringify(obj) === JSON.stringify(value)){
			return true;
		}
	}
	
	return false;
}

/*
 * 배열(내부) 중복 확인
 * @param value 값
 * @param list 배열
 * @returns 중복값이 있으면 true, 아니면 false
 */
function isArrayDuplicate(arr)  {
	const isDup = arr.some(function(x) {
		return arr.indexOf(x) !== arr.lastIndexOf(x);
	});
	                         
	return isDup;
}


/**
 * 금지 단어 체크 함수
 *  - value : 체크 할 문자열
 *  return : true - 이상 없음
 *  		 false - 금지 단어 포함되어있음 또는 금지 단어 체크 중 에러 발생
 * */
function checkPrhibtWrd(value){
	let returnValue = false;
	if(value == null || value == '' || value == undefined){
		returnValue = true;
	} else {
		$.ajax({
			url : contextPath + '/cmmn/adm/prhibtWrd/checkPrhibtWrdAjax.do',
			type : 'POST',
			async : false,
			dataType : 'json',
			data : {"checkValue" : value},
			success : function(data){
				if(data.result == 'success'){
					let containPrhbWrdList = data.containPrhbWrdList;
					if(containPrhbWrdList != null && containPrhbWrdList.length > 0){
						swAlert('확인', '금지단어 ['+containPrhbWrdList.join()+']가 포함되어있습니다.', 'warning');
						returnValue =  false;
					} else {
						returnValue =  true;
					}
				} else {
					swAlert('확인', '금지 단어 체크중 오류가 발생했습니다.', 'warning');
					returnValue =  false;
				}
			}, error : function(status){
				swAlert('확인', '금지 단어 체크중 오류가 발생했습니다.', 'warning');
				returnValue =  false;
			}
		});
	}
	return returnValue;
}

/**
 * 기간 체크
 * - startDate : 시작 날짜
 * - endDate : 종료 날짜
 * return : true - 종료
 * */
function checkPeriod(startDate, endDate) {
	let returnVal = true;
	
	let start = startDate;
	if(start != '' && start != null && start != undefined){
		start = moment(startDate);
	}
	
	let end = endDate
	if(end != '' && end != null && end != undefined){
		end = moment(endDate);
	}
	
	if(start.isAfter(end)){
		returnVal = false;
	}
	
	return returnVal;
}

/**
 * auto Hypen Phone
 * */
(function($){
	$.fn.autoHypenPhone = function(options){
		this.each(function(){
			var $id = $(this);
			var tmp = '';
			$id.keyup(function(){
				var str = $(this).val();
				$id.val(autoHypenPhoneString(str));
			});
			$id.change(function(){
				var str = $(this).val();
				$id.val(autoHypenPhoneString(str));
			});
		});
		function autoHypenPhoneString(str){
			str = str.replace(/[^0-9]/g, '');
			var tmp = '';
			var c = (str.substr(0, 2) == "02") ? 1 : 0;
			if( str.length < (4 - c)){
				return str;
			}else if(str.length < (7 - c)){
				tmp += str.substr(0, (3 - c));
				tmp += '-';
				tmp += str.substr((3 - c));
				return tmp;
			}else if(str.length < (11 - c)){
				tmp += str.substr(0, (3 - c));
				tmp += '-';
				tmp += str.substr((3 - c), 3);
				tmp += '-';
				tmp += str.substr((6 - c));
				return tmp;
			}else{
				tmp += str.substr(0, (3 - c));
				tmp += '-';
				tmp += str.substr((3 - c), 4);
				tmp += '-';
				tmp += str.substr((7 - c), 4);
				return tmp;
			}
		}
	};
	
	$.fn.isEmail = function(){
		let asValue = $(this).val();
		var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		return regExp.test(asValue);
	}
	
	/**
	 * javascript 페이징 코드 생성 함수
	 * paginationInfo : 페이징 정보를 가지고 있는 Object
	 * pagingFnStr : 페이징에 사용될 함수 문자열
	 * liAddClassStr : li태그에 추가될 클래스 문자열
	 * aAddClassStr : a태크에 추가될 클래스 문자열
	 * */
	$.fn.createPaginationInfo = function(paginationInfo, pagingFnStr, liAddClassStr, aAddClassStr ){
		let thisEl = $(this);
		
		if(liAddClassStr == null || liAddClassStr == '' || liAddClassStr == undefined){
			liAddClassStr = '';
		}
		if(aAddClassStr == null || aAddClassStr == '' || aAddClassStr == undefined){
			aAddClassStr = '';
		}
		let code = "";
		let paginationWapper = $('<div class="text-center"><ul class="pagination justify-content-center"></ul></div>');
		if(paginationInfo.totalPageCount > paginationInfo.pageSize){
			if(paginationInfo.firstPageNoOnPageList > paginationInfo.pageSize){
				code += '<li class="page-item '+liAddClassStr+'"><a class="page-link '+aAddClassStr+'" href="?pageIndex='+paginationInfo.firstPageNo+'" onclick="'+pagingFnStr+'('+paginationInfo.firstPageNo+');return false; "><i class="fas fa-angle-double-left"></i></a>&#160;</li>';
				code += '<li class="page-item '+liAddClassStr+'"><a class="page-link '+aAddClassStr+'" href="?pageIndex='+(Number(paginationInfo.firstPageNoOnPageList) - 1)+'" onclick="'+pagingFnStr+'('+(Number(paginationInfo.firstPageNoOnPageList) - 1)+');return false; "><i class="fas fa-angle-left"></i></a>&#160;</li>';
			} else {
				code += '<li class="page-item '+liAddClassStr+'"><a class="page-link '+aAddClassStr+'" href="?pageIndex='+paginationInfo.firstPageNo+'" onclick="'+pagingFnStr+'('+paginationInfo.firstPageNo+');return false; "><i class="fas fa-angle-double-left"></i></a>&#160;</li>';
				code += '<li class="page-item '+liAddClassStr+'"><a class="page-link '+aAddClassStr+'" href="?pageIndex='+paginationInfo.firstPageNo+'" onclick="'+pagingFnStr+'('+paginationInfo.firstPageNo+');return false; "><i class="fas fa-angle-left"></i></a>&#160;</li>';
			}
		}
		for(var i = Number(paginationInfo.firstPageNoOnPageList); i <= Number(paginationInfo.lastPageNoOnPageList); i++){
			if (i == Number(paginationInfo.currentPageNo)) {
				code += '<li class="page-item '+liAddClassStr+' active"><a class="page-link '+aAddClassStr+'"><strong>'+i+'</strong></a>&#160;</li>';
			} else {
				code += '<li class="page-item '+liAddClassStr+'"><a class="page-link '+aAddClassStr+'" href="?pageIndex='+i+'" onclick="'+pagingFnStr+'('+i+');return false; ">'+i+'</a>&#160;</li>';
			}
		}
		if (paginationInfo.totalPageCount > paginationInfo.pageSize) {
			if (paginationInfo.lastPageNoOnPageList < paginationInfo.totalPageCount) {
				code += '<li class="page-item '+liAddClassStr+'"><a class="page-link '+aAddClassStr+'" href="?pageIndex='+(Number(paginationInfo.firstPageNoOnPageList) + Number(paginationInfo.pageSize))+'" onclick="'+pagingFnStr+'('+(Number(paginationInfo.firstPageNoOnPageList) + Number(paginationInfo.pageSize))+');return false; "><i class="fas fa-angle-right"></i></a>&#160;</li>';
				code += '<li class="page-item '+liAddClassStr+'"><a class="page-link '+aAddClassStr+'" href="?pageIndex='+paginationInfo.lastPageNo+'" onclick="'+pagingFnStr+'('+paginationInfo.lastPageNo+');return false; "><i class="fas fa-angle-double-right"></i></a>&#160;</li>';
			} else {
				code += '<li class="page-item '+liAddClassStr+'"><a class="page-link '+aAddClassStr+'" href="?pageIndex='+paginationInfo.lastPageNo+'" onclick="'+pagingFnStr+'('+paginationInfo.lastPageNo+');return false; "><i class="fas fa-angle-right"></i></a>&#160;</li>';
				code += '<li class="page-item '+liAddClassStr+'"><a class="page-link '+aAddClassStr+'" href="?pageIndex='+paginationInfo.lastPageNo+'" onclick="'+pagingFnStr+'('+paginationInfo.lastPageNo+');return false; "><i class="fas fa-angle-double-right"></i></a>&#160;</li>';
			}
		}
		paginationWapper.find('ul').append(code);
		thisEl.html(paginationWapper);
	}
	
	/**
	 * 파일 확장자 구하기
	 * fileName : 파일 명 
	 * 	return : 파일 확장자 (모두 소문자)
	 * */
	$.fn.getFileExt = function(fileName){
		let ext = '';
		
		pathHeader = fileName.lastIndexOf("\\");
		//경로 일 경우 
		if(pathHeader != -1){
			//파일 이름 추출
			fileName = fileName.substring(pathHeader+1);
		}
		pathMiddle = fileName.lastIndexOf(".");
		pathEnd = fileName.length;
		//파일 확장자
		ext = fileName.substring(pathMiddle+1, pathEnd);
		
		//파일 확장자 소문자로 변경
		return ext.toLowerCase();
	}
	
	/**
	 * 파일 확장자 체크
	 * useExtArr : 허용할 확장자 배열
	 * fileName : 파일 명
	 * */
	$.fn.useExtCheck = function(useExtArr, fileName){
		//허용 파일 확장자
		if(useExtArr == null || useExtArr == '' || useExtArr == undefined || !Array.isArray(useExtArr)){
			return false;
		}
		
		let thisEl = $(this);
		
		//Element 우선
		if(thisEl.length != 0){
			fileName = thisEl.val();
		}
		
		//파일 확장자 구하기
		let fileExt = $.fn.getFileExt(fileName);
		
		var i = 0;
		for(i = 0; i < useExtArr.length; i++){
			if($.trim(useExtArr[i].toLowerCase()) == fileExt){
				break;
			}
		}
		if(i == useExtArr.length){
			swAlert('확인', '허용된 파일 확장자가 아닙니다. 허용 확장자 : [' + useExtArr.toString() + ']', 'warning');
			return false;
		}
		
		return true;		
	};
	/**
	 * 파일 이미지 미리보기
	 * file : 파일 object
	 * target : 파일 이미지가 표출될 Element id
	 * */
	$.fn.fileImagePreView = function(options){
		let defaults = {
				file : null,
				target : null
		};
		
		let mixOptions = $.extend(defaults, options || {});
		
		let thisEl = $(this);
		
		if(mixOptions.target == null && thisEl.length == 0){
			swAlert('확인', '이미지를 띄울 타겟을 설정하세요.', 'warning');
			return ;
		}
		
		if(mixOptions.file == null){
			swAlert('확인', '파일을 확인하세요.', 'warning');
			return ;
		}
		
		//타겟우선
		if(mixOptions.target != null){
			thisEl = $('#'+mixOptions.target);
		}
		
		let reader = new FileReader();
		reader.onload = function(e){
			thisEl.attr('src', e.target.result);
		}
		reader.readAsDataURL(mixOptions.file);
	}
	
	/**
	 * 링크 아이콘 생성 및 링크 복사 기능
	 * url : 복사할 url (default : location.href)
	 */
	$.fn.setLinkBtn = function(url){
		let thisEl = $(this);
		let linkBtn = $('<button type="button" class="linkBtn"><i class="board-icon icon-linkcopy">링크 아이콘</i></button>');
		
		let shareUrlStr = '';
		if(url){
			shareUrlStr = url;
		} else {
			shareUrlStr = location.href;
		}
		
		createClipboardCopyBtn(linkBtn[0], shareUrlStr);
		
		thisEl.append(linkBtn);
	}
	
	/**
	 * 인쇄 아이콘 생성 및 프린트 기능
	 * printTarget : 프린트 영역 id / default : body
	 */
	$.fn.setPrintBtn = function(printTarget){
		let printArea = '';
		if(printTarget){
			printArea = printTarget;
		} else {
			printArea = 'body';
		}
		let thisEl = $(this);
		//프린트를 위한 자바스크립트 추가
		let printJs = $('<script src="'+contextPath+'/static/js/plugins/print/printThis.js"></script>');
		thisEl.append(printJs);
		
		let printBtn = $('<button type="button" class="printBtn"><i class="board-icon icon-print">인쇄 아이콘</i></button>');
		
		printBtn.on('click', function(){
			if(printArea == 'body'){
				$('body').printThis();
			} else {
				$('#'+printArea).printThis();
			}
		});
		
		thisEl.append(printBtn);
	}

	let scriptInit = false;
	/**
	 * 소셜 공유 아이콘 생성 및 소셜 공유 기능
	 * url : 공유할 url (default : locatino.href)
	 * title : 공유할 title (default : '컨텐츠관리시스템')
	 */
	$.fn.setSocialShareBtn = function(url, title){
		let thisEl = $(this);
		
		if(!url){
			url = '';
		}
		
		if(!title){
			title = '';
		}
		
		let kakaoAPICodeValue = '';
		if(!scriptInit){
			scriptInit = true;
			//KAKAO API 키 가져오기
			kakaoAPICodeValue = getSysCmptnValueToCode('KAKAO_JAVASCRIPT_API');
			
			//필요 스크립트 추가
			thisEl.append('<script async defer crossorigin="anonymous" src="https://connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v9.0" nonce="8obwvogD"></script>');
			thisEl.append('<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>');
			thisEl.append('<script type="text/javascript" src="//developers.band.us/js/share/band-button.js?v=30122020"></script>');
			thisEl.append('<script src="https://www.line-website.com/social-plugins/js/thirdparty/loader.min.js" async="async" defer="defer"></script>');
			thisEl.append('<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>');
			thisEl.append('<script src="'+contextPath+'/static/js/vaiv/cmmn/com/social.js"></script>');
			
			//카카오 초기화
			let kakaoInitInterval = setInterval(function(){
				console.log("interval");
				if(typeof Kakao !== "undefined"){
					Kakao.init(kakaoAPICodeValue);
					clearInterval(kakaoInitInterval);
				}
			}, 100);
			
			setTimeout(function(){
				console.log("timeout");
				clearInterval(kakaoInitInterval);
				if(typeof Kakao === "undefined" || !Kakao.isInitialized()){
					Swal.fire('확인', '카카오 SDK초기화를 실패하였습니다. 확인해주세요.', 'warning');	
				}
			}, 1000);
		}
		//btn 생성
		let socialShareBtn = $('<button type="button" class="socialShare" data-toggle="dropdown"><i class="board-icon icon-share">공유 아이콘</i></button>');
		
		//공유 icon 생성
		let socialIcon = '';
		socialIcon += '<ul class="dropdown-menu" role="menu">';
	    socialIcon += '    <li><a class="icon_sns fb" href="javascript:fnShareSNS(\'facebook\',\''+title+'\',\''+url+'\');" title="페이스북 새창에서 열림"><span class="sr-only">페이스북</span></a></li>';
	    socialIcon += '    <li><a class="icon_sns tw" href="javascript:fnShareSNS(\'twitter\',\''+title+'\',\''+url+'\');" title="트위터 새창에서 열림"><span class="sr-only">트위터</span></a></li>';
	    socialIcon += '    <li><a class="icon_sns bd" href="javascript:fnShareSNS(\'band\',\''+title+'\',\''+url+'\');" title="밴드 새창에서 열림"><span class="sr-only">밴드</span></a></li>';
	    socialIcon += '    <li><a class="icon_sns ln" href="javascript:fnShareSNS(\'line\',\''+title+'\',\''+url+'\');" title="라인 새창에서 열림"><span class="sr-only">라인</span></a></li>';
	    socialIcon += '    <li><a class="icon_sns nv" href="javascript:fnShareSNS(\'naverBlog\',\''+title+'\',\''+url+'\');" title="네이버 새창에서 열림"><span class="sr-only">네이버</span></a></li>';
	    socialIcon += '    <li><a class="icon_sns ks" href="javascript:fnShareSNS(\'kakaoStory\',\''+title+'\',\''+url+'\');" title="카카오스토리 새창에서 열림"><span class="sr-only">카카오스토리</span></a></li>';
	    socialIcon += '    <li><a class="icon_sns kt" href="javascript:fnShareSNS(\'kakaoTalk\',\''+title+'\',\''+url+'\');" title="카카오톡 새창에서 열림"><span class="sr-only">카카오톡</span></a></li>';
	    socialIcon += '</ul>';
		
	    thisEl.append(socialShareBtn);
	    thisEl.append(socialIcon);
	    
	}
	
	$.fn.sArrayToSerialize = function(exceptKey, exceptValue){
		let thisEl = $(this);
		let thisSerializeArray = thisEl.serializeArray();
		
		let returnValue = '';
		$(thisSerializeArray).each(function(i, v){
			let exceptKeyBool = false;
			$.each(exceptKey, function(i2, v2){
				if(v2 == v.name){
					exceptKeyBool = true;
					return false;
				}
			});
			
			if(exceptKeyBool){
				return true;
			}
			
			let exceptValueBool = false;
			$.each(exceptValue, function(i2, v2){
				if(v2 == v.value){
					exceptValueBool = true;
					return false;
				}
			});
			
			if(exceptValueBool){
				return true;
			}
			if(returnValue != ''){
				returnValue += '&';
			}
			returnValue += v.name+"="+v.value;
		});
		
		return returnValue;
	}
		
})(jQuery);


/*
 * excel 파일 생성 스크립트
 * fileName : 확장자를 포함한 파일 명
 * sheetName : 시트 명
 * type : aoa(배열) - ex) [['이름' , '나이', '부서'],['도사원' , '21', '인사팀'],['김부장' , '27', '비서실'],['엄전무' , '45', '기획실']] 
 *        ,json(json배열) ex) [{'상품명':'삼성 갤럭시 s11' , '가격':'200000'}, {'상품명':'삼성 갤럭시 s12' , '가격':'220000'}, {'상품명':'삼성 갤럭시 s13' , '가격':'230000'}]
 *        ,table(tableId) ex) table 태그 ID
 * data : 각 타입에 맞는 데이터 입력
 *   
 * 참고 사이트 : https://github.com/SheetJS/sheetjs
 */
function exportExcel(fileName, sheetName, type, data){
	if($('script[src$="xlsx.full.min.js"]').length > 0){
		// step 1. workbook 생성
	    let wb = XLSX.utils.book_new();

	    // step 2. 시트 만들기 
	    let newWorksheet = '';
	    if(type == 'aoa') {
	    	newWorksheet = XLSX.utils.aoa_to_sheet(data);
	    } else if(type == 'json') {
	    	newWorksheet = XLSX.utils.json_to_sheet(data);
	    } else if(type == 'table') {
	    	let ele = $('#'+data);
	    	ele.find('a').each(function(i,v){
	    		$(this).removeAttr('href');
	    	});
	    	newWorksheet = XLSX.utils.table_to_sheet(ele[0]);
	    }
	    
	    if(fileName == null || fileName == '' || fileName == undefined){
	    	fileName = 'exportExcel.xlsx';
	    }
	    
	    let extName = fileName.substr(fileName.lastIndexOf('.') + 1);
	    
	    if(extName != 'xls' && extName != 'xlsx'){
	    	swAlert('확인', '확장자는 xls, xlsx만 가능합니다.', 'info');
	    	return false;
	    }
	    
	    // step 3. workbook에 새로만든 워크시트에 이름을 주고 붙인다.  
	    XLSX.utils.book_append_sheet(wb, newWorksheet, sheetName);

	    // step 4. 엑셀 파일 만들기 
	    XLSX.writeFile(wb, fileName);
	    
	} else {
		swAlert('확인', 'xlsx 관련 스크립트를 추가하세요.', 'error');
	}
}


const excelDownloadHTML = '<html xmlns:x="urn:schemas-microsoft-com:office:excel">'+
						  '	<head>'+
						  '		<meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">'+
						  '		<xml>'+
						  '			<x:ExcelWorkbook>'+
						  '				<x:ExcelWorksheets>'+
						  '					<x:ExcelWorksheet>'+
						  '						<x:Name>#sheetName#</x:Name>'+
						  '						<x:WorksheetOptions>'+
						  '							<x:Panes></x:Panes>'+
						  '						</x:WorksheetOptions>'+
						  '					</x:ExcelWorksheet>'+
						  '				</x:ExcelWorksheets>'+
						  '			</x:ExcelWorkbook>'+
						  '		</xml>'+
						  '	</head>'+
						  '	<body>'+
						  '		#contentHTML#'+
						  '	</body>'+
						  '</html>';

/*
 * html코드를 excel(xls)파일로 변환하여 다운로드
 * @param fileName 파일명
 * @param sheetName sheet명
 * @param targetHTML html tag ID
 */
function excelDownloadToHTML(fileName, sheetName, targetId){
	let target = $('#'+targetId);
	if(!target){ 
		// CASE 대상 테이블이 존재하는 경우 
		// 엑셀다운 (엑셀파일명, 시트명, 내부데이터HTML)
		swAlert('확인', 'html 타겟이 없습니다.', 'warning');
		return ;
	}

	//a태그의 href 속성 삭제
	target.find('a').each(function(i, v){
		$(this).removeAttr('href');
	});
	
	let targetHTML = target[0].outerHTML;
	
	fileName = fileName + '.xls';
	
	let html = excelDownloadHTML; //callViewFunc('/cmmn/com/cuc/getJSPPageAjax.do', 'post', {'pageNm':'excelDownLoad'});
	
	html = html.replace('#sheetName#', sheetName);
	html = html.replace('#contentHTML#', targetHTML);
	// 데이터 타입 
	let data_type = 'data:application/vnd.ms-excel'; 
	var ua = window.navigator.userAgent; 
	var blob = new Blob([html], {type: "application/csv;charset=utf-8;"}); 
	
	if ((ua.indexOf("MSIE ") > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) && window.navigator.msSaveBlob) { 
		// ie이고 msSaveBlob 기능을 지원하는 경우 
		navigator.msSaveBlob(blob, fileName); 
	} else { 
		// ie가 아닌 경우 (바로 다운이 되지 않기 때문에 클릭 버튼을 만들어 클릭을 임의로 수행하도록 처리) 
		var anchor = window.document.createElement('a'); 
		anchor.href = window.URL.createObjectURL(blob); 
		anchor.download = fileName; 
		document.body.appendChild(anchor); 
		anchor.click(); 
		// 클릭(다운) 후 요소 제거 
		document.body.removeChild(anchor); 
	}
}

/*
 * 시스템 구성 코드로 시스템 구성 코드 값 가져오기
 */
function getSysCmptnValueToCode(code){
	let returnData = '';
	callAjaxExtend({
		url : contextPath + '/cmmn/com/cuc/selectSysCmptnValueToCodeAjax.do',
		data : {"code" : code},
		async:false,
		dataType : 'json',
		success : function(result){
			returnData = result.cmptnValue;
		}, error : function(xhr, status, error){
			swAlert('확인','코드 가져오는 중 에러 발생', 'error');
			returnData = false;
		}
	});
	return returnData;
}

//Changes XML to JSON
function xmlToJson(xml) {
	// Create the return object
	var obj = {};
	if (xml.nodeType == 1) { // element
		// do attributes
		if (xml.attributes.length > 0) {
			obj["@attributes"] = {};
			for (var j = 0; j < xml.attributes.length; j++) {
				var attribute = xml.attributes.item(j);
				obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
			}
		}
	} else if (xml.nodeType == 3) { // text
		obj = xml.nodeValue;
	}
	// do children
	if (xml.hasChildNodes()) {
		for(var i = 0; i < xml.childNodes.length; i++) {
			var item = xml.childNodes.item(i);
			var nodeName = item.nodeName;
			if (typeof(obj[nodeName]) == "undefined") {
				obj[nodeName] = xmlToJson(item);
			} else {
				if (typeof(obj[nodeName].push) == "undefined") {
					var old = obj[nodeName];
					obj[nodeName] = [];
					obj[nodeName].push(old);
				}
				obj[nodeName].push(xmlToJson(item));
			}
		}
	}
	return obj;
};

function getXmlFromString(xmlStr) {
    var parseXml;

    if (window.DOMParser) {
        var dp = new window.DOMParser();
        return dp.parseFromString(xmlStr, "text/xml");
    } else if (typeof window.ActiveXObject != "undefined" && new window.ActiveXObject("Microsoft.XMLDOM")) {
        var xmlDoc = new window.ActiveXObject("Microsoft.XMLDOM");
        xmlDoc.async = "false";
        xmlDoc.loadXML(xmlStr);
        
        return xmlDoc;
    }

    return null;
}



