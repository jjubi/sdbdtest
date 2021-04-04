/**
 * 관리자 > 팝업 관리 javascript
 * */
var nowOrdr = "";
$(document).ready(function(){
	/*
	 * 토글버튼 있을 시 bootstrapToggle 사용
	 */
	if($('.bootstrap-toggle-btn.useAt').length > 0){
		$('.bootstrap-toggle-btn.useAt').bootstrapToggle({
		      on: '사용',
		      off: '사용안함'
		});
		
		$('.bootstrap-toggle-btn.useAt').change(function(){
			let useAt = $(this).prop('checked');
			let popupSeqNo = $(this).parents('.dd-item.kanban-item').data('popupSeqNo');
			if(useAt){
				//사용여부 Y로 변경
				updatePopupUseAt(popupSeqNo, "Y");
			} else {
				//사용여부 N으로 변경
				updatePopupUseAt(popupSeqNo, "N");
			}
		});
	}
	
	/*
	 * 팝업 목록 있을 경우 nestable 사용 설정
	 */
	if($('#popupList').length > 0){
		$('#popupList').nestable({
			maxDepth : 1,
			onDragStart : function(l, e){
				//순서 변경에 따른 ordr 변경하기
				let popupList = $('#popupList');
				let popupList_Json = popupList.nestable('serialize');
				nowOrdr = '';
				$.each(popupList_Json, function(i, v){
					if(i != 0){
						nowOrdr += '-';
					}
					nowOrdr += v.popupSeqNo;
				});
			},
			callback : function(l, e, p){
				updatePopupOrdr();
			}
		});
	}
	
	/*
	 * 팝업 유형 수정에 따른 Area 표출
	 */
	$('#popupTy').change(function(){
		let value = $(this).val();
		if(value == 'POP_SWAL'){
			$('.popupLcArea').hide();
			$('.popupLcArea').find('input').each(function(){
				$(this).val('');
			});
		} else {
			$('.popupLcArea').show();
		}
	});
	
	/*
	 * 최초 팝업 유형이 swal이면 위치 영역 숨기기 
	 */
	if($('#popupTy').val() == 'POP_SWAL'){
		$('.popupLcArea').hide();
	}
	
	/*
	 * 팝업 이미지 파일 선택 시 미리보기
	 */
	$('#file_0').change(function(e){
		let file = e.target.files[0];
		if($.fn.useExtCheck(['png', 'jpg', 'jpeg', 'gif', 'bmp'], $(this).val())){
			$('#preViewImageArea').fileImagePreView({"file":file});
		} else {
			$(this).val(null);
			return false;
		}
	});
	
	/*
	 * 팝업 등록 버튼 클릭
	 */
	$('#popupRegistBtn').click(function(){
		swAlertConfirm('등록하시겠습니까?', {icon:'info',confirmButtonText:'등록'}, function(){
			let popupForm = document.getElementById("popupVO");
			//url 형식 체크
			// /로 시작하는 경우(사이트 내 url) 제외
			if(popupForm.popupUrl.value.indexOf('/') != 0){
				if(!(popupForm.popupUrl.value.indexOf('http://') == 0 || popupForm.popupUrl.value.indexOf('https://') == 0)){
					swAlert('확인', 'URL은 http://, https://를 포함하여야 합니다.', 'warning');
					return false;
				}
			}
			//게시기간 체크
			if(!checkPeriod(popupForm.popupBgnde.value, popupForm.popupEndde.value)){
				swAlert('확인', '게시기간을 확인하세요.', 'warning');
				return false;
			}
			//첨부파일 체크
			if(popupForm.file_0.value == ''){
				swAlert('확인', '첨부파일은 필수입니다.', 'warning');
				return false;
			}
			if(validatePopupVO(popupForm)){
				popupForm.submit();
			} else {
				return false;
			}
		});
	});
	
	/*
	 * 팝업 수정 버튼 클릭
	 */
	$('#popupUpdateBtn').click(function(){
		swAlertConfirm('수정하시겠습니까?', {icon:'info',confirmButtonText:'수정'}, function(){
			let popupForm = document.getElementById("popupVO");
			//url 형식 체크
			// /로 시작하는 경우(사이트 내 url) 제외
			if(popupForm.popupUrl.value.indexOf('/') != 0){
				if(!(popupForm.popupUrl.value.indexOf('http://') == 0 || popupForm.popupUrl.value.indexOf('https://') == 0)){
					swAlert('확인', 'URL은 http://, https://를 포함하여야 합니다.', 'warning');
					return false;
				}
			}
			//게시기간 체크
			if(!checkPeriod(popupForm.popupBgnde.value, popupForm.popupEndde.value)){
				swAlert('확인', '게시기간을 확인하세요.', 'warning');
				return false;
			}
			
			if(validatePopupVO(popupForm)){
				popupForm.submit();
			} else {
				return false;
			}
		});
	});
	
	$('#popupDeleteBtn').click(function(){
		swAlertConfirm('삭제하시겠습니까?', {icon:'warning',confirmButtonText:'삭제'}, function(){
			let popupForm = document.getElementById("popupVO");
			popupForm.action = contextPath + "/cmmn/adm/popup/deletePopup.do";
			popupForm.submit();
		});
	});
})

