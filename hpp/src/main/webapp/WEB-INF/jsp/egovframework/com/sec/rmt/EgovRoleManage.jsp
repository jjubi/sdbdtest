<%
/**
 * @Class Name : EgovRoleManage.java
 * @Description : EgovRoleManage jsp
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
<c:set var="pageTitle"><spring:message code="comCopSecRmt.title"/></c:set>
<script type="text/javaScript" language="javascript" defer="defer">
function fncCheckAll() {
    var checkField = document.listForm.delYn;
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

function fncManageChecked() {

    var checkField = document.listForm.delYn;
    var checkId = document.listForm.checkId;
    var returnValue = "";
    var returnBoolean = false;
    var checkCount = 0;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkCount++;
                    checkField[i].value = checkId[i].value;
                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else
                        returnValue = returnValue + ";" + checkField[i].value;
                }
            }
            if(checkCount > 0)
                returnBoolean = true;
            else {
//                 alert("<spring:message code="comCopSecRmt.validate.groupSelect"/>"); //선택된  롤이 없습니다.
                swAlert("<spring:message code="comCopSecRmt.validate.groupSelect"/>"); //선택된  롤이 없습니다.
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
//                 alert("<spring:message code="comCopSecRmt.validate.groupSelect"/>"); //선택된  롤이 없습니다.
                swAlert("<spring:message code="comCopSecRmt.validate.groupSelect"/>"); //선택된  롤이 없습니다.
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
//     	alert("<spring:message code="comCopSecRmt.validate.groupSelectResult"/>"); //조회된 결과가 없습니다.
    	swAlert("<spring:message code="comCopSecRmt.validate.groupSelectResult"/>"); //조회된 결과가 없습니다.
    }

    document.listForm.roleCodes.value = returnValue;
    return returnBoolean;
}

function fncSelectRoleList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/rmt/EgovRoleList.do'/>";
    document.listForm.submit();
}

function fncSelectRole(roleCode) {
    document.listForm.roleCode.value = roleCode;
    document.listForm.action = "<c:url value='/sec/rmt/EgovRole.do'/>";
    document.listForm.submit();
}

function fncAddRoleInsert() {
    location.href = "<c:url value='/sec/rmt/EgovRoleInsertView.do'/>";
}

function fncRoleListDelete() {
	if(fncManageChecked()) {
		swAlertConfirm('삭제하시겠습니까?',{icon:'warning',confirmButtonText:'삭제'}, function(){
			document.listForm.action = "<c:url value='/sec/rmt/EgovRoleListDelete.do'/>";
	    	document.listForm.submit();
		});
		return false;
    }
}

function fncAddRoleView() {
    document.listForm.action = "<c:url value='/sec/rmt/EgovRoleUpdate.do'/>";
    document.listForm.submit();
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/rmt/EgovRoleList.do'/>";
    document.listForm.submit();
}

function press() {
    if (event.keyCode==13) {
    	fncSelectRoleList('1');
    }
}
</script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
			<div class="table-responsive">
				<form:form name="listForm" action="${pageContext.request.contextPath}/sec/rmt/EgovRoleList.do" method="post">
					<fieldset>
						<legend>롤관리 검색 영역</legend>
						<input type="hidden" name="roleCode"/>
						<input type="hidden" name="roleCodes"/>
						<input type="hidden" name="pageIndex" value="1"/>
						<div class="ui program-search" title="롤관리 검색 영역">
							<div class="btn-group float-left">
								<input type="button" class="btn btn-sm btn-danger" onClick="fncRoleListDelete()" value="<spring:message code="title.delete" />" title="<spring:message code="title.delete" /> <spring:message code="input.button" />" />
								<a class="btn btn-sm btn-basic" href="<c:url value='/sec/rmt/EgovRoleInsertView.do'/>" onClick="javascript:fncAddRoleInsert();" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
									<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
								</a>
							</div>
							<div class="search_inner clearfix float-right">
								<div class="fieldset">
									<div class="search-select">
										<span>
											<select name="searchCondition" class="form-control" title="검색조건 선택">
												<option value="1" selected="selected">롤명</option>
											</select>
										</span>
									</div>
									<div class="search-text">
										<span>
											<input class="form-control" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${roleManageVO.searchKeyword}"/>'  maxlength="155" >			
										</span>
									</div>
									<div class="search-btn">
										<button type="button" class="btn btn-sm btn-dark" onclick="fncSelectRoleList(1); return false;">
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
						<caption>롤관리 목록 : 롤ID, 롤명, 롤타입, 롤Sort, 롤 설명, 등록일로 구성</caption>
						<colgroup>
							<col style="width: 3%;">
							<col style="width: 12%;">
							<col style="width: 12%;">
							<col style="width: 8%;">
							<col style="width: 8%;">
							<col style="width: auto;">
							<col style="width: 10%;">
						</colgroup>
						<thead>
							<tr>
								<th>
									<input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="<spring:message code="input.selectAll.title" />">
								</th><!-- 번호 -->
								<th>
									<spring:message code="comCopSecRam.list.rollId" />
								</th><!-- 롤 ID -->
								<th>
									<spring:message code="comCopSecRam.list.rollNm" />
								</th><!-- 롤 명 -->
								<th>
									<spring:message code="comCopSecRam.list.rollType" />
								</th><!-- 롤 타입 -->
								<th>
									<spring:message code="comCopSecRam.list.rollSort" />
								</th><!-- 롤 Sort -->
								<th>
									<spring:message code="comCopSecRam.list.rollDc" />
								</th><!-- 롤 설명 -->
								<th>
									<spring:message code="table.regdate" />
								</th><!-- 등록일자 -->
							</tr>
						</thead>
						<tbody class="ov">
							<c:if test="${fn:length(roleList) == 0}">
								<tr>
									<td colspan="7"><spring:message code="common.nodata.msg" /></td>
								</tr>
							</c:if>
							<c:forEach var="role" items="${roleList}" varStatus="status">
								<tr>
									<td>
										<input type="checkbox" name="delYn" class="check2" title="선택">
										<input type="hidden" name="checkId" value="<c:out value="${role.roleCode}"/>" />
									</td>
									<td>
										<a href="<c:url value='/sec/rmt/EgovRoleList.do'/>?roleCode=${role.roleCode}" onclick="javascript:fncSelectRole('<c:out value="${role.roleCode}"/>');return false;">
											<c:out value="${role.roleCode}"/>
										</a>
									</td>
									<td>
										<c:out value="${role.roleNm}"/>
									</td>
									<td>
										<c:out value="${role.roleTyp}"/>
									</td>
									<td>
										<c:out value="${role.roleSort}"/>
									</td>
									<td>
										<c:out value="${role.roleDc}"/>
									</td>
									<td>
										<c:out value="${fn:substring(role.roleCreatDe,0,10)}"/>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- paging navigation -->
					<div class="text-center">
						<ul class="pagination justify-content-center">
							<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fncSelectRoleList"/>
						</ul>
					</div>	
				</form:form>
			</div>
		</div>
	</div>
</div>

