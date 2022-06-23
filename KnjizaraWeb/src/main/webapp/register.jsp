<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/style.css">
<meta charset="UTF-8">
<title>Registracija</title>
</head>
<body>
	<%@ include file="/sidenav.jsp"%>

	<div class="content">

		<form:form modelAttribute="korisnik" action="register" method="post">
			<table>
				<tr>
					<td>Ime:</td>
					<td><form:input path="ime" required="true" /></td>
				</tr>
				<tr>
					<td>Prezime:</td>
					<td><form:input path="prezime" required="true" /></td>
				</tr>
				<tr>
					<td>Korisnicko ime:</td>
					<td><form:input path="username" required="true" /></td>
				</tr>
				<tr>
					<td>Sifra:</td>
					<td><form:input path="password" required="true" /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><form:input path="email" required="true" /></td>
				</tr>
				<tr>
					<td>Uloga:</td>
					<td><form:select path="uloga" items="${roles}"
							itemValue="idUloga" itemLabel="naziv" /></td>
				</tr>
				<tr>
					<td>Telefon:</td>
					<td><form:input path="telefon" /></td>
				</tr>
				<tr>
					<td>Adresa:</td>
					<td><form:input path="adresa" /></td>
				</tr>
				<tr>
					<td />
					<td><input type="submit" value="Sacuvaj"></td>
				</tr>

			</table>
		</form:form>
	</div>

</body>
</html>