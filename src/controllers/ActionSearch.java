/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Constants.GlobalConstants;
import com.mongodb.MongoClient;
import dao.SearchTopicDao;
import dao.SearchUserDao;
import dao.UserDAO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import model.Discussion;
import model.PublicDiscussion;

/**
 *
 * @author pritesh
 */
public class ActionSearch implements Action {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        String searchvalue = request.getParameter("searchvalue");
        
            
        MongoClient mongo = (MongoClient) request.getServletContext().getAttribute(GlobalConstants.MONGO_CLIENT);
        SearchUserDao searchDao = new SearchUserDao(mongo);
        SearchTopicDao topicDao = new SearchTopicDao(mongo);
        
        List<User> users = searchDao.searchUser(searchvalue);
        ArrayList<PublicDiscussion> topic = topicDao.searchTopic(searchvalue);
        request.setAttribute("searchResult_discussion",topic);
        request.setAttribute("searchResult_users", users);
        return "SearchResult.jsp";
    }

}
