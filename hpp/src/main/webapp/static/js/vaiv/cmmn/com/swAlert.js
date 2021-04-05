/**
 * SweetAlert 커스터마이징 사용 javascript
 */
/*
 * 사용자 정의 swAlert
 * @param : swOption - SweetAlert 옵션
 * @param : confirmFn - confirm버튼 클릭 callback 함수
 * @param : denyFn - deny버튼 클릭 callback 함수
 * @param : cancelFn - cancel버튼 클릭 callback 함수
 */
let swAlertCloseFocusElement = '';
function swAlertExtand(swOption, confirmFn, denyFn, cancelFn){
	if(swOption.closeFocusEl){
		swAlertCloseFocusElement = swOption.closeFocusEl;
		delete swOption.closeFocusEl;
	}
	
	let defaultOption = {
			title : '',
			confirmButtonText:'확인',
			cancelButtonText:'취소',
			allowOutsideClick : false,
			didClose : function(e){
				if($(swAlertCloseFocusElement).length > 0){
					$(swAlertCloseFocusElement).focus();
					swAlertCloseFocusElement = '';
					return false;
				} else {
					return false;
				}
			}
	};
	let swOptions = $.extend(defaultOption, swOption || {});
	
	let isConfirmFn = jQuery.isFunction(confirmFn);
	let isDenyFn = jQuery.isFunction(denyFn);
	let isCancelFn = jQuery.isFunction(cancelFn);
	
	let customSwAlert = Swal.mixin(swOptions);
	
	customSwAlert.fire().then(function(result) {
		if(result.isConfirmed){
			if(isConfirmFn){
				confirmFn(result);
			} else {
				return false;
			}
		} else if(result.isDenied){
			if(isDenyFn){
				denyFn(result);
			} else {
				return false;
			}
		} else if(result.isDismissed){
			if(isCancelFn){
				cancelFn(result);
			} else {
				return false;
			}
		} else {
			return false;
		}
	});
}

/*
 * 기본 swAlert
 * @param : title(제목)
 * @param : message(내용 - text)
 * @param : icon(아이콘) - warning, error, success, info, question
 */
function swAlert(title, message, icon, closeFocusEl){
	let aLen = arguments.length; 
	if(aLen == 1) {
		swAlertExtand({title:title});
	} else if(aLen == 2) {
		swAlertExtand({title:title, html:message});
	} else if(aLen == 3) {
		swAlertExtand({title:title, html:message, icon:icon});
	} else if(aLen == 4) {
		swAlertExtand({title:title, html:message, icon:icon, closeFocusEl:closeFocusEl});
	} else {
		swAlertExtand({});
	}
}

/*
 * 2버튼 (confirm action) swAlert
 * @param : title(제목)
 * @param : options - https://sweetalert2.github.io/#configuration 참조
 * @param : confirmFn - confirm클릭 시 실행되는 함수
 */
function swAlertConfirm(title, options, confirmFn){
	let aLen = arguments.length; 
	let defaultOptions = {
			title : title,
			showCancelButton : true
	}

	let swOptions = $.extend(defaultOptions, options || {});
	if(aLen == 3){
		swAlertExtand(swOptions, confirmFn);
	}
}

/*
 * 알림용(Toast) swAlert
 * @param : title(제목)
 * @param : message(내용 - text)
 * @param : icon(아이콘) - 'warning', 'error', 'success', 'info', 'question'
 * @param : position -  'top', 'top-start', 'top-end', 'center', 'center-start', 'center-end', 'bottom', 'bottom-start', 'bottom-end'
 * @param : timer - 밀리초 (default : 1500) 
 */
function swAlertToast(title, message, icon, position, timer){
	let aLen = arguments.length; 
	let defaultToastOptions = {
			toast : true,
			position : 'center',
			showConfirmButton : false,
			timer : 1500,
			timerProgressBar : true,
			allowOutsideClick : true
	}
	
	if(aLen == 1) {
		defaultToastOptions.title = title;
	} else if(aLen == 2) {
		defaultToastOptions.title = title;
		defaultToastOptions.text = message;
	} else if(aLen == 3) {
		defaultToastOptions.title = title;
		defaultToastOptions.text = message;
		defaultToastOptions.icon = icon;
	} else if(aLen == 4) {
		defaultToastOptions.title = title;
		defaultToastOptions.text = message;
		defaultToastOptions.icon = icon;
		defaultToastOptions.position = position;
	} else if(aLen == 5) {
		defaultToastOptions.title = title;
		defaultToastOptions.text = message;
		defaultToastOptions.icon = icon;
		defaultToastOptions.position = position;
		defaultToastOptions.timer = timer;
	}
	
	swAlertExtand(defaultToastOptions);
}

/*
 * input swAlert
 * @param : inputType -  text, email, password, number, tel, range, textarea, select, radio, checkbox, file, url.
 * @param : message - 제목
 * @param : options - https://sweetalert2.github.io/#configuration 참조
 * @param : confirmFn - confirm클릭 시 실행되는 함수
 */
function swAlertInput(inputType, message, options, confirmFn){
	let aLen = arguments.length; 
	let defaultInputOptions = {
			input : 'text',
			showCancelButton : true,
			inputPlaceholder : '값을 입력하세요.',
	}

	let inputOptions = {};
	inputOptions.input = inputType;
	
	if(message != ''){
		inputOptions.text = message;
	}
	
	if(inputType == 'url'){
		inputOptions.validationMessage = '유효하지 않은 URL입니다.';
	} else if(inputType == 'email'){
		inputOptions.validationMessage = '유효하지 않은 이메일입니다.';
	}
	
	inputOptions = $.extend(inputOptions, options || {});
	inputOptions = $.extend(defaultInputOptions, inputOptions || {});
	
	if(aLen == 4){
		swAlertExtand(defaultInputOptions, confirmFn);
	}
}

/*
 * image swAlert
 * @param : title(제목)
 * @param : message(내용 - text)
 * @param : imageUrl - image 호출 url
 * @param : options - https://sweetalert2.github.io/#configuration 참조
 */
function swAlertImage(title, message, imageUrl, options){
	let aLen = arguments.length; 
	let defaultImageOptions = {
			imageHeight : 150,
			imageWidth : 150,
			imageAlt : '이미지'
	}
	
	if(aLen > 2){
		defaultImageOptions.imageUrl = imageUrl;
		defaultImageOptions = $.extend(defaultImageOptions, options || {});
		swAlertExtand(defaultImageOptions);
	} else if(aLen == 1){
		swAlert(title);
	} else {
		swAlert(title, message);
	}
}










