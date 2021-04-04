<%
 /**
  * @Class Name : EgovUserSelectUpdt.jsp
  * @Description : 사용자상세조회, 수정 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.02    조재영          최초 생성
  * @ 2015.06.16	조정국		  password 중복필드 정리
  * @ 2016.07.26    장동한          표준프레임워크 v3.6 개선
  * @ 2017.07.21  장동한 			로그인인증제한 작업
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
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUssUmt.deptUserManage.title"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="userManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">
function fnListPage(){
    document.userManageVO.action = "<c:url value='/uss/umt/EgovUserManage.do'/>";
    document.userManageVO.submit();
}
function fnDeleteUser(checkedIds) {
	swAlertConfirm('<spring:message code="common.delete.msg" />',{icon:'warning',confirmButtonText:'삭제'}, function(){
		document.userManageVO.checkedIdForDel.value=checkedIds;
	    document.userManageVO.action = "<c:url value='/uss/umt/EgovUserDelete.do'/>";
	    document.userManageVO.submit();
	});
	return ;
}

function fnPasswordMove(){
	document.userManageVO.action = "<c:url value='/uss/umt/EgovUserPasswordUpdtView.do'/>";
    document.userManageVO.submit();
}

function fnLockIncorrect(){
	swAlertConfirm('<spring:message code="comUssUmt.common.lockAtConfirm" />',{icon:'warning',confirmButtonText:'삭제'}, function(){
		document.userManageVO.action = "<c:url value='/uss/umt/EgovUserLockIncorrect.do'/>";
	    document.userManageVO.selectedId.value=document.userManageVO.uniqId.value;
	    document.userManageVO.submit();
	});
	return false;
}

function fnUpdate(form){
	swAlertConfirm('<spring:message code="common.update.msg" />',{icon:'info',confirmButtonText:'수정'}, function(){
	    if(validateUserManageVO(form)){
	    	form.submit();
	        return true;
	    }else{
	    	return false;
	    }
	});
	return false;
}
function fn_egov_inqire_cert() {
	var url = "<c:url value='/uat/uia/EgovGpkiRegist.do' />";
	var popupwidth = '500';
	var popupheight = '400';
	var title = '인증서';

	Top = (window.screen.height - popupheight) / 3;
	Left = (window.screen.width - popupwidth) / 2;
	if (Top < 0) Top = 0;
	if (Left < 0) Left = 0;
	Future = "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,	scrollbars=no,resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
	PopUpWindow = window.open(url, title, Future)
	PopUpWindow.focus();
}

function fn_egov_dn_info_setting(dn) {
	var frm = document.userManageVO;

	frm.subDn.value = dn;
}

</script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.update" /></h1>
			<form:form commandName="userManageVO" action="${pageContext.request.contextPath}/uss/umt/EgovUserSelectUpdt.do" name="userManageVO" method="post" onSubmit="fnUpdate(document.forms[0]); return false;">
				<div class="card-wrap">
					<div class="form-horizontal contents-form">
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						<c:set var="inputSelect"><spring:message code="input.select"/></c:set>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.id"/></c:set>
								<label for="emplyrId">${title } <span class="pilsu">*</span> 
									<span id="checkIdStr" class="d-block"></span>
								</label>
							</div>
							<div class="col-sm-12 col-md-10">
								<div class="input-group">
									<form:input path="emplyrId" class="form-control" title="${title} 입력" size="40" maxlength="30" readonly="true" required="true"/>
									<div><form:errors path="emplyrId" cssClass="error" /></div>
									<form:hidden path="uniqId" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.name"/></c:set>
								<label for="emplyrNm">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="emplyrNm" class="form-control" title="${title} ${inputTxt}" size="40" maxlength="60" required="true"/>
								<div><form:errors path="emplyrNm" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.pass"/></c:set>
								<label for="password">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<a class="btn btn-danger" href="javascript:fnPasswordMove();">비밀번호 변경</a>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.passHit"/></c:set>
								<label for="passwordHint">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:select path="passwordHint" id="passwordHint" class="form-control" title="${title} ${inputSelect}" required="true">
									<form:option value="" label="${inputSelect}"/>
									<form:options items="${passwordHint_result}" itemValue="code" itemLabel="codeNm"/>
								</form:select>
								<div><form:errors path="passwordHint" cssClass="error"/></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.passOk"/></c:set>
								<label for="passwordCnsr">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="passwordCnsr" id="passwordCnsr" title="${title} ${inputTxt}" class="form-control" size="50" maxlength="100" required="true"/>
								<div><form:errors path="passwordCnsr" cssClass="error"/></div>
							</div>
						</div>
						<%-- <div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<!-- 소속기관코드 -->
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.insttCode"/></c:set>
								<label for="insttCode">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:select path="insttCode" id="insttCode" title="${title} ${inputSelect}">
	                       			<form:option value="" label="${inputSelect}"/>
	                       			<form:options items="${insttCode_result}" itemValue="code" itemLabel="codeNm"/>
	                    		</form:select>
								<div><form:errors path="insttCode" cssClass="error"/></div>
							</div>
						</div> --%>
						<%-- <div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<!-- 조직아이디 -->
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.orgnztId"/></c:set>
								<label for="orgnztId">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:select path="orgnztId" id="orgnztId" title="${title} ${inputSelect}">
	                       			<form:option value="" label="${inputSelect}"/>
	                       			<form:options items="${orgnztId_result}" itemValue="code" itemLabel="codeNm"/>
	                    		</form:select>
								<div><form:errors path="orgnztId" cssClass="error"/></div>
							</div>
						</div> --%>
						<%-- <div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<!-- 직위 -->
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.ofcps"/></c:set>
								<label for="ofcpsNm">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="ofcpsNm" id="ofcpsNm" title="${title} ${inputTxt}" size="20" maxlength="50" />
								<div><form:errors path="ofcpsNm" cssClass="error"/></div>
							</div>
						</div> --%>
						<%-- <div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<!-- 사번 -->
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.emplNum"/></c:set>
								<label for="emplNo">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="emplNo" id="emplNo" title="${title} ${inputTxt}" size="20" maxlength="20" />
								<div><form:errors path="emplNo" cssClass="error"/></div>
							</div>
						</div> --%>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.saxTypeCode"/></c:set>
								<label for="sexdstnCode">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-4">
								<form:select path="sexdstnCode" id="sexdstnCode" title="${title} ${inputSelect}" class="form-control" required="true">
									<form:option value="" label="${inputSelect}"/>
									<form:options items="${sexdstnCode_result}" itemValue="code" itemLabel="codeNm"/>
								</form:select>
								<div><form:errors path="sexdstnCode" cssClass="error"/></div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.brth"/></c:set>
								<label for="brth">${title } </label>
							</div>
							<div class="col-sm-12 col-md-4">
								<fmt:parseDate var="brthPatternSet" pattern="yyyyMMdd" value="${userManageVO.brth }" />
								<fmt:formatDate var="brthPatternSet" pattern="yyyy-MM-dd" value="${brthPatternSet }" />
								<form:input path="brth" id="brth" class="form-control b-datepicker" title="${title} ${inputTxt}" size="20" maxlength="8" type="text" value="${brthPatternSet }" />
								<div><form:errors path="brth" cssClass="error"/></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.tel"/></c:set>
								<label for="areaNo">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-4">
								<div class="input-group">
									<form:input path="areaNo" id="areaNo" class="form-control" title="${title} ${inputSelect}" size="5" maxlength="5" required="true"/>
									<div><form:errors path="areaNo" cssClass="error" /></div>
									<em class="hyphen">-</em>
									<form:input path="homemiddleTelno" class="form-control" id="homemiddleTelno" size="5" maxlength="5" required="true"/>
									<div><form:errors path="homemiddleTelno" cssClass="error" /></div>
									<em class="hyphen">-</em>
									<form:input path="homeendTelno" class="form-control" id="homeendTelno"  size="5" maxlength="5" required="true"/>
									<div><form:errors path="homeendTelno" cssClass="error" /></div>	
								</div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.offmTelno"/></c:set>
								<label for="offmTelno">${title } </label>
							</div>
							<div class="col-sm-12 col-md-4">
								<form:input path="offmTelno" id="offmTelno" class="phoneNumber form-control" title="${title} ${inputTxt}" size="20" maxlength="15" />
								<div><form:errors path="offmTelno" cssClass="error"/></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.phone"/></c:set>
								<label for="moblphonNo">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-4">
								<form:input path="moblphonNo" id="moblphonNo" class="phoneNumber form-control" title="${title} ${inputTxt}" size="20" maxlength="15" required="true"/>
								<div><form:errors path="moblphonNo" cssClass="error"/></div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.fax"/></c:set>
								<label for="fxnum">${title } </label>
							</div>
							<div class="col-sm-12 col-md-4">
								<form:input path="fxnum" id="fxnum" class="phoneNumber form-control" title="${title} ${inputTxt}" size="20" maxlength="15" />
								<div><form:errors path="fxnum" cssClass="error"/></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.email"/></c:set>
								<label for="emailAdres">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="emailAdres" id="emailAdres" title="${title} ${inputTxt}" class="form-control" size="30" maxlength="50" required="true"/>
                  				<div><form:errors path="emailAdres" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.post"/></c:set>
								<label for="zip">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<div class="input-group">
									<form:input path="zip" id="zip" class="form-control" title="${title} ${inputTxt}" size="20" value="" maxlength="8" readonly="true" required="true"/>
									<div><form:errors path="zip" cssClass="error" /></div>	
									<div class="input-group-append">
										<a class="input-group-text" onclick="searchAddr('zip','homeadres','','');" title="<spring:message code="comUssUmt.deptUserManageRegist.post"/> <spring:message code="input.button" />"><spring:message code="comUssUmt.deptUserManageRegist.post"/></a>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.addr"/></c:set>
								<label for="homeadres">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-4">
								<form:input path="homeadres" id="homeadres" title="${title} ${inputTxt}" class="form-control" size="30" maxlength="50" readonly="true" required="true"/>
                  				<div><form:errors path="homeadres" cssClass="error" /></div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.addrDetail"/></c:set>
								<label for="detailAdres">${title } </label>
							</div>
							<div class="col-sm-12 col-md-4">
								<form:input path="detailAdres" id="detailAdres" title="${title} ${inputTxt}" class="form-control" size="30" maxlength="100" />
                  				<div><form:errors path="detailAdres" cssClass="error" /></div>
							</div>
						</div>
						<%-- <div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<!-- 그룹아이디 -->
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.groupId"/></c:set>
								<label for="groupId">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-4">
								<form:select path="groupId" id="groupId" title="${title} ${inputSelect}" class="form-control">
			                        <form:option value="" label="${inputSelect}"/>
			                        <form:options items="${groupId_result}" itemValue="code" itemLabel="codeNm"/>
			                    </form:select>
	                  			<div><form:errors path="groupId" cssClass="error" /></div>
							</div>
						</div> --%>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.author"/></c:set>
								<label for="authorCode">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:select path="authorCode" id="authorCode" title="${title} ${inputSelect}" class="form-control" required="true">
			                        <form:option value="" label="${inputSelect}"/>
			                        <form:options items="${author_result}" itemValue="authorCode" itemLabel="authorNm"/>
			                    </form:select>
	                  			<div><form:errors path="authorCode" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.status"/></c:set>
								<label for="emplyrSttusCode">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:select path="emplyrSttusCode" id="emplyrSttusCode" title="${title} ${inputSelect}" class="form-control" required="true">
			                        <form:option value="" label="${inputSelect}"/>
			                        <form:options items="${emplyrSttusCode_result}" itemValue="code" itemLabel="codeNm"/>
			                    </form:select>
	                  			<div><form:errors path="emplyrSttusCode" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.common.lockAt"/></c:set>
								<label>${title } </label>
							</div>
							<div class="col-sm-12 col-md-10">
								<c:if test="${userManageVO.lockAt eq 'Y'}">
									예
									<a class="btn btn-info pl-5" href="javascript:fnLockIncorrect();" title="<spring:message code="comUssUmt.common.lockAtBtn" />"><spring:message code="comUssUmt.common.lockAtBtn" /></a>
								</c:if>
								<c:if test="${userManageVO.lockAt == null || userManageVO.lockAt eq '' || userManageVO.lockAt eq 'N'}">
									아니오
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<div class="card-wrap-footer">
					<button type="button" class="btn btn-outline-secondary" onclick="fnListPage();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
						<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
					</button>
					<div class="float-right">
						<button type="button" class="btn btn-danger" onclick="fnDeleteUser('<c:out value='${userManageVO.userTy}'/>:<c:out value='${userManageVO.uniqId}'/>'); return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />">
							<spring:message code="button.delete" /><!-- 삭제 -->
						</button>
						<button type="submit" class="btn btn-warning" title="<spring:message code="button.update" /> <spring:message code="input.button" />">
							<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.update" />
						</button>
					</div>
				</div>
				<!-- 상세정보 사용자 삭제시 prameter 전달용 input -->
				<input name="checkedIdForDel" type="hidden" />
				<!-- 검색조건 유지 -->
				<input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
				<input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
				<input type="hidden" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>"/>
				<input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
				<!-- 사용자유형정보 : password 수정화면으로 이동시 타겟 유형정보 확인용, 만약검색조건으로 유형이 포함될경우 혼란을 피하기위해 userTy명칭을 쓰지 않음-->
				<input type="hidden" name="userTyForPassword" value="<c:out value='${userManageVO.userTy}'/>" />
				<!-- for validation -->
				<input type="hidden" name="password" id="password" value="Test#$123)"/>
				<input type="hidden" name="selectedId" id="selectedId" value=""/>  
				<form:hidden path="subDn" />
			</form:form>			
		</div>
	</div>
</div>

