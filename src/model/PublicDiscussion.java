package model;

import java.util.ArrayList;
import java.util.HashMap;
import org.bson.types.ObjectId;

public class PublicDiscussion extends Discussion{
	
	
	  private ArrayList<String> viewedBy=new ArrayList<String>();
        private ArrayList<Comments> comment=new ArrayList<Comments>(); 
        private ArrayList<String> category=new ArrayList<String>();
	private Boolean spam;
        private ArrayList<HashMap<String,String>> spamHistory=new ArrayList<HashMap<String,String>>();
public Boolean isSpam()
	{
	return this.spam;
	}
       
        
}
