<%
 /**
  * @Class Name : EgovMberInsert.jsp
  * @Description : 일반회원등록 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.02    조재영          최초 생성
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
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUssUmt.userManage.title"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mberManageVO" staticJavascript="false" xhtml="true" cdata="false"/>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javaScript" language="javascript" defer="defer">
$(document).ready(function(){
	$('#mberId').keyup(function(event){
		$('#checkIdBoolean').val('false');
	});
	
	$('#password2').keyup(function(event){
		if($(this).val() == $('#password').val()){
			$('#checkPwdStr').html('<font color="blue">비밀번호 일치</font>');
			$('#checkPwdBoolean').val('true');
		} else {
			$('#checkPwdStr').html('<font color="red">비밀번호 불일치</font>');
			$('#checkPwdBoolean').val('false');
		}
	});
});
/*********************************************************
 * 아이디 체크 AJAX
 ******************************************************** */
function fn_id_check(){	
	$.ajax({
		type:"POST",
		url:"<c:url value='/uss/umt/EgovIdDplctCnfirmAjax.do' />",
		data:{
			"checkId": $("#mberId").val()			
		},
		dataType:'json',
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				if(returnData.usedCnt > 0 ){
					//사용할수 없는 아이디입니다.
					$('#checkIdStr').html('<font color="red"><spring:message code="comUssUmt.userManageRegistModal.result" /> : ['+returnData.checkId+']<spring:message code="comUssUmt.userManageRegistModal.useMsg" /></font>');
					$('#checkIdBoolean').val('false');
				}else{
					//사용가능한 아이디입니다.
					$("#checkIdStr").html("<font color='blue'><spring:message code="comUssUmt.userManageRegistModal.result" /> : ["+returnData.checkId+"]<spring:message code="comUssUmt.userManageRegistModal.notUseMsg" /></font>");
					$('#checkIdBoolean').val('true');
				}
			}else{ 
// 				alert("ERROR!");
				swAlert("ERROR!");
				return;
			} 
		}
	});
}

/*********************************************************
 * 아이디 체크 확인
 ******************************************************** */
// function fn_id_checkOk(){
// 	$.ajax({
// 		type:"POST",
// 		url:"<c:url value='/uss/umt/EgovIdDplctCnfirmAjax.do' />",
// 		data:{
// 			"checkId": $("#checkIdModal").val()			
// 		},
// 		dataType:'json',
// 		timeout:(1000*30),
// 		success:function(returnData, status){
// 			if(status == "success") {
// 				if(returnData.usedCnt > 0 ){
// 					alert("<spring:message code="comUssUmt.userManageRegistModal.noIdMsg" />"); //사용이 불가능한 아이디 입니다.
// 					return;
// 				}else{
					
// 					$("input[name=mberId]").val(returnData.checkId);
// 					$("#egovModal").setEgovModalClose();
// 				}
// 			}else{ alert("ERROR!");return;} 
// 		}
// 		});
// }


// function fnIdCheck1(){
//     var retVal;
//     var url = "<c:url value='/uss/umt/EgovIdDplctCnfirmView.do'/>";
//     var varParam = new Object();
//     varParam.checkId = document.mberManageVO.mberId.value;
//     var openParam = "dialogWidth:303px;dialogHeight:250px;scroll:no;status:no;center:yes;resizable:yes;";
        
//     alert(1);
//     return false;
//     retVal = window.showModalDialog(url, varParam, openParam);
//     if(retVal) {
//     	document.mberManageVO.mberId.value = retVal;
//     }
// }

// function showModalDialogCallback(retVal) {
// 	if(retVal) {
// 	    document.mberManageVO.mberId.value = retVal;
// 	}
// }

function fnListPage(){
    document.mberManageVO.action = "<c:url value='/uss/umt/EgovMberManage.do'/>";
    document.mberManageVO.submit();
}

