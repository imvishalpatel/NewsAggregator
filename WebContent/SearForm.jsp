<%-- 
    Document   : SearForm
    Created on : 23 Nov, 2016, 1:03:31 PM
    Author     : pritesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sewwarec form!</h1>
        <form action="Controller" method="post">
            <input type="hidden" name="action" value="search" />
            <input type="text" name="searchvalue" />
            <input type="submit" value="Search" />
        </form>
    </body>
</html>
