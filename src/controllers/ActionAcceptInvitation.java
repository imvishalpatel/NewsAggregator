/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.PrivatePostDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PrivateDiscussion;
import model.User;

/**
 *
 * @author Gaurav
 */
public class ActionAcceptInvitation implements Action{
private String viewPage = "PrivateDetailedDiscussion.jsp";
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
       String fname,lname;
       
        PrivateDiscussion pb = null;
        PrivatePostDAO privatePostDao = null;
       String post_id;
       post_id=request.getParameter("topic").toString();
        pb=privatePostDao.showDetailPost(post_id);
       
        //fname = request.getParameter("first");//Yaaan Post id aayega    Then full post se obj leke we'll forward it to discussion  details page
        //lname = request.getParameter("last");
        request.setAttribute("pb", pb);
       
        
        
        return viewPage;
                
    }
    
}
