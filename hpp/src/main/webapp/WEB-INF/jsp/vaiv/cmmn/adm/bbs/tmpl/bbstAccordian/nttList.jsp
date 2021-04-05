<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : bbstAccordian > nttList.jsp
  * @상세설명 : accordian 게시물 목록 표출 페이지 
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
<%@ include file="/WEB-INF/jsp/vaiv/cmmn/adm/bbs/tmpl/nttCommon.jsp" %>
<% pageContext.setAttribute("rlcn", "\n"); %>
<c:set var="pageTitle"><c:out value="${bbsVO.bbsNm }"/></c:set>

<script>
$(document).ready(function(){
  	let nowPageIndex = '${searchVO.pageIndex}';
	let pidx = nowPageIndex != '' ? nowPageIndex : 1;
	$('input[name="pageIndex"]').val(pidx);
	let baseUrl = location.protocol + "//" + location.host + location.pathname;
	
	$('.board_info_btn').each(function(i, v){
		let nttId = $(this).parents('div.accordion-header').find('input[name="nttId"]').val();
		let searchParam = $('#listForm').sArrayToSerialize(['bbsTyCode'], ['']);
		let urlstr = baseUrl + '?' + searchParam + '&nttId=' + nttId;
		$(this).setLinkBtn(urlstr);
	});
	$('.board_info_btn').setPrintBtn('contentsArea');
	$('.board_info_btn').each(function(){
		let nttId = $(this).parents('div.accordion-header').find('input[name="nttId"]').val();
		let searchParam = $('#listForm').sArrayToSerialize(['bbsTyCode'], ['']);
		let urlstr = baseUrl + '?' + searchParam + '&nttId=' + nttId;
		$(this).setSocialShareBtn(urlstr, '${pageTitle}');
	});
	
	$('input[name="pageIndex"]').val(1);
	$('#accordionDiv a[data-toggle="collapse"]').click(function(){
		let numbering = $(this).data('numbering');
		mapRelayout(numbering);
		$('.board_info_btn').hide();
		$(this).next('.board_info_btn').show();
// 		setTimeout("vmap["+numbering+"].updateSize()", 1000);
// 		setTimeout("move('36.4814966430506', '127.300584068306', '1', '1')", 2000);
	});
});

function move(x,y,z, listNum){
	console.log(x);
	console.log(y);
	console.log(z);
	console.log(listNum);
	var _center = [ x, y ];
	var z = z;
	var pan = ol.animation.pan({
		duration : 2000,
		source : (vmap[listNum].getView().getCenter())
	});
	vmap[listNum].beforeRender(pan);
	vmap[listNum].getView().setCenter(_center);
	setTimeout("fnMoveZoom("+listNum+")", 3000);
}
	   
function fnMoveZoom(num) {
	zoom = vmap[num].getView().getZoom();
	if (16 > zoom) {
		vmap[num].getView().setZoom(14);
	}
};
</script>

