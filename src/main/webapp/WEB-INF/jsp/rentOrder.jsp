<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>取車日期 : ${chdate}</p><br/>
    
    <p>還車日期 : ${redate}</p>
    
<p>Debug: selectedCar = ${selectedCar}</p>
<p>您選擇的車輛:</p>
<table border="1">
    <thead>
        <tr>
            <th>車型</th>
            <th>地點</th>
            <th>價格</th>
            <th>可容納人數</th>
        </tr>
    </thead>
    <tbody>
            <tr>
                <td >${selectedCar.carType}</td>
                <td>${selectedCar.c_Location}</td>
                <td >${selectedCar.price}</td>
                <td>${selectedCar.peopleNub}</td>
            </tr>     
    </tbody>
</table>
<form action="payment" method="post">
    <input type="hidden" name="carId" value="${selectedCar.carID}">
    <input type="hidden" name="chdate" value="${chdate}">
    <input type="hidden" name="redate" value="${redate}">
    <input type="submit" value="Confirm Order">
</form>
</body>
</html>