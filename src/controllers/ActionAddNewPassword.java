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
import util.MailService;

/**
 *
 * @author vishal
 */
public class ActionAddNewPassword implements Action {

    private String viewPage = "Login.jsp";

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {

        String newPassword = request.getParameter("newpassword");
        String confirmNewPassword = request.getParameter("confirmnewpassword");
        String userid = request.getParameter("userid");
        LinkedList<String> errors = new LinkedList<>();
        try {
            // if user not found in session
            if (newPassword == null || newPassword.equals("")) {
                errors.add("New Password can not be blank");
            }
            if (confirmNewPassword == null || !confirmNewPassword.equals(newPassword)) {
                errors.add("Confirm Password must be same as New Password");
            }

            if (errors.size() > 0) {
                request.setAttribute("resetPasswordErr", errors);
                viewPage = "ResetPassword.jsp";
                return viewPage;
            } else {
                MongoClient mongo = (MongoClient) request.getServletContext().getAttribute(GlobalConstants.MONGO_CLIENT);
                UserDAO userDao = new UserDAO(mongo);

                User u = userDao.searchByObjectId(userid);
                if (u != null) {
                    u.setPassword(newPassword);
                    userDao.updateUser(u);
                    StringBuilder sb = new StringBuilder();
                    System.out.println("[LOGGING] -> Password has been changed for user=" + u.getEmail());
                    sb.append("Your password has been changed.");

                    String firstMessage = "You have changed your password.";
                    String buttonValue = "Login in your account";
                    String buttonUrl = "http://localhost:8084/NewsAggregator/Controller?action=viewlogin";
                    String lastMessage = "If the link doesn't work please copy below link and paste it directly into your browser."
                            + "<br> " + buttonUrl;

                    MailService mailContent = new MailService(firstMessage, lastMessage, buttonValue, buttonUrl);
                    boolean mailStatus = mailContent.sendMail(u.getEmail(), "[UPDATE] Password Changed");

                    ArrayList<String> error = new ArrayList<>();
                    error.add("You have successfully changed your password. Login in using your new password");
                    request.setAttribute("signupSucc", error);
                } else {
                    ArrayList<String> error = new ArrayList<>();
                    error.add("Umh! seems like you are anonymous. Please register yourself to access vidico community!");

                    request.setAttribute("signupErr", error);
                    return "Signup.jsp"; // if user is not yet register
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return viewPage;
    }

}
