/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import java.util.Date;
import model.User;
import org.bson.types.ObjectId;

/**
 *
 * @author vishal
 */
public class UserConverter {
    
    public static DBObject toDBObject(User u) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("username", u.getUsername())
                .append("password", u.getPassword())
                .append("firstname", u.getFirstName())
                .append("lastname", u.getLastName())
                .append("email", u.getEmail())
                .append("type", u.getType())
                .append("lastAccessTime", u.getLastAcessTime())
                .append("rating", u.getRating())
                .append("verified", u.isVerified())
                .append("registeredTime", u.getRegisterTime())
                .append("reportedSpamCount", u.getReportedSpamCount())
                .append("imgSource", u.getImgSource())
                .append("publicPost", u.getPublicPost())
                .append("privatePost", u.getPrivatePost())
                .append("notification",u.getNotification())
                .append("_id", u.getId());
        
        return builder.get();
    }
    
    public static User toUser(DBObject doc) {
        User u = new User();
        u.setVerified((boolean) doc.get("verified"));
        u.setType(String.valueOf(doc.get("type")).charAt(0));
        
        u.setUsername((String) doc.get("username"));
        u.setPassword((String) doc.get("password"));
        u.setFirstName((String) doc.get("firstname"));
        u.setLastName((String) doc.get("lastname"));
        u.setEmail((String) doc.get("email"));
        
        u.setLastAcessTime((Date) doc.get("lastAccessTime"));
        u.setRating((int) doc.get("rating"));
        u.setRegisterTime((Date) doc.get("registeredTime"));
        u.setReportedSpamCount((int) doc.get("reportedSpamCount"));
        
        u.setImgSource((String) doc.get("imgSource"));
        
        u.setId((ObjectId) doc.get("_id"));
        return u;
    }
}
