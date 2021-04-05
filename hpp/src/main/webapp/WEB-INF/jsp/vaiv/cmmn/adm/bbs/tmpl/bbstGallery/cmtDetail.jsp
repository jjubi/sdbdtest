<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : bbstBasic > cmtDetail.jsp
  * @상세설명 : basic 댓글 상세 페이지
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
<% pageContext.setAttribute("rlcn", "\n"); %>

<div class="comment" id="${cmtVO.cmtId }">
	<div class="comment-body">
		<div class="comment-info">
			<div class="comment-title">
				<h6><c:out value="${cmtVO.registNm }"/></h6>
			</div>
			<div class="comment-sub">
				<c:if test="${loginVO.uniqId eq cmtVO.registId }">
				<c:if test="${cmtUpdateAuthor eq 'Y' }">
					<a class="comment-update" href="javascript:fnUpdateCommentForm('${cmtVO.cmtId }');">수정</a>
				</c:if>
				<c:if test="${cmtDeleteAuthor eq 'Y' }">
					<a class="comment-delete" href="javascript:fnDeleteComment('${cmtVO.cmtId }');">삭제</a>
				</c:if>
				</c:if>
				<time class="comment-time"><c:out value="${cmtVO.registDe}"/></time>
			</div>
		</div>
		<div class="comment-text">
			<p>
				<c:out value="${fn:replace(cmtVO.cmtCn, rlcn, '<br/>') }" escapeXml="false"/>
			</p>
		</div>
	</div>
</div>
