/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mongodb.MongoClient;
import dao.UserDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import java.util.LinkedList;

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
                u.setEmail(email);
                u.setFirstName(firstName);
                u.setLastName(lastName);

                MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
                UserDAO userDao = new UserDAO(mongo);

                if (!userDao.isExists(u)) {
                    userDao.creatUser(u);
                    System.out.println("User created successfully with id=" + u.getId()); // logs
                    request.setAttribute("signupSucc", "Congratulation " + u.getFirstName() + "! Your registration is successful.");
                    viewPage = "Login.jsp";
                } else {
                    System.out.println("User exists............."); // logs
                    errors.add("User already there");
                    request.setAttribute("signupErr", errors);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return viewPage;
    }

}
