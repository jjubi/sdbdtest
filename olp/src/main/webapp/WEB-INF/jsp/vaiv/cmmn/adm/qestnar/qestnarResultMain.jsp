<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : qestnarResultMain.jsp
  * @상세설명 : 설문조사 관리 결과 표출 페이지
  * @작성일시 : 2021. 02. 17
  * @작 성 자 : jo
  * @수 정 이 력
  * @
  * @  수정일         수정자                   수정내용
  * @ -------       --------    ---------------------------
  * @ 2021.02.17   jo	  최초등록
  * @ 
  * 
  */
%>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/amcharts/amcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/amcharts/pie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/amcharts/lang/ko.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/amcharts/plugins/animate/animate.js"></script>

<script>
var rsCnt = '${qestnarInfo.qestnarResultCnt}';
$(document).ready(function(){
	callDataApi(contextPath + '/cmmn/adm/qestnar/result/qestnarResultStat.do', {"qestnarSeqNo" : '${qestnarInfo.qestnarSeqNo}'}).then(function(result){
		console.log(result);
		$.each(result.resultList, function(i, v){
			//통계 데이터를 가지고 차트 표출
			setQestnarResultChart(v);	
		});
		
		$('#pageContents').find('.tab-pane.active .View_content li').each(function(i, v){
	        if(hasOverflown($(this))){
	            let viewcon = $('<button type="button" class="btn btn-outline-dark viewcon" title="등록 버튼" style="display:show;">더보기</button>');
	            let closecon = $('<button type="button" class="btn btn-outline-dark closecon" title="접기 버튼" style="display:none;">접기</button>');
	            $(this).append(viewcon);
	            $(this).append(closecon);
	        }
	    });
	}).catch(function(result){
		swAlert('확인', '통계 데이터 가져오는 중 오류 발생. 관리자에게 문의해주세요.', 'error');
		return false;
	});
	
	$('#pageTabs').on('show.bs.tab', function(e){
		$(e.target).find('.View_content li').each(function(i, v){
			if($(this).find('.viewcon').length < 1){
		        if(hasOverflown($(this))){
		            let viewcon = $('<button type="button" class="btn btn-outline-dark viewcon" title="등록 버튼" style="display:show;">더보기</button>');
		            let closecon = $('<button type="button" class="btn btn-outline-dark closecon" title="접기 버튼" style="display:none;">접기</button>');
		            $(this).append(viewcon);
		            $(this).append(closecon);
		        }
			}
	    });
	})
	
	$('#pageContents').on('click', '.View_content > li > .viewcon',function(){
        $(this).parents('li:eq(0)').addClass("active");
        $(this).hide();
        $(this).next().show();
    });

   	$('#pageContents').on('click', '.View_content > li > .closecon',function(){
        $(this).parents('li:eq(0)').removeClass("active");
        $(this).hide();
        $(this).prev().show();
    });
});

function setEtcStr(data){
	let etcListHTML = '';
	$.each(data.split(';'), function(i, v){
		if(v != ''){
			etcListHTML += '<li>' + v + '</li>';
		}
	});
	return etcListHTML;
}

