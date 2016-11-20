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
        <h1></h1>
        
        <table>
            
        <%
            try{
           
            
              PublicDiscussion pb = (PublicDiscussion)request.getAttribute("pb");
          // for(int i=0;i<arpb.size();i++)    
            //{
                
                
                 //pb=arpb.get(i);
                 out.println("<tr>");
                 out.println("<td>Post id:"+ pb.getid()+"</td>");
                 out.println("<td>User id:"+ pb.getUsername()+"</td>");
                out.println("<td>Topic:"+ pb.getTopic()+"</td>");
                 out.println("<td>Content:"+ pb.getContent()+"</td>");
                 // out.println("<td>Tags:"+ pb.getTagsString()+"</td>");
               // out.println("<td>Category:"+ pb.getCategoryString()+"</td>");
                 
                 out.println("</tr>");
                 
           // }   
            }
            catch (Exception e) {
            out.println(e.getMessage()+"test page");
        }
        %>
        </table>
    </body>
</html>
