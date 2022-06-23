<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/style.css">
<meta charset="UTF-8">
<title>Detalji knjizevne veceri</title>
</head>
<body>

	<%@ include file="/sidenav.jsp"%>

	<div class="content">

		<h2>${kv.knjiga.naziv}</h2>
		<img src="/BookStore/pretraga/getKnjigaPhoto/${kv.knjiga.idKnjiga}"
			class="center"> <br> <br> ${poruka}<br>


		<table id="employees">
			<tr>
				<td>Naziv</td>
				<td>${kv.knjiga.naziv}</td>
			</tr>
			<tr>
				<td>Pisac</td>
				<td>${kv.knjiga.pisac.fullName}</td>
			</tr>
			<tr>
				<td>Mesto</td>
				<td>${kv.mesto}</td>
			</tr>
			<tr>
				<td>Vreme</td>
				<td>${kv.lepoVreme}</td>
			</tr>
			<tr>
				<td>Broj slobodnih mesta</td>
				<td>${kv.brojSlobodnihMesta}</td>
			</tr>
			<sec:authorize access="hasRole('Administrator')">
				<tr>
					<td />
					<td><a class="button bad" type="button"
						href="/BookStore/knjizVec/deleteKnjizevnoVece?idKnjizevnoVece=${kv.idKnjizevnoVece}">Otkazi</a></td>
				</tr>
			</sec:authorize>
			<sec:authorize access="hasRole('Korisnik')">
				<tr>
					<td />
					<c:if test="${kv.brojSlobodnihMesta > 0 && !pr}">
						<td><a class="button good" type="button" style="width: 100%"
							href="/BookStore/korisnik/prijaviKnjizevnuVecer?idKnjizevnoVece=${kv.idKnjizevnoVece}">Prijavi
								se</a></td>
					</c:if>
					<c:if test="${pr}">
						<td><a class="button bad" type="button" style="width: 100%"
							href="/BookStore/korisnik/odjaviKnjizevnuVecer?idKnjizevnoVece=${kv.idKnjizevnoVece}">Odjavi
								se</a></td>
					</c:if>
				</tr>
			</sec:authorize>
		</table>

		<br>
		<br>
		<br>


	</div>

</body>
</html>