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
<title>Omiljene knjige</title>
</head>
<body>

	<%@ include file="/sidenav.jsp"%>

	<div class="content">

		<h2>Omiljene knjige</h2>

		<table>
			<c:forEach items="${omkn}" var="k">
				<tr>
					<td>
						<div class="box">
							<div class="imgBx">
								<img src="/BookStore/pretraga/getKnjigaPhoto/${k.idKnjiga}">
							</div>
							<div class="con">
								<h3>${k.naziv }</h3>
								<p>
									${k.pisac.fullName}<br> ${k.izdavac}<br>
									${k.godinaIzdanja}<br> ${k.averageOcena}
								</p>
								<a class="button good" type="button"
									href="/BookStore/korisnik/prikaziDetalje?idKnjiga=${k.idKnjiga}">Prikazi
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
	</div>

</body>
</html>