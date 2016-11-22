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
import java.util.ArrayList;
import java.util.HashMap;
import model.Comment;
import model.PublicDiscussion;
import org.bson.types.ObjectId;
import service.PublicPostConverter;
import util.sequence.model.SequenceId;

/**
 *
 * @author BHAVESH GOYAL
 */
public class PublicPostDAO {
        private DBCollection col;

    public PublicPostDAO(MongoClient mongo) {
        this.col = mongo.getDB("vidico").getCollection("public");
    }

    public PublicDiscussion newPost(PublicDiscussion pb) {
        DBObject doc = PublicPostConverter.toDBObject(pb);
        this.col.insert(doc);
        pb.setid((ObjectId) doc.get("_id"));
        return pb;
    }
    
    public ArrayList<PublicDiscussion> showPost()
    { 
        ArrayList<PublicDiscussion> arpb=new ArrayList<PublicDiscussion>();
             
        try{
        BasicDBObject whereQuery = new BasicDBObject();
        BasicDBObject order = new BasicDBObject();
        DBObject oneDetails;
   whereQuery.put("isSpam",false);
        order.put("date", -1);
    DBCursor cursor = this.col.find(whereQuery).sort(order);
    while(cursor.hasNext()) {
        oneDetails=cursor.next();
        PublicDiscussion pb;
     pb=PublicPostConverter.toPublicDiscussion(oneDetails);
       
        arpb.add(pb);
  //      System.out.println(pb.getUsertId());
//        System.out.println(pb.getTopic());
//        System.out.println(pb.getContent());
//        System.out.println(pb.getTagsString());
    }
    
        }catch (Exception e) {
            System.out.println(e.getMessage()+"dao");
        }
        
    return arpb;    }
    
    public void acceptSpam(String postid)
    {
        
         BasicDBObject whereQuery = new BasicDBObject();
          whereQuery.put("_id", new ObjectId(postid));
            this.col.remove(whereQuery);
       
        
    }
    public void rejectSpam(String postid)
    {
        
         BasicDBObject whereQuery = new BasicDBObject();
         BasicDBObject newspam = new BasicDBObject();
         
          whereQuery.put("_id", new ObjectId(postid));
          newspam.put("isSpam", false);
          BasicDBObject set = new BasicDBObject("$set", newspam);
            this.col.update(whereQuery,set);
       
        
    }
    public void makeSpam(String postid)
    {
        
         BasicDBObject whereQuery = new BasicDBObject();
         BasicDBObject newspam = new BasicDBObject();
         
          whereQuery.put("_id", new ObjectId(postid));
          newspam.put("isSpam", true);
          BasicDBObject set = new BasicDBObject("$set", newspam);
            this.col.update(whereQuery,set);
       
        
    }
     public ArrayList<PublicDiscussion> showSpamPost()
    { 
        ArrayList<PublicDiscussion> arpb=new ArrayList<PublicDiscussion>();
             
        try{
        BasicDBObject whereQuery = new BasicDBObject();
        BasicDBObject order = new BasicDBObject();
        DBObject oneDetails;
   whereQuery.put("isSpam",true);
       order.put("date", 1);
    DBCursor cursor = this.col.find(whereQuery).sort(order);
    while(cursor.hasNext()) {
        oneDetails=cursor.next();
        PublicDiscussion pb;
     pb=PublicPostConverter.toPublicDiscussion(oneDetails);
       
        arpb.add(pb);
  //      System.out.println(pb.getUsertId());
//        System.out.println(pb.getTopic());
//        System.out.println(pb.getContent());
//        System.out.println(pb.getTagsString());
    }
    
        }catch (Exception e) {
            System.out.println(e.getMessage()+"dao");
        }
        
    return arpb;    }
   
   
    public PublicDiscussion showDetailPost(String postid)
    { 
         
             PublicDiscussion pb=null;
        try
        {
        BasicDBObject whereQuery = new BasicDBObject();
        DBObject oneDetails;
  whereQuery.put("_id", new ObjectId(postid));
    DBCursor cursor = this.col.find(whereQuery);
    
        oneDetails=cursor.next();
        
     pb=PublicPostConverter.toPublicDiscussion(oneDetails);
       
      //      System.out.println(pb.getUsertId());
//        System.out.println(pb.getTopic());
//        System.out.println(pb.getContent());
//        System.out.println(pb.getTagsString());
    
    
        }catch (Exception e) {
            System.out.println(e.getMessage()+"dao");
        }
        
    return pb;    }
//     public  void updatePost(ArrayList<PublicDiscussion> arpb)
//    { 
//             
//        try{
//            
//        
//    DBCursor cursor = this.col.find();
//    while(cursor.hasNext()) {
//             PublicDiscussion pb=arpb.get(index);
//             DBObject doc = PublicPostConverter.toDBObject(pb);
//            BasicDBObject whereQuery = new BasicDBObject();
//    whereQuery.put("userId",pb.getUsertId());
//   
//     this.col.update(whereQuery, doc);
//        
//        
//    
//    }
//    
//        }catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        
//      }
//     
//     public void addcomment(String id,Comments newComment)
//     {
//         
     
//     }
    public HashMap<String, PublicDiscussion> getAllPublicPosts(){
        	
        	//ArrayList<PublicDiscussion> list=null;
        	HashMap<String, PublicDiscussion> list=null;
        	DBCursor cursor=col.find();
        	
        	while(cursor.hasNext()){
        		PublicDiscussion pd=PublicPostConverter.toPublicDiscussion(cursor.next());
        		
        		if(list==null){
        			list=new HashMap<String,PublicDiscussion>();
        		}
        		
        		list.put(pd.getid().toString(),pd);
        	}
        	
        	return list;
        }
       public PublicDiscussion getPublicDiscussion(String pid){
			
        	PublicDiscussion pd=PublicPostConverter.toPublicDiscussion(col.findOne(new ObjectId(pid)));
        
        	return pd;	
        }
        
        public void updatePublicDiscussion(PublicDiscussion pd){
			
        	DBObject obj=PublicPostConverter.toDBObject(pd);
        	col.findAndModify(new BasicDBObject().append("_id", pd.getid()), obj);
        }	

}
