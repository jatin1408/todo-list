<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="academy.learnprogramming.util.Mappings" %>
<html>
<head>
    <title>Todo Items</title>
</head>
<body>
<div align="center">
   <c:url var="itemsLink" value="${Mappings.items}"/>
    <h2><a href="${itemsLink}">Show Todo Items</a></h2>

</div>

</body>
</html>