<div class="container-fluid" id="contentsArea">
	<h1 class="header-title">${pageTitle}</h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
			<form id="listForm" name="listForm" method="post" action="${pageContext.request.contextPath }/cmmn/adm/bbs/ntt/${bbsId}/nttList.do" onsubmit="return false;">
				<fieldset>
					<legend>게시물 검색 영역</legend>
					<input type="hidden" name="bbsTyCode" value="<c:out value="${bbsTyCode }"/>"/>
					<input type="hidden" name="pageIndex" value="1"/>
					<div class="ui program-search" title="게시물 검색 영역">
						<div class="btn-group float-left">
							<a class="btn btn-sm btn-basic" href="${pageContext.request.contextPath }/cmmn/adm/bbs/ntt/${bbsId}/nttRegist.do" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
								<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
							</a>
						</div>
						<div class="search_inner clearfix float-right">
							<div class="fieldset">
								<div class="search-select">
									<span>
										<select name="searchCondition" class="form-control" title="검색조건 선택">
											<option value="1" <c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>>제목</option>
											<option value="2" <c:if test="${searchVO.searchCondition eq '2'}">selected="selected"</c:if>>내용</option>
										</select>
									</span>
								</div>
								<div class="search-text">
									<span>
										<input type="text" class="form-control pressEnter" name="searchKeyword" data-press="fnSelectNttList('1');" placeholder="검색어 입력 ..." title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155">			
									</span>
								</div>
								<div class="search-btn">
									<button type="button" class="btn btn-sm btn-dark" onclick="fnSelectNttList('1')">
										<i class="fa fa-search" aria-hidden="true"></i> 조회
									</button>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="ui program-search-form">
                    <!-- 게시글정보 -->
                    <div class="ui program-info">
                        <div class="ui program-count">
                            <span>총 게시물 
                            	<strong><c:out value="${paginationInfo.totalRecordCount }"/></strong> 개
                            </span>, 
                            <span class="ui program--division-line">페이지 
                            	<strong><c:out value="${paginationInfo.currentPageNo }"/></strong>
                                / <c:out value="${paginationInfo.totalPageCount }"/>
                            </span>
                        </div>
                    </div>
                </div>
			</form>
			<div id="accordionDiv">
				<script>
					vmap = {};
				</script>
<!-- 				<script type="text/javascript" src="http://map.vworld.kr/js/vworldMapInit.js.do?version=2.0&apiKey=6C37422A-3B55-3858-B49B-498CB1DB5385"></script> -->
				<c:forEach items="${nttList }" var="nttVO" varStatus="status">
					<div class="accordion-header">
						<input type="hidden" name="nttId" value="${nttVO.nttId }">
						<div id="heading${status.count }">
							<a href="#collapse${status.count }" class="${empty nttId ? (status.first ? '' : 'collapsed') : (nttId eq nttVO.nttId ? '' : 'collapsed') }" data-toggle="collapse" aria-expanded="${empty nttId ? (status.first ? 'true' : 'false') : (nttId eq nttVO.nttId ? 'true' : 'false') }" aria-controls="collapse${status.count }" data-numbering="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + status.index) }">
								<h5 class="accordion_tit">
									<c:out value="${nttVO.nttSj }" />
								</h5>
							</a>
							<div class="board_info_btn" style="${empty nttId ? (status.first ? 'display: inline-block;' : 'display: none;') : (nttId eq nttVO.nttId ? 'display: inline-block;' : 'display: none;') }">
<!-- 							<button type="button" class="linkBtn"><i class="board-icon icon-linkcopy">링크 아이콘</i></button> -->
<!-- 							<button type="button" class="printBtn"><i class="board-icon icon-print">인쇄 아이콘</i></button> -->
<!-- 							<button type="button" class="socialShare" data-toggle="dropdown"><i class="board-icon icon-share">공유 아이콘</i></button> -->
							</div>
						</div>
						<div id="collapse${status.count }" class="collapse ${empty nttId ? (status.first ? 'show' : '') : (nttId eq nttVO.nttId ? 'show' : '') }" aria-labelledby="heading${status.count }" data-parent="#accordionDiv">
							<div class="accordion-body">
								<div class="body_text">
									<c:out value="${fn:replace(nttVO.nttCn, rlcn, '<br/>') }"/>
								</div>
						    	<c:if test="${nttVO.lcIndictAt eq 'Y' }">
 						    		<c:import url="/cmmn/com/cuc/mapView.do">
						    			<c:param name="lat" value="${nttVO.adresLa }"/>
						    			<c:param name="lng" value="${nttVO.adresLo }"/>
						    			<c:param name="addr" value="${nttVO.adres }"/>
						    			<c:param name="listNum" value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + status.index) }"/>
						    		</c:import>
