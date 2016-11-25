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
 * @author BHAVESH GOYAL
 */
public class ActionRefUser implements Action {

    private String viewPage = "RefUserProfile.jsp";
    ArrayList<String> errors = new ArrayList<>();

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = request.getParameter("uname");
            if (username != null) {
                MongoClient mongo = (MongoClient) request.getServletContext().getAttribute(GlobalConstants.MONGO_CLIENT);
                UserDAO userDao = new UserDAO(mongo);

                User refUser = userDao.searchUser(username);
                request.setAttribute(GlobalConstants.REF_USER, refUser);
            } else {
                errors.add("User not exists!");
                request.setAttribute("errors", errors);
            }
        } catch (Exception e) {
            errors.add(e.getMessage());
            request.setAttribute("errors", errors);
            System.out.println("Exception : " + e.getMessage());
        }

        return viewPage;
    }

}
