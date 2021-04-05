/**
 * SlickGrid 커스터마이징 사용 javascript
 * https://github.com/6pac/SlickGrid/wiki/Grid-Events
 */

/*
 * slickGrid 사용될 data 가져오기
 * (param값에 reqUrl을 넣을 경우 해당 URL로 요청 [contextPath는 자동으로 넣음])
 */
function getSlickGridData(queryStr, param) {
	let returnData = '';
	let reqUrl = '';
	
	if(queryStr == null || queryStr == '' || queryStr == undefined){
		swAlert('확인', '쿼리ID 문자열을 입력하세요.', 'warning');
		return null;
	}
	
	param.query = queryStr;
	
	if(param.reqUrl != null && param.reqUrl != '' && param.reqUrl != undefined){
		reqUrl = param.reqUrl;
	} else {
		reqUrl = "/cmmn/com/cuc/getSlickGridData.do";
	}
	
	callFunc(contextPath + reqUrl, 'post', JSON.stringify(param), function(status, data){
		if(status == 'success'){
			returnData = data.resultList;
		} else {
			swAlertToast('데이터 가져오기 실패');
			returnData = null;	
		}
	}, false);
	
	return returnData;
}

/*
 * 기본 SlickGrid
 * sgOption : slickGrid에서 사용되는 값들
 */
function slickGridExtand(sgOption) {
	let slickgrid = '';
	let defaultOption = {
		target : '#slickGrid',
		data : [],
		columns : [],
		options : {}
	};
	
	let sgOptions = $.extend(defaultOption, sgOption || {});
	
	if(sgOptions.target == null || sgOptions.target == '' || sgOptions.target == undefined) {
		swAlert('확인', 'Gird Target이 설정되지 않았습니다.', 'warning');
		return null;
	}
	
	if(sgOptions.dataView){
		slickgrid = new Slick.Grid(sgOptions.target, sgOptions.dataView, sgOptions.columns, sgOptions.options);
	} else {
		slickgrid = new Slick.Grid(sgOptions.target, sgOptions.data, sgOptions.columns, sgOptions.options);
	}
	return slickgrid;
}

/*
 * 기본 SlickGrid 2
 * target : slickGrid 표출 영역 태그 ID
 * options : slickGrid option들
 * queryStr : 가져올 데이터 쿼리 Str
 * param : 가져올 데이터 조건 파라미터
 * sortable : grid sort 할 지 (true/false)
 */
function slickGridBasic(sgOption) {
	let defaultOption = {
		target : '#slickGrid',
		options : {
			enableCellNavigation: true,
		},
		exceptColumn : [],
		headerText : {},
		queryStr : null,
		param : null,
		sortable : null,
		getAllDataClickEle : null,
		getAllData : null
	};
	
	sgOption.options = $.extend(defaultOption.options, sgOption.options || {});
	
	let sgOptions = $.extend(defaultOption, sgOption || {});
	
	let isGetAllDataFn= jQuery.isFunction(sgOptions.getAllData);
	
	if(sgOptions.queryStr != null) {
		let params = {};
		if(sgOptions.param != null){
			params = $.extend(sgOptions.param, params || {});
		}
		let resultData = getSlickGridData(sgOptions.queryStr, params);
		sgOptions.selectData = resultData;
	}
	
	let logData = sgOptions.selectData;
	let columns = [];
	$.each(logData[0], function(i, v){
		let exceptBool = false;
		$.each(sgOptions.exceptColumn, function(i2, v2){
			if(v2 == i){
				exceptBool = true;
				return false;
			}
		});
		if(exceptBool){
			return true;
		}
		let headerStr = null;
		if(sgOptions.headerText[i] != null && sgOptions.headerText[i] != '' && sgOptions.headerText[i] != undefined){
			headerStr = sgOptions.headerText[i];
		} else {
			headerStr = i;
		}
		let columnOption = {id:headerStr, name:headerStr, field:headerStr};
		if(sgOptions.sortable != null && new Boolean(sgOptions.sortable)){
			columnOption.sortable = sgOptions.sortable;
		}
		columns.push(columnOption);
	});
	
	let rows = [];
	$.each(logData, function(i, v){
		let set = {};
		set["id"] = "id_" + i;
		$.each(v, function(i2, v2){
			let headerStr = null;
			if(sgOptions.headerText[i2] != null && sgOptions.headerText[i2] != '' && sgOptions.headerText[i2] != undefined){
				headerStr = sgOptions.headerText[i2];
			} else {
				headerStr = i2;
			}
			set[headerStr] = v2;
		});
		rows.push(set);
	});
	
	sgOptions.data = rows;
	sgOptions.columns = columns;
	
	if(sgOptions.sortable == true){
		let dataView = new Slick.Data.DataView({ inlineFilters: true });
		dataView.beginUpdate();
		dataView.setItems(rows);
		dataView.endUpdate();
		
		sgOptions.dataView = dataView;
	}
	
	if(sgOptions.getAllDataClickEle != null){
		if(isGetAllDataFn){
			$(sgOptions.getAllDataClickEle).click(function(){
				let len = slickGrid.getDataLength();
				let datas = [];
				for(let i = 0; i < len; i++){
					datas.push(summaryEditSlickgrid.getDataItem(i));
				}
				sgOptions.getAllData(datas);
			});
		}
	}
	
	let slickGrid = slickGridExtand(sgOptions);
	
	if(sgOptions.sortable == true){
		slickGrid.onSort.subscribe(function (e, args) {
			addSort(args, sgOptions.dataView);
		});
		sgOptions.dataView.onRowCountChanged.subscribe(function (e, args) {
			addDataViewRowCountChange(slickGrid, args);
		});
		sgOptions.dataView.onRowsChanged.subscribe(function (e, args) {
			addDataViewRowChange(slickGrid, args);
		});
		return slickGrid;
	} else {
		return slickGrid;
	}
}

