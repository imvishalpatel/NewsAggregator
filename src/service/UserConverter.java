/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Notifications;
import model.User;
import org.bson.types.ObjectId;

/**
 *
 * @author vishal
 */
public class UserConverter {

    public static DBObject toDBObject(User u) {
//        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
//                .append("username", u.getUsername())
//                .append("password", u.getPassword())
//                .append("firstname", u.getFirstName())
//                .append("lastname", u.getLastName())
//                .append("email", u.getEmail())
//                .append("type", u.getType())
//                .append("lastAccessTime", u.getLastAcessTime())
//                .append("rating", u.getRating())
//                .append("verified", u.isVerified())
//                .append("registeredTime", u.getRegisterTime())
//                .append("reportedSpamCount", u.getReportedSpamCount())
//                .append("imgSource", u.getImgSource())
//                .append("publicPost", u.getPublicPost())
//                .append("privatePost", u.getPrivatePost())
//                .append("notification", u.getNotification())
//                .append("_id", u.getId());
//
//        return builder.get();
//    }
        
        
        	int i=0;
	    System.out.print("usertobasicdb");
        BasicDBObject object=new BasicDBObject();
        
        if(u.getId()!=null){
        	object.put("_id", u.getId());
        }
        
        object.put("username", u.getUsername());
        object.put("password", u.getPassword());
        object.put("firstname", u.getFirstName());
        object.put("lastname", u.getLastName());
        object.put("email", u.getEmail());
        object.put("type", u.getType());
        object.put("lastAccessTime", u.getLastAcessTime());
        object.put("rating", u.getRating());
        object.put("verified", u.isVerified());
        object.put("registeredTime", u.getRegisterTime());
        object.put("reportedSpamCount", u.getReportedSpamCount());
        object.put("imgSource", u.getImgSource());
	   
        ArrayList<String> pub = u.getPublicPost();
        if(pub!=null){
            BasicDBObject[] publicpost=new BasicDBObject[u.getPublicPost().size()];
        i=0;
        System.out.print("afterpub");
        for(String pb : u.getPublicPost()){
        	publicpost[i]=new BasicDBObject();
        	publicpost[i].put("pid", pb);
        	i++;
        }
          System.out.print("afterpub");
            object.put("publicPost", publicpost);
        }
        else{
            pub = new ArrayList<String>();
            object.put("publicPost", pub);
        }
           
        
        ArrayList<String> pri = u.getPrivatePost();
        if(pri!=null){
             BasicDBObject[] privatepost=new BasicDBObject[u.getPrivatePost().size()];
            i=0;
            for(String pb : u.getPrivatePost()){
            	privatepost[i]=new BasicDBObject();
            	privatepost[i].put("pid", pb);
            	i++;
            }
            object.put("privatePost", privatepost);
        }
         else{
            pri = new ArrayList<String>();
            object.put("privatePost", pri);
        }       
           
            ArrayList<Notifications> n = u.getNotification();
            if(n!=null){
                         BasicDBObject[] notification=new BasicDBObject[u.getNotification().size()];
                i=0;
                for(Notifications pb : u.getNotification()){
                	notification[i]=(BasicDBObject)NotificationConverter.toDBObject(pb);
                        //notification[i].put("notification", pb);
                	i++;
                }
                object.put("notification", notification);
          
            }
            else{
                n = new ArrayList<Notifications>();
                object.put("notification", n);
            }
                
                
                
                

        return object;
    }

    public static User toUsers(DBObject doc) throws Exception {
        System.out.println("converting for" + doc.get("firstname").toString());
        User u = new User();
        u.setVerified((boolean) doc.get("verified"));
        u.setType(String.valueOf(doc.get("type")).charAt(0));

        u.setUsername((String) doc.get("username").toString());
        u.setPassword(String.valueOf(doc.get("password").toString()));
        u.setFirstName((String) doc.get("firstname"));
        u.setLastName((String) doc.get("lastname"));
        u.setEmail((String) doc.get("email"));

        Date ldate = new Date();
        Date rdate = new Date();

        String lastAccessTime = String.valueOf(doc.get("lastAccessTime").toString());
        String registationTime = String.valueOf(doc.get("registeredTime").toString());
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        ldate = (Date) formatter.parse(lastAccessTime); // TODO Auto-generated catch block
        rdate = (Date) formatter.parse(registationTime);
        u.setLastAcessTime(ldate);
        u.setRegisterTime(rdate);

        //u.setRating(Integer.parseInt(doc.get("rating").toString()));
        u.setRating(5);
        //u.setReportedSpamCount(Integer.parseInt(doc.get("reportedSpamCount").toString()));
        u.setReportedSpamCount(0);
        ArrayList<Notifications> notify=null;
		BasicDBList notificationsObject=(BasicDBList) doc.get("notification");
		if(notificationsObject!=null){
                    if(notificationsObject.size()>0)
                    {
			notify=new ArrayList<Notifications>();
//			for(int i=0 ; i<notificationsObject.size() ; i++){
//	        	notify.add( NotificationConverter.toNotification((BasicDBObject)notificationsObject.get(i) ) );
//                        }
                    }
                    
		}
                else
                    notify=new ArrayList<Notifications>();
                
        u.setNotification(notify);

        u.setImgSource(doc.get("imgSource").toString());

        u.setId((ObjectId) doc.get("_id"));
        return u;
    }
}
