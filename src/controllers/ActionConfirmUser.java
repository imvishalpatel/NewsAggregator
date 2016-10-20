/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mongodb.MongoClient;
import dao.UserDAO;
import java.util.Date;
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
            return "Error.jsp";
        }

        MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
        UserDAO userDao = new UserDAO(mongo);
        User u = userDao.searchByObjectId(key);

        if (u != null) {
            if (u.isVerified() == false) {
                u.setVerified(true);
                System.out.println("LOGGING --> verify set to TRUE for " + u.getEmail() + "" + u.getId() + "" + u.getFirstName());
                request.setAttribute("confirmSucc", u.getUsername());
            } else {
                System.out.println("LOGGING --> you are already verified " + u.getEmail() + " " + u.getId() + " " + u.getFirstName());
            }
        } else {
            return "Signup.jsp"; // if user is not in database means not registered yet
        }

        u.setLastAcessTime(new Date());
        userDao.updateUser(u);

        return "Login.jsp";
    }

}
