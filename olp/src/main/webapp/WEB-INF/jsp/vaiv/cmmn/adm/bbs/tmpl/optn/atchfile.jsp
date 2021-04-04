<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : nttOptn > atchfile.jsp
  * @상세설명 : 게시물 옵션 페이지(첨부파일) 
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

<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/egovframework/com/fms/EgovMultiFile.js"></script>

<c:choose>
	<c:when test="${bbsTyCode eq 'xxx' }">
		<!-- 게시글 첨부파일 사용 시 특별한 템플릿이 있을경우 추가 -->
	</c:when>
	<c:otherwise>
		<!-- 기본적으로 사용되는 게시글 템플릿 -->
		<div class="col-sm-12 col-md-2 control-label col-lg-2">
			<!-- 첨부파일 -->
			<label for="file_0">첨부파일 <span class="pilsu">*</span></label>
		</div>
		<div class="col-sm-12 col-md-10">
			<div class="form-imgbox_file">
                <div class="custom-file">
                    <label class="custom-file-label" for="file_0">파일 찾아보기</label>
                	<input type="file" name="file_" id="egovComFileUploader" class="custom-file-input" readonly="readonly">
                </div>
            </div>
            <div class="" id="egovComFileList">
            	<c:if test="${formType eq 'update' }">
					<c:import url="/cmmn/com/cfc/nttFileList.do">
						<c:param name="atchFileId" value="${nttVO.atchFileId }"/>
						<c:param name="nttId" value="${nttVO.nttId }"/>
						<c:param name="loadType" value="editFileList"/>
					</c:import>
				</c:if>
            </div>
		</div>
		<input type="hidden" id="atchFilePermExtsn" name="atchFilePermExtsn" value="${atchFilePermExtsn }">
		<input type="hidden" id="atchFilePermMxmmCnt" name="atchFilePermMxmmCnt" value="${atchFilePermMxmmCnt }">
	</c:otherwise>
</c:choose>

<script>
	var maxFileNum = $("#atchFilePermMxmmCnt").val();
	var multi_selector = new MultiSelector(document.getElementById('egovComFileList'), maxFileNum);
	multi_selector.addElement(document.getElementById('egovComFileUploader'));
	if($(".file_add").length > 0){
		multi_selector.setCurrentCount($(".file_add").length + 1);
	}
</script>