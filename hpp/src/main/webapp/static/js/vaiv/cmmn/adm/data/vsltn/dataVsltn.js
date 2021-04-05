/**
 * 
 */
var uploadFileData = '';
var data = '';
var fileName = '';
var xFieldNm = '';
var xFieldData = [];
$(document).ready(function(){
	
	$('#limitRowNum').change(function(){
		let chartType = $('#chartType').val();
		changeChartType(chartType);
	});
	
	$('#chartType').change(function(){
		let chartType = $(this).val();
		changeChartType(chartType);
	});
});

function createDataVsltnCnrsUrl(){
	if($('.amChart').find('div').length == 0 && $('.dataView').find('div').length == 0){
		swAlert('확인', '차트 또는 데이터를 확인해주세요.', 'warning');
		return false;
	}
	
	swAlertInput('text', '공유할 데이터 명칭을 입력해주세요.', {
		title : '시각화 공유 URL 생성',
		inputAttributes:{
			autocapitalize:'off'
		},
  		confirmButtonText: '생성',
  		showLoaderOnConfirm: true
	}, function(result){
		if(result.value == ''){
			swAlert('확인', '데이터 명칭을 입력해야합니다.', 'warning');
			return false;
		} else {
			pageLoadingView('show');
			let formData = new FormData();
			formData.append('file', uploadFileData);	
			formData.append('vsltnCnrsNm', result.value);
			formData.append('vsltnTy', $('#chartType').val() + "-" + $.fn.getFileExt(fileName));
			let optnJson = {};
			optnJson.lineRN = $('#lineRowNum').val();
			optnJson.limitRN = $('#limitRowNum').val();
			formData.append('vsltnOptn', JSON.stringify(optnJson));
			
    		uploadData(contextPath + '/cmmn/adm/data/vsltn/createDataVsltnCnrsAjax.do', formData, function(status, data){
    			if(status == 'success'){
    				if(data.result == 'success'){
						swAlert('확인', "http://localhost:8080"+ data.cnrsUrl, 'success');
					} else {
						swAlert('확인', '공유 URL 생성 중 오류가 발생했습니다. 관리자에게 문의하세요.', 'error');	
					}
					pageLoadingView('hide');
    			} else {
    				swAlert('확인', '공유 URL 생성 중 오류가 발생했습니다. 관리자에게 문의하세요.', 'error');
					pageLoadingView('hide');
    			}
    		});
		}
	});
}

function chartAndDataView(viewData, fileName){
	try {
		visualExcelDataAmChartView(viewData, fileName);
	} catch (e) {
		swAlert('chart를 생성하지 못했습니다.');
		pageLoadingView('hide');
		return false;
	}
	try {
		visualExcelDataView(viewData, fileName);
	} catch (e) {
		swAlert('data를 표출하지 못했습니다.');
		pageLoadingView('hide');
		return false;
	}
}

function changeChartType(chartType){
	$('#lineNumInput').hide();
	
	if(chartType == 'column'){
		amChartColumn(xFieldNm, xFieldData, fileName, data);
		$('#lineNumInput').show().find('label').text('Line Row Num');
	} else if(chartType == 'column3d'){
		amChartColumn(xFieldNm, xFieldData, fileName, data, {"angle":30, "depth3D":20});
		$('#lineNumInput').show().find('label').text('Line Row Num');
	} else if(chartType == 'bar'){
		amChartColumn(xFieldNm, xFieldData, fileName, data, {"rotate": true});
		$('#lineNumInput').show().find('label').text('Line Row Num');
	} else if(chartType == 'line' || chartType == 'stepLine' || chartType == 'smoothLine'){
		amChartLine(xFieldNm, xFieldData, fileName, data);
		$('#lineNumInput').show().find('label').text('bar Row Num');
	} else if(chartType == 'area') {
		amChartArea(xFieldNm, xFieldData, fileName, data);
	} else if(chartType == 'radar') {
		amChartRadar(xFieldNm, xFieldData, fileName, data);
	} else if(chartType == 'polar'){
		amChartRadar(xFieldNm, xFieldData, fileName, data, {
			"valueAxes": [
				{
					"axisTitleOffset": 20,
					"gridType": "circles",
					"id": "ValueAxis-1",
					"minimum": 0,
					"axisAlpha": 0.15,
					"dashLength": 3
				}
			]
		});
	}
	if($('#amChartArea')[0].childElementCount == 0){
		swAlert('chart를 생성하지 못하였습니다.');
	}
}

