/**
 * 만족도 조사 관리 자바스크립트
 */
$(document).ready(function(){
	
	
	
	
});

/*
 * 만족도 조사 목록 조회 해당 페이지로 이동
 * @param : pageNo(페이지 번호) / 생략 가능
 */
function fnSelectCsnstList(pageNo){
	pageLoadingView('show');
	if(arguments.length == 1){
		//pageNo가 있으면 
		document.listForm.pageIndex.value = pageNo;
	    document.listForm.action = contextPath + "/cmmn/adm/csnst/csnstMain.do";
	    document.listForm.submit();
	} else {
		//없으면 
		let searchForm = document.getElementById("hiddenSearchForm");
		searchForm.action = contextPath + "/cmmn/adm/csnst/csnstMain.do";
		searchForm.submit();
	}
}