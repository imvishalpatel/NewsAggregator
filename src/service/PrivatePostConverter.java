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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.PrivateDiscussion;
import java.util.Date;
import java.util.HashMap;
import model.Comment;
import org.bson.types.ObjectId;

/**
 *
 * @author Gaurav
 */
public class PrivatePostConverter {
        public static DBObject toDBObject(PrivateDiscussion pb) {
//        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
//               // .append("userId", pb.getUsertId())
//                .append("topic", pb.getTopic())
//                .append("content",pb.getContent())
//                //.append("tags", pb.getTagsString())
//                .append("viewedBy", null)
//                .append("comment", null)
//                .append("voter", null)
//                .append("category",null)
//                .append("spam", false)
//                .append("open", false)
//                .append("reportedSpamHistory", null);
//               // .append("_id", pb.getPostId());
//
//        return builder.get();

        
        
		int i=0;
	    
        BasicDBObject object=new BasicDBObject();
        
        if(pb.getid()!=null){
        	object.put("_id", pb.getid());
        }
        
        object.put("userId", pb.getUsername());
        object.put("topic", pb.getTopic());
        object.put("content", pb.getContent());
        object.put("status", pb.getStatus());
        object.put("date", pb.getDate());
        
        BasicDBObject[] tags=new BasicDBObject[pb.getTags().size()];
        i=0;
        for(String tag : pb.getTags()){
        	tags[i]=new BasicDBObject();
        	tags[i].put("tag", tag);
        	i++;
        }
        object.put("tags", tags);
        
        BasicDBObject[] votes=new BasicDBObject[pb.getVoteList().keySet().size()];
		i=0;
        for(String username : pb.getVoteList().keySet()){
        	votes[i]=new BasicDBObject();
        	votes[i].put("username", username);
        	votes[i].put("vote", pb.getVoteList().get(username));
        	i++;
        }
        object.put("votes", votes);
        
        BasicDBObject[] comments=new BasicDBObject[pb.getComments().size()];
        i=0;
        for(Comment comment : pb.getComments()){
        	comments[i]=(BasicDBObject) CommentConverter.toDBObject(comment);
        	i++;
        }
        object.put("comments", comments);
        
        object.put("isSpam", pb.isSpam());
        
        BasicDBObject[] spamHistory=new BasicDBObject[pb.getSpamHistory().keySet().size()];
		i=0;
        for(String username : pb.getSpamHistory().keySet()){
        	spamHistory[i]=new BasicDBObject();
        	spamHistory[i].put("username", username);
        	spamHistory[i].put("reason", pb.getSpamHistory().get(username));
        	i++;
        }
        object.put("spamHistory", spamHistory);
        
        BasicDBObject[] category=new BasicDBObject[pb.getCategory().size()];
        i=0;
        for(String cat : pb.getCategory()){
        	category[i]=new BasicDBObject();
        	category[i].put("cat", cat);
        	i++;
        }
        object.put("category", category);
        
        BasicDBObject[] userlist=new BasicDBObject[pb.getUserlist().size()];
        i=0;
        for(String username : pb.getUserlist()){
        	userlist[i]=new BasicDBObject();
        	userlist[i].put("username", username);
        	i++;
        }
        object.put("userlist", userlist);
    
        return object;        
        }
        
// public static PrivateDiscussion fromDBObject(DBObject dbo) {
//     PrivateDiscussion pb=null;
//            //pb = new PrivateDiscussion();
//      //pb.setUserId(dbo.get("userId").toString());
//        pb.setTopic(dbo.get("topic").toString());
//        pb.setContent(dbo.get("content").toString());
//        pb.setTags(dbo.get("tags").toString());
//        return pb;
//    }

 
 	public static PrivateDiscussion toPrivateDiscussion(DBObject object){
		
		int i=0;
		
		PrivateDiscussion pb=new PrivateDiscussion(object.get("userId").toString(), object.get("topic").toString(), object.get("content").toString());
		
                pb.setid(new ObjectId(object.get("_id").toString()));
		
		pb.setStatus(new Boolean(object.get("status").toString()));
		
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		try {
			pb.setDate(formatter.parse(object.get("date").toString()));
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		BasicDBList tagObject=(BasicDBList) object.get("tags");
		ArrayList<String> tags=null;
		if(tagObject!=null){
			tags=new ArrayList<String>();
			for(i=0 ; i<tagObject.size() ; i++){
	        	tags.add(((BasicDBObject)tagObject.get(i)).get("tag").toString());
	        }
		}
		pb.setTags(tags);
		
		HashMap<String, Integer> votes=null;
		BasicDBList voteObject=(BasicDBList) object.get("votes");
		if(voteObject!=null){
			votes=new HashMap<String, Integer>();
			for(i=0 ; i<voteObject.size() ; i++){
				votes.put(((BasicDBObject) voteObject.get(i)).get("username").toString(), (Integer)((BasicDBObject) voteObject.get(i)).get("vote"));
			}
		}
		pb.setVotes(votes);
		
		ArrayList<Comment> comments=null;
		BasicDBList commentsObject=(BasicDBList) object.get("comments");
		if(commentsObject!=null){
			comments=new ArrayList<Comment>();
			for(i=0 ; i<commentsObject.size() ; i++){
	        	comments.add( CommentConverter.toComment( (BasicDBObject)commentsObject.get(i) ) );
	        }
		}
        pb.setComments(comments);

        pb.setSpam( new Boolean(object.get("isSpam").toString()) );
        
        HashMap<String, String> spamHistory=null;
		BasicDBList spamHistoryObject=(BasicDBList) object.get("spamHistory");
		if(spamHistoryObject!=null){
			spamHistory=new HashMap<String, String>();
			for(i=0 ; i<spamHistoryObject.size() ; i++){
				spamHistory.put(((BasicDBObject) spamHistoryObject.get(i)).get("username").toString(),  ((BasicDBObject) spamHistoryObject.get(i)).get("reason").toString());
			}
		}
		pb.setSpamHistory(spamHistory);
		
		ArrayList<String> category=null;
		BasicDBList catObject=(BasicDBList) object.get("category");
		if(catObject!=null){
			category=new ArrayList<String>();
			for(i=0 ; i<catObject.size() ; i++){
	        	category.add(((BasicDBObject)catObject.get(i)).get("cat").toString());
	        }
		}
		pb.setCategory(category);
		
		ArrayList<String> userlist=null;
		BasicDBList userlistObject=(BasicDBList) object.get("userlist");
		if(userlistObject!=null){
			userlist=new ArrayList<String>();
			for(i=0 ; i<userlistObject.size() ; i++){
	        	userlist.add(((BasicDBObject)userlistObject.get(i)).get("username").toString());
	        }
		}
		pb.setUserlist(userlist);
		
		return pb;
	}
    
}

