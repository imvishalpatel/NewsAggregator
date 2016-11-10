<%-- 
    Document   : newjspLogin
    Created on : 15 Oct, 2016, 8:10:59 PM
    Author     : vishal
--%>

<%@page import="model.PublicDiscussion"%>
<%@page import="java.util.ArrayList"%>
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
        <c:if test="${requestScope.confirmAlready ne null}">

            <p>You are already verified!</p>
            <p style="color:blue; font-weight: bold">
                Login in vidico and enjoy the discussion.
            </p>
        </c:if>
            
        <h1></h1>
        
        <table>
            
        <%
            try{
             PublicDiscussion pb=new PublicDiscussion(); 
            
            ArrayList<PublicDiscussion> arpb = (ArrayList<PublicDiscussion>)request.getAttribute("arpb");
           for(int i=0;i<arpb.size();i++)    
            {
                
                
                 pb=arpb.get(i);
                 out.println("<tr>");
                 out.println("<td>User id:"+ pb.getUsertId()+"</td>");
                out.println("<td>Topic:"+ pb.getTopic()+"</td>");
                 out.println("<td>Content:"+ pb.getContent()+"</td>");
                  out.println("<td>Tags:"+ pb.getTagsString()+"</td>");
                out.println("<td>Category:"+ pb.getCategoryString()+"</td>");
                 
                 out.println("</tr>");
                 
            }   
            }
            catch (Exception e) {
            out.println(e.getMessage()+"test page");
        }
        %>
        </table>
    </body>
</html>