function fnInsert(form){
	swAlertExtand({title:'등록', text : '<spring:message code="common.regist.msg" />', showCancelButton : true}, function(){
		if(validateMberManageVO(form)){
			if(form.password.value != form.password2.value){
// 	            alert("<spring:message code="fail.user.passwordUpdate2" />");
	            swAlert("<spring:message code="fail.user.passwordUpdate2" />");
	            return false;
	        }
			if(!$('#mberEmailAdres').isEmail()){
	    		Swal.fire('확인', '이메일 형식을 맞게 작성해주세요.', 'warning');
	    		return false;
	    	}
			if($('#checkIdBoolean').val() == 'false'){
	    		Swal.fire('확인', '아이디 중복확인을 해주세요.', 'warning');
	    		return false;
	    	}
			form.submit();
			return true;
	    }
	});
	
// 	if(confirm("<spring:message code="common.regist.msg" />")){	
// 		if(validateMberManageVO(form)){
// 			if(form.password.value != form.password2.value){
// // 	            alert("<spring:message code="fail.user.passwordUpdate2" />");
// 	            swAlert("<spring:message code="fail.user.passwordUpdate2" />");
// 	            return false;
// 	        }
// 			if(!$('#mberEmailAdres').isEmail()){
// 	    		Swal.fire('확인', '이메일 형식을 맞게 작성해주세요.', 'warning');
// 	    		return false;
// 	    	}
// 			if($('#checkIdBoolean').val() == 'false'){
// 	    		Swal.fire('확인', '아이디 중복확인을 해주세요.', 'warning');
// 	    		return false;
// 	    	}
// 			form.submit();
// 			return true;
// 	    }
// 	}

}
</script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.create" /></h1>
			<form:form commandName="mberManageVO" action="${pageContext.request.contextPath}/uss/umt/EgovMberInsert.do" name="mberManageVO"  method="post" onSubmit="fnInsert(document.forms[0]); return false;">
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
									<input type="hidden" id="checkIdBoolean" name="checkIdBoolean" value="false">
									<form:input path="mberId" class="form-control" title="${title} ${inputTxt}" size="40" maxlength="20" required="true"/>
									<div><form:errors path="mberId" cssClass="error" /></div>
									<div class="input-group-append">
										<a class="input-group-text text-reset" style="cursor: pointer;" onclick="fn_id_check();" title="<spring:message code="comUssUmt.userManageRegistBtn.idCheck" /> <spring:message code="input.button" />"><spring:message code="comUssUmt.userManageRegistBtn.idCheck" /></a>
									</div>
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
							<div class="col-sm-12 col-md-4">
								<form:password path="password" class="form-control" title="${title} ${inputTxt}" size="40" maxlength="20" required="true"/>
								<div><form:errors path="password" cssClass="error" /></div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="comUssUmt.userManageRegist.passConfirm"/></c:set>
								<label for="password2">${title } <span class="pilsu">*</span> <span id="checkPwdStr"></span></label>
							</div>
							<div class="col-sm-12 col-md-4">
								<input type="password" id="password2" name="password2" class="form-control" title="${title} ${inputTxt}" size="40" maxlength="20" />
								<input type="hidden" id="checkPwdBoolean" name="checkPwdBoolean" value="false">
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
					</div>	
				</div>
				<div class="card-wrap-footer">
					<a class="btn btn-outline-secondary" href="<c:url value='/uss/umt/EgovMberManage.do'/>" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
						<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
					</a>
					<div class="float-right">
						<button type="submit" class="btn btn-basic" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
							<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
						</button>
					</div>
				</div>
				<input name="checkedIdForDel" type="hidden" />
				<!-- 검색조건 유지 -->
				<input type="hidden" name="searchCondition" value="<c:out value='${empty userSearchVO ? mberManageVO.searchCondition : userSearchVO.searchCondition}'/>"/>
				<input type="hidden" name="searchKeyword" value="<c:out value='${empty userSearchVO ? mberManageVO.searchKeyword : userSearchVO.searchKeyword}'/>"/>
				<input type="hidden" name="sbscrbSttus" value="<c:out value='${empty userSearchVO ? mberManageVO.sbscrbSttus : userSearchVO.sbscrbSttus}'/>"/>
				<input type="hidden" name="pageIndex" value="<c:out value='${empty userSearchVO ? mberManageVO.pageIndex : userSearchVO.pageIndex}'/>"/>
			</form:form>			
		</div>
	</div>
</div>

