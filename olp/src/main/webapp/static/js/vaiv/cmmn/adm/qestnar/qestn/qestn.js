/**
 * 설문조사 문항 만들기 javascript v1.1
 * 
 * Copyright By vaiv
 * 
 * @author hm
 * 
 * Date : 2020.12.31
 */
$(document).ready(function(){
	$('#qestnList1').data('pge', 1);
	$(document).on('click', 'a.qestnMod', function(){
		let qestnData = $(this).parents('.card').data('qestnData');
		let type = qestnData.qestnTy;
		setQestn({modalType : 'modify', qestnType : type, inputData : qestnData});
	});
	
	$(document).on('click', 'a.qestnDel', function(){
		let thisEl = $(this);
		swAlertConfirm('삭제하시겠습니까?',{icon:'warning', text:'저장해야 정상적으로 삭제가 됩니다.',confirmButtonText:'삭제'},function(){
			thisEl.parents('.card')[0].remove();
		});
	});
});

function fnSelectQestnarList(){
	let searchForm = document.getElementById("hiddenSearchForm");
	searchForm.action = contextPath + "/cmmn/adm/qestnar/qestnarMain.do";
	searchForm.submit();
}

function fnSelectQestnList(qestnarSeqNo){
	pageLoadingView('show');
	let returnValue = null;
	$.ajax({
		url : contextPath + '/cmmn/adm/qestnar/qestn/selectQestnListAjax.do'
		,type : 'post'
		,data : {"qestnarSeqNo" : qestnarSeqNo}
		,async : false
		,dataType : 'json'
		,success : function(data){
			if(data.result == "success"){
				console.log(data.qestnDataList);
				let returnJsonArr = [];
				$.each(data.qestnDataList, function(i, v){
					let json = {};
					
					json.qestnSj = v.qestnSj;
					json.qestnTy = v.qestnTy;
					json.qestnHpcm = v.qestnHpcm;
					json.qestnEssntlAt = v.qestnEssntlAt;
					json.qestnId = 'qestn0000' + v.qestnSeqNo;
					json.qestnPge = v.qestnPge;
					json.qestnMaxPge = v.qestnMaxPge;
					
					if(v.qestnAswperList.length != 0){
						if(v.qestnAswperList.length == 1){
							json.aswperText = v.qestnAswperList[0].aswperText;
							if(v.aswperScore != null && v.aswperScore != '' && v.aswperScore != undefined){
								json.aswperScore = v.aswperScore;
								json.aswperScoreAt = 'Y';
							} else {
								json.aswperScoreAt = 'N';
							}
						} else {
							let aswperTextArr = [];
							let aswperScoreArr = [];
							$.each(v.qestnAswperList, function(i2, v2){
								aswperTextArr.push(v2.aswperText);
								if(v2.aswperScore != null && v2.aswperScore != '' && v2.aswperScore != undefined){
									aswperScoreArr.push(v2.aswperScore);
								}
								
								if(v2.aswperEtcAt == 'Y'){
									json.aswperEtcAt = 'Y';
								} else {
									json.aswperEtcAt = 'N';
								}
							});
							json.aswperText = aswperTextArr;
							json.aswperScore = aswperScoreArr;
							if(aswperScoreArr.length != 0){
								json.aswperScoreAt = 'Y';
							} else {
								json.aswperScoreAt = 'N';
							}
						}
					}
					
					
					if(v.qestnOptnList.length != 0){
						$.each(v.qestnOptnList, function(i2, v2){
							if(v2.optnTy == 'Tic'){
								json.optnTicAt = v2.optnValue;
							} else if(v2.optnTy == 'Lwc'){
								if(v2.optnValue == 'N'){
									json.optnLwcAt = v2.optnValue; 
								} else {
									json.optnLwcAt = 'Y';
									json.optnLwcValue = v2.optnValue;
								}
							} else if(v2.optnTy == 'Afe'){
								if(v2.optnValue == 'N'){
									json.optnAfeAt = v2.optnValue; 
								} else {
									json.optnAfeAt = 'Y';
									json.optnAfeValue = v2.optnValue;
								}
							} else if(v2.optnTy == 'Mfc'){
								if(v2.optnValue == 'N'){
									json.optnMfcAt = v2.optnValue; 
								} else {
									json.optnMfcAt = 'Y';
									json.optnMfcValue = v2.optnValue;
								}
							}
						});
					}
					
					returnJsonArr.push(json);
				});
				
				returnValue = returnJsonArr;
			} else {
				swAlert('확인', '설문 문항 정보 가져오기 실패. 관리자에게 문의하세요.', 'error');
				returnValue = null;
			}
		}, error : function(status){
			swAlert('확인', '설문 문항 정보 가져오기 실패. 관리자에게 문의하세요.', 'error');
			returnValue = null;
		}
	});
	pageLoadingView('hide');
	return returnValue;
}

