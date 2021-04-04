/**
 * 관리자 > 공통코드 관리 javascript
 * */
$(document).ready(function(){
	/*
	 *	공통코드 등록 버튼 클릭 함수
	 */
	$('#comCodeRegistBtn').click(function(){
		swAlertConfirm('등록하시겠습니까?', {icon:'info', confirmButtonText:'등록'}, function(){
			let comCodeForm = document.getElementById("comCodeVO");
			if($('#checkCodeBoolean').val() == 'false'){
				Swal.fire('확인', '코드 중복확인을 해주세요.', 'warning');
	    		return false;
			}
			if(!fn_code_check('Y')){
				if(validateComCodeVO(comCodeForm)){
					comCodeForm.submit();
				} else {
					return false;
				}
			} else {
				Swal.fire('확인', '코드 중복확인을 해주세요.', 'warning');
				return false;
			}
		});
	});
	
	/*
	 *	공통코드 삭제 버튼 클릭 함수
	 */
	$('#comCodeDeleteBtn').click(function(){
		swAlertConfirm('삭제하시겠습니까?', {icon:'warning', confirmButtonText:'삭제'}, function(){
			let comCodeForm = document.getElementById("comCodeVO");
			let groupCode = comCodeForm.groupCode.value;
			if(groupCode != null && groupCode != ''){
				comCodeForm.action = contextPath + "/cmmn/adm/comCode/deleteComCode.do";				
				comCodeForm.submit();
			} else {
				return false;
			}
		})
	});
	
	/*
	 *	공통코드 수정 버튼 클릭 함수
	 */
	$('#comCodeUpdateBtn').click(function(){
		swAlertConfirm('수정하시겠습니까?', {icon:'info', confirmButtonText:'수정'}, function(){
			let comCodeForm = document.getElementById("comCodeVO");
			if(validateComCodeVO(comCodeForm)){
				comCodeForm.submit();
			} else {
				return false;
			}
		});
	});
	
});

/*
 * 공통코드 목록 조회 해당 페이지로 이동
 * @param : pageNo(페이지 번호) / 생략 가능
 */
function fnSelectComCodeList(pageNo){
	pageLoadingView('show');
	if(arguments.length == 1){
		//pageNo가 있으면 
		document.listForm.pageIndex.value = pageNo;
	    document.listForm.action = contextPath + "/cmmn/adm/comCode/comCodeMain.do";
	    document.listForm.submit();
	} else {
		//없으면 
		let searchForm = document.getElementById("hiddenSearchForm");
		searchForm.action = contextPath + "/cmmn/adm/comCode/comCodeMain.do";
		searchForm.submit();
	}
}

/*
 * 공통코드 상세 조회 페이지로 이동
 * @param : id(공통코드)
 */
function fnSelectComCode(id) {
	pageLoadingView('show');
    document.listForm.code.value = id;
    document.listForm.action = contextPath + "/cmmn/adm/comCode/comCodeModify.do";
    document.listForm.submit();
}