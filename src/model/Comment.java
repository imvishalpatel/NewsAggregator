package model;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class Comment {
    
	private String id;
    private String comment;
    private String username;
    private HashMap<String, Integer> votes;
    Date date;
    
    public Comment(String comment, String username){
    	setId(UUID.randomUUID().toString());
    	setComment(comment);
    	votes=new HashMap<String,Integer>();
    	setUserName(username);
        setDate(new Date());
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
    
    public void setDate(Date date){
    	this.date=date;
    }

    public Date getDate(){
    	return date;
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
public int getVoteValue(String username)
{
    if(this.votes.get(username)!=null)
    {
    if(this.votes.get(username)==1)
        return 1;
    else if(this.votes.get(username)==-1)
        return -1;
    else
        return 0;
     
}else
        return 0;
}
	public void setId(String id) {
		this.id = id;
	}
        
        public String getDateString()
         {
             Date d1=new Date();
             String st="";
             long diff=d1.getTime()-this.date.getTime();
            // System.out.print(diff /( 1000*60*60*24));
             if(diff / 1000<60)
             {
                 st=(diff / 1000)+" Seconds Ago";
             }
             else if((diff / (1000*60)) <60)
                     {
                         st=(diff / (1000*60))+" Minutes Ago";
                 
                     }
             else if((diff / (1000*60*60)) <24)
                     {
                         st=(diff / (1000*60*60))+" Hours Ago";
                 
                     }
             else if((diff / (1000*60*60*24)) <7)
                     {
                         st=(diff / (1000*60*60*24))+" Days Ago";
                 
                     }
            
             else
             {
                 SimpleDateFormat ft = 
                    new SimpleDateFormat ("dd.MM.yyyy");

                 st=ft.format(this.date);
             }
             
             return st;
         }
        
        public int getUpVotes() {
		int totalvotes=0;
		for(Integer v : votes.values()){
                    if(v==1)	
			totalvotes+=v;
		}
		return totalvotes;
	}
        
        public int getDownVotes() {
		int totalvotes=0;
		for(Integer v : votes.values()){
		if(v==-1)	
                    totalvotes+=v;
		}
		return Math.abs(totalvotes);
	}
}