/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Constants.GlobalConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author vishal
 */
public class ActionLogout implements Action {

    private String viewPage = "Login.jsp";

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = new ArrayList<>();

        System.out.println("IN LOGOUT ACTION");
        User loggedInUser = (User) request.getSession().getAttribute(GlobalConstants.LOGGED_IN_USER);
        if (loggedInUser != null) {
            System.out.println("Logged in user =" + loggedInUser);
            request.getSession().setAttribute(GlobalConstants.LOGGED_IN_USER, null);
            System.out.println("User=" + loggedInUser.getUsername() + " successfully logged out");

            //remove logged in user from active list
            Map<String, User> activeUsers = (Map<String, User>) request.getServletContext().getAttribute("activeUSers");
            if (activeUsers != null) {
                activeUsers.remove(loggedInUser.getUsername());
            }
        }
        errors.add("You are successfully logged out");
        request.setAttribute("loginErr", errors);
        return viewPage;
    }

}
