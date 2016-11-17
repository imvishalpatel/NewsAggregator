package model;
import java.util.HashMap;
import java.util.UUID;

public class Comment {
    
	private String id;
    private String comment;
    private String username;
    private HashMap<String, Integer> votes;
    
    public Comment(String comment, String username){
    	setId(UUID.randomUUID().toString());
    	setComment(comment);
    	votes=new HashMap<String,Integer>();
    	setUserName(username);
    }
    
    public Comment(String comment, String username, String id){
    	setId(id);
    	setComment(comment);
    	votes=new HashMap<String,Integer>();
    	setUserName(username);
    }
    
    public void setComment(String comment){
    	this.comment=comment;
    }

    public String getComment(){
    	return comment;
    }
    
	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public HashMap<String, Integer> getVoteList() {
		return votes;
	}

	public int getVotes() {
		int totalvotes=0;
		for(Integer v : votes.values()){
			totalvotes+=v;
		}
		return totalvotes;
	}
	
	public void votes(int vote, String user) {
		votes.put(user, vote);
	}

	public void setVotes(HashMap<String, Integer> votes){
		this.votes=votes;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}