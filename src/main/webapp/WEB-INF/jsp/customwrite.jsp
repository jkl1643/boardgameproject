<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
    <title>제목</title>
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
        <form:input path="name"/>
        </label>
    </p>
    
    <p>
    	<label>이메일<br>
    	  <form:input path="email"  />
    	 </label>
	 </p>
    <input type="submit" value="저장하기">
    </form:form>
	
 

	
 	
</body>
</html>