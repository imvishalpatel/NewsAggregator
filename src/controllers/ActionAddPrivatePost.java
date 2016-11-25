/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Constants.GlobalConstants;
import com.mongodb.MongoClient;
import dao.PrivatePostDAO;
import dao.UserDAO;
import static java.lang.System.out;
import java.util.HashMap;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PrivateDiscussion;
import model.User;

/**
 *
 * @author Gaurav
 */
public class ActionAddPrivatePost implements Action{
    private String viewPage="InviteUsers.jsp";
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        //String userId = request.getParameter("userid");
        User u = (User)request.getSession().getAttribute(GlobalConstants.LOGGED_IN_USER);
        // tmp
        String username = u.getUsername();//"imsunil";
        String topic = request.getParameter("topic");
        String content = request.getParameter("content");
        String[] category = request.getParameterValues("cb");
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
                //viewPage = "PrivatePost.jsp";
            } else {
                PrivateDiscussion pb = new PrivateDiscussion(username, topic, content,category);
               //pb.setPost(username, topic, content);

                MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");

                PrivatePostDAO privatePostDao = new PrivatePostDAO(mongo);
               PrivateDiscussion temp =  privatePostDao.newPost(pb);
                String f = String.valueOf(temp.getid());
                out.println("AAJA " +f);
                UserDAO udao = new UserDAO(mongo);
                
                HashMap<String, User> userList=null;
                request.setAttribute("post_id",f);
                
                userList = udao.getUserList();
                
                int a = userList.size();
                
                request.setAttribute("userList",userList);
                
                
                System.out.println(a);
                request.setAttribute("pb_obj", pb);     //Discusion Object
            
            
            }

             
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return viewPage;
    }
    
    
}