function visualExcelDataUpload(){
	swAlertExtand({
		title : '엑셀 파일 선택',
		input : 'file',
		inputAttributes : {
			'accept' : 'application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
		},
		showCancelButton: true,
		confirmButtonText: '업로드',
	}, function(result){
		if($.fn.useExtCheck(["xls", "xlsx"], result.value.name)){
			pageLoadingView('show');
			let fileData = $('.swal2-content > input[type="file"]')[0].files[0];
			uploadFileData = fileData;
			//excel 읽기
			if (fileData && window.FileReader) {
	            var reader = new FileReader();    // HTML5에서 제공하는 File Reader 객체를 생성합니다.                                                                
	            reader.onload = function(oFile) {
				    try {
						let fileData = arrayBufferToBinary(oFile.target.result);
						let wb = XLSX.read(fileData, {type : 'binary'});
						let sheetObj = [];
						wb.SheetNames.forEach(function(sheetName){
							let rowObj = XLSX.utils.sheet_to_json(wb.Sheets[sheetName]);
							sheetObj.push(rowObj);
							console.log(rowObj);
						});
						data = sheetObj[0];
					} catch (e) {
						swAlert('data를 확인해주세요.');
						pageLoadingView('hide');
						return false;
					}
					fileName = result.value.name;
					chartAndDataView(data, fileName);
					pageLoadingView('hide');
	            };
	            reader.readAsArrayBuffer(fileData);
			}
		}
	});
}

function visualExcelDataAmChartView(data, fileName){
	let chartType = $('#chartType').val();
	xFieldNm = 'category';
	xFieldData = [];
	$.each(data[0], function(i, v){
		if(v != '' && v != null && v != undefined){
			xFieldData.push(i);
		}
	});
	
	$('#amChartArea').empty().removeClass('empty');
	
	changeChartType(chartType);
	
}

function visualExcelDataView(data, fileName){
	let limitRowNum = $('#limitRowNum').val();
	let table = $('<table class="table"></table>');
	let thead = $('<thead></thead>');
	let thead_tr = $('<tr></tr>');
	let theadKey = [];
	$.each(data[0], function(i, v){
		if(v != '' && v != null && v != undefined){
			thead_tr.append('<th>'+i+'</th>');
			theadKey.push(i);
		}
	});
	thead.append(thead_tr);
	
	let tbody = $('<tbody></tbody>');
	$.each(data, function(i, v){
		if(limitRowNum != null && limitRowNum != '' && limitRowNum != undefined && limitRowNum <= i){
			return false;
		}
		let tbody_tr = $('<tr></tr>');
		for(let i = 0; i < theadKey.length; i++){
			tbody_tr.append('<td>'+v[theadKey[i]]+'</td>');
		}
		tbody.append(tbody_tr);
	});
	
	table.append(thead);
	table.append(tbody);
	
	$('.dataVisualArea > div.dataView').empty().removeClass('empty').append('<div>'+fileName+'</div>').append(table);
}

