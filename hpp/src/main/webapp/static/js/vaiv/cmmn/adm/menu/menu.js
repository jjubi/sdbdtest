/**
 * 관리자 > 메뉴 관리 javascript
 * */
$(document).ready(function(){
	if($('.dd').length > 0){
		//최대 Depth : 3
		$('.dd').nestable({
			maxDepth : 3
		});
	}
	
	let menuSe = ($('#menuSe').val() == '' ? 'U' : $('#menuSe').val());
	let menuList = getMenuList(menuSe);
	createMenuCardList(menuList);
	
	$('#menuRegistModal').on('show.bs.modal', function(e){
		let btnEl = $(e.relatedTarget);
		let type = btnEl.data('type');
		
		if(type == 'mod'){
			//데이터 넣기
			let itemData = btnEl.parents('.dd-item.kanban-item').eq(0).data();
			$(this).find('label[for="useAt'+itemData.useAt+'"]').click();
			$(this).find('label[for="type'+itemData.menuTy+'"]').click();
			$(this).find('#menuNm').val(itemData.menuNm);
			$(this).find('#menuUrl').val(itemData.menuUrl);
			$(this).find('#menuTarget').val(itemData.menuTarget);
			$(this).find('#menuAuthor').val(itemData.menuAuthor);
			$(this).find('#menuIconClass').val(itemData.menuIconClass);
			if(itemData.menuTy == 'B'){
				$(this).find('#menuBbs').val(itemData.menuUrl).prop('selected', true);
			} else if(itemData.menuTy == 'C'){
				$(this).find('#menuCntnts').val(itemData.menuUrl).prop('selected', true);
			}
			$(this).find('.modal-title').text('메뉴 수정');
			$(this).find('.btn-primary').text('수정').attr('id', 'menuModBtn').data('dsn', itemData.dsn);
		} else {
			$(this).find('label[for="useAtY"]').click();
			$(this).find('label[for="typeP"]').click();
//			$(this).find('input[name="menuUseAt"]:input[value="Y"]').attr('checked', true);
			$(this).find('.modal-title').text('메뉴 생성');
			$(this).find('.btn-primary').text('생성');
			$(this).find('.btn-primary').text('생성').attr('id', 'menuRegistBtn');
		}
	}).on('hidden.bs.modal', function(){
		if($(this).find('.btn-primary').text() == '수정'){
			let registF_data = $('#menuRegistModal').find('#menuRegistForm');
			registF_data[0].reset();
		}
	}).on('click', '#menuRegistBtn', function(){
		let registF_data = $('#menuRegistModal').find('#menuRegistForm');
		//타입에 따른 validation
		let typeVal = registF_data.find('input[name="menuTy"]:checked').val();
		if(typeVal == 'B'){
			if(registF_data.find('#menuBbs').val() == ''){
				swAlert('확인', '게시판을 선택해주세요.', 'warning');
				return ;
			}
		} else if(typeVal == 'C'){
			if(registF_data.find('#menuCntnts').val() == ''){
				swAlert('확인', '컨텐츠를 선택해주세요.', 'warning');
				return ;
			}
		}
		
		let menuItem = createMenuCard(registF_data, 'R');
		
		$('#menuList').append(menuItem);	
		
		//reset
		registF_data[0].reset();
		$('#menuRegistModal').modal('hide');
	}).on('click', '#menuModBtn', function(){
		let registF_data = $('#menuRegistModal').find('#menuRegistForm');
		//타입에 따른 validation
		let typeVal = registF_data.find('input[name="menuTy"]:checked').val();
		if(typeVal == 'B'){
			if(registF_data.find('#menuBbs').val() == ''){
				swAlert('확인', '게시판을 선택해주세요.', 'warning');
				return ;
			}
		} else if(typeVal == 'C'){
			if(registF_data.find('#menuCntnts').val() == ''){
				swAlert('확인', '컨텐츠를 선택해주세요.', 'warning');
				return ;
			}
		}
		
		let targetItemEle = $('#menuList').find('.s'+$(this).data('dsn'));
		
		targetItemEle.data('menuAuthor', registF_data.find('#menuAuthor').val());
		targetItemEle.data('menuNm', registF_data.find('#menuNm').val());
		targetItemEle.data('menuUrl', registF_data.find('#menuUrl').val());
		targetItemEle.data('menuTarget', registF_data.find('#menuTarget').val());
		targetItemEle.data('useAt', registF_data.find('input[name="menuUseAt"]:checked').val());
		targetItemEle.data('menuIconClass', registF_data.find('#menuIconClass').val());
		targetItemEle.data('menuTy', registF_data.find('input[name="menuTy"]:checked').val());
		
		targetItemEle.find('.menu-name').eq(0).text(registF_data.find('#menuNm').val());
		
		if(registF_data.find('input[name="menuUseAt"]:checked').val() == 'Y'){
			targetItemEle.find('.card-text.small.text-muted').eq(0).text('사용');
		} else {
			targetItemEle.find('.card-text.small.text-muted').eq(0).text('사용안함');
		}
		
		//reset
		registF_data[0].reset();
		$('#menuRegistModal').modal('hide');
	}).on('change', 'input[name="menuTy"]', function(){
		let menuTy = $(this).val();
		if(menuTy == 'P'){
			$('#menuUrlArea').show().find('#menuUrl').val('');
			$('#menuBbsArea').hide().find('#menuBbs > option:eq(0)').prop('selected', true);
			$('#menuCntntsArea').hide().find('#menuCntnts > option:eq(0)').prop('selected', true);
		} else if(menuTy == 'B'){
			$('#menuUrlArea').hide().find('#menuUrl').val('');
			$('#menuBbsArea').show().find('#menuBbs > option:eq(0)').prop('selected', true);
			$('#menuCntntsArea').hide().find('#menuCntnts > option:eq(0)').prop('selected', true);
		} else {
			$('#menuUrlArea').hide().find('#menuUrl').val('');
			$('#menuBbsArea').hide().find('#menuBbs > option:eq(0)').prop('selected', true);
			$('#menuCntntsArea').show().find('#menuCntnts > option:eq(0)').prop('selected', true);
		}
	}).on('change', '#menuBbs, #menuCntnts', function(){
		let value = $(this).val();
		$('#menuUrl').val(value);
	});
	
	$('#menuList').on('click', '.menuDelete', function(){
		let thisEl = $(this);
		swAlertConfirm('삭제하시겠습니까?',{icon:'info',text:'일괄저장해야 정상적으로 삭제가 됩니다.',confirmButtonText:'삭제'}, function(){
			let parents = thisEl.parents('.dd-item.kanban-item');
			let pitem = parents.eq(1);	//바로 상위 Depth
			let titem = parents.eq(0);	//자신 Depth
			let tData = titem.data();	//자신 data
			//삭제 문자열 추가
			if(tData.menuSeqNo != null) {
				let deleteMenuStrs = $('#deleteMenuStr').val();
				if(deleteMenuStrs != '') {
					deleteMenuStrs += ',';
				}
				$('#deleteMenuStr').val(deleteMenuStrs + tData.menuSeqNo);
			}
			//하위 item 삭제 문자열 추가
			titem.find('.dd-item.kanban-item').each(function(i, v){
				let tData = $(this).data();	//자신 data
				//삭제 문자열 추가
				if(tData.menuSeqNo != null) {
					let deleteMenuStrs = $('#deleteMenuStr').val();
					if(deleteMenuStrs != '') {
						deleteMenuStrs += ',';
					}
					$('#deleteMenuStr').val(deleteMenuStrs + tData.menuSeqNo);
				}
			});
			//자신 삭제
			titem.remove();		
			//상위 메뉴에서 하위 메뉴 확인 후 collapsed 삭제
			let itemCnt = pitem.find('.dd-list > .dd-item').length;
			if(itemCnt == 0){
				pitem.find('.collapseBtn, .expandBtn').remove().removeClass('.dd-collapsed');
			}
		});
	}).on('click', '.menuOneSave', function(){
		let thisEl = $(this);
		swAlertConfirm('개별 저장 하시겠습니까?',{icon:'info',text:'순서 변동사항은 일괄저장해야 변경이 됩니다.',confirmButtonText:'개별저장'}, function(){
			pageLoadingView('show');
			let saveItem = thisEl.parents('.dd-item.kanban-item').eq(0);
			let saveData_Json = window.JSON.stringify(saveItem.data());
			console.log(saveData_Json);
			$.ajax({
				url : contextPath + '/cmmn/adm/menu/updateMenuAjax.do',
				type : 'post',
				data : {"menuJson" : saveData_Json},
				dataType : 'json',
				success : function(data){
					pageLoadingView('hide');
					if(data.result == "success"){
						swAlert('성공', '개별저장 완료.', 'success');
					} else {
						swAlert('실패', '개별저장 실패. 관리자에게 문의하세요.', 'error');
					}
				}, error : function(status) {
					pageLoadingView('hide');
					swAlert('실패', '개별저장 실패. 관리자에게 문의하세요.', 'error');
				}
			});
		});
	});
});

