/**
 * 설문 문항 View javascript
 */

$(document).ready(function(){
	$('footer').remove();
	$('#layoutSidenav_nav').remove();
	$('nav.topnavbar').remove();
	
	if($('.fileArea').length > 0){
		$('#qestnarQestnForm').prepend('<script type="text/javascript" src="'+contextPath+'/static/js/egovframework/com/fms/EgovMultiFile.js"></script>');
		let fileSelectorJson = {};
		$('.fileArea').each(function(i, v){
			let maxFileNum = $(this).find('input[name="atchFilePermMxmmCnt"]').val();
			let uploader = $(this).find('input[id^="egovComFileUploader"]');
			let fileList = $(this).find('div[id^="egovComFileList"]');
			fileSelectorJson[i] = new MultiSelector(fileList[0], maxFileNum); 
			fileSelectorJson[i].addElement(uploader[0]);
//			if($(".file_add").length > 0){
//				fileSelectorJson[i].setCurrentCount($(".file_add").length + 1);
//			}
			$(this).find('.file_add .btn-danger').on('click', function(i, v){
				$(this).parent('.file_add').remove();
			});
		});
		
	}
	
	$('input[name^="qestnAnswer"]').change(function(){
		let type = $(this).attr('type');
		if(type == 'radio') {
			$('.qestn_radio1').removeClass('clicked');
			$(this).parent('.qestn_radio1').addClass('clicked');
		} else if(type == 'checkbox'){
			$(this).parent('.qestn_radio1').toggleClass('clicked');
		}
		if(type == 'radio' || type == 'checkbox'){
			if($(this).hasClass('etc')){
				$(this).parent('.qestn_radio1').append('<input type="text" class="qestn_antx" name="qestnarAnswerEtc" value="">');
			} else {
				if($(this).parents('.qestn').find('input[name="qestnarAnswerEtc"]').length > 0){
					$(this).parents('.qestn').find('input[name="qestnarAnswerEtc"]').remove();
				}
			}
		}
	});
});

function qestnarResultValue(ele){
	let resultValueEl = $('[name^="qestnAnswer"]').not('input[name="qestnAnswer"]');
	let qestnTy = $('input[name="qestnType"]').val();
	if(arguments.length == 1){
		resultValueEl = ele.find('[name^="qestnAnswer"]');
		qestnTy = ele.find('input[name="qestnType"]').val();
	}
	let resultValue = '';
	if(qestnTy == 'text' || qestnTy == 'textarea' || qestnTy == 'select' || qestnTy == 'file'){
		resultValue = $.trim(resultValueEl.val());		
	} else if(qestnTy == 'radio' || qestnTy == 'checkbox'){
		$.each(resultValueEl, function(i, v){
			if($(this).prop('checked')){
				if(resultValue != ''){
					resultValue += ',';
				}
				resultValue += $(this).val();
				
				if($(this).hasClass('etc')){
					resultValue += ":" + resultValueEl.next().val();
				}
			}
			
		});
	}
	
	return resultValue;
}

function qestnarQestnNextFn(link){
	let resultValue = qestnarResultValue();
	let qestnRequireAt = $('input[name="qestnRequire"]').val();
	if(qestnRequireAt == 'Y'){
		if(resultValue == null || resultValue == '' || resultValue == undefined){
			swAlert('필수', '필수 답변입니다. 확인해주세요.', 'warning');
			return ;
		}
	} 
	
	$('#qestnarQestnForm').find('input[name="qestnAnswer"]').val(resultValue);
	let frm = document.getElementById('qestnarQestnForm');
	frm.action = link;
	frm.method = 'post';
	frm.submit();
}


function qestnarQestnPgeNextFn(link){
	let vaildateCheck = false;
	let qestnAnswerStr = "";
	$('.qestn').each(function(i, v){
		let resultValue = qestnarResultValue($(this));
		let qestnRequireAt = $(this).find('input[name="qestnRequire"]').val();
		if(qestnRequireAt == 'Y'){
			if(resultValue == null || resultValue == '' || resultValue == undefined){
				vaildateCheck = true;
				return false;
			}
		} 
		if(qestnAnswerStr != ''){
			qestnAnswerStr += ';';
		}
		qestnAnswerStr += resultValue;
	});
	
	if(vaildateCheck){
		swAlert('필수', '필수 답변이 있습니다. 확인해주세요.', 'warning');
		return false;
	}
	
	$('#qestnarQestnForm').find('input[name="qestnAnswer"]').val(qestnAnswerStr);
	
	let frm = document.getElementById('qestnarQestnForm');
	frm.action = link;
	frm.method = 'post';
	frm.submit();
}



