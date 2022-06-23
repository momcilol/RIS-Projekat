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
<title>Pretraga knjizevnih veceri</title>
</head>
<body>

	<%@ include file="/sidenav.jsp"%>

	<div class="content">

		<h2>Pretraga Knjizevne Veceri</h2>


		<form:form action="/BookStore/pretraga/searchKnjizevnoVece"
			method="get" modelAttribute="knjizevnoVeceSearch">
			<table>
				<tr>
					<td>Naziv:</td>
					<td><form:input path="naziv" /></td>
				</tr>
				<tr>
					<td>Mesto:</td>
					<td><form:input path="mesto" /></td>
				</tr>
				<tr>
					<td>Datum od:</td>
					<td><form:input path="datumOd" type="date" /></td>
				</tr>
				<tr>
					<td>Datum do:</td>
					<td><form:input path="datumDo" type="date" /></td>
				</tr>
				<tr>
					<td />
					<td><input type="submit" value="Pretrazi" /></td>
				</tr>
			</table>
		</form:form>

		<br> <br> ${poruka} <br> <br>

		<table>
			<c:forEach items="${knjizevneVeceri}" var="kv">
				<tr>
					<td>
						<div class="box">
							<div class="imgBx">
								<img
									src="/BookStore/pretraga/getKnjigaPhoto/${kv.knjiga.idKnjiga}">
							</div>
							<div class="con">
								<h3>${kv.knjiga.naziv }</h3>
								<p>
									${kv.mesto}<br> ${kv.lepoVreme}<br>
								</p>
								<sec:authorize access="hasRole('Administrator')">
									<a class="button bad" type="button"
										href="/BookStore/knjizVec/deleteKnjizevnoVece?idKnjizevnoVece=${kv.idKnjizevnoVece}">Otkazi</a>
								</sec:authorize>
								<sec:authorize access="hasRole('Korisnik')">
									<a class="button good" type="button"
										href="/BookStore/korisnik/prikaziDetaljeKV?idKnjizevnoVece=${kv.idKnjizevnoVece}">Prikazi
										detalje</a>
								</sec:authorize>
								<sec:authorize access="!hasRole('Korisnik')">
									<a class="button good" type="button"
										href="/BookStore/pretraga/prikaziDetaljeKV?idKnjizevnoVece=${kv.idKnjizevnoVece}">Prikazi
										detalje</a>
								</sec:authorize>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>