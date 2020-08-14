<%@ page contentType="text/html; charset=utf-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>${custom.title}</title>
</head>
<body>
    <p>제목: ${custom.title}</p>
    <p>내용: ${custom.content}</p>
    <p>작성자: ${custom.name}</p>
    <p>기록날짜: ${custom.registerDateTime}  </p>
                       
      
	 <c:set var="email" value="${custom.email}" />
	 
    <c:if test="${mem.getEmail() eq email}">
    <p>
        <a href="<c:url value="/delete/${custom.count}" />">글삭제</a>
        <a href="<c:url value="/customchange/${custom.count}" />">글수정</a>
       
    </p>
	</c:if>
	<p>
		<a href="<c:url value="/custom" />" > 돌아가기</a>
     </p>  
                      
                   
          
                                  
</body>
</html>