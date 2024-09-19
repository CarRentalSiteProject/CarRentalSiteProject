<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Available Cars</title>
</head>
<body>
    <h1>Available Cars</h1>
    <form action="order2" method="post">
    <p>取車日期 : ${chdate}</p><br/>
    <input type="hidden" name="chdate" value="${chdate}">
    <p>還車日期 : ${redate}</p>
    <input type="hidden" name="redate" value="${redate}">
    <table border="1">
        <thead>
            <tr>
                <th>選擇</th>
                <th>車型</th>
                <th>地點</th>
                <th>價格</th>
                <th>可容納人數</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="car" items="${cars}">
                <tr>
                    <td><input type="radio" name="selectedCar" value="${car.CarID}"></td>
                    <td>${car.CarType}</td>
                    <td>${car.C_Location}</td>
                    <td>${car.Price}</td>
                    <td>${car.PeopleNub}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <button type="submit">提交訂單</button>
</form>
</body>
</html>