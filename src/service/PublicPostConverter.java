/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import model.PublicDiscussionModel;
import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author BHAVESH GOYAL
 */
public class PublicPostConverter {
        public static DBObject toDBObject(PublicDiscussionModel pb) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("userId", pb.getUsertId())
                .append("topic", pb.getTopic())
                .append("content",pb.getContent())
                .append("tags", pb.getTagsString())
                .append("viewedBy", null)
                .append("comment", null)
                .append("voter", null)
                .append("category",null)
                .append("spam", false)
                .append("open", true)
                .append("reportedSpamHistory", null)
                .append("_id", pb.getPostId());

        return builder.get();
    }
        
 public static PublicDiscussionModel fromDBObject(DBObject dbo) {
     PublicDiscussionModel pb=new PublicDiscussionModel();
      pb.setUserId(dbo.get("userId").toString());
        pb.setTopic(dbo.get("topic").toString());
        pb.setContent(dbo.get("content").toString());
        pb.setTags(dbo.get("tags").toString());
        return pb;
    }

    
}
