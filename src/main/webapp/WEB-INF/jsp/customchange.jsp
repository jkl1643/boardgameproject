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

<form action="customchangeok" method="post">
<p>
        <label>번호<br>
			<Input Type = "Text" Name = "count1" value ="${custom1.count}">
		</label>
</p>



<p>
        <label>제목<br>
			<Input Type = "Text" id = "title1" value ="${custom1.title}">
		</label>
</p>

<p>
        <label>내용<br>
<Input Type = "Text" Name = "content1" value ="${custom1.content}">
		</label>
</p>

<p>
        <label>작성자<br>
<Input Type = "Text" Name = "name1" value ="${custom1.name}">
		</label>
</p>

<p>
        <label>이메일<br>
<Input Type = "Text" Name = "name1" value ="${custom1.email}">
		</label>
</p>


<input type="submit" value="수정하기">

</form>
<%--
<form action="customchangeok" method =post>
	<p>
        <label>번호<br>
        <form:input path="count1" value = "${custom.title}" />
    
        </label>
    </p>


    <p>
        <label>제목<br>
        <form:input path="title1" value = "${custom.title}" />
    
        </label>
    </p>
    <p>
        <label>
        <form:input path="content1" value = "${custom.content}" />
  
        </label>
    </p>
    <p>
        <label>작성자<br>
        <form:input path="name1" value = "${custom.name}" />
        </label>
    </p>
    
    <p>
    	<label>이메일<br>
    	  <form:input path="email1"  />
    	 </label>
	 </p>
    <input type="submit" value="수정하기">
    </form>

	
--%>

 	
</body>
</html>