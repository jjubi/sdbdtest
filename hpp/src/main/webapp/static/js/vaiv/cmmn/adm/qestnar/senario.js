/**
 * 설문조사 시나리오 만들기 javascript v1.1
 * 
 * Copyright By vaiv
 * 
 * @author hm
 * 
 * Date : 2020.12.31
 */
$(document).ready(function(){
	
	
})



var senarioInitOption;
(function($){
	/*
	 * 질문 유형
	 *	- 객관식 : RADIO : is, isNot
	 *	- 객관식 목록형 : SELECT : is, isNot
	 *	- 객관식 중복형 : CHECKBOX : is, isNot
	 *	- 객관식 이미지 : R_IMAGE : is, isNot		(destroy)
	 *	- 객관식 이미지 중복형 : CB_IMAGE : is, isNot	(destroy)
	 *	- 단답형 : TEXT : isCti, isNotCti
	 *	- 장문형 : TEXTAREA : isCti, isNotCti
	 *	- 연락처 : TEL : is, isNot
	 *	- 주소 : ADDRESS : isCti, isNotCti
	 *	- 날짜 : CALEN : is, isNot
	 *	- 시간 : TIME : is, isNot
	 *	- 기간 : CALENTERM : isCti, isNotCti
	 *	- 파일 : FILE : isCit, isNotCti
	 */
	const qestnTypes = {
			"radio"        : {tyEngTxt : "radio"    , tyKorTxt : '객관식', tyCnd : 'is,isNot'},
			"select"       : {tyEngTxt : "select"   , tyKorTxt : '객관식 목록형', tyCnd : 'is,isNot'},
			"checkbox"     : {tyEngTxt : "checkbox" , tyKorTxt : '객관식 중복형', tyCnd : 'is,isNot'},
//			"rImage"       : {tyEngTxt : "rImage"   , tyKorTxt : '객관식 이미지', tyCnd : 'is,isNot'},
//			"cbImage"      : {tyEngTxt : "cbImage"  , tyKorTxt : '객관식 이미지 중복형', tyCnd : 'is,isNot'},
			"text"         : {tyEngTxt : "text"     , tyKorTxt : '단답형', tyCnd : 'isCit,isNotCti'},
			"textarea"     : {tyEngTxt : "textarea" , tyKorTxt : '장문형', tyCnd : 'isCit,isNotCti'},
			"tel"          : {tyEngTxt : "tel"      , tyKorTxt : '연락처', tyCnd : 'is,isNot'},
			"address"      : {tyEngTxt : "address"  , tyKorTxt : '주소', tyCnd : 'isCit,isNotCti'},
			"calen"        : {tyEngTxt : "calen"    , tyKorTxt : '날짜', tyCnd : 'is,isNot'},
			"time"         : {tyEngTxt : "time"     , tyKorTxt : '시간', tyCnd : 'is,isNot'},
			"calenterm"    : {tyEngTxt : "calenterm", tyKorTxt : '기간', tyCnd : 'isCit,isNotCti'},
			"file"         : {tyEngTxt : "file"     , tyKorTxt : '파일', tyCnd : 'isCit,isNotCti'},
	};
	
	let cndJson = { 
			'is' : {value : "is", name : "같음"}
			,'isNot' : {value : "isNot", name : "같지않음"}
			,'isCit' : {value : "isContain", name : "포함(단어)"}
			,'isNotCti' : {value : "isNotContain", name : "미포함(단어)"}
	};
	
	let logicCndJson = {
			'and' : {value : "and", name : "그리고"}
			,'or' : {value : "or", name : "또는"}
	}
	
	let checkSelectedValue = function(targetValue, value){
		if(targetValue == value){
			return 'selected="selected"';
		} else {
			return '';
		}
	}
	
	/**
	 * 문항 옵션 중복 방지를 위한 문항 옵션 체크
	 * */
	let checkQestnOptionValue = function(senarioEl, value){
		let useCk = false;
		let senarioElArr = senarioEl.find('select[name="dtrmnValue"]');
		$.each(senarioElArr, function(i, v){
			let thisQestnSeqNo = $(this).find('option:selected').val(); 
			if(thisQestnSeqNo == value){
				useCk = true;
				return false;
			}
		});
		return useCk;
	}
	
	let setQestnList = function(qestnList, dtrmnQestnSeqNo){
		let defaultQestnArea = $(defaultQestnHTML);
		let qestnSelectArea = defaultQestnArea.find('select');
		$.each(qestnList, function(i, v){
			let check = "";
			if(dtrmnQestnSeqNo == v.qestnSeqNo){
				check = 'selected="selected"';		
			}
			let qestnOption = $('<option value="'+v.qestnSeqNo+'" '+check+'>'+v.qestnSj+'</option>');
			qestnOption.data('qestnItemData', v);
			qestnSelectArea.append(qestnOption);
		});
		
		if(qestnSelectArea.find('option').not(':disabled').length == 0){
			Swal.fire('확인', '설정할 문항이 없습니다.', 'warning');
			return '';
		}
		
		return defaultQestnArea;
	}
	
	let setSenarioCnd = function(senarioDiv, qestn, type, dtrmnCnd, dtrmnValue, dtrmnLogic){
		let defaultSenarioCndView = $(defaultSenarioCndHTML);
		
		let logicCk = false;
		if(type != '1' || (dtrmnLogic != '' && dtrmnLogic != null && dtrmnLogic != undefined)){
			logicCk = true;
		}
		
		if(logicCk) {
			let defaultLogicView = $(defaultLogicHTML);
			let logicSelect = defaultLogicView.find('select');
			$.each(logicCndJson, function(i, v){
				let check = checkSelectedValue(dtrmnLogic, v.value);
				logicSelect.append('<option value="'+v.value+'" '+check+'>'+v.name+'</option>');
			});
			defaultSenarioCndView.find('.cndValues').append(defaultLogicView);
		}
		
		let defaultCndView = $(defaultCndHTML);
		if(!logicCk) {
			defaultCndView.removeClass('col-md-2').addClass('col-md-4');
		}
		let typeOfList = qestnTypes[qestn.qestnTy].tyCnd.split(",");
		let defaultCndSelect = defaultCndView.find('select');
		$.each(typeOfList, function(i, v){
			let check = checkSelectedValue(dtrmnCnd, v);
			let checkSeqNo = '';
//			if(v == 'isCti' || v == 'isNotCti'){
//				checkSeqNo = $.fn.checkQestnOptionValue2(senarioDiv, v);
//			}
			defaultCndSelect.append('<option value="'+ cndJson[v].value+'" '+check+' '+(checkSeqNo ? 'disabled="disabled"' : '')+'>'+cndJson[v].name+'</option>');
		});
		if(defaultCndSelect.find('option').not(':disabled').length == 0){
			Swal.fire('확인', '설정할 조건이 없습니다.', 'warning');
			return '';
		}
		defaultSenarioCndView.find('.cndValues').append(defaultCndView);
		
		let aswperList = qestn.qestnAswperList;
		let optnList = qestn.qestnOptnList;
		let defaultValueView = '';
		if(aswperList.length == 0){
			defaultValueView = $(defaultValueTextHTML);
			if(dtrmnValue != undefined){
				defaultValueView.find('input[name="dtrmnValue"]').val(dtrmnValue);
			}
		} else {
			defaultValueView = $(defaultValueSelectHTML);
			let valueSelect = defaultValueView.find('select');
			//option 넣기
			$.each(aswperList, function(i, v){
				let check = checkSelectedValue(dtrmnValue, v.aswperSeqNo);
				let checkSeqNo = checkQestnOptionValue(senarioDiv, v.aswperSeqNo);
				let dtrmnValueOption = $('<option value="'+v.aswperSeqNo+'" '+check+' '+(checkSeqNo ? 'disabled="disabled"' : '')+'>'+v.aswperText+'</option>');
				valueSelect.append(dtrmnValueOption);
			});
			//기타 체크
			let etcAt = '';
			$.each(optnList, function(i, v){
				if(v.optnTy == 'Etc'){
					etcAt = v.optnValue;
				}
			});
			if(etcAt == 'Y'){
				let check = checkSelectedValue(dtrmnValue, qestn.qestnSeqNo + '기타');
				let checkSeqNo = checkQestnOptionValue(senarioDiv, qestn.qestnSeqNo+'기타');
				valueSelect.append('<option value="'+qestn.qestnSeqNo +'기타" '+check+' '+(checkSeqNo ? 'disabled="disabled"' : '')+'>기타</option>');
			}
			if(valueSelect.find('option').not(':disabled').length == 0){
				Swal.fire('확인', '설정할 옵션이 없습니다.', 'warning');
				return '';
			}
		}
		defaultSenarioCndView.find('.cndValues').append(defaultValueView);
		
		if(!logicCk){
			defaultSenarioCndView.find('.cndValues').append($(cndAddBtnHTML));
		} else {
			defaultSenarioCndView.find('.cndValues').append($(cndMinusBtnHTML));
		}
		
		return defaultSenarioCndView;
	}
	
	let setTrgetQestnList = function(qestnList, trgetQestnSeqNo){
		let defaultTrgetQestnView = $(defaultTrgetQestnHTML);
		let qestnSelect = defaultTrgetQestnView.find('select[name="trgetQestnSeqNo"]');
		$.each(qestnList, function(i, v){
			let check = "";
			if(trgetQestnSeqNo == v.qestnSeqNo){
				check = 'selected="selected"';		
			}
			let qestnOption = $('<option value="'+v.qestnSeqNo+'" '+check+'>'+v.qestnSj+'</option>');
			qestnOption.data('qestnItemData', v);
			qestnSelect.append(qestnOption);
		});
		return defaultTrgetQestnView;
	}
	
	let setQestnSenarioList = function(options){
		let qestnList = options.qestnList;
		let senarioList =  options.senarioList;
		let senarioWapper = '';
		let senarioWapperArr = [];
		if(senarioList != null && senarioList != '' && senarioList != undefined) {
			//시나리오 있을 경우
			$.each(senarioList, function(i, v){
				senarioWapper = setQestnList(qestnList, v.dtrmnQestnSeqNo);
//				let qestnDiv = senarioDiv.setQestnList(qestnList, v.dtrmnQestnSeqNo);
				let nowSelectQestn = senarioWapper.find('select[name="dtrmnQestnSeqNo"] > option:selected').data();
				//조건 여러개 일 경우 
				let dtrmnCndList = v.senarioDtlList;
				$.each(dtrmnCndList, function(i2, v2){
					let setSenarioCndView = setSenarioCnd(senarioWapper, nowSelectQestn.qestnItemData, '1', v2.dtrmnCnd, v2.dtrmnCndValue, v2.dtrmnCndLogic);
					if(i2 != 0){
						senarioWapper.find('.cndValueOption').append(setSenarioCndView.html());
					} else {
						senarioWapper.append(setSenarioCndView);
					}
				});
				let setTrgetQestnView = setTrgetQestnList(qestnList, v.trgetQestnSeqNo);
				senarioWapper.append(setTrgetQestnView);
				senarioWapperArr.push(senarioWapper);
			});
		} else {
			senarioWapper = setQestnList(qestnList);
			if(senarioWapper == ''){
				return ;
			}
			let nowSelectQestn = senarioWapper.find('select[name="dtrmnQestnSeqNo"] > option:selected').data();
			let setSenarioCndWapper = setSenarioCnd(senarioWapper, nowSelectQestn.qestnItemData, 1);
			senarioWapper.append(setSenarioCndWapper);
			let setTrgetQestnListWapper = setTrgetQestnList(qestnList);
			senarioWapper.append(setTrgetQestnListWapper);
		}
		
		if($('#senarioModal').find('.senario').length != 0){
			$('#senarioModal').find('.senario-group').append('<hr>');
		}
		
		if(senarioWapperArr.length != 0){
			$.each(senarioWapperArr, function(i, v){
				if(i != 0){
					senarioWapper.after('<hr>');
				}
				senarioWapper.after(v);
			});
			return senarioWapperArr;
		} else {
			return senarioWapper;
		}
	}
	
	let setSenarioModal = function(options){
		//기본 모달 가져오기
		let defaultModal = $(defaultQestnarSenarioModalHTML);
		defaultModal.find('#nowQestnarSeqNo').val(options.qestnarSeqNo);
		
		defaultModal.on('show.bs.modal', function(e){
			let senarioGroupView = setQestnSenarioList(options);
			defaultModal.find('.senario-group').append(senarioGroupView);
		}).on('hidden.bs.modal', function(){
			defaultModal.remove();
		}).on('click', '.addSenarioBtn', function(){
			//시나리오 폼 추가 로직
			let senarioGroupView = setQestnSenarioList({qestnList : options.qestnList});
			defaultModal.find('.senario-group').append(senarioGroupView);
			//문항 check 및 reset
//			$.fn.resetQestn(option.qestnList);
			$.fn.resetQestnOption(senarioGroupView.find('.dtrmnQestn'));
		}).on('click', '.deleteBtn', function(){
			let senarioWapper = $(this).parents('.senario');
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
			let cndOptionWapper = setSenarioCnd(senarioWapper, nowSelectQestn, '0');
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
			let senarioWapper = $(this).parents('.senario');
			$.fn.resetQestnOption($(this));
			let nowSelectQestn = senarioWapper.find('select[name="dtrmnQestnSeqNo"] > option:selected').data('qestnItemData');
			if(nowSelectQestn.qestnOptn.optionText != undefined){
				$.fn.resetQestnOption($(this));
			}
		}).on('change', 'select[name="dtrmnCnd"]', function(){
			let senarioWapper = $(this).parents('.senario');
			let nowSelectQestn = senarioWapper.find('select[name="dtrmnQestnSeqNo"] > option:selected').data('qestnItemData');
			if(nowSelectQestn.qestnAswperList.aswperText == undefined){
				$.fn.resetQestnOption($(this));
			}
		}).on('change', 'select[name="dtrmnQestnSeqNo"]', function(){
			let senarioWapper = $(this).parents('.senario');
			let nowSelectQestn = senarioWapper.find('select[name="dtrmnQestnSeqNo"] > option:selected').data('qestnItemData');
			senarioWapper.find('.cndValueOption').remove();
			let cndOptionDiv = setSenarioCnd(senarioWapper, nowSelectQestn, '1');
			senarioWapper.find('.dtrmnQestn').after(cndOptionDiv);
			//문항 check 및 reset
//			$.fn.resetQestn(option.qestnList);
			$.fn.resetQestnOption($(this));
		}).on('click', '#saveBtn', function(){
			//저장 로직
			if(options.isSaveFn){
				options.save($.fn.getSenarioDataSerialize());
			}
			
			defaultModal.modal('hide');
		});
		
		return defaultModal;
	}
	
	setSenario = function(options){
		if(options.qestnarSeqNo == ''){
			Swal.fire('확인', '설문조사를 선택해주세요.', 'warning');
			return ;
		}
		
		if(options.qestnList == null || options.qestnList == '' || options.qestnList == undefined || options.qestnList.length == 0){
			Swal.fire('확인', '설문조사 문항을 등록해주세요.', 'warning');
			return ;
		}
		
		let settingModal = setSenarioModal(options);
		
		$(document.body).append(settingModal);
		
		settingModal.modal('show');
	}
	
	$.initSenario = function(options){
		//설문 시나리오 셋팅 모달 생성 시작
		let defaults = {
				qestnarSeqNo : '',
				senarioRegistBtn : '',
				containerId : '',
				qestnList : null,
				senarioList : null,
				save : null
		};
		
		let senarioInitOption = $.extend(defaults, options || {});
		
		senarioInitOption.isSaveFn = jQuery.isFunction(senarioInitOption.save);
		
//		$('#'+senarioInitOption.senarioRegistBtn).click(function(){
			setSenario(senarioInitOption);
//		});
		
		if(senarioInitOption.containerId != '' && $('script[src$="dragula.min.js"]').length > 0){
			dragulaList = dragula([$('#'+senarioInitOption.containerId)[0]]);
		}
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
	
	$.fn.resetQestnOption = function(ele){
		let senarioDiv = $(ele).parents('.senario');
		let qestn = senarioDiv.find('select[name="dtrmnQestnSeqNo"] > option:selected').data('qestnItemData');
		//옵션에 대한 로직 추가 시 체크 필요
		let aswperList = qestn.qestnAswperList;
		let optnList = qestn.optnList;
		if(aswperList.length != 0){
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
//			//나머지는 옵션이 아닌 조건을 체크
//			let senarioQestnCndList = senarioDiv.find('select[name="dtrmnCnd"]');
//			let preSetValueArr = [];
//			$.each(senarioQestnCndList, function(i, v){
//				let thisQestnCnd = $(this).find('option:selected').val();
//				preSetValueArr.push(thisQestnCnd);
//			});
//			senarioDiv.find('select[name="dtrmnCnd"]').each(function(i, v){
//				let options = $(this).find('option');
//				$.each(options, function(i2, v2){
//					if(arrayContain($(this).val(), preSetValueArr)){
//						if($(this).val() != $(v).find('option:selected').val()){
//							v2.disabled = true;
//						}
//					} else {
//						v2.disabled = false;
//					}
//				});
//			});
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
				let value = $(this).find('select[name="dtrmnValue"] > option:selected').val(); 
				if(value == '' || value == null || value == undefined){
					value = $(this).find('input[name="dtrmnValue"]').val();
				}
				cndValueJson.cnd = cnd;
				cndValueJson.value = value;
				if(i != 0){
					let logic = $(this).find('select[name="dtrmnLogicCnd"]').val();
					cndValueJson.logic = logic;
				}
				cndJsons['s'+(i+1)] = cndValueJson;
//				eval('cndJsons.s'+(i+1)+'=cndValueJson;');
			});
			senarioJsonData.dtrmnCnd = cndJsons;
			//trgetQestn
			senarioJsonData.trgetQestnSeqNo = $(this).find('select[name="trgetQestnSeqNo"]').val();
			senarioJsonData.qestnarSeqNo = qestnarSeqNo;
			
			returnSerializeData.push(senarioJsonData);
		});
		
		if(returnSerializeData.length == 0){
			returnSerializeData.push({"qestnarSeqNo" : qestnarSeqNo});
		}
		
//		serializeOption.container.find('.card[id^="qestn"]').each(function(i, v){
//			returnSerializeData.push($(v).data('itemData'));
//			eval("returnSerializeData.data"+(i+1)+"="+JSON.stringify($(v).data('itemData'))+";");
//		});
		
		return returnSerializeData;
	}
	
})(jQuery);



