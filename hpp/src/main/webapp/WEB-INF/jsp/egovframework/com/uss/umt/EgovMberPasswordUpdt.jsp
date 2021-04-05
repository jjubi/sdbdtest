<%
 /**
  * @Class Name : EgovMberPasswordUpdt.jsp
  * @Description : 일반회원암호수정 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.04.02    조재영          최초 생성
  *   2016.06.13    장동한          표준프레임워크 v3.6 개선
  *
  *  @author 공통서비스 개발팀 조재영
  *  @since 2009.04.02
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
<c:set var="pageTitle"><spring:message code="comUssUmt.userManagePasswordUpdt.title"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="passwordChgVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">

function fnListPage(){
    document.passwordChgVO.action = "<c:url value='/uss/umt/EgovMberManage.do'/>";
    document.passwordChgVO.submit();
}
function fnUpdate(form){
    if(validatePasswordChgVO(form)){
        if(form.newPassword.value != form.newPassword2.value){
        	swAlert('확인', '<spring:message code="fail.user.passwordUpdate2" />', 'warning');
            return false;
        }
        document.passwordChgVO.submit();
        return  true;
    }else{
    	return false;
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
			<h1 class="h1"><spring:message code="comUssUmt.userManage.title" /> ${pageTitle}</h1>
			<form name="passwordChgVO" method="post" action="<c:url value="/uss/umt/EgovMberPasswordUpdt.do"/>" onsubmit="fnUpdate(document.forms[0]); return false;">
				<!-- 상세정보 사용자 삭제시 prameter 전달용 input -->
				<input name="checkedIdForDel" type="hidden" />
				<!-- 검색조건 유지 -->
				<input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
				<input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
				<input type="hidden" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>"/>
				<input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
				<div class="card-wrap">
					<div class="form-horizontal contents-form">
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManagePasswordUpdt.id" /></c:set>
								<label for="mberId">${title } </label>
							</div>
							<div class="col-sm-12 col-md-10">
								<input type="text" name="mberId" id="mberId" value="<c:out value='${mberManageVO.mberId}'/>" class="form-control" title="사용자아이디" size="20" maxlength="20" readonly/>
								<input name="uniqId" id="uniqId" title="uniqId" type="hidden" size="20" value="<c:out value='${mberManageVO.uniqId}'/>"/>
								<input name="userTy" id="userTy" title="userTy" type="hidden" size="20" value="<c:out value='${mberManageVO.userTy}'/>"/>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManagePasswordUpdt.oldPass" /></c:set>
								<label for="oldPassword">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<input name="oldPassword" id="oldPassword" type="password" size="20" value="" class="form-control" maxlength="100" >
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManagePasswordUpdt.pass" /></c:set>
								<label for="newPassword">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<input name="newPassword" id="newPassword" type="password" size="20" value="" class="form-control" maxlength="100" >
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManagePasswordUpdt.passConfirm" /></c:set>
								<label for="newPassword2">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<input name="newPassword2" id="newPassword2" type="password" size="20" value="" class="form-control" maxlength="100" >
							</div>
						</div>
					</div>
				</div>
				<div class="card-wrap-footer">
					<button type="button" class="btn btn-outline-secondary" onclick="fnListPage();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
						<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
					</button>
					<div class="float-right">
						<button type="submit" class="btn btn-warning" title="<spring:message code="button.update" /> <spring:message code="input.button" />">
							<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.update" />
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

