<%
 /**
  * @Class Name : EgovMberSelectUpdt.jsp
  * @Description : 일반회원상세조회, 수정 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.02    조재영          최초 생성
  * @ 2015.06.16	조정국		  password 중복필드 정리
  * @ 2016.06.13    장동한          표준프레임워크 v3.6 개선
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
<c:set var="pageTitle"><spring:message code="comUssUmt.userManage.title"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mberManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">
function fnListPage(){
    document.mberManageVO.action = "<c:url value='/uss/umt/EgovMberManage.do'/>";
    document.mberManageVO.submit();
}
function fnDeleteMber(checkedIds) {
	swAlertConfirm('<spring:message code="common.delete.msg" />',{icon:'warning',confirmButtonText:'삭제'}, function(){
	    document.mberManageVO.checkedIdForDel.value=checkedIds;
	    document.mberManageVO.action = "<c:url value='/uss/umt/EgovMberDelete.do'/>";
	    document.mberManageVO.submit();
	});
	return false;
}
function fnPasswordMove(){
    document.mberManageVO.action = "<c:url value='/uss/umt/EgovMberPasswordUpdtView.do'/>";
    document.mberManageVO.submit();
}

function fnLockIncorrect(){
	swAlertConfirm('<spring:message code="comUssUmt.common.lockAtConfirm" />',{icon:'warning',confirmButtonText:'삭제'}, function(){
		document.mberManageVO.action = "<c:url value='/uss/umt/EgovMberLockIncorrect.do'/>";
	    document.mberManageVO.selectedId.value=document.mberManageVO.uniqId.value;
	    document.mberManageVO.submit();
	});
	return false;
}

function fnUpdate(form){
	swAlertConfirm('<spring:message code="common.update.msg" />',{icon:'info',confirmButtonText:'수정'}, function(){
		if(validateMberManageVO(form)){
			document.mberManageVO.submit();
			return true;
	    }else{
	    	return false;
	    }
	});
}
</script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.update" /></h1>
			<form:form commandName="mberManageVO" action="${pageContext.request.contextPath}/uss/umt/EgovMberSelectUpdt.do" name="mberManageVO"  method="post" onSubmit="fnUpdate(document.forms[0]); return false;">
				<div class="card-wrap">
					<div class="form-horizontal contents-form">
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						<c:set var="inputSelect"><spring:message code="input.select"/></c:set>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.id"/></c:set>
								<label for="mberId">${title } <span class="pilsu">*</span> 
									<span id="checkIdStr" class="d-block"></span>
								</label>	
							</div>
							<div class="col-sm-12 col-md-10">
								<div class="input-group">
									<form:input path="mberId" id="mberId" class="form-control" title="${title} 입력" size="40" maxlength="30" readonly="true" required="true"/>
									<div><form:errors path="mberId" cssClass="error" /></div>
									<form:hidden path="uniqId" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.name"/></c:set>
								<label for="mberNm">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="mberNm" class="form-control" title="${title} ${inputTxt}" size="40" maxlength="50" required="true"/>
								<div><form:errors path="mberNm" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.pass"/></c:set>
								<label for="password">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<a class="btn btn-danger" href="javascript:fnPasswordMove();">비밀번호 변경</a>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.passHit"/></c:set>
								<label for="passwordHint">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-4">
								<form:select path="passwordHint" id="passwordHint" class="form-control" title="${title} ${inputSelect}" required="true">
									<form:option value="" label="${inputSelect}"/>
									<form:options items="${passwordHint_result}" itemValue="code" itemLabel="codeNm"/>
								</form:select>
								<div><form:errors path="passwordHint" cssClass="error"/></div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.passOk"/></c:set>
								<label for="passwordCnsr">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-4">
								<form:input path="passwordCnsr" id="passwordCnsr" title="${title} ${inputTxt}" class="form-control" size="50" maxlength="100" required="true"/>
								<div><form:errors path="passwordCnsr" cssClass="error"/></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.saxTypeCode"/></c:set>
								<label for="sexdstnCode">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:select path="sexdstnCode" id="sexdstnCode" title="${title} ${inputSelect}" class="form-control" required="true">
									<form:option value="" label="${inputSelect}"/>
									<form:options items="${sexdstnCode_result}" itemValue="code" itemLabel="codeNm"/>
								</form:select>
								<div><form:errors path="sexdstnCode" cssClass="error"/></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.phone"/></c:set>
								<label for="moblphonNo">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="moblphonNo" id="moblphonNo" class="phoneNumber form-control" title="${title} ${inputTxt}" size="20" maxlength="15" required="true"/>
								<div><form:errors path="moblphonNo" cssClass="error"/></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.tel"/></c:set>
								<label for="areaNo">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-4">
								<div class="input-group">
									<form:input path="areaNo" id="areaNo" class="form-control" title="${title} ${inputSelect}" size="5" maxlength="4" required="true"/>
									<div><form:errors path="areaNo" cssClass="error" /></div>
									<em class="hyphen">-</em>
									<form:input path="middleTelno" class="form-control" id="middleTelno" size="5" maxlength="4" required="true"/>
									<div><form:errors path="middleTelno" cssClass="error" /></div>
									<em class="hyphen">-</em>
									<form:input path="endTelno" class="form-control" id="endTelno"  size="5" maxlength="4" required="true"/>
									<div><form:errors path="endTelno" cssClass="error" /></div>	
								</div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.fax"/></c:set>
								<label for="mberFxnum">${title } </label>
							</div>
							<div class="col-sm-12 col-md-4">
								<form:input path="mberFxnum" id="mberFxnum" class="phoneNumber form-control" title="${title} ${inputTxt}" size="20" maxlength="15" />
								<div><form:errors path="mberFxnum" cssClass="error"/></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.email"/></c:set>
								<label for="mberEmailAdres">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="mberEmailAdres" id="mberEmailAdres" title="${title} ${inputTxt}" class="form-control" size="30" maxlength="50" required="true"/>
                  				<div><form:errors path="mberEmailAdres" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.post"/></c:set>
								<label for="zip">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<div class="input-group">
									<form:input path="zip" id="zip" class="form-control" title="${title} ${inputTxt}" size="20" value="" maxlength="8" readonly="true" required="true"/>
									<div><form:errors path="zip" cssClass="error" /></div>	
									<div class="input-group-append">
										<a class="input-group-text" onclick="searchAddr('zip','adres','','');" title="<spring:message code="comUssUmt.deptUserManageRegist.post"/> <spring:message code="input.button" />"><spring:message code="comUssUmt.deptUserManageRegist.post"/></a>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.addr"/></c:set>
								<label for="adres">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-4">
								<form:input path="adres" id="adres" title="${title} ${inputTxt}" class="form-control" size="30" maxlength="100" readonly="true" required="true"/>
                  				<div><form:errors path="adres" cssClass="error" /></div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.addrDetail"/></c:set>
								<label for="detailAdres">${title } </label>
							</div>
							<div class="col-sm-12 col-md-4">
								<form:input path="detailAdres" id="detailAdres" title="${title} ${inputTxt}" class="form-control" size="30" maxlength="100" />
                  				<div><form:errors path="detailAdres" cssClass="error" /></div>
							</div>
						</div>
						<%-- <div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.groupId"/></c:set>
								<label for="groupId">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:select path="groupId" id="groupId" title="${title} ${inputSelect}" class="form-control">
			                        <form:option value="" label="${inputSelect}"/>
			                        <form:options items="${groupId_result}" itemValue="code" itemLabel="codeNm"/>
			                    </form:select>
	                  			<div><form:errors path="groupId" cssClass="error" /></div>
							</div>
						</div> --%>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.status"/></c:set>
								<label for="mberSttus">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:select path="mberSttus" id="mberSttus" title="${title} ${inputSelect}" class="form-control" required="true">
			                        <form:option value="" label="${inputSelect}"/>
			                        <form:options items="${mberSttus_result}" itemValue="code" itemLabel="codeNm"/>
			                    </form:select>
	                  			<div><form:errors path="mberSttus" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<!-- 로그인인증제한여부 -->
								<c:set var="title"><spring:message code="comUssUmt.common.lockAt"/></c:set>
								<label>${title } </label>
							</div>
							<div class="col-sm-12 col-md-10">
								<c:if test="${mberManageVO.lockAt eq 'Y'}">
									예
									<a class="btn btn-info pl-5" href="javascript:fnLockIncorrect();" title="<spring:message code="comUssUmt.common.lockAtBtn" />"><spring:message code="comUssUmt.common.lockAtBtn" /></a>
								</c:if>
								<c:if test="${mberManageVO.lockAt == null || mberManageVO.lockAt eq '' || mberManageVO.lockAt eq 'N'}">
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
						<button type="button" class="btn btn-danger" onclick="fnDeleteMber('<c:out value='${mberManageVO.userTy}'/>:<c:out value='${mberManageVO.uniqId}'/>'); return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />">
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
				<input type="hidden" name="userTyForPassword" value="<c:out value='${mberManageVO.userTy}'/>" />
				<!-- for validation -->
				<input type="hidden" name="password" id="password" value="Test#$123)"/>
				<input type="hidden" name="selectedId" id="selectedId" value=""/>  
			</form:form>			
		</div>
	</div>
</div>

