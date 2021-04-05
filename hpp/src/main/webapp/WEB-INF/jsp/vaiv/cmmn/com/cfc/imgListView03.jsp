<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>

<link rel="stylesheet" type="text/css" href="/static/css/plugins/lightgallery/lightgallery.css" />
<script type="text/javascript" src="/static/js/plugins/lightgallery/lightgallery.min.js"></script>

<div class="gallery_type2">
	<ul id="lightgallery" class="list-unstyled col4">
		<c:forEach items="${fileList }" var="fileVO" varStatus="status">
			<c:if test="${fileVO.fileExtsn eq 'jpg' || fileVO.fileExtsn eq 'jpeg' || fileVO.fileExtsn eq 'png' || fileVO.fileExtsn eq 'gif' || fileVO.fileExtsn eq 'bmp'}">
				<li class="col" data-src="${pageContext.request.contextPath }/cmmn/com/cfc/getOneImageFile.do?atchFileId=${fileVO.atchFileId }&amp;fileOrdrNo=${status.index}">
					<a href="">
						<div class="inner">
							<img alt="${fileVO.orignlFileNm }" src="${pageContext.request.contextPath }/cmmn/com/cfc/getOneImageFile.do?atchFileId=${fileVO.atchFileId }&amp;fileOrdrNo=${status.index}">
						</div>
						<div class="gallery_type2-poster">
							<img alt="확대이미지" src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/zoom.png">
						</div>
					</a>
				</li>
			</c:if>
		</c:forEach>
	</ul>
</div>

<script>
	//https://sachinchoolur.github.io/lightgallery.js 옵션 확인 후 추가 및 삭제 가능
	lightGallery(document.getElementById('lightgallery'), {
		download : false
	});
</script>
