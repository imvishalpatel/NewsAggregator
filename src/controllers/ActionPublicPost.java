/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import com.mongodb.MongoClient;
import dao.PublicPostDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PublicDiscussionModel;
import java.util.LinkedList;
/**
 *
 * @author BHAVESH GOYAL
 */
public class ActionPublicPost implements Action {
    
      private String viewPage = "TestPage.jsp";

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userid");
        String topic = request.getParameter("topic");
        String content = request.getParameter("content");
        LinkedList<String> errors = new LinkedList<>();
        try {
            if (topic== null || topic.equals("")) {
                errors.add("Topic Should be Seleted");
            }
            if (content== null || content.equals("")) {
                errors.add("Content cannot be blank");
            }
            if (errors.size() > 0) {
                request.setAttribute("error", errors);
                viewPage = "PublicPost.jsp";
            } else {
                PublicDiscussionModel pb = new PublicDiscussionModel();
               pb.setPost(userId, topic, content);

                MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");

                PublicPostDAO publicPostDao = new PublicPostDAO(mongo);
                publicPostDao.newPost(pb);
              //  System.out.println("Publicpost action id=" + pb.getPostId());
                  ArrayList<PublicDiscussionModel> arpb = new ArrayList<PublicDiscussionModel>();
                arpb=publicPostDao.showPost();
                request.setAttribute("arpb",arpb);
            
            
            }

             
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return viewPage;
    }

}
