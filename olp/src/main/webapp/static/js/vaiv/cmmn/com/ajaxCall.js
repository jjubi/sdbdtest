/**
 * ajax 호출 function
 * @author kmk
 */
// ajaxOption {url : "", ... } 형태에 맞춰 기입
function callAjaxExtend(ajaxOption){
	defaultOption = {
		url : "/",
		method : "post",
		success : function(result){
			console.log(result);
		},
		error : function(request,status,error){
			console.log(request,status,error);
		}
	}
	
	ajaxOption = $.extend(defaultOption, ajaxOption);
	
	return $.ajax(ajaxOption);
}

// 기본 ajax call prameter는 JSON.stringify(parameter) 문자열 이어야한다.
function callFunc(url, type, parameter, callback, async){
	var boolAsync = true; 
	if(arguments.length == 5 && new Boolean(async)){
		boolAsync = async;
	}
	$.ajax({
    	url: url,
        type: type,
        contentType: 'application/json',
        dataType: 'json',
        data: parameter,
        async: boolAsync,
        success: function (data, status, xhr) {
        	callback(status, data);
        },
        error: function (xhr, status, error)  {
        	callback(status, xhr);
        }
    });
}

//기본 ajax view call
function callViewFunc(url, type, parameter, callback){
	if(callback){
		$.ajax({
			url: url,
			type: type,
			contentType: 'application/json',
			dataType: 'html',
			data: JSON.stringify(parameter),
			success: function (data, status, xhr) {
        		callback(status, data);
			},
			error: function (xhr, status, error)  {
				callback(status, xhr);
			}
		});
	}else{
		returndata = "";
		$.ajax({
			url: url,
			type: type,
			contentType: 'application/json',
			dataType: 'html',
			async:false,
			data: JSON.stringify(parameter),
			success: function (data, status, xhr) {
				console.log(data);
				returndata = data;
			},
			error: function (xhr, status, error)  {
				console.log(status, xhr);
			}
		});
		return returndata;
	}
}

//업로드 ajax
function uploadData(url,data,callback){
	$.ajax({
		url: url,
		type: 'POST',
		data: data,
		enctype: 'multipart/form-data',
		contentType:false,
		processData: false,
		success: function (data, status, xhr) {
			callback(status, data);
		},
		error: function (xhr, status, error)  {
			callback(status, xhr);
		}
	});
}

//간편한 일반 ajax
function callDataApi(url,parameter){
	return new Promise(function(resolve,reject){
		callFunc(url,'POST',JSON.stringify(parameter),function(status, result){
			if (status === 'success') {
		        resolve(result);
		    } else {
		    	reject(result);
		    }
		});
	})
}

//간편한 업로드 ajax
function uploadDataApi(url,data){
	return new Promise(function(resolve,reject){
		uploadData(url,data,function(status, result){
			if (status === 'success') {
		      resolve(result);
		    } else {
		    	reject(result);
		    }
		});
	})
}