function qestnarQestnSave(){
	if($('.qestnListArea').find('.card').length == 0) {
		swAlert('확인', '문항은 최소 1개를 입력해야됩니다.', 'info');
		return ;
	}
	swAlertConfirm('문항을 저장 하시겠습니까?',{icon:'info', text:'저장 시 설정된 시나리오는 모두 삭제가 됩니다.',confirmButtonText:'저장'},function(){
		pageLoadingView('show');
		let qestnJsonData = $.getQestnDataSerialize({container : $('.qestnListArea')});
		let saveData_Json = window.JSON.stringify(qestnJsonData);
		console.log(saveData_Json);
		//저장하기
		let qestnarSeqNo = $('input[name="qestnarSeqNo"]').val();
		$.ajax({
			url : contextPath + '/cmmn/adm/qestnar/qestn/insertQestnAjax.do',
			type : 'post',
			data : {"qestnarSeqNo" : qestnarSeqNo, "qestnData" : saveData_Json},
			dataType : 'json',
			success : function(data){
				pageLoadingView('hide');
				if(data.result == "success"){
					swAlert('성공', '문항 저장 완료.', 'success');
				} else {
					swAlert('실패', '문항 저장 실패. 관리자에게 문의하세요.', 'error');
				}
			}, error : function(status){
				pageLoadingView('hide');
				swAlert('실패', '문항 저장 실패. 관리자에게 문의하세요.', 'error');
			}
		});
	});
}

function addQestnPage(){
	let pageCnt = $('#pageContents > .tab-pane').length;
	let nowPageNo = pageCnt + 1;
	
	let tab = $(qestnTabHTML);
	tab.find('a').attr('id','page'+nowPageNo+'-tab').attr('href','#page'+nowPageNo).attr('aria-controls', 'page'+nowPageNo).text(nowPageNo+'P');
	$('#pageTabs > .nav-item:last').before(tab);
	
	let tabCn = $(qestnTabCnHTML);
	tabCn.attr('id','page'+nowPageNo).attr('aria-labelledby','page'+nowPageNo+'-tab').find('.qestnListArea').attr('id', 'qestnList'+nowPageNo).data('pge',nowPageNo);
	$('#pageContents').append(tabCn);
	
	dragulaList.containers.push(tabCn.find('#qestnList'+nowPageNo)[0]);
}

var qestnInitOptions;
var dragulaList;
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
			"radio"        : {tyEngTxt : "radio"    , tyKorTxt : '객관식', tyCnd : 'is, isNot'},
			"select"       : {tyEngTxt : "select"   , tyKorTxt : '객관식 목록형', tyCnd : 'is, isNot'},
			"checkbox"     : {tyEngTxt : "checkbox" , tyKorTxt : '객관식 중복형', tyCnd : 'is, isNot'},
