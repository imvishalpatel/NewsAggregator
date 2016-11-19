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
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import util.Mail;

/**
 *
 * @author vishal
 */
public class ActionForgotPassword implements Action {

    private String viewPage = "Login.jsp";

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        email = email.trim();

        LinkedList<String> errors = new LinkedList<>();

        try {
            if (email == null || email.equals("")) {
                errors.add("Email can not be blank");
            }
            if (errors.size() > 0) {
                request.setAttribute("forgotPasswordErr", errors);
                viewPage = "ForgotPassword.jsp";
            } else {
                User u = new User();
                u.setEmail(email);

                MongoClient mongo = (MongoClient) request.getServletContext().getAttribute(GlobalConstants.MONGO_CLIENT);
                UserDAO userDao = new UserDAO(mongo);
                User foundUser = userDao.searchUserByUsernameOrEmail(u);

                if (foundUser != null) {

                    StringBuilder sb = new StringBuilder();
                    sb.append("You have requested to reset your password");
                    sb.append("Click on the below link to reset your password.");
                    System.out.println(foundUser.getId());
                    sb.append("Link : http://localhost:8084/NewsAggregator/Controller?action=resetpassword&key=" + foundUser.getId());

                    boolean mailStatus = Mail.send(u.getEmail(), "[IMPORTANT] Reset Password", sb.toString());
                } else {
                    ArrayList<String> error = new ArrayList<>();
                    error.add("Umh! seems like you are anonymous. Please register yourself to access vidico community!");

                    request.setAttribute("signupErr", error);
                    return "Signup.jsp"; // if user is not in registered yet
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return viewPage;
    }

}
