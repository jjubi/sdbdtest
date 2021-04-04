var loginLogDayChart;
var bbsToNttStatsChart;
var menuUseCntStatsChart;

$(document).ready(function(){
	
	
});


function selectLoginLogDayStats(){
	$.ajax({
		url : contextPath + '/cmmn/adm/stats/selectLoginLogDataAjax.do'
		,type : 'POST'
		,dataType : 'json'
		,data : {"type" : 'day'}
		,success : function(result){
			if(result.result == 'success'){
				let statsData = [];
				$.each(result.statsData.resultData, function(i, v){
					statsData.push({
						"date" : v.dates,
						"logCnt" : v.logCnt
					});
				});
				loginLogDayChart = AmCharts.makeChart("loginLogStatsArea", {
										"language" : "ko",
										"type": "serial",
										"categoryField": "date",
										"dataDateFormat": "YYYY-MM-DD",
										"categoryAxis": {
											"parseDates": true
										},
										"chartCursor": {
											"enabled": true,
											"categoryBalloonDateFormat": "YYYY-MM-DD"
										},
										"chartScrollbar": {
											"enabled": true
										},
										"trendLines": [],
										"graphs": [
											{
												"bullet": "round",
												"id": "dayToLoginCnt",
												"title": "일별 로그인 수",
												"valueField": "logCnt"
											}
										],
										"guides": [],
										"valueAxes": [
											{
												"id": "loginCnt",
												"title": "로그인 수"
											}
										],
										"allLabels": [],
										"balloon": {},
										"legend" : {
											"enabled" : true,
											"useGraphSettings" : true
										},
										"titles": [
											{
												"id" : "dayToLoginCntTitle",
												"size" : 15,
												"text" : "최근 1달 일별 로그인 수"
											}
										],
										"dataProvider": statsData
								});
			} else {
				Swal.fire('확인', '로그인 로그 일별 통계 불러오기 실패. 관리자에게 문의하세요', 'error');
			}
		}, error : function(status){
			Swal.fire('확인', '로그인 로그 일별 통계 불러오기 실패. 관리자에게 문의하세요', 'error');
		}
	});
}

function selectLoginLogMonthStats(){
	$.ajax({
		url : contextPath + '/cmmn/adm/stats/selectLoginLogDataAjax.do'
		,type : 'POST'
		,dataType : 'json'
		,data : {"type" : 'month'}
		,success : function(result){
			if(result.result == 'success'){
				let statsData = [];
				$.each(result.statsData.resultData, function(i, v){
					statsData.push({
						"date" : v.dates,
						"logCnt" : v.logCnt
					});
				});
				loginLogDayChart = AmCharts.makeChart("loginLogStatsArea", {
										"language" : "ko",
										"type": "serial",
										"categoryField": "date",
										"dataDateFormat": "YYYY-MM",
										"categoryAxis": {
											"minPeriod": "MM",
											"parseDates": true
										},
										"chartCursor": {
											"enabled": true,
											"categoryBalloonDateFormat": "YYYY-MM"
										},
										"chartScrollbar": {
											"enabled": true
										},
										"trendLines": [],
										"graphs": [
											{
												"bullet": "round",
												"id": "dayToLoginCnt",
												"title": "월별 로그인 수",
												"valueField": "logCnt"
											}
										],
										"guides": [],
										"valueAxes": [
											{
												"id": "loginCnt",
												"title": "로그인 수"
											}
										],
										"allLabels": [],
										"balloon": {},
										"legend" : {
											"enabled" : true,
											"useGraphSettings" : true
										},
										"titles": [
											{
												"id" : "dayToLoginCntTitle",
												"size" : 15,
												"text" : "최근 1년 월별 로그인 수"
											}
										],
										"dataProvider": statsData
								});
			} else {
				Swal.fire('확인', '로그인 로그 월별 통계 불러오기 실패. 관리자에게 문의하세요', 'error');
			}
		}, error : function(status){
			Swal.fire('확인', '로그인 로그 월별 통계 불러오기 실패. 관리자에게 문의하세요', 'error');
		}
	});
}