function visualCSVDataUpload(){
	swAlertExtand({
		title : 'CSV 파일 선택',
		input : 'file',
		inputAttributes : {
			'accept' : '.csv'
		},
		showCancelButton: true,
		confirmButtonText: '업로드',
	}, function(result){
		if($.fn.useExtCheck(["csv"], result.value.name)){
			pageLoadingView('show');
			let fileData = $('.swal2-content > input[type="file"]')[0].files[0];
			uploadFileData = fileData;
			//excel 읽기
			if (fileData && window.FileReader) {
	            var reader = new FileReader();    // HTML5에서 제공하는 File Reader 객체를 생성합니다.                                                                
	             
	            // File Reader객체의 onload Event Handler를 구현합니다.
	            reader.onload = function(oFile) {
				    try {
						let fileData = arrayBufferToBinary(oFile.target.result);
						let wb = XLSX.read(fileData, {type : 'binary'});
						let sheetObj = [];
						wb.SheetNames.forEach(function(sheetName){
							let rowObj = XLSX.utils.sheet_to_json(wb.Sheets[sheetName]);
							sheetObj.push(rowObj);
							console.log(rowObj);
						});
						data = sheetObj[0];
					} catch (e) {
						swAlert('data를 확인해주세요.');
						pageLoadingView('hide');
						return false;
					}
					fileName = result.value.name;
					chartAndDataView(data, fileName);
					pageLoadingView('hide');
	            };
	            reader.readAsArrayBuffer(fileData);
			}
		}
	});
}

function visualJSONDataUpload(){
	swAlertExtand({
		title : 'JSON 파일 선택',
		input : 'file',
		inputAttributes : {
			'accept' : 'application/JSON'
		},
		showCancelButton: true,
		confirmButtonText: '업로드',
	}, function(result){
		if($.fn.useExtCheck(["json"], result.value.name)){
			pageLoadingView('show');
			let fileData = $('.swal2-content > input[type="file"]')[0].files[0];
			uploadFileData = fileData;
			//json 읽기
			if (fileData && window.FileReader) {
	            var reader = new FileReader();    // HTML5에서 제공하는 File Reader 객체를 생성합니다.                                                                
	            // File Reader객체의 onload Event Handler를 구현합니다.
	            reader.onload = function(oFile) {
				    try {
				    	let fileData = arrayBufferToBinary(oFile.target.result);
				    	data = JSON.parse(fileData);
				    } catch (e) {
						swAlert('data를 확인해주세요.');
						pageLoadingView('hide');
						return false;
					}
					fileName = result.value.name;
					chartAndDataView(data, fileName);
					pageLoadingView('hide');
	            };
	            reader.readAsArrayBuffer(fileData);
			}
		}
	});
}

function visualXMLDataUpload(){
	swAlertExtand({
		title : 'XML 파일 선택',
		input : 'file',
		inputAttributes : {
			'accept' : 'text/xml'
		},
		showCancelButton: true,
		confirmButtonText: '업로드',
	}, function(result){
		if($.fn.useExtCheck(["xml"], result.value.name)){
			pageLoadingView('show');
			let fileData = $('.swal2-content > input[type="file"]')[0].files[0];
			uploadFileData = fileData;
			
			//xml 읽기
			if (fileData && window.FileReader) {
	            var reader = new FileReader();    // HTML5에서 제공하는 File Reader 객체를 생성합니다.                                                                
	             
	            // File Reader객체의 onload Event Handler를 구현합니다.
	            reader.onload = function(oFile) {
				    try {
				    	let fileData = getXmlFromString(arrayBufferToBinary(oFile.target.result));
						if(fileData != null){
							var x2js = new X2JS();
					        data = x2js.xml2json(fileData).items.item;
						}
				    } catch (e) {
						swAlert('data를 확인해주세요.');
						pageLoadingView('hide');
						return false;
					}
					fileName = result.value.name;
					chartAndDataView(data, fileName);
					pageLoadingView('hide');
	            };
	            reader.readAsArrayBuffer(fileData);
			}
		}
	});
}

