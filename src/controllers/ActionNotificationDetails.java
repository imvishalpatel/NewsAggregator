/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gaurav
 */
public class ActionNotificationDetails implements Action{

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        String viewPage="details.jsp";
        String id = request.getParameter("id");
        String ref = request.getParameter("ref");
        
        
        
        return viewPage;
    }
    
}
