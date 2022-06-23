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
<title>Svi izvestaji</title>
</head>
<body>

	<%@ include file="/sidenav.jsp"%>

	<div class="content">

		<h2>Izvestaji</h2>

		<table id="employees">
			<tr>
				<td>Svi zaposleni</td>
				<td><a class="button good" style="width: 100%"
					href="/BookStore/report/sviZaposleni">Izaberi</a></td>
			</tr>
			<tr>
				<td>Narudzbine knjiga u periodu</td>
				<td><a class="button good" style="width: 100%"
					href="/BookStore/report/narudzbine">Izaberi</a></td>
			</tr>
			<tr>
				<td>Najbolji pisci iz kategorije</td>
				<td><a class="button good" style="width: 100%"
					href="/BookStore/report/narudzbine">Izaberi</a></td>
			</tr>
		</table>

	</div>
</body>
</html>