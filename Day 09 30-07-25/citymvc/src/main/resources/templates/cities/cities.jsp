<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Indian Cities</title>
</head>
<body>
    <h2>List of Cities in India</h2>
    <ul>
        <c:forEach var="city" items="${cities}">
            <li>${city}</li>
        </c:forEach>
    </ul>
</body>
</html>
