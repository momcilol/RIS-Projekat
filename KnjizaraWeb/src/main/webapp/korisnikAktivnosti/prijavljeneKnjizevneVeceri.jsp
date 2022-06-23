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
<title>Prijavljene knjizevne veceri</title>
</head>
<body>

	<%@ include file="/sidenav.jsp"%>

	<div class="content">

		<h2>Prijavljene knjizevne veceri</h2>

		<table>
			<c:forEach items="${prkv}" var="kv">
				<tr>
					<td>
						<div class="box">
							<div class="imgBx">
								<img
									src="/BookStore/pretraga/getKnjigaPhoto/${kv.knjiga.idKnjiga}">
							</div>
							<div class="con">
								<h3>${kv.knjiga.naziv }</h3>
								<p>
									${kv.mesto}<br> ${kv.lepoVreme}<br>
								</p>

								<a class="button good" type="button"
									href="/BookStore/korisnik/prikaziDetaljeKV?idKnjizevnoVece=${kv.idKnjizevnoVece}">Prikazi
									detalje</a>

							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
			</c:forEach>
		</table>

		<br> <br>
	</div>

</body>
</html>