<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="kr">
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
                    <img class="for_bg" src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/error_img.png" alt="Page Not Found">
                    <div class="tit">요청하신 페이지를 찾을 수 없습니다.</div>
                    <div class="txt">
                        <p>
                            <span>페이지가 존재하지 않거나, 사용할 수 없는 페이지입니다. </span>
                            <span>입력한 주소가 정확한지 다시 한 번 확인해 주시기 바랍니다.</span>
                        </p>
                    </div>
                </div>
                <div class="btn_wrap">
                    <a href="${pageContext.request.contextPath }/cmmn/adm/dashboard/dashboardMain.do" class="btn1">메인으로</a>
                    <a href="javascript:fncGoAfterErrorPage();" class="btn2">이전 페이지</a>
                </div>
            </div>
        </div>
        <div class="error_footer">
            <p>(주) 바이브컴퍼니</p>
            <p>서울특별시 용산구 독서당로 97 대표이사 송성환 사업자등록번호 120-86-08334</p>
            <p>Copyright © vaiv. All rights reserved. </p>
        </div>
    </div>
</body>

</html>
