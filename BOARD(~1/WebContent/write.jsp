<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<p>${article}</p>
<script>

   self.window.alert("입력한 글을 저장하였습니다.");
   self.window.alert("${article}");
   <c:if test="${article eq 1}">
   
	   location.href="list1.do";
  	</c:if>
  	 <c:if test="${article eq 2}">

	   location.href="list2.do";  
	</c:if>
	<c:if test="${article eq 3}">

	   location.href="list3.do";  
	</c:if>
	<c:if test="${article eq 4}">

	   location.href="list4.do";  
	</c:if>
	<c:if test="${article eq 5}">

	   location.href="list5.do";  
	</c:if>
	
</script>
</body>
</html>