//			"rImage"       : {tyEngTxt : "rImage"   , tyKorTxt : '객관식 이미지', tyCnd : 'is, isNot'},
//			"cbImage"      : {tyEngTxt : "cbImage"  , tyKorTxt : '객관식 이미지 중복형', tyCnd : 'is, isNot'},
			"text"         : {tyEngTxt : "text"     , tyKorTxt : '단답형', tyCnd : 'isCit, isNotCti'},
			"textarea"     : {tyEngTxt : "textarea" , tyKorTxt : '장문형', tyCnd : 'isCit, isNotCti'},
			"tel"          : {tyEngTxt : "tel"      , tyKorTxt : '연락처', tyCnd : 'is, isNot'},
			"address"      : {tyEngTxt : "address"  , tyKorTxt : '주소', tyCnd : 'isCit, isNotCti'},
			"calen"        : {tyEngTxt : "calen"    , tyKorTxt : '날짜', tyCnd : 'is, isNot'},
			"time"         : {tyEngTxt : "time"     , tyKorTxt : '시간', tyCnd : 'is, isNot'},
			"calenterm"    : {tyEngTxt : "calenterm", tyKorTxt : '기간', tyCnd : 'isCit, isNotCti'},
			"file"         : {tyEngTxt : "file"     , tyKorTxt : '파일', tyCnd : 'isCit, isNotCti'},
	};
	
	let setQestnTyList = function(options){
		let defaults = {
				selectTarget : 'qestnType',
				except : ''
		};
		let qestnTyOptions = $.extend(defaults, options || {});
		targetEl = $('#'+qestnTyOptions.selectTarget);
		targetEl.append('<option value="">--선택--</option>');
		$.each(qestnTypes, function(i, v){
			if(qestnTyOptions.except.indexOf(v.tyEngTxt)){
				targetEl.append('<option value="'+v.tyEngTxt+'">'+v.tyKorTxt+'</option>');
			}
		});
	}
	
	let setQestnModal = function(options){
		//기본 모달 가져오기
		let defaultModal = $(modalHTMLStr);
		
		//모달 타이틀 설정
		defaultModal.find('.qestnModalTitle').text(qestnTypes[options.qestnType].tyKorTxt + (options.modalType == 'regist' ? ' 문항 추가' : ' 문항 수정'));
		
		//모달 기본 body 생성
		let modalForm = defaultModal.find('#qestnModalForm');
		
		modalForm.append('<input type="hidden" name="qestnTy" value="'+options.qestnType+'">');
		if(options.modalType == 'modify'){
			modalForm.append('<input type="hidden" name="qestnId" value="'+options.inputData.qestnId+'">');
		}
		
		//모달 유형별 body 추가
		//기본정보 추가
		modalForm.append($(defaultQestntHTMLStr));
		//답안정보 추가
		let aswperArea = qestnTyByQestnHTML[options.qestnType].setAswperModal();
		if(aswperArea != null){
			modalForm.append('<hr>');
			modalForm.append(aswperArea);
			if(options.modalType == 'modify'){
				qestnTyByQestnHTML[options.qestnType].setAswperModalData(options.inputData, modalForm);
			}
		}
		//옵션정보 추가
		let optnArea = qestnTyByQestnHTML[options.qestnType].setOptnModal();
		if(optnArea != null){
			modalForm.append('<hr>');
			modalForm.append(optnArea);
			
			//수정 시 문항 모달 유형별 데이터 입력
			if(options.modalType == 'modify'){
				defaultModal.find('#confirm').text('수정').attr('id', 'modify');
				qestnTyByQestnHTML[options.qestnType].setOptnModalData(options.inputData, modalForm);
			}
		}
		
		//기본 모달 listener 등록
		defaultModal.on('show.bs.modal', function(){
			if(qestnInitOptions.isOpenFn){
				qestnInitOptions.setOpen(defaultModal);
			}
		});
		
		defaultModal.on('hidden.bs.modal', function(){
			if(qestnInitOptions.isCloseFn){
				qestnInitOptions.setClose(defaultModal);
			}
			//모달 닫을 때 삭제 및 변수 초기화
			defaultModal.remove();
		});
		
		/*
		 * 추가 버튼 클릭
		 */
		defaultModal.on('click', '#confirm', function(){
			let modalData = qestnTyByQestnHTML[options.qestnType].getModalData(modalForm);
			if(modalData == null){
				return ;
			}
			//qestnId 생성 후 넣기
			let d = new Date();
			modalData.qestnId = 'qestn'+d.getFullYear()+''+d.getMonth()+''+d.getDate()+''+d.getHours()+''+d.getMinutes()+''+d.getSeconds();
			if(qestnInitOptions.isConfirmFn){
				qestnInitOptions.confirm(defaultModal, modalData);
			}
			defaultModal.modal('hide');
		});
		
		/*
		 * 수정 버튼 클릭
		 */
		defaultModal.on('click', '#modify', function(){
			let modalData = qestnTyByQestnHTML[options.qestnType].getModalData(modalForm);
			if(qestnInitOptions.isModifyFn){
				qestnInitOptions.modify(defaultModal, modalData);
			}
			defaultModal.modal('hide');
		});
		
		return defaultModal;
	}
	
	setQestn = function(options){
		//설문 문항 유형별 셋팅 모달 생성 시작
		let defaults = {
				modalType : '',
				qestnType : '',
				inputData : null,
		}
		
		let qestnOptions = $.extend(defaults, options || {});
		if(qestnOptions.qestnMode == ''){
			swAlert('확인', '모달 유형를 입력하세요.', 'warning');
			return ;
		}
		if(qestnOptions.qestnType == ''){
			swAlert('확인', '문항 유형을 입력하세요.', 'warning');
			return ;
		}
		if(qestnOptions.modalType == 'modify'){
			if(qestnOptions.inputData == null){
				swAlert('확인', '수정 문항의 데이터를 입력하세요.', 'warning');
				return ;
			}
		}
		
		let settingModal = setQestnModal(qestnOptions);
		
		$(document.body).append(settingModal);
		
		settingModal.modal('show');
	}
	
	$.initQestn = function(options){
		//설문 문항 유형별 셋팅 모달 생성 시작
		let defaults = {
				qestnTySelectTrget : '',
				qestnRegistBtn : '',
				container : '',
				maxAswperCnt : 5,
				setClose : null,
				setOpen : null,
				confirm : null,
				modify : null,
				deleteItem : null,
				maxOptions : 5,
				qestnData : null
		}
		
		qestnInitOptions = $.extend(defaults, options || {});
		
		qestnInitOptions.isCloseFn = jQuery.isFunction(qestnInitOptions.setClose);
		qestnInitOptions.isOpenFn = jQuery.isFunction(qestnInitOptions.setOpen);
		qestnInitOptions.isConfirmFn = jQuery.isFunction(qestnInitOptions.confirm);
		qestnInitOptions.isModifyFn = jQuery.isFunction(qestnInitOptions.modify);
		qestnInitOptions.isDeleteFn = jQuery.isFunction(qestnInitOptions.deleteItem);
		
		//설문 유형 목록 생성
		setQestnTyList({selectTarget : qestnInitOptions.qestnTySelectTrget});
		
		$('#'+qestnInitOptions.qestnRegistBtn).click(function(){
			let type = $('#qestnTypes').val();
			setQestn({modalType : 'regist', qestnType : type});
		});
		
		
		if(qestnInitOptions.container != '' && $('script[src$="dragula.min.js"]').length > 0){
			dragulaList = dragula([$('#'+qestnInitOptions.container)[0]]);
		}
		
		if(qestnInitOptions.qestnData != null && qestnInitOptions.qestnData.length != 0){
			//데이터 셋팅
			//페이지 추가
			for(let i = 1; i < qestnInitOptions.qestnData[0].qestnMaxPge; i++){
				addQestnPage();
			}
			$.each(qestnInitOptions.qestnData, function(i, v){
				let view = qestnTyByQestnHTML[v.qestnTy].setView(v);
				let qestnPage = v.qestnPge;
				//페이지에 맞게 view 추가
				if(qestnInitOptions.container != ''){
					$('#qestnList'+qestnPage).append(view);
//					$('#'+qestnInitOptions.container).append(view);
				}
			});
		}
		
	}
	
	/**
	 * 질문 Data serialize
	 * */
	$.getQestnDataSerialize = function(options){
		let defaults = {
				container : '',
		};
		
		let serializeOption = $.extend(defaults, options || {});
		
		if(serializeOption.container == ''){
			swAlert('확인', '최상위 Container를 입력해주세요.', 'warning');
			return '';
		}
		
		let returnSerializeData = new Array();
		
		serializeOption.container.each(function(i, v){
			let qestnPge = $(this).data('pge');
			$(this).find('.card[id^="qestn"]').each(function(i2, v2){
				let qestnData = $(v2).data('qestnData');
				qestnData.qestnPge = qestnPge;
				returnSerializeData.push(qestnData);
			});
		});
		
		return returnSerializeData;
	}
	
})(jQuery);












