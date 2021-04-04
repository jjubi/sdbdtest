<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>

<c:forEach items="${fileList }" var="fileVO" varStatus="status">
	<c:choose>
		<c:when test="${fileVO.fileExtsn eq 'xlsx' || fileVO.fileExtsn eq 'xls'}">
			<c:set var="extIcon" value="excel"/>
		</c:when>
		<c:when test="${fileVO.fileExtsn eq 'docx' || fileVO.fileExtsn eq 'doc'}">
			<c:set var="extIcon" value="word"/>
		</c:when>
		<c:when test="${fileVO.fileExtsn eq 'pdf'}">
			<c:set var="extIcon" value="pdf"/>
		</c:when>
		<c:when test="${fileVO.fileExtsn eq 'pptx' || fileVO.fileExtsn eq 'ppt'}">
			<c:set var="extIcon" value="powerpoint"/>
		</c:when>
		<c:when test="${fileVO.fileExtsn eq 'jpg' || fileVO.fileExtsn eq 'jpeg' || fileVO.fileExtsn eq 'png' || fileVO.fileExtsn eq 'gif' || fileVO.fileExtsn eq 'bmp'}">
			<c:set var="extIcon" value="image"/>
		</c:when>
		<c:otherwise>
			<c:set var="extIcon" value="download"/>
		</c:otherwise>
	</c:choose>
	<sec:authorize access="hasRole('${bbsAuthorVO.nttFileDwldAuthor }')">
	<a href="javascript:fnFileDown('${fileVO.atchFileId }', '${fileVO.fileSn }');" class="btn btn-file" title="<c:out value="${fileVO.orignlFileNm }"/>">
		<span>
			<i class="far fa-file-<c:out value="${extIcon}"/>"></i>
		</span>
		<c:out value="${fileVO.orignlFileNm }"/>
		<i class="fas fa-arrow-circle-down"></i>
	</a>	
	</sec:authorize>
	<sec:authorize access="!hasRole('${bbsAuthorVO.nttFileDwldAuthor }')">
	<a href="javascript:Swal.fire('확인', '파일다운로드 권한이 없습니다.', 'info');" class="btn btn-file" title="<c:out value="${fileVO.orignlFileNm }"/>">
		<span>
			<i class="far fa-file-<c:out value="${extIcon}"/>"></i>
		</span>
		<c:out value="${fileVO.orignlFileNm }"/>
		<i class="fas fa-arrow-circle-down"></i>
	</a>	
	</sec:authorize>
</c:forEach>
    