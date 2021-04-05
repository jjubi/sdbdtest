 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : qestnarMain.jsp
  * @상세설명 : 설문조사 관리 목록 표출 페이지
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
<c:set var="pageTitle"><spring:message code="vaivQestnar.title"/></c:set>

<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/qestnar/qestnar.js"></script>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/qestnar/senarioHTML.js"></script>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/qestnar/senario.js"></script>

<script>
function fnSetQestnarSenario(id){
	$.ajax({
		url : contextPath + '/cmmn/adm/qestnar/senario/selectQestnarSenarioListAjax.do',
		type : 'POST',
		dataType : 'json',
		data : {"qestnarSeqNo" : id},
		success : function(data){
			if(data.result == 'success'){
				$.initSenario({
					qestnarSeqNo : id,
					qestnList : data.qestnList,
					senarioList : data.senarioList,
					save : function(data){
						//저장하기
						if(data.length == 0){
							Swal.fire('확인', '시나리오가 없습니다. 확인해주세요.');
							return ;
						}
						//모든 input 값은 필수
						let checkIdx = 0;
						$('#qestnarSenarioForm').find('input').each(function(i, v){
							if($(this).val() == ''){
								Swal.fire('확인', '시나리오의 값을 확인해주세요.');
								checkIdx = i + 1;
								return false;
							}
						});
						if(checkIdx != 0){
							return ;
						}
						console.log(JSON.stringify(data));
						pageLoadingView('show');
						$.ajax({
							url : contextPath + '/cmmn/adm/qestnar/senario/insertQestnarSenarioAjax.do',
							type : 'POST',
							dataType : 'json',
							data : {"senarioJsonData" : JSON.stringify(data)},
							success : function(data){
								if(data.result == 'success'){
									Swal.fire('성공', '시나리오 저장 완료', 'success');
								} else {
									Swal.fire('실패', '시나리오를 저장하는데 실패했습니다. 관리자에게 문의해주세요.', 'error');
								}
								pageLoadingView('hide');
							},error : function(error){
								Swal.fire('실패', '시나리오를 저장하는데 실패했습니다. 관리자에게 문의해주세요.', 'error');
								pageLoadingView('hide');
							}
						});
					}
				});
			} else {
				Swal.fire('실패', '시나리오 목록을 가져오는데 실패했습니다. 관리자에게 문의해주세요.', 'error');
				pageLoadingView('hide');
			}
		}, error : function(error){
			Swal.fire('실패', '시나리오 목록을 가져오는데 실패했습니다. 관리자에게 문의해주세요.', 'error');
			pageLoadingView('hide');
		}
	});
}
</script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">${pageTitle} <spring:message code="title.list" /></h1>
			<div class="table-responsive">
				<form id="listForm" name="listForm" method="post" action="${pageContext.request.contextPath }/cmmn/adm/qestnar/qestnarMain.do">
					<fieldset>
						<legend>설문조사 검색 영역</legend>
						<input type="hidden" name="qestnarSeqNo" >
						<input type="hidden" name="pageIndex" value="${not empty searchVO ? searchVO.pageIndex : 1}">
						<div class="ui program-search" title="설문조사 검색 영역">
							<div class="btn-group float-left">
								<a class="btn btn-sm btn-basic" href="<c:url value='/cmmn/adm/qestnar/qestnarRegist.do'/>" title="<spring:message code="button.create" /> <spring:message code="input.button" />">
									<i class="fa fa-save" aria-hidden="true"></i> <spring:message code="button.create" />
								</a>
							</div>
							<div class="search_inner clearfix float-right">
								<div class="fieldset">
									<div class="search-select">
										<span>
											<select name="searchCondition" class="form-control" title="검색조건 선택">
												<option value="1" <c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>>설문조사 명</option>
											</select>
										</span>
									</div>
									<div class="search-text">
										<span>
											<input class="form-control pressEnter" name="searchKeyword" data-press="fnSelectQestnarList('1')" type="text" placeholder="검색어 입력 ..." title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155">			
										</span>
									</div>
									<div class="search-btn">
										<button type="button" class="btn btn-sm btn-dark" onclick="fnSelectQestnarList('1')">
											<i class="fa fa-search" aria-hidden="true"></i> 조회
										</button>
									</div>
								</div>
							</div>
						</div>
					</fieldset>
					<div class="ui program-search-form">
	                    <div class="ui program-info">
	                        <div class="ui program-count">
	                            <span>총 게시물 
	                            	<strong><c:out value="${paginationInfo.totalRecordCount }"/></strong> 개
	                            </span>, 
	                            <span class="ui program--division-line">페이지 
	                            	<strong><c:out value="${paginationInfo.currentPageNo }"/></strong>
	                                / <c:out value="${paginationInfo.totalPageCount }"/>
	                            </span>
	                        </div>
	                    </div>
	                </div>
				</form>
				<!-- 테이블 : s -->
				<table class="table table-bordered text-center">
					<caption>설문조사 목록 : 번호, 설문조사면, 설문조사기간, 설문조사 대상, 사용여부, 등록일, 비고로 구성</caption>
					<colgroup>
						<col style="width:8%;">
						<col style="width:auto;">
						<col style="width:auto;">
						<col style="width:10%;">
						<col style="width:8%;">
						<col style="width:10%;">
						<col style="width:auto;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">
								<spring:message code="table.num" /> <!-- 번호 -->
							</th>
							<th scope="col">
								<spring:message code="vaivQestnar.list.qestnarNm" /> <!-- 설문조사 명 -->
							</th>
							<th scope="col">
								<spring:message code="vaivQestnar.list.qestnarPd" /> <!-- 설문조사 기간 -->
							</th>
							<th scope="col">
								<spring:message code="vaivQestnar.list.qestnarTrget" /> <!-- 설문조사 대상 -->
							</th>
							<th scope="col">
								<spring:message code="vaivQestnar.list.useYn" /> <!-- 사용여부 -->
							</th>
							<th scope="col">
								<spring:message code="vaivQestnar.list.registDe" /> <!-- 등록일 -->
							</th>
							<th scope="col">
								<spring:message code="vaivQestnar.list.etc" /> <!-- 비고 -->
							</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty qestnarList or fn:length(qestnarList) == 0 }">
							<tr>
								<td class="text-center" colspan="7"><spring:message code="common.nodata.msg" /></td>
							</tr>
						</c:if>
						<c:forEach items="${qestnarList }" var="qestnarVO" varStatus="status">
							<tr>
								<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + status.index) }</td>
								<td>
									<a href="javascript:;" onclick="fnSelectQestnar('<c:out value="${qestnarVO.qestnarSeqNo }"/>');">
										<c:out value="${qestnarVO.qestnarNm }"/>
									</a>
								</td>
								<td>
									<c:out value="${qestnarVO.qestnarBgnde }"/> ~ <c:out value="${qestnarVO.qestnarEndde }"/> 
								</td>
								<td>
									<c:if test="${qestnarVO.qestnarTrget eq 'ALL' }">
										전체
									</c:if>
									<c:if test="${qestnarVO.qestnarTrget ne 'ALL' }">
										<c:out value="${qestnarVO.qestnarTrgetCnt }"/> 명
									</c:if>
								</td>
								<td><c:out value="${qestnarVO.useAt }"/></td>
								<td><c:out value="${qestnarVO.registDe }"/></td>
								<td>
									<c:if test="${qestnarVO.qestnarPgeAt eq 'N' }">
									<a class="btn btn-sm btn-info" href="javascript:;" onclick="fnSetQestnarSenario('<c:out value="${qestnarVO.qestnarSeqNo }"/>');">시나리오 설정</a>
									</c:if>
									<c:if test="${qestnarVO.qestnarResultCnt eq '0' }">
									<a class="btn btn-sm btn-info" href="javascript:;" onclick="fnSelectQestnarQestn('<c:out value="${qestnarVO.qestnarSeqNo }"/>');">문항 설정</a>
									</c:if>
									<c:if test="${qestnarVO.qestnarResultCnt ne '0' }">
									<a class="btn btn-sm btn-info" href="javascript:;" onclick="fnSelectQestnarResult('<c:out value="${qestnarVO.qestnarSeqNo }"/>')">결과 보기</a>
									</c:if>
									<c:if test="${not empty qestnarVO.firstQestnSeqNo }">
									<div class="dropdown" style="display: inline-block;">
										<a href="#" class="dropdown-ellipses dropdown-toggle btn btn-sm btn-primary" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
  											미리보기<i class="fe fe-more-vertical"></i>
										</a>
										<div class="dropdown-menu dropdown-menu-right">
  											<a href="/cmmn/adm/qestnar/${qestnarVO.qestnarSeqNo}/selectQestnPrfaceView.do" class="dropdown-item" target="_blank">
    											관리자
  											</a>
  											<a href="javascript:swAlert('준비중입니다.');" class="dropdown-item">
    											사용자
  											</a>
										</div>
									</div>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>	
				<!-- 테이블 : e -->
				<!-- paging navigation -->
				<div class="text-center">
					<ul class="pagination justify-content-center">
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnSelectQestnarList"/>
					</ul>
				</div>			
			</div>
		</div>
	</div>
</div>

