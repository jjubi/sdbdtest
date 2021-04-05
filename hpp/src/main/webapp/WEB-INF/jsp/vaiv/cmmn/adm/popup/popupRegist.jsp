<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : popupRegist.jsp
  * @상세설명 : 팝업 등록 페이지
  * @작성일시 : 2020. 12. 31
  * @작 성 자 : jo
  * @수 정 이 력
  * @
  * @  수정일         수정자                   수정내용
  * @ -------       --------    ---------------------------
  * @ 2020.12.31   jo	  최초등록
  * @ 
  * 
  */
%>
<c:set var="pageTitle"><spring:message code="vaivPopup.title"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="popupVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/popup/popup.js"></script>

<script>
$(document).ready(function(){
	/*
	 * 팝업 기간의 날짜가 없을 경우 default 오늘 날짜
	 */
	if(eval('${empty popupVO.popupBgnde && empty popupVO.popupEndde}')){
		$('.b-datepicker').val(moment().format('YYYY-MM-DD'));
	}
})
</script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.create" /></h1>
			<form:form commandName="popupVO" action="${pageContext.request.contextPath}/cmmn/adm/popup/insertPopup.do" method="post"  enctype="multipart/form-data" onsubmit="return false;">
				<div class="card-wrap">
					<div class="form-horizontal contents-form">
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivPopup.regist.popupNm" /></c:set>
								<label for="popupNm">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="popupNm" class="form-control" title="${title} ${inputTxt}" size="40" maxlength="50" />
								<div><form:errors path="popupNm" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivPopup.regist.popupDc" /></c:set>
								<label for="popupDc">${title } </label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:textarea path="popupDc" class="form-control" title="${title} ${inputTxt}" cols="30" rows="5" />
								<div><form:errors path="popupDc" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivPopup.regist.popupUrl" /></c:set>
								<label for="popupUrl">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="popupUrl" class="form-control" title="${title} ${inputTxt}" size="40" maxlength="400" />
								<div><form:errors path="popupUrl" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivPopup.regist.popupTy" /></c:set>
								<label for="popupTy">${title } </label>
							</div>
							<div class="col-sm-12 col-md-4">
								<form:select path="popupTy" class="form-control">
									<form:options items="${popupTyList }" itemValue="code" itemLabel="codeNm"/>
								</form:select>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2 popupLcArea">
								<c:set var="title"><spring:message code="vaivPopup.regist.popupLc" /></c:set>
								<label for="popupTop">${title } </label>
							</div>
							<div class="col-sm-12 col-md-4 popupLcArea">
								<div class="input-group">
									<div class="input-group-append">
										<span class="input-group-text"> TOP </span>
									</div>
									<form:input path="popupTopLc" class="form-control onlyNum" title="${title} Top ${inputTxt}" size="40" maxlength="10" />
									<div class="input-group-append">
										<span class="input-group-text"> LEFT </span>
									</div>
									<form:input path="popupLeftLc" class="form-control onlyNum" title="${title} Left ${inputTxt}" size="40" maxlength="10" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivPopup.regist.popupOrdr" /></c:set>
								<label for="popupOrdr">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-4">
								<form:input path="popupOrdr" class="form-control onlyNum" title="${title} ${inputTxt}" size="40" maxlength="10" />
								<div><form:errors path="popupOrdr" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<!-- 첨부파일 -->
	    						<label for="file_0">첨부파일 <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<div class="row">
									<div class="col-sm-12 col-md-6">
										<div class="form-imgbox_file">
			                                <div class="custom-file">
			                                    <label class="custom-file-label" for="file_0">파일 찾아보기</label>
			                                    <input type="file" class="custom-file-input" id="file_0" name="file_0" readonly="readonly">
			                                </div>
			                            </div>
		                            </div>
	       							<div class="col-sm-12 col-md-6">
										<img id="preViewImageArea" src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/no-image.png" height="38">
	       							</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivPopup.regist.popupPd" /></c:set>
								<label for="popupBgnde">${title } </label>
							</div>
							<div class="col-sm-12 col-md-10">
								<div class="input-group">
									<form:input path="popupBgnde" type="text" class="form-control b-datepicker" title="${title} 시작일 ${inputTxt}" size="40" maxlength="10" />
									<div class="input-group-append">
										<span class="input-group-text"> ~ </span>
									</div>
									<form:input path="popupEndde" type="text" class="form-control b-datepicker" title="${title} 종료일 ${inputTxt}" size="40" maxlength="10" />
								</div>
							</div>
						</div>
						<div class="form-group">
                            <div class="col-sm-12 col-md-2 control-label col-lg-2">
                            	<c:set var="title"><spring:message code="vaivBnr.regist.useAt" /></c:set>
								<label for="useAtY">${title } <span class="pilsu">*</span></label>
                            </div>
                            <div class="col-sm-12 col-md-10">
                                <label class="radio-inline" for="useAtY">
                                	<form:radiobutton path="useAt" id="useAtY" value="Y" checked="checked" /> 사용
                                </label>
                                <label class="radio-inline" for="useAtN">
                                	<form:radiobutton path="useAt" id="useAtN" value="N" /> 사용안함
                                </label>
                            </div>
                        </div>	
					</div>
				</div>
				<div class="card-wrap-footer">
					<button type="button" class="btn btn-outline-secondary" onclick="fnSelectPopupList();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
						<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
					</button>
					<div class="float-right">
						<button type="submit" id="popupRegistBtn" class="btn btn-basic" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
							<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
						</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>

<form id="hiddenSearchForm" class="d-none" method="post">
	<input type="hidden" name="popupTy" value="${searchVO.popupTy }">
	<input type="hidden" name="searchKeyword" value="${searchVO.searchKeyword }">
	<input type="hidden" name="searchCondition" value="${searchVO.searchCondition }">
	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }">
</form>