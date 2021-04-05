<%
/**
 * @Class Name  : egovAuthorUpdate.java
 * @Description : egovAuthorUpdate jsp
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j          최초 생성
 *   2016.06.13    장동한          표준프레임워크 v3.6 개선
 *
 *  @author lee.m.j
 *  @since 2009.03.11
 *  @version 1.0
 *  @see
 *
 */
 %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/static/common/taglib.jsp"%>
<c:set var="pageTitle"><spring:message code="comCopSecRam.title"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="authorManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectAuthorList() {
	let varFrom = document.getElementById("authorManage");
	varFrom.action = "<c:url value='/sec/ram/selectEgovAuthorList.do'/>";
	varFrom.submit();
}

function fncAuthorUpdate(form) {
	swAlertConfirm('<spring:message code="common.update.msg" />', {icon:'info',confirmButtonText:'수정'},function(){
		if(!validateAuthorManage(form)){
        	return false;
        }else{
        	form.submit();
        }
	});
}

function fncAuthorDelete() {
	swAlertConfirm('<spring:message code="common.delete.msg" />', {icon:'warning',confirmButtonText:'삭제'},function(){
		let varFrom = document.getElementById("authorManage");
		varFrom.action = "<c:url value='/sec/ram/deleteEgovAuthor.do'/>";
		varFrom.submit();
	});
}

</script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.update" /></h1>
			<form:form commandName="authorManage" action="${pageContext.request.contextPath}/sec/ram/updateEgovAuthor.do" method="post" onSubmit="fncAuthorUpdate(document.forms[0]); return false;">
				<div class="card-wrap">
					<div class="form-horizontal contents-form">
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comCopSecRam.regist.authorCode" /></c:set>
								<label for="authorCode">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:hidden path="authorCode"/>
								<c:out value="${authorManage.authorCode }" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comCopSecRam.regist.authorNm" /></c:set>
								<label for="authorNm">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="authorNm" class="form-control" title="${title} ${inputTxt}" size="40" maxlength="60" />
								<div><form:errors path="authorNm" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comCopSecRam.regist.authorDc" /></c:set>
								<label for="authorDc">${title} <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:textarea path="authorDc" class="form-control" title="${title} ${inputTxt}" cols="300" rows="10" />
								<div><form:errors path="authorDc" cssClass="error" /></div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-wrap-footer">
					<button type="button" class="btn btn-outline-secondary" onclick="fncSelectAuthorList();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
						<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
					</button>
					<div class="float-right">
						<button type="button" onclick="fncAuthorDelete();" class="btn btn-danger" title="<spring:message code="button.delete" /> <spring:message code="input.button" />">
							<spring:message code="button.delete" /><!-- 삭제 -->
						</button>
						<button type="submit" class="btn btn-warning" title="<spring:message code="button.update" /> <spring:message code="input.button" />">
							<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.update" />
						</button>
					</div>
				</div>
				<input type="hidden" name="searchCondition" value="<c:out value='${authorManageVO.searchCondition}'/>"/>
				<input type="hidden" name="searchKeyword" value="<c:out value='${authorManageVO.searchKeyword}'/>"/>
				<input type="hidden" name="pageIndex" value="<c:out value='${authorManageVO.pageIndex}'/>"/>
			</form:form>
		</div>
	</div>
</div>

