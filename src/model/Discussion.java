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
public class Discussion {
    private ObjectId postId;
	private String userId;
	private String topic;
        private String content;
	private ArrayList<String> tags=new ArrayList<String>();
       private ArrayList<HashMap<String,Integer>> voter=new ArrayList<HashMap<String,Integer>>();
       	private Boolean status;
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
	 public Boolean isOpen()
	{
	return this.status;
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
