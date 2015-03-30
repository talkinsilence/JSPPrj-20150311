<%@taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="nami" uri ="http://www.eoot.com/jsp/tags/control" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/bind.css" rel="stylesheet" type="text/css" />
<link href="css/notice.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="modernizr.js"></script>
</head>
<body>
	
	<!-- header 부분 -->
	<tiles:insertAttribute name="header"/>

	<!-- visual 부분 -->
	<tiles:insertAttribute name="visual"/>
	<%-- <jsp:include page="/customer/inc/visual.jsp"></jsp:include> --%>

	<div id="body">
		<div class="content-wrapper clearfix">
			<aside class="aside-menu-group">
				<h1 class="aside-menu-group-title">고객센터</h1>

				<nav class="aside-main-menu">
					<h1 class="hidden">고객센터 메뉴</h1>
					<ul>
						<li class="aside-menu-item"><a href="">공지사항</a></li>
						<li class="aside-menu-item"><a href="">1:1고객문의</a></li>
						<li class="aside-menu-item"><a href="">학습안내</a></li>
					</ul>
				</nav>

				<nav class="aside-menu">
					<h1>추천사이트</h1>
					<ul class="aside-menu-list">
						<li><img src="../images/answeris.png" alt="앤서이즈" /></li>
						<li><img src="../images/w3c.png" alt="W3C" /></li>
						<li><img src="../images/microsoft.png" alt="마이크로소프트" /></li>
					</ul>
				</nav>

			</aside>

			<!-- main 부분 -->
			<tiles:insertAttribute name="main"/>
			
		</div>
	</div>

	<!-- footer 부분 -->
	<tiles:insertAttribute name="footer"/>
	
	<!-- aside 부분 -->
	<tiles:insertAttribute name="aside"/>
	
</body>
</html>

