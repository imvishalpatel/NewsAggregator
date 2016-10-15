/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mongodb.DBCollection;
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

}
