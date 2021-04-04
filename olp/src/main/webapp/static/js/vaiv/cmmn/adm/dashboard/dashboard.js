$(document).ready(function(){
	if($('.satisfaction').length > 0){
		$('.satisfaction').remove();
	}
	//로그인 일별 통계
	selectLoginLogDayStats();
	//게시판별 게시물 통계
	selectBbsToNttCntStats();
	//메뉴별 통계
	selectMenuCntStats();
	
	$('input[name="loginLogStatsType"]').change(function(){
		let type = $(this).val();
		if(type == 'D'){
			selectLoginLogDayStats();
		} else if(type == 'M'){
			selectLoginLogMonthStats();
		} else {
			selectLoginLogYearStats();
		}
	});
	
	$('input[name="bbsToNttStatsType"]').change(function(){
		let type = $(this).val();
		if(type == 'C'){
			selectBbsToNttCntStats();
		} else {
			selectBbsToNttAttrCntStats();
		}
	});
	
	fnSelectSysSummaryList();
	fnSelectSysSummaryList2();
});

function fnSelectSysSummaryList(){
	summarySlickgrid = slickGridBasic({
						target : '#sysLogSummaryData',
						queryStr : 'SysLog.selectSysLogSummaryList',
						options : {
							fullWidthRows : true,
						    forceFitColumns: false
						},
						sortable : true,
						exceptColumn : ['outptCo', 'svcNm'],
						headerText : {
							occrrncDe : '발생일',
							methodNm : '메소드명',
							requstUri : '요청URI',
							menuAt : '메뉴여부',
							menuNm : '메뉴명'
						},
						getAllDataClickEle : '#getData2',
						getAllData : function(data){
							console.log(data);
						}
					});
	
	if(summarySlickgrid == null){
		swAlertToast('에러 발생');
	}
}

function fnSelectSysSummaryList2(){
	summaryEditSlickgrid = slickGridEdit({
						target : '#sysLogSummaryData2',
						queryStr : 'SysLog.selectSysLogSummaryList',
						options : {
							fullWidthRows : true,
							forceFitColumns: false
						},
						sortable : false,
						exceptColumn : ['outptCo', 'svcNm'],
						headerText : {
							occrrncDe : '발생일',
							methodNm : '메소드명',
							requstUri : '요청URI',
							menuAt : '메뉴여부',
							menuNm : '메뉴명'
						},
						columnEditorType : ['Text', 'Text', '', '', ],
						onChange : function(data){
							let grid = data.grid;
							let row = data.row;
							let cell = data.cell;
							let item = data.item;
							console.log(data);
						},
						onRowDragEnd : function(data, selectedRows){
							console.log(data);
							console.log(selectedRows);
						},
						getAllDataClickEle : '#getData',
						getAllData : function(data){
							console.log(data);
						}
					});
	
	if(summaryEditSlickgrid == null){
		swAlertToast('에러 발생');
	}
}

function resetSort1(){
	let len = summaryEditSlickgrid.getDataLength();
	let datas = [];
	for(let i = 0; i < len; i++){
		datas.push(summaryEditSlickgrid.getDataItem(i));
	}
	summarySlickgrid.setSortColumn([]);
	let dataView = summarySlickgrid.getData();
	dataView.beginUpdate();
	dataView.setItems(datas);
	dataView.endUpdate();
	summarySlickgrid.render();
}
