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
import model.PublicDiscussion;
import model.User;
import service.PublicPostConverter;
import service.UserConverter;

/**
 *
 * @author Herat Patel
 */
public class SearchTopicDao {
    private DBCollection col;
     public SearchTopicDao(MongoClient mongo) {
        
        this.col = mongo.getDB("vidico").getCollection("public");
    }
     
      public ArrayList<PublicDiscussion> searchTopic(String searchvalue) {
       BasicDBList or = new BasicDBList();
       ArrayList<PublicDiscussion> arpb=new ArrayList<PublicDiscussion>();
        BasicDBObject topic = new BasicDBObject("topic", new BasicDBObject("$regex",java.util.regex.Pattern.compile(searchvalue)).append("$options", "i"));
        BasicDBObject content = new BasicDBObject("content", new BasicDBObject("$regex",java.util.regex.Pattern.compile(searchvalue)).append("$options", "i"));
        BasicDBObject tags = new BasicDBObject("tags", new BasicDBObject("$regex",java.util.regex.Pattern.compile(searchvalue)).append("$options", "i"));
        or.add(topic);
        or.add(content);
        or.add(tags);
        DBObject query = new BasicDBObject("$or", or);

        DBCursor cursor = col.find(query);
        
        if (cursor.size() > 0) {
            System.out.println("data is exists");
        } else {
            System.out.println("data  is not exists");
        }
   
        try {
            DBObject oneDetails;
            while (cursor.hasNext()) {
         oneDetails=cursor.next();
         
          PublicDiscussion tmpDiscussion = PublicPostConverter.toPublicDiscussion(oneDetails);
                arpb.add(tmpDiscussion);
          
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return arpb;
    
    }
    
    
}