function setQestnarResultChart(data){
	let statsData = [];
	let qestnTy = data.qestnTy;
	let etcListHTML = '';
	if(qestnTy == 'radio' || qestnTy == 'select'){
		$.each(data.aswperSeqNo.split(';'), function(i, v){
			let json = {};
			json.category = data.aswperText.split(';')[i];
			json.resultCnt = 0;
			$.each(data.qestnResultCn.split(';'), function(i2, v2){				
				if(v == v2){
					json.resultCnt++;
				}
			});
			statsData.push(json);
		});
		if(data.qestnEtcResultCn != null){
			etcListHTML = setEtcStr(data.qestnEtcResultCn);
		}
	} else if(qestnTy == 'checkbox'){
		$.each(data.aswperSeqNo.split(';'), function(i, v){
			let json = {};
			json.category = data.aswperText.split(';')[i];
			json.resultCnt = 0;
			$.each(data.qestnResultCn.split(';'), function(i2, v2){		
				if(v2.indexOf(',') > -1){
					$.each(v2.split(','), function(i3, v3){
						if(v == v3){
							json.resultCnt++;
						}
					});
				} else {
					if(v == v2){
						json.resultCnt++;
					}
				}
			});
			statsData.push(json);
		});
		etcListHTML = setEtcStr(data.qestnEtcResultCn);
	} else if(qestnTy == 'text' || qestnTy == 'textarea') {
		$.each(data.qestnResultCn.split(';'), function(i, v){
			let json = {};
			json.category = v;
			json.resultCnt = 0;
			$.each(data.qestnResultCn.split(';'), function(i2, v2){				
				if(v == v2){
					json.resultCnt++;
				}
			});
			
			if(!jsonArrayContain(json, statsData)){
				json.category = v.length > 9 ? v.substr(0, 10) + '...' : v;	
				statsData.push(json);
			}
		});
		etcListHTML = setEtcStr(data.qestnResultCn);
	}
	
	if('${qestnarInfo.qestnarPgeAt}' != 'Y'){
		$('#qestnList1').append('<div class="col-md-6"><h3 class="result_tit">문항 명 : '+data.qestnSj+' <small>('+data.totRspnsCnt+'/'+rsCnt+')</small></h3><div id="qestn'+data.qestnSeqNo+'" style="width:100%; height:400px;"></div></div>');
		$('#qestnList1').append('<div class="col-md-6"><h3>답변 목록</h3><div id="qestnText'+data.qestnSeqNo+'" style="max-height:380px; overflow-y:auto;">'+etcListHTML+'</div>');
	} else {
		$('#qestnList'+data.qestnPge).append('<div class="col-md-6"><h3 class="result_tit">문항 명 : '+data.qestnSj+' <small>('+data.totRspnsCnt+'/'+rsCnt+')</small></h3><div id="qestn'+data.qestnSeqNo+'" style="width:100%; height:400px;"></div></div>');
		$('#qestnList'+data.qestnPge).append('<div class="col-md-6"><h3>답변 목록</h3><div id="qestnText'+data.qestnSeqNo+'" style="max-height:380px; overflow-y:auto;"><ul class="list-1st View_content line1">'+etcListHTML+'</ul></div>');
	}
	
	AmCharts.makeChart("qestn" + data.qestnSeqNo, {
						"language" : "ko",
						"type": "pie",
						"balloonText" : "[[title]]<br><span>[[value]] ([[percents]]%)</span>",
						"titleField" : "category",
						"valueField" : "resultCnt",
						"allLabels": [],
						"balloon": {},
						"legend" : {
							"enabled" : true,
							"align" : "center",
							"markerType" : "circle"
						},
						"titles": [
							{
								"id" : data.qestnSj+"title",
								"size" : 15,
								"text" : data.qestnSj
							}
						],
						"dataProvider": statsData
				});
}

</script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1"><c:out value="${qestnarInfo.qestnarNm }"/> 결과</h1>
			<c:if test="${qestnarInfo.qestnarPgeAt eq 'Y' }">
			<ul class="nav nav-tabs" id="pageTabs" role="tablist">
				<c:forEach begin="1" end="${qestnList[0].qestnMaxPge }" step="1" var="pageNum" varStatus="status">
  				<li class="nav-item tab_wrap">
    				<a class="nav-link tab_tit ${status.first ? 'active' : '' }" id="page${pageNum }-tab" href="#page${pageNum }" data-toggle="tab" role="tab" aria-controls="page${pageNum }" aria-selected="true">${pageNum }P</a>
  				</li>
				</c:forEach>
			</ul>
			</c:if>
			<div class="mt-3 tab-content" id="pageContents" style="justify-content: center;">
				<c:if test="${qestnarInfo.qestnarPgeAt eq 'Y' }">
				<c:forEach begin="1" end="${qestnList[0].qestnMaxPge }" step="1" var="pageNum" varStatus="status">
				<div class="tab_inner_con tab-pane ${status.first ? 'fade show active' : '' }" id="page${pageNum }" role="tabpanel" aria-labelledby="page${pageNum }-tab">
					<div id="qestnList${pageNum }" class="qestnListArea row">
					</div>
				</div>
				</c:forEach>
				</c:if>
				<c:if test="${qestnarInfo.qestnarPgeAt ne 'Y' }">
					<div id="qestnList1" class="qestnListArea row">
					</div>
				</c:if>
			</div>
		</div>
	</div>
</div>