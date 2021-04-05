/**
 * 관리자 > 댓글 관리 javascript
 */
var cmtNttId = '';
var cmtBbsTyCode = '';
$(document).ready(function(){
	cmtNttId = $('input[name="cmtNttId"]').val();
	cmtBbsTyCode = $('input[name="cmtBbsTyCode"]').val();
});

/*
 * 댓글 저장
 */
function fnInsertCmt(){
	swAlertConfirm('등록하시겠습니까?', {icon:'info',confirmButtonText:'등록'}, function(){
		let cmtCn = $('#cmtCn').val();
		if(cmtCn == null || cmtCn == ''){
			swAlert('확인', '댓글 내용을 입력하세요.', 'warning');
			return ;
		} else {
			//금지 단어 체크
			//내용
			if(!checkPrhibtWrd(cmtCn)){
				return false;
			}
			pageLoadingView('show');
			$.ajax({
				url : contextPath + '/cmmn/adm/bbs/ntt/cmt/insertCmtAjax.do',
				type:'post',
				dataType:'json',
				data:{"cmtCn" : cmtCn, "nttId" : cmtNttId},
				success : function(data){
					if(data.result == 'success'){
						//댓글 넣기
//						let cmtHtml = makeBbstBasicCmtView(data.cmtVO);
						let cmtHtml = makeCmtView(data.cmtVO.cmtId);
						
						$('.cmtArea > h5').after(cmtHtml);
						
						$('#cmtCn').val('');
						pageLoadingView('hide');
					} else {
						if(data.message != null && data.message != '' && data.message != undefined){
							swAlert('확인', data.message, 'warning');
							pageLoadingView('hide');
							return ;
						} else {
							swAlert('확인', '댓글 저장 실패! 관리자에게 문의해주세요.', 'warning');
							pageLoadingView('hide');
							return ;
						}
					}
				}, error : function(xhr, status, error){
					pageLoadingView('hide');
					swAlert('확인', '댓글 저장 실패! 관리자에게 문의해주세요.', 'warning');
				}
			});
		}
	});
}

/*
 * 댓글 수정
 * @param : cmtId(댓글 id)
 */
function fnUpdateComment(cmtId){
	swAlertConfirm('수정하시겠습니까?', {icon:'info',confirmButtonText:'수정'}, function(){
		let cmtCn = $('#cmtCnMod'+cmtId).val();
		if(cmtCn == null || cmtCn == ''){
			swAlert('확인', '수정 내용을 입력하세요.', 'warning');
			return ;
		} else {
			//금지 단어 체크
			//내용
			if(!checkPrhibtWrd(cmtCn)){
				return false;
			}
			pageLoadingView('show');
			$.ajax({
				url : contextPath + '/cmmn/adm/bbs/ntt/cmt/updateCmtAjax.do',
				type:'post',
				dataType:'json',
				data:{"cmtId" : cmtId, "cmtCn" : cmtCn, "nttId" : cmtNttId},
				success : function(data){
					if(data.result == 'success'){
						//댓글 수정
						cmtCn = xssFiltering(cmtCn);
						fnUpdateCommentCancel(stringReplaceAll(cmtCn,"\n","<br>"), cmtId);
						pageLoadingView('hide');
					} else {
						swAlert('확인', '댓글 수정 실패! 관리자에게 문의해주세요.', 'warning');
						pageLoadingView('hide');
						return ;
					}
				}, error : function(xhr, status, error){
					swAlert('확인', '댓글 수정 실패! 관리자에게 문의해주세요.', 'warning');
					pageLoadingView('hide');
				}
			});
		}
	});
}

/*
 * 댓글 삭제
 * @param : cmtId(댓글 id)
 */
function fnDeleteComment(cmtId){
	swAlertConfirm('삭제하시겠습니까?', {icon:'warning',confirmButtonText:'삭제'}, function(){
		pageLoadingView('show');
		$.ajax({
			url : contextPath + '/cmmn/adm/bbs/ntt/cmt/deleteCmtAjax.do',
			type:'post',
			dataType:'json',
			data:{"cmtId" : cmtId, "nttId" : cmtNttId},
			success : function(data){
				pageLoadingView('hide');
				if(data.result == 'success'){
					//댓글 삭제
					$('#'+cmtId).remove();
				} else {
					swAlert('확인', '댓글 삭제 실패! 관리자에게 문의해주세요.', 'warning');
					return ;
				}
			}
		});
	});
}

/*
 * 댓글 수정 폼으로 변경
 * @param : cmtId(댓글 id)
 */
function fnUpdateCommentForm(cmtId){
	let commentEle = $('#'+cmtId);
	if(cmtBbsTyCode == 'bbstBasic'){
		let commentText = $.trim(commentEle.find('.comment-text > p').html());
		commentTextEx = stringReplaceAll(commentText,"<br>","\n");
		commentTextEx = xssFiltering(commentTextEx);
		commentEle.find('.comment-update').text('완료').attr('href', 'javascript:fnUpdateComment(\''+cmtId+'\');');
		commentEle.find('.comment-update').after('<a class="comment-cancel" href="javascript:fnUpdateCommentCancel(\''+commentText+'\',\''+cmtId+'\');">수정취소</a>');
		
		let updateFormHtml = makeCmtUpdateView(cmtId, commentTextEx);
		commentEle.find('.comment-text').html(updateFormHtml);
	} else if(cmtBbsTyCode == 'xxxxxxx') {
		//다른 게시판 코드에 따른 폼 생성
	}
}

/*
 * 댓글 뷰 폼으로 변경
 * @param : cmtCn(댓글 내용), cmtId(댓글 id)
 */
function fnUpdateCommentCancel(cmtCn, cmtId){
	let commentEle = $('#'+cmtId);
	if(cmtBbsTyCode == 'bbstBasic'){
		commentEle.find('.comment-text').html('<p>'+cmtCn+'</p>');
		commentEle.find('.comment-update').text('수정').attr('href', 'javascript:fnUpdateCommentForm(\''+cmtId+'\');');
		commentEle.find('.comment-cancel').remove();
	} else if(cmtBbsTyCode == 'xxxxxxx') {
		//다른 게시판 코드에 따른 폼 생성
	}
}


/*
 * 댓글 View html 생성
 * @param : cmtId(댓글 id)
 */
function makeCmtView(cmtId){
	let returnHtml;
	$.ajax({
		url : contextPath + '/cmmn/adm/bbs/ntt/cmt/cmtDetail.do',
		type : 'post',
		async : false,
		data : {"bbsTyCode" : cmtBbsTyCode, "nttId" : cmtNttId, "cmtId" : cmtId},
		success : function(html){
			returnHtml = html;
		}
	});
	return returnHtml;
}

/*
 * 댓글 수정 폼 생성
 * @param : cmtId(댓글 id), cmtCn(댓글 내용)
 */
function makeCmtUpdateView(cmtId, cmtCn){
	let updateFormHtml = '';
	if(cmtBbsTyCode == 'bbstBasic'){
		updateFormHtml += '<textarea id="cmtCnMod'+cmtId+'" class="form-control" rows="3">'+cmtCn+'</textarea>';
	} else if(cmtBbsTyCode == 'xxxxxxx') {
		//다른 게시판 코드에 따른 폼 생성
	}
	
	return updateFormHtml;
}



