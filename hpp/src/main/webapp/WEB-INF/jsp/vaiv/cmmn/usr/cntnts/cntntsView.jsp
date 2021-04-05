<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : cntntsView.jsp
  * @상세설명 : 사용자 컨텐츠 상세 뷰 페이지
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

<!-- 추후 디자인 확인 후 수정 -->
<div class="float-right">
	<c:import url="/cmmn/com/cuc/socialShareView.do"></c:import>
</div>
<c:out value="${viewCntntsVO.cntntsCn}" escapeXml="false"/>