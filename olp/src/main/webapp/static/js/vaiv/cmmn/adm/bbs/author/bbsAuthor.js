/**
 * 관리자 > 게시판 권한 관리 javascript
 */
$(document).ready(function(){
	/*
	 * 게시판 권한 수정버튼 클릭
	 */
	$('#bbsAuthorUpdateBtn').click(function(){
		swAlertConfirm('수정하시겠습니까?', {icon:'info', confirmButtonText :'수정'}, function(){
			pageLoadingView('show');
			let bbsAuthorForm = document.getElementById("bbsAuthorVO");
			bbsAuthorForm.submit();
		});
	});
});
