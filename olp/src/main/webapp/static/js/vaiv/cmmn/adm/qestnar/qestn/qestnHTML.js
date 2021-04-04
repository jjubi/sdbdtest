/**
 * 설문 문항 HTML 관리 javascript
 */

/*
 * 기본 모달 HTML
 */
const modalHTMLStr = '<div class="modal fade" id="qestnSettingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">' 
						+'	<div class="modal-dialog modal-lg">'
						+'		<div class="modal-content">'
						+'			<div class="modal-header">'
						+'				<h4 class="modal-title qestnModalTitle"></h4>'
						+'			</div>'
						+'			<div class="modal-body form-horizontal">'
						+'				<form id="qestnModalForm">'
						+'				</form>'
						+'			</div>'
						+'			<div class="modal-footer">'
						+'				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>'
						+'				<button type="button" id="confirm" class="btn btn-primary">추가</button>'
						+'			</div>'
						+'		</div>'
						+'	</div>'
						+'</div>';

/*
 * 기본 문항 HTML
 */
const defaultQestntHTMLStr ='<div class="form-group">'
							+'	<h4>기본 정보</h4>'
							+'</div>'
							+ '<div class="form-group">'
							+'	<label for="qestnSj" class="h5-no color-blue">문항 질문 <span class="pilsu">*</span></label>'
							+'	<input type="text" id="qestnSj" name="qestnSj" placeholder="질문을 입력하세요." class="form-control">'
							+'</div>'
							+'<div class="form-group">'
							+	'<label for="qestnHpcm" class="h5-no color-blue">문항 안내문</label>'
							+	'<textarea rows="3" cols="10" id="qestnHpcm" name="qestnHpcm" placeholder="안내문을 입력하세요." class="form-control"></textarea>'
							+'</div>';

/*
 * 기본 설문 View HTML (card)
 */
const defaultQestnViewHTMLStr = '<div class="card">'
								+'	<div class="card-body">'
								+'		<h2 class="card-title"></h2>'
								+'		<h6 class="card-subtitle mt-2 mb-3"></h6>'
								+'		<div class="form-group">'
								+'		</div>'
								+'	</div>'
								+'	<div class="card-footer">'
								+'		<div class="text-right">'
								+'			<a class="btn btn-warning qestnMod" title="문항 수정">수정</a>'
								+'			<a class="btn btn-danger qestnDel ml-2" title="문항 삭제">삭제</a>'
								+'		</div>'
								+'	</div>'
								+'</div>';

const qestnTabHTML = '<li class="nav-item tab_wrap">'
					+'	<a class="nav-link tab_tit" id="" href="" data-toggle="tab" role="tab" aria-controls="" aria-selected="false"></a>'
					+'</li>';

const qestnTabCnHTML = '<div class="tab-pane fade" id="" role="tabpanel" aria-labelledby=""  style="width:55%;">'
						+'	<div id="" class="qestnListArea">'
						+'	</div>'
						+'</div>';

