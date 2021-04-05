<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : dataVisual.jsp
  * @상세설명 : 데이터 시각화 표출 페이지
  * @작성일시 : 2021. 03. 22
  * @작 성 자 : jo
  * @수 정 이 력
  * @
  * @  수정일         수정자                   수정내용
  * @ -------       --------    ---------------------------
  * @ 2021.03.22   jo    최초등록
  * @ 
  * 
  */
%>
<c:set var="pageTitle">DATA 시각화</c:set>
<style>
.dataVisualArea {
    width : 100%;
}
.dataVisualArea > #amChartArea {
    height : 500px;
}
.dataVisualArea > .dataView {
    max-height : 500px;
    overflow: auto;
}
.dataView > div {
    text-align: center;
    margin-bottom: 10px;
}
.explan-area i.fas.fa-mouse-pointer {
    animation: move 2s;
    animation-iteration-count: infinite;
    transform-origin: 50% 50%;
}

.explan-area {
    text-align: center;
    padding: 0 0.5rem;
    display: inline-flex;
    margin-left: 2px;
}

@keyframes move {
   0% {transform:translate(0, 0);}
   50% {transform:translate(-15px, 0px);}
   100% {transform:translate(0, 0);}
}

i.far.fa-hand-point-left {
    animation: move 2s;
    animation-iteration-count: infinite;
    transform-origin: 50% 50%;
    padding-right: 5px;
    padding-top: 5px;
}

div#amChartArea.empty, .dataView.empty {
    width: 100%;
    text-align: center;
    padding: 12rem 8rem;
    border: 1px solid #ddd;
    background: #fff;
}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/js/plugins/amcharts/plugins/export/export.css" type="text/css" media="all" />

<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/amcharts/amcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/amcharts/serial.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/amcharts/pie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/amcharts/radar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/amcharts/lang/ko.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/amcharts/plugins/export/export.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/amcharts/plugins/animate/animate.js"></script>
<script src="${pageContext.request.contextPath }/static/js/plugins/xlsx/shim.min.js"></script>
<script src="${pageContext.request.contextPath }/static/js/plugins/xlsx/xlsx.full.min.js"></script>
<script src="${pageContext.request.contextPath }/static/js/plugins/xlsx/xml2json.js"></script>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/data/vsltn/dataVsltn.js"></script>
<div class="container-fluid">
    <h1 class="header-title"></h1>
    <div class="card">
        <div class="card-body" id="ex">
            <h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
            <div class="table-responsive">
                <fieldset>
                    <div class="ui program-search" title="DATA 시각화 컨트롤 영역">
                        <div class="float-left uploadBtn-group">
                            <button class="btn btn-sm btn-info" onclick="visualExcelDataUpload();" title="<spring:message code="button.excel" /> <spring:message code="input.button" />">
                                                                엑셀 업로드
                            </button>
                            <button class="btn btn-sm btn-info" onclick="visualCSVDataUpload();" title="<spring:message code="button.excel" /> <spring:message code="input.button" />">
                                CSV 업로드
                            </button>
                            <button class="btn btn-sm btn-info" onclick="visualJSONDataUpload();" title="<spring:message code="button.excel" /> <spring:message code="input.button" />">
                                JSON 업로드
                            </button>
                            <button class="btn btn-sm btn-info" onclick="visualXMLDataUpload();" title="<spring:message code="button.excel" /> <spring:message code="input.button" />">
                                XML 업로드
                            </button>
                            <div class="explan-area">
                                <i class="far fa-hand-point-left"></i> 
                                <strong> 버튼</strong>을 이용하여 파일을 업로드해 주시면 하단에 정보가 표출됩니다.
                            </div>
                        </div>
                        <div class="search_inner clearfix float-right">
                            <div class="fieldset">
                                <div class="search-btn">
                                    <button type="button" class="btn btn-sm btn-dark" onclick="createDataVsltnCnrsUrl();">
                                                                            공유 URL 생성
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </fieldset>
                
                <div class="row dataVisualArea">
                    <div class="col-sm-12 col-md-2">
                        <div class="form-group" id="lineNumInput">
                            <label>Line Row Num</label>
                            <input class="form-control onlyNum" data-except="," id="lineRowNum">
                        </div>
                        <div class="form-group">
                            <label>Limit Row Num</label>
                            <input class="form-control onlyNum" id="limitRowNum">
                        </div>
                        <div class="form-group">
                            <label>그래프 선택</label>
                            <select class="form-control" id="chartType">
                                <option value="column">column</option>
                                <option value="column3d">column 3D</option>
                                <option value="bar">bar</option>
                                <option value="line">line</option>
                                <option value="stepLine">step Line</option>
                                <option value="smoothLine">smoothed Line</option>
                                <option value="area">area</option>
                                <option value="radar">radar</option>
                                <option value="polar">polar</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-5 amChart empty" id="amChartArea">
                                                차트 표출 영역<br>
                        * 파일 업로드 시 차트가 표출되는 영역입니다.
                    </div>
                    <div class="col-sm-12 col-md-5 dataView empty">
                                                데이터 표출 영역<br>
                        * 파일 업로드 시 데이터가 표출되는 영역입니다.
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