function getMenuList(type){
	let menuList = '';
	//메뉴 가져오기
	$.ajax({
		url : contextPath + '/cmmn/adm/menu/selectMenuListAjax.do',
		data : {'menuSe' : type},
		type : 'post',
		dataType : 'json',
		async : false,
		success : function(data){
			if(data.result == "success"){
				menuList = data.resultList;
			} else {
				swAlert('실패', '메뉴 불러오기 실패. 관리자에게 문의하세요.', 'error');
			}
		}, error : function(error){
			console.log(error);
		}
	});
	
	return menuList;
}

function createMenuCardList(menuList){
	$('#menuList').empty();
	$.each(menuList, function(i, v){
		let menuItem = createMenuCard(v, 'L');
		
		//부모Sn이 0이 아닌 경우 자식이므로
		if(v.menuDp != 1){
			let depth1_lastItem = $('#menuList > .dd-item.kanban-item').last();
			//2depth
			if(v.menuDp != 2){
				//3depth
//				let depth2_lastItem = depth1_lastItem.children('.dd-list > .dd-item.kanban-item').last();
				let depth2_lastItem = depth1_lastItem.children('.dd-list').last().children('.dd-item.kanban-item').last();
				if(depth2_lastItem.find('.dd-list').length > 0){
					//첫 자식이 아닐 경우 
					depth2_lastItem.find('.dd-list').eq(0).append(menuItem);
				} else {
					//첫 자식일 경우 접기 펼치기 버튼 생성 및 ol list 생성
					depth2_lastItem.prepend('<button class="collapseBtn" data-action="collapse" type="button">Collapse</button>');
					depth2_lastItem.prepend('<button class="expandBtn" data-action="expand" type="button" style="display: none;">Expand</button>');
					
					let inList = $('<ol class="dd-list"></ol>');
					inList.append(menuItem)
					depth2_lastItem.append(inList);
				}
			} else {
				//2depth
				if(depth1_lastItem.find('.dd-list').length > 0){
					//첫 자식이 아닐 경우 
					depth1_lastItem.find('.dd-list').eq(0).append(menuItem);
				} else {
					//첫 자식일 경우 접기 펼치기 버튼 생성 및 ol list 생성
					depth1_lastItem.prepend('<button class="collapseBtn" data-action="collapse" type="button">Collapse</button>');
					depth1_lastItem.prepend('<button class="expandBtn" data-action="expand" type="button" style="display: none;">Expand</button>');
					
					let inList = $('<ol class="dd-list"></ol>');
					inList.append(menuItem)
					depth1_lastItem.append(inList);
				}
			}
			return true;
		}
		
		$('#menuList').append(menuItem);
	});
}

