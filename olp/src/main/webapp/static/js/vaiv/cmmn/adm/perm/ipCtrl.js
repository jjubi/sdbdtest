/**
 * 관리자 > IP 관리 javascript
 */
$(document).ready(function(){
	/*
	 * IP 등록 버튼 클릭
	 */
	$('#ipCtrlRegistBtn').click(function(){
		swAlertConfirm('등록하시겠습니까?', {icon:'info', confirmButtonText:'등록'}, function(){
			if($('#checkIpBoolean').val() == 'false'){
				Swal.fire('확인', 'IP 중복확인을 해주세요.', 'warning');
	    		return false;
			}
			if(!fn_IP_check('Y')){
				let ipCtrlForm = document.getElementById("ipCtrlForm");
				if(!validateIpCtrlVO(ipCtrlForm) && !nullCheck()){
					return false;
				} else {
					ipCtrlForm.submit();
				}
			} else {
				Swal.fire('확인', 'IP 중복확인을 해주세요.', 'warning');
				return false;
			}
		});
	});
	
	/*
	 *	IP 삭제 버튼 클릭 함수
	 */
	$('#ipCtrlDeleteBtn').click(function(){
		swAlertConfirm('삭제하시겠습니까?', {icon:'warning', confirmButtonText:'삭제'}, function(){
			let connectIp = $("#connectIp").val();
			if(connectIp != null && connectIp != ''){
				let ipCtrlForm = document.getElementById("ipCtrlForm");
				ipCtrlForm.action = contextPath + "/cmmn/adm/perm/ipCtrlDelete.do";
				ipCtrlForm.submit();
			} else {
				return false;
			}
		});
	});
	
	/*
	 *	IP 수정 버튼 클릭 함수
	 */
	$('#ipCtrlUpdateBtn').click(function(){
		swAlertConfirm('수정하시겠습니까?', {icon:'info', confirmButtonText:'수정'}, function(){
			let ipCtrlForm = document.getElementById("ipCtrlForm");
			if($('#checkIpBoolean').val() == 'false'){
				Swal.fire('확인', 'IP 중복확인을 해주세요.', 'warning');
	    		return false;
			}
			if(!fn_IP_check('Y')){
				if(!validateIpCtrlVO(ipCtrlForm) && !nullCheck()){
					return false;
				} else {
					ipCtrlForm.submit();
				}
			} else {
				Swal.fire('확인', 'IP 중복확인을 해주세요.', 'warning');
				return false;
			}
		});
	});
});

function nullCheck(){
	let permAt = $("#permAt").val();
	if(permAt == null || permAt == '' || permAt == undefined){
		Swal.fire('확인', '잘못된 접근입니다.', 'warning');
		return false;
	}else{
		return true;
	}
}

/*
 * ip 중복체크 
 * @param : connectIp
 */
function fn_IP_check(recheck){
	let ip = $("#connectIp").val();
	let permAt = $("#permAt").val();
	if(ip == null || ip == '' || ip == undefined){
		Swal.fire('확인', 'ip를 입력하세요.', 'warning');
		return false;
	}
	if(!regIpCheck(ip)){
		Swal.fire("확인","IP 형식에 맞지 않습니다.", "info");
		return false; 
	}
	$.ajax({
		type:"POST",
		url: contextPath + "/cmmn/adm/perm/ipCtrlDplctCnfirmAjax.do",
		data:{
			"connectIp": ip,	
			"permAt" : permAt
		},
		async : false,
		dataType:'json',
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				if(returnData.usedCnt > 0 ){
					//사용할수 없는 코드입니다.
					$('#checkIpStr').html('<font color="red"> ['+returnData.connectIp+']는 사용할수 없는 코드입니다</font>');
					$('#checkIpBoolean').val('false');
					if(recheck == 'Y'){
						return true;
					}
				} else {
					//사용가능한 코드입니다.
					$("#checkIpStr").html('<font color="blue">['+returnData.connectIp+']는 사용가능한 코드입니다</font>');
					$('#checkIpBoolean').val('true');
					if(recheck == 'Y'){
						return false;
					}
				}
			} else { 
				Swal.fire("확인",'IP 중복 체크 중 오류 발생. 관리자에게 문의하세요.', 'error');
				$('#checkIpBoolean').val('false');
				if(recheck == 'Y'){
					return true;
				}
			} 
		}, error : function(status){
			Swal.fire("확인",'IP 중복 체크 중 오류 발생. 관리자에게 문의하세요.', 'error');
			$('#checkIpBoolean').val('false');
			if(recheck == 'Y'){
				return true;
			}
		}
	});
}

/*
 * ip 정규식 표현
 * @param : pageNo(페이지 번호)
 */
function regIpCheck(ip){
	let regExp = /^(25[0-5*]|2[0-4][0-9]|2[0-4*]|2[0-4][0-9*]|[01]?[0-9][0-9*]?|[01]?[0-9*]?|\*)\.(25[0-5*]|2[0-4][0-9]|2[0-4*]|[01]?[0-9][0-9*]?|\*)\.(25[0-5*]|2[0-4][0-9]|2[0-4*]|[01]?[0-9][0-9*]?|\*)\.(25[0-5*]|2[0-4][0-9]|2[0-4*]|[01]?[0-9][0-9*]?|[01]?[0-9*]|\*)$/;
	if(regExp.test(ip)){
		return true;
	}else{
		return false;
	}
}

/*
 * ip 목록 조회 해당 페이지로 이동
 * @param : pageNo(페이지 번호)
 */
function fnSelectIpCtrlList(pageNo){
	pageLoadingView('show');
	if(arguments.length == 1){
	    $("#pageIndex").val(pageNo);
	    $("#listForm").attr("action", contextPath + "/cmmn/adm/perm/ipCtrlMain.do");
	    $("#listForm").submit();
	} else {
		let searchForm = document.getElementById("hiddenSearchForm");
		let permAt = $('#permAt').val();
		$("#hiddenSearchForm").attr("action", contextPath + "/cmmn/adm/perm/ipCtrlMain.do?permAt="+permAt);
		$("#hiddenSearchForm").submit();
	}
}

/*
 * ip 상세 조회 해당 페이지로 이동
 * @param : id(게시판id)
 */
function fnSelectIpCtrl(id){
	pageLoadingView('show');
	if(id != null && id != '' && id != undefined){
		$("#permIpId").val(id);
		$("#listForm").attr("action", contextPath + "/cmmn/adm/perm/ipCtrlModify.do");
		$("#listForm").submit();
	}else{
		return false;
	}
}
