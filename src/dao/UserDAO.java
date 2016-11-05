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
import java.util.Date;
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

    public void updateUser(User u) {
        u.setLastAcessTime(new Date());
        DBObject newDocument = UserConverter.toDBObject(u);
        BasicDBObject searchQuery = new BasicDBObject().append("_id", u.getId());
        col.findAndModify(searchQuery, newDocument);
    }

    public void removeUser(User u) {
        // TODO
    }

    public void searchUser(User u) {
        // TODO
    }

    public User searchUserByUsernameOrEmail(User u) {
        BasicDBObject username = new BasicDBObject("username", u.getUsername());
        BasicDBObject email = new BasicDBObject("email", u.getEmail());

        BasicDBList or = new BasicDBList();
        or.add(username);
        or.add(email);
        DBObject query = new BasicDBObject("$or", or);
        
        DBObject obj = col.findOne(query);
        if(obj==null){
            return null;
        }
        
        User foundUser = UserConverter.toUser(obj);
        return foundUser;
    }

    public boolean isExists(User u) {
        BasicDBList or = new BasicDBList();

        BasicDBObject username = new BasicDBObject("username", u.getUsername());
        BasicDBObject email = new BasicDBObject("email", u.getEmail());
        or.add(username);
        or.add(email);
        DBObject query = new BasicDBObject("$or", or);
        DBCursor cursor = col.find(query);

        if (cursor.size() > 0) {
            return true;
        }
//        while(cursor.hasNext()){
//            System.out.println(cursor.next());
//        }
        return false;
    }

    public User searchByObjectId(String id) {
        DBObject obj = col.findOne(new ObjectId(id));
        User u = null;
        System.out.println("LOGGING --> [obj] " + obj);
        if (obj == null) {
            return u;
        }

        System.out.println("LOGGING --> " + obj.get("firstname"));
        System.out.println("LOGGING --> " + obj.get("verified"));
        u = UserConverter.toUser(obj);

        return u;
    }
}
