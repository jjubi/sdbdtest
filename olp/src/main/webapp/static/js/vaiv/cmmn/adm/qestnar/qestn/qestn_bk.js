/**
 * 설문조사 문항 만들기 javascript v1.1
 * 
 * Copyright By vaiv
 * 
 * @author hm
 * 
 * Date : 2020.12.31
 */
(function($){
	var qestnInitOptions = '';
	
	var makeQestnType_ = {
			/**
			 * 문항 단답형 text (input type=text)
			 * */
			text : {
				makeModal : function(){
					let qestnSettingBodyEl = $('<form id="qestnSettingForm"></form>');
					//설문조사 공통 설정 내용
					qestnSettingBodyEl.append(makeQestnFn.makeQestnSettingCommonHtml());
					return qestnSettingBodyEl;
				},
				makeView : function(jsonData){
					let qestnItemEl = '';
					let contentsEl = '<input type="text" class="form-control" placeholder="답변을 입력하세요." disabled="disabled">';
					qestnItemEl = makeQestnFn.makeQestnItemEl(jsonData, contentsEl);
					return qestnItemEl;
				},
				setModal : function(jsonData, modalDiv){
					modalDiv.find('input[name="qestnSj"]').val(jsonData.qestnSj);
					modalDiv.find('textarea[name="qestnCn"]').val(jsonData.qestnCn);
					modalDiv.find('label[for="requireYn'+jsonData.require+'"]').click();
				}
			},
			/**
			 * 문항 장문형 textarea (textarea)
			 * */
			textarea : {
				makeModal : function(){
					let qestnSettingBodyEl = $('<form id="qestnSettingForm"></form>');
					//설문조사 공통 설정 내용
					qestnSettingBodyEl.append(makeQestnFn.makeQestnSettingCommonHtml());
					return qestnSettingBodyEl;
				},
				makeView : function(jsonData){
					let qestnItemEl = '';
					let contentsEl = '<textarea class="form-control" row="3" col="10" placeholder="답변을 입력하세요." disabled="disabled"></textarea>';
					qestnItemEl = makeQestnFn.makeQestnItemEl(jsonData, contentsEl);
					return qestnItemEl;
				},
				setModal : function(jsonData, modalDiv){
					modalDiv.find('input[name="qestnSj"]').val(jsonData.qestnSj);
					modalDiv.find('textarea[name="qestnCn"]').val(jsonData.qestnCn);
					modalDiv.find('label[for="requireYn'+jsonData.require+'"]').click();
				}
			},
			/**
			 * 문항 객관식 선택 RADIO
			 * */
			radio : {
				makeModal : function(){
					var qestnSettingBodyEl = $('<form id="qestnSettingForm"></form>');
					qestnSettingBodyEl.append(makeQestnFn.makeQestnSettingCommonHtml());
					qestnSettingBodyEl.append(makeQestnFn.makeQestnSettingSelectOptionHtml());
					qestnSettingBodyEl.append(makeQestnFn.makeQestnSettingSelectOptionEtcHtml());
					return qestnSettingBodyEl;
				},
				makeView : function(jsonData){
					let qestnItemEl = '';
					
					var contentsEl = '';
					$.each(jsonData.qestnOption.optionText, function(i, v){
						contentsEl += '<label class="col-auto">'+
										'<input type="radio" class="m-3" name="'+jsonData.qestnId+'_radio" value="'+jsonData.qestnOption.optionValue[i]+'" disabled="disabled">'+v;
						if(jsonData.qestnOption.optionImage.length > 0 && jsonData.qestnOption.optionImage[i] != ''){
							contentsEl += 		'<br><img src="'+jsonData.qestnOption.optionImage[i]+'" width="100" height="100">';
						}
						contentsEl += '</label>';
					});
					
					//기타 추가하기
					if(jsonData.qestnOption.etc == 'Y'){
						contentsEl += '<label class="col-auto">'+
										'<input type="radio" class="m-3" name="'+jsonData.qestnId+'_radio" value="" disabled="disabled">기타'+
										'<input type="text" name="'+jsonData.qestnId+'_radio_etc_val" disabled="disabled">'+
									'</label>';
					}
						
					qestnItemEl = makeQestnFn.makeQestnItemEl(jsonData, contentsEl);
					return qestnItemEl;
				},
				setModal : function(jsonData, modalDiv){
					modalDiv.find('input[name="qestnSj"]').val(jsonData.qestnSj);
					modalDiv.find('textarea[name="qestnCn"]').val(jsonData.qestnCn);
					modalDiv.find('label[for="requireYn'+jsonData.require+'"]').click();
					let qestnOptions = jsonData.qestnOption; 
					$.each(qestnOptions.optionText, function(i, v){
						if(i != 0){
							modalDiv.find('#addOptions').click();
						}
						modalDiv.find('input[name="optionText"]').eq(i).val(v);
						modalDiv.find('input[name="optionValue"]').eq(i).val(qestnOptions.optionValue[i]);
						if(qestnOptions.optionImage[i] != ""){
							modalDiv.find('.optionsImageBtn').eq(i).attr('data-original-title', '<img src=\''+qestnOptions.optionImage[i]+'\' alt=\'등록 이미지\' height=\'100px\' />').tooltip('hide');
							modalDiv.find('input[name="fileValue"]').eq(i).val(qestnOptions.optionImage[i]);
						}
					});
					modalDiv.find('label[for="etcYn'+qestnOptions.etc+'"]').click();
					modalDiv.find('[data-toggle="tooltip"]').tooltip({"html":true});
				}
			},
			/**
			 * 문항 체크박스 선택 CHECK
			 * */
			checkbox : {
				makeModal : function(){
					var qestnSettingBodyEl = $('<form id="qestnSettingForm"></form>');
					qestnSettingBodyEl.append(makeQestnFn.makeQestnSettingCommonHtml());
					qestnSettingBodyEl.append(makeQestnFn.makeQestnSettingSelectOptionHtml());
					qestnSettingBodyEl.append(makeQestnFn.makeQestnSettingSelectOptionEtcHtml());
					return qestnSettingBodyEl;
				},
				makeView : function(jsonData){
					let qestnItemEl = '';
					
					var contentsEl = '';
					$.each(jsonData.qestnOption.optionText, function(i, v){
						contentsEl += '<label class="col-auto">'+
										'<input type="checkbox" class="m-3" name="'+jsonData.qestnId+'_check" value="'+jsonData.qestnOption.optionValue[i]+'" disabled="disabled">'+v;
						if(jsonData.qestnOption.optionImage.length > 0 && jsonData.qestnOption.optionImage[i] != ''){
							contentsEl += 		'<br><img src="'+jsonData.qestnOption.optionImage[i]+'" width="100" height="100">';
						}
						contentsEl += '</label>';
					});
					
					//기타 추가하기
					if(jsonData.qestnOption.etc == 'Y'){
						contentsEl += '<label class="col-auto">'+
										'<input type="radio" class="m-3" name="'+jsonData.qestnId+'_check" value="" disabled="disabled">기타'+
										'<input type="text" name="'+jsonData.qestnId+'_check_etc_val" disabled="disabled">'+
									'</label>';
					}
					
					qestnItemEl = makeQestnFn.makeQestnItemEl(jsonData, contentsEl);
					return qestnItemEl;
				},
				setModal : function(jsonData, modalDiv){
					modalDiv.find('input[name="qestnSj"]').val(jsonData.qestnSj);
					modalDiv.find('textarea[name="qestnCn"]').val(jsonData.qestnCn);
					modalDiv.find('label[for="requireYn'+jsonData.require+'"]').click();
					let qestnOptions = jsonData.qestnOption; 
					$.each(qestnOptions.optionText, function(i, v){
						if(i != 0){
							modalDiv.find('#addOptions').click();
						}
						modalDiv.find('input[name="optionText"]').eq(i).val(v);
						modalDiv.find('input[name="optionValue"]').eq(i).val(qestnOptions.optionValue[i]);
						if(qestnOptions.optionImage[i] != ""){
							modalDiv.find('.optionsImageBtn').eq(i).attr('data-original-title', '<img src=\''+qestnOptions.optionImage[i]+'\' alt=\'등록 이미지\' height=\'100px\' />').tooltip('hide');
							modalDiv.find('input[name="fileValue"]').eq(i).val(qestnOptions.optionImage[i]);
						}
					});
					modalDiv.find('label[for="etcYn'+qestnOptions.etc+'"]').click();
					modalDiv.find('[data-toggle="tooltip"]').tooltip({"html":true});
				}
			},
			/**
			 * 문항 드롭다운 SELECT
			 * */
			select : {
				makeModal : function(){
					var qestnSettingBodyEl = $('<form id="qestnSettingForm"></form>');
					qestnSettingBodyEl.append(makeQestnFn.makeQestnSettingCommonHtml());
					qestnSettingBodyEl.append(makeQestnFn.makeQestnSettingSelectOptionHtml());
					return qestnSettingBodyEl;
				},
				makeView : function(jsonData){
					let qestnItemEl = '';
					
					var contentsEl = '<select class="form-control" disabled="disabled">';
					$.each(jsonData.qestnOption.optionText, function(i, v){
						contentsEl += '<option value="'+jsonData.qestnOption.optionValue[i]+'">'+v+'</option>';
					});
					contentsEl += '</select>';
					qestnItemEl = makeQestnFn.makeQestnItemEl(jsonData, contentsEl);
					return qestnItemEl;
				},
				setModal : function(jsonData, modalDiv){
					modalDiv.find('input[name="qestnSj"]').val(jsonData.qestnSj);
					modalDiv.find('textarea[name="qestnCn"]').val(jsonData.qestnCn);
					modalDiv.find('label[for="requireYn'+jsonData.require+'"]').click();
					let qestnOptions = jsonData.qestnOption; 
					$.each(qestnOptions.optionText, function(i, v){
						if(i != 0){
							modalDiv.find('#addOptions').click();
						}
						modalDiv.find('input[name="optionText"]').eq(i).val(v);
						modalDiv.find('input[name="optionValue"]').eq(i).val(qestnOptions.optionValue[i]);
						if(qestnOptions.optionImage[i] != ""){
							modalDiv.find('.optionsImageBtn').eq(i).attr('data-original-title', '<img src=\''+qestnOptions.optionImage[i]+'\' alt=\'등록 이미지\' height=\'100px\' />').tooltip('hide');
							modalDiv.find('input[name="fileValue"]').eq(i).val(qestnOptions.optionImage[i]);
						}
					});
					modalDiv.find('[data-toggle="tooltip"]').tooltip({"html":true});
				}
			},
			/**
			 * 문항 연락처 TEL
			 * */
			tel : {
				makeModal : function(){
					var qestnSettingBodyEl = $('<form id="qestnSettingForm"></form>');
					qestnSettingBodyEl.append(makeQestnFn.makeQestnSettingCommonHtml());
					return qestnSettingBodyEl;
				},
				makeView : function(jsonData){
					let qestnItemEl = '';
					let contentsEl = '<input type="text" class="form-control phoneNumber" placeholder="연락처를 입력하세요." disabled="disabled" maxlength="13">';
					qestnItemEl = makeQestnFn.makeQestnItemEl(jsonData, contentsEl);
					return qestnItemEl;
				},
				setModal : function(jsonData, modalDiv){
					modalDiv.find('input[name="qestnSj"]').val(jsonData.qestnSj);
					modalDiv.find('textarea[name="qestnCn"]').val(jsonData.qestnCn);
					modalDiv.find('label[for="requireYn'+jsonData.require+'"]').click();
				}
			},
			/**
			 * 문항 주소 ADDRESS
			 * */
			address : {
				makeModal : function(){
					var qestnSettingBodyEl = $('<form id="qestnSettingForm"></form>');
					qestnSettingBodyEl.append(makeQestnFn.makeQestnSettingCommonHtml());
					return qestnSettingBodyEl;
				},
				makeView : function(jsonData){
					let qestnItemEl = '';
					let contentsEl = '<div class="input-group">'+
										'<input type="text" id="addr" class="form-control" title="${title} 입력" size="40" readonly="true">'+
										'<div class="input-group-append">'+
											'<a href="javascript:searchAddr(\'\',\'addr\',\'\',\'\');" class="input-group-text">주소 검색</a>'+
										'</div>'+
									'</div>';
					qestnItemEl = makeQestnFn.makeQestnItemEl(jsonData, contentsEl);
					return qestnItemEl;
				},
				setModal : function(jsonData, modalDiv){
					modalDiv.find('input[name="qestnSj"]').val(jsonData.qestnSj);
					modalDiv.find('textarea[name="qestnCn"]').val(jsonData.qestnCn);
					modalDiv.find('label[for="requireYn'+jsonData.require+'"]').click();
				}
			},
			/**
			 * 문항 날짜 CALEN
			 * */
			calen : {
				makeModal : function(){
					var qestnSettingBodyEl = $('<form id="qestnSettingForm"></form>');
					qestnSettingBodyEl.append(makeQestnFn.makeQestnSettingCommonHtml());
					qestnSettingBodyEl.append($(qestn2RadioBtnSelectForm('시간 포함여부', 'time', '포함', '미포함')));
					return qestnSettingBodyEl;
				},
				makeView : function(jsonData){
					let qestnItemEl = '';
					var timeYn = jsonData.qestnOption.time;
					var contentsEl = '';
					if(timeYn == 'Y'){
						contentsEl = '<input type="text" class="form-control datepicker-time" placeholder="" disabled="disabled">';
					} else {
						contentsEl = '<input type="text" class="form-control datepicker" placeholder="" disabled="disabled">';
					}
					qestnItemEl = makeQestnFn.makeQestnItemEl(jsonData, contentsEl);
					return qestnItemEl;
				},
				setModal : function(jsonData, modalDiv){
					modalDiv.find('input[name="qestnSj"]').val(jsonData.qestnSj);
					modalDiv.find('textarea[name="qestnCn"]').val(jsonData.qestnCn);
					modalDiv.find('label[for="requireYn'+jsonData.require+'"]').click();
					modalDiv.find('label[for="timeYn'+jsonData.qestnOption.time+'"]').click();
				}
			},
			/**
			 * 문항 시간 TIME
			 * */
			time : {
				makeModal : function(){
					var qestnSettingBodyEl = $('<form id="qestnSettingForm"></form>');
					qestnSettingBodyEl.append(makeQestnFn.makeQestnSettingCommonHtml());
					return qestnSettingBodyEl;
				},
				makeView : function(jsonData){
					let qestnItemEl = '';
					let contentsEl = '<input type="text" class="form-control timepicker24" placeholder="" disabled="disabled">';
					qestnItemEl = makeQestnFn.makeQestnItemEl(jsonData, contentsEl);
					return qestnItemEl;
				},
				setModal : function(jsonData, modalDiv){
					modalDiv.find('input[name="qestnSj"]').val(jsonData.qestnSj);
					modalDiv.find('textarea[name="qestnCn"]').val(jsonData.qestnCn);
					modalDiv.find('label[for="requireYn'+jsonData.require+'"]').click();
				}
			},
			/**
			 * 문항 기간 CALENTERM
			 * */
			calenterm : {
				makeModal : function(){
					var qestnSettingBodyEl = $('<form id="qestnSettingForm"></form>');
					qestnSettingBodyEl.append(makeQestnFn.makeQestnSettingCommonHtml());
					qestnSettingBodyEl.append($(qestn2RadioBtnSelectForm('시간 포함여부', 'time', '포함', '미포함')));
					return qestnSettingBodyEl;
				},
				makeView : function(jsonData){
					var qestnItemEl = '';
					var timeYn = jsonData.qestnOption.time;
					var timeYnStr = (timeYn == 'Y' ? 'datepicker-time' : 'datepicker');
					var contentsEl = '';
					contentsEl += '<div class="input-group">';
					contentsEl += '	<input type="text" class="form-control '+timeYnStr+'" placeholder="" disabled="disabled">';
					contentsEl += '	<div class="input-group-addon">~</div>';
					contentsEl += '	<input type="text" class="form-control '+timeYnStr+'" placeholder="" disabled="disabled">';
					contentsEl += '</div>';
					qestnItemEl = makeQestnFn.makeQestnItemEl(jsonData, contentsEl);
					return qestnItemEl;
				},
				setModal : function(jsonData, modalDiv){
					modalDiv.find('input[name="qestnSj"]').val(jsonData.qestnSj);
					modalDiv.find('textarea[name="qestnCn"]').val(jsonData.qestnCn);
					modalDiv.find('label[for="requireYn'+jsonData.require+'"]').click();
					modalDiv.find('label[for="timeYn'+jsonData.qestnOption.time+'"]').click();
				}
			},
			/**
			 * 문항 파일 FILE
			 * */
			file : {
				makeModal : function(){
					var qestnSettingBodyEl = $('<form id="qestnSettingForm"></form>');
					qestnSettingBodyEl.append(makeQestnFn.makeQestnSettingCommonHtml());
					return qestnSettingBodyEl;
				},
				makeView : function(jsonData){
					let qestnItemEl = '';
					let contentsEl = '<input type="file" class="form-control" disabled="disabled">';
					qestnItemEl = makeQestnFn.makeQestnItemEl(jsonData, contentsEl);
					return qestnItemEl;
				},
				setModal : function(jsonData, modalDiv){
					modalDiv.find('input[name="qestnSj"]').val(jsonData.qestnSj);
					modalDiv.find('textarea[name="qestnCn"]').val(jsonData.qestnCn);
					modalDiv.find('label[for="requireYn'+jsonData.require+'"]').click();
				}
			}
	}
	
	var makeQestnFn = {
			makeQestnSettingCommonHtml : function(){
				let html = '';
				//질문
				html += '<div class="col mb-3">'+
							'<label for="qestnSj">문항 질문 <span class="pilsu">*</span></label>'+
							'<input type="text" id="qestnSj" name="qestnSj" placeholder="질문을 입력하세요." class="form-control" required="true">'+
						'</div>';
				//안내문
				html += '<div class="col mb-3">'+
								'<label for="qestnCn">문항 안내문</label>'+
								'<textarea rows="3" cols="10" id="qestnCn" name="qestnCn" placeholder="안내문을 입력하세요." class="form-control"></textarea>'+
							'</div>';
				//필수 여부
				html += qestn2RadioBtnSelectForm('필수 여부', 'require', '필수', '필수아님');
				return $(html);
			},
			makeQestnSettingSelectOptionHtml : function(){
				let optionEl = '';
				optionEl += '<div class="col mb-3" id="options">'+
								'<div class="row">'+
									'<label class="col">문항 선택지 </label>'+
									'<div class="col-auto">'+
										'<a href="javascript:;" id="addOptions" class="btn btn-sm btn-info"><i class="fas fa-plus"></i> 추가</a>'+
									'</div>'+
								'</div>'+
							'</div>';
				
				optionEl = $(optionEl);
				optionEl.append(makeQestnFn.makeQestnSelectOptionItemHtml());
				optionEl.find('#addOptions').on('click', function(){
					if(optionEl.find('.optionDiv').length < qestnInitOptions.maxOptions){
						optionEl.append(makeQestnFn.makeQestnSelectOptionItemHtml());
					} else {
						Swal.fire('확인', '문항 선택지는 최대 '+qestnInitOptions.maxOptions+'입니다.', 'warning');
					}
				});
				return optionEl;
			},
			makeQestnSelectOptionItemHtml : function(){
				let optionItemEl = '';
				optionItemEl += '<div class="row mt-3 optionDiv">'+
									'<div class="col">'+
										'<label>Text <span class="pilsu">*</span></label>'+
										'<input type="text" name="optionText" placeholder="Text을 입력하세요." required="true" class="form-control">'+
									'</div>'+
									'<div class="col">'+
										'<label>Value <span class="pilsu">*</span></label>'+
										'<input type="text" name="optionValue" placeholder="Value을 입력하세요." required="true" class="form-control">'+
									'</div>'+
									'<div class="col-auto">'+
										'<label>Image </label>'+
										'<a href="javascript:;" class="form-control optionsImageBtn" data-toggle="tooltip" data-placement="top" title="이미지 등록"><i class="far fa-image"></i></a>'+
										'<input type="hidden" name="fileValue">'+
										'<input type="file" class="sr-only optionImageInputFile">'+
									'</div>'+
									'<div class="col-auto">'+
										'<label>Delete </label>'+
										'<a href="javascript:;" class="form-control optionsDeleteBtn" data-toggle="tooltip" data-placement="top" title="옵션 삭제"><i class="far fa-trash-alt"></i></a>'+
									'</div>'+
								'</div>';
				optionItemEl = $(optionItemEl);
				optionItemEl.on('click', '.optionsImageBtn', function(){
					optionItemEl.find('.optionImageInputFile').click();
				});
				optionItemEl.on('change', '.optionImageInputFile', function(e){
					let files = e.target.files;
					let filesArr = Array.prototype.slice.call(files);
					
					filesArr.forEach(function(f){
						if(!f.type.match("image.*")){
							Swal.fire('확인', '이미지만 가능합니다.', 'warning');
							return ;
						}
						let fNm = f.name;
						let reader = new FileReader();
						reader.onload = function(e){
							optionItemEl.find('.optionsImageBtn').attr('data-original-title', '<img src=\''+e.target.result+'\' alt=\''+fNm+'\' height=\'100px\' />').tooltip('hide').tooltip('show');
							optionItemEl.find('input[name="fileValue"]').val(e.target.result);
						}
						reader.readAsDataURL(f);
					});
				});
				optionItemEl.on('click', '.optionsDeleteBtn', function(){
					if($('#options').find('.optionsDeleteBtn').length > 1){
						Swal.fire({
							icon : 'info',
							title: '삭제 하시겠습니까?',
							showCancelButton: true,
							confirmButtonText: '삭제',
						}).then((result) => {
							if (result.isConfirmed) {
								optionItemEl.remove();
							} 
						});
					} else {
						Swal.fire('확인', '최소 1개의 문항이 있어야됩니다.', 'warning');
					}
				});
				optionItemEl.find('[data-toggle="tooltip"]').tooltip({"html":true});
				
				return optionItemEl;
			},
			makeQestnSettingSelectOptionEtcHtml : function(){
				let html = '';
				html += qestn2RadioBtnSelectForm('기타 사용여부', 'etc', '사용', '사용안함');
				return $(html);
			},
			makeQestnItemEl : function(jsonData, contentsEl){
				let qestnItemEl = $('<div class="card" id="'+jsonData.qestnId+'"></div>');
				qestnItemEl.data('itemData', jsonData);
				
				let qestnItemBody = $('<div class="card-body"></div>');
				let qestnItemBodySj = $('<h5 class="card-title">'+(jsonData.qestnSj).replace(/</g, "&lt;").replace(/>/g, "&gt;")+'</h5>');
				if(jsonData.require == 'Y'){
					qestnItemBodySj.append('&nbsp;<span class="pilsu">*</span>');
				}
				qestnItemBody.append(qestnItemBodySj);
				if(jsonData.qestnCn != '' && jsonData.qestnCn != null){
					let qestnItemSubCn = $('<h6 class="card-subtitle mt-2 mb-3">'+jsonData.qestnCn+'</h6>');
					qestnItemBody.append(qestnItemSubCn);
				}
				let qestnItemBodyForm = $('<div class="form-group"></div>');
				qestnItemBodyForm.append(contentsEl);
				qestnItemBody.append(qestnItemBodyForm);
				qestnItemEl.append(qestnItemBody);
				
				let qestnItemFooter = $('<div class="card-footer"></div>');
				let qestnItemBtnGroup = $('<div class="text-right"></div>');
				let qestnItemModBtn = $('<a class="btn btn-warning" title="문항 수정" name="qestnMod">수정</a>');
				qestnItemModBtn.on('click', function(){
					$.fn.setQestnarQestnSetting({
						qestnMode : 'mod',
						qestnType : jsonData.qestnType,
						inputData : jsonData
					});
				});
				
				let qestnItemDelBtn = $('<a class="btn btn-danger ml-2" title="문항 삭제" name="qestnDel">삭제</a>');
				qestnItemDelBtn.on('click', function(){
					if(qestnInitOptions.isDeleteFn){
						qestnInitOptions.deleteItem(qestnItemEl, jsonData);
					} else {
						if(confirm("삭제하시겠습니까?")){
							qestnItemEl.remove();
						}
					}
				});
				
				qestnItemBtnGroup.append(qestnItemModBtn);
				qestnItemBtnGroup.append(qestnItemDelBtn);
				qestnItemFooter.append(qestnItemBtnGroup);
				qestnItemEl.append(qestnItemFooter);
				
				return qestnItemEl;
			}
			
	}
	
	/**
	 * 설문 문항 유형 선택 박스 만들기
	 * 유형 목록 : text, textarea, radio, checkbox, select, tel, address, calen, time, calenterm, file
	 * $(selectTarget).setQestnarQestnType(options) 가능
	 * options : 옵션
	 *  - selectTarget : 설문 문항 유형 옵션 넣을 Target selectBox Id (default : qestnType), 
	 *  - except : 제외할 유형 타입 설정 (text, textarea 등 ','로 구분)
	 * */
	/*$.fn.setQestnarQestnType = function(options){
		let defaults = {
				selectTarget : 'qestnType',
				except : ''
		};
		
		let qestnTypeOptions = $.extend(defaults, options || {});
		
		let targetEl = '';
		
		if($(this).length != 0){
			targetEl = $(this);
		} else {
			targetEl = $('#'+qestnTypeOptions.selectTarget);
		}
		
		targetEl.append('<option value="">--선택--</option>');
		
		$.each(qestnTypes, function(i, v){
			if(qestnTypeOptions.except.indexOf(v.value)){
				targetEl.append('<option value="'+v.value+'">'+v.text+'</option>');
			}
		});
	};*/
	/**
	 *   - containerId : 설문 문항이 표출되는 container element id
	 *   - settingClose : 설문 문항 작성 모달 Close 전 설정 함수 (param : modal),
 	 *   - settingOpen : 설문 문항 작성 모달 Open 전 설정 함수 (param : modal),
	 *	 - confirm : 설문 문항 작성 모달 '확인'버튼 클릭 설정 함수 (param : modal, view, jsonData)
	 *   - modify : 설문 문항 작성 모달 '확인'버튼(수정) 클릭 설정 함수 (param : modal, view, jsonData)
	 *   - deleteItem : 삭제....
	 *   - maxOptions : 문항 선택목록 최대 갯수 (radio, checkbox만 적용), default : 5
	 *   - qestnData : 기존 수정(mod)시 사용되며, json형태로 해당 모달에 데이터를 넣음 (ex : [{qestnId : "", qestnSj : "", qestnCn : "", require : "", qestnType : "", qestnOption : {}}])
	 * */
	/*$.fn.initQestnarQestn = function(options){
		//설문 문항 유형별 셋팅 모달 생성 시작
		let defaults = {
				containerId : '',
				settingClose : null,
				settingOpen : null,
				confirm : null,
				modify : null,
				deleteItem : null,
				maxOptions : 5,
				qestnData : null
		}
		
		qestnInitOptions = $.extend(defaults, options || {});
		
		qestnInitOptions.isCloseFn = jQuery.isFunction(qestnInitOptions.settingClose);
		qestnInitOptions.isOpenFn = jQuery.isFunction(qestnInitOptions.settingOpen);
		qestnInitOptions.isConfirmFn = jQuery.isFunction(qestnInitOptions.confirm);
		qestnInitOptions.isModifyFn = jQuery.isFunction(qestnInitOptions.modify);
		qestnInitOptions.isDeleteFn = jQuery.isFunction(qestnInitOptions.deleteItem);
		
		if(qestnInitOptions.qestnData != null){
			//데이터 셋팅
			$.each(qestnInitOptions.qestnData, function(i, v){
				v.qestnOption = JSON.parse(nonXssFiltering(v.qestnOption));
				let view = eval('makeQestnType_.'+v.qestnType+'.makeView(v);');
				//컨테이너에 view 수정
				if(qestnInitOptions.containerId != ''){
					$('#'+qestnInitOptions.containerId).append(view);
				}
			});
		}
		
		if(qestnInitOptions.containerId != '' && $('script[src$="dragula.min.js"]').length > 0){
			dragulaList = dragula([$('#'+qestnInitOptions.containerId)[0]]);
		}
		
	}*/
	/**
	 * 설문 문항 생성 폽
	 *   - qestnMode : 신규 추가(add), 기존 수정(mod), default : add
	 *   - qestnType : 설문 문항 타입 : text, textarea, radio, checkbox, select, tel, address, calen, time, calenterm, file
	 * */
	/*$.fn.setQestnarQestnSetting = function(options){
		//설문 문항 유형별 셋팅 모달 생성 시작
		let defaults = {
				qestnMode : '',
				qestnType : '',
				inputData : null
		}
		
		let qestnOptions = $.extend(defaults, options || {});
		if(qestnOptions.qestnMode == ''){
			Swal.fire('확인', '문항 모드을 입력하세요.', 'warning');
			return ;
		}
		if(qestnOptions.qestnType == ''){
			Swal.fire('확인', '문항 유형을 입력하세요.', 'warning');
			return ;
		}
		if(qestnOptions.qestnMode == 'mod'){
			if(qestnOptions.inputData == null){
				Swal.fire('확인', '수정 문항의 데이터를 입력하세요.', 'warning');
				return ;
			}
		}
		
		let settingModal = $.fn.makeQestnarQestnSettingForm(qestnOptions);
		
		settingModal.on('show.bs.modal', function(){
			if(qestnOptions.qestnMode == 'mod'){
				//수정 모드 시 각 유형에 맞게 데이터 넣기
				eval('makeQestnType_.'+qestnOptions.qestnType+'.setModal(qestnOptions.inputData, settingModal);');
			}
			if(qestnInitOptions.isOpenFn){
				qestnInitOptions.settingOpen(settingModal);
			}
		});
		
		settingModal.on('hidden.bs.modal', function(){
			if(qestnInitOptions.isCloseFn){
				qestnInitOptions.settingClose(settingModal);
			}
			//모달 닫을 때 삭제 및 변수 초기화
			settingModal.remove();
		});
		
		settingModal.on('click', '#confirm', function(){
			let modalForm = settingModal.find('#qestnSettingForm');
			let jsonData = {};
			let d = new Date();
			if(qestnOptions.qestnMode == 'mod') {
				jsonData.qestnId = settingModal.find('input[name="qestnId"]').val();
			} else {
				jsonData.qestnId = 'qestn'+d.getFullYear()+''+d.getMonth()+''+d.getDate()+''+d.getHours()+''+d.getMinutes()+''+d.getSeconds();
			}
			
			if($.trim(modalForm.find('input[name="qestnSj"]').val()) == ''){
				Swal.fire('확인', '문항 질문을 입력하세요.', 'warning');
				modalForm.find('input[name="qestnSj"]').focus();
				return;
			}
			jsonData.qestnType = qestnOptions.qestnType;
			jsonData.qestnSj = modalForm.find('input[name="qestnSj"]').val();
			jsonData.qestnCn = modalForm.find('textarea[name="qestnCn"]').val();
			jsonData.require = modalForm.find('input[name="requireYn"]:checked').val();
			jsonData.qestnOption = {};
			if(modalForm.find('.optionDiv').length > 0){
				let optionDivArr = modalForm.find('.optionDiv'); 
				let optionEmpty = false;
				
				let list = new Array();
				//텍스트 확인
				optionDivArr.each(function(i, v){
					let value = $(this).find('input[name="optionText"]').val();
					if($.trim(value) == ''){
						optionEmpty = true;
						$(this).find('input[name="optionText"]').focus();
						return false;
					}
					list.push(value);
				});
					
				if(optionEmpty){
					Swal.fire('확인', '문항 Text를 입력하세요.', 'warning');
					return;
				} else {
					jsonData.qestnOption.optionText = list;
				}
				
				optionEmpty = false;
				list = new Array();
				//값 확인
				optionDivArr.each(function(i, v){
					let value = $(this).find('input[name="optionValue"]').val();
					if($.trim(value) == ''){
						optionEmpty = true;
						$(this).find('input[name="optionValue"]').focus();
						return false;
					}
					list.push(value);
				});
					
				if(optionEmpty){
					Swal.fire('확인', '문항 Value를 입력하세요.', 'warning');
					return;
				} else {
					jsonData.qestnOption.optionValue = list;
				}
				
				list = new Array();
				//이미지 값 넣기
				optionDivArr.each(function(i, v){
					let value = $(this).find('input[name="fileValue"]').val();
					list.push(value);
				});
				
				jsonData.qestnOption.optionImage = list;
			}
			
			//기타
			if(modalForm.find('input[name="etcYn"]').length > 0){
				jsonData.qestnOption.etc = modalForm.find('input[name="etcYn"]:checked').val();
			}
			//시간
			if(modalForm.find('input[name="timeYn"]').length > 0){
				jsonData.qestnOption.time = modalForm.find('input[name="timeYn"]:checked').val();
			}
			
			//각 타입에 맞는 기본 틀 생성하여 데이터로 주기
			let view = eval('makeQestnType_.'+jsonData.qestnType+'.makeView(jsonData);');
			
			if(qestnOptions.qestnMode == 'mod') {
				//컨테이너에 view 수정
				if(qestnInitOptions.containerId != ''){
					$('#'+jsonData.qestnId).replaceWith(view);
				}
				if(qestnInitOptions.isModifyFn){
					qestnInitOptions.modify(settingModal, view, jsonData);
				}
			} else {
				//컨테이너에 view 넣기
				if(qestnInitOptions.containerId != ''){
					$('#'+qestnInitOptions.containerId).append(view);
				}
				if(qestnInitOptions.isConfirmFn){
					qestnInitOptions.confirm(settingModal, view, jsonData);
				}
			}
			//모달 닫을 때 삭제
			settingModal.modal('hide');
		});
		
		settingModal.modal('show');
		
	}*/
	
	/**
	 * 설문 문항 유형별 옵션 작성 폼(모달) 생성
	 * options : 옵션
	 *   - qestnMode : 신규 추가(add), 기존 수정(mod), default : add
	 *   - qestnType : 설문 타입
	 * */
	/*$.fn.makeQestnarQestnSettingForm = function(options){
		//설문 문항 유형별 셋팅 모달 생성 시작
		let defaults = {
				qestnMode : '',
				qestnType : '',
				inputData : null
		}
		
		let qestnSettingFormOptions = $.extend(defaults, options || {});
		
		let modalDiv = $('<div class="modal fade" id="qestnSettingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static"></div>');
		let modalDialogDiv = $('<div class="modal-dialog modal-lg"></div>');
		let modalContentDiv = $('<div class="modal-content"></div>');
		let modalTitle = eval('qestnTypes.'+qestnSettingFormOptions.qestnType+'.text');
		let modalHeaderDiv = '';
		if(qestnSettingFormOptions.qestnMode == 'mod'){
			modalHeaderDiv = $('<div class="modal-header"><h4 class="modal-title">' + modalTitle + '문항 수정</h4></div>');
		} else {
			modalHeaderDiv = $('<div class="modal-header"><h4 class="modal-title">' + modalTitle + '문항 추가</h4></div>');
		}
		modalContentDiv.append(modalHeaderDiv);
		let modalBodyDiv = $('<div class="modal-body form-horizontal"></div>');
		modalBodyDiv.append('<input type="hidden" name="qestnTypeName" value="'+qestnSettingFormOptions.qestnType+'">');
		
		if(qestnSettingFormOptions.qestnMode == 'mod'){
			modalBodyDiv.append('<input type="hidden" name="qestnId" value="'+qestnSettingFormOptions.inputData.qestnId+'">');
		}
		let modalBodyContents = eval('makeQestnType_.'+qestnSettingFormOptions.qestnType+'.makeModal();');
		modalBodyDiv.append(modalBodyContents);
		modalContentDiv.append(modalBodyDiv);
		
		let modalFooterDiv = $('<div class="modal-footer"></div>');
		modalFooterDiv.append('<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>');
		modalFooterDiv.append('<button type="button" id="confirm" class="btn btn-primary">확인</button>');
		modalContentDiv.append(modalFooterDiv);
		modalDialogDiv.append(modalContentDiv);
		modalDiv.append(modalDialogDiv);
		
		$(document.body).append(modalDiv);
		
		return modalDiv;
	};*/
	
	/**
	 * 질문 Data serialize
	 * */
	$.fn.getDataSerialize = function(options){
		let defaults = {
				container : '',
		};
		
		let serializeOption = $.extend(defaults, options || {});
		
		if(serializeOption.container == ''){
			Swal.fire('확인', '최상위 Container를 입력해주세요.', 'warning');
			return '';
		}
		
		let returnSerializeData = new Array();
		
		serializeOption.container.find('.card[id^="qestn"]').each(function(i, v){
			returnSerializeData.push($(v).data('itemData'));
//			eval("returnSerializeData.data"+(i+1)+"="+JSON.stringify($(v).data('itemData'))+";");
		});
		
		return returnSerializeData;
	}
	
	var cndJson = { 
		'is' : {value : "is", name : "같음"}
		,'isNot' : {value : "isNot", name : "같지않음"}
		,'isContain' : {value : "isContain", name : "포함(단어)"}
		,'isNotContain' : {value : "isNotContain", name : "미포함(단어)"}
	};
	
	var logicCndJson = {
			'and' : {value : "and", name : "그리고"}
			,'or' : {value : "or", name : "또는"}
	}
	var qestnList = '';
	/**
	 * 시나리오 모달 생성
	 *  - qestnarSeqNo : 설문 번호 
	 *  - qestnList : 문항 목록
	 *	- senarioList : 시나리오 목록
	 *	- save : 저장시 리턴되는 함수
	 * */
	$.fn.makeSenarioModal = function(options){
		let defaults = {
				qestnarSeqNo : '',
				qestnList : null,
				senarioList : null,
				save : null
		};
		
		let senarioOption = $.extend(defaults, options || {});
		
		senarioOption.isSaveFn = jQuery.isFunction(senarioOption.save);
		
		if(senarioOption.qestnarSeqNo == ''){
			Swal.fire('확인', '설문조사를 선택해주세요.', 'warning');
			return ;
		}
		
		if(senarioOption.qestnList == null || senarioOption.qestnList == '' || senarioOption.qestnList == undefined || senarioOption.qestnList.length == 0){
			Swal.fire('확인', '설문조사 문항을 등록해주세요.', 'warning');
			return ;
		}
		
		qestnList = senarioOption.qestnList;
		
		let modalDiv = $('<div class="modal fade" id="senarioModal" aria-labelledby="senarioModalLabel" aria-hidden="true" data-backdrop="static"></div>');
		let modalDialogDiv = $('<div class="modal-dialog modal-lg"></div>');
		let modalContentDiv = $('<div class="modal-content"></div>');
		let modalHeaderDiv = $('<div class="modal-header"><h4 class="modal-title" id="senarioModalLabel">설문조사 시나리오 관리</h4></div>');
		modalContentDiv.append(modalHeaderDiv);
		let modalBodyDiv = $('<div class="modal-body form-horizontal"></div>');
		modalBodyDiv.append('<div class="col">'+
								'<div class="row">'+
									'<h5 class="col">시나리오 목록</h5>'+
									'<div class="col-auto">'+
										'<a href="javascript:;" class="btn btn-sm btn-primary addSenarioBtn">'+
											'<i class="far fa-plus-square"></i> 추가'+
										'</a>'+
									'</div>'+
								'</div>'+
							'</div>');
		modalBodyDiv.append('<div class="senario-group"></div>');
		modalContentDiv.append(modalBodyDiv);
		let modalFooterDiv = $('<div class="modal-footer"></div>');
		modalFooterDiv.append('<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>');
		modalFooterDiv.append('<button type="button" id="saveBtn" class="btn btn-primary">저장</button>');
		modalContentDiv.append(modalFooterDiv);
		modalDialogDiv.append(modalContentDiv);
		modalDiv.append(modalDialogDiv);
		modalDiv.append('<input type="hidden" name="nowQestnarSeqNo" value="'+senarioOption.qestnarSeqNo+'">');
		$(document.body).append(modalDiv);
		modalDiv.senarioModalFn(senarioOption);
		modalDiv.modal('show');
	}
	
	$.fn.senarioModalFn = function(option){
		let thisEl = $(this);
		
		thisEl.on('show.bs.modal', function(e){
			let qestnarSeqNo = $(this).find('input[name="nowQestnarSeqNo"]').val();
			$(this).find('.senario-group').setQestnSenarioList(option);
		}).on('hidden.bs.modal', function(){
			$(this).remove();
		}).on('click', '.addSenarioBtn', function(){
			//시나리오 폼 추가 로직
			let senarioDiv = $('#senarioModal').find('.senario-group').setQestnSenarioList({qestnList : option.qestnList});
			//문항 check 및 reset
//			$.fn.resetQestn(option.qestnList);
			$.fn.resetQestnOption(senarioDiv.find('.dtrmnQestn'));
		}).on('change', 'select[name="dtrmnQestnSeqNo"]', function(){
			let senarioWapper = $(this).parents('.senario');
			let nowSelectQestn = senarioWapper.find('select[name="dtrmnQestnSeqNo"] > option:selected').data('qestnItemData');
			senarioWapper.find('.cndValueOption').remove();
			let cndOptionDiv = $.fn.setSenarioCndOption(senarioWapper, nowSelectQestn, '1');
			senarioWapper.find('.dtrmnQestn').after(cndOptionDiv);
			//문항 check 및 reset
//			$.fn.resetQestn(option.qestnList);
			$.fn.resetQestnOption($(this));
		}).on('click', '.deleteBtn', function(){
			let senarioWapper = $(this).parents('.senario');
//			let nowSelectQestn = senarioWapper.find('select[name="dtrmnQestnSeqNo"] > option:selected').data();
			if($('#senarioModal').find('.senario').index(senarioWapper) == 0){
				senarioWapper.next('hr').remove();
			} else {
				senarioWapper.prev('hr').remove();
			}
			senarioWapper.remove();
			//문항 check 및 reset
//			$.fn.resetQestn(option.qestnList);
		}).on('click', '.addOption', function(){
			let senarioWapper = $(this).parents('.senario');
			let nowSelectQestn = senarioWapper.find('select[name="dtrmnQestnSeqNo"] > option:selected').data('qestnItemData');
			let cndOptionWapper = $.fn.setSenarioCndOption(senarioWapper, nowSelectQestn, '0');
			if(cndOptionWapper == ''){
				return ;
			}
			let cndOptionDiv = $(cndOptionWapper.html()); 
			senarioWapper.find('.cndValueOption').append(cndOptionDiv);
			
			$.fn.resetQestnOption($(this));
		}).on('click', '.deleteOption', function(){
			$(this).parents('.cndValues').remove();
			$.fn.resetQestnOption($(this));
		}).on('change', 'select[name="dtrmnValue"]', function(){
			$.fn.resetQestnOption($(this));
			let nowSelectQestn = senarioWapper.find('select[name="dtrmnQestnSeqNo"] > option:selected').data('qestnItemData');
			if(nowSelectQestn.qestnOptn.optionText != undefined){
				$.fn.resetQestnOption($(this));
			}
		}).on('change', 'select[name="dtrmnCnd"]', function(){
			let senarioWapper = $(this).parents('.senario');
			let nowSelectQestn = senarioWapper.find('select[name="dtrmnQestnSeqNo"] > option:selected').data('qestnItemData');
			if(nowSelectQestn.qestnOptn.optionText == undefined){
				$.fn.resetQestnOption($(this));
			}
		}).on('click', '#saveBtn', function(){
			//저장 로직
			if(option.isSaveFn){
				option.save($.fn.getSenarioDataSerialize());
			}
			
			thisEl.modal('hide');
		});
	}
	
	$.fn.setQestnSenarioList = function(option){
		let thisEl = $(this);
		let qestnList = option.qestnList;
		let senarioList = option.senarioList;
		//시나리오 목록 셋팅
		let senarioDiv = '';
		let senarioDivArr = [];
		if(senarioList != null && senarioList != '' && senarioList != undefined){
			//시나리오 있을 경우
			$.each(senarioList, function(i, v){
				senarioDiv = $('<div class="col senario"></div>');
				let qestnDiv = senarioDiv.setQestnList(qestnList, v.dtrmnQestnSeqNo);
				let nowSelectQestn = qestnDiv.find('select[name="dtrmnQestnSeqNo"] > option:selected').data();
				//조건 여러개 일 경우 
				let dtrmnCndList = JSON.parse(v.dtrmnCnd);
				$.each(dtrmnCndList, function(i2, v2){
					//"{"s1":{"cnd":"is","value":"사과"},"s2":{"cnd":"is","value":"배","logic":"or"}}"
					senarioDiv.setSenarioCndOption(senarioDiv, nowSelectQestn.qestnItemData, '1', v2.cnd, v2.value, v2.logic);
				});
				senarioDiv.setTrgetQestnList(qestnList, v.trgetQestnSeqNo);
				senarioDivArr.push(senarioDiv);
			});
		} else {
			senarioDiv = $('<div class="col senario"></div>');
			//시나리오 없을 경우
			let qestnDiv = senarioDiv.setQestnList(qestnList);
			if(qestnDiv == ''){
				return ;
			}
			let nowSelectQestn = qestnDiv.find('select[name="dtrmnQestnSeqNo"] > option:selected').data();
			senarioDiv.setSenarioCndOption(senarioDiv, nowSelectQestn.qestnItemData, '1');
			senarioDiv.setTrgetQestnList(qestnList);
		}
		if($('#senarioModal').find('.senario').length != 0){
			thisEl.append('<hr>');
		}
		
		if(thisEl.length > 0){
			if(senarioDivArr.length != 0){
				$.each(senarioDivArr, function(i, v){
					if(i != 0){
						thisEl.append('<hr>');
					}
					thisEl.append(v);
				});
				return senarioDivArr;
			} else {
				thisEl.append(senarioDiv);
				return senarioDiv;
			}
		}
	}
	
	$.fn.setQestnList = function(qestnList, dtrmnQestnSeqNo){
		let thisEl = $(this);
		let qestnWapper = $('<div class="row mb-2 dtrmnQestn"></div>'); 
		let qestnDiv = $('<div class="row col-md-12 pr-0"></div>');
		let dtrmnQestnDiv = $('<div class="col-md-11"></div>');
		let qestnSelect = $('<select class="form-control" name="dtrmnQestnSeqNo"></select>');
		$.each(qestnList, function(i, v){
			let check = "";
			if(dtrmnQestnSeqNo == v.qestnSeqNo){
				check = 'selected="selected"';		
			}
//			let checkSeqNo = $.fn.checkQestn(v.qestnSeqNo);
//			let qestnOption = $('<option value="'+v.qestnSeqNo+'" '+check+' '+(checkSeqNo ? 'disabled="disabled"' : '')+'>'+v.qestnSj+'</option>');
			let qestnOption = $('<option value="'+v.qestnSeqNo+'" '+check+'>'+v.qestnSj+'</option>');
			qestnOption.data('qestnItemData', v);
			qestnSelect.append(qestnOption);
		});
		if(qestnSelect.find('option').not(':disabled').length == 0){
			Swal.fire('확인', '설정할 문항이 없습니다.', 'warning');
			return '';
		}
		dtrmnQestnDiv.append(qestnSelect);
		qestnDiv.append(dtrmnQestnDiv);
		
		let deleteBtnEl = $('<a class="btn btn-sm btn-danger deleteBtn col-md-1 p-0 pt-2"><i class="far fa-minus-square"></i> 삭제</a>');
		qestnDiv.append(deleteBtnEl);
		
		qestnWapper.append(qestnDiv);
		if(thisEl.length > 0){
			thisEl.append(qestnWapper);
		}
		return qestnWapper;
	}
	
	$.fn.setSenarioCndOption = function(senarioDiv, qestn, type, dtrmnCnd, dtrmnValue, dtrmnLogic){
		let thisEl = $(this);
		let senarioCndOptionWapper = $('<div class="row mb-2 cndValueOption"></div>'); 
		let senarioCndOptionDiv = $('<div class="row col-md-12 pr-0 cndValues"></div>');
		let logicCk = false;
		if(type != '1' || (dtrmnLogic != '' && dtrmnLogic != null && dtrmnLogic != undefined)){
			logicCk = true;
		}
		if(logicCk){
			let logicTypeList = [''];
			let dtrmnLogicCndSelectWapper = $('<div class="dtrmnLogicCnd col-md-2"></div>');
			let dtrmnLogicCndSelect = $('<select class="form-control" name="dtrmnLogicCnd"></select>');
			$.each(logicCndJson, function(i, v){
				let check = "";
				if(dtrmnLogic == v.value){
					check = 'selected="selected"';		
				}
				dtrmnLogicCndSelect.append('<option value="'+v.value+'" '+check+'>'+v.name+'</option>');
			});
			dtrmnLogicCndSelectWapper.append(dtrmnLogicCndSelect);
			senarioCndOptionDiv.append(dtrmnLogicCndSelectWapper);
		}
		
		let dtrmnCndSelectWapper = $('<div class="dtrmnCnd col-md-'+(logicCk? '2' : '4')+'"></div>');
		let typeOfList = eval('qestnTypes.'+qestn.qestnTy+'.cnd.split(",");');
		let dtrmnCndSelect = $('<select class="form-control" name="dtrmnCnd"></select>');
		$.each(typeOfList, function(i, v){
			let check = "";
			if(dtrmnCnd == v){
				check = 'selected="selected"';		
			}
			let checkSeqNo = '';
			if(v == 'isContain' || v == 'isNotContain'){
				checkSeqNo = $.fn.checkQestnOptionValue2(senarioDiv, v);
			}
			dtrmnCndSelect.append('<option value="'+eval('cndJson.'+v+'.value')+'" '+check+' '+(checkSeqNo ? 'disabled="disabled"' : '')+'>'+eval('cndJson.'+v+'.name')+'</option>');
		});
		if(dtrmnCndSelect.find('option').not(':disabled').length == 0){
			Swal.fire('확인', '설정할 조건이 없습니다.', 'warning');
			return '';
		}
		dtrmnCndSelectWapper.append(dtrmnCndSelect);
		senarioCndOptionDiv.append(dtrmnCndSelectWapper);
		let dtrmnValueSelectWapper = $('<div class="dtrmnValue col-md-7"></div>');
		let optionValArr = JSON.parse(nonXssFiltering(qestn.qestnOptn));
		if(optionValArr.optionText == undefined){
			dtrmnValueSelectWapper.append($('<input type="text" class="form-control" name="dtrmnValue" value="'+(dtrmnValue == undefined ? '' : dtrmnValue)+'" placeholder="\',\'로 구분하여 작성">'));
		} else {
			let dtrmnValueSelect = $('<select class="form-control" name="dtrmnValue"></select>');
			$.each(optionValArr.optionText, function(i, v){
				let check = "";
//				if(dtrmnValue == v){
//					check = 'selected="selected"';		
//				}
				let checkSeqNo = $.fn.checkQestnOptionValue(senarioDiv, v);
				let dtrmnValue = $('<option value="'+optionValArr.optionValue[i]+'" '+check+' '+(checkSeqNo ? 'disabled="disabled"' : '')+'>'+v+'</option>');
				dtrmnValueSelect.append(dtrmnValue);
//				dtrmnValueSelect.append('<option value="'+optionValArr.optionValue[i]+'" '+check+'>'+v+'</option>');
			});
			if(optionValArr.etc == 'Y'){
				let check = "";
				if(dtrmnValue == qestn.qestnSeqNo + optionValArr.etc){
					check = 'selected="selected"';		
				}
				let checkSeqNo = $.fn.checkQestnOptionValue(senarioDiv, '기타');
				dtrmnValueSelect.append('<option value="'+qestn.qestnSeqNo + optionValArr.etc+'" '+check+' '+(checkSeqNo ? 'disabled="disabled"' : '')+'>기타</option>');
			}
			if(dtrmnValueSelect.find('option').not(':disabled').length == 0){
				Swal.fire('확인', '설정할 옵션이 없습니다.', 'warning');
				return '';
			}
			dtrmnValueSelectWapper.append(dtrmnValueSelect);
		}
		senarioCndOptionDiv.append(dtrmnValueSelectWapper);
		senarioCndOptionWapper.append(senarioCndOptionDiv);
		let optionBtnEl = '';
		if(!logicCk){
			optionBtnEl = $('<a class="btn btn-sm btn-info pt-2 addOption"><i class="far fa-plus-square"></i></a>');
			senarioCndOptionDiv.append(optionBtnEl);
		} else {
			optionBtnEl = $('<a class="btn btn-sm btn-danger pt-2 deleteOption"><i class="far fa-minus-square"></i></a>');
			senarioCndOptionDiv.append(optionBtnEl);
		}
		
		if(thisEl.length > 0){
			thisEl.append(senarioCndOptionWapper);
		}
		return senarioCndOptionWapper;
	}
	
	$.fn.setTrgetQestnList = function(qestnList, trgetQestnSeqNo){
		let thisEl = $(this);
		let qestnWapper = $('<div class="row mb-2 trgetQestn"></div>');
		let qestnDiv = $('<div class="row col-md-12 pr-0"></div>');
		let dtrmnQestnDiv = $('<div class="col-md-11"></div>');
		let qestnSelect = $('<select class="form-control" name="trgetQestnSeqNo"></select>');
		$.each(qestnList, function(i, v){
			let check = "";
			if(trgetQestnSeqNo == v.qestnSeqNo){
				check = 'selected="selected"';		
			}
			let qestnOption = $('<option value="'+v.qestnSeqNo+'" '+check+'>'+v.qestnSj+'</option>');
			qestnOption.data('qestnItemData', v);
			qestnSelect.append(qestnOption);
		});
		dtrmnQestnDiv.append(qestnSelect);
		qestnDiv.append(dtrmnQestnDiv);
		qestnWapper.append(qestnDiv);
		if(thisEl.length > 0){
			thisEl.append(qestnWapper);
		}
		return qestnWapper;
	}
	
	/**
	 * 문항 중복 방지를 위한 문항 체크
	 * */
	$.fn.checkQestn = function(qestnSeqNo){
		let useCk = false;
		let senarioElArr = $('select[name="dtrmnQestnSeqNo"]');
		$.each(senarioElArr, function(i, v){
			let thisQestnSeqNo = $(this).find('option:selected').val(); 
			if(thisQestnSeqNo == qestnSeqNo){
				useCk = true;
				return false;
			}
		});
		return useCk;
	}
	/**
	 * 문항 옵션 중복 방지를 위한 문항 옵션 체크
	 * */
	$.fn.checkQestnOptionValue = function(senarioEl, value){
		let useCk = false;
		let senarioElArr = senarioEl.find('select[name="dtrmnValue"]');
		$.each(senarioElArr, function(i, v){
			let thisQestnSeqNo = $(this).find('option:selected').text(); 
			if(thisQestnSeqNo == value){
				useCk = true;
				return false;
			}
		});
		return useCk;
	}
	/**
	 * 문항 옵션 중복 방지를 위한 문항 옵션 체크
	 * */
	$.fn.checkQestnOptionValue2 = function(senarioEl, value){
		let useCk = false;
		let senarioElArr = senarioEl.find('select[name="dtrmnCnd"]');
		$.each(senarioElArr, function(i, v){
			let thisQestnSeqNo = $(this).find('option:selected').val(); 
			if(thisQestnSeqNo == value){
				useCk = true;
				return false;
			}
		});
		return useCk;
	}
	
//	$.fn.resetQestn = function(qestnList){
//		//시나리오 문항 변경 시 문항 값 check 및 reset
//		let senarioQestnList = $('select[name="dtrmnQestnSeqNo"]');
//		let preSetValueArr = [];
//		$.each(senarioQestnList, function(i, v){
//			let thisQestnSeqNo = $(this).find('option:selected').val();
//			preSetValueArr.push(thisQestnSeqNo);
//		});
//		
//		$.each(senarioQestnList, function(i, v){
//			let options = $(this).find('option');
//			$.each(options, function(i2, v2){
//				if(arrayContain($(this).val(), preSetValueArr)){
//					if($(this).val() != $(v).val()){
//						v2.disabled = true;
//					}
//				} else {
//					v2.disabled = false;
//				}
//			});
//		});
//	}
	
	$.fn.resetQestnOption = function(ele){
		let senarioDiv = ele.parents('.senario');
		let qestn = senarioDiv.find('select[name="dtrmnQestnSeqNo"] > option:selected').data('qestnItemData');
		//옵션에 대한 로직 추가 시 체크 필요
		let optionValArr = JSON.parse(nonXssFiltering(qestn.qestnOptn));
		if(optionValArr.optionText != undefined){
			//옵션이 있을 시 radio, checked, select
			let senarioQestnValueList = senarioDiv.find('select[name="dtrmnValue"]');
			let preSetValueArr = [];
			$.each(senarioQestnValueList, function(i, v){
				let thisQestnText = $(this).find('option:selected').text();
				preSetValueArr.push(thisQestnText);
			});
			senarioDiv.find('select[name="dtrmnValue"]').each(function(i, v){
				let options = $(this).find('option');
				$.each(options, function(i2, v2){
					if(arrayContain($(this).text(), preSetValueArr)){
						if($(this).text() != $(v).find('option:selected').text()){
							v2.disabled = true;
						}
					} else {
						v2.disabled = false;
					}
				});
			});
		} else {
			//나머지는 옵션이 아닌 조건을 체크
			let senarioQestnCndList = senarioDiv.find('select[name="dtrmnCnd"]');
			let preSetValueArr = [];
			$.each(senarioQestnCndList, function(i, v){
				let thisQestnCnd = $(this).find('option:selected').val();
				preSetValueArr.push(thisQestnCnd);
			});
			senarioDiv.find('select[name="dtrmnCnd"]').each(function(i, v){
				let options = $(this).find('option');
				$.each(options, function(i2, v2){
					if(arrayContain($(this).val(), preSetValueArr)){
						if($(this).val() != $(v).find('option:selected').val()){
							v2.disabled = true;
						}
					} else {
						v2.disabled = false;
					}
				});
			});
			
		}
		
	}
	
	/**
	 * 시나리오 Data serialize
	 * */
	$.fn.getSenarioDataSerialize = function(options){
		let defaults = {
				container : '',
		};
		
		let serializeOption = $.extend(defaults, options || {});
		
//		if(serializeOption.container == ''){
//			Swal.fire('확인', '최상위 Container를 입력해주세요.', 'warning');
//			return '';
//		}
		
		let returnSerializeData = new Array();
		
		let qestnarSeqNo = $('input[name="nowQestnarSeqNo"]').val();
		
		$('.senario-group').find('.senario').each(function(i, v){
			let senarioJsonData = {};
			//dtrmnQestn
			senarioJsonData.dtrmnQestnSeqNo = $(this).find('select[name="dtrmnQestnSeqNo"]').val();
			//{"1" : {cnd : "aa", value : "bb"}, "2" : {logic : "a", cnd : "b", value : "c"}}
			//cnd
			let cndJsons = {};
			$.each($(this).find('.cndValueOption > .cndValues'), function(i, v){
				let cndValueJson = {};
				let cnd = $(this).find('select[name="dtrmnCnd"]').val();
				let value = $(this).find('select[name="dtrmnValue"] > option:selected').text(); 
				if(value == '' || value == null || value == undefined){
					value = $(this).find('input[name="dtrmnValue"]').val();
				}
				cndValueJson.cnd = cnd;
				cndValueJson.value = value;
				if(i != 0){
					let logic = $(this).find('select[name="dtrmnLogicCnd"]').val();
					cndValueJson.logic = logic;
				}
				eval('cndJsons.s'+(i+1)+'=cndValueJson;');
			});
			senarioJsonData.dtrmnCnd = cndJsons;
			//trgetQestn
			senarioJsonData.trgetQestnSeqNo = $(this).find('select[name="trgetQestnSeqNo"]').val();
			senarioJsonData.qestnarSeqNo = qestnarSeqNo;
			
			returnSerializeData.push(senarioJsonData);
		});
		
//		serializeOption.container.find('.card[id^="qestn"]').each(function(i, v){
//			returnSerializeData.push($(v).data('itemData'));
//			eval("returnSerializeData.data"+(i+1)+"="+JSON.stringify($(v).data('itemData'))+";");
//		});
		
		return returnSerializeData;
	}
	
	
	
})(jQuery);

function qestn2RadioBtnSelectForm(title, elName, yText, nText){
	let html = '';
	
	html += '<div class="col mb-3">'+
			'	<label for="'+elName+'YnY">'+title+'</label>'+
			'	<div class="btn-group-toggle" data-toggle="buttons">'+
            '    	<label for="'+elName+'YnY" class="btn btn-white active">'+
            '      		<input type="radio" name="'+elName+'Yn" id="'+elName+'YnY" value="Y" checked="checked">'+
            '      		<i class="far fa-check-circle"></i> '+yText+
            '    	</label>'+
            '    	<label for="'+elName+'YnN" class="btn btn-white">'+
            '      		<input type="radio" name="'+elName+'Yn" id="'+elName+'YnN" value="N">'+
            '      		<i class="far fa-check-circle"></i> '+nText+
            '    	</label>'+
            '	</div>'+
			'</div>';
	
	return html;
}














