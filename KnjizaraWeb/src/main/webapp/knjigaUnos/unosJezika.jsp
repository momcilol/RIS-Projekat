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
<title>Unos novog Jezika</title>
</head>
<body>

	<%@ include file="/sidenav.jsp"%>

	<div class="content">

		<h2>Dodavanje novog jezika</h2>

		<form:form action="/BookStore/adminKatalog/saveJezik" method="post"
			modelAttribute="jezik">
			<table>
				<tr>
					<td>Naziv:</td>
					<td><form:input path="naziv" required="true" /></td>
				</tr>
				<tr>
					<td />
					<td><input type="submit" value="Sacuvaj"></td>
				</tr>
			</table>

		</form:form>


		<c:if test="${!empty j}">
			${poruka}
		</c:if>
		<c:if test="${empty j}">
			<div class="error">
				${poruka}
			</div>
		</c:if>
		
	</div>

</body>
</html>