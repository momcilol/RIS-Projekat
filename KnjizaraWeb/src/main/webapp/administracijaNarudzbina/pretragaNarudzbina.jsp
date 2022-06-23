<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/style.css">
<meta charset="UTF-8">
<title>Pretraga Narudzbina</title>
</head>
<body>

	<%@ include file="/sidenav.jsp"%>

	<div class="content">


		<h2>Pretraga narudzbina</h2>

		<c:if test="${empty n}">
			${poruka}
		</c:if>
		<c:if test="${!empty n}">
			<div class="error">${poruka}</div>
		</c:if>

		<form:form action="/BookStore/narudzbine/searchNarudzbina"
			method="get" modelAttribute="narudzbinaSearch">
			<table>
				<tr>
					<td>Naziv:</td>
					<td><form:input path="naziv" /></td>
				</tr>
				<tr>
					<td>Username:</td>
					<td><form:input path="username" /></td>
				</tr>
				<tr>
					<td>Datum od:</td>
					<td><form:input path="datumOd" type="date" />
					</td>
				</tr>
				<tr>
					<td>Datum do:</td>
					<td><form:input path="datumDo" type="date" />
					</td>
				</tr>
				<tr>
					<td />
					<td><input type="submit" value="Pretrazi" /></td>
				</tr>

			</table>

		</form:form>
		<br> <br>

		<table id="employees">
			<tr>
				<th>Naziv knjige</th>
				<th>Username</th>
				<th>Ime i prezime</th>
				<th>Datum narudzbine</th>
				<th>Obrisi narudzbinu</th>
			</tr>
			<c:forEach items="${narudzbine}" var="n">
				<tr>
					<td>${n.knjiga.naziv}</td>
					<td>${n.korisnik.username}</td>
					<td>${n.korisnik.fullName}</td>
					<td>${n.datum}</td>
					<td><a class="button bad" style="width: 100%"
						href="/BookStore/narudzbine/deleteNarudzbina?idNarudzbina=${n.idNarudzbina}">Obrisi
							narudzbinu</a></td>
				</tr>
			</c:forEach>
		</table>

	</div>
</body>
</html>