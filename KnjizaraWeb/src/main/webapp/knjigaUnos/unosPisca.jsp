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
<title>Unos Pisca</title>
</head>
<body>
	<%@ include file="/sidenav.jsp"%>

	<div class="content">

		<h2>Dodavanje novog pisca</h2>

		<form:form action="/BookStore/adminKatalog/savePisac" method="post"
			modelAttribute="pisac">
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
					<td>Godina rodjenja:</td>
					<td><form:input path="godinaRodjenja" type="number" /></td>
				</tr>
				<tr>
					<td>Godina smrti:</td>
					<td><form:input path="godinaSmrti" type="number" /></td>
				</tr>
				<tr>
					<td />
					<td><input type="submit" value="Sacuvaj"></td>
				</tr>

			</table>

		</form:form>


		<c:if test="${!empty p}">
			${poruka}
		</c:if>
		<c:if test="${empty p}">
			<div class="error">
				${poruka}
			</div>
		</c:if>
		
	</div>
</body>
</html>