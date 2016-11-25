/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mongodb.MongoClient;
import dao.PrivatePostDAO;
import dao.UserDAO;
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
public class ActionPrivatePost implements Action{
       private String viewPage = "PrivatePost.jsp";

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        return viewPage;
    }

}
