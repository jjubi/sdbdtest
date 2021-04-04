/**
 * 관리자 > 게시물 관리 javascript
 */
/* 전역 변수 : s */
var bbsId = '';
var bbsTyId = '';
/* 전역 변수 : e */
$(document).ready(function(){
	bbsId = $('#nowBbsId').val();
	bbsTyId = $('#nowBbsTyId').val();
	
	/*
	 * 검색버튼 클릭 시 페이지 번호 1로 초기화
	 */
	$('.searchBtn').click(function(){
		document.listForm.pageIndex.value = 1;
	});
	
	/*
	 * 게시물 등록 버튼 클릭
	 */
	$('#nttRegistBtn').click(function(){
		swAlertConfirm('등록하시겠습니까?', {icon:'info',confirmButtonText:'등록'}, function(){
			let nttForm;
			if($('#nttAnswerVO').length > 0){
				nttForm = document.getElementById("nttAnswerVO");
			} else {
				nttForm = document.getElementById("nttVO");
			}
			//옵션 체크
			//공지글 / 비밀글 동시 사용 못함
			if(fnCheckNttUseOptn("noticeAt", 'Y') && fnCheckNttUseOptn("secretAt", 'Y')){
				Swal.fire('확인', '공지글은 비밀글로 사용할 수 없습니다.', 'warning');
				return false;
			}
			
			//비밀글 사용 시 Key 체크
			if(fnCheckNttUseOptn("secretAt", 'Y')){
				if($('#secretKey').val() == ''){
					Swal.fire('확인', '비밀글 Key를 확인하세요.', 'warning');
					return false;
				}
			}
			
			//위치표시 사용 시 주소 체크
			if(fnCheckNttUseOptn("lcIndictAt", 'Y')){
				if($('#adres').val() == ''){
					Swal.fire('확인', '주소를 확인하세요.', 'warning');
					return false;
				} 
			}
			
			//금지 단어 체크
			//제목
			if(!checkPrhibtWrd(nttForm.nttSj.value)){
				return false;
			}
			//내용
			if(!checkPrhibtWrd(nttForm.nttCn.value)){
				return false;
			} 
			
			if(validateNttVO(nttForm)){
				pageLoadingView('show');
				nttForm.submit();
			} else {
				return false;
			}
		});
	});
	
	/*
	 * 게시물 수정 버튼 클릭
	 */
	$('#nttUpdateBtn').click(function(){
		swAlertConfirm('수정하시겠습니까?', {icon:'info',confirmButtonText:'수정'}, function(){
			let nttForm = document.getElementById("nttVO");
			//옵션 체크
			//답글이 있을 경우 공지글 안됨
			if($('#nttAnswerYn').val() == 'Y' && fnCheckNttUseOptn("noticeAt", "Y")){
				Swal.fire('확인', '답글이 있을 경우 공지글 설정을 할 수 없습니다.', 'warning');
				return false;
			}
			
			//공지글 / 비밀글 동시 사용 못함
			if(fnCheckNttUseOptn("noticeAt", 'Y') && fnCheckNttUseOptn("secretAt", 'Y')){
				Swal.fire('확인', '공지글은 비밀글로 사용할 수 없습니다.', 'warning');
				return false;
			}
			
			//비밀글 사용 시 Key 체크
			if(fnCheckNttUseOptn("secretAt", 'Y')){
				if($('#secretKey').val() == ''){
					Swal.fire('확인', '비밀글 Key를 확인하세요.', 'warning');
					return false;
				}
			}
			
			//위치표시 사용 시 주소 체크
			if(fnCheckNttUseOptn("lcIndictAt", 'Y')){
				if($('#adres').val() == ''){
					Swal.fire('확인', '주소를 확인하세요.', 'warning');
					return false;
				} 
			}
			
			//금지 단어 체크
			//제목
			if(!checkPrhibtWrd(nttForm.nttSj.value)){
				return false;
			}
			//내용
			if(!checkPrhibtWrd(nttForm.nttCn.value)){
				return false;
			}
			
			if(validateNttVO(nttForm)){
				pageLoadingView('show');
				nttForm.submit();
			} else {
				return false;
			}
		});
	});
	
	//슬라이드 갤러리
	//type1
	if($('.slider-for').length > 0){
	    $('.slider-for').slick({
			slidesToShow: 1,
			slidesToScroll: 1,
			arrows: false,
			fade: true,
			asNavFor: '.slider-nav'
	    });
	}
	if($('.slider-nav').length > 0){
	    $('.slider-nav').slick({
			slidesToShow: 3,
			slidesToScroll: 1,
			asNavFor: '.slider-for',
			dots: false,
			centerMode: true,
			focusOnSelect: true
	    });
	}
	//type2
	if($('.slider2.center').length > 0){
		$('.slider2.center').slick({
			centerMode: true,
			centerPadding: '60px',
			slidesToShow: 3,
			responsive: [{
				breakpoint: 768,
				settings: {
					arrows: false,
					centerMode: true,
					centerPadding: '40px',
					slidesToShow: 3
				}
			}, 
			{
				breakpoint: 480,
				settings: {
					arrows: false,
					centerMode: true,
					centerPadding: '40px',
					slidesToShow: 1
				}
			}]
		});
	}
    //슬라이드 갤러리 :End
	
	/*
	 * ccl 유형과 공공누리 유형 중복 선택되지 않도록
	 */
	$("input[name='koglTy']:radio, input[name='cclTy']:radio").change(function(){
		var name = $(this).attr('name');
		if(name == 'koglTy'){
			$("input:radio[name='cclTy']:input[value='']").prop('checked', true);
		}else if(name == 'cclTy'){
			$("input:radio[name='koglTy']:input[value='']").prop('checked', true);
		}
	});
});

