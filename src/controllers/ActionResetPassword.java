/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mongodb.MongoClient;
import dao.UserDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author vishal
 */
public class ActionResetPassword implements Action {

    private String viewPage = "Login.jsp";

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {

        String key = (String) request.getAttribute("key");

        System.out.println("LOGGING --> " + key);
        // TODO
        if (key == null || key.equals("")) {
            viewPage = "Error.jsp";
            return viewPage;
        }

        try {
            MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
            UserDAO userDao = new UserDAO(mongo);

            User u = userDao.searchByObjectId(key);

            if (u != null) {
                if (u.getPassword().equals("null")) {
                    // pass key as parameter because in new password servlet i need referce of which user want to update password
                    viewPage = "ResetPassword.jsp?key=" + u.getId();
                    return viewPage;
                }
                u.setPassword("null");
                System.out.println("LOGGING --> password set to NULL for " + u.getEmail() + " " + u.getId() + " " + u.getFirstName());
                userDao.updateUser(u);
                viewPage = "ResetPassword.jsp?key=" + u.getId();
                return viewPage;
            } else {
                ArrayList<String> error = new ArrayList<>();
                error.add("Umh! seems like you are anonymous. Please register yourself to access vidico community!");

                request.setAttribute("signupErr", error);
                return "Signup.jsp"; // if user is not yet register
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return viewPage;
    }

}
