<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List,vo.CarVo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Car Search Results</title>
</head>
<body>
<h2>搜尋結果</h2>

<%
    List<CarVo> carList = (List<CarVo>) request.getAttribute("cars");
    if (carList != null && !carList.isEmpty()) {
%>
        <table border="1">
            <tr>
                <th>車型</th>
                <th>狀態</th>
            </tr>
<%
        for (CarVo car : carList) {
%>
            <tr>
                <td><%= car.getCarType() %></td>
                <td><%= car.getCar_Status() %></td>
            </tr>
<%
        }
%>
        </table>
<%
    } else {
%>
        <p>沒有找到符合的車型。</p>
<%
    }
%>

<a href="yourFormPage.jsp">返回查詢</a>
</body>
</html>