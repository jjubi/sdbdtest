<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : qestnarTrgetListModal.jsp
  * @상세설명 : 설문조사 대상 목록 페이지
  * @작성일시 : 2020. 12. 31
  * @작 성 자 : jo
  * @수 정 이 력
  * @
  * @  수정일         수정자                   수정내용
  * @ -------       --------    ---------------------------
  * @ 2020.12.31   jo	  최초등록
  * @ 
  * 
  */
%>
<script>
$(document).ready(function(){
	$('#trgetListModal').on('show.bs.modal', function(e){
		selectTrgetList(1);
	}).on('hidden.bs.modal', function(){
		$('#targetListTable > tbody').empty();
	}).on('change', '#checkAll', function(){
		let allCheckStatus = $(this).is(':checked');
		$('#targetListTable > tbody').find('input[type="checkbox"]').each(function(i, v){
			if(allCheckStatus){
				$(this).prop('checked', true);
			} else {
				$(this).prop('checked', false);
			}
		});
	}).on('click', '#addAllQestnarMamber', function(){
		Swal.fire({
			icon : 'info',
			title : '일괄 추가 하시겠습니까?',
			showCancelButton : true,
			confirmButtonText : '일괄 추가'
		}).then((result) => {
			if(result.isConfirmed){
				if($('#targetListTable > tbody').find('input[type="checkbox"]:checked').length == 0){
					Swal.fire('확인', '선택한 대상이 없습니다.', 'warning');
					return ;
				}
				$('#targetListTable > tbody > tr .selectUserBtn').each(function(){
					//체크 되어있는 사람만 추가
					if($(this).parents('tr').find('input[type="checkbox"]:checked').length < 1){
						return true;
					}
					
					let userInfo = $(this).data('userInfo');
					let userInfos = userInfo.split(':');
					let userUniqId = userInfos[0];
					let userNm = userInfos[1];
					
					let userInfoHtml = $('<p></p>');
					let userInfoSpan = $('<span class="label label-default">'+userNm+'</span>');
					let userDeleteBtn = $('<span class="target_close"><i class="fas fa-times"></i></span>');
					
					userInfoSpan.append(userDeleteBtn);
					userInfoSpan.append('<input type="hidden" name="userId" value="'+userUniqId+'">');
					userInfoSpan.append('<input type="hidden" name="userNm" value="'+userNm+'">');
					
					userInfoHtml.append(userInfoSpan);
					$('.boxOn').append(userInfoHtml);
					$(this).parents('tr').find('td:eq(0)').html('');
					$(this).remove();
				});
			}
		});
	}).on('click', '.selectUserBtn', function(){
		let thisEl = $(this);
		Swal.fire({
			icon : 'info',
			title : '추가 하시겠습니까?',
			showCancelButton : true,
			confirmButtonText : '추가'
		}).then((result) => {
			if(result.isConfirmed){
				//중복 체크
				let userInfo = $(this).data('userInfo');
				let userInfos = userInfo.split(':');
				let userUniqId = userInfos[0];
				let userNm = userInfos[1];
				let userArr = $('.boxOn > p');
				let checkDup = true;
				if(userArr.length > 0){
					userArr.each(function(i, v){
						if($(this).find('input[name="userId"]').val() == userUniqId){
							checkDup = false;
							return false;
						}
					});
				}
				if(!checkDup){
					Swal.fire('확인', '이미 추가한 대상입니다.', 'warning');
					return false;
				}
				let userInfoHtml = $('<p></p>');
				let userInfoSpan = $('<span class="label label-default">'+userNm+'</span>');
				let userDeleteBtn = $('<span class="target_close"><i class="fas fa-times"></i></span>');
				
				userInfoSpan.append(userDeleteBtn);
				userInfoSpan.append('<input type="hidden" name="userId" value="'+userUniqId+'">');
				userInfoSpan.append('<input type="hidden" name="userNm" value="'+userNm+'">');
				
				userInfoHtml.append(userInfoSpan);
				$('.boxOn').append(userInfoHtml);
				thisEl.parents('tr').find('td:eq(0)').html('');
				thisEl.remove();
			}
		});
	});
});

