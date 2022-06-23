<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/style.css">
<meta charset="UTF-8">
<title>Brisanje pisca</title>
</head>
<body>

	<%@ include file="/sidenav.jsp"%>

	<div class="content">
		
		<h2>Brisanje pisca</h2>

		<c:forEach items="${sviPisci}" var="p">
			<a class="button bad" type="button" href="/BookStore/adminKatalog/deletePisac?idPisac=${p.idPisac}">${p.fullName}</a><br>
		</c:forEach>
		
		<c:if test="${empty p}">
			${poruka}
		</c:if>
		<c:if test="${!empty p}">
			<div class="error">
				${poruka}
			</div>
		</c:if>

	</div>

</body>
</html>