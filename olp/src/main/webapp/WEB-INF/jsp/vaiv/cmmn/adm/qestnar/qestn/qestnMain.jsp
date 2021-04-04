<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : qestnMain.jsp
  * @상세설명 : 설문조사 문항 관리 목록 표출 페이지
  * @작성일시 : 2020. 12. 31
  * @작 성 자 : jo
  * @수 정 이 력
  * @
  * @  수정일         수정자                   수정내용
  * @ -------       --------    ---------------------------
  * @ 2020.12.31   jo	  최초등록
  * @ 
  * 
  */
%>
<link rel="stylesheet" type="text/css" href="/static/css/plugins/dragula/dragula.min.css" />
<script src="${pageContext.request.contextPath }/static/js/plugins/dragula/dragula.min.js"></script>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/qestnar/qestn/qestn.js"></script>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/qestnar/qestn/qestnHTML.js"></script>

<script>
$(document).ready(function(){
	//설문 초기 세팅
	$.initQestn({
		qestnTySelectTrget : 'qestnTypes',
		qestnRegistBtn : 'qestnRegistBtn',
		container : 'qestnList1',
		maxAswperCnt : 5,
		setOpen : function(modal){
			console.log(modal);
		},
		setClose : function(modal){
			console.log(modal);
		},
		confirm : function(modal, qestnData) {
			console.log('confirm : ' + modal);
			console.log('qestnData : ' + qestnData);
			let view = qestnTyByQestnHTML[qestnData.qestnTy].setView(qestnData);
			
			if(eval("${qestnarInfo.qestnarPgeAt eq 'Y' }")){
				let activeTab = $('#pageTabs').find('li > a.active').attr('href');
				$(activeTab).find('.qestnListArea').append(view);
			} else {
				$('.qestnListArea').append(view);
			}
		}, 
		modify : function(modal, qestnData){
			console.log('confirm : ' + modal);
			console.log('qestnData : ' + qestnData);
			let view = qestnTyByQestnHTML[qestnData.qestnTy].setView(qestnData);
			$('#'+qestnData.qestnId).replaceWith(view);
		},
		qestnData : fnSelectQestnList('${qestnarInfo.qestnarSeqNo }')
	});
})
</script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">문항 관리 <small>(<c:out value="${qestnarInfo.qestnarNm }"/>)</small></h1>
			<fieldset>
				<legend>문항관리 영역</legend>
				<input type="hidden" name="sysCmptnCode">
				<input type="hidden" name="pageIndex" value="1"/>
				<div class="ui program-search" title="문항관리 영역">
					<div class="btn-group float-left">
						<a class="btn btn-sm btn-basic" href="javascript:qestnarQestnSave();" title="<spring:message code="button.save" /> <spring:message code="input.button" />">
							<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.save" />
						</a>
						<button class="btn btn-sm btn-outline-secondary" onclick="fnSelectQestnarList();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
							<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
						</button>
					</div>
					<div class="search_inner clearfix float-right">
						<div class="fieldset">
							<div class="search-select">
								<span>
									<select id="qestnTypes" class="custom-select custom-select-sm" title="문항 유형 목록" data-toggle="select">
									</select>
								</span>
							</div>
							<div class="search-btn">
								<button type="button" id="qestnRegistBtn" class="btn btn-sm btn-dark" onclick="return false;">
									생성
								</button>
							</div>
						</div>
					</div>
				</div>
			</fieldset>
			<c:if test="${qestnarInfo.qestnarPgeAt eq 'Y' }">
			<ul class="nav nav-tabs" id="pageTabs" role="tablist">
  				<li class="nav-item tab_wrap">
    				<a class="nav-link tab_tit active" id="page1-tab" href="#page1" data-toggle="tab" role="tab" aria-controls="page1" aria-selected="true">1P</a>
  				</li>
  				<li class="nav-item">
    				<a class="nav-link tab_tit plus" href="javascript:addQestnPage();"><i class="fas fa-plus"></i></a>
  				</li>
			</ul>
			</c:if>
			<div class="row align-items-center mt-3 tab-content" id="pageContents" style="justify-content: center;">
				<c:if test="${qestnarInfo.qestnarPgeAt eq 'Y' }">
				<div class="tab_inner_con tab-pane fade show active" id="page1" role="tabpanel" aria-labelledby="page1-tab" style="width:55%;">
					<div id="qestnList1" class="qestnListArea">
						
					</div>
				</div>
				</c:if>
				<c:if test="${qestnarInfo.qestnarPgeAt ne 'Y' }">
					<div id="qestnList1" class="qestnListArea">
						
					</div>
				</c:if>
			</div>
		</div>
	</div>
</div>

<input type="hidden" name="qestnarSeqNo" value="${qestnarInfo.qestnarSeqNo }">

<form id="hiddenSearchForm" class="d-none" method="post">
	<input type="hidden" name="searchKeyword" value="${searchVO.searchKeyword }">
	<input type="hidden" name="searchCondition" value="${searchVO.searchCondition }">
	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }">
</form>









