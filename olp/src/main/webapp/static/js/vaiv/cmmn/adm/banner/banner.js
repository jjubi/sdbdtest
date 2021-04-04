/**
 * 관리자 > 배너 관리 javascript
 * */
var nowOrdr = "";
$(document).ready(function(){
	/*
	 * 검색버튼 클릭 시 페이지 번호 1로 초기화
	 */
	$('.searchBtn').click(function(){
		document.listForm.pageIndex.value = 1;
	});
	
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
			let bannerSeqNo = $(this).parents('.dd-item.kanban-item').data('bannerSeqNo');
			if(useAt){
				//사용여부 Y로 변경
				updateBannerUseAt(bannerSeqNo, "Y");
			} else {
				//사용여부 N으로 변경
				updateBannerUseAt(bannerSeqNo, "N");
			}
		});
	}
	
	/*
	 * 배너 목록 있을 경우 nestable 사용 설정
	 */
	if($('#bannerList').length > 0){
		$('#bannerList').nestable({
			maxDepth : 1,
			onDragStart : function(l, e){
				//순서 변경에 따른 ordr 변경하기
				let bannerList = $('#bannerList');
				let bannerList_Json = bannerList.nestable('serialize');
				nowOrdr = '';
				$.each(bannerList_Json, function(i, v){
					if(i != 0){
						nowOrdr += '-';
					}
					nowOrdr += v.bannerSeqNo;
				});
			},
			callback : function(l, e, p){
				updateBannerOrdr();
			}
		});
	}
	
	/*
	 * 배너 이미지 파일 선택 시 미리보기
	 */
	$('#file_0').change(function(e){
		let file = e.target.files[0];
		if($.fn.useExtCheck(['png', 'jpg', 'jpeg', 'gif', 'bmp'], $(this).val())){
			$('.custom-file-label').text($(this).val());
			$('#preViewImageArea').fileImagePreView({"file":file});
		} else {
			$(this).val(null);
			return false;
		}
	});
	
	/*
	 * 배너 등록 버튼 클릭
	 */
	$('#bannerRegistBtn').click(function(){
		swAlertConfirm('등록하시겠습니까?', {icon:'info', confirmButtonText : '등록'}, function(){
			let bannerForm = document.getElementById("bannerVO");
			//url 형식 체크
			// /로 시작하는 경우(사이트 내 url) 제외
			if(bannerForm.bannerUrl.value.indexOf('/') != 0){
				if(!(bannerForm.bannerUrl.value.indexOf('http://') == 0 || bannerForm.bannerUrl.value.indexOf('https://') == 0)){
					swAlert('확인', 'URL은 http://, https://를 포함하여야 합니다.', 'warning');
					return false;
				}
			}
			//게시기간 체크
			if(!checkPeriod(bannerForm.bannerBgnde.value, bannerForm.bannerEndde.value)){
				swAlert('확인', '게시기간을 확인하세요.', 'warning');
				return false;
			}
			if(!validateBannerVO(bannerForm)){
				return false;
			} else {
				bannerForm.submit();
			}
		});
	});
	
	/*
	 * 배너 수정 버튼 클릭
	 */
	$('#bannerUpdateBtn').click(function(){
		swAlertConfirm('수정하시겠습니까?', {icon:'info', confirmButtonText : '수정'}, function(){
			let bannerForm = document.getElementById("bannerVO");
			//url 형식 체크
			// /로 시작하는 경우(사이트 내 url) 제외
			if(bannerForm.bannerUrl.value.indexOf('/') != 0){
				if(!(bannerForm.bannerUrl.value.indexOf('http://') == 0 || bannerForm.bannerUrl.value.indexOf('https://') == 0)){
					swAlert('확인', 'URL은 http://, https://를 포함하여야 합니다.', 'warning');
					return false;
				}
			}
			//게시기간 체크
			if(!checkPeriod(bannerForm.bannerBgnde.value, bannerForm.bannerEndde.value)){
				swAlert('확인', '게시기간을 확인하세요.', 'warning');
				return false;
			}
			if(!validateBannerVO(bannerForm)){
				return false;
			} else {
				bannerForm.submit();
			}
		});
	});
})

/*
 * 배너 목록 조회 해당 페이지로 이동
 * @param : pageNo(페이지 번호) / 생략 가능
 */
function fnSelectBannerList(pageNo){
	if(arguments.length == 1){
		//pageNo가 있으면 
		document.listForm.pageIndex.value = pageNo;
	    document.listForm.action = contextPath + "/cmmn/adm/banner/bannerMain.do";
	    document.listForm.submit();
	} else {
		//없으면 
		let searchForm = document.getElementById("hiddenSearchForm");
		searchForm.action = contextPath + "/cmmn/adm/banner/bannerMain.do";
		searchForm.submit();
	}
}

/*
 * 배너 상세 조회 페이지로 이동
 * @param : id(공통코드)
 */
function fnSelectBanner(id) {
	document.listForm.bannerSeqNo.value = id;
    document.listForm.action = contextPath + "/cmmn/adm/banner/bannerModify.do";
    document.listForm.submit();
}

/*
 * 배너 삭제
 * @param : id(배너 ID)
 */
function fnDeleteBanner(id){
	swAlertConfirm('삭제하시겠습니까?',{icon:'info',confirmButtonText:'삭제'}, function(){
		document.listForm.bannerSeqNo.value = id;
	    document.listForm.action = contextPath + "/cmmn/adm/banner/deleteBanner.do";
	    document.listForm.submit();
	});
}

/*
 * 배너 사용 여부 수정
 * @param : id(배너 ID), useAt(사용여부)
 */
function updateBannerUseAt(id, useAt){
	
	$.ajax({
		url : contextPath + '/cmmn/adm/banner/updateBannerAjax.do',
		type : 'post',
		dataType : 'json',
		async : false,
		data : {'bannerSeqNo' : id, 'useAt' : useAt, 'updateType' : 'useAt'},
		success : function(data){
			if(data.result == 'success'){
				
			} else {
				swAlert('사용여부 변경 저장 실패. 관리자에게 문의해주세요.');	
			}
		}
	});
}

/*
 * 배너 순서 변경 (사용중인 배너만 해당)
 */
function updateBannerOrdr(){
	//순서 변경에 따른 ordr 변경하기
	let bannerList = $('#bannerList');
	let bannerList_Json = bannerList.nestable('serialize');
	let ordrData = '';
	$.each(bannerList_Json, function(i, v){
		if(i != 0){
			ordrData += '-';
		}
		ordrData += v.bannerSeqNo;
	});
	if(nowOrdr == ordrData){
		return ;
	}
	$.ajax({
		url : contextPath + '/cmmn/adm/banner/updateBannerAjax.do',
		type : 'post',
		dataType : 'json',
		data : {'bannerOrdr' : ordrData, 'updateType' : 'ordr'},
		success : function(data){
			if(data.result == 'success'){
				
			} else {
				swAlert('순서 변경 저장 실패. 관리자에게 문의해주세요.');	
			}
		}
	});
}







