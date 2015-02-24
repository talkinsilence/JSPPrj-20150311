<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="total" required="false" rtexprvalue="true"%>
<!-- 속성명. 필수컬럼인지 아닌지. expression을 이용해 값을 넣을 수 있게 할 것인지.  -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.eoot.com/jsp/tags/function"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="urlTokens" value="${fn:split(pageContext.request.requestURI,'?')}" />
${urlTokens[0]}  
<%-- 복잡하니까 set을 이용해서 두 단계로 나눠줌  --%>
<%-- ${fn:split(pageContext.request.requestURI, "?")[0] } --%>

<ul class="pager-list">
	<c:if test="${empty param.page}">
		<c:set var="page" value="1" />
	</c:if>
	<c:if test="${not empty param.page}">
		<c:set var="page" value="${param.page}" />
	</c:if>
	<c:set var="startNum" value="${page - (page - 1) % 5}" />
	<c:forEach var="i" begin="${startNum}" end="${startNum+4}">
		<c:if test="${i <= f:lastNum(total)}">
			<c:if test="${page==i}">
				<li class="pager-item"><a href="notice.jsp?p=${i}&q=${param.q}&f=${param.f}" class="text-highlight">${i}</a></li>
			</c:if>                                           
			<c:if test="${page!=i}">
				<li class="pager-item"><a href="notice.jsp?p=${i}&q=${param.q}&f=${param.f}">${i}</a></li>
			</c:if>
		</c:if>
		<c:if test="${i > f:lastNum(total)}">
			<li class="pager-item">${i}</li>
		</c:if>
	</c:forEach>
</ul>