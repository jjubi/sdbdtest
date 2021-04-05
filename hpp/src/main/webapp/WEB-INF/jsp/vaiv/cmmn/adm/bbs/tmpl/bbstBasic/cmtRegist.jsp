<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : bbstBasic > cmtRegist.jsp
  * @상세설명 : basic 댓글 등록 페이지
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

<div class="comment-register">
	<div class="comment-title">
		<h6><c:out value="${loginVO.name }"/></h6>
	</div>
	<div class="comment-register-con">
		<textarea id="cmtCn" class="form-control form-control-flush comment-input-textarea" data-toggle="autosize" rows="3" placeholder="댓글을 입력해주세요.."></textarea>
		<button type="button" class="btn btn-dark" onclick="fnInsertCmt();">댓글 등록</button>
	</div>
</div>
