<%-- 
    Document   : Signup
    Created on : 15 Oct, 2016, 6:04:54 PM
    Author     : vishal
--%>


<%@page import="java.util.LinkedList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up Page</title>
    </head>
    <body>
        <%-- Sing up user --%>

        <c:if test="${requestScope.error ne null}">
            <c:forEach items="${error}" var="current">
                <p><c:out value="${current}" /></p>
            </c:forEach>
        </c:if>
        <form action='Controller?action=adduser' method="post">
            <table>
                <tr>
                    <td> Username:</td>
                    <td><input type="text" name="username" value="${requestScope.username}"></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td>Firstname:</td>
                    <td><input type="text" name="firstname" value="${requestScope.firstname}"></td>
                </tr>
                <tr>
                    <td>Lastname:</td>
                    <td><input type="text" name="lastname" value="${requestScope.lastname}"></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" name="email" value="${requestScope.email}"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Sign up"></td>
                </tr>
            </table>

        </form>

    </body>
</html>

