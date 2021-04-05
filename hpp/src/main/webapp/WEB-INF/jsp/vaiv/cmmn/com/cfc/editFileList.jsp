<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>

<script>
var deleteFileArr = "";
function fnOriFileDel(atchFileId, fileSn){
	swAlertConfirm('삭제하시겠습니까?', {icon:'warning', confirmButtonText:'삭제'}, function(){
		if(deleteFileArr != ''){
			deleteFileArr += ',';
		} 
		deleteFileArr += atchFileId+";"+fileSn;
		$('#fileNo_'+fileSn).remove();
		$('#deleteFileArr').val(deleteFileArr);
	});
}
</script>

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
	<div class="file_add col-sm-12 col-md-12 mt-2" id="fileNo_${fileVO.fileSn }">
		<c:if test="${not empty bbsAuthorVO }">
		<sec:authorize access="hasRole('${bbsAuthorVO.nttFileDwldAuthor }')">
		<a href="javascript:fnFileDown('${fileVO.atchFileId }', '${fileVO.fileSn }');" class="btn p-0 btn-file btn-not-ico" title="<c:out value="${fileVO.orignlFileNm }"/>">
			<i class="far fa-file-<c:out value="${extIcon}"/> mr-2"></i><c:out value="${fileVO.orignlFileNm }"/>
		</a>
		</sec:authorize>
		<sec:authorize access="!hasRole('${bbsAuthorVO.nttFileDwldAuthor }')">
		<a href="javascript:swAlert('확인', '파일다운로드 권한이 없습니다.', 'info');" class="btn pt-0 pb-0 btn-file btn-not-ico" title="<c:out value="${fileVO.orignlFileNm }"/>">
			<i class="far fa-file-<c:out value="${extIcon}"/> mr-2"></i><c:out value="${fileVO.orignlFileNm }"/>
		</a>	
		</sec:authorize>
		</c:if>
		<c:if test="${empty bbsAuthorVO }">
		<a href="javascript:fnFileDown('${fileVO.atchFileId }', '${fileVO.fileSn }');" class="btn p-0 btn-file btn-not-ico" title="<c:out value="${fileVO.orignlFileNm }"/>">
			<i class="far fa-file-<c:out value="${extIcon}"/> mr-2"></i><c:out value="${fileVO.orignlFileNm }"/>
		</a>
		</c:if>
		<input type="button" value="삭제" class="btn btn-sm btn-danger ml-3" onclick="fnOriFileDel('${fileVO.atchFileId }', '${fileVO.fileSn }');">
	</div>
</c:forEach>

<input type="hidden" id="deleteFileArr" name="deleteFileArr" value="" >
    