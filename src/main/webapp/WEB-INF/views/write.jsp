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

	$(document).ready(function(){
		$('#date').val(date());
		console.log('date');
		
	});

	function bbsWrite(){
		$(".frm").attr("action", "writeAction.do").submit();
	}
	
	function bbsBack(){
		history.back();
	}
	
	function date(){
		var date = new Date();
		var year = date.getFullYear();
		var month = (date.getMonth()+1);
		month = month >= 10 ? month : '0'+month;
		var clockdate = date.getDate();
		clockdate = clockdate >= 10 ? clockdate : '0'+clockdate;
		var hours = date.getHours();
		var minutes = date.getMinutes();
		var seconds = date.getSeconds(); 
		var sigan = year+"-"+month+"-"+clockdate+" "+hours+":"+minutes+":"+seconds;
		
		return sigan;
	}
	
</script>

<title>Insert title here</title>
</head>
<body>

	<div class="container-fluid">
		<table class="table table-bordered">
			<form class="frm" method="post" enctype="multipart/form-data">
			<tr>
				<th class="col-md-1" style="text-align: center;">작성자</th>
				<td class="col-md-11"><input type="text" class="form-control" id="writer" name="writer" placeholder="작성자를 입력해주세요"></td>
			</tr>
			<tr>
				<th align="center" style="text-align: center;">제목</th>
				<td colspan="3"><input type="text" class="form-control" id="subject" name="subject" placeholer="제목을 입력해주세요"></td>
			</tr>
			
			<tr>
				<td colspan="4">
            			<textarea name="content" rows="10" cols="80" style="resize:none;" placeholer="내용을 입력해주세요"></textarea>
            			 <input type="hidden" name="date" id="date" >
				</td>
			</tr>
			<tr>
				<th align="center" style="text-align: center;">파일첨부</th>
				<td colspan="3"><input type="file" class="form-control"  name="file1" multiple="multiple"></td>
			</tr>
			</form>
			<tr align="center">
				<td  colspan="4">
					<button type="button" class="btn btn-default" onclick="bbsWrite()">등록</button>
					<button type="button" class="btn btn-default" onclick="bbsBack()">취소</button>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>