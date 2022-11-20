<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Kirill
  Date: 20.11.2022
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form:form action="saveUser" modelAttribute="saveOrUpdateUser">

    <form:hidden path="id"/>

    Name<form:input path="name"/>
    <br><br>
    Surname<form:input path="surname"/>
    <br><br>
    Age<form:input path="age"/>
    <br><br>
    Job<form:input path="job"/>
    <br><br>
    <input type="submit" value="OK">

</form:form>




</body>
</html>
