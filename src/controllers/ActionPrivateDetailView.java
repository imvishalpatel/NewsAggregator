/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Constants.GlobalConstants;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import dao.PrivatePostDAO;
import dao.PublicPostDAO;
import dao.UserDAO;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Notifications;
import model.PrivateDiscussion;
import model.PublicDiscussion;
import model.User;
import service.UserConverter;

/**
 *
 * @author Gaurav
 */
public class ActionPrivateDetailView implements Action{
private String viewPage = "PrivateDetailedDiscussion.jsp";
String post_id;
    @Override
   public String process(HttpServletRequest request, HttpServletResponse response) {
      //          PublicDiscussion pb = new PublicDiscussion();
          //post_id=request.getParameter("postid");
        post_id=request.getParameter("post_id123");
          ArrayList<String> invited_users = new ArrayList<String>();
          User u = (User)request.getSession().getAttribute(GlobalConstants.LOGGED_IN_USER);
         // ArrayList<String> check_user = new ArrayList<String>();
        //YEH RAHAA INCOMING LIST
          String[] unames = request.getParameterValues("users");
        for(int i=0;i<unames.length;i++)
            invited_users.add(unames[i]);
        
        
//length = check.length;
         MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
         
         PrivateDiscussion pb=null;
       UserDAO udao = new UserDAO(mongo);

        //pb = (PrivateDiscussion)request.getAttribute("disc_object");
        //post_id=pb.getTopic();//pb.getid().toString();
        
        //udao.sendNotification(unames, pb);
        PrivatePostDAO privatePostDao = new PrivatePostDAO(mongo);

        pb=privatePostDao.showDetailPost(post_id);
        
        
     User temp_user = new User();
     String uname="";
     int size = unames.length;
     for(int i =0;i<size;i++){
         uname = unames[i];
         try {
             temp_user = udao.searchUser(uname);
             //temp_user = UserConverter.toUsers(temp_user);
         } catch (Exception ex) {
             Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
       
         Notifications notification_obj = new Notifications();
         ArrayList<Notifications> notification_list = null;
         notification_list = temp_user.getNotification();
         
         if(notification_list==null)
             notification_list = new ArrayList<Notifications>();
         
         notification_obj.setIsSeen(false);
         notification_obj.setReference(post_id);
         notification_obj.setText("You have been invited by "+ pb.getUsername()+" for Private Discussion on "+ pb.getTopic());
         notification_obj.setType(3);
         notification_list.add(notification_obj);
         temp_user.setNotification(notification_list);
         udao.updateUser(temp_user);
        
     }
        
        
        
        
        
        
        
        //udao.sendNotification(unames,pb);
        pb.setUserlist(invited_users);
        
        //check_user = pb.getUserlist();
        
        request.setAttribute("pb",pb);
        request.setAttribute("post_id", post_id);
        return viewPage;
                        
   }
    
}