function amChartColumn(xFieldNm, xFieldData, fileName, data, options){
	let lineNumArr = $('#lineRowNum').val();
	lineNumArr = lineNumArr.split(',');
	let graphs = [];
	$.each(xFieldData, function(i, v){
		let boolColumn = true;
		if(lineNumArr != ''){
			$.each(lineNumArr, function(i2, v2){
				if(Number(v2) == i){
					boolColumn = false;
				}
			});
		}
		
		let json = {};
		json.balloonText = "[[category]] of [[title]] : [[value]]";
		json.title = v;
		json.valueField = v;
		
		if(boolColumn){
			json.fillAlphas = 1;
			json.id = 'column-'+i;
			json.type = "column";
		} else {
			json.id = 'line-'+i;
			json.bullet = "round";
		}
		
		graphs.push(json);
		boolColumn = true;
	});
	
	let limitRowNum = $('#limitRowNum').val();
	
	let statsDataOri = JSON.parse(JSON.stringify(data));
	let statsData = [];
	$.each(statsDataOri, function(i, v){
		if(limitRowNum != null && limitRowNum != '' && limitRowNum != undefined && limitRowNum <= i){
			return false;
		}
		statsData.push(v);
		statsData[i][xFieldNm] = "Row"+i;
	});
	
	let amChartsOptions = {
			"language" : "ko",
			"type": "serial",
			"categoryField": xFieldNm,
			"chartScrollbar": {
				"enabled": true
			},
			"trendLines": [],
			"graphs": graphs,
			"guides": [],
			"valueAxes": [
				{
					"id": "axix-1",
					"title": xFieldNm+" Data"
				}
			],
			"allLabels": [],
			"balloon": {},
			"legend" : {
				"enabled" : true,
				"useGraphSettings" : true
			},
			"titles": [
				{
					"id" : "title-1",
					"size" : 15,
					"text" : fileName
				}
			],
			"dataProvider": statsData,
			"export": {
				"enabled": true
			}
	};
	
	amChartsOptions =  $.extend(amChartsOptions, options || {});
	
	AmCharts.makeChart("amChartArea", amChartsOptions);
}

function amChartLine(xFieldNm, xFieldData, fileName, data, options){
	let lineNumArr = $('#lineRowNum').val();
	lineNumArr = lineNumArr.split(',');
	let chartType = $('#chartType').val();
	let graphs = [];
	$.each(xFieldData, function(i, v){
		let boolColumn = false;
		if(lineNumArr != ''){
			$.each(lineNumArr, function(i2, v2){
				if(Number(v2) == i){
					boolColumn = true;
				}
			});
		}
		
		let json = {};
		json.balloonText = "[[category]] of [[title]] : [[value]]";
		json.title = v;
		json.valueField = v;
		
		if(boolColumn){
			json.id = 'column-'+i;
			json.fillAlphas = 1;
			json.type = "column";
		} else {
			json.id = 'line-'+i;
			if(chartType == 'line'){
				json.bullet = "round";
			} else if(chartType == 'stepLine'){
				json.lineThickness = 2;
				json.type = "step";
			} else if(chartType == 'smoothLine'){
				json.bullet = "round";
				json.type = "smoothedLine";
			}
		}
		
		graphs.push(json);
		boolColumn = true;
	});
	
	let limitRowNum = $('#limitRowNum').val();
	
	let statsDataOri = JSON.parse(JSON.stringify(data));
	let statsData = [];
	$.each(statsDataOri, function(i, v){
		if(limitRowNum != null && limitRowNum != '' && limitRowNum != undefined && limitRowNum <= i){
			return false;
		}
		statsData.push(v);
		statsData[i][xFieldNm] = "Row"+i;
	});
	
	let amChartsOptions = {
			"language" : "ko",
			"type": "serial",
			"categoryField": xFieldNm,
			"chartScrollbar": {
				"enabled": true
			},
			"trendLines": [],
			"graphs": graphs,
			"guides": [],
			"valueAxes": [
				{
					"id": "axix-1",
					"title": xFieldNm+" Data"
				}
			],
			"allLabels": [],
			"balloon": {},
			"legend" : {
				"enabled" : true,
				"useGraphSettings" : true
			},
			"titles": [
				{
					"id" : "title-1",
					"size" : 15,
					"text" : fileName
				}
			],
			"dataProvider": statsData,
			"export": {
				"enabled": true
			}
	};
	
	amChartsOptions =  $.extend(amChartsOptions, options || {});
	
	AmCharts.makeChart("amChartArea", amChartsOptions);
}

