<%@ page import = "java.util.*"%>
<%@ page import ="java.text.*"%>
<%@ page 
language= "java" 
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>XD</title>
</head>
<body>
<!--
<h2>Hello World! What up!!!!</h2>

<%
out.println("WHATUP!!!!!!!!!!!!!!!"+"<br>");
String name ="Jsp";
Date dt = new Date();
out.println("WHATUP "+name+"<br>");
out.println("TOday"+dt.toString()+"<br>");
out.println("中文"+"<br>"+"<h1>WTF</h1>"+"<br>");

%>
-->
<!--相對路徑-->
<a href="./requestDemo.jsp">requestDemo1</a>
<br>
<a href="./requestDemo.jsp?username=RMF">requestDemo2</a>
<br>
<!--絕對路徑-->
<a href="/n-jsp/requestDemo.jsp">requestDemo3</a>
<form method="get" action="payment">
<div>訂單編號 :<input type="text" name="tno"></div>
<div>訂單金額 :<input type="text" name="total"></div>
<button type="submit">模擬金流交易></button>
<span>你好</span>
</body>
