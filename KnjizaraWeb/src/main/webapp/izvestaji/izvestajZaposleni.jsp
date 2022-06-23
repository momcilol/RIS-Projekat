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
<title>Izvestaj zaposleni</title>
</head>
<body>

	<%@ include file="/sidenav.jsp"%>

	<div class="content">

		<h2>Izvestaj svih zaposlenih</h2>

		<form action="/BookStore/report/getSviZaposleniReport.pdf"
			method="get">
			<table>
				<tr>
					<td>Sortiraj po radnom stazu <br> 
					<input type="checkbox" name="sCheck" value="t"></td>
					<td>
					<input type="radio" id="stazASC" name="radniStaz" value="ASC" checked> 
					<label for="stazASC">Rastuce</label> 
					<br> 
					<input type="radio" id="stazDESC" name="radniStaz" value="DESC"> 
					<label for="stazDESC">Opadajuce</label></td>
				</tr>
				<tr>
					<td>Sortiraj po datumu zaposlenja<br> 
					<input type="checkbox" name="dCheck" value="t">
					</td>
					<td>
					<input type="radio" id="datumASC" name="datumZaposlenja" value="ASC" checked> 
					<label for="datumASC">Od najranijeg do najkasnijeg</label> 
					<br> 
					<input type="radio" id="datumDESC" name="datumZaposlenja" value="DESC"> 
					<label for="datumDESC">Od najkasnijeg do najranijeg</label></td>
				</tr>
				<tr>
					<td>Sortiraj po plati<br> 
					<input type="checkbox" name="pCheck" value="t"></td>
					<td>
					<input type="radio" id="plataASC" name="plata" value="ASC" checked> 
					<label for="plataASC">Rastuce</label>
					<br> 
					<input type="radio" id="plataDESC" name="plata" value="DESC"> 
					<label for="plataDESC">Opadajuce</label></td>
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