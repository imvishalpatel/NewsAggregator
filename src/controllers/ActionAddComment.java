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
public class ActionAddComment implements Action {
     private String viewPage = "PublicDetailedDiscussion.jsp";

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        String userName =request.getParameter("username");
        String comment = request.getParameter("comment");
        
        String postid = request.getParameter("postid");
        
//        String[] category = new String[]; 
//        if(checkBox != null){
//        for(int i=0; i<checkBox.length; i++){
//           
//            category[i]=checkBox[i];
//        }
        //}
        
        
        LinkedList<String> errors = new LinkedList<>();
        try {
             
                PublicDiscussion pb = null;
                
               MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");

                PublicPostDAO publicPostDao = new PublicPostDAO(mongo);
                pb=publicPostDao.getPublicDiscussion(postid);
                pb.addComments(comment, userName);
                publicPostDao.updatePublicDiscussion(pb);
            
request.setAttribute("postid",postid);
             
            
        } catch (Exception e) {
            System.out.println(e.getMessage()+"Action");
        }
        
        return viewPage;
    }

}
