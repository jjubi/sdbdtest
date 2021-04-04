<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : cntntsHisListModal.jsp
  * @상세설명 : 컨텐츠 히스토로 목록 모달
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

<!-- 컨텐츠 히스토리 목록 모달 -->
<div class="modal fade" id="cntntsHisListModal" data-backdrop="static" aria-labelledby="cntntsHisListModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title" id="cntntsHisListModalLabel">컨텐츠 히스토리 목록</h2>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="table-responsive">
					<input type="hidden" id="cntntsHisId" name="cntntsHisId" value="">
					<table class="table table-bordered text-center table-hover" id="cntntsHisTable">
						<caption class="sr-only">컨텐츠 히스토리 <spring:message code="title.list" /></caption>
						<colgroup>
							<col style="width:8%;">
							<col style="width:auto;">
							<col style="width:20%;">
							<col style="width:28%;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">
									<spring:message code="table.num" /> <!-- 번호 -->
								</th>
								<th scope="col">
									<spring:message code="vaivCntnts.list.cntntsNm" /> <!-- 컨텐츠 명 -->
								</th>
								<th scope="col">
									<spring:message code="vaivCntnts.list.updtDe" /> <!-- 수정일 -->
								</th>
								<th scope="col">
									<spring:message code="vaivCntnts.list.etc" /> <!-- 비고 -->
								</th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
				</div>
				<div class="col" id="cntntsHisPaginationArea">
					
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>