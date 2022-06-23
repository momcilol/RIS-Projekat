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
<title>Prikaz zaposlenog</title>
</head>
<body>
	<%@ include file="/sidenav.jsp"%>

	<div class="content">
	
	<h3>Prikaz zaposlenih</h3>
		<table id="employees">
			<tr>
				<td>Ime</td>
				<td>${zd.ime}</td>
			</tr>
			<tr>
				<td>Prezime</td>
				<td>${zd.prezime}</td>
			</tr>
			<tr>
				<td>Email</td>
				<td>${zd.email}</td>
			</tr>
			<tr>
				<td>Adresa</td>
				<td>${zd.adresa}</td>
			</tr>
			<tr>
				<td>Telefon</td>
				<td>${zd.telefon}</td>
			</tr>
			<tr>
				<td>Datum zaposlenja</td>
				<td>${zd.datumZaposlenja}</td>
			</tr>
			<tr>
				<td>Plata</td>
				<td>${zd.plata}</td>
			</tr>
			<tr>
				<td>Radni staz</td>
				<td>${zd.radniStaz}</td>
			</tr>
			<tr>
				<td><a class="button bad" type="button" style="width:100%"
					href="/BookStore/adminZaposleni/deleteZaposleni?idKorisnik=${zd.idKorisnik}">Obrisi
						zaposlenog</a></td>
				<td><a class="button good" type="button" style="width:100%"
					href="/BookStore/adminZaposleni/azurirajZaposlenog?idKorisnik=${zd.idKorisnik}">Azuriraj
						podatke zaposlenog</a></td>
			</tr>
		</table>
	</div>

</body>
</html>