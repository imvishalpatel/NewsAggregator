package model;

import java.util.ArrayList;
import java.util.HashMap;

public class PublicDiscussion extends Discussion{

	private ArrayList<String> userlist;
	private ArrayList<String> category;
	private boolean isSpam;
	private HashMap<String, String> spamHistory;
 	
	public PublicDiscussion(String username, String topic, String content, String[] category) {
		this(username, topic, content);
		
		this.category=new ArrayList<String>();
		
		for(String cat : category){
			this.category.add(cat);
		}
	}

	public PublicDiscussion(String username, String topic, String content){
		super(username, topic, content);
		
		setUserlist(new ArrayList<String>());
		setSpam(false);
		setSpamHistory(new HashMap<String, String>());
		
		this.category=null;
	}

	public ArrayList<String> getUserlist() {
		return userlist;
	}

	public void setUserlist(ArrayList<String> userlist) {
		this.userlist = userlist;
	}

	public ArrayList<String> getCategory() {
		return category;
	}

	public void setCategory(ArrayList<String> category) {
		this.category = category;
	}

	public boolean isSpam() {
		return isSpam;
	}

	public void setSpam(boolean isSpam) {
		this.isSpam = isSpam;
	}

	public HashMap<String, String> getSpamHistory() {
		return spamHistory;
	}

	public void setSpamHistory(HashMap<String, String> spamHistory) {
		this.spamHistory = spamHistory;
	}

	public void reportSpam(String username, String reason){
		setSpam(true);
		spamHistory.put(username, reason);
	}
	
	public void view(String username){
		
		if(!userlist.contains(username))
			userlist.add(username);
	}
	
	public int getViewCount(){
		return userlist.size();
	}
}
