<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : menuManageMain.jsp
  * @상세설명 : 메뉴 관리 페이지
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

<script src="${pageContext.request.contextPath }/static/js/vaiv/cmmn/adm/menu/menu.js"></script>

<div class="container-fluid">
	<h1 class="header-title"></h1>
	<div class="card">
		<div class="card-body">
			<h1 class="h1">
				<c:choose>
					<c:when test="${nowMenuSe eq 'A' }">관리자</c:when>
					<c:otherwise>사용자</c:otherwise>
				</c:choose>
				 메뉴 관리
			</h1>
			<fieldset>
				<legend>메뉴 검색 영역</legend>
				<div class="ui program-search" title="메뉴 검색 영역">
					<div class="btn-group float-left">
						<a href="javascript:menuSave();" class="btn btn-sm btn-basic">
							<i class="fa fa-save" aria-hidden="true"></i> 일괄 저장
						</a>
						<a href="#menuRegistModal" data-toggle="modal" data-type="reg" class="btn btn-sm btn-info">
							메뉴 생성
						</a>
					</div>
				</div>
			</fieldset>
			<div class="dd nestableWapper">
			    <ol class="dd-list kanban-category" id="menuList">
			        
			    </ol>
			</div>
		</div>
	</div>
</div>

<input type="hidden" name="menuSe" id="menuSe" value="${nowMenuSe }">
<input type="hidden" name="deleteMenuStr" id="deleteMenuStr" value="">

<!-- 메뉴 등록 모달 -->
<div class="modal fade" id="menuRegistModal" data-backdrop="static" aria-labelledby="menuRegistModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title" id="staticBackdropLabel">메뉴 생성</h2>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form id="menuRegistForm">
					<div class="form-group">
						<label for="menuNm">메뉴 이름</label>
						<input class="form-control" id="menuNm" name="menuNm" title="메뉴 이름" type="text" placeholder="메뉴 이름 입력..">
					</div>
					<div class="form-group">
						<label for="typeP">메뉴타입</label>
						<div class="btn-group-toggle" data-toggle="buttons">
	                    	<label for="typeP" class="btn btn-white">
	                      		<input type="radio" name="menuTy" id="typeP" value="P">
	                      		<i class="far fa-check-circle"></i> 프로그램
	                    	</label>
	                    	<label for="typeB" class="btn btn-white">
	                      		<input type="radio" name="menuTy" id="typeB" value="B">
	                      		<i class="far fa-check-circle"></i> 게시판
	                    	</label>
	                    	<label for="typeC" class="btn btn-white">
	                      		<input type="radio" name="menuTy" id="typeC" value="C">
	                      		<i class="far fa-check-circle"></i> 컨텐츠
	                    	</label>
	                  	</div>
					</div>
					<div class="form-group" id="menuUrlArea">
						<label for="menuUrl">메뉴 URL</label>
						<input class="form-control" id="menuUrl" name="menuUrl" title="메뉴 URL" type="text" placeholder="메뉴 URL 입력..">
					</div>
					<div class="form-group" id="menuBbsArea">
						<label for="menuBbs">게시판 목록</label>
						<select class="custom-select" id="menuBbs" name="menuBbs" title="게시판 목록" data-toggle="select">
							<option value="">- 선택 -</option>
							<c:forEach items="${bbsList }" var="bbs" varStatus="status">
								<option value="${bbs.bbsId }">${bbs.bbsNm }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group" id="menuCntntsArea">
						<label for="menuCntnts">컨텐츠 목록</label>
						<select class="custom-select" id="menuCntnts" name="menuCntnts" title="컨텐츠 목록" data-toggle="select">
							<option value="">- 선택 -</option>
							<c:forEach items="${cntntsList }" var="cntnts" varStatus="status">
								<option value="${cntnts.cntntsId }">${cntnts.cntntsNm }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="menuTarget">메뉴 Target</label>
						<select class="custom-select" id="menuTarget" name="menuTarget" title="메뉴 Target" data-toggle="select">
							<option value="_blank">새 창</option>
							<option value="_self">현재 창</option>
						</select>
					</div>
					<div class="form-group">
						<label for="menuAuthor">메뉴 권한</label>
						<select class="custom-select" id="menuAuthor" name="menuAuthor" title="메뉴 권한" data-toggle="select">
							<c:forEach items="${authorList }" var="author" varStatus="status">
								<option value="${author.authorCode }">${author.authorNm }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="menuIconClass">아이콘 Class</label>
						<input class="form-control" id="menuIconClass" name="menuIconClass" title="메뉴 아이콘 Class" type="text" placeholder="메뉴 아이콘 Class 입력..">
					</div>
					<div class="form-group">
						<label for="useAtY">사용여부</label>
						<div class="btn-group-toggle" data-toggle="buttons">
	                    	<label for="useAtY" class="btn btn-white">
	                      		<input type="radio" name="menuUseAt" id="useAtY" value="Y">
	                      		<i class="far fa-check-circle"></i> 사용
	                    	</label>
	                    	<label for="useAtN" class="btn btn-white">
	                      		<input type="radio" name="menuUseAt" id="useAtN" value="N">
	                      		<i class="far fa-check-circle"></i> 사용안함
	                    	</label>
	                  	</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-primary" id="menuRegistBtn">생성</button>
			</div>
		</div>
	</div>
</div>
<!-- 메뉴 등록 모달 끝 -->