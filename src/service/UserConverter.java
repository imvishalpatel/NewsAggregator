/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
                .append("notification", u.getNotification())
                .append("_id", u.getId());

        return builder.get();
    }

    public static User toUsers(DBObject doc) throws Exception {
        System.out.println("converting for" + doc.get("firstname"));
        User u = new User();
        u.setVerified((boolean) doc.get("verified"));
        u.setType(String.valueOf(doc.get("type")).charAt(0));

        u.setUsername((String) doc.get("username"));
        u.setPassword((String) doc.get("password"));
        u.setFirstName((String) doc.get("firstname"));
        u.setLastName((String) doc.get("lastname"));
        u.setEmail((String) doc.get("email"));

        Date ldate = new Date();
        Date rdate = new Date();

        String lastAccessTime = doc.get("lastAccessTime").toString();
        String registationTime = doc.get("registeredTime").toString();
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        ldate = (Date) formatter.parse(lastAccessTime); // TODO Auto-generated catch block
        rdate = (Date) formatter.parse(registationTime);
        u.setLastAcessTime(ldate);
        u.setRegisterTime(rdate);

        u.setRating(Integer.parseInt(doc.get("rating").toString()));

        u.setReportedSpamCount(Integer.parseInt(doc.get("reportedSpamCount").toString()));

        u.setImgSource(doc.get("imgSource").toString());

        u.setId((ObjectId) doc.get("_id"));
        return u;
    }
}