const qestnTyByQestnHTML = {
		text : {
			setAswperModal : function(){
				return null;
			},
			setAswperModalData : function(){
				return null;
			},
			setOptnModal : function(){
				let htmlStr = '<div class="form-group">'
							+'	<h4>옵션 설정</h4>'
							+'</div>'
							+qestn2RadioBtnSelectForm('필수 여부', 'qestnEssntl', '필수', '필수아님', '필수')
							+qestn2RadioBtnSelectForm('글자 제한', 'optnLwc', '사용', '사용안함', '사용')
							+'<div class="form-group" id="optnLwcValueArea">'
							+'	<label for="optnLwcValue" class="h5-no color-blue">최대 글자 수 <span class="pilsu">*</span></label>'
							+'	<input type="text" id="optnLwcValue" name="optnLwcValue" placeholder="최대 글자 수를 입력하세요." class="form-control onlyNum">'
							+'</div>';	//'제한 글자 수 : LWC (int)'
				let html = $(htmlStr);
				html.on('change', 'input[name="optnLwcAt"]', function(){
					if($(this).val() == 'Y'){
						$('#optnLwcValueArea').show();
					} else {
						$('#optnLwcValueArea').hide();
						$('#optnLwcValueArea').find('#optnLwcValue').val('');
					}
				});
				
				return html;
			},
			setOptnModalData : function(data, modalForm){
				modalForm.find('#qestnSj').val(data.qestnSj);
				modalForm.find('#qestnHpcm').val(data.qestnHpcm);
				modalForm.find('#qestnEssntlAt'+data.qestnEssntlAt).prop('checked', true);
				modalForm.find('#optnLwcAt'+data.optnLwcAt).prop('checked', true);
				if(data.optnLwcAt == 'N'){
					modalForm.find('#optnLwcValueArea').hide();
				} else {
					modalForm.find('#optnLwcValue').val(data.optnLwcValue);
				}
			},
			setView : function(data){
				let defaultView = $(defaultQestnViewHTMLStr);
				
				let view = setCommonViewData(data, defaultView);
				let maxLng = '';
				if(data.optnLwcAt == 'Y'){
					maxLng = data.optnLwcValue;
				}
				view.find('.form-group').append('<input type="text" class="form-control qestn_answer" placeholder="답변을 입력하세요." '+(maxLng != '' ? 'maxlength='+maxLng : '')+' disabled="disabled">');
				
				view.data('qestnData', data);
				return view;
			},
			setViewData : function(){
				
			},
			getModalData : function(form){
				//필수 체크
				if(form.find('#qestnSj').val() == ''){
					swAlert('확인', '문항 질문을 입력해주세요.', 'warning');
					return null;
				}
				if(form.find('input[name="optnLwcAt"]:checked').val() == 'Y' && form.find('#optnLwcValue').val() == ''){
					swAlert('확인', '최대 글자 수를 입력해주세요.', 'warning');
					return null;
				}
				//데이터 json으로 파싱
				let formDataJson = formToJSON(form[0]);
				console.log(formDataJson);
				return formDataJson;
			}
		},
		textarea : {
			setAswperModal : function(){
				return null;
			},
			setAswperModalData : function(){
				return null;
			},
			setOptnModal : function(){
				let htmlStr = '<div class="form-group">'
							+'	<h4>옵션 설정</h4>'
							+'</div>'
							+qestn2RadioBtnSelectForm('필수 여부', 'qestnEssntl', '필수', '필수아님', '필수')
							+qestn2RadioBtnSelectForm('글자 제한', 'optnLwc', '사용', '사용안함', '사용')
							+'<div class="form-group" id="optnLwcValueArea">'
							+'	<label for="optnLwcValue" class="h5-no color-blue">최대 글자 수 <span class="pilsu">*</span></label>'
							+'	<input type="text" id="optnLwcValue" name="optnLwcValue" placeholder="최대 글자 수를 입력하세요." class="form-control onlyNum">'
							+'</div>';	//'제한 글자 수 : LWC (int)'
				let html = $(htmlStr);
				html.on('change', 'input[name="optnLwcAt"]', function(){
					if($(this).val() == 'Y'){
						$('#optnLwcValueArea').show();
					} else {
						$('#optnLwcValueArea').hide();
						$('#optnLwcValueArea').find('#optnLwcValue').val('');
					}
				});
				
				return html;
			},
			setOptnModalData : function(data, modalForm){
				modalForm.find('#qestnSj').val(data.qestnSj);
				modalForm.find('#qestnHpcm').val(data.qestnHpcm);
				modalForm.find('#qestnEssntlAt'+data.qestnEssntlAt).prop('checked', true);
				modalForm.find('#optnLwcAt'+data.optnLwcAt).prop('checked', true);
				if(data.optnLwcAt == 'N'){
					modalForm.find('#optnLwcValueArea').hide();
				} else {
					modalForm.find('#optnLwcValue').val(data.optnLwcValue);
				}
			},
			setView : function(data){
				let defaultView = $(defaultQestnViewHTMLStr);
				
				let view = setCommonViewData(data, defaultView);
				let maxLng = '';
				if(data.optnLwcAt == 'Y'){
					maxLng = data.optnLwcValue;
				}
				view.find('.form-group').append('<textarea class="form-control qestn_answer" placeholder="답변을 입력하세요." '+(maxLng != '' ? 'maxlength='+maxLng : '')+' disabled="disabled" row="5"></textarea>');
				view.data('qestnData', data);
				return view;
			},
			setViewData : function(){
				
			},
			getModalData : function(form){
				if(form.find('#qestnSj').val() == ''){
					Swal.fire('확인', '문항 질문을 입력해주세요.', 'warning');
					return null;
				}
				if(form.find('input[name="optnLwcAt"]:checked').val() == 'Y' && form.find('#optnLwcValue').val() == ''){
					swAlert('확인', '최대 글자 수를 입력해주세요.', 'warning');
					return null;
				}
				//데이터 json으로 파싱
				let formDataJson = formToJSON(form[0]);
				console.log(formDataJson);
				return formDataJson;
			}
		},
		radio : {
			setAswperModal : function(){
				let htmlStr = '<div class="form-group">'
							+'	<h4>답안 설정'
							+'		<a href="javascript:void(0);" class="btn btn-sm btn-info right" id="addAswperBtn">추가</a>'
//						    +'		<a href="javascript:void(0);" class="btn btn-sm btn-info" id="addAllAswperBtn">일괄입력</a>'
							+'	</h4>'
							+'</div>'
							+'<div class="form-group">'
							+'	<div id="aswperList">'
							+'		<div class="row mb-1">'
							+'			<div class="col aswperArea">'
							+'				<input type="text" name="aswperText" class="form-control" placeholder="값을 입력하세요">'
							+'			</div>'
							+'		</div>'
							+'	</div>'
							+'</div>'
							+qestn2RadioBtnSelectForm('답안 점수 여부', 'aswperScore', '사용', '사용안함', '사용안함')
							+qestn2RadioBtnSelectForm('기타 여부', 'aswperEtc', '사용', '사용안함', '사용안함');
				let html = $(htmlStr);
				dragulaList2 = dragula([html.find('#aswperList')[0]]);
				aswperHandlingAction(html);
				return html;
			},
			setAswperModalData : function(data, modalForm){
				modalForm.find('label').removeClass('active');
				modalForm.find('#aswperScoreAt'+data.aswperScoreAt).prop('checked', true);
//				modalForm.find('label[for="aswperScoreAt'+data.aswperScoreAt+'"]').addClass('active').find('input[type="radio"]').prop('checked', true);
				
				if(Array.isArray(data.aswperText)){
					modalForm.find('#aswperList').empty();
					$.each(data.aswperText, function(i, v){
						let aswperHTML = $('<div class="row mb-1"><div class="col aswperArea">'
								+'			<input type="text" name="aswperText" class="form-control" placeholder="값을 입력하세요" value="'+v+'">'
								+'		</div></div>');
						if(data.aswperScoreAt == 'Y'){
							aswperHTML.find('.aswperArea').addClass('qestnPoint').after('<input type="text" name="aswperScore" class="form-control qestnPointInput onlyNum"  value="'+data.aswperScore[i]+'">');
						}
						if(i != 0){
							aswperHTML.prepend('<a class="btn btn-sm btn-danger deleteAswper">삭제</a>');
						}
						modalForm.find('#aswperList').append(aswperHTML);
					});
				} else {
					if(data.aswperText != ''){
						modalForm.find('input[name="aswperText"]').val(data.aswperText);
						if(data.aswperScoreAt == 'Y'){
							modalForm.find('input[name="aswperText"]').parent('.aswperArea').addClass('qestnPoint').after('<input type="text" name="aswperScore" class="form-control qestnPointInput onlyNum"  value="'+data.aswperScore+'">');
						}
					}
				}
			},
			setOptnModal : function(){
				let htmlStr = '<div class="form-group">'
							+'	<h4>옵션 설정</h4>'
							+'</div>'
							+qestn2RadioBtnSelectForm('필수 여부', 'qestnEssntl', '필수', '필수아님', '필수')
				let html = $(htmlStr);
				return html;
			},
			setOptnModalData : function(data, modalForm){
				modalForm.find('#qestnSj').val(data.qestnSj);
				modalForm.find('#qestnHpcm').val(data.qestnHpcm);
				modalForm.find('#qestnEssntlAt'+data.qestnEssntlAt).prop('checked', true);
				modalForm.find('#aswperEtcAt'+data.aswperEtcAt).prop('checked', true);
				if(data.aswperEtcAt == 'Y'){
					//마지막 기타는 기타설정에 의한 기타
					let etcAswperEl = modalForm.find('#aswperList > .row:last');
					let etcAswperElClone = etcAswperEl.clone();
					etcAswperElClone.find('.deleteAswper').remove();
					etcAswperElClone.find('input[name="aswperText"]').addClass('etcAswper').attr('readonly','readonly');
					modalForm.find('#aswperList').after(etcAswperElClone);
					etcAswperEl.remove();
				}
			},
			setView : function(data){
				let defaultView = $(defaultQestnViewHTMLStr);
				
				let view = setCommonViewData(data, defaultView);
				if(Array.isArray(data.aswperText)){
					$.each(data.aswperText, function(i, v){
						view.find('.form-group').append('<label class="radio-inline qestn_radio1">'+
								'<input type="radio" name="'+data.qestnId+'_radio" disabled="disabled"> ' + v +
						'</label>');
					});
				} else {
					view.find('.form-group').append('<label class="radio-inline qestn_radio1">'+
														'<input type="radio" name="'+data.qestnId+'_radio" disabled="disabled"> ' + data.aswperText +
													'</label>');
				}
				
				view.data('qestnData', data);
				return view;
			},
			setViewData : function(){
				
			},
			getModalData : function(form){
				//필수 체크
				if(form.find('#qestnSj').val() == ''){
					Swal.fire('확인', '문항 질문을 입력해주세요.', 'warning');
					return null;
				}
				let check = false;
				$.each(form.find('input[name="aswperText"]'), function(i, v){
					if($.trim($(this).val()) == ''){
						check = true;
						return false;
					}
				});
				
				if(form.find('input[name="aswperScoreAt"]:checked').val() == 'Y'){
					$.each(form.find('input[name="aswperScore"]'), function(i, v){
						if($.trim($(this).val()) == ''){
							check = true;
							return false;
						}
					});
				}
				
				if(check){
					Swal.fire('확인', '답안값 또는 답안점수를 입력해주세요.', 'warning');
					return null;
				}
				//데이터 json으로 파싱
				let formDataJson = formToJSON(form[0]);
				console.log(formDataJson);
				return formDataJson;
			}
		},
		checkbox : {
			setAswperModal : function(){
				let htmlStr = '<div class="form-group">'
							+'	<h4>답안 설정'
							+'		<a href="javascript:void(0);" class="btn btn-sm btn-info right" id="addAswperBtn">추가</a>'
//						    +'		<a href="javascript:void(0);" class="btn btn-sm btn-info" id="addAllAswperBtn">일괄입력</a>'
							+'	</h4>'
							+'</div>'
							+'<div class="form-group">'
							+'	<div id="aswperList">'
							+'		<div class="row mb-1">'
							+'			<div class="col aswperArea">'
							+'				<input type="text" name="aswperText" class="form-control" placeholder="값을 입력하세요">'
							+'			</div>'
							+'		</div>'
							+'	</div>'
							+'</div>'
							+qestn2RadioBtnSelectForm('답안 점수 여부', 'aswperScore', '사용', '사용안함', '사용안함')
							+qestn2RadioBtnSelectForm('기타 여부', 'aswperEtc', '사용', '사용안함', '사용안함');
				let html = $(htmlStr);
				aswperHandlingAction(html);
				dragulaList2 = dragula([html.find('#aswperList')[0]]);
				return html;
			},
			setAswperModalData : function(data, modalForm){
				modalForm.find('label').removeClass('active');
				modalForm.find('#aswperScoreAt'+data.aswperScoreAt).prop('checked', true);
				
				if(Array.isArray(data.aswperText)){
					modalForm.find('#aswperList').empty();
					$.each(data.aswperText, function(i, v){
						let aswperHTML = $('<div class="row mb-1"><div class="col aswperArea">'
								+'			<input type="text" name="aswperText" class="form-control" placeholder="값을 입력하세요" value="'+v+'">'
								+'		</div></div>');
						if(data.aswperScoreAt == 'Y'){
							aswperHTML.find('.aswperArea').addClass('qestnPoint').after('<input type="text" name="aswperScore" class="form-control qestnPointInput onlyNum"  value="'+data.aswperScore[i]+'">');
						}
						if(i != 0){
							aswperHTML.prepend('<a class="btn btn-sm btn-danger deleteAswper">삭제</a>');
						}
						modalForm.find('#aswperList').append(aswperHTML);
						
					});
				} else {
					if(data.aswperText != ''){
						modalForm.find('input[name="aswperText"]').val(data.aswperText);
						if(data.aswperScoreAt == 'Y'){
							modalForm.find('input[name="aswperText"]').parent('.aswperArea').addClass('qestnPoint').after('<input type="text" name="aswperScore" class="form-control qestnPointInput onlyNum"  value="'+data.aswperScore+'">');
						}
					}
				}
			},
			setOptnModal : function(){
				let htmlStr = '<div class="form-group">'
							+'	<h4>옵션 설정</h4>'
							+'</div>'
							+qestn2RadioBtnSelectForm('필수 여부', 'qestnEssntl', '필수', '필수아님', '필수')
				let html = $(htmlStr);
				return html;
			},
			setOptnModalData : function(data, modalForm){
				modalForm.find('#qestnSj').val(data.qestnSj);
				modalForm.find('#qestnHpcm').val(data.qestnHpcm);
				modalForm.find('#qestnEssntlAt'+data.qestnEssntlAt).prop('checked', true);
				modalForm.find('#aswperEtcAt'+data.aswperEtcAt).prop('checked', true);

				if(data.aswperEtcAt == 'Y'){
					//마지막 기타는 기타설정에 의한 기타
					let etcAswperEl = modalForm.find('#aswperList > .row:last');
					let etcAswperElClone = etcAswperEl.clone();
					etcAswperElClone.find('.deleteAswper').remove();
					etcAswperElClone.find('input[name="aswperText"]').addClass('etcAswper').attr('readonly','readonly');
					modalForm.find('#aswperList').after(etcAswperElClone);
					etcAswperEl.remove();
				}
			},
			setView : function(data){
				let defaultView = $(defaultQestnViewHTMLStr);
				
				let view = setCommonViewData(data, defaultView);
				
				if(Array.isArray(data.aswperText)){
					$.each(data.aswperText, function(i, v){
						view.find('.form-group').append('<label class="col-auto qestn_radio1">'+
															'<input type="checkbox" class="m-3" name="'+data.qestnId+'_check" disabled="disabled"> ' + v +
														'</label>');
					});
				
				} else {
					view.find('.form-group').append('<label class="col-auto qestn_radio1">'+
														'<input type="checkbox" class="m-3" name="'+data.qestnId+'_check" disabled="disabled"> ' + data.aswperText +
													'</label>');
				}
				
				view.data('qestnData', data);
				return view;
			},
			setViewData : function(){
				
			},
			getModalData : function(form){
				//필수 체크
				if(form.find('#qestnSj').val() == ''){
					Swal.fire('확인', '문항 질문을 입력해주세요.', 'warning');
					return null;
				}
				let check = false;
				$.each(form.find('input[name="aswperText"]'), function(i, v){
					if($.trim($(this).val()) == ''){
						check = true;
						return false;
					}
				});
				
				if(form.find('input[name="aswperScoreAt"]:checked').val() == 'Y'){
					$.each(form.find('input[name="aswperScore"]'), function(i, v){
						if($.trim($(this).val()) == ''){
							check = true;
							return false;
						}
					});
				}
				
				if(check){
					Swal.fire('확인', '답안값 또는 답안점수를 입력해주세요.', 'warning');
					return null;
				}
				//데이터 json으로 파싱
				let formDataJson = formToJSON(form[0]);
				console.log(formDataJson);
				return formDataJson;
			}
		},
		select : {
			setAswperModal : function(){
				let htmlStr = '<div class="form-group">'
							+'	<h4>답안 설정'
							+'		<a href="javascript:void(0);" class="btn btn-sm btn-info right" id="addAswperBtn">추가</a>'
//						    +'		<a href="javascript:void(0);" class="btn btn-sm btn-info" id="addAllAswperBtn">일괄입력</a>'
							+'	</h4>'
							+'</div>'
							+'<div class="form-group">'
							+'	<div id="aswperList">'
							+'		<div class="row mb-1">'
							+'			<div class="col aswperArea">'
							+'				<input type="text" name="aswperText" class="form-control" placeholder="값을 입력하세요">'
							+'			</div>'
							+'		</div>'
							+'	</div>'
							+'</div>'
							+qestn2RadioBtnSelectForm('답안 점수 여부', 'aswperScore', '사용', '사용안함', '사용안함');
//							+qestn2RadioBtnSelectForm('기타 여부', 'aswperEtc', '사용', '사용안함', '사용안함');
				let html = $(htmlStr);
				aswperHandlingAction(html);
				dragulaList2 = dragula([html.find('#aswperList')[0]]);
				
				return html;
			},
			setAswperModalData : function(data, modalForm){
				modalForm.find('label').removeClass('active');
				modalForm.find('#aswperScoreAt'+data.aswperScoreAt).prop('checked', true);

				if(Array.isArray(data.aswperText)){
					modalForm.find('#aswperList').empty();
					$.each(data.aswperText, function(i, v){
						let aswperHTML = $('<div class="row mb-1"><div class="col aswperArea">'
								+'			<input type="text" name="aswperText" class="form-control" placeholder="값을 입력하세요" value="'+v+'">'
								+'		</div></div>');
						if(data.aswperScoreAt == 'Y'){
							aswperHTML.find('.aswperArea').addClass('qestnPoint').after('<input type="text" name="aswperScore" class="form-control qestnPointInput onlyNum"  value="'+data.aswperScore[i]+'">');
						}
						if(i != 0){
							aswperHTML.prepend('<a class="btn btn-sm btn-danger deleteAswper">삭제</a>');
						}
						modalForm.find('#aswperList').append(aswperHTML);
						
					});
				} else {
					if(data.aswperText != ''){
						modalForm.find('input[name="aswperText"]').val(data.aswperText);
						if(data.aswperScoreAt == 'Y'){
							modalForm.find('input[name="aswperText"]').parent('.aswperArea').addClass('qestnPoint').after('<input type="text" name="aswperScore" class="form-control qestnPointInput onlyNum"  value="'+data.aswperScore+'">');
						}
					}
				}
			},
			setOptnModal : function(){
				let htmlStr = '<div class="form-group">'
							+'	<h4>옵션 설정</h4>'
							+'</div>'
							+qestn2RadioBtnSelectForm('필수 여부', 'qestnEssntl', '필수', '필수아님', '필수')
				let html = $(htmlStr);
				return html;
			},
			setOptnModalData : function(data, modalForm){
				modalForm.find('#qestnSj').val(data.qestnSj);
				modalForm.find('#qestnHpcm').val(data.qestnHpcm);
				modalForm.find('#qestnEssntlAt'+data.qestnEssntlAt).prop('checked', true);
//				modalForm.find('#aswperEtcAt'+data.aswperEtcAt).prop('checked', true);
				
//				if(data.aswperEtcAt == 'Y'){
//					//마지막 기타는 기타설정에 의한 기타
//					let etcAswperEl = modalForm.find('#aswperList > .row:last');
//					let etcAswperElClone = etcAswperEl.clone();
//					etcAswperElClone.find('.deleteAswper').remove();
//					etcAswperElClone.find('input[name="aswperText"]').attr('readonly','readonly');
//					modalForm.find('#aswperList').after(etcAswperElClone);
//					etcAswperEl.remove();
//				}
			},
			setView : function(data){
				let defaultView = $(defaultQestnViewHTMLStr);
				
				let view = setCommonViewData(data, defaultView);
				
				let contentsEl = '<select class="form-control qestn_answer" disabled="disabled">';
				if(Array.isArray(data.aswperText)){
					$.each(data.aswperText, function(i, v){
						contentsEl += '<option value="">'+v+'</option>';
					});
				} else {
					contentsEl += '<option value="">'+data.aswperText+'</option>';
				}
				contentsEl += '</select>';
				
				view.find('.form-group').append(contentsEl);
				view.data('qestnData', data);
				return view;
			},
			setViewData : function(){
				
			},
			getModalData : function(form){
				//필수 체크
				if(form.find('#qestnSj').val() == ''){
					Swal.fire('확인', '문항 질문을 입력해주세요.', 'warning');
					return null;
				}
				let check = false;
				$.each(form.find('input[name="aswperText"]'), function(i, v){
					if($.trim($(this).val()) == ''){
						check = true;
						return false;
					}
				});
				
				if(form.find('input[name="aswperScoreAt"]:checked').val() == 'Y'){
					$.each(form.find('input[name="aswperScore"]'), function(i, v){
						if($.trim($(this).val()) == ''){
							check = true;
							return false;
						}
					});
				}
				
				if(check){
					Swal.fire('확인', '답안값 또는 답안점수를 입력해주세요.', 'warning');
					return null;
				}
				//데이터 json으로 파싱
				let formDataJson = formToJSON(form[0]);
				console.log(formDataJson);
				return formDataJson;
			}
		},
		tel : {
			setAswperModal : function(){
				return null;
			},
			setAswperModalData : function(){
				return null;
			},
			setOptnModal : function(){
				let htmlStr = '<div class="form-group">'
							+'	<h4>옵션 설정</h4>'
							+'</div>'
							+qestn2RadioBtnSelectForm('필수 여부', 'qestnEssntl', '필수', '필수아님', '필수')
				let html = $(htmlStr);
				return html;
			},
			setOptnModalData : function(data, modalForm){
				modalForm.find('#qestnSj').val(data.qestnSj);
				modalForm.find('#qestnHpcm').val(data.qestnHpcm);
				modalForm.find('#qestnEssntlAt'+data.qestnEssntlAt).prop('checked', true);
			},
			setView : function(data){
				let defaultView = $(defaultQestnViewHTMLStr);
				
				let view = setCommonViewData(data, defaultView);
				let maxLng = '13';
				view.find('.form-group').append('<input class="form-control phoneNumber qestn_answer" placeholder="연락처를 입력하세요." '+(maxLng != '' ? 'maxlength='+maxLng : '')+' disabled="disabled">');
				view.data('qestnData', data);
				return view;
			},
			setViewData : function(){
				
			},
			getModalData : function(form){
				if(form.find('#qestnSj').val() == ''){
					Swal.fire('확인', '문항 질문을 입력해주세요.', 'warning');
					return null;
				}
				//데이터 json으로 파싱
				let formDataJson = formToJSON(form[0]);
				console.log(formDataJson);
				return formDataJson;
			}
		},
		address : {
			setAswperModal : function(){
				return null;
			},
			setAswperModalData : function(){
				return null;
			},
			setOptnModal : function(){
				let htmlStr = '<div class="form-group">'
							+'	<h4>옵션 설정</h4>'
							+'</div>'
							+qestn2RadioBtnSelectForm('필수 여부', 'qestnEssntl', '필수', '필수아님', '필수')
				let html = $(htmlStr);
				return html;
			},
			setOptnModalData : function(data, modalForm){
				modalForm.find('#qestnSj').val(data.qestnSj);
				modalForm.find('#qestnHpcm').val(data.qestnHpcm);
				modalForm.find('#qestnEssntlAt'+data.qestnEssntlAt).prop('checked', true);
			},
			setView : function(data){
				let defaultView = $(defaultQestnViewHTMLStr);
				
				let view = setCommonViewData(data, defaultView);
				view.find('.form-group').append('<div class="input-group">'+
													'<input type="text" id="addr" class="form-control qestn_answer" title="주소 입력" placeholder="주소를 입력하세요." readonly="true">'+
													'<div class="input-group-append">'+
														'<a href="javascript:void(0);" class="input-group-text qestn_address">주소 검색</a>'+
													'</div>'+
												'</div>');
				view.data('qestnData', data);
				return view;
			},
			setViewData : function(){
				
			},
			getModalData : function(form){
				if(form.find('#qestnSj').val() == ''){
					Swal.fire('확인', '문항 질문을 입력해주세요.', 'warning');
					return null;
				}
				//데이터 json으로 파싱
				let formDataJson = formToJSON(form[0]);
				console.log(formDataJson);
				return formDataJson;
			}
		},
		calen : {
			setAswperModal : function(){
				return null;
			},
			setAswperModalData : function(){
				return null;
			},
			setOptnModal : function(){
				let htmlStr = '<div class="form-group">'
							+'	<h4>옵션 설정</h4>'
							+'</div>'
							+qestn2RadioBtnSelectForm('필수 여부', 'qestnEssntl', '필수', '필수아님', '필수')
							+qestn2RadioBtnSelectForm('시간 포함 여부', 'optnTic', '포함', '포함아님', '포함')
				let html = $(htmlStr);
				return html;
			},
			setOptnModalData : function(data, modalForm){
				modalForm.find('#qestnSj').val(data.qestnSj);
				modalForm.find('#qestnHpcm').val(data.qestnHpcm);
				modalForm.find('#qestnEssntlAt'+data.qestnEssntlAt).prop('checked', true);
				modalForm.find('#optnTicAt'+data.optnTicAt).prop('checked', true);
			},
			setView : function(data){
				let defaultView = $(defaultQestnViewHTMLStr);
				
				let view = setCommonViewData(data, defaultView);
				
				if(data.optnTicAt == 'Y'){
					contentsEl = '<input type="text" class="form-control datepicker-time qestn_answer" placeholder="시간을 포함한 날짜를 선택" disabled="disabled">';
				} else {
					contentsEl = '<input type="text" class="form-control datepicker qestn_answer" placeholder="날짜를 선택" disabled="disabled">';
				}
				view.find('.form-group').append(contentsEl);
				view.data('qestnData', data);
				return view;
			},
			setViewData : function(){
				
			},
			getModalData : function(form){
				if(form.find('#qestnSj').val() == ''){
					Swal.fire('확인', '문항 질문을 입력해주세요.', 'warning');
					return null;
				}
				//데이터 json으로 파싱
				let formDataJson = formToJSON(form[0]);
				console.log(formDataJson);
				return formDataJson;
			}
		},
		time : {
			setAswperModal : function(){
				return null;
			},
			setAswperModalData : function(){
				return null;
			},
			setOptnModal : function(){
				let htmlStr = '<div class="form-group">'
							+'	<h4>옵션 설정</h4>'
							+'</div>'
							+qestn2RadioBtnSelectForm('필수 여부', 'qestnEssntl', '필수', '필수아님', '필수')
				let html = $(htmlStr);
				return html;
			},
			setOptnModalData : function(data, modalForm){
				modalForm.find('#qestnSj').val(data.qestnSj);
				modalForm.find('#qestnHpcm').val(data.qestnHpcm);
				modalForm.find('#qestnEssntlAt'+data.qestnEssntlAt).prop('checked', true);
			},
			setView : function(data){
				let defaultView = $(defaultQestnViewHTMLStr);
				
				let view = setCommonViewData(data, defaultView);
				
				view.find('.form-group').append('<input type="text" class="form-control timepicker24 qestn_answer" placeholder="시간을 입력하세요." disabled="disabled">');
				view.data('qestnData', data);
				return view;
			},
			setViewData : function(){
				
			},
			getModalData : function(form){
				if(form.find('#qestnSj').val() == ''){
					Swal.fire('확인', '문항 질문을 입력해주세요.', 'warning');
					return null;
				}
				//데이터 json으로 파싱
				let formDataJson = formToJSON(form[0]);
				console.log(formDataJson);
				return formDataJson;
			}
		},
		calenterm : {
			setAswperModal : function(){
				return null;
			},
			setAswperModalData : function(){
				return null;
			},
			setOptnModal : function(){
				let htmlStr = '<div class="form-group">'
							+'	<h4>옵션 설정</h4>'
							+'</div>'
							+qestn2RadioBtnSelectForm('필수 여부', 'qestnEssntl', '필수', '필수아님', '필수')
							+qestn2RadioBtnSelectForm('시간 포함 여부', 'optnTic', '포함', '포함아님', '포함')
				let html = $(htmlStr);
				return html;
			},
			setOptnModalData : function(data, modalForm){
				modalForm.find('#qestnSj').val(data.qestnSj);
				modalForm.find('#qestnHpcm').val(data.qestnHpcm);
				modalForm.find('#qestnEssntlAt'+data.qestnEssntlAt).prop('checked', true);
				modalForm.find('#optnTicAt'+data.optnTicAt).prop('checked', true);
			},
			setView : function(data){
				let defaultView = $(defaultQestnViewHTMLStr);
				
				let view = setCommonViewData(data, defaultView);
				
				var timeAtStr = (data.optnTicAt == 'Y' ? 'datepicker-time' : 'datepicker');
				var contentsEl = '';
				contentsEl += '<div class="input-group">';
				contentsEl += '	<div class="qestndate_wrap">';
				contentsEl += '		<span>';
				contentsEl += '			<input type="text" class="form-control '+timeAtStr+' qestndate" placeholder="날짜를 선택해주세요" disabled="disabled">';
				contentsEl += '		</span>';
				contentsEl += '	</div>';
				contentsEl += '	<em class="qestn_date">~</em>';
				contentsEl += '	<div class="qestndate_wrap">';
				contentsEl += '		<span>';
				contentsEl += '			<input type="text" class="form-control '+timeAtStr+' qestndate" placeholder="날짜를 선택해주세요" disabled="disabled">';
				contentsEl += '		</span>';
				contentsEl += '	</div>';
				contentsEl += '</div>';
				
				view.find('.form-group').append(contentsEl);
				view.data('qestnData', data);
				return view;
			},
			setViewData : function(){
				
			},
			getModalData : function(form){
				if(form.find('#qestnSj').val() == ''){
					Swal.fire('확인', '문항 질문을 입력해주세요.', 'warning');
					return null;
				}
				//데이터 json으로 파싱
				let formDataJson = formToJSON(form[0]);
				console.log(formDataJson);
				return formDataJson;
			}
		},
		file : {
			setAswperModal : function(){
				return null;
			},
			setAswperModalData : function(){
				return null;
			},
			setOptnModal : function(){
				let htmlStr = '<div class="form-group">'
							+'	<h4>옵션 설정</h4>'
							+'</div>'
							+qestn2RadioBtnSelectForm('필수 여부', 'qestnEssntl', '필수', '필수아님', '필수')
							+qestn2RadioBtnSelectForm('파일 확장자 제한 사용 여부', 'optnAfe', '사용', '사용안함', '사용')
							+'<div class="form-group" id="optnAfeValueArea">'
							+'	<label for="optnAfeValue" class="h5-no color-blue">허용 파일 확장자 (,로 구분) </label>'
							+'	<input type="text" id="optnAfeValue" name="optnAfeValue" placeholder="허용 파일 확장자를 입력하세요." class="form-control">'
							+'</div>'
							+qestn2RadioBtnSelectForm('업로드 파일 갯수 제한 사용 여부', 'optnMfc', '사용', '사용안함', '사용')
							+'<div class="form-group" id="optnMfcValueArea">'
							+'	<label for="optnMfcValue" class="h5-no color-blue">업로드 파일 최대 갯수</label>'
							+'	<input type="text" id="optnMfcValue" name="optnMfcValue" placeholder="업로드 파일 최대 갯수를 입력하세요." class="form-control onlyNum">'
							+'</div>';
				let html = $(htmlStr);
				html.on('change', 'input[name="optnAfeAt"]', function(){
					if($(this).val() == 'Y'){
						$('#optnAfeValueArea').show();
					} else {
						$('#optnAfeValueArea').hide();
						$('#optnAfeValueArea').find('#optnAfeValue').val('');
					}
				});
				html.on('change', 'input[name="optnMfcAt"]', function(){
					if($(this).val() == 'Y'){
						$('#optnMfcValueArea').show();
					} else {
						$('#optnMfcValueArea').hide();
						$('#optnMfcValueArea').find('#optnMfcValue').val('');
					}
				});
				
				return html;
			},
			setOptnModalData : function(data, modalForm){
				modalForm.find('#qestnSj').val(data.qestnSj);
				modalForm.find('#qestnHpcm').val(data.qestnHpcm);
				modalForm.find('#qestnEssntlAt'+data.qestnEssntlAt).prop('checked', true);
				modalForm.find('#optnAfeAt'+data.optnAfeAt).prop('checked', true);
				modalForm.find('#optnMfcAt'+data.optnMfcAt).prop('checked', true);
				
				if(data.optnAfeAt == 'N'){
					modalForm.find('#optnAfeValueArea').hide();
				} else {
					modalForm.find('#optnAfeValue').val(data.optnAfeValue);
				}
				if(data.optnMfcAt == 'N'){
					modalForm.find('#optnMfcValueArea').hide();
				} else {
					modalForm.find('#optnMfcValue').val(data.optnMfcValue);
				}
			},
			setView : function(data){
				let defaultView = $(defaultQestnViewHTMLStr);
				
				let view = setCommonViewData(data, defaultView);
				view.find('.form-group').append( '<div class="input-group mb-3">'
									            +'	<div class="custom-file">'
									            +'  	<input type="file" class="custom-file-input " id="inputGroupFile02" disabled="disabled">'
									            +'      <label class="custom-file-label qestn_file" for="inputGroupFile02">파일을 등록해주세요.</label>'
									            +'  </div>'
									            +'</div>');
				//파일 최대 갯수 및 허용 파일 확장자 체크 로직 필요
				view.data('qestnData', data);
				return view;
			},
			setViewData : function(){
				
			},
			getModalData : function(form){
				if(form.find('#qestnSj').val() == ''){
					Swal.fire('확인', '문항 질문을 입력해주세요.', 'warning');
					return null;
				}
				if(form.find('input[name="optnAfeAt"]:checked').val() == 'Y' && form.find('#optnAfeValue').val() == ''){
					Swal.fire('확인', '허용 파일 확장자를 입력해주세요.', 'warning');
					return null;
				}
				if(form.find('input[name="optnMfcAt"]:checked').val() == 'Y' && form.find('#optnMfcValue').val() == ''){
					Swal.fire('확인', '업로드 파일 최대 갯수를 입력해주세요.', 'warning');
					return null;
				}
				//데이터 json으로 파싱
				let formDataJson = formToJSON(form[0]);
				console.log(formDataJson);
				return formDataJson;
			}
		}
}

