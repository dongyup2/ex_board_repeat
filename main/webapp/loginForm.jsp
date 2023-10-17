<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Login Page</h1>
<hr>
<form action="login" method="POST">
	<input type="text" name="id" placeholder="Input id.."><br>
	<input type="password" name="pw" placeholder="Input pw.."><br>
	<input type="submit" value="Login">&nbsp;&nbsp;
	<input type="button" value="회원가입" onclick="moveMemverForm()">
</form>
<script>
	function moveMemberForm(){
		location.href="memberRegist";
	}
</script>
</body>
</html>