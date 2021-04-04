/**
 * 관리자 > 공통코드 그룹 관리 javascript
 * */
$(document).ready(function(){
	/*
	 * 검색버튼 클릭 시 페이지 번호 1로 초기화
	 */
	$('.searchBtn').click(function(){
		document.listForm.pageIndex.value = 1;
	});
	
	/*
	 *	공통코드 그룹코드 등록 버튼 클릭 함수
	 */
	$('#comCodeGroupRegistBtn').click(function(){
		swAlertConfirm('등록하시겠습니까?', {icon:'info', confirmButtonText:'등록'}, function(){
			let comCodeGroupForm = document.getElementById("comCodeGroupVO");
			if($('#checkCodeBoolean').val() == 'false'){
				Swal.fire('확인', '그룹코드 중복확인을 해주세요.', 'warning');
	    		return false;
			}
			
			if(!fn_code_check('Y')){
				if(validateComCodeGroupVO(comCodeGroupForm)){
					comCodeGroupForm.submit();
				} else {
					return false;
				}
			} else {
				Swal.fire('확인', '그룹코드 중복확인을 해주세요.', 'warning');
				return false;
			}
		});
	});
	
	/*
	 *	공통코드 그룹코드 삭제 버튼 클릭 함수
	 */
	$('#comCodeGroupDeleteBtn').click(function(){
		swAlertConfirm('삭제하시겠습니까?', {icon:'warning', confirmButtonText:'삭제'}, function(){
			let comCodeGroupForm = document.getElementById("comCodeGroupVO");
			let groupCode = comCodeGroupForm.groupCode.value;
			if(groupCode != null && groupCode != ''){
				comCodeGroupForm.action = contextPath + "/cmmn/adm/comCode/group/deleteComCodeGroup.do";				
				comCodeGroupForm.submit();
			} else {
				return false;
			}
		});
	});
	
	/*
	 *	공통코드 그룹코드 수정 버튼 클릭 함수
	 */
	$('#comCodeGroupUpdateBtn').click(function(){
		swAlertConfirm('수정하시겠습니까?', {icon:'info', confirmButtonText:'수정'}, function(){
			let comCodeGroupForm = document.getElementById("comCodeGroupVO");
			if(validateComCodeGroupVO(comCodeGroupForm)){
				comCodeGroupForm.submit();
			} else {
				return false;
			}
		});
	});
});

/*
 * 공통코드 그룹코드 조회 해당 페이지로 이동
 * @param : pageNo(페이지 번호)
 */
function fnSelectComCodeGroupList(pageNo){
	if(arguments.length == 1){
		document.listForm.pageIndex.value = pageNo;
		document.listForm.action = contextPath + "/cmmn/adm/comCode/group/comCodeGroupMain.do";
		document.listForm.submit();
	} else {
		let searchForm = document.getElementById("hiddenSearchForm");
		searchForm.action = contextPath + "/cmmn/adm/comCode/group/comCodeGroupMain.do";
		searchForm.submit();
	}
}

/*
 * 공통코드 그룹코드 상세 조회 페이지로 이동
 * @param : id(공통코드 그룹코드)
 */
function fnSelectComCodeGroup(id) {
    document.listForm.groupCode.value = id;
    document.listForm.action = contextPath + "/cmmn/adm/comCode/group/comCodeGroupModify.do";
    document.listForm.submit();
}
