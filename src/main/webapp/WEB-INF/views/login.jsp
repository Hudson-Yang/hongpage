<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>
	
	<div class="center-block">
		<div class="container">
			<div class="col-lg-4"></div>
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
			<div class="col-lg-4"></div>
		</div>
	</div>

	
</body>
</html>