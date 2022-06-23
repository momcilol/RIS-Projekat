<%@page import="model.Knjiga"%>
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
<title>Detalji knjige</title>
</head>
<body>


	<%@ include file="/sidenav.jsp"%>

	<div class="content">

		<h2>${k.naziv}</h2>
		<img src="/BookStore/pretraga/getKnjigaPhoto/${k.idKnjiga}"
			class="center"> <br> <br>

		<c:if test="${!empty n}">
			${poruka}
		</c:if>
		<c:if test="${empty n}">
			<div class="error">${poruka}</div>
		</c:if>

		<table id="employees">
			<tr>
				<td>Naziv</td>
				<td>${k.naziv}</td>
			</tr>
			<tr>
				<td>Pisac</td>
				<td>${k.pisac.fullName}</td>
			</tr>
			<tr>
				<td>Godina izdanja</td>
				<td>${k.godinaIzdanja}</td>
			</tr>
			<tr>
				<td>Izdavac</td>
				<td>${k.izdavac}</td>
			</tr>
			<tr>
				<td>Jezik</td>
				<td>${k.jezik.naziv}</td>
			</tr>
			<tr>
				<td>Ocena</td>
				<td>${k.averageOcena}</td>
			</tr>
			<tr>
				<td>Kategorije</td>
				<td><c:forEach items="${k.kategorijas}" var="kat">
						${kat.naziv}<br>
					</c:forEach></td>
			</tr>
			<tr>
				<td>Opis</td>
				<td>${k.opis}</td>
			</tr>
			<sec:authorize access="hasRole('Administrator')">
				<tr>
					<td><a class="button good" type="button" style="width: 100%"
						href="/BookStore/knjizVec/zakaziKnjizevnoVece?idKnjiga=${k.idKnjiga}">Zakazi
							knjizevno vece</a></td>

					<td><a class="button bad" type="button" style="width: 100%"
						href="/BookStore/adminKatalog/deleteKnjiga?idKnjiga=${k.idKnjiga}">Obrisi
					</a></td>
				</tr>
			</sec:authorize>
			<sec:authorize access="hasRole('Korisnik')">
				<tr>
					<td><a class="button good" type="button" style="width: 100%"
						href="/BookStore/korisnik/naruciKnjigu?idKnjiga=${k.idKnjiga}">Naruci</a></td>
					<c:if test="${!fav}">
						<td><a class="button good" type="button" style="width: 100%"
							href="/BookStore/korisnik/omiljenaKnjiga?idKnjiga=${k.idKnjiga}">Dodaj
								u omiljene</a></td>
					</c:if>
					<c:if test="${ fav}">
						<td><a class="button bad" type="button" style="width: 100%"
							href="/BookStore/korisnik/ukloniOmiljenaKnjiga?idKnjiga=${k.idKnjiga}">Ukloni
								iz omiljenih</a></td>
					</c:if>
				</tr>
			</sec:authorize>
		</table>

		<sec:authorize access="hasRole('Korisnik')">
			<h4>Oceni komentar</h4>
			<form:form
				action="/BookStore/korisnik/oceniKnjigu?idKnjiga=${k.idKnjiga}"
				method="post" modelAttribute="ocena">
				<table>
					<tr>
						<td>Ocena</td>
						<td><form:input path="vrednost" type="number" min="0" max="5"
								value="${ocena.vrednost}" /></td>
					</tr>
					<tr>
						<td>Komentar</td>
						<td><form:textarea path="komentar" value="${ocena.komentar}" /></td>
					</tr>
					<tr>
						<td />
						<td><input type="submit" value="Sacuvaj"></td>
					</tr>
				</table>
			</form:form>
		</sec:authorize>

		<br> <br>
		<table>
			<c:forEach items="${k.ocenas}" var="o">
				<tr>
					<td>
						<div class="box" style="width: 700px">
							<div class="con">
								<h3>${o.korisnik.username }</h3>
								<p>
									Ocena: ${o.vrednost}<br> Komentar:<br> ${o.komentar}
								</p>
							</div>
						</div>
					</td>
				</tr>
				<tr><td><br></td></tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>