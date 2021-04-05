<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Google OTP 인증</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/bootstrap/bootstrap_custom.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/vaiv/cmmn/adm/layout.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/sweetalert2/sweetalert2-10.9.0.min.css"/>
<script src="${pageContext.request.contextPath }/static/js/plugins/jquery/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath }/static/js/plugins/sweetalert2/sweetalert2-10.9.0.min.js"></script>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/com/swAlert.js"></script>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/com/ajaxCall.js"></script>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/com/common.js"></script>
</head>
<body>
	<form id="frm" action="${pageContext.request.contextPath }/cmmn/com/cuc/checkGoogleOTPAuthCodeAjax.do" method="post" onsubmit="return false;">
		<input type="hidden" name="encodedKey" readonly="readonly" value="${authKey['encodedkey'] }">
		<div class="container-fluid otp_wrap">
		    <div class="card">
		        <div class="card-body">
		        	<h1>구글 OTP 인증</h1>
		        	<hr>
		        	<div class="otp_list">
                       <div class="otp_tit">
                         <div><span>1</span></div>
                           <label>key</label>	
                       </div>
                       <div class="otp_txt">
                           <div class="">${authKey['encodedkey'] }</div>
                       </div>
                       
                   </div>
                   <div class="otp_list" id="qr_code_view" style="display: none;">
                       <div class="otp_tit">
                         <div><span>2</span></div>
                           <label>QR code</label>	
                       </div>
                       <div class="otp_txt">
                           <img src="${authKey['url'] }" width="100px" alt="QR code" />
                       </div>
                       
                   </div>
                   <div class="otp_list">
                       <div class="otp_tit">
                         <div><span>3</span></div>
                           <label>구글 OTP 코드 입력</label>	
                       </div>
                       <div class="otp_txt">
                           <input class="form-control" type="text" name="user_code">
                       </div>
                   </div>
		        </div>
			</div>
			<button type="button" id="checkCodeBtn" class="btn btn-basic right mt-3">확인</button>
		</div>
		
	</form>
	<script>
		var isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) ? true : false;
		$(document).ready(function(){
			setTimeout(function(){
				swAlertExtand({title:'시간초과', text : '다시 로그인 후 인증해주세요.', icon:'info'}, function(){
					let frm = $('<form></form>');
					frm.attr('action', '/uat/uia/actionLogout.do');
					frm.attr('method', 'post');
					$(document.body).append(frm);
					frm.submit();
				});
			}, 3 * 60 * 1000);
			
			if(isMobile){
				$('#qr_code_view').hide();
			} else {
				$('#qr_code_view').show();
			}
			$('#checkCodeBtn').click(function(){
				let frm = document.getElementById("frm");
				if(frm.user_code.value == ''){
					swAlert('확인', 'OTP 코드를 입력하세요.', 'warning');
					return ;
				}
				callDataApi('/cmmn/com/cuc/checkGoogleOTPAuthCodeAjax.do', {'userCode':frm.user_code.value, 'encodedKey':frm.encodedKey.value}).then(function(result){
					console.log(result);
					if(result.result == 'success'){
						swAlertExtand({title:'확인', text : '인증 성공', icon:'success'}, function(){
							setCookie('googleOTPAuthAT','Y', 3);
							location.href = '/cmmn/adm/dashboard/dashboardMain.do';
						});
					} else {
						swAlert('실패', '인증 실패', 'error');
					}
				}).catch(function(result){
					console.log(result);
				});
				return false;
			});
		});
	</script>
</body>
</html>