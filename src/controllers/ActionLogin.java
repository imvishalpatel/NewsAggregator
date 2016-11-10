package controllers;

import java.util.HashMap;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

import com.mongodb.MongoClient;

import dao.UserDAO;
import java.util.Map;

public class ActionLogin implements Action {

    @Override
    public String process(HttpServletRequest request,
            HttpServletResponse response) {

        String view = "error.jsp";
        try {

            String username = request.getAttribute("username").toString();
            String password = request.getAttribute("password").toString();

            MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
            UserDAO dao = new UserDAO(mongo);

            User user;
            user = dao.searchUser(username);

            String message = "";

            if (user.getUsername().equals(username.trim())) {
                if (user.getPassword().trim().equals(password.trim())) {
                    if (user.isVerified()) {
                        message = "Login Sucessful";

                        //Add this user object to active list
                        Map<String, User> activeUsers = (Map<String, User>) request.getServletContext().getAttribute("activeUSers");
                        if(activeUsers==null)
                            activeUsers = new HashMap<>();
                        activeUsers.put(username, user);

                        //Set session
                        request.getSession().setAttribute("username", username);
                        request.getSession().setAttribute("userType", user.getType());
                    } else {
                        message = "You have not verified your email id yet.";
                    }
                } else {
                    message = "The password you entered is incorrect";
                }
            } else {
                message = "The username you entered is incorrect";
            }
            System.out.println(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return view;
    }
}
