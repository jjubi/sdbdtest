<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>

<div class="gallery_view">
	<div class="slider2 center">
		<c:forEach items="${fileList }" var="fileVO" varStatus="status">
			<c:if test="${fileVO.fileExtsn eq 'jpg' || fileVO.fileExtsn eq 'jpeg' || fileVO.fileExtsn eq 'png' || fileVO.fileExtsn eq 'gif' || fileVO.fileExtsn eq 'bmp'}">
				<div><h3><img src="${pageContext.request.contextPath }/cmmn/com/cfc/getOneImageFile.do?atchFileId=${fileVO.atchFileId }&amp;fileOrdrNo=${status.index}" alt="${fileVO.orignlFileNm }"></h3></div>
			</c:if>
		</c:forEach>
	</div>
</div>
