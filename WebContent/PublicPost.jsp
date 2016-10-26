<%-- 
    Document   : PublicPost
    Created on : Oct 23, 2016, 4:04:30 AM
    Author     : BHAVESH GOYAL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Public Post Page</title>
    </head>
      <body>
        <%-- Sing up user --%>

        <c:if test="${requestScope.error ne null}">
            <c:forEach items="${error}" var="current">
                <p><c:out value="${current}" /></p>
            </c:forEach>
        </c:if>
        <form action='Controller?action=publicpost' method="post">
            <table>
                <tr>
                    <td> User Id:</td>
                    <td><input type="text" name="userid" value="${requestScope.userid}"></td>
                </tr>
                <tr>
                    <td>Topic:</td>
                    <td><input type="text" name="topic"></td>
                </tr>
                <tr>
                    <td>Content:</td>
                    <td><input type="text" name="content" value="${requestScope.content}"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="POST"></td>
                </tr>
            </table>

        </form>

    </body>
</html>
