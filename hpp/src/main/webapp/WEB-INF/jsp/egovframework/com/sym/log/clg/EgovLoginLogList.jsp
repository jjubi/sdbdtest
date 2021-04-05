<%
/**
 * @Class Name : EgovloginLogList.jsp
 * @Description : 접속로그 정보목록 화면
 * @Modification Information
 * @
 * @  수정일      수정자          수정내용
 * @  -------     --------    ---------------------------
 * @ 2009.03.11   이삼섭          최초 생성
 * @ 2011.07.08   이기하          패키지 분리로 경로 수정(sym.log -> sym.log.clg)
 * @ 2011.09.14   서준식          검색 후 화면에 검색일자 표시안되는 오류 수정
 * @ 2017.09.21   이정은          표준프레임워크 v3.7 개선
 * @author 공통서비스 개발팀 이삼섭
 * @since 2009.03.11
 * @version 1.0
 * @see
 *
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/static/common/taglib.jsp"%>
<c:set var="pageTitle"><spring:message code="comSymLogClg.loginLog.title"/></c:set>

<script type="text/javascript">
$(document).ready(function(){
	fn_egov_init();
});
/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	// 첫 입력란에 포커스..
	document.LoginLogForm.searchWrd.focus();
}
/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	document.LoginLogForm.pageIndex.value = pageNo;
	document.LoginLogForm.action = "<c:url value='/sym/log/clg/selectLoginLogList.do'/>";
   	document.LoginLogForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_loginLog(){
	var vFrom = document.LoginLogForm;

	 if(vFrom.searchEndDe.value != ""){
	     if(vFrom.searchBgnDe.value > vFrom.searchEndDe.value){
// 	         alert("<spring:message code="comSymLogClg.validate.dateCheck" />"); //검색조건의 시작일자가 종료일자보다  늦습니다. 검색조건 날짜를 확인하세요!
	         swAlert("<spring:message code="comSymLogClg.validate.dateCheck" />"); //검색조건의 시작일자가 종료일자보다  늦습니다. 검색조건 날짜를 확인하세요!
	         return;
		  }
	 }else{
		 vFrom.searchEndDe.value = "";
	 }

	vFrom.pageIndex.value = "1";
	vFrom.action = "<c:url value='/sym/log/clg/selectLoginLogList.do'/>";
	vFrom.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_loginLog(logId) {
  	var width = 500;
  	var height = 350;
  	var top = 0;
  	var left = 0;
  	var url = "<c:url value='/sym/log/clg/selectLoginLogDetail.do' />?logId="+logId;
  	var name = "LoginLogDetailPopup"
  	var openWindows = window.open(url,name, "width="+width+",height="+height+",top="+top+",left="+left+",toolbar=no,status=no,location=no,scrollbars=yes,menubar=no,resizable=yes");
}
</script>
<style>
.board_list tbody td img{align: middle;}
</style>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
			<div class="table-responsive">
				<form:form name="LoginLogForm" action="<c:url value='/sym/log/clg/SelectLoginLogList.do'/>" method="post" onSubmit="fn_egov_search_loginLog(); return false;">
					<fieldset>
						<legend>접속로그 검색 영역</legend>
						<input type="hidden" name="pageIndex" value="1"/>
						<div class="ui program-search" title="접속로그 검색 영역">
							<div class="search_inner clearfix float-right">
								<div class="fieldset">
									<spring:message code="comSymLogClg.loginLog.occrrncDe" />
									<div class="search-date">
										<span>
											<input name="searchBgnDe" id="searchBgnDe" type="text" value="${searchVO.searchBgnDe}" class="form-control b-datepicker" title="<spring:message code="comSymLogClg.seachWrd.searchBgnDe" />" size="40" maxlength="10" />
										</span>
									</div>
									<em class="data">~</em>
									<div class="search-date">
										<span>
											<input name="searchEndDe" id="searchEndDe" type="text" value="${searchVO.searchEndDe}" class="form-control b-datepicker" title="<spring:message code="comSymLogClg.seachWrd.searchEndDe" />" size="40" maxlength="10" />
										</span>
									</div>
									<div class="search-select">
										<span>
											<select name="searchCondition" class="form-control" title="검색조건 선택">
												<option value="1" selected="selected"><spring:message code="comSymLogClg.loginLog.loginMthd" /></option>
											</select>
										</span>
									</div>
									<div class="search-text">
										<span>
											<input class="form-control" name="searchWrd" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchWrd}"/>'  maxlength="155" >			
										</span>
									</div>
									<div class="search-btn">
										<button type="button" class="btn btn-sm btn-dark" onclick="fn_egov_search_loginLog();return false;">
											<i class="fa fa-search" aria-hidden="true"></i> 조회
										</button>
									</div>
								</div>
							</div>
						</div>
					</fieldset>
					<div class="ui program-search-form">
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
				</form:form>	
				<table class="table table-bordered text-center">
					<caption>접속로그 목록 : 번호, 로그ID, 발생일자, 접속방식, 사용자명, 접속IP, 상세보기로 구성</caption>
					<colgroup>
						<col style="width: 5%;">
						<col style="width: auto;">
						<col style="width: auto;">
						<col style="width: 10%;">
						<col style="width: 10%;">
						<col style="width: auto;">
						<col style="width: 10%;">
					</colgroup>
					<thead>
						<tr>
							<th><spring:message code="table.num" /></th><!-- 번호 -->
							<th><spring:message code="comSymLogClg.loginLog.logId" /></th><!-- 로그ID -->
							<th><spring:message code="comSymLogClg.loginLog.occrrncDe" /></th><!-- 발생일자 -->
							<th><spring:message code="comSymLogClg.loginLog.loginMthd" /></th><!-- 로그유형 -->
							<th><spring:message code="comSymLogClg.loginLog.loginNm" /></th><!-- 사용자 -->
							<th><spring:message code="comSymLogClg.loginLog.loginIp" /></th><!-- 접속IP -->
							<th><spring:message code="comSymLogClg.loginLog.detail" /></th><!-- 상세보기 -->
						</tr>
					</thead>
					<tbody class="ov">
						<c:if test="${fn:length(resultList) == 0}">
							<tr>
								<td colspan="7"><spring:message code="common.nodata.msg" /></td>
							</tr>
						</c:if>
						<c:forEach items="${resultList}" var="result" varStatus="status">
							<tr>
								<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
								<td><c:out value='${result.logId}'/></td>
								<td><c:out value='${fn:substring(result.creatDt,0,10)}'/></td>
								<td><c:out value='${result.loginMthd}'/></td>
								<td><c:out value='${result.loginNm}'/></td>
								<td><c:out value='${result.loginIp}'/></td>
								<td>
									<a href="javascript:;" class="btn btn-sm btn-info" onclick="fn_egov_detail_loginLog('<c:out value="${result.logId}"/>'); return false;" title="<spring:message code="title.detail" />">상세조회</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>	
				<!-- paging navigation -->
				<div class="text-center">
					<ul class="pagination justify-content-center">
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_linkPage"/>
					</ul>
				</div>		
			</div>
		</div>
	</div>
</div>

