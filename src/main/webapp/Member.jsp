<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, vo.MemberVo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員資料</title>
<%
if (request.getAttribute("members") == null) {
    response.sendRedirect("member");
}
%>
</head>
<body>
<h2>Member Table</h2>
<%
List<MemberVo> members = (List<MemberVo>) request.getAttribute("members");
if (members != null) {
    if (members.isEmpty()) {
%>
        <p>No members found.</p>
<%
    } else {
%>
<table>
    <thead>
        <tr>
            <th>MemberID</th>
            <th>Age</th>
            <th>Gender</th>
            <th>Name</th>
            <th>Email</th>
            <th>LicenseNub</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Login</th>
        </tr>
    </thead>
    <tbody>
        <%
        for (MemberVo member : members) {
        %>
        <tr>
            <td><%= member.getMemberID() %></td>
            <td><%= member.getAge() %></td>
            <td><%= member.getGender() %></td>
            <td><%= member.getName() %></td>
            <td><%= member.getEmail() %></td>
            <td><%= member.getLicenseNub() %></td>
            <td><%= member.getAddress() %></td>
            <td><%= member.getPhone() %></td>
            <td><%= member.getLogin() ? "Yes" : "No" %></td>
        </tr>
        <% } %>
    </tbody>
</table>
<% 
    }
} else { 
%>
<p>No members found.</p>
<% 
} 
%>
</body>
</html>
