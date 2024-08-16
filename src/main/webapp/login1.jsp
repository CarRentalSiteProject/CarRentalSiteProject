<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


${mes}
	<form action="login" method="post">
		帳號:<input type="text" name="id" value="${param.id}"/><br></br>
		
		密碼:<input type="text" name="paswd" value="${param.paswd}"/><br></br>
		
		<input type="submit" name="登入" value="登入"/>
	</form>
</body>
</html>