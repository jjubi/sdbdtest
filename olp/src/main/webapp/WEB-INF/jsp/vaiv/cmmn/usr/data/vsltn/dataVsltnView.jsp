<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="author" content="jo">
	<meta name="description" content="VAIV CONTENTS MANAGE SYSTEM ADMIN">
	<meta name="keywords" content="VAIV, CONTENTS, CONTENTS MANAGE, MANAGE, SYSTEM, ADMIN, VAIV CONTENTS MANAGE SYSTEM">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1"> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!--[if lt IE 9]>
	    <script src="assets/js/html5shiv.js"></script>
	<![endif]-->
	
	<!--[if lt IE 9]>
	    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<![endif]-->
	<title>CMS Basic</title>

	<link rel="shortcut icon" href="${pageContext.request.contextPath }/static/images/vaiv/cmmn/com/vaiv_favicon.ico" type="image/x-icon">
	
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/bootstrap/bootstrap_custom.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/fontawesome/fontawesome-5.15.1.all.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/vaiv/cmmn/adm/layout.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/sweetalert2/sweetalert2-10.9.0.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/js/plugins/amcharts/plugins/export/export.css" type="text/css" media="all" />
	
	<script src="${pageContext.request.contextPath }/static/js/plugins/jquery/jquery-3.5.1.min.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/plugins/sweetalert2/sweetalert2-10.9.0.min.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/com/swAlert.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/com/common.js"></script>
	

	<script src="${pageContext.request.contextPath }/static/js/plugins/amcharts/amcharts.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/plugins/amcharts/serial.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/plugins/amcharts/pie.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/plugins/amcharts/radar.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/plugins/amcharts/lang/ko.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/plugins/amcharts/plugins/export/export.min.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/plugins/amcharts/plugins/animate/animate.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/plugins/xlsx/shim.min.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/plugins/xlsx/xlsx.full.min.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/plugins/xlsx/xml2json.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/data/vsltn/dataVsltn.js"></script>
	
	<style>
		.dataVisualArea {
		    width : 100%;
		}
		.dataVisualArea > #amChartArea {
		    height : 500px;
		    margin-bottom: 20px;
		}
		.dataVisualArea > .dataView {
		    max-height : 500px;
		    overflow: auto;
		}
		.dataView > div {
		    text-align: center;
		    margin-bottom: 10px;
		}
	</style>
</head>
<body>
    <div class="container-fluid">
        <div class="card">
            <div class="card-body">
                <h1 class="header-title"><c:out value="${dataVsltnCnrsVO.vsltnCnrsNm }"/></h1>
                <hr>  
                <input type="hidden" id="chartType" value="">
                <input type="hidden" id="lineRowNum" value="">
                <input type="hidden" id="limitRowNum" value="">
                <div class="dataVisualArea">
	                <div class="col-sm-12 col-md-12 amChart" id="amChartArea">
	
	                </div>
	                <div class="col-sm-12 col-md-12 dataView">
	                                
	                </div>
                </div>              
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function(){
            fileName = '${dataVsltnCnrsVO.vsltnCnrsNm }';
            let ty = '${dataVsltnCnrsVO.vsltnTy }';
            let fileTy = ty.split('-')[1];
            let chartTy = ty.split('-')[0];
            $('#chartType').val(chartTy);
            let optn = JSON.parse(nonXssFiltering('${dataVsltnCnrsVO.vsltnOptn}'));
            let lineRN = optn.lineRN;
            let limitRN = optn.limitRN;
            $('#lineRowNum').val(lineRN);
            $('#limitRowNum').val(limitRN);
            
            $.ajax({
            	url : '${pageContext.request.contextPath}/cmm/fms/FileDown.do'
            	,type : 'get'
            	,data : {"atchFileId" : '${dataVsltnCnrsVO.vsltnOrignlFileId}', "fileSn" : '0'}
            	,dataType : 'binary'
            	,success : function(fileBlobData){
            		let reader = new FileReader();
                    reader.onload = function(oFile){
                        try {
                        	let binaryData = arrayBufferToBinary(oFile.target.result);
                        	if(fileTy == 'xlsx' || fileTy == 'xls'){
                        		let fileData = binaryData;
                                let wb = XLSX.read(fileData, {type : 'binary'});
                                let sheetObj = [];
                                wb.SheetNames.forEach(function(sheetName){
                                    let rowObj = XLSX.utils.sheet_to_json(wb.Sheets[sheetName]);
                                    sheetObj.push(rowObj);
                                    console.log(rowObj);
                                });
                                data = sheetObj[0];
                            } else if(fileTy == 'csv'){
                            	let fileData = binaryData;
                                let wb = XLSX.read(fileData, {type : 'binary'});
                                let sheetObj = [];
                                wb.SheetNames.forEach(function(sheetName){
                                    let rowObj = XLSX.utils.sheet_to_json(wb.Sheets[sheetName]);
                                    sheetObj.push(rowObj);
                                    console.log(rowObj);
                                });
                                data = sheetObj[0];
                            } else if(fileTy == 'json'){
                            	let fileData = binaryData;
                                data = JSON.parse(fileData);
                            } else if(fileTy == 'xml'){
                            	let fileData = getXmlFromString(binaryData);
                                if(fileData != null){
                                    var x2js = new X2JS();
                                    data = x2js.xml2json(fileData).items.item;
                                }
                            }
                        } catch (e) {
                            swAlert('data를 확인해주세요.');
                            pageLoadingView('hide');
                            return false;
                        }
                        chartAndDataView(data, fileName);
                        pageLoadingView('hide');
                    }
                    reader.readAsArrayBuffer(fileBlobData);
            	}
            });
            
            
        });
    </script>
</body>
</html>





