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
import model.User;
import org.bson.types.ObjectId;
import service.UserConverter;

/**
 *
 * @author vishal
 */
public class UserDAO {

    private DBCollection col;

    public UserDAO(MongoClient mongo) {
        this.col = mongo.getDB("vidico").getCollection("users");
    }

    public User creatUser(User u) {
        DBObject doc = UserConverter.toDBObject(u);
        this.col.insert(doc);
        u.setId((ObjectId) doc.get("_id"));
        return u;
    }
    
    public void updateUser(User u){
        // TODO
    }
    
    public void removeUser(User u){
        // TODO
    }
    
    public void searchUser(User u){
        // TODO
    }
    
    public boolean isExists(User u){
        BasicDBList or = new BasicDBList();
        
        BasicDBObject username = new BasicDBObject("username",u.getUsername());
        BasicDBObject email = new BasicDBObject("email",u.getEmail());
        or.add(username);
        or.add(email);
        DBObject query = new BasicDBObject("$or",or);
        DBCursor cursor = col.find(query);
        
        if(cursor.size() > 0){
            return true;
        }
//        while(cursor.hasNext()){
//            System.out.println(cursor.next());
//        }
        return false;
    }
}
