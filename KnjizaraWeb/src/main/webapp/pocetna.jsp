<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/style.css">
<meta charset="UTF-8">
<title>Dobrodosli</title>
</head>
<body>

	<%@ include file="/sidenav.jsp"%>

	<div class="center">
		<h1>
			Dobrodosli
			<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal.username" />
			</sec:authorize>
		</h1>
	</div>

</body>
</html>