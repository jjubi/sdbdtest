/**
 * 관리자 > 컨텐츠 관리 javascript
 */
$(document).ready(function(){
	
	if($('script[src$="/js/egovframework/com/fms/EgovMultiFile.js"]').length > 0){
		var maxFileNum = $("#atchFilePermMxmmCnt").val();
		var multi_selector = new MultiSelector(document.getElementById('egovComFileList'), maxFileNum);
		multi_selector.addElement(document.getElementById('egovComFileUploader'));
		if($(".file_add").length > 0){
			multi_selector.setCurrentCount($(".file_add").length + 1);
		}
	}
	/*
	 * 컨텐츠 등록 버튼 클릭
	 */
	$('#cntntsRegistBtn').click(function(){
		swAlertConfirm('등록하시겠습니까?', {icon:'info',confirmButtonText:'등록'}, function(){
			let cntntsForm = document.getElementById("cntntsVO");
			
			if(validateCntntsVO(cntntsForm)){
				cntntsForm.submit();
			} else {
				return false;
			}
		});
	});
	
	/*
	 * 컨텐츠 삭제 버튼 클릭
	 */
	$('#cntntsDeleteBtn').click(function(){
		swAlertConfirm('삭제하시겠습니까?', {icon:'warning',confirmButtonText:'삭제'}, function(){
			let cntntsForm = document.getElementById("cntntsVO");
			let cntntsIdVal = cntntsForm.cntntsId.value;
			if(cntntsIdVal != null && cntntsIdVal != ''){
				cntntsForm.action = contextPath + "/cmmn/adm/cntnts/deleteCntnts.do";				
				cntntsForm.submit();
			} else {
				return false;
			}
		});
	});
	
	/*
	 * 컨텐츠 수정 버튼 클릭
	 */
	$('#cntntsUpdateBtn').click(function(){
		swAlertConfirm('수정하시겠습니까?', {icon:'info',confirmButtonText:'수정'}, function(){
			let cntntsForm = document.getElementById("cntntsVO");
			
			if(validateCntntsVO(cntntsForm)){
				cntntsForm.submit();
			} else {
				return false;
			}
		});
	});
	
	/*
	 * 컨텐츠 히스토리 모달 action
	 */
	$('#cntntsHisListModal').on('show.bs.modal', function(e){
		let btnEl = $(e.relatedTarget);
		let id = btnEl.data('id');
		$(this).find('#cntntsHisId').val(id);
		selectCntntsHisList(1);		
	}).on('hidden.bs.modal', function(){
		$(this).find('#cntntsHisId').val('');
		$('#cntntsHisTable > tbody').empty();
	}).on('click', '.rollbackCntntsBtn', function(){
		let hisSeq = $(this).data('seq');
		//복구하기
		swAlertConfirm('복구하시겠습니까?',{icon:'info',confirmButtonText:'복구'}, function(){
			pageLoadingView('show');
			$.ajax({
				url : contextPath + '/cmmn/adm/cntnts/updateCntntsHisRollback.do',
				data : {"histSeqNo" : hisSeq},
				dataType : 'json',
				type : 'POST',
				success : function(data){
					if(data.result == 'success'){
						Swal.fire('성공', '컨텐츠 복구에 성공했습니다.', 'success').then((result) => {
							location.reload();
						});
						$('#cntntsHisListModal').modal('hide');
					} else {
						Swal.fire('실패', '컨텐츠 복구에 실패했습니다. 관리자에게 문의해주세요.', 'error');
					}
					pageLoadingView('hide');
				}, error : function(error){
					Swal.fire('실패', '컨텐츠 복구에 실패했습니다. 관리자에게 문의해주세요.', 'error');
					pageLoadingView('hide');
				}
			});
		});
	});
	
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
})

/*
 * 컨텐츠 목록 조회 해당 페이지로 이동
 * @param : pageNo(페이지 번호)
 */
function fnSelectCntntsList(pageNo){
	pageLoadingView('show');
	if(arguments.length == 1){
	    document.listForm.pageIndex.value = pageNo;
	    document.listForm.action = contextPath + "/cmmn/adm/cntnts/cntntsMain.do";
	    document.listForm.submit();
	} else {
		let searchForm = document.getElementById("hiddenSearchForm");
		searchForm.action = contextPath + "/cmmn/adm/cntnts/cntntsMain.do";
		searchForm.submit();
	}
}

