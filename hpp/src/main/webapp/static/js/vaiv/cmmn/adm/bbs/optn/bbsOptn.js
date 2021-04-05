/**
 * 관리자 > 게시판 옵션 관리 javascript
 */
$(document).ready(function(){
	/*
	 * 게시판 옵션 첨부파일 사용 여부에 따른 추가 옵션 영역 컨트롤
	 */
	$('input[name="atchFileUseAt"]').change(function(){
		if($(this).val() == 'Y'){
			$('.atchFilePermExtsnArea').show();
			$('.atchFilePermMxmmCntArea').show();
			$('.atchFilePermExtsnArea').find('#atchFilePermExtsn').val('.gif.jpg.jpeg.png');
			$('.atchFilePermMxmmCntArea').find('#atchFilePermMxmmCnt').val('3');
		} else {
			$('.atchFilePermExtsnArea').hide();
			$('.atchFilePermMxmmCntArea').hide();
			$('.atchFilePermExtsnArea').find('#atchFilePermExtsn').val('');
			$('.atchFilePermMxmmCntArea').find('#atchFilePermMxmmCnt').val('0');
		}
	});
	
	/*
	 * 게시판 옵션 수정버튼 클릭
	 */
	$('#bbsOptnUpdateBtn').click(function(){
		swAlertConfirm('수정하시겠습니까?', {icon:'info', confirmButtonText :'수정'}, function(){
			pageLoadingView('show');
			if($('input[name="atchFileUseAt"]:checked').val() == 'Y'){
				if($('#atchFilePermExtsn').val() == ''){
					Swal.fire('확인', '첨부파일 확장자를 입력해주세요.', 'info');
					return false;
				} else if($('#atchFilePermMxmmCnt').val() == ''){
					Swal.fire('확인', '첨부파일 허용 개수를 입력해주세요.', 'info');
					return false;
				}
			}
			
			let bbsOptnForm = document.getElementById("bbsOptnVO");
			bbsOptnForm.submit();
		});
	});
});
