<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="CSM Basic">
	<!-- Title -->
	<title>CMS Basic</title>

	<!-- Libs CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/sweetalert2/sweetalert2-10.9.0.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/vaiv/cmmn/adm/login/login.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/bootstrap/bootstrap_custom.css"/>
	
	<script src="${pageContext.request.contextPath }/static/js/plugins/jquery/jquery-3.5.1.min.js"></script>
	
	<script>
		function init(msg){
			if(msg != ''){
				Swal.fire('확인', msg, 'warning');
			}
		}
		
		$(document).ready(function(){
			$('#loginForm').submit(function(){
				if($(this).find('#id').val() == ''){
					Swal.fire('확인','아이디를 입력하세요.','info');
					return false;
				}
				if($(this).find('#password').val() == ''){
					Swal.fire('확인','비밀번호를 입력하세요.','info');
					return false;
				}
			});		
		});
		
	</script>
</head>
<body onload="init('${not empty message ? message : '' }');">
    <div class="bg-gradient-primary cmsLogin">
	    <div class="container">
	        <!-- Outer Row -->
	        <div class="row justify-content-center">
	            <div class="col-xl-10 col-lg-12 col-md-9">
	                <div class="card shadow-lg">
	                    <div class="card-body p-0">
	                        <!-- Nested Row within Card Body -->
	                        <form id="loginForm" action="${pageContext.request.contextPath }/uat/uia/actionLogin.do" method="post">
	                        	<input type="hidden" name="userSe" value="USR">
		                        <div class="row">
		                            <div class="col-lg-6 text-center Company">
		                                <div class="company_logo">
		                                    <img src="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/company_logo.png" alt="VAIV COMPANY">
		                                </div>
		                                <h2 class="h2 color-gray">바이브컴퍼니CMS</h2>
		                                <p>관리자 <span class="color-blue">로그인</span></p>
		                            </div>
		                            <div class="col-lg-6">
		                                <div class="p-5">
	                                       <div class="form-group">
	                                           <input type="text" id="id" name="id" class="form-control form-control-user" aria-describedby="id" placeholder="ID">
	                                       </div>
	                                       <div class="form-group">
	                                           <input type="password" id="password" name="password" class="form-control form-control-user passCapsCheck" placeholder="Password">
	                                       </div>
	                                       <button type="submit" class="btn btn-basic btn-user btn-block">로그인</button>
	                                       <div class="user-page">
												<a href="#">회원가입</a>
												<a href="#">아이디/비밀번호 찾기</a>
											</div>
		                                </div>
		                            </div>
		                        </div>
		                    </form>
	                    </div>
	                </div>
	                <div class="logintxt-footer">
	                    <p>본 페이지는 사이트 관리자만이 사용하실 수 있습니다.<br>
	                      불법적인 로그인 시도를 할 경우 법적인 제재를 받을 수 있습니다.<br><br>
	                    Copyright © VAIV COMPANY. All Rights Reserved. SERVED</p>
	                </div>
	            </div>
	        </div>
	    </div>
    </div>

    <!-- JAVASCRIPT : s-->
    <!-- Libs JS -->
    <script src="${pageContext.request.contextPath }/static/js/plugins/jquery/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/plugins/bootstrap/bootstrap-4.5.2.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/plugins/sweetalert2/sweetalert2-10.9.0.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/login.js"></script>
</body>
</html>

