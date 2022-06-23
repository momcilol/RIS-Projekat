<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/style.css">
<meta charset="UTF-8">
<title>Prijava</title>
</head>
<body>
	<%@ include file="/sidenav.jsp" %>

	<div class="content">
	
	
		<c:url var="loginUrl" value="/login"/>
		<c:if test="${not empty param.error}">
			<div class="alert alert-danger">
				<p>Pogresni podaci</p>	
			</div>
		</c:if>
		<form action="${loginUrl}" method="post">
			<table>
				<tr>
					<td>Korisnicko ime</td>
					<td><input type="text" name="username" placeholder="Unesite korisnicko ime" required></td>
				</tr>
				<tr>
					<td>Sifra</td>
					<td><input type="password" name="password" placeholder="Unesite sifru" required></td>
				</tr>
				<tr>
					<td>Zapamti me</td>
					<td><input type="checkbox" name="remember-me"></td>
				</tr>
				<tr>
					<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></td>
					<td><input type="submit" value="Prijava"></td>
				</tr>
			</table><br>
			Nemate nalog? <a href="/BookStore/auth/registerUser">Registrujte se</a>
		</form>
	</div>
</body>
</html>