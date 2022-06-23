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
<title>Pretraga knjige</title>
</head>
<body>

	<%@ include file="/sidenav.jsp"%>

	<div class="content">

		<h2>Pretraga knjige</h2>

		<c:if test="${empty k}">
			${poruka}
		</c:if>
		<c:if test="${!empty k}">
			<div class="error">${poruka}</div>
		</c:if>

		<form:form action="/BookStore/pretraga/searchKnjiga" method="get"
			modelAttribute="knjigaSearch">
			<table>
				<tr>
					<td>Naziv:</td>
					<td><form:input path="naziv" /></td>
				</tr>
				<tr>
					<td>Kategorija:</td>
					<td><form:select path="kategorijas" items="${sveKategorije}"
							itemValue="idKategorija" itemLabel="naziv" multiple="true" /></td>
				</tr>
				<tr>
					<td>Pisac:</td>
					<td><form:select path="pisac">
							<form:option value="0" label="" />
							<form:options items="${sviPisci}" itemValue="idPisac"
								itemLabel="fullName" />
						</form:select></td>
				</tr>
				<tr>
					<td>Jezik:</td>
					<td><form:select path="jezik">
							<form:option value="0" label="" />
							<form:options items="${sviJezici}" itemValue="idJezik"
								itemLabel="naziv" />
						</form:select></td>
				</tr>
				<tr>
					<td />
					<td><input type="submit" value="Pretrazi" /></td>
				</tr>
			</table>

		</form:form>
		<br> <br>
		<table>
			<c:forEach items="${knjige}" var="k">
				<tr>
					<td>
						<div class="box">
							<div class="imgBx">
								<img src="/BookStore/pretraga/getKnjigaPhoto/${k.idKnjiga}">
							</div>
							<div class="con">
								<h3>${k.naziv }</h3>
								<p>
									${k.pisac.fullName}<br> ${k.izdavac}<br>
									${k.godinaIzdanja}<br> ${k.averageOcena}
								</p>
								<sec:authorize access="hasRole('Administrator')">
									<a class="button bad" type="button"
										href="/BookStore/adminKatalog/deleteKnjiga?idKnjiga=${k.idKnjiga}">Obrisi</a>
								</sec:authorize>
								<sec:authorize access="hasRole('Korisnik')">
									<a class="button good" type="button"
										href="/BookStore/korisnik/prikaziDetalje?idKnjiga=${k.idKnjiga}">Prikazi
										detalje</a>
								</sec:authorize>
								<sec:authorize access="!hasRole('Korisnik')">
									<a class="button good" type="button"
										href="/BookStore/pretraga/prikaziDetalje?idKnjiga=${k.idKnjiga}">Prikazi
										detalje</a>
								</sec:authorize>


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