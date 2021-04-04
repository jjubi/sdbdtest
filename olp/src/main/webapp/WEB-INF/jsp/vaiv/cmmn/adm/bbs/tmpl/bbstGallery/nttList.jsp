<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : bbstGallery > nttList.jsp
  * @상세설명 : Gallery 게시물 목록 표출 페이지 
  * @작성일시 : 2021. 02. 15
  * @작 성 자 : jo
  * @수 정 이 력
  * @
  * @  수정일         수정자                   수정내용
  * @ -------       --------    ---------------------------
  * @ 2021.02.15   jo	  최초등록
  * @ 
  * 
  */
%> 
<%@ include file="/WEB-INF/jsp/vaiv/cmmn/adm/bbs/tmpl/nttCommon.jsp" %>
<c:set var="pageTitle"><c:out value="${bbsVO.bbsNm }"/></c:set>

<div class="container-fluid">
	<h1 class="header-title">${pageTitle}</h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
			<form id="listForm" name="listForm" method="post" action="${pageContext.request.contextPath }/cmmn/adm/bbs/ntt/${bbsId}/nttList.do" onsubmit="return false;">
				<fieldset>
					<legend>게시물 검색 영역</legend>
					<input type="hidden" name="bbsTyCode" value="<c:out value="${bbsTyCode }"/>"/>
					<input type="hidden" name="pageIndex" value="${not empty searchVO ? searchVO.pageIndex : 1}"/>
					<div class="ui program-search" title="게시물 검색 영역">
						<div class="btn-group float-left">
							<a class="btn btn-sm btn-basic" href="${pageContext.request.contextPath }/cmmn/adm/bbs/ntt/${bbsId}/nttRegist.do" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
								<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
							</a>
						</div>
						<div class="search_inner clearfix float-right">
							<div class="fieldset">
								<div class="search-select">
									<span>
										<select name="searchCondition" class="form-control" title="검색조건 선택">
											<option value="1" <c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>>제목</option>
											<option value="2" <c:if test="${searchVO.searchCondition eq '2'}">selected="selected"</c:if>>내용</option>
										</select>
									</span>
								</div>
								<div class="search-text">
									<span>
										<input type="text" class="form-control pressEnter" name="searchKeyword" data-press="fnSelectNttList('1');" placeholder="검색어 입력 ..." title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155">			
									</span>
								</div>
								<div class="search-btn">
									<button type="button" class="btn btn-sm btn-dark" onclick="fnSelectNttList('1')">
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
                </div>
			</form>
			<!-- 테이블 : s -->
			<div class="gallery_type1 col3">
				<c:forEach items="${nttList }" var="nttVO" varStatus="status">
				<div class="photo-item col">
					<div class="photo-item-box">
						<a href="javascript:fnSelectNtt('<c:out value="${nttVO.nttId }"/>');">
							<div class="card-img-top">
								<div class="inner">
									<c:choose>
										<c:when test="${not empty nttVO.atchFileId }">
											<img alt="${nttVO.nttSj }의 이미지 썸네일" src="${pageContext.request.contextPath }/cmmn/com/cfc/getOneImageFile.do?atchFileId=${nttVO.atchFileId}">
										</c:when>
										<c:otherwise>
											<img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/no_image_photoG.png" alt="이미지 없음.">	
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="card-body">
								<h4 class="card-title"><c:out value="${nttVO.nttSj }" escapeXml="false"/></h4>
								<p class="card-text">
									<c:out value="${nttVO.nttCn }" escapeXml="false"/>
								</p>
							</div>
							<div class="card-footer">
								<small>
									<span class="Name"><c:out value="${nttVO.registNm }"/></span>
									<span><c:out value="${nttVO.registDe }"/></span>
								</small>
							</div>
						</a>
					</div>
				</div>
				</c:forEach>
			</div>
			<!-- 테이블 : e -->
			<!-- paging navigation -->
			<div class="text-center">
				<ul class="pagination justify-content-center">
					<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnSelectNttList"/>
				</ul>
			</div>
		</div>
	</div>
</div>