function selectTrgetList(pageNo){
	pageLoadingView('show');
	$.ajax({
		url : '/cmmn/adm/qestnar/selectQestnarTrgetListAjax.do',
		type : 'POST',
		dataType : 'json',
		data : {"pageIndex" : pageNo},
		success : function(data){
			if(data.result == 'success'){
				setTrgetList(data.resultList, data.paginationInfo);
			} else {
				Swal.fire('실패', '대상자 목록을 가져오는데 실패했습니다. 관리자에게 문의해주세요.', 'error');
				pageLoadingView('hide');
			}
		}, error : function(error){
			Swal.fire('실패', '대상자 목록을 가져오는데 실패했습니다. 관리자에게 문의해주세요.', 'error');
			pageLoadingView('hide');
		}
	});
}

function setTrgetList(data, paginationInfo){
	let tbody = $('#targetListTable > tbody').empty();
	if(data.length == 0){
		tbody.append('<tr><td class="text-center" colspan="4"><spring:message code="common.nodata.msg" /></td></tr>');
	} else {
		let trgetMember = [];
		if($('.boxOn > p').length > 0){
			$('.boxOn > p').each(function(i, v){
				trgetMember.push($(this).find('input[name="userId"]').val());
			});
		}
		
		$.each(data, function(i, v){
			let tableTr = $('<tr></tr>');
// 			let numTd = $('<td></td>').append(paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + i));
			let numTd = $('<td></td>');
			if(!arrayContain(v.uniqId, trgetMember)){
				numTd.append('<input type="checkbox" name="memberCheck" >');
			}
			tableTr.append(numTd);
			let idTd = $('<td></td>').append(v.userId);
			tableTr.append(idTd);
			let nmTd = $('<td></td>').append(v.userNm);
			tableTr.append(nmTd);
			let seTd = $('<td></td>').append((v.userSe == 'USR' ? '관리자' : v.userSe == 'GNR' ? '회원' : '기업관리자'));
			tableTr.append(seTd);
			let etcTd = $('<td></td>');
			if(!arrayContain(v.uniqId, trgetMember)){
				etcTd.append('<a class="btn btn-sm btn-primary selectUserBtn">추가</a>');
				etcTd.find('a.selectUserBtn').data('userInfo', v.uniqId + ":" + v.userNm);
			}
			
			tableTr.append(etcTd);
			tbody.append(tableTr);
		});
	}
	$('#targetListPaginationArea').createPaginationInfo(paginationInfo, 'selectTrgetList', '', '');
	pageLoadingView('hide');
}
</script>

<!-- 설문조사 대상 목록 모달 -->
<div class="modal fade" id="trgetListModal" data-backdrop="static" aria-labelledby="trgetListModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title" id="trgetListModalLabel">설문조사 대상 목록</h2>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col">
					<a class="btn btn-sm btn-primary mb-3" id="addAllQestnarMamber">일괄 추가</a>
					<table class="table table-hover mb-3" id="targetListTable">
						<caption class="sr-only">설문조사 대상 <spring:message code="title.list" /></caption>
						<colgroup>
							<col style="width:8%;">
							<col style="width:20%;">
							<col style="width:28%;">
							<col style="width:auto;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">
<%-- 									<spring:message code="table.num" /> <!-- 번호 --> --%>
									<input type="checkbox" id="checkAll" name="checkAll" class="check2" title="<spring:message code="input.selectAll.title" />">
								</th>
								<th scope="col">
									<spring:message code="vaivQestnar.list.userId" /> <!-- 사용자 ID -->
								</th>
								<th scope="col">
									<spring:message code="vaivQestnar.list.userNm" /> <!-- 사용자 명 -->
								</th>
								<th scope="col">
									<spring:message code="vaivQestnar.list.userSe" /> <!-- 사용자 구분 -->
								</th>
								<th scope="col">
									<spring:message code="vaivQestnar.list.etc" /> <!-- 비고 -->
								</th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
				</div>
				<div class="col" id="targetListPaginationArea">
					
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

