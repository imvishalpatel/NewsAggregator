package model;

import java.util.ArrayList;
import java.util.HashMap;
import org.bson.types.ObjectId;

public class PublicDiscussionModel {
	
	private ObjectId postId;
	private String userId;
	private String topic;
	private String content;
	private ArrayList<String> tags=new ArrayList<String>();
        private ArrayList<String> viewedBy=new ArrayList<String>();
        private ArrayList<Comments> comment=new ArrayList<Comments>(); 
        private ArrayList<HashMap<String,Integer>> voter=new ArrayList<HashMap<String,Integer>>();
        private ArrayList<String> category=new ArrayList<String>();
	private Boolean spam;
	private Boolean open;
        private ArrayList<HashMap<String,String>> spamHistory=new ArrayList<HashMap<String,String>>();

        public void setPostId(ObjectId id)
        {
            this.postId=id;
        }
        public void setUserId(String userId)
        {
            this.userId=userId;
        }
        public void setTopic(String topic)
        {
            this.topic=topic;
        }
        public void setContent(String content)
        {
            this.content=content;
        }
        public void setTags(String tagString)
        {
            String[] str=tagString.split(",");
        
        	for(int i=0;i<str.length;i++)
                {
                    tags.add(str[i]);
                }
        }
	public void setPost(String userId,String topic,String content)
	{
	this.postId=postId;
        this.userId=userId;
        this.topic=topic;
        String[] str=content.split("#");
        this.content=str[0];
        	for(int i=1;i<str.length;i++)
                {
                    tags.add(str[i]);
                }
                if(tags.size()<=0)
                    tags.add(" ");
	}
	public ObjectId getPostId()
	{
	return this.postId;
	}
        
	public String getUsertId()
	{
	return this.userId;
	}
	public Boolean isSpam()
	{
	return this.spam;
	}
        public Boolean isOpen()
	{
	return this.open;
	}

	public String getContent()
	{
	return this.content;
	}
	public String getTopic()
	{
	return this.topic;
	}
        public String getTagsString()
	{
            String str="";
	for(int i=0;i<tags.size();i++)
        {
           str=str+tags.get(i)+",";
        }
        str=str.substring(0, str.length()-1);
        return str;
	}
}
