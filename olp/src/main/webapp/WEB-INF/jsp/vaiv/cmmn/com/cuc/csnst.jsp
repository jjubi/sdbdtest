<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : nttOptn > csnst.jsp
  * @상세설명 : 만족도 평가
  * @작성일시 : 2020. 12. 31
  * @작 성 자 : jo
  * @수 정 이 력
  * @
  * @  수정일         수정자                   수정내용
  * @ -------       --------    ---------------------------
  * @ 2020.12.31   jo	  최초등록
  * @ 
  * 
  */
%>

<link rel="stylesheet" href="/static/css/vaiv/cmmn/adm/csnst/csnst.css" />

<div class="satisfaction">
    <fieldset>
    <legend class="sr-only">만족도조사</legend>
        <div class="satisfaction_box">
            <span class="sr-only">만족도 조사</span>
            <strong>이 페이지에서 제공하는 정보에 대하여 만족하시나요?</strong>
            <div>
                <label for="scoreValue_1"><input name="scoreValue" class="point_radio" type="radio" id="scoreValue_1" value="5"> 매우만족</label>
                <label for="scoreValue_2"><input name="scoreValue" class="point_radio" type="radio" id="scoreValue_2" value="4"> 만족</label>
                <label for="scoreValue_3"><input name="scoreValue" class="point_radio" type="radio" id="scoreValue_3" value="3"> 보통</label>
                <label for="scoreValue_4"><input name="scoreValue" class="point_radio" type="radio" id="scoreValue_4" value="2"> 불만족</label>
                <label for="scoreValue_5"><input name="scoreValue" class="point_radio" type="radio" id="scoreValue_5" value="1"> 매우불만족</label>
            </div>
            <div>
                <label for="research_descript" class="sr-only">만족도조사 내용을 입력하세요</label>
                <input id="research_descript" type="text" name="descript" placeholder="의견을 입력해 주세요">
                <input type="button" value="등록하기" onclick="javascript: fn_csnst()">
            </div>
        </div>
    </fieldset>
</div>


<script>
function fn_csnst() {
	//만족도 체크했는지 확인
	if($('input[name="scoreValue"]').is(':checked') == false){
		Swal.fire('확인', '만족도 점수를 체크해주세요.', 'warning');
		return ;
	}
	//메뉴명, 만족도, 설명, 현재 URL, 게시판번호, 컨텐츠 번호, 게시물 번호, IP
	let am = $('.nav-link.active');
	let menuNm = $.trim(am.text());
	let msn = am.data('msn');
	let nowUrl = location.href;
	let nttId = '';
	let bbsId = '';
	let cntntsId = '';
	let score = $('input[name="scoreValue"]:checked').val();
	let descript = $('input[name="descript"]').val();
	bbsId = $('input[name="nowBbsId"]').length > 0 ? $('input[name="nowBbsId"]').val() : ($('input[name="bbsId"]').length > 0 ? $('input[name="bbsId"]').val() : ''); 	
	nttId = $('input[name="nttId"]').length > 0 ? $('input[name="nttId"]').val() : '';
	cntntsId = $('input[name="cntntsId"]').length > 0 ? $('input[name="cntntsId"]').val() : '';
	
	$.ajax({
		type : "POST",
		url : "/cmmn/csnst/insertCsnstAjax.do",
		data : {
			"menuNm" : menuNm,
			"menuSeqNo" : msn,
			"pgeUrl" : nowUrl,
			"bbsId" : bbsId,
			"nttId" : nttId,
			"cntntsId" : cntntsId,
			"csnstDc" : descript,
			"csnstScore" : score
		},
		dataType : "json",
		success : function(returnData) {

			if (returnData.result == 'success') {
				Swal.fire('확인', '만족도 조사를 성공적으로 등록 했습니다.', 'success');
				return;
			} else {
				Swal.fire('확인', '만족도 조사를 등록하는데 실패 했습니다.', 'error');
				$('input[name="descript"]').val('');
				return;
			}
		},
		error : function(returnData, status, err) {
			Swal.fire('확인', '만족도 조사를 등록하는데 에러가 발생 했습니다.', 'error');
			$('input[name="descript"]').val('');
			console.log(err);
		}
	});
}
</script>						

