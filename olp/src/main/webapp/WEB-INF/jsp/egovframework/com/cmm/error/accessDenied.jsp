<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>CMS</title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/vaiv/cmmn/adm/error_01.css" />
	<script>
		function fncGoAfterErrorPage(){
		    history.back(-2);
		}
	</script> 
</head>

<body>
    <div class="error_wrap">
        <div class="logo">
            <a href="${pageContext.request.contextPath }/cmmn/adm/dashboard/dashboardMain.do">VAIV COMPANY</a>
        </div>
        <div class="contBox">
            <div class="cont_inner">
                <div class="error_img">
                    <img class="for_bg" src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/restriction_img.png" alt="">
                    <div class="tit_02">죄송합니다. 페이지에 대한 접근 권한이 없습니다.</div>
                </div>
                <div class="btn_wrap">
                    <a href="${pageContext.request.contextPath }/cmmn/adm/dashboard/dashboardMain.do" class="btn1">메인으로</a>
                    <a href="javascript:fncGoAfterErrorPage();" class="btn2">이전 페이지</a>
                </div>
            </div>
        </div>
        <div class="error_footer">
            <p>Copyright © VAIV 2021</p>
        </div>
    </div>
</body>
</html>
