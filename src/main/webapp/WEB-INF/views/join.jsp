<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>      
<!--request.setCharacterEncoding("UTF-8");--> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="resources/js/jquery-1.11.0.min.js"></script>
<script src="resources/js/join.js"></script>
<style>
.logo{
text-align: center;
font-size: xx-large;}
</style>
<title>회원가입</title>
</head>
<body>
<div class="logo"><a href="main.do">HongPage</a><hr></div>
	<div class="center-block">
		<div class="container">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<div class="jumbotron" id="uh">
					<form method="post" action="joinAction.do">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="이메일 ex)abc@gmail.com" id="useremail" name="useremail" maxlength="20">
							<div id="emailuh"></div>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="비밀번호(영어 대소문자,숫자 8~16자 이내)" id="userpw" name="userpw" maxlength="16">
							<div id="pwuh"></div>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="비밀번호 확인" id="userpwchk" name="userpwchk" maxlength="16">
							<div id="pwchkuh"></div>	
						</div>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="닉네임" id="username" name="username" maxlength="20">
							<div id="nameuh"></div>
						</div>
						<button type="submit" disabled="disabled" class="btn form-control" id="joinsubmit">회원가입</button>
					</form>
				</div>
			</div>
			<div class="col-lg-4"></div>
		</div>
	</div>
</body>
</html>