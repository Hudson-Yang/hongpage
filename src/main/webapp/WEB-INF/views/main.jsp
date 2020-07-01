<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ page session="true" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<style>
.flex-container{ width: 100%; height: 100vh; text-align: center; display: -webkit-box; display: -ms-flexbox; 
display: flex; -webkit-box-align: center; -ms-flex-align: center; align-items: center; -webkit-box-pack: center; 
-ms-flex-pack: center; justify-content: center; }
.center{
text-align: center;}
</style>
<title>메인</title>
</head>
<body>
	<div class="center">
		<h1>HongPage</h1>
		<hr>
	</div>
	<div class="center">
		<c:set var="uvo" value="${sessionScope.loginUser}"/>
		<c:if test="${empty sessionScope.loginUser}">
			<a href="login.do">Sign In</a>
			|
			<a href="join.do">Sign Up</a>
		</c:if>
		<c:if test="${null ne sessionScope.loginUser}">
			<a href="#">${sessionScope.loginUser.username}님</a>
			|
			<a href="logoutAction.do"> Log Out</a>
		</c:if>
		|
		<a href="bbsList1.do?curpage=1">Board</a>
	</div>
	
</body>
</html>