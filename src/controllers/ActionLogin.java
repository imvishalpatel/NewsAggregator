package controllers;

import Constants.GlobalConstants;
import com.mongodb.MongoClient;
import dao.UserDAO;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

public class ActionLogin implements Action {

    @Override
    public String process(HttpServletRequest request,
            HttpServletResponse response) {
        LinkedList<String> errors = new LinkedList<>();
        String view = "Login.jsp";
        try {

            String username = (String) request.getAttribute("username");
            String password = (String) request.getAttribute("password");

            if (username == null || username.equals("")) {
                errors.add("Username can not be blank");
            }
            if (password == null || password.equals("")) {
                errors.add("Password can not be blank");
            }

            if (errors.size() > 0) {
                request.setAttribute("loginErr", errors);
            } else {
                username = username.trim();
                MongoClient mongo = (MongoClient) request.getServletContext().getAttribute(GlobalConstants.MONGO_CLIENT);
                UserDAO dao = new UserDAO(mongo);

                // created tmp user to pass in function
                User tmpUser = new User();
                tmpUser.setUsername(username);
                tmpUser.setEmail(username);
                tmpUser.setPassword(password);

                // fetch user by username or email
                User foundUser = dao.searchUserByUsernameOrEmail(tmpUser);

                if (foundUser != null) {

                    String message;

                    // compare user entered email or username
                    if (foundUser.getUsername().equals(tmpUser.getUsername()) || foundUser.getEmail().equals(tmpUser.getEmail())) {
                        if (foundUser.getPassword().trim().equals(tmpUser.getPassword())) {
                            if (foundUser.isVerified()) {
                                message = "Login Sucessful for user=" + foundUser.getUsername();
                                System.out.println(message);

                                //Add this user object to active list
                                Map<String, User> activeUsers = (Map<String, User>) request.getServletContext().getAttribute("activeUSers");
                                if (activeUsers == null) {
                                    activeUsers = new HashMap<>();
                                }
                                activeUsers.put(username, foundUser);

                                //Set session
                                request.getSession().setAttribute(GlobalConstants.LOGGED_IN_USER, foundUser);
                                request.getSession().setAttribute("userType", foundUser.getType());

                                // check if admin or not
                                if (foundUser.getType() == 'a' || foundUser.getType() == 'A') {
                                        return "AdminSpam.jsp";
                                }

                                // once user successfully authenticate return it to home
                                // for tmp i have redireted to public post page
                                return "PublicDiscussionList.jsp";
                            } else {
                                errors.add("You have not verified your email id yet.");
                                request.setAttribute("loginErr", errors);
                            }
                        } else {
                            errors.add("The password you entered is incorrect");
                            request.setAttribute("loginErr", errors);
                        }
                    } else {
                        errors.add("The username you entered is incorrect");
                        request.setAttribute("loginErr", errors);
                    }
                } else {
                    errors.add("!Incorrect Username and Password");
                    request.setAttribute("loginErr", errors);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return view;
    }
}
