<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : dashboardMain.jsp
  * @상세설명 : 대쉬보드 표출 페이지
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
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/amcharts/amcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/amcharts/serial.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/amcharts/pie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/amcharts/radar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/amcharts/lang/ko.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/plugins/amcharts/plugins/animate/animate.js"></script>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/stats.js"></script>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/dashboard/dashboard.js"></script>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/com/sckGrid.js"></script>

<div class="container-fluid">
	<h1 class="header-title">CMS 관리</h1>
	<div class="row">
		<div class="col-xl-3">
			<div class="card colorBox colorBox1">
				<div class="card-body">
					<h5 class="h5">게시물 수</h5>
					<div class="colorBox_cont">
						<strong>${nttTotCnt }</strong>건
					</div>
				</div>
			</div>
		</div>
		<div class="col-xl-3">
			<div class="card colorBox colorBox2">
				<div class="card-body">
					<h5 class="h5">배너 수</h5>
					<div class="colorBox_cont">
						<strong>${bnrTotCnt }</strong>건
					</div>
				</div>
			</div>
		</div>
		<div class="col-xl-3">
			<div class="card colorBox colorBox3">
				<div class="card-body">
					<h5 class="h5">팝업 수</h5>
					<div class="colorBox_cont">
						<strong>${popupTotCnt }</strong>건
					</div>
				</div>
			</div>
		</div>
		<div class="col-xl-3">
			<div class="card colorBox colorBox4">
				<div class="card-body">
					<h5 class="h5">회원 수 <small>(일반/관리자)</small></h5>
					<div class="colorBox_cont">
						<strong>${mberTotCnt[0].cnt } / ${mberTotCnt[1].cnt }</strong>건
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- /////////////////////////////////////// -->
	
	<div class="row">
		<div class="col-xl-8 col-lg-7">
			<div class="card">
				<div class="card-body">
					<h1>로그인 로그 통계
						<div class="float-right" style="font-size: 16px;">
							<label class="radio-inline" for="loginLogStatsTypeD">
	                           	<input type="radio" name="loginLogStatsType" id="loginLogStatsTypeD" value="D" checked="checked" /> 일별
							</label>
							<label class="radio-inline" for="loginLogStatsTypeM">
	                        	<input type="radio" name="loginLogStatsType" id="loginLogStatsTypeM" value="M" /> 월별
	                       	</label>
	                        <label class="radio-inline" for="loginLogStatsTypeY">
	                        	<input type="radio" name="loginLogStatsType" id="loginLogStatsTypeY" value="Y" /> 년별
	                       	</label>
						</div>
					</h1>
					<div id="loginLogStatsArea" style="width:100%; height:400px;"></div>
				</div>
			</div>
		</div>
		<div class="col-xl-4 col-lg-5">
			<div class="card">
				<div class="card-body">
					<h1>게시판 통계
						<div class="float-right" style="font-size: 16px;">
							<label class="radio-inline" for="bbsToNttStatsTypeC">
                           		<input type="radio" name="bbsToNttStatsType" id="bbsToNttStatsTypeC" value="C" checked="checked" /> 게시물 수
							</label>
                           	<label class="radio-inline" for="bbsToNttStatsTypeA">
                           		<input type="radio" name="bbsToNttStatsType" id="bbsToNttStatsTypeA" value="A" /> 게시물 속성
                           	</label>
						</div>
					</h1>
					<div id="bbsToNttStatsArea" style="width:100%; height:400px;"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xl-12 col-lg-12">
			<div class="card">
				<div class="card-body">
<!-- 						<div class="float-right"> -->
<!-- 							<label class="radio-inline" for="loginLogStatsTypeD"> -->
<!--                             	<input type="radio" name="loginLogStatsType" id="loginLogStatsTypeD" value="D" checked="checked" /> 일별 -->
<!-- 							</label> -->
<!--                             <label class="radio-inline" for="loginLogStatsTypeM"> -->
<!--                             	<input type="radio" name="loginLogStatsType" id="loginLogStatsTypeM" value="M" /> 월별 -->
<!--                             </label> -->
<!--                             <label class="radio-inline" for="loginLogStatsTypeY"> -->
<!--                             	<input type="radio" name="loginLogStatsType" id="loginLogStatsTypeY" value="Y" /> 년별 -->
<!--                             </label> -->
<!-- 						</div> -->
					<h1>메뉴별 사용 통계</h1>
					<div id="menuUseStatsArea" style="width:100%; height:400px;"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xl-12 col-lg-12">
			<div class="card">
				<div class="card-body">
					<h1>시스템 로그 통계 데이터
						<button class="btn btn-info right" onclick="resetSort1();">정렬초기화</button>
						<button class="btn btn-info right" id="getData2">데이터 가져오기</button>
					</h1>
					<div id="sysLogSummaryData" class="slickGrid-area" style="width:100%; height:500px;"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xl-12 col-lg-12">
			<div class="card">
				<div class="card-body">
					<h1>시스템 로그 통계 데이터2
						<button class="btn btn-info right" id="getData">데이터 가져오기</button>
					</h1>
					<div id="sysLogSummaryData2" class="slickGrid-area" style="width:100%; height:500px;"></div>
				</div>
			</div>
		</div>
	</div>
</div>

