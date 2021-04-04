/**
 * 관리자 > 시스템 구성 관리 javascript
 */
$(document).ready(function(){
	/*
	 * 시스템 구성 등록 버튼 클릭
	 */
	$('#sysCmptnRegistBtn').click(function(){
		swAlertConfirm('등록하시겠습니까?',{icon:'info',confirmButtonText:'등록'},function(){
			let sysCmptnForm = document.getElementById("sysCmptnVO");
			if($('#checkCodeBoolean').val() == 'false'){
				Swal.fire('확인', '코드 중복확인을 해주세요.', 'warning');
	    		return false;
			}
			if(!fn_code_check('Y')){
				if(!validateSysCmptnVO(sysCmptnForm)){
					return false;
				} else {
					sysCmptnForm.submit();
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
	$('#sysCmptnDeleteBtn').click(function(){
		swAlertConfirm('삭제하시겠습니까?',{icon:'warning',confirmButtonText:'삭제'},function(){
			let sysCmptnFrom = document.getElementById("sysCmptnVO");
			let sysCmptnCode = sysCmptnFrom.sysCmptnCode.value;
			if(sysCmptnCode != null && sysCmptnCode != ''){
				sysCmptnFrom.action = contextPath + "/cmmn/adm/sys/cmptn/deleteSysCmptn.do";				
				sysCmptnFrom.submit();
			} else {
				return false;
			}
		});
	});
	
	/*
	 *	공통코드 수정 버튼 클릭 함수
	 */
	$('#sysCmptnUpdateBtn').click(function(){
		swAlertConfirm('수정하시겠습니까?',{icon:'info',confirmButtonText:'수정'},function(){
			let sysCmptnFrom = document.getElementById("sysCmptnVO");
			if(!validateSysCmptnVO(sysCmptnFrom)){
				return false;
			} else {
				sysCmptnFrom.submit();
			}
		});
	});
});

/*
 * 게시판 목록 조회 해당 페이지로 이동
 * @param : pageNo(페이지 번호)
 */
function fnSelectSysCmptnList(pageNo){
	pageLoadingView('show');
	if(arguments.length == 1){
	    document.listForm.pageIndex.value = pageNo;
	    document.listForm.action = contextPath + "/cmmn/adm/sys/cmptn/sysCmptnMain.do";
	    document.listForm.submit();
	} else {
		let searchForm = document.getElementById("hiddenSearchForm");
		searchForm.action = contextPath + "/cmmn/adm/sys/cmptn/sysCmptnMain.do";
		searchForm.submit();
	}
}

/*
 * 게시판 상세 조회 해당 페이지로 이동
 * @param : id(게시판id)
 */
function fnSelectSysCmptn(id){
	pageLoadingView('show');
	document.listForm.sysCmptnCode.value = id;
	document.listForm.action = contextPath + "/cmmn/adm/sys/cmptn/sysCmptnModify.do";
    document.listForm.submit();
}