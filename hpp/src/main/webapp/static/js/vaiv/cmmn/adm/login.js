$(document).ready(function(){
//	$('#showPw').click(function(){
//		let pwEl = $('#password');
//		if(pwEl.attr('type') == 'password'){
//			$(this).attr('class', 'fas fa-eye-slash');
//			pwEl.attr('type', 'text');
//		} else {
//			$(this).attr('class', 'fas fa-eye');
//			pwEl.attr('type', 'password');
//		}
//	});
	
	$('#loginForm').submit(function(){
		if($('#id').val() == ''){
			Swal.fire('확인', 'ID를 입력하세요.', 'warning');
			return false;
		}
		if($('#password').val() == ''){
			Swal.fire('확인', '비밀번호를 입력하세요.', 'warning');
			return false;
		}
		
	});
	
	$(".passCapsCheck").keypress(function(e){
		let keyCode = 0;
		let shiftKey = false;
		keyCode = e.keyCode;
		shiftKey = e.shiftKey
		//shift를 누르지 않은 상태에서 대문자
		if(((keyCode >= 65 && keyCode <=90) && !shiftKey) || ((keyCode >= 97 && keyCode <= 112) && shiftKey)){
			$(this).tooltip({
				placement : 'bottom',
				title : 'CapsLock이 켜져있습니다.'
			}).tooltip('show');
		} else {
			$(this).tooltip('hide');
		}
	});
	
});