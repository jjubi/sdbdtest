/**
 * 관리자 > exception 관리 javascript
 */

/*
 * 게시판 목록 조회 해당 페이지로 이동
 * @param : pageNo(페이지 번호)
 */
function fnSelectSysExcpList(pageNo){
	pageLoadingView('show');
	if(arguments.length == 1){
	    document.listForm.pageIndex.value = pageNo;
	    document.listForm.action = contextPath + "/cmmn/adm/sys/excp/sysExcpMain.do";
	    document.listForm.submit();
	} else {
		let searchForm = document.getElementById("hiddenSearchForm");
		searchForm.action = contextPath + "/cmmn/adm/sys/excp/sysExcpMain.do";
		searchForm.submit();
	}
}

/*
 * 게시판 상세 조회 해당 페이지로 이동
 * @param : id(게시판id)
 */
function fnSelectSysExcp(id){
	pageLoadingView('show');
	document.listForm.action = contextPath + "/cmmn/adm/sys/excp/sysExcpDetail.do?exceptionSeqNo="+id;
    document.listForm.submit();
}

function getExcelData(){
	pageLoadingView('show');
	let formData = new FormData();
	formData.append("file", $('#bus')[0].files[0]);
	//파일 업로드
	uploadData(contextPath + '/cmmn/adm/sys/excp/excelDataAjax.do', formData, function(status, data){
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