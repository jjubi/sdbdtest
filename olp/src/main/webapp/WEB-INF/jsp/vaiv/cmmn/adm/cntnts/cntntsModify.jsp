<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : cntntsModify.jsp
  * @상세설명 : 컨텐츠 수정 페이지
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
<c:set var="pageTitle"><spring:message code="vaivCntnts.title"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="cntntsVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/cntnts/cntnts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/egovframework/com/fms/EgovMultiFile.js"></script>
<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.update" /></h1>
			<form:form commandName="cntntsVO" action="${pageContext.request.contextPath}/cmmn/adm/cntnts/updateCntnts.do" method="post" enctype="multipart/form-data" onsubmit="return false;">
				<div class="card-wrap">
					<div class="form-horizontal contents-form">
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivCntnts.regist.cntntsId" /></c:set>
								<label for="cntntsId">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:hidden path="cntntsId" />
								<c:out value="${cntntsVO.cntntsId }"/>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivCntnts.regist.cntntsCode" /></c:set>
								<label for="cntntsCode">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="cntntsCode" class="form-control noKrLang" title="${title} ${inputTxt}" size="40" maxlength="30" />
								<div><form:errors path="cntntsCode" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivCntnts.regist.cntntsNm" /></c:set>
								<label for="cntntsNm">${title } <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<form:input path="cntntsNm" class="form-control" title="${title} ${inputTxt}" size="40" maxlength="100" />
								<div><form:errors path="cntntsNm" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<c:set var="title"><spring:message code="vaivCntnts.regist.cntntsCn" /></c:set>
								<label for="cntntsCn">${title } </label>
							</div>
							<div class="col-sm-12 col-md-10">
								<textarea id="cntntsCn" name="cntntsCn" class="form-control summernoteArea" title="${title} ${inputTxt}" cols="30" rows="10" required="required"><c:out value="${cntntsVO.cntntsCn }" escapeXml="false"/></textarea>
								<div><form:errors path="cntntsCn" cssClass="error" /></div>
							</div>
						</div>
						<div class="form-group">
							<!-- 첨부파일 사용 -->
							<div class="col-sm-12 col-md-2 control-label col-lg-2">
								<!-- 첨부파일 -->
								<label for="file_0">첨부파일 <span class="pilsu">*</span></label>
							</div>
							<div class="col-sm-12 col-md-10">
								<div class="form-imgbox_file">
					                <div class="custom-file">
					                	<input type="file" name="file_" id="egovComFileUploader" class="custom-file-input" readonly="readonly">
					                    <label class="custom-file-label" for="file_0">파일 찾아보기</label>
					                </div>
					            </div>
					            <div class="col-sm-12 col-md-12 pl-0" id="egovComFileList">
					            	<c:import url="/cmmn/com/cfc/nttFileList.do">
										<c:param name="atchFileId" value="${cntntsVO.atchFileId }"/>
										<c:param name="loadType" value="editFileList"/>
									</c:import>
					            </div>
							</div>
							<input type="hidden" id="atchFilePermExtsn" name="atchFilePermExtsn" value="png,jpg,jpeg,zip,xls,xlsx,ppt,pptx,doc,docx,txt,gif,bmp">
							<input type="hidden" id="atchFilePermMxmmCnt" name="atchFilePermMxmmCnt" value="5">
						</div>
						<div class="form-group">
                            <div class="col-sm-12 col-md-2 control-label col-lg-2">
                            	<c:set var="title"><spring:message code="vaivCntnts.regist.cclTy" /></c:set>
								<label for="cclTyA">${title } <span class="pilsu">*</span></label>
                            </div>
                            <div class="col-sm-12 col-md-10 Kogl_check">
                                <label class="radio-inline" for="cclTyA">
                                	<form:radiobutton path="cclTy" id="cclTyA" value="A"/>
                                	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/ccl/by.png" alt="CCL 컨텐츠저작물 자유이용허락-출처표시">
                                	<span>BY</span>
                                </label>
                                <label class="radio-inline" for="cclTyB">
                                	<form:radiobutton path="cclTy" id="cclTyB" value="B"/>
                                	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/ccl/by-sa.png" alt="CCL 컨텐츠저작물 자유이용허락-출처표시">
                                	<span>BY-SA</span>
                                </label>
                                <label class="radio-inline" for="cclTyC">
                                	<form:radiobutton path="cclTy" id="cclTyC" value="C"/>
                                	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/ccl/by-nd.png" alt="CCL 컨텐츠저작물 자유이용허락-출처표시">
                                	<span>BY-ND</span>
                                </label>
                                <label class="radio-inline" for="cclTyD">
                                	<form:radiobutton path="cclTy" id="cclTyD" value="D"/>
                                	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/ccl/by-nc.png" alt="CCL 컨텐츠저작물 자유이용허락-출처표시">
                                	<span>BY-NC</span>
                                </label>
                                <label class="radio-inline" for="cclTyE">
                                	<form:radiobutton path="cclTy" id="cclTyE" value="E"/>
                                	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/ccl/by-nc-sa.png" alt="CCL 컨텐츠저작물 자유이용허락-출처표시">
                                	<span>BY-NC-SA</span>
                                </label>
                                <label class="radio-inline" for="cclTyF">
                                	<form:radiobutton path="cclTy" id="cclTyF" value="F"/>
                                	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/ccl/by-nc-nd.png" alt="CCL 컨텐츠저작물 자유이용허락-출처표시">
                                	<span>BY-NC-ND</span>
                                </label>
                                <label class="radio-inline" for="cclTyNO">
                                	<form:radiobutton path="cclTy" id="cclTyNO" value=""/> 
                                	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/kogl/no-data-img.png" alt="CCL 없음">
                                	<span>없음</span>
                                </label>
                            </div>
                        </div>
						<div class="form-group">
                            <div class="col-sm-12 col-md-2 control-label col-lg-2">
                            	<c:set var="title"><spring:message code="vaivCntnts.regist.koglTy" /></c:set>
								<label for="koglTyA">${title } <span class="pilsu">*</span></label>
                            </div>
                               <div class="col-sm-12 col-md-10 Kogl_check">
                                <label class="radio-inline" for="koglTyA">
                                	<form:radiobutton path="koglTy" id="koglTyA" value="A"/>
                                	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/kogl/img_opentype01.png" alt="공공누리 공공저작물 자유이용허락-출처표시">
                                	<span>출처표시</span>
                                </label>
                                <label class="radio-inline" for="koglTyB">
                                	<form:radiobutton path="koglTy" id="koglTyB" value="B"/>
                                	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/kogl/img_opentype02.png" alt="공공누리 공공저작물 자유이용허락-출처표시+상업용금지">
                                	<span>출처표시+상업용금지</span>
                                </label>
                                <label class="radio-inline" for="koglTyC">
                                	<form:radiobutton path="koglTy" id="koglTyC" value="C"/>
                                	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/kogl/img_opentype03.png" alt="공공누리 공공저작물 자유이용허락-출처표시+변경금지">
                                	<span>출처표시+변경금지</span>
                                </label>
                                <label class="radio-inline" for="koglTyD">
                                	<form:radiobutton path="koglTy" id="koglTyD" value="D"/>
                                	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/kogl/img_opentype04.png" alt="공공누리 공공저작물 자유이용허락-출처표시+상업용이용금지+변경금지">
                                	<span>출처표시+상업용이용금지+변경금지</span>
                                </label>
                                <label class="radio-inline" for="koglTyNO">
                                	<form:radiobutton path="koglTy" id="koglTyNO" value=""/>
                                	<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/kogl/no-data-img.png" alt="공공누리 없음">
                                	<span>없음</span>
                                </label>
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
					<button type="button" class="btn btn-outline-secondary" onclick="fnSelectCntntsList();" title="<spring:message code="button.list" />  <spring:message code="input.button" />">
						<i class="fa fa-list-ul" aria-hidden="true"></i> <spring:message code="button.list" />
					</button>
					<div class="float-right">
						<button type="button" id="cntntsDeleteBtn" class="btn btn-danger" title="<spring:message code="button.delete" /> <spring:message code="input.button" />">
							<spring:message code="button.delete" /><!-- 삭제 -->
						</button>
						<button type="submit" id="cntntsUpdateBtn" class="btn btn-warning" title="<spring:message code="button.update" /> <spring:message code="input.button" />">
							<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.update" />
						</button>
					</div>
				</div>
				<form:hidden path="atchFileId"/>
			</form:form>
		</div>
	</div>
</div>

<form id="hiddenSearchForm" class="d-none" method="post">
	<input type="hidden" name="searchKeyword" value="${searchVO.searchKeyword }">
	<input type="hidden" name="searchCondition" value="${searchVO.searchCondition }">
	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }">
</form>