function amChartArea(xFieldNm, xFieldData, fileName, data, options){
	let graphs = [];
	$.each(xFieldData, function(i, v){
		let json = {};
		json.balloonText = "[[category]] of [[title]] : [[value]]";
		json.fillAlphas = 0.7;
		json.lineAlpha = 0;
		json.id = 'area-'+i;
		json.title = v;
		json.valueField = v;
		graphs.push(json);
	});
	
	let limitRowNum = $('#limitRowNum').val();
	
	let statsDataOri = JSON.parse(JSON.stringify(data));
	let statsData = [];
	$.each(statsDataOri, function(i, v){
		if(limitRowNum != null && limitRowNum != '' && limitRowNum != undefined && limitRowNum <= i){
			return false;
		}
		statsData.push(v);
		statsData[i][xFieldNm] = "Row"+i;
	});
	
	let amChartsOptions = {
			"language" : "ko",
			"type": "serial",
			"categoryField": xFieldNm,
			"chartScrollbar": {
				"enabled": true
			},
			"trendLines": [],
			"graphs": graphs,
			"guides": [],
			"valueAxes": [
				{
					"id": "axix-1",
					"title": xFieldNm+" Data"
				}
			],
			"allLabels": [],
			"balloon": {},
			"legend" : {
				"enabled" : true,
			},
			"titles": [
				{
					"id" : "title-1",
					"size" : 15,
					"text" : fileName
				}
			],
			"dataProvider": statsData,
			"export": {
				"enabled": true
			}
	};
	
	amChartsOptions =  $.extend(amChartsOptions, options || {});
	
	AmCharts.makeChart("amChartArea", amChartsOptions);
}

function amChartRadar(xFieldNm, xFieldData, fileName, data, options){
	let graphs = [];
	$.each(xFieldData, function(i, v){
		let json = {};
		json.balloonText = "[[category]] of [[title]] : [[value]]";
		json.bullet = "round";
		json.id = 'graph-'+i;
		json.title = v;
		json.valueField = v;
		graphs.push(json);
	});
	
	let limitRowNum = $('#limitRowNum').val();
	
	let statsDataOri = JSON.parse(JSON.stringify(data));
	let statsData = [];
	$.each(statsDataOri, function(i, v){
		if(limitRowNum != null && limitRowNum != '' && limitRowNum != undefined && limitRowNum <= i){
			return false;
		}
		statsData.push(v);
		statsData[i][xFieldNm] = "Row"+i;
	});
	
	let amChartsOptions = {
			"language" : "ko",
			"type": "radar",
			"categoryField": xFieldNm,
			"graphs": graphs,
			"guides": [],
			"valueAxes": [
				{
					"axisTitleOffset": 20,
					"id": "axix-1",
					"minimum": 0,
					"axisAlpha": 0.15,
					"dashLength": 3
				}
			],
			"allLabels": [],
			"balloon": {},
			"legend" : {
				"enabled" : true,
				"align" : "center",
				"markerType" : "circle"
			},
			"titles": [
				{
					"id" : "title-1",
					"size" : 15,
					"text" : fileName
				}
			],
			"dataProvider": statsData,
			"export": {
				"enabled": true
			}
	};
	
	amChartsOptions =  $.extend(amChartsOptions, options || {});
	
	AmCharts.makeChart("amChartArea", amChartsOptions);
}











