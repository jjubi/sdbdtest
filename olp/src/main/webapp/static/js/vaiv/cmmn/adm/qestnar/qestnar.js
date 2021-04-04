/**
 * 관리자 > 설문조사 관리 javascript
 * */
$(document).ready(function(){
	/*
	 * 설문조사 대상 설정에 따른 대상목록 영역 표출구분
	 */
	$('input[name="qestnarTrget"]').change(function(){
		if($(this).val() == 'TARGET'){
			$('#qestnarTrgetListDiv').show();
		} else {
			$('#qestnarTrgetListDiv').hide();
			$('#qestnarTrgetListDiv').find('.boxOn').empty();
		}
	});
	
	/*
	 * 설문조사 대상 삭제 버튼 클릭
	 */
	$('.boxOn').on('click', 'p .target_close', function(){
		$(this).parents('p').remove();
	});
	
	/*
	 * 설문조사 설정 등록 버튼 클릭
	 */
	$('#qestnarRegistBtn').click(function(){
		swAlertConfirm('등록하시겠습니까?', {icon:'info',confirmButtonText:'등록'}, function(){
			let qestnarForm = document.getElementById("qestnarVO");
			
			let pridSt = $('#qestnarBgnde').val();
			let pridEd = $('#qestnarEndde').val();
			
			if(pridSt == '' || pridEd == ''){
				swAlert('확인', '게시기간을 확인하세요.', 'warning');
				return false;
			} else if(!moment(pridEd).isAfter(pridSt, 'day')) {
				swAlert('확인', '게시기간을 확인하세요.', 'warning');
				return false;
			}

			if($('input[name="qestnarTrget"]:checked').val() != 'ALL'){
				if($('#qestnarTrgetListDiv').find('.boxOn > p').length == 0){
					swAlert('확인', '설문조사 대상은 최소 1명이상입니다.', 'warning');
					return false;
				} else {
					let trgetList = '';
					
					$('#qestnarTrgetListDiv').find('.boxOn > p').each(function(i, v){
						if(trgetList != ''){
							trgetList += ';';
						}
						trgetList += $(v).find('input[name="userId"]').val() + '-' + $(v).find('input[name="userNm"]').val(); 
					});
					$('#qestnarTrgetListStr').val(trgetList);
				}
			}
			
			//금지 단어 체크
			//머리말
			if(!checkPrhibtWrd(qestnarForm.qestnarPrface.value)){
				return false;
			}
			//맺음말
			if(!checkPrhibtWrd(qestnarForm.qestnarCnclsn.value)){
				return false;
			}
			
			if(!validateQestnarVO(qestnarForm)){
				return false;
			} else {
				qestnarForm.submit();
			}
		});
	});
	
	/*
	 * 설문조사 설정 수정 버튼 클릭
	 */
	$('#qestnarUpdateBtn').click(function(){
		swAlertConfirm('수정하시겠습니까?', {icon:'info',confirmButtonText:'수정'}, function(){
			let qestnarForm = document.getElementById("qestnarVO");
			let pridSt = $('#qestnarBgnde').val();
			let pridEd = $('#qestnarEndde').val();
			
			if(pridSt == '' || pridEd == ''){
				swAlert('확인', '게시기간을 확인하세요.', 'warning');
				return false;
			} else if(!moment(pridEd).isAfter(pridSt, 'day')) {
				swAlert('확인', '게시기간을 확인하세요.', 'warning');
				return false;
			}
			
			if($('input[name="qestnarTrget"]:checked').val() != 'ALL'){
				if($('#qestnarTrgetListDiv').find('.boxOn > p').length == 0){
					swAlert('확인', '설문조사 대상은 최소 1명이상입니다.', 'warning');
					return false;
				} else {
					let trgetList = '';
					
					$('#qestnarTrgetListDiv').find('.boxOn > p').each(function(i, v){
						if(trgetList != ''){
							trgetList += ';';
						}
						trgetList += $(v).find('input[name="userId"]').val() + '-' + $(v).find('input[name="userNm"]').val(); 
					});
					$('#qestnarTrgetListStr').val(trgetList);
				}
			}
			
			//금지 단어 체크
			//머리말
			if(!checkPrhibtWrd(qestnarForm.qestnarPrface.value)){
				return false;
			}
			//맺음말
			if(!checkPrhibtWrd(qestnarForm.qestnarCnclsn.value)){
				return false;
			}
			
			if(!validateQestnarVO(qestnarForm)){
				return false;
			} else {
				qestnarForm.submit();
			}
		});
	});
	
	/*
	 * 설문조사 설정 삭제 버튼 클릭
	 */
	$('#qestnarDeleteBtn').click(function(){
		swAlertConfirm('삭제하시겠습니까?', {icon:'warning',confirmButtonText:'삭제'}, function(){
			let qestnarForm = document.getElementById("qestnarVO");
			let qestnarSeqNoVal = qestnarForm.qestnarSeqNo.value;
			if(qestnarSeqNoVal != null && qestnarSeqNoVal != ''){
				qestnarForm.action = contextPath + "/cmmn/adm/qestnar/deleteQestnar.do";				
				qestnarForm.submit();
			} else {
				return false;
			}
		});
	});
});

/*
 * 설문조사 목록 조회 해당 페이지로 이동
 * @param : pageNo(페이지 번호) / 생략 가능
 */
function fnSelectQestnarList(pageNo){
	pageLoadingView('show');
	if(arguments.length == 1){
		//pageNo가 있으면 
		document.listForm.pageIndex.value = pageNo;
	    document.listForm.action = contextPath + "/cmmn/adm/qestnar/qestnarMain.do";
	    document.listForm.submit();
	} else {
		//없으면 
		let searchForm = document.getElementById("hiddenSearchForm");
		searchForm.action = contextPath + "/cmmn/adm/qestnar/qestnarMain.do";
		searchForm.submit();
	}
}

/*
 * 설문조사 상세 조회 페이지로 이동
 * @param : id(공통코드)
 */
function fnSelectQestnar(id) {
	pageLoadingView('show');
    document.listForm.qestnarSeqNo.value = id;
    document.listForm.action = contextPath + "/cmmn/adm/qestnar/qestnarModify.do";
    document.listForm.submit();
}

/*
 * 설문조사 문항 관리 페이지로 이동
 */
function fnSelectQestnarQestn(id){
	pageLoadingView('show');
	document.listForm.qestnarSeqNo.value = id;
	document.listForm.action = contextPath + "/cmmn/adm/qestnar/qestn/qestnMain.do";
    document.listForm.submit();
}

function fnSelectQestnarResult(id){
	pageLoadingView('show');
	document.listForm.qestnarSeqNo.value = id;
	document.listForm.action = contextPath + "/cmmn/adm/qestnar/result/qestnarResultMain.do";
    document.listForm.submit();
}