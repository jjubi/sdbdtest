<%
/**
 * @Class Name  : EgovRoleUpdate.java
 * @Description : EgovRoleUpdate jsp
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j       최초 생성
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
<c:set var="pageTitle"><spring:message code="comCopSecRmt.title"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="roleManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript">

function fncSelectRoleList() {
    var varFrom = document.getElementById("roleManage");
    varFrom.action = "<c:url value='/sec/rmt/EgovRoleList.do'/>";
    varFrom.submit();
}

function fncRoleUpdate(form) {  
	swAlertConfirm('<spring:message code="common.update.msg" />', {icon:'info',confirmButtonText:'수정'},function(){
		if(!validateRoleManage(form)){
            return false;
        }else{
        	form.submit();
        }
	});
	return ;
}

function fncRoleDelete() {
    var varFrom = document.getElementById("frmIdDelete");
    varFrom.action = "<c:url value='/sec/rmt/EgovRoleDelete.do'/>";
    swAlertConfirm('<spring:message code="common.delete.msg" />', {icon:'info',confirmButtonText:'삭제'},function(){
    	varFrom.submit();
    });
    return false;
}

</script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.update" /></h1>
			<form:form commandName="roleManage" method="post" action="${pageContext.request.contextPath}/sec/rmt/EgovRoleUpdate.do" onSubmit="fncRoleUpdate(document.forms[0]); return false;">
				<div class="card-wrap">
					<div class="form-horizontal contents-form">
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comCopSecRam.list.rollId" /></c:set>
								<label for="roleNm">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<c:out value="${roleManage.roleCode}"/>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comCopSecRam.regist.rollNm" /></c:set>
								<label for="roleNm">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="roleNm" class="form-control" title="${title} ${inputTxt}" size="40" maxlength="50" />
								<div><form:errors path="roleNm" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comCopSecRam.regist.rollPtn" /></c:set>
								<label for="rolePtn">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="rolePtn" class="form-control" title="${title} ${inputTxt}" size="40" maxlength="200" />
								<div><form:errors path="rolePtn" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comCopSecRam.regist.rollDc" /></c:set>
								<label for="roleDc">${title} </label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:textarea path="roleDc" class="form-control" title="${title} ${inputTxt}" cols="300" rows="10" />
								<div><form:errors path="roleDc" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comCopSecRam.regist.rollType" /></c:set>
								<label for="roleTyp">${title} <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:select path="roleTyp" class="form-control">
									<form:options items="${roleTypeComCodeList}" itemValue="code" itemLabel="codeNm"/>
								</form:select>
								<div><form:errors path="roleTyp" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comCopSecRam.regist.rollSort" /></c:set>
								<label for="roleSort">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="roleSort" class="form-control onlyNum" title="${title} ${inputTxt}" size="40" maxlength="200" />
								<div><form:errors path="roleSort" cssClass="error" /></div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-wrap-footer">
					<button type="button" class="btn btn-outline-secondary" onclick="fncSelectRoleList();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
						<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
					</button>
					<div class="float-right">
						<button type="button" class="btn btn-danger" onclick="fncRoleDelete(); return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />">
							<spring:message code="button.delete" /><!-- 삭제 -->
						</button>
						<button type="submit" class="btn btn-warning" title="<spring:message code="button.update" /> <spring:message code="input.button" />">
							<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.update" />
						</button>
					</div>
				</div>
				<input type="hidden" name="roleCode" value="<c:out value='${roleManage.roleCode}'/>"/>
				<input type="hidden" name="searchCondition" value="<c:out value='${roleManageVO.searchCondition}'/>"/>
				<input type="hidden" name="searchKeyword" value="<c:out value='${roleManageVO.searchKeyword}'/>"/>
				<input type="hidden" name="pageIndex" value="<c:out value='${roleManageVO.pageIndex}'/>"/>
			</form:form>
		</div>
	</div>
</div>

<form id="frmIdDelete" name="frmDelete" method="post">
	<input type="hidden" name="roleCode" value="<c:out value='${roleManage.roleCode}'/>"/>
	<input type="hidden" name="searchCondition" value="<c:out value='${roleManageVO.searchCondition}'/>"/>
	<input type="hidden" name="searchKeyword" value="<c:out value='${roleManageVO.searchKeyword}'/>"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${roleManageVO.pageIndex}'/>"/>
</form>


