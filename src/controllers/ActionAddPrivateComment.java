/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mongodb.MongoClient;
import dao.PrivatePostDAO;
import dao.PublicPostDAO;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PrivateDiscussion;

/**
 *
 * @author Gaurav
 */
public class ActionAddPrivateComment implements Action {
     private String viewPage = "PrivateDetailedDiscussion.jsp";

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
             
                PrivateDiscussion pb = null;
                
               MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");

                PrivatePostDAO privatePostDao = new PrivatePostDAO(mongo);
                pb=privatePostDao.getPrivateDiscussion(postid);
                
                pb.addComments(comment, userName);
                privatePostDao.updatePrivateDiscussion(pb);
            
request.setAttribute("postid",postid);
             
            
        } catch (Exception e) {
            System.out.println(e.getMessage()+"Action");
        }
        
        return viewPage;
    }

}

