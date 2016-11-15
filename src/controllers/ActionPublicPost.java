/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import com.mongodb.MongoClient;
import dao.PublicPostDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PublicDiscussion;
import java.util.LinkedList;
/**
 *
 * @author BHAVESH GOYAL
 */
public class ActionPublicPost implements Action {
    
      private String viewPage = "PublicPost.jsp";

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
         return viewPage;
    }

}
