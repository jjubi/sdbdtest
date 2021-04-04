<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>

<script>
//메시지가 있을 경우 띄우기
if(eval("${not empty message}")){
	Swal.fire('확인', '${message}', 'info');
} else {
	let urlMessage = get_parameter_query('message');
	if(urlMessage != null){
		Swal.fire('확인', urlMessage, 'info');
	}
}
</script>

<nav class="topnavbar">
    <a class="navbar-brand" href="${pageContext.request.contextPath }/cmmn/adm/dashboard/dashboardMain.do">
		<span class="sr-only">Context Management System</span>
	</a>
    <button class="btn btn-link" id="sidebarToggle">
    	<span class="btn_sidebarToggle">
    		<span class="hiddentext">전체목록</span>
    	</span>
    </button>
    <ul class="toplist">
        <li class="Adminuser"><strong><c:out value="${loginVO.name }"/></strong><em>님</em></li>
        <li class="btn_logout"><a href="javascript:logout();">로그아웃</a></li>
    </ul>
</nav>
