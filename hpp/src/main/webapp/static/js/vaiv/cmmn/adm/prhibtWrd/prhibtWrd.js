/**
 * 관리자 > 금지 단어 관리 javascript
 * */
$(document).ready(function(){
	/*
	 * 등록영역 input 엔터 처리
	 */
	$('#insertKeyword').keypress(function(e){
		if(e.keyCode == 13){
			fnInsertPrhibtWrd();
			return false;
		}
	});
	
	/*
	 * 사용여부 수정
	 */
	$('#prhibtWrdTbody').on('change', 'input[type="checkbox"]', function(){
		let idx = $(this).val();
		let useAtVal = '';
		if($(this).prop('checked')){
			useAtVal = 'Y';
		} else {
			useAtVal = 'N';
		}
		let updateJson = {
			prhibtWrdSeqNo : idx,
			useAt : useAtVal
		};
		fnUpdatePrhibtWrd(updateJson);
	});
});

/*
 * 금지단어 목록 조회 해당 페이지로 이동
 * @param : pageNo(페이지 번호)
 */
function fnSelectPrhibtWrdList(pageNo){
	pageLoadingView('show');
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = contextPath + "/cmmn/adm/prhibtWrd/prhibtWrdProcMain.do";
    document.listForm.submit();
}

/*
 * 금지 단어 등록
 */
function fnInsertPrhibtWrd(){
	let pwrd = $('#insertKeyword').val();
	if(pwrd == null || pwrd == '' || pwrd == undefined){
		swAlert('확인', '금지단어를 입력하세요.', 'warning');
		return false;
	}
	$.ajax({
		url : contextPath + '/cmmn/adm/prhibtWrd/insertPrhibtWrdAjax.do',
		type : 'post',
		dataType : 'json',
		data : {"prhibtWrd" : pwrd},
		success : function(data) {
			if(data.result == 'success'){
				swAlertConfirm('금지단어가 등록되었습니다.',{icon:'success',showCancelButton:false}, function(){
					location.reload();
				});
			} else if(data.result == 'dup') {
				swAlert('확인', '이미 등록된 금지단어 입니다.', 'warning');
			} else {
				swAlert('확인', '금지 단어 등록 중 오류가 발생했습니다. 관리자에게 문의하세요.', 'error');
			}
		}, error : function(status){
			swAlert('확인', '금지 단어 등록 중 오류가 발생했습니다. 관리자에게 문의하세요.', 'error');
		}
	});
}

/*
 * 수정 Swal 표출
 */
function fnSelectPrhibtWrd(seq){
	let modifyWrd = $('#prhibtWrdTbody > tr.prhibtTr'+seq).find('td:eq(1)').text();
	swAlertInput('text', '금지단어 수정', {
		inputValue:modifyWrd,
		inputAttributes:{
			autocapitalize:'off'
		},
  		confirmButtonText: '수정',
  		showLoaderOnConfirm: true
	}, function(result){
		if(result.value == ''){
			swAlertConfirm('수정할 단어를 입력하세요.', {showCancelButton:false}, function(){
				fnSelectPrhibtWrd(seq);
			});
		} else {
    		let updateJson = {
   				prhibtWrdSeqNo : seq,
   				prhibtWrd : result.value
   			}; 
    		fnUpdatePrhibtWrd(updateJson);
		}
	});
}

/*
 * 금지 단어 수정
 */
function fnUpdatePrhibtWrd(updateJson){
	$.ajax({
		url : contextPath + '/cmmn/adm/prhibtWrd/updatePrhibtWrdAjax.do',
		type : 'post',
		dataType : 'json',
		data : updateJson,
		success : function(data) {
			if(data.result == 'success'){
				if(updateJson.prhibtWrd != undefined){
					swAlert('확인', '금지 단어가 수정되었습니다.', 'success');
					fnSetUpdatePrhibtWrd(data.prhibtWrdVO);
				} 
			} else if(data.result == 'dup'){
				swAlert('확인', '이미 등록된 금지단어 입니다.', 'warning');
			} else {
				swAlert('확인', '금지 단어 수정 중 오류가 발생했습니다. 관리자에게 문의하세요.', 'error');
			}
		},  error : function(status){
			swAlert('확인', '금지 단어 수정 중 오류가 발생했습니다. 관리자에게 문의하세요.', 'error');
		}
	});
}

/*
 * 금지 단어 수정 후 목록의 단어 수정
 */
function fnSetUpdatePrhibtWrd(vo){
	let trgtEl = $('#prhibtWrdTbody > tr.prhibtTr'+vo.prhibtWrdSeqNo);
	trgtEl.find('td:eq(1)').text(vo.prhibtWrd);
}

/*
 * 금지 단어 삭제
 */
function fnDeletePrhibtWrd(id){
	//Swal로 수정하기
	swAlertConfirm('삭제하시겠습니까?',{icon:'warning',confirmButtonText:'삭제'}, function(){
		callAjaxExtend({
			url : contextPath + '/cmmn/adm/prhibtWrd/deletePrhibtWrdAjax.do',
			dataType : 'json',
			data : {"prhibtWrdSeqNo" : id},
			success : function(data) {
				if(data.result == 'success'){
					swAlertConfirm('금지단어가 삭제되었습니다.',{icon:'success',showCancelButton:false},function(){
						location.reload();
					});
				} else {
					swAlert('확인', '금지 단어 수정 중 오류가 발생했습니다. 관리자에게 문의하세요.', 'error');
				}
			},  error : function(status){
				swAlert('확인', '금지 단어 수정 중 오류가 발생했습니다. 관리자에게 문의하세요.', 'error');
			}
		});
	});
}

/*
 * 금지단어 엑셀 업로드 등록
 */ 
function fnExcelDataUpload(){
	swAlertExtand({
		title:'금지단어 엑셀 업로드',
		text : '파일 업로드 시 중복 데이터 제외하고 등록 됩니다.',
		input:'file', 
		inputAttributes: {
			'accept': '*/*'
		},
		showCancelButton: true,
  		showDenyButton: true,
  		confirmButtonText: '업로드',
  		denyButtonText: '양식 다운로드'
	},function(result){
		if($.fn.useExtCheck(["xls", "xlsx"], result.value.name)){
			pageLoadingView('show');
			let formData = new FormData();
			formData.append("file", $('.swal2-content > input[type="file"]')[0].files[0]);
			//파일 업로드
			uploadData(contextPath + '/cmmn/adm/prhibtWrd/insertPrhibtWrdExcelDataAjax.do', formData, function(status, data){
				if(status == 'success'){
					if(data.result == 'success'){
						location.reload();
					} else {
						swAlert('확인', '금지 단어 엑셀 업로드 등록 중 오류가 발생했습니다. 관리자에게 문의하세요.', 'error');	
					}
					pageLoadingView('hide');
				} else {
					swAlert('확인', '금지 단어 엑셀 업로드 등록 중 오류가 발생했습니다. 관리자에게 문의하세요.', 'error');
					pageLoadingView('hide');
				}
			});
		}
	},function(result){
		location.href="/cmm/fms/exampleExcelFileDown.do?fileName=prhibtWrd_example.xlsx";
	});
}




