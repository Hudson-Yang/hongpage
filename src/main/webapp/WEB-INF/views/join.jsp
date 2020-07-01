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
<title>회원가입</title>
</head>
<body>
	<div class="center-block">
		<div class="container">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<div class="jumbotron">
					<form method="post" action="joinAction.do">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="ex)abc@gmail.com" name="useremail" maxlength="20">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="비밀번호" name="userpw" maxlength="20">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="닉네임" name="username" maxlength="20">
						</div>
						<input type="submit" class="btn form-control" value="회원가입" >
					</form>
				</div>
			</div>
			<div class="col-lg-4"></div>
		</div>
	</div>
</body>
</html>