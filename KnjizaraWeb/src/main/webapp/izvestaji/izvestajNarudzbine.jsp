<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/style.css">
<meta charset="UTF-8">
<title>Izvestaj o narudzbinama</title>
</head>
<body>

	<%@ include file="/sidenav.jsp"%>

	<div class="content">

		<h2>Izvestaj o narudzbinama</h2>

		<form action="/BookStore/report/getNarudzbineKnjiga.pdf" method="get">
			<table>
				<tr>
					<td>Datum od:</td>
					<td><input type="date" name="datumOd" required></td>
				</tr>
				<tr>
					<td>Datum do:</td>
					<td><input type="date" name="datumDo" required></td>
				</tr>
				<tr>
					<td />
					<td><input type="submit" value="Generisi izvestaj"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>