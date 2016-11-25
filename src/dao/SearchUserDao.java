/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import model.Discussion;
import model.User;
import service.UserConverter;
import service.PublicPostConverter;

/**
 *
 * @author pritesh
 */
public class SearchUserDao {

    private DBCollection col,col1;

    public SearchUserDao(MongoClient mongo) {
        this.col = mongo.getDB("vidico").getCollection("users");
        this.col1 = mongo.getDB("vidico").getCollection("Publicpost");
    }

    public List<User> searchUser(String searchvalue) {
        BasicDBList or = new BasicDBList();

        BasicDBObject username = new BasicDBObject("username", new BasicDBObject("$regex",java.util.regex.Pattern.compile(searchvalue)).append("$options", "i"));
        BasicDBObject firstname = new BasicDBObject("firstname", new BasicDBObject("$regex",java.util.regex.Pattern.compile(searchvalue)).append("$options", "i"));
        BasicDBObject lastname = new BasicDBObject("lastname", java.util.regex.Pattern.compile(searchvalue));
        or.add(username);
        or.add(firstname);
        or.add(lastname);
        DBObject query = new BasicDBObject("$or", or);

        DBCursor cursor = col.find(query);
        if (cursor.size() > 0) {
            System.out.println("user is exists");
        } else {
            System.out.println("user is not exists");
        }

        List<User> users = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                User tmpUser = UserConverter.toUsers(cursor.next());
                users.add(tmpUser);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return users;
    }
    
    public List<Discussion> searchTopic(String searchvalue) {
         BasicDBList or1 = new BasicDBList();

        BasicDBObject topic = new BasicDBObject("Topic",java.util.regex.Pattern.compile(searchvalue));
        BasicDBObject content = new BasicDBObject("Content",java.util.regex.Pattern.compile(searchvalue));
        BasicDBObject tags = new BasicDBObject("Tags",java.util.regex.Pattern.compile(searchvalue));
        or1.add(topic);
        or1.add(content);
        or1.add(tags);
        DBObject query1 = new BasicDBObject("$or", or1);
        DBCursor cursor1 = col1.find(query1);
        if (cursor1.size() > 0) {
            System.out.println("data is exist");
        }
        else
        {
            System.out.println("data is not exist");
        }

   
       
            while (cursor1.hasNext()) {
          
                System.out.println(cursor1.next());
            }
        
       return null;
    }
    
}
