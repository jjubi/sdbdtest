<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : bannerModify.jsp
  * @상세설명 : 배너 수정 페이지
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
<c:set var="pageTitle"><spring:message code="vaivBnr.title"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="bannerVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/banner/banner.js"></script>

<script>
$(document).ready(function(){
	/*
	 * 배너 기간의 날짜가 없을 경우 default 오늘 날짜
	 */
	if(eval('${empty bannerVO.bannerBgnde && empty bannerVO.bannerEndde}')){
		$('.b-datepicker').val(moment().format('YYYY-MM-DD'));
	}
})
</script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.update" /></h1>
			<form:form commandName="bannerVO" action="${pageContext.request.contextPath}/cmmn/adm/banner/updateBanner.do" method="post"  enctype="multipart/form-data" onsubmit="return false;">
				<form:hidden path="bannerSeqNo" />
				<form:hidden path="atchFileId" />
				<div class="card-wrap">
					<div class="form-horizontal contents-form">
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBnr.regist.bannerNm" /></c:set>
								<label for="bannerNm">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="bannerNm" class="form-control" title="${title} ${inputTxt}" size="40" maxlength="50" />
								<div><form:errors path="bannerNm" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBnr.regist.bannerDc" /></c:set>
								<label for="bannerDc">${title } </label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:textarea path="bannerDc" class="form-control" title="${title} ${inputTxt}" cols="20" rows="5" />
								<div><form:errors path="bannerDc" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBnr.regist.bannerUrl" /></c:set>
								<label for="bannerUrl">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="bannerUrl" class="form-control" title="${title} ${inputTxt}" size="40" maxlength="400" />
								<div><form:errors path="bannerUrl" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBnr.regist.bannerTy" /> <span class="pilsu">*</span></c:set>
								<label for="bannerTy">${title } </label>
							</div>
							<div class="col-sm-12 col-md-4">
								<form:select path="bannerTy" class="form-control">
									<form:options items="${bannerTyList }" itemValue="code" itemLabel="codeNm"/>
								</form:select>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBnr.regist.bannerOrdr" /></c:set>
								<label for="bannerOrdr">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-4">
								<form:input path="bannerOrdr" class="form-control onlyNum" title="${title} ${inputTxt}" size="40" maxlength="10" />
								<div><form:errors path="bannerOrdr" cssClass="error" /></div>
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
										<c:if test="${not empty bannerVO.atchFileId }">
											<img id="preViewImageArea" src="${pageContext.request.contextPath }/cmm/fms/getImage.do?atchFileId=${bannerVO.atchFileId}&amp;fileSn=0" height="50">
										</c:if>
										<c:if test="${empty bannerVO.atchFileId }">
											<img id="preViewImageArea" src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/common/no-image.png" height="50">
										</c:if>
	       							</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBnr.regist.bannerPd" /></c:set>
								<label for="bannerBgnde">${title } </label>
							</div>
							<div class="col-sm-12 col-md-10">
								<div class="input-group">
									<form:input path="bannerBgnde" type="text" class="form-control b-datepicker" title="${title} 시작일 ${inputTxt}" size="40" maxlength="10" />
									<div class="input-group-append">
										<span class="input-group-text"> ~ </span>
									</div>
									<form:input path="bannerEndde" type="text" class="form-control b-datepicker" title="${title} 종료일 ${inputTxt}" size="40" maxlength="10" />
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
                                	<form:radiobutton path="useAt" id="useAtY" value="Y" checked="checked" /> 출력
                                </label>
                                <label class="radio-inline" for="useAtN">
                                	<form:radiobutton path="useAt" id="useAtN" value="N" /> 출력안함
                                </label>
                            </div>
                        </div>
					</div>
				</div>
				<div class="card-wrap-footer">
					<button type="button" class="btn btn-outline-secondary" onclick="fnSelectBannerList();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
						<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
					</button>
					<div class="float-right">
						<button type="submit" id="bannerUpdateBtn" class="btn btn-warning" title="<spring:message code="button.update" /> <spring:message code="input.button" />">
							<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.update" />
						</button>
					</div>
				</div>
			</form:form>			
		</div>
	</div>
</div>

<form id="hiddenSearchForm" class="d-none" method="post">
	<input type="hidden" name="bannerTy" value="${searchVO.bannerTy }">
	<input type="hidden" name="searchKeyword" value="${searchVO.searchKeyword }">
	<input type="hidden" name="searchCondition" value="${searchVO.searchCondition }">
	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }">
</form>
