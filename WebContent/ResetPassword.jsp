<%-- 
    Document   : ResetPassword
    Created on : 17 Nov, 2016, 11:31:31 PM
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
        <h1> Reset Password Page</h1>

    <c:if test="${requestScope.resetPasswordErr ne null}">
        <c:forEach items="${resetPasswordErr}" var="current">
            <div class="alert alert-danger fade in">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>Required!</strong>
                <c:out value="${current}" />
            </div>
        </c:forEach>
        <hr>
    </c:if>

    <form action="Controller" method="post">
        <input type="hidden" name="_key" value="${requestScope.key}" />
        <input type="hidden" name="action" value="addnewpassword" />
        
        <div class="top-margin">
            <label>New Password <span class="text-danger">*</span></label>
            <input type="text" class="form-control" name="newpassword">
        </div>
        <div class="top-margin">
            <label>Confirm New Password <span class="text-danger">*</span></label>
            <input type="text" class="form-control" name="confirmnewpassword">
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
