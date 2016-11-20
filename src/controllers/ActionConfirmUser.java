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
public class ActionConfirmUser implements Action {

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
                if (u.isVerified() == false) {
                    u.setVerified(true);
                    System.out.println("LOGGING --> verify set to TRUE for " + u.getEmail() + "" + u.getId() + "" + u.getFirstName());
                    request.setAttribute("confirmSucc", "Congratulation "+u.getFirstName()+", you are now member of vidico.");
                } else {
                    request.setAttribute("confirmErr", "You are already verified.");
                    System.out.println("LOGGING --> you are already verified " + u.getEmail() + " " + u.getId() + " " + u.getFirstName());
                }

                userDao.updateUser(u);
            } else {
                ArrayList<String> error = new ArrayList<>();
                error.add("Umh! seems like you are anonymous. Please register yourself to access vidico community!");

                request.setAttribute("signupErr", error);
                return "Signup.jsp"; // if user is not in database means not registered yet
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return viewPage;
    }

}
