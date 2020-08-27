<%@page import="com.example.MemberLogin" %>
<%@ page import="com.example.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%Member mem = (Member) session.getAttribute("mem");%>
    <meta charset="UTF-8">
    <title>개인정보 수정</title>
    <STYLE TYPE="text/css">
        <!--
        BODY {background-image: url('board2.jpg'); background-repeat: no-repeat; background-size: 2000px 1750px}
        .title {text-align: center; font-family: sans-serif; color: brown}
        table#table1 {width: 400px; height: 400px; position: relative; left: 400px; top: 10px}
        input#text3 {background-color: black; color: white; width: 100px; height: 50px; position: relative; left: 520px; top: 110px}
        input#text4 {background-color: black; color: white; width: 100px; height: 50px; position: relative; left: 620px; top: 110px}
        -->
    </STYLE>
    <script type = "text/javascript">
        function validate(){
            var re = /^[a-zA-Z0-9]{4,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식
            var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
            // 이메일이 적합한지 검사할 정규식

            var email = document.getElementById("EMAIL");
            var oldpwd = document.getElementById("oldpwd");
            var pw = document.getElementById("pwd");
            var pwd2 = document.getElementById("pwd2");
            var nickname = document.getElementById("nickname");



            if(email.value==null) {
                alert("이메일을 입력해 주세요");
                email.focus();
                return false;
            }

            if(!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
                return false;
            }

            if(oldpwd.value==null) {
                alert("기존 비밀번호를 입력해 주세요");
                oldpwd.focus();
                return false;
            }

            if(pw.value==null) {
                alert("비밀번호를 입력해 주세요");
                pw.focus();
                return false;
            }

            if(oldpwd.value==pw.value) {
                alert("기존과 같은 비밀번호");
                pw.focus();
                return false;
            }


            if(!check(re,pw,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력")) {
                return false;
            }

            if(pwd2.value==null) {
                alert("비밀번호를 입력해 주세요");
                pwd2.focus();
                return false;
            }


            if(pw.value != pwd2.value) {
                alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
                pwd2.value = "";
                pwd2.focus();
                return false;
            }


            if(nickname.value=="") {
                alert("닉네임을 입력해 주세요");
                nickname.focus();
                return false;
            }
            return true;
        }



        function check(re, what, message) {
            if(re.test(what.value)) {
                return true;
            }
            alert(message);
            what.value = "";
            what.focus();
            return false;
        }


    </script>

</head>
<body>
<h1 class="title">회원정보 수정</h1>>
    <form name="join" onsubmit="return validate();" action="editaccount2"  method="post">
    <table id="table1">
        <tr>
            <td><strong>아이디</strong></td>
            <td><Input Type = "Text" value="<%=mem.getEmail()%>" id = "EMAIL" Name = "EMAIL" readonly> <BR></td>
        </tr>
        <tr>
            <td><strong>현재 비밀번호</strong></td>
            <td><Input Type = "PassWord" id = "oldpwd" Name = "oldpwd"> <BR></td>
        </tr>
        <tr>
            <td><strong>새 비밀번호</strong></td>
            <td><Input Type = "PassWord" id = "pwd" Name = "pwd"> <BR></td>
        </tr>
        <tr>
            <td><strong>비밀번호 확인</strong></td>
            <td><Input Type = "PassWord" id = "pwd2" Name = "pwd2"> <BR></td>
        </tr>
        <tr>
            <td><strong>닉네임</strong></td>
            <td><Input Type = "Text" id = "nickname" Name = "nickname"> <BR></td>
        </tr>
    </table>
    <Input Type = "Submit" Value = "제출합니다" id="text3">
    <Input type = "button" value="뒤로가기" id="text4" onClick="history.back();">
</form>
</body>
</html>