/*
 * 에디터 SlickGrid
 * target : slickGrid 표출 영역 태그 ID
 * options : slickGrid option들
 * queryStr : 가져올 데이터 쿼리 Str
 * param : 가져올 데이터 조건 파라미터
 * sortable : grid sort 할 지 (true/false)
 * columnEditorType : [] 타이틀 별 에디터 유형
 * 
 * 에디터 종류
 * "Text", "Integer", "Float", "Date", "YesNoSelect", "Checkbox", "PercentComplete", "LongText"
 */
function slickGridEdit(sgOption){
	let defaultOption = {
		target : '#slickGrid',
		options : {
			enableCellNavigation: true,
		    editable: true,
		    enableAddRow: false,
		    rowHeight: 30
		},
		exceptColumn : [],
		headerText : {},
		queryStr : null,
		param : null,
		sortable : null,
		columnEditorType : null,
		onChange : null,
		onRowDragEnd : null,
		getAllDataClickEle : null,
		getAllData : null
	};
	
	sgOption.options = $.extend(defaultOption.options, sgOption.options || {});
	
	let sgOptions = $.extend(defaultOption, sgOption || {});
	
	let isOnChangeFn = jQuery.isFunction(sgOptions.onChange);
	let isOnRowDragEndFn = jQuery.isFunction(sgOptions.onRowDragEnd);	
	let isGetAllDataFn= jQuery.isFunction(sgOptions.getAllData);
	
	if(sgOptions.sortable == true && isOnRowDragEndFn){
		swAlert('확인', '정렬과 Row 순서 변경은 같이 사용할 수 없습니다. (자동으로 정렬 false처리)', 'warning');
		sgOptions.sortable = false;
	}
	
	if(sgOptions.queryStr != null) {
		let params = {};
		if(sgOptions.param != null){
			params = $.extend(sgOptions.param, params || {});
		}
		let resultData = getSlickGridData(sgOptions.queryStr, params);
		sgOptions.selectData = resultData;
	}
	
	let logData = sgOptions.selectData;
	let columns = [];
	let columnIdx = 0;
	$.each(logData[0], function(i, v){
		let exceptBool = false;
		$.each(sgOptions.exceptColumn, function(i2, v2){
			if(v2 == i){
				exceptBool = true;
				return false;
			}
		});
		if(exceptBool){
			return true;
		}
		let headerStr = null;
		if(sgOptions.headerText[i] != null && sgOptions.headerText[i] != '' && sgOptions.headerText[i] != undefined){
			headerStr = sgOptions.headerText[i];
		} else {
			headerStr = i;
		}
		
		let columnOption = {id:headerStr, name:headerStr, field:headerStr};
		
		if(sgOptions.columnEditorType != null && sgOptions.columnEditorType.length > columnIdx){
			let type = sgOptions.columnEditorType[columnIdx];
			if(type != null && type != '' && type != undefined){
				columnOption.editor = Slick.Editors[type];
			}
		}
		
		if(sgOptions.sortable != null && new Boolean(sgOptions.sortable)){
			columnOption.sortable = sgOptions.sortable;
		}
		
		columnOption["behavior"] = "selectAndMove";
		columns.push(columnOption);
		columnIdx++;
	});
	
	let rows = [];
	$.each(logData, function(i, v){
		let set = {};
		set["id"] = "id_" + i;
		$.each(v, function(i2, v2){
			let headerStr = null;
			if(sgOptions.headerText[i2] != null && sgOptions.headerText[i2] != '' && sgOptions.headerText[i2] != undefined){
				headerStr = sgOptions.headerText[i2];
			} else {
				headerStr = i2;
			}
			set[headerStr] = v2;
		});
		rows.push(set);
	});
	
	sgOptions.data = rows;
	sgOptions.columns = columns;
	
	if(sgOptions.sortable == true){
		let dataView = new Slick.Data.DataView({ inlineFilters: true });
		dataView.beginUpdate();
		dataView.setItems(rows);
		dataView.endUpdate();
		
		sgOptions.dataView = dataView;
	}
	
	let slickGrid = slickGridExtand(sgOptions);
	
	if(isOnChangeFn){
		slickGrid.onCellChange.subscribe(function(e, data){
			sgOptions.onChange(data);
			e.stopPropagation();
		});
	}
	
	if(isOnRowDragEndFn){
		slickGrid.setSelectionModel(new Slick.RowSelectionModel());
		let moveRowsPlugin = moveFn(slickGrid, sgOptions);
		slickGrid.registerPlugin(moveRowsPlugin);
	}
	
	if(sgOptions.getAllDataClickEle != null){
		if(isGetAllDataFn){
			$(sgOptions.getAllDataClickEle).click(function(){
				let len = slickGrid.getDataLength();
				let datas = [];
				for(let i = 0; i < len; i++){
					datas.push(summaryEditSlickgrid.getDataItem(i));
				}
				sgOptions.getAllData(datas);
			});
		}
	}
	
	if(sgOptions.sortable == true){
		slickGrid.onSort.subscribe(function (e, args) {
			addSort(args, sgOptions.dataView);
		});
		sgOptions.dataView.onRowCountChanged.subscribe(function (e, args) {
			addDataViewRowCountChange(slickGrid, args);
		});
		sgOptions.dataView.onRowsChanged.subscribe(function (e, args) {
			addDataViewRowChange(slickGrid, args);
		});
		return slickGrid;
	} else {
		return slickGrid;
	}
}

