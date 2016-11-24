/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mongodb.MongoClient;
import dao.PublicPostDAO;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PublicDiscussion;

/**
 *
 * @author BHAVESH GOYAL
 */
public class ActionPublicSetSpam implements Action {
     private String viewPage = "PublicDetailedDiscussion.jsp";

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        
        String postid = request.getParameter("postid");
        try {
             
                
               MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");

                PublicPostDAO publicPostDao = new PublicPostDAO(mongo);
                publicPostDao.makeSpam(postid);
                
request.setAttribute("postid",postid);
             
            
        } catch (Exception e) {
            System.out.println(e.getMessage()+"Action");
        }
        
        return viewPage;
    }

    
}
