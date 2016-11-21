package service;

/**
 *
 * @author Herat Patel
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import model.Comment;
import model.ExclusiveDiscussion;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

public class ExclusivePostConverter {
    public static DBObject toDBObject(ExclusiveDiscussion ed){
        int i=0;
        
        BasicDBObject object = new BasicDBObject();
        
        if(ed.getid()!=null){
            object.put("_id",ed.getid());
        }
        
        object.put("userId", ed.getUsername());
        object.put("topic", ed.getTopic());
        object.put("content", ed.getContent());
        object.put("status", ed.getStatus());
        object.put("date", ed.getDate());
        object.put("discussionDate",ed.getDiscussDate());
        
        BasicDBObject[] tags=new BasicDBObject[ed.getTags().size()];
        i=0;
        for(String tag : ed.getTags()){
            tags[i]=new BasicDBObject();
            tags[i].put("tag",tag);
            i++;
        }
        object.put("tags",tags);
        
        BasicDBObject[] votes=new BasicDBObject[ed.getVoteList().keySet().size()];
        i=0;
        for(String username : ed.getVoteList().keySet()){
        	votes[i]=new BasicDBObject();
        	votes[i].put("username", username);
        	votes[i].put("vote", ed.getVoteList().get(username));
        	i++;
        }
        object.put("votes", votes);
        
        BasicDBObject[] comments=new BasicDBObject[ed.getComments().size()];
        i=0;
        for(Comment comment : ed.getComments()){
        	comments[i]=(BasicDBObject) CommentConverter.toDBObject(comment);
        	i++;
        }
        object.put("comments", comments);
        
        BasicDBObject[] category=new BasicDBObject[ed.getCategory().size()];
        i=0;
        for(String cat : ed.getCategory()){
        	category[i]=new BasicDBObject();
        	category[i].put("cat", cat);
        	i++;
        }
        object.put("category", category);
        
        BasicDBObject[] userlist=new BasicDBObject[ed.getUserList().size()];
        i=0;
        for(String username : ed.getUserList()){
        	userlist[i]=new BasicDBObject();
        	userlist[i].put("username", username);
        	i++;
        }
        object.put("userlist", userlist);
                
        BasicDBObject[] guestUsers=new BasicDBObject[ed.getGuestUsers().keySet().size()];
	i=0;
        
                
        for(String username : ed.getGuestUsers().keySet()){
        	guestUsers[i]=new BasicDBObject();
        	guestUsers[i].put("gUsername", username);
                guestUsers[i].put("ustatus",ed.getGuestUsers().get(username));
        	i++;
        }
        object.put("guestUser", guestUsers);
        
        return object;
    }
    
    public static ExclusiveDiscussion toExclussiveDiscussion(DBObject object){
        int i=0;
        
        ExclusiveDiscussion ed = new ExclusiveDiscussion(object.get("userId").toString(),object.get("topic").toString(),object.get("content").toString());
        ed.setid(new ObjectId(object.get("_id").toString()));
        ed.setStatus(new Boolean(object.get("status").toString()));
        ed.setUsername(new String(object.get("userId").toString()));
        ed.setTopic(new String(object.get("topic").toString()));
        ed.setContent(new String(object.get("content").toString()));
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        
        try {
            ed.setDate(formatter.parse(object.get("date").toString()));
            ed.setDiscussDate(formatter.parse(object.get("discussionDate").toString()));
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
        ed.setTags(tags);
        
        HashMap<String, Integer> votes=null;
	BasicDBList voteObject=(BasicDBList) object.get("votes");
	if(voteObject!=null){
            votes=new HashMap<String, Integer>();
            for(i=0 ; i<voteObject.size() ; i++){
                votes.put(((BasicDBObject) voteObject.get(i)).get("username").toString(), (Integer)((BasicDBObject) voteObject.get(i)).get("vote"));
            }
	}
	ed.setVotes(votes);
        
        ArrayList<Comment> comments=null;
	BasicDBList commentsObject=(BasicDBList) object.get("comments");
	if(commentsObject!=null){
            comments=new ArrayList<Comment>();
            for(i=0 ; i<commentsObject.size() ; i++){
	        comments.add( CommentConverter.toComment( (BasicDBObject)commentsObject.get(i) ) );
	    }
	}
        ed.setComments(comments);
        
        ArrayList<String> category=null;
	BasicDBList catObject=(BasicDBList) object.get("category");
        if(catObject!=null){
            category=new ArrayList<String>();
            for(i=0 ; i<catObject.size() ; i++){
               	category.add(((BasicDBObject)catObject.get(i)).get("cat").toString());
	    }
        }
	ed.setCategory(category);
        
        ArrayList<String> userlist=null;
        BasicDBList userlistObject=(BasicDBList) object.get("userlist");
	if(userlistObject!=null){
            userlist=new ArrayList<String>();
            for(i=0 ; i<userlistObject.size() ; i++){
	        userlist.add(((BasicDBObject)userlistObject.get(i)).get("username").toString());
	    }
	}
	ed.setUserList(userlist);
        
        HashMap<String,Boolean> guestUsers=null;
        BasicDBList guestUserObject=(BasicDBList) object.get("guestUser");
	if(guestUserObject!=null){
            guestUsers=new HashMap<String,Boolean>();
            for(i=0 ; i<guestUserObject.size() ; i++){
               guestUsers.put(((BasicDBObject) guestUserObject.get(i)).get("gUsername").toString(),(Boolean)((BasicDBObject) guestUserObject.get(i)).get("ustatus"));
            }
	}
	ed.setGuestUsers(guestUsers);
        
        return ed;
    }
}