function moveFn(grid, options){
	let data = options.data;
	let moveRowsPlugin = new Slick.RowMoveManager({
		cancelEditOnDrag: true
	});

	moveRowsPlugin.onBeforeMoveRows.subscribe(function (e, data) {
		for (var i = 0; i < data.rows.length; i++) {
			//no point in moving before or after itself
			if (data.rows[i] == data.insertBefore || data.rows[i] == data.insertBefore - 1) {
				e.stopPropagation();
				return false;
			}
		}
		return true;
	});

	moveRowsPlugin.onMoveRows.subscribe(function (e, args) {
		var extractedRows = [], left, right;
		var rows = args.rows;
		var insertBefore = args.insertBefore;
		left = data.slice(0, insertBefore);
		right = data.slice(insertBefore, data.length);

		rows.sort(function(a,b) { return a-b; });

		for (var i = 0; i < rows.length; i++) {
			extractedRows.push(data[rows[i]]);
		}

		rows.reverse();

		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if (row < insertBefore) {
				left.splice(row, 1);
			} else {
				right.splice(row - insertBefore, 1);
			}
		}

		data = left.concat(extractedRows.concat(right));

	    var selectedRows = [];
	    for (var i = 0; i < rows.length; i++) {
	      selectedRows.push(left.length + i);
	    }
	    
	    grid.resetActiveCell();
	    grid.setData(data);
	    grid.setSelectedRows(selectedRows);
	    grid.render();
	    
	    options.onRowDragEnd(data[selectedRows], selectedRows);
	});
	
	return moveRowsPlugin;
}

function addDataViewRowCountChange(grid, args){
	grid.updateRowCount();
	grid.render();
}

function addDataViewRowChange(grid, args){
	grid.invalidateRows(args.rows);
	grid.render();
}

function addSort(args, dataView){
	sortdir = args.sortAsc ? 1 : -1;
    sortcol = args.sortCol.field;

    if (isIEPreVer9()) {
    	// using temporary Object.prototype.toString override
    	// more limited and does lexicographic sort only by default, but can be much faster

    	var percentCompleteValueFn = function () {
    		var val = this["percentComplete"];
    		if (val < 10) {
    			return "00" + val;
    		} else if (val < 100) {
    			return "0" + val;
    		} else {
    			return val;
    		}
    	};

    	// use numeric sort of % and lexicographic for everything else
    	dataView.fastSort((sortcol == "percentComplete") ? percentCompleteValueFn : sortcol, args.sortAsc);
    } else {
    	// using native sort with comparer
    	// preferred method but can be very slow in IE with huge datasets
    	dataView.sort(comparer, args.sortAsc);
    }
}

function isIEPreVer9() { 
	var v = navigator.appVersion.match(/MSIE ([\d.]+)/i); 
	return (v ? v[1] < 9 : false); 
}

function comparer(a, b) {
	var x = a[sortcol], y = b[sortcol];
	return (x == y ? 0 : (x > y ? 1 : -1));
}



