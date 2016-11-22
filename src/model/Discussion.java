/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.bson.types.ObjectId;

public class Discussion {
    
	private ObjectId id;
	private String username;
	private String topic;
	private String content;
	private ArrayList<String> tags;
	//private String tags;
	private HashMap<String,Integer> votes;
    private Boolean status;
    private Date date;
    private ArrayList<Comment> comments;
    
    public Discussion(String username, String topic, String content){
    	status=true;
    	setDate(new Date());
    	votes=new HashMap<String,Integer>();
    	setComments(new ArrayList<Comment>());
    	id=null;
    	
    	setUsername(username);
    	setTopic(topic);
        String[] str=content.split("#");
        
        
    	setContent(str[0]);
    	setTags(content);
    }
       
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}
        public HashMap<String,Integer> getVotes()
        {
            return this.votes;
        }

	public void setContent(String content) {
		this.content = content;
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

	public void setVotes(HashMap<String, Integer> votes) {
		this.votes=votes;
	}

	public void vote(String username, int vote) {
		votes.put(username, vote);
	}
	
	public HashMap<String, Integer> getVoteList() {
		return votes;
	}
        
	
	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}


	public Date getDate() {
		return this.date;
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
	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<String> getTags() {
		return tags;
	}
	
	public void setTags(String content) {
		tags=new ArrayList<String>();
		String[] tagList=content.split("#");
		
		
		for(int i=1 ; i<tagList.length ; i++){
			addTag(tagList[i]);
		}
	}

	public void setTags(ArrayList<String> tags){
		this.tags=tags;
	}
	
	public void addTag(String tag){
		tags.add(tag);
	}
	
	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComments(String comment, String username) {
		comments.add(new Comment(comment, username));
	}

	public ObjectId getid() {
		return id;
	}

	public void setid(ObjectId id) {
		this.id = id;
	}
        
        
}
