<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<body>
<style>
    table {
        border: 2px solid black;
        margin: 0 auto;
    }
</style>

<div style="text-align: center;">
    <h1>Table for Users</h1>
</div>

<table>
    <tr>
        <th><h2>Name</h2></th>
        <th><h2>Surname</h2></th>
        <th><h2>Age</h2></th>
        <th><h2>Job</h2></th>
    </tr>

    <c:forEach var="user" items="${allUsers}">

        <c:url var="Update" value="/updateUser">
            <c:param name="id" value="${user.id}"/>
        </c:url>

        <c:url var="Delete" value="/deleteUser">
            <c:param name="id" value="${user.id}"/>
        </c:url>

        <tr>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.age}</td>
            <td>${user.job}</td>
            <td>
                <input type="button" value="Update"
                       onclick="window.location.href = '${Update}'"/>

            </td>

            <td>
                <input type="button" value="Delete"
                       onclick="window.location.href = '${Delete}'"/>
            </td>
        </tr>

    </c:forEach>

    <td><input type="button" value="add User" onclick="window.location.href = 'addUser'"/></td>

</table>
</body>
</html>