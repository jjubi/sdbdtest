<%
/**
 * @Class Name  : EgovAuthorInsert.java
 * @Description : EgovAuthorInsert jsp
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j          최초 생성
 *   2016.06.13    장동한            표준프레임워크 v3.6 개선
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
<script type="text/javaScript">
function fncSelectAuthorList() {
	var varFrom = document.getElementById("authorManage");
	varFrom.action = "<c:url value='/sec/ram/selectEgovAuthorList.do'/>";
	varFrom.submit();
}

/**
 *  중복이면 return true
 *	중복이 아니면 return false
 */
function fnCheckCodeDup(authorCode){
	let returnVal = false;
	$.ajax({
		url : '<c:url value="/sec/ram/checkAuthorCodeAjax.do"/>',
		type : 'post',
		async : false,
		dataType : 'json',
		data : {"authorCode" : authorCode},
		success : function(data){
			if(data.result == 'success'){
				returnVal = false;
			} else {
				returnVal = true;
			}
		}, error : function(error){
			returnVal = true;
		}
	});
	
	return returnVal;
}

function fncAuthorInsert(form) {
	swAlertConfirm('<spring:message code="common.regist.msg" />',{icon:'info', confirmButtonText:'등록'}, function(){
		let authorCodeVal = form.authorCode.value;
		//중복확인
		if(fnCheckCodeDup(authorCodeVal)){
			Swal.fire('확인', '권한 코드가 중복입니다.', 'warning');
			return false;				
		} else {
			if(!validateAuthorManage(form)){
	        	return false;
	        }else{
	        	form.submit();
	        }
		}
	});
}
</script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.create" /></h1>
			<form:form commandName="authorManage" action="${pageContext.request.contextPath}/sec/ram/insertEgovAuthor.do" method="post" onSubmit="fncAuthorInsert(document.forms[0]); return false;">
				<div class="card-wrap">
					<div class="form-horizontal contents-form">
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comCopSecRam.regist.authorCode" /></c:set>
								<label for="authorCode">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="authorCode" class="form-control" title="${title} ${inputTxt}" size="40" maxlength="30" />
								<div><form:errors path="authorCode" cssClass="error" /></div>
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
					<a class="btn btn-outline-secondary" href="<c:url value='/sec/ram/selectEgovAuthorList.do' />" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
						<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
					</a>
					<div class="float-right">
						<button type="submit" class="btn btn-basic" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
							<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
						</button>
					</div>
				</div>
			</form:form>			
		</div>
	</div>
</div>

