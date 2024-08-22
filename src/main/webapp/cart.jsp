<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="carcart" method="post">
		<p>輸入日期</p>
		<input type="date" name = "fordate" /><br>
		<P>輸入車型</P>
		<input type="text" name = "forcar" /><br>
		<table border=2 <p>車型</p>>
		
			<tr>
				<td>	Benz</td>
				<td>BMW</td>
				<td>Honda</td>
				<td>Toyota</td>
				<td>Ferrari</td>
			</tr>
		</table><br>
		<input type="submit"name="set"value="送出日期與車型"/>
	</form>
</body>
</html>