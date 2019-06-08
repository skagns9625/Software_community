<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
 <html>
<head>
<script>
function writeCheck()
  {
   var form = document.writeform;
 
  if( !form.content.value )
   {
    alert( "내용을 적어주세요" );
    form.content.focus();
    return;
   }  
 
  form.submit();
  }
 </script>
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
		<input type=button class="btn btn-danger" value="삭제하기" OnClick="window.location='delete.do?num=${article.num}'">
		<input type=button class="btn btn-warning" value="수정하기" OnClick="window.location='modifyform.do?num=${article.num}'">			
		<input type=button class="btn btn-secondary" value="돌아가기" OnClick="window.location='main.jsp'">
	</div>
	</c:forEach>
	<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@댓글부분@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
	<!-- 여기는 댓글창이 보여지는곳 -->
		<table class="table table-striped table-bordered table-hover" style="text-align:center">
	<caption style="text-align:left"> <br><br> 댓글 작성</caption>
		<tr>
			<td width="250px">아이디</td>
			<td width="120px">작성일자</td>
			<td>댓글 내용</td>
		</tr>
		<c:forEach items="${commentList}" var="comments">
		
		<tr>
			<td>${comments.id}</td>
			<td>${comments.boarddate}</td>
			<td>${comments.content}
			<%-- <c:if test="${comments.id eq id}">
				<%int num=Integer.parseInt(request.getParameter("num")); %>
				<input type=button class="btn btn-danger" value="삭제하기" OnClick="window.location='commentsdelete.do?num=${comments.num}&boardnum=<%=num%>'" >
			</c:if> --%>
			</td>
		</tr>
		</c:forEach>
	</table>
	
	<c:forEach items="${articleList}" var="article">
	<form id="inform" action="/board/commentwrite.do?num=${comments.num}" method="post" style="margin-bottom:10;" onsubmit="return writeCheck();">
		<table class="table table-striped table-bordered table-hover" style="text-align:left; ">
			<tr>
				<td width="250px">${id}</td>
				<td><input type="text" name="comment" placeholder="내용을 입력하세요" size="100"></td>
				<td><input type=submit class="btn btn-success" value="등록" Onclick="javascript:writeCheck();"></td>
			</tr>	
		</table>
	</form>
	</c:forEach>
</body>
</html>