/*
 * 컨텐츠 상세 조회 해당 페이지로 이동
 * @param : id(컨텐츠id)
 */
function fnSelectCntnts(id){
	pageLoadingView('show');
	document.listForm.cntntsId.value = id;
	document.listForm.action = contextPath + "/cmmn/adm/cntnts/cntntsModify.do";
    document.listForm.submit();
}

/*
 * 컨텐츠 파일 다운로드
 * @param : fileId(파일id), fileSn(파일 순번)
 */
function fnFileDown(fileId, fileSn){
	location.href= contextPath + "/cmm/fms/FileDown.do?atchFileId="+fileId+"&fileSn="+fileSn;
}

/*
 * 컨텐츠 히스토리 목록 가져오기
 */
function selectCntntsHisList(pageNo){
	pageLoadingView('show');
	let cntntsId = $('#cntntsHisId').val();
	$.ajax({
		url : contextPath + '/cmmn/adm/cntnts/selectCntntsHisListAjax.do',
		type : 'POST',
		dataType : 'json',
		data : {"cntntsId" : cntntsId, "pageIndex" : pageNo},
		success : function(data){
			if(data.result == 'success'){
				setCntntsHisList(data.resultList, data.paginationInfo);
			} else {
				Swal.fire('실패', '히스토리를 가져오는데 실패했습니다. 관리자에게 문의해주세요.', 'error');
				pageLoadingView('hide');
			}
		}, error : function(error){
			Swal.fire('실패', '히스토리를 가져오는데 실패했습니다. 관리자에게 문의해주세요.', 'error');
			pageLoadingView('hide');
		}
	});
}

/*
 * 컨텐츠 히스토리 목록 표출
 */
function setCntntsHisList(data, paginationInfo){
	let tbody = $('#cntntsHisTable > tbody').empty();
	if(data.length == 0){
		tbody.append('<tr><td class="text-center" colspan="4">데이터가 없습니다.</td></tr>');
	} else {
		$.each(data, function(i, v){
			let tableTr = $('<tr></tr>');
			let numTd = $('<td></td>').append(paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + i));
			tableTr.append(numTd);
			let nmTd = $('<td></td>').append(v.cntntsNm);
			tableTr.append(nmTd);
			let registDttmTd = $('<td></td>').append(v.updtDe);
			tableTr.append(registDttmTd);
			let etcTd = $('<td></td>').append('<a class="btn btn-sm btn-primary rollbackCntntsBtn">복구하기</a>');
			etcTd.find('a.rollbackCntntsBtn').data('seq', v.histSeqNo);
			let preViewBtnHtml = '';
			preViewBtnHtml += '<div class="dropdown ml-2" style="display: inline-block;">';
			preViewBtnHtml += '		<a href="javascript:;" class="dropdown-ellipses dropdown-toggle btn btn-sm btn-primary" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">';
			preViewBtnHtml += '			미리보기<i class="fe fe-more-vertical"></i>';
			preViewBtnHtml += '		</a>';
			preViewBtnHtml += '		<div class="dropdown-menu dropdown-menu-right">';
			preViewBtnHtml += '			<a href="/cmmn/adm/cntnts/'+v.cntntsCode+'/cntntsView'+v.cntntsId+'.do?hisSeq='+v.histSeqNo+'" class="dropdown-item" target="_blank">';
			preViewBtnHtml += '				관리자';
			preViewBtnHtml += '			</a>';
			preViewBtnHtml += '			<a href="javascript:swAlert(\'준비중입니다.\');" class="dropdown-item">';
			preViewBtnHtml += '				사용자';
			preViewBtnHtml += '			</a>';
			preViewBtnHtml += '		</div>';
			preViewBtnHtml += '</div>';
			etcTd.append(preViewBtnHtml);
			
			tableTr.append(etcTd);
			tbody.append(tableTr);
		});
	}
	$('#cntntsHisPaginationArea').createPaginationInfo(paginationInfo, 'selectCntntsHisList', '', '');
	pageLoadingView('hide');
}