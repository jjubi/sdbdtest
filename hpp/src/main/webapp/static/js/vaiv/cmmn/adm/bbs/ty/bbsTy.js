/**
 * 관리자 > 게시판 관리 javascript
 */
$(document).ready(function(){
	/*
	 * 검색버튼 클릭 시 페이지 번호 1로 초기화
	 */
	$('.searchBtn').click(function(){
		document.listForm.pageIndex.value = 1;
	});
	
	/*
	 * 게시판 유형 등록 버튼 클릭
	 */
	$('#bbsTyRegistBtn').click(function(){
		swAlertConfirm('등록하시겠습니까?', {icon:'info',confirmButtonText:'등록'}, function(){
			let bbsTyForm = document.getElementById("bbsTyVO");
			let bbsTyCodeVal = bbsTyForm.bbsTyCode.value;
			//중복확인
			if(fnCheckCodeDup(bbsTyCodeVal)){
				Swal.fire('확인', '게시판 유형 코드가 중복입니다.', 'warning');
				return false;
			} else {
				if(validateBbsTyVO(bbsTyForm)){
					pageLoadingView('show');
					bbsTyForm.submit();
				} else {
					return false;
				}
			}
		});
	});
	
	/*
	 * 게시판 유형 삭제 버튼 클릭
	 */
	$('#bbsTyDeleteBtn').click(function(){
		swAlertConfirm('삭제하시겠습니까?', {icon:'warning',confirmButtonText:'삭제'}, function(){
			let bbsTyForm = document.getElementById("bbsTyVO");
			let bbsTyIdVal = bbsTyForm.bbsTyId.value;
			if(bbsTyIdVal != null && bbsTyIdVal != ''){
				pageLoadingView('show');
				bbsTyForm.action = contextPath + "/cmmn/adm/bbs/ty/deleteBbsTy.do";				
				bbsTyForm.submit();
			} else {
				return false;
			}
		});
	});
	
	/*
	 * 게시판 유형 수정 버튼 클릭
	 */
	$('#bbsTyUpdateBtn').click(function(){
		swAlertConfirm('수정하시겠습니까?', {icon:'info',confirmButtonText:'수정'}, function(){
			let bbsTyForm = document.getElementById("bbsTyVO");
			let bbsTyCodeVal = bbsTyForm.bbsTyCode.value;
			let bbsTyIdVal = bbsTyForm.bbsTyId.value;
			//중복확인
			if(fnCheckCodeDup(bbsTyCodeVal, bbsTyIdVal)){
				Swal.fire('확인', '게시판 유형 코드가 중복입니다.', 'warning');
				return false;
			} else {
				if(validateBbsTyVO(bbsTyForm)){
					pageLoadingView('show');
					bbsTyForm.submit();
				} else {
					return false;
				}
			}
		});
	});
});

/*
 *  게시판 유형 코드 중복 확인
 *  @param : bbsTyCode(게시판 유형 코드) / bbsTyId(게시판 유형 아이디) 
 *	중복이면 return true
 *	중복이 아니면 return false
 */
function fnCheckCodeDup(bbsTyCode, bbsTyId){
	let data = {};
	if(arguments.length == 1){
		data.bbsTyCode = bbsTyCode;
	} else if(arguments.length == 2){
		data.bbsTyCode = bbsTyCode;
		data.bbsTyId = bbsTyId;
	} else {
		return true;
	}
	
	let returnVal = false;
	$.ajax({
		url : contextPath + '/cmmn/adm/bbs/ty/checkBbsTyCodeAjax.do',
		type : 'post',
		async : false,
		dataType : 'json',
		data : data,
		success : function(data){
			if(data.result == 'success'){
				returnVal = false;
			} else {
				returnVal = true;
			}
		}, error : function(error){
			returnVal = true;
		}
	});
	
	return returnVal;
}

/*
 * 게시판 유형 목록 조회 해당 페이지로 이동
 * @param : pageNo(페이지 번호) / 없을 시 hiddenSearchForm사용
 */
function fnSelectBbsTyList(pageNo){
	pageLoadingView('show');
	if(arguments.length == 1){
        document.listForm.pageIndex.value = pageNo;
		document.listForm.action = contextPath + "/cmmn/adm/bbs/ty/bbsTyMain.do";
		document.listForm.submit();
	} else {
		let searchForm = document.getElementById("hiddenSearchForm");
		searchForm.action = contextPath + "/cmmn/adm/bbs/ty/bbsTyMain.do";
		searchForm.submit();
	}
}

/*
 * 게시판 유형 상세 조회 해당 페이지로 이동
 * @param : id(게시판id)
 */
function fnSelectBbsTy(id){
	pageLoadingView('show');
	document.listForm.bbsTyId.value = id;
	document.listForm.action = contextPath + "/cmmn/adm/bbs/ty/bbsTyModify.do";
	document.listForm.submit();
}