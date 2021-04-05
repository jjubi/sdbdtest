<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : comCodeGroupModify.jsp
  * @상세설명 : 공통코드 그룹코드 수정 페이지
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

<c:set var="pageTitle"><spring:message code="vaivCodeGrp.title"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="comCodeGroupVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/comCode/group/comCodeGroup.js"></script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.update" /></h1>
			<form:form commandName="comCodeGroupVO" action="${pageContext.request.contextPath}/cmmn/adm/comCode/group/updateComCodeGroup.do" method="post" onsubmit="return false;">
				<div class="card-wrap">
					<div class="form-horizontal contents-form">
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivCodeGrp.regist.groupCode" /></c:set>
								<label for="groupCode">${title } <span class="pilsu">*</span> 
									<span id="checkCodeStr" class="d-block"></span>
								</label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:hidden path="groupCode" />
								<c:out value="${comCodeGroupVO.groupCode }"/>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivCodeGrp.regist.groupNm" /></c:set>
								<label for="groupNm">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="groupNm" class="form-control" title="${title} ${inputTxt}" size="40" required="true" maxlength="100" />
								<div><form:errors path="groupNm" cssClass="error" /></div>
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
					<button type="button" class="btn btn-outline-secondary" onclick="fnSelectComCodeGroupList();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
						<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
					</button>
					<div class="float-right">
						<button type="button" id="comCodeGroupDeleteBtn" class="btn btn-danger" title="<spring:message code="button.delete" /> <spring:message code="input.button" />">
							<spring:message code="button.delete" /><!-- 삭제 -->
						</button>
						<button type="submit" id="comCodeGroupUpdateBtn" class="btn btn-warning" title="<spring:message code="button.update" /> <spring:message code="input.button" />">
							<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.update" />
						</button>
					</div>
				</div>
			</form:form>			
		</div>
	</div>
</div>

<form id="hiddenSearchForm" class="d-none" method="post">
	<input type="hidden" name="searchKeyword" value="${searchVO.searchKeyword }">
	<input type="hidden" name="searchCondition" value="${searchVO.searchCondition }">
	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }">
</form>