function fnAddAswperList(elem, type){
	let aswperHTML = $('<div class="row mb-1"><div class="col aswperArea">'
				+'			<input type="text" name="aswperText" class="form-control" placeholder="값을 입력하세요">'
				+'		</div></div>');
	if(type=='ETC'){
		aswperHTML.find('input').val('기타').attr('readonly', 'readonly').addClass('etcAswper');
	}
	
	if(elem.find('input[name="aswperScoreAt"]:checked').val() == 'Y'){
		aswperHTML.find('.col').addClass('qestnPoint').after('<input type="text" name="aswperScore" class="form-control qestnPointInput onlyNum">');
	}
	if(type!='ETC'){
		aswperHTML.prepend('<a class="btn btn-sm btn-danger deleteAswper">삭제</a>');
	}
	if(type != 'ETC'){
		$('#aswperList').append(aswperHTML);
	} else {
		$('#aswperList').after(aswperHTML);
	}
}

function aswperHandlingAction(elem){
	elem.on('change', 'input[name="aswperScoreAt"]', function(){
		if($(this).val() == 'Y'){
			$('.aswperArea').each(function(){
				$(this).addClass('qestnPoint');
				$(this).after('<input type="text" name="aswperScore" class="form-control qestnPointInput onlyNum">');
			});
		} else {
			$('.aswperArea').each(function(){
				$(this).removeClass('qestnPoint');
				$(this).next().remove();
			});
		}
	});
	
	elem.on('click', '#addAswperBtn', function(){
		if(qestnInitOptions.maxAswperCnt <= elem.find('input[name="aswperText"]').length){
			Swal.fire('확인', '최대 '+qestnInitOptions.maxAswperCnt+'개까지 가능합니다.', 'warning');
			return ;
		}
		fnAddAswperList(elem);
	});
	
	elem.on('change', 'input[name="aswperEtcAt"]', function(){
		if($(this).val() == 'Y'){
			fnAddAswperList(elem, 'ETC');
		} else {
			$('.etcAswper').parents('.row')[0].remove();
		}
	});
	
	
//	elem.on('click', '#addAllAswperBtn', function(){
//		let inputValues = "";
//		elem.find('input[name="aswperText"]').each(function(i, v){
//			inputValues += $(this).val() + "\n";
//		});
//		
//		Swal.fire({
//			title: '일괄 입력',
//			text : '답안을 한 줄에 하나씩 입력할 수 있습니다.',
//	  		input: 'textarea',
//	  		inputValue : inputValues,
//	  		showCancelButton: true,
//	  		confirmButtonText: '수정',
//	  		allowOutsideClick: false,
//		}).then((result) => {
//	  		if (result.isConfirmed) {
//	  			if(result.value != ''){
//	  				let values = result.value.split('\n');
//	  				$('#aswperList').empty();
//	  				$.each(values, function(i, v){
//	  					let aswperHTML = $('<div class="row mb-1"><div class="col">'
//	  							+'			<input type="text" name="aswperText" class="form-control" placeholder="값을 입력하세요">'
//	  							+'		</div></div>');
//	  					if(elem.find('input[name="aswperScoreAt"]:checked').val() == 'Y'){
//	  						aswperHTML.find('.col').addClass('qestnPoint').after('<input type="text" name="aswperScore" class="form-control qestnPointInput onlyNum">');
//	  					}
//	  					if(i != 0){
//	  						aswperHTML.prepend('<a class="btn btn-danger deleteAswper ml-3">삭제</a>');
//	  					}
//	  					$('#aswperList').append(aswperHTML);
//	  				})
//	  			} 
//	  		}
//		});
//	});
	
	elem.on('click', '.deleteAswper', function (){
		$(this).parent('.row').remove();
	});
}

