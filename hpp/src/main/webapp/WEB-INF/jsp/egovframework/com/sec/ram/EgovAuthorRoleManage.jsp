<%
/**
 * @Class Name : EgovAuthorRoleManage.java
 * @Description : EgovAuthorRoleManage.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2009.02.01    lee.m.j     최초 생성
 *   2016.06.13    장동한        표준프레임워크 v3.6 개선
 *
 *  @author lee.m.j
 *  @since 2009.03.21
 *  @version 1.0
 *  @see
 *
 */
%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<c:set var="pageTitle"><spring:message code="comCopSecRam.authorRoleList.title"/></c:set>
<script type="text/javaScript" language="javascript" defer="defer">
function fncManageChecked() {

    var checkId = document.listForm.checkId;
    var checkRegYn = document.listForm.regYn;
    var returnValue = "";
    var returnRegYns = "";
    var returnBoolean = false;

    for(let i = 0; i < checkRegYn.length; i++){
    	let checkRegYnStr = checkRegYn[i].checked ? 'Y' : 'N';
    	if(returnValue == "") {
			returnValue = checkId[i].value;
		    returnRegYns = checkRegYnStr;
		} else {
		    returnValue = returnValue + ";" + checkId[i].value;
		    returnRegYns = returnRegYns + ";" + checkRegYnStr;
		}
    }
    
    if(returnValue != "") {
		returnBoolean = true;
    } 
    
    document.listForm.roleCodes.value = returnValue;
    document.listForm.regYns.value = returnRegYns;

    return returnBoolean;

}

function fncAddAuthorRoleInsert() {
	if(fncManageChecked()) {
		swAlertConfirm('<spring:message code="comCopSecRam.authorRoleList.validate.confirm.regist" />',{icon:'info',confirmButtonText:'등록'}, function(){
			document.listForm.action = "<c:url value='/sec/ram/EgovAuthorRoleInsert.do'/>";
            document.listForm.submit();
		});
	} else {
		return;
	}
}

function fncSelectAuthorList(){
    // document.listForm.searchCondition.value = "1";
    // document.listForm.pageIndex.value = "1";
    
    //document.listForm.searchKeyword.value = "";
    //document.listForm.action = "<c:url value='/sec/ram/selectEgovAuthorList.do'/>";
    //document.listForm.submit();
    location.href = "<c:url value='/sec/ram/selectEgovAuthorList.do'/>";
}
</script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" />
				<small>(<c:out value="${authorManage.authorNm }"/>)</small>
			</h1>
			<div class="table-responsive">
				<form:form name="listForm" action="${pageContext.request.contextPath}/sec/ram/EgovAuthorRoleList.do" method="post">
					<table class="table table-bordered text-center table-hover">
						<caption>권한 롤관리 목록 : 번호, 롤ID, 롤 명, 롤 타입, 롤 SORT, 롤 설명, 등록일, 등록여부로 구성</caption>
						<colgroup>
							<col style="width: 5%;">
							<col style="width: 10%;">
							<col style="width: 12%;">
							<col style="width: 8%;">
							<col style="width: 8%;">
							<col style="width: auto;">
							<col style="width: 8%;">
							<col style="width: 8%;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col" class="text-center">
									번호
								</th><!-- 번호 -->
								<th>
									<spring:message code="comCopSecRam.authorRoleList.rollId" />
								</th><!-- 롤 ID -->
								<th>
									<spring:message code="comCopSecRam.authorRoleList.rollNm" />
								</th><!-- 롤 명 -->
								<th>
									<spring:message code="comCopSecRam.authorRoleList.rollType" />
								</th><!-- 롤 타입 -->
								<th>
									<spring:message code="comCopSecRam.authorRoleList.rollSort" />
								</th><!-- 롤 Sort -->
								<th>
									<spring:message code="comCopSecRam.authorRoleList.rollDc" />
								</th><!-- 롤 설명 -->
								<th>
									<spring:message code="table.regdate" />
								</th><!--등록일 -->
								<th>
									<spring:message code="comCopSecRam.authorRoleList.regYn" />
								</th><!-- 등록여부 -->
							</tr>
						</thead>
						<tbody class="ov">
							<c:if test="${fn:length(authorRoleList) == 0}">
								<tr>
									<td colspan="8"><spring:message code="common.nodata.msg" /></td>
								</tr>
							</c:if>
							<c:forEach var="authorRole" items="${authorRoleList}" varStatus="status">
								<tr>
									<td><c:out value="${status.count }"/></td>
									<td><c:out value="${authorRole.roleCode}"/></td>
									<td><c:out value="${authorRole.roleNm}"/></td>
									<td><c:out value="${authorRole.roleTyp}"/></td>
									<td><c:out value="${authorRole.roleSort}"/></td>
									<td><c:out value="${authorRole.roleDc}"/></td>
									<td><c:out value="${fn:substring(authorRole.creatDt,0,10)}"/></td>
									<td>
										<input type="hidden" name="checkId" value="<c:out value="${authorRole.roleCode}"/>" />
										<div class="custom-control custom-switch text-center">
											<input type="checkbox" class="custom-control-input" name="regYn" id="regYn${status.index }" <c:if test="${authorRole.regYn == 'Y'}">checked="checked"</c:if>>
											<label class="custom-control-label" for="regYn${status.index }"></label>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="card-wrap-footer">
						<button type="button" class="btn btn-outline-secondary" onclick="fncSelectAuthorList();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
							<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
						</button>
						<div class="float-right">
							<button type="button" onclick="fncAddAuthorRoleInsert();" class="btn btn-basic" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
								<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
							</button>
						</div>
					</div>
					<input type="hidden" name="roleCodes"/>
					<input type="hidden" name="regYns"/>
					<input type="hidden" name="pageIndex" value="<c:out value='${authorRoleManageVO.pageIndex}'/>"/>
					<input type="hidden" name="authorCode" value="<c:out value="${authorRoleManageVO.searchKeyword}"/>"/>
					<input type="hidden" name="searchCondition">
					<input type="hidden" name="searchKeyword" value="<c:out value="${authorRoleManageVO.searchKeyword}"/>">
				</form:form>
			</div>
		</div>
	</div>
</div>

