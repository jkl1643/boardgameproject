<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
    <title>고객문의 작성</title>
</head>
<body>




<form:form action="customwriteok" modelAttribute="customrequest">
    <p>
        <label>제목<br>
        <form:input path="title" />
    
        </label>
    </p>
    <p>
        <label>
        <form:input path="content" />
  
        </label>
    </p>
    <p>
        <label>작성자<br>
        <form:input path="name" value = "${mem.getNickname()}"/>
        </label>
    </p>
    
    <div style="display:none;">
    <p>
    	<label>이메일<br>
    	  <form:input path="email" value = "${idid}" />
    	 </label>
	 </p>
	 </div>
    <input type="submit" value="저장하기">
    </form:form>
    

 	<a href="<c:url value="/custom"/>">돌아가기 </a>

	
 	
</body>
</html>