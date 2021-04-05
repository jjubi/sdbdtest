<%
 /**
  * @Class Name : EgovAuthorManage.java
  * @Description : EgovAuthorManage List 화면
  * @Modification Information
  * @
  * @  수정일                     수정자                    수정내용
  * @ -------       --------    ---------------------------
  * @ 2009.03.01    Lee.m.j       최초 생성
  *   2016.06.13    장동한          표준프레임워크 v3.6 개선
  *
  *  @author 실행환경 개발팀 홍길동
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<c:set var="pageTitle"><spring:message code="comCopSecRam.title"/></c:set>
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
                    checkField[i].value = checkId[i].value;
                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else
                	    returnValue = returnValue + ";" + checkField[i].value;
                    checkCount++;
                }
            }
            if(checkCount > 0)
                returnBoolean = true;
            else {
//                 alert("<spring:message code="comCopSecRam.validate.authorSelect" />"); //선택된 권한이 없습니다."
                swAlert("<spring:message code="comCopSecRam.validate.authorSelect" />");
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
//                 alert("<spring:message code="comCopSecRam.validate.authorSelect" />"); //선택된 권한이 없습니다."
				swAlert("<spring:message code="comCopSecRam.validate.authorSelect" />");
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
//         alert("<spring:message code="comCopSecRam.validate.authorSelectResult" />"); //조회된 결과가 없습니다.
        swAlert("<spring:message code="comCopSecRam.validate.authorSelectResult" />");
    }

    document.listForm.authorCodes.value = returnValue;

    return returnBoolean;
}

function fncSelectAuthorList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/ram/selectEgovAuthorList.do'/>";
    document.listForm.submit();
}

function fncSelectAuthor(author) {
    document.listForm.authorCode.value = author;
    document.listForm.action = "<c:url value='/sec/ram/selectEgovAuthor.do'/>";
    document.listForm.submit();
}

function fncAddAuthorInsert() {
    location.replace("<c:url value='/sec/ram/EgovAuthorInsertView.do'/>");
}

function fncAuthorDeleteList() {
    if(fncManageChecked()) {
    	swAlertConfirm('<spring:message code="common.delete.msg" />', {icon:'warning', confirmButtonText:'삭제'}, function(){
    		document.listForm.action = "<c:url value='/sec/ram/EgovAuthorListDelete.do'/>";
            document.listForm.submit();
    	});
    	return false;
    }
}

function fncSelectAuthorRole(author) {
    document.listForm.searchKeyword.value = author;
    document.listForm.action = "<c:url value='/sec/ram/EgovAuthorRoleList.do'/>";
    document.listForm.submit();
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/ram/selectEgovAuthorList.do'/>";
    document.listForm.submit();
}


function press() {
    if (event.keyCode==13) {
    	fncSelectAuthorList('1');
    }
}

function fnAuthorHierarchyList(){
	$('#authorHierarchyModal').modal('show');
}
$(document).ready(function(){
	var authorList = '';
	$('#authorHierarchyModal').on('show.bs.modal', function(e){
		//계층구조 가져오기
		$.ajax({
			url : contextPath + '/sec/ram/selectAuthorHierarchyListAjax.do',
			type : 'post',
			dataType : 'json',
			success : function(result){
				if(result.result == 'success'){
					authorList = result.authorList;
					let rowDiv = $('<div class="row"></div>');
					let pformGroupDiv = $('<div class="form-group col-md-6 parntsDiv"></div>');
					let plabelDiv = $('<label for="parntsRole1">부모 롤</label>');
					pformGroupDiv.append(plabelDiv);
					$.each(result.hierarchyList, function(i, v){
						let selectDiv;
						if(i != 0){
							selectDiv = $('<select class="custom-select mt-3" id="parntsRole'+(i+1)+'" name="parntsRole" title="부모롤" data-toggle="select"></select>');
						} else {
							selectDiv = $('<select class="custom-select" id="parntsRole'+(i+1)+'" name="parntsRole" title="부모롤" data-toggle="select"></select>');	
						}
						$.each(result.authorList, function(i2, v2){
							selectDiv.append('<option value="'+v2.authorCode+'" '+(v.parntsRole == v2.authorCode ? 'selected="selected"' : '')+'>'+v2.authorCode+'</option>');
						});
						pformGroupDiv.append(selectDiv);	
					});
					rowDiv.append(pformGroupDiv);
					
					let cformGroupDiv = $('<div class="form-group col-md-6 chldrnDiv"></div>');
					let clabelDiv = $('<label for="chldrnRole1">자식 롤</label>');
					cformGroupDiv.append(clabelDiv);
					$.each(result.hierarchyList, function(i, v){
						let selectDiv;
						if(i != 0){
							selectDiv = $('<select class="custom-select mt-3" id="chldrnRole'+(i+1)+'" name="chldrnRole" title="자식롤" data-toggle="select"></select>');
						} else {
							selectDiv = $('<select class="custom-select" id="chldrnRole'+(i+1)+'" name="chldrnRole" title="자식롤" data-toggle="select"></select>');	
						}
						$.each(result.authorList, function(i2, v2){
							selectDiv.append('<option value="'+v2.authorCode+'" '+(v.chldrnRole == v2.authorCode ? 'selected="selected"' : '')+'>'+v2.authorCode+'</option>');
						});
						cformGroupDiv.append(selectDiv);	
					});
					rowDiv.append(cformGroupDiv);
					
					$('#authorHierarchyForm').append(rowDiv);
				} else {
					swAlert('확인','계층구조 가져오기 실패. 관리자에게 문의하세요.','error');	
				}
			}
		});
	}).on('hidden.bs.modal', function(){
		$('#authorHierarchyForm').empty();
	}).on('click', '#hierarchyAddBtn', function(){
		let pCnt = $('select[name="parntsRole"]').length;
		let selectDiv = $('<select class="custom-select mt-3" id="parntsRole'+(pCnt+1)+'" name="parntsRole" title="부모롤" data-toggle="select"></select>');
		$.each(authorList, function(i, v){
			selectDiv.append('<option value="'+v.authorCode+'">'+v.authorCode+'</option>');
		});
		$('.parntsDiv').append(selectDiv);
		
		pCnt = $('select[name="chldrnRole"]').length;
		selectDiv = $('<select class="custom-select mt-3" id="chldrnRole'+(pCnt+1)+'" name="chldrnRole" title="자식롤" data-toggle="select"></select>');
		$.each(authorList, function(i, v){
			selectDiv.append('<option value="'+v.authorCode+'">'+v.authorCode+'</option>');
		});
		$('.chldrnDiv').append(selectDiv);
		
	}).on('click', '#authorHierarchySaveBtn', function(){
		//부모롤은 unique key -> 중복 허용 X
		let parntsRoleVals = [];
		$('select[name="parntsRole"]').each(function(i, v){
			parntsRoleVals.push($(this).val());
		});
		
		if(isArrayDuplicate(parntsRoleVals)){
			swAlert('확인','부모롤에 중복값이 있습니다 확인해주세요.','warning');	
			return false;	
		}
		
		let form = document.getElementById('authorHierarchyForm');
		
		form.action = "<c:url value='/sec/ram/insertAuthorHierarchy.do'/>";
		form.method = "POST";
		
		form.submit();
		
	});
	
});
</script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
			<div class="table-responsive">
				<form:form name="listForm" action="${pageContext.request.contextPath}/sec/ram/selectEgovAuthorList.do" method="post">
					<fieldset>
						<legend>권한관리 검색 영역</legend>
						<input type="hidden" name="authorCode"/>
						<input type="hidden" name="authorCodes"/>
						<input type="hidden" name="pageIndex" value="1"/>
						<div class="ui program-search" title="권한관리 검색 영역">
							<div class="btn-group float-left">
								<input type="button" class="btn btn-sm btn-danger" onClick="fncAuthorDeleteList()" value="<spring:message code="title.delete" />" title="<spring:message code="title.delete" /> <spring:message code="input.button" />" />
								<a class="btn btn-sm btn-basic" href="<c:url value='/sec/ram/EgovAuthorInsertView.do'/>" onClick="javascript:fncAddAuthorInsert();" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
									<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
								</a>
								<input type="button" class="btn btn-sm btn-info" onClick="fnAuthorHierarchyList();" value="권한 계층 설정" title="권한 계층 설정 <spring:message code="input.button" />" />
							</div>
							<div class="search_inner clearfix float-right">
								<div class="fieldset">
									<div class="search-select">
										<span>
											<select name="searchCondition" class="form-control" title="검색조건 선택">
												<option value="1" selected="selected">권한명</option>
											</select>
										</span>
									</div>
									<div class="search-text">
										<span>
											<input class="form-control" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${authorManageVO.searchKeyword}"/>'  maxlength="155" >			
										</span>
									</div>
									<div class="search-btn">
										<button type="button" class="btn btn-sm btn-dark" onclick="fncSelectAuthorList('1') return false;">
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
						<caption>권한관리 목록 : 권한ID, 권한명, 설명, 등록일, 롤정보로 구성</caption>
						<colgroup>
							<col style="width: 9%;">
							<col style="width: 33%;">
							<col style="width: 30%;">
							<col style="width: auto;">
							<col style="width: 10%;">
							<col style="width: 7%;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">
									<input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="<spring:message code="input.selectAll.title" />">
								</th><!-- 번호 -->
								<th scope="col" class="board_th_link">
									<spring:message code="comCopSecRam.list.authorRollId" />
								</th><!-- 권한 ID -->
								<th scope="col">
									<spring:message code="comCopSecRam.list.authorNm" />
								</th><!-- 권한 명 -->
								<th scope="col">
									<spring:message code="comCopSecRam.list.authorDc" />
								</th><!-- 설명 -->
								<th scope="col">
									<spring:message code="table.regdate" />
								</th><!-- 등록일자 -->
								<th scope="col">
									<spring:message code="comCopSecRam.list.authorRoll" />
								</th><!-- 롤 정보 -->
							</tr>
						</thead>
						<tbody class="ov">
							<c:if test="${fn:length(authorList) == 0}">
								<tr>
									<td colspan="6"><spring:message code="common.nodata.msg" /></td>
								</tr>
							</c:if>
							<c:forEach var="author" items="${authorList}" varStatus="status">
								<tr>
									<td scope="row">
										<input type="checkbox" name="delYn" class="check2" title="선택">
										<input type="hidden" name="checkId" value="<c:out value="${author.authorCode}"/>" />
									</td>
									<td>
										<a href="#LINK" onclick="javascript:fncSelectAuthor('<c:out value="${author.authorCode}"/>')"><c:out value="${author.authorCode}"/></a>
									</td>
									<td>
										<c:out value="${author.authorNm}"/>
									</td>
									<td>
										<c:out value="${author.authorDc}"/>
									</td>
									<td>
										<c:out value="${fn:substring(author.authorCreatDe,0,10)}"/>
									</td>
									<td>
										<a class="btn btn-sm btn-success" href="<c:url value='/sec/ram/EgovAuthorRoleList.do'/>?searchKeyword=<c:out value="${author.authorCode}"/>" onclick="javascript:fncSelectAuthorRole('<c:out value="${author.authorCode}"/>')">롤 정보</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- 테이블 : e -->
					<!-- paging navigation -->
					<div class="text-center">
						<ul class="pagination justify-content-center">
							<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fncSelectAuthorList"/>
						</ul>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<!-- 권한 계층 설정 모달 -->
<div class="modal fade" id="authorHierarchyModal" data-backdrop="static" aria-labelledby="authorHierarchyModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<div class="col">
					<h2 class="modal-title" id="authorHierarchyModalLabel">권한 계층 설정</h2>
				</div>
				<div class="col-auto">
					<a class="btn btn-primary" id="hierarchyAddBtn">추가</a>
				</div>
			</div>
			<div class="modal-body">
				<form id="authorHierarchyForm">
					
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-primary" id="authorHierarchySaveBtn">저장</button>
			</div>
		</div>
	</div>
</div>
