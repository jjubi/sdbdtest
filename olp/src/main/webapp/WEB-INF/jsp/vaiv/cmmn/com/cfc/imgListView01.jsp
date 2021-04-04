<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>

<div class="gallery_view">
	<div class="slider-for">	
		<c:forEach items="${fileList }" var="fileVO" varStatus="status">
			<c:if test="${fileVO.fileExtsn eq 'jpg' || fileVO.fileExtsn eq 'jpeg' || fileVO.fileExtsn eq 'png' || fileVO.fileExtsn eq 'gif' || fileVO.fileExtsn eq 'bmp'}">
				<div><img src="${pageContext.request.contextPath }/cmmn/com/cfc/getOneImageFile.do?atchFileId=${fileVO.atchFileId }&amp;fileOrdrNo=${status.index}" alt="${fileVO.orignlFileNm }"></div>
			</c:if>
		</c:forEach>
	</div>
	<div class="slider-nav">
		<c:forEach items="${fileList }" var="fileVO" varStatus="status">
			<c:if test="${fileVO.fileExtsn eq 'jpg' || fileVO.fileExtsn eq 'jpeg' || fileVO.fileExtsn eq 'png' || fileVO.fileExtsn eq 'gif' || fileVO.fileExtsn eq 'bmp'}">
				<div><img src="${pageContext.request.contextPath }/cmmn/com/cfc/getOneImageFile.do?atchFileId=${fileVO.atchFileId }&amp;fileOrdrNo=${status.index}" alt="${fileVO.orignlFileNm }"></div>
			</c:if>
		</c:forEach>
	</div>
</div>