/*
 * 옵션 체크 함수 (사용여부 체크)
 * @param : eleName(element Name), val(옵션 체크값)
 */
function fnCheckNttUseOptn(eleName, val){
	let returnVal = false;
	if($('input[name="'+eleName+'"]').length > 0 && $('input[name="'+eleName+'"]:checked').val() == val){
		returnVal = true;
	} 
	return returnVal;	
}

/*
 * 게시물 목록 조회 해당 페이지로 이동
 * @param : pageNo(페이지 번호) / 없을 시 hiddenSearchForm 사용
 */
function fnSelectNttList(pageNo){
	pageLoadingView('show');
	if(arguments.length == 1){
	    document.listForm.pageIndex.value = pageNo;
	    document.listForm.action = contextPath + "/cmmn/adm/bbs/ntt/"+bbsId+"/nttList.do";
	    document.listForm.submit();
	} else {
		let searchForm = document.getElementById("hiddenSearchForm");
		searchForm.action = contextPath + "/cmmn/adm/bbs/ntt/"+bbsId+"/nttList.do";
		searchForm.submit();
	}
}
/*
 * 게시물 상세 조회 해당 페이지로 이동
 * @param : id(게시물 id)
 */
function fnSelectNtt(id){
	pageLoadingView('show');
//	document.listForm.nttId.value = id;
	document.listForm.action = contextPath + "/cmmn/adm/bbs/ntt/"+bbsId+"/nttDetail.do?nttId="+id;
    document.listForm.submit();
}

function fnSelectNttModify(id){
	pageLoadingView('show');
	$('#listForm').append('<input type="hidden" name="nttId" value="'+id+'">');
	document.listForm.action = contextPath + "/cmmn/adm/bbs/ntt/"+bbsId+"/nttModify.do";
    document.listForm.submit();
}

/*
 * 게시물 상세 조회 해당페이지로 이동 (비밀글)
 * @param : id(게시물 id)
 */
function fnSecretSelectNtt(id){
	swAlertInput('password', '비밀키를 입력하세요.', {}, function(result){
		$.ajax({
			url : contextPath + '/cmmn/adm/bbs/ntt/checkNttSecretKeyAjax.do',
			type : 'post',
			dataType : 'json',
			data : {"nttId" : id, "secretKey" : result.value},
			success : function(data){
				if(data.result == 'success'){
					fnSelectNtt(id);
				} else {
					Swal.fire('확인', '비밀번호가 틀렸습니다.', 'warning');
					return false;
				}
			}, error : function(status){
				Swal.fire('확인', '비밀번호 확인 중 에러발생. 관리자에게 문의해주세요', 'error');
				return false;
			}
		});
	});
}

/*
 * 게시물 파일 다운로드
 * @param : fileId(파일id), fileSn(파일 순번)
 */
function fnFileDown(fileId, fileSn){
	location.href= contextPath + "/cmm/fms/FileDown.do?atchFileId="+fileId+"&fileSn="+fileSn;
}

/*
 * 게시물 삭제 
 * @param : nttId(게시물id)
 */
function fnDeleteNtt(nttId){
	swAlertConfirm('삭제하시겠습니까?', {icon:'warning', text:'답글이 있을 경우 같이 삭제 됩니다.', confirmButtonText:'삭제'}, function(){
		pageLoadingView('show');
		let frm = $('<form></form>');
		frm.attr('method', 'post');
		frm.attr('action', contextPath + "/cmmn/adm/bbs/ntt/"+bbsId+"/deleteNtt.do");
		frm.append('<input type="hidden" name="nttId" value="'+nttId+'">');
		$(document.body).append(frm);
		frm.submit();
	});
}









