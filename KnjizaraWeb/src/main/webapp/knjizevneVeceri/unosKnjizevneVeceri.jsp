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
<title>Unos knjizevne veceri</title>
</head>
<body>

	<%@ include file="/sidenav.jsp"%>
	
	<div class="content">
	
		<h2>Zakazivanje knjizevne veceri: ${knjiga.naziv}</h2>

		<form:form action="/BookStore/knjizVec/saveKnjizevnoVece?idKnjiga=${knjiga.idKnjiga}" method="post"
			modelAttribute="knjizevnoVece">
			<table>
				<tr>
					<td>Mesto:</td>
					<td><form:input path="mesto" required="true" /></td>
				</tr>
				<tr>
					<td>Datum:</td>
					<td><form:input path="vreme" type="date" required="true" /></td>
				</tr>
				<tr>
					<td>Maksimalan broj posetioca:</td>
					<td><form:input path="maxBrPos" type="number" min="5" required="true" /></td>
				</tr>
				<tr>
					<td />
					<td><input type="submit" value="Sacuvaj"></td>
				</tr>
			</table>

		</form:form>


		<c:if test="${!empty kv}">
			${poruka}
		</c:if>
		<c:if test="${empty kv}">
			<div class="error">
				${poruka}
			</div>
		</c:if>
	</div>

</body>
</html>