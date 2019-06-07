<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
 <html>
<head>
<title>게시판</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
	<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@내용@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
<body>
	<div style="text-align:right">
	사용자ID : ${id} <input type=button class="btn btn-info" value="로그아웃" OnClick="window.location='logout.do'">
	</div>
	<c:forEach items="${articleList}" var="article">
	<table class="table table-striped table-bordered table-hover" style="text-align:center">
		<caption style="text-align:center">게시판 내용</caption>
		<tr>
			<td>제목</td>
			<td style="text-align:left">${article.subject}</td>			
		</tr>
		<tr>
			<td>작성자</td>
			<td style="text-align:left">${article.id}</td>						
		</tr>
		<tr>
			<td>카테고리</td>
			<td style="text-align:left">${article.category}</td>						
		</tr>
		<tr>
			<td>작성일자</td>
			<td style="text-align:left">${article.boarddate}</td>						
		</tr>
		<tr>
			<td>조회수</td>
			<td style="text-align:left">${article.score}</td>						
		</tr>	
		<tr>
			<td>내용</td>
			<td style="text-align:left">${article.content}</td>						
		</tr>
		
	</table>
	<div style="text-align:right">
		<input type=button class="btn btn-danger" value="댓글달기" OnClick="window.location='delete.do?num=${article.num}'">
		<input type=button class="btn btn-danger" value="삭제하기" OnClick="window.location='delete.do?num=${article.num}'">
		<input type=button class="btn btn-warning" value="수정하기" OnClick="window.location='modifyform.do?num=${article.num}'">			
		<input type=button class="btn btn-secondary" value="돌아가기" OnClick="window.location='main.jsp'">
	</div>
	<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@댓글부분@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
	</c:forEach>		
		<table class="table table-striped table-bordered table-hover" style="text-align:center">
	<caption style="text-align:left"> <br><br> 댓글 작성</caption>
		<tr>
			<td width="250px">아이디 (이메일)</td>
			<td width="120px">작성일자</td>
			<td>댓글 내용</td>
		</tr>
		<c:forEach items="${commentsList}" var="comments">
		
		<tr>
			<td>${comments.id} (${comments.email})</td>
			<td>${comments.date}</td>
			<td style="text-align-last:justify;">${comments.comment}
			<c:if test="${comments.id eq id}">
				<%int num=Integer.parseInt(request.getParameter("num")); %>
				<input type=button class="btn btn-danger" value="삭제하기" OnClick="window.location='commentsdelete.do?num=${comments.num}&boardnum=<%=num%>'" >
			</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	
	<c:forEach items="${articleList}" var="article">
	<form id="inform" action="/board/commentsWrite.do?num=${article.num}" method="post" style="margin-bottom:10;" onsubmit="return writeCheck();">
		<table class="table table-striped table-bordered table-hover" style="text-align:left; ">
			<tr>
				<td width="250px">${id} (${email})</td>
				<td><input type="text" name="comment" placeholder="내용을 입력하세요" size="100"></td>
				<td><input type=submit class="btn btn-success" value="등록" Onclick="javascript:writeCheck();"></td>
			</tr>	
		</table>
	</form>
	</c:forEach>
</body>
</html>