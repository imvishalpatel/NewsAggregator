package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

import com.mongodb.MongoClient;

import dao.UserDAO;

public class ActionUpdatePassword implements Action {

    @Override
    public String process(HttpServletRequest request,
            HttpServletResponse response) {

        System.out.println("Updating the password...");
        String view = "Login.jsp";

        String password = "123";
        //String password=request.getAttribute("password").toString();
        try {
            MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
            UserDAO dao = new UserDAO(mongo);

            User u = dao.searchUser("vnation"); //fetch from session

            if (u == null) {

            } else {
                u.setPassword(password);
                dao.updateUser(u);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return view;
    }

}
