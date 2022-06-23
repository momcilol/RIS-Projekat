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
<title>Unos knjige</title>
</head>
<body>

	<%@ include file="/sidenav.jsp"%>

	<div class="content">
		<form:form action="/BookStore/adminKatalog/saveKnjiga" method="post"
			modelAttribute="knjigaImg" enctype="multipart/form-data">
			<table>
				<tr>
					<td>Naziv:</td>
					<td><form:input path="naziv" required="true" /></td>
				</tr>
				<tr>
					<td>Godina izdanja:</td>
					<td><form:input type="number" path="godinaIzdanja"
							required="true" /></td>
				</tr>
				<tr>
					<td>Izdavac:</td>
					<td><form:input path="izdavac" required="true" /></td>
				</tr>
				<tr>
					<td>Opis:</td>
					<td><form:textarea path="opis" /></td>
				</tr>
				<tr>
					<td>Slika:</td>
					<td><form:input type="file" path="slika" /></td>
				</tr>
				<tr>
					<td>Kategorija:</td>
					<td><form:select path="kategorijas" items="${sveKategorije}"
							itemValue="idKategorija" itemLabel="naziv" multiple="true" /></td>
				</tr>
				<tr>
					<td>Pisac:</td>
					<td><form:select path="pisac" items="${sviPisci}"
							itemValue="idPisac" itemLabel="fullName" /></td>
				</tr>
				<tr>
					<td>Jezik:</td>
					<td><form:select path="jezik" items="${sviJezici}"
							itemValue="idJezik" itemLabel="naziv" /></td>
				</tr>
				<tr>
					<td />
					<td><input type="submit" value="Sacuvaj" /></td>
				</tr>
			</table>
		</form:form>


		<c:if test="${!empty k}">
			${poruka}
		</c:if>
		<c:if test="${empty k}">
			<div class="error">${poruka}</div>
		</c:if>
	</div>
</body>
</html>