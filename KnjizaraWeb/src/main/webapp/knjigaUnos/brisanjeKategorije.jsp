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
<title>Brisanje kategorije</title>
</head>
<body>

	<%@ include file="/sidenav.jsp"%>

	<div class="content">
		
		<h2>Brisanje kategorije</h2>

		<c:forEach items="${sveKategorije}" var="k">
			<a class="button bad" type="button" href="/BookStore/adminKatalog/deleteKategorija?idKategorija=${k.idKategorija}">${k.naziv}</a><br>
		</c:forEach>
		
		<c:if test="${empty k}">
			${poruka}
		</c:if>
		<c:if test="${!empty k}">
			<div class="error">
				${poruka}
			</div>
		</c:if>

	</div>

</body>
</html>