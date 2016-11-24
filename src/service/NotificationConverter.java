/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.util.HashMap;
import javax.management.Notification;
import model.Notifications;

/**
 *
 * @author Gaurav
 */
public class NotificationConverter {
    
    	static public DBObject toDBObject(Notifications notification){
		BasicDBObject object=new BasicDBObject();
		
		object.put("Id",notification.getId());
		object.put("Reference",notification.getReference());
		object.put("Text", notification.getText());
                object.put("Type", notification.getType());
                object.put("isSeen", notification.getIsSeen());
		return object;
	}
	
	static public Notifications toNotification(DBObject object){
		if(object==null){
			System.out.println("Object null");
			return null;
		}
                Notifications notify = new Notifications();
                
                notify.setIsSeen((boolean) object.get("IsSeen"));
                notify.setReference((String) object.get("Reference"));
                notify.setText((String) object.get("Text"));
                notify.setType((Integer)object.get("Type"));
                
                return notify;
	}
    
}
