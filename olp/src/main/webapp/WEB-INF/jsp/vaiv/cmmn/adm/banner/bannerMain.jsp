<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : bannerMain.jsp
  * @상세설명 : 배너 표출 페이지
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
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/bootstrap/bootstrap-toggle.css"/>
<script src="${pageContext.request.contextPath }/static/js/plugins/bootstrap/bootstrap-toggle.js"></script>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/banner/banner.js"></script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
			<!-- 검색 영역 : s -->
			<form id="listForm" name="listForm" method="post" action="${pageContext.request.contextPath}/cmmn/adm/banner/bannerMain.do">
				<fieldset>
					<legend>팝업 관리 검색 영역</legend>
					<input type="hidden" name="bannerSeqNo" value="">
					<input type="hidden" name="bannerTy" value="${not empty bannerTy ? bannerTy : searchVO.bannerTy }"/>
					<input type="hidden" name="pageIndex" value="${not empty searchVO ? searchVO.pageIndex : 1}" >
					<div class="ui program-search" title="팝업 관리 검색 영역">
						<div class="btn-group float-left">
							<a class="btn btn-sm btn-basic" href="<c:url value='/cmmn/adm/banner/bannerRegist.do'/>" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
								<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
							</a>
						</div>
						<div class="search_inner clearfix float-right">
							<div class="fieldset">
								<div class="search-select">
									<span>
										<select name="searchCondition" class="form-control" title="검색조건 선택">
											<option value="1" <c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>>배너 명</option>
										</select>
									</span>
								</div>
								<div class="search-text">
									<span>
										<input class="form-control pressEnter" name="searchKeyword" data-press="fnSelectBannerList('1');" type="text" placeholder="검색어 입력 ..." title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155">
									</span>
								</div>
								<div class="search-btn">
									<button type="button" class="btn btn-sm btn-dark" onclick="fnSelectBannerList('1')">
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
                    	<a class="btn btn-sm ${empty bannerTy && empty searchVO.bannerTy ? 'active' : '' }" href="${pageContext.request.contextPath }/cmmn/adm/banner/bannerMain.do" class="bannerType">
                    		전체
                    		<c:if test="${empty bannerTy && empty searchVO.bannerTy }">
                    			<i class="fas fa-caret-down"></i>
                    		</c:if>
                    	</a>
						<c:forEach items="${bannerTyList }" var="bannerTyVO" varStatus="status">
							<a class="btn btn-sm ${not empty bannerTy && bannerTy eq bannerTyVO.code ? 'active' : (not empty searchVO.bannerTy && searchVO.bannerTy eq bannerTyVO.code ? 'active' : '')}" href="${pageContext.request.contextPath }/cmmn/adm/banner/bannerMain.do?bannerTy=<c:out value="${bannerTyVO.code }"/>" class="bannerType">
								<c:out value="${bannerTyVO.codeNm }"/>
								<c:if test="${(not empty bannerTy && bannerTy eq bannerTyVO.code) || (not empty searchVO.bannerTy && searchVO.bannerTy eq bannerTyVO.code)}">
									<i class="fas fa-caret-down"></i>
								</c:if>
							</a>
						</c:forEach>
                    </div>
                </div>
			</form>			
			<!-- 검색 영역 : e -->
			<div class="dd nestableWapper" id="bannerList">
				<c:if test="${empty bannerList }">
					<div class="dd-empty"> 데이터가 없습니다.</div>
				</c:if>
				<c:if test="${not empty bannerList }">
			    <ol class="dd-list kanban-category">
			    	<c:forEach items="${bannerList }" var="bannerVO" varStatus="status">
			    		<li class="dd-item kanban-item" data-banner-seq-no="${bannerVO.bannerSeqNo }">
			    			<div class="dd-handle dd3-handle">Drag</div>
			    			<div class="card card-sm mb-2">
			    				<div class="card-body">
			    					<div class="row align-items-center">
			    						<div class="col-12 col-sm">
			    							<p class="mb-sm-0 dd-card-name">
			    								${bannerVO.bannerNm } 
			    								<c:choose>
			    									<c:when test="${bannerVO.bannerPdCheck eq 'N'}"><font color="red">(게시기간 지남)</font></c:when>
			    									<c:when test="${bannerVO.bannerPdCheck eq 'P'}"><font color="green">(게시 전)</font></c:when>
			    									<c:otherwise></c:otherwise>
			    								</c:choose>
			    							</p>
			    						</div>
			    						<div class="col col-sm-auto">
			    							<a href="${bannerVO.bannerUrl }" target="_blank">
												<img src="${pageContext.request.contextPath }/cmm/fms/getImage.do?atchFileId=${bannerVO.atchFileId}&amp;fileSn=0" height="30">
											</a>
							            </div>
							            <div class="col col-sm-auto">
							            	<input type="checkbox" name="useAt" class="bootstrap-toggle-btn useAt" data-size="sm" data-style="round" <c:if test="${bannerVO.useAt eq 'Y' }">checked="checked"</c:if>>
							            </div>
							            <div class="col-auto col-sm-auto">
							               <div class="avatar-group">
							                  <a class="btn btn-sm btn-info mr-3" href="javascript:fnSelectBanner('<c:out value="${bannerVO.bannerSeqNo }"/>');">수정</a>
							                  <a class="btn btn-sm btn-danger" href="javascript:fnDeleteBanner('<c:out value="${bannerVO.bannerSeqNo }"/>');">삭제</a>
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
			<!-- contents 영역 : e -->
		</div>
	</div>
</div>











