<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/style.css">
<meta charset="UTF-8">
<title>Prikaz zaposlenih</title>
</head>
<body>

	<%@ include file="/sidenav.jsp"%>

	<div class="content">

		<h2>Prikaz Zaposlenih</h2>

		<c:if test="${empty k}">
			${poruka}
		</c:if>
		<c:if test="${!empty k}">
			<div class="error">${poruka}</div>
		</c:if>

		<table id="employees">
			<tr>
				<th>Ime</th>
				<th>Prezime</th>
				<th>Email</th>
				<th>Detalji</th>
			</tr>
			<c:forEach items="${sviZaposleni}" var="z">
				<tr>
					<td>${z.ime}</td>
					<td>${z.prezime}</td>
					<td>${z.email}</td>
					<td><a class="button good" style="width: 100%"
						href="/BookStore/adminZaposleni/prikazDetalja?id=${z.idKorisnik}">
							Prikazi detalje</a></td>
				</tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>