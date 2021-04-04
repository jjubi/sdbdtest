<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>

<c:if test="${fn:length(fileList) gt 2 }">
	<a href="javascript:fnSelectNtt('<c:out value="${nttId }"/>');" class="btn pt-0 pb-0 btn-file btn-not-ico" title="상세페이지로">
		<i class="far fa-file-download"></i>
	</a>
</c:if>
<c:if test="${fn:length(fileList) le 2 }">
	<c:if test="${fn:length(fileList) eq 0 }">
		파일 없음.
	</c:if>
	<c:forEach items="${fileList }" var="fileVO" varStatus="status" begin="0" end="1">
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
		<a href="javascript:fnFileDown('${fileVO.atchFileId}', '${fileVO.fileSn}');" class="btn pt-0 pb-0 btn-file btn-not-ico" title="<c:out value="${fileVO.orignlFileNm }"/>">
			<i class="far fa-file-<c:out value="${extIcon}"/>"></i>
		</a>	
		</sec:authorize>
		<sec:authorize access="!hasRole('${bbsAuthorVO.nttFileDwldAuthor }')">
		<a href="javascript:Swal.fire('확인', '파일다운로드 권한이 없습니다.', 'info');" class="btn pt-0 pb-0 btn-file btn-not-ico" title="<c:out value="${fileVO.orignlFileNm }"/>">
			<i class="far fa-file-<c:out value="${extIcon}"/>"></i>
		</a>	
		</sec:authorize>
	</c:forEach>
</c:if>







