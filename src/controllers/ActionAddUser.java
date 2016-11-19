/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Constants.GlobalConstants;
import com.mongodb.MongoClient;
import dao.UserDAO;
import java.util.Date;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import util.Mail;

/**
 *
 * @author vishal
 */
public class ActionAddUser implements Action {

    private String viewPage = "Signup.jsp";

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        LinkedList<String> errors = new LinkedList<>();
        try {
            if (username == null || username.equals("")) {
                errors.add("Username can not be blank");
            }
            if (password == null || password.equals("")) {
                errors.add("Password can not be blank");
            }
            if (email == null || email.equals("")) {
                errors.add("Email can not be blank");
            }

            if (errors.size() > 0) {
                request.setAttribute("signupErr", errors);
            } else {

                User u = new User();
                u.setUsername(username);
                u.setPassword(password);
                u.setEmail(email);                          // public post, private post and notification need 
                u.setFirstName(firstName);                  // to be done
                u.setLastName(lastName);
                u.setType('u');
                u.setLastAcessTime(new Date());
                u.setRegisterTime(new Date());
                u.setRating(0);
                u.setReportedSpamCount(0);
                u.setVerified(false);
                u.setImgSource("");
             
                
                MongoClient mongo = (MongoClient) request.getServletContext().getAttribute(GlobalConstants.MONGO_CLIENT);
                UserDAO userDao = new UserDAO(mongo);

                if (!userDao.isExists(u)) { // check if user exist or not 
                    userDao.creatUser(u);
                    System.out.println("User created successfully with id=" + u.getId()); // logs

                    String mailContent = "verify yourself and be a member of vidico community."
                            + "confirmation key : http://localhost:8084/NewsAggregator/Controller?action=confirm&key=" + u.getId();
                    mailContent += "\nClick on the link or paste in browser.";

                    boolean mailStatus = Mail.send(u.getEmail(), "confirmation code", mailContent);

                    String[] parameters = {u.getFirstName(), u.getEmail()};
                    request.setAttribute("signupSucc", parameters);
                    viewPage = "TestPage.jsp";
                } else {
                    System.out.println("User exists............."); // logs
                    errors.add("User exists, choose another username or email");
                    request.setAttribute("signupErr", errors);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return viewPage;
    }

}
