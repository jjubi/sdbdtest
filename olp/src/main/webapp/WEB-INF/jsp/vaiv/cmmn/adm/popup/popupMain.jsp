<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : popupMain.jsp
  * @상세설명 : 팝업 표출 페이지
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
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/bootstrap/bootstrap-toggle.css"/>
<script src="${pageContext.request.contextPath }/static/js/plugins/bootstrap/bootstrap-toggle.js"></script>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/popup/popup.js"></script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
			<form id="listForm" name="listForm" method="post" action="${pageContext.request.contextPath}/cmmn/adm/popup/popupMain.do" onsubmit="return false;">
				<fieldset>
					<legend>팝업 관리 검색 영역</legend>
					<input type="hidden" name="popupTy" value="${not empty popupTy ? popupTy : searchVO.popupTy }"/>
					<input type="hidden" name="popupSeqNo" value="" >
					<input type="hidden" name="pageIndex" value="${not empty searchVO ? searchVO.pageIndex : 1}" >
					<div class="ui program-search" title="팝업 관리 검색 영역">
						<div class="btn-group float-left">
							<a class="btn btn-sm btn-basic" href="<c:url value='/cmmn/adm/popup/popupRegist.do'/>" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
								<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
							</a>
						</div>
						<div class="search_inner clearfix float-right">
							<div class="fieldset">
								<div class="search-select">
									<span>
										<select name="searchCondition" class="form-control" title="검색조건 선택">
											<option value="1" <c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>>팝업 명</option>
										</select>
									</span>
								</div>
								<div class="search-text">
									<span>
										<input class="form-control pressEnter" name="searchKeyword" data-press="fnSelectPopupList('1');" type="text" placeholder="검색어 입력 ..." title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155">
									</span>
								</div>
								<div class="search-btn">
									<button type="button" class="btn btn-sm btn-dark" onclick="fnSelectPopupList('1')">
										<i class="fa fa-search" aria-hidden="true"></i> 조회
									</button>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="ui program-search-form">
                    <!-- 게시글정보 -->
                    <div class="ui program-info">
                        <div class="ui program-count">
                            <span>총 게시물 
                            	<strong><c:out value="${paginationInfo.totalRecordCount }"/></strong> 개
                            </span>, 
                            <span class="ui program--division-line">페이지 
                            	<strong><c:out value="${paginationInfo.currentPageNo }"/></strong>
                                / <c:out value="${paginationInfo.totalPageCount }"/>
                            </span>
                        </div>
                    </div>
                    <div class="right select-btn">
                    	<a class="btn btn-sm ${empty popupTy && empty searchVO.popupTy ? 'active' : '' }" href="${pageContext.request.contextPath }/cmmn/adm/popup/popupMain.do" class="popupType">
                    		전체
                    		<c:if test="${empty popupTy && empty searchVO.popupTy }">
                    			<i class="fas fa-caret-down"></i>
                    		</c:if>
                    	</a>
						<c:forEach items="${popupTyList }" var="popupTyVO" varStatus="status">
							<a class="btn btn-sm ${not empty popupTy && popupTy eq popupTyVO.code ? 'active' : (not empty searchVO.popupTy && searchVO.popupTy eq popupTyVO.code ? 'active' : '')}" href="${pageContext.request.contextPath }/cmmn/adm/popup/popupMain.do?popupTy=<c:out value="${popupTyVO.code }"/>" class="popupType">
								<c:out value="${popupTyVO.codeNm }"/>
								<c:if test="${(not empty popupTy && popupTy eq popupTyVO.code) || (not empty searchVO.popupTy && searchVO.popupTy eq popupTyVO.code)}">
									<i class="fas fa-caret-down"></i>
								</c:if>
							</a>
						</c:forEach>
                    </div>
                </div>
			</form>
			<div class="dd nestableWapper" id="popupList">
				<c:if test="${empty popupList }">
					<div class="dd-empty"> 데이터가 없습니다.</div>
				</c:if>
				<c:if test="${not empty popupList }">
			    <ol class="dd-list kanban-category">
			    	<c:forEach items="${popupList }" var="popupVO" varStatus="status">
			    		<li class="dd-item kanban-item" data-popup-seq-no="${popupVO.popupSeqNo }">
			    			<div class="dd-handle dd3-handle">Drag</div>
			    			<div class="card card-sm mb-2">
			    				<div class="card-body">
			    					<div class="row align-items-center">
			    						<div class="col-12 col-sm">
			    							<p class="mb-sm-0 dd-card-name">
			    								<c:out value="${popupVO.popupNm }"/>
			    								<c:choose>
			    									<c:when test="${popupVO.popupPdCheck eq 'N'}"><font color="red">(게시기간 지남)</font></c:when>
			    									<c:when test="${popupVO.popupPdCheck eq 'P'}"><font color="green">(게시 전)</font></c:when>
			    									<c:otherwise></c:otherwise>
			    								</c:choose>
			    							</p>
			    						</div>
			    						<div class="col col-sm-auto">
			    							<a href="${popupVO.popupUrl }" target="_blank">
												<img src="${pageContext.request.contextPath}/cmm/fms/getImage.do?atchFileId=${popupVO.atchFileId}&amp;fileSn=0" height="30">
											</a>
							            </div>
							            <div class="col col-sm-auto">
							            	<input type="checkbox" name="useAt" class="bootstrap-toggle-btn useAt" data-size="sm" data-style="round" <c:if test="${popupVO.useAt eq 'Y' }">checked="checked"</c:if>>
							            </div>
							            <div class="col-auto col-sm-auto">
							               <div class="avatar-group">
							                  <a class="btn btn-sm btn-info mr-3" href="javascript:fnSelectPopup('<c:out value="${popupVO.popupSeqNo }"/>');">수정</a>
							                  <a class="btn btn-sm btn-danger" href="javascript:fnDeletePopup('<c:out value="${popupVO.popupSeqNo }"/>');">삭제</a>
							               </div>
							            </div>
			    					</div>
			    				</div>
			    			</div>
			    		</li>
			    	</c:forEach>
			    </ol>
			    </c:if>
			</div>
		</div>
	</div>
</div>
			
