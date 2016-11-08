/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mongodb.MongoClient;
import dao.UserDAO;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author vishal
 */
public class ActionLoginAuth implements Action {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LinkedList<String> errors = new LinkedList<>();
        if (username == null || username.equals("")) {
            errors.add("Username can not be blank");
        }
        if (password == null || password.equals("")) {
            errors.add("Password can not be blank");
        }

        if (errors.size() > 0) {
            request.setAttribute("loginErr", errors);
            return "Signup.jsp";
        } else {
            MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
            UserDAO userDao = new UserDAO(mongo);
            // create dummy user ; will be sent to userDao for searching
            User dummyUser = new User();
            dummyUser.setEmail(username);
            dummyUser.setUsername(username);

            User foundUser = userDao.searchUserByUsernameOrEmail(dummyUser);
            if (foundUser == null) {
                System.out.println("[LOGGIN] -> user not found");
                errors.add("No user found having username : " + username);
                request.setAttribute("loginErr", errors);
                return "Signup.jsp";
            } else {
                if (!foundUser.getPassword().equals(password)) {
                    System.out.println("[LOGGIN] -> Bad credentials");
                    errors.add("Bad Credentials");
                    request.setAttribute("loginErr", errors);
                    return "Signup.jsp";
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("loggedin_user", username);
                }
            }
        }
        return "PublicPost.jsp";
    }

}
