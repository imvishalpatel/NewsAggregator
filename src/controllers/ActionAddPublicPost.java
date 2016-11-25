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
public class ActionAddPublicPost implements Action{
          private String viewPage = "PublicDiscussionList.jsp";

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        String userId =request.getParameter("uname");
        String topic = request.getParameter("topic");
        
        String content = request.getParameter("content");
        String[] category = request.getParameterValues("cb");
//        String[] category = new String[]; 
//        if(checkBox != null){
//        for(int i=0; i<checkBox.length; i++){
//           
//            category[i]=checkBox[i];
//        }
        //}
        
        
        LinkedList<String> errors = new LinkedList<>();
        try {
            if (topic== null || topic.equals(" ") ||topic.length()<5 ||topic.length()>100) {
                errors.add("Topic Should be Seleted");
            }
            if (content== null || content.equals("")||content.length()<15) {
                errors.add("Content cannot be blank");
            }
            if (errors.size() > 0) {
                request.setAttribute("error", errors);
                viewPage = "PublicPost.jsp";
            } else {
                PublicDiscussion pb = new PublicDiscussion(userId, topic, content,category);
                
               MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");

                PublicPostDAO publicPostDao = new PublicPostDAO(mongo);
                publicPostDao.newPost(pb);
            }

             
            
        } catch (Exception e) {
            System.out.println(e.getMessage()+"Action");
        }
        
        return viewPage;
    }

}
