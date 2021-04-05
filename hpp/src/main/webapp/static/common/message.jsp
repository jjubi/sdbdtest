<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/sweetalert2/sweetalert2-10.9.0.min.css"/>
<script src="${pageContext.request.contextPath }/static/js/plugins/jquery/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath }/static/js/plugins/sweetalert2/sweetalert2-10.9.0.min.js"></script>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/com/swAlert.js"></script>
</head>
<body>
<script>
	window.onload = function(){
		swAlertExtand({
			<c:if test="${not empty title}">
			title : '${title}',
			</c:if>
			<c:if test="${not empty message}">
			text : '${message}',
			</c:if>
			<c:if test="${not empty icon}">
			icon : '${icon}',
			</c:if>
		}, function(result){
			history.go(-1);
		});
	}
</script>
</body>
</html>