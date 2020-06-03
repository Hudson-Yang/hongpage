<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ page session="true" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>

	function bbsWrite(){
		location.href="write.do";
	}
	
	// 원하는 페이지로 이동시 검색조건, 키워드 값을 유지하기 위해 
	function list(page){
		location.href="${path}/bbsList1.do?curpage="+page+"&searchOption-${map.searchOption}"+"&keyword=${map.keyword}";
	}
	
</script>

</head>
<body>
<div><a href="main.do">HongPage</a></div>
<hr>
	<div class="container-fluid text-center">    
  		<div class="row content">
	    	<div class="col-sm-12 text-left">
	    		<div class="container">
	    	<%-- <form name="form1" method="post" action="${path}/bbsList1.do">
		<select name="searchOption">
			<!-- 검색조건을 검색처리후 결과화면에 보여주기위해  c:out 출력태그 사용, 삼항연산자 -->
			<option value="all" <c:out value="${listMap.searchOption == 'all'?'selected':''}"/> >제목+이름+제목</option>
			<option value="user_name" <c:out value="${listMap.searchOption == 'writer'?'selected':''}"/> >이름</option>
			<option value="content" <c:out value="${listMap.searchOption == 'content'?'selected':''}"/> >내용</option>
			<option value="title" <c:out value="${listMap.searchOption == 'subject'?'selected':''}"/> >제목</option>
		</select>  
		<input name="keyword" value="${listMap.keyword}">
		<input type="hidden" name="curpage" value="${listMap.boardPager.curPage}">
		<input type="submit" value="조회">
		</form> --%>
		${listMap.count}개의 게시물 
						<table class="table" id="Spreadsheet">
							<tr>
								<th class="col-md-1">#</th>
								<th class="col-md-6">Subject</th>
								<th class="col-md-3">Writer</th>
								<th class="col-md-2">Date</th>
								<th class="col-md-3">Views</th>
							</tr>
					 		<c:forEach items="${listMap.list}" var="row">
								<tr>
									<td>${row.bno}</td>
						   	        <td><a href="bbsContent.do?bno=${row.bno}">${row.subject}</a></td> 
									<td>${row.writer}</td>
									<td>${row.date}</td>
									<td>${row.viewcnt}</td>
								</tr>
							</c:forEach>
						</table>
		<tr>				
			<td colspan="5">
				<!-- 처음페이지로 이동 : 현재 페이지가 1보다 크면  [처음]하이퍼링크를 화면에 출력-->
		  		<c:if test="${listMap.boardPager.curBlock >= 1}"> 
					<a href="javascript:list('1')">[처음]</a>
				</c:if> 
				
				<!-- 이전페이지 블록으로 이동 : 현재 페이지 블럭이 1보다 크면 [이전]하이퍼링크를 화면에 출력 -->
				<c:choose>				
					<c:when test="${listMap.boardPager.curBlock > 1}">
						<a href="javascript:list('${listMap.boardPager.prevPage}')">[이전]</a>
					</c:when>
					<c:otherwise>
						<span style="color:grey">[이전]</span>
					</c:otherwise>
				</c:choose>
			 	<!-- **하나의 블럭 시작페이지부터 끝페이지까지 반복문 실행 -->
			<c:forEach var="num" begin="${listMap.boardPager.blockBegin}" end="${listMap.boardPager.blockEnd}">
					<!-- 현재페이지이면 하이퍼링크 제거 -->
					<c:choose>
						<c:when test="${num == listMap.boardPager.curPage}">
							<span style="color:red">${num}</span>&nbsp;
						</c:when>
						<c:otherwise>
							<a href="javascript:list('${num}')">${num}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>  
				
				<!-- 다음페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 [다음]하이퍼링크를 화면에 출력 -->
				<c:choose>
					<c:when test="${listMap.boardPager.curBlock < listMap.boardPager.totBlock}">
						<a href="javascript:list('${listMap.boardPager.nextPage}')">[다음]</a>
					</c:when>
					<c:otherwise>
						<span style="color:grey">[다음]</span>
					</c:otherwise>
				</c:choose>
				<!-- 끝페이지로 이동 : 현재 페이지가 전체 페이지보다 작거나 같으면 [끝]하이퍼링크를 화면에 출력 -->
				<c:if test="${listMap.boardPager.curPage <= listMap.boardPager.totPage}">
					<a href="javascript:list('${listMap.boardPager.totPage}')">[끝]</a>
				</c:if>
			</td>
		</tr>
		<br/>
		
						<div>
							<button type="button" class="btn btn-default" onclick="bbsWrite()">글쓰기</button>
						</div>
					</div>
				</div>
	    	</div>
	    </div>

			
	
</body>
</html>