<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : bbsOptnModify.jsp
  * @상세설명 : 게시판 옵션 수정 페이지
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
<c:set var="pageTitle"><spring:message code="vaivBbsOptn.title"/></c:set>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/bbs/bbs.js"></script>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/bbs/optn/bbsOptn.js"></script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.update" />
				<small>( <c:out value="${bbsVO.bbsNm }"/> )</small>
			</h1>
			<form:form commandName="bbsOptnVO" method="post" action="${pageContext.request.contextPath }/cmmn/adm/bbs/optn/updateBbsOptn.do" onsubmit="return false;">
				<form:hidden path="bbsId"/>
				<div class="card-wrap">
					<div class="form-horizontal contents-form">
						<c:set var="inputTxt"><spring:message code="input.select" /></c:set>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label">
								<c:set var="title"><spring:message code="vaivBbsOptn.regist.atchFileUseAt" /></c:set>
								<label for="atchFileUseAt">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<label class="radio-inline" for="atchFileUseAtY">
                                	<form:radiobutton path="atchFileUseAt" id="atchFileUseAtY" value="Y" /> 사용
                                </label>
                                <label class="radio-inline" for="atchFileUseAtN">
                                	<form:radiobutton path="atchFileUseAt" id="atchFileUseAtN" value="N" /> 사용안함
                                </label>
							</div>
							<div class="col-sm-12 col-md-2 control-label atchFilePermExtsnArea" style="display: ${bbsOptnVO.atchFileUseAt eq 'Y' ? 'block' : 'none'};">
		                  		<!-- 첨부파일 허용 확장자 -->
								<c:set var="title"><spring:message code="vaivBbsOptn.regist.atchFilePermExtsn" /></c:set>
								<label for="atchFilePermExtsn">${title } </label>
							</div>
							<div class="col-sm-12 col-md-2 atchFilePermExtsnArea" style="display: ${bbsOptnVO.atchFileUseAt eq 'Y' ? 'block' : 'none'};">
								<form:input path="atchFilePermExtsn" class="form-control" title="${title} 입력" size="40" maxlength="50" />
								<div><form:errors path="atchFilePermExtsn" cssClass="error" /></div>
		                  	</div>
		                  	<div class="col-sm-12 col-md-2 control-label atchFilePermMxmmCntArea" style="display: ${bbsOptnVO.atchFileUseAt eq 'Y' ? 'block' : 'none'};">
		                  		<!-- 첨부파일 최대 갯수-->
								<c:set var="title"><spring:message code="vaivBbsOptn.regist.atchFilePermMxmmCnt" /></c:set>
								<label for="atchFilePermMxmmCnt">${title } </label>
							</div>
							<div class="col-sm-12 col-md-2 atchFilePermMxmmCntArea" style="display: ${bbsOptnVO.atchFileUseAt eq 'Y' ? 'block' : 'none'};">
								<form:input path="atchFilePermMxmmCnt" class="form-control onlyNum" title="${title} 입력" size="40" maxlength="50" />
								<div><form:errors path="atchFilePermMxmmCnt" cssClass="error" /></div>
		                  	</div>
		              	</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<!-- 답글 사용 여부  -->
								<c:set var="title"><spring:message code="vaivBbsOptn.regist.answerUseAt" /></c:set>
								<label for="answerUseAt">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<label class="radio-inline" for="answerUseAtY">
                                	<form:radiobutton path="answerUseAt" id="answerUseAtY" value="Y" /> 사용
                                </label>
                                <label class="radio-inline" for="answerUseAtN">
                                	<form:radiobutton path="answerUseAt" id="answerUseAtN" value="N" /> 사용안함
                                </label>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsOptn.regist.cmtUseAt" /></c:set>
								<label for="cmtUseAt">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<label class="radio-inline" for="cmtUseAtY">
                                	<form:radiobutton path="cmtUseAt" id="cmtUseAtY" value="Y" /> 사용
                                </label>
                                <label class="radio-inline" for="cmtUseAtN">
                                	<form:radiobutton path="cmtUseAt" id="cmtUseAtN" value="N" /> 사용안함
                                </label>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsOptn.regist.secretUseAt" /></c:set>
								<label for="secretUseAt">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<label class="radio-inline" for="secretUseAtY">
                                	<form:radiobutton path="secretUseAt" id="secretUseAtY" value="Y" /> 사용
                                </label>
                                <label class="radio-inline" for="secretUseAtN">
                                	<form:radiobutton path="secretUseAt" id="secretUseAtN" value="N" /> 사용안함
                                </label>
		                  	</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsOptn.regist.noticeUseAt" /></c:set>
								<label for="noticeUseAt">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<label class="radio-inline" for="noticeUseAtY">
                                	<form:radiobutton path="noticeUseAt" id="noticeUseAtY" value="Y" /> 사용
                                </label>
                                <label class="radio-inline" for="noticeUseAtN">
                                	<form:radiobutton path="noticeUseAt" id="noticeUseAtN" value="N" /> 사용안함
                                </label>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsOptn.regist.lcIndictUseAt" /></c:set>
								<label for="lcIndictUseAt">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<label class="radio-inline" for="lcIndictUseAtY">
                                	<form:radiobutton path="lcIndictUseAt" id="lcIndictUseAtY" value="Y" /> 사용
                                </label>
                                <label class="radio-inline" for="lcIndictUseAtN">
                                	<form:radiobutton path="lcIndictUseAt" id="lcIndictUseAtN" value="N" /> 사용안함
                                </label>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsOptn.regist.popupUseAt" /></c:set>
								<label for="popupUseAt">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<label class="radio-inline" for="popupUseAtY">
                                	<form:radiobutton path="popupUseAt" id="popupUseAtY" value="Y" /> 사용
                                </label>
                                <label class="radio-inline" for="popupUseAtN">
                                	<form:radiobutton path="popupUseAt" id="popupUseAtN" value="N" /> 사용안함
                                </label>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsOptn.regist.cclUseAt" /></c:set>
								<label for="cclUseAtY">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<label class="radio-inline" for="cclUseAtY">
                                	<form:radiobutton path="cclUseAt" id="cclUseAtY" value="Y" /> 사용
                                </label>
                                <label class="radio-inline" for="cclUseAtN">
                                	<form:radiobutton path="cclUseAt" id="cclUseAtN" value="N" /> 사용안함
                                </label>
							</div>
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivBbsOptn.regist.koglUseAt" /></c:set>
								<label for="koglUseAt">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-2">
								<label class="radio-inline" for="koglUseAtY">
                                	<form:radiobutton path="koglUseAt" id="koglUseAtY" value="Y" /> 사용
                                </label>
                                <label class="radio-inline" for="koglUseAtN">
                                	<form:radiobutton path="koglUseAt" id="koglUseAtN" value="N" /> 사용안함
                                </label>
							</div>
						</div>
					</div>
				</div>
				<div class="card-wrap-footer">
					<button type="button" class="btn btn-outline-secondary" onclick="fnSelectBbsList();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
						<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
					</button>
					<div class="float-right">
						<button type="submit" id="bbsOptnUpdateBtn" class="btn btn-warning" title="<spring:message code="button.update" /> <spring:message code="input.button" />">
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