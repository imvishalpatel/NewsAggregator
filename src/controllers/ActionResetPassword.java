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
            MongoClient mongo = (MongoClient) request.getServletContext().getAttribute(GlobalConstants.MONGO_CLIENT);
            UserDAO userDao = new UserDAO(mongo);

            User u = userDao.searchByObjectId(key);

            if (u != null) {
                // if user already clicked reset password link but again click on the link
                // then this method handles
                if (u.getPassword().equals(u.getId().toString())) {
                    // pass key as parameter because in new password servlet i need referce of which user want to update password
                    // set user in session
                    request.setAttribute("userid", u.getId());
                    viewPage = "ResetPassword.jsp";
                    return viewPage;
                }
                u.setPassword(u.getId().toString());
                System.out.println("LOGGING --> password set to" + u.getId() + " for " + u.getEmail() + " " + u.getId() + " " + u.getFirstName());
                userDao.updateUser(u);
                request.setAttribute("userid", u.getId());
                viewPage = "ResetPassword.jsp";
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