function createMenuCard(data, type){
	//메뉴 생성
	let menuItem = $('<li class="dd-item kanban-item"></li>');
	let menuNm = '';
	let menuUseAt = '';
	let dSn = moment().valueOf();
	sleep(10);
	if(type == 'R'){
		//등록시
		menuItem.addClass('s'+dSn);
		menuItem.data('dsn', dSn);
		menuItem.data('upperSeqNo', '');
		menuItem.data('menuAuthor', data.find('#menuAuthor').val());
		menuItem.data('menuNm', data.find('#menuNm').val());
		menuItem.data('menuUrl', data.find('#menuUrl').val());
		menuItem.data('menuTarget', data.find('#menuTarget').val());
		menuItem.data('useAt', data.find('input[name="menuUseAt"]:checked').val());
		menuItem.data('menuIconClass', data.find('#menuIconClass').val());
		menuItem.data('menuTy', data.find('input[name="menuTy"]:checked').val());
		menuNm = data.find('#menuNm').val();
	} else {
		//목록 호출시
		menuItem.addClass('s'+dSn);
		menuItem.data('dsn', dSn);
		menuItem.data('menuSeqNo', data.menuSeqNo);
		menuItem.data('upperSeqNo', data.upperSeqNo);
		menuItem.data('menuAuthor', data.menuAuthor);
		menuItem.data('menuNm', data.menuNm);
		menuItem.data('menuUrl', data.menuUrl);
		menuItem.data('menuTarget', data.menuTarget);
		menuItem.data('useAt', data.useAt);
		menuItem.data('menuIconClass', data.menuIconClass);
		menuItem.data('menuTy', data.menuTy);
		menuNm = data.menuNm;
		menuUseAt = (data.useAt == 'Y' ? '사용' : '사용안함');
	}
	menuItem.data('menuSe', $('#menuSe').val());
	
	menuItem.append('<div class="dd-handle dd3-handle">Drag</div>');
	let menuCard = $('<div class="card card-sm mb-2"></div>');
	let cardBody = $('<div class="card-body"></div>');
	let cardBody_in = $('<div class="row align-items-center"></div>');
	//Body
	let cardBody_name = '<div class="col-12 col-sm">'+
							'<p class="mb-sm-0 menu-name">'+
							menuNm
							'</p>'+
						'</div>';
	let cardBody_useyn = '<div class="col col-sm-auto">';
	cardBody_useyn += '<p class="card-text small text-muted mb-0">';
	if(type == 'R'){
		if(data.find('input[name="menuUseAt"]:checked').val() == 'Y'){
			cardBody_useyn += '사용';
		} else {
			cardBody_useyn += '사용안함';
		}
	} else {
		cardBody_useyn += menuUseAt;
	}
	cardBody_useyn += '	</p>';
	cardBody_useyn += '</div>';

	cardBody_in.append(cardBody_name);
	cardBody_in.append(cardBody_useyn);

	let cardBody_btnGrp_str = '<div class="col-auto col-sm-auto">';
	cardBody_btnGrp_str += '<div class="avatar-group">'; 
	cardBody_btnGrp_str += '<a class="btn btn-sm btn-info mr-3" href="#menuRegistModal" data-toggle="modal" data-type="mod">수정</a>';
	if(menuItem.data('menuSeqNo') != null && menuItem.data('menuSeqNo') != '' && menuItem.data('menuSeqNo') != undefined){
		cardBody_btnGrp_str += '<a class="btn btn-sm btn-primary mr-3 menuOneSave">개별 저장</a>';
	}
	cardBody_btnGrp_str += '<a class="btn btn-sm btn-danger menuDelete">삭제</a>';
	cardBody_btnGrp_str += '</div>';
	cardBody_btnGrp_str += '</div>';
	
	cardBody_in.append(cardBody_btnGrp_str);
	
	cardBody.append(cardBody_in);
	menuCard.append(cardBody);
	menuItem.append(menuCard);
	
	return menuItem;
}

function menuSave(){
	swAlertConfirm('일괄 저장 하시겠습니까?', {icon:'info',confirmButtonText:'일괄저장'}, function(){
		pageLoadingView('show');
		let menuList = $('.dd');
		let menuList_Json = window.JSON.stringify(menuList.nestable('serialize'));
		console.log(menuList_Json);
		
		let insertFrm = $('<form></form>');
		
		insertFrm.attr('method', 'post');
		insertFrm.attr('action', contextPath + '/cmmn/adm/menu/insertMenu.do');
		insertFrm.append('<input type="hidden" name="menuListJson" value=\''+menuList_Json+'\'>');
		insertFrm.append('<input type="hidden" name="menuSe" value="'+$('#menuSe').val()+'">');
		insertFrm.append('<input type="hidden" name="menuDeleteStr" value="'+$('#deleteMenuStr').val()+'">');
		
		$(document.body).append(insertFrm);
		insertFrm.submit();	
	});
}