function selectLoginLogYearStats(){
	$.ajax({
		url : contextPath + '/cmmn/adm/stats/selectLoginLogDataAjax.do'
		,type : 'POST'
		,dataType : 'json'
		,data : {"type" : 'year'}
		,success : function(result){
			if(result.result == 'success'){
				let statsData = [];
				$.each(result.statsData.resultData, function(i, v){
					statsData.push({
						"date" : v.dates,
						"logCnt" : v.logCnt
					});
				});
				loginLogDayChart = AmCharts.makeChart("loginLogStatsArea", {
										"language" : "ko",
										"type": "serial",
										"categoryField": "date",
										"dataDateFormat": "YYYY",
										"categoryAxis": {
											"minPeriod" : "YYYY",
											"parseDates": true
										},
										"chartCursor": {
											"enabled": true,
											"animationDuration": 0,
											"categoryBalloonDateFormat": "YYYY"
										},
										"chartScrollbar": {
											"enabled": true
										},
										"trendLines": [],
										"graphs": [
											{
												"bullet": "round",
												"id": "dayToLoginCnt",
												"title": "년별 로그인 수",
												"valueField": "logCnt"
											}
										],
										"guides": [],
										"valueAxes": [
											{
												"id": "loginCnt",
												"title": "로그인 수"
											}
										],
										"allLabels": [],
										"balloon": {},
										"legend" : {
											"enabled" : true,
											"useGraphSettings" : true
										},
										"titles": [
											{
												"id" : "dayToLoginCntTitle",
												"size" : 15,
												"text" : "최근 10년 년별 로그인 수"
											}
										],
										"dataProvider": statsData
								});
			} else {
				Swal.fire('확인', '로그인 로그 년별 통계 불러오기 실패. 관리자에게 문의하세요', 'error');
			}
		}, error : function(status){
			Swal.fire('확인', '로그인 로그 년별 통계 불러오기 실패. 관리자에게 문의하세요', 'error');
		}
	});
}

function selectBbsToNttCntStats(){
	$.ajax({
		url : contextPath + '/cmmn/adm/stats/selectBbsToNttDataAjax.do'
		,type : 'POST'
		,dataType : 'json'
		,data : {"type" : 'cnt'}
		,success : function(result){
			if(result.result == 'success'){
				let statsData = [];
				$.each(result.statsData.resultData, function(i, v){
					statsData.push({
						"category" : v.bbsNm,
						"nttCnt" : v.nttCnt
					});
				});
				bbsToNttStatsChart = AmCharts.makeChart("bbsToNttStatsArea", {
										"language" : "ko",
										"type": "pie",
										"balloonText" : "[[title]]<br><span>[[value]] ([[percents]]%)</span>",
										"titleField" : "category",
										"valueField" : "nttCnt",
										"allLabels": [],
										"balloon": {},
										"legend" : {
											"enabled" : true,
											"align" : "center",
											"markerType" : "circle"
										},
										"titles": [
											{
												"id" : "bbsToNttCntTitle",
												"size" : 15,
												"text" : "최근 1달 게시판별 게시물 수"
											}
										],
										"dataProvider": statsData
								});
			} else {
				Swal.fire('확인', '게시판별 게시물 통계 불러오기 실패. 관리자에게 문의하세요', 'error');
			}
		}, error : function(status){
			Swal.fire('확인', '게시판별 게시물 통계 불러오기 실패. 관리자에게 문의하세요', 'error');
		}
	});
}

