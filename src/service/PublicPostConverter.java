/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import model.PublicDiscussion;
import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author BHAVESH GOYAL
 */
public class PublicPostConverter {
        public static DBObject toDBObject(PublicDiscussion pb) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("userId", pb.getUsertId())
                .append("topic", pb.getTopic())
                .append("content",pb.getContent())
                .append("tags", pb.getTagsString())
                .append("viewedBy", null)
                .append("comment", null)
                .append("voter", null)
                .append("category",pb.getCategoryString())
                .append("spam", false)
                .append("open", true)
                .append("reportedSpamHistory", null)
                .append("_id", pb.getPostId());

        return builder.get();
    }
        
 public static PublicDiscussion fromDBObject(DBObject dbo) {
     PublicDiscussion pb=new PublicDiscussion();
     try{
      pb.setUserId(dbo.get("userId").toString());
        pb.setTopic(dbo.get("topic").toString());
        pb.setContent(dbo.get("content").toString());
        pb.setTags(dbo.get("tags").toString());
        //pb.setCategory(dbo.get("category").toString());
        System.out.println(pb.getUsertId());
        
     }catch (Exception e) {
            System.out.println(e.getMessage()+"converter");
        }
 return pb;
 }

    
}
