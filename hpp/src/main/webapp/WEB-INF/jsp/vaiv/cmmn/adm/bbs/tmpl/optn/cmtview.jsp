<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : nttOptn > cmtview.jsp
  * @상세설명 : 게시물 옵션 페이지(댓글영역) 
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
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/bbs/ntt/cmt/cmt.js"></script>

<div class="form-horizontal cmtArea">
	<h5>댓글쓰기</h5>
	<c:forEach items="${cmtList }" var="cmtVO" varStatus="status">
		<c:import url="/cmmn/adm/bbs/ntt/cmt/cmtDetail.do">
			<c:param name="bbsTyCode" value="${cmtBbsTyCode }"/>
			<c:param name="nttId" value="${cmtNttId }"/>
			<c:param name="cmtId" value="${cmtVO.cmtId }"/>
		</c:import>
	</c:forEach>
	<input type="hidden" name="cmtNttId" value="<c:out value="${cmtNttId }"/>">
	<input type="hidden" name="cmtBbsTyCode" value="<c:out value="${cmtBbsTyCode }"/>"/>
	<sec:authorize access="hasRole('${bbsAuthorVO.cmtRegistAuthor }')">
		<c:import url="/cmmn/adm/bbs/ntt/cmt/cmtRegist.do">
			<c:param name="bbsTyCode" value="${cmtBbsTyCode }"/>
		</c:import>
	</sec:authorize>
</div>
