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
	 * 게시판 기간 사용여부에 따른 기간 설정 Area 표출 
	 */
	$('input[name="bbsPdUseAt"]').change(function(){
		if($(this).val() == 'Y'){
			$('.bbsPdDateArea').show();
		} else {
			$('.bbsPdDateArea').hide();
			$('.bbsPdDateArea').find('#bbsPdBgnde').val('');
			$('.bbsPdDateArea').find('#bbsPdEndde').val('');
		}
	});
	
	/*
	 * 게시판 등록 버튼 클릭
	 */
	$('#bbsRegistBtn').click(function(){
		swAlertConfirm('등록하시겠습니까?', {icon:"info",confirmButtonText:'등록'}, function(){
			let bbsForm = document.getElementById("bbsVO");
			if($('input[name="bbsPdUseAt"]:checked').val() == 'Y'){
				let pridSt = $('#bbsPdBgnde').val();
				let pridEd = $('#bbsPdEndde').val();
				
				if(pridSt == '' || pridEd == ''){
					Swal.fire('확인', '게시기간을 확인하세요.', 'warning');
					return false;
				} else if(!moment(pridEd).isAfter(pridSt, 'day')) {
					Swal.fire('확인', '게시기간을 확인하세요.', 'warning');
					return false;
				}
			}
			if(validateBbsVO(bbsForm)){
				pageLoadingView('show');
				bbsForm.submit();
			} else {
				return false;
			}
		});
	});
	
	/*
	 * 게시판 삭제 버튼 클릭
	 */
	$('#bbsDeleteBtn').click(function(){
		swAlertConfirm('삭제하시겠습니까?', {icon : 'warning', confirmButtonText : '삭제'}, function(){
			let bbsForm = document.getElementById("bbsVO");
			let bbsIdVal = bbsForm.bbsId.value;
			if(bbsIdVal != null && bbsIdVal != ''){
				pageLoadingView('show');
				bbsForm.action = contextPath + "/cmmn/adm/bbs/deleteBbs.do";
				bbsForm.submit();
			} else {
				return false;
			}
		});
	});
	
	/*
	 * 게시판 수정 버튼 클릭
	 */
	$('#bbsUpdateBtn').click(function(){
		swAlertConfirm('수정하시겠습니까?', {icon:"info",confirmButtonText:'수정'}, function(){
			let bbsForm = document.getElementById("bbsVO");
			if($('input[name="bbsPdUseAt"]:checked').val() == 'Y'){
				let pridSt = $('#bbsPdBgnde').val();
				let pridEd = $('#bbsPdEndde').val();
				
				if(pridSt == '' || pridEd == ''){
					Swal.fire('확인', '게시기간을 확인하세요.', 'warning');
					return false;
				} else if(!moment(pridEd).isAfter(pridSt, 'day')) {
					Swal.fire('확인', '게시기간을 확인하세요.', 'warning');
					return false;
				}
			}
			if(validateBbsVO(bbsForm)){
				pageLoadingView('show');
				bbsForm.submit();
			} else {
				return false;
			}
		});
	});
});

/*
 * 게시판 목록 조회 해당 페이지로 이동
 * @param : pageNo(페이지 번호)
 */
function fnSelectBbsList(pageNo){
	pageLoadingView('show');
	if(arguments.length == 1){
	    document.listForm.pageIndex.value = pageNo;
	    document.listForm.action = contextPath + "/cmmn/adm/bbs/bbsMain.do";
	    document.listForm.submit();
	} else {
		let searchForm = document.getElementById("hiddenSearchForm");
		searchForm.action = contextPath + "/cmmn/adm/bbs/bbsMain.do";
		searchForm.submit();
	}
}

/*
 * 게시판 상세 조회 해당 페이지로 이동
 * @param : id(게시판id)
 */
function fnSelectBbs(id){
	pageLoadingView('show');
	document.listForm.bbsId.value = id;
	document.listForm.action = contextPath + "/cmmn/adm/bbs/bbsModify.do";
    document.listForm.submit();
}

/*
 * 게시판 권한 조회 해당 페이지로 이동
 * @param : id(게시판id)
 */
function fnSelectBbsAuthor(id){
	pageLoadingView('show');
	document.listForm.bbsId.value = id;
	document.listForm.action = contextPath + "/cmmn/adm/bbs/author/bbsAuthorModify.do";
    document.listForm.submit();
}

/*
 * 게시판 옵션 조회 해당 페이지로 이동
 * @param : id(게시판id)
 */
function fnSelectBbsOptn(id){
	pageLoadingView('show');
	document.listForm.bbsId.value = id;
	document.listForm.action = contextPath + "/cmmn/adm/bbs/optn/bbsOptnModify.do";
    document.listForm.submit();
}