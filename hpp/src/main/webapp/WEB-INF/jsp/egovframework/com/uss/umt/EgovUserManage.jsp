<%

 /**
  * @Class Name : EgovUserManage.jsp
  * @Description : 사용자관리(조회,삭제) JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.02    조재영          최초 생성
  *   2011.09.07    서준식          네비게이션명 변경 (사용자 관리 -> 업무사용자관리)
  *   2016.06.13    장동한          표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스 개발팀 조재영
  *  @since 2009.03.02
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comUssUmt.deptUserManage.title"/></c:set>

<script type="text/javaScript" language="javascript" defer="defer">
function fnCheckAll() {
    var checkField = document.listForm.checkField;
    if(document.listForm.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}

function fnDeleteUser() {
    var checkField = document.listForm.checkField;
    var id = document.listForm.checkId;
    var checkedIds = "";
    var checkedCount = 0;
    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                    checkedIds += ((checkedCount==0? "" : ",") + id[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
                checkedIds = id.value;
            }
        }
    }
    if(checkedIds.length > 0) {
    	//alert(checkedIds);
    	swAlertConfirm('<spring:message code="common.delete.msg" />',{icon:'warning',confirmButtonText:'삭제'}, function(){
    		document.listForm.checkedIdForDel.value=checkedIds;
            document.listForm.action = "<c:url value='/uss/umt/EgovUserDelete.do'/>";
            document.listForm.submit();
    	});
    	return false;
    }
}
function fnSelectUser(id) {
    document.listForm.selectedId.value = id;
    array = id.split(":");
    if(array[0] == "") {
    } else {
        userTy = array[0];
        userId = array[1];
    }
   	document.listForm.selectedId.value = userId;
    document.listForm.action = "<c:url value='/uss/umt/EgovUserSelectUpdtView.do'/>";
    document.listForm.submit();

}
function fnAddUserView() {
    document.listForm.action = "<c:url value='/uss/umt/EgovUserInsertView.do'/>";
    document.listForm.submit();
}
function fnLinkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/uss/umt/EgovUserManage.do'/>";
    document.listForm.submit();
}
function fnSearch(){
	document.listForm.pageIndex.value = 1;
	document.listForm.action = "<c:url value='/uss/umt/EgovUserManage.do'/>";
    document.listForm.submit();
}
function fnViewCheck(){
    if(insert_msg.style.visibility == 'hidden'){
    	insert_msg.style.visibility = 'visible';
    }else{
    	insert_msg.style.visibility = 'hidden';
    }
}
<c:if test="${!empty resultMsg}">
// alert("<spring:message code="${resultMsg}" />");
swAlert("<spring:message code="${resultMsg}" />");
</c:if>
</script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" />
				<small>(관리자 : <c:out value="${paginationInfo.totalRecordCount}"/>명)</small>
			</h1>
			<div class="table-responsive">
				<form id="listForm" name="listForm" action="<c:url value='/uss/umt/EgovUserManage.do'/>" method="post">
					<fieldset>
						<legend>업무사용자관리 검색 영역</legend>
						<input name="selectedId" type="hidden" />
						<input name="checkedIdForDel" type="hidden" />
						<input type="hidden" name="pageIndex" value="1"/>
						<div class="ui program-search" title="업무사용자관리 검색 영역">
							<div class="btn-group float-left">
								<input type="button" class="btn btn-sm btn-danger" onClick="fnDeleteUser(); return false;" value="<spring:message code="title.delete" />" title="<spring:message code="title.delete" /> <spring:message code="input.button" />" />
								<a class="btn btn-sm btn-basic" href="<c:url value='/uss/umt/EgovUserInsertView.do'/>" onClick="fnAddUserView(); return false;" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
									<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
								</a>
							</div>
							<div class="search_inner clearfix float-right">
								<div class="fieldset">
									<div class="search-select">
										<span>
											<select class="form-control" name="sbscrbSttus" id="sbscrbSttus" title="<spring:message code="comUssUmt.userManageSsearch.sbscrbSttusTitle" />">
												<option value="0" <c:if test="${empty userSearchVO.sbscrbSttus || userSearchVO.sbscrbSttus == '0'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.sbscrbSttusAll" /></option><!-- 상태(전체) -->
							                    <option value="A" <c:if test="${userSearchVO.sbscrbSttus == 'A'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.sbscrbSttusA" /></option><!-- 가입신청 -->
							                    <option value="D" <c:if test="${userSearchVO.sbscrbSttus == 'D'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.sbscrbSttusD" /></option><!-- 삭제 -->
							                    <option value="P" <c:if test="${userSearchVO.sbscrbSttus == 'P'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.sbscrbSttusP" /></option><!-- 승인 -->
											</select>
										</span>
									</div>
									<div class="search-select">
										<span>
											<select name="searchCondition" class="form-control" title="검색조건 선택">
												<option value="0" <c:if test="${userSearchVO.searchCondition == '0'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.searchConditionId" /></option><!-- ID  -->
                    							<option value="1" <c:if test="${empty userSearchVO.searchCondition || userSearchVO.searchCondition == '1'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.searchConditionName" /></option><!-- Name -->
											</select>
										</span>
									</div>
									<div class="search-text">
										<span>
											<input class="form-control" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${userSearchVO.searchKeyword}"/>'  maxlength="155" >			
										</span>
									</div>
									<div class="search-btn">
										<button type="button" class="btn btn-sm btn-dark" onclick="fnLinkPage('1') return false;">
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
	                
	                <!-- 테이블 : s -->
					<table class="table table-bordered text-center">
						<caption>업무사용자관리 목록 : 아이디, 사용자이름, 사용자이메일, 전화번호, 등록일, 가입상태로 구성</caption>
						<colgroup>
							<col style="width: 5%;">
							<col style="width: 3%;">
							<col style="width: 15%;">
							<col style="width: auto;">
							<col style="width: 20%;">
							<col style="width: 10%;">
							<col style="width: 13%;">
						</colgroup>
						<thead>
							<tr>
								<th><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fnCheckAll()" title="<spring:message code="input.selectAll.title" />"></th><!-- 전체선택 -->
								<th class="board_th_link"><spring:message code="comUssUmt.userManageList.id" /></th><!--아이디 -->
								<th><spring:message code="comUssUmt.userManageList.name" /></th><!-- 사용자이름 -->
								<th><spring:message code="comUssUmt.userManageList.email" /></th><!-- 사용자이메일 -->
								<th><spring:message code="comUssUmt.userManageList.phone" /></th><!-- 전화번호 -->
								<th><spring:message code="table.regdate" /></th><!-- 등록일 -->
								<th><spring:message code="comUssUmt.userManageList.sbscrbSttus" /></th><!-- 가입상태 -->
						
							</tr>
						</thead>
						<tbody class="ov">
							<c:if test="${fn:length(resultList) == 0}">
								<tr>
									<td colspan="7"><spring:message code="common.nodata.msg" /></td>
								</tr>
							</c:if>
							<c:forEach var="result" items="${resultList}" varStatus="status">
							<tr>
							    <td>
							        <input name="checkField" title="checkField <c:out value="${status.count}"/>" type="checkbox"/>
							        <input name="checkId" type="hidden" value="<c:out value='${result.userTy}'/>:<c:out value='${result.uniqId}'/>"/>
							    </td>
							    <td><a href="<c:url value='/uss/umt/EgovMberSelectUpdtView.do'/>?selectedId=<c:out value="${result.uniqId}"/>"  onclick="javascript:fnSelectUser('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>'); return false;"><c:out value="${result.userId}"/></a></td>
							    <td><c:out value="${result.userNm}"/></td>
							    <td><c:out value="${result.emailAdres}"/></td>
							    <td><c:out value="${result.areaNo}"/>)<c:out value="${result.middleTelno}"/>-<c:out value="${result.endTelno}"/></td>
							    <td><c:out value="${fn:substring(result.sbscrbDe,0,10)}"/></td>
							    <td>
						          <c:forEach var="emplyrSttusCode_result" items="${emplyrSttusCode_result}" varStatus="status">
						              <c:if test="${result.sttus == emplyrSttusCode_result.code}"><c:out value="${emplyrSttusCode_result.codeNm}"/></c:if>
						          </c:forEach>
							    </td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
	                <!-- paging navigation -->
					<div class="text-center">
						<ul class="pagination justify-content-center">
							<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnLinkPage"/>
						</ul>
					</div>	
				</form>
			</div>
		</div>
	</div>
</div>

