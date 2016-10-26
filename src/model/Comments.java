/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.ArrayList;
import java.util.HashMap;
import org.bson.types.ObjectId;
/**
 *
 * @author BHAVESH GOYAL
 */
public class Comments {
    
    private ObjectId id;
    private CommentString cs;
    
    void setComment(String comment){
     cs.setComment(comment);
    }
    String getComment(){
        return cs.getComment();
    }
    void setVote(String user,int vot){
        
        cs.setVote(user,vot);
        
    }
   public ArrayList<HashMap<String,Integer>> get_votes(){
        return cs.getVotes();
    }
}