/*
 * 팝업 목록 조회 해당 페이지로 이동
 * @param : pageNo(페이지 번호) / 생략 가능
 */
function fnSelectPopupList(pageNo){
	if(arguments.length == 1){
		//pageNo가 있으면 
		document.listForm.pageIndex.value = pageNo;
	    document.listForm.action = contextPath + "/cmmn/adm/popup/popupMain.do";
	    document.listForm.submit();
	} else {
		//없으면 
		let searchForm = document.getElementById("hiddenSearchForm");
		searchForm.action = contextPath + "/cmmn/adm/popup/popupMain.do";
		searchForm.submit();
	}
}

/*
 * 팝업 상세 조회 페이지로 이동
 * @param : id(공통코드)
 */
function fnSelectPopup(id) {
	document.listForm.popupSeqNo.value = id;
    document.listForm.action = contextPath + "/cmmn/adm/popup/popupModify.do";
    document.listForm.submit();
}

/*
 * 팝업 삭제
 * @param : id(팝업 ID)
 */
function fnDeletePopup(id){
	swAlertConfirm('삭제하시겠습니까?', {icon:'warning',confirmButtonText:'삭제'}, function(){
		document.listForm.popupSeqNo.value = id;
	    document.listForm.action = contextPath + "/cmmn/adm/popup/deletePopup.do";
	    document.listForm.submit();
	});
}

/*
 * 팝업 사용 여부 수정
 * @param : id(팝업 ID), useAt(사용여부)
 */
function updatePopupUseAt(id, useAt){
	$.ajax({
		url : contextPath + '/cmmn/adm/popup/updatePopupAjax.do',
		type : 'post',
		dataType : 'json',
		async : false,
		data : {'popupSeqNo' : id, 'useAt' : useAt, 'updateType' : 'useAt'},
		success : function(data){
			if(data.result == 'success'){
				console.log('사용여부변경 success');
			} else {
				swAlert('사용여부 변경 저장 실패. 관리자에게 문의해주세요.');	
			}
		}
	});
}

/*
 * 팝업 순서 변경 (사용중인 팝업만 해당)
 */
function updatePopupOrdr(){
	//순서 변경에 따른 ordr 변경하기
	let popupList = $('#popupList');
	let popupList_Json = popupList.nestable('serialize');
	let ordrData = '';
	$.each(popupList_Json, function(i, v){
		if(i != 0){
			ordrData += '-';
		}
		ordrData += v.popupSeqNo;
	});
	if(nowOrdr == ordrData){
		return ;
	}
	$.ajax({
		url : contextPath + '/cmmn/adm/popup/updatePopupAjax.do',
		type : 'post',
		dataType : 'json',
		data : {'popupOrdr' : ordrData, 'updateType' : 'ordr'},
		success : function(data){
			if(data.result == 'success'){
				console.log('순서변경 success');
			} else {
				swAlert('순서 변경 저장 실패. 관리자에게 문의해주세요.');	
			}
		}
	});
}







