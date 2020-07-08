<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<style>.logo{
text-align: center;
font-size: xx-large;}
</style>
<title>로그인</title>
</head>
<body>
	<div class="logo"><a href="main.do">HongPage</a><hr></div>
	<div class="center-block">
		<div class="container">
			<div class="col-lg-4">
				<div class="jumbotron" style="padding-top: 20px;">
					<form method="post" action="loginAction.do">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="이메일" name="useremail" maxlength="20" style="margin-top: 20px;">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="비밀번호" name="userpw" maxlength="20">
						</div>
						<input type="submit" class="btn form-control" value="로그인" style="background-color: #585858; color: white;">
					</form>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>