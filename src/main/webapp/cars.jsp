<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,vo.CarVo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Car List</h1>
   <%
List<CarVo> cars =(List<CarVo>)request.getAttribute("cars");
if(cars!=null){
%>
<table border=1>
<tr>
<td>id</td><td>type</td><td>price</td><td>date</td><td>peopleNub</td><td>status</td><td>location</td>
</tr>
<% 
	int total = 0;
	for(int x=0;x<cars.size();x++){
		CarVo vo = cars.get(x);
%>
<tr>
	<td><%= vo.getCarID() %></td>
	<td><%= vo.getCarType() %></td>
	<td><%= vo.getPrice()%></td>
	<td><%= vo.getDate()%></td>
	<td><%= vo.getPeopleNub()%></td>
	<td><%= vo.getCar_Status()%></td>
	<td><%= vo.getC_Location()%></td>
</tr>
<%} %>
<%} %>
</table>

<a href="y">更新</a>
</body>
</html>