<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="author" content="jo">
	<meta name="description" content="Style Guide Manage Admin">
	<meta name="keywords" content="style, guide, style guide, manage, admin">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1"> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!--[if lt IE 9]>
	    <script src="assets/js/html5shiv.js"></script>
	<![endif]-->
	
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<![endif]-->
	<title>CMS Basic</title>
	
	<!-- favicon -->
	<link rel="shortcut icon" href="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/vaiv_favicon.ico" type="image/x-icon">
	
	<!-- Libs CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/bootstrap/bootstrap-4.5.2.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/fontawesome/fontawesome-5.15.1.all.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/sweetalert2/sweetalert2-10.9.0.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/jquery/jquery.mCustomScrollbar-3.1.5.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/summernote/summernote.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/vaiv/cmmn/adm/layout.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/vaiv/cmmn/com/common.css" />

	<!-- Theme CSS -->
<!-- 	<link rel="stylesheet" href="/css/adm/theme/theme.min.css" id="stylesheetLight"> -->
	
	<!-- JAVASCRIPT : s-->
    <!-- Libs JS -->
    <script src="${pageContext.request.contextPath }/static/js/plugins/jquery/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/plugins/jquery/jquery.nestable2.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/plugins/bootstrap/bootstrap-4.5.2.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/plugins/sweetalert2/sweetalert2-10.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/plugins/jquery/jquery.mCustomScrollbar-3.1.5.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/plugins/moment/moment-with-locales.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/plugins/summernote/summernote.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/plugins/clipboard/clipboard.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/com/common.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/com/swAlert.js"></script>
	
</head>
<body>
	<t:insertAttribute name="left" />
	<footer>
		<t:insertAttribute name="footer" />
	</footer>
	<div class="main-content">
		<t:insertAttribute name="header"/>
		<div class="container-fluid">
			<t:insertAttribute name="content"/>
			<!-- 만족도 조사 jsp 추가 -->
			<jsp:include page="/WEB-INF/jsp/vaiv/cmmn/com/cuc/csnst.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>