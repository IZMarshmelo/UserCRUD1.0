<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
    <head>
        <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
        <meta HTTP-EQUIV="Content-Type" content="text/html" charset="UTF-8">
    <title>Все пользователи</title>
</head>
<body>

<div th:each="user : ${users}">
    <a th:href="@{/users/{id}(id=${user.getId()})}" th:text="${user.getName()}">user</a>
</div>

</body>
</html>