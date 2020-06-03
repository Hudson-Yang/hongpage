<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<script src="resources/js/jquery-1.11.0.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script>

	function bbsUpdate(){
		$(".frm").attr("action", "updateAction.do").submit();
	}
	
	function bbsBack(){
		history.go(-2);
	}

</script>

<title>Insert asd here</title>
</head>
<body>
	<div class="container-fluid">
		<form class="content" action="updateAction.do" method="post">
			<table class="table table-bordered">
				<c:forEach items="${bList}" var="row">
				<tr>
					<th>글번호</th>
					<td colspan="3" name="bno">${row.bno}</td>
					<input type="hidden" name="bno" value="${row.bno}" id="no"/>
				</tr>
				<tr>
					<th>작성자</th>
					<td name="writer">${row.writer}</td>
					<input type="hidden" name="writer" value="${row.writer}"/>

				</tr>
				<tr>
					<th>제목</th>
					<td colspan="3">
					<input type="text" class="form-control" id="subject" name="subject" value="${row.subject}"></td>
				</tr>
				<tr>
					<td colspan="4">
	            			<textarea name="content" id="editor1" rows="10" cols="80">
	                			${row.content}
	            			</textarea>
					</td>
				</tr>
				</c:forEach>
				<tr align="center">
					<td  colspan="4">
						<button type="submit" class="btn btn-default" onclick="bbsUpdate()">수정</button>
						<button type="button" class="btn btn-default" onclick="bbsBack()">취소</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>