<%--						    		<c:import url="/cmmn/com/cuc/vworldMapView.do">
						    			<c:param name="lat" value="${nttVO.adresLa }"/>
						    			<c:param name="lng" value="${nttVO.adresLo }"/>
						    			<c:param name="addr" value="${nttVO.adres }"/>
						    			<c:param name="listNum" value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + status.index) }"/>
						    		</c:import> --%>
							    </c:if>
							    <c:if test="${not empty nttVO.atchFileId }">
								<div class="board_view_file">
									<c:import url="/cmmn/com/cfc/nttFileList.do">
										<c:param name="atchFileId" value="${nttVO.atchFileId }"/>
										<c:param name="nttId" value="${nttVO.nttId }"/>
										<c:param name="loadType" value="viewFileList"/>
									</c:import>
								</div>
								</c:if>
								<div class="accordion-info row">
									<div class="col">
									    <small><c:out value="${nttVO.registNm }"/></small>
									    <small>|<span> <c:out value="${nttVO.registDe }"/></span></small>
									</div>
									<sec:authorize access="hasRole('${bbsAuthorVO.nttDeleteAuthor }')">
									<c:if test="${loginVO.uniqId eq nttVO.registId || isAdmin == true}">
										<button type="button" onclick="fnDeleteNtt('<c:out value="${nttVO.nttId }"/>');" class="btn btn-sm btn-danger" title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></button><!-- 삭제 -->
									</c:if>
									</sec:authorize>
									<sec:authorize access="hasRole('${bbsAuthorVO.nttUpdtAuthor }')">
									<c:if test="${loginVO.uniqId eq nttVO.registId || isAdmin == true}">
										<a href="javascript:fnSelectNttModify('<c:out value="${nttVO.nttId }"/>');" class="btn btn-sm btn-warning" title="<spring:message code="button.update" /> <spring:message code="input.button" />"><spring:message code="button.update" /></a><!-- 수정 -->
									</c:if>
									</sec:authorize>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<!-- paging navigation -->
			<div class="text-center mt-4">
				<ul class="pagination justify-content-center">
					<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnSelectNttList"/>
				</ul>
			</div>
		</div>
	</div>
</div>





