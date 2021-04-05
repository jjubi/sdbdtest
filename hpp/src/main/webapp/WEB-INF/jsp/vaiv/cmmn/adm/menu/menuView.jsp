<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/static/common/taglib.jsp"%>
<%
 /**
  * @Jsp Name : adm > menuView.jsp
  * @상세설명 : 메뉴 목록 표출 페이지
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

<div class="nav" id="leftNavbar">
	<c:forEach items="${resultList }" var="menuVO" varStatus="status">
		<sec:authorize access="hasRole('${menuVO.menuAuthor }')">
		<c:if test="${menuVO.menuDp eq 1 }">
				<c:choose>
					<c:when test="${!status.last and (resultList[status.index+1].menuDp eq 2 )}">
						<a class="nav-link collapsed" href="#menu-G${menuVO.menuSeqNo }" data-toggle="collapse" role="button" aria-expanded="false" aria-controls="menu-G${menuVO.menuSeqNo }">
							<c:if test="${not empty menuVO.menuIconClass }">
								<i class="${menuVO.menuIconClass }"></i> &nbsp;
							</c:if>
							<c:out value="${menuVO.menuNm }"/>
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="menu-G${menuVO.menuSeqNo }" data-parent="#sidenavAccordion">
							<nav class="nav sb-sidenav-menu-nested">
								<c:forEach items="${resultList }" var="menuVO2" varStatus="status2">
									<sec:authorize access="hasRole('${menuVO2.menuAuthor }')">
									<c:if test="${menuVO.menuSeqNo eq menuVO2.upperSeqNo and menuVO2.menuDp eq 2}">
										<c:choose>
											<c:when test="${!status2.last and (resultList[status2.index+1].menuDp eq 3 )}">
												<a class="nav-link collapsed" href="#menu-G${menuVO2.menuSeqNo }" data-toggle="collapse" role="button" aria-expanded="false" aria-controls="menu-G${menuVO2.menuSeqNo }">
													<c:if test="${not empty menuVO2.menuIconClass }">
														<i class="${menuVO2.menuIconClass }"></i> &nbsp;
													</c:if>
													<c:out value="${menuVO2.menuNm }"/>
													<div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
												</a>
												<div class="collapse" id="menu-G${menuVO2.menuSeqNo }" data-parent="#menu-G${menuVO.menuSeqNo }">
													<nav class="nav sb-sidenav-menu-nested">
														<c:forEach items="${resultList }" var="menuVO3" varStatus="status3">
															<sec:authorize access="hasRole('${menuVO3.menuAuthor }')">
															<c:if test="${menuVO2.menuSeqNo eq menuVO3.upperSeqNo and menuVO3.menuDp eq 3}">
																<li class="nav-item">
																	<a class="nav-link" href="${pageContext.request.contextPath }${menuVO3.menuUrl }" target="${menuVO3.menuTarget }" title="${menuVO3.menuNm }" data-msn="${menuVO3.menuSeqNo }">
																		<c:if test="${not empty menuVO3.menuIconClass }">
																			<i class="${menuVO3.menuIconClass }"></i> &nbsp;
																		</c:if>
																		<c:out value="${menuVO3.menuNm }"/>
																	</a>
																</li>
															</c:if>
															</sec:authorize>
														</c:forEach>
													</nav>
												</div>
											</c:when>
											<c:otherwise>
												<a class="nav-link" href="${pageContext.request.contextPath }${menuVO2.menuUrl }" target="${menuVO2.menuTarget }" title="${menuVO2.menuNm }" data-msn="${menuVO2.menuSeqNo }">
													<c:if test="${not empty menuVO2.menuIconClass }">
														<i class="${menuVO2.menuIconClass }"></i> &nbsp;
													</c:if>
													<c:out value="${menuVO2.menuNm }"/>
												</a>	
											</c:otherwise>
										</c:choose>
									</c:if>
									</sec:authorize>
								</c:forEach>
							</nav>
						</div>
					</c:when>
					<c:otherwise>
						<a class="nav-link" href="${pageContext.request.contextPath }${menuVO.menuUrl }" target="${menuVO.menuTarget }" title="${menuVO.menuNm }" data-msn="${menuVO.menuSeqNo }">
							<c:if test="${not empty menuVO.menuIconClass }">
								<i class="${menuVO.menuIconClass }"></i> &nbsp;
							</c:if>
							<c:out value="${menuVO.menuNm }"/>
						</a>
					</c:otherwise>
				</c:choose>
		</c:if>
		</sec:authorize>
	</c:forEach>
</div>