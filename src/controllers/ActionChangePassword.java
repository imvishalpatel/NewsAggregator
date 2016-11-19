/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Constants.GlobalConstants;
import com.mongodb.MongoClient;
import dao.UserDAO;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import util.Mail;

/**
 *
 * @author vishal
 */
public class ActionChangePassword implements Action {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        User session_user = (User) request.getSession().getAttribute(GlobalConstants.LOGGED_IN_USER);
        // if user is not in session then redirect to login.jsp
        if (session_user != null) {
            String oldPassword = request.getParameter("oldpassword");
            String newPassword = request.getParameter("newpassword");
            String confirmNewPassword = request.getParameter("confirmnewpassword");
            LinkedList<String> errors = new LinkedList<>();
            try {
                if (oldPassword == null || oldPassword.equals("")) {
                    errors.add("Enter old password");
                }
                if (newPassword == null || newPassword.equals("")) {
                    errors.add("New Password can not be blank");
                }
                if (confirmNewPassword == null || !confirmNewPassword.equals(newPassword)) {
                    errors.add("Confirm Password must be same as New Password");
                }
                if (errors.size() > 0) {
                    request.setAttribute("changePasswordErr", errors);
                } else {
                    MongoClient mongo = (MongoClient) request.getServletContext().getAttribute(GlobalConstants.MONGO_CLIENT);
                    UserDAO userDao = new UserDAO(mongo);

                    if (oldPassword.equals(session_user.getPassword())) {
                        if (!oldPassword.equals(newPassword)) {
                            session_user.setPassword(newPassword);
                            userDao.updateUser(session_user);
                            StringBuilder sb = new StringBuilder();
                            System.out.println("[LOGGING] -> Password has been changed for user=" + session_user.getEmail());
                            sb.append("Your password has been changed.");

                            boolean mailStatus = Mail.send(session_user.getEmail(), "[IMPORTANT] Password Changed", sb.toString());

                            errors.add("Password has been updated.");
                            request.setAttribute("changePasswordSucc", errors);
                        } else {
                            errors.add("New Password shold be different than old password!");
                            request.setAttribute("changePasswordErr", errors);
                        }
                    } else {
                        errors.add("You entered wrong password!");
                        request.setAttribute("changePasswordErr", errors);
                    }
                }
            } catch (Exception e) {
                System.out.println("Exception : " + e.getMessage());
            }
        } else {
            System.out.println("Session out");
            return "Login.jsp";
        }
        return "ChangePassword.jsp";
    }

}
