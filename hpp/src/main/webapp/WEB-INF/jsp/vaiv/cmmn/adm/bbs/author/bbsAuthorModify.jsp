<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : bbsAuthorModify.jsp
  * @상세설명 : 게시판 권한 수정 페이지
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
<c:set var="pageTitle"><spring:message code="vaivBbsAuthor.title"/></c:set>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/bbs/bbs.js"></script>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/bbs/author/bbsAuthor.js"></script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.update" />
				<small>( <c:out value="${bbsVO.bbsNm }"/> )</small>
			</h1>
			<form:form commandName="bbsAuthorVO" method="post" action="${pageContext.request.contextPath }/cmmn/adm/bbs/author/updateBbsAuthor.do" onsubmit="return false;">
				<form:hidden path="bbsId"/>
				<div class="card-wrap">
					<div class="form-horizontal contents-form">
						<c:set var="inputTxt"><spring:message code="input.select" /></c:set>
						<div class="form-group">
							<div class="col-sm-12 col-md-12 control-label">
								<label for="nttRedngAuthor">게시글 권한 </label>
							</div>
						</div>
						<div class="form-group">	
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.nttRedngAuthor" /></c:set>
								<label for="nttRedngAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="nttRedngAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="nttRedngAuthor" cssClass="error" /></div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.nttRegistAuthor" /></c:set>
								<label for="nttRegistAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="nttRegistAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="nttRegistAuthor" cssClass="error" /></div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.nttUpdtAuthor" /></c:set>
								<label for="nttUpdtAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="nttUpdtAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="nttUpdtAuthor" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.nttDeleteAuthor" /></c:set>
								<label for="nttDeleteAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="nttDeleteAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="nttDeleteAuthor" cssClass="error" /></div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.nttFileUploadAuthor" /></c:set>
								<label for="nttFileUploadAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="nttFileUploadAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="nttFileUploadAuthor" cssClass="error" /></div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.nttFileDwldAuthor" /></c:set>
								<label for="nttFileDwldAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="nttFileDwldAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="nttFileDwldAuthor" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-12 control-label col-lg-12">
								<label for="cmtRedngAuthor">댓글 권한</label>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.cmtRedngAuthor" /></c:set>
								<label for="cmtRedngAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="cmtRedngAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="cmtRedngAuthor" cssClass="error" /></div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.cmtRegistAuthor" /></c:set>
								<label for="cmtRegistAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="cmtRegistAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="cmtRegistAuthor" cssClass="error" /></div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.cmtUpdtAuthor" /></c:set>
								<label for="cmtUpdtAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="cmtUpdtAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="cmtUpdtAuthor" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.cmtDeleteAuthor" /></c:set>
								<label for="cmtDeleteAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="cmtDeleteAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="cmtDeleteAuthor" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-12 control-label col-lg-12">
								<label for="answerRedngAuthor">답글 권한</label>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.answerRedngAuthor" /></c:set>
								<label for="answerRedngAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="answerRedngAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="answerRedngAuthor" cssClass="error" /></div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.answerRegistAuthor" /></c:set>
								<label for="answerRegistAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="answerRegistAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="answerRegistAuthor" cssClass="error" /></div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.answerUpdtAuthor" /></c:set>
								<label for="answerUpdtAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="answerUpdtAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="answerUpdtAuthor" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.answerDeleteAuthor" /></c:set>
								<label for="answerDeleteAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="answerDeleteAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="answerDeleteAuthor" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-12 control-label col-lg-12">
								<label for="secretUseAuthor">기타 권한 </label>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<!-- 비밀글 사용 권한  -->
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.secretUseAuthor" /></c:set>
								<label for="secretUseAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="secretUseAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="secretUseAuthor" cssClass="error" /></div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<!-- 공지글 사용 권한  -->
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.noticeUseAuthor" /></c:set>
								<label for="noticeUseAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="noticeUseAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="noticeUseAuthor" cssClass="error" /></div>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<!-- 위치 표시 사용 권한  -->
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.lcIndictUseAuthor" /></c:set>
								<label for="lcIndictUseAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="lcIndictUseAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="lcIndictUseAuthor" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsAuthor.regist.popupUseAuthor" /></c:set>
								<label for="popupUseAuthor">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<form:select path="popupUseAuthor" class="form-control" title="${title} ${inputTxt}">
									<form:options items="${authorList}" itemValue="authorCode" itemLabel="authorDc" />
								</form:select>
								<div><form:errors path="popupUseAuthor" cssClass="error" /></div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-wrap-footer">
					<button type="button" class="btn btn-outline-secondary" onclick="fnSelectBbsList();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
						<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
					</button>
					<div class="float-right">
						<button type="submit" id="bbsAuthorUpdateBtn" class="btn btn-warning" title="<spring:message code="button.update" /> <spring:message code="input.button" />">
							<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.update" />
						</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>

<form id="hiddenSearchForm" class="d-none" method="post">
	<input type="hidden" name="bbsId" value="${searchVO.bbsId }">
	<input type="hidden" name="searchKeyword" value="${searchVO.searchKeyword }">
	<input type="hidden" name="searchCondition" value="${searchVO.searchCondition }">
	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }">
</form>
    