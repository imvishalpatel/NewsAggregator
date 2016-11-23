package service;

import java.util.HashMap;

import org.bson.types.BasicBSONList;

import model.Comment;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CommentConverter {
	
	static public DBObject toDBObject(Comment comment){
		BasicDBObject object=new BasicDBObject();
		
		object.put("id",comment.getId());
		object.put("comment",comment.getComment());
		object.put("username", comment.getUserName());
		
		BasicDBObject[] votes=new BasicDBObject[comment.getVoteList().keySet().size()];
		int i=0;
        for(String username : comment.getVoteList().keySet()){
        	votes[i]=new BasicDBObject();
        	votes[i].put("username", username);
        	votes[i].put("vote", comment.getVoteList().get(username));
        	i++;
        }
        object.put("votes", votes);
        object.put("date", comment.getDate());
        
		return object;
	}
	
	static public Comment toComment(DBObject object){
		if(object==null){
			System.out.println("Object null");
			return null;
		}
		
		Comment comment=new Comment(object.get("comment").toString(),object.get("username").toString(),object.get("id").toString());
		
		HashMap<String, Integer> votes=new HashMap<String, Integer>();
		BasicDBList voteObject=(BasicDBList) object.get("votes");
		for(int i=0 ; i<voteObject.size() ; i++){
			votes.put(((BasicDBObject) voteObject.get(i)).get("username").toString(), (Integer)((BasicDBObject) voteObject.get(i)).get("vote"));
		}
		comment.setVotes(votes);
		
                DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		try {
                        comment.setDate(formatter.parse(object.get("date").toString()));
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		return comment;
	}
}
