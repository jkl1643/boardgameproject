<%@page import="javax.tools.DocumentationTool.Location" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>메인</title>
</head>
<body>
${userid}님 로그인 되었습니다.<BR>
<table>
    <tr>
        <td>
            <form action="delaccount" method="post">
                <Input Type="Submit" Value="계정삭제 ▶">
            </form>
        </td>
    </tr>
    <tr>

        <td>
            <form action="editaccount" method="post">
                <Input Type="Submit" Value="정보수정 ▶">
            </form>
        </td>
        <td>
            <form action="login" method="post">
                <Input Type="Submit" Value="로그아웃 ▶">
            </form>
        </td>

    </tr>
    <tr>

    </tr>
	<%--<form action="main" method="post">
		<tr>
			<td>날 짜</td>
			<td><input type="date" name="DATE"/></td>
		</tr>
		<tr>
            <td>메 모</td>
            <td><Input Type="Text" Name="MEMO" style="width:80px"></td>
        </tr>
        <tr>
            <td>이미지</td>
            <td><input type="file" name="IMAGE"></td>
        </tr>
        <tr>
            <td><Input Type="Submit" Value="메모등록"> <BR></td>
            <td><c:forEach var="memo" items="${memos}" varStatus="status">
                <li> ${status.index+1}. ${memo.year}년 ${memo.month}월 ${memo.day}일 [ 메모 : ${memo.memo} ] [ 이미지
                    : ${memo.saveImagePath} ]
                </li>
            </c:forEach></td>
        </tr>
	</form>--%>
        <%
            boolean select_date = (boolean) request.getAttribute("select_date");
            boolean insert_memo = (boolean) request.getAttribute("insert_memo");
            boolean delmemo = (boolean) request.getAttribute("delmemo");
            boolean editaccount = (boolean) request.getAttribute("editaccount");
            boolean chkpwd = (boolean) request.getAttribute("chkpwd");
            boolean currentpwd = (boolean) request.getAttribute("currentpwd");
            boolean created_memo = (boolean) request.getAttribute("created_memo");
            if (select_date == true) {
        %><BR>날짜를 선택해주십시오.<BR>
        <%} else if (insert_memo == true) {%>
        <BR>메모를 입력해주십시오.<BR>
        <%
            }
            if (delmemo == true) {
        %>
        <BR>메모가 삭제되었습니다.<BR>
        <%}%>
        <%if (editaccount == true) {%>
        <BR>정보를 수정했습니다.<BR>
        <%}%>
        <%if (chkpwd == true) {%>
        <BR>확인 비밀번호가 일치하지 않습니다.<BR>
        <%}%>
        <%if (currentpwd == true) {%>
        <BR>현재 비밀번호가 일치하지 않습니다.<BR>
        <%}%>
        <%if (created_memo == true) {%>
        <BR>메모가 등록되었습니다.<BR>
        <%}%>
        <BR>



    <script>
        document.getElementById('currentDatetime').value = new Date().toISOString().slice(0, -1);
    </script>
</table>
</body>
</html>