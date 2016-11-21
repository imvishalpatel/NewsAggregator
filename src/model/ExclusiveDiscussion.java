package model;

/**
 *
 * @author Herat Patel
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

public class ExclusiveDiscussion extends Discussion{
    
        private ArrayList<String> userlist;
	private ArrayList<String> category;
        private HashMap<String,Boolean> guestUser;
        private Date discussDate;
    
    public ExclusiveDiscussion(String username, String topic, String content, String[] category) {
        this(username, topic, content);
        this.category=new ArrayList<String>();
        
        for(String cat : category){
            this.category.add(cat);
        }
    }
    
    public ExclusiveDiscussion(String username, String topic, String content){
        super(username, topic, content);
        
        this.category=null;
    }
    
    public ArrayList<String> getUserList(){
        return userlist;
    }
    
    public void setUserList(ArrayList<String> userlist){
        this.userlist=userlist;
    }
    
    public ArrayList<String> getCategory(){
        return category;
    }
    
    public void setCategory(ArrayList<String> category){
        this.category=category;
    }
    
    public HashMap<String,Boolean> getGuestUsers(){
        return guestUser;
    }
    
    public void setGuestUsers(HashMap<String,Boolean> guestUser){
        this.guestUser=guestUser;
    }
    
    public Date getDiscussDate(){
        return discussDate;
    }
    
    public void setDiscussDate(Date discussDate){
        this.discussDate=discussDate;
    }
    
    public void view(String username){
        if(!userlist.contains(username))
            userlist.add(username);
    }
    
    public int getViewCount(){
        return userlist.size();
    }
}
