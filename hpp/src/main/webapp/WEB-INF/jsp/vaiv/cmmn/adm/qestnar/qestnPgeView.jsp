<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>

<style>
	.container-fluid {
		padding: 0% 25%;
   		margin-top: 60px;
	}
</style>
<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/qestnar/qestnarView.js"></script>

<form name="qestnarQestnForm" id="qestnarQestnForm" method="post" onsubmit="return false;" enctype="multipart/form-data">
	<input type="hidden" name="qestnAnswer" value="">
	<c:choose>
		<c:when test="${viewType eq 'Prface'}">
			<input type="hidden" name="qestnarPgeNum" value="1">
		</c:when>
		<c:otherwise>
			<input type="hidden" name="qestnarPgeNum" value="${qestnarPgeNum + 1 }">
		</c:otherwise>
	</c:choose>
	<div class="container-fluid">
		<div class="card">
			<div class="card-body">
				<h1 class="h1"><c:out value="${qestnarInfo.qestnarNm }"/> 
					<c:if test="${viewType eq '' }">
					(${qestnarPgeNum } / ${not empty qestnList ? qestnList[0].qestnMaxPge : qestnarPgeNum })
					</c:if>
				</h1>
				<c:choose>
					<c:when test="${viewType eq 'Prface' }">
						<div class="col-md-12">
							<c:out value="${qestnarInfo.qestnarPrface }" escapeXml="false"/>
						</div>
					</c:when>
					<c:when test="${viewType eq 'Cnclsn' }">
						<div class="col-md-12">
							<c:out value="${qestnarInfo.qestnarCnclsn }" escapeXml="false"/>
						</div>
					</c:when>
					<c:otherwise>
					<c:forEach items="${qestnList}" var="qestnInfo" varStatus="status">
						<div class="col-md-12 qestn">
							<input type="hidden" name="qestnRequire" value="${qestnInfo.qestnEssntlAt }">
							<input type="hidden" name="qestnType" value="${qestnInfo.qestnTy }">
							<input type="hidden" name="qestnSeqNo" value="${qestnInfo.qestnSeqNo }">
							<c:if test="${!status.first }">
								<hr>
							</c:if>
							<h2 class="card-title">
								<span><c:out value="${qestnInfo.qestnOrdr }"/>. </span>
								<c:out value="${qestnInfo.qestnSj }"/>
								<c:if test="${qestnInfo.qestnEssntlAt eq 'Y' }">
									<label><span class="pilsu">*</span></label> 
								</c:if>
							</h2>
							<c:if test="${not empty qestnInfo.qestnHpcm }">
							<h6 class="card-subtitle mt-2 mb-3">
								<c:out value="${qestnInfo.qestnHpcm }"/>
							</h6>
							</c:if>
							<!-- 실제 질의 -->
							<c:choose>
								<c:when test="${qestnInfo.qestnTy eq 'text' }">
									<div class="form-group">
										<c:forEach items="${qestnInfo.qestnOptnList }" var="optnVO" varStatus="status">
											<c:if test="${optnVO.optnTy eq 'Lwc'}">
												<c:set var="lwcOptionVal" value="${optnVO.optnValue }"/>
											</c:if>
										</c:forEach>
										<input type="text" class="form-control qestn_answer" name="qestnAnswer${qestnInfo.qestnSeqNo }" maxlength="${lwcOptionVal }">
									</div>
								</c:when>
								<c:when test="${qestnInfo.qestnTy eq 'textarea' }">
									<div class="form-group">
										<c:forEach items="${qestnInfo.qestnOptnList }" var="optnVO" varStatus="status">
											<c:if test="${optnVO.optnTy eq 'Lwc'}">
												<c:set var="lwcOptionVal" value="${optnVO.optnValue }"/>
											</c:if>
										</c:forEach>
										<textarea class="form-control qestn_answer" name="qestnAnswer${qestnInfo.qestnSeqNo }" rows="5" cols="5" maxlength="${lwcOptionVal }"></textarea>
									</div>
								</c:when>
								<c:when test="${qestnInfo.qestnTy eq 'radio' }">
									<div class="form-group">
										<c:if test="${not empty qestnInfo.qestnAswperList }">
											<c:forEach items="${qestnInfo.qestnAswperList }" var="aswperVO" varStatus="status">
												<c:if test="${aswperVO.aswperEtcAt eq 'N' }">
												<label class="radio-inline qestn_radio1">
													<input type="radio" name="qestnAnswer${qestnInfo.qestnSeqNo }" value="${aswperVO.aswperSeqNo }"> ${aswperVO.aswperText}
													<c:if test="${not empty aswperVO.aswperImage }">
														<br>
														<img alt="${aswperVO.aswperImage }의 이미지" src="${aswperVO.aswperImage }" width="150" height="150">
													</c:if> 
												</label>
												</c:if>
												<c:if test="${aswperVO.aswperEtcAt eq 'Y' }">
													<label class="radio-inline qestn_radio1">
														<input type="radio" class="etc" name="qestnAnswer${qestnInfo.qestnSeqNo }" value="${aswperVO.aswperSeqNo}"> 기타
													</label>
												</c:if>
											</c:forEach>
										</c:if>
									</div>
								</c:when>
								<c:when test="${qestnInfo.qestnTy eq 'checkbox' }">
									<div class="form-group">
										<c:if test="${not empty qestnInfo.qestnAswperList }">
											<c:forEach items="${qestnInfo.qestnAswperList }" var="aswperVO" varStatus="status">
												<c:if test="${aswperVO.aswperEtcAt eq 'N' }">
												<label class="qestn_radio1">
													<input type="checkbox" name="qestnAnswer${qestnInfo.qestnSeqNo }" value="${aswperVO.aswperSeqNo }"> ${aswperVO.aswperText}
													<c:if test="${not empty aswperVO.aswperImage }">
														<br>
														<img alt="${aswperVO.aswperImage }의 이미지" src="${aswperVO.aswperImage }" width="150" height="150">
													</c:if> 
												</label>
												</c:if>
												<c:if test="${aswperVO.aswperEtcAt eq 'Y' }">
													<label class="qestn_radio1">
														<input type="checkbox" class="etc" name="qestnAnswer${qestnInfo.qestnSeqNo }" value="${aswperVO.aswperSeqNo}"> 기타
													</label>
												</c:if>
											</c:forEach>
										</c:if>
									</div>
								</c:when>
								<c:when test="${qestnInfo.qestnTy eq 'select' }">
									<div class="form-group">
										<select class="form-control qestn_answer" name="qestnAnswer${qestnInfo.qestnSeqNo }">
											<c:forEach items="${qestnInfo.qestnAswperList }" var="aswperVO" varStatus="status">
												<option value="${aswperVO.aswperSeqNo }"><c:out value="${aswperVO.aswperText}"/></option>
											</c:forEach>
										</select>				
									</div>
								</c:when>
								<c:when test="${qestnInfo.qestnTy eq 'tel' }">
									<div class="form-group">
										<input type="text" class="form-control phoneNumber qestn_answer" name="qestnAnswer${qestnInfo.qestnSeqNo }" maxlength="13" placeholder="연락처를 입력하세요.">
									</div>
								</c:when>
								<c:when test="${qestnInfo.qestnTy eq 'address' }">
									<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
									<div class="form-group">
										<div class="input-group">
											<input type="text" id="addr" class="form-control qestn_answer" name="qestnAnswer${qestnInfo.qestnSeqNo }" title="주소 입력" placeholder="주소를 입력하세요.">
											<div class="input-group-append">
												<a href="javascript:searchAddr('','addr');" class="input-group-text qestn_address">주소 검색</a>
											</div>
										</div>
									</div>
								</c:when>
								<c:when test="${qestnInfo.qestnTy eq 'calen' }">
									<div class="form-group">
										<c:forEach items="${qestnInfo.qestnOptnList }" var="optnVO" varStatus="status">
											<c:if test="${optnVO.optnTy eq 'Tic'}">
												<c:set var="ticOptionVal" value="${optnVO.optnValue }"/>
											</c:if>
										</c:forEach>
										<input type="${ticOptionVal eq 'Y' ? 'datetime-local' : 'date' }" class="form-control datepicker${ticOptionVal eq 'Y' ? '-time' : '' } qestn_answer" name="qestnAnswer${qestnInfo.qestnSeqNo }" placeholder="날짜를 선택해주세요.">
									</div>
								</c:when>
								<c:when test="${qestnInfo.qestnTy eq 'time' }">
									<div class="form-group">
										<input type="time" class="form-control timepicker qestn_answer" name="qestnAnswer${qestnInfo.qestnSeqNo }" placeholder="날짜를 선택해주세요.">
									</div>
								</c:when>
								<c:when test="${qestnInfo.qestnTy eq 'calenterm' }">
									<div class="form-group">
										<c:forEach items="${qestnInfo.qestnOptnList }" var="optnVO" varStatus="status">
											<c:if test="${optnVO.optnTy eq 'Tic'}">
												<c:set var="ticOptionVal" value="${optnVO.optnValue }"/>
											</c:if>
										</c:forEach>
										<div class="input-group">
											<div class="qestndate_wrap">
												<span>
													<input type="${ticOptionVal eq 'Y' ? 'datetime-local' : 'date' }" name="qestnAnswer${qestnInfo.qestnSeqNo }" class="form-control datepicker${ticOptionVal eq 'Y' ? '-time' : '' } qestndate" placeholder="날짜를 선택해주세요">
												</span>
											</div>
											<em class="qestn_date"> ~ </em>
											<div class="qestndate_wrap">
												<span>
													<input type="${ticOptionVal eq 'Y' ? 'datetime-local' : 'date' }" name="qestnAnswer${qestnInfo.qestnSeqNo }" class="form-control datepicker${ticOptionVal eq 'Y' ? '-time' : '' } qestndate" placeholder="날짜를 선택해주세요">
												</span>
											</div>
										</div>
									</div>
								</c:when>
								<c:when test="${qestnInfo.qestnTy eq 'file' }">
									<div class="form-group fileArea">
										<c:forEach items="${qestnInfo.qestnOptnList }" var="optnVO" varStatus="status">
											<c:if test="${optnVO.optnTy eq 'Afe'}">
												<c:if test="${optnVO.optnValue ne 'N'}">
												<input type="hidden" name="atchFilePermExtsn" value="${optnVO.optnValue }">
												</c:if>
											</c:if>
											<c:if test="${optnVO.optnTy eq 'Mfc'}">
												<c:if test="${optnVO.optnValue ne 'N'}">
												<input type="hidden" name="atchFilePermMxmmCnt" value="${optnVO.optnValue }">
												</c:if>
											</c:if>
										</c:forEach>
										<div class="input-group mb-3">
							            	<div class="custom-file">
			                                    <label class="custom-file-label qestn_file" for="file_0">파일을 등록해주세요.</label>
					                        	<input type="file" name="file_" class="custom-file-input" id="egovComFileUploader${qestnInfo.qestnSeqNo }" readonly="readonly">
					                        </div>
										</div>
										<div class="" id="egovComFileList${qestnInfo.qestnSeqNo }">
											
										</div>
									</div>
								</c:when>
							</c:choose>
						</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="card-footer">
				<div class="right">
					<c:choose>
						<c:when test="${viewType eq 'Prface' }">
							<a class="btn btn-primary" href="javascript:;" onclick="qestnarQestnPgeNextFn('/cmmn/adm/qestnar/${qestnarSeqNo}/selectQestnView${not empty testYn && testYn == 'Y' ? 'Y' : '' }.do');">시작</a>
							<input type="hidden" name="qestnarRate" value="1">
						</c:when>
						<c:when test="${viewType eq 'Cnclsn' }">
							<a class="btn btn-primary" href="javascript:self.close();">닫기</a>
						</c:when>
						<c:otherwise>
							<c:if test="${qestnarPgeNum ne qestnList[0].qestnMaxPge }">
								<a class="btn btn-primary" href="javascript:;" onclick="qestnarQestnPgeNextFn('/cmmn/adm/qestnar/${qestnarSeqNo}/selectQestnView${not empty testYn && testYn == 'Y' ? 'Y' : '' }.do');">다음</a>
							</c:if>
							<c:if test="${qestnarPgeNum eq qestnList[0].qestnMaxPge}">
								<a class="btn btn-primary" href="javascript:;" onclick="qestnarQestnPgeNextFn('/cmmn/adm/qestnar/${qestnarSeqNo}/selectQestnCnclsnView${not empty testYn && testYn == 'Y' ? 'Y' : '' }.do');">제출</a>
								<input type="hidden" name="qestnarRate" value="end">
							</c:if>
							<input type="hidden" name="qestnarResultSeqNo" value="${qestnarResultSeqNo }">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</form>