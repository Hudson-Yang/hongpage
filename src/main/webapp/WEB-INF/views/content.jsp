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
<script src="resources/js/content.js"></script>  
<title>Insert title here</title>
</head>
<body>

	<div class="container-fluid">
		<form class="frm" method="post" action="update.do">
			<table class="table table-bordered">
				<c:forEach items="${bList}" var="row">
				<tr>
					<th>글번호</th>
					<td colspan="5">${row.bno}</td>
					<input type="hidden" name="bno" value="${row.bno}" class="bno"/>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${row.writer}</td>
					<th>작성일</th>
					<td>${row.date}</td>
					<th>조회수</th>
					<td>${row.viewcnt}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="4">${row.subject}</td>
				</tr>
				<tr>
					<td colspan="4">
	                	${row.content}
					</td>
				</tr>

				</c:forEach>
				<tr>
					<th>첨부파일</th>
                        <c:forEach items="${fList}" var="frow">
                        <td colspan="3">
                            <a href="${path}/fileDownload.do?fno=${frow.fno}" name="file">${frow.fsvname}</a>
                            (${frow.fsize} KB)
                        </td>
                        </c:forEach>                 
            	</tr>
				<tr align="center">
					<td colspan="5">
						<button type="button" class="btn btn-default js-btnUpdate">수정</button>
						<button type="button" class="btn btn-default js-btnDelete">삭제</button>
						<button type="button" class="btn btn-default js-btnList">목록으로</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>