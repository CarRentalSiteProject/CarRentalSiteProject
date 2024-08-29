<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<script>
    // JavaScript function to display an alert if the message is present
    function showError(message) {
        if (message) {
            alert(message);
        }
    }
</script>
</head>
<body onload="showError('${mes}')">
	Login...
	<form action="login" method="post">
    <input type="text" name="username" placeholder="Username" required>
    <input type="password" name="password" placeholder="Password" required>
    <button type="submit">Log in</button>
</form>
</body>
</html>
