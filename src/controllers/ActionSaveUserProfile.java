/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Constants.GlobalConstants;
import com.mongodb.MongoClient;
import dao.UserDAO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author vishal
 */
public class ActionSaveUserProfile implements Action {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("in save user profile");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");

        List<String> errors = new ArrayList<>();
        try {
            if (firstName == null || firstName.equals("")) {
                errors.add("Firstname can not be blank");
            }
            if (lastName == null || lastName.equals("")) {
                errors.add("Lastname can not be blank");
            }
            if (username == null || username.equals("")) {
                errors.add("Username can not be blank");
            }
            if (email == null || email.equals("")) {
                errors.add("Email can not be blank");
            }

            if (errors.size() > 0) {
                request.setAttribute("userProfileErr", errors);
            } else {
                MongoClient mongo = (MongoClient) request.getServletContext().getAttribute(GlobalConstants.MONGO_CLIENT);
                UserDAO userDao = new UserDAO(mongo);

                User updateUser = (User) request.getSession().getAttribute(GlobalConstants.LOGGED_IN_USER);
                if (updateUser != null) {
                    updateUser.setUsername(username);
                    updateUser.setEmail(email);
                    updateUser.setFirstName(firstName);
                    updateUser.setLastName(lastName);

                    userDao.updateUser(updateUser);
                    request.getSession().setAttribute(GlobalConstants.LOGGED_IN_USER, updateUser);
                    errors.add("Profile Saved");
                    request.setAttribute("userProfileSucc", errors);
                } else {// if user is not logged in and trying to update profile
                    System.out.println("[ERROR] User is not logged in. redirect to login.jsp");
                    return "Login.jsp";
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in SaveUserProfile -> " + e.getMessage());
        }

        return "UserProfile.jsp";
    }

}
