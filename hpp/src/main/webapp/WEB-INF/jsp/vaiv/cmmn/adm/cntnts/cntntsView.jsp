<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : cntntsView.jsp
  * @상세설명 : 관리자 컨텐츠 상세 뷰 페이지
  * @작성일시 : 2020. 12. 31
  * @작 성 자 : jo
  * @수 정 이 력
  * @
  * @  수정일         수정자                   수정내용
  * @ -------       --------    ---------------------------
  * @ 2020.12.31   jo     최초등록
  * @ 
  * 
  */
%>
<!-- 추후 디자인 확인 후 수정 -->
<div class="float-right">
    <c:import url="/cmmn/com/cuc/socialShareView.do"></c:import>
</div>
<input type="hidden" name="cntntsId" value="${viewCntntsVO.cntntsId }">
<c:out value="${viewCntntsVO.cntntsCn}" escapeXml="false"/>

<c:if test="${!empty viewCntntsVO.cclTy }">
    <c:import url="/cmmn/com/cuc/cclView.do">
        <c:param name="cclTy" value="${viewCntntsVO.cclTy }"></c:param>
        <c:param name="registNm" value="${viewCntntsVO.registNm }"></c:param>
        <c:param name="cntntsNm" value="${viewCntntsVO.cntntsNm }"></c:param>
    </c:import>
</c:if>
<c:if test="${!empty viewCntntsVO.koglTy }">
    <c:import url="/cmmn/com/cuc/koglView.do">
        <c:param name="koglTy" value="${viewCntntsVO.koglTy }"></c:param>
        <c:param name="registNm" value="${viewCntntsVO.registNm }"></c:param>
        <c:param name="cntntsNm" value="${viewCntntsVO.cntntsNm }"></c:param>
    </c:import>
</c:if>
