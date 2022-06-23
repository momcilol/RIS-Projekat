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
<title>Azuriranje podataka zaposlenog</title>
</head>
<body>

	<%@ include file="/sidenav.jsp"%>

	<div class="content">

		<h2>Azuriranje podataka zaposlenog: ${zd.ime } ${zd.prezime}</h2>

		${poruka}

		<form:form modelAttribute="zd"
			action="/BookStore/adminZaposleni/updateZaposleni" method="post">
			<table>
				<tr>
					<td>Radni staz:</td>
					<td><form:input path="radniStaz" value="${zd.radniStaz}"/></td>
				</tr>
				<tr>
					<td>Plata:</td>
					<td><form:input path="plata" value="${zd.plata}" /></td>
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