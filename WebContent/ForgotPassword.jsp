<%-- 
    Document   : ForgotPassword
    Created on : 17 Nov, 2016, 9:41:04 PM
    Author     : vishal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Forgot Password Page!</h1>

    <c:if test="${requestScope.forgotPasswordErr ne null}">
        <c:forEach items="${forgotPasswordErr}" var="current">
            <div class="alert alert-danger fade in">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>Required!</strong>
                <c:out value="${current}" />
            </div>
        </c:forEach>
        <hr>
    </c:if>
    <form action="Controller" method="post">
        <input type="hidden" name="action" value="forgotaction" />
        <div class="top-margin">
            <label>Email <span class="text-danger">*</span></label>
            <input type="text" class="form-control" name="email" value="${requestScope.email}">
        </div>
        <hr>
        <div class="row">
            <div class="col-lg-4 text-right">
                <button class="btn btn-action" type="submit">Submit</button>
            </div>
        </div>
    </form>
</body>
</html>
