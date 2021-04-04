<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- test -->
<!-- 슬릭그리드 기본 CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/slickgrid/slick.grid.css" type="text/css"/>
<!-- jquery-ui가 없으면 안예쁘게 나옴 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/slickgrid/jquery-ui-1.8.16.custom.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/slickgrid/examples.css" type="text/css"/>

<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/plugins/sweetalert2/sweetalert2-10.9.0.min.css"/>
</head>

<body onload="fnSelectSysAllList();">

	<div id="sysLogAllData" style="width:100%;height:400px;"></div>

	<!-- 필수 jquery library -->
	
	<script src="${pageContext.request.contextPath }/static/js/plugins/jquery/jquery-3.5.1.min.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/plugins/slickgrid/jquery.event.drag-2.3.0.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath }/static/js/plugins/sweetalert2/sweetalert2-10.9.0.min.js"></script>
    
	<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/com/swAlert.js"></script>
	<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/com/ajaxCall.js"></script>
	<!-- 필수 슬릭그리드 library -->
	<script src="${pageContext.request.contextPath }/static/js/plugins/slickgrid/slick.core.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/static/js/plugins/slickgrid/slick.grid.js" type="text/javascript"></script>
	<!-- test -->
	<script type="text/javascript">

	function fnSelectSysAllList(){
		callDataApi('/cmmn/com/cuc/selectSysLogAllListAjax.do').then(function(data){
			let logData = data.resultList;
			let columns = [];
			$.each(logData[0], function(i, v){
				columns.push({id:i, name:i, field:i});
			});
			
			let rows = [];
			$.each(logData, function(i, v){
				let set = {};
				$.each(v, function(i2, v2){
					set[i2] = v2;
				});
				rows.push(set);
			});
	
			var slickgrid = new Slick.Grid("#sysLogAllData", rows, columns, {enableCellNavigation: true,
			    enableColumnReorder: false});
	        slickgrid.onSort.subscribe(function (e, args) { // args: sort information. 
	            var field = args.sortCol.field;
	
	            rows.sort(function (a, b) {
	                var result =
	                        a[field] > b[field] ? 1 : a[field] < b[field] ? -1 : 0;
	
	                return args.sortAsc ? result : -result;
	            });
	
	            slickgrid.invalidate();
	        });
		}).catch(function(data){
			swAlertToast('에러 발생');
		});
	}
	</script>

</body>
</html>