<%-- 
<form:form commandName="listForm" name="listForm" method="post" action="">
	<div class="row">
		<div class="col-12">
			<div class="list-group list-group-lg list-group-flush">
				<div class="list-group-item tableView">
					<div class="row align-items-center mb-3">
						<div class="col">
							<h4 class="mb-0" id="menuTitle">${pageTitle} <spring:message code="title.list" /></h4>
						</div>
						<sec:authorize access="hasRole('${bbsAuthorVO.nttRegistAuthor }')">
						<div class="col-auto">
							<a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath }/cmmn/adm/bbs/ntt/${bbsId}/nttRegist.do" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
								<spring:message code="button.create" />
							</a>
						</div>
						</sec:authorize>
					</div>
					<div class="row align-items-center mb-3">
						<div class="col">
							<small>총 게시물 : <c:out value="${paginationInfo.totalRecordCount }"/></small>
						</div>
						<div class="col-auto pr-0">
							<select class="form-control form-control-sm" name="searchCondition">
								<option value="1" <c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>>제목</option>
								<option value="2" <c:if test="${searchVO.searchCondition eq '2'}">selected="selected"</c:if>>내용</option>
							</select>
						</div>
						<div class="col-auto">
							<input class="form-control form-control-sm pressEnter" name="searchKeyword" data-press="fnSelectNttList('1');" type="text" placeholder="검색어 입력 ..." title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155">
						</div>
						<div class="col-auto">
							<input type="submit" class="btn btn-sm btn-secondary searchBtn" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
						</div>
					</div>
					<div class="row align-items-center mb-3 p-3 accordion" id="accordionDiv">
						<!-- 게시글 목록  -->
						<c:if test="${empty nttList or fn:length(nttList) == 0 }">
						<div class="col-md-12 mb-0 card">
							<div class="card-header" id="heading1">
								<a class="form-control text-reset text-decoration-none" href="javascript:;">
									<spring:message code="common.nodata.msg" />
								</a>
							</div>
						</div>
						</c:if>
						<c:forEach items="${nttList }" var="nttVO" varStatus="status">
							<div class="col-md-12 mb-0 card">
								<div class="card-header" id="heading${status.count }">
									<a href="#collapse${status.count }" class="text-reset text-decoration-none" data-toggle="collapse" aria-expanded="${status.first ? 'true' : 'false' }" aria-controls="collapse${status.count }" data-numbering="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + status.index) }">
										<c:out value="${nttVO.nttSj }" />
									</a>
									<div class="float-right">
										<c:import url="/cmmn/com/cuc/socialShareView.do">
										</c:import>
									</div>
								</div>
								<div id="collapse${status.count }" class="collapse ${status.first ? 'show' : '' }" aria-labelledby="heading${status.count }" data-parent="#accordionDiv">
							    	<div class="card-body">
							    		<c:if test="${bbsOptnVO.atchFileUseAt eq 'Y' }">
										<div class="pl-0 pb-3">
										<c:if test="${not empty nttVO.atchFileId }">
											<h6 class="pl-2">첨부파일</h6>
											<c:import url="/cmmn/com/cfc/nttFileList.do">
												<c:param name="atchFileId" value="${nttVO.atchFileId }"/>
												<c:param name="nttId" value="${nttVO.nttId }"/>
												<c:param name="loadType" value="viewFileList"/>
											</c:import>
										</c:if>
										</div>
										</c:if>
										<div class="pl-3 mb-3">
							        		${fn:replace(nttVO.nttCn, rlcn, '<br/>') }
							        	</div>
										<c:if test="${nttVO.lcIndictAt eq 'Y' }">
							    		<div class="mb-3 text-center">
								    		<c:import url="/cmmn/com/cuc/mapView.do">
								    			<c:param name="lat" value="${nttVO.adresLa }"/>
								    			<c:param name="lng" value="${nttVO.adresLo }"/>
								    			<c:param name="addr" value="${nttVO.adres }"/>
								    			<c:param name="listNum" value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + status.index) }"/>
								    		</c:import>
								    	</div>
								    	</c:if>
							        	<div class="row align-items-center">
							        		<div class="col">
							        			<small class="text-center font-weight-bolder"><c:out value="${nttVO.registNm }"/></small>
												/ <small class="text-center font-weight-bolder"><c:out value="${nttVO.registDe }"/></small>
							        		</div>
											<sec:authorize access="hasRole('${bbsAuthorVO.nttDeleteAuthor }')">
											<c:if test="${loginVO.uniqId eq nttVO.registId || isAdmin == true}">
											<div class="col-auto">
												<button type="button" onclick="fnDeleteNtt('<c:out value="${nttVO.nttId }"/>');" class="btn btn-danger" title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></button><!-- 삭제 -->
											</div>
											</c:if>
											</sec:authorize>
											<sec:authorize access="hasRole('${bbsAuthorVO.nttUpdtAuthor }')">
											<c:if test="${loginVO.uniqId eq nttVO.registId || isAdmin == true}">
											<div class="col-auto">
												<a href="/cmmn/adm/bbs/ntt/${bbsId}/nttModify.do?nttId=<c:out value="${nttVO.nttId }"/>" class="btn btn-warning" title="<spring:message code="button.update" /> <spring:message code="input.button" />"><spring:message code="button.update" /></a><!-- 수정 -->
											</div>
											</c:if>
											</sec:authorize>
										</div>
							    	</div>
							    </div>
							</div>
						</c:forEach>
					</div>
					<div class="row align-items-center mb-3">
						<c:if test="${!empty searchVO.pageIndex }">
							<!-- paging navigation -->
							<div class="pagination">
								<ul class="pagination justify-content-center">
									<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnSelectNttList"/>
								</ul>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" name="nttId"/>
	<input type="hidden" name="bbsTyCode" value="<c:out value="${bbsTyCode }"/>"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form:form> --%>