function setCommonViewData(data, view){
	view.attr('id', data.qestnId);
	view.find('.card-title').text(xssFiltering(data.qestnSj));
	if(data.qestnEssntlAt == 'Y'){
		view.find('.card-title').append('&nbsp;<span class="pilsu">*</span>');
	}
	if(data.qestnHpcm != ''){
		view.find('.card-subtitle').text(xssFiltering(data.qestnHpcm));
	} else {
		view.find('.card-subtitle').remove();
	}
	
	return view;
}

function qestn2RadioBtnSelectForm(title, elName, yText, nText, activeValue){
	let html = '';
	if(activeValue == null || activeValue == undefined){
		activeValue = yText;
	}
	
	html += '<div class="form-group">'+
			'	<label for="'+elName+'AtY" class="h5-no color-blue">'+title+'</label>'+
			'	<div class="check-type1">'+
			'   	<input type="radio" name="'+elName+'At" id="'+elName+'AtY" value="Y" '+(activeValue == yText ? 'checked="checked"' : '')+'">'+
			'   	<label for="'+elName+'AtY" class="btn radio-type1 first">'+
			'			<span class="radio_check"></span>'+yText+
			'  		</label>'+
			'   	<input type="radio" name="'+elName+'At" id="'+elName+'AtN" value="N" '+(activeValue == nText ? 'checked="checked"' : '')+'>'+
			'   	<label for="'+elName+'AtN" class="btn radio-type1">'+
			'			<span class="radio_check"></span>'+nText+
			'   	</label>'+
			'	</div>'+
			'</div>';
	
	return html;
}








