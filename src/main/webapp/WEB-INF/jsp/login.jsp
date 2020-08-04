<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
</head>
<body>
<table border = 1 align=center cellspacing=3>
    <Form Action="logincheck" Method = "post">
    <tr align=center>
        <th colspan=2>회원 로그인 </th>
    </tr><tr align=center>
    <td>
            아이디 : </td>
    <td> <input Type = "Text" Name ="ID" size=20> </td>
    </tr><tr align=center>
    <td>
        비밀번호 : </td>
    <td><input Type = "PassWord" Name = "PW" size=20></td>
</tr><tr align=center>
    <td>
        <button type="button" onclick="window.open('/midtermfinal/register','_blank', 'width=400, height=300')"> 가입 </button> </td>
    <td>
        <Input Type ="Submit" Value="로그인"></td>
</tr>
    </Form>
</table>
</body>
</html>