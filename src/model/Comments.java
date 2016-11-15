/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author BHAVESH GOYAL
 */
public class Comments {
    
    private String id;
   private String commentLine;
   private String userId;
    private ArrayList<HashMap<String,Integer>> voter=new ArrayList<HashMap<String,Integer>>();
   
      public void setUserId(String userId)
        {
            this.userId=userId;
        }
    void setCommentId(String id)
    {
        this.id=id;
        
    
    }  
    public String getUsertId()
	{
	return this.userId;
	}
    String getCommentId()
    {
        return this.id;
        
    }
    void setComment(String comment){
        this.commentLine=comment;
    }
    String getComment(){
        return this.commentLine;
    }
    void setVote(String user,int vot){
        HashMap<String,Integer> v1=new HashMap<String,Integer>();
        v1.put(user,vot);
        voter.add(v1);
   
    }
    
   public ArrayList<HashMap<String,Integer>> getVotes(){
        return voter;
    }
    
  
}