function selectBbsToNttAttrCntStats(){
	$.ajax({
		url : contextPath + '/cmmn/adm/stats/selectBbsToNttDataAjax.do'
		,type : 'POST'
		,dataType : 'json'
		,data : {"type" : 'attrCnt'}
		,success : function(result){
			if(result.result == 'success'){
				let statsData = [];
				$.each(result.statsData.resultData, function(i, v){
					let json = {};
					$.each(v, function(i2, v2){
						if(i2 != 'bbsId'){
							if(i2 == 'bbsNm'){
								json.attribute = v2;
							} else {
								eval('json.'+i2+'="'+v2+'";');
							}
						}
					});
					statsData.push(json);
				});
				console.log(statsData);
				bbsToNttStatsChart = AmCharts.makeChart("bbsToNttStatsArea", {
										"language" : "ko",
										"type": "radar",
										"categoryField" : "attribute",
										"startDuration":1,
										"graphs":[
											{
												"balloonText":"[[value]] 건",
												"bullet" : "round",
												"id" : "secretCnt",
												"title" : "비밀키",
												"valueField" : "nttSecretCnt"
											},
											{
												"balloonText":"[[value]] 건",
												"bullet" : "round",
												"id" : "noticeCnt",
												"title" : "공지",
												"valueField" : "nttNoticeCnt"
											},
											{
												"balloonText":"[[value]] 건",
												"bullet" : "round",
												"id" : "lcIndictCnt",
												"title" : "위치표시",
												"valueField" : "nttLcIndictCnt"
											},
											{
												"balloonText":"[[value]] 건",
												"bullet" : "round",
												"id" : "popupCnt",
												"title" : "팝업",
												"valueField" : "nttPopupCnt"
											},
										],
										"guides":[],
										"valueAxes": [
											{
												"axisTitleOffset": 20,
												"id": "ValueAxis-1",
												"minimum": 0,
												"axisAlpha": 0.15,
												"dashLength": 3
											}
										],
										"allLabels": [],
										"balloon": {},
										"legend" : {
											"enabled" : true,
											"align" : "center",
											"markerType" : "circle"
										},
										"titles": [
											{
												"id" : "bbsToNttAttrCntTitle",
												"size" : 15,
												"text" : "최근 1달 게시판별 게시물 속성 사용 수"
											}
										],
										"dataProvider": statsData
								});
			} else {
				Swal.fire('확인', '게시판별 게시물 통계 불러오기 실패. 관리자에게 문의하세요', 'error');
			}
		}, error : function(status){
			Swal.fire('확인', '게시판별 게시물 통계 불러오기 실패. 관리자에게 문의하세요', 'error');
		}
	});
}

function selectMenuCntStats(){
	$.ajax({
		url : contextPath + '/cmmn/adm/stats/selectMenuDataAjax.do'
		,type : 'POST'
		,dataType : 'json'
//		,data : {"type" : 'attrCnt'}
		,success : function(result){
			if(result.result == 'success'){
				let statsData = [];
				$.each(result.statsData.resultData, function(i, v){
					let json = {};
					json.menuNm = v.trgetMenuNm;
					json.menuCnt = v.menuCnt;
					statsData.push(json);
				});
				console.log(statsData);
				menuUseCntStatsChart = AmCharts.makeChart("menuUseStatsArea", {
										"language" : "ko",
										"type": "serial",
										"categoryField" : "menuNm",
										"startDuration":1,
										"categoryAxis" : {
											"gridPosition":"start"
										},
										"chartCursor" : {
											"enabled":true
										},
										"chartScrollbar": {
											"enabled": true
										},
										"trendLines": [],
										"graphs": [
											{
												"balloonText":"[[menuNm]] : [[value]]",
												"fillAlphas": 0.8,
												"columnWidth":0.55,
												"lineColor": "#ff7200",
												"id": "menuCnt-1",
												"title": "메뉴 사용 수",
												"type": "column",
												"valueField": "menuCnt"
											}
										],
										"valueAxes": [
											{
												"id": "ValueAxis-1",
												"title":"사용 수"
											}
										],
										"allLabels": [],
										"balloon": {},
										"legend" : {
											"enabled" : true,
											"align" : "center",
											"markerType" : "square"
										},
										"titles": [
											{
												"id" : "menuUseStatsTitle",
												"size" : 15,
												"text" : "최근 1달 메뉴 사용 수"
											}
										],
										"dataProvider": statsData
								});
			} else {
				Swal.fire('확인', '게시판별 게시물 통계 불러오기 실패. 관리자에게 문의하세요', 'error');
			}
		}, error : function(status){
			Swal.fire('확인', '게시판별 게시물 통계 불러오기 실패. 관리자에게 문의하세요', 'error');
		}
	});
}