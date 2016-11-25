/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import model.PrivateDiscussion;
import org.bson.types.ObjectId;
import service.PrivatePostConverter;

public class PrivatePostDAO {
        private DBCollection col;

    public PrivatePostDAO(MongoClient mongo) {
        this.col = mongo.getDB("vidico").getCollection("privatepost");
    }

    public PrivateDiscussion newPost(PrivateDiscussion pb) {
        DBObject doc = PrivatePostConverter.toDBObject(pb);
        this.col.insert(doc);
        pb.setId((ObjectId) doc.get("_id"));
        return pb;
    }
    
//    public PrivateDiscussion showPost()
//    { 
//        PrivateDiscussion obj=null;
//       try{
//        BasicDBObject whereQuery = new BasicDBObject();
//        DBObject oneDetails;
//    DBCursor cursor = this.col.find();
//    while(cursor.hasNext()) {
//        oneDetails=cursor.next();
//        PrivateDiscussion pb=null;
//     pb=PrivatePostConverter.toPrivateDiscussion(oneDetails);
//     obj = pb;
//    }
//    
//        }catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return obj;
//    }
    
    
           public PrivateDiscussion getPrivateDiscussion(String pid){
			
        	PrivateDiscussion pd=PrivatePostConverter.toPrivateDiscussion(col.findOne(new ObjectId(pid)));
        
        	return pd;	
        }
    
           
            public void updatePrivateDiscussion(PrivateDiscussion pd){
			
        	DBObject obj=PrivatePostConverter.toDBObject(pd);
        	col.findAndModify(new BasicDBObject().append("_id", pd.getid()), obj);
        }       
    public PrivateDiscussion showDetailPost(String postid)
    { 
         
        PrivateDiscussion pb = null;
        try
        {
        BasicDBObject whereQuery = new BasicDBObject();
        DBObject oneDetails;
        whereQuery.put("_id", new ObjectId(postid));
            DBCursor cursor = this.col.find(whereQuery);
    
        oneDetails=cursor.next();
        
     pb=PrivatePostConverter.toPrivateDiscussion(oneDetails);
       
      //      System.out.println(pb.getUsertId());
//        System.out.println(pb.getTopic());
//        System.out.println(pb.getContent());
//        System.out.println(pb.getTagsString());
    
    
        }catch (Exception e) {
            System.out.println(e.getMessage()+"dao");
        }
        
    return pb;
    }

}