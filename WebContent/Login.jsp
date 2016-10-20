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
        <c:if test="${requestScope.signupSucc ne null}">
            <p>Congratulations, ${requestScope.signupSucc[0]}! your registration is complete</p>
            <p>
                We have sent a mail on <span style="color:blue">${requestScope.signupSucc[1]}</span> to verify your identity.
                verify yourself and be a member of vidico community.
            </p>
        </c:if>
        <c:if test="${requestScope.confirmSucc ne null}">

            <p>Congratulations, ${requestScope.confirmSucc}! We identified that you are real!</p>
            <p style="color:blue; font-weight: bold">
                You are now member of vidico.
            </p>
        </c:if>
        <h1>Login Page</h1>
    </body>
</html>
