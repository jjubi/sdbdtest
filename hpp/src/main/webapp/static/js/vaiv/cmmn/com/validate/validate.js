/**
 * validate 호출 function
 * @author kmk
 */

const regexps = {};
regexps.data = {
	"password_regexp" : /^(?=.*[A-Za-z])(?=.*\d)(?=.*[\{\}\[\]\/?.,;:|\)*~`!^\-+<>@\#$%&\\\=\(\'\"_])[A-Za-z\d\{\}\[\]\/?.,;:|\)*~`!^\-+<>@\#$%&\\\=\(\'\"_]{8,16}$/,
	"nickname_regexp" : /^([ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\*]){3,10}$/,
	"email_regexp" : /^[0-9a-zA-Z\-\_\.]([-_\.]?[0-9a-zA-Z\-\_\.])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i,
	"hp_regexp" : /^\d{3}-\d{3,4}-\d{4}$/,
	"tel_regexp" : /^\d{2,3}-\d{3,4}-\d{4}$/
};

regexps.fails = {
	"password_regexp" : "비밀번호는 8~16자리의 특수문자를 포함하여 입력해주세요.",
	"nickname_regexp" : "닉네임은 한글 및 영문, 숫자로 구성된 3~10자리를 입력해주세요",
	"email_regexp" : "형식에 맞지 않는 이메일주소입니다. 다시 입력해주세요.",
	"hp_regexp" : "형식에 맞지 않는 핸드폰번호입니다. 다시 입력해주세요",
	"tel_regexp" : "형식에 맞지 않는 전화번호입니다. 다시 입력해주세요."
}

/**
 * 태그에
 * 
 * data-validate="####_required" 형태로 입력
 * 
 * #### ex) password, nickname, email, hp, tel ...
 * 
 * title="" => required 속성일 경우 입력 요청하는 title
 * 
 * data-failmessage="" 데이터 validation 실패 시 나오는 메세지. 없을경우 상단의 regexps.fails default 문구가 출력된다.
 *   
 */

validate = function(form){
	var returnBool = true;
	$('#' + form).find('input, textarea, select').each(function(i, item){
		param = $(this).data('validate');
		
		if(param){
			if(param.indexOf('_required') > -1){
				if($(this).attr('type') == 'radio'){
					radioName = $(this).attr('name');
					if(!$('input[name='+radioName+']:checked').val()){
						var title = $(this).attr('title');
						$(this).focus();
						if(title){
							alert(title + "을/를 선택해주세요.");
							returnBool = false;
						}else{
							alert('라디오버튼을 선택해주세요.');
							returnBool = false;
						}
						return false;
					}
				}else if($(this).attr('type') == 'checkbox'){
					chkboxName = $(this).attr('name');
					if($('input[name='+chkboxName+']').is(":checked") == false){
						var title = $(this).attr('title');
						$(this).focus();
						if(title){
							alert(title + "을/를 선택해주세요.");
							returnBool = false;
						}else{
							alert('체크박스를 선택해주세요.');
							returnBool = false;
						}
						return false;
					}
				}else if(!$(this).val()){
					var title = $(this).attr('title');
					$(this).focus();
					if(title){
						alert(title + "을/를 입력해주세요.");
						returnBool = false;
					}else{
						alert('해당 위치의 값을 입력해주세요.');
						returnBool = false;
					}
					return false;
				}
			}
			
			var jsonParam = param.replace('_required', '')+"_regexp";
			
			if(param != ""){
				pattern = new RegExp(regexps.data[jsonParam]);
				if(!pattern.test($(this).val())){
					var failmsg = "";
					if($(this).data('failmessage')){
						failmsg = $(this).data('failmessage');
					}else{
						failmsg = regexps.fails[jsonParam];
					}
					alert(failmsg);
					returnBool = false;
					return false;
				}
			}
		}
	});
	
	return returnBool;
}