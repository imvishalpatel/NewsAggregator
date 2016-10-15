<%-- 
    Document   : newjspLogin
    Created on : 15 Oct, 2016, 8:10:59 PM
    Author     : vishal
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${requestScope.success ne null}">
            <p>${requestScope.success}</p>
        </c:if>
        <h1>Login Page</h1>